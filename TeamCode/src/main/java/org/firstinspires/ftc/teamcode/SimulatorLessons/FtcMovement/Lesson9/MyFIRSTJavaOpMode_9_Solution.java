
package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcMovement.Lesson9;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

public class MyFIRSTJavaOpMode_9_Solution extends LinearOpMode {
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

        // turn right
        moveIt(0.35, -0.35, 0.9);

        // Move Forward a bit until we can wide turn into the flag area
        moveIt(0.55, 0.55, .85);

        // turn wide left until you reach the duck
        moveIt(0.05, 0.55, 3);
        
        // backwards wide left turn until we get to the circle
        moveIt(-0.55, -0.05, 5);
        

    }

    private void moveIt(double leftSpeed, double rightSpeed, double seconds) {
        motorLeft.setPower(leftSpeed);
        motorRight.setPower(rightSpeed);
        sleep((long) (seconds * 1000));

        motorLeft.setPower(0);
        motorRight.setPower(0);
    }
}