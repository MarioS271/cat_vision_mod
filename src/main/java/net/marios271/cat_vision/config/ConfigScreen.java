package net.marios271.cat_vision.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ConfigScreen {
    public static Screen create(Screen parent, ConfigData config) {
        ConfigBuilder builder = ConfigBuilder.create()
            .setParentScreen(parent)
            .setTitle(Component.translatable("text.cat_vision.config.option.title"));

        ConfigCategory category = builder.getOrCreateCategory(Component.translatable("text.cat_vision.config.category"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        // Remember NV
        category.addEntry(entryBuilder.startBooleanToggle(Component.translatable("text.cat_vision.config.option.remember_nv"), config.remember_nv)
            .setDefaultValue(true)
            .setSaveConsumer((new_value) -> config.remember_nv = new_value)
            .build());

        // Automatic NV
        category.addEntry(entryBuilder.startBooleanToggle(Component.translatable("text.cat_vision.config.option.auto_nv"), config.auto_nv)
            .setDefaultValue(true)
            .setSaveConsumer((new_value) -> config.auto_nv = new_value)
            .build());

        // Blindness Immunity
        category.addEntry(entryBuilder.startBooleanToggle(Component.translatable("text.cat_vision.config.option.blindness_immunity"), config.blindness_immunity)
            .setDefaultValue(true)
            .setSaveConsumer((new_value) -> config.blindness_immunity = new_value)
            .build());

        // Darkness Immunity
        category.addEntry(entryBuilder.startBooleanToggle(Component.translatable("text.cat_vision.config.option.darkness_immunity"), config.darkness_immunity)
            .setDefaultValue(true)
            .setSaveConsumer((new_value) -> config.darkness_immunity = new_value)
            .build());

        // Nausea Immunity
        category.addEntry(entryBuilder.startBooleanToggle(Component.translatable("text.cat_vision.config.option.nausea_immunity"), config.nausea_immunity)
            .setDefaultValue(true)
            .setSaveConsumer((new_value) -> config.nausea_immunity = new_value)
            .build());

        builder.setSavingRunnable(config::save);

        return builder.build();
    }
}