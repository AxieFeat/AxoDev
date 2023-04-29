package me.axiefeat.axolotlstudio.Commands;

import com.ubivashka.vk.bukkit.BukkitVkApiPlugin;
import com.ubivashka.vk.bukkit.events.VKMessageEvent;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import me.axiefeat.axolotlstudio.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.InvalidPluginException;

import java.util.Random;

import static org.bukkit.Bukkit.getOnlinePlayers;

public class UserCommands implements Listener {
    private final static VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getVkApiClient();
    private final static GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getActor();
    private final static Random RANDOM = new Random();

    @EventHandler
    public void onMessage(VKMessageEvent e) throws ApiException, ClientException, InvalidPluginException {

            if (e.getMessage().getText().equalsIgnoreCase("/ping")) {
                if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Pong!") // https://vk.com/dev/messages.send
                            .execute();
                    return;
                } else {
                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                }
            }
            if (e.getMessage().getText().equalsIgnoreCase("/online")) {
                if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Онлайн на Vanilla: " + getOnlinePlayers()) // https://vk.com/dev/messages.send
                            .execute();
                    return;
                } else {
                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                }
            }
            if (e.getMessage().getText().equalsIgnoreCase("/info")) {
                if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message("» Полезная информация:\n" +
                                    "\n" +
                                    "• IP сервера - mc.lifecraft.ru\n" +
                                    "• Группа вк - @mc_lifecraft\n" +
                                    "• Правила проекта - https://vk.cc/ckefWq\n" +
                                    "• Персонал проекта - https://vk.cc/ckavMb") // https://vk.com/dev/messages.send
                            .execute();
                    return;
                } else {
                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                }
            }
            if (e.getMessage().getText().equalsIgnoreCase("/about")) {
                if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message("» Информация о AxoDev:\n" +
                                    "\n" +
                                    "AxoDev version 1.0\n" +
                                    "Vk-bot and Report system special for LifeCraft 2022\n" +
                                    "Website: https://vk.com/axostudio\n" +
                                    "Author: AxieFeat")
                            .execute();
                    return;
                } else {
                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                }
            }
    }
}
