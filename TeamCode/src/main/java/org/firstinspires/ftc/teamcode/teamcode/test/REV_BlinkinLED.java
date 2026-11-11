package org.firstinspires.ftc.teamcode.teamcode.test;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

    public class REV_BlinkinLED extends OpMode {

    RevBlinkinLedDriver lights;


    public void init() {
        lights = hardwareMap.get(RevBlinkinLedDriver.class, "lights");
    }

    public void loop() {
        if (gamepad1.dpad_up) {
            lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.TWINKLES_OCEAN_PALETTE);
        }
    }
}

