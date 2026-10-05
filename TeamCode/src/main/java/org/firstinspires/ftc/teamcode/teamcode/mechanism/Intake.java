package org.firstinspires.ftc.teamcode.teamcode.mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    DcMotor intakeMotor;

    public void init(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "IM");
        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setPower(double power) {
        intakeMotor.setPower(power);
    }
}
