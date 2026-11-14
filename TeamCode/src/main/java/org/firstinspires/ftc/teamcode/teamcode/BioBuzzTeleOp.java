package org.firstinspires.ftc.teamcode.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teamcode.mechanism.Arms;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.Intake;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.MecanumDrive;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.Shooter;

@TeleOp(name = "TeleOp", group = "Comp Nov 14")
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

    double pollenShootPower= 0.55;

    boolean shooterOn = false;
    boolean useFieldOriented = true;

    @Override
    public void loop() {

        telemetry.addData("pidCoefficients.p", myShooter.pidCoefficients.p);
        telemetry.addData("pidCoefficients.i", myShooter.pidCoefficients.i);
        telemetry.addData("pidCoefficients.d", myShooter.pidCoefficients.d);
        telemetry.addData("PID f", myShooter.f);
        telemetry.addData("RPM",myShooter.getRpm());

        //----------------------------
        // Turn On/Off Intake and Sweeper Motors
        //----------------------------
        if(gamepad2.a) {
            myIntake.turnOn();
        }
        else if(gamepad2.b) {
            myIntake.spitout();
        } else {
            myIntake.turnOff();
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

        if (gamepad2.dpadRightWasPressed()) {
            pollenShootPower = Math.min(1.0, pollenShootPower + 0.01);
        } else if (gamepad2.dpadLeftWasPressed()) {
            pollenShootPower = Math.max(0.0, pollenShootPower - 0.01);
        }


        if (shooterOn ) {
            telemetry.addData("Shooter Wheel Is", "ON");
            myShooter.setPollenShooterPower(pollenShootPower);
            // myShooter.flipperPosition(gamepad2.right_trigger);
            if (gamepad2.right_trigger > 0) {
                myShooter.openFlipper();
                myIntake.turnOn(); //.turnOnForShooting();
            } else {
                myShooter.closeFlipper();
                //myIntake.turnOff();
            }
        } else if (shooterOn == false) {
            telemetry.addData("Shooter Wheel Is", "OFF");
            myShooter.turnOffShooter();
        }

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

        if (useFieldOriented) {
            telemetry.addData("Field Oriented Is", "ON");
            drive.driveFieldRelative(forward, strafe, rotate);
        } else {
            telemetry.addData("Field Oriented Is", "OFF");
            drive.drive(forward, strafe, rotate);
        }

        telemetry.update();
    }
}

//
// boolean IntakeOn = false;
// ...
//        if(gamepad2.a)
//        {
//            if(IntakeOn == false)
//            {
//                myIntake.turnOn();
//                IntakeOn = true;
//            }
//            else {
//                myIntake.turnOff();
//                IntakeOn = false;
//            }
//        }
//        else if(gamepad2.bWasPressed())
//        {
//            if (IntakeOn== false)
//            {
//                myIntake.spitout();
//                IntakeOn = true;
//            }
//            else {
//                myIntake.turnOff();
//                IntakeOn = false;
//            }
//        }
