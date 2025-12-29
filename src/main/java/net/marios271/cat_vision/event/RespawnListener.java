package net.marios271.cat_vision.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.marios271.cat_vision.CatVision;
import net.marios271.cat_vision.config.ConfigData;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class RespawnListener {
    public static void register() {
        ClientEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (!(entity instanceof LocalPlayer player))
                return;

            ConfigData config = CatVision.CONFIG;

            if (config.has_nv)
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, -1));
        });
    }
}