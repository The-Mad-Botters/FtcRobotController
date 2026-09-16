# 🤖 Lesson 3: Turning and Strafing

Welcome to Lesson 3! In this activity, you'll learn the difference between **rotation turn** and **strafe**—two essential movements for navigating the field like a pro. Lesson 3 of the VRS simulator challenges students to maneuver around a "U" shaped course and align with targets using these advanced techniques.

This lesson introduces:

1. Strafing (slide sideways) using mMcanum or Omni wheels.
2. **methods** - grouping blocks of code that can be called/used many times.

---

## 🎯 What You’ll Learn

- How to make your robot **strafe** using mecanum wheel logic.
- How to use **methods** to group code to perform actions that you can reuse.

---

## 🔄 Start Here: Turning with Encoders

Begin with the **rotation turn** code. This builds on what you learned in Lesson 2 and reinforces the rotational movement to face the direction you want to move forward.

### How it works

- **methods** - are a way for you to "encapsulate" code to perform actions that you might repeat. On this course, we need to move forward 3 times and turn 2 times. But we don't want to copy and paste the same chunks of code over-and-over. To navigate through a "U" shape course, copying and pasting duplicate code might be manageable. But what if the next course has more turns and runways...like an "S" or zig zag?

### Instructions

1. Go to the [VRS simulator](https://www.vrobotsim.online/levelselector.html) and open **Level 3**.
2. Click **On Bot Java** to open the code editor.
3. Copy and paste the [`Lesson3_starter_code.java`](Lesson3_starter_code.java) into the coding window.
4. Follow the comments in the starter code to understand how turning works.
5. Click **Initialize Program**, then **Run Program** to test your robot.

If you get stuck or want to see a working example, check out [`Lesson3_encoder_based_the_solution.java`](Lesson3_encoder_based_the_solution.java).

---

## 🧭 Next Level: Strafing with Mecanum Wheels

Once you've completed the above, try **strafing**—a unique movement that lets your robot slide sideways!

### What is strafing?

- **strafe** Strafing lets a robot move sideways—left or right—without turning its body. For example, strafing left means your robot slides sideways to the left without turning, and it works thanks to mecanum wheels—special wheels with diagonal rollers. To make this happen, the motors spin in a specific pattern: the front-left and back-right wheels spin backward, while the front-right and back-left wheels spin forward. Because the rollers are angled, each wheel pushes the robot diagonally, and when combined, these forces cancel out forward and backward motion and instead create a smooth sideways movement to the left. It’s like the robot is gliding left across the floor, which is super helpful for lining up with objects or dodging obstacles in a competition.

```
      Strafing Left (⬅️)
         
           [Front]   
      ┌────┬──────┬────┐↑↑↑
   ↓↓↓│ FL │      │ FR │ 
      └────┤      ├────┘ 
<----      |      |
      ┌────┤      ├────┐
   ↑↑↑│ BL │      │ BR │
      └────┴──────┴────┘↓↓↓ 
            [Back]   
         
Legend:
FL = Front Left wheel → spins forward (↑)
FR = Front Right wheel → spins backward (↓)
BL = Back Left wheel → spins backward (↓)
BR = Back Right wheel → spins forward (↑)

←←← Robot moves left (strafe)
```

### Instructions

1. Go to the [VRS simulator](https://www.vrobotsim.online/levelselector.html) and open **Level 3**.
2. Copy and paste the [`Lesson3_strafe_starter.java`](./Lesson3_strafe_starter.java) into the coding window.
3. Read the comments to understand how motor power affects strafing.
4. Test your robot and tweak the code to improve accuracy.

If you get stuck or want to see a working example, check out [`Lesson3_strafe_based_the_solution.java`](./Lesson3_strafe_the_solution.java)
---

## 🆚 Turning vs. Strafing

| Feature       | Turning                         | Strafing                          |
|--------------|----------------------------------|-----------------------------------|
| Movement      | Rotates the robot               | Slides sideways                   |
| Uses encoders | ✅ Yes                          | ⚠️ Not always                     |
| Best for...   | Changing direction              | Dodging obstacles or lining up    |

---

## 🚀 How to Run Your Code

1. Open the VRS simulator and go to Level 3.
2. Click **On Bot Java** to open the code editor.
3. Start with the turning starter file.
4. Test your robot and see how it rotates!
5. Then try the strafing code and compare the results.

---

## 🖼️ What It Looks Like  

![Robot Simulator Screenshot](https://github.com/IndecisiveDevices/PreSeasonSandbox/raw/main/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/SimulatorLessons/VRS_Screenshot.png)

---

## 🔗 More Lessons

- [Lesson 1: Getting Started](../Lesson1)
- [Lesson 2: Movement with Time and Encoders](../Lesson2)

---

Keep experimenting and have fun—you're mastering advanced robot movement! 🛠️🧠
