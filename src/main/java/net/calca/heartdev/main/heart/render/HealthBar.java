package net.calca.heartdev.main.heart.render;

import com.mojang.blaze3d.systems.RenderSystem;

import net.calca.heartdev.main.effect.ModEffects;
import net.calca.heartdev.main.heart.types.CustomContainerType;
import net.calca.heartdev.main.heart.types.CustomHeartType;
import net.calca.heartdev.main.heart.types.HealthTypes;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/**
 * This mod was possible to made thanks to the Colorful Hearts mod, from which I took inspiration.
 * The following methods, in fact, present a revisited version of the Colorful Hearts rendering code.
 * Colorful Hearts license = MIT license (in date 16/06/2025 for version 1.21.1 NeoForge)
 * Colorful Hearts author = Terrails
 */
public class HealthBar {
    public static class PreSets {
        private final Minecraft mc = Minecraft.getInstance();
        public static final PreSets PRESETS_ISTANCE = new PreSets();

        public void activateOrangeEffect (){
            LocalPlayer player = mc.player;
            assert player != null;
            if (!player.hasEffect(ModEffects.ORANGE_HEARTS)){
                return;
            }
            HealthBarVariables.HEARTS = HealthTypes.ModdedTextures.ORANGE_HEARTS;

        }
        public void activateYellowEffect (){
            LocalPlayer player = mc.player;
            assert player != null;
            if (!player.hasEffect(ModEffects.YELLOW_HEARTS)){
                return;
            }
            HealthBarVariables.HEARTS = HealthTypes.ModdedTextures.YELLOW_HEARTS;
        }
        public void activateGreenEffect (){
            LocalPlayer player = mc.player;
            assert player != null;
            if (!player.hasEffect(ModEffects.GREEN_HEARTS)){
                return;
            }
            HealthBarVariables.HEARTS = HealthTypes.ModdedTextures.GREEN_HEARTS;
        }
        public void activateLightBlueEffect (){
            LocalPlayer player = mc.player;
            assert player != null;
            if (!player.hasEffect(ModEffects.LIGHT_BLUE_HEARTS)){
                return;
            }
            HealthBarVariables.HEARTS = HealthTypes.ModdedTextures.LIGHT_BLUE_HEARTS;
        }
        public void activateBlueEffect (){
            LocalPlayer player = mc.player;
            assert player != null;
            if (!player.hasEffect(ModEffects.BLUE_HEARTS)){
                return;
            }
            HealthBarVariables.HEARTS = HealthTypes.ModdedTextures.BLUE_HEARTS;
        }
        public void activatePurpleEffect (){
            LocalPlayer player = mc.player;
            assert player != null;
            if (!player.hasEffect(ModEffects.PURPLE_HEARTS)){
                return;
            }
            HealthBarVariables.HEARTS = HealthTypes.ModdedTextures.PURPLE_HEARTS;
        }
        public void activateMagentaEffect (){
            LocalPlayer player = mc.player;
            assert player != null;
            if (!player.hasEffect(ModEffects.MAGENTA_HEARTS)){
                return;
            }
            HealthBarVariables.HEARTS = HealthTypes.ModdedTextures.MAGENTA_HEARTS;
        }

    }

    private long lastHealthTime = 0;
    private long healthBlinkTime = 0;
    private int regenerating = 0;
    private int displayHealth = 0;
    private int lastHealth = -1;
    private long lastTickCount = -1;
    private long regenStartTick = -9999L; // DA METTERE come variabile di istanza della classe
    private int regenIndex = -1;// dichiara in cima alla classe
    private long blinkStartTick = 0;
    private final RandomSource random = RandomSource.create();
    private final Minecraft mc = Minecraft.getInstance();

    public static final HealthBar HEALTH_INSTANCE = new HealthBar();

    public void shouldRenderHealthBar(RenderGuiLayerEvent.Pre event, Consumer<LivingEntity> render){
        if (event.isCanceled()
                || mc.options.hideGui
                || !Objects.requireNonNull(mc.gameMode).canHurtPlayer()
                || !event.getName().equals(VanillaGuiLayers.PLAYER_HEALTH)
                || !(mc.getCameraEntity() instanceof LocalPlayer player)) {
            return;
        }
        render.accept(player);
    }

    public void renderHealthBar(RenderGuiLayerEvent.Pre event){
        renderHealthBar(event, () -> {

        });
    }
    public void renderHealthBar(RenderGuiLayerEvent.Pre event, Runnable overrides) {

        // === RENDERING HEALTH LAYER ===
            event.setCanceled(true);

            // === UNIVERSAL VARIABLES ===
            LocalPlayer player = mc.player;
            assert player != null;
            HealthBarVariables.isHardcore = player.level().getLevelData().isHardcore();

            // Health and absorption
            int health = Mth.ceil(player.getHealth());
            int maxHealth = Mth.ceil(player.getMaxHealth());
            int absorption = Mth.ceil(player.getAbsorptionAmount());
            Set<Integer> absorptionSlots = new HashSet<>();


        if (HealthBarVariables.hideEmptyHearts){
                maxHealth = (health % 2 == 0) ? health : health + 1;
            }



            // Hearts and rows
            int fullHearts = health / 2;
            boolean half = (health % 2) != 0;
            int redHearts = maxHealth / 2 + (maxHealth % 2);
            int currentRedHearts = health / 2 + (health % 2);
            int totalHearts = (maxHealth / 2 + (maxHealth % 2)) + (absorption / 2 + (absorption % 2));
            int totalCurrentHearts = (health / 2 + (health % 2)) + (absorption / 2 + (absorption % 2));
            int totalHealth = health + absorption;

            int rows      = (totalHearts + 9) / 10;
            if (HealthBarVariables.collapseDifferentLifeTypes){
                if (half){
                    // righe “collapse”: usa totale slot reali
                    int r = (((totalHealth + 1) / 2) + 9) / 10;
                    // fino a 2 righe piene (<=20 slot) forzi rows=2,
                    // solo da 21 slot in su r diventa 3.
                    rows = Math.max(r, 2);
                }else{
                    rows = ((totalCurrentHearts + 9) / 10);
                }
            }
            int extra     = Math.max(0, rows - 2);
            int spacing   = HealthBarVariables.spaceBetweenRowsMax - Math.min(extra, HealthBarVariables.spaceBetweenRowsMin);

            // cuori health (+ boost) già disegnati

            // Positioning and graphics
            GuiGraphics gfx = event.getGuiGraphics();
            int height = gfx.guiHeight();
            HealthBarVariables.startY = height - mc.gui.leftHeight;
            int width = gfx.guiWidth();
            HealthBarVariables.startX = width / 2 - 91;

            // Time and animations
            long tickCount = mc.gui.getGuiTicks();
            long now = Util.getMillis();

            // Regeneration parameter
            int ticksPerHeart = HealthBarVariables.regenAnimationSpeed;
            int visibleHearts = Mth.ceil((player.getMaxHealth() + player.getAbsorptionAmount()) / 2f);
            int ticksSinceStart = (int) (tickCount - regenStartTick);
            int waveDuration = visibleHearts * ticksPerHeart;
            int cooldown = HealthBarVariables.regenAnimationCooldown;
            int yOffset = HealthBarVariables.regenAnimationYoffSet;
            random.setSeed(tickCount * 312871L);

            if (lastTickCount < tickCount - 1){
                lastHealth = health;
                displayHealth = health;
                lastHealthTime = Util.getMillis();
                lastTickCount = tickCount;
                return;
            }
        lastTickCount = tickCount;



            // -- 0) Building textures
            buildTextures();

            // -- 1) Updating Blinking state
            updateBlinkingState(health, now, tickCount, player);

            // -- 2) Calculating regeneration event
            regenerationEvent(cooldown, ticksSinceStart, waveDuration, ticksPerHeart, tickCount, player);

            // Refreshing
            refreshDisplayHealth(now, health);


            boolean takingDamage = health < displayHealth;
            boolean takingRegen = health > displayHealth;
            boolean blinking = false;
            if (tickCount < healthBlinkTime) {
                long elapsed = tickCount - blinkStartTick;
                blinking = (elapsed / 3L) % 2L == 0L;
            }
            blinking = blinking || regenerating > 0;

            lastHealth = health;
            regenerating --;
            // -- 2) ---

        overrides.run();

        if (HealthBarVariables.collapseDifferentLifeTypes && half){
            absorption++;
        }


        int absorbSlots     = Mth.ceil(absorption / 2f);
        boolean absorptionHalf;
        if (HealthBarVariables.collapseDifferentLifeTypes){
            absorptionHalf = (totalHealth % 2) != 0;
        }else{
            absorptionHalf = (absorption % 2) != 0;
        }
            for (int j = absorbSlots - 1; j >= 0; j--) {
                int slotIndex = redHearts + j;

                if (HealthBarVariables.collapseDifferentLifeTypes){
                    slotIndex = currentRedHearts + j;
                }

                int line = slotIndex / 10;
                int col  = (slotIndex % 10);

                if (HealthBarVariables.collapseDifferentLifeTypes && half){
                    int check = (slotIndex) / 11;
                    if ((check == 0)){
                        col  = ((slotIndex) % 11);
                        line = slotIndex / 11;
                        col --;
                    }else{
                        slotIndex--;
                        line = slotIndex / 10;
                        col  = (slotIndex % 10);
                    }
                }
                int localSpacing = line * spacing;

                int x = HealthBarVariables.startX + col * 8;
                int y = HealthBarVariables.startY - localSpacing;


                boolean halfAbs = (absorptionHalf && j == absorbSlots - 1);



                if (HealthBarVariables.collapseDifferentLifeTypes && half){
                    halfAbs = absorptionHalf && j == absorbSlots-1;
                    if (absorption-1 > 0 && (j > 0 || half)){
                        int key = line * 10 + col;
                        absorptionSlots.add(key);
                        renderAbsorption(player, HealthBarVariables.isHardcore, gfx, x, y, halfAbs);
                    }
                }else{
                    int key = line * 10 + col;
                    absorptionSlots.add(key);
                    renderAbsorption(player, HealthBarVariables.isHardcore, gfx, x, y, halfAbs);
                }

            }

            for (int i = redHearts - 1; i >= 0; i--) {
                int line = i / 10;
                int col  = i % 10;
                int x = HealthBarVariables.startX + (i % 10) * 8;
                int y = HealthBarVariables.startY - line * spacing;

                if (health + absorption <= 4) y += random.nextInt(2);
                if (i == regenIndex) y += yOffset;


                // -- 3) RENDER BACKGROUND CUORI (vuoti + absorption) --
                if (HealthBarVariables.collapseDifferentLifeTypes){int key = line * 10 + col;
                    if (!absorptionSlots.contains(key)) {
                        renderContainer(HealthBarVariables.isHardcore, gfx, x, y, blinking, takingRegen);
                    }
                }else{
                    renderContainer(HealthBarVariables.isHardcore, gfx, x, y, blinking, takingRegen);
                }

                // -- 4) RENDER BLINKING HEARTS (danno) --
                renderBlinking(player, HealthBarVariables.isHardcore, gfx, i, x, y, maxHealth, health, blinking, takingDamage);

                // -- 5) RENDER CUORI PIENI NORMALI --
                renderHeart(player, HealthBarVariables.isHardcore, gfx, i, x, y, fullHearts, half);

            }
                fixArmors(rows, spacing);

            //Reset
        HealthBarVariables.CONTAINER = null;
        HealthBarVariables.HEARTS = null;
        HealthBarVariables.POISONED_HEARTS = null;
        HealthBarVariables.WITHERED_HEARTS = null;
        HealthBarVariables.FROZEN_HEARTS = null;
        HealthBarVariables.ABSORBING_HEARTS = null;

        HealthBarVariables.hideEmptyHearts = false;
        HealthBarVariables.collapseDifferentLifeTypes = false;

        HealthBarVariables.regenAnimationSpeed = 1;
        HealthBarVariables.regenAnimationCooldown = 15;
        HealthBarVariables.regenAnimationYoffSet = -2;

        HealthBarVariables.spaceBetweenRowsMax = 10;
        HealthBarVariables.spaceBetweenRowsMin = 7;
    }


    private void buildTextures(){
        if (HealthBarVariables.CONTAINER != null) HealthBarVariables.CONTAINER.buildResources();
        else HealthBarVariables.buildResourcesWithVanilla(HealthTypes.VanillaHeartTypes.CONTANER);

        if (HealthBarVariables.HEARTS != null) HealthBarVariables.HEARTS.buildResources();
        else HealthBarVariables.buildResourcesWithVanilla(HealthTypes.VanillaHeartTypes.NORMAL);

        if (HealthBarVariables.POISONED_HEARTS != null) HealthBarVariables.POISONED_HEARTS.buildResources();
        else HealthBarVariables.buildResourcesWithVanilla(HealthTypes.VanillaHeartTypes.POISONED);

        if (HealthBarVariables.WITHERED_HEARTS != null) HealthBarVariables.WITHERED_HEARTS.buildResources();
        else HealthBarVariables.buildResourcesWithVanilla(HealthTypes.VanillaHeartTypes.WITHERED);

        if (HealthBarVariables.FROZEN_HEARTS != null) HealthBarVariables.FROZEN_HEARTS.buildResources();
        else HealthBarVariables.buildResourcesWithVanilla(HealthTypes.VanillaHeartTypes.FROZEN);

        if (HealthBarVariables.ABSORBING_HEARTS != null) HealthBarVariables.ABSORBING_HEARTS.buildResources();
        else HealthBarVariables.buildResourcesWithVanilla(HealthTypes.VanillaHeartTypes.ABSORBING);
    }

    private void renderContainer(boolean isHardcore, GuiGraphics gfx, int x, int y, boolean blinking, boolean isRegen) {
        ResourceLocation heartsBackground;

    if (isHardcore) {
        if (blinking){
            heartsBackground = isRegen ?  HealthBarVariables.hardcore_container_blinking_healing : HealthBarVariables.hardcore_container_blinking_damage;
        }
        else {
            heartsBackground = HealthBarVariables.hardcore_container;
        }
        } else {
        if (blinking){
            heartsBackground = isRegen ?  HealthBarVariables.container_blinking_healing : HealthBarVariables.container_blinking_damage;
        }
        else{
            heartsBackground = HealthBarVariables.container;
        }
        }

        RenderSystem.setShaderTexture(0, heartsBackground);
        gfx.blit(heartsBackground, x, y, 0, 0, 9, 9, 9, 9);
    }
    private void renderBlinking(LivingEntity livingEntity, boolean isHardcore, GuiGraphics gfx, int i, int x, int y, int maxHealth, float health,
                                boolean blinking, boolean takingDamage) {
        if (blinking && takingDamage) {
            float oldHealth = Math.min(displayHealth, maxHealth);

            float slotMin = i * 2f;
            float slotMid = slotMin + 1f;
            float slotMax = slotMin + 2f;

            ResourceLocation blinkTex = null;

            if (oldHealth >= slotMax && health < slotMax) {
                blinkTex = HealthBarVariables.getSprite(livingEntity, isHardcore, false, true, false);
            } else if (oldHealth >= slotMid && health < slotMid) {
                blinkTex = HealthBarVariables.getSprite(livingEntity, isHardcore, true, true, false);
            }

            if (blinkTex != null) {
                RenderSystem.setShaderTexture(0, blinkTex);
                gfx.blit(blinkTex, x, y, 0, 0, 9, 9, 9, 9);
            }
        }
    }
    private void renderAbsorption(LivingEntity livingEntity, boolean isHardcore, GuiGraphics gfx, int x, int y, boolean half) {

        // scegli la texture di assorbimento
        ResourceLocation bg = HealthBarVariables.container;
        RenderSystem.setShaderTexture(0, bg);
        gfx.blit(bg, x, y, 0, 0, 9,9, 9,9);


        ResourceLocation texA = half
                ? HealthBarVariables.getSprite(livingEntity, isHardcore, true, false, true)
                : HealthBarVariables.getSprite(livingEntity, isHardcore, false, false, true);

        // disegna cuore di assorbimento
        RenderSystem.setShaderTexture(0, texA);
        gfx.blit(texA, x, y, 0, 0, 9,9, 9,9);
    }
    private void renderHeart(LivingEntity livingEntity, boolean isHardcore, GuiGraphics gfx, int i, int x, int y, int fullHearts, boolean half) {
        ResourceLocation heartTex = null;
        if (i < fullHearts) {
            heartTex = HealthBarVariables.getSprite(livingEntity, isHardcore, false, false, false);
        } else if (i == fullHearts && half) {
            heartTex = HealthBarVariables.getSprite(livingEntity, isHardcore, true, false, false);
        }

        if (heartTex != null) {
            RenderSystem.setShaderTexture(0, heartTex);
            gfx.blit(heartTex, x, y, 0, 0, 9, 9, 9, 9);
        }
    }

    private void updateBlinkingState(int health, long now, long tickCount, LocalPlayer player) {
        if (health < lastHealth && player.invulnerableTime > 0) {
            lastHealthTime = now;
            healthBlinkTime = tickCount + 18;
            blinkStartTick = tickCount;
        } else if (health > lastHealth) {
            regenerating = 5;
            lastHealthTime = now;
            healthBlinkTime = tickCount + 6 + regenerating;
            blinkStartTick = tickCount;
        }
    }
    private void regenerationEvent(int cooldown, int ticksSinceStart, int waveDuration, int ticksPerHeart, long tickCount, LocalPlayer player) {
        if (!player.hasEffect(MobEffects.REGENERATION)) {
            regenIndex = -1;
            return;
        }

        if (ticksSinceStart < waveDuration) {
            // siamo ancora all’interno dell’onda
            regenIndex = ticksSinceStart / ticksPerHeart;
        } else if (ticksSinceStart < waveDuration + cooldown) {
            // in pausa: nessun cuore saltella
            regenIndex = -1;
        } else {
            // onda e cooldown completati → resetta per la prossima onda
            regenStartTick = tickCount;
            regenIndex      = -1;
        }

    }

    private void refreshDisplayHealth(long now, int health) {
        if (now - lastHealthTime > 1000L) {
            displayHealth = health;
            lastHealthTime = now;
        }
    }

    private void fixArmors(int rows, int spacing){
        int armorHight;
        armorHight = 49 + (rows - 1) * spacing;

        Minecraft mc = Minecraft.getInstance();
        mc.gui.leftHeight = armorHight;
    }

}

