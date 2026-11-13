package org.firstinspires.ftc.teamcode.teamcode.mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * The pollen shooter: one spinning flywheel ("pShooter") and one flipper servo ("pFlipper")
 * that pushes a ball into the flywheel.
 *
 * Two ways to run the flywheel:
 *  - POWER mode (TeleOp): setPollenShooterPower(0.7) -> "use 70% power".
 *  - RPM mode (Autos):    setTargetRpm(3000)         -> "hold 3000 RPM", the motor fixes itself
 *                                                        when a ball slows it down.
 * RPM mode is better for autos because the shot is the same even when the battery is low.
 *
 * Shooting in autos never blocks (no sleep). Call startShooting() once, then call
 * updateShooting() every loop until it returns true.
 */
public class Shooter {

    // ---------------- Tunable numbers ----------------

    // How many encoder "ticks" the flywheel motor counts for ONE turn of the flywheel.
    // 14 comes from last season's shooter (28 ticks per motor turn, geared 2:1).
    // TODO (coach): VERIFY on the real robot! We could not find which motor the pollen
    // shooter uses. How to check: in a test OpMode call setPollenShooterPower(1.0), show
    // getRpm() on telemetry, and compare with the motor's spec sheet free speed
    // (e.g. a goBILDA 6000 RPM motor at full power on a good battery reads about 5500-6000).
    // If telemetry shows about HALF the real speed, use 28. If about DOUBLE, use 7.
    public static double TICKS_PER_REV = 14;

    // We never ask for more than this. (Most FTC motors top out around 6000 RPM.)
    public static double MAX_RPM = 6000;

    // "Close enough" to the target RPM to shoot. Example: target 3000, tolerance 75
    // means anything from 2925 to 3075 counts as "at speed".
    public static double RPM_TOLERANCE = 75;

    // Flipper servo positions (same numbers TeleOp has always used).
    public static double FLIPPER_OPEN_POS = 1.0;
    public static double FLIPPER_CLOSED_POS = 0.0;

    // How long (milliseconds) to wait for the flywheel to reach speed before each ball.
    // If it never gets there (low battery, something rubbing), we shoot anyway after this
    // long so the auto can't get stuck forever.
    public static double MAX_SPINUP_MS = 1500;

    // How long the flipper stays open to push one ball in. Too short = ball doesn't go.
    public static double FLIP_OPEN_MS = 500;

    // How long the flipper stays closed before the next ball. This gives the next ball time
    // to roll into place and the flywheel time to recover its speed.
    public static double FLIP_CLOSE_MS = 500;

    // ---------------- Hardware ----------------

    DcMotorEx pollenShooter;
    Servo pollenFlipper;
    HardwareMap hardwareMap;

    // The RPM we are trying to hold. 0 means "not in RPM mode".
    private double targetRpm = 0;

    // ---------------- Shooting sequence (state machine) ----------------
    // A "state machine" just means: remember which part of the job we are on,
    // and each loop check if it's time to move to the next part.
    private static final int IDLE = 0;          // not shooting
    private static final int SPINNING_UP = 1;   // waiting for flywheel speed
    private static final int FLIPPER_OPEN = 2;  // pushing a ball in
    private static final int FLIPPER_CLOSING = 3; // flipper back, waiting for next ball
    private static final int DONE = 4;          // all balls shot

    private int shootState = IDLE;
    private int ballsToShoot = 0;
    private int ballsShot = 0;
    private boolean lastSpinUpTimedOut = false;
    private final ElapsedTime shootTimer = new ElapsedTime();

    public double f = 0.0009434;
    public PIDCoefficients pidCoefficients = new PIDCoefficients(
            115,
            1.10,
            3.0);
//    public PIDCoefficients pidCoefficients = new PIDCoefficients(
//            100,
//            1.05,
//            3.0);

    public void init(HardwareMap hwMap) {
        this.hardwareMap = hwMap;
        pollenShooter = this.hardwareMap.get(DcMotorEx.class, "pShooter");

        pollenShooter.setVelocityPIDFCoefficients(
                pidCoefficients.p,
                pidCoefficients.i,
                pidCoefficients.d,
                f);


        pollenFlipper = this.hardwareMap.get(Servo.class, "pFlipper");

        pollenShooter.setDirection(DcMotorSimple.Direction.REVERSE);
        // RUN_USING_ENCODER lets the motor count its own speed. setVelocity() needs this.
        pollenShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // OPTIONAL PIDF HOOK (leave commented until you tune it on THIS motor).
        // PIDF numbers tell the motor how hard to correct when it's too slow or too fast.
        // The built-in defaults usually work OK. If the RPM swings up and down a lot
        // ("pendulum swing"), tune and set them here. Last season's shooter used
        // p=100, i=1.05, d=3.0, f=0.0009434 -- but that was a DIFFERENT motor and setup,
        // so do NOT just copy them; start from those and test.
        // pollenShooter.setVelocityPIDFCoefficients(100, 1.05, 3.0, 0.0009434);

        pollenFlipper.setPosition(FLIPPER_CLOSED_POS);
    }

    // ================= Old methods (TeleOp and older autos use these) =================

    public void setPollenShooter(double speed) {
        setPollenShooterPower(speed);
    }

    public void turnOnShooter() {
        setPollenShooterPower(0.75);
    }

    /** POWER mode. Clears any target RPM so power mode and RPM mode never fight. */
    public void setPollenShooterPower(double power) {
        if (targetRpm > 0) {
            // Coming from RPM mode: zero the velocity target first. The SDK sometimes skips
            // a setPower() that repeats the last power it sent, which could leave the
            // flywheel spinning at the old RPM. This makes sure "off" really means off.
            this.pollenShooter.setVelocity(0);
        }
        targetRpm = 0;
        this.pollenShooter.setPower(power);
    }

    public void turnOffShooter() {
        setPollenShooterPower(0);
    }

    public void flipperPosition(float rightTrigger) {
        pollenFlipper.setPosition(rightTrigger);
    }

    // ================= RPM methods (ported from last season's tested code) =================

    /** RPM mode: ask the motor to hold this speed. 0 turns the flywheel off. */
    public void setTargetRpm(double rpm) {
        targetRpm = Math.max(0, Math.min(rpm, MAX_RPM));
        // The motor wants "ticks per second", so convert: RPM / 60 = turns per second.
        double ticksPerSecond = (targetRpm / 60.0) * TICKS_PER_REV;
        pollenShooter.setVelocity(ticksPerSecond);
    }

    /** How fast the flywheel is really spinning right now. */
    public double getRpm() {
        // getVelocity() gives "ticks per second". Turn that into turns per minute.
        double ticksPerSecond = pollenShooter.getVelocity();
        return (ticksPerSecond / TICKS_PER_REV) * 60.0;
    }

    public double getTargetRpm() {
        return targetRpm;
    }

    /** True when the flywheel is close enough to the target RPM to shoot. */
    public boolean isAtSpeed() {
        // A target under 100 RPM means "off" -- never say we're ready to shoot.
        if (targetRpm < 100) {
            return false;
        }
        return Math.abs(targetRpm - getRpm()) <= RPM_TOLERANCE;
    }

    public void openFlipper() {
        pollenFlipper.setPosition(FLIPPER_OPEN_POS);
    }

    public void closeFlipper() {
        pollenFlipper.setPosition(FLIPPER_CLOSED_POS);
    }

    /** Everything off: flywheel stopped, flipper closed, shooting sequence cancelled. */
    public void stop() {
        shootState = IDLE;
        turnOffShooter();
        closeFlipper();
    }

    /**
     * Stop a shooting sequence part-way (for example, a step ran out of time).
     * Closes the flipper but keeps the flywheel spinning.
     */
    public void cancelShooting() {
        if (shootState != IDLE && shootState != DONE) {
            shootState = IDLE;
            closeFlipper();
        }
    }

    // ================= Shooting sequence (non-blocking) =================

    /**
     * Begin shooting some balls at an RPM. Call ONCE, then call updateShooting() every loop.
     * The flywheel keeps spinning after the last ball (handy if you shoot again soon).
     * Call stop() to turn it off.
     */
    public void startShooting(double rpm, int balls) {
        setTargetRpm(rpm);
        ballsToShoot = balls;
        ballsShot = 0;
        lastSpinUpTimedOut = false;
        closeFlipper();
        shootTimer.reset();
        shootState = (balls > 0) ? SPINNING_UP : DONE;
    }

    /**
     * Call every loop. Returns true when all the balls are shot (or if nothing was started).
     * Every wait has a time limit, so this always finishes.
     */
    public boolean updateShooting() {
        double ms = shootTimer.milliseconds();

        if (shootState == SPINNING_UP) {
            boolean ready = isAtSpeed();
            boolean tooLong = ms > MAX_SPINUP_MS;
            if (ready || tooLong) {
                lastSpinUpTimedOut = !ready;   // remember for telemetry
                openFlipper();
                shootTimer.reset();
                shootState = FLIPPER_OPEN;
            }
        } else if (shootState == FLIPPER_OPEN) {
            if (ms > FLIP_OPEN_MS) {
                closeFlipper();
                shootTimer.reset();
                shootState = FLIPPER_CLOSING;
            }
        } else if (shootState == FLIPPER_CLOSING) {
            if (ms > FLIP_CLOSE_MS) {
                ballsShot++;
                shootTimer.reset();
                if (ballsShot >= ballsToShoot) {
                    shootState = DONE;
                } else {
                    shootState = SPINNING_UP;   // get ready for the next ball
                }
            }
        }

        // IDLE (never started / stopped) and DONE both count as "finished".
        return shootState == DONE || shootState == IDLE;
    }

    /** A short sentence for telemetry, like "Ball 2 of 3: spinning up". */
    public String shootingStatus() {
        String ball = "Ball " + Math.min(ballsShot + 1, ballsToShoot) + " of " + ballsToShoot + ": ";
        if (shootState == SPINNING_UP) {
            return ball + "spinning up";
        } else if (shootState == FLIPPER_OPEN) {
            return ball + "flipper open" + (lastSpinUpTimedOut ? " (spin-up TIMED OUT)" : "");
        } else if (shootState == FLIPPER_CLOSING) {
            return ball + "flipper closing";
        } else if (shootState == DONE) {
            return "Done: shot " + ballsShot + " of " + ballsToShoot;
        }
        return "Not shooting";
    }
}
