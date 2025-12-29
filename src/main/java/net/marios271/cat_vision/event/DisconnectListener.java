package net.marios271.cat_vision.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.marios271.cat_vision.CatVision;
import net.marios271.cat_vision.config.ConfigData;

public class DisconnectListener {
    public static void register() {
        ClientPlayConnectionEvents.DISCONNECT.register((listener, client) -> {
            ConfigData config = CatVision.CONFIG;
            config.save();
        });
    }
}
