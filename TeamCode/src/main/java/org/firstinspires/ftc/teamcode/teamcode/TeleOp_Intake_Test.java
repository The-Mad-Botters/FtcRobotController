package org.firstinspires.ftc.teamcode.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.teamcode.mechanism.Intake;

public class TeleOp_Intake_Test extends OpMode {
    Intake myIntake;

    @Override
    public void init() {
        myIntake.init(hardwareMap);
    }

    @Override
    public void loop() {
        myIntake.turnOn(gamepad1.left_trigger_pressed);
    }
}
