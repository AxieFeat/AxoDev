package me.axiefeat.axolotlstudio;

import me.axiefeat.axolotlstudio.Commands.HelpCommands;
import me.axiefeat.axolotlstudio.Commands.RconCommandsLogic.*;
import me.axiefeat.axolotlstudio.Commands.ReloadCmd;
import me.axiefeat.axolotlstudio.Commands.UserCommands;
import me.axiefeat.axolotlstudio.Commands.VKCommandsLogic.*;
import me.axiefeat.axolotlstudio.Reports.Commands.ReportCmd;
import me.axiefeat.axolotlstudio.Watch.Commands.WatchCmd;
import me.axiefeat.axolotlstudio.Watch.Listeners.NoClipListener;
import me.axiefeat.axolotlstudio.logs.Logs;
import me.axiefeat.axolotlstudio.logs.Protector;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;


public final class Main extends JavaPlugin {

    private AbstractConfig warns;

    public static Connection conn;
    private static Main instance;

    public static Main plugin() {
        return instance;
    }
    public static Main getInstance() {
        return instance;
    }
    public Main() {
    }

    @Override
    public void onEnable() {

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new ServerManager(), 100L, 1L);

        File langs = new File(getDataFolder() + File.separator + "langs");
        if(!langs.exists()) {
            try {
                langs.mkdirs();
                langs.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        File backups = new File(getDataFolder() + File.separator + "backups");
        if(!backups.exists()) {
            try {
                backups.mkdirs();
                backups.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        saveDefaultConfig();
        instance = this;

        File reports = new File(getDataFolder(), "reports.yml");
        File log = new File(getDataFolder(), "log.yml");
        File warns = new File(getDataFolder(), "warns.yml");
        File moders = new File(getDataFolder(), "moder.yml");
        File moders_storage = new File(getDataFolder(), "moder_storage.yml");

        File config_backup = new File(getDataFolder() + File.separator + "backups", "config_backup.yml");
        File warns_backup = new File(getDataFolder() + File.separator + "backups", "warns_backup.yml");
        File moders_backup = new File(getDataFolder() + File.separator + "backups", "moder_backup.yml");
        File moders_storage_backup = new File(getDataFolder() + File.separator + "backups", "moders_storage_backup.yml");

        File langs_s = new File(getDataFolder() + File.separator + "langs", "ru_RU.yml");

        if(!warns.exists() | !moders.exists() | !moders_storage.exists() | !config_backup.exists() | !warns_backup.exists() | !moders_backup.exists() | !moders_storage_backup.exists() | !langs_s.exists() | !reports.exists() | !log.exists()) {
            try {
                warns.createNewFile();
                log.createNewFile();
                reports.createNewFile();
                moders.createNewFile();
                moders_storage.createNewFile();
                config_backup.createNewFile();
                warns_backup.createNewFile();;
                moders_backup.createNewFile();
                moders_storage_backup.createNewFile();
                langs_s.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if (!Bukkit.getPluginManager().isPluginEnabled("Vault")) {
                getServer().getLogger().info("[AxoDev] Плагин Vault не найден!");
            }
        if (!Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
                getServer().getLogger().info("[AxoDev] Плагин LuckPerms не найден!");
            }
        if (!Bukkit.getPluginManager().isPluginEnabled("PlayerPoints")) {
                getServer().getLogger().info("[AxoDev] Плагин PlayerPoints не найден!");
            }
        if (!Bukkit.getPluginManager().isPluginEnabled("Essentials")) {
            getServer().getLogger().info("[AxoDev] Плагин Essentials не найден!");
        }

        Bukkit.getPluginManager().registerEvents(new Alerter(), this);
        Bukkit.getPluginManager().registerEvents(new UserCommands(), this);
        Bukkit.getPluginManager().registerEvents(new ServerInfo(), this);
        Bukkit.getPluginManager().registerEvents(new HelpCommands() , this);
        Bukkit.getPluginManager().registerEvents(new VkKick() , this);
        Bukkit.getPluginManager().registerEvents(new RconKick() , this);
        Bukkit.getPluginManager().registerEvents(new RconProfile() , this);
        Bukkit.getPluginManager().registerEvents(new RconBan() , this);
        Bukkit.getPluginManager().registerEvents(new RconUnban() , this);
        Bukkit.getPluginManager().registerEvents(new RconMute() , this);
        Bukkit.getPluginManager().registerEvents(new RconUnmute() , this);
        Bukkit.getPluginManager().registerEvents(new RconNotify() , this);
        Bukkit.getPluginManager().registerEvents(new RconIsBlock() , this);
        Bukkit.getPluginManager().registerEvents(new RconPerms() , this);
        Bukkit.getPluginManager().registerEvents(new VkModer() , this);
        Bukkit.getPluginManager().registerEvents(new VkModerLists() , this);
        Bukkit.getPluginManager().registerEvents(new VkWarn() , this);
        Bukkit.getPluginManager().registerEvents(new VkUnwarn() , this);
        Bukkit.getPluginManager().registerEvents(new VkWarnList() , this);
        Bukkit.getPluginManager().registerEvents(new RconShop() , this);
        Bukkit.getPluginManager().registerEvents(new ReloadCmd() , this);
        Bukkit.getPluginManager().registerEvents(new Logs(), this);
        Bukkit.getPluginManager().registerEvents(new Protector(), this);

        Bukkit.getPluginManager().registerEvents(new NoClipListener(), this);

        this.getCommand("report").setExecutor(new ReportCmd());
        this.getCommand("watch").setExecutor(new WatchCmd());
    }


    @Override
    public void onDisable() {
        saveConfig();
    }
}
