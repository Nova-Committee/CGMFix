package com.plr.cgmfix.mixin.common;

import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings("all")
@Mixin(value = LivingEntity.class, priority = 1500)
public abstract class MixinLivingEntity {
    @TargetHandler(
            mixin = "com.mrcrayfish.guns.mixin.common.LivingEntityMixin",
            name = "modifyApplyKnockbackArgs"
    )
    @Redirect(
            method = "@MixinSquared:Handler",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/damagesource/DamageSource;getEntity()Lnet/minecraft/world/entity/Entity;"
            ),
            require = 0
    )
    private Entity redirect$hurt(DamageSource src) {
        return src.getDirectEntity();
    }
}
