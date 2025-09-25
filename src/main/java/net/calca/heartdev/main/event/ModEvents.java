package net.calca.heartdev.main.event;

import net.calca.heartdev.HeartDev;
import net.calca.heartdev.main.heart.render.HealthBar;
import net.calca.heartdev.main.heart.render.HealthBarGlobalVariables;
import net.calca.heartdev.main.heart.render.HealthComponent;
import net.calca.heartdev.main.heart.render.data.variables.HealthBarPersonalVariables;
import net.calca.heartdev.main.heart.types.HealthTypes;
import net.minecraft.client.gui.Gui;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.ArrayList;

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
            HealthBarPersonalVariables.PlayerVariables playerVariables = HealthBarGlobalVariables.getPlayerVariables(serverPlayer);
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
