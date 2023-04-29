package me.axiefeat.axolotlstudio;

import com.earth2me.essentials.utils.DateUtil;
import com.ubivashka.vk.bukkit.BukkitVkApiPlugin;
import com.ubivashka.vk.bukkit.events.VKMessageEvent;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class ServerInfo implements Listener {

    Main plugin = Main.plugin();
    private final static VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getVkApiClient();
    private final static GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getActor();

    private final static Random RANDOM = new Random();
    @EventHandler
    public void onMessage(VKMessageEvent e) throws ApiException, ClientException {
        double tps = ServerManager.getTPS();
        String formattedDouble = new DecimalFormat("#0.0").format(tps);
        String command = "/server";
        String command2 = "/gc";
        String vk = String.valueOf(e.getMessage().getFromId());
        String admins = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Admins").toArray());
        String moders = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Moders").toArray());
        String auth = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Chats").toArray());
        String id = String.valueOf(e.getMessage().getPeerId() - 2000000000);

        if (moders.contains(vk) | admins.contains(vk)) {
            if (e.getMessage().getText().startsWith(command) | e.getMessage().getText().startsWith(command2)) {
                if (auth.contains(id)) {
                    if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                        if (e.getMessage().getText().startsWith(command) | e.getMessage().getText().startsWith(command2)) {

                            String memory = String.valueOf((Runtime.getRuntime().totalMemory() / 1024 / 1024) - (Runtime.getRuntime().freeMemory() / 1024 / 1024));
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Информация о сервере:\n" +
                                    "\n" +
                                    "- Аптайм: " + DateUtil.formatDateDiff(ManagementFactory.getRuntimeMXBean().getStartTime()) + "\n" +
                                    "- Оперативная память: " + memory + "/" + (Runtime.getRuntime().maxMemory() / 1024 / 1024) + " МБ \n" +
                                    "- TPS сервера: " + formattedDouble + "\n" +
                                    "- Онлайн сервера: " + Bukkit.getServer().getOnlinePlayers().size()).execute();
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
