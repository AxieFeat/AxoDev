package me.axiefeat.axolotlstudio.Reports.Utils;

import me.axiefeat.axolotlstudio.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class ConfigUtil {

    static File reportscfg = new File(Main.getInstance().getDataFolder(), "reports.yml");
    static FileConfiguration reports = YamlConfiguration.loadConfiguration(reportscfg);
    public static void ReportAdd(String nickname, CommandSender who, String reason) throws IOException {
        if (reports.getConfigurationSection("Count") == null) {
            reports.createSection("Count");
        }
        if (reports.getConfigurationSection("Reports") == null) {
            reports.createSection("Reports");
        }
        Player player = Bukkit.getServer().getPlayer(String.valueOf(who));
        if (reports.getString("Reports." + nickname + ".reasons").contains(who.getName())) {
            player.sendMessage(Main.getInstance().getConfig().getString("Messages.AlredyReported").replace("%player%", nickname));
        } else {
            int counter = reports.getInt("Count");
            if (reports.getConfigurationSection("Reports." + nickname) == null) {
                reports.createSection("Reports." + nickname);
                reports.createSection("Reports." + nickname + ".count");
                reports.set("Reports." + nickname + ".count", 1);
                reports.createSection("Reports." + nickname + ".reasons");
                reports.createSection("Reports." + nickname + ".reasons." + who);
                reports.set("Reports." + nickname + ".reasons." + who, reason);
                reports.save(reportscfg);
                player.sendMessage(Main.getInstance().getConfig().getString("Messages.Reported").replace("%player%", nickname));
            } else {
                int count = reports.getInt("Reports." + nickname + ".count");
                reports.set("Reports." + nickname + ".count", count + 1);
                reports.createSection("Reports." + nickname + ".reasons." + who);
                reports.set("Reports." + nickname + ".reasons." + who, reason);
                reports.save(reportscfg);
                player.sendMessage(Main.getInstance().getConfig().getString("Messages.Reported").replace("%player%", nickname));
            }
        }
    }
}
