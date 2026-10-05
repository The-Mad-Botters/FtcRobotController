# 🧠 Lesson 5 – L-Shaped Maze & Smarter Code

Welcome to **Lesson 5**, where your robot faces an L-shaped maze and your brain faces a coding challenge: how to write smarter, cleaner, and less headache-inducing code.

Inside this folder, you’ll find **four Java solutions** to the same maze. They all work, but they each take a different approach. Let’s break them down and figure out which one future-you will thank you for.

---

## 🧪 The Four Solutions

| File Name | Description | Pros | Cons |
|----------|-------------|------|------|
| [`MyFIRSTJavaOpMode_5L.java`](MyFIRSTJavaOpMode_5L.java) | The “just get it done” version. Every movement is written out manually. | Easy to follow line-by-line. Great for beginners. | Lots of repeated code. Hard to update or reuse. |
| [`MyFIRSTJavaOpMode_5L_Methods.java`](MyFIRSTJavaOpMode_5L_Methods.java) | Adds custom methods like `moveForward()` and `turnLeft()`. | Cleaner, reusable, easier to tweak. | Slightly more abstract—need to understand the method names. |
| [`MyFIRSTJavaOpMode_5L_Method_Single.java`](MyFIRSTJavaOpMode_5L_Method_Single.java) | Uses one custom method: `moveIt()` with parameters. | Super compact. Minimal duplication. | Can be harder to read. “moveIt()” is kinda vague—what does it *do* exactly? |
| [`MyFIRSTJavaOpMode_5L_WithEncoder.java`](MyFIRSTJavaOpMode_5L_WithEncoder.java) | Uses motor encoders to move precise distances and turns. | Much more accurate movement! Great for real robots. | Slightly more complex code. Need to understand what an encoder is. |

---

## 🏷️ What’s an Encoder, and Why Use One?

An **encoder** is a sensor built into your robot’s motors that counts how much the motor shaft turns—kind of like a super-precise step counter for your wheels. Instead of just guessing how far your robot moves by timing the motors, you can use encoders to measure the exact distance traveled or angle turned.

**Why use encoders?**

- **Accuracy:** Your robot moves the same distance every time, even if the battery is low or the floor is slippery.
- **Repeatability:** You can make your robot follow the same path again and again.
- **Smarter turns:** You can rotate your robot by a specific number of degrees, not just “about a second.”

If you want your robot to move like a pro, encoders are the way to go!

---

## 🧼 Why Clean Code Matters

Imagine writing a recipe that says:

> “Crack egg. Stir. Crack egg. Stir. Crack egg. Stir.”

Instead of:

> “Repeat: Crack and stir 3 times.”

The second one is easier to read, right? That’s what **methods** do in code—they help you avoid repeating yourself and make your instructions clearer.

Now imagine someone else has to read your code (like your teammate or future you). If your code looks like a spaghetti monster, they’ll spend more time decoding it than fixing or improving it. Clean code = happy teammates = fewer bugs = more time for snacks.

---

## 🤔 Is `moveIt()` Too Generic?

Let’s talk naming. A method called `moveIt()` sounds cool, but what does it *actually* do? Move forward? Turn? Do a backflip?

If you’re reading code and see `moveIt(-0.5, 0.5, 1.35)`, you might be like, “Uhh… what’s happening here?” But if you see `moveForward(1, 1100)` or `turnLeft(0.35, 1350)`, it’s instantly clear what is supposed to happen.

So while generic methods are powerful, they can be confusing if the name doesn’t tell the story. Think of method names like labels on buttons—make them obvious so anyone can press them without blowing up the robot.

---

## 📏 Code Line Comparison – Why It Matters

For this T-shaped maze, the number of lines between the four solutions isn’t wildly different. But what happens when the maze gets bigger? Let’s play a game of “What If…”

| Maze Scenario | Description | MyFIRSTJavaOpMode 5L | MyFIRSTJavaOpMode 5L_Methods | MyFIRSTJavaOpMode 5L_Method_Single | MyFIRSTJavaOpMode 5L_WithEncoder |
|---------------|-------------|------------|-------------|----------------|-------------------------------|
| 🔹 T Maze (Lesson 5) | 3 segments + 2 turns | 43 lines | 41 lines | 39 lines | 48 lines |
| 🟦 Square Maze | 4 straight segments + 4 turns | ~60 lines | ~46 lines | ~36 lines | ~54 lines |
| 🌀 Helix Maze | 10 segments + 10 turns | ~120 lines | ~66 lines | ~48 lines | ~70 lines |
| 🍦 Ice Cream Run | East Middle School → Dairy King → McDonald's (cuz I smelled fries on the way) → Back to School | ~200+ lines | ~100 lines | ~60 lines | ~110 lines |

> 🚨 *Disclaimer: No robots were harmed in the making of this ice cream run. But they did get hungry. And yes, they dipped the fries in the ice cream.*

---

## 🏆 Final Thoughts

All four solutions work. But as you level up, you’ll want to write code that’s:

- Easy to read
- Easy to change
- Easy to share

Using methods is like giving your robot a toolbox instead of a pile of parts. And naming those tools well? That’s the difference between “mystery wrench” and “Phillips screwdriver.”

So go ahead—tinker, test, and try writing your own methods. Your robot (and your future self) will thank you.
