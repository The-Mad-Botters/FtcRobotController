
package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcMovement.Lesson8;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

/**
 * In this example, I created descriptive methods for each type of movement the robot needs to do.
 * This is not much different than some of the *_Methods.java example files in previous lessons, but
 * unlike the previous examples, the methods I create here actually use the generic moveIt() method 
 * under the hood. This way, I can give each method a clear name that describes what it does without
 * needing to repeat the same "matorLeft.setPower(...); motorRight.setPower(...); sleep(...);" code
 * over and over again. You might hear some programmers call it a "wrapper method" or "convienience method".
 * 
 * Note: you might not save a lot of lines of code here, but the clarity is worth it!
 * As the robot's tasks get more complex, the benefits of clear method names become even more
 * apparent.
 */

public class MyFIRSTJavaOpMode_8_Solution_Methods extends LinearOpMode {
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

        wideRightTurn(0.75);
        wideLeftTurn(1.35);
        wideRightTurn(0.75);
        moveForward(0.55, 1);
        rotateRight(1.3);
        moveForward(1, 1.3);
        wideRightTurn(1.7);
        moveForward(0.55, 1.0);
        wideRightTurn(3.5);

    }
    
    private void wideLeftTurn(double seconds) {
        moveIt(0.05, 0.55, seconds);
    }
    private void wideRightTurn(double seconds) {
        moveIt(0.55, 0.05, seconds);
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