package net.calca.heartdev.main.event;

import net.calca.heartdev.HeartDev;
import net.calca.heartdev.main.effect.ModEffects;
import net.calca.heartdev.main.heart.render.HealthBar;
import net.calca.heartdev.main.heart.render.HealthBarVariables;
import net.calca.heartdev.main.heart.render.HealthComponent;
import net.calca.heartdev.main.heart.types.CustomContainerType;
import net.calca.heartdev.main.heart.types.CustomHeartType;
import net.calca.heartdev.main.heart.types.HealthTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffects;
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
        HealthBar.HEALTH_INSTANCE.shouldRenderHealthBar(event, livingEntity -> {
            if (livingEntity.isInWater()) {
                HealthComponent.setWitheredType(HealthTypes.ModdedTextures.BLUE_HEARTS);
                HealthComponent.setRegenAnimationSpeed(2);
                HealthComponent.setRegenAnimationOffSetY(-1);


                HealthBar.HEALTH_INSTANCE.renderHealthBar(event, () -> {
                    HealthComponent.overrideHalfHeart(HealthTypes.ModdedTextures.BLUE_HEARTS.half());
                    HealthComponent.overrideContainerBlinking(HealthTypes.ModdedTextures.MAGENTA_HEARTS.fullBlinkingDamage());
                });
            }
        });

    }

}
