package org.firstinspires.ftc.teamcode.Test.SubSystems;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Test.SubSystems.Outtake.Claw;
import org.firstinspires.ftc.teamcode.Test.SubSystems.Outtake.Arm;
import org.firstinspires.ftc.teamcode.Test.SubSystems.Outtake.Sliders;

public class outtakeMain {
    Arm arm;
    Claw claw;
    Sliders slider;
    Sensors sensors;
    public outtakeState OutakeState;
    public enum outtakeState {
        normal,basket,transfer,hang
    }

    public void updateOuttake() {
        arm.updateOuttakeArm();
        claw.updateOuttakeClaw();
        slider.updateOuttakeSliders();
    }

    public void update() {
        switch (OutakeState) {
            case normal:

                slider.OuttakeSlidersState = Sliders.outtakeSlidersState.normal;
                claw.OuttakeClawState = Claw.outtakeClawState.closed;
                arm.OuttakeArmState = Arm.outtakeArmState.normal;
                arm.OuttakeAxisState = Arm.outtakeAxisState.normal;

                break;
            case basket:

                slider.OuttakeSlidersState = Sliders.outtakeSlidersState.basket;
                claw.OuttakeClawState = Claw.outtakeClawState.closed;
                arm.OuttakeArmState = Arm.outtakeArmState.basket;
                arm.OuttakeAxisState = Arm.outtakeAxisState.basket;

                break;
            case transfer:

                slider.OuttakeSlidersState = Sliders.outtakeSlidersState.transfer;
                arm.OuttakeArmState = Arm.outtakeArmState.transfer;
                arm.OuttakeAxisState = Arm.outtakeAxisState.transfer;

                if (sensors.isInTransferWindow())
                    claw.OuttakeClawState = Claw.outtakeClawState.open;
                else
                    claw.OuttakeClawState = Claw.outtakeClawState.closed;

                break;
            case hang:

                slider.OuttakeSlidersState = Sliders.outtakeSlidersState.hang;
                claw.OuttakeClawState = Claw.outtakeClawState.closed;
                arm.OuttakeArmState = Arm.outtakeArmState.hang;
                arm.OuttakeAxisState = Arm.outtakeAxisState.hang;

                break;
        }
        updateOuttake();
    }

    public outtakeMain(HardwareMap hardwaremap) {
        arm = new Arm(hardwaremap);
        claw = new Claw(hardwaremap);
        slider = new Sliders(hardwaremap);
        sensors = new Sensors(hardwaremap);
    }
}
