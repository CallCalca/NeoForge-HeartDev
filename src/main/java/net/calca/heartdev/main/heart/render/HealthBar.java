package net.calca.heartdev.main.heart.render;

import com.google.errorprone.annotations.DoNotCall;
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
import org.checkerframework.framework.qual.IgnoreInWholeProgramInference;
import org.jetbrains.annotations.ApiStatus;
import org.openjdk.nashorn.internal.ir.annotations.Ignore;

import javax.annotation.Detainted;
import javax.annotation.meta.Exclusive;
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

    @Deprecated
    public long lastHealthTime = 0; // UNKOWN
    @Deprecated
    public long healthBlinkTime = 0; // UNKOWN
    @Deprecated
    public int regenerating = 0; // UNKOWN
    @Deprecated
    public int displayHealth = 0; // UNKOWN
    @Deprecated
    public int lastHealth = -1; // UNKOWN
    @Deprecated
    public long lastTickCount = -1; // UNKOWN
    @Deprecated
    public long regenStartTick = -9999L;  // UNKOWN
    @Deprecated
    public int regenIndex = -1; // UNKOWN
    @Deprecated
    public long blinkStartTick = 0; // UNKOWN
    @Deprecated
    public final RandomSource random = RandomSource.create(); // UNKOWN
    @Deprecated
    public final Minecraft mc = Minecraft.getInstance(); // Minecraft instance

    public static final HealthBar HEALTH_INSTANCE = new HealthBar(); //Class instance

    //Check whether or not the event is actually rendering the health bar and nothing else, then it runs the rest of the code
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

    //Simple method version
    public void renderHealthBar(RenderGuiLayerEvent.Pre event){
        renderHealthBar(event, () -> {

        });
    }

    //Complex method version
    //Contains all the rendering system itself
    public void renderHealthBar(RenderGuiLayerEvent.Pre event, Runnable overrides) {

        // RENDERING HEALTH LAYER
            event.setCanceled(true);

            // INITIALIZING VARIABLES
            LocalPlayer player = mc.player; //Player entity
            assert player != null;
            HealthBarVariables.isHardcore = player.level().getLevelData().isHardcore(); //Hardcore variable matches the world hardcore difficulty

            // Health and absorption
            int health = Mth.ceil(player.getHealth()); //Current player's health
            int maxHealth = Mth.ceil(player.getMaxHealth()); //Current player's max health
            int absorption = Mth.ceil(player.getAbsorptionAmount()); //Current player's absorption health amount
            int totalHealth = health + absorption; //Total player's health (normal + absorption)
            Set<Integer> absorptionSlotsList = new HashSet<>(); //List to check for absorption hearts UNKOWN


        //If empty hearts are hidden, this block will fire, reducing maxHealth variable to the current hearts of player, plus 1 in case it is uneven.
        //This way the container will only be rendered under the full and half hearts of the health bar.
        if (HealthBarVariables.hideEmptyHearts){
                maxHealth = (health % 2 == 0) ? health : health + 1;
            }



            // Variables: Hearts and rows
            int fullCurrentHearts = health / 2; //Current full hearts amount
            boolean hasHalfHeart = (health % 2) != 0; //Does the player have a half heart?
            int maxHearts = maxHealth / 2 + (maxHealth % 2); //Max hearts amount
            int currentHearts = health / 2 + (health % 2); //Current normal health hearts amount
            int totalMaxHearts = (maxHealth / 2 + (maxHealth % 2)) + (absorption / 2 + (absorption % 2)); //Total Max hearts amount (max normal health + absorption health)
            int totalCurrentHearts = (health / 2 + (health % 2)) + (absorption / 2 + (absorption % 2)); //Total current hearts amount (normal health + absorption)
            int rows      = (totalMaxHearts + 9) / 10; //Player's hearts rows amount (every row is 10 hearts by vanilla)

            //If this variable is true, this block will fire
            //A bunch of calculation to reduce the player's health bars to 1 unique health bar
            if (HealthBarVariables.collapseDifferentLifeTypes){
                if (hasHalfHeart){
                    int r = (((totalHealth + 1) / 2) + 9) / 10;
                    rows = Math.max(r, 2);
                }else{
                    rows = ((totalCurrentHearts + 9) / 10);
                }
            }
            int extraRows     = Math.max(0, rows - 2); // Heart rows amount, but it starts to count form the 3rd player's row.
            int spacing   = HealthBarVariables.spaceBetweenRowsMax - Math.min(extraRows, HealthBarVariables.spaceBetweenRowsMin); //Space between rows

            // Positioning and graphics
            GuiGraphics gfx = event.getGuiGraphics(); //Graphic
            int height = gfx.guiHeight(); //Heigh of the Minecraft window
            HealthBarVariables.startY = height - mc.gui.leftHeight; // Y start point, used to draw the health bar.
            int width = gfx.guiWidth(); //Width of the Minecraft window
            HealthBarVariables.startX = width / 2 - 91; //X start point, used to draw the health bar.

            // Time and animations
            long tickCount = mc.gui.getGuiTicks(); //In game ticks time (every second it increment by 20)
            long now = Util.getMillis(); //In game current tick

            // Regeneration parameter
            int ticksPerHeart = HealthBarVariables.regenAnimationSpeed; //Ticks a heart should remain lifted
            int visibleHearts = Mth.ceil((player.getMaxHealth() + player.getAbsorptionAmount()) / 2f); // UNKOWN
            int ticksSinceStart = (int) (tickCount - regenStartTick); // UNKOWN
            int waveDuration = visibleHearts * ticksPerHeart; //Duration of the wave like effect cause by the regeneration effect.
            int cooldown = HealthBarVariables.regenAnimationCooldown; //Rest time between wave cycles
            int yOffset = HealthBarVariables.regenAnimationYoffSet; //How much a heart should lift, in pixels
            random.setSeed(tickCount * 312871L); //Used to draw the wiggling effect caused by low health

        // UNKOWN
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


            boolean takingDamage = health < displayHealth; //Is the player taking damage on this tick?
            boolean takingRegen = health > displayHealth; //Is the player getting healed in this tick?
            boolean blinking = false; //Is the health bar blinking?
            // UNKOWN
            if (tickCount < healthBlinkTime) {
                long elapsed = tickCount - blinkStartTick;
                blinking = (elapsed / 3L) % 2L == 0L;
            }
            blinking = blinking || regenerating > 0;

            lastHealth = health;
            regenerating --;

            //Override methods run time
        overrides.run();

        //When this variable is true and the player has a half heart, this block will fire
        //It adds 1 to the absorption variable. This is necessary since, when health bars are collapse, and the player has a half heart,
        //the second life type will shift one of its hearts back by 1, to fill the half heart. This would mean that the same life type would not render
        //1 health heart, or else, it would be hidden by the normal health current health heart. Better contact me to better understand this process
        if (HealthBarVariables.collapseDifferentLifeTypes && hasHalfHeart){
            absorption++;
        }

        int absorbSlots     = Mth.ceil(absorption / 2f); // UNKOWN
        boolean absorptionHalf; //Does the player have 1 half absorption heart?
        //When this variable is true, this block will fire
        //Overrides the variable responsible for checking the player's health, to match the variable value.
        //The player will result to have an absorption half heart based on total health, and not on absorption health.
        if (HealthBarVariables.collapseDifferentLifeTypes){
            absorptionHalf = (totalHealth % 2) != 0;
        }else{
            absorptionHalf = (absorption % 2) != 0;
        }
        //Rendering absorption hearts CYCLE
        renderAbsorptionHeartsCycle(maxHearts, currentHearts, hasHalfHeart, absorption, absorbSlots, absorptionHalf, spacing, gfx, player, absorptionSlotsList);

        renderNormalHeartCycle(maxHearts, fullCurrentHearts, spacing, health, absorption, yOffset, absorptionSlotsList, gfx, blinking, takingDamage, takingRegen, maxHealth,
                hasHalfHeart, player);
            //Normal life type render CYCLE
            //Y start point is fixed to allow the armor bar to correctly render.
                fixArmors(rows, spacing);

            //Soma variables are reset
        reset();
    }

    /**
     *Last change in version: 1.1.4-neoforge
     */
    //Contains the cycle to render the absorption life type hearts
    @Deprecated
    public void renderAbsorptionHeartsCycle(int maxHearts, int currentHearts, boolean hasHalfHeart, int absorption, int absorbSlots, boolean absorptionHalf, int spacing,
                                            GuiGraphics gfx, LivingEntity player, Set<Integer> absorptionSlots){

        for (int j = absorbSlots - 1; j >= 0; j--) {
            int slotIndex = maxHearts + j; //The slot is going to be rendered. It starts from maxHearts because those are the normal life slots.

            //When this variable is true, this block will fire
            //Because life types will collapse, it is no longer needed to base the slotIndex on maxHearts, but it is needed to base on currentHearts.
            if (HealthBarVariables.collapseDifferentLifeTypes){
                slotIndex = currentHearts + j;
            }

            int line = slotIndex / 10; //Indicates in which row this slot is being rendered
            int col  = (slotIndex % 10); //Indicated in which column this slot is being rendered


            boolean halfAbs = absorptionHalf && j == absorbSlots - 1; //Does the player have an half absorption heart? Calculated on the last available absorption
            //heart, since it is the only one capable of being halved.

            //When this variable is true and the player has an half normal life type heart, this block will fire
            //All variables such as col and line are re inizialized to match the changing health bar render system
            if (HealthBarVariables.collapseDifferentLifeTypes && hasHalfHeart){
                int check = (slotIndex) / 11; //Because the absorption heart will shift back by 1 when a half heart is present, it is needed to check for 11 hearts in the first line, and not for 10.
                if ((check == 0)){ //IF UNKOWN
                    col  = ((slotIndex) % 11); //Column based on 11 hearts because fo the same reason check does
                    line = slotIndex / 11; //Same with line
                    col --; //Col is reduced by 1 because to match the shift in the life types
                }else{ //This will basically fire when the player does not have a full heart.
                    slotIndex--;
                    line = slotIndex / 10;
                    col  = (slotIndex % 10);
                }
            }
            int localSpacing = line * spacing; //This spacing is based on the lines calculated on the health shift

            int x = HealthBarVariables.startX + col * 8; // X start render point (every slot will have its own)
            int y = HealthBarVariables.startY - localSpacing; //Y start render point (every slot will have its own)

            //When this variable is true and the player has an half normal life type heart, this block will fire
            //halfAbs will be based on
            if (HealthBarVariables.collapseDifferentLifeTypes && hasHalfHeart){
                halfAbs = absorptionHalf && j == absorbSlots - 1;
                //If the player has at least 2 absorption hearts (1 isnt considered since it is used just for rendering the half heart) and
                //the is not being rendered the first slot (j > 0) or the player has and half normal heart, this block will fire
                if (absorption-1 > 0 && (j > 0 || hasHalfHeart)){
                    int key = line * 10 + col; //The slot value
                    absorptionSlots.add(key); //The slot this happens to be true will be saved inside the absorptionSlots list, that will be later used.
                    renderAbsorption(player, HealthBarVariables.isHardcore, gfx, x, y, halfAbs); //Finally the absorption heart is rendered
                }
            }else{ //This will basically fire when the player does not have a full heart.
                int key = line * 10 + col; //The slot value
                absorptionSlots.add(key);//The slot will be saved inside the absorptionSlots list, that will be later used.
                renderAbsorption(player, HealthBarVariables.isHardcore, gfx, x, y, halfAbs); //Finally the absorption heart is rendered
            }

        }

    }

    /**
     *Last change in version: 1.1.4-neoforge
     */
    //Contains the for cycle to render the normal life type hearts
    @Deprecated
    public void renderNormalHeartCycle(int maxHearts, int fullCurrentHearts, int spacing, int health, int absorption, int yOffset, Set<Integer> absorptionSlotsList, GuiGraphics gfx,
                                       boolean blinking, boolean takingDamage, boolean takingRegen, int maxHealth, boolean hasHalfHeart, LivingEntity player){
        for (int i = maxHearts - 1; i >= 0; i--) {
            int line = i / 10; //The line in which this particular slot is in
            int col  = i % 10; //The column in which this particular slot is in
            int x = HealthBarVariables.startX + (i % 10) * 8; //X start render point
            int y = HealthBarVariables.startY - line * spacing; //Y start render point

            if (health + absorption <= 4) y += random.nextInt(2); //If the player has low health, the Y value is randomized to mimic the wiggling effect.
            if (i == regenIndex) y += yOffset; //If the player has regeneration, the Y value is addition to the y regen offset, to mimic the wave effect


            //If this variable is true, this block will fire
            //It checks for slots with the same value as the absorption slots, saved before inside the absorptionSlotsList list.
            if (HealthBarVariables.collapseDifferentLifeTypes){
                int key = line * 10 + col; //Current slot value
                //If the current slot value does not match any of the value inside the absorptionSlotsList list, this block will fire
                if (!absorptionSlotsList.contains(key)) {
                    renderContainer(HealthBarVariables.isHardcore, gfx, x, y, blinking, takingRegen); //The container is rendered
                }
                //Concluding, the container is rendered only in those slots that are not the absorption slots. It is this way because the container would be
                //rendered over the absorption, hiding them. This is why is important to NOT render containers on the same slots as the absorption.
            }else{
                renderContainer(HealthBarVariables.isHardcore, gfx, x, y, blinking, takingRegen); //The container is rendered
            }

            // Blinking hearts are rendered (caused by damage)
            renderBlinking(player, HealthBarVariables.isHardcore, gfx, i, x, y, maxHealth, health, blinking, takingDamage);

            // Normale hearts are rendered
            renderHeart(player, HealthBarVariables.isHardcore, gfx, i, x, y, fullCurrentHearts, hasHalfHeart);

        }

    }


    //Set all necessarry variable responsible for heart rendering
    @Deprecated
    public void buildTextures(){
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

    //Render the container, heart background.
    @Deprecated
    public void renderContainer(boolean isHardcore, GuiGraphics gfx, int x, int y, boolean blinking, boolean isRegen) {
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
    //Render the blinking hearts, cause by: taking damage
    @Deprecated
    public void renderBlinking(LivingEntity livingEntity, boolean isHardcore, GuiGraphics gfx, int i, int x, int y, int maxHealth, float health,
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
    //Render the absorption hearts
    @Deprecated
    public void renderAbsorption(LivingEntity livingEntity, boolean isHardcore, GuiGraphics gfx, int x, int y, boolean half) {

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
    //Render the normal hearts
    @Deprecated
    public void renderHeart(LivingEntity livingEntity, boolean isHardcore, GuiGraphics gfx, int i, int x, int y, int fullHearts, boolean half) {
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

    //Update the blinking state to simulate vanilla blinking animation
    @Deprecated
    public void updateBlinkingState(int health, long now, long tickCount, LocalPlayer player) {
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
    //Check if the player has regeneration effect and, based on that, activate the regeneration animations
    @Deprecated
    public void regenerationEvent(int cooldown, int ticksSinceStart, int waveDuration, int ticksPerHeart, long tickCount, LocalPlayer player) {
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

    //Refresh health value to match the current
    @Deprecated
    public void refreshDisplayHealth(long now, int health) {
        if (now - lastHealthTime > 1000L) {
            displayHealth = health;
            lastHealthTime = now;
        }
    }

    //Fix the Y start point to allow the correct rendering of the ermor bar
    @Deprecated
    public void fixArmors(int rows, int spacing){
        int armorHight;
        armorHight = 49 + (rows - 1) * spacing;

        Minecraft mc = Minecraft.getInstance();
        mc.gui.leftHeight = armorHight;
    }

    //Reset variables to allow for other values to be used.
    @Deprecated
    public void reset(){
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

}

