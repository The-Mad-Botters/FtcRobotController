# 🌎 Lesson 9 – Around the World

Round and round! This field has a ring, a prize in the middle, and a flag outside. Your robot needs more than a straight line—it needs a travel plan.

Think of it as a tiny robotic road trip: enter the circle, visit the center, get back out, and finish at the flag. No passport required. Snacks are still encouraged.

[Open FTC Movement in FTC Sim](https://ftcsim.org/course/fzapjibwdkf/) and choose **9 – Around the World**.

---

## 🎯 Your Mission

Plan a route that reaches the important objects **without getting trapped by the inner wall**.

Before coding, sketch arrows for three legs:

- **Approach:** How will the robot enter the circular area?
- **Visit:** How will it reach the center?
- **Exit:** Can it reverse part of the route instead of making a crowded turn?

That last question matters. Sometimes the smartest forward move is actually backward.

---

## 🧠 New Robot Skill: Undoing a Move

If the robot drives forward into a narrow spot, running both motors backward for the right distance can retrace that path. This idea is useful far beyond FTC Sim—real autonomous routines often need to approach a game piece and then back away cleanly.

Test the approach and exit as a pair. If one changes, the other may need tuning too.

---

## 🚀 Copy/Paste Launchpad

Create `MyFIRSTJavaOpMode.java` and turn these three empty route sections into your round-the-world trip.

```java
package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcMovement.Lesson9;

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

        // TODO: Approach the circular area.
        // TODO: Visit the center.
        // TODO: Back out safely and reach the flag.
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

## 🧰 Pick a Route, Then Compare

- [`MyFIRSTJavaOpMode_9_Solution.java`](./MyFIRSTJavaOpMode_9_Solution.java) uses timed motor commands and sweeping turns.
- [`MyFIRSTJavaOpMode_9_Solution_Methods.java`](./MyFIRSTJavaOpMode_9_Solution_Methods.java) gives those same ideas readable names.
- [`MyFIRSTJavaOpMode_9_WithEncoder.java`](./MyFIRSTJavaOpMode_9_WithEncoder.java) uses measured rotations and distances.

The examples do not all drive the field in exactly the same style. That is the point: a good challenge can have more than one good solution.

## 🎤 Pit Huddle

Point to your approach, visit, and exit code. **Which leg would you redesign if the field changed?**
