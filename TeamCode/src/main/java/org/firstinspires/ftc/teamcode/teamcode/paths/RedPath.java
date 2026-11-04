package org.firstinspires.ftc.teamcode.teamcode.paths;

import static com.pedropathing.api.Paths.*;
import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class RedPath {

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(59.5173, 7.0758, 90);
    private final Pose point1 = poseFactory.of(58.736, 42.6726, 180);
    private final Pose point2Start = poseFactory.of(58.736, 42.6726, 0);
    private final Pose point2 = poseFactory.of(102, 64, 270);
    private final Pose point3 = poseFactory.of(45, 104, -35.0594);
    private final Pose point4 = poseFactory.of(92, 46, 129.0194);
    private final Pose point5 = poseFactory.of(9.2985, 105.0392, -35.5224);

    public Path path1() {
        return Paths.line(start, point1).linear(start, point1);
    }

    public Path path2() {
        return Paths.line(point2Start, point2).linear(point2Start, point2);
    }

    public Path path3() {
        return Paths.line(point2, point3).reverseTangent();
    }

    public Path path4() {
        return Paths.line(point3, point4).reverseTangent();
    }

    public Path path5() {
        return Paths.line(point4, point5).reverseTangent();
    }
}
