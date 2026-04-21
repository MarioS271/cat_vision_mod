package net.marios271.cat_vision;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.marios271.cat_vision.event.DisconnectListener;
import net.marios271.cat_vision.event.EndTickListener;
import net.marios271.cat_vision.event.JoinListener;
import net.marios271.cat_vision.event.RespawnListener;
import net.marios271.cat_vision.handler.KeyInputHandler;
import net.marios271.cat_vision.platform.CatVisionPlatform;

@Environment(EnvType.CLIENT)
public class CatVisionFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CatVisionPlatform.setConfigDir(FabricLoader.getInstance().getConfigDir());

        KeyInputHandler.register();
        JoinListener.register();
        DisconnectListener.register();
        RespawnListener.register();
        EndTickListener.register();

        CatVisionCommon.LOGGER.info("Initialized CatVision (Fabric)");
    }
}
