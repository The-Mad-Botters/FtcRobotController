
package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcMovement.Lesson9;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

public class MyFIRSTJavaOpMode_9_Solution_Methods extends LinearOpMode {
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

        rotateRight(0.9);
        moveForward(0.85, 0.55);
        wideLeftTurn(3);
        wideReverseRightTurn(5);
    }

    private void wideLeftTurn(double seconds) {
        moveIt(0.05, 0.55, seconds);
    }
    private void wideReverseRightTurn(double seconds) {
        moveIt(-0.55, -0.05, seconds);
    }
    private void moveForward(double speed, double seconds) {
        moveIt(speed, speed, seconds);
    }
    private void rotateRight(double seconds) {
        moveIt(0.35, -0.35, seconds);
    }

    private void moveIt(double leftSpeed, double rightSpeed, double seconds) {
        motorLeft.setPower(leftSpeed);
        motorRight.setPower(rightSpeed);
        sleep((long) (seconds * 1000));

        motorLeft.setPower(0);
        motorRight.setPower(0);
    }
}