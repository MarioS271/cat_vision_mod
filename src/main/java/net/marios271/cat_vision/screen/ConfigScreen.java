package net.marios271.cat_vision.screen;

import com.terraformersmc.modmenu.gui.ModsScreen;
import net.marios271.cat_vision.config.ModConfigs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;

public class ConfigScreen extends Screen {
    public ConfigScreen() { super(Text.translatable("gui_title.cat_vision.config")); }

    @Override
    protected void init() {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

        Text btn_remember_nv = Text.translatable("button.cat_vision.config_enabled");
        if (!ModConfigs.REMEMBER_NV) { btn_remember_nv = Text.translatable("button.cat_vision.config_disabled"); }
        Text btn_auto_nv = Text.translatable("button.cat_vision.config_disabled");
        if (ModConfigs.AUTO_NV) { btn_auto_nv = Text.translatable("button.cat_vision.config_enabled"); }
        Text btn_blindness_immunity = Text.translatable("button.cat_vision.config_enabled");
        if (!ModConfigs.BLINDNESS_IMMUNITY) { btn_blindness_immunity = Text.translatable("button.cat_vision.config_disabled"); }
        Text btn_darkness_immunity = Text.translatable("button.cat_vision.config_enabled");
        if (!ModConfigs.DARKNESS_IMMUNITY) { btn_darkness_immunity = Text.translatable("button.cat_vision.config_disabled"); }
        Text btn_nausea_immunity = Text.translatable("button.cat_vision.config_enabled");
        if (!ModConfigs.NAUSEA_IMMUNITY) { btn_nausea_immunity = Text.translatable("button.cat_vision.config_disabled"); }

        TextWidget text_remember_nv = new TextWidget(Text.translatable("text.cat_vision.config.remember_nv"), textRenderer);
        text_remember_nv.setPosition(width / 2 + 5, height / 2 - 80);
        ButtonWidget button_remember_nv = ButtonWidget.builder(btn_remember_nv, button -> {
                    if (ModConfigs.REMEMBER_NV) {
                        ModConfigs.REMEMBER_NV = false;
                        button.setMessage(Text.translatable("button.cat_vision.config_disabled"));
                    } else {
                        ModConfigs.REMEMBER_NV = true;
                        button.setMessage(Text.translatable("button.cat_vision.config_enabled"));
                    }
                })
                .dimensions(width / 2 - 105, height / 2 - 85, 100, 20)
                .build();

        TextWidget text_auto_nv = new TextWidget(Text.translatable("text.cat_vision.config.auto_nv"), textRenderer);
        text_auto_nv.setPosition(width / 2 + 5, height / 2 - 50);
        ButtonWidget button_auto_nv = ButtonWidget.builder(btn_auto_nv, button -> {
                    if (ModConfigs.AUTO_NV) {
                        ModConfigs.AUTO_NV = false;
                        button.setMessage(Text.translatable("button.cat_vision.config_disabled"));
                    } else {
                        ModConfigs.AUTO_NV = true;
                        button.setMessage(Text.translatable("button.cat_vision.config_enabled"));
                    }
                })
                .dimensions(width / 2 - 105, height / 2 - 55, 100, 20)
                .build();

        TextWidget text_blindness_immunity = new TextWidget(Text.translatable("text.cat_vision.config.blindness_immunity"), textRenderer);
        text_blindness_immunity.setPosition(width / 2 + 5, height / 2 - 20);
        ButtonWidget button_blindness_immunity = ButtonWidget.builder(btn_blindness_immunity, button -> {
                    if (ModConfigs.BLINDNESS_IMMUNITY) {
                        ModConfigs.BLINDNESS_IMMUNITY = false;
                        button.setMessage(Text.translatable("button.cat_vision.config_disabled"));
                    } else {
                        ModConfigs.BLINDNESS_IMMUNITY = true;
                        button.setMessage(Text.translatable("button.cat_vision.config_enabled"));
                    }
                })
                .dimensions(width / 2 - 105, height / 2 - 25, 100, 20)
                .build();

        TextWidget text_darkness_immunity = new TextWidget(Text.translatable("text.cat_vision.config.darkness_immunity"), textRenderer);
        text_darkness_immunity.setPosition(width / 2 + 5, height / 2 + 10);
        ButtonWidget button_darkness_immunity = ButtonWidget.builder(btn_darkness_immunity, button -> {
                    if (ModConfigs.DARKNESS_IMMUNITY) {
                        ModConfigs.DARKNESS_IMMUNITY = false;
                        button.setMessage(Text.translatable("button.cat_vision.config_disabled"));
                    } else {
                        ModConfigs.DARKNESS_IMMUNITY = true;
                        button.setMessage(Text.translatable("button.cat_vision.config_enabled"));
                    }
                })
                .dimensions(width / 2 - 105, height / 2 + 5, 100, 20)
                .build();

        TextWidget text_nausea_immunity = new TextWidget(Text.translatable("text.cat_vision.config.nausea_immunity"), textRenderer);
        text_nausea_immunity.setPosition(width / 2 + 5, height / 2 + 40);
        ButtonWidget button_nausea_immunity = ButtonWidget.builder(btn_nausea_immunity, button -> {
                    if (ModConfigs.NAUSEA_IMMUNITY) {
                        ModConfigs.NAUSEA_IMMUNITY = false;
                        button.setMessage(Text.translatable("button.cat_vision.config_disabled"));
                    } else {
                        ModConfigs.NAUSEA_IMMUNITY = true;
                        button.setMessage(Text.translatable("button.cat_vision.config_enabled"));
                    }
                })
                .dimensions(width / 2 - 105, height / 2 + 35, 100, 20)
                .build();

        ButtonWidget button_close = ButtonWidget.builder(Text.translatable("button.cat_vision.config_close"), button -> {
                    MinecraftClient.getInstance().setScreen(new ModsScreen(null));
                })
                .dimensions(width / 2 - 80, height / 2 + 92, 160, 20)
                .build();

        addDrawableChild(text_remember_nv);
        addDrawableChild(button_remember_nv);
        addDrawableChild(text_auto_nv);
        addDrawableChild(button_auto_nv);
        addDrawableChild(text_blindness_immunity);
        addDrawableChild(button_blindness_immunity);
        addDrawableChild(text_darkness_immunity);
        addDrawableChild(button_darkness_immunity);
        addDrawableChild(text_nausea_immunity);
        addDrawableChild(button_nausea_immunity);

        addDrawableChild(button_close);
    }
}