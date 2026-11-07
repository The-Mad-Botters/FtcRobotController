package org.firstinspires.ftc.teamcode.teamcode.paths;

import static com.pedropathing.api.Paths.*;
import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class ShortShootFirst implements IPaths {


    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(58.8254, 136.2996, 270);
    private final Pose goToFirstShot = poseFactory.of(57.6573, 123.6771, 270);
    private final Pose goParkStart = poseFactory.of(57.6573, 123.6771, 270);
    private final Pose goPark = poseFactory.of(11.5032, 118.391, 0);

    public Pose getStartingPose() {return start;}

    public Path goToFirstShot() {
        return Paths.line(start, goToFirstShot).linear(start, goToFirstShot);
    }

    public Path goPark() {
        return Paths.line(goParkStart, goPark).linear(goParkStart, goPark);
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
