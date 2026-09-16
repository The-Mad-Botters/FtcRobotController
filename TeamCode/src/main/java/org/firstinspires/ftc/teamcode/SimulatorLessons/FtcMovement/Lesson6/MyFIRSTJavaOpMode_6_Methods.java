
package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcMovement.Lesson6;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

public class MyFIRSTJavaOpMode_6_Methods extends LinearOpMode {
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

        // Move Forward to the lightning bolt
        moveForward(1, 1.3);

        // turn right
        turnRight(0.35, 1.35);

        // Move Forward to the lightning bolt
        moveForward(1, 1.3);

        // turn right
        turnRight(0.35, 1.45);

        // Move Forward to the lightning bolt
        moveForward(1, 1.2);

        // turn right
        turnRight(0.35, 1.45);

        // Move Forward to the lightning bolt
        moveForward(1, .55);

    }

    private void moveForward(double speed, double seconds) {
        motorLeft.setPower(speed);
        motorRight.setPower(speed);
        sleep(secondsToMilliseconds(seconds));
        stopAllMotors();
    }

    private void turnRight(double power, double seconds) {
        motorRight.setPower(-power);
        motorLeft.setPower(power);
        sleep(secondsToMilliseconds(seconds));
        stopAllMotors();
    }

    private void stopAllMotors() {
        motorLeft.setPower(0);
        motorRight.setPower(0);
    }

    private long secondsToMilliseconds(double seconds) {
        return (long)(seconds * 1000);
    }
}