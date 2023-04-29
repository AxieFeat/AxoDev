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

public class VkWarnList implements Listener {

    File warnscfg = new File(Main.getInstance().getDataFolder(), "warns.yml");
    FileConfiguration warns = YamlConfiguration.loadConfiguration(warnscfg);

    private final static VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getVkApiClient();
    private final static GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getActor();
    private final static Random RANDOM = new Random();

    @EventHandler
    public void onMessage(VKMessageEvent e) throws ApiException, ClientException, IOException {
        String command = "/warns ";
        String vk3 = String.valueOf(e.getMessage().getFromId());
        String admins = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Admins").toArray());
        String moders = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Moders").toArray());
        String managers = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Managers").toArray());
        String auth = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Chats").toArray());
        String id2 = String.valueOf(e.getMessage().getPeerId() - 2000000000);

        if (moders.contains(vk3) | admins.contains(vk3) | managers.contains(vk3)) {
            if (e.getMessage().getText().startsWith(command)) {
                if (auth.contains(id2)) {
                    if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
//                        if (!(e.getMessage().getText().startsWith(command)))
//                            return;
//                        if (e.getMessage().getText().length() < (command).length())
//                            return;
                        String args = e.getMessage().getText();
                        String[] parts = args.split(" ");
                        String vk = parts[1];
                        String[] part = vk.split("\\|");
                        String vk1 = part[0];
                        String id0 = vk1.replace("]", "");
                        int id = Integer.parseInt(id0.substring(3));
                        if (warns.getString("Warns." + id + ".5") != null) {
                            String reason5 = warns.getString("Warns." + id + ".5");
                            String reason4 = warns.getString("Warns." + id + ".4");
                            String reason3 = warns.getString("Warns." + id + ".3");
                            String reason2 = warns.getString("Warns." + id + ".2");
                            String reason1 = warns.getString("Warns." + id + ".1");
                            warns.save(warnscfg);
                            reloadConfig(warnscfg, warns);
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(
                                    ">> У пользователя " + vk + " [5/5] предупреждений:\n\n"
                                            + "• [1/5] - " + reason1
                                            + "\n• [2/5] - " + reason2
                                            + "\n• [3/5] - " + reason3
                                            + "\n• [4/5] - " + reason4
                                            + "\n• [5/5] - " + reason5).execute();
                        } else {
                            if (warns.getString("Warns." + id + ".4") != null) {
                                String reason4 = warns.getString("Warns." + id + ".4");
                                String reason3 = warns.getString("Warns." + id + ".3");
                                String reason2 = warns.getString("Warns." + id + ".2");
                                String reason1 = warns.getString("Warns." + id + ".1");
                                warns.save(warnscfg);
                                reloadConfig(warnscfg, warns);
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(
                                        ">> У пользователя " + vk + " [4/5] предупреждений:\n\n"
                                                + "• [1/5] - " + reason1
                                                + "\n• [2/5] - " + reason2
                                                + "\n• [3/5] - " + reason3
                                                + "\n• [4/5] - " + reason4).execute();
                            } else {
                                if (warns.getString("Warns." + id + ".3") != null) {
                                    String reason3 = warns.getString("Warns." + id + ".3");
                                    String reason2 = warns.getString("Warns." + id + ".2");
                                    String reason1 = warns.getString("Warns." + id + ".1");
                                    warns.save(warnscfg);
                                    reloadConfig(warnscfg, warns);
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(
                                            ">> У пользователя " + vk + " [3/5] предупреждений:\n\n"
                                                    + "• [1/5] - " + reason1
                                                    + "\n• [2/5] - " + reason2
                                                    + "\n• [3/5] - " + reason3).execute();
                                } else {
                                    if (warns.getString("Warns." + id + ".2") != null) {
                                        String reason2 = warns.getString("Warns." + id + ".2");
                                        String reason1 = warns.getString("Warns." + id + ".1");
                                        warns.save(warnscfg);
                                        reloadConfig(warnscfg, warns);
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(
                                                ">> У пользователя " + vk + " [2/5] предупреждений:\n\n"
                                                        + "• [1/5] - " + reason1
                                                        + "\n• [2/5] - " + reason2).execute();
                                    } else {
                                        if (warns.getString("Warns." + id + ".1") != null) {
                                            String reason1 = warns.getString("Warns." + id + ".1");
                                            warns.save(warnscfg);
                                            reloadConfig(warnscfg, warns);
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(
                                                    ">> У пользователя " + vk + " [1/5] предупреждений:\n\n"
                                                            + "• [1/5] - " + reason1).execute();
                                        } else {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(
                                                    ">> У пользователя нет предупреждений!").execute();
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                    }
                } else {
                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/warns)\n" +
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
