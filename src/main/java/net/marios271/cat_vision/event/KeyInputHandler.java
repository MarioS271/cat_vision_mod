package net.marios271.cat_vision.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.marios271.cat_vision.CatVision;
import net.marios271.cat_vision.config.ModConfigs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_QUICK_COMMANDS = "key.category.cat_vision";
    public static final String KEY_TOGGLE_CLIENT_NV = "key.cat_vision.toggle_client_night_vision";

    public static KeyBinding toggleNightVisionKey;

    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (toggleNightVisionKey.wasPressed()) {
                if (!ModConfigs.NV_STATUS) {
                    ModConfigs.NV_STATUS = true;
                    CatVision.activate();
                } else {
                    ModConfigs.NV_STATUS = false;
                    CatVision.deactivate();
                }
            }
        });
    }

    public static void register(){
        toggleNightVisionKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_TOGGLE_CLIENT_NV,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_COMMA,
                KEY_CATEGORY_QUICK_COMMANDS
        ));

        registerKeyInputs();
    }
}