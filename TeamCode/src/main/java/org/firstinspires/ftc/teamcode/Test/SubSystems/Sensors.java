package org.firstinspires.ftc.teamcode.Test.SubSystems;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Test.Utils.Globals;

public class Sensors {
    AnalogInput clawExtendo,clawOuttake;

    public boolean isInTransferWindow() {
        Globals.isInTransferWindow = ((clawExtendo.getVoltage() > 1.0) && (clawOuttake.getVoltage() > 0.5));
        return Globals.isInTransferWindow;
    }

    public Sensors(HardwareMap hardwaremap) {
        clawExtendo = hardwaremap.get(AnalogInput.class, "clawExtendo");
        clawOuttake = hardwaremap.get(AnalogInput.class, "clawOuttake");
    }
}
