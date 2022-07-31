package io.github.thewebcode;

import io.github.thewebcode.utils.FileService;
import org.bukkit.plugin.java.JavaPlugin;

public final class TWCWaterfall extends JavaPlugin {
    private static TWCWaterfall instance;

    private FileService fileService;

    @Override
    public void onEnable() {
        instance = this;

        this.fileService = new FileService();
    }

    public FileService getFileService() {
        return fileService;
    }

    public static TWCWaterfall getInstance() {
        return instance;
    }
}
