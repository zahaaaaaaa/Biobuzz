package org.firstinspires.ftc.teamcode.Test.SubSystems;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Test.SubSystems.Extendo.Arm;
import org.firstinspires.ftc.teamcode.Test.SubSystems.Extendo.Claw;
import org.firstinspires.ftc.teamcode.Test.SubSystems.Extendo.Sliders;
import org.firstinspires.ftc.teamcode.Test.Utils.Globals;

public class extendoMain {
    Arm arm;
    Claw claw;
    Sliders slider;
    Sensors sensors;
    public extendoState ExtendoState;
    public enum extendoState {
        normal,extended,transfer
    }
    private void updateExtendo() {
        arm.updateArm();
        claw.updateExtendoClaw();
        slider.updateExtendoSliders();
    }

    public void update() {
        switch (ExtendoState) {
            case normal:
                slider.ExtendoSlidersState = Sliders.extendoSlidersState.normal;
                claw.ExtendoClawState = Claw.extendoClawState.closed;
                arm.ExtendnoArmState = Arm.extendnoArmState.normal;
                arm.ExtendoClawRotate = Arm.extendoClawRotate.normal;

                break;
            case extended:
                slider.ExtendoSlidersState = Sliders.extendoSlidersState.extended;

                if (abs(Globals.sliderErrorExtendo) < 200) {
                    arm.ExtendnoArmState = Arm.extendnoArmState.intake;
                    arm.ExtendoClawRotate = Arm.extendoClawRotate.intake;
                }
                else {
                    arm.ExtendnoArmState = Arm.extendnoArmState.normal;
                    arm.ExtendoClawRotate = Arm.extendoClawRotate.normal;
                }

                break;
            case transfer:
                slider.ExtendoSlidersState = Sliders.extendoSlidersState.transfer;
                arm.ExtendnoArmState = Arm.extendnoArmState.transfer;
                arm.ExtendoClawRotate = Arm.extendoClawRotate.transfer;

                if (sensors.isInTransferWindow()) claw.ExtendoClawState = Claw.extendoClawState.open;
                else claw.ExtendoClawState = Claw.extendoClawState.closed;

                break;
        }
        updateExtendo();
    }
    public extendoMain(HardwareMap hardwaremap) {
        arm = new Arm(hardwaremap);
        claw = new Claw(hardwaremap);
        slider = new Sliders(hardwaremap);
        sensors = new Sensors(hardwaremap);
    }
}
