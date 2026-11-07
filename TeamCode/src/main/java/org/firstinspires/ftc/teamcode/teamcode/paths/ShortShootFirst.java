package org.firstinspires.ftc.teamcode.teamcode.paths;

import static com.pedropathing.api.Paths.*;
import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class ShortShootFirst implements IPaths {

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose calebByFlower = poseFactory.of(7.8651, 58.5897, 0);
    private final Pose toFirstShot = poseFactory.of(57.5116, 9.9695, 90);
    private final Pose toFirstShotControl1 = poseFactory.of(27.5075, 58.6433, 0);
    private final Pose toFirstShotControl2 = poseFactory.of(42.6447, 31.6452, 0);
    private final Pose goParkStart = poseFactory.of(57.5116, 9.9695, 180);
    private final Pose goPark = poseFactory.of(10.5343, 94.7731, 90);
    private final Pose goParkControl1 = poseFactory.of(23.105, 48.4896, 0);

    public Path goToFirstShot() {
        return Paths.curve(calebByFlower, toFirstShotControl1, toFirstShotControl2, toFirstShot).linear(calebByFlower, toFirstShot);
    }

    public Path goPark() {
        return Paths.curve(goParkStart, goParkControl1, goPark).linear(goParkStart, goPark);
    }

    @Override
    public Pose getStartingPose() {
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
