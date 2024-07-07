package net.marios271.cat_vision;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.marios271.cat_vision.config.ModConfigs;
import net.marios271.cat_vision.event.KeyInputHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatVision implements ClientModInitializer {
	public static final String MOD_ID = "cat_vision";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		ModConfigs.registerConfigs();
		KeyInputHandler.register();

		LOGGER.info("Initialized " + MOD_ID);

		ClientPlayConnectionEvents.JOIN.register((arg1, arg2, arg3) -> {
			ClientPlayerEntity player = MinecraftClient.getInstance().player;

			if (!ModConfigs.REMEMBER_NV) { ModConfigs.NV_STATUS = false; }
			if (ModConfigs.AUTO_NV) { ModConfigs.NV_STATUS = true; }

			if (ModConfigs.NV_STATUS) { MinecraftClient.getInstance().player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, -1)); }
		});

		ServerPlayerEvents.AFTER_RESPAWN.register((arg1, arg2, arg3) -> {
			if (ModConfigs.NV_STATUS) {
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

			if (!client.player.hasStatusEffect(StatusEffects.NIGHT_VISION) && ModConfigs.NV_STATUS) { client.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, -1)); }

			if (client.player.hasStatusEffect(StatusEffects.BLINDNESS) && ModConfigs.BLINDNESS_IMMUNITY) { client.player.removeStatusEffect(StatusEffects.BLINDNESS); }
			if (client.player.hasStatusEffect(StatusEffects.DARKNESS) && ModConfigs.DARKNESS_IMMUNITY) { client.player.removeStatusEffect(StatusEffects.DARKNESS); }
			if (client.player.hasStatusEffect(StatusEffects.NAUSEA) && ModConfigs.NAUSEA_IMMUNITY) { client.player.removeStatusEffect(StatusEffects.NAUSEA); }
		});
	}
}