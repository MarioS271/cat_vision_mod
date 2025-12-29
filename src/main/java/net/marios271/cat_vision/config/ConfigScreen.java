package net.marios271.cat_vision.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.ExcludeFromScreen;
import io.wispforest.owo.config.annotation.Modmenu;
import net.marios271.cat_vision.CatVision;

@Modmenu(modId = CatVision.MOD_ID)
@Config(name = "cat_vision_config", wrapperName = "CatVisionModConfig")
public class ConfigScreen {
    @ExcludeFromScreen
    public boolean nv_status = false;

    public boolean remember_nv = true;
    public boolean auto_nv = false;
    public boolean blindness_immunity = false;
    public boolean darkness_immunity = false;
    public boolean nausea_immunity = false;
}