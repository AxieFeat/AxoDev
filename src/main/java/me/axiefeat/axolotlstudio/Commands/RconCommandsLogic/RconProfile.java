package me.axiefeat.axolotlstudio.Commands.RconCommandsLogic;


import com.earth2me.essentials.api.UserDoesNotExistException;
import com.ubivashka.vk.bukkit.BukkitVkApiPlugin;
import com.ubivashka.vk.bukkit.events.VKMessageEvent;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import me.axiefeat.axolotlstudio.Main;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.InvalidPluginException;

import java.util.Arrays;
import java.util.Random;

import static com.earth2me.essentials.api.Economy.getMoneyExact;

public class RconProfile implements Listener {

    private static Permission perms = null;
    private final static VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getVkApiClient();
    private final static GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getActor();
    private final static Random RANDOM = new Random();

    @EventHandler
    public void onMessage(VKMessageEvent e) throws ApiException, ClientException, InvalidPluginException {
        String command = "/s usrinfo ";
        String commandfull = "/service usrinfo ";
        String vk = String.valueOf(e.getMessage().getFromId());
        String admins = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Admins").toArray());
        String moders = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Moders").toArray());
        String auth = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Chats").toArray());
        String id = String.valueOf(e.getMessage().getPeerId() - 2000000000);

        if (e.getMessage().getText().equals("/s usrinfo") && moders.contains(vk) | admins.contains(vk) && !(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Использование: /s usrinfo <ник>").execute();
        }
        if (e.getMessage().getText().equals("/service usrinfo") && moders.contains(vk) | admins.contains(vk) && !(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Использование: /service usrinfo <ник>").execute();
        }

        if (moders.contains(vk) | admins.contains(vk)) {
            if (e.getMessage().getText().startsWith(command)) {

//                if (!(e.getMessage().getText().startsWith(command)))
//                    return;
//                if (e.getMessage().getText().length() < command.length())
//                    return;
                String Nickname = e.getMessage().getText().substring(command.length());
                Player p = Main.getInstance().getServer().getPlayer(Nickname);
                try {
                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message("\uD83D\uDC64Профиль игрока " + Nickname +
                            "\n" +
                            "\n" +
                            "\uD83D\uDCB0Баланс: " + getMoneyExact(Nickname).doubleValue() + " •\n" +
                            "\n" +
                            "✨Группа игрока: " + perms.getPrimaryGroup(p) + "\n" +
                            "\n" +
                            "\uD83D\uDCAC Привязка: " + "Отсуствует\n" +
                            "\uD83D\uDD10 2FA: " + "Отсутсвует\n" +
                            "\n" +
                            "\uD83D\uDE36 Блокировка чата: " + "(SOON...)\n" +
                            "⛓Блокировка: " + "(SOON...)\n").execute();
                    return;
                } catch (NullPointerException | UserDoesNotExistException ignored) {
                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Игрок не найден!").execute();
                }
            }
            if (e.getMessage().getText().startsWith(commandfull)) {

//                if (!(e.getMessage().getText().startsWith(commandfull)))
//                    return;
//                if (e.getMessage().getText().length() < commandfull.length())
//                    return;
                String Nickname = e.getMessage().getText().substring(commandfull.length());
                Player p = Main.getInstance().getServer().getPlayer(Nickname);
                try {
                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message("\uD83D\uDC64Профиль игрока " + Nickname +
                            "\n" +
                            "\n" +
                            "\uD83D\uDCB0Баланс: " + getMoneyExact(Nickname).doubleValue() + " •\n" +
                            "\n" +
                            "✨Группа игрока: " + perms.getPrimaryGroup(p) + "\n" +
                            "\n" +
                            "\uD83D\uDCAC Привязка: " + "Отсуствует\n" +
                            "\uD83D\uDD10 2FA: " + "Отсутсвует\n" +
                            "\n" +
                            "\uD83D\uDE36 Блокировка чата: " + "(SOON...)\n" +
                            "⛓Блокировка: " + "(SOON...)\n").execute();
                    return;
                } catch (NullPointerException | UserDoesNotExistException ignored) {
                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Игрок не найден!").execute();
                }
            }
        }
    }

}
