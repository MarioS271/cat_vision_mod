package net.marios271.cat_vision.logic;

import net.marios271.cat_vision.CatVisionCommon;
import net.marios271.cat_vision.config.ConfigData;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public final class KeyLogic {
    public static final String KEY_TOGGLE = "key.cat_vision.toggle_client_night_vision";
    public static final String KEY_CATEGORY = "key.categories.cat_vision";

    // Set by each loader's KeyInputHandler during registration
    public static KeyMapping toggleNightVisionKey;

    public static void onToggleNightVision(Minecraft client) {
        if (client.player == null) return;
        ConfigData config = CatVisionCommon.getConfig();
        if (!config.has_nv) {
            config.has_nv = true;
            client.player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, -1));
            //? if >=1.21 {
            client.player.sendOverlayMessage(Component.translatable("message.cat_vision.activated"));
            //?} else {
            /*
            client.player.displayClientMessage(Component.translatable("message.cat_vision.activated"), true);
            */
            //?}
        } else {
            config.has_nv = false;
            client.player.removeEffect(MobEffects.NIGHT_VISION);
            //? if >=1.21 {
            client.player.sendOverlayMessage(Component.translatable("message.cat_vision.deactivated"));
            //?} else {
            /*
            client.player.displayClientMessage(Component.translatable("message.cat_vision.deactivated"), true);
            */
            //?}
        }
    }
}
