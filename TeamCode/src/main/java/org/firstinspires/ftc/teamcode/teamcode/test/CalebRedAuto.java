package org.firstinspires.ftc.teamcode.teamcode.test;

import static com.pedropathing.api.Paths.*;
import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.*;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.Arms;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.Intake;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.Shooter;

@Autonomous(name = "CalebRedAuto", group = "Autonomous")
@Disabled
public class CalebRedAuto extends LinearOpMode {
    Intake myIntake = new Intake();
    Arms myArms = new Arms();
    Shooter myShooter = new Shooter();

    private Follower follower;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose calebByFlower = poseFactory.of(7.8651, 58.5897, 0);
    private final Pose toFirstShot = poseFactory.of(59.4592, 23.4637, 90);
    private final Pose toFirstShotControl1 = poseFactory.of(27.5075, 58.6433, 0);
    private final Pose toFirstShotControl2 = poseFactory.of(42.6447, 31.6452, 0);
    private final Pose approachPollenLaneStart = poseFactory.of(59.4592, 23.4637, 90);
    private final Pose approachPollenLane = poseFactory.of(28.5462, 8.4969, 180);
    private final Pose pickUpBalls = poseFactory.of(8.6178, 8.6188, 180);
    private final Pose goParkStart = poseFactory.of(8.6178, 8.6188, 180);
    private final Pose goPark = poseFactory.of(10.5343, 94.7731, 0);
    private final Pose goParkControl1 = poseFactory.of(36.5653, 65.9311, 0);

    // Autonomous routine
    public Command autoRoutine() {
        return sequential(
                follow(follower, toFirstShot()),
                instant(() -> myShooter.turnOnShooter()),
                waitMs(3000),
                follow(follower, approachPollenLane()),
                instant(() -> {
                    myIntake.turnOn();
                }),
                follow(follower, pickUpBalls()),
                instant(() -> {
                    myIntake.turnOff();
                }),
                follow(follower, goPark())
        );
    }

    @Override
    public void runOpMode() {
        // we need to initialize our mechanisms
        myIntake.init(hardwareMap);
        myArms.init(hardwareMap);
        myShooter.init(hardwareMap);

        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(calebByFlower);
        follower.update();

        waitForStart();
        schedule(autoRoutine());

        while (opModeIsActive()) {
            follower.update();
            Scheduler.execute();

            telemetry.addData("x", follower.pose().x());
            telemetry.addData("y", follower.pose().y());
            telemetry.addData("heading", follower.pose().heading());

            if (follower.currentPath() != null) {
                telemetry.addData("Current path distance remaining", follower.distanceToEndpoint());
                telemetry.addData("Path number", follower.pathIndex());
            }

            telemetry.update();
        }
    }

    public void shoot() {
        myShooter.turnOnShooter();
    }

    public Path toFirstShot() {
        return Paths.curve(calebByFlower, toFirstShotControl1, toFirstShotControl2, toFirstShot).linear(calebByFlower, toFirstShot);
    }

    public Path approachPollenLane() {
        return Paths.line(approachPollenLaneStart, approachPollenLane).linear(approachPollenLaneStart, approachPollenLane);
    }

    public Path pickUpBalls() {
        return Paths.line(approachPollenLane, pickUpBalls).constant(pickUpBalls);
    }

    public Path goPark() {
        return Paths.curve(goParkStart, goParkControl1, goPark).linear(goParkStart, goPark);
    }
}
