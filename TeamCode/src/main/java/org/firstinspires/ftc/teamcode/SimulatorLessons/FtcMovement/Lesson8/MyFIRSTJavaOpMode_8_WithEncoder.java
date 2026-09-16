
package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcMovement.Lesson8;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

// In the previous examples, we use time as a means to control the robot movement.
// But the DcMotors provide a way for us to use distance (in ticks) as a more precise
// mechanism for controlling how far the robot moves.
public class MyFIRSTJavaOpMode_8_WithEncoder extends LinearOpMode {
    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor frontLeft;
    DcMotor frontRight;
    ColorSensor color1;
    DistanceSensor distance1;
    BNO055IMU imu;

    @Override
    public void runOpMode() {
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        color1 = hardwareMap.get(ColorSensor.class, "color1");
        distance1 = hardwareMap.get(DistanceSensor.class, "distance1");
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        // First, set the left motor to the reverse direction
        // since it is installed on the robot the opposite of the
        // right motor. This way we don't have to remember
        // to always use an inverse value for the left motor to get
        // it to go the direction we want
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Put initialization blocks here
        waitForStart();

        // weave through the first "S" path until we reach the long hall
        // using a combination of wide turns and small forward movements so we
        // avoid hitting the wall.
        moveLinear(.3);
        turnWideRight(1.1);
        moveLinear(.35);
        turnWideLeft(1.9);
        moveLinear(.4);
        turnWideRight(0.75);
        moveLinear(1.0);

        // now turn to face the end of the long hall and move forward until
        // we reach the bend
        rotate(90);
        moveLinear(4.0);

        // turn towards the wide bend with a wide right turn, then move forward until we
        // reach near the end of the wall to the right of the bot
        turnWideRight(3.1);
        moveLinear(1.6);

        // now we can take a wide turn until we reach the flag
        turnWideRight(5);

    }

    private void turnWideLeft(double outerInches) {
        int rightTicks = 1000 * (int)outerInches;
        int leftTicks = rightTicks / 5;
        encoderDrive(0.05, 1, leftTicks, rightTicks);
    }

    private void turnWideRight(double outerInches) {
        int leftTicks = 1000 * (int)outerInches;
        int rightTicks = leftTicks / 5;
        encoderDrive(1, 0.05, leftTicks, rightTicks);
    }

    private void rotate(int degrees) {
        int ticks = (int) (degrees * 15.55556);
        encoderDrive(0.5, 0.5, ticks, -ticks);
    }

    private void moveLinear(double inches) {
        int ticks = 1000 * (int)inches;
        encoderDrive(1, 1, ticks, ticks);
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