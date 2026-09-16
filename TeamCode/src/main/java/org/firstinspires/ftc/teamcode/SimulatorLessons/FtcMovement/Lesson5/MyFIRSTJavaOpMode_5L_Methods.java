
package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcMovement.Lesson5;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

public class MyFIRSTJavaOpMode_5L_Methods extends LinearOpMode {
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
        // Put initialization blocks here
        waitForStart();
        
        // Put run blocks here. 
        
        // First, set the left motor to the reverse directoin
        // since it is installed on the robot the opposite of the
        // right motor. This way we don't have to remember
        // to always use an inverse value for the left motor to get
        // it to go the direction we want
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        // Move Forward to the lightning bolt
        moveForward(1, 1100);

        // turn towards the rubber duck
        turnLeft(0.35, 1350);

        // Move forward to the rubber duck
        moveForward(1, 700);

        // Back up until it reaches the flag
        moveForward(-1, 1500);
    }

    private void moveForward(double speed, int milliseconds) {
        motorLeft.setPower(speed);
        motorRight.setPower(speed);
        sleep(milliseconds);
        stopAllMotors();
    }

    private void turnLeft(double power, int milliseconds) {
        motorRight.setPower(power);
        motorLeft.setPower(-power);
        sleep(milliseconds);
        stopAllMotors();
    }

    public void stopAllMotors() {
        motorLeft.setPower(0);
        motorRight.setPower(0);
    }
}