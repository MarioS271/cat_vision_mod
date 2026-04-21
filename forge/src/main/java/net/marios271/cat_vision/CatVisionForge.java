package net.marios271.cat_vision;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.client.ConfigScreenHandler;
import net.marios271.cat_vision.config.ConfigScreen;
import net.marios271.cat_vision.event.ForgeEvents;
import net.marios271.cat_vision.handler.KeyInputHandler;
import net.marios271.cat_vision.platform.CatVisionPlatform;

@Mod(CatVisionCommon.MOD_ID)
public class CatVisionForge {

    public CatVisionForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // DistExecutor ensures all client-side code is unreachable on a dedicated server
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            CatVisionPlatform.setConfigDir(FMLPaths.GAMEDIR.get().resolve("config"));

            modEventBus.addListener(KeyInputHandler::onRegisterKeyMappings);
            ForgeEvents.register();

            ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory(
                    (mc, parent) -> ConfigScreen.create(parent, CatVisionCommon.getConfig())
                )
            );

            CatVisionCommon.LOGGER.info("Initialized CatVision (Forge)");
        });
    }
}
