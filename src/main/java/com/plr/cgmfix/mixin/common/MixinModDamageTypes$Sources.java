package com.plr.cgmfix.mixin.common;

import com.mrcrayfish.guns.init.ModDamageTypes;
import com.plr.cgmfix.fix.BulletDamageSource;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ModDamageTypes.Sources.class)
public abstract class MixinModDamageTypes$Sources {
    @Shadow(remap = false)
    private static Holder.Reference<DamageType> getHolder(RegistryAccess access, ResourceKey<DamageType> damageTypeKey) {
        throw new RuntimeException();
    }

    @Inject(method = "source", at = @At("HEAD"), cancellable = true, remap = false)
    private static void inject$source(RegistryAccess access, ResourceKey<DamageType> damageTypeKey, Entity directEntity, Entity causingEntity, CallbackInfoReturnable<DamageSource> cir) {
        cir.setReturnValue(new BulletDamageSource(getHolder(access, damageTypeKey), directEntity, causingEntity));
    }


}
