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

@Autonomous(name = "North Wait Shoot", group = "Comp Nov 14")
public class WaitTiltPark extends LinearOpMode {

    // Autonomous routine

    Intake myIntake;
    Arms myArms;
    Shooter myShooter;

    public WaitTiltPark() {
        myIntake = new Intake();
        myArms = new Arms();
        myShooter = new Shooter();
    }

    public Command autoRoutine() {
        return sequential(
                waitMs(15000),  // wait 15 seconds, then start.
                follow(follower, goToFirstShot()),
                instant(() -> {
                    myShooter.setPollenShooterPower(0.55);
                }),
                waitMs(1500),
                instant(() -> {
                    myShooter.openFlipper();
                    myIntake.turnOnForShooting();
                }),
                waitMs(2500),
                instant(() -> {
                    myShooter.turnOffShooter();
                    myIntake.turnOff();
                    myShooter.closeFlipper();
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

    private final Pose start = poseFactory.of(58.3911, 133.2634, 0);
    private final Pose goToFirstShot = poseFactory.of(58.7912, 132.9627, 90);
    private final Pose goToFirstShotControl1 = poseFactory.of(66.5509, 121.2199, 0);
    private final Pose goParkStart = poseFactory.of(58.7912, 132.9627, 90);
    private final Pose goPark = poseFactory.of(10.2365, 119.4856, 0);
    private final Pose goParkControl1 = poseFactory.of(48.3068, 106.5257, 0);

    public Path goToFirstShot() {
        return Paths.curve(start, goToFirstShotControl1, goToFirstShot).linear(start, goToFirstShot);
    }

    public Path goPark() {
        return Paths.curve(goParkStart, goParkControl1, goPark).linear(goParkStart, goPark);
    }
}
