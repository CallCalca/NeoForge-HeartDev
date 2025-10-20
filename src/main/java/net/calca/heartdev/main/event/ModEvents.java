package net.calca.heartdev.main.event;

import net.calca.heartdev.HeartDev;
import net.calca.heartdev.main.heart.render.HealthBarRender;
import net.calca.heartdev.main.heart.render.HealthBarResourceBuilding;
import net.calca.heartdev.main.heart.render.HealthComponent;
import net.calca.heartdev.main.heart.render.data.variables.HealthBarVariables;
import net.calca.heartdev.main.heart.types.TextureTypes;
import net.minecraft.world.effect.MobEffects;
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
        if (!(event.getEntity() instanceof  Player player)) {
            return;
        }

        HealthComponent healthComponent = new HealthComponent(player);

        healthComponent.resetAll();
            HealthBarResourceBuilding.buildTextures(player, healthComponent.playerVars);

    }


    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event){

        if (event.getEntity() instanceof Player player){
            if (player.level().isClientSide) return;
            HealthComponent healthComponent = new HealthComponent(player);

            if (player.hasEffect(MobEffects.BAD_OMEN)){
                healthComponent.variablesRefresh();
                healthComponent.activateRendering(true);
                healthComponent.setHeartType(TextureTypes.ModdedTextures.MAGENTA_HEARTS);
                healthComponent.overrideHalfHeart(TextureTypes.ModdedTextures.ORANGE_HEARTS.half());
                healthComponent.setRegenAnimationOffSetY(3);
            }else{
                healthComponent.activateRendering(false);
            }
        }
    }

}
