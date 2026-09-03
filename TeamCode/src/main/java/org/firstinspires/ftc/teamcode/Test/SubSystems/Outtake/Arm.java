package org.firstinspires.ftc.teamcode.Test.SubSystems.Outtake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import dev.frozenmilk.dairy.cachinghardware.CachingServo;

public class Arm {
    private CachingServo rotateAxis;
    private CachingServo rotateArmLeft,rotateArmRight;
    private static double
        rotateToTransfer,rotateToBasket,rotateToNormal,rotateToHang;
    private static double
        armToTransfer,armToNormal,armToBasket,armToHang;
    public outtakeArmState OuttakeArmState;
    public outtakeAxisState OuttakeAxisState;
    public enum outtakeArmState {
        transfer,normal,basket,hang
    }
    public enum outtakeAxisState {
        transfer,normal,basket,hang
    }

    public void rotateArm(double pos) {
        rotateArmLeft.setPosition(pos);
        rotateArmRight.setPosition(pos);
    }

    public void updateOuttakeArm() {
        switch (OuttakeAxisState) {
            case normal:
                rotateAxis.setPosition(rotateToNormal);
                break;
            case transfer:
                rotateAxis.setPosition(rotateToTransfer);
                break;
            case basket:
                rotateAxis.setPosition(rotateToBasket);
                break;
            case hang:
                rotateAxis.setPosition(rotateToHang);
                break;
        }
        switch (OuttakeArmState) {
            case normal:
                rotateArm(armToNormal);
                break;
            case transfer:
                rotateArm(armToTransfer);
                break;
            case basket:
                rotateArm(armToBasket);
                break;
            case hang:
                rotateArm(armToHang);
                break;
        }
    }
    public Arm(HardwareMap hardwaremap) {
        rotateAxis = new CachingServo(hardwaremap.get(Servo.class,""));
        rotateArmLeft = new CachingServo(hardwaremap.get(Servo.class,""));
        rotateArmRight = new CachingServo(hardwaremap.get(Servo.class,""));
        OuttakeAxisState = outtakeAxisState.normal;
        OuttakeArmState = outtakeArmState.normal;
    }
}
