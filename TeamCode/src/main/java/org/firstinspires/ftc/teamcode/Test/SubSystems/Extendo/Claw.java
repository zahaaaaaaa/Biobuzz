package org.firstinspires.ftc.teamcode.Test.SubSystems.Extendo;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Test.Utils.Globals;

import dev.frozenmilk.dairy.cachinghardware.CachingServo;

public class Claw {
    private CachingServo servoClaw;
    private static double clawClosed, clawOppened;
    public extendoClawState ExtendoClawState;
    public enum extendoClawState {
        closed,
        open
    }

    public void updateExtendoClaw() {

        if (Globals.forceClawClose && !Globals.isInTransferWindow) {
            servoClaw.setPosition(clawClosed);
            return;
        }

        if (Globals.forceClawOpen && !Globals.isInTransferWindow) {
            servoClaw.setPosition(clawOppened);
            return;
        }

        switch (ExtendoClawState) {
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
        ExtendoClawState = extendoClawState.closed;
    }
}
