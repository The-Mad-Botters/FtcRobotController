# 🌀 Lesson 8 – Tame the Helix

The **Helix** looks like the robot wandered into a spiral doodled in the corner of a math notebook. It starts with an S-shaped wiggle, opens into a long hallway, and finishes with another big bend.

FTC Sim warns that the angles may take an attempt or two. Translation: retries are part of the level, not proof that you are bad at it.

[Open FTC Movement in FTC Sim](https://ftcsim.org/course/fzapjibwdkf/) and choose **8 – Helix**.

---

## 🗺️ Turn One Giant Maze into Checkpoints

Do not try to solve the entire picture in your head. Break it into smaller wins:

1. **The wiggle** – thread through the first S curve.
2. **The hallway** – straighten the robot and travel the long section.
3. **The final bend** – use a smooth curve to enter the flag area.

When one checkpoint works, leave it alone and start on the next. That is called **decomposition**, which is a fancy word for “make the giant problem less giant.”

---

## 🧪 Your Test Loop

For each checkpoint:

> Predict → Run → Watch → Change one value → Run again

Keep a tiny tuning log. Even three notes—“too early,” “too wide,” “made it”—will save you from repeating the same failed experiment.

---

## 🚀 Copy/Paste Launchpad

Create `MyFIRSTJavaOpMode.java`, paste this in, and build one checkpoint at a time.

```java
package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcMovement.Lesson8;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class MyFIRSTJavaOpMode extends LinearOpMode {
    DcMotor motorLeft;
    DcMotor motorRight;

    @Override
    public void runOpMode() {
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        // TODO: Checkpoint 1 - the opening wiggle.
        // TODO: Checkpoint 2 - the long hallway.
        // TODO: Checkpoint 3 - the final bend to the flag.
    }

    private void drive(double leftSpeed, double rightSpeed, double seconds) {
        motorLeft.setPower(leftSpeed);
        motorRight.setPower(rightSpeed);
        sleep((long) (seconds * 1000));
        motorLeft.setPower(0);
        motorRight.setPower(0);
    }
}
```

---

## 🧰 Coach’s Code Shelf

- [`MyFIRSTJavaOpMode_8_Solution.java`](./MyFIRSTJavaOpMode_8_Solution.java) lists each timed move directly.
- [`MyFIRSTJavaOpMode_8_Solution_Methods.java`](./MyFIRSTJavaOpMode_8_Solution_Methods.java) hides the motor math behind readable movement names.
- [`MyFIRSTJavaOpMode_8_WithEncoder.java`](./MyFIRSTJavaOpMode_8_WithEncoder.java) turns the route into measured wheel travel.

Compare the first two files after you attempt the maze. Which one makes the three checkpoints easiest to spot?

## 🎤 Pit Huddle

Choose your hardest checkpoint and share: **What did the robot do, what did you change, and how did the result improve?**
