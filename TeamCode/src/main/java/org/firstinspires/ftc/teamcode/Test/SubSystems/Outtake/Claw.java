package org.firstinspires.ftc.teamcode.Test.SubSystems.Outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import dev.frozenmilk.dairy.cachinghardware.CachingServo;

public class Claw {
    private CachingServo servoClaw;
    private static double clawClosed, clawOppened;
    public outtakeClawState OuttakeClawState;
    public enum outtakeClawState {
        closed,
        open
    }

    public void updateOuttakeClaw() {
        switch (OuttakeClawState) {
            case open:
                servoClaw.setPosition(clawOppened);
                break;
            case closed:
                servoClaw.setPosition(clawClosed);
                break;
        }
    }

    public Claw(HardwareMap hardwaremap) {
        servoClaw = new CachingServo(hardwaremap.get(Servo.class, "claw"));
        servoClaw.setDirection(Servo.Direction.REVERSE);
        OuttakeClawState = outtakeClawState.closed;
    }
}
