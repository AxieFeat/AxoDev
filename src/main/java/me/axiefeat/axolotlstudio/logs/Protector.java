package me.axiefeat.axolotlstudio.logs;

import com.ubivashka.vk.bukkit.BukkitVkApiPlugin;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import me.axiefeat.axolotlstudio.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static org.bukkit.Bukkit.*;
import static org.bukkit.Bukkit.getConsoleSender;

public class Protector implements Listener {

    Main plugin = Main.plugin();

    File log = new File(Main.getInstance().getDataFolder(), "log.yml");
    FileConfiguration cfg = YamlConfiguration.loadConfiguration(log);
    private final static VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getVkApiClient();
    private final static GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getActor();
    private final static Random RANDOM = new Random();

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent e) throws ClientException, ApiException, IOException {
        try {
            if (cfg.getConfigurationSection("logs") == null) {
                cfg.createSection("logs");
            }
            cfg.save(log);

            String cmd = e.getMessage();
            Player sender = Bukkit.getServer().getPlayer(String.valueOf(e.getPlayer()).replace("CraftPlayer{name=", "").replace("}", ""));

            String[] array = cmd.split(" ");

            String ban = "/ban ";
            String mute = "/mute ";
            String warn = "/warn ";

            String nickname = array[1];
            String time = array[2];
            String num = time.replaceAll("[^0-9]", "");

            String ban_reason = cmd.substring(ban.length() + nickname.length() + time.length() + 2).toLowerCase();
            String ban_reasonperm = cmd.substring(ban.length() + nickname.length() + 1).toLowerCase();

            String mute_reason = cmd.substring(mute.length() + nickname.length() + time.length() + 2).toLowerCase();
            String mute_reasonperm = cmd.substring(mute.length() + nickname.length() + 1).toLowerCase();

            String warn_reason = cmd.substring(warn.length() + nickname.length() + 1).toLowerCase();

            if (cmd.startsWith("/ban ")) {
                if (cfg.getConfigurationSection("logs." + sender) != null) {
                    if (cfg.getConfigurationSection("logs." + sender).getKeys(false).size() >= 10) {
                        ArrayList<String> list = (ArrayList<String>) cfg.getConfigurationSection("logs." + sender).getKeys(false).stream().toList();
                        Collections.sort(list);
                        String first = list.get(1);
                        cfg.set(first, null);

                        String unix = String.valueOf(System.currentTimeMillis() / 1000L);
                        cfg.createSection("logs." + sender + "." + unix + ".type");
                        cfg.createSection("logs." + sender + "." + unix + ".nickname");
                        cfg.createSection("logs." + sender + "." + unix + ".reason");

                        cfg.set("logs." + sender + "." + unix + ".type", "ban");
                        cfg.set("logs." + sender + "." + unix + ".nickname", nickname);
                        if (time.contains("s") | time.contains("m") | time.contains("d") | time.contains("h") | time.contains("mo") && (time.length() <= num.length() + 2)) {
                            cfg.set("logs." + sender + "." + unix + ".reason", ban_reason);
                        } else {
                            cfg.set("logs." + sender + "." + unix + ".reason", ban_reasonperm);
                        }
                        cfg.save(log);


                        String previous = list.get(9);
                        String last = list.get(10);

                        String reason_10 = cfg.getString("logs." + sender + "." + last + ".reason");
                        String reason_9 = cfg.getString("logs." + sender + "." + previous + ".reason");
                        String reason_8 = cfg.getString("logs." + sender + "." + list.get(8) + ".reason");
                        String reason_7 = cfg.getString("logs." + sender + "." + list.get(7) + ".reason");
                        String reason_6 = cfg.getString("logs." + sender + "." + list.get(6) + ".reason");

                        int i_10 = Integer.parseInt(list.get(10));
                        int i_9 = Integer.parseInt(list.get(9));
                        int i_8 = Integer.parseInt(list.get(8));
                        int i_7 = Integer.parseInt(list.get(7));
                        int i_6 = Integer.parseInt(list.get(6));
                        if (Main.getInstance().getConfig().getList("Reasons").toString().contains(cfg.getString("logs." + sender + "." + last + "." + ".reason"))) {
                            if (!(i_10 - i_9 - i_8 - i_7 - i_6 >= 10)) {
                                if (!(i_10 - i_9 - i_8 - i_7 - i_6 >= 2)) {
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("AdminChat")).message(
                                            "#Некк_действия\n\n" + "Модератор " + sender + " выдал 5 наказаний за " + (i_10 - i_9 - i_8 - i_7 - i_6) + " секунд!\n\n" +
                                                    ">> Снимаю группу...").execute();
                                    getServer().getScheduler().callSyncMethod(plugin, () -> {
                                        dispatchCommand(getConsoleSender(), "lp user " + sender + " parent" + " set " + "default");
                                        return null;
                                    });
                                    return;
                                }
                                if (reason_10.contains(reason_9) && reason_9.contains(reason_8) &&
                                        reason_8.contains(reason_7) && reason_7.contains(reason_6)) {
                                    if (!(i_10 - i_9 - i_8 - i_7 - i_6 >= 2)) {
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("AdminChat")).message(
                                                "#Некк_действия\n\n" + "Модератор " + sender + " выдал 5 наказаний подряд по схожим причинам(" + reason_10 + ") за " + (i_10 - i_9 - i_8 - i_7 - i_6) + " секунд!\n\n" +
                                                        ">> Снимаю группу...").execute();
                                        getServer().getScheduler().callSyncMethod(plugin, () -> {
                                            dispatchCommand(getConsoleSender(), "lp user " + sender + " parent" + " set " + "default");
                                            return null;
                                        });
                                        return;
                                    }
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("AdminChat")).message(
                                            "#Некк_действия\n\n" + "Модератор " + sender + " выдал 5 наказаний подряд по схожим причинам(" + reason_10 + ") за " + (i_10 - i_9 - i_8 - i_7 - i_6) + " секунд!").execute();
                                    return;
                                }
                            }
                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("AdminChat")).message(
                                    "#Некк_действия\n\n" + "Модератор " + sender + " забанил игрока " + nickname + " на " + time + " по причине '" + ban_reason + "'!").execute();
                        }

                    } else {
                        String unix = String.valueOf(System.currentTimeMillis() / 1000L);
                        cfg.createSection("logs." + sender + "." + unix + ".type");
                        cfg.createSection("logs." + sender + "." + unix + ".nickname");
                        cfg.createSection("logs." + sender + "." + unix + ".reason");

                        cfg.set("logs." + sender + "." + unix + ".type", "ban");
                        cfg.set("logs." + sender + "." + unix + ".nickname", nickname);
                        if (time.contains("s") | time.contains("m") | time.contains("d") | time.contains("h") | time.contains("mo") && (time.length() <= num.length() + 2)) {
                            cfg.set("logs." + sender + "." + unix + ".reason", ban_reason);
                        } else {
                            cfg.set("logs." + sender + "." + unix + ".reason", ban_reasonperm);
                        }
                        cfg.save(log);
                    }
                } else {
                    String unix = String.valueOf(System.currentTimeMillis() / 1000L);
                    cfg.createSection("logs." + sender + "." + unix + ".type");
                    cfg.createSection("logs." + sender + "." + unix + ".nickname");
                    cfg.createSection("logs." + sender + "." + unix + ".reason");

                    cfg.set("logs." + sender + "." + unix + ".type", "ban");
                    cfg.set("logs." + sender + "." + unix + ".nickname", nickname);
                    if (time.contains("s") | time.contains("m") | time.contains("d") | time.contains("h") | time.contains("mo") && (time.length() <= num.length() + 2)) {
                        cfg.set("logs." + sender + "." + unix + ".reason", ban_reason);
                    } else {
                        cfg.set("logs." + sender + "." + unix + ".reason", ban_reasonperm);
                    }
                    cfg.save(log);
                }
            }
            if (cmd.startsWith("/mute ")) {
                if (cfg.getConfigurationSection("logs." + sender).getKeys(false).size() >= 10) {
                    ArrayList<String> list = (ArrayList<String>) cfg.getConfigurationSection("logs." + sender).getKeys(false).stream().toList();
                    Collections.sort(list);
                    String first = list.get(1);
                    cfg.set(first, null);

                    String unix = String.valueOf(System.currentTimeMillis() / 1000L);
                    cfg.createSection("logs." + sender + "." + unix + ".type");
                    cfg.createSection("logs." + sender + "." + unix + ".nickname");
                    cfg.createSection("logs." + sender + "." + unix + ".reason");

                    cfg.set("logs." + sender + "." + unix + ".type", "ban");
                    cfg.set("logs." + sender + "." + unix + ".nickname", nickname);
                    if (time.contains("s") | time.contains("m") | time.contains("d") | time.contains("h") | time.contains("mo") && (time.length() <= num.length() + 2)) {
                        cfg.set("logs." + sender + "." + unix + ".reason", mute_reason);
                    } else {
                        cfg.set("logs." + sender + "." + unix + ".reason", mute_reasonperm);
                    }
                } else {
                    String unix = String.valueOf(System.currentTimeMillis() / 1000L);
                    cfg.createSection("logs." + sender + "." + unix + ".type");
                    cfg.createSection("logs." + sender + "." + unix + ".nickname");
                    cfg.createSection("logs." + sender + "." + unix + ".reason");

                    cfg.set("logs." + sender + "." + unix + ".type", "mute");
                    cfg.set("logs." + sender + "." + unix + ".nickname", nickname);
                    if (time.contains("s") | time.contains("m") | time.contains("d") | time.contains("h") | time.contains("mo") && (time.length() <= num.length() + 2)) {
                        cfg.set("logs." + sender + "." + unix + ".reason", mute_reason);
                    } else {
                        cfg.set("logs." + sender + "." + unix + ".reason", mute_reasonperm);
                    }
                }
            }
            if (cmd.startsWith("/unban ")) {
                if (cfg.getConfigurationSection("logs." + sender).getKeys(false).size() >= 10) {
                    ArrayList<String> list = (ArrayList<String>) cfg.getConfigurationSection("logs." + sender).getKeys(false).stream().toList();
                    Collections.sort(list);
                    String first = list.get(1);
                    cfg.set(first, null);

                    String unix = String.valueOf(System.currentTimeMillis() / 1000L);
                    cfg.createSection("logs." + sender + "." + unix + ".type");
                    cfg.createSection("logs." + sender + "." + unix + ".nickname");

                    cfg.set("logs." + sender + "." + unix + ".type", "unban");
                    cfg.set("logs." + sender + "." + unix + ".nickname", nickname);
                } else {
                    String unix = String.valueOf(System.currentTimeMillis() / 1000L);
                    cfg.createSection("logs." + sender + "." + unix + ".type");
                    cfg.createSection("logs." + sender + "." + unix + ".nickname");

                    cfg.set("logs." + sender + "." + unix + ".type", "unban");
                    cfg.set("logs." + sender + "." + unix + ".nickname", nickname);
                }
            }
            if (cmd.startsWith("/unmute ")) {
                if (cfg.getConfigurationSection("logs." + sender).getKeys(false).size() >= 10) {
                    ArrayList<String> list = (ArrayList<String>) cfg.getConfigurationSection("logs." + sender).getKeys(false).stream().toList();
                    Collections.sort(list);
                    String first = list.get(1);
                    cfg.set(first, null);

                    String unix = String.valueOf(System.currentTimeMillis() / 1000L);
                    cfg.createSection("logs." + sender + "." + unix + ".type");
                    cfg.createSection("logs." + sender + "." + unix + ".nickname");

                    cfg.set("logs." + sender + "." + unix + ".type", "unmute");
                    cfg.set("logs." + sender + "." + unix + ".nickname", nickname);
                } else {
                    String unix = String.valueOf(System.currentTimeMillis() / 1000L);
                    cfg.createSection("logs." + sender + "." + unix + ".type");
                    cfg.createSection("logs." + sender + "." + unix + ".nickname");

                    cfg.set("logs." + sender + "." + unix + ".type", "unmute");
                    cfg.set("logs." + sender + "." + unix + ".nickname", nickname);
                }
            }
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException exception) {
            return;
        }
    }
}
