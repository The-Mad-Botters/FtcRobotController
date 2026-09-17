# 👀 Sensor Lesson 1 – Color Sense Detective

Your robot has learned to move. Now it gets an upgrade: **eyes pointed at the floor**.

In Color Sense, the course can show the robot a red panel or a blue panel. The open exit changes with that color, so a memorized route will not work every time. Your robot must look, decide, and *then* move.

[Open FTC Sensors in FTC Sim](https://ftcsim.org/course/uczcnzpqcmc/) and choose **1 – Color Sense**.

---

## 🕵️ The Robot Detective’s Case

Every good detective follows the clues in order:

1. **Travel to the clue.** The color sensor cannot read a panel from across the room.
2. **Observe it.** Read `color1.red()` and `color1.blue()`.
3. **Decide.** Use `if` and `else if` to choose a direction.
4. **Act.** Turn toward the open exit and drive to the flag.

That pattern—**sense → decide → act**—is one of the biggest ideas in robotics.

---

## 🔬 First, Learn What the Sensor Sees

Use telemetry before writing your full route:

```java
telemetry.addData("Red", color1.red());
telemetry.addData("Blue", color1.blue());
telemetry.update();
```

Run the field more than once. What values appear over red? What values appear over blue? Write them down instead of guessing.

---

## 🚀 Copy/Paste Launchpad

Create `MyFIRSTJavaOpMode.java`, paste this in, and add your travel and decision code at the `TODO`s.

```java
package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcSensors.Lesson1;

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

        // TODO: Move until the sensor is over the colored panel.
        telemetry.addData("Red", color1.red());
        telemetry.addData("Blue", color1.blue());
        telemetry.update();

        if (color1.red() > color1.blue()) {
            // TODO: Take the route for a red panel.
        } else if (color1.blue() > color1.red()) {
            // TODO: Take the route for a blue panel.
        } else {
            // TODO: Decide what the robot should do if the reading is unclear.
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

## 🧰 Coach’s Case File

[`MyFIRSTJavaOpMode_Color_Sensor.java`](./MyFIRSTJavaOpMode_Color_Sensor.java) shows one complete sense-decide-act solution. Try your own logic first, then compare.

If you peek, hunt for these three moments: **Where does the robot reach the panel? Where does it make the decision? Where does each choice send it?**

## 🎤 Pit Huddle

Be ready to explain your `if` statement in plain English: **What does the robot notice, and what does it do because of that clue?**
