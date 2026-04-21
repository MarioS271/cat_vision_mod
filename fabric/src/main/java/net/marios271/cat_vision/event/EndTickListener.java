package net.marios271.cat_vision.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.marios271.cat_vision.logic.TickLogic;

public class EndTickListener {
    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(TickLogic::onEndTick);
    }
}
