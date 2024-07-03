package net.marios271.cat_vision.config;

import com.mojang.datafixers.util.Pair;
import net.marios271.cat_vision.CatVision;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static Boolean NV_STATUS;
    public static Boolean REMEMBER_NV;
    public static Boolean AUTO_NV;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(CatVision.MOD_ID).provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("night_vision_status", false), "boolean");

        configs.addKeyValuePair(new Pair<>("remember_night_vision", true), "boolean");
        configs.addKeyValuePair(new Pair<>("automatically_enable_night_vision", false), "boolean");
    }

    private static void assignConfigs() {
        NV_STATUS = CONFIG.getOrDefault("night_vision_status", false);

        REMEMBER_NV = CONFIG.getOrDefault("remember_night_vision", true);
        AUTO_NV = CONFIG.getOrDefault("automatically_enable_night_vision", false);
    }
}