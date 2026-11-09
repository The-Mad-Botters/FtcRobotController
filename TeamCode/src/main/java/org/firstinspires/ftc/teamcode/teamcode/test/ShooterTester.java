package org.firstinspires.ftc.teamcode.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "ShooterTester", group = "Ed-Test")
@Disabled
public class ShooterTester extends OpMode {

    DcMotor pollenShooter;
    DcMotor nectarShooter;

    @Override
    public void init() {
        pollenShooter = hardwareMap.get(DcMotor.class, "PS");
        nectarShooter = hardwareMap.get(DcMotor.class, "NS");

        pollenShooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        nectarShooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }


    double pollenShooterPower = 0.5;
    double nectarShooterPower = 0.5;

    @Override
    public void loop() {

        if (gamepad1.dpadUpWasPressed()) {
            pollenShooterPower += 0.05;
        } else if (gamepad1.dpadDownWasPressed()) {
            pollenShooterPower -= 0.05;
        } else if (gamepad1.dpadLeftWasPressed()) {
            nectarShooterPower -= 0.05;
        } else if (gamepad1.dpadRightWasPressed()) {
            nectarShooterPower += 0.05;
        }

        telemetry.addData("Pollen Shooter Power", pollenShooterPower);
        telemetry.addData("Nectar Shooter Power", nectarShooterPower);
        telemetry.update();

        if (gamepad1.left_trigger > 0) {
            pollenShooter.setPower(pollenShooterPower);
        }

        if (gamepad1.right_trigger > 0) {
            nectarShooter.setPower(nectarShooterPower);
        }

    }
}
