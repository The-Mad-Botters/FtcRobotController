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
        c.frontLeftDirection.set(DcMotorSimple.Direction.FORWARD);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
    });

    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(3.1975414246086062);
        c.yPodOffset.set(-3.922762532872478);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.192511368627162);
                Controller secondaryTranslationalForward = Controller.proportional(0.07112780670922828);
                Controller primaryTranslationalLateral = Controller.proportional(0.26981498235110696);
                Controller secondaryTranslationalLateral = Controller.proportional(0.0996894263896247);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.025522663822282));
                c.brake.set(Controller.proportionalFeedforward(0.0216942642489397));

                c.headingFeedback.set(Controller.proportional(3.9062608283253013));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.04788361164434831, 0.007689004363505731));

                c.linearBrakeCoefficients.set(Matrix.diag(0.045308291016137765, 0.03935382353356086));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0021324159772245117, 0.0022469076751024416));

                c.maxAchievableForwardVelocity.set(40.94002586826685);
                c.maxAchievableStrafeVelocity.set(32.04219587246231);
                c.naturalForwardDeceleration.set(71.47034980661785);
                c.naturalStrafeDeceleration.set(92.08716436492612);
            }
    );
}