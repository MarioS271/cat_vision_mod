package net.marios271.cat_vision.handler;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.marios271.cat_vision.logic.KeyLogic;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {

    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        KeyLogic.toggleNightVisionKey = new KeyMapping(
            KeyLogic.KEY_TOGGLE,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_COMMA,
            KeyLogic.KEY_CATEGORY
        );
        event.register(KeyLogic.toggleNightVisionKey);

        // Poll the key on each client tick (registered here so the key exists when polled)
        NeoForge.EVENT_BUS.addListener(KeyInputHandler::onClientTick);
    }

    private static void onClientTick(ClientTickEvent.Post event) {
        if (KeyLogic.toggleNightVisionKey != null && KeyLogic.toggleNightVisionKey.consumeClick())
            KeyLogic.onToggleNightVision(Minecraft.getInstance());
    }
}
