package me.axiefeat.axolotlstudio.Commands.VKCommandsLogic;

import com.ubivashka.vk.bukkit.BukkitVkApiPlugin;
import com.ubivashka.vk.bukkit.events.VKMessageEvent;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import me.axiefeat.axolotlstudio.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class VkUnwarn implements Listener {

    File warnscfg = new File(Main.getInstance().getDataFolder(), "warns.yml");
    FileConfiguration warns = YamlConfiguration.loadConfiguration(warnscfg);

    private final static VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getVkApiClient();
    private final static GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getActor();
    private final static Random RANDOM = new Random();

    @EventHandler
    public void onMessage(VKMessageEvent e) throws ApiException, ClientException, IOException {
        String command = "/unwarn ";
        String vk2 = String.valueOf(e.getMessage().getFromId());
        String admins = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Admins").toArray());
        String moders = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Moders").toArray());
        String managers = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Managers").toArray());
        String auth = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Chats").toArray());
        String id2 = String.valueOf(e.getMessage().getPeerId() - 2000000000);

            if (moders.contains(vk2) | admins.contains(vk2) | managers.contains(vk2)) {
                if (e.getMessage().getText().startsWith(command)) {
                if (auth.contains(id2)) {
                    if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                        String args = e.getMessage().getText();
                        String[] parts = args.split(" ");
                        String vk = parts[1];
                        String[] part = vk.split("\\|");
                        String vk1 = part[0];
                        String id0 = vk1.replace("]", "");
//                    if (!(e.getMessage().getText().startsWith(command)))
//                        return;
//                    if (e.getMessage().getText().length() < (command).length())
//                        return;
                        if (e.getMessage().getText().equals(command + vk)) {
                            int id = Integer.parseInt(id0.substring(3));
                            if (warns.getString("Warns." + id + ".5") != null) {
                                String reason = warns.getString("Warns." + id + ".5");
                                warns.set("Warns." + id + ".5", null);
                                warns.save(warnscfg);
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(
                                        ">> Снято предупреждение [5/5] с пользователя " + vk + ". Причина предупреждения: " + reason).execute();
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Варны\n\n" + ">> Пользователь id" + e.getMessage().getFromId() + " снял предупреждение [5/5] с " + vk + ". Причина предупреждения: " + reason).execute();
                            } else {
                                if (warns.getString("Warns." + id + ".4") != null) {
                                    String reason = warns.getString("Warns." + id + ".4");
                                    warns.set("Warns." + id + ".4", null);
                                    warns.save(warnscfg);
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(
                                            ">> Снято предупреждение [4/5] с пользователя " + vk + ". Причина предупреждения: " + reason).execute();
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Варны\n\n" + ">> Пользователь id" + e.getMessage().getFromId() + " снял предупреждение [4/5] с " + vk + ". Причина предупреждения: " + reason).execute();
                                } else {
                                    if (warns.getString("Warns." + id + ".3") != null) {
                                        String reason = warns.getString("Warns." + id + ".3");
                                        warns.set("Warns." + id + ".3", null);
                                        warns.save(warnscfg);
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(
                                                ">> Снято предупреждение [3/5] с пользователя " + vk + ". Причина предупреждения: " + reason).execute();
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Варны\n\n" + ">> Пользователь id" + e.getMessage().getFromId() + " снял предупреждение [3/5] с " + vk + ". Причина предупреждения: " + reason).execute();
                                    } else {
                                        if (warns.getString("Warns." + id + ".2") != null) {
                                            String reason = warns.getString("Warns." + id + ".2");
                                            warns.set("Warns." + id + ".2", null);
                                            warns.save(warnscfg);
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(
                                                    ">> Снято предупреждение [2/5] с пользователя " + vk + ". Причина предупреждения: " + reason).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Варны\n\n" + ">> Пользователь id" + e.getMessage().getFromId() + " снял предупреждение [2/5] с " + vk + ". Причина предупреждения: " + reason).execute();
                                        } else {
                                            if (warns.getString("Warns." + id + ".1") != null) {
                                                String reason = warns.getString("Warns." + id + ".1");
                                                warns.set("Warns." + id, null);
                                                warns.save(warnscfg);
                                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(
                                                        ">> Снято предупреждение [1/5] с пользователя " + vk + ". Причина предупреждения: " + reason).execute();
                                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Варны\n\n" + ">> Пользователь id" + e.getMessage().getFromId() + " снял предупреждение [1/5] с " + vk + ". Причина предупреждения: " + reason).execute();
                                            } else {
                                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(
                                                        ">> У пользователя нет предупреждений!").execute();
                                            }
                                        }
                                    }
                                }
                            }
                            if (e.getMessage().getText().equals(command + vk + " all")) {
                                if (auth.contains(id2)) {
                                    warns.set("Warns." + id, null);
                                    warns.save(warnscfg);
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(
                                            ">> C пользователя " + vk + " сняты все предупреждения!").execute();
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Варны\n\n" + ">> Пользователь id" + e.getMessage().getFromId() + " снял все предупреждения с " + vk).execute();
                                } else {
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                                }
                            }
                        }
                        } else {
                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                    }
                    } else {
                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/unwarn)\n" +
                            "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                }
            }
            }
        }
   }
