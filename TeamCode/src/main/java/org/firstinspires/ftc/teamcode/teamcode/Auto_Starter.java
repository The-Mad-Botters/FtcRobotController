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
    // Drive = Error * Gain. Smaller values provide smoother control; larger values provide aggressive response.
    final double SPEED_GAIN  = 0.02;   // Forward Speed Control "Gain"
    final double STRAFE_GAIN = 0.015;  // Strafe Speed Control "Gain"
    final double TURN_GAIN   = 0.01;   // Turn Control "Gain"

    final double MAX_AUTO_SPEED  = 0.5; // Max approach speed
    final double MAX_AUTO_STRAFE = 0.5; // Max strafing speed
    final double MAX_AUTO_TURN   = 0.3; // Max turn speed

    // Target distance in inches to stop in front of AprilTag
    final double DESIRED_DISTANCE_TO_TARGET = 45.5;

    // April Tag IDs for Red and Blue goals/targets
    final protected int RED_TAG_ID  = 24;
    final protected int BLUE_TAG_ID = 20;

    protected int targetTagId = BLUE_TAG_ID;

    double centerOffSet = 0.0;
    double distanceToTarget = 0.0;

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

                // Example 3: Drive and align to desired distance from AprilTag
                goToTarget(DESIRED_DISTANCE_TO_TARGET);
            }
        } finally {
            aprilTagsWebCam.stop();
        }
    }

    /**
     * Drives the robot towards the specified AprilTag until it reaches desiredDistance inches.
     *
     * @param desiredDistance Desired distance to stop in front of tag (inches)
     */
    public void goToTarget(double desiredDistance) {
        while (opModeIsActive()) {
            aprilTagsWebCam.update();
            AprilTagDetection targetTag = aprilTagsWebCam.getTagBySpecificId(targetTagId);

            double drive;
            double turn;
            double strafe;

            if (targetTag == null) {
                telemetry.addData("Tag Not Detected", targetTagId);
                // Slowly creep forward to locate tag
                drive = -0.05;
                strafe = 0;
                turn = 0;
            } else {
                if (targetTag.ftcPose != null) {
                    distanceToTarget = targetTag.ftcPose.range;
                }
                aprilTagsWebCam.displayDetectionTelemetry(targetTag);

                // Determine heading, range, and yaw error
                double rangeError = (targetTag.ftcPose.range - desiredDistance);
                double headingError = targetTag.ftcPose.bearing;
                double yawError = targetTag.ftcPose.yaw;

                // Stop if we are within 0.8 inches of target distance
                if (Math.abs(rangeError) < 0.8) {
                    driver.drive(0, 0, 0);
                    return;
                }

                // Proportional control based on gains
                drive  = Range.clip(rangeError * SPEED_GAIN, -MAX_AUTO_SPEED, MAX_AUTO_SPEED);
                turn   = Range.clip(headingError * TURN_GAIN, -MAX_AUTO_TURN, MAX_AUTO_TURN);
                strafe = Range.clip(-yawError * STRAFE_GAIN, -MAX_AUTO_STRAFE, MAX_AUTO_STRAFE);

                telemetry.addData("Auto", "Drive %5.2f, Strafe %5.2f, Turn %5.2f", drive, strafe, turn);
            }
            telemetry.update();

            // Apply axes motions to drivetrain
            driver.drive(drive, -strafe, -turn);
            sleep(10);
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


