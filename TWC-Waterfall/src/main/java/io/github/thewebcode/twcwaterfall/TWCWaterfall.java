package io.github.thewebcode.twcwaterfall;

import io.github.thewebcode.twcwaterfall.command.MaintenanceModeCommand;
import io.github.thewebcode.twcwaterfall.command.MoveCommand;
import io.github.thewebcode.twcwaterfall.event.Eventhandler;
import io.github.thewebcode.twcwaterfall.utils.FileService;
import io.github.thewebcode.twcwaterfall.utils.setting.SettingManager;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.util.EventListener;

public final class TWCWaterfall extends Plugin {
    public static String PREFIX = "§8[§e§lSERVER§8] §f>> ";
    private static TWCWaterfall instance;

    private FileService fileService;
    private SettingManager settingManager;

    @Override
    public void onEnable() {
        instance = this;

        this.fileService = new FileService();
        this.settingManager = new SettingManager();

        registerEvents();
        registerCommands();
    }

    private void registerEvents() {
        PluginManager pluginManager = getProxy().getPluginManager();
        pluginManager.registerListener(this, new Eventhandler());
    }

    private void registerCommands() {
        PluginManager pluginManager = getProxy().getPluginManager();
        pluginManager.registerCommand(this, new MoveCommand());
        pluginManager.registerCommand(this, new MaintenanceModeCommand());
    }

    public FileService getFileService() {
        return fileService;
    }

    public SettingManager getSettingManager() {
        return settingManager;
    }

    public static TWCWaterfall getInstance() {
        return instance;
    }
}
