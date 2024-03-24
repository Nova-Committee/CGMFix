package com.plr.cgmfix.mixin.common;

import com.mrcrayfish.guns.common.network.ServerPlayHandler;
import com.mrcrayfish.guns.network.message.C2SMessageShoot;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ServerPlayHandler.class, remap = false)
public abstract class MixinServerPlayHandler {
    @Inject(
            method = "handleShoot",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/eventbus/api/IEventBus;post(Lnet/minecraftforge/eventbus/api/Event;)Z",
                    ordinal = 1,
                    remap = false
            ),
            require = 0
    )
    private static void inject$handleShoot(C2SMessageShoot message, ServerPlayer player, CallbackInfo ci) {
        player.level.gameEvent(GameEvent.PROJECTILE_SHOOT, player.position(), GameEvent.Context.of(player));
    }
}
