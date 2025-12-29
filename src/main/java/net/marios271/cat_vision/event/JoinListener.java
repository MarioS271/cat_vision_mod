package net.marios271.cat_vision.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.marios271.cat_vision.CatVision;
import net.marios271.cat_vision.config.ConfigData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class JoinListener {
    public static void register() {
        ClientPlayConnectionEvents.JOIN.register((listener, sender, client) -> {
            if (client.player == null)
                return;

            ConfigData config = CatVision.CONFIG;

            if (config.auto_nv || (config.remember_nv && config.has_nv))
                config.has_nv = true;
            else
                config.has_nv = false;

            if (config.has_nv)
                client.player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, -1));
        });
    }
}