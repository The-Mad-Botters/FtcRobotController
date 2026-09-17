# 🗺️ Lesson 10 – Choose Your Own Robot Adventure

You made it to **Open Ended**, where FTC Sim stops pointing at one obvious finish and asks a much more interesting question:

> Multiple goals are in play. Where are *you* going to go?

This is the closest movement lesson to planning a real autonomous routine. There may be several useful targets, but your robot has limited time. You need to choose, plan, test, and explain your strategy.

[Open FTC Movement in FTC Sim](https://ftcsim.org/course/fzapjibwdkf/) and choose **10 – Open Ended**.

---

## 🎯 Your Mission

Pick a goal—or a sequence of goals—and make the robot complete your plan reliably.

Before touching the code, write a one-sentence strategy:

> “First my robot will ______ because ______. Then it will ______.”

Now sketch **two possible routes**. The shortest path is not always the easiest or most dependable one. Competitive teams care about points, but they also care about doing the same thing every single match.

---

## 🧠 Strategy Scorecard

Rate each route from 1–3:

| Question | Route A | Route B |
|---|---:|---:|
| Is it short? | | |
| Are the turns simple? | | |
| Is there room for mistakes? | | |
| Can I explain it clearly? | | |

Choose a route, then prove it with more than one successful run.

---

## 🚀 Copy/Paste Launchpad

Create `MyFIRSTJavaOpMode.java`. The code gives you a robot and a driving tool; you supply the strategy.

```java
package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcMovement.Lesson10;

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

        // Strategy: ______________________________________________
        // TODO: Code one goal first. Add another only after it is reliable.
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

## 🧰 One Coach Strategy

The examples in this folder show **one** choice, not the one “correct” answer:

- [`MyFIRSTJavaOpMode_10_Solution.java`](./MyFIRSTJavaOpMode_10_Solution.java) writes each timed move directly.
- [`MyFIRSTJavaOpMode_10_Solution_Methoda.java`](./MyFIRSTJavaOpMode_10_Solution_Methoda.java) organizes the same route with movement methods.

Try your plan before opening them. If your route is different and it works, congratulations—you did engineering.

## 🎤 Pit Huddle

Give the team your 30-second strategy pitch: **What did you choose, why did you choose it, and how many times did it succeed?**
