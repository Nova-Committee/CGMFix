package com.plr.cgmfix.fix;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.concurrent.ThreadLocalRandom;


public class BulletDamageSource extends DamageSource {
    private static final String[] msgSuffix = {
            "cgm.bullet.killed",
            "cgm.bullet.eliminated",
            "cgm.bullet.executed",
            "cgm.bullet.annihilated",
            "cgm.bullet.decimated"
    };

    public BulletDamageSource(Holder<DamageType> pType, Entity pDirectEntity, Entity pCausingEntity) {
        super(pType, pDirectEntity, pCausingEntity);
    }

    @Override
    public Component getLocalizedDeathMessage(LivingEntity pLivingEntity) {
        final String s = "death.attack." + this.getMsgId();
        if (this.getEntity() == null && this.getDirectEntity() == null) {
            LivingEntity living = pLivingEntity.getKillCredit();
            return living != null ? Component.translatable(s + ".player", pLivingEntity.getDisplayName(), living.getDisplayName()) : Component.translatable(s, pLivingEntity.getDisplayName());
        } else {
            final Component component = this.getEntity() == null ? this.getDirectEntity().getDisplayName() : this.getEntity().getDisplayName();
            final ItemStack stack = this.getEntity() instanceof LivingEntity livingentity ? livingentity.getMainHandItem() : ItemStack.EMPTY;
            return !stack.isEmpty() && stack.hasCustomHoverName() ?
                    Component.translatable(
                            s + ".item",
                            pLivingEntity.getDisplayName(),
                            component,
                            stack.getDisplayName()
                    ) : Component.translatable(s, pLivingEntity.getDisplayName(), component);
        }
    }

    @Override
    public String getMsgId() {
        return msgSuffix[ThreadLocalRandom.current().nextInt(5)];
    }
}
