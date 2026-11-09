package org.firstinspires.ftc.teamcode.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teamcode.mechanism.Arms;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.Intake;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.MecanumDrive;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.Shooter;

@TeleOp(name = "TeleOp", group = "BIO BUZZ")
public class BioBuzzTeleOp extends OpMode {
    Intake myIntake;
    Shooter myShooter;


    MecanumDrive drive = new MecanumDrive();
    double forward, strafe, rotate;

    public BioBuzzTeleOp() {
        myIntake = new Intake();

        myShooter = new Shooter();
    }

    @Override
    public void init() {
        drive.initialize(hardwareMap);
        myIntake.init(hardwareMap);
        myShooter.init(hardwareMap);


    }

    double nectarShootPower= 0.0;
    double pollenShootPower= 0.70;

    boolean IntakeOn = false;
    boolean shooterOn = false;
    float LegPosition = 0;
    boolean useFieldOriented = false;

    @Override
    public void loop() {

        //----------------------------
        // Turn On/Off Intake and Sweeper Motors
        //----------------------------
        if(gamepad2.aWasPressed())
        {
            if(IntakeOn == false)
            {
                myIntake.turnOn();
                IntakeOn = true;
            }
            else {
                myIntake.turnOff();
                IntakeOn = false;
            }
        }


        //----------------------------
        // Turn On/Off Shooter Motors
        //----------------------------

        if(gamepad2.rightBumperWasPressed()) {
            if (shooterOn) {
                shooterOn = false;
            }
            else {
                shooterOn = true;
            }
        }

//        if (gamepad2.dpadUpWasPressed()) {
//            nectarShootPower = Math.min(1.0, nectarShootPower + 0.01);
//        } else if (gamepad2.dpadDownWasPressed()) {
//            nectarShootPower = Math.max(0.0, nectarShootPower - 0.01);
//        } else
        if (gamepad2.dpadRightWasPressed()) {
            pollenShootPower = Math.min(1.0, pollenShootPower + 0.01);
        } else if (gamepad2.dpadLeftWasPressed()) {
            pollenShootPower = Math.max(0.0, pollenShootPower - 0.01);
        }

        if (shooterOn ) {
            telemetry.addData("Shooter Wheel Is", "ON");
            myShooter.setNectarShooterPower(nectarShootPower);
            myShooter.setPollenShooterPower(pollenShootPower);
            myShooter.flipperPosition(gamepad2.right_trigger);
        } else if (shooterOn == false) {
            telemetry.addData("Shooter Wheel Is", "OFF");
            myShooter.turnOffShooter();
        }

        telemetry.addData("nectarShootPower", nectarShootPower);
        telemetry.addData("pollenShooterPower", pollenShootPower);

        //----------------------------
        // Drive Controls (Done)
        //----------------------------
        if (gamepad1.aWasPressed()) {
            useFieldOriented = !useFieldOriented;
        }

        double forward = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double rotate = gamepad1.right_stick_x;

        if (gamepad1.left_trigger > 0) {
            forward = forward / 2;
            strafe = strafe / 2;
            rotate = rotate / 2;
        }

//        if (useFieldOriented) {
//            telemetry.addData("Field Oriented Is", "ON");
//            drive.driveFieldRelative(forward, strafe, rotate);
//        } else {
            telemetry.addData("Field Oriented Is", "OFF");
            drive.drive(forward, strafe, rotate);
//        }

        telemetry.update();
    }
}
