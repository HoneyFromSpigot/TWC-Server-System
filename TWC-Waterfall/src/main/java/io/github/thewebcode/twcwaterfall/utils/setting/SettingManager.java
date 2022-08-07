package io.github.thewebcode.twcwaterfall.utils.setting;

import io.github.thewebcode.twcwaterfall.TWCWaterfall;
import io.github.thewebcode.twcwaterfall.utils.FileService;
import net.md_5.bungee.config.Configuration;

public class SettingManager {
    public boolean getValue(Setting setting) {
        String path = setting.getPath();
        Configuration config = FileService.getService().getConfig();

        if (config.contains("Settings." + path)) {
            return config.getBoolean("Settings." + path);
        }

        return false;
    }

    public void set(Setting setting, boolean value) {
        FileService service = FileService.getService();
        Configuration config = service.getConfig();
        String path = setting.getPath();

        config.set("Settings." + path, value);

        service.saveFiles();
    }

    public static SettingManager get() {
        return TWCWaterfall.getInstance().getSettingManager();
    }
}
