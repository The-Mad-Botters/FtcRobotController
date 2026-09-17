# 🟦 Lesson 6 – Escape the Square

Welcome to the **Square**: four tight hallways, three sharp corners, and approximately zero room for a robot with a dramatic turning radius.

FTC Sim’s hint says to watch your distances and sleep times. That is excellent advice. In this maze, a tiny bit too long can turn “perfect corner” into “hello, wall.”

[Open FTC Movement in FTC Sim](https://ftcsim.org/course/fzapjibwdkf/) and choose **6 – Square**.

---

## 🎯 Your Mission

Get the robot around the square and safely to the flag.

Before you code, trace the route with your finger and name the repeating pattern:

> straight → turn → straight → turn → straight → turn → flag

See all that repetition? This is a great course for practicing **methods**—small, reusable commands with names that explain what the robot is doing.

---

## 🧪 Build It Like a Scientist

1. Make only the **first straight section** work.
2. Add one sharp turn. Change just one number at a time.
3. Reuse those moves for the next side of the square.
4. When the robot misses, decide whether the problem is **distance**, **turn angle**, or **speed** before changing code.

> 💡 A useful test changes one thing. If you change five numbers at once, the robot may improve—but you will not know why.

---

## 🚀 Copy/Paste Launchpad

Create a file named `MyFIRSTJavaOpMode.java`, paste this in, and build your route where the `TODO` comments are. The toolbox works; the maze solution is still yours.

```java
package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcMovement.Lesson6;

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

        // TODO: Build one side and one corner first.
        // TODO: Repeat your tested moves until you reach the flag.
    }

    private void moveForward(double speed, double seconds) {
        moveIt(speed, speed, seconds);
    }

    private void turnRight(double speed, double seconds) {
        moveIt(speed, -speed, seconds);
    }

    private void moveIt(double leftSpeed, double rightSpeed, double seconds) {
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

Stuck, curious, or ready to compare approaches? This folder has three working examples:

- [`MyFIRSTJavaOpMode_6_Method_Single.java`](./MyFIRSTJavaOpMode_6_Method_Single.java) uses one flexible `moveIt()` method.
- [`MyFIRSTJavaOpMode_6_Methods.java`](./MyFIRSTJavaOpMode_6_Methods.java) gives each kind of movement a descriptive name.
- [`MyFIRSTJavaOpMode_6_WithEncoder.java`](./MyFIRSTJavaOpMode_6_WithEncoder.java) trades timed guesses for motor encoder targets.

Looking is allowed. Copying without understanding is not very useful. If you borrow an idea, be ready to point to it, explain it, and show what you changed.

## 🎤 Pit Huddle

Be ready to show one corner and answer: **Which number did you tune, what happened, and what will you try next?**
