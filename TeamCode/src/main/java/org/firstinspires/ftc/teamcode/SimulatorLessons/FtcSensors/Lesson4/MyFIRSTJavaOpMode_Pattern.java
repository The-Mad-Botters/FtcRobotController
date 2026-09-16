package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcSensors.Lesson4;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/*
 * For this solution, we will need to read 2 panels to determine which gates open
 * based on the patterns you can identify by running "reset" and "run" several times
 * as the field will rotate them. Here is what I observed. Using the starting location
 * as "North" and the flag as "South"
 *
 * +----------------+----------------+-------------------------------+
 * | NE panel color | NW panel color | Gates that are open           |
 * +----------------+----------------+-------------------------------+
 * | Blue           | Red            | West field gates              |
 * | Blue           | Blue           | Second-from-West gates        |
 * | Red            | Red            | Second-from-East gates        |
 * | Red            | Blue           | East field gates              |
 * +----------------+----------------+-------------------------------+
 *
 * This means, we will need to make the robot drive over both panels and remember
 * the color of each one in order to determine which gates to go through.
 */
public class MyFIRSTJavaOpMode_Pattern extends LinearOpMode {
    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor frontLeft;
    DcMotor frontRight;
    ColorSensor color1;
    DistanceSensor distance1;
    BNO055IMU imu;
    int distanceBetweenPanels;
    double throughGateSeconds;
    double rightAngleSeconds;

    @Override
    public void runOpMode() {
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        color1 = hardwareMap.get(ColorSensor.class, "color1");
        distance1 = hardwareMap.get(DistanceSensor.class, "distance1");
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        throughGateSeconds = 1.3;

        // Put initialization blocks here
        waitForStart();

        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        // let's read the first panel at the top left of the field
        goToFirstPanel();
        boolean eastPanelIsRed = (color1.red() == 255); // will be true if color is red, otherwise false.

        // reset the distance to zero so we can capture the distance after moving to the seond panel
        // using the DistanceSensor
        distanceBetweenPanels = 0;
        distance1.getDistance(DistanceUnit.MM);

        // now we read the 2nd panel
        goToSecondPanel();
        boolean westPanelIsRed = (color1.red() == 255); // will be true if color is red, otherwise false.


        // now we need to back up and read the color

        telemetry.addData("Red Value", color1.red());
        telemetry.addData("Blue Value", color1.blue());
        telemetry.addData("eastPanelIsRed", eastPanelIsRed);
        telemetry.addData("westPanelIsRed", westPanelIsRed);
        telemetry.update();

        if (eastPanelIsRed && !westPanelIsRed) {
            goThroughEastGates();
        }
        if (eastPanelIsRed && westPanelIsRed) {
            goThroughSecondFromEastGates();
        }
        if (!eastPanelIsRed && westPanelIsRed) {
            goThroughWestGates();
        }
        if (!eastPanelIsRed && !westPanelIsRed) {
            goThroughSecondFromWestGates();
        }

    }

    /* private methods for each combination of eastPanelIsRed and westPanelIsRed
     * +----------------+----------------+-------------------------------+
     * | NE panel color | NW panel color | Gates that are open           |
     * +----------------+----------------+-------------------------------+
     * | Blue           | Red            | West field gates              |
     * | Blue           | Blue           | Second-from-West gates        |
     * | Red            | Red            | Second-from-East gates        |
     * | Red            | Blue           | East field gates              |
     * +----------------+----------------+-------------------------------+
     * */
    private void goThroughEastGates() {
        moveForward(1,1.75);
        // rotate left and go through gate
        rotateLeft(1.05);
        moveForward(1,throughGateSeconds);

        // rotate left and head towards middle
        rotateLeft(1.0);
        moveForward(1,1);

        // rotate right and move to flag
        rotateRight(1.0);
        moveForward(1,.2);
    }


    private void goThroughSecondFromEastGates() {
        moveForward(1,1);
        // rotate left and go through gate
        rotateLeft(.95);
        moveForward(1,throughGateSeconds);

        // rotate left and head towards middle
        rotateLeft(.5);
        moveForward(1,.5);

    }

    private void goThroughSecondFromWestGates() {
        moveForward(1, .3);
        rotateLeft(.98);
        moveForward(1,throughGateSeconds);
        rotateRight(0.5);
        moveForward(1, 1.5);
    }

    private void goThroughWestGates() {
        moveBackward(.05, 1);
        rotateLeft(.98);
        moveForward(1,throughGateSeconds);
        rotateRight(rightAngleSeconds);
        moveForward(1,1.5);
        rotateLeft(rightAngleSeconds);
        moveForward(1,1);
    }


    private void goToSecondPanel() {
        moveBackward(0.5, 2.6);
    }

    private void goToFirstPanel() {
        moveForward(0.5, 0.5);
        // the robot isn't quite straight,
        rotateRight(0.942);
        moveForward(0.5, 1.2);
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