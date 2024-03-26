package com.plr.cgmfix.mixin.common;

import com.mrcrayfish.guns.entity.ProjectileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ProjectileEntity.class, remap = false)
public abstract class MixinProjectileEntity extends Entity {
    @Shadow
    protected abstract void onHitBlock(BlockState state, BlockPos pos, Direction face, double x, double y, double z);

    @Shadow
    protected abstract void onHitEntity(Entity entity, Vec3 hitVec, Vec3 startVec, Vec3 endVec, boolean headshot);

    public MixinProjectileEntity(EntityType<?> t, Level l) {
        super(t, l);
    }

    @Redirect(
            method = "onHit",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mrcrayfish/guns/entity/ProjectileEntity;onHitBlock(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;DDD)V"
            ),
            require = 0
    )
    private void redirect$onHit$onHitBlock(ProjectileEntity instance, BlockState state, BlockPos pos, Direction face, double x, double y, double z) {
        this.onHitBlock(state, pos, face, x, y, z);
        level.gameEvent(GameEvent.PROJECTILE_LAND, pos);
    }

    @Redirect(
            method = "onHit",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mrcrayfish/guns/entity/ProjectileEntity;onHitEntity(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;Z)V"
            ),
            require = 0
    )
    private void redirect$onHit$onHitBlock(ProjectileEntity instance, Entity entity, Vec3 hitVec, Vec3 startVec, Vec3 endVec, boolean headshot) {
        this.onHitEntity(entity, hitVec, startVec, endVec, headshot);
        level.gameEvent(GameEvent.PROJECTILE_LAND, pos);
    }
}
