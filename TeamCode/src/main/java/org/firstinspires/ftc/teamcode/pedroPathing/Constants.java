package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.control.PredictiveBrakingCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
//    public static FollowerConstants followerConstants = new FollowerConstants()
//            .forwardZeroPowerAcceleration(-31.2523)
//            .lateralZeroPowerAcceleration(-67.3555)
//            .translationalPIDFCoefficients(new PIDFCoefficients(0.12, 0.0, 0.025, 0.02))
//            .headingPIDFCoefficients(new PIDFCoefficients(1, 0.0, 0.055, 0.025))
//            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.01, 0.0, 0.0042, 0.6, 0.03))
//            .centripetalScaling(0.0005)
//
    public static FollowerConstants followerConstants = new FollowerConstants()
            .predictiveBrakingCoefficients(new PredictiveBrakingCoefficients(0.1, 0.14374, 0.0013325)) // (kP, kLinear, kQuadratic)
            .forwardZeroPowerAcceleration(-31.2523)
            .lateralZeroPowerAcceleration(-67.3555)
            .headingPIDFCoefficients(new PIDFCoefficients(1, 0.0, 0.115, 0.02))
            .centripetalScaling(0)
            .mass(13);

    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .rightFrontMotorName("rightFront")
            .rightRearMotorName("rightRear")
            .leftRearMotorName("leftRear")
            .leftFrontMotorName("leftFront")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .useBrakeModeInTeleOp(true)
            .yVelocity(65.9080)
            .xVelocity(82.7344);

    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(-3.989)
            .strafePodX(4.257)
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("pinpoint")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD);

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 50, 0.5, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .pinpointLocalizer(localizerConstants)
                .mecanumDrivetrain(driveConstants)
                .build();
    }


}