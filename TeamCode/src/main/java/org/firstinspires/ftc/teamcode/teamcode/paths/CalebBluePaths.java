package org.firstinspires.ftc.teamcode.teamcode.paths;

import static com.pedropathing.api.Paths.*;
import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class CalebBluePaths implements IPaths {

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(133.4583, 82.7465, 180);
    private final Pose goToFirstShot = poseFactory.of(83.6079, 116.3943, 270);
    private final Pose goToFirstShotControl1 = poseFactory.of(117.5035, 78.0053, 0);
    private final Pose goToFirstShotControl2 = poseFactory.of(99.3585, 114.3386, 0);
    private final Pose approachPollenStart = poseFactory.of(83.6079, 116.3943, 270);
    private final Pose approachPollen = poseFactory.of(107.6748, 133.2781, 0);
    private final Pose gatherPollen = poseFactory.of(131.2601, 133.1975, 0);
    private final Pose goParkStart = poseFactory.of(131.2601, 133.1975, 0);
    private final Pose goPark = poseFactory.of(132.3612, 47.6017, 180);
    private final Pose goParkControl1 = poseFactory.of(113.3681, 97.9854, 0);

    public Pose getStartingPose() {
        return start;
    }

    public Path goToFirstShot() {
        return Paths.curve(start, goToFirstShotControl1, goToFirstShotControl2, goToFirstShot).linear(start, goToFirstShot);
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
