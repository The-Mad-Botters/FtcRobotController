package org.firstinspires.ftc.teamcode.teamcode;

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
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.Arms;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.Intake;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.teamcode.paths.CalebBluePaths;
import org.firstinspires.ftc.teamcode.teamcode.paths.IPaths;

@Autonomous(name = "Caleb Blue", group = "BIO BUZZ")
public class Auto_Starter extends LinearOpMode {
    Intake myIntake;
    Arms myArms;
    Shooter myShooter;

    private Follower follower;
    private IPaths paths;

    public Auto_Starter() {
        myIntake = new Intake();
        myArms = new Arms();
        myShooter = new Shooter();
        paths = new CalebBluePaths();
    }

    // Autonomous routine
    public Command autoRoutine() {
        return sequential(
                follow(follower, paths.goToFirstShot()),
                instant(() -> {
                    myShooter.setPollenShooterPower(0.75);
                    myShooter.setNectarShooterPower(0.75);
                }),
                waitMs(1500),
                instant(() -> myShooter.flipperPosition(1)),
                waitMs(2500),
                instant(() -> {
                    myShooter.turnOffShooter();
                    myShooter.flipperPosition(0);
                }),
                follow(follower, paths.approachPollen()),
//                instant(() -> {
//                    myIntake.turnOn();
//                }),
                follow(
                  follower,
                  paths.gatherPollen()
                       .with(Constants.foresightConfig.maxPathSpeed.at(0.3))
                ),
//                instant(() -> {
//                    myIntake.turnOff();
//                }),
                follow(follower, paths.goPark())
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
        follower.setPose(paths.getStartingPose());
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
}
