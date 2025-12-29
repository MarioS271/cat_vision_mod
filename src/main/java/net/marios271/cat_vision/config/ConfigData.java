package net.marios271.cat_vision.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.marios271.cat_vision.CatVision;

import java.io.*;

public class ConfigData {
    private static final File FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "cat_vision.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public boolean remember_nv = true;
    public boolean auto_nv = true;
    public boolean blindness_immunity = true;
    public boolean darkness_immunity = true;
    public boolean nausea_immunity = true;

    public boolean has_nv = false;

    public void save() {
        try (FileWriter writer = new FileWriter(FILE)) {
            GSON.toJson(this, writer);
            CatVision.LOGGER.info("Saved CatVision config");
        } catch (IOException exception) {
            CatVision.LOGGER.error("Failed to save config", exception);
        }
    }

    public static ConfigData load() {
        if (FILE.exists()) {
            try (FileReader reader = new FileReader(FILE)) {
                CatVision.LOGGER.info("Loaded CatVision config");
                return GSON.fromJson(reader, ConfigData.class);
            } catch (IOException exception) {
                CatVision.LOGGER.warn("Failed to load config, returning default values");
            }
        }
        return new ConfigData();
    }
}
