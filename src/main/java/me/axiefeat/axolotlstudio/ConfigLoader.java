package me.axiefeat.axolotlstudio;

public class ConfigLoader  extends AbstractConfig {


    public ConfigLoader(String path) {
//таким образом, будет создан конфиг Example.yml по пути который вы указали, и в него будет перенесена информация с Example.yml который находится в джарнике(если нужно)
        super(path, "warns.yml");
    }
}
