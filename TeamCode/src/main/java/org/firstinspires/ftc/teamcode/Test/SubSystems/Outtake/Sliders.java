package org.firstinspires.ftc.teamcode.Test.SubSystems.Outtake;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Test.Utils.Globals;

import dev.frozenmilk.dairy.cachinghardware.CachingDcMotorEx;

public class Sliders {
    private CachingDcMotorEx sliderleft,sliderright;
    private static double
        normalMotorPos,transferMotorPos,hangMotorPos,basketMotorPos;
    private static double currentPos,targetPos;
    private static double sign = 1;
    private static double kP,kS,kV;
    public outtakeSlidersState OuttakeSlidersState;
    public enum outtakeSlidersState {
        normal,transfer,hang,basket,init
    }
    private void calculatePid(double targetPos) {
        currentPos = sliderright.getCurrentPosition(); double error,power;

        error = targetPos - currentPos; Globals.sliderErrorOuttake = error;
        sign = (error < 0) ? -1 : 1;

        power = ((kP * abs(error)) + (kV * targetPos) + kS) * sign;

        sliderleft.setPower(power);
        sliderright.setPower(power);
    }
    public void updateOuttakeSliders() {
        switch (OuttakeSlidersState){
            case normal:
                targetPos = normalMotorPos;
                break;
            case transfer:
                targetPos = transferMotorPos;
                break;
            case hang:
                targetPos = hangMotorPos;
                break;
            case basket:
                targetPos = basketMotorPos;
                break;
            case init:
                return;
        }
        calculatePid(targetPos);
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

        OuttakeSlidersState = outtakeSlidersState.init;
    }
}
