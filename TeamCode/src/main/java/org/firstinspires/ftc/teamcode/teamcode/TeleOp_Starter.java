package org.firstinspires.ftc.teamcode.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.teamcode.mechanism.AprilTagsWebCam;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.MecanumDrive;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

@TeleOp(name = "TeleOp Starter", group = "Starter")
public class TeleOp_Starter extends OpMode {

    // Robot mechanisms
    protected MecanumDrive driver = new MecanumDrive();
    protected AprilTagsWebCam aprilTagsWebCam = new AprilTagsWebCam();

    // April Tag IDs for Red and Blue goals/targets
    final protected int RED_TAG_ID = 24;
    final protected int BLUE_TAG_ID = 20;

    // Active target tag ID (defaults to Blue)
    protected int targetTagId = BLUE_TAG_ID;

    private boolean exposureCalibrated = false;

    @Override
    public void init() {
        // Initialize drive motors and webcam
        driver.initialize(hardwareMap);
        aprilTagsWebCam.initialize(hardwareMap, telemetry);

        telemetry.addLine("Robot Initialized. Calibrating camera in init_loop...");
        telemetry.update();
    }

    @Override
    public void init_loop() {
        // Auto-calibrate camera exposure in init_loop so it is non-blocking and completes w/o crashing
        if (!exposureCalibrated) {
            aprilTagsWebCam.autoSetExposure(3000);
            exposureCalibrated = true;
        }

        telemetry.addLine("Robot Initialized. Press Start.");
        telemetry.update();
    }


    @Override
    public void loop() {
        //----------------------------------------
        // Target Selection
        // Gamepad1 X: Blue target
        // Gamepad1 B: Red target
        //----------------------------------------
        if (gamepad1.xWasReleased()) {
            targetTagId = BLUE_TAG_ID;
        } else if (gamepad1.bWasReleased()) {
            targetTagId = RED_TAG_ID;
        }

        if (targetTagId == BLUE_TAG_ID) {
            telemetry.addLine("\uD83D\uDFE6 \uD83D\uDFE6  BLUE TARGET \uD83D\uDFE6 \uD83D\uDFE6");
        } else if (targetTagId == RED_TAG_ID) {
            telemetry.addLine("\uD83D\uDFE5 \uD83D\uDFE5  RED TARGET \uD83D\uDFE5 \uD83D\uDFE5");
        }

        // Fetch current target tag detection
        AprilTagDetection targetTag = aprilTagsWebCam.getTagBySpecificId(targetTagId);

        //----------------------------------------
        // Drive Controls
        // Left stick Y: Forward / Backward
        // Left stick X: Strafe Left / Right
        // Right stick X: Rotate Turn Left / Right
        //----------------------------------------
        double forward = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double rotate = gamepad1.right_stick_x;

        // Apply power to mecanum drivetrain
        driver.drive(forward, strafe, rotate);

        //----------------------------------------
        // AprilTag Telemetry Update
        //----------------------------------------
        if (targetTag == null) {
            telemetry.addData("No Tag Detected", targetTagId);
        } else {
            aprilTagsWebCam.displayDetectionTelemetry(targetTag);
        }

        telemetry.addData("Drive Motors", "Fwd: %.2f, Str: %.2f, Rot: %.2f", forward, strafe, rotate);
        telemetry.update();
    }

    @Override
    public void stop() {
        aprilTagsWebCam.stop();
        super.stop();
    }
}

