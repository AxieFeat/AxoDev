package me.axiefeat.axolotlstudio;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public abstract class AbstractConfig {
    private final String path;
    private final String filename;
    private File file;
    private YamlConfiguration configuration;

    public AbstractConfig(final String path, final String filename) {
        //путь к вашему конфигу, можете изменять как угодно
        this.path = Main.getInstance().getDataFolder() + path + filename;


        this.filename = filename;
        setFile();
        if (!file.exists()) {
            initializeFile();
            //этот метод для того, чтобы скопировать кастомный конфиг с вашего джарника(если он есть) можете удалить если хотите создавать чистые конфиги без дефолтных настроек
            copyFromResource(Main.getInstance().getResource(filename), file);
        }
        setYamlConfiguration();
    }

    /**
     * создать файл
     */
    private void initializeFile() {

        if (!file.exists())
            file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param resource
     * @param target
     *
     *               метод для копирования внутренних файлов из джарника в другой файл(внешний)
     *
     */
    private void copyFromResource(InputStream resource, File target) {
        byte[] buffer;
        try {
            buffer = new byte[resource.available()];
            resource.read(buffer);
            OutputStream outStream = new FileOutputStream(target);
            outStream.write(buffer);
            outStream.flush();
            outStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param path создать файл по другому пути
     */
    public void initializeFile(String path) {
        File file = new File(path);
        if (!file.exists())
            file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * сохранить конфиг
     */
    public void save() {
        try {
            getYamlConfiguration().save(getFile());
            reload();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setFile() {
        this.file = new File(path);
    }

    public File getFile() {
        return file;
    }

    private void setYamlConfiguration() {
        configuration = YamlConfiguration.loadConfiguration(file);
    }
    /**
     * получить конфиг
     */
    public YamlConfiguration getYamlConfiguration() {
        return configuration;
    }

    /**
     * перезагрузить конфиг
     */
    public void reload() {
        this.setYamlConfiguration();
    }
}