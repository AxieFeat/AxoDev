package me.axiefeat.axolotlstudio.Commands.VKCommandsLogic;

import com.ubivashka.vk.bukkit.BukkitVkApiPlugin;
import com.ubivashka.vk.bukkit.events.VKMessageEvent;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiAccessException;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import me.axiefeat.axolotlstudio.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.Random;

public class VkKick implements Listener {
    private final static VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getVkApiClient();
    private final static GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getActor();
    private final static Random RANDOM = new Random();

    @EventHandler
    public void onMessage(VKMessageEvent e) throws ApiException, ClientException, ApiAccessException {
        String command = "/kick ";
        String vk = String.valueOf(e.getMessage().getFromId());
        String admins = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Admins").toArray());
        String moders = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Moders").toArray());
        String auth = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Chats").toArray());
        String id2 = String.valueOf(e.getMessage().getPeerId() - 2000000000);
            if (moders.contains(vk) | admins.contains(vk)) {
                if (e.getMessage().getText().startsWith(command)) {
                    if (auth.contains(id2)) {
                        if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                            String prevkid = e.getMessage().getText();
                            String[] vkfo = prevkid.split(" ");
                            String vkk = vkfo[1];
                            String[] vkffo = vkk.split("\\|");
                            String vkiid = vkffo[0];
                            String vkid = vkiid.replace("[", "").replace("id", "");

                            try {
                                Integer idvk = Integer.valueOf(vkid);
                                int peer = (e.getPeer()) - 2000000000;
                                try {
                                    String reason = e.getMessage().getText().substring(command.length() + vkk.length() + 1);
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Пользователь @id" + vkid + " был кикнут с беседы по причине: '" + reason + "'!").execute();
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Кики_с_беседы\n\n" + ">> Пользователь id" + e.getMessage().getFromId() + " кикнул @id" + vkid + " с беседы по причине: '" + reason + "'!").execute();
                                    CLIENT.messages().removeChatUser(ACTOR, peer).userId(Integer.valueOf(vkid)).execute();
                                    Main.getInstance().getConfig().set("Warns." + vkid, null);
                                    Main.getInstance().saveConfig();
                                } catch (ApiAccessException exception) {
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Невозможно кикнуть!") // https://vk.com/dev/messages.send
                                            .execute();
                                }
                            } catch (ApiException | ClientException ignored) {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Произошла ошибка!") // https://vk.com/dev/messages.send
                                        .execute();
                            }
                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                        }
                    } else {
                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/kick)\n" +
                                "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                    }
                }
            }
    }
}
