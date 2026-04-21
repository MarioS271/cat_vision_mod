package net.marios271.cat_vision.logic;

import net.marios271.cat_vision.CatVisionCommon;
import net.marios271.cat_vision.config.ConfigData;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public final class RespawnLogic {
    public static void onRespawn(LocalPlayer player) {
        ConfigData config = CatVisionCommon.getConfig();
        if (config.has_nv)
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, -1));
    }
}
