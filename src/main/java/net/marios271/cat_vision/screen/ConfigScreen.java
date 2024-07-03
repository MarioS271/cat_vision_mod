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

        TextWidget text_remember_nv = new TextWidget(Text.translatable("text.cat_vision.config.remember_nv"), textRenderer);
        text_remember_nv.setPosition(width / 2 + 5, height / 2 - 20);
        ButtonWidget button_remember_nv = ButtonWidget.builder(btn_remember_nv, button -> {
                    if (ModConfigs.REMEMBER_NV) {
                        ModConfigs.REMEMBER_NV = false;
                        button.setMessage(Text.translatable("button.cat_vision.config_disabled"));
                    } else {
                        ModConfigs.REMEMBER_NV = true;
                        button.setMessage(Text.translatable("button.cat_vision.config_enabled"));
                    }
                })
                .dimensions(width / 2 - 105, height / 2 - 25, 100, 20)
                .build();

        TextWidget text_auto_nv = new TextWidget(Text.translatable("text.cat_vision.config.auto_nv"), textRenderer);
        text_auto_nv.setPosition(width / 2 + 5, height / 2 + 10);
        ButtonWidget button_auto_nv = ButtonWidget.builder(btn_auto_nv, button -> {
                    if (ModConfigs.AUTO_NV) {
                        ModConfigs.AUTO_NV = false;
                        button.setMessage(Text.translatable("button.cat_vision.config_disabled"));
                    } else {
                        ModConfigs.AUTO_NV = true;
                        button.setMessage(Text.translatable("button.cat_vision.config_enabled"));
                    }
                })
                .dimensions(width / 2 - 105, height / 2 + 5, 100, 20)
                .build();

        ButtonWidget button_close = ButtonWidget.builder(Text.translatable("button.cat_vision.config_close"), button -> {
                    MinecraftClient.getInstance().setScreen(new ModsScreen(null));
                })
                .dimensions(width / 2 - 80, height / 2 + 80, 160, 20)
                .build();

        addDrawableChild(text_remember_nv);
        addDrawableChild(button_remember_nv);

        addDrawableChild(text_auto_nv);
        addDrawableChild(button_auto_nv);

        addDrawableChild(button_close);
    }
}