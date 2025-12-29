package net.marios271.cat_vision;

import net.fabricmc.api.ClientModInitializer;
import net.marios271.cat_vision.config.CatVisionModConfig;
import net.marios271.cat_vision.event.EndTickListener;
import net.marios271.cat_vision.event.JoinListener;
import net.marios271.cat_vision.handler.KeyInputHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatVision implements ClientModInitializer {
	public static final String MOD_ID = "cat_vision";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final CatVisionModConfig CONFIG = CatVisionModConfig.createAndLoad();

	@Override
	public void onInitializeClient() {
		KeyInputHandler.register();

		JoinListener.register(CONFIG);
		//RespawnListener.register(CONFIG);
		EndTickListener.register(CONFIG);

		LOGGER.info("Initialized " + MOD_ID);
	}
}