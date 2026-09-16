package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcSensors.Lesson3;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

public class MyFIRSTJavaOpMode_TintedMaze extends LinearOpMode {
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
                
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        // Move Forward to the lightning bolt
        moveForward(0.2, 2.8);
        
        telemetry.addData("Red Value", color1.red());
        telemetry.addData("Blue Value", color1.blue());
        telemetry.update();
        
        if (color1.red() == 255) {
            moveBackward(0.2, 2.8);
            rotateRight(1);
            moveForward(1, 0.6);
            rotateLeft(1);
            moveForward(1, 1.2);
            rotateRight(1);
            moveForward(1, 0.8);
        } 
        else if (color1.blue() == 255) {
            moveForward(0.2, 4);
            rotateRight(0.95);
            moveForward(1, 0.8);
            rotateRight(0.95);
            moveForward(1, .7);
            rotateLeft(1);
            moveForward(1, 0.6);
            rotateRight(1);
            moveForward(1, 0.3);
        }
        
    }
    private void moveForward(double speed, double seconds) {
        moveIt(speed, speed, seconds);
    }
    private void moveBackward(double speed, double seconds) {
        moveIt(-speed, -speed, seconds);
    }
    private void rotateLeft(double seconds) {
        moveIt(-0.5, 0.5, seconds);
    }
    private void rotateRight(double seconds) {
        moveIt(0.5, -0.5, seconds);
    }

    private void moveIt(double leftSpeed, double rightSpeed, double seconds) {
        motorLeft.setPower(leftSpeed);
        motorRight.setPower(rightSpeed);
        sleep((long) (seconds * 1000));

        motorLeft.setPower(0);
        motorRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
    }
}