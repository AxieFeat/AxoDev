package me.axiefeat.axolotlstudio.Watch.Commands;

import me.axiefeat.axolotlstudio.Main;
import me.axiefeat.axolotlstudio.Watch.Listeners.NoClipListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;

public class WatchCmd implements CommandExecutor {
    private static WatchCmd instance;

    private Main plugin;
    String prefix = ChatColor.translateAlternateColorCodes('&', "&a▶&f ");

    public WatchCmd() {
        instance = this;
    }

    public static WatchCmd getInstance() {
        return instance;
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (sender instanceof Player) {
                Player player = (Player)sender;
                Scoreboard s = player.getPlayer().getScoreboard();
                    player.setGameMode(GameMode.CREATIVE);
                    if (player.hasPermission("axodev.watch")) {
                        if (NoClipListener.getInstance().noclip.contains(player.getName()) && cmd.getName().equals("watch")) {
                            NoClipListener.getInstance().noclip.remove(player.getName());
                            this.teleportToSurface(player);
                            player.setGameMode(GameMode.CREATIVE);
                            player.getPlayer().setScoreboard(null);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.prefix + "Вы вышли из слежки за " + args[0]));
                        } else {
                            if (args.length == 0) {
                                Player p = Bukkit.getServer().getPlayer(args[0]);
                                if(s == null) s = Bukkit.getScoreboardManager().getNewScoreboard();

                                // Получение цели (обджектива), в которой хранятся все строки блока с информацией
                                Objective o = s.getObjective(player.getPlayer().getName());
                                // Если её нет, то создаётся новая
                                if(o == null) o = s.registerNewObjective(player.getPlayer().getName(), "dummy", "Слежка за " + args[0], RenderType.INTEGER);

                                // Обнуление всех существующих записи
                                for(String i : s.getEntries()) {
                                    s.resetScores(i);
                                }
                                // Добавление текста
                                // Стоит отметить, что если нужна пустая строка, то нужно ставить уникальный цветовой код,
                                // а также чем больше значение, тем выше будет располагаться строка
                                o.getScore("HP: " + p.getHealthScale()).setScore(4);
                                o.getScore("CPS: ").setScore(3);
                                o.getScore("SPEED: " + p.getWalkSpeed()).setScore(2);
                                o.getScore("PING: " + p.getPing()).setScore(1);
                                o.getScore("EFFECTS: " + p.getActivePotionEffects()).setScore(0);

                                // Установка параметра цели, чтобы она отображалась справа
                                o.setDisplaySlot(DisplaySlot.SIDEBAR);

                                // Установка игроку этого скорборда
                                player.getPlayer().setScoreboard(s);

                                NoClipListener.getInstance().noclip.add(player.getName());
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.prefix + "Вы следите за " + args[0]));
                            } else {
                                if (NoClipListener.getInstance().noclip.contains(player.getName())) {
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.prefix + "Для выхода из слежки используйте &a/watch"));
                                } else {
                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.prefix + "Использование: &a/watch <ник>"));
                                }
                            }
                        }
                    }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.prefix + "Не для консоли!"));
            }

        return true;
    }

    public void teleportToSurface(Player player) {
        int height = 0;
        int maxHeight = player.getWorld().getMaxHeight();

        while(height <= maxHeight) {
            ++height;
            Block block = player.getLocation().add(0.0, (double)height, 0.0).getBlock();
            if (block.isEmpty()) {
                block = player.getEyeLocation().add(0.0, (double)height, 0.0).getBlock();
                if (block.isEmpty()) {
                    player.teleport(player.getLocation().add(0.0, (double)height, 0.0));
                    break;
                }
            }
        }

    }
}
