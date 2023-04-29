package me.axiefeat.axolotlstudio.Commands;

import com.ubivashka.vk.bukkit.BukkitVkApiPlugin;
import com.ubivashka.vk.bukkit.events.VKMessageEvent;
import com.ubivashka.vk.bukkit.events.VKMessageReplyEvent;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.messages.MessageAction;
import me.axiefeat.axolotlstudio.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.InvalidPluginException;

import java.util.Random;

public class ChatId implements Listener {
    private final static VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getVkApiClient();
    private final static GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getActor();
    private final static Random RANDOM = new Random();

    @EventHandler
    public void onJoin(VKMessageEvent e) throws ApiException, ClientException, InvalidPluginException {
//        String action = String.valueOf(e.getEventName().equals("chat_invite_user"));
//        String check = String.valueOf(Main.getInstance().getConfig().getConfigurationSection("Chats" + (e.getPeer() - 2000000000)));
//        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована!\n" +
//             "CHAT ID: " + (e.getPeer() - 2000000000) + "\n\n" + action).execute();
    }
}
