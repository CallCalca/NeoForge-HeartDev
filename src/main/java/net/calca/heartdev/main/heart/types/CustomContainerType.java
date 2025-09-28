package net.calca.heartdev.main.heart.types;

import net.calca.heartdev.main.heart.render.data.variables.HealthBarPersonalVariables;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public record CustomContainerType(
        ResourceLocation container,
        ResourceLocation containerBlinkingDamage,
        ResourceLocation containerBlinkingHealing,
        ResourceLocation containerHardcore,
        ResourceLocation containerHardcoreBlinkingDamage,
        ResourceLocation containerHardcoreBlinkingHealing
) {

    public void buildResources(Player player, HealthBarPersonalVariables.PlayerVariables playerVariables){
        HealthBarPersonalVariables.PlayerVariables.ResourceValues resources = playerVariables.resources;
        resources.container = this.container;
        resources.container_blinking_damage = this.containerBlinkingDamage;
        resources.container_blinking_healing = this.containerBlinkingHealing;
        resources.hardcore_container = this.containerHardcore;
        resources.hardcore_container_blinking_damage = this.containerBlinkingDamage;
        resources.hardcore_container_blinking_healing = this.containerBlinkingHealing;
        playerVariables.syncPlayerVariables(player);
    }
    public CompoundTag toNbt() {
        CompoundTag t = new CompoundTag();
        t.putString("containertype_container", container() == null ? "" : container().toString());
        t.putString("containertype_containerBlinkingDamage", containerBlinkingDamage() == null ? "" : containerBlinkingDamage().toString());
        t.putString("containertype_containerBlinkingHealing", containerBlinkingHealing() == null ? "" : containerBlinkingHealing().toString());
        t.putString("containertype_containerHardcore", containerHardcore() == null ? "" : containerHardcore().toString());
        t.putString("containertype_containerHardcoreBlinkingDamage", containerHardcoreBlinkingDamage() == null ? "" : containerHardcoreBlinkingDamage().toString());
        t.putString("containertype_containerHardcoreBlinkingHealing", containerHardcoreBlinkingHealing() == null ? "" : containerHardcoreBlinkingHealing().toString());
        return t;
    }

    public static CustomContainerType fromNbt(CompoundTag t) {
        if (t == null) return null;
        try {
            ResourceLocation c = optRes(t, "containertype_container");
            ResourceLocation cbd = optRes(t, "containertype_containerBlinkingDamage");
            ResourceLocation cbh = optRes(t, "containertype_containerBlinkingHealing");
            ResourceLocation ch = optRes(t, "containertype_containerHardcore");
            ResourceLocation chbd = optRes(t, "containertype_containerHardcoreBlinkingDamage");
            ResourceLocation chbh = optRes(t, "containertype_containerHardcoreBlinkingHealing");
            return new CustomContainerType(c, cbd, cbh, ch, chbd, chbh);
        } catch (Exception e) {
            return null;
        }
    }

    private static ResourceLocation optRes(CompoundTag t, String key) {
        String s = t.getString(key);
        return (s == null || s.isEmpty()) ? null : ResourceLocation.parse(s);
    }

}
