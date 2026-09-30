package org.firstinspires.ftc.teamcode.teamcode.mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    DcMotor intakeMotor;

    public void init(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakemotor");
        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void turnOn(boolean turnOnYN) {
        if (turnOnYN) {
            intakeMotor.setPower(1.0);
        } else {
            intakeMotor.setPower(0.0);
        }
    }
}
