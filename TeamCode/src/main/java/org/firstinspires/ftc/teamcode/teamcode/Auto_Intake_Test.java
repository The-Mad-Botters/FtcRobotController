package org.firstinspires.ftc.teamcode.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.teamcode.mechanism.Intake;

@Autonomous(name = "Ed_Intake_Test", group = "Test")
public class Auto_Intake_Test extends LinearOpMode {
    Intake myIntake = new Intake();

    @Override
    public void runOpMode() {
        myIntake.init(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {
            myIntake.setPower(0.5);
            sleep(3000);
            myIntake.setPower(0);
            myIntake.setPower(-0.5);
            sleep(3000);
            myIntake.setPower(0);
        }
    }
}
