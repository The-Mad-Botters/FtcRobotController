package org.firstinspires.ftc.teamcode.teamcode.paths;

import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class PathSlideLeftFromSide implements IPaths {

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(7.8651, 58.5897, 0);
    private final Pose goPark = poseFactory.of(10.5056, 94.3733, 0);

    public Path goPark() {
        return Paths.line(start, goPark).linear(start, goPark);
    }
    @Override
    public Pose getStartingPose() {
        return start;
    }

    @Override
    public Path goToFirstShot() {
        return null;
    }

    @Override
    public Path approachPollen() {
        return null;
    }

    @Override
    public Path gatherPollen() {
        return null;
    }

}
