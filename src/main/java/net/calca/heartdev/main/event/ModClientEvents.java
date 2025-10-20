package net.calca.heartdev.main.event;

import net.calca.heartdev.HeartDev;
import net.calca.heartdev.main.heart.render.HealthBarRender;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;

@EventBusSubscriber(modid = HeartDev.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value =  Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onRenderHealth(RenderGuiLayerEvent.Pre event) {
            HealthBarRender.HEALTH_INSTANCE.shouldRenderHealthBar(event);

    }

}
