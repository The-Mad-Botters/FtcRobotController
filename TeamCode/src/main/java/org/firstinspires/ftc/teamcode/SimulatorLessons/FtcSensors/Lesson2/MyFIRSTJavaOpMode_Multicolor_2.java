package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcSensors.Lesson2;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

public class MyFIRSTJavaOpMode_Multicolor_2 extends LinearOpMode {
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
        
        // for the purposes of this lesson, we will assume the gate is either red or blue
        // if the gate is red, we will turn left, towards the open red gate and make a wide
        // "U" turn into the flag area on the left side of the field.
        // if the gate is blue, we will turn right, towards the open blue gate and make a wide
        // "U" turn into the flag area on the right side of the field.
        
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        // Move Forward to the lightning bolt
        moveIt(0.2, 0.2, 2.3, false);
        
        telemetry.addData("Red Value", color1.red());
        telemetry.addData("Blue Value", color1.blue());
        telemetry.update();
        
        // In this solution, I decided to make the "red" path the default path through the maze.
        // Knowing that the blue path is just a mirror opposite of the red path, I can use a boolean
        // variable to switch the left and right motor speeds if the color sensor tells me the color 
        // is not red. We assume it is blue if it is not red.
        boolean switchMotors = color1.red() != 255;

        // turn left towards open gate
        moveIt(-0.5, 0.5, 1, switchMotors);
        // move forword
        moveIt(0.5, 0.5, 0.6, false);
        // wide "U" turn through the gate
        moveIt(0.6, 0.08, 3.75, switchMotors);
        // move forword
        moveIt(0.6, 0.6, 1, false);
        // wide turn until flag
        moveIt(0.08, 0.6, 2.5, switchMotors);
                
    }

    private void moveIt(double leftSpeed, double rightSpeed, double seconds, boolean switchMotors) {
        if (switchMotors) {
            motorLeft.setPower(rightSpeed);
            motorRight.setPower(leftSpeed);
        } else {
            motorLeft.setPower(leftSpeed);
            motorRight.setPower(rightSpeed);
        }
        
        sleep((long) (seconds * 1000));

        motorLeft.setPower(0);
        motorRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
    }
}
