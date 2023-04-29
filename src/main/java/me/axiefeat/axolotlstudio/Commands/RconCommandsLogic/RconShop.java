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

import static me.axiefeat.axolotlstudio.Main.*;
import static org.bukkit.Bukkit.*;

public class RconShop implements Listener {

    Main plugin = Main.plugin();
    private final static VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getVkApiClient();
    private final static GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getActor();

    private final static Random RANDOM = new Random();
    @EventHandler
    public void onMessage(VKMessageEvent e) throws ApiException, ClientException {
        String command = "/shop ";
        String vk = String.valueOf(e.getMessage().getFromId());
        String admins = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Admins").toArray());
        String moders = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Moders").toArray());
        String auth = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Chats").toArray());
        String id = String.valueOf(e.getMessage().getPeerId() - 2000000000);
            if (moders.contains(vk) | admins.contains(vk)) {
                if (e.getMessage().getText().startsWith(command)) {
                    if (auth.contains(id)) {
                        if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
//                    if (!(e.getMessage().getText().startsWith(command)))
//                        return;
//                    if (e.getMessage().getText().length() < command.length())
//                        return;
                            String args = e.getMessage().getText().toString();
                            String[] parts = args.split(" ");
                            String type = parts[1];
                            String nick = parts[2];
                            String amount = parts[3];
                            try {
                                if (type.equalsIgnoreCase("rep")) {
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Не реализовано!").execute();
                                    return;
                                }
                                if (type.equalsIgnoreCase("money")) {
                                    if (amount.startsWith("-")) {
                                        String money = amount.replace("-", "");
                                        getServer().getScheduler().callSyncMethod(plugin, () -> {
                                            dispatchCommand(getConsoleSender(), "eco take " + nick + " " + money);
                                            return null;
                                        });
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Забрано " + money + "$ у игрока " + nick).execute();
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Монеты\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") забрал у игрока '" + nick + "' " + money + "$").execute();
                                        return;
                                    }
                                    if (!amount.startsWith("-")) {
                                        String money = amount.replace("+", "");
                                        getServer().getScheduler().callSyncMethod(plugin, () -> {
                                            dispatchCommand(getConsoleSender(), "eco give " + nick + " " + money);
                                            return null;
                                        });
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Выдано " + money + "$ игроку " + nick).execute();
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Монеты\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") выдал игроку '" + nick + "' " + money + "$").execute();
                                        return;
                                    }
                                }
                                if (type.equalsIgnoreCase("snow")) {
                                    if (amount.startsWith("-")) {
                                        String snow = amount.replace("-", "");
                                        getServer().getScheduler().callSyncMethod(plugin, () -> {
                                            dispatchCommand(getConsoleSender(), "p take " + nick + " " + snow);
                                            return null;
                                        });
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Забрано " + snow + "❄ у игрока " + nick).execute();
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Снежинки\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") забрал у игрока '" + nick + "' " + snow + " снежинок.").execute();
                                        return;
                                    }
                                    if (!amount.startsWith("-")) {
                                        String snow = amount.replace("+", "");
                                        getServer().getScheduler().callSyncMethod(plugin, () -> {
                                            dispatchCommand(getConsoleSender(), "p give " + nick + " " + snow);
                                            return null;
                                        });
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Выдано " + snow + "❄ игроку " + nick).execute();
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Снежинки\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") выдал игроку '" + nick + "' " + snow + " снежинок.").execute();
                                        return;
                                    }
                                }
                            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException ignored) {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Использование: /shop <rep/money/snow> <ник> <кол-во>").execute();
                                return;
                            }
                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                        }
                    } else {
                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/shop)\n" +
                                "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                    }
                }
            }
    }
}


