package net.marios271.cat_vision.platform;

import java.nio.file.Path;

public final class CatVisionPlatform {
    private static Path configDir;

    public static void setConfigDir(Path dir) {
        configDir = dir;
    }

    public static Path getConfigDir() {
        if (configDir == null)
            throw new IllegalStateException("CatVisionPlatform.setConfigDir() was not called before config access");
        return configDir;
    }
}
