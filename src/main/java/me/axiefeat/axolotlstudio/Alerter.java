package me.axiefeat.axolotlstudio;

import com.ubivashka.vk.bukkit.BukkitVkApiPlugin;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.text.DecimalFormat;
import java.util.Random;

import static org.bukkit.Bukkit.*;

public class Alerter implements Listener {

    Main plugin = Main.plugin();

    private final static VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getVkApiClient();
    private final static GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getActor();
    private final static Random RANDOM = new Random();

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent e) throws ClientException, ApiException {
        double tps = ServerManager.getTPS();
        String formattedDouble = new DecimalFormat("#0.0").format(tps);
        int formatted = Integer.parseInt(new DecimalFormat("#0").format(tps));
        if (formatted <= 17 && formatted > 15) {
            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("AlertChat")).message(
                    "#Предупреждения\n\n" + "» Низкий TPS на сервере!\n" +
                    "» Текущее : " + formattedDouble).execute();
        }
        if (formatted <= 15 && formatted > 10) {
            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("AlertChat")).message(
                    "#Предупреждения\n\n" + "» Очень низкий TPS на сервере!\n" +
                            "» Текущее : " + formattedDouble).execute();
            getServer().getScheduler().callSyncMethod(plugin, () -> {
                dispatchCommand(getConsoleSender(), "stoplag");
                return null;
            });
        }
        if (formatted <= 10 && formatted > 5) {
            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("AlertChat")).message(
                    "#Предупреждения\n@id613258536 @id528290750\n" + "» Критически низкий TPS на сервере!\n" +
                            "» Текущее : " + formattedDouble).execute();
        }
        if (formatted <= 5) {
            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("AlertChat")).message(
                    "#Предупреждения\n@all\n" + "» ЕБАНЫЙ РОТ " + formattedDouble + " ТПС БЛЯТЬ!"
                            ).execute();
            Bukkit.shutdown();
        }
    }
}
