package org.firstinspires.ftc.teamcode.teamcode.mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    private DcMotor intakeMotor, sweeperMotor;

    public void init(HardwareMap hwMap) {
        intakeMotor = hwMap.get(DcMotor.class,"IM");
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void turnOn(){
        intakeMotor.setPower(0.5);
    }
    public void turnOff() {
        intakeMotor.setPower(0.0);

    }
    public void spitout(){
        intakeMotor.setPower(-1);
    }
}
