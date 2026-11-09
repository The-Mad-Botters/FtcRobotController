package org.firstinspires.ftc.teamcode.teamcode.mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

//@TeleOp(name = "Shooter", group = "Shooter")
public class Shooter {
    DcMotor pollenShooter, nectarShooter;
    Servo pollenFlipper, nectarFlipper;
    HardwareMap hardwareMap ;

    public void init(HardwareMap hwMap) {
        this.hardwareMap = hwMap ;
        pollenShooter = this.hardwareMap.get(DcMotor.class,"pShooter");
        nectarShooter = this.hardwareMap.get(DcMotor.class,"nShooter");
        pollenFlipper = this.hardwareMap.get(Servo.class,"pFlipper");
        nectarFlipper = this.hardwareMap.get(Servo.class,"nFlipper");

        nectarShooter.setDirection(DcMotorSimple.Direction.FORWARD);
        pollenShooter.setDirection(DcMotorSimple.Direction.REVERSE);
        pollenShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        nectarShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        nectarFlipper.setDirection(Servo.Direction.REVERSE);

        pollenFlipper.setPosition(0);
        nectarFlipper.setPosition(0);
    }

    public void setPollenShooter(double speed) {
        this.pollenShooter.setPower(speed);
        this.nectarShooter.setPower(speed);
    }

    public void turnOnShooter() {
        this.pollenShooter.setPower(0.75);
        this.nectarShooter.setPower(0.75);
    }

    public void setNectarShooterPower(double power) {
        this.nectarShooter.setPower(power);
    }

    public void setPollenShooterPower(double power) {
        this.pollenShooter.setPower(power);
    }

    public void turnOffShooter() {
        this.pollenShooter.setPower(0);
        this.nectarShooter.setPower(0);
    }

    public void flipperPosition(float rightTrigger) {
        nectarFlipper.setPosition(rightTrigger);
        pollenFlipper.setPosition(rightTrigger);
    }
}