package net.calca.heartdev.main.heart.render;

import net.calca.heartdev.main.heart.render.data.variables.HealthBarPersonalVariables;
import net.minecraft.client.gui.Gui;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class HealthBarGlobalVariables {
    public static HealthBarPersonalVariables.PlayerVariables getPlayerVariables(LivingEntity livingEntity){
        HealthBarPersonalVariables.PlayerVariables var = null;
        if (livingEntity instanceof Player player){
            var = player.getData(HealthBarPersonalVariables.PLAYER_VARIABLES);
        }
        return var;
    }
    public static void buildResourcesWithVanilla(Gui.HeartType heartType, LivingEntity livingEntity){
        HealthBarPersonalVariables.PlayerVariables playerVars = getPlayerVariables(livingEntity);
        if (heartType == Gui.HeartType.CONTAINER) {
            playerVars.resources.container = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/container.png");
            playerVars.resources.container_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/container_blinking.png");
            playerVars.resources.hardcore_container = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/container_hardcore.png");
            playerVars.resources.hardcore_container_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/container_hardcore_blinking.png");
            playerVars.resources.container_blinking_healing = playerVars.resources.container_blinking_damage;
            playerVars.resources.hardcore_container_blinking_healing = playerVars.resources.hardcore_container_blinking_damage;
        } else if (heartType == Gui.HeartType.NORMAL) {
            playerVars.resources.full_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/full.png");
            playerVars.resources.full_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/full_blinking.png");
            playerVars.resources.half_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/half.png");
            playerVars.resources.half_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/half_blinking.png");
            playerVars.resources.hardcore_full_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/hardcore_full.png");
            playerVars.resources.hardcore_full_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/hardcore_full_blinking.png");
            playerVars.resources.hardcore_half_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/hardcore_half.png");
            playerVars.resources.hardcore_half_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/hardcore_half_blinking.png");
        } else if (heartType == Gui.HeartType.POISIONED) {
            playerVars.resources.poisoned_full_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/poisoned_full.png");
            playerVars.resources.poisoned_full_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/poisoned_full_blinking.png");
            playerVars.resources.poisoned_half_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/poisoned_half.png");
            playerVars.resources.poisoned_half_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/poisoned_half_blinking.png");
            playerVars.resources.hardcore_poisoned_full_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/poisoned_hardcore_full.png");
            playerVars.resources.hardcore_poisoned_full_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/poisoned_hardcore_full_blinking.png");
            playerVars.resources.hardcore_poisoned_half_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/poisoned_hardcore_half.png");
            playerVars.resources.hardcore_poisoned_half_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/poisoned_hardcore_half_blinking.png");
        } else if (heartType == Gui.HeartType.WITHERED) {
            playerVars.resources.withered_full_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/withered_full.png");
            playerVars.resources.withered_full_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/withered_full_blinking.png");
            playerVars.resources.withered_half_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/withered_half.png");
            playerVars.resources.withered_half_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/withered_half_blinking.png");
            playerVars.resources.hardcore_withered_full_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/withered_hardcore_full.png");
            playerVars.resources.hardcore_withered_full_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/withered_hardcore_full_blinking.png");
            playerVars.resources.hardcore_withered_half_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/withered_hardcore_half.png");
            playerVars.resources.hardcore_withered_half_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/withered_hardcore_half_blinking.png");
        } else if (heartType == Gui.HeartType.FROZEN) {
            playerVars.resources.frozen_full_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/frozen_full.png");
            playerVars.resources.frozen_full_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/frozen_full_blinking.png");
            playerVars.resources.frozen_half_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/frozen_half.png");
            playerVars.resources.frozen_half_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/frozen_half_blinking.png");
            playerVars.resources.hardcore_frozen_full_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/frozen_hardcore_full.png");
            playerVars.resources.hardcore_frozen_full_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/frozen_hardcore_full_blinking.png");
            playerVars.resources.hardcore_frozen_half_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/frozen_hardcore_half.png");
            playerVars.resources.hardcore_frozen_half_heart_blinking_damage = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/frozen_hardcore_half_blinking.png");
        } else if (heartType == Gui.HeartType.ABSORBING) {
            playerVars.resources.absorbing_full_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/absorbing_full.png");
            playerVars.resources.absorbing_half_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/absorbing_half.png");
            playerVars.resources.hardcore_absorbing_full_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/absorbing_hardcore_full.png");
            playerVars.resources.hardcore_absorbing_half_heart = ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/heart/absorbing_hardcore_half.png");
        }
        playerVars.syncPlayerVariables(livingEntity);
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
        HealthBarPersonalVariables.PlayerVariables playerVars = getPlayerVariables(livingEntity);
        ResourceLocation txt = isHardcore ? playerVars.resources.hardcore_full_heart : playerVars.resources.full_heart;
        if (isAbsorption){
            txt = isHardcore ? playerVars.resources.hardcore_absorbing_full_heart : playerVars.resources.absorbing_full_heart;

        } else if (livingEntity.hasEffect(MobEffects.POISON)) {
                txt = isHardcore ? playerVars.resources.hardcore_poisoned_full_heart : playerVars.resources.poisoned_full_heart;

            } else if (livingEntity.hasEffect(MobEffects.WITHER)) {
                txt = isHardcore ? playerVars.resources.hardcore_withered_full_heart : playerVars.resources.withered_full_heart;

            } else if (livingEntity.isFullyFrozen()) {
                txt = isHardcore ? playerVars.resources.hardcore_frozen_full_heart : playerVars.resources.frozen_full_heart;

            }
        playerVars.syncPlayerVariables(livingEntity);
        return txt;
    }
    private static ResourceLocation getFullBlinkingHeart(LivingEntity livingEntity, boolean isHardcore){
        HealthBarPersonalVariables.PlayerVariables playerVars = getPlayerVariables(livingEntity);
        ResourceLocation txt;
        if (!isHardcore) {
            txt = playerVars.resources.full_heart_blinking_damage;
            if (livingEntity.hasEffect(MobEffects.POISON)) {
                txt = playerVars.resources.poisoned_full_heart_blinking_damage;

            } else if (livingEntity.hasEffect(MobEffects.WITHER)) {
                txt = playerVars.resources.withered_full_heart_blinking_damage;

            } else if (livingEntity.isFullyFrozen()) {
                txt = playerVars.resources.frozen_full_heart_blinking_damage;

            }
        }else {
            txt = playerVars.resources.hardcore_full_heart_blinking_damage;
            if (livingEntity.hasEffect(MobEffects.POISON)) {
                txt = playerVars.resources.hardcore_poisoned_full_heart_blinking_damage;

            } else if (livingEntity.hasEffect(MobEffects.WITHER)) {
                txt = playerVars.resources.hardcore_withered_full_heart_blinking_damage;

            } else if (livingEntity.isFullyFrozen()) {
                txt = playerVars.resources.hardcore_frozen_full_heart_blinking_damage;

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
        HealthBarPersonalVariables.PlayerVariables playerVars = getPlayerVariables(livingEntity);
        ResourceLocation txt = isHardcore ? playerVars.resources.hardcore_half_heart : playerVars.resources.half_heart;
        if (isAbsorption){
            txt = isHardcore ? playerVars.resources.hardcore_absorbing_half_heart : playerVars.resources.absorbing_half_heart;

        } else if (livingEntity.hasEffect(MobEffects.POISON)) {
                txt = isHardcore ? playerVars.resources.hardcore_poisoned_half_heart : playerVars.resources.poisoned_half_heart;

        } else if (livingEntity.hasEffect(MobEffects.WITHER)) {
                txt = isHardcore ? playerVars.resources.hardcore_withered_half_heart : playerVars.resources.withered_half_heart;

        } else if (livingEntity.isFullyFrozen()) {
                txt = isHardcore ? playerVars.resources.hardcore_frozen_half_heart : playerVars.resources.frozen_half_heart;

        }
        return txt;
    }
    private static ResourceLocation getHalfBlinkingHeart(LivingEntity livingEntity, boolean isHardcore){
        HealthBarPersonalVariables.PlayerVariables playerVars = getPlayerVariables(livingEntity);
        ResourceLocation txt;
        if (!isHardcore){
           txt = playerVars.resources.half_heart_blinking_damage;
            if (livingEntity.hasEffect(MobEffects.POISON)) {
                txt = playerVars.resources.poisoned_half_heart_blinking_damage;

        } else if (livingEntity.hasEffect(MobEffects.WITHER)) {
                txt =playerVars.resources.withered_half_heart_blinking_damage;

        } else if (livingEntity.isFullyFrozen()) {
            txt = playerVars.resources.frozen_half_heart_blinking_damage;
            }
        }else {
            txt = playerVars.resources.hardcore_half_heart_blinking_damage;
            if (livingEntity.hasEffect(MobEffects.POISON)) {
                txt = playerVars.resources.hardcore_poisoned_half_heart_blinking_damage;

            } else if (livingEntity.hasEffect(MobEffects.WITHER)) {
                txt = playerVars.resources.hardcore_withered_half_heart_blinking_damage;

            } else if (livingEntity.isFullyFrozen()) {
                txt = playerVars.resources.hardcore_frozen_half_heart_blinking_damage;
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
