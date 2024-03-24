package com.plr.cgmfix.mixin.common;

import com.mrcrayfish.guns.Config;
import net.minecraftforge.common.ForgeConfigSpec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = Config.Griefing.class, remap = false)
public class MixinConfig$Griefing {
    @Redirect(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;comment(Ljava/lang/String;)Lnet/minecraftforge/common/ForgeConfigSpec$Builder;"
            )
    )
    private ForgeConfigSpec.Builder redirect$init(ForgeConfigSpec.Builder instance, String comment) {
        if (!comment.equals("If enabled, allows block removal on explosions")) return instance.comment(comment);
        return instance.comment("[INVALID, DO NOT USE]");
    }
}
