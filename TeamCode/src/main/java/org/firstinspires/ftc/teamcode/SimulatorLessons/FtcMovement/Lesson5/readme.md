# Movement 5: L

[All simulator lessons](../../README.md) · [Movement course](../readme.md)

## Your mission

Plan a route in short sections. Predict how motor power and time change each section, then turn repeated instructions into a method.

**What you are learning:** sequence, timing, and reusable steps.

Open the matching challenge in [FTC Sim](https://ftcsim.org/). These lesson names follow our saved coach examples; use the simulator's displayed objective if its field or wording has changed.

## Choose how to start

- **Blocks:** follow the built-in tutorial or continue your saved work. Predict a result, run it, and change one thing.
- **Blocks → Java:** save a copy, convert with OnBot Java, and find the Java lines for two blocks you understand. Save Java edits before converting Blocks again; conversion can overwrite them.
- **Java:** use the starter below and fill in your own route. Android Studio is an optional editor; run the challenge in FTC Sim.

## Try it

1. Sketch your route and mark a first stopping point.
2. Use Blocks to test one section. Match a motor block and a wait block to their Java lines.
3. In Java, add one section at a time. Choose your own powers and durations; reset before comparing trials.

Keep a small record: **prediction → change → observed result → next step**. If stuck, show that record to a teammate or coach. When sharing a computer, switch keyboard ownership every 5–7 minutes; each person must make and explain a change.

## Java starter for Android Studio

1. In the open **FtcRobotController** project on branch **SimulatorLessons**, find `TeamCode/src/main/java/org/firstinspires/ftc/teamcode/SimulatorLessons/FtcMovement/Lesson5`.
2. Create a Java class named **`MovementLesson5Starter`**. Replace the entire file with the code below, including its package line. Keep the coach's files intact.
3. This starter builds but **does not solve or drive the course** until you fill in the TODOs. The movement helper is provided; you choose when and how to call it.
4. To run in **FTC Sim OnBot Java**, copy your code, remove the `package ...;` line from the simulator copy, and change `public class MovementLesson5Starter` to `public class MyFIRSTJavaOpMode`. Keep `extends LinearOpMode`, imports, and helper methods. Leave the Android Studio name unchanged.
5. Save your existing simulator code before replacing it. These names and motor directions are for this simulator's robot, not a deployment recipe for our competition robot.

```java
package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcMovement.Lesson5;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class MovementLesson5Starter extends LinearOpMode {
    private DcMotor motorLeft;
    private DcMotor motorRight;

    @Override
    public void runOpMode() {
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        // Matches the simulated robot used by these coach examples.
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        if (isStopRequested()) {
            return;
        }

        // TODO: Sketch your route and mark a first stopping point.
        // TODO: Add and test one movement at a time.
        // Choose your own powers and durations; no route is supplied.

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
- your answer to: **What does sleep measure, and why is it not a distance measurement?**

You do not have to finish the whole maze to show useful progress. Be honest about unfinished work and help you used.

**Stretch:** Create a named movement method and explain its parameters.

## Coach's solutions: references you may use

- [MyFIRSTJavaOpMode_5L.java](MyFIRSTJavaOpMode_5L.java)
- [MyFIRSTJavaOpMode_5L_Methods.java](MyFIRSTJavaOpMode_5L_Methods.java)
- [MyFIRSTJavaOpMode_5L_Method_Single.java](MyFIRSTJavaOpMode_5L_Method_Single.java)
- [MyFIRSTJavaOpMode_5L_WithEncoder.java](MyFIRSTJavaOpMode_5L_WithEncoder.java)

You may read, compare, or borrow from these examples. Point out what you borrowed, explain how it works, and test a change of your own. A working copied program is a starting point for discussion; be ready to explain the code and predict what a change will do. Examples are approaches to investigate, not a promise that every route or comment matches the current simulator.
