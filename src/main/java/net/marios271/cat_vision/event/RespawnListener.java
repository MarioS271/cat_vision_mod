package net.marios271.cat_vision.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.marios271.cat_vision.config.CatVisionModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class RespawnListener {
    public static void register(CatVisionModConfig config) {
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            Minecraft client = Minecraft.getInstance();
            if (client.player == null)
                return;

            if (config.nv_status() || config.auto_nv())
                client.player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, -1));
        });
    }
}