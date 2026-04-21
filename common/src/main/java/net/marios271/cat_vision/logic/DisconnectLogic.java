package net.marios271.cat_vision.logic;

import net.marios271.cat_vision.CatVisionCommon;

public final class DisconnectLogic {
    public static void onDisconnect() {
        CatVisionCommon.getConfig().save();
    }
}
