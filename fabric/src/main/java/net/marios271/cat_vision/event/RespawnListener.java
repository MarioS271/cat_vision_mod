package net.marios271.cat_vision.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.minecraft.client.player.LocalPlayer;
import net.marios271.cat_vision.logic.RespawnLogic;

public class RespawnListener {
    public static void register() {
        ClientEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof LocalPlayer player)
                RespawnLogic.onRespawn(player);
        });
    }
}
