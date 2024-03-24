package com.plr.cgmfix.mixin.common;

import com.mrcrayfish.guns.Config;
import com.mrcrayfish.guns.entity.ThrowableGrenadeEntity;
import com.mrcrayfish.guns.entity.ThrowableItemEntity;
import com.plr.cgmfix.api.IEnableBlockRemoval;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.plr.cgmfix.util.ProjectileUtils.createExplosion;

@Mixin(value = ThrowableGrenadeEntity.class, remap = false)
public abstract class MixinThrowableGrenadeEntity extends ThrowableItemEntity {
    public MixinThrowableGrenadeEntity(EntityType<? extends ThrowableItemEntity> entityType, Level worldIn) {
        super(entityType, worldIn);
    }

    @Inject(method = "onDeath", at = @At("HEAD"), cancellable = true)
    private void inject$onDeath(CallbackInfo ci) {
        ci.cancel();
        createExplosion(this, Config.COMMON.grenades.explosionRadius.get().floatValue(), ((IEnableBlockRemoval) Config.COMMON.grenades).cgmfix$enableBlockRemoval());
    }
}
