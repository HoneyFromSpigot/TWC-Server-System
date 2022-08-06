package io.github.thewebcode.twcwaterfall.utils;

import io.github.thewebcode.twcwaterfall.TWCWaterfall;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileService {
    private File FOLDER, configFile;

    private Configuration config;

    public FileService() {
        this.FOLDER = new File("./plugins/twc/");
        this.configFile = new File(FOLDER, "config.yml");

        try {
            if (!FOLDER.exists()) FOLDER.mkdirs();

            if (!configFile.exists()) configFile.createNewFile();

            this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFiles() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Configuration getConfig() {
        return config;
    }

    public static FileService getService() {
        return TWCWaterfall.getInstance().getFileService();
    }
}
