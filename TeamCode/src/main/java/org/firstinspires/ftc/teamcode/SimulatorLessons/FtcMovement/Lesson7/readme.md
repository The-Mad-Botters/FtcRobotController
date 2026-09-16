# Movement 7: Turn Test

[All simulator lessons](../../README.md) · [Movement course](../readme.md)

## Your mission

Compare turning in place with traveling along a curve by changing the relationship between left and right motor powers.

**What you are learning:** spin turns and curved turns.

Open the matching challenge in [FTC Sim](https://ftcsim.org/). These lesson names follow our saved coach examples; use the simulator's displayed objective if its field or wording has changed.

## Choose how to start

- **Blocks:** follow the built-in tutorial or continue your saved work. Predict a result, run it, and change one thing.
- **Blocks → Java:** save a copy, convert with OnBot Java, and find the Java lines for two blocks you understand. Save Java edits before converting Blocks again; conversion can overwrite them.
- **Java:** use the starter below and fill in your own route. Android Studio is an optional editor; run the challenge in FTC Sim.

## Try it

1. Predict motion for equal powers, opposite signs, and unequal powers with the same sign.
2. Test each turn shape in the simulator and record what happens.
3. Choose the turn shape that fits each part of the course, then tune one value at a time.

Keep a small record: **prediction → change → observed result → next step**. If stuck, show that record to a teammate or coach. When sharing a computer, switch keyboard ownership every 5–7 minutes; each person must make and explain a change.

## Java starter for Android Studio

1. In the open **FtcRobotController** project on branch **SimulatorLessons**, find `TeamCode/src/main/java/org/firstinspires/ftc/teamcode/SimulatorLessons/FtcMovement/Lesson7`.
2. Create a Java class named **`MovementLesson7Starter`**. Replace the entire file with the code below, including its package line. Keep the coach's files intact.
3. This starter builds but **does not solve or drive the course** until you fill in the TODOs. The movement helper is provided; you choose when and how to call it.
4. To run in **FTC Sim OnBot Java**, copy your code, remove the `package ...;` line from the simulator copy, and change `public class MovementLesson7Starter` to `public class MyFIRSTJavaOpMode`. Keep `extends LinearOpMode`, imports, and helper methods. Leave the Android Studio name unchanged.
5. Save your existing simulator code before replacing it. These names and motor directions are for this simulator's robot, not a deployment recipe for our competition robot.

```java
package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcMovement.Lesson7;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class MovementLesson7Starter extends LinearOpMode {
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

        // TODO: Predict motion for equal powers, opposite signs, and unequal powers with the same sign.
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
- your answer to: **How do the signs and relative sizes of the two powers change the turn?**

You do not have to finish the whole maze to show useful progress. Be honest about unfinished work and help you used.

**Stretch:** Add separately named spin and arc methods, then show why they are different.

## Coach's solutions: references you may use

- [MyFIRSTJavaOpMode_7_Methods.java](MyFIRSTJavaOpMode_7_Methods.java)
- [MyFIRSTJavaOpMode_7_Method_Single.java](MyFIRSTJavaOpMode_7_Method_Single.java)
- [MyFIRSTJavaOpMode_7_WithEncoder.java](MyFIRSTJavaOpMode_7_WithEncoder.java)

You may read, compare, or borrow from these examples. Point out what you borrowed, explain how it works, and test a change of your own. A working copied program is a starting point for discussion; be ready to explain the code and predict what a change will do. Examples are approaches to investigate, not a promise that every route or comment matches the current simulator.
