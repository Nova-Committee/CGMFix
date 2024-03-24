package com.plr.cgmfix.mixin.common;

import com.mrcrayfish.guns.Config;
import com.mrcrayfish.guns.entity.MissileEntity;
import com.mrcrayfish.guns.entity.ProjectileEntity;
import com.plr.cgmfix.api.IEnableBlockRemoval;
import com.plr.cgmfix.util.ProjectileUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MissileEntity.class, remap = false)
public abstract class MixinMissileEntity extends ProjectileEntity {

    public MixinMissileEntity(EntityType<? extends Entity> entityType, Level worldIn) {
        super(entityType, worldIn);
    }

    @Inject(method = "onHitEntity", at = @At("HEAD"), cancellable = true)
    private void inject$onHitEntity(Entity entity, Vec3 hitVec, Vec3 startVec, Vec3 endVec, boolean headshot, CallbackInfo ci) {
        ci.cancel();
        ProjectileUtils.createExplosion(this, Config.COMMON.missiles.explosionRadius.get().floatValue(), cgmfix$breakTerrain());
    }

    @Inject(method = "onHitBlock", at = @At("HEAD"), cancellable = true)
    private void inject$onHitBlock(BlockState state, BlockPos pos, Direction face, double x, double y, double z, CallbackInfo ci) {
        ci.cancel();
        ProjectileUtils.createExplosion(this, Config.COMMON.missiles.explosionRadius.get().floatValue(), cgmfix$breakTerrain());
    }

    @Inject(method = "onExpired", at = @At("HEAD"), cancellable = true)
    private void inject$onExpired(CallbackInfo ci) {
        ci.cancel();
        ProjectileUtils.createExplosion(this, Config.COMMON.missiles.explosionRadius.get().floatValue(), cgmfix$breakTerrain());
    }

    @Unique
    private static boolean cgmfix$breakTerrain() {
        return ((IEnableBlockRemoval) Config.COMMON.missiles).cgmfix$enableBlockRemoval();
    }
}
