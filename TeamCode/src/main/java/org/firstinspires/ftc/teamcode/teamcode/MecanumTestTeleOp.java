package org.firstinspires.ftc.teamcode.teamcode;

import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teamcode.mechanism.MecanumDrive;

@TeleOp(name = "Ed-MecanumTestTeleOp", group = "Test")
public class MecanumTestTeleOp extends OpMode {

    MecanumDrive drive = new MecanumDrive();
    double forward, strafe, rotate;

    @Override
    public void init() {
        drive.initialize(hardwareMap);
    }


    double motorRatioOffset = 1.0;

    @Override
    public void loop() {
        // Test each individual wheel by gamepad buttons when neither left/right sticks are
        // being used. The expected button to wheel are
        //  Square: Front Left
        //  Triangle: Front Right
        //  Cross: Rear Left
        //  Circle: Rear Right
        if (gamepad1.dpad_up) {
            motorRatioOffset += 0.1;
            sleep(1000);
        } else if (gamepad1.dpad_down) {
            motorRatioOffset -= 0.1;
            sleep(1000);
        }

        if (gamepad1.left_stick_x + gamepad1.left_stick_y + gamepad1.right_stick_x == 0) {
            drive.displayTelemetry(telemetry);

            drive.powerFrontLeftMotor(gamepad1.square ? 0.5 : 0.0);
            drive.powerFrontRightMotor(gamepad1.triangle ? 0.5 : 0.0);
            drive.powerRearLeftMotor(gamepad1.cross ? 0.5 : 0.0);

            double rrmPower = (gamepad1.circle ? 0.5 : 0.0) * motorRatioOffset;
            drive.powerRearRightMotor(rrmPower);

            telemetry.addData("Motor Ratio Offset", motorRatioOffset);
            telemetry.addData("RRM Power", rrmPower);
            telemetry.update();
            return;
        }

        //----------------------------
        // Drive Controls (Done)
        //----------------------------
        double forward = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double rotate = gamepad1.right_stick_x;

        if (gamepad1.left_trigger > 0) {
            forward = forward / 2;
            strafe = strafe / 2;
            rotate = rotate / 2;
        }

        // driver.drive(forward, strafe, rotate);
        drive.driveFieldRelative(forward, strafe, rotate);
    }
}
