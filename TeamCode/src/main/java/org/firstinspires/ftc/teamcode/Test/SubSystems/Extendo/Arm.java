package org.firstinspires.ftc.teamcode.Test.SubSystems.Extendo;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import dev.frozenmilk.dairy.cachinghardware.CachingServo;

public class Arm {
    CachingServo rotateHoleArmLeft,rotateHoleArmRight;
    CachingServo rotateClawLeft,rotateClawRight;
    private static double
        intakeArmPos,transferArmPos,normalArmPos;
    private static double
        intakeClawPos,transferClawPos,normalClawPos;
    public extendnoArmState ExtendnoArmState;
    public extendoClawRotate ExtendoClawRotate;
    public enum extendnoArmState {
        transfer,intake,normal
    }
    public enum extendoClawRotate {
        transfer,intake,normal
    }
    public void rotateArm(double pos) {
        rotateHoleArmLeft.setPosition(pos);
        rotateHoleArmRight.setPosition(pos);
    }
    public void rotateClaw(double pos) {
        rotateClawLeft.setPosition(pos);
        rotateClawRight.setPosition(pos);
    }

    public void updateArm() {
        switch (ExtendoClawRotate) {
            case normal:
                rotateClaw(normalClawPos);
                break;
            case transfer:
                rotateClaw(transferClawPos);
                break;
            case intake:
                rotateClaw(intakeClawPos);
                break;
        }
        switch (ExtendnoArmState) {
            case normal:
                rotateArm(normalArmPos);
                break;
            case transfer:
                rotateArm(transferArmPos);
                break;
            case intake:
                rotateArm(intakeArmPos);
                break;
        }
    }

    public Arm(HardwareMap hardwaremap) {
        rotateHoleArmLeft = new CachingServo(hardwaremap.get(Servo.class, ""));
        rotateHoleArmRight = new CachingServo(hardwaremap.get(Servo.class, ""));
        rotateClawLeft = new CachingServo(hardwaremap.get(Servo.class, ""));
        rotateClawRight = new CachingServo(hardwaremap.get(Servo.class, ""));
        ExtendnoArmState = extendnoArmState.normal;
        ExtendoClawRotate = extendoClawRotate.normal;
    }
}
