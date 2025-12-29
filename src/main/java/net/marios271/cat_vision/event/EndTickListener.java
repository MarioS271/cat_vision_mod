package net.marios271.cat_vision.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.marios271.cat_vision.config.CatVisionModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class EndTickListener {
    public static void register(CatVisionModConfig config) {
        ClientTickEvents.END_WORLD_TICK.register(world -> {
            Minecraft client = Minecraft.getInstance();
            if (client.player == null)
                return;

            if (!client.player.hasEffect(MobEffects.NIGHT_VISION) && config.nv_status())
                client.player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, -1));

            if (client.player.hasEffect(MobEffects.BLINDNESS) && config.blindness_immunity())
                client.player.removeEffect(MobEffects.BLINDNESS);

            if (client.player.hasEffect(MobEffects.DARKNESS) && config.darkness_immunity())
                client.player.removeEffect(MobEffects.DARKNESS);

            if (client.player.hasEffect(MobEffects.NAUSEA) && config.nausea_immunity())
                client.player.removeEffect(MobEffects.NAUSEA);
        });
    }
}
