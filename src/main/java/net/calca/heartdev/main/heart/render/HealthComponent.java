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

    /*
    public static void overrideHalfHeartBlinkingHealing(ResourceLocation halfHeartBlinking){
        HealthBarVariables.half_heart_blinking_healing = halfHeartBlinking;
    }
    public static void overrideHardcoreFullHeartBlinkingHealing(ResourceLocation hardcoreFullHeartBlinking){
        HealthBarVariables.hardcore_full_heart_blinking_healing = hardcoreFullHeartBlinking;
    }
    public static void overrideHardcoreHalfHeartBlinkingHealing(ResourceLocation hardcoreHalfHeartBlinking){
        HealthBarVariables.hardcore_half_heart_blinking_healing = hardcoreHalfHeartBlinking;
    }
    public static void overridePoisonedFullHeartBlinkingHealing(ResourceLocation fullHeartBlinking){
        HealthBarVariables.poisoned_full_heart_blinking_healing = fullHeartBlinking;
    }
    public static void overridePoisonedHalfHeartBlinkingHealing(ResourceLocation halfHeartBlinking){
        HealthBarVariables.poisoned_half_heart_blinking_healing = halfHeartBlinking;
    }
    public static void overridePoisonedHardcoreFullHeartBlinkingHealing(ResourceLocation hardcoreFullHeartBlinking){
        HealthBarVariables.hardcore_poisoned_full_heart_blinking_healing = hardcoreFullHeartBlinking;
    }
    public static void overridePoisonedHardcoreHalfHeartBlinkingHealing(ResourceLocation hardcoreHalfHeartBlinking){
        HealthBarVariables.hardcore_poisoned_half_heart_blinking_healing = hardcoreHalfHeartBlinking;
    }
    public static void overrideWitheredFullHeartBlinkingHealing(ResourceLocation fullHeartBlinking){
        HealthBarVariables.withered_full_heart_blinking_healing = fullHeartBlinking;
    }
    public static void overrideWitheredHalfHeartBlinkingHealing(ResourceLocation halfHeartBlinking){
        HealthBarVariables.withered_half_heart_blinking_healing = halfHeartBlinking;
    }
    public static void overrideWitheredHardcoreFullHeartBlinkingHealing(ResourceLocation hardcoreFullHeartBlinking){
        HealthBarVariables.hardcore_withered_full_heart_blinking_healing = hardcoreFullHeartBlinking;
    }
    public static void overrideWitheredHardcoreHalfHeartBlinkingHealing(ResourceLocation hardcoreHalfHeartBlinking){
        HealthBarVariables.hardcore_withered_half_heart_blinking_healing = hardcoreHalfHeartBlinking;
    }
    public static void overrideFrozenFullHeartBlinkingHealing(ResourceLocation fullHeartBlinking){
        HealthBarVariables.frozen_full_heart_blinking_healing = fullHeartBlinking;
    }
    public static void overrideFrozenHalfHeartBlinkingHealing(ResourceLocation halfHeartBlinking){
        HealthBarVariables.frozen_half_heart_blinking_healing = halfHeartBlinking;
    }
    public static void overrideFrozenHardcoreFullHeartBlinkingHealing(ResourceLocation hardcoreFullHeartBlinking){
        HealthBarVariables.hardcore_frozen_full_heart_blinking_healing = hardcoreFullHeartBlinking;
    }
    public static void overrideFrozenHardcoreHalfHeartBlinkingHealing(ResourceLocation hardcoreHalfHeartBlinking){
        HealthBarVariables.hardcore_frozen_half_heart_blinking_healing = hardcoreHalfHeartBlinking;
    }

    */
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
    public static void overrideFullHeartBlinkingDamage(ResourceLocation fullHeartBlinking){
        HealthBarVariables.full_heart_blinking_damage = fullHeartBlinking;
    }
    public static void overrideFullHeartBlinkingHealing(ResourceLocation fullHeartBlinking){
        HealthBarVariables.full_heart_blinking_damage = fullHeartBlinking;
    }
    public static void overrideHalfHeart(ResourceLocation halfHeart){
        HealthBarVariables.half_heart = halfHeart;
    }
    public static void overrideHalfHeartBlinkingDamage(ResourceLocation halfHeartBlinking){
        HealthBarVariables.half_heart_blinking_damage = halfHeartBlinking;
    }
    public static void overrideHardcoreFullHeart(ResourceLocation hardcoreFullHeart){
        HealthBarVariables.hardcore_full_heart = hardcoreFullHeart;
    }
    public static void overrideHardcoreFullHeartBlinkingDamage(ResourceLocation hardcoreFullHeartBlinking){
        HealthBarVariables.hardcore_full_heart_blinking_damage = hardcoreFullHeartBlinking;
    }
    public static void overrideHardcoreHalfHeart(ResourceLocation hardcoreHalfHeart){
        HealthBarVariables.hardcore_half_heart = hardcoreHalfHeart;
    }
    public static void overrideHardcoreHalfHeartBlinkingDamage(ResourceLocation hardcoreHalfHeartBlinking){
        HealthBarVariables.hardcore_half_heart_blinking_damage = hardcoreHalfHeartBlinking;
    }

    public static void overridePoisonedFullHeart(ResourceLocation fullHeart){
        HealthBarVariables.poisoned_full_heart = fullHeart;
    }
    public static void overridePoisonedFullHeartBlinkingDamage(ResourceLocation fullHeartBlinking){
        HealthBarVariables.poisoned_full_heart_blinking_damage = fullHeartBlinking;
    }
    public static void overridePoisonedHalfHeart(ResourceLocation halfHeart){
        HealthBarVariables.poisoned_half_heart = halfHeart;
    }
    public static void overridePoisonedHalfHeartBlinkingDamage(ResourceLocation halfHeartBlinking){
        HealthBarVariables.poisoned_half_heart_blinking_damage = halfHeartBlinking;
    }
    public static void overridePoisonedHardcoreFullHeart(ResourceLocation hardcoreFullHeart){
        HealthBarVariables.hardcore_poisoned_full_heart = hardcoreFullHeart;
    }
    public static void overridePoisonedHardcoreFullHeartBlinkingDamage(ResourceLocation hardcoreFullHeartBlinking){
        HealthBarVariables.hardcore_poisoned_full_heart_blinking_damage = hardcoreFullHeartBlinking;
    }
    public static void overridePoisonedHardcoreHalfHeart(ResourceLocation hardcoreHalfHeart){
        HealthBarVariables.hardcore_poisoned_half_heart = hardcoreHalfHeart;
    }
    public static void overridePoisonedHardcoreHalfHeartBlinkingDamage(ResourceLocation hardcoreHalfHeartBlinking){
        HealthBarVariables.hardcore_poisoned_half_heart_blinking_damage = hardcoreHalfHeartBlinking;
    }

    public static void overrideWitheredFullHeart(ResourceLocation fullHeart){
        HealthBarVariables.withered_full_heart = fullHeart;
    }
    public static void overrideWitheredFullHeartBlinkingDamage(ResourceLocation fullHeartBlinking){
        HealthBarVariables.withered_full_heart_blinking_damage = fullHeartBlinking;
    }
    public static void overrideWitheredHalfHeart(ResourceLocation halfHeart){
        HealthBarVariables.withered_half_heart = halfHeart;
    }
    public static void overrideWitheredHalfHeartBlinkingDamage(ResourceLocation halfHeartBlinking){
        HealthBarVariables.withered_half_heart_blinking_damage = halfHeartBlinking;
    }
    public static void overrideWitheredHardcoreFullHeart(ResourceLocation hardcoreFullHeart){
        HealthBarVariables.hardcore_withered_full_heart = hardcoreFullHeart;
    }
    public static void overrideWitheredHardcoreFullHeartBlinkingDamage(ResourceLocation hardcoreFullHeartBlinking){
        HealthBarVariables.hardcore_withered_full_heart_blinking_damage = hardcoreFullHeartBlinking;
    }
    public static void overrideWitheredHardcoreHalfHeart(ResourceLocation hardcoreHalfHeart){
        HealthBarVariables.hardcore_withered_half_heart = hardcoreHalfHeart;
    }
    public static void overrideWitheredHardcoreHalfHeartBlinkingDamage(ResourceLocation hardcoreHalfHeartBlinking){
        HealthBarVariables.hardcore_withered_half_heart_blinking_damage = hardcoreHalfHeartBlinking;
    }

    public static void overrideFrozenFullHeart(ResourceLocation fullHeart){
        HealthBarVariables.frozen_full_heart = fullHeart;
    }
    public static void overrideFrozenFullHeartBlinkingDamage(ResourceLocation fullHeartBlinking){
        HealthBarVariables.frozen_full_heart_blinking_damage = fullHeartBlinking;
    }
    public static void overrideFrozenHalfHeart(ResourceLocation halfHeart){
        HealthBarVariables.frozen_half_heart = halfHeart;
    }
    public static void overrideFrozenHalfHeartBlinkingDamage(ResourceLocation halfHeartBlinking){
        HealthBarVariables.frozen_half_heart_blinking_damage = halfHeartBlinking;
    }
    public static void overrideFrozenHardcoreFullHeart(ResourceLocation hardcoreFullHeart){
        HealthBarVariables.hardcore_frozen_full_heart = hardcoreFullHeart;
    }
    public static void overrideFrozenHardcoreFullHeartBlinkingDamage(ResourceLocation hardcoreFullHeartBlinking){
        HealthBarVariables.hardcore_frozen_full_heart_blinking_damage = hardcoreFullHeartBlinking;
    }
    public static void overrideFrozenHardcoreHalfHeart(ResourceLocation hardcoreHalfHeart){
        HealthBarVariables.hardcore_frozen_half_heart = hardcoreHalfHeart;
    }
    public static void overrideFrozenHardcoreHalfHeartBlinkingDamage(ResourceLocation hardcoreHalfHeartBlinking){
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


    public static void setStartY(int startY){
        HealthBarVariables.startY = startY;
    }
    public static void setStartX(int startX){
        HealthBarVariables.startX = startX;
    }
    private static void setSpaceBetweenRows(int space){}

    public static void setRegenAnimationSpeed(int speed){
        HealthBarVariables.regenAnimationSpeed = speed;
    }
    public static void setRegenAnimationCooldown(int cooldown){
        HealthBarVariables.regenAnimationCooldown = cooldown;
    }
    public static void setRegenAnimationOffSetY(int offSetY){
        HealthBarVariables.regenAnimationYoffSet = offSetY;
    }

}
