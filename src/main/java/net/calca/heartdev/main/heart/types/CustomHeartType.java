package net.calca.heartdev.main.heart.types;

import net.calca.heartdev.main.heart.render.data.variables.HealthBarVariables;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public record CustomHeartType(
        ResourceLocation full,
        ResourceLocation fullBlinkingDamage,
        //ResourceLocation fullBlinkingHealing,
        ResourceLocation half,
        ResourceLocation halfBlinkingDamage,
        //ResourceLocation halfBlinkingHealing,
        ResourceLocation hardcoreFull,
        ResourceLocation hardcoreFullBlinkingDamage,
        //ResourceLocation hardcoreFullBlinkingHealing,
        ResourceLocation hardcoreHalf,
        ResourceLocation hardcoreHalfBlinkingDamage
        //ResourceLocation hardcoreHalfBlinkingHealing
) {

    public void buildResources(LivingEntity LivingEntity, HealthBarVariables.PlayerVariables playerVariables){
        HealthBarVariables.PlayerVariables.ResourceValues resources = playerVariables.resources;
        resources.full_heart = this.full;
        resources.full_heart_blinking_damage = this.fullBlinkingDamage;
        //resources.full_heart_blinking_healing = this.fullBlinkingHealing;
        resources.half_heart = this.half;
        resources.half_heart_blinking_damage = this.halfBlinkingDamage;
        //resources.half_heart_blinking_healing = this.halfBlinkingHealing;
        resources.hardcore_full_heart = this.hardcoreFull;
        resources.hardcore_full_heart_blinking_damage = this.hardcoreFullBlinkingDamage;
        //resources.hardcore_full_heart_blinking_healing = this.hardcoreFullBlinkingHealing;
        resources.hardcore_half_heart = this.hardcoreHalf;
        resources.hardcore_half_heart_blinking_damage = this.hardcoreHalfBlinkingDamage;
        //resources.hardcore_half_heart_blinking_healing = this.hardcoreHalfBlinkingHealing;
        playerVariables.syncPlayerVariables(LivingEntity);
    }

    public CompoundTag toNbt() {
        CompoundTag t = new CompoundTag();
        t.putString("hearttype_full", full == null ? "" : full.toString());
        t.putString("hearttype_fullBlinkingDamage", fullBlinkingDamage == null ? "" : fullBlinkingDamage.toString());
        t.putString("hearttype_half", half == null ? "" : half.toString());
        t.putString("hearttype_halfBlinkingDamage", halfBlinkingDamage == null ? "" : halfBlinkingDamage.toString());
        t.putString("hearttype_hardcoreFull", hardcoreFull == null ? "" : hardcoreFull.toString());
        t.putString("hearttype_hardcoreFullBlinkingDamage", hardcoreFullBlinkingDamage == null ? "" : hardcoreFullBlinkingDamage.toString());
        t.putString("hearttype_hardcoreHalf", hardcoreHalf == null ? "" : hardcoreHalf.toString());
        t.putString("hearttype_hardcoreHalfBlinkingDamage", hardcoreHalfBlinkingDamage == null ? "" : hardcoreHalfBlinkingDamage.toString());
        return t;
    }

    public static CustomHeartType fromNbt(CompoundTag t) {
        if (t == null) return null;
        try {
            ResourceLocation full = optRes(t, "hearttype_full");
            ResourceLocation fullBlinking = optRes(t, "hearttype_fullBlinkingDamage");
            ResourceLocation half = optRes(t, "hearttype_half");
            ResourceLocation halfBlinking = optRes(t, "hearttype_halfBlinkingDamage");
            ResourceLocation hardcoreFull = optRes(t, "hearttype_hardcoreFull");
            ResourceLocation hardcoreFullBlinking = optRes(t, "hearttype_hardcoreFullBlinkingDamage");
            ResourceLocation hardcoreHalf = optRes(t, "hearttype_hardcoreHalf");
            ResourceLocation hardcoreHalfBlinking = optRes(t, "hearttype_hardcoreHalfBlinkingDamage");
            return new CustomHeartType(full, fullBlinking, half, halfBlinking, hardcoreFull, hardcoreFullBlinking, hardcoreHalf, hardcoreHalfBlinking);
        } catch (Exception e) {
            return null;
        }
    }

    private static ResourceLocation optRes(CompoundTag t, String key) {
        String s = t.getString(key);
        return (s == null || s.isEmpty()) ? null : ResourceLocation.parse(s);
    }

}

