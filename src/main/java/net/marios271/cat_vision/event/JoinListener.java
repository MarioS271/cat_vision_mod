package net.marios271.cat_vision.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.marios271.cat_vision.config.CatVisionModConfig;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class JoinListener {
    public static void register(CatVisionModConfig config) {
        ClientPlayConnectionEvents.JOIN.register((listener, sender, client) -> {
            if (client.player == null)
                return;

            if (config.auto_nv())
                config.nv_status(true);

            if (!config.remember_nv() && config.nv_status())
                config.nv_status(false);

            if (config.nv_status())
                client.player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, -1));
        });
    }
}