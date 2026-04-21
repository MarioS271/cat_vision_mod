package net.marios271.cat_vision.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.marios271.cat_vision.logic.JoinLogic;

public class JoinListener {
    public static void register() {
        ClientPlayConnectionEvents.JOIN.register((listener, sender, client) ->
            JoinLogic.onJoin(client));
    }
}
