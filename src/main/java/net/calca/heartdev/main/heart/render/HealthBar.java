package net.calca.heartdev.main.heart.render;

import com.mojang.blaze3d.systems.RenderSystem;

import net.calca.heartdev.main.effect.ModEffects;
import net.calca.heartdev.main.heart.render.data.variables.HealthBarPersonalVariables;
import net.calca.heartdev.main.heart.types.HealthTypes;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

import java.util.HashSet;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * This mod was possible to made thanks to the Colorful Hearts mod, from which I took inspiration.
 * The following methods, in fact, present a revisited version of the Colorful Hearts rendering code.
 * Colorful Hearts license = MIT license (in date 16/06/2025 for version 1.21.1 NeoForge)
 * Colorful Hearts author = Terrails
 */
public class HealthBar {
    public static class PreSets {

        public final ServerPlayer player;
        public final HealthBarPersonalVariables.PlayerVariables.ResourceValues resources;

        public PreSets(Player player) {
            if (player instanceof ServerPlayer serverPlayer1){
                this.player = serverPlayer1;
            }else{
                this.player = null;
            }
            this.resources = (this.player != null)
                    ? HealthBarGlobalVariables.getPlayerVariables(player).resources
                    : null;
        }

        public void activateOrangeEffect (){
            if (player.hasEffect(ModEffects.ORANGE_HEARTS)){
                return;
            }
            resources.HEARTS = HealthTypes.ModdedTextures.ORANGE_HEARTS;

        }
        public void activateYellowEffect (){
            if (player.hasEffect(ModEffects.YELLOW_HEARTS)){
                return;
            }
            resources.HEARTS = HealthTypes.ModdedTextures.YELLOW_HEARTS;
        }
        public void activateGreenEffect (){
            if (player.hasEffect(ModEffects.GREEN_HEARTS)){
                return;
            }
            resources.HEARTS = HealthTypes.ModdedTextures.GREEN_HEARTS;
        }
        public void activateLightBlueEffect (){
            if (player.hasEffect(ModEffects.LIGHT_BLUE_HEARTS)){
                return;
            }
            resources.HEARTS = HealthTypes.ModdedTextures.LIGHT_BLUE_HEARTS;
        }
        public void activateBlueEffect (){
            if (player.hasEffect(ModEffects.BLUE_HEARTS)){
                return;
            }
            resources.HEARTS = HealthTypes.ModdedTextures.BLUE_HEARTS;
        }
        public void activatePurpleEffect (){
            if (player.hasEffect(ModEffects.PURPLE_HEARTS)){
                return;
            }
            resources.HEARTS = HealthTypes.ModdedTextures.PURPLE_HEARTS;
        }
        public void activateMagentaEffect (){
            if (player.hasEffect(ModEffects.MAGENTA_HEARTS)){
                return;
            }
            resources.HEARTS = HealthTypes.ModdedTextures.MAGENTA_HEARTS;
        }

    }
    @Deprecated
    public final Minecraft mc = Minecraft.getInstance(); // Minecraft instance

    public static final HealthBar HEALTH_INSTANCE = new HealthBar(); //Class instance

    //Check whether or not the event is actually rendering the health bar and nothing else, then it runs the rest of the code
    public void shouldRenderHealthBar(RenderGuiLayerEvent.Pre event) {

        for (Player player : HealthBar.HEALTH_INSTANCE.mc.level.players()) {
            if (event.isCanceled()
                    || mc.options.hideGui
                    || !Objects.requireNonNull(mc.gameMode).canHurtPlayer()
                    || !event.getName().equals(VanillaGuiLayers.PLAYER_HEALTH)
                    || !(mc.getCameraEntity() instanceof LocalPlayer localPlayer)) {
                return;
            }
            event.setCanceled(true); // Deve cancellare solo quando necessario qunidi fare un metodo aparte che comprenda se cancellare o meno

            HealthBarPersonalVariables.PlayerVariables playerVars = HealthBarGlobalVariables.getPlayerVariables(localPlayer);

            if (localPlayer.tickCount > -1) {
                renderHealthBar(event, player, playerVars);
                playerVars.gui.absorptionSlotsList = new HashSet<>(); //Reset qua perche cosi tutti i metodi di render, anche quelli di vite+, comunicano tra loro.
            }

        }
    }

    /**
     * health -> red_Amount
     * absorption -> abs_Amount
     * totalHealth -> tot_Amount
     * red_MaxAmount
     * abs_MaxAmount
     * maxHealth -> tot_MaxAmount
     * fullCurrentHearts -> red_FullHearts
     * hasHalfHeart -> red_hasHalf
     * maxHearts -> red_MaxHearts
     * totalMaxHearts -> tot_MaxHearts
     * currentHearts -> red_Hearts
     * totalCurrentHearts -> tot_Hearts
     * rows -> barRows
     * extraRows -> barRowsMenus2
     * spacing -> /
     * gfx -> guiGraphics
     * now -> nowTick
     */
    public void setVariables(Player player){
        HealthBarPersonalVariables.PlayerVariables playerVars = HealthBarGlobalVariables.getPlayerVariables(player);
        HealthBarPersonalVariables.PlayerVariables.HealthValues health = playerVars.health;
        HealthBarPersonalVariables.PlayerVariables.GuiValues gui = playerVars.gui;
        HealthBarPersonalVariables.PlayerVariables.RegenEventValues regen = playerVars.regen;


        //Misc
        playerVars.isHardcore = player.level().getLevelData().isHardcore(); //Hardcore variable matches the world hardcore difficulty

        //Current healths
        health.red_Amount = Mth.ceil(player.getHealth()); //Current player's health
        health.abs_Amount = Mth.ceil(player.getAbsorptionAmount());
        health.tot_Amount = health.red_Amount + health.abs_Amount;

        health.red_FullHearts = health.red_Amount / 2;
        health.red_Hearts = health.red_Amount / 2 + (health.red_Amount % 2);
        health.tot_Hearts = (health.red_Amount / 2 + (health.red_Amount % 2)) + (health.abs_Amount / 2 + (health.abs_Amount % 2));

        health.red_HasHalf = (health.red_Amount % 2) != 0;

        //Max healths
        health.red_MaxAmount = Mth.ceil(player.getMaxHealth());
        health.abs_MaxAmount = health.abs_Amount;
        health.tot_MaxAmount = health.red_MaxAmount + health.abs_MaxAmount;

        health.red_MaxHearts = health.red_MaxAmount / 2 + (health.red_MaxAmount % 2);
        health.tot_MaxHearts = (health.red_MaxAmount / 2 + (health.red_MaxAmount % 2)) + (health.abs_MaxAmount / 2 + (health.abs_MaxAmount % 2));

        //Utilities
        gui.barRows = (health.tot_MaxHearts + 9) / 10;
        gui.barRowsMenus2     = Math.max(0, gui.barRows - 2); // Heart rows amount, but it starts to count form the 3rd player's row.
        gui.spacing   = gui.spaceBetweenRowsMax - Math.min(gui.barRowsMenus2, gui.spaceBetweenRowsMin); //Space between rows

        gui.tickCount = mc.gui.getGuiTicks(); //In game ticks time (every second it increment by 20)
        gui.nowTick = Util.getMillis(); //In game current tick

        // Regeneration parameter
        regen.reg_visibleHearts = Mth.ceil((player.getMaxHealth() + player.getAbsorptionAmount()) / 2f); // UNKOWN
        regen.reg_ticksSinceStart = (int) (gui.tickCount - gui.regenStartTick); // UNKOWN
        regen.reg_waveDuration = regen.reg_visibleHearts * regen.reg_ticksPerHeart; //Duration of the wave like effect cause by the regeneration effect.

        //Modifiers
        if (gui.hideEmptyHearts){
            health.tot_MaxAmount = (health.red_Amount % 2 == 0) ? health.red_Amount : health.red_Amount + 1;
        }

        if (gui.collapseDifferentLifeTypes){
            if (health.red_HasHalf){
                int r = (((health.tot_Amount + 1) / 2) + 9) / 10;
                gui.barRows = Math.max(r, 2);
            }else{
                gui.barRows = ((health.tot_Hearts + 9) / 10);
            }
        }

        //When this variable is true and the player has a half heart, this block will fire
        //It adds 1 to the absorption variable. This is necessary since, when health bars are collapse, and the player has a half heart,
        //the second life type will shift one of its hearts back by 1, to fill the half heart. This would mean that the same life type would not render
        //1 health heart, or else, it would be hidden by the normal health current health heart. Better contact me to better understand this process
        if (gui.collapseDifferentLifeTypes && health.red_HasHalf){
            health.abs_Amount++;
        }

        //When this variable is true, this block will fire
        //Overrides the variable responsible for checking the player's health, to match the variable value.
        //The player will result to have an absorption half heart based on total health, and not on absorption health.
        if (gui.collapseDifferentLifeTypes){
            health.abs_HasHalf = (health.tot_Amount % 2) != 0; //Does the player have 1 half absorption heart?
        }else{
            health.abs_HasHalf = (health.abs_Amount % 2) != 0;
        }

        gui.abs_Slots     = Mth.ceil(health.abs_Amount / 2f); // Slots to render (1 slot is 1 heart)

        if (gui.lastTickCount < gui.tickCount - 1){
            gui.lastHealth = health.red_Amount;
            gui.displayHealth = health.red_Amount;
            gui.lastHealthTime = Util.getMillis();
            gui.lastTickCount = gui.tickCount;
            return;
        }
        gui.lastTickCount = gui.tickCount;

        //After check
        checks(player);
        playerVars.player_takingDamage = health.red_Amount < gui.displayHealth; //Is the player taking damage on this tick?
        playerVars.player_takingRegen = health.red_Amount > gui.displayHealth; //Is the player getting healed in this tick?
        playerVars.player_blinking = false; //Is the health bar blinking?

        if (gui.tickCount < gui.healthBlinkTime) {
            long elapsed = gui.tickCount - gui.blinkStartTick;
            playerVars.player_blinking = (elapsed / 3L) % 2L == 0L;
        }
        playerVars.player_blinking = playerVars.player_blinking || gui.regenerating > 0;

        gui.lastHealth = health.red_Amount;
        gui.regenerating --;


    }

    private void checks(Player player){
        HealthBarPersonalVariables.PlayerVariables playerVariables = HealthBarGlobalVariables.getPlayerVariables(player);

        // -- 1) Updating Blinking state
        updateBlinkingState(player, playerVariables);

        // -- 2) Calculating regeneration event
        regenerationEvent(player, playerVariables);

        // Refreshing
        refreshDisplayHealth(player, playerVariables);
    }

    //Aumenta la quantità di vita parziale fino alla vita precedente a quella che deve essere renderizzata, così da ottenere la location in cui renderizzarla.
    public void setPartialHealthAmountVariable(Player player, int healthNumber){
        HealthBarPersonalVariables.PlayerVariables playerVariables = HealthBarGlobalVariables.getPlayerVariables(player);
        HealthBarPersonalVariables.PlayerVariables.HealthValues health = playerVariables.health;
        int localNormalLifeNumber = health.red_LifeNumber;
        int localAbsorptionLifeNumber = health.abs_LifeNumber;
        int returnAmount = 0;

        if (localNormalLifeNumber < healthNumber){
            returnAmount += localNormalLifeNumber;
        }

        if (localAbsorptionLifeNumber < healthNumber){
            returnAmount += localAbsorptionLifeNumber;
        }
        for (int life : health.extraLifeNumbers) {
            if (life < healthNumber) {
                returnAmount += life;
            }
        }
        health.partialHealthAmount = returnAmount;

    }

    //Complex method version
    //Contains all the rendering system itself
    public void renderHealthBar(RenderGuiLayerEvent.Pre event, Player player, HealthBarPersonalVariables.PlayerVariables playerVars) {

        final RandomSource random = RandomSource.create(); // UNKOWN
        HealthBarPersonalVariables.PlayerVariables.HealthValues health = playerVars.health;
        HealthBarPersonalVariables.PlayerVariables.GuiValues gui = playerVars.gui;


        // RENDERING HEALTH LAYER

            // INITIALIZING VARIABLES
       //     HealthBarVariables.isHardcore = player.level().getLevelData().isHardcore(); //Hardcore variable matches the world hardcore difficulty

            // Health and absorption
       //     int health = Mth.ceil(player.getHealth()); //Current player's health
       //     int maxHealth = Mth.ceil(player.getMaxHealth()); //Current player's max health
      //      int absorption = Mth.ceil(player.getAbsorptionAmount()); //Current player's absorption health amount
      //      int totalHealth = health + absorption; //Total player's health (normal + absorption)


        //If empty hearts are hidden, this block will fire, reducing maxHealth variable to the current hearts of player, plus 1 in case it is uneven.
        //This way the container will only be rendered under the full and half hearts of the health bar.
        /*
        if (HealthBarVariables.hideEmptyHearts){
                maxHealth = (health % 2 == 0) ? health : health + 1;
            }
         */



            // Variables: Hearts and rows
      //     int fullCurrentHearts = health / 2; //Current full hearts amount
      //     boolean hasHalfHeart = (health % 2) != 0; //Does the player have a half heart?
      //     int maxHearts = maxHealth / 2 + (maxHealth % 2); //Max hearts amount
      //     int currentHearts = health / 2 + (health % 2); //Current normal health hearts amount
      //     int totalMaxHearts = (maxHealth / 2 + (maxHealth % 2)) + (absorption / 2 + (absorption % 2)); //Total Max hearts amount (max normal health + absorption health)
      //     int totalCurrentHearts = (health / 2 + (health % 2)) + (absorption / 2 + (absorption % 2)); //Total current hearts amount (normal health + absorption)
      //     int rows      = (totalMaxHearts + 9) / 10; //Player's hearts rows amount (every row is 10 hearts by vanilla)

            //If this variable is true, this block will fire
            //A bunch of calculation to reduce the player's health bars to 1 unique health bar
     //       if (HealthBarVariables.collapseDifferentLifeTypes){
     //           if (hasHalfHeart){
     //               int r = (((totalHealth + 1) / 2) + 9) / 10;
     //               rows = Math.max(r, 2);
     //           }else{
     //               rows = ((totalCurrentHearts + 9) / 10);
     //           }
     //       }
     //      int extraRows     = Math.max(0, rows - 2); // Heart rows amount, but it starts to count form the 3rd player's row.
     //      int spacing   = HealthBarVariables.spaceBetweenRowsMax - Math.min(extraRows, HealthBarVariables.spaceBetweenRowsMin); //Space between rows

            // Positioning and graphics
            GuiGraphics gfx = event.getGuiGraphics(); //Graphic
     //       int height = gfx.guiHeight(); //Heigh of the Minecraft window
     //       HealthBarVariables.startY = height - mc.gui.leftHeight; // Y start point, used to draw the health bar.
     //       int width = gfx.guiWidth(); //Width of the Minecraft window
     //       HealthBarVariables.startX = width / 2 - 91; //X start point, used to draw the health bar.
//
     //       // Time and animations
     //       long tickCount = mc.gui.getGuiTicks(); //In game ticks time (every second it increment by 20)
     //       long now = Util.getMillis(); //In game current tick
//
             // Regeneration parameter
       //      int ticksPerHeart = HealthBarVariables.regenAnimationSpeed; //Ticks a heart should remain lifted
       //      int visibleHearts = Mth.ceil((player.getMaxHealth() + player.getAbsorptionAmount()) / 2f); // UNKOWN
       //      int ticksSinceStart = (int) (tickCount - regenStartTick); // UNKOWN
       //      int waveDuration = visibleHearts * ticksPerHeart; //Duration of the wave like effect cause by the regeneration effect.
       //      int cooldown = HealthBarVariables.regenAnimationCooldown; //Rest time between wave cycles
       //      int yOffset = HealthBarVariables.regenAnimationYoffSet; //How much a heart should lift, in pixels
             random.setSeed(gui.tickCount * 312871L); //Used to draw the wiggling effect caused by low health

    //    // UNKOWN
    //        if (lastTickCount < tickCount - 1){
    //            lastHealth = health;
    //            displayHealth = health;
    //            lastHealthTime = Util.getMillis();
    //            lastTickCount = tickCount;
    //            return;
    //        }
    //    lastTickCount = tickCount;



            // -- 0) Building textures
            //buildTextures(player, playerVars); //Non deve essere universalizzato

            // -- 1) Updating Blinking state
    //       updateBlinkingState(health, now, tickCount, player);

    //       // -- 2) Calculating regeneration event
    //       regenerationEvent(cooldown, ticksSinceStart, waveDuration, ticksPerHeart, tickCount, player);

    //       // Refreshing
    //       refreshDisplayHealth(now, health);


      //      boolean takingDamage = health < displayHealth; //Is the player taking damage on this tick?
      //      boolean takingRegen = health > displayHealth; //Is the player getting healed in this tick?
      //      boolean blinking = false; //Is the health bar blinking?
            // UNKOWN
    //        if (tickCount < healthBlinkTime) {
    //            long elapsed = tickCount - blinkStartTick;
    //            blinking = (elapsed / 3L) % 2L == 0L;
    //        }
    //        blinking = blinking || regenerating > 0;
//
    //        lastHealth = health;
    //        regenerating --;


        //When this variable is true and the player has a half heart, this block will fire
        //It adds 1 to the absorption variable. This is necessary since, when health bars are collapse, and the player has a half heart,
        //the second life type will shift one of its hearts back by 1, to fill the half heart. This would mean that the same life type would not render
        //1 health heart, or else, it would be hidden by the normal health current health heart. Better contact me to better understand this process
 //      if (HealthBarVariables.collapseDifferentLifeTypes && hasHalfHeart){
 //          absorption++;
 //      }

 //       int absorbSlots     = Mth.ceil(absorption / 2f); // UNKOWN
 //       boolean absorptionHalf; //Does the player have 1 half absorption heart?
 //       //When this variable is true, this block will fire
 //       //Overrides the variable responsible for checking the player's health, to match the variable value.
 //       //The player will result to have an absorption half heart based on total health, and not on absorption health.
 //       if (HealthBarVariables.collapseDifferentLifeTypes){
 //           absorptionHalf = (totalHealth % 2) != 0;
 //       }else{
 //           absorptionHalf = (absorption % 2) != 0;
 //       }

        int height;
        int width;
        int startY;
        int startX;

        if (gui.height == -9999) height = gfx.guiHeight(); //Heigh of the Minecraft window
        else height = gui.height;

        if (gui.width == -9999) width = gfx.guiWidth(); //Width of the Minecraft window
        else width = gui.width;

        if (gui.startY == -9999) startY = height - mc.gui.leftHeight; // Y start point, used to draw the health bar.
        else startY = gui.startY;

        if (gui.startX == -9999) startX = width / 2 - 91; //X start point, used to draw the health bar.
        else startX = gui.startX;


        //Normal life type render CYCLE
        renderNormalHeartCycle(player, playerVars, random, gfx, startY, startX);

        //Rendering absorption hearts CYCLE
        renderAbsorptionHeartsCycle(player, playerVars, gfx, startY, startX);

            //Y start point is fixed to allow the armor bar to correctly render.
                fixArmors(playerVars);

            //Soma variables are reset
        //reset(player, playerVars);
    }

    /**
     *Last change in version: 1.1.4-neoforge
     */
    //Contains the cycle to render the absorption life type hearts
    @Deprecated
    public void renderAbsorptionHeartsCycle(Player player, HealthBarPersonalVariables.PlayerVariables playerVars, GuiGraphics gfx, int startY, int startX){
        HealthBarPersonalVariables.PlayerVariables.HealthValues health = playerVars.health;
        HealthBarPersonalVariables.PlayerVariables.GuiValues gui = playerVars.gui;

        for (int j = gui.abs_Slots - 1; j >= 0; j--) {
            int slotIndex = health.tot_MaxHearts + j; //The slot is going to be rendered. It starts from maxHearts because those are the normal life slots.

            //When this variable is true, this block will fire
            //Because life types will collapse, it is no longer needed to base the slotIndex on maxHearts, but it is needed to base on currentHearts.
            if (gui.collapseDifferentLifeTypes){
                slotIndex = health.red_Hearts + j;
            }

            int line = slotIndex / 10; //Indicates in which row this slot is being rendered
            int col  = (slotIndex % 10); //Indicated in which column this slot is being rendered


            boolean halfAbs = health.abs_HasHalf && j == gui.abs_Slots - 1; //Does the player have an half absorption heart? Calculated on the last available absorption
            //heart, since it is the only one capable of being halved.

            //When this variable is true and the player has an half normal life type heart, this block will fire
            //All variables such as col and line are re inizialized to match the changing health bar render system
            if (gui.collapseDifferentLifeTypes && health.red_HasHalf){
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
            int localSpacing = line * gui.spacing; //This spacing is based on the lines calculated on the health shift

            int x = startX + col * 8; // X start render point (every slot will have its own)
            int y = startY - localSpacing; //Y start render point (every slot will have its own)

            //When this variable is true and the player has an half normal life type heart, this block will fire
            //halfAbs will be based on
            if (gui.collapseDifferentLifeTypes && health.red_HasHalf){
                halfAbs = health.abs_HasHalf && j == gui.abs_Slots - 1;
                //If the player has at least 2 absorption hearts (1 isnt considered since it is used just for rendering the half heart) and
                //the is not being rendered the first slot (j > 0) or the player has and half normal heart, this block will fire
                if (health.abs_Amount-1 > 0 && (j > 0 || health.red_HasHalf)){
                    int key = line * 10 + col; //The slot value
                    gui.absorptionSlotsList.add(key); //The slot this happens to be true will be saved inside the absorptionSlots list, that will be later used.
                    renderAbsorption(player, playerVars, gfx, x, y); //Finally the absorption heart is rendered
                }
            }else{ //This will basically fire when the player does not have a full heart.
                int key = line * 10 + col; //The slot value
                gui.absorptionSlotsList.add(key);//The slot will be saved inside the absorptionSlots list, that will be later used.
                renderAbsorption(player, playerVars, gfx, x, y); //Finally the absorption heart is rendered
            }

        }
        playerVars.syncPlayerVariables(player);

    }
    /**
     *Last change in version: 1.1.4-neoforge
     */
    //Contains the for cycle to render the normal life type hearts
    @Deprecated
    public void renderNormalHeartCycle(Player player, HealthBarPersonalVariables.PlayerVariables playerVars, RandomSource random, GuiGraphics gfx, int startY, int startX) {
        HealthBarPersonalVariables.PlayerVariables.HealthValues health = playerVars.health;
        HealthBarPersonalVariables.PlayerVariables.GuiValues gui = playerVars.gui;
        HealthBarPersonalVariables.PlayerVariables.RegenEventValues regen = playerVars.regen;

        for (int i = health.tot_MaxHearts - 1; i >= 0; i--) {
            int line = i / 10; //The line in which this particular slot is in
            int col  = i % 10; //The column in which this particular slot is in
            int x = startX + (i % 10) * 8; //X start render point
            int y = startY - line * gui.spacing; //Y start render point

            if (health.red_Amount + health.abs_Amount <= 4) y += random.nextInt(2); //If the player has low health, the Y value is randomized to mimic the wiggling effect.
            if (i == gui.regenIndex) y += regen.reg_yOffset; //If the player has regeneration, the Y value is addition to the y regen offset, to mimic the wave effect


            //If this variable is true, this block will fire
            //It checks for slots with the same value as the absorption slots, saved before inside the absorptionSlotsList list.
            if (gui.collapseDifferentLifeTypes){
                int key = line * 10 + col; //Current slot value
                //If the current slot value does not match any of the value inside the absorptionSlotsList list, this block will fire
                if (!gui.absorptionSlotsList.contains(key)) {
                    renderContainer(playerVars, gfx, x, y); //The container is rendered
                }
                //Concluding, the container is rendered only in those slots that are not the absorption slots. It is this way because the container would be
                //rendered over the absorption, hiding them. This is why is important to NOT render containers on the same slots as the absorption.
            }else{
                renderContainer(playerVars, gfx, x, y); //The container is rendered
            }

            // Blinking hearts are rendered (caused by damage)
            renderBlinking(player, playerVars, gfx, i, x, y);

            // Normale hearts are rendered
            renderHeart(player, playerVars, gfx, i, x, y);

        }
        //There is no need to sync since no change has been made

    }

    //Set all necessary variable responsible for heart rendering
    @Deprecated
    public void buildTextures(Player player, HealthBarPersonalVariables.PlayerVariables playerVariables) {
        HealthBarPersonalVariables.PlayerVariables.ResourceValues resources = playerVariables.resources;
        if (resources.CONTAINER != null) resources.CONTAINER.buildResources(player, playerVariables);
        else HealthBarGlobalVariables.buildResourcesWithVanilla(HealthTypes.VanillaHeartTypes.CONTANER, player);

        if (resources.HEARTS != null) resources.HEARTS.buildResources(player, playerVariables);
        else HealthBarGlobalVariables.buildResourcesWithVanilla(HealthTypes.VanillaHeartTypes.NORMAL, player);

        if (resources.POISONED_HEARTS != null) resources.POISONED_HEARTS.buildResources(player, playerVariables);
        else HealthBarGlobalVariables.buildResourcesWithVanilla(HealthTypes.VanillaHeartTypes.POISONED, player);

        if (resources.WITHERED_HEARTS != null) resources.WITHERED_HEARTS.buildResources(player, playerVariables);
        else HealthBarGlobalVariables.buildResourcesWithVanilla(HealthTypes.VanillaHeartTypes.WITHERED, player);

        if (resources.FROZEN_HEARTS != null) resources.FROZEN_HEARTS.buildResources(player, playerVariables);
        else HealthBarGlobalVariables.buildResourcesWithVanilla(HealthTypes.VanillaHeartTypes.FROZEN, player);

        if (resources.ABSORBING_HEARTS != null) resources.ABSORBING_HEARTS.buildResources(player, playerVariables);
        else HealthBarGlobalVariables.buildResourcesWithVanilla(HealthTypes.VanillaHeartTypes.ABSORBING, player);

        //There is no need to sync since the syncing is made inside buildResourcesWithVanilla and inside buildResources
    }

    //Render the container, heart background.
    @Deprecated
    public void renderContainer(HealthBarPersonalVariables.PlayerVariables playerVariables, GuiGraphics gfx, int x, int y) {
        ResourceLocation heartsBackground;
        HealthBarPersonalVariables.PlayerVariables.ResourceValues resources = playerVariables.resources;

    if (playerVariables.isHardcore) {
        if (playerVariables.player_blinking){
            heartsBackground = playerVariables.player_takingRegen ?  resources.hardcore_container_blinking_healing : resources.hardcore_container_blinking_damage;
        }
        else {
            heartsBackground = resources.hardcore_container;
        }
        } else {
        if (playerVariables.player_blinking){
            heartsBackground = playerVariables.player_takingRegen ?  resources.container_blinking_healing : resources.container_blinking_damage;
        }
        else{
            heartsBackground = resources.container;
        }
        }
    //There is no need to sync since no change has been made

        RenderSystem.setShaderTexture(0, heartsBackground);
        gfx.blit(heartsBackground, x, y, 0, 0, 9, 9, 9, 9);
    }
    //Render the blinking hearts, cause by: taking damage
    @Deprecated
    public void renderBlinking(Player player, HealthBarPersonalVariables.PlayerVariables playerVariables, GuiGraphics gfx, int i, int x, int y) {
        HealthBarPersonalVariables.PlayerVariables.HealthValues health = playerVariables.health;
        HealthBarPersonalVariables.PlayerVariables.GuiValues gui = playerVariables.gui;
        if (playerVariables.player_blinking && playerVariables.player_takingDamage) {
            float oldHealth = Math.min(gui.displayHealth, health.red_MaxAmount);

            float slotMin = i * 2f;
            float slotMid = slotMin + 1f;
            float slotMax = slotMin + 2f;

            ResourceLocation blinkTex = null;

            if (oldHealth >= slotMax && health.red_Amount < slotMax) {
                blinkTex = HealthBarGlobalVariables.getSprite(player, playerVariables.isHardcore, false, true, false);
            } else if (oldHealth >= slotMid && health.red_Amount < slotMid) {
                blinkTex = HealthBarGlobalVariables.getSprite(player, playerVariables.isHardcore, true, true, false);
            }

            if (blinkTex != null) {
                RenderSystem.setShaderTexture(0, blinkTex);
                gfx.blit(blinkTex, x, y, 0, 0, 9, 9, 9, 9);
            }
        }
        //No syncing since no change has been made
    }
    //Render the absorption hearts
    @Deprecated
    public void renderAbsorption(Player player, HealthBarPersonalVariables.PlayerVariables playerVariables, GuiGraphics gfx, int x, int y) {
        HealthBarPersonalVariables.PlayerVariables.HealthValues health = playerVariables.health;
        HealthBarPersonalVariables.PlayerVariables.ResourceValues resources = playerVariables.resources;

        // scegli la texture di assorbimento
        ResourceLocation bg = resources.container;
        RenderSystem.setShaderTexture(0, bg);
        gfx.blit(bg, x, y, 0, 0, 9,9, 9,9);


        ResourceLocation texA = health.red_HasHalf
                ? HealthBarGlobalVariables.getSprite(player, playerVariables.isHardcore, true, false, true)
                : HealthBarGlobalVariables.getSprite(player, playerVariables.isHardcore, false, false, true);

        // disegna cuore di assorbimento
        RenderSystem.setShaderTexture(0, texA);
        gfx.blit(texA, x, y, 0, 0, 9,9, 9,9);

        //No need to sync
    }
    //Render the normal hearts
    @Deprecated
    public void renderHeart(Player player, HealthBarPersonalVariables.PlayerVariables playerVariables, GuiGraphics gfx, int i, int x, int y) {
        HealthBarPersonalVariables.PlayerVariables.HealthValues health = playerVariables.health;
        ResourceLocation heartTex = null;
        if (i < health.red_FullHearts) {
            heartTex = HealthBarGlobalVariables.getSprite(player, playerVariables.isHardcore, false, false, false);
        } else if (i == health.red_FullHearts && health.red_HasHalf) {
            heartTex = HealthBarGlobalVariables.getSprite(player, playerVariables.isHardcore, true, false, false);
        }

        if (heartTex != null) {
            RenderSystem.setShaderTexture(0, heartTex);
            gfx.blit(heartTex, x, y, 0, 0, 9, 9, 9, 9);
        }

        //No need to sync since no change has been made
    }

    //Update the blinking state to simulate vanilla blinking animation
    @Deprecated
    public void updateBlinkingState(Player player, HealthBarPersonalVariables.PlayerVariables playerVariables) {
        HealthBarPersonalVariables.PlayerVariables.HealthValues health = playerVariables.health;
        HealthBarPersonalVariables.PlayerVariables.GuiValues gui = playerVariables.gui;

        if (health.red_Amount < gui.lastHealth && player.invulnerableTime > 0) {
            gui.lastHealthTime = gui.nowTick;
            gui.healthBlinkTime = gui.tickCount + 18;
            gui.blinkStartTick = gui.tickCount;
        } else if (health.red_Amount > gui.lastHealth) {
            gui.regenerating = 5;
            gui.lastHealthTime = gui.nowTick;
            gui.healthBlinkTime = gui.tickCount + 6 + gui.regenerating;
            gui.blinkStartTick = gui.tickCount;
        }

        playerVariables.syncPlayerVariables(player); //Syncing
    }
    //Check if the player has regeneration effect and, based on that, activate the regeneration animations
    @Deprecated
    public void regenerationEvent(Player player, HealthBarPersonalVariables.PlayerVariables playerVariables) {
        HealthBarPersonalVariables.PlayerVariables.GuiValues gui = playerVariables.gui;
        HealthBarPersonalVariables.PlayerVariables.RegenEventValues regen = playerVariables.regen;

        if (!player.hasEffect(MobEffects.REGENERATION)) {
            gui.regenIndex = -1;
            return;
        }

        if (regen.reg_ticksSinceStart < regen.reg_waveDuration) {
            // siamo ancora all’interno dell’onda
            gui.regenIndex = regen.reg_ticksSinceStart / regen.reg_ticksPerHeart;
        } else if (regen.reg_ticksSinceStart < regen.reg_waveDuration + regen.reg_cooldown) {
            // in pausa: nessun cuore saltella
            gui.regenIndex = -1;
        } else {
            // onda e cooldown completati → resetta per la prossima onda
            gui.regenStartTick = gui.tickCount;
            gui.regenIndex      = -1;
        }

        playerVariables.syncPlayerVariables(player); //Syncing

    }

    //Refresh health value to match the current
    @Deprecated
    public void refreshDisplayHealth(Player player, HealthBarPersonalVariables.PlayerVariables playerVariables) {
        HealthBarPersonalVariables.PlayerVariables.GuiValues gui = playerVariables.gui;
        HealthBarPersonalVariables.PlayerVariables.HealthValues health = playerVariables.health;

        if (gui.nowTick - gui.lastHealthTime > 1000L) {
            gui.displayHealth = health.red_Amount;
            gui.lastHealthTime = gui.nowTick;
            playerVariables.syncPlayerVariables(player); //Syncing
        }
    }

    //Fix the Y start point to allow the correct rendering of the armor bar
    @Deprecated
    public void fixArmors(HealthBarPersonalVariables.PlayerVariables playerVariables){
        HealthBarPersonalVariables.PlayerVariables.GuiValues gui = playerVariables.gui;
        int armorHight;
        armorHight = 49 + (gui.barRows - 1) * gui.spacing;

        Minecraft mc = Minecraft.getInstance();
        mc.gui.leftHeight = armorHight;

        //No need to sync
    }

    //Reset variables to allow for other values to be used.
    @Deprecated
    public void reset(Player player, HealthBarPersonalVariables.PlayerVariables playerVariables){
        HealthBarPersonalVariables.PlayerVariables.ResourceValues resources = playerVariables.resources;
        HealthBarPersonalVariables.PlayerVariables.RegenEventValues regen = playerVariables.regen;
        HealthBarPersonalVariables.PlayerVariables.GuiValues gui = playerVariables.gui;
        resources.CONTAINER = null;
        resources.HEARTS = null;
        resources.POISONED_HEARTS = null;
        resources.WITHERED_HEARTS = null;
        resources.FROZEN_HEARTS = null;
        resources.ABSORBING_HEARTS = null;

        gui.hideEmptyHearts = false;
        gui.collapseDifferentLifeTypes = false;

        regen.reg_ticksPerHeart = 1;
        regen.reg_cooldown = 15;
        regen.reg_cooldown = -2;

        gui.spaceBetweenRowsMax = 10;
        gui.spaceBetweenRowsMin = 7;

        playerVariables.syncPlayerVariables(player); //Syncing
    }

}

