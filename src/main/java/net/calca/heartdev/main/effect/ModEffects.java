package net.calca.heartdev.main.effect;

import net.calca.heartdev.HeartDev;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, HeartDev.MOD_ID);

    public static final Holder<MobEffect> ORANGE_HEARTS = MOB_EFFECTS.register("orange_hearts",
            () -> new ColorfullHeartsEffect(MobEffectCategory.NEUTRAL, 0xFF8000 ));

    public static final Holder<MobEffect> YELLOW_HEARTS = MOB_EFFECTS.register("yellow_hearts",
            () -> new ColorfullHeartsEffect(MobEffectCategory.NEUTRAL, 0xFFFF00 ));

    public static final Holder<MobEffect> GREEN_HEARTS = MOB_EFFECTS.register("green_hearts",
            () -> new ColorfullHeartsEffect(MobEffectCategory.NEUTRAL, 0x80FF00 ));

    public static final Holder<MobEffect> LIGHT_BLUE_HEARTS = MOB_EFFECTS.register("light_blue_hearts",
            () -> new ColorfullHeartsEffect(MobEffectCategory.NEUTRAL, 0x00C8FF ));

    public static final Holder<MobEffect> BLUE_HEARTS = MOB_EFFECTS.register("blue_hearts",
            () -> new ColorfullHeartsEffect(MobEffectCategory.NEUTRAL, 0x0000FF ));

    public static final Holder<MobEffect> PURPLE_HEARTS = MOB_EFFECTS.register("purple_hearts",
            () -> new ColorfullHeartsEffect(MobEffectCategory.NEUTRAL, 0x8000FF ));

    public static final Holder<MobEffect> MAGENTA_HEARTS = MOB_EFFECTS.register("magenta_hearts",
            () -> new ColorfullHeartsEffect(MobEffectCategory.NEUTRAL, 0xFF00FF ));


    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}
