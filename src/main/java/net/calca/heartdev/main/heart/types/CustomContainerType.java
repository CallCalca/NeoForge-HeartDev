package net.calca.heartdev.main.heart.types;

import net.calca.heartdev.main.heart.render.HealthBarVariables;
import net.minecraft.resources.ResourceLocation;

public record CustomContainerType(
        ResourceLocation container,
        ResourceLocation containerBlinkingDamage,
        ResourceLocation containerBlinkingHealing,
        ResourceLocation containerHardcore,
        ResourceLocation containerHardcoreBlinkingDamage,
        ResourceLocation containerHardcoreBlinkingHealing
) {

    public void buildResources(){
        HealthBarVariables.container = this.container;
        HealthBarVariables.container_blinking_damage = this.containerBlinkingDamage;
        HealthBarVariables.container_blinking_healing = this.containerBlinkingHealing;
        HealthBarVariables.hardcore_container = this.containerHardcore;
        HealthBarVariables.hardcore_container_blinking_damage = this.containerBlinkingDamage;
        HealthBarVariables.hardcore_container_blinking_healing = this.containerBlinkingHealing;
    }

}
