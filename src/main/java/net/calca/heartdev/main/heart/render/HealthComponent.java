package net.calca.heartdev.main.heart.render;

import net.calca.heartdev.main.heart.render.data.variables.HealthBarVariables;
import net.calca.heartdev.main.heart.types.CustomContainerType;
import net.calca.heartdev.main.heart.types.CustomHeartType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

public class HealthComponent{

    public final ServerPlayer serverPlayer;
    public final HealthBarVariables.PlayerVariables playerVars;

    //Variables initializer (It initializes serverPlayer and playerVars).
    public HealthComponent(LivingEntity livingEntity) {
        if (livingEntity instanceof ServerPlayer sp) {
            this.serverPlayer = sp;
        } else {
            this.serverPlayer = null;
        }

        this.playerVars = (this.serverPlayer != null)
                ? HealthResourceBuilding.getPlayerVariables(this.serverPlayer)
                : null;
    }

    private void sync(){
        playerVars.syncPlayerVariables(serverPlayer);
    }
    public void variablesRefresh(){
        HealthBarRender.HEALTH_INSTANCE.setVariables(playerVars, serverPlayer);
        sync();
    }

    public void activateRendering(boolean shouldRender){
        if (playerVars.should_render == shouldRender) return;
        playerVars.should_render = shouldRender;
        sync();
    }

    //Start auto-synced block ---
    public void setContainerType(CustomContainerType customContainerType){
        if (playerVars.resources.CONTAINER == customContainerType) return;
        playerVars.resources.CONTAINER = customContainerType;
        HealthResourceBuilding.buildContainerTextures(serverPlayer, playerVars);
    }
    public void setHeartType(CustomHeartType customHeartType){
        if (playerVars.resources.HEARTS == customHeartType) return;
        playerVars.resources.HEARTS = customHeartType;
        HealthResourceBuilding.buildRedHeartsTextures(serverPlayer, playerVars);
    }
    public void setPoisonedType(CustomHeartType customHeartType){
        if (playerVars.resources.POISONED_HEARTS == customHeartType) return;
        playerVars.resources.POISONED_HEARTS = customHeartType;
        HealthResourceBuilding.buildPoisonedHeartsTextures(serverPlayer, playerVars);
    }
    public void setWitheredType(CustomHeartType customHeartType){
        if (playerVars.resources.WITHERED_HEARTS == customHeartType) return;
        playerVars.resources.WITHERED_HEARTS = customHeartType;
        HealthResourceBuilding.buildWitheredHeartsTextures(serverPlayer, playerVars);
    }
    public void setFrozenType(CustomHeartType customHeartType){
        if (playerVars.resources.FROZEN_HEARTS == customHeartType) return;
        playerVars.resources.FROZEN_HEARTS = customHeartType;
        HealthResourceBuilding.buildFrozenHeartsTextures(serverPlayer, playerVars);
    }
    public void setAbsorbingType(CustomHeartType customHeartType){
        if (playerVars.resources.ABSORBING_HEARTS == customHeartType) return;
        playerVars.resources.ABSORBING_HEARTS = customHeartType;
        HealthResourceBuilding.buildAbsorbingHeartsTextures(serverPlayer, playerVars);
    }
    //End auto-synced block -||

    //Non auto-synced methods. These methods must be put inside the "overrideWorkspace" were the syncing will happen.
    public void setRegenAnimationSpeed(int speed){
        if (playerVars.regen.reg_ticksPerHeart == speed) return;
        playerVars.regen.reg_ticksPerHeart = speed;
        sync();
    }
    public void setRegenAnimationCooldown(int cooldown){
        if (playerVars.regen.reg_cooldown == cooldown) return;
        playerVars.regen.reg_cooldown = cooldown;
        sync();
    }
    public void setRegenAnimationOffSetY(int offSetY){
        if (playerVars.regen.reg_yOffset == offSetY) return;
        playerVars.regen.reg_yOffset = offSetY;
        sync();
    }

    public void setMaximumSpaceBetweenRows(int space){
        if (playerVars.gui.spaceBetweenRowsMax == space) return;
        playerVars.gui.spaceBetweenRowsMax = space;
        sync();
    }
    public void setMinimumSpaceBetweenRows(int space){
        if (playerVars.gui.spaceBetweenRowsMin == space) return;
        playerVars.gui.spaceBetweenRowsMin = space;
        sync();
    }
    public void setCollapseDifferentLifeTypes(boolean collapse){
        if (playerVars.gui.collapseDifferentLifeTypes == collapse) return;
        playerVars.gui.collapseDifferentLifeTypes = collapse;
        sync();
    }

    private void setNormalLifePriority(int priority){
        if (playerVars.health.red_LifePriority == priority) return;
        playerVars.health.red_LifePriority = priority;
        sync();
    }
    private void setAbsorptionLifePriority(int priority){
        if (playerVars.health.abs_LifePriority == priority) return;
        playerVars.health.abs_LifePriority = priority;
        sync();
    }

    public void setStartX(int startX){
        if (playerVars.gui.startX == startX) return;
        playerVars.gui.startX = startX;
        sync();
    }
    public void setStartY(int startY){
        if (playerVars.gui.startY == startY) return;
        playerVars.gui.startY = startY;
        sync();
    }

    public void setIsHardcore(boolean isHardcore){
        if (playerVars.isHardcore == isHardcore) return;
        playerVars.isHardcore = isHardcore;
        sync();
    }
    public void hideEmptyHearts(boolean hide){
        if (playerVars.gui.hideEmptyHearts == hide) return;
        playerVars.gui.hideEmptyHearts = hide;
        sync();
    }

    public void overrideContainer(ResourceLocation container){
        if (playerVars.resources.container == container) return;
        playerVars.resources.container = container;
        sync();
    }
    public void overrideContainerBlinkingDamage(ResourceLocation containerBlinking){
        if (playerVars.resources.container_blinking_damage == containerBlinking) return;
        playerVars.resources.container_blinking_damage = containerBlinking;
        sync();
    }
    public void overrideContainerBlinkingHealing(ResourceLocation containerBlinking){
        if (playerVars.resources.container_blinking_healing == containerBlinking) return;
        playerVars.resources.container_blinking_healing = containerBlinking;
        sync();
    }
    public void overrideContainerHardcore(ResourceLocation containerHardcore){
        if (playerVars.resources.hardcore_container == containerHardcore) return;
        playerVars.resources.hardcore_container = containerHardcore;
        sync();
    }
    public void overrideContainerHardcoreBlinkingDamage(ResourceLocation containerHardcoreBlinking){
        if (playerVars.resources.hardcore_container_blinking_damage == containerHardcoreBlinking) return;
        playerVars.resources.hardcore_container_blinking_damage = containerHardcoreBlinking;
        sync();
    }
    public void overrideContainerHardcoreBlinkingHealing(ResourceLocation containerHardcoreBlinking){
        if (playerVars.resources.hardcore_container_blinking_healing == containerHardcoreBlinking) return;
        playerVars.resources.hardcore_container_blinking_healing = containerHardcoreBlinking;
        sync();
    }

    public void overrideFullHeart(ResourceLocation fullHeart){
        if (playerVars.resources.full_heart == fullHeart) return;
        playerVars.resources.full_heart = fullHeart;
        sync();
    }
    public void overrideFullBlinkingHeart(ResourceLocation fullHeartBlinking){
        if (playerVars.resources.full_heart_blinking_damage == fullHeartBlinking) return;
        playerVars.resources.full_heart_blinking_damage = fullHeartBlinking;
        sync();
    }
    public void overrideHalfHeart(ResourceLocation halfHeart){
        if (playerVars.resources.half_heart == halfHeart) return;
        playerVars.resources.half_heart = halfHeart;
        sync();
    }
    public void overrideHalfBlinkingHeart(ResourceLocation halfHeartBlinking){
        if (playerVars.resources.half_heart_blinking_damage == halfHeartBlinking) return;
        playerVars.resources.half_heart_blinking_damage = halfHeartBlinking;
        sync();
    }
    public void overrideHardcoreFullHeart(ResourceLocation hardcoreFullHeart){
        if (playerVars.resources.hardcore_full_heart == hardcoreFullHeart) return;
        playerVars.resources.hardcore_full_heart = hardcoreFullHeart;
        sync();
    }
    public void overrideHardcoreFullBlinkingHeart(ResourceLocation hardcoreFullHeartBlinking){
        if (playerVars.resources.hardcore_full_heart_blinking_damage == hardcoreFullHeartBlinking) return;
        playerVars.resources.hardcore_full_heart_blinking_damage = hardcoreFullHeartBlinking;
        sync();
    }
    public void overrideHardcoreHalfHeart(ResourceLocation hardcoreHalfHeart){
        if (playerVars.resources.hardcore_half_heart == hardcoreHalfHeart) return;
        playerVars.resources.hardcore_half_heart = hardcoreHalfHeart;
        sync();
    }
    public void overrideHardcoreHalfBlinkingHeart(ResourceLocation hardcoreHalfHeartBlinking){
        if (playerVars.resources.hardcore_half_heart_blinking_damage == hardcoreHalfHeartBlinking) return;
        playerVars.resources.hardcore_half_heart_blinking_damage = hardcoreHalfHeartBlinking;
        sync();
    }

    public void overridePoisonedFullHeart(ResourceLocation fullHeart){
        if (playerVars.resources.poisoned_full_heart == fullHeart) return;
        playerVars.resources.poisoned_full_heart = fullHeart;
        sync();
    }
    public void overridePoisonedFullBlinkingHeart(ResourceLocation fullHeartBlinking){
        if (playerVars.resources.poisoned_full_heart_blinking_damage == fullHeartBlinking) return;
        playerVars.resources.poisoned_full_heart_blinking_damage = fullHeartBlinking;
        sync();
    }
    public void overridePoisonedHalfHeart(ResourceLocation halfHeart){
        if (playerVars.resources.poisoned_half_heart == halfHeart) return;
        playerVars.resources.poisoned_half_heart = halfHeart;
        sync();
    }
    public void overridePoisonedHalfBlinkingHeart(ResourceLocation halfHeartBlinking){
        if (playerVars.resources.poisoned_half_heart_blinking_damage == halfHeartBlinking) return;
        playerVars.resources.poisoned_half_heart_blinking_damage = halfHeartBlinking;
        sync();
    }
    public void overridePoisonedHardcoreFullHeart(ResourceLocation hardcoreFullHeart){
        if (playerVars.resources.hardcore_poisoned_full_heart == hardcoreFullHeart) return;
        playerVars.resources.hardcore_poisoned_full_heart = hardcoreFullHeart;
        sync();
    }
    public void overridePoisonedHardcoreFullBlinkingHeart(ResourceLocation hardcoreFullHeartBlinking){
        if (playerVars.resources.hardcore_poisoned_full_heart_blinking_damage == hardcoreFullHeartBlinking) return;
        playerVars.resources.hardcore_poisoned_full_heart_blinking_damage = hardcoreFullHeartBlinking;
        sync();
    }
    public void overridePoisonedHardcoreHalfHeart(ResourceLocation hardcoreHalfHeart){
        if (playerVars.resources.hardcore_poisoned_half_heart == hardcoreHalfHeart) return;
        playerVars.resources.hardcore_poisoned_half_heart = hardcoreHalfHeart;
        sync();
    }
    public void overridePoisonedHardcoreHalfBlinkingHeart(ResourceLocation hardcoreHalfHeartBlinking){
        if (playerVars.resources.hardcore_poisoned_half_heart_blinking_damage == hardcoreHalfHeartBlinking) return;
        playerVars.resources.hardcore_poisoned_half_heart_blinking_damage = hardcoreHalfHeartBlinking;
        sync();
    }

    public void overrideWitheredFullHeart(ResourceLocation fullHeart){
        if (playerVars.resources.withered_full_heart == fullHeart) return;
        playerVars.resources.withered_full_heart = fullHeart;
        sync();
    }
    public void overrideWitheredFullBlinkingHeart(ResourceLocation fullHeartBlinking){
        if (playerVars.resources.withered_full_heart_blinking_damage == fullHeartBlinking) return;
        playerVars.resources.withered_full_heart_blinking_damage = fullHeartBlinking;
        sync();
    }
    public void overrideWitheredHalfHeart(ResourceLocation halfHeart){
        if (playerVars.resources.withered_half_heart == halfHeart) return;
        playerVars.resources.withered_half_heart = halfHeart;
        sync();
    }
    public void overrideWitheredHalfBlinkingHeart(ResourceLocation halfHeartBlinking){
        if (playerVars.resources.withered_half_heart_blinking_damage == halfHeartBlinking) return;
        playerVars.resources.withered_half_heart_blinking_damage = halfHeartBlinking;
        sync();
    }
    public void overrideWitheredHardcoreFullHeart(ResourceLocation hardcoreFullHeart){
        if (playerVars.resources.hardcore_withered_full_heart == hardcoreFullHeart) return;
        playerVars.resources.hardcore_withered_full_heart = hardcoreFullHeart;
        sync();
    }
    public void overrideWitheredHardcoreFullBlinkingHeart(ResourceLocation hardcoreFullHeartBlinking){
        if (playerVars.resources.hardcore_withered_full_heart_blinking_damage == hardcoreFullHeartBlinking) return;
        playerVars.resources.hardcore_withered_full_heart_blinking_damage = hardcoreFullHeartBlinking;
        sync();
    }
    public void overrideWitheredHardcoreHalfHeart(ResourceLocation hardcoreHalfHeart){
        if (playerVars.resources.hardcore_withered_half_heart == hardcoreHalfHeart) return;
        playerVars.resources.hardcore_withered_half_heart = hardcoreHalfHeart;
        sync();
    }
    public void overrideWitheredHardcoreHalfBlinkingHeart(ResourceLocation hardcoreHalfHeartBlinking){
        if (playerVars.resources.hardcore_withered_half_heart_blinking_damage == hardcoreHalfHeartBlinking) return;
        playerVars.resources.hardcore_withered_half_heart_blinking_damage = hardcoreHalfHeartBlinking;
        sync();
    }

    public void overrideFrozenFullHeart(ResourceLocation fullHeart){
        if (playerVars.resources.frozen_full_heart == fullHeart) return;
        playerVars.resources.frozen_full_heart = fullHeart;
        sync();
    }
    public void overrideFrozenFullBlinkingHeart(ResourceLocation fullHeartBlinking){
        if (playerVars.resources.frozen_full_heart_blinking_damage == fullHeartBlinking) return;
        playerVars.resources.frozen_full_heart_blinking_damage = fullHeartBlinking;
        sync();
    }
    public void overrideFrozenHalfHeart(ResourceLocation halfHeart){
        if (playerVars.resources.frozen_half_heart == halfHeart) return;
        playerVars.resources.frozen_half_heart = halfHeart;
        sync();
    }
    public void overrideFrozenHalfHeartBlinking(ResourceLocation halfHeartBlinking){
        if (playerVars.resources.frozen_half_heart_blinking_damage == halfHeartBlinking) return;
        playerVars.resources.frozen_half_heart_blinking_damage = halfHeartBlinking;
        sync();
    }
    public void overrideFrozenHardcoreFullHeart(ResourceLocation hardcoreFullHeart){
        if (playerVars.resources.hardcore_frozen_full_heart == hardcoreFullHeart) return;
        playerVars.resources.hardcore_frozen_full_heart = hardcoreFullHeart;
        sync();
    }
    public void overrideFrozenHardcoreFullBlinkingHeart(ResourceLocation hardcoreFullHeartBlinking){
        if (playerVars.resources.hardcore_frozen_full_heart_blinking_damage == hardcoreFullHeartBlinking) return;
        playerVars.resources.hardcore_frozen_full_heart_blinking_damage = hardcoreFullHeartBlinking;
        sync();
    }
    public void overrideFrozenHardcoreHalfHeart(ResourceLocation hardcoreHalfHeart){
        if (playerVars.resources.hardcore_frozen_half_heart == hardcoreHalfHeart) return;
        playerVars.resources.hardcore_frozen_half_heart = hardcoreHalfHeart;
        sync();
    }
    public void overrideFrozenHardcoreHalfBlinkingHeart(ResourceLocation hardcoreHalfHeartBlinking){
        if (playerVars.resources.hardcore_frozen_half_heart_blinking_damage == hardcoreHalfHeartBlinking) return;
        playerVars.resources.hardcore_frozen_half_heart_blinking_damage = hardcoreHalfHeartBlinking;
        sync();
    }

    public void overrideAbsorbingFullHeart(ResourceLocation fullHeart){
        if (playerVars.resources.absorbing_full_heart == fullHeart) return;
        playerVars.resources.absorbing_full_heart = fullHeart;
        sync();
    }
    public void overrideAbsorbingHalfHeart(ResourceLocation halfHeart){
        if (playerVars.resources.absorbing_half_heart == halfHeart) return;
        playerVars.resources.absorbing_half_heart = halfHeart;
        sync();
    }
    public void overrideAbsorbingHardcoreFullHeart(ResourceLocation hardcoreFullHeart){
        if (playerVars.resources.hardcore_absorbing_full_heart == hardcoreFullHeart) return;
        playerVars.resources.hardcore_absorbing_full_heart = hardcoreFullHeart;
        sync();
    }
    public void overrideAbsorbingHardcoreHalfHeart(ResourceLocation hardcoreHalfHeart){
        if (playerVars.resources.hardcore_absorbing_half_heart == hardcoreHalfHeart) return;
        playerVars.resources.hardcore_absorbing_half_heart = hardcoreHalfHeart;
        sync();
    }

    //Reset variables to allow for other values to be used.
    public void resetAll(){
        HealthBarVariables.PlayerVariables.ResourceValues resources = playerVars.resources;
        HealthBarVariables.PlayerVariables.RegenEventValues regen = playerVars.regen;
        HealthBarVariables.PlayerVariables.GuiValues gui = playerVars.gui;
        resources.CONTAINER = null;
        resources.HEARTS = null;
        resources.POISONED_HEARTS = null;
        resources.WITHERED_HEARTS = null;
        resources.FROZEN_HEARTS = null;
        resources.ABSORBING_HEARTS = null;

        gui.hideEmptyHearts = false;
        gui.collapseDifferentLifeTypes = false;

        regen.reg_ticksPerHeart = 1;
        regen.reg_cooldown = 15;
        regen.reg_yOffset = -2;

        gui.spaceBetweenRowsMax = 10;
        gui.spaceBetweenRowsMin = 7;

        sync(); //Syncing
        HealthResourceBuilding.buildTextures(new HealthComponent(serverPlayer));
    }

    @Deprecated
    private void reset(){}

}
