package org.firstinspires.ftc.teamcode.teamcode.paths;

import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public interface IPaths {
    public Pose getStartingPose();
    public Path goToFirstShot();
    public Path approachPollen();
    public Path gatherPollen();
    public Path goPark();
}
