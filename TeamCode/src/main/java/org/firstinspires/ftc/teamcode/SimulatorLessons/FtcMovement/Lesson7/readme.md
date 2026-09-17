# 🎢 Lesson 7 – The Turn Test

This course is basically a robot driving test designed by someone who really, really likes curves.

The path mixes a sharp turn with long sweeping bends. A robot that only knows “straight” and “spin in place” is about to learn a new move: the **wide turn**.

[Open FTC Movement in FTC Sim](https://ftcsim.org/course/fzapjibwdkf/) and choose **7 – Turn Test**.

---

## 🎯 Your Mission

Reach the flag without clipping the walls—and figure out how two motor powers control the shape of a turn.

Try these ideas in a safe spot and predict each result before pressing Run:

| Left motor | Right motor | What do you predict? |
|---|---|---|
| Same power | Same power | ? |
| Positive | Negative | ? |
| Low positive | Higher positive | ? |

That last row is the secret sauce. Both wheels move forward, but one travels faster, so the robot draws a curve instead of spinning.

---

## 🧪 The Turn Lab

1. Solve the first sharp turn.
2. Test one wide turn by changing the **difference** between the motor powers.
3. Tune how long the curve lasts.
4. Add the final curve only after the first one is repeatable.

Give your successful moves names such as `turnWideLeft()` or `turnWideRight()`. Good names let your code read like driving directions instead of robot algebra.

---

## 🚀 Copy/Paste Launchpad

Create `MyFIRSTJavaOpMode.java` and paste this code into Android Studio or FTC Sim’s OnBot Java editor. Your first experiment belongs at the `TODO`.

```java
package org.firstinspires.ftc.teamcode.SimulatorLessons.FtcMovement.Lesson7;

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

        // TODO: Test equal powers, opposite powers, and unequal forward powers.
        // TODO: Turn your best tests into a route to the flag.
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

## 🧰 Three Ways to Teach a Robot to Turn

- [`MyFIRSTJavaOpMode_7_Method_Single.java`](./MyFIRSTJavaOpMode_7_Method_Single.java) puts every move through one all-purpose method.
- [`MyFIRSTJavaOpMode_7_Methods.java`](./MyFIRSTJavaOpMode_7_Methods.java) uses names like `turnWideLeft()` so the route tells a story.
- [`MyFIRSTJavaOpMode_7_WithEncoder.java`](./MyFIRSTJavaOpMode_7_WithEncoder.java) measures wheel travel with encoders.

Peek when it helps you learn. Then close the example and make one change of your own—you should be able to explain why it works.

## 🏁 Bonus Lap

Can you write one wide-turn method that accepts **direction, power, and time** instead of making a separate method for every curve?

## 🎤 Pit Huddle

Show one curve and explain: **Why does your robot bend instead of driving straight or spinning?**
