package org.firstinspires.ftc.teamcode.teamcode.mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

//@TeleOp(name = "Shooter", group = "Shooter")
public class Shooter {
    DcMotor pollenShooter;
    Servo pollenFlipper;
    HardwareMap hardwareMap ;

    public void init(HardwareMap hwMap) {
        this.hardwareMap = hwMap ;
        pollenShooter = this.hardwareMap.get(DcMotor.class,"pShooter");
        pollenFlipper = this.hardwareMap.get(Servo.class,"pFlipper");

        pollenShooter.setDirection(DcMotorSimple.Direction.REVERSE);
        pollenShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        pollenFlipper.setPosition(0);
    }

    public void setPollenShooter(double speed) {
        this.pollenShooter.setPower(speed);
    }

    public void turnOnShooter() {
        this.pollenShooter.setPower(0.75);
    }



    public void setPollenShooterPower(double power) {
        this.pollenShooter.setPower(power);
    }

    public void turnOffShooter() {
        this.pollenShooter.setPower(0);
    }

    public void flipperPosition(float rightTrigger) {
        pollenFlipper.setPosition(rightTrigger);
    }
}