package org.firstinspires.ftc.teamcode.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;

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
import static com.pedropathing.ivy.groups.Groups.parallel;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

@Autonomous(name = "Pedro Auto OpMode", group = "Ed")
@Disabled
public class PedroAutoOpMode extends LinearOpMode {

    private Follower follower;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(60, 12, 90);
    private final Pose toShoot = poseFactory.of(60, 72, 270);
    private final Pose toShootControl1 = poseFactory.of(0, 36, 0);
    private final Pose backabitStart = poseFactory.of(60, 72, 270);
    private final Pose backabit = poseFactory.of(60, 36, 180);
    private final Pose randoLookbackStart = poseFactory.of(60, 36, 180);
    private final Pose randoLookback = poseFactory.of(24, 60, 327);
    private final Pose backToStartStart = poseFactory.of(24, 60, 327);
    private final Pose backToStart = poseFactory.of(60, 12, 90);

    // Autonomous routine
    public Command autoRoutine() {
        return sequential(
                follow(follower, toShoot()),
                instant(this::shootAtBucket),
                waitMs(3000),
                instant(() -> {
                    telemetry.addLine("Done Shooting!");
                    telemetry.update();
                }),
                follow(follower, backabit()),
                instant(() -> {
                    telemetry.addLine("Now what? Maybe I'll hang out over there like I wasn't doing anything.");
                    telemetry.update();
                }),
                waitMs(2000),
                instant(() -> {
                    telemetry.clearAll();
                    telemetry.update();
                }),
                follow(follower, randoLookback()),
                instant(() -> {
                    telemetry.addLine("Let's go home. I'm bored.");
                    telemetry.update();
                }),
                waitMs(3000),
                instant(() -> {
                    telemetry.clearAll();
                    telemetry.update();
                }),
                follow(follower, backToStart()),
                instant(() -> {
                    telemetry.addLine("Home again. Yay!");
                    telemetry.update();
                })
        );
    }

    private void shootAtBucket() {
        telemetry.addData("Shooting At", "bucket");
        telemetry.update();
    }

    @Override
    public void runOpMode() {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(start);
        follower.update();

        waitForStart();
        schedule(autoRoutine().raceWith());

        while (opModeIsActive()) {
            follower.update();
            Scheduler.execute();

//            telemetry.addData("x", follower.pose().x());
//            telemetry.addData("y", follower.pose().y());
//            telemetry.addData("heading", follower.pose().heading());
//
//            if (follower.currentPath() != null) {
//                telemetry.addData("Current path distance remaining", follower.distanceToEndpoint());
//                telemetry.addData("Path number", follower.pathIndex());
//            }
//
//            telemetry.update();
        }
    }

    public Path toShoot() {
        return Paths.curve(start, toShootControl1, toShoot).linear(start, toShoot);
    }

    public Path backabit() {
        return Paths.line(backabitStart, backabit).linear(backabitStart, backabit);
    }

    public Path randoLookback() {
        return Paths.line(randoLookbackStart, randoLookback).linear(randoLookbackStart, randoLookback);
    }

    public Path backToStart() {
        return Paths.line(backToStartStart, backToStart).linear(backToStartStart, backToStart);
    }
}