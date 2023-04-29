package me.axiefeat.axolotlstudio.Commands.VKCommandsLogic;

import com.ubivashka.vk.bukkit.BukkitVkApiPlugin;
import com.ubivashka.vk.bukkit.events.VKMessageEvent;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import me.axiefeat.axolotlstudio.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class VkWarn implements Listener {

    File warnscfg = new File(Main.getInstance().getDataFolder(), "warns.yml");
    FileConfiguration warns = YamlConfiguration.loadConfiguration(warnscfg);

    private final static VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getVkApiClient();
    private final static GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getActor();
    private final static Random RANDOM = new Random();

    @EventHandler
    public void onMessage(VKMessageEvent e) throws ApiException, ClientException, IOException {
        String command = "/warn ";
        String vk2 = String.valueOf(e.getMessage().getFromId());
        String admins = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Admins").toArray());
        String moders = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Moders").toArray());
        String managers = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Managers").toArray());
        String auth = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Chats").toArray());
        String id1 = String.valueOf(e.getMessage().getPeerId() - 2000000000);

        if (warns.getConfigurationSection("Warns") == null) {
            warns.createSection("Warns");
        }


        if (moders.contains(vk2) | admins.contains(vk2) | managers.contains(vk2)) {
            if (e.getMessage().getText().startsWith(command)) {
                if (auth.contains(id1)) {
                    if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                        String args = e.getMessage().getText();
                        String[] parts = args.split(" ");
                        String vk = parts[1];
                        String[] part = vk.split("\\|");
                        String vk1 = part[0];
                        String id0 = vk1.replace("]", "");
                        int id = Integer.parseInt(id0.substring(3));
                        String reason = e.getMessage().getText().substring(command.length() + vk.length() + 1);
//                    if (!(e.getMessage().getText().startsWith(command)))
//                        return;
//                    if (e.getMessage().getText().length() < (command).length())
//                        return;
                        if (warns.getString("Warns." + id + ".1") == null) {
                            warns.set("Warns." + id + ".1", reason);
                            warns.save(warnscfg);
                            reloadConfig(warnscfg, warns);
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(
                                    ">> Пользователю " + vk + " выдано предупреждение [1/5] по причине: " + reason).execute();
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Варны\n\n" + ">> Пользователь id" + e.getMessage().getFromId() + " выдал предупреждение [1/5] пользователю " + vk + ". Причина предупреждения: " + reason).execute();
                        } else {
                            if (warns.getString("Warns." + id + ".2") == null) {
                                warns.set("Warns." + id + ".2", reason);
                                warns.save(warnscfg);
                                reloadConfig(warnscfg, warns);
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(
                                        ">> Пользователю " + vk + " выдано предупреждение [2/5] по причине: " + reason).execute();
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Варны\n\n" + ">> Пользователь id" + e.getMessage().getFromId() + " выдал предупреждение [2/5] пользователю " + vk + ". Причина предупреждения: " + reason).execute();
                            } else {
                                if (warns.getString("Warns." + id + ".3") == null) {
                                    warns.set("Warns." + id + ".3", reason);
                                    warns.save(warnscfg);
                                    reloadConfig(warnscfg, warns);
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(
                                            ">> Пользователю " + vk + " выдано предупреждение [3/5] по причине: " + reason).execute();
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Варны\n\n" + ">> Пользователь id" + e.getMessage().getFromId() + " выдал предупреждение [3/5] пользователю " + vk + ". Причина предупреждения: " + reason).execute();
                                } else {
                                    if (warns.getString("Warns." + id + ".4") == null) {
                                        warns.set("Warns." + id + ".4", reason);
                                        warns.save(warnscfg);
                                        reloadConfig(warnscfg, warns);
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(
                                                ">> Пользователю " + vk + " выдано предупреждение [4/5] по причине: " + reason).execute();
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Варны\n\n" + ">> Пользователь id" + e.getMessage().getFromId() + " выдал предупреждение [4/5] пользователю " + vk + ". Причина предупреждения: " + reason).execute();
                                    } else {
                                        if (warns.getString("Warns." + id + ".5") == null) {
                                            warns.set("Warns." + id + ".5", reason);
                                            warns.save(warnscfg);
                                            reloadConfig(warnscfg, warns);
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(
                                                    ">> Пользователю " + vk + " выдано предупреждение [5/5] по причине: " + reason).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(
                                                    ">> Пользователь получил максимальное колличество варнов, выкидываю с беседы...").execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Варны\n\n" + ">> Пользователь id" + e.getMessage().getFromId() + " выдал предупреждение [5/5] пользователю " + vk + ". Причина предупреждения: " + reason).execute();
                                            int peer = (Main.getInstance().getConfig().getInt("MainChat"));
                                            warns.set("Warns." + id, null);
                                            warns.save(warnscfg);
                                            reloadConfig(warnscfg, warns);
                                            CLIENT.messages().removeChatUser(ACTOR, peer).userId(Integer.valueOf(id)).execute();
                                        } else {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(
                                                    ">> Достигнут предел варнов!").execute();
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                    }
                } else {
                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/warn)\n" +
                            "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                }
            }
        }
    }
    public void reloadConfig(File file, FileConfiguration config) {
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            System.err.println(e);
        }
    }
}