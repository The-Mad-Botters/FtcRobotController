package org.firstinspires.ftc.teamcode.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teamcode.mechanism.MecanumDrive;

@TeleOp(name = "EdTestTeleOp", group = "Test")
public class MecanumTestTeleOp extends OpMode {

    MecanumDrive drive = new MecanumDrive();
    double forward, strafe, rotate;

    @Override
    public void init() {
        drive.initialize(hardwareMap);
    }

    @Override
    public void loop() {
        forward = -gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        drive.driveFieldRelative(forward, strafe, rotate);
    }
}
