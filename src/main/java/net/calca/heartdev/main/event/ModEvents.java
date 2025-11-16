package net.calca.heartdev.main.event;

import net.calca.heartdev.HeartDev;
import net.calca.heartdev.main.heart.render.HealthComponent;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

// Done with the help of https://github.com/CoFH/CoFHCore/blob/1.19.x/src/main/java/cofh/core/event/AreaEffectEvents.java
// Don't be a jerk License
@EventBusSubscriber(modid = HeartDev.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents{


    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event){
        /*
    }
        if (event.getEntity() instanceof Player player){
            if (player.level().isClientSide) return;

            HealthComponent healthComponent = new HealthComponent(player);

            healthComponent.resetAll();
            healthComponent.activateRendering(true);
        }

         */

    }


    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event){
        /*
        if (event.getEntity() instanceof Player player){
            if (player.level().isClientSide) return;

            HealthComponent healthComponent = new HealthComponent(player);
            healthComponent.setCollapseDifferentLifeTypes(true);

        }
            */
    }



}
