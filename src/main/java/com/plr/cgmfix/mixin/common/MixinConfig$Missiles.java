package com.plr.cgmfix.mixin.common;

import com.mrcrayfish.guns.Config;
import com.plr.cgmfix.api.IEnableBlockRemoval;
import net.minecraftforge.common.ForgeConfigSpec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = Config.Missiles.class, remap = false)
public class MixinConfig$Missiles implements IEnableBlockRemoval {
    @Unique
    private ForgeConfigSpec.BooleanValue cgmfix$enableBlockRemoval;

    @Redirect(method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/common/ForgeConfigSpec$Builder;pop()Lnet/minecraftforge/common/ForgeConfigSpec$Builder;",
                    remap = false
            )
    )
    private ForgeConfigSpec.Builder inject$init(ForgeConfigSpec.Builder instance) {
        cgmfix$enableBlockRemoval = instance
                .comment("If enabled, allows block removal on explosions")
                .define("enableBlockRemoval", false);
        return instance.pop();
    }

    @Override
    public boolean cgmfix$enableBlockRemoval() {
        return cgmfix$enableBlockRemoval.get();
    }
}