package org.firstinspires.ftc.teamcode.teamcode.test;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorGoBildaPinpoint;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.MecanumDrive;

@TeleOp(name = "Ed-MecanumSimple", group = "Workshop")
@Disabled
public class MecanumSimple extends OpMode {
    MecanumDrive drive = new MecanumDrive();
    GoBildaPinpointDriver pinpoint;

    @Override
    public void init() {
        drive.initialize(hardwareMap);
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

        pinpoint.setOffsets(0.1, -0, DistanceUnit.INCH);
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpoint.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD);
        pinpoint.resetPosAndIMU();
        // Set the location of the robot - this should be the place you are starting the robot from
        pinpoint.setPosition(new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0));
    }

    @Override
    public void loop() {

//        // Test each individual wheel by gamepad buttons when neither left/right sticks are
//        // being used. The expected button to wheel are
//        //  Square: Front Left
//        //  Triangle: Front Right
//        //  Cross: Rear Left
//        //  Circle: Rear Right
//        if (gamepad1.left_stick_x + gamepad1.left_stick_y + gamepad1.right_stick_x == 0) {
//            drive.powerFrontLeftMotor(gamepad1.square ? 0.5 : 0.0);
//            drive.powerFrontRightMotor(gamepad1.triangle ? 0.5 : 0.0);
//            drive.powerRearLeftMotor(gamepad1.cross ? 0.5 : 0.0);
//            drive.powerRearRightMotor(gamepad1.circle ? 0.5 : 0.0);
//            return;
//        }
//
//        //----------------------------
//        // Drive Controls (Done)
//        //----------------------------
//        double forward = -gamepad1.left_stick_y;
//        double strafe = gamepad1.left_stick_x;
//        double rotate = gamepad1.right_stick_x;
//
//        if (gamepad1.left_trigger > 0) {
//            forward = forward / 2;
//            strafe = strafe / 2;
//            rotate = rotate / 2;
//        }
//
//        // driver.drive(forward, strafe, rotate);
//        drive.driveFieldRelative(forward, strafe, rotate);

        // Pinpoint Tests
        telemetry.addLine("Push your robot around to see it track");
        telemetry.addLine("Press A to reset the position");
        if(gamepad1.dpad_up){
            // You could use readings from April Tags here to give a new known position to the pinpoint
            pinpoint.setPosition(new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0));
        }
        pinpoint.update();
        Pose2D pose2D = pinpoint.getPosition();

        telemetry.addData("X coordinate (IN)", pose2D.getX(DistanceUnit.INCH));
        telemetry.addData("Y coordinate (IN)", pose2D.getY(DistanceUnit.INCH));
        telemetry.addData("Heading angle (DEGREES)", pose2D.getHeading(AngleUnit.DEGREES));
        telemetry.update();
    }
}