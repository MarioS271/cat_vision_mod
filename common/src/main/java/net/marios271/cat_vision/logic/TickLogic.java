package net.marios271.cat_vision.logic;

import net.marios271.cat_vision.CatVisionCommon;
import net.marios271.cat_vision.config.ConfigData;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public final class TickLogic {
    public static void onEndTick(Minecraft client) {
        if (client.player == null) return;
        ConfigData config = CatVisionCommon.getConfig();

        if (!client.player.hasEffect(MobEffects.NIGHT_VISION) && config.has_nv)
            client.player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, -1));
        if (client.player.hasEffect(MobEffects.BLINDNESS) && config.blindness_immunity)
            client.player.removeEffect(MobEffects.BLINDNESS);
        if (client.player.hasEffect(MobEffects.DARKNESS) && config.darkness_immunity)
            client.player.removeEffect(MobEffects.DARKNESS);
        if (client.player.hasEffect(MobEffects.NAUSEA) && config.nausea_immunity)
            client.player.removeEffect(MobEffects.NAUSEA);
    }
}
