package net.calca.heartdev.main.heart.render;

import net.calca.heartdev.main.heart.render.data.variables.HealthBarVariables;
import net.calca.heartdev.main.heart.types.CustomContainerType;
import net.calca.heartdev.main.heart.types.CustomHeartType;
import net.calca.heartdev.main.heart.types.TextureTypes;
import net.minecraft.client.gui.Gui;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class HealthResourceBuilding {
    public static HealthBarVariables.PlayerVariables getPlayerVariables(LivingEntity livingEntity){
        HealthBarVariables.PlayerVariables var = null;
        if (livingEntity instanceof Player player){
            var = player.getData(HealthBarVariables.PLAYER_VARIABLES);
        }
        return var;
    }
    protected static void buildResourcesWithVanilla(Gui.HeartType heartType, LivingEntity livingEntity){
        HealthBarVariables.PlayerVariables playerVars = getPlayerVariables(livingEntity);
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


    public static CustomHeartType createCustomHeartType(String modID, String heartName){
        return new CustomHeartType(
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_full.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_full_blinking.png"),
                //ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_full_blinking.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_half.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_half_blinking.png"),
                //ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_half_blinking.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_hardcore_full.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_hardcore_full_blinking.png"),
                //ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_hardcore_full_blinking.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_hardcore_half.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_hardcore_half_blinking.png")
                //ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_hardcore_half_blinking.png")
        );
    }
    /*
    public static CustomHeartType createCOMPLEXCustomHeartType(String modID, String heartName){
        return new CustomHeartType(
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_full.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_full_blinking_damage.png"),
                //ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_full_blinking_healing.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_half.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_half_blinking_damage.png"),
                //ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_half_blinking_healing.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_hardcore_full.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_hardcore_full_blinking_damage.png"),
                //ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_hardcore_full_blinking_healing.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_hardcore_half.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_hardcore_half_blinking_damage.png"),
                //ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_hardcore_half_blinking_healing.png")
        );
    }
    */
    public static CustomContainerType createSimpleCustomContainerType(String modID, String heartName){
        return new CustomContainerType(
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container_blinking.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container_blinking.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container_hardcore.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container_hardcore_blinking.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container_hardcore_blinking.png")
        );
    }
    public static CustomContainerType createComplexCustomContainerType(String modID, String heartName){
        return new CustomContainerType(
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container_blinking_damage.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container_blinking_healing.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container_hardcore.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container_hardcore_blinking_damage.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container_hardcore_blinking_healing.png")
        );
    }

    /// method use to get the heart to draw (not used for the container hearts, since they would need a different method, and I decide
    /// to simply write the code inside its rendering method)
    protected static ResourceLocation getSprite(LivingEntity livingEntity, boolean isHardcore, boolean isHalf, boolean isBlinking, boolean isAbsorption){
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
        HealthBarVariables.PlayerVariables playerVars = getPlayerVariables(livingEntity);
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
        HealthBarVariables.PlayerVariables playerVars = getPlayerVariables(livingEntity);
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
        HealthBarVariables.PlayerVariables playerVars = getPlayerVariables(livingEntity);
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
        HealthBarVariables.PlayerVariables playerVars = getPlayerVariables(livingEntity);
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


    //Set all necessary variable responsible for heart rendering
    public static void buildTextures(HealthComponent healthComponent) {
        HealthBarVariables.PlayerVariables playerVariables = healthComponent.playerVars;
        Player player = healthComponent.serverPlayer;
        HealthBarVariables.PlayerVariables.ResourceValues resources = playerVariables.resources;
        if (resources.CONTAINER != null) resources.CONTAINER.buildResources(player, playerVariables);
        else buildResourcesWithVanilla(TextureTypes.VanillaHeartTypes.CONTANER, player);

        if (resources.HEARTS != null) resources.HEARTS.buildResources(player, playerVariables);
        else buildResourcesWithVanilla(TextureTypes.VanillaHeartTypes.NORMAL, player);

        if (resources.POISONED_HEARTS != null) resources.POISONED_HEARTS.buildResources(player, playerVariables);
        else buildResourcesWithVanilla(TextureTypes.VanillaHeartTypes.POISONED, player);

        if (resources.WITHERED_HEARTS != null) resources.WITHERED_HEARTS.buildResources(player, playerVariables);
        else buildResourcesWithVanilla(TextureTypes.VanillaHeartTypes.WITHERED, player);

        if (resources.FROZEN_HEARTS != null) resources.FROZEN_HEARTS.buildResources(player, playerVariables);
        else buildResourcesWithVanilla(TextureTypes.VanillaHeartTypes.FROZEN, player);

        if (resources.ABSORBING_HEARTS != null) resources.ABSORBING_HEARTS.buildResources(player, playerVariables);
        else buildResourcesWithVanilla(TextureTypes.VanillaHeartTypes.ABSORBING, player);

        //There is no need to sync since the syncing is made inside buildResourcesWithVanilla and inside buildResources
    }

    //There is no need to sync since the syncing is made inside buildResourcesWithVanilla and inside buildResources
    protected static void buildContainerTextures(Player player, HealthBarVariables.PlayerVariables playerVariables){
        HealthBarVariables.PlayerVariables.ResourceValues resources = playerVariables.resources;
        if (resources.CONTAINER != null) resources.CONTAINER.buildResources(player, playerVariables);
        else buildResourcesWithVanilla(TextureTypes.VanillaHeartTypes.CONTANER, player);
    }
    public static void buildRedHeartsTextures(Player player, HealthBarVariables.PlayerVariables playerVariables){
        HealthBarVariables.PlayerVariables.ResourceValues resources = playerVariables.resources;
        if (resources.HEARTS != null) resources.HEARTS.buildResources(player, playerVariables);
        else buildResourcesWithVanilla(TextureTypes.VanillaHeartTypes.NORMAL, player);
    }
    protected static void buildPoisonedHeartsTextures(Player player, HealthBarVariables.PlayerVariables playerVariables){
        HealthBarVariables.PlayerVariables.ResourceValues resources = playerVariables.resources;
        if (resources.POISONED_HEARTS != null) resources.POISONED_HEARTS.buildResources(player, playerVariables);
        else buildResourcesWithVanilla(TextureTypes.VanillaHeartTypes.POISONED, player);
    }
    protected static void buildWitheredHeartsTextures(Player player, HealthBarVariables.PlayerVariables playerVariables){
        HealthBarVariables.PlayerVariables.ResourceValues resources = playerVariables.resources;
        if (resources.WITHERED_HEARTS != null) resources.WITHERED_HEARTS.buildResources(player, playerVariables);
        else buildResourcesWithVanilla(TextureTypes.VanillaHeartTypes.WITHERED, player);
    }
    protected static void buildFrozenHeartsTextures(Player player, HealthBarVariables.PlayerVariables playerVariables){
        HealthBarVariables.PlayerVariables.ResourceValues resources = playerVariables.resources;
        if (resources.FROZEN_HEARTS != null) resources.FROZEN_HEARTS.buildResources(player, playerVariables);
        else buildResourcesWithVanilla(TextureTypes.VanillaHeartTypes.FROZEN, player);
    }
    protected static void buildAbsorbingHeartsTextures(Player player, HealthBarVariables.PlayerVariables playerVariables){
        HealthBarVariables.PlayerVariables.ResourceValues resources = playerVariables.resources;
        if (resources.ABSORBING_HEARTS != null) resources.ABSORBING_HEARTS.buildResources(player, playerVariables);
        else buildResourcesWithVanilla(TextureTypes.VanillaHeartTypes.ABSORBING, player);
    }
}
