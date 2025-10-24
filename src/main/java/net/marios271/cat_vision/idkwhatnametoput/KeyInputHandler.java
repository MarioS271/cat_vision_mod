package net.marios271.cat_vision.idkwhatnametoput;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.marios271.cat_vision.CatVision;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final KeyBinding.Category CAT_VISION_CATEGORY = KeyBinding.Category.create(Identifier.of("key.category.cat_vision"));
    public static final String KEY_TOGGLE_CLIENT_NV = "key.cat_vision.toggle_client_night_vision";

    public static KeyBinding toggleNightVisionKey;

    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (toggleNightVisionKey.wasPressed()) {
                if (!CatVision.CONFIG.nv_status()) {
                    CatVision.CONFIG.nv_status(true);

                    MinecraftClient.getInstance().player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, -1));
                    MinecraftClient.getInstance().player.sendMessage(Text.translatable("message.cat_vision.activated"), true);
                } else {
                    CatVision.CONFIG.nv_status(false);

                    MinecraftClient.getInstance().player.removeStatusEffect(StatusEffects.NIGHT_VISION);
                    MinecraftClient.getInstance().player.sendMessage(Text.translatable("message.cat_vision.deactivated"), true);
                }
            }
        });
    }

    public static void register(){
        toggleNightVisionKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_TOGGLE_CLIENT_NV,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_COMMA,
                CAT_VISION_CATEGORY
        ));

        registerKeyInputs();
    }
}