package org.firstinspires.ftc.teamcode.teamcode.PritikaAutos;

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
import org.firstinspires.ftc.teamcode.teamcode.mechanism.Arms;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.Intake;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.teamcode.paths.IPaths;

@Autonomous(name = "East Shoot Collect Park", group = "Comp Nov 14")
public class GardenPollenCollect extends LinearOpMode {

    // Autonomous routine
    Intake myIntake;
    Arms myArms;
    Shooter myShooter;

    public GardenPollenCollect() {
        myIntake = new Intake();
        myArms = new Arms();
        myShooter = new Shooter();
    }

    public Command autoRoutine() {
        return sequential(
                follow(follower, goToFirstShot()),
                instant(() -> {
                    myShooter.setPollenShooterPower(0.55);
                }),
                waitMs(1500),
                instant(() -> {
                    myShooter.openFlipper();
                    myIntake.turnOnForShooting();
                }),
                waitMs(3500),
                instant(() -> {
                    myShooter.turnOffShooter();
                    myIntake.turnOff();
                    myShooter.closeFlipper();
                }),
                follow(follower, approachPollen()),
                instant(() -> {
                    myIntake.turnOn();
                }),
                follow(
                    follower,
                    gatherPollen()
                            .with(Constants.foresightConfig.maxPathSpeed.at(0.3))
                ),
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

    private Follower follower;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(8.577, 60.052, 0);
    private final Pose goToFirstShot = poseFactory.of(57.1142, 8.7552, 270);
    private final Pose goToFirstShotControl1 = poseFactory.of(40.7468, 40.3004, 0);
    private final Pose approachPollenStart = poseFactory.of(57.1142, 8.7552, 270);
    private final Pose approachPollen = poseFactory.of(24.6763, 8.5206, 180);
    private final Pose gatherPollen = poseFactory.of(9.4141, 8.3177, 180);
    private final Pose goParkStart = poseFactory.of(9.4141, 8.3177, 180);
    private final Pose goPark = poseFactory.of(8.6914, 96.7928, 0);
    private final Pose goParkControl1 = poseFactory.of(28.3676, 46.9354, 0);

    public Path goToFirstShot() {
        return Paths.curve(start, goToFirstShotControl1, goToFirstShot).linear(start, goToFirstShot);
    }

    public Path approachPollen() {
        return Paths.line(approachPollenStart, approachPollen).linear(approachPollenStart, approachPollen);
    }

    public Path gatherPollen() {
        return Paths.line(approachPollen, gatherPollen).constant(gatherPollen);
    }

    public Path goPark() {
        return Paths.curve(goParkStart, goParkControl1, goPark).linear(goParkStart, goPark);
    }
}
