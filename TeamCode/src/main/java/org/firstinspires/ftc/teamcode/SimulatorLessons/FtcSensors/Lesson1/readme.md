# Sensors 1: Color Sense

[All simulator lessons](../../README.md) · [Sensors course](../readme.md)

## Your mission

Use color readings to make a choice. Connect a sensor block, its Java reading, and the robot action that follows.

**What you are learning:** read a sensor before making a decision.

Open the matching challenge in [FTC Sim](https://ftcsim.org/). These lesson names follow our saved coach examples; use the simulator's displayed objective if its field or wording has changed.

## Choose how to start

- **Blocks:** follow the built-in tutorial or continue your saved work. Predict a result, run it, and change one thing.
- **Blocks → Java:** save a copy, convert with OnBot Java, and find the Java lines for two blocks you understand. Save Java edits before converting Blocks again; conversion can overwrite them.
- **Java:** use the starter below and fill in your own route and decisions. Android Studio is an optional editor; run the challenge in FTC Sim.

## Try it

1. Reach a colored panel using movement you can explain.
2. Read the red and blue values in telemetry; record values for each color you encounter.
3. Write your own condition and action for each observed color. Include what should happen for an unrecognized reading.

A sensor reads where it is now. Do not assume that 'not red' always means blue. Coach examples use particular simulated readings; observe yours before choosing a comparison. A real robot may need different thresholds.

Keep a small record: **prediction → change → observed result → next step**. If stuck, show that record to a teammate or coach. When sharing a computer, switch keyboard ownership every 5–7 minutes; each person must make and explain a change.

## Java starter for Android Studio

1. In the open **FtcRobotController** project on branch **SimulatorLessons**, find `TeamCode/src/main/java/org/firstinspires/ftc/teamcode/SimulatorLessons/FtcSensors/Lesson1`.
2. Create a Java class named **`SensorsLesson1Starter`**. Replace the entire file with the code below, including its package line. Keep the coach's files intact.
3. This starter builds but **does not solve or drive the course** until you fill in the TODOs. It only shows the current color readings.
4. To run in **FTC Sim OnBot Java**, copy your code, remove the `package ...;` line from the simulator copy, and change `public class SensorsLesson1Starter` to `public class MyFIRSTJavaOpMode`. Keep `extends LinearOpMode`, imports, and helper methods. Leave the Android Studio name unchanged.
5. Save your existing simulator code before replacing it. These names and motor directions are for this simulator's robot, not a deployment recipe for our competition robot.

```java
package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcSensors.Lesson1;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;

public class SensorsLesson1Starter extends LinearOpMode {
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private ColorSensor color1;

    @Override
    public void runOpMode() {
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        color1 = hardwareMap.get(ColorSensor.class, "color1");
        // Matches the simulated robot used by these coach examples.
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        if (isStopRequested()) {
            return;
        }

        // TODO: Travel to the panel before reading its color.
        // TODO: Record color1.red() and color1.blue() at that location.
        // TODO: Write your own conditions and route actions.
        // TODO: Decide what to do if neither expected color is detected.

        // This shows the CURRENT location's values, not saved panel history.
        telemetry.addData("Red", color1.red());
        telemetry.addData("Blue", color1.blue());
        telemetry.update();
        sleep(1500); // Briefly leave the readings visible.

        stopMotors();
    }

    // Building block, not a route. Power: -1 to 1; time: seconds.
    private void moveIt(double leftPower, double rightPower, double seconds) {
        if (!opModeIsActive()) {
            return;
        }
        motorLeft.setPower(leftPower);
        motorRight.setPower(rightPower);
        sleep((long) (seconds * 1000));
        stopMotors();
    }

    private void stopMotors() {
        motorLeft.setPower(0);
        motorRight.setPower(0);
    }
}
```

`setPower` commands a motor; `sleep` waits in milliseconds. Our helper accepts seconds and converts them. Waiting does not stop a motor—`stopMotors()` sends zero power. Timing is not an exact distance or angle measurement.

## Ready to share

Everyone will give a short explanation near the end of the meeting. Be ready to show:

- your goal and one part you personally changed;
- one block and its matching Java line;
- a prediction, the actual result, and your next step;
- your answer to: **How do you know the sensor was over the panel when you read it?**

You do not have to finish the whole maze to show useful progress. Be honest about unfinished work and help you used.

**Stretch:** Test another field state and explain why your decision still works.

## Coach's solutions: references you may use

- [MyFIRSTJavaOpMode_Color_Sensor.java](MyFIRSTJavaOpMode_Color_Sensor.java)

You may read, compare, or borrow from these examples. Point out what you borrowed, explain how it works, and test a change of your own. A working copied program is a starting point for discussion; be ready to explain the code and predict what a change will do. Examples are approaches to investigate, not a promise that every route or comment matches the current simulator.
