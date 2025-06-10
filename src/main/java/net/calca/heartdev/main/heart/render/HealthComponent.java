package net.calca.heartdev.main.heart.render;

import net.calca.heartdev.main.heart.types.CustomContainerType;
import net.calca.heartdev.main.heart.types.CustomHeartType;
import net.minecraft.resources.ResourceLocation;

public class HealthComponent {
    public static void setContainerType(CustomContainerType customContainerType){
        HealthBarVariables.CONTAINER = customContainerType;
    }
    public static void setHeartType(CustomHeartType customHeartType){
        HealthBarVariables.HEARTS = customHeartType;
    }
    public static void setPoisonedType(CustomHeartType customHeartType){
        HealthBarVariables.POISONED_HEARTS = customHeartType;
    }
    public static void setWitheredType(CustomHeartType customHeartType){
        HealthBarVariables.WITHERED_HEARTS = customHeartType;
    }
    public static void setFrozenType(CustomHeartType customHeartType){
        HealthBarVariables.FROZEN_HEARTS = customHeartType;
    }
    public static void setAbsorbingType(CustomHeartType customHeartType){
        HealthBarVariables.ABSORBING_HEARTS = customHeartType;
    }

    public static void setRegenAnimationSpeed(int speed){
        HealthBarVariables.regenAnimationSpeed = speed;
    }
    public static void setRegenAnimationCooldown(int cooldown){
        HealthBarVariables.regenAnimationCooldown = cooldown;
    }
    public static void setRegenAnimationOffSetY(int offSetY){
        HealthBarVariables.regenAnimationYoffSet = offSetY;
    }

    public static void overrideContainer(ResourceLocation container){
        HealthBarVariables.container = container;
    }
    public static void overrideContainerBlinkingDamage(ResourceLocation containerBlinking){
        HealthBarVariables.container_blinking_damage = containerBlinking;
    }
    public static void overrideContainerBlinkingHealing(ResourceLocation containerBlinking){
        HealthBarVariables.container_blinking_healing = containerBlinking;
    }
    public static void overrideContainerHardcore(ResourceLocation containerHardcore){
        HealthBarVariables.hardcore_container = containerHardcore;
    }
    public static void overrideContainerHardcoreBlinkingDamage(ResourceLocation containerHardcoreBlinking){
        HealthBarVariables.hardcore_container_blinking_damage = containerHardcoreBlinking;
    }
    public static void overrideContainerHardcoreBlinkingHealing(ResourceLocation containerHardcoreBlinking){
        HealthBarVariables.hardcore_container_blinking_healing = containerHardcoreBlinking;
    }

    public static void overrideFullHeart(ResourceLocation fullHeart){
        HealthBarVariables.full_heart = fullHeart;
    }
    public static void overrideFullHeartBlinking(ResourceLocation fullHeartBlinking){
        HealthBarVariables.full_heart_blinking_damage = fullHeartBlinking;
    }
    public static void overrideHalfHeart(ResourceLocation halfHeart){
        HealthBarVariables.half_heart = halfHeart;
    }
    public static void overrideHalfHeartBlinking(ResourceLocation halfHeartBlinking){
        HealthBarVariables.half_heart_blinking_damage = halfHeartBlinking;
    }
    public static void overrideHardcoreFullHeart(ResourceLocation hardcoreFullHeart){
        HealthBarVariables.hardcore_full_heart = hardcoreFullHeart;
    }
    public static void overrideHardcoreFullHeartBlinking(ResourceLocation hardcoreFullHeartBlinking){
        HealthBarVariables.hardcore_full_heart_blinking_damage = hardcoreFullHeartBlinking;
    }
    public static void overrideHardcoreHalfHeart(ResourceLocation hardcoreHalfHeart){
        HealthBarVariables.hardcore_half_heart = hardcoreHalfHeart;
    }
    public static void overrideHardcoreHalfHeartBlinking(ResourceLocation hardcoreHalfHeartBlinking){
        HealthBarVariables.hardcore_half_heart_blinking_damage = hardcoreHalfHeartBlinking;
    }

    public static void overridePoisonedFullHeart(ResourceLocation fullHeart){
        HealthBarVariables.poisoned_full_heart = fullHeart;
    }
    public static void overridePoisonedFullHeartBlinking(ResourceLocation fullHeartBlinking){
        HealthBarVariables.poisoned_full_heart_blinking_damage = fullHeartBlinking;
    }
    public static void overridePoisonedHalfHeart(ResourceLocation halfHeart){
        HealthBarVariables.poisoned_half_heart = halfHeart;
    }
    public static void overridePoisonedHalfHeartBlinking(ResourceLocation halfHeartBlinking){
        HealthBarVariables.poisoned_half_heart_blinking_damage = halfHeartBlinking;
    }
    public static void overridePoisonedHardcoreFullHeart(ResourceLocation hardcoreFullHeart){
        HealthBarVariables.hardcore_poisoned_full_heart = hardcoreFullHeart;
    }
    public static void overridePoisonedHardcoreFullHeartBlinking(ResourceLocation hardcoreFullHeartBlinking){
        HealthBarVariables.hardcore_poisoned_full_heart_blinking_damage = hardcoreFullHeartBlinking;
    }
    public static void overridePoisonedHardcoreHalfHeart(ResourceLocation hardcoreHalfHeart){
        HealthBarVariables.hardcore_poisoned_half_heart = hardcoreHalfHeart;
    }
    public static void overridePoisonedHardcoreHalfHeartBlinking(ResourceLocation hardcoreHalfHeartBlinking){
        HealthBarVariables.hardcore_poisoned_half_heart_blinking_damage = hardcoreHalfHeartBlinking;
    }

    public static void overrideWitheredFullHeart(ResourceLocation fullHeart){
        HealthBarVariables.withered_full_heart = fullHeart;
    }
    public static void overrideWitheredFullHeartBlinking(ResourceLocation fullHeartBlinking){
        HealthBarVariables.withered_full_heart_blinking_damage = fullHeartBlinking;
    }
    public static void overrideWitheredHalfHeart(ResourceLocation halfHeart){
        HealthBarVariables.withered_half_heart = halfHeart;
    }
    public static void overrideWitheredHalfHeartBlinking(ResourceLocation halfHeartBlinking){
        HealthBarVariables.withered_half_heart_blinking_damage = halfHeartBlinking;
    }
    public static void overrideWitheredHardcoreFullHeart(ResourceLocation hardcoreFullHeart){
        HealthBarVariables.hardcore_withered_full_heart = hardcoreFullHeart;
    }
    public static void overrideWitheredHardcoreFullHeartBlinking(ResourceLocation hardcoreFullHeartBlinking){
        HealthBarVariables.hardcore_withered_full_heart_blinking_damage = hardcoreFullHeartBlinking;
    }
    public static void overrideWitheredHardcoreHalfHeart(ResourceLocation hardcoreHalfHeart){
        HealthBarVariables.hardcore_withered_half_heart = hardcoreHalfHeart;
    }
    public static void overrideWitheredHardcoreHalfHeartBlinking(ResourceLocation hardcoreHalfHeartBlinking){
        HealthBarVariables.hardcore_withered_half_heart_blinking_damage = hardcoreHalfHeartBlinking;
    }

    public static void overrideFrozenFullHeart(ResourceLocation fullHeart){
        HealthBarVariables.frozen_full_heart = fullHeart;
    }
    public static void overrideFrozenFullHeartBlinking(ResourceLocation fullHeartBlinking){
        HealthBarVariables.frozen_full_heart_blinking_damage = fullHeartBlinking;
    }
    public static void overrideFrozenHalfHeart(ResourceLocation halfHeart){
        HealthBarVariables.frozen_half_heart = halfHeart;
    }
    public static void overrideFrozenHalfHeartBlinking(ResourceLocation halfHeartBlinking){
        HealthBarVariables.frozen_half_heart_blinking_damage = halfHeartBlinking;
    }
    public static void overrideFrozenHardcoreFullHeart(ResourceLocation hardcoreFullHeart){
        HealthBarVariables.hardcore_frozen_full_heart = hardcoreFullHeart;
    }
    public static void overrideFrozenHardcoreFullHeartBlinking(ResourceLocation hardcoreFullHeartBlinking){
        HealthBarVariables.hardcore_frozen_full_heart_blinking_damage = hardcoreFullHeartBlinking;
    }
    public static void overrideFrozenHardcoreHalfHeart(ResourceLocation hardcoreHalfHeart){
        HealthBarVariables.hardcore_frozen_half_heart = hardcoreHalfHeart;
    }
    public static void overrideFrozenHardcoreHalfHeartBlinking(ResourceLocation hardcoreHalfHeartBlinking){
        HealthBarVariables.hardcore_frozen_half_heart_blinking_damage = hardcoreHalfHeartBlinking;
    }

    public static void overrideAbsorbingFullHeart(ResourceLocation fullHeart){
        HealthBarVariables.absorbing_full_heart = fullHeart;
    }
    public static void overrideAbsorbingHalfHeart(ResourceLocation halfHeart){
        HealthBarVariables.absorbing_half_heart = halfHeart;
    }
    public static void overrideAbsorbingHardcoreFullHeart(ResourceLocation hardcoreFullHeart){
        HealthBarVariables.hardcore_absorbing_full_heart = hardcoreFullHeart;
    }
    public static void overrideAbsorbingHardcoreHalfHeart(ResourceLocation hardcoreHalfHeart){
        HealthBarVariables.hardcore_absorbing_half_heart = hardcoreHalfHeart;
    }


    public static void overrideStartY(int startY){
        HealthBarVariables.startY = startY;
    }
    public static void overrideStartX(int startX){
        HealthBarVariables.startX = startX;
    }
    public static void overrideIsHardcore(boolean isHardcore){
        HealthBarVariables.isHardcore = isHardcore;
    }
    private static void setSpaceBetweenRows(int space){}


}
