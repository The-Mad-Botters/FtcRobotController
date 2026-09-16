package org.firstinspires.ftc.teamcode.SimulatorLessons.Strafing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * This file contains a solution to Lesson 3 and builds on top of the encoder based solution in Lesson 2.
 * <p>
 * Note: this code also works around a bug in the VRS simulator that doesn't reset the encoder
 * position to zero when you would expect it to on a "real" FTC robot.
 */

@TeleOp(name = "Lesson3_strafe_the_solution", group = "Tutorial")
public class Lesson3_strafe_the_solution extends LinearOpMode {
    // create DcMotor objects
    private DcMotor frontRight;
    private DcMotor frontLeft;
    private DcMotor backRight;
    private DcMotor backLeft;

    @Override
    public void runOpMode() {
        // initialize hardware variables
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");

        // Set motor direction
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        // when started
        if (opModeIsActive()) {
            moveStraight(4000, frontRight, frontLeft, backRight, backLeft);
            sleepSec(1);

            strafeLeft(3500, frontRight, frontLeft, backRight, backLeft);
            sleepSec(1);

            moveStraight(-6000, frontRight, frontLeft, backRight, backLeft);
        }
    }

    private void sleepSec(int seconds) {
        sleep(seconds * 1000);
    }

    /**
     * Moves the robot forward for a specified distance using encoder counts.
     * Note: Normally, we wouldn't need to pass in the motor objects, but the simulator seems
     * to have ANOTHER BUG accessing the motor object properties in the class object.
     * But, we can work around this by having the caller give us the motor objects.
     */
    private void moveStraight(int distanceTicks, DcMotor frontRight, DcMotor frontLeft, DcMotor backRight, DcMotor backLeft) {
        double power = 0.5;

        if (distanceTicks < 0) {
            power = -0.5;
        }
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + distanceTicks);

        // loop until reach target position
        // - If the power is positive, we're moving forward so we need to check if the current position is less than the target
        // - If the power is negative, we're moving backwards so we need to check if the current position is greater than the target
        while (
                (power > 0 && frontLeft.getCurrentPosition() < frontLeft.getTargetPosition()) ||
                        (power < 0 && frontLeft.getCurrentPosition() > frontLeft.getTargetPosition())
        ) {
            frontRight.setPower(power);
            frontLeft.setPower(power);
            backRight.setPower(power);
            backLeft.setPower(power);
            // displays encoder position
            telemetry.addData("Moving Straight", frontLeft.getCurrentPosition());
            telemetry.update();
            sleep(50);
        }
        stopAllMotors(frontRight, frontLeft, backRight, backLeft);
    }

    private void stopAllMotors(DcMotor frontRight, DcMotor frontLeft, DcMotor backRight, DcMotor backLeft) {
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
    }

    /**
     * Moves the robot sideways to the left without actually turning. Requires Mecanum or Omni wheels.
     * This method is used to strafe left for a specified duration. It does this by setting the
     * front right and back left motors to spin inwards towards the left side of the robot,
     * while the front left and back right motors spin outwards away from the left side.
     * 
     *            [Front]   
     *       ┌────┬──────┬────┐↑↑↑
     *    ↓↓↓│ FL │      │ FR │ 
     *       └────┤      ├────┘ 
     *   <----    |      |
     *       ┌────┤      ├────┐
     *    ↑↑↑│ BL │      │ BR │
     *       └────┴──────┴────┘↓↓↓ 
     *             [Back]   
     */
    private void strafeLeft(int milliseconds, DcMotor frontRight, DcMotor frontLeft, DcMotor backRight, DcMotor backLeft) {
        double turnPower = 0.5;
        // Motors spinning "inwards" towards the left side of the robot
        frontLeft.setPower(-turnPower); // ↓
        backLeft.setPower(turnPower);   // ↑
        
        // Motors spinning "outwards" away from the left side of the robot
        frontRight.setPower(turnPower); // ↑
        backRight.setPower(-turnPower); // ↓

        sleep(milliseconds);
        stopAllMotors(frontRight, frontLeft, backRight, backLeft);
    }
}
