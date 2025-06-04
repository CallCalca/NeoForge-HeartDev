package net.calca.heartdev.main.event;

import net.calca.heartdev.HeartDev;
import net.calca.heartdev.main.effect.ModEffects;
import net.calca.heartdev.main.heart.render.HealthBar;
import net.calca.heartdev.main.heart.render.HealthBarVariables;
import net.calca.heartdev.main.heart.render.HealthComponent;
import net.calca.heartdev.main.heart.types.HealthTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

import java.util.Objects;

@EventBusSubscriber(modid = HeartDev.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value =  Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onRenderHealth(RenderGuiLayerEvent.Pre event) {
/*
        HealthBar.HEALTH_INSTANCE.shouldRenderHealthBar(event, livingEntity -> {
            HealthBar.PreSets.PRESETS_ISTANCE.activateOrangeEffect();
            HealthBar.PreSets.PRESETS_ISTANCE.activateYellowEffect();
            HealthBar.PreSets.PRESETS_ISTANCE.activateMagentaEffect();
            HealthBar.PreSets.PRESETS_ISTANCE.activateLightBlueEffect();
            HealthBar.PreSets.PRESETS_ISTANCE.activateBlueEffect();
            HealthBar.PreSets.PRESETS_ISTANCE.activateGreenEffect();
            HealthBar.PreSets.PRESETS_ISTANCE.activatePurpleEffect();
            HealthBar.HEALTH_INSTANCE.renderHealthBar(event, () -> {

            });
        });

        */

    }

}
