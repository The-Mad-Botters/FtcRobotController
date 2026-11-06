package org.firstinspires.ftc.teamcode.teamcode.mechanism;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class MecanumDrive {

    // Drive Motors
    public DcMotor frontLeftDrive, frontRightDrive, rearLeftDrive, rearRightDrive;

    // can be used to determine robot orientation (gyro)
    private IMU imu;

    public void initialize(HardwareMap hardwareMap) {
        frontLeftDrive = hardwareMap.get(DcMotor.class, "FLD");
        frontRightDrive = hardwareMap.get(DcMotor.class, "FRD");
        rearLeftDrive = hardwareMap.get(DcMotor.class, "BLD");
        rearRightDrive = hardwareMap.get(DcMotor.class, "BRD");

        frontLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
//        rearLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        rearRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        frontLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearLeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearRightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        imu = hardwareMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.LEFT);

        imu.initialize(new IMU.Parameters(RevOrientation));
    }

    public void drive(double forward, double strafe, double rotate) {
        double frontLeftPower = forward + strafe + rotate;
        double rearLeftPower = forward - strafe + rotate;
        double frontRightPower = forward - strafe - rotate;
        double rearRightPower = forward + strafe - rotate;

        double maxPower = 1.0;
        double maxSpeed = 1.0; // this is a throttle that we can set lower for demos. Do no set higher than 1.0

        maxPower = Math.max(maxPower, Math.abs(frontLeftPower));
        maxPower = Math.max(maxPower, Math.abs(rearLeftPower));
        maxPower = Math.max(maxPower, Math.abs(frontRightPower));
        maxPower = Math.max(maxPower, Math.abs(rearRightPower));

        this.frontLeftDrive.setPower(maxSpeed * (frontLeftPower / maxPower));
        this.rearRightDrive.setPower(maxSpeed * (rearRightPower / maxPower));
        this.rearLeftDrive.setPower(maxSpeed * (rearLeftPower / maxPower));
        this.frontRightDrive.setPower(maxSpeed * (frontRightPower / maxPower));
    }

    public void driveFieldRelative(double forward, double strafe, double rotate) {
        double theta = Math.atan2(forward, strafe);
        double r = Math.hypot(strafe, forward);

        theta = AngleUnit.normalizeRadians(theta -
                imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));

        double newForward = r * Math.sin(theta);
        double newStrafe = r * Math.cos(theta);

        this.drive(newForward, newStrafe, rotate);
    }

    public void powerFrontLeftMotor(double power) {
        frontLeftDrive.setPower(power);
    }

    public void powerFrontRightMotor(double power) {
        frontRightDrive.setPower(power);
    }

    public void powerRearLeftMotor(double power) {
        rearLeftDrive.setPower(power);
    }

    public void powerRearRightMotor(double power) {
        rearRightDrive.setPower(power);
    }

    public void displayTelemetry(Telemetry telemetry) {
        telemetry.addData("Front Left Encoder", frontLeftDrive.getCurrentPosition());
        telemetry.addData("Front Right Encoder", frontRightDrive.getCurrentPosition());
        telemetry.addData("Rear Left Encoder", rearLeftDrive.getCurrentPosition());
        telemetry.addData("Rear Right Encoder", rearRightDrive.getCurrentPosition());
    }
}
