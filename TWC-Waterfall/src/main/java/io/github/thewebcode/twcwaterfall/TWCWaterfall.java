package io.github.thewebcode.twcwaterfall;

import net.md_5.bungee.api.plugin.Plugin;

public final class TWCWaterfall extends Plugin {
    private static TWCWaterfall instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    public static TWCWaterfall getInstance() {
        return instance;
    }
}
