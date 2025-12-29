package net.marios271.cat_vision;

import net.fabricmc.api.ClientModInitializer;
import net.marios271.cat_vision.config.ConfigData;
import net.marios271.cat_vision.event.DisconnectListener;
import net.marios271.cat_vision.event.EndTickListener;
import net.marios271.cat_vision.event.JoinListener;
import net.marios271.cat_vision.event.RespawnListener;
import net.marios271.cat_vision.handler.KeyInputHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatVision implements ClientModInitializer {
	public static final String MOD_ID = "cat_vision";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final ConfigData CONFIG = ConfigData.load();

	@Override
	public void onInitializeClient() {
		KeyInputHandler.register();

		JoinListener.register();
		DisconnectListener.register();
		RespawnListener.register();
		EndTickListener.register();

		LOGGER.info("Initialized CatVision");
	}
}