package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcSensors.Lesson4;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/*
 * For this solution, we will need to read 2 panels to determine which gates open
 * based on the patterns you can identify by running "reset" and "run" several times
 * as the field will rotate them. Here is what I observed. Using the starting location
 * as "North" and the flag as "South"
 *
 * +----------------+----------------+-------------------------------+
 * | NE panel color | NW panel color | Gates that are open           |
 * +----------------+----------------+-------------------------------+
 * | Blue           | Red            | West field gates              |
 * | Blue           | Blue           | Second-from-West gates        |
 * | Red            | Red            | Second-from-East gates        |
 * | Red            | Blue           | East field gates              |
 * +----------------+----------------+-------------------------------+
 *
 * This means, we will need to make the robot drive over both panels and remember
 * the color of each one in order to determine which gates to go through.
 */
public class MyFIRSTJavaOpMode_Pattern_WithEncoder extends LinearOpMode {
    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor frontLeft;
    DcMotor frontRight;
    ColorSensor color1;
    DistanceSensor distance1;
    BNO055IMU imu;
    int distanceBetweenPanels;
    double distanceThroughGate;

    @Override
    public void runOpMode() {
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        color1 = hardwareMap.get(ColorSensor.class, "color1");
        distance1 = hardwareMap.get(DistanceSensor.class, "distance1");
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        distanceThroughGate = 3.5;

        // Put initialization blocks here
        waitForStart();

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        // let's read the first panel at the top left of the field
        goToFirstPanel();
        boolean eastPanelIsRed = (color1.red() == 255); // will be true if color is red, otherwise false.

        // now we read the 2nd panel
        goToSecondPanel();
        boolean westPanelIsRed = (color1.red() == 255); // will be true if color is red, otherwise false.


        // now we need to back up and read the color

        telemetry.addData("Red Value", color1.red());
        telemetry.addData("Blue Value", color1.blue());
        telemetry.addData("eastPanelIsRed", eastPanelIsRed);
        telemetry.addData("westPanelIsRed", westPanelIsRed);
        telemetry.update();

        if (eastPanelIsRed && !westPanelIsRed) {
            goThroughEastGates();
        }
        if (eastPanelIsRed && westPanelIsRed) {
            goThroughSecondFromEastGates();
        }
        if (!eastPanelIsRed && westPanelIsRed) {
            goThroughWestGates();
        }
        if (!eastPanelIsRed && !westPanelIsRed) {
            goThroughSecondFromWestGates();
        }

    }

    /* private methods for each combination of eastPanelIsRed and westPanelIsRed
     * +----------------+----------------+-------------------------------+
     * | NE panel color | NW panel color | Gates that are open           |
     * +----------------+----------------+-------------------------------+
     * | Blue           | Red            | West field gates              |
     * | Blue           | Blue           | Second-from-West gates        |
     * | Red            | Red            | Second-from-East gates        |
     * | Red            | Blue           | East field gates              |
     * +----------------+----------------+-------------------------------+
     * */
    // West field gates
    private void goThroughWestGates() {
        goToAndThroughGates(-1);
        rotate(90);
        moveLinear(2);
        rotate(-45);
        moveLinear(1);
    }
    // Second-from-West gates
    private void goThroughSecondFromWestGates() {
        goToAndThroughGates(1.1);
        rotate(45);
        moveLinear(1);
    }
    // Second-from-East gates
    private void goThroughSecondFromEastGates() {
        goToAndThroughGates(2.9);
        rotate(-45);
        moveLinear(1);
    }
    // East field gates
    private void goThroughEastGates() {
        goToAndThroughGates(4.8);
        rotate(-90);
        moveLinear(2);
        rotate(45);
        moveLinear(1);
    }

    // goes to the gate and turns to face it
    private void goToAndThroughGates(double distanceFromWestPanel) {
        moveLinear(distanceFromWestPanel);
        rotate(-90);
        moveLinear(distanceThroughGate);
    }

    private void goToSecondPanel() {
        moveLinear(-3.9);
    }

    private void goToFirstPanel() {
        moveLinear(0.6);
        // the robot isn't quite straight,
        rotate(91);
        moveLinear(1.9);
    }

    private void rotate(int degrees) {
        int ticks = (int) (degrees * 15.55556);
        encoderDrive(0.5, 0.5, ticks, -ticks);
    }

    private void moveLinear(double inches) {
        int ticks = 1000 * (int)inches;
        encoderDrive(0.5, 0.5, ticks, ticks);
    }

    /*
     *  Method to perform a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop when it gets to the desired position.
     */
    private void encoderDrive(double speedLeft, double speedRight, int leftTicks, int rightTicks) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the OpMode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = motorLeft.getCurrentPosition() + (leftTicks);
            newRightTarget = motorRight.getCurrentPosition() + (rightTicks);
            motorLeft.setTargetPosition(newLeftTarget);
            motorRight.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            motorLeft.setPower(speedLeft); //Math.abs(speed));
            motorRight.setPower(speedRight); //Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (motorLeft.isBusy() && motorRight.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Left is going to", newLeftTarget);
                telemetry.addData("Right is going to", newRightTarget);
                telemetry.addData("Currently at left", motorLeft.getCurrentPosition());
                telemetry.addData("Currently at right", motorRight.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            motorLeft.setPower(0);
            motorRight.setPower(0);

            // Turn off RUN_TO_POSITION
            motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }
}