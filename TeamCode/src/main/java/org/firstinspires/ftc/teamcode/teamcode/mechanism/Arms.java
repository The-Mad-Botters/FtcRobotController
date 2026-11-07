package org.firstinspires.ftc.teamcode.teamcode.mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arms {
    private Servo leftArm;
    private Servo rightArm;
    private DcMotor sweeperMotor;

    public void init(HardwareMap hwMap) {
        leftArm = hwMap.get(Servo.class, "LA");
        rightArm = hwMap.get(Servo.class, "RA");

        leftArm.setDirection(Servo.Direction.REVERSE);

        sweeperMotor = hwMap.get(DcMotor.class,"SM");
        sweeperMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void extend() {
        leftArm.setPosition(1);
        rightArm.setPosition(1);
    }

    public void retract(){
        leftArm.setPosition(0);
        rightArm.setPosition(0);
    }

    public void extendLeft() {
        leftArm.setPosition(1.0);
    }

    public void retractLeft() {
        leftArm.setPosition(0);
    }

    public void extendRight() {rightArm.setPosition(1.0);}

    public void retractRight() {
        rightArm.setPosition(0.0);
    }

    public void turnSweeperOn(){
        sweeperMotor.setPower(1.0);
    }
    public void turnSweeperOff() {
        sweeperMotor.setPower(0.0);
    }
}
