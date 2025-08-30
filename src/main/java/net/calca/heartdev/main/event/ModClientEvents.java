package net.calca.heartdev.main.event;

import net.calca.heartdev.HeartDev;
import net.calca.heartdev.main.heart.render.HealthBar;
import net.calca.heartdev.main.heart.render.HealthBarGlobalVariables;
import net.calca.heartdev.main.heart.render.HealthComponent;
import net.calca.heartdev.main.heart.types.HealthTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;

import java.util.Objects;

@EventBusSubscriber(modid = HeartDev.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value =  Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onRenderHealth(RenderGuiLayerEvent.Pre event) {


            HealthBar.HEALTH_INSTANCE.shouldRenderHealthBar(event);





    }

}
