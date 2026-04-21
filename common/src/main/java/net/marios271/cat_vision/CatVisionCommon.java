package net.marios271.cat_vision;

import net.marios271.cat_vision.config.ConfigData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CatVisionCommon {
    public static final String MOD_ID = "cat_vision";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final String CONFIG_FILE = "cat_vision.json";

    private static ConfigData config;

    public static ConfigData getConfig() {
        if (config == null) config = ConfigData.load();
        return config;
    }
}
