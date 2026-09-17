# 🧩 Sensor Lesson 4 – Crack the Pattern

One color is a clue. **Two colors are a code.**

In Pattern, the robot must inspect two floor panels. Together, those colors reveal which pair of gates is open. The field can change when you reset it, so charging toward the flag and hoping for the best is not a long-term strategy. (It is, however, a pretty good way to bonk a wall.)

[Open FTC Sensors in FTC Sim](https://ftcsim.org/course/uczcnzpqcmc/) and choose **4 – Pattern**.

---

## 🔎 Mission One: Discover the Secret Code

Before solving the maze, become a field scientist:

1. Reset and run the course several times.
2. Record the first panel’s color.
3. Record the second panel’s color.
4. Notice which gates are open.

Build your own observation table:

| First panel | Second panel | Open gates |
|---|---|---|
| Red | Red | ? |
| Red | Blue | ? |
| Blue | Red | ? |
| Blue | Blue | ? |

Do not skip this part. Your `if` statements can only be as smart as the pattern you discovered.

---

## 🧠 Mission Two: Give the Robot a Memory

The robot cannot sit on both panels at once. It must save the first color, drive to the second panel, and then combine the two clues.

Two `boolean` variables work like labeled sticky notes:

```java
boolean firstPanelIsRed = color1.red() > color1.blue();
// Move to panel two before taking the next reading!
boolean secondPanelIsRed = color1.red() > color1.blue();
```

If both readings happen in the same place, you have two sticky notes containing the same clue. Sneaky bug!

---

## 🚀 Copy/Paste Launchpad

Create `MyFIRSTJavaOpMode.java`, paste this in, and connect your four observed patterns to four routes.

```java
package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcSensors.Lesson4;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

public class MyFIRSTJavaOpMode extends LinearOpMode {
    DcMotor motorLeft;
    DcMotor motorRight;
    ColorSensor color1;

    @Override
    public void runOpMode() {
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        color1 = hardwareMap.get(ColorSensor.class, "color1");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        // TODO: Move to the first panel.
        boolean firstPanelIsRed = color1.red() > color1.blue();

        // TODO: Move to the second panel BEFORE reading again.
        boolean secondPanelIsRed = color1.red() > color1.blue();

        if (firstPanelIsRed && secondPanelIsRed) {
            // TODO: Route for red + red.
        } else if (firstPanelIsRed && !secondPanelIsRed) {
            // TODO: Route for red + blue.
        } else if (!firstPanelIsRed && secondPanelIsRed) {
            // TODO: Route for blue + red.
        } else {
            // TODO: Route for blue + blue.
        }
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

`&&` means **AND**: both statements must be true. `!` means **NOT**: it flips true to false or false to true. Tiny symbols, big robot decisions.

---

## 🧰 Two Coach Solutions, Two Measuring Styles

- [`MyFIRSTJavaOpMode_Pattern.java`](./MyFIRSTJavaOpMode_Pattern.java) uses time to control each movement.
- [`MyFIRSTJavaOpMode_Pattern_WithEncoder.java`](./MyFIRSTJavaOpMode_Pattern_WithEncoder.java) uses motor encoder counts to measure travel.

Both examples first learn the colors, save them, and choose a route. Look for that shared story before comparing the movement details.

## 🏆 Boss-Level Check

Reset and run your program for **all four color combinations**. A solution that wins once may be lucky; a solution that handles every pattern is autonomous.

## 🎤 Pit Huddle

Show your observation table and explain one condition in normal words—for example: **“If the first panel is red and the second is not red, then…”**
