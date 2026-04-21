package net.marios271.cat_vision.handler;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.marios271.cat_vision.logic.KeyLogic;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static void register() {
        KeyLogic.toggleNightVisionKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
            KeyLogic.KEY_TOGGLE,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_COMMA,
            KeyLogic.KEY_CATEGORY
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (KeyLogic.toggleNightVisionKey.consumeClick())
                KeyLogic.onToggleNightVision(client);
        });
    }
}
