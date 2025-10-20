package net.calca.heartdev.main.heart.types;

import net.calca.heartdev.HeartDev;
import net.minecraft.client.gui.Gui;

import static net.calca.heartdev.main.heart.render.HealthBarResourceBuilding.createCustomHeartType;

public class TextureTypes {

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
}
