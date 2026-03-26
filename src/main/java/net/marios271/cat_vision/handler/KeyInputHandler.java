package net.marios271.cat_vision.handler;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.marios271.cat_vision.CatVision;
import net.marios271.cat_vision.config.ConfigData;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final KeyMapping.Category CAT_VISION_CATEGORY = KeyMapping.Category.register(Identifier.parse("cat_vision"));
    public static final String KEY_TOGGLE_CLIENT_NV = "key.cat_vision.toggle_client_night_vision";

    public static KeyMapping toggleNightVisionKey;

    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null)
                return;

            ConfigData config = CatVision.CONFIG;

            if (toggleNightVisionKey.consumeClick()) {
                if (!config.has_nv) {
                    config.has_nv = true;

                    client.player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, -1));
                    client.player.sendOverlayMessage(Component.translatable("message.cat_vision.activated"));
                } else {
                    config.has_nv = false;

                    client.player.removeEffect(MobEffects.NIGHT_VISION);
                    client.player.sendOverlayMessage(Component.translatable("message.cat_vision.deactivated"));
                }
            }
        });
    }

    public static void register(){
        toggleNightVisionKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                KEY_TOGGLE_CLIENT_NV,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_COMMA,
                CAT_VISION_CATEGORY
        ));

        registerKeyInputs();
    }
}