
package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcMovement.Lesson10;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

public class MyFIRSTJavaOpMode_10_Solution_Methoda extends LinearOpMode {
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

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        moveStraight(-1, .7);
        rotateLeft(1.45);
        moveStraight(1, .42);
        rotateRight(1.45);
        wideLeftTurn(3);
    }
    private void moveStraight(double speed, double seconds) {
        moveIt(speed, speed, seconds);
    }
    private void rotateLeft(double seconds) {
        moveIt(-0.35, 0.35, seconds);
    }
    private void rotateRight(double seconds) {
        moveIt(0.35, -0.35, seconds);
    }
    private void wideLeftTurn(double seconds) {
        moveIt(0.05, 0.55, seconds);
    }


    private void moveIt(double leftSpeed, double rightSpeed, double seconds) {
        motorLeft.setPower(leftSpeed);
        motorRight.setPower(rightSpeed);
        sleep((long) (seconds * 1000));

        motorLeft.setPower(0);
        motorRight.setPower(0);
    }
}