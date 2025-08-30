package net.calca.heartdev.main.heart.render.data.variables;

import net.calca.heartdev.main.heart.types.CustomContainerType;
import net.calca.heartdev.main.heart.types.CustomHeartType;
import net.minecraft.nbt.CompoundTag;

public class NBTHealper {

    // --- CustomContainerType ---
    public static void putCustomContainer(CompoundTag tag, String key, CustomContainerType container) {
        if (container == null) return;
        tag.put(key, container.toNbt()); // toNbt() lo definiamo nel record (vedi sotto)
    }

    public static CustomContainerType getCustomContainer(CompoundTag tag, String key) {
        if (!tag.contains(key)) return null;
        try {
            return CustomContainerType.fromNbt(tag.getCompound(key));
        } catch (Exception e) {
            // log se vuoi
            return null;
        }
    }

    // --- CustomHeartType ---
    public static void putCustomHeartType(CompoundTag tag, String key, CustomHeartType hearts) {
        if (hearts == null) return;
        tag.put(key, hearts.toNbt());
    }

    public static CustomHeartType getCustomHeartType(CompoundTag tag, String key) {
        if (!tag.contains(key)) return null;
        try {
            return CustomHeartType.fromNbt(tag.getCompound(key));
        } catch (Exception e) {
            // log se vuoi
            return null;
        }
    }
}
