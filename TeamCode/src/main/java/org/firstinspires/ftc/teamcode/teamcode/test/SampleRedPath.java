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
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous(name = "SampleRedPath", group = "Autonomous")
public class SampleRedPath extends LinearOpMode {

    private Follower follower;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(8.2312, 59.4268, 0);
    private final Pose toSouthHive = poseFactory.of(58.3215, 35.0607, 90);
    private final Pose goToPollenCornerStart = poseFactory.of(58.3215, 35.0607, 90);
    private final Pose goToPollenCorner = poseFactory.of(20.8719, 8.335, 180);
    private final Pose pickUpPollenStart = poseFactory.of(20.8719, 8.335, 180);
    private final Pose pickUpPollen = poseFactory.of(8.0191, 8.2932, 180);
    private final Pose toSouthHiveAgainStart = poseFactory.of(8.0191, 8.2932, 180);
    private final Pose toSouthHiveAgain = poseFactory.of(59.1518, 34.3447, 90);
    private final Pose toNorthHiveStart = poseFactory.of(59.1518, 34.3447, 90);
    private final Pose toNorthHive = poseFactory.of(59.3581, 105.7832, 270);
    private final Pose goParkStart = poseFactory.of(59.3581, 105.7832, 270);
    private final Pose goPark = poseFactory.of(12.5223, 118.4615, 0);

    // Autonomous routine
    public Command autoRoutine() {
        return sequential(
                follow(follower, toSouthHive()),
                follow(follower, goToPollenCorner()),
                follow(follower, pickUpPollen()),
                follow(follower, toSouthHiveAgain()),
                follow(follower, toNorthHive()),
                follow(follower, goPark())
        );
    }

    @Override
    public void runOpMode() {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(start);
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

    public Path toSouthHive() {
        return Paths.line(start, toSouthHive).linear(start, toSouthHive);
    }

    public Path goToPollenCorner() {
        return Paths.line(goToPollenCornerStart, goToPollenCorner).linear(goToPollenCornerStart, goToPollenCorner);
    }

    public Path pickUpPollen() {
        return Paths.line(pickUpPollenStart, pickUpPollen).linear(pickUpPollenStart, pickUpPollen);
    }

    public Path toSouthHiveAgain() {
        return Paths.line(toSouthHiveAgainStart, toSouthHiveAgain).linear(toSouthHiveAgainStart, toSouthHiveAgain);
    }

    public Path toNorthHive() {
        return Paths.line(toNorthHiveStart, toNorthHive).linear(toNorthHiveStart, toNorthHive);
    }

    public Path goPark() {
        return Paths.line(goParkStart, goPark).linear(goParkStart, goPark);
    }
}
