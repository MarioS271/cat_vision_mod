package net.marios271.cat_vision.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.marios271.cat_vision.logic.DisconnectLogic;
import net.marios271.cat_vision.logic.JoinLogic;
import net.marios271.cat_vision.logic.RespawnLogic;
import net.marios271.cat_vision.logic.TickLogic;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

// All event listeners registered on NeoForge.EVENT_BUS (game-side, not mod bus).
// The class only runs on Dist.CLIENT because @Mod(dist = Dist.CLIENT) prevents server loading.
public class NeoForgeEvents {

    public static void register() {
        NeoForge.EVENT_BUS.addListener(NeoForgeEvents::onClientTick);
        NeoForge.EVENT_BUS.addListener(NeoForgeEvents::onJoin);
        NeoForge.EVENT_BUS.addListener(NeoForgeEvents::onDisconnect);
        NeoForge.EVENT_BUS.addListener(NeoForgeEvents::onEntityJoin);
    }

    // ClientTickEvent.Post replaces the deprecated TickEvent.ClientTickEvent Phase.END
    private static void onClientTick(ClientTickEvent.Post event) {
        TickLogic.onEndTick(Minecraft.getInstance());
    }

    private static void onJoin(ClientPlayerNetworkEvent.LoggingIn event) {
        JoinLogic.onJoin(Minecraft.getInstance());
    }

    private static void onDisconnect(ClientPlayerNetworkEvent.LoggingOut event) {
        DisconnectLogic.onDisconnect();
    }

    private static void onEntityJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof LocalPlayer player && event.getLevel().isClientSide())
            RespawnLogic.onRespawn(player);
    }
}
