package net.calca.heartdev.main.heart.types;

import net.calca.heartdev.HeartDev;
import net.minecraft.client.gui.Gui;
import net.minecraft.resources.ResourceLocation;

public class HealthTypes {

    public static class VanillaHeartTypes {
        public static Gui.HeartType CONTANER = Gui.HeartType.CONTAINER;
        public static Gui.HeartType NORMAL = Gui.HeartType.NORMAL;
        public static Gui.HeartType POISONED = Gui.HeartType.POISIONED;
        public static Gui.HeartType WITHERED = Gui.HeartType.WITHERED;
        public static Gui.HeartType FROZEN = Gui.HeartType.FROZEN;
        public static Gui.HeartType ABSORBING = Gui.HeartType.ABSORBING;

    }

    public static class ModdedTextures {
        public static final CustomHeartType ORANGE_HEARTS = createCustomHeartType(HeartDev.MOD_ID, "orange");
        public static final CustomHeartType YELLOW_HEARTS = createCustomHeartType(HeartDev.MOD_ID, "yellow");
        public static final CustomHeartType GREEN_HEARTS = createCustomHeartType(HeartDev.MOD_ID, "green");
        public static final CustomHeartType LIGHT_BLUE_HEARTS = createCustomHeartType(HeartDev.MOD_ID, "light_blue");
        public static final CustomHeartType BLUE_HEARTS = createCustomHeartType(HeartDev.MOD_ID, "blue");
        public static final CustomHeartType PURPLE_HEARTS = createCustomHeartType(HeartDev.MOD_ID, "purple");
        public static final CustomHeartType MAGENTA_HEARTS = createCustomHeartType(HeartDev.MOD_ID, "magenta");
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
    public static CustomContainerType createNORMALCustomContainerType(String modID, String heartName){
        return new CustomContainerType(
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container_blinking.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container_blinking.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container_hardcore.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container_hardcore_blinking.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container_hardcore_blinking.png")
        );
    }
    public static CustomContainerType createCOMPLEXCustomContainerType(String modID, String heartName){
        return new CustomContainerType(
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container_blinking_damage.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container_blinking_healing.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container_hardcore.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container_hardcore_blinking_damage.png"),
                ResourceLocation.fromNamespaceAndPath(modID, "textures/gui/sprites/hud/heart/" + heartName + "_container_hardcore_blinking_healing.png")
        );
    }
}
