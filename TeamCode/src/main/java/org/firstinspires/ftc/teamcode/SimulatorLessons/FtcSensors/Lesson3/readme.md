# 🕶️ Sensor Lesson 3 – The Tinted Maze

The Tinted Maze has two possible worlds hiding in the same field. A colored panel tells your robot which world it woke up in—and which route is safe.

This is not just a driving challenge with a splash of paint. It is a **branching story** written in Java:

[Open FTC Sensors in FTC Sim](https://ftcsim.org/course/uczcnzpqcmc/) and choose **3 – Tinted Maze**.

> If the clue is red, follow one chapter. If it is blue, follow another.

---

## 🎯 Your Mission

Reach the color panel, read it, and guide the robot through the matching maze to the flag.

Sketch both routes before coding:

- 🔴 **Red route:** Where must the robot back up, turn, and travel?
- 🔵 **Blue route:** Which hallway and turns lead through that version?

Circle any movement that both routes share. Shared moves may belong before or after the `if` statement—or inside a reusable method.

---

## 🧠 Variables Are the Robot’s Sticky Notes

The sensor reading matters at one particular spot. You can save the answer so the rest of the code does not depend on the robot staying there:

```java
boolean sawRed = color1.red() > color1.blue();
```

Now `sawRed` is a tiny sticky note in the robot’s memory. It can drive away and still remember what it saw.

---

## 🚀 Copy/Paste Launchpad

Create `MyFIRSTJavaOpMode.java`. This launchpad includes the fork in the story, but you must write both chapters.

```java
package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcSensors.Lesson3;

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

        // TODO: Drive the color sensor over the panel.
        boolean sawRed = color1.red() > color1.blue();

        telemetry.addData("Saw red?", sawRed);
        telemetry.update();

        if (sawRed) {
            // TODO: Build the red route one move at a time.
        } else {
            // TODO: Build the blue route one move at a time.
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

---

## 🧰 Coach’s Route Map

[`MyFIRSTJavaOpMode_TintedMaze.java`](./MyFIRSTJavaOpMode_TintedMaze.java) contains one complete solution with named movement methods and two branches.

Before copying anything, read only the method names inside each branch. Can you act out the robot’s route with your hand? If yes, the code is telling a clear story.

## 🎤 Pit Huddle

Show both branches and answer: **What changes between the red and blue worlds, and what code stays the same?**
