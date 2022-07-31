package io.github.thewebcode.utils;

import io.github.thewebcode.TWCWaterfall;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileService {
    private File FOLDER, configFile;

    private YamlConfiguration config;

    public FileService() {
        this.FOLDER = new File("./plugins/twc-server/");
        this.configFile = new File(FOLDER, "config.yml");

        try{
            if(!FOLDER.exists()) FOLDER.mkdirs();
            if(!configFile.exists()) configFile.createNewFile();

            this.config = YamlConfiguration.loadConfiguration(configFile);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveFiles() {
        try{
            config.save(configFile);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public static FileService getService() {
        return TWCWaterfall.getInstance().getFileService();
    }
}
