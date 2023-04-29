package me.axiefeat.axolotlstudio.Reports.Commands;

import me.axiefeat.axolotlstudio.Main;
import me.axiefeat.axolotlstudio.Reports.Utils.ConfigUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class ReportCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        StringBuffer reason = new StringBuffer();
        for (int i = 1; i < args.length; ++i) {
            reason.append(args[i]).append(" ");
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.OnlyPlayer"));
        } else {
            if(args.length >= 1) {
                try {
                    ConfigUtil.ReportAdd(args[0], sender, String.valueOf(reason));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Main.getInstance().getConfig().getString("Messages.Usage");
                return true;
            }
            return true;
        }
        return true;
    }
}
