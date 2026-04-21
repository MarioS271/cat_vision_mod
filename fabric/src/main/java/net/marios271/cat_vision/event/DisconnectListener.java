package net.marios271.cat_vision.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.marios271.cat_vision.logic.DisconnectLogic;

public class DisconnectListener {
    public static void register() {
        ClientPlayConnectionEvents.DISCONNECT.register((listener, client) ->
            DisconnectLogic.onDisconnect());
    }
}
