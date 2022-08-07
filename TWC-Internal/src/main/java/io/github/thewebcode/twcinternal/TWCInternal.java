package io.github.thewebcode.twcinternal;

import org.bukkit.plugin.java.JavaPlugin;

public final class TWCInternal extends JavaPlugin {
    private static TWCInternal instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    public static TWCInternal getInstance() {
        return instance;
    }
}
