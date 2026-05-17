package net.calca.heartdev.main.heart.types;

import net.calca.heartdev.main.heart.render.HealthBarVariables;
import net.minecraft.resources.ResourceLocation;

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

    public void buildResources(){
        HealthBarVariables.full_heart = this.full;
        HealthBarVariables.full_heart_blinking_damage = this.fullBlinkingDamage;
        //HealthBarVariables.full_heart_blinking_healing = this.fullBlinkingHealing;
        HealthBarVariables.half_heart = this.half;
        HealthBarVariables.half_heart_blinking_damage = this.halfBlinkingDamage;
        //HealthBarVariables.half_heart_blinking_healing = this.halfBlinkingHealing;
        HealthBarVariables.hardcore_full_heart = this.hardcoreFull;
        HealthBarVariables.hardcore_full_heart_blinking_damage = this.hardcoreFullBlinkingDamage;
        //HealthBarVariables.hardcore_full_heart_blinking_healing = this.hardcoreFullBlinkingHealing;
        HealthBarVariables.hardcore_half_heart = this.hardcoreHalf;
        HealthBarVariables.hardcore_half_heart_blinking_damage = this.hardcoreHalfBlinkingDamage;
        //HealthBarVariables.hardcore_half_heart_blinking_healing = this.hardcoreHalfBlinkingHealing;
    }

}

