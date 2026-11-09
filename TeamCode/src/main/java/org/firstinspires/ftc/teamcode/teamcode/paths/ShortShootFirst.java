package org.firstinspires.ftc.teamcode.teamcode.paths;

import static com.pedropathing.api.Paths.*;
import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class ShortShootFirst implements IPaths {

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(58.7698, 132.6659, 270);
    private final Pose goToFirstShot = poseFactory.of(57.0716, 123.0157, 270);
    private final Pose goParkStart = poseFactory.of(57.0716, 123.0157, 270);
    private final Pose goPark = poseFactory.of(10.1072, 119.1465, 0);
    private final Pose goParkControl1 = poseFactory.of(43.5805, 102.7275, 0);

    public Path goToFirstShot() {
        return Paths.line(start, goToFirstShot).linear(start, goToFirstShot);
    }

    public Path goPark() {
        return Paths.curve(goParkStart, goParkControl1, goPark).linear(goParkStart, goPark);
    }

    public Pose getStartingPose() {return start;}

    @Override
    public Path approachPollen() {
        return null;
    }

    @Override
    public Path gatherPollen() {
        return null;
    }
}
