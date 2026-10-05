package org.firstinspires.ftc.teamcode.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.teamcode.mechanism.AprilTagsWebCam;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.MecanumDrive;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

@Autonomous(name = "Auto Starter", group = "Starter")
public class Auto_Starter extends LinearOpMode {

    // Robot mechanisms
    protected MecanumDrive driver = new MecanumDrive();
    protected AprilTagsWebCam aprilTagsWebCam = new AprilTagsWebCam();

    // Gain constants to control error correction response
    final double TURN_GAIN   = 0.01;   // Turn Control "Gain"
    final double MAX_AUTO_TURN   = 0.3; // Max turn speed

    // April Tag IDs for Red and Blue goals/targets
    final protected int BLUE_TAG_ID = 20;

    protected int targetTagId = BLUE_TAG_ID;

    double centerOffSet = 0.0;

    protected void initialize() {
        // Initialize the AprilTag detection process
        aprilTagsWebCam.initialize(hardwareMap, telemetry);
        aprilTagsWebCam.setManualExposure(6, 250);

        // Initialize drive motors
        driver.initialize(hardwareMap);

        // Wait for driver to press start
        telemetry.addData("Camera preview on/off", "3 dots, Camera Stream");
        telemetry.addData(">", "Touch START to begin now");
        telemetry.update();
    }

    @Override
    public void runOpMode() {
        try {
            initialize();
            waitForStart();

            if (opModeIsActive()) {
                // ====================================================================
                // Autonomous Routine Examples (Students can customize below)
                // ====================================================================

                // Example 1: Timed movement
                // driver.drive(0.5, 0, 0); // Move forward at 50% power
                // sleep(1000);
                // driver.drive(0, 0, 0);   // Stop

                // Example 2: Center on target AprilTag
                centerOnTarget();

            }
        } finally {
            aprilTagsWebCam.stop();
        }
    }

    /**
     * Rotates the robot to center on the specified AprilTag without driving forward or backward.
     * The robot will turn until the tag is directly in front of it (headingError is minimal).
     */
    public void centerOnTarget() {
        while (opModeIsActive()) {
            aprilTagsWebCam.update();
            AprilTagDetection targetTag = aprilTagsWebCam.getTagBySpecificId(targetTagId);

            if (targetTag == null) {
                driver.drive(0, 0, 0);
                telemetry.addData("Tag Not Detected", targetTagId);
                telemetry.update();
                break;
            }

            double headingError = targetTag.ftcPose.bearing - centerOffSet;

            // Stop turning if centered within tolerance
            final double HEADING_TOLERANCE = 0.5; // degrees
            if (Math.abs(headingError) <= HEADING_TOLERANCE) {
                driver.drive(0, 0, 0);
                telemetry.addLine("Centered on Target!");
                telemetry.update();
                break;
            }

            telemetry.addData("Centering...", "Heading Error: %.2f", headingError);
            aprilTagsWebCam.displayDetectionTelemetry(targetTag);
            telemetry.update();

            double turnPower = Range.clip(headingError * TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN);
            driver.drive(0, 0, -turnPower);
            sleep(10);
        }
    }
}


