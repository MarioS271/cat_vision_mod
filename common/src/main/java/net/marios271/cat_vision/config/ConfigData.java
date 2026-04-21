package net.marios271.cat_vision.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.marios271.cat_vision.CatVisionCommon;
import net.marios271.cat_vision.platform.CatVisionPlatform;

import java.io.*;

public class ConfigData {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public boolean remember_nv = true;
    public boolean auto_nv = true;
    public boolean blindness_immunity = true;
    public boolean darkness_immunity = true;
    public boolean nausea_immunity = true;
    public boolean has_nv = false;

    public void save() {
        File file = CatVisionPlatform.getConfigDir().resolve(CatVisionCommon.CONFIG_FILE).toFile();
        try (FileWriter writer = new FileWriter(file)) {
            GSON.toJson(this, writer);
            CatVisionCommon.LOGGER.info("Saved CatVision config");
        } catch (IOException e) {
            CatVisionCommon.LOGGER.error("Failed to save config", e);
        }
    }

    public static ConfigData load() {
        File file = CatVisionPlatform.getConfigDir().resolve(CatVisionCommon.CONFIG_FILE).toFile();
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                CatVisionCommon.LOGGER.info("Loaded CatVision config");
                return GSON.fromJson(reader, ConfigData.class);
            } catch (IOException e) {
                CatVisionCommon.LOGGER.warn("Failed to load config, using defaults");
            }
        }
        return new ConfigData();
    }
}
