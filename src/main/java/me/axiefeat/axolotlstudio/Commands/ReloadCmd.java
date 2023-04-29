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

import java.util.Arrays;
import java.util.Random;

public class ReloadCmd  implements Listener {
    private final static VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getVkApiClient();
    private final static GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getActor();
    private final static Random RANDOM = new Random();

    @EventHandler
    public void onMessage(VKMessageEvent e) throws ApiException, ClientException, InvalidPluginException {

        String command = "/axodev ";
        String vk = String.valueOf(e.getMessage().getFromId());
        String admins = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Admins").toArray());
        String auth = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Chats").toArray());
        String id = String.valueOf(e.getMessage().getPeerId() - 2000000000);
        if (admins.contains(vk)) {
            if (e.getMessage().getText().startsWith(command + "reload")) {
                if (auth.contains(id)) {
//                    if (!(e.getMessage().getText().startsWith(command + "reload")))
//                        return;
//                    if (e.getMessage().getText().length() < (command + "reload").length())
//                        return;
                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Конфиг перезагружен!").execute();
                    Main.getInstance().reloadConfig();
                } else {
                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/reload)\n" +
                            "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                }
            }
        }
    }
}
