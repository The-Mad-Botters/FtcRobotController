package org.firstinspires.ftc.teamcode.teamcode.mechanism;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arms {
    private Servo leftArm;
    private Servo rightArm;

    public void init(HardwareMap hwMap) {
        leftArm = hwMap.get(Servo.class, "LA");
        rightArm = hwMap.get(Servo.class, "RA");
    }

    public void extend() {
        leftArm.setPosition(1.0);
        rightArm.setPosition(0);
    }

    public void retract(){
        leftArm.setPosition(0);
        rightArm.setPosition(1);
    }

    public void middle(){
        leftArm.setPosition(0.5);
        rightArm.setPosition(0.5);
    }

}
