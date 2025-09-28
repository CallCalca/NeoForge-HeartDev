package net.calca.heartdev.main.heart.render.data.variables;


import net.calca.heartdev.HeartDev;
import net.calca.heartdev.main.heart.types.CustomContainerType;
import net.calca.heartdev.main.heart.types.CustomHeartType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public final class HealthBarPersonalVariables {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, HeartDev.MOD_ID);
    public static final Supplier<AttachmentType<PlayerVariables>> PLAYER_VARIABLES = ATTACHMENT_TYPES.register("player_variables", () -> AttachmentType.serializable(() -> new PlayerVariables()).build());

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        HeartDev.addNetworkMessage(PlayerVariablesSyncMessage.TYPE, PlayerVariablesSyncMessage.STREAM_CODEC, PlayerVariablesSyncMessage::handleData);
    }

    @EventBusSubscriber
    public static class EventBusVariableHandlers {
        @SubscribeEvent
        public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
            if (event.getEntity() instanceof ServerPlayer player)
                player.getData(PLAYER_VARIABLES).syncPlayerVariables(event.getEntity());
        }

        @SubscribeEvent
        public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
            if (event.getEntity() instanceof ServerPlayer player)
                player.getData(PLAYER_VARIABLES).syncPlayerVariables(event.getEntity());
        }

        @SubscribeEvent
        public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
            if (event.getEntity() instanceof ServerPlayer player)
                player.getData(PLAYER_VARIABLES).syncPlayerVariables(event.getEntity());
        }

        //Player variables - CLONING
        @SubscribeEvent
        public static void clonePlayer(PlayerEvent.Clone event) {
            PlayerVariables original = event.getOriginal().getData(PLAYER_VARIABLES);
            PlayerVariables clone = new PlayerVariables();
            if (!event.isWasDeath()) {
                clone.player_takingDamage        =       original.player_takingDamage;
                clone.player_takingRegen        =       original.player_takingRegen;
                clone.player_blinking        =       original.player_blinking;
                clone.isHardcore        =       original.isHardcore;

                clone.health.red_LifeNumber = original.health.red_LifeNumber;
                clone.health.abs_LifeNumber = original.health.abs_LifeNumber;
                clone.health.extraLifeNumbers = original.health.extraLifeNumbers;
                clone.health.tot_Amount = original.health.tot_Amount;
                clone.health.partialHealthAmount = original.health.partialHealthAmount;
                clone.health.red_Amount = original.health.red_Amount;
                clone.health.abs_Amount = original.health.abs_Amount;
                clone.health.tot_MaxAmount = original.health.tot_MaxAmount;
                clone.health.partialMaxHealthAmount = original.health.partialMaxHealthAmount;
                clone.health.red_MaxAmount = original.health.red_MaxAmount;
                clone.health.abs_MaxAmount = original.health.abs_MaxAmount;
                clone.health.red_FullHearts = original.health.red_FullHearts;
                clone.health.red_Hearts = original.health.red_Hearts;
                clone.health.red_MaxHearts = original.health.red_MaxHearts;
                clone.health.red_HasHalf = original.health.red_HasHalf;
                clone.health.abs_HasHalf = original.health.abs_HasHalf;
                clone.health.tot_Hearts = original.health.tot_Hearts;
                clone.health.tot_MaxHearts = original.health.tot_MaxHearts;

                clone.gui.lastHealthTime = original.gui.lastHealthTime;
                clone.gui.healthBlinkTime = original.gui.healthBlinkTime;
                clone.gui.regenerating = original.gui.regenerating;
                clone.gui.displayHealth = original.gui.displayHealth;
                clone.gui.lastHealth = original.gui.lastHealth;
                clone.gui.lastTickCount = original.gui.lastTickCount;
                clone.gui.regenStartTick = original.gui.regenStartTick;
                clone.gui.regenIndex = original.gui.regenIndex;
                clone.gui.blinkStartTick = original.gui.blinkStartTick;

                clone.gui.barRows = original.gui.barRows;
                clone.gui.barRowsMenus2 = original.gui.barRowsMenus2;
                clone.gui.spacing = original.gui.spacing;
                clone.gui.height = original.gui.height;
                clone.gui.width = original.gui.width;
                clone.gui.tickCount = original.gui.tickCount;
                clone.gui.nowTick = original.gui.nowTick;
                clone.gui.startY = original.gui.startY;
                clone.gui.startX = original.gui.startX;
                clone.gui.spaceBetweenRowsMax = original.gui.spaceBetweenRowsMax;
                clone.gui.spaceBetweenRowsMin = original.gui.spaceBetweenRowsMin;
                clone.gui.abs_Slots = original.gui.abs_Slots;
                clone.gui.hideEmptyHearts = original.gui.hideEmptyHearts;
                clone.gui.collapseDifferentLifeTypes = original.gui.collapseDifferentLifeTypes;
                clone.gui.absorptionSlotsList = original.gui.absorptionSlotsList;

                clone.regen.reg_ticksPerHeart = original.regen.reg_ticksPerHeart;
                clone.regen.reg_visibleHearts = original.regen.reg_visibleHearts;
                clone.regen.reg_ticksSinceStart = original.regen.reg_ticksSinceStart;
                clone.regen.reg_waveDuration = original.regen.reg_waveDuration;
                clone.regen.reg_cooldown = original.regen.reg_cooldown;
                clone.regen.reg_yOffset = original.regen.reg_yOffset;

                clone.resources.CONTAINER = original.resources.CONTAINER;
                clone.resources.HEARTS = original.resources.HEARTS;
                clone.resources.POISONED_HEARTS = original.resources.POISONED_HEARTS;
                clone.resources.WITHERED_HEARTS = original.resources.WITHERED_HEARTS;
                clone.resources.FROZEN_HEARTS = original.resources.FROZEN_HEARTS;
                clone.resources.ABSORBING_HEARTS = original.resources.ABSORBING_HEARTS;
                clone.resources.container = original.resources.container;
                clone.resources.container_blinking_damage = original.resources.container_blinking_damage;
                clone.resources.container_blinking_healing = original.resources.container_blinking_healing;
                clone.resources.hardcore_container = original.resources.hardcore_container;
                clone.resources.hardcore_container_blinking_damage = original.resources.hardcore_container_blinking_damage;
                clone.resources.hardcore_container_blinking_healing = original.resources.hardcore_container_blinking_healing;
                clone.resources.full_heart = original.resources.full_heart;
                clone.resources.full_heart_blinking_damage = original.resources.full_heart_blinking_damage;
                clone.resources.half_heart = original.resources.half_heart;
                clone.resources.half_heart_blinking_damage = original.resources.half_heart_blinking_damage;
                clone.resources.hardcore_full_heart = original.resources.hardcore_full_heart;
                clone.resources.hardcore_full_heart_blinking_damage = original.resources.hardcore_full_heart_blinking_damage;
                clone.resources.hardcore_half_heart = original.resources.hardcore_half_heart;
                clone.resources.hardcore_half_heart_blinking_damage = original.resources.hardcore_half_heart_blinking_damage;
                clone.resources.poisoned_full_heart = original.resources.poisoned_full_heart;
                clone.resources.poisoned_full_heart_blinking_damage = original.resources.poisoned_full_heart_blinking_damage;
                clone.resources.poisoned_half_heart = original.resources.poisoned_half_heart;
                clone.resources.poisoned_half_heart_blinking_damage = original.resources.poisoned_half_heart_blinking_damage;
                clone.resources.hardcore_poisoned_full_heart = original.resources.hardcore_poisoned_full_heart;
                clone.resources.hardcore_poisoned_full_heart_blinking_damage = original.resources.hardcore_poisoned_full_heart_blinking_damage;
                clone.resources.hardcore_poisoned_half_heart = original.resources.hardcore_poisoned_half_heart;
                clone.resources.hardcore_poisoned_half_heart_blinking_damage = original.resources.hardcore_poisoned_half_heart_blinking_damage;
                clone.resources.withered_full_heart = original.resources.withered_full_heart;
                clone.resources.withered_full_heart_blinking_damage = original.resources.withered_full_heart_blinking_damage;
                clone.resources.withered_half_heart = original.resources.withered_half_heart;
                clone.resources.withered_half_heart_blinking_damage = original.resources.withered_half_heart_blinking_damage;
                clone.resources.hardcore_withered_full_heart = original.resources.hardcore_withered_full_heart;
                clone.resources.hardcore_withered_full_heart_blinking_damage = original.resources.hardcore_withered_full_heart_blinking_damage;
                clone.resources.hardcore_withered_half_heart = original.resources.hardcore_withered_half_heart;
                clone.resources.hardcore_withered_half_heart_blinking_damage = original.resources.hardcore_withered_half_heart_blinking_damage;
                clone.resources.frozen_full_heart = original.resources.frozen_full_heart;
                clone.resources.frozen_full_heart_blinking_damage = original.resources.frozen_full_heart_blinking_damage;
                clone.resources.frozen_half_heart = original.resources.frozen_half_heart;
                clone.resources.frozen_half_heart_blinking_damage = original.resources.frozen_half_heart_blinking_damage;
                clone.resources.hardcore_frozen_full_heart = original.resources.hardcore_frozen_full_heart;
                clone.resources.hardcore_frozen_full_heart_blinking_damage = original.resources.hardcore_frozen_full_heart_blinking_damage;
                clone.resources.hardcore_frozen_half_heart = original.resources.hardcore_frozen_half_heart;
                clone.resources.hardcore_frozen_half_heart_blinking_damage = original.resources.hardcore_frozen_half_heart_blinking_damage;
                clone.resources.absorbing_full_heart = original.resources.absorbing_full_heart;
                clone.resources.absorbing_half_heart = original.resources.absorbing_half_heart;
                clone.resources.hardcore_absorbing_full_heart = original.resources.hardcore_absorbing_full_heart;
                clone.resources.hardcore_absorbing_half_heart = original.resources.hardcore_absorbing_half_heart;

            }
            event.getEntity().setData(PLAYER_VARIABLES, clone);
        }
    }

    //Player variables - START
    public static class PlayerVariables implements INBTSerializable<CompoundTag> {
        //MISC variables
        public boolean player_takingDamage;
        public boolean player_takingRegen;
        public boolean player_blinking;
        public boolean isHardcore;

        //Health variables
        public class HealthValues {
            public int red_LifeNumber = -2;
            public int abs_LifeNumber = -1;
            public int[] extraLifeNumbers = {};

            public int tot_Amount = 0;
            public int partialHealthAmount = 0;
            public int red_Amount = 0;
            public int abs_Amount = 0;

            public int tot_MaxAmount = 0;
            public int partialMaxHealthAmount = 0;
            public int red_MaxAmount = 0;
            public int abs_MaxAmount = 0;

            public int red_FullHearts;
            public int red_Hearts;
            public int red_MaxHearts;
            public boolean red_HasHalf;
            public boolean abs_HasHalf;
            public int tot_Hearts;
            public int tot_MaxHearts;
        }
        //Gui Variables
        public class GuiValues {
            public long lastHealthTime = 0; // UNKOWN
            public long healthBlinkTime = 0; // UNKOWN
            public int regenerating = 0; // UNKOWN
            public int displayHealth = 0; // UNKOWN
            public int lastHealth = -1; // UNKOWN
            public long lastTickCount = -1; // UNKOWN
            public long regenStartTick = -9999L;  // UNKOWN
            public int regenIndex = -1; // UNKOWN
            public long blinkStartTick = 0; // UNKOWN

            public int barRows;
            public int barRowsMenus2;
            public int spacing;
            public int height = -9999;
            public int width = -9999;
            public long tickCount;
            public long nowTick;
            public int startY = -9999;
            public int startX = -9999;
            public int spaceBetweenRowsMax = 10;
            public int spaceBetweenRowsMin = 7;
            public int abs_Slots;
            public boolean hideEmptyHearts = false;
            public boolean collapseDifferentLifeTypes = false;
            public Set<Integer> absorptionSlotsList = new HashSet<>(); //List to check for absorption hearts UNKOWN
        }
        //Variables responsible for rendering regen event
        public class RegenEventValues {
            public int reg_ticksPerHeart = 1;
            public int reg_visibleHearts;
            public int reg_ticksSinceStart;
            public int reg_waveDuration = 1;
            public int reg_cooldown = 15;
            public int reg_yOffset = -2;
        }
        //Variables containeing textures path
        public class ResourceValues {
            public CustomContainerType CONTAINER = null;
            public CustomHeartType HEARTS = null;
            public CustomHeartType POISONED_HEARTS = null;
            public CustomHeartType WITHERED_HEARTS = null;
            public CustomHeartType FROZEN_HEARTS = null;
            public CustomHeartType ABSORBING_HEARTS = null;

            public ResourceLocation container = ResourceLocation.parse("null");
            public ResourceLocation container_blinking_damage = ResourceLocation.parse("null");;
            public ResourceLocation container_blinking_healing = ResourceLocation.parse("null");;
            public ResourceLocation hardcore_container = ResourceLocation.parse("null");;
            public ResourceLocation hardcore_container_blinking_damage = ResourceLocation.parse("null");;
            public ResourceLocation hardcore_container_blinking_healing = ResourceLocation.parse("null");;

            public ResourceLocation full_heart = ResourceLocation.parse("null");;
            public ResourceLocation full_heart_blinking_damage = ResourceLocation.parse("null");;
            public ResourceLocation half_heart = ResourceLocation.parse("null");;
            public ResourceLocation half_heart_blinking_damage = ResourceLocation.parse("null");;
            public ResourceLocation hardcore_full_heart = ResourceLocation.parse("null");;
            public ResourceLocation hardcore_full_heart_blinking_damage = ResourceLocation.parse("null");;
            public ResourceLocation hardcore_half_heart = ResourceLocation.parse("null");;
            public ResourceLocation hardcore_half_heart_blinking_damage = ResourceLocation.parse("null");;

            public ResourceLocation poisoned_full_heart = ResourceLocation.parse("null");;
            public ResourceLocation poisoned_full_heart_blinking_damage = ResourceLocation.parse("null");;
            public ResourceLocation poisoned_half_heart = ResourceLocation.parse("null");;
            public ResourceLocation poisoned_half_heart_blinking_damage = ResourceLocation.parse("null");;
            public ResourceLocation hardcore_poisoned_full_heart = ResourceLocation.parse("null");;
            public ResourceLocation hardcore_poisoned_full_heart_blinking_damage = ResourceLocation.parse("null");;
            public ResourceLocation hardcore_poisoned_half_heart = ResourceLocation.parse("null");;
            public ResourceLocation hardcore_poisoned_half_heart_blinking_damage = ResourceLocation.parse("null");;

            public ResourceLocation withered_full_heart = ResourceLocation.parse("null");;
            public ResourceLocation withered_full_heart_blinking_damage = ResourceLocation.parse("null");;
            public ResourceLocation withered_half_heart = ResourceLocation.parse("null");;
            public ResourceLocation withered_half_heart_blinking_damage = ResourceLocation.parse("null");;
            public ResourceLocation hardcore_withered_full_heart = ResourceLocation.parse("null");;
            public ResourceLocation hardcore_withered_full_heart_blinking_damage = ResourceLocation.parse("null");;
            public ResourceLocation hardcore_withered_half_heart = ResourceLocation.parse("null");;
            public ResourceLocation hardcore_withered_half_heart_blinking_damage = ResourceLocation.parse("null");;

            public ResourceLocation frozen_full_heart = ResourceLocation.parse("null");;
            public ResourceLocation frozen_full_heart_blinking_damage = ResourceLocation.parse("null");;
            public ResourceLocation frozen_half_heart = ResourceLocation.parse("null");;
            public ResourceLocation frozen_half_heart_blinking_damage = ResourceLocation.parse("null");;
            public ResourceLocation hardcore_frozen_full_heart = ResourceLocation.parse("null");;
            public ResourceLocation hardcore_frozen_full_heart_blinking_damage = ResourceLocation.parse("null");;
            public ResourceLocation hardcore_frozen_half_heart = ResourceLocation.parse("null");;
            public ResourceLocation hardcore_frozen_half_heart_blinking_damage = ResourceLocation.parse("null");;

            public ResourceLocation absorbing_full_heart = ResourceLocation.parse("null");;
            public ResourceLocation absorbing_half_heart = ResourceLocation.parse("null");;
            public ResourceLocation hardcore_absorbing_full_heart = ResourceLocation.parse("null");;
            public ResourceLocation hardcore_absorbing_half_heart = ResourceLocation.parse("null");;
        }
        public final HealthValues health = new HealthValues();
        public final ResourceValues resources = new ResourceValues();
        public final GuiValues gui = new GuiValues();
        public final RegenEventValues regen = new RegenEventValues();


        @Override
        public CompoundTag serializeNBT(HolderLookup.Provider lookupProvider) {
            CompoundTag nbt = new CompoundTag();
            ListTag listTag = new ListTag();

            nbt.putBoolean("player_takingDamage",               player_takingDamage);
            nbt.putBoolean("player_takingRegen",                player_takingRegen);
            nbt.putBoolean("player_blinking",                   player_blinking);
            nbt.putBoolean("isHardcore",                        isHardcore);

            nbt.putInt("red_LifeNumber",                health.red_LifeNumber);
            nbt.putInt("abs_LifeNumber",                health.abs_LifeNumber);
            nbt.putIntArray("extraLifeNumbers",         health.extraLifeNumbers);
            nbt.putInt("tot_Amount",                    health.tot_Amount);
            nbt.putInt("partialHealthAmount",           health.partialHealthAmount);
            nbt.putInt("red_Amount",                    health.red_Amount);
            nbt.putInt("abs_Amount",                    health.abs_Amount);
            nbt.putInt("tot_MaxAmount",                 health.tot_MaxAmount);
            nbt.putInt("partialMaxHealthAmount",        health.partialMaxHealthAmount);
            nbt.putInt("red_MaxAmount",                 health.red_MaxAmount);
            nbt.putInt("abs_MaxAmount",                 health.abs_MaxAmount);
            nbt.putInt("red_FullHearts",                health.red_FullHearts);
            nbt.putInt("red_Hearts",                    health.red_Hearts);
            nbt.putInt("red_MaxHearts",                 health.red_MaxHearts);
            nbt.putBoolean("red_HasHalf",               health.red_HasHalf);
            nbt.putBoolean("abs_HasHalf",               health.abs_HasHalf);
            nbt.putInt("tot_Hearts",                    health.tot_Hearts);
            nbt.putInt("tot_MaxHearts",                 health.tot_MaxHearts);

            nbt.putLong("lastHealthTime",         gui.lastHealthTime);
            nbt.putLong("healthBlinkTime",        gui.healthBlinkTime);
            nbt.putInt("regenerating",            gui.regenerating);
            nbt.putInt("displayHealth",           gui.displayHealth);
            nbt.putInt("lastHealth",              gui.lastHealth);
            nbt.putLong("lastTickCount",          gui.lastTickCount);
            nbt.putLong("regenStartTick",         gui.regenStartTick);
            nbt.putInt("regenIndex",              gui.regenIndex);
            nbt.putLong("blinkStartTick",         gui.blinkStartTick);
            nbt.putInt("barRows",                 gui.barRows);
            nbt.putInt("barRowsMenus2",           gui.barRowsMenus2);
            nbt.putInt("spacing",                 gui.spacing);
            nbt.putInt("height",                  gui.height);
            nbt.putInt("width",                   gui.width);
            nbt.putLong("tickCount",              gui.tickCount);
            nbt.putLong("nowTick",                gui.nowTick);
            nbt.putInt("startY",                  gui.startY);
            nbt.putInt("startX",                  gui.startX);
            nbt.putInt("spaceBetweenRowsMax",               gui.spaceBetweenRowsMax);
            nbt.putInt("spaceBetweenRowsMin",               gui.spaceBetweenRowsMin);
            nbt.putInt("abs_Slots",                         gui.abs_Slots);
            nbt.putBoolean("hideEmptyHearts",               gui.hideEmptyHearts);
            nbt.putBoolean("collapseDifferentLifeTypes",    gui.collapseDifferentLifeTypes);
            for (Integer slot : gui.absorptionSlotsList) {
                listTag.add(IntTag.valueOf(slot));
            }
            nbt.put("absorptionSlots", listTag);

            nbt.putInt("reg_ticksPerHeart",         regen.reg_ticksPerHeart);
            nbt.putInt("reg_visibleHearts",         regen.reg_visibleHearts);
            nbt.putInt("reg_ticksSinceStart",       regen.reg_ticksSinceStart);
            nbt.putInt("reg_waveDuration",          regen.reg_waveDuration);
            nbt.putInt("reg_cooldown",              regen.reg_cooldown);
            nbt.putInt("reg_yOffset",               regen.reg_yOffset);

            NBTHealper.putCustomContainer(nbt, "CONTAINER", resources.CONTAINER);
            NBTHealper.putCustomHeartType(nbt, "normal_hearts", resources.HEARTS);
            NBTHealper.putCustomHeartType(nbt, "poisoned_hearts", resources.POISONED_HEARTS);
            NBTHealper.putCustomHeartType(nbt, "withered_hearts", resources.WITHERED_HEARTS);
            NBTHealper.putCustomHeartType(nbt, "frozen_hearts", resources.FROZEN_HEARTS);
            NBTHealper.putCustomHeartType(nbt, "absorbing_hearts", resources.ABSORBING_HEARTS);
            nbt.putString("container",              resources.container.toString());
            nbt.putString("container_blinking_damage",              resources.container_blinking_damage.toString());
            nbt.putString("container_blinking_healing",              resources.container_blinking_healing.toString());
            nbt.putString("hardcore_container",              resources.hardcore_container.toString());
            nbt.putString("hardcore_container_blinking_damage",              resources.hardcore_container_blinking_damage.toString());
            nbt.putString("hardcore_container_blinking_healing",              resources.hardcore_container_blinking_healing.toString());
            nbt.putString("full_heart",              resources.full_heart.toString());
            nbt.putString("full_heart_blinking_damage",              resources.full_heart_blinking_damage.toString());
            nbt.putString("half_heart",              resources.half_heart.toString());
            nbt.putString("half_heart_blinking_damage",              resources.half_heart_blinking_damage.toString());
            nbt.putString("hardcore_full_heart",              resources.hardcore_full_heart.toString());
            nbt.putString("hardcore_full_heart_blinking_damage",              resources.hardcore_full_heart_blinking_damage.toString());
            nbt.putString("hardcore_half_heart",              resources.hardcore_half_heart.toString());
            nbt.putString("hardcore_half_heart_blinking_damage",              resources.hardcore_half_heart_blinking_damage.toString());
            nbt.putString("poisoned_full_heart",              resources.poisoned_full_heart.toString());
            nbt.putString("poisoned_full_heart_blinking_damage",              resources.poisoned_full_heart_blinking_damage.toString());
            nbt.putString("poisoned_half_heart",              resources.poisoned_half_heart.toString());
            nbt.putString("poisoned_half_heart_blinking_damage",              resources.poisoned_half_heart_blinking_damage.toString());
            nbt.putString("hardcore_poisoned_full_heart",              resources.hardcore_poisoned_full_heart.toString());
            nbt.putString("hardcore_poisoned_full_heart_blinking_damage",              resources.hardcore_poisoned_full_heart_blinking_damage.toString());
            nbt.putString("hardcore_poisoned_half_heart",              resources.hardcore_poisoned_half_heart.toString());
            nbt.putString("hardcore_poisoned_half_heart_blinking_damage",              resources.hardcore_poisoned_half_heart_blinking_damage.toString());
            nbt.putString("withered_full_heart",              resources.withered_full_heart.toString());
            nbt.putString("withered_full_heart_blinking_damage",              resources.withered_full_heart_blinking_damage.toString());
            nbt.putString("withered_half_heart",              resources.withered_half_heart.toString());
            nbt.putString("withered_half_heart_blinking_damage",              resources.withered_half_heart_blinking_damage.toString());
            nbt.putString("hardcore_withered_full_heart",              resources.hardcore_withered_full_heart.toString());
            nbt.putString("hardcore_withered_full_heart_blinking_damage",              resources.hardcore_withered_full_heart_blinking_damage.toString());
            nbt.putString("hardcore_withered_half_heart",              resources.hardcore_withered_half_heart.toString());
            nbt.putString("hardcore_withered_half_heart_blinking_damage",              resources.hardcore_withered_half_heart_blinking_damage.toString());
            nbt.putString("frozen_full_heart",              resources.frozen_full_heart.toString());
            nbt.putString("frozen_full_heart_blinking_damage",              resources.frozen_full_heart_blinking_damage.toString());
            nbt.putString("frozen_half_heart",              resources.frozen_half_heart.toString());
            nbt.putString("frozen_half_heart_blinking_damage",              resources.frozen_half_heart_blinking_damage.toString());
            nbt.putString("hardcore_frozen_full_heart",              resources.hardcore_frozen_full_heart.toString());
            nbt.putString("hardcore_frozen_full_heart_blinking_damage",              resources.hardcore_frozen_full_heart_blinking_damage.toString());
            nbt.putString("hardcore_frozen_half_heart",              resources.hardcore_frozen_half_heart.toString());
            nbt.putString("hardcore_frozen_half_heart_blinking_damage",              resources.hardcore_frozen_half_heart_blinking_damage.toString());
            nbt.putString("absorbing_full_heart",              resources.absorbing_full_heart.toString());
            nbt.putString("absorbing_half_heart",              resources.absorbing_half_heart.toString());
            nbt.putString("hardcore_absorbing_full_heart",              resources.hardcore_absorbing_full_heart.toString());
            nbt.putString("hardcore_absorbing_half_heart",              resources.hardcore_absorbing_half_heart.toString());


            return nbt;

        }

        @Override
        public void deserializeNBT(HolderLookup.Provider lookupProvider, CompoundTag nbt) {
            player_takingDamage      = nbt.getBoolean("player_takingDamage");
            player_takingRegen      = nbt.getBoolean("player_takingRegen");
            player_blinking      = nbt.getBoolean("player_blinking");
            isHardcore      = nbt.getBoolean("isHardcore");

            health.red_LifeNumber      = nbt.getInt("red_LifeNumber");
            health.abs_LifeNumber      = nbt.getInt("abs_LifeNumber");
            health.extraLifeNumbers      = nbt.getIntArray("extraLifeNumbers");
            health.tot_Amount      = nbt.getInt("tot_Amount");
            health.partialHealthAmount      = nbt.getInt("partialHealthAmount");
            health.red_Amount      = nbt.getInt("red_Amount");
            health.abs_Amount      = nbt.getInt("abs_Amount");
            health.tot_MaxAmount      = nbt.getInt("tot_MaxAmount");
            health.partialMaxHealthAmount      = nbt.getInt("partialMaxHealthAmount");
            health.red_MaxAmount      = nbt.getInt("red_MaxAmount");
            health.abs_MaxAmount      = nbt.getInt("abs_MaxAmount");
            health.red_FullHearts      = nbt.getInt("red_FullHearts");
            health.red_Hearts      = nbt.getInt("red_Hearts");
            health.red_MaxHearts      = nbt.getInt("red_MaxHearts");
            health.red_HasHalf      = nbt.getBoolean("red_HasHalf");
            health.abs_HasHalf      = nbt.getBoolean("abs_HasHalf");
            health.tot_Hearts      = nbt.getInt("tot_Hearts");
            health.tot_MaxHearts      = nbt.getInt("tot_MaxHearts");

            gui.lastHealthTime      = nbt.getLong("lastHealthTime");
            gui.healthBlinkTime      = nbt.getLong("healthBlinkTime");
            gui.regenerating      = nbt.getInt("regenerating");
            gui.displayHealth      = nbt.getInt("displayHealth");
            gui.lastHealth      = nbt.getInt("lastHealth");
            gui.lastTickCount      = nbt.getLong("lastTickCount");
            gui.regenStartTick      = nbt.getLong("regenStartTick");
            gui.regenIndex      = nbt.getInt("regenIndex");
            gui.blinkStartTick      = nbt.getLong("blinkStartTick");
            gui.barRows      = nbt.getInt("barRows");
            gui.barRowsMenus2      = nbt.getInt("barRowsMenus2");
            gui.spacing      = nbt.getInt("spacing");
            gui.height      = nbt.getInt("height");
            gui.width      = nbt.getInt("width");
            gui.width      = nbt.getInt("width");
            gui.tickCount      = nbt.getLong("tickCount");
            gui.nowTick      = nbt.getLong("nowTick");
            gui.startY      = nbt.getInt("startY");
            gui.startX      = nbt.getInt("startX");
            gui.spaceBetweenRowsMax      = nbt.getInt("spaceBetweenRowsMax");
            gui.spaceBetweenRowsMin      = nbt.getInt("spaceBetweenRowsMin");
            gui.abs_Slots      = nbt.getInt("abs_Slots");
            gui.hideEmptyHearts      = nbt.getBoolean("hideEmptyHearts");
            gui.collapseDifferentLifeTypes      = nbt.getBoolean("collapseDifferentLifeTypes");
            gui.absorptionSlotsList.clear();
            if (nbt.contains("absorptionSlots", Tag.TAG_LIST)) {
                ListTag listTag = nbt.getList("absorptionSlots", Tag.TAG_INT);
                for (int i = 0; i < listTag.size(); i++) {
                    gui.absorptionSlotsList.add(listTag.getInt(i));
                }
            }

            regen.reg_ticksPerHeart      = nbt.getInt("reg_ticksPerHeart");
            regen.reg_visibleHearts      = nbt.getInt("reg_visibleHearts");
            regen.reg_ticksSinceStart      = nbt.getInt("reg_ticksSinceStart");
            regen.reg_waveDuration      = nbt.getInt("reg_waveDuration");
            regen.reg_cooldown      = nbt.getInt("reg_cooldown");
            regen.reg_yOffset      = nbt.getInt("reg_yOffset");

            resources.CONTAINER = NBTHealper.getCustomContainer(nbt, "CONTAINER");
            resources.HEARTS = NBTHealper.getCustomHeartType(nbt, "normal_hearts");
            resources.POISONED_HEARTS = NBTHealper.getCustomHeartType(nbt, "poisoned_hearts");
            resources.WITHERED_HEARTS = NBTHealper.getCustomHeartType(nbt, "withered_hearts");
            resources.FROZEN_HEARTS = NBTHealper.getCustomHeartType(nbt, "frozen_hearts");
            resources.ABSORBING_HEARTS = NBTHealper.getCustomHeartType(nbt, "absorbing_hearts");
            resources.container      = ResourceLocation.parse(nbt.getString("container"));
            resources.container_blinking_damage      = ResourceLocation.parse(nbt.getString("container_blinking_damage"));
            resources.container_blinking_healing      = ResourceLocation.parse(nbt.getString("container_blinking_healing"));
            resources.hardcore_container      = ResourceLocation.parse(nbt.getString("hardcore_container"));
            resources.hardcore_container_blinking_damage      = ResourceLocation.parse(nbt.getString("hardcore_container_blinking_damage"));
            resources.hardcore_container_blinking_healing      = ResourceLocation.parse(nbt.getString("hardcore_container_blinking_healing"));
            resources.full_heart      = ResourceLocation.parse(nbt.getString("full_heart"));
            resources.full_heart_blinking_damage      = ResourceLocation.parse(nbt.getString("full_heart_blinking_damage"));
            resources.half_heart      = ResourceLocation.parse(nbt.getString("half_heart"));
            resources.half_heart_blinking_damage      = ResourceLocation.parse(nbt.getString("half_heart_blinking_damage"));
            resources.hardcore_full_heart      = ResourceLocation.parse(nbt.getString("hardcore_full_heart"));
            resources.hardcore_full_heart_blinking_damage      = ResourceLocation.parse(nbt.getString("hardcore_full_heart_blinking_damage"));
            resources.hardcore_half_heart      = ResourceLocation.parse(nbt.getString("hardcore_half_heart"));
            resources.hardcore_half_heart_blinking_damage      = ResourceLocation.parse(nbt.getString("hardcore_half_heart_blinking_damage"));
            resources.poisoned_full_heart      = ResourceLocation.parse(nbt.getString("poisoned_full_heart"));
            resources.poisoned_full_heart_blinking_damage      = ResourceLocation.parse(nbt.getString("poisoned_full_heart_blinking_damage"));
            resources.poisoned_half_heart      = ResourceLocation.parse(nbt.getString("poisoned_half_heart"));
            resources.poisoned_half_heart_blinking_damage      = ResourceLocation.parse(nbt.getString("poisoned_half_heart_blinking_damage"));
            resources.hardcore_poisoned_full_heart      = ResourceLocation.parse(nbt.getString("hardcore_poisoned_full_heart"));
            resources.hardcore_poisoned_full_heart_blinking_damage      = ResourceLocation.parse(nbt.getString("hardcore_poisoned_full_heart_blinking_damage"));
            resources.hardcore_poisoned_half_heart      = ResourceLocation.parse(nbt.getString("hardcore_poisoned_half_heart"));
            resources.hardcore_poisoned_half_heart_blinking_damage      = ResourceLocation.parse(nbt.getString("hardcore_poisoned_half_heart_blinking_damage"));
            resources.withered_full_heart      = ResourceLocation.parse(nbt.getString("withered_full_heart"));
            resources.withered_full_heart_blinking_damage      = ResourceLocation.parse(nbt.getString("withered_full_heart_blinking_damage"));
            resources.withered_half_heart      = ResourceLocation.parse(nbt.getString("withered_half_heart"));
            resources.withered_half_heart_blinking_damage      = ResourceLocation.parse(nbt.getString("withered_half_heart_blinking_damage"));
            resources.hardcore_withered_full_heart      = ResourceLocation.parse(nbt.getString("hardcore_withered_full_heart"));
            resources.hardcore_withered_full_heart_blinking_damage      = ResourceLocation.parse(nbt.getString("hardcore_withered_full_heart_blinking_damage"));
            resources.hardcore_withered_half_heart      = ResourceLocation.parse(nbt.getString("hardcore_withered_half_heart"));
            resources.hardcore_withered_half_heart_blinking_damage      = ResourceLocation.parse(nbt.getString("hardcore_withered_half_heart_blinking_damage"));
            resources.frozen_full_heart      = ResourceLocation.parse(nbt.getString("frozen_full_heart"));
            resources.frozen_full_heart_blinking_damage      = ResourceLocation.parse(nbt.getString("frozen_full_heart_blinking_damage"));
            resources.frozen_half_heart      = ResourceLocation.parse(nbt.getString("frozen_half_heart"));
            resources.frozen_half_heart_blinking_damage      = ResourceLocation.parse(nbt.getString("frozen_half_heart_blinking_damage"));
            resources.hardcore_frozen_full_heart      = ResourceLocation.parse(nbt.getString("hardcore_frozen_full_heart"));
            resources.hardcore_frozen_full_heart_blinking_damage      = ResourceLocation.parse(nbt.getString("hardcore_frozen_full_heart_blinking_damage"));
            resources.hardcore_frozen_half_heart      = ResourceLocation.parse(nbt.getString("hardcore_frozen_half_heart"));
            resources.hardcore_frozen_half_heart_blinking_damage      = ResourceLocation.parse(nbt.getString("hardcore_frozen_half_heart_blinking_damage"));
            resources.absorbing_full_heart      = ResourceLocation.parse(nbt.getString("absorbing_full_heart"));
            resources.absorbing_half_heart      = ResourceLocation.parse(nbt.getString("absorbing_half_heart"));
            resources.hardcore_absorbing_full_heart      = ResourceLocation.parse(nbt.getString("hardcore_absorbing_full_heart"));
            resources.hardcore_absorbing_half_heart      = ResourceLocation.parse(nbt.getString("hardcore_absorbing_half_heart"));


        }

        //Player variables - END
        public void syncPlayerVariables(Entity entity) {
            if (entity instanceof ServerPlayer serverPlayer)
                PacketDistributor.sendToPlayer(serverPlayer, new PlayerVariablesSyncMessage(this));
        }
    }

    public record PlayerVariablesSyncMessage(PlayerVariables data) implements CustomPacketPayload {
        public static final Type<PlayerVariablesSyncMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(HeartDev.MOD_ID, "player_variables_sync"));
        public static final StreamCodec<RegistryFriendlyByteBuf, PlayerVariablesSyncMessage> STREAM_CODEC = StreamCodec
                .of((RegistryFriendlyByteBuf buffer, PlayerVariablesSyncMessage message) -> buffer.writeNbt(message.data().serializeNBT(buffer.registryAccess())), (RegistryFriendlyByteBuf buffer) -> {
                    PlayerVariablesSyncMessage message = new PlayerVariablesSyncMessage(new PlayerVariables());
                    message.data.deserializeNBT(buffer.registryAccess(), buffer.readNbt());
                    return message;
                });

        @Override
        public Type<PlayerVariablesSyncMessage> type() {
            return TYPE;
        }

        public static void handleData(final PlayerVariablesSyncMessage message, final IPayloadContext context) {
            if (context.flow() == PacketFlow.CLIENTBOUND && message.data != null) {
                context.enqueueWork(() -> context.player().getData(PLAYER_VARIABLES).deserializeNBT(context.player().registryAccess(), message.data.serializeNBT(context.player().registryAccess()))).exceptionally(e -> {
                    context.connection().disconnect(Component.literal(e.getMessage()));
                    return null;
                });
            }
        }
    }




}