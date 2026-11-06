package org.firstinspires.ftc.teamcode.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.teamcode.mechanism.Arms;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.Intake;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.MecanumDrive;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.Shooter;

@TeleOp(name = "Ed-MecanumRobotOriented", group = "Test")
public class MecanumRobotOriented extends OpMode {
    Intake myIntake;
    Arms myArms;
    Shooter myShooter;

    // pollen shooter port 0 control hub = pShooter
    // 1 = nectar shooter = nShooter
    // 2 = sweeper motor core hex motor = SM
    // 3 = intake core hex = IM

    // New Servos - nector pollen pushers NK and PK

    // arms - on stop, it retracts
    // arms - motor rotation stops when retracted
    // nectar and pollen shooter kicker using ->

    MecanumDrive drive = new MecanumDrive();
    double forward, strafe, rotate;

    public MecanumRobotOriented() {
        myIntake = new Intake();
        myArms = new Arms();
        myShooter = new Shooter();
    }

    @Override
    public void init() {
        drive.initialize(hardwareMap);
        myIntake.init(hardwareMap);
        myArms.init(hardwareMap);
        myShooter.init(hardwareMap);
    }

    double nectarShootPower= 0;
    double pollenShootPower= 0;

    boolean IntakeOn = false;
    float LegPosition = 0;

    @Override
    public void loop() {


        if(gamepad2.crossWasPressed())
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

        if(gamepad2.rightTriggerWasPressed()) {
            myShooter.turnOnShooter();
        }
        else if(gamepad2.rightTriggerWasReleased()){
            myShooter.turnOffShooter();
        }

        if(gamepad2.left_stick_y > 0) {
            //LegPosition += LegPosition + gamepad1.left_stick_y;

            //move both legs
            myArms.extend();
        }
        else if(gamepad2.left_stick_y < 0) {
            myArms.retract();
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

        drive.drive(forward, strafe, rotate);
        //drive.driveFieldRelative(forward, strafe, rotate);
    }
}


/* COPY TO CLASS WHEN TESTING ONLY
//        if (gamepad1.left_stick_x + gamepad1.left_stick_y + gamepad1.right_stick_x == 0) {
//        drive.displayTelemetry(telemetry);
//
//            drive.powerFrontLeftMotor(gamepad1.square ? 0.5 : 0.0);
//            drive.powerFrontRightMotor(gamepad1.triangle ? 0.5 : 0.0);
//            drive.powerRearLeftMotor(gamepad1.cross ? 0.5 : 0.0);
//            drive.powerRearRightMotor(gamepad1.circle ? 0.5 : 0.0);
//
//            telemetry.update();
//            return;
//                    }
*/