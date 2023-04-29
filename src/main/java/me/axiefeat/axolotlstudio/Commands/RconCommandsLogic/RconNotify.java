package me.axiefeat.axolotlstudio.Commands.RconCommandsLogic;

import com.ubivashka.vk.bukkit.BukkitVkApiPlugin;
import com.ubivashka.vk.bukkit.events.VKMessageEvent;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import me.axiefeat.axolotlstudio.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.Random;

import static org.bukkit.Bukkit.*;

public class RconNotify implements Listener {

    Main plugin = Main.plugin();
    private final static VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getVkApiClient();
    private final static GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getActor();

    private final static Random RANDOM = new Random();
    @EventHandler
    public void onMessage(VKMessageEvent e) throws ApiException, ClientException {
        String command = "/s notify ";
        String commandfull = "/service notify ";
        String vk = String.valueOf(e.getMessage().getFromId());
        String admins = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Admins").toArray());
        String moders = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Moders").toArray());
        String auth = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Chats").toArray());
        String id = String.valueOf(e.getMessage().getPeerId() - 2000000000);

        if (e.getMessage().getText().equals("/s notify") && moders.contains(vk) | admins.contains(vk) && !(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Использование: /s notify <ник> <сообщение>").execute();
        }
        if (e.getMessage().getText().equals("/service notify") && moders.contains(vk) | admins.contains(vk) && !(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Использование: /service notify <ник> <сообщение>").execute();
        }

            if (moders.contains(vk) | admins.contains(vk)) {
                if (e.getMessage().getText().startsWith(command)) {
                    if (auth.contains(id)) {
//                        if (!(e.getMessage().getText().startsWith(command)))
//                            return;
//                        if (e.getMessage().getText().length() < command.length())
//                            return;
                        if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                            if (e.getMessage().getText().startsWith(command)) {
                                String Nickname = e.getMessage().getText().toString();
                                String[] parts = Nickname.split(" ");
                                String nick = parts[2];
                                String Online = String.valueOf(Bukkit.getOnlinePlayers());
                                String msg = e.getMessage().getText().substring(command.length() + nick.length() + 1);
                                try {
                                    if (Online.contains(nick)) {
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Вы отправили сообщение '" + msg + "' игроку '" + nick + "'!").execute();
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Уведомления\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") уведомил игрока " + nick + " текст сообщения: '" + msg + "'").execute();
                                        getServer().getScheduler().callSyncMethod(plugin, () -> {
                                            dispatchCommand(getConsoleSender(), "essentials:msg " + nick + " " + msg);
                                            return null;
                                        });
                                    } else {
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Игрок не найден!").execute();
                                        return;
                                    }
                                } catch (StringIndexOutOfBoundsException ignored) {
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Использование: /s notify <ник> <сообщение>").execute();
                                    return;
                                }
                            }
                            if (e.getMessage().getText().startsWith(commandfull)) {
                                String Nickname = e.getMessage().getText().toString();
                                String[] parts = Nickname.split(" ");
                                String nick = parts[2];
                                String Online = String.valueOf(Bukkit.getOnlinePlayers());
                                String msg = e.getMessage().getText().substring(commandfull.length() + nick.length() + 1);
                                try {
                                    if (Online.contains(nick)) {
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Вы отправили сообщение '" + msg + "' игроку '" + nick + "'!").execute();
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Уведомления\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") уведомил игрока " + nick + " текст сообщения: '" + msg + "'").execute();
                                        getServer().getScheduler().callSyncMethod(plugin, () -> {
                                            dispatchCommand(getConsoleSender(), "essentials:msg " + nick + " " + msg);
                                            return null;
                                        });
                                    } else {
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Игрок не найден!").execute();
                                        return;
                                    }
                                } catch (StringIndexOutOfBoundsException ignored) {
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Использование: /s notify <ник> <сообщение>").execute();
                                    return;
                                }
                            }
                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                        }
                    } else {
                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/s notify)\n" +
                                "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                    }
                }
            }
    }
}

