package org.firstinspires.ftc.teamcode.teamcode.mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

//@TeleOp(name = "Shooter", group = "Shooter")
public class Shooter {
    DcMotor pollenShooter, nectarShooter;
    HardwareMap hardwareMap ;

    public void init(HardwareMap hwMap) {
        this.hardwareMap = hwMap ;
        pollenShooter = this.hardwareMap.get(DcMotor.class,"pShooter");
        nectarShooter = this.hardwareMap.get(DcMotor.class,"nShooter");

        nectarShooter.setDirection(DcMotorSimple.Direction.FORWARD);
        pollenShooter.setDirection(DcMotorSimple.Direction.REVERSE);
        pollenShooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        nectarShooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setPollenShooter(double speed) {
        this.pollenShooter.setPower(speed);
        this.nectarShooter.setPower(speed);
    }

    public void turnOnShooter() {
        this.pollenShooter.setPower(1);
        this.nectarShooter.setPower(1);
    }

    public void turnOffShooter() {
        this.pollenShooter.setPower(0);
        this.nectarShooter.setPower(0);
    }
}