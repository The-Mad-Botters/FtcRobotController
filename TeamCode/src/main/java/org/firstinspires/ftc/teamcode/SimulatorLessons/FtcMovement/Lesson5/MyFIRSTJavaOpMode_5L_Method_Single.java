
package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcMovement.Lesson5;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

public class MyFIRSTJavaOpMode_5L_Method_Single extends LinearOpMode {
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
        
        // First, set the left motor to the reverse direction
        // since it is installed on the robot the opposite of the
        // right motor. This way we don't have to remember
        // to always use an inverse value for the left motor to get
        // it to go the direction we want
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        // Move Forward to the lightning bolt
        moveIt(1, 1, 1.1);

        // turn towards the rubber duck
        moveIt(-0.35, 0.35, 1.35);

        // Move forward to the rubber duck
        moveIt(1, 1, 0.7);

        // Back up until it reaches the flag
        moveIt(-1, -1, 1.5);
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