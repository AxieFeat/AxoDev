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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class VkModer implements Listener {

    File moderscfg1 = new File(Main.getInstance().getDataFolder(), "moder.yml");
    FileConfiguration moderscfg = YamlConfiguration.loadConfiguration(moderscfg1);

    File moderscfgstorage = new File(Main.getInstance().getDataFolder(), "moder_storage.yml");
    FileConfiguration modersstr = YamlConfiguration.loadConfiguration(moderscfgstorage);

    private static ArrayList<String> nicks = new ArrayList<>();

    private final static VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getVkApiClient();
    private final static GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getActor();
    private final static Random RANDOM = new Random();

    @EventHandler
    public void onMessage(VKMessageEvent e) throws ApiException, ClientException, IOException {
        String command = "/moder ";
        String vk = String.valueOf(e.getMessage().getFromId());
        String admins = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Admins").toArray());
        String moders = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Moders").toArray());
        String auth = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Chats").toArray());
        String id = String.valueOf(e.getMessage().getPeerId() - 2000000000);

        if (moderscfg.getConfigurationSection("Moders") == null) {
            moderscfg.createSection("Moders");
        }
        if (moderscfg.getConfigurationSection("List") == null) {
            moderscfg.createSection("List");
        }
        if (modersstr.getConfigurationSection("Data") == null) {
            modersstr.createSection("Data");
        }

            if (moders.contains(vk) | admins.contains(vk)) {
                if (e.getMessage().getText().startsWith(command)) {
                        if (e.getMessage().getText().startsWith(command + "add ")) {
                            if (auth.contains(id)) {
                                if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
//                        if (!(e.getMessage().getText().startsWith(command + "add ")))
//                            return;
//                        if (e.getMessage().getText().length() < (command + "add ").length())
//                            return;
                                    String Nickname = e.getMessage().getText().toString();
                                    String[] parts = Nickname.split(" ");
                                    String nick = parts[2];
                                    if (moderscfg.getString("Moders." + nick) == null) {
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Модератор " + nick + " добавлен!").execute();
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Модеры\n\n" + ">> Пользователь id" + e.getMessage().getFromId() + " добавил модератора " + nick).execute();
                                        moderscfg.createSection("Moders." + nick + ".Bans");
                                        moderscfg.createSection("Moders." + nick + ".Mutes");
                                        moderscfg.createSection("Moders." + nick + ".Points");
                                        moderscfg.save(moderscfg1);
                                        if (modersstr.getString("Data." + nick) == null) {
                                            modersstr.createSection("Data." + nick);
                                            modersstr.createSection("Data." + nick + ".Bans");
                                            modersstr.createSection("Data." + nick + ".Mutes");
                                            modersstr.createSection("Data." + nick + ".Points");
                                        }
                                        moderscfg.set("Moders." + nick + ".Bans", 0);
                                        moderscfg.set("Moders." + nick + ".Mutes", 0);
                                        moderscfg.save(moderscfg1);
                                        if (modersstr.getInt("Data." + nick + ".Points") == 0) {
                                            modersstr.save(moderscfgstorage);
                                        } else {
                                            if (modersstr.getInt("Data." + nick + ".Points") != 0) {
                                                int old = modersstr.getInt("Data." + nick + ".Points");
                                                moderscfg.set("Moders." + nick + ".Points", old);
                                            }
                                            int now = moderscfg.getInt("Moders." + nick + ".Points");
                                            moderscfg.save(moderscfg1);
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Модератор " + nick + " был ранее на должности, выдано " + now + " баллов.").execute();
                                        }
                                    } else {
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Модератор " + nick + " уже на должности!").execute();
                                    }
                                } else {
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                                }
                            } else {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/moder add)\n" +
                                        "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                            }
                        }
                        if (e.getMessage().getText().startsWith(command + "info ")) {
                            if (auth.contains(id)) {
                                if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
//                            if (!(e.getMessage().getText().startsWith(command + "info ")))
//                                return;
//                            if (e.getMessage().getText().length() < (command + "info ").length())
//                                return;
                                    String Nickname = e.getMessage().getText().toString();
                                    String[] parts = Nickname.split(" ");
                                    String nick = parts[2];
                                    int points = modersstr.getInt("Data." + nick + ".Points");
                                    String mutes = String.valueOf(moderscfg.getInt("Moders." + nick + ".Bans"));
                                    String bans = String.valueOf(moderscfg.getInt("Moders." + nick + ".Mutes"));
                                    String Nicknames = moderscfg.getString("Moders." + nick + ".Bans");
                                    String Is = moderscfg.getString("Moders." + nick);
                                    String Is2 = modersstr.getString("Data." + nick);
                                    if (Nicknames != null) {
                                        if (Is != null) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Информации о модераторе " + nick + ":\n" +
                                                    "\n" + "• Баллов: " + points + "\n" + "• Банов: " + bans + "\n" + "• Мутов: " + mutes + "\n\n" + "Место в рейтинге: #").execute();
                                        } else {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Модератор " + nick + " не найден!").execute();
                                        }
                                    } else {
                                        if (Is2 != null) {
                                            int OldPoints = modersstr.getInt("Data." + nick + ".Points");
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Информации о модераторе " + nick + ":\n" +
                                                    "\n" + "• Баллов: " + OldPoints + "\n\n" + "Модератор удалён!").execute();
                                        } else {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Модератор " + nick + " не найден!").execute();
                                        }
                                    }
                                } else {
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                                }
                            } else {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/moder info)\n" +
                                        "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                            }
                        }
                        if (e.getMessage().getText().startsWith(command + "del ")) {
                            if (auth.contains(id)) {
                                if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                                    String Nickname = e.getMessage().getText().toString();
                                    String[] parts = Nickname.split(" ");
                                    String nick = parts[2];
                                    String Is = moderscfg.getString("Moders." + nick);
                                    if (Is != null) {
                                        moderscfg.set("Moders." + nick, null);
                                        moderscfg.set("List." + nick + " - %bans%", null);
                                        nicks.remove("%number%. " + nick + " - %bans% наказаний");
                                        moderscfg.set("List", nicks.stream().toList());
                                        moderscfg.save(moderscfg1);
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Модератор " + nick + " удалён!").execute();
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Модеры\n\n" + ">> Пользователь id" + e.getMessage().getFromId() + " удалил модератора " + nick).execute();
                                    } else {
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Модератор " + nick + " не найден!").execute();
                                    }
                                } else {
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                                }
                            } else {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/moder del)\n" +
                                        "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                            }
                        }
                    if (e.getMessage().getText().startsWith(command + "list add ")) {
                        if (auth.contains(id)) {
                            if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
//                            if (!(e.getMessage().getText().startsWith(command + "del ")))
//                                return;
//                            if (e.getMessage().getText().length() < (command + "del ").length())
//                                return;
                                String Nickname = e.getMessage().getText().toString();
                                String[] parts = Nickname.split(" ");
                                String nick = parts[3];
                                if (moderscfg.getString("Moders." + nick) != null) {
                                    if ((nicks.size()) == 0) {
                                        nicks.add("%number%. " + nick + " - %bans% наказаний");
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Модератор " + nick + " добавлен в список!").execute();
                                        moderscfg.set("List", nicks.stream().toList());
                                        moderscfg.save(moderscfg1);
                                    } else {
                                        nicks.add("%number%. " + nick + " - %bans% наказаний");
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Модератор " + nick + " добавлен в список!").execute();
                                        moderscfg.set("List", nicks.stream().toList());
                                        moderscfg.save(moderscfg1);
                                    }
                                } else {
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Модератор " + nick + " не существует!").execute();
                                }
                            } else {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                            }
                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/moder del)\n" +
                                    "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                        }
                    }
                        if (e.getMessage().getText().startsWith(command + "score ")) {
                            if (e.getMessage().getText().startsWith(command + "score add ")) {
                                if (auth.contains(id)) {
                                    if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
//                                if (!(e.getMessage().getText().startsWith(command + "score add ")))
//                                    return;
//                                if (e.getMessage().getText().length() < (command + "score add ").length())
//                                    return;
                                        String Nickname = e.getMessage().getText().toString();
                                        String[] parts = Nickname.split(" ");
                                        String nick = parts[3];
                                        int points = Integer.parseInt(parts[4]);
                                        int old = moderscfg.getInt("Moders." + nick + ".Points");
                                        int old2 = modersstr.getInt("Data." + nick + ".Points");
                                        String Is = moderscfg.getString("Moders." + nick);
                                        if (Is != null) {
                                            moderscfg.set("Moders." + nick + ".Points", old + points);
                                            modersstr.set("Data." + nick + ".Points", old2 + points);
                                            int result = moderscfg.getInt("Moders." + nick + ".Points");
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Добавлено " + points + " баллов модератору " + nick + ". Всего баллов: " + result).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Модеры\n\n" + ">> Пользователь id" + e.getMessage().getFromId() + " добавил модератору '" + nick + "' " + points + " баллов.").execute();
                                            moderscfg.save(moderscfg1);
                                            modersstr.save(moderscfgstorage);
                                        } else {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Модератор " + nick + " не найден!").execute();
                                        }
                                    } else {
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                                    }
                                } else {
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/moder score)\n" +
                                            "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                                }
                            }
                            if (e.getMessage().getText().startsWith(command + "score take ")) {
                                if (auth.contains(id)) {
                                    if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
//                                if (!(e.getMessage().getText().startsWith(command + "score take ")))
//                                    return;
//                                if (e.getMessage().getText().length() < (command + "score take ").length())
//                                    return;
                                        String Nickname = e.getMessage().getText().toString();
                                        String[] parts = Nickname.split(" ");
                                        String nick = parts[3];
                                        int points = Integer.parseInt(parts[4]);
                                        int old = moderscfg.getInt("Moders." + nick + ".Points");
                                        int old2 = modersstr.getInt("Data." + nick + ".Points");
                                        String Is = moderscfg.getString("Moders." + nick);
                                        if (Is != null) {
                                            moderscfg.set("Moders." + nick + ".Points", old - points);
                                            modersstr.set("Data." + nick + ".Points", old2 - points);
                                            int result = moderscfg.getInt("Moders." + nick + ".Points");
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Забрано " + points + " баллов у модератора " + nick + ". Всего баллов: " + result).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Модеры\n\n" + ">> Пользователь id" + e.getMessage().getFromId() + " забрал у модератора '" + nick + "' " + points + " баллов.").execute();
                                            moderscfg.save(moderscfg1);
                                            modersstr.save(moderscfgstorage);
                                        } else {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Модератор " + nick + " не найден!").execute();
                                        }
                                    } else {
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                                    }
                                } else {
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/moder score)\n" +
                                            "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                                }
                            }

                        }
                    }
            }
    }
    public static void savedb() throws IOException {

//        Gson gson = new Gson();
//        System.out.println(Main.getInstance().getDataFolder().getAbsolutePath());
//        File file = new File(Main.getInstance().getDataFolder().getAbsolutePath() + "/db.json");
//        file.getParentFile().mkdir();
//        file.createNewFile();
//        Writer writer = new FileWriter(file, false);
//        gson.toJson(nicks, writer);
//        writer.flush();
//        writer.close();
//        System.out.println("[VK] db saved.");
    }
}
