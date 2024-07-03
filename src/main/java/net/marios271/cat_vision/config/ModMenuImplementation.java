package net.marios271.cat_vision.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.marios271.cat_vision.screen.ConfigScreen;

public class ModMenuImplementation implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> new ConfigScreen();
    }
}