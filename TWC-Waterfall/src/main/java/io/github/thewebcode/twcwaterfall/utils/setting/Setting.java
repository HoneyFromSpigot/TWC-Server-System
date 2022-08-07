package io.github.thewebcode.twcwaterfall.utils.setting;

public enum Setting {
    MAINTENANCE_MODE("setting.maintenance_mode");

    private String path;

    Setting(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}

