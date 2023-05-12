package com.nhoryzon.mc.adventurerspaths.mixin;

import com.nhoryzon.mc.adventurerspaths.AdventurersPathsMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class DummyLivingEntityOnUseItemFinishMixin {

    @Inject(method = "finishUsing", at = @At("HEAD"))
    private void canHaveStatusEffect(World world, LivingEntity entity, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack itemUsing = entity.getActiveItem();

        if (AdventurersPathsMod.CONFIG.isDummyBoolSetting() && itemUsing.isFood()) {
            entity.addStatusEffect(
                    new StatusEffectInstance(StatusEffects.DARKNESS, AdventurersPathsMod.CONFIG.getDummyIntegerSetting(), 1));
        }
    }

}