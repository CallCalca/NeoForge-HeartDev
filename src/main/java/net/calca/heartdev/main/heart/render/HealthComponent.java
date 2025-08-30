package net.calca.heartdev.main.heart.render;

import net.calca.heartdev.main.heart.render.data.variables.HealthBarPersonalVariables;
import net.calca.heartdev.main.heart.types.CustomContainerType;
import net.calca.heartdev.main.heart.types.CustomHeartType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

public class HealthComponent{

    private final ServerPlayer serverPlayer;
    private final HealthBarPersonalVariables.PlayerVariables playerVars;

    public HealthComponent(LivingEntity livingEntity) {
        if (livingEntity instanceof ServerPlayer sp) {
            this.serverPlayer = sp;
        } else {
            this.serverPlayer = null;
        }

        this.playerVars = (this.serverPlayer != null)
                ? HealthBarGlobalVariables.getPlayerVariables(this.serverPlayer)
                : null;
    }

    public void setContainerType(CustomContainerType customContainerType){
        playerVars.resources.CONTAINER = customContainerType;
    }
    public void setHeartType(CustomHeartType customHeartType){
        playerVars.resources.HEARTS = customHeartType;
    }
    public void setPoisonedType(CustomHeartType customHeartType){
        playerVars.resources.POISONED_HEARTS = customHeartType;
    }
    public void setWitheredType(CustomHeartType customHeartType){
        playerVars.resources.WITHERED_HEARTS = customHeartType;
    }
    public void setFrozenType(CustomHeartType customHeartType){
        playerVars.resources.FROZEN_HEARTS = customHeartType;
    }
    public void setAbsorbingType(CustomHeartType customHeartType){
        playerVars.resources.ABSORBING_HEARTS = customHeartType;
    }

    public void setRegenAnimationSpeed(int speed){
        playerVars.regen.reg_ticksPerHeart = speed;
    }
    public void setRegenAnimationCooldown(int cooldown){
        playerVars.regen.reg_cooldown = cooldown;
    }
    public void setRegenAnimationOffSetY(int offSetY){
        playerVars.regen.reg_yOffset = offSetY;
    }
    public void overrideIsHardcore(boolean isHardcore){
        playerVars.isHardcore = isHardcore;
    }

    public void setMaximumSpaceBetweenRows(int space){
        playerVars.gui.spaceBetweenRowsMax = space;
    }
    public void setMinimumSpaceBetweenRows(int space){
        playerVars.gui.spaceBetweenRowsMin = space;
    }

    public void setHideEmptyHearts(boolean hide){
        playerVars.gui.hideEmptyHearts = hide;
    }
    public void setCollapseDifferentLifeTypes(boolean collapse){
        playerVars.gui.collapseDifferentLifeTypes = collapse;
    }

    public void setNormalLifeNumber(int number){
        playerVars.health.red_LifeNumber = number;
    }
    public void setAbsorptionLifeNumber(int number){
        playerVars.health.abs_LifeNumber = number;
    }

    public void overrideContainer(ResourceLocation container){
        playerVars.resources.container = container;
    }
    public void overrideContainerBlinkingDamage(ResourceLocation containerBlinking){
        playerVars.resources.container_blinking_damage = containerBlinking;
    }
    public void overrideContainerBlinkingHealing(ResourceLocation containerBlinking){
        playerVars.resources.container_blinking_healing = containerBlinking;
    }
    public void overrideContainerHardcore(ResourceLocation containerHardcore){
        playerVars.resources.hardcore_container = containerHardcore;
    }
    public void overrideContainerHardcoreBlinkingDamage(ResourceLocation containerHardcoreBlinking){
        playerVars.resources.hardcore_container_blinking_damage = containerHardcoreBlinking;
    }
    public void overrideContainerHardcoreBlinkingHealing(ResourceLocation containerHardcoreBlinking){
        playerVars.resources.hardcore_container_blinking_healing = containerHardcoreBlinking;
    }

    public void overrideFullHeart(ResourceLocation fullHeart){
        playerVars.resources.full_heart = fullHeart;
    }
    public void overrideFullHeartBlinking(ResourceLocation fullHeartBlinking){
        playerVars.resources.full_heart_blinking_damage = fullHeartBlinking;
    }
    public void overrideHalfHeart(ResourceLocation halfHeart){
        playerVars.resources.half_heart = halfHeart;
    }
    public void overrideHalfHeartBlinking(ResourceLocation halfHeartBlinking){
        playerVars.resources.half_heart_blinking_damage = halfHeartBlinking;
    }
    public void overrideHardcoreFullHeart(ResourceLocation hardcoreFullHeart){
        playerVars.resources.hardcore_full_heart = hardcoreFullHeart;
    }
    public void overrideHardcoreFullHeartBlinking(ResourceLocation hardcoreFullHeartBlinking){
        playerVars.resources.hardcore_full_heart_blinking_damage = hardcoreFullHeartBlinking;
    }
    public void overrideHardcoreHalfHeart(ResourceLocation hardcoreHalfHeart){
        playerVars.resources.hardcore_half_heart = hardcoreHalfHeart;
    }
    public void overrideHardcoreHalfHeartBlinking(ResourceLocation hardcoreHalfHeartBlinking){
        playerVars.resources.hardcore_half_heart_blinking_damage = hardcoreHalfHeartBlinking;
    }

    public void overridePoisonedFullHeart(ResourceLocation fullHeart){
        playerVars.resources.poisoned_full_heart = fullHeart;
    }
    public void overridePoisonedFullHeartBlinking(ResourceLocation fullHeartBlinking){
        playerVars.resources.poisoned_full_heart_blinking_damage = fullHeartBlinking;
    }
    public void overridePoisonedHalfHeart(ResourceLocation halfHeart){
        playerVars.resources.poisoned_half_heart = halfHeart;
    }
    public void overridePoisonedHalfHeartBlinking(ResourceLocation halfHeartBlinking){
        playerVars.resources.poisoned_half_heart_blinking_damage = halfHeartBlinking;
    }
    public void overridePoisonedHardcoreFullHeart(ResourceLocation hardcoreFullHeart){
        playerVars.resources.hardcore_poisoned_full_heart = hardcoreFullHeart;
    }
    public void overridePoisonedHardcoreFullHeartBlinking(ResourceLocation hardcoreFullHeartBlinking){
        playerVars.resources.hardcore_poisoned_full_heart_blinking_damage = hardcoreFullHeartBlinking;
    }
    public void overridePoisonedHardcoreHalfHeart(ResourceLocation hardcoreHalfHeart){
        playerVars.resources.hardcore_poisoned_half_heart = hardcoreHalfHeart;
    }
    public void overridePoisonedHardcoreHalfHeartBlinking(ResourceLocation hardcoreHalfHeartBlinking){
        playerVars.resources.hardcore_poisoned_half_heart_blinking_damage = hardcoreHalfHeartBlinking;
    }

    public void overrideWitheredFullHeart(ResourceLocation fullHeart){
        playerVars.resources.withered_full_heart = fullHeart;
    }
    public void overrideWitheredFullHeartBlinking(ResourceLocation fullHeartBlinking){
        playerVars.resources.withered_full_heart_blinking_damage = fullHeartBlinking;
    }
    public void overrideWitheredHalfHeart(ResourceLocation halfHeart){
        playerVars.resources.withered_half_heart = halfHeart;
    }
    public void overrideWitheredHalfHeartBlinking(ResourceLocation halfHeartBlinking){
        playerVars.resources.withered_half_heart_blinking_damage = halfHeartBlinking;
    }
    public void overrideWitheredHardcoreFullHeart(ResourceLocation hardcoreFullHeart){
        playerVars.resources.hardcore_withered_full_heart = hardcoreFullHeart;
    }
    public void overrideWitheredHardcoreFullHeartBlinking(ResourceLocation hardcoreFullHeartBlinking){
        playerVars.resources.hardcore_withered_full_heart_blinking_damage = hardcoreFullHeartBlinking;
    }
    public void overrideWitheredHardcoreHalfHeart(ResourceLocation hardcoreHalfHeart){
        playerVars.resources.hardcore_withered_half_heart = hardcoreHalfHeart;
    }
    public void overrideWitheredHardcoreHalfHeartBlinking(ResourceLocation hardcoreHalfHeartBlinking){
        playerVars.resources.hardcore_withered_half_heart_blinking_damage = hardcoreHalfHeartBlinking;
    }

    public void overrideFrozenFullHeart(ResourceLocation fullHeart){
        playerVars.resources.frozen_full_heart = fullHeart;
    }
    public void overrideFrozenFullHeartBlinking(ResourceLocation fullHeartBlinking){
        playerVars.resources.frozen_full_heart_blinking_damage = fullHeartBlinking;
    }
    public void overrideFrozenHalfHeart(ResourceLocation halfHeart){
        playerVars.resources.frozen_half_heart = halfHeart;
    }
    public void overrideFrozenHalfHeartBlinking(ResourceLocation halfHeartBlinking){
        playerVars.resources.frozen_half_heart_blinking_damage = halfHeartBlinking;
    }
    public void overrideFrozenHardcoreFullHeart(ResourceLocation hardcoreFullHeart){
        playerVars.resources.hardcore_frozen_full_heart = hardcoreFullHeart;
    }
    public void overrideFrozenHardcoreFullHeartBlinking(ResourceLocation hardcoreFullHeartBlinking){
        playerVars.resources.hardcore_frozen_full_heart_blinking_damage = hardcoreFullHeartBlinking;
    }
    public void overrideFrozenHardcoreHalfHeart(ResourceLocation hardcoreHalfHeart){
        playerVars.resources.hardcore_frozen_half_heart = hardcoreHalfHeart;
    }
    public void overrideFrozenHardcoreHalfHeartBlinking(ResourceLocation hardcoreHalfHeartBlinking){
        playerVars.resources.hardcore_frozen_half_heart_blinking_damage = hardcoreHalfHeartBlinking;
    }

    public void overrideAbsorbingFullHeart(ResourceLocation fullHeart){
        playerVars.resources.absorbing_full_heart = fullHeart;
    }
    public void overrideAbsorbingHalfHeart(ResourceLocation halfHeart){
        playerVars.resources.absorbing_half_heart = halfHeart;
    }
    public void overrideAbsorbingHardcoreFullHeart(ResourceLocation hardcoreFullHeart){
        playerVars.resources.hardcore_absorbing_full_heart = hardcoreFullHeart;
    }
    public void overrideAbsorbingHardcoreHalfHeart(ResourceLocation hardcoreHalfHeart){
        playerVars.resources.hardcore_absorbing_half_heart = hardcoreHalfHeart;
    }


    public void overrideStartY(int startY){
        playerVars.gui.startY = startY;
    }
    public void overrideStartX(int startX){
        playerVars.gui.startX = startX;
    }


}
