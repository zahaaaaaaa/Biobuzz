package org.firstinspires.ftc.teamcode.Test.SubSystems.Extendo;

import static java.lang.Math.abs;

import androidx.lifecycle.GenericLifecycleObserver;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Test.Utils.Globals;

import dev.frozenmilk.dairy.cachinghardware.CachingDcMotorEx;

public class Sliders {
    private CachingDcMotorEx sliderleft,sliderright;
    private static double
        extendedMotorPos,normalMotorPos,transferMotorPos,hangMotorPos;
    private static double currentPos,targetPos;
    private static double sign = 1;
    private static double kP,kS,kV;
    public extendoSlidersState ExtendoSlidersState;
    public enum extendoSlidersState {
        extended,normal,transfer,hang,init
    }
    private void calculatePid(double targetPos) {
        currentPos = sliderright.getCurrentPosition(); double error,power;

        error = targetPos - currentPos; Globals.sliderErrorExtendo = error;
        sign = (error < 0) ? -1 : 1;

        power = ((kP * abs(error)) + (kV * targetPos) + kS) * sign;

        sliderleft.setPower(power);
        sliderright.setPower(power);
    }
    public void updateExtendoSliders() {
        switch (ExtendoSlidersState){
            
            case normal:
                targetPos = normalMotorPos;
                break;
            case extended:
                targetPos = extendedMotorPos;
                break;
            case transfer:
                targetPos = transferMotorPos;
                break;
            case hang:
                targetPos = hangMotorPos;
                break;
            case init:
                return;
        }
        calculatePid(targetPos);
    }

    public void setExtendoSlidersState(extendoSlidersState state) {
        ExtendoSlidersState = state;
    }

    public Sliders(HardwareMap hardwaremap) {
        sliderleft = new CachingDcMotorEx(hardwaremap.get(DcMotorEx.class, ""));
        sliderright = new CachingDcMotorEx(hardwaremap.get(DcMotorEx.class, ""));

        sliderleft.setZeroPowerBehavior(CachingDcMotorEx.ZeroPowerBehavior.BRAKE);
        sliderright.setZeroPowerBehavior(CachingDcMotorEx.ZeroPowerBehavior.BRAKE);

        sliderleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sliderright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        sliderleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        sliderright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        ExtendoSlidersState = extendoSlidersState.init;
    }
}
