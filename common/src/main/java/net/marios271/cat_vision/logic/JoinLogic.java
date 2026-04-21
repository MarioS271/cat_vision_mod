package net.marios271.cat_vision.logic;

import net.marios271.cat_vision.CatVisionCommon;
import net.marios271.cat_vision.config.ConfigData;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public final class JoinLogic {
    public static void onJoin(Minecraft client) {
        if (client.player == null) return;
        ConfigData config = CatVisionCommon.getConfig();
        config.has_nv = config.auto_nv || (config.remember_nv && config.has_nv);
        if (config.has_nv)
            client.player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, -1));
    }
}
