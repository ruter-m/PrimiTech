package net.ruterm.primitech.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties RYE_BREAD = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.7F)
            .build();

    public static final FoodProperties KVASS = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.5F)
            .effect(new MobEffectInstance(MobEffects.JUMP, 1200, 0),0.5F)
            .build();
}
