# Sensors 4: Pattern

[All simulator lessons](../../README.md) · [Sensors course](../readme.md)

## Your mission

Store readings from two different panels and use their combination to decide a route.

**What you are learning:** remember two readings and combine conditions.

Open the matching challenge in [FTC Sim](https://ftcsim.org/). These lesson names follow our saved coach examples; use the simulator's displayed objective if its field or wording has changed.

## Choose how to start

- **Blocks:** follow the built-in tutorial or continue your saved work. Predict a result, run it, and change one thing.
- **Blocks → Java:** save a copy, convert with OnBot Java, and find the Java lines for two blocks you understand. Save Java edits before converting Blocks again; conversion can overwrite them.
- **Java:** use the starter below and fill in your own route and decisions. Android Studio is an optional editor; run the challenge in FTC Sim.

## Try it

1. Label the two panels consistently on a sketch.
2. Observe field states and build your own table: first-panel color, second-panel color, available route.
3. Read and store the first panel before moving to the second. Combine both saved readings to select a route.

A sensor reads where it is now. Do not assume that 'not red' always means blue. Coach examples use particular simulated readings; observe yours before choosing a comparison. A real robot may need different thresholds.

Keep a small record: **prediction → change → observed result → next step**. If stuck, show that record to a teammate or coach. When sharing a computer, switch keyboard ownership every 5–7 minutes; each person must make and explain a change.

## Java starter for Android Studio

1. In the open **FtcRobotController** project on branch **SimulatorLessons**, find `TeamCode/src/main/java/org/firstinspires/ftc/teamcode/SimulatorLessons/FtcSensors/Lesson4`.
2. Create a Java class named **`SensorsLesson4Starter`**. Replace the entire file with the code below, including its package line. Keep the coach's files intact.
3. This starter builds but **does not solve or drive the course** until you fill in the TODOs. It only shows the current color readings.
4. To run in **FTC Sim OnBot Java**, copy your code, remove the `package ...;` line from the simulator copy, and change `public class SensorsLesson4Starter` to `public class MyFIRSTJavaOpMode`. Keep `extends LinearOpMode`, imports, and helper methods. Leave the Android Studio name unchanged.
5. Save your existing simulator code before replacing it. These names and motor directions are for this simulator's robot, not a deployment recipe for our competition robot.

```java
package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcSensors.Lesson4;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;

public class SensorsLesson4Starter extends LinearOpMode {
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

        // TODO: Travel to the first panel, then save its color reading.
        // TODO: Travel to the second panel, then save its color reading.
        // TODO: Use your observation table to combine the saved readings.
        // TODO: Call the route you selected; handle unknown readings too.

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
- your answer to: **Why must you save the first reading before moving to the second panel?**

You do not have to finish the whole maze to show useful progress. Be honest about unfinished work and help you used.

**Stretch:** Test every combination you can access; label untested combinations honestly. Explain && (and) and ! (not).

## Coach's solutions: references you may use

- [MyFIRSTJavaOpMode_Pattern.java](MyFIRSTJavaOpMode_Pattern.java)
- [MyFIRSTJavaOpMode_Pattern_WithEncoder.java](MyFIRSTJavaOpMode_Pattern_WithEncoder.java)

You may read, compare, or borrow from these examples. Point out what you borrowed, explain how it works, and test a change of your own. A working copied program is a starting point for discussion; be ready to explain the code and predict what a change will do. Examples are approaches to investigate, not a promise that every route or comment matches the current simulator.
