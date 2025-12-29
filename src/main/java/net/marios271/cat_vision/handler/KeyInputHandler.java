package net.marios271.cat_vision.handler;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.marios271.cat_vision.CatVision;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final KeyMapping.Category CAT_VISION_CATEGORY = KeyMapping.Category.register(Identifier.parse("key.category.cat_vision"));
    public static final String KEY_TOGGLE_CLIENT_NV = "key.cat_vision.toggle_client_night_vision";

    public static KeyMapping toggleNightVisionKey;

    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (toggleNightVisionKey.consumeClick()) {
                if (!CatVision.CONFIG.nv_status()) {
                    CatVision.CONFIG.nv_status(true);

                    Minecraft.getInstance().player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, -1));
                    Minecraft.getInstance().player.displayClientMessage(Component.translatable("message.cat_vision.activated"), true);
                } else {
                    CatVision.CONFIG.nv_status(false);

                    Minecraft.getInstance().player.removeEffect(MobEffects.NIGHT_VISION);
                    Minecraft.getInstance().player.displayClientMessage(Component.translatable("message.cat_vision.deactivated"), true);
                }
            }
        });
    }

    public static void register(){
        toggleNightVisionKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                KEY_TOGGLE_CLIENT_NV,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_COMMA,
                CAT_VISION_CATEGORY
        ));

        registerKeyInputs();
    }
}