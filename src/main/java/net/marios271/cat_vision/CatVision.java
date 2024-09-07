package net.marios271.cat_vision;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.marios271.cat_vision.idkwhatnametoput.CatVisionModConfig;
import net.marios271.cat_vision.idkwhatnametoput.KeyInputHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatVision implements ClientModInitializer {
	public static final String MOD_ID = "cat_vision";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final CatVisionModConfig CONFIG = CatVisionModConfig.createAndLoad();

	@Override
	public void onInitializeClient() {
		KeyInputHandler.register();

		LOGGER.info("Initialized " + MOD_ID);

		ClientPlayConnectionEvents.JOIN.register((arg1, arg2, arg3) -> {
			if (CONFIG.auto_nv()) { CONFIG.nv_status(true); }
			if (!CONFIG.remember_nv() && CONFIG.nv_status()) { CONFIG.nv_status(false); }

			if (CONFIG.nv_status()) { MinecraftClient.getInstance().player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, -1)); }
		});

		ServerPlayerEvents.AFTER_RESPAWN.register((arg1, arg2, arg3) -> {
			if (CONFIG.nv_status() || CONFIG.auto_nv()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException ie) {
					Thread.currentThread().interrupt();
				}

				MinecraftClient.getInstance().player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, -1));
			}
		});

		ClientTickEvents.END_WORLD_TICK.register(world -> {
			MinecraftClient client = MinecraftClient.getInstance();

			if (!client.player.hasStatusEffect(StatusEffects.NIGHT_VISION) && CONFIG.nv_status()) { client.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, -1)); }

			if (client.player.hasStatusEffect(StatusEffects.BLINDNESS) && CONFIG.blindness_immunity()) { client.player.removeStatusEffect(StatusEffects.BLINDNESS); }
			if (client.player.hasStatusEffect(StatusEffects.DARKNESS) && CONFIG.darkness_immunity()) { client.player.removeStatusEffect(StatusEffects.DARKNESS); }
			if (client.player.hasStatusEffect(StatusEffects.NAUSEA) && CONFIG.nausea_immunity()) { client.player.removeStatusEffect(StatusEffects.NAUSEA); }
		});
	}
}