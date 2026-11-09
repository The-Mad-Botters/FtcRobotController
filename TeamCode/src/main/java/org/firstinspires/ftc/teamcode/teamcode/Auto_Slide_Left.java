package org.firstinspires.ftc.teamcode.teamcode;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.Arms;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.Intake;
import org.firstinspires.ftc.teamcode.teamcode.mechanism.Shooter;
import org.firstinspires.ftc.teamcode.teamcode.paths.IPaths;
import org.firstinspires.ftc.teamcode.teamcode.paths.PathSlideLeftFromSide;
import org.firstinspires.ftc.teamcode.teamcode.paths.ShortShootFirst;

@Autonomous(name = "Slide Left From Side", group = "BIO BUZZ")
public class Auto_Slide_Left extends LinearOpMode {
    Intake myIntake;
    Shooter myShooter;

    private Follower follower;
    private IPaths paths;

    public Auto_Slide_Left() {
        myIntake = new Intake();
        myShooter = new Shooter();
        paths = new PathSlideLeftFromSide();
    }

    // Autonomous routine
    public Command autoRoutine() {
        return sequential(
                follow(follower, paths.goPark())
        );
    }

    @Override
    public void runOpMode() {

        // we need to initialize our mechanisms
        myIntake.init(hardwareMap);
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
