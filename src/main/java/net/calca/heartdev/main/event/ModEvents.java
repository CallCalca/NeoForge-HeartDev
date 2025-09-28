package net.calca.heartdev.main.event;

import net.calca.heartdev.HeartDev;
import net.calca.heartdev.main.heart.render.HealthBar;
import net.calca.heartdev.main.heart.render.HealthBarHelper;
import net.calca.heartdev.main.heart.render.data.variables.HealthBarPersonalVariables;
import net.minecraft.server.level.ServerPlayer;
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
        if (event.getEntity() instanceof ServerPlayer serverPlayer){
            HealthBarPersonalVariables.PlayerVariables playerVariables = HealthBarGlobalVariables.getPlayerVariables(serverPlayer);
            HealthComponent healthComponent = new HealthComponent(serverPlayer);
            healthComponent.setHeartType(HealthTypes.ModdedTextures.ORANGE_HEARTS);
            HealthBar.HEALTH_INSTANCE.buildTextures(serverPlayer, playerVariables);
        }

         */
        if (event.getEntity() instanceof ServerPlayer serverPlayer){
            HealthBarPersonalVariables.PlayerVariables playerVariables = HealthBarHelper.getPlayerVariables(serverPlayer);
            HealthBar.HEALTH_INSTANCE.reset(serverPlayer, playerVariables);
            HealthBar.HEALTH_INSTANCE.buildTextures(serverPlayer, playerVariables);
        }
    }


    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event){
        if (event.getEntity() instanceof ServerPlayer serverPlayer){
            HealthBar.HEALTH_INSTANCE.setVariables(serverPlayer);
        }

    }

}
