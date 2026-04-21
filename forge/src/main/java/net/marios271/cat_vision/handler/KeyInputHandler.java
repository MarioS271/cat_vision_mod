package net.marios271.cat_vision.handler;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.marios271.cat_vision.logic.KeyLogic;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class KeyInputHandler {

    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        KeyLogic.toggleNightVisionKey = new KeyMapping(
            KeyLogic.KEY_TOGGLE,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_COMMA,
            KeyLogic.KEY_CATEGORY
        );
        event.register(KeyLogic.toggleNightVisionKey);

        MinecraftForge.EVENT_BUS.addListener(KeyInputHandler::onClientTick);
    }

    @OnlyIn(Dist.CLIENT)
    private static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END
                && KeyLogic.toggleNightVisionKey != null
                && KeyLogic.toggleNightVisionKey.consumeClick())
            KeyLogic.onToggleNightVision(Minecraft.getInstance());
    }
}
