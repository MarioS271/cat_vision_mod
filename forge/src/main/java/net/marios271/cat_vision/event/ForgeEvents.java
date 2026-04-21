package net.marios271.cat_vision.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.marios271.cat_vision.logic.DisconnectLogic;
import net.marios271.cat_vision.logic.JoinLogic;
import net.marios271.cat_vision.logic.RespawnLogic;
import net.marios271.cat_vision.logic.TickLogic;

@OnlyIn(Dist.CLIENT)
public class ForgeEvents {

    public static void register() {
        MinecraftForge.EVENT_BUS.addListener(ForgeEvents::onClientTick);
        MinecraftForge.EVENT_BUS.addListener(ForgeEvents::onJoin);
        MinecraftForge.EVENT_BUS.addListener(ForgeEvents::onDisconnect);
        MinecraftForge.EVENT_BUS.addListener(ForgeEvents::onEntityJoin);
    }

    @OnlyIn(Dist.CLIENT)
    private static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END)
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
