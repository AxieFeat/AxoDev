package me.axiefeat.axolotlstudio;

public class ConfigManager {

    private AbstractConfig warns;

//    public ConfigManager() {
//        init();
//    }

//    private void init() {
//        String path = "/configs/";
//        warns = new AbstractConfiguration(path) {
//        };
//    }

    public AbstractConfig getWarns() {
        return warns;
    }
}
