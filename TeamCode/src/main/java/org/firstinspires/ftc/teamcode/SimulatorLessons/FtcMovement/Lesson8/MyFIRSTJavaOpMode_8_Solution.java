
package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcMovement.Lesson8;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

public class MyFIRSTJavaOpMode_8_Solution extends LinearOpMode {
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

        // turn wide left
        moveIt(0.55, 0.05, 0.75);

        // turn wide right
        moveIt(0.05, 0.55, 1.35);
        
        // turn wide left until straight
        moveIt(0.55, 0.05, .75);

        // Move Forward to be perpendicular to longest wall
        moveIt(.5,.5, 1);

        // turn right
        moveIt(0.35, -0.35, 1.3);
        
        // Move Forward through the longest "hall"
        moveIt(1, 1, 1.3);
       
        // turn wide right 
        moveIt(0.55, 0.05, 1.7);
        
        // Move Forward a bit until we can wide turn into the flag area
        moveIt(0.55, 0.55, 1.0);
        
        // turn wide right one last time into the flag circle
        moveIt(0.55, 0.05, 3.5);

    }

    private void moveIt(double leftSpeed, double rightSpeed, double seconds) {
        motorLeft.setPower(leftSpeed);
        motorRight.setPower(rightSpeed);
        sleep((long) (seconds * 1000));

        motorLeft.setPower(0);
        motorRight.setPower(0);
    }
}