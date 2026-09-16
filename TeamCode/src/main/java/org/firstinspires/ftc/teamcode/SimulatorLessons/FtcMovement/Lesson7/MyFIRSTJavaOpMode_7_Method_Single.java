
package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcMovement.Lesson7;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

public class MyFIRSTJavaOpMode_7_Method_Single extends LinearOpMode {
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
        moveIt(0.35, -0.35, 1.35);

        // Move Forward to the lightning bolt
        moveIt(1, 1, .7);

        // wide left turn
        moveIt(0.05, 0.55, 3);
        
        // wide right turn
        moveIt(0.35, 0.05, 2.2);
        
        // Move Forward to the lightning bolt
        moveIt(0.05, 0.05, 0.3);

    }

    private void moveIt(double leftSpeed, double rightSpeed, double seconds) {
        motorLeft.setPower(leftSpeed);
        motorRight.setPower(rightSpeed);
        sleep((long) (seconds * 1000));

        motorLeft.setPower(0);
        motorRight.setPower(0);
    }
}
