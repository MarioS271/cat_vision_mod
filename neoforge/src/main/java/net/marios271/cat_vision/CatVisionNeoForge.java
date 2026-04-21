package net.marios271.cat_vision;

import net.marios271.cat_vision.config.ConfigScreen;
import net.marios271.cat_vision.event.NeoForgeEvents;
import net.marios271.cat_vision.handler.KeyInputHandler;
import net.marios271.cat_vision.platform.CatVisionPlatform;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;
// TODO: if this import fails, find the config screen factory interface in your NeoForge version's API
import net.neoforged.neoforge.client.gui.IModConfigScreenFactory;

// dist = Dist.CLIENT prevents this class (and the whole mod) from loading on a dedicated server
@Mod(value = CatVisionCommon.MOD_ID, dist = Dist.CLIENT)
public class CatVisionNeoForge {

    public CatVisionNeoForge(IEventBus modEventBus, ModContainer container) {
        CatVisionPlatform.setConfigDir(FMLPaths.GAMEDIR.get().resolve("config"));

        modEventBus.addListener(KeyInputHandler::onRegisterKeyMappings);
        NeoForgeEvents.register();

        container.registerExtensionPoint(
            IModConfigScreenFactory.class,
            (mod, parent) -> ConfigScreen.create(parent, CatVisionCommon.getConfig())
        );

        CatVisionCommon.LOGGER.info("Initialized CatVision (NeoForge)");
    }
}
