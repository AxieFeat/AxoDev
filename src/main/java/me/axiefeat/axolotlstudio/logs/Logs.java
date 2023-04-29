package me.axiefeat.axolotlstudio.logs;

import com.ubivashka.vk.bukkit.BukkitVkApiPlugin;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import me.axiefeat.axolotlstudio.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Random;

public class Logs implements Listener {
    private final static VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getVkApiClient();
    private final static GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getActor();
    private final static Random RANDOM = new Random();

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent e) throws ClientException, ApiException {
        try {
            String message = e.getMessage();
            String ban = "/ban ";
            String mute = "/ban ";
            String[] array = message.split(" ");
            String nickname = array[1];
            String time = array[2];
            String banreason = message.substring(ban.length() + nickname.length() + time.length() + 2);
            String banreasonperm = message.substring(ban.length() + nickname.length() + 1);
            String mutereason = message.substring(mute.length() + nickname.length() + time.length() + 3);
            String mutereasonperm = message.substring(mute.length() + nickname.length() + 2);
            String num = time.replaceAll("[^0-9]", "");
            String totime = time.replaceAll("[^a-z]", "");
            String by = String.valueOf(e.getPlayer()).replace("CraftPlayer{name=", "").replace("}", "");
            if (message.startsWith("/ban ")) {
                if (Main.getInstance().getConfig().getString("User.Moder." + by + ".Bans") != null) {
                    if (time.contains(num) & totime.equals("d") | totime.equals("h") | (totime.equals("m") | (totime.equals("s") | (totime.equals("mo"))))) {
                        if (totime.equals("s")) {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message(
                                    "#Баны #Модеры\n\n" + ">> Модератор " + by + " забанил игрока " + nickname + " на " + num + " секунд по причине: '" + banreason + "'!").execute();
                        }
                        if (totime.equals("m")) {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message(
                                    "#Баны #Модеры\n\n" + ">> Модератор " + by + " забанил игрока " + nickname + " на " + num + " минут по причине: '" + banreason + "'!").execute();
                        }
                        if (totime.equals("h")) {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message(
                                    "#Баны #Модеры\n\n" + ">> Модератор " + by + " забанил игрока " + nickname + " на " + num + " часов по причине: '" + banreason + "'!").execute();
                        }
                        if (totime.equals("d")) {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message(
                                    "#Баны #Модеры\n\n" + ">> Модератор " + by + " забанил игрока " + nickname + " на " + num + " дней по причине: '" + banreason + "'!").execute();
                        }
                        if (totime.equals("mo")) {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message(
                                    "#Баны #Модеры\n\n" + ">> Модератор " + by + " забанил игрока " + nickname + " на " + num + " месяцев по причине: '" + banreason + "'!").execute();
                        }
                    } else {
                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message(
                                "#Баны #Модеры\n\n" + ">> Модератор " + by + " забанил игрока " + nickname + " навсегда по причине: '" + banreasonperm + "'!").execute();
                    }
                    int bans = Main.getInstance().getConfig().getInt("Users.Moder." + by + ".Bans");
                    Main.getInstance().getConfig().set("Users.Moder." + by + ".Bans", 1+bans);
                    Main.getInstance().saveConfig();
                }
            }
            if (message.startsWith("/mute ")) {
                if (Main.getInstance().getConfig().getString("User.Moder." + by + ".Bans") != null) {
                    if (time.contains(num) & totime.equals("d") | totime.equals("h") | (totime.equals("m") | (totime.equals("s") | (totime.equals("mo"))))) {
                        if (totime.equals("s")) {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message(
                                    "#Муты #Модеры\n\n" + ">> Модератор " + by + " замутил игрока " + nickname + " на " + num + " секунд по причине: '" + mutereason + "'!").execute();
                        }
                        if (totime.equals("m")) {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message(
                                    "#Муты #Модеры\n\n" + ">> Модератор " + by + " замутил игрока " + nickname + " на " + num + " минут по причине: '" + mutereason + "'!").execute();
                        }
                        if (totime.equals("h")) {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message(
                                    "#Муты #Модеры\n\n" + ">> Модератор " + by + " замутил игрока " + nickname + " на " + num + " часов по причине: '" + mutereason + "'!").execute();
                        }
                        if (totime.equals("d")) {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message(
                                    "#Муты #Модеры\n\n" + ">> Модератор " + by + " замутил игрока " + nickname + " на " + num + " дней по причине: '" + mutereason + "'!").execute();
                        }
                        if (totime.equals("mo")) {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message(
                                    "#Муты #Модеры\n\n" + ">> Модератор " + by + " замутил игрока " + nickname + " на " + num + " месяцев по причине: '" + mutereason + "'!").execute();
                        }
                    } else {
                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message(
                                "#Муты #Модеры\n\n" + ">> Модератор " + by + " замутил игрока " + nickname + " навсегда по причине: '" + mutereasonperm + "'!").execute();
                    }
                    int mutes = Main.getInstance().getConfig().getInt("Users.Moder." + by + ".Bans");
                    Main.getInstance().getConfig().set("Users.Moder." + by + ".Bans", 1+mutes);
                    Main.getInstance().saveConfig();
                }
            }
            if (message.startsWith("/unban")) {
                if (Main.getInstance().getConfig().getString("User.Moder." + by + ".Bans") != null) {
                    if(message.contains("/unban " + nickname)) {
                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message(
                                "#Баны #Модеры\n\n" + ">> Модератор " + by + " разбанил игрока " + nickname + "!").execute();
                    }
                }
            }
            if (message.startsWith("/unmute")) {
                if (Main.getInstance().getConfig().getString("User.Moder." + by + ".Bans") != null) {
                    if(message.contains("/unmute " + nickname)) {
                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message(
                                "#Баны #Модеры\n\n" + ">> Модератор " + by + " размутил игрока " + nickname + "!").execute();
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException exception) {
            return;
        }
    }
}
