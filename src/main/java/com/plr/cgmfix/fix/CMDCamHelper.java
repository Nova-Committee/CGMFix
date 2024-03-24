package com.plr.cgmfix.fix;

import team.creative.cmdcam.client.CamEventHandlerClient;

public class CMDCamHelper {
    public static boolean isRollModified() {
        return CamEventHandlerClient.roll != 0;
    }
}
