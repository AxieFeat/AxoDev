package me.axiefeat.axolotlstudio.Commands.RconCommandsLogic;

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

import java.util.Arrays;
import java.util.Random;

public class RconIsBlock implements Listener {
    private final static VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getVkApiClient();
    private final static GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getActor();
    private final static Random RANDOM = new Random();

    @EventHandler
    public void onMessage(VKMessageEvent e) throws ApiException, ClientException, InvalidPluginException {
        String vk = String.valueOf(e.getMessage().getFromId());
        String admins = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Admins").toArray());
        String moders = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Moders").toArray());
        String auth = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Chats").toArray());
        String id = String.valueOf(e.getMessage().getPeerId() - 2000000000);

        if (e.getMessage().getText().equals("/s isblock") && moders.contains(vk) | admins.contains(vk) && !(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Использование: /s isblock <ник>").execute();
        }
        if (e.getMessage().getText().equals("/service isblock") && moders.contains(vk) | admins.contains(vk) && !(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Использование: /service isblock <ник>").execute();
        }

        if (moders.contains(vk) | admins.contains(vk)) {
                    String command = "/s isblock ";
                    if (!(e.getMessage().getText().startsWith(command)))
                        return;
                    if (e.getMessage().getText().length() < command.length())
                        return;
                    String Nickname = e.getMessage().getText().substring(command.length());
                    try {
                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message("\uD83D\uDC41\u200D\uD83D\uDDE8Блокировки игрока" + Nickname + "\n" +
                                "\n" +
                                "\uD83D\uDE36Мут: Нет\n" +
                                "\n" +
                                "⛓Бан: Нет" +
                                "\n" +
                                "Наказание выдал: Никто" +
                                "Причина: Нет" +
                                "Срок: Нет").execute();
                        return;
                    } catch (NullPointerException ignored) {
                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Произошла ошибка!").execute();
                }
        }
    }
}

