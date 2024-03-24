package com.plr.cgmfix.mixin.client;

import com.mrcrayfish.guns.client.handler.GunRenderingHandler;
import com.plr.cgmfix.CGMFix;
import com.plr.cgmfix.fix.CMDCamHelper;
import net.minecraftforge.client.event.ViewportEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GunRenderingHandler.class)
public class MixinGunRenderingHandler {
    @Inject(method = "onCameraSetup", at = @At("HEAD"), cancellable = true, remap = false)
    private void inject$onCameraSetup(ViewportEvent.ComputeCameraAngles event, CallbackInfo ci) {
        if (CGMFix.cmdCamLoaded && CMDCamHelper.isRollModified()) ci.cancel();
    }
}
