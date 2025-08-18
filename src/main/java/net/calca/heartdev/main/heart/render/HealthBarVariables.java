package net.calca.heartdev.main.heart.render;

import net.calca.heartdev.main.heart.types.CustomContainerType;
import net.calca.heartdev.main.heart.types.CustomHeartType;
import net.minecraft.client.gui.Gui;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashSet;
import java.util.Set;

public class HealthBarVariables {
    public static boolean isHardcore;

    public static int startY;
    public static int startX;
    public static int spaceBetweenRowsMax = 10;
    public static int spaceBetweenRowsMin = 7;

    public static boolean hideEmptyHearts = false;
    public static boolean collapseDifferentLifeTypes = false;
    public static Set<Integer> absorptionSlotsList = new HashSet<>(); //List to check for absorption hearts UNKOWN

    public static int regenAnimationSpeed = 1;
    public static int regenAnimationCooldown = 15;
    public static int regenAnimationYoffSet = -2;

    public static CustomContainerType CONTAINER = null;
    public static CustomHeartType HEARTS = null;
    public static CustomHeartType POISONED_HEARTS = null;
    public static CustomHeartType WITHERED_HEARTS = null;
    public static CustomHeartType FROZEN_HEARTS = null;
    public static CustomHeartType ABSORBING_HEARTS = null;

    public static ResourceLocation container;
    public static ResourceLocation container_blinking_damage;
    public static ResourceLocation container_blinking_healing;
    public static ResourceLocation hardcore_container;
    public static ResourceLocation hardcore_container_blinking_damage;
    public static ResourceLocation hardcore_container_blinking_healing;

    public static ResourceLocation full_heart;
    public static ResourceLocation full_heart_blinking_damage;
    public static ResourceLocation half_heart;
    public static ResourceLocation half_heart_blinking_damage;
    public static ResourceLocation hardcore_full_heart;
    public static ResourceLocation hardcore_full_heart_blinking_damage;
    public static ResourceLocation hardcore_half_heart;
    public static ResourceLocation hardcore_half_heart_blinking_damage;

    public static ResourceLocation poisoned_full_heart;
    public static ResourceLocation poisoned_full_heart_blinking_damage;
    public static ResourceLocation poisoned_half_heart;
    public static ResourceLocation poisoned_half_heart_blinking_damage;
    public static ResourceLocation hardcore_poisoned_full_heart;
    public static ResourceLocation hardcore_poisoned_full_heart_blinking_damage;
    public static ResourceLocation hardcore_poisoned_half_heart;
    public static ResourceLocation hardcore_poisoned_half_heart_blinking_damage;

    public static ResourceLocation withered_full_heart;
    public static ResourceLocation withered_full_heart_blinking_damage;
    public static ResourceLocation withered_half_heart;
    public static ResourceLocation withered_half_heart_blinking_damage;
    public static ResourceLocation hardcore_withered_full_heart;
    public static ResourceLocation hardcore_withered_full_heart_blinking_damage;
    public static ResourceLocation hardcore_withered_half_heart;
    public static ResourceLocation hardcore_withered_half_heart_blinking_damage;

    public static ResourceLocation frozen_full_heart;
    public static ResourceLocation frozen_full_heart_blinking_damage;
    public static ResourceLocation frozen_half_heart;
    public static ResourceLocation frozen_half_heart_blinking_damage;
    public static ResourceLocation hardcore_frozen_full_heart;
    public static ResourceLocation hardcore_frozen_full_heart_blinking_damage;
    public static ResourceLocation hardcore_frozen_half_heart;
    public static ResourceLocation hardcore_frozen_half_heart_blinking_damage;

    public static ResourceLocation absorbing_full_heart;
    public static ResourceLocation absorbing_half_heart;
    public static ResourceLocation hardcore_absorbing_full_heart;
    public static ResourceLocation hardcore_absorbing_half_heart;

    public static void buildResourcesWithVanilla(Gui.HeartType heartType){
        if (heartType == Gui.HeartType.CONTAINER) {
            container = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/container.png");
            container_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/container_blinking.png");
            hardcore_container = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/container_hardcore.png");
            hardcore_container_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/container_hardcore_blinking.png");
            container_blinking_healing = container_blinking_damage;
            hardcore_container_blinking_healing = hardcore_container_blinking_damage;
        } else if (heartType == Gui.HeartType.NORMAL) {
            full_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/full.png");
            full_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/full_blinking.png");
            half_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/half.png");
            half_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/half_blinking.png");
            hardcore_full_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/hardcore_full.png");
            hardcore_full_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/hardcore_full_blinking.png");
            hardcore_half_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/hardcore_half.png");
            hardcore_half_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/hardcore_half_blinking.png");
        } else if (heartType == Gui.HeartType.POISIONED) {
            poisoned_full_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/poisoned_full.png");
            poisoned_full_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/poisoned_full_blinking.png");
            poisoned_half_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/poisoned_half.png");
            poisoned_half_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/poisoned_half_blinking.png");
            hardcore_poisoned_full_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/poisoned_hardcore_full.png");
            hardcore_poisoned_full_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/poisoned_hardcore_full_blinking.png");
            hardcore_poisoned_half_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/poisoned_hardcore_half.png");
            hardcore_poisoned_half_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/poisoned_hardcore_half_blinking.png");
        } else if (heartType == Gui.HeartType.WITHERED) {
            withered_full_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/withered_full.png");
            withered_full_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/withered_full_blinking.png");
            withered_half_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/withered_half.png");
            withered_half_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/withered_half_blinking.png");
            hardcore_withered_full_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/withered_hardcore_full.png");
            hardcore_withered_full_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/withered_hardcore_full_blinking.png");
            hardcore_withered_half_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/withered_hardcore_half.png");
            hardcore_withered_half_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/withered_hardcore_half_blinking.png");
        } else if (heartType == Gui.HeartType.FROZEN) {
            frozen_full_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/frozen_full.png");
            frozen_full_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/frozen_full_blinking.png");
            frozen_half_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/frozen_half.png");
            frozen_half_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/frozen_half_blinking.png");
            hardcore_frozen_full_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/frozen_hardcore_full.png");
            hardcore_frozen_full_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/frozen_hardcore_full_blinking.png");
            hardcore_frozen_half_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/frozen_hardcore_half.png");
            hardcore_frozen_half_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/frozen_hardcore_half_blinking.png");
        } else if (heartType == Gui.HeartType.ABSORBING) {
            absorbing_full_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/absorbing_full.png");
            absorbing_half_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/absorbing_half.png");
            hardcore_absorbing_full_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/absorbing_hardcore_full.png");
            hardcore_absorbing_half_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/absorbing_hardcore_half.png");
        }
    }

    /// method use to get the heart to draw (not used for the container hearts, since they would need a different method, and I decide
    /// to simply write the code inside its rendering method)
    public static ResourceLocation getSprite(LivingEntity livingEntity, boolean isHardcore, boolean isHalf, boolean isBlinking, boolean isAbsorption){
        ResourceLocation txt;
            if (isHalf){
                txt = isBlinking ? getHalfBlinkingHeart(livingEntity, isHardcore) : getHalfHeart(livingEntity, isHardcore, isAbsorption);
            }else {
                txt = isBlinking ? getFullBlinkingHeart(livingEntity, isHardcore) : getFullHeart(livingEntity, isHardcore, isAbsorption);
            }
        return txt;
    }
    //          ^
    //          |
    private static ResourceLocation getFullHeart(LivingEntity livingEntity, boolean isHardcore, boolean isAbsorption){
        ResourceLocation txt = isHardcore ? hardcore_full_heart : full_heart;
        if (isAbsorption){
            txt = isHardcore ? hardcore_absorbing_full_heart : absorbing_full_heart;

        } else if (livingEntity.hasEffect(MobEffects.POISON)) {
                txt = isHardcore ? hardcore_poisoned_full_heart : poisoned_full_heart;

            } else if (livingEntity.hasEffect(MobEffects.WITHER)) {
                txt = isHardcore ? hardcore_withered_full_heart : withered_full_heart;

            } else if (livingEntity.isFullyFrozen()) {
                txt = isHardcore ? hardcore_frozen_full_heart : frozen_full_heart;

            }
        return txt;
    }
    private static ResourceLocation getFullBlinkingHeart(LivingEntity livingEntity, boolean isHardcore){
        ResourceLocation txt;
        if (!isHardcore) {
            txt = full_heart_blinking_damage;
            if (livingEntity.hasEffect(MobEffects.POISON)) {
                txt = poisoned_full_heart_blinking_damage;

            } else if (livingEntity.hasEffect(MobEffects.WITHER)) {
                txt = withered_full_heart_blinking_damage;

            } else if (livingEntity.isFullyFrozen()) {
                txt = frozen_full_heart_blinking_damage;

            }
        }else {
            txt = hardcore_full_heart_blinking_damage;
            if (livingEntity.hasEffect(MobEffects.POISON)) {
                txt = hardcore_poisoned_full_heart_blinking_damage;

            } else if (livingEntity.hasEffect(MobEffects.WITHER)) {
                txt = hardcore_withered_full_heart_blinking_damage;

            } else if (livingEntity.isFullyFrozen()) {
                txt = hardcore_frozen_full_heart_blinking_damage;

            }
        }
        /*
        if (!isHardcore) {
            txt = isRegen ? full_heart_blinking_healing : full_heart_blinking_damage;
            if (livingEntity.hasEffect(MobEffects.POISON)) {
                txt = isRegen ? poisoned_full_heart_blinking_healing : poisoned_full_heart_blinking_damage;

            } else if (livingEntity.hasEffect(MobEffects.WITHER)) {
                txt = isRegen ? withered_full_heart_blinking_healing : withered_full_heart_blinking_damage;

            } else if (livingEntity.isFullyFrozen()) {
                txt = isRegen ? frozen_full_heart_blinking_healing : frozen_full_heart_blinking_damage;

            }
        }else {
            txt = isRegen ? hardcore_full_heart_blinking_healing : hardcore_full_heart_blinking_damage;
            if (livingEntity.hasEffect(MobEffects.POISON)) {
                txt = isRegen ? hardcore_poisoned_full_heart_blinking_healing : hardcore_poisoned_full_heart_blinking_damage;

            } else if (livingEntity.hasEffect(MobEffects.WITHER)) {
                txt = isRegen ? hardcore_withered_full_heart_blinking_healing : hardcore_withered_full_heart_blinking_damage;

            } else if (livingEntity.isFullyFrozen()) {
                txt = isRegen ? hardcore_frozen_full_heart_blinking_healing : hardcore_frozen_full_heart_blinking_damage;

            }
        }

         */
        return txt;
    }
    private static ResourceLocation getHalfHeart(LivingEntity livingEntity, boolean isHardcore, boolean isAbsorption){
        ResourceLocation txt = isHardcore ? hardcore_half_heart : half_heart;
        if (isAbsorption){
            txt = isHardcore ? hardcore_absorbing_half_heart : absorbing_half_heart;

        } else if (livingEntity.hasEffect(MobEffects.POISON)) {
                txt = isHardcore ? hardcore_poisoned_half_heart : poisoned_half_heart;

        } else if (livingEntity.hasEffect(MobEffects.WITHER)) {
                txt = isHardcore ? hardcore_withered_half_heart : withered_half_heart;

        } else if (livingEntity.isFullyFrozen()) {
                txt = isHardcore ? hardcore_frozen_half_heart : frozen_half_heart;

        }
        return txt;
    }
    private static ResourceLocation getHalfBlinkingHeart(LivingEntity livingEntity, boolean isHardcore){
        ResourceLocation txt;
        if (!isHardcore){
           txt = half_heart_blinking_damage;
            if (livingEntity.hasEffect(MobEffects.POISON)) {
                txt = poisoned_half_heart_blinking_damage;

        } else if (livingEntity.hasEffect(MobEffects.WITHER)) {
                txt = withered_half_heart_blinking_damage;

        } else if (livingEntity.isFullyFrozen()) {
            txt = frozen_half_heart_blinking_damage;
            }
        }else {
            txt = hardcore_half_heart_blinking_damage;
            if (livingEntity.hasEffect(MobEffects.POISON)) {
                txt = hardcore_poisoned_half_heart_blinking_damage;

            } else if (livingEntity.hasEffect(MobEffects.WITHER)) {
                txt = hardcore_withered_half_heart_blinking_damage;

            } else if (livingEntity.isFullyFrozen()) {
                txt = hardcore_frozen_half_heart_blinking_damage;
            }

        }
        /*
        if (!isHardcore){
           txt = isRegen ? half_heart_blinking_healing : half_heart_blinking_damage;
            if (livingEntity.hasEffect(MobEffects.POISON)) {
                txt = isRegen ? poisoned_half_heart_blinking_healing : poisoned_half_heart_blinking_damage;

        } else if (livingEntity.hasEffect(MobEffects.WITHER)) {
                txt = isRegen ? withered_half_heart_blinking_healing : withered_half_heart_blinking_damage;

        } else if (livingEntity.isFullyFrozen()) {
            txt = isRegen ? frozen_half_heart_blinking_healing : frozen_half_heart_blinking_damage;
            }
        }else {
            txt = isRegen ? hardcore_half_heart_blinking_healing : hardcore_half_heart_blinking_damage;
            if (livingEntity.hasEffect(MobEffects.POISON)) {
                txt = isRegen ? hardcore_poisoned_half_heart_blinking_healing : hardcore_poisoned_half_heart_blinking_damage;

            } else if (livingEntity.hasEffect(MobEffects.WITHER)) {
                txt = isRegen ? hardcore_withered_half_heart_blinking_healing : hardcore_withered_half_heart_blinking_damage;

            } else if (livingEntity.isFullyFrozen()) {
                txt = isRegen ? hardcore_frozen_half_heart_blinking_healing : hardcore_frozen_half_heart_blinking_damage;
            }
        }

         */
        return txt;
    }

}
