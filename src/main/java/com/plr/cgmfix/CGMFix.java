package com.plr.cgmfix;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod(CGMFix.MODID)
public class CGMFix {
    public static final String MODID = "cgmfix";
    public static boolean cmdCamLoaded = false;

    public CGMFix() {
        cmdCamLoaded = ModList.get().isLoaded("cmdcam");
    }
}
