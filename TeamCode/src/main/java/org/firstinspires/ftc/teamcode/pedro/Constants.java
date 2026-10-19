package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static Follower create(HardwareMap h) {
        return new Follower(
            new PinpointLocalizer(h, localizerConfig),
            new Mecanum(h, drivetrainConfig),
            new Foresight(foresightConfig)
        );
    }

    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("FLD");
        c.frontRightName.set("FRD");
        c.backLeftName.set("BLD");
        c.backRightName.set("BRD");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
    });

    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(0.004237019640254224);
        c.yPodOffset.set(-0.1701853782173217);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.21067268252409857);
                Controller secondaryTranslationalForward = Controller.proportional(0.07783792691490149);
                Controller primaryTranslationalLateral = Controller.proportional(0.7051081108481176);
                Controller secondaryTranslationalLateral = Controller.proportional(0.260518606122661);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.014562937152220805));
                c.brake.set(Controller.proportionalFeedforward(0.012378496579387685));

                c.headingFeedback.set(Controller.proportional(2.9186022545871992));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.05830023036613919, 0.00950325423626077));

                c.linearBrakeCoefficients.set(Matrix.diag(0.07699168900294483, 0.13294880325334038));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0027277183216747234, 8.44703872548282E-4));

                c.maxAchievableForwardVelocity.set(69.82323023104922);
                c.maxAchievableStrafeVelocity.set(59.135738640140104);
                c.naturalForwardDeceleration.set(62.13879261423437);
                c.naturalStrafeDeceleration.set(74.87977268473969);
            }
    );
}