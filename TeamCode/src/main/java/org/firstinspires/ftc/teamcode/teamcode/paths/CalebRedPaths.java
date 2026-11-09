package org.firstinspires.ftc.teamcode.teamcode.paths;

import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class CalebRedPaths implements IPaths {

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(7.8651, 58.5897, 0);
    private final Pose toFirstShot = poseFactory.of(59.4592, 23.4637, 90);
    private final Pose toFirstShotControl1 = poseFactory.of(27.5075, 58.6433, 0);
    private final Pose toFirstShotControl2 = poseFactory.of(42.6447, 31.6452, 0);
    private final Pose approachPollenLaneStart = poseFactory.of(59.4592, 23.4637, 90);
    private final Pose approachPollenLane = poseFactory.of(28.5462, 8.4969, 180);
    private final Pose pickUpBalls = poseFactory.of(8.6178, 8.6188, 180);
    private final Pose goParkStart = poseFactory.of(8.6178, 8.6188, 180);
    private final Pose goPark = poseFactory.of(10.5343, 94.7731, 0);
    private final Pose goParkControl1 = poseFactory.of(36.5653, 65.9311, 0);

    public Pose getStartingPose() {
        return start;
    }

    @Override
    public Path goToFirstShot() {
        return Paths.curve(start, toFirstShotControl1, toFirstShotControl2, toFirstShot).linear(start, toFirstShot);
    }

    @Override
    public Path approachPollen() {
        return Paths.line(approachPollenLaneStart, approachPollenLane).linear(approachPollenLaneStart, approachPollenLane);
    }

    @Override
    public Path gatherPollen() {
        return Paths.line(approachPollenLane, pickUpBalls).constant(pickUpBalls);
    }

    public Path goPark() {
        return Paths.curve(goParkStart, goParkControl1, goPark).linear(goParkStart, goPark);
    }
}