package io.github.thewebcode.twcwaterfall;

import io.github.thewebcode.twcwaterfall.utils.FileService;
import net.md_5.bungee.api.plugin.Plugin;

public final class TWCWaterfall extends Plugin {
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
