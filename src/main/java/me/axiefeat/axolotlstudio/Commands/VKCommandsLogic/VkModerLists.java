package me.axiefeat.axolotlstudio.Commands.VKCommandsLogic;

import com.ubivashka.vk.bukkit.BukkitVkApiPlugin;
import com.ubivashka.vk.bukkit.events.VKMessageEvent;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import me.axiefeat.axolotlstudio.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class VkModerLists implements Listener {

    private final static VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getVkApiClient();
    private final static GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getActor();
    private final static Random RANDOM = new Random();

    @EventHandler
    public void onMessage(VKMessageEvent e) throws ApiException, ClientException, IOException {
        String command = "/moder ";
        String vk = String.valueOf(e.getMessage().getFromId());
        String admins = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Admins").toArray());
        String moders = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Moders").toArray());
        String auth = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Chats").toArray());
        String id = String.valueOf(e.getMessage().getPeerId() - 2000000000);
        if (moders.contains(vk) | admins.contains(vk)) {
            if (e.getMessage().getText().startsWith(command)) {
                if (e.getMessage().getText().startsWith(command + "list all")) {
                    if (auth.contains(id)) {
                        if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                            File moderscfg1 = new File(Main.getInstance().getDataFolder(), "moder.yml");
                            FileConfiguration moderscfg = YamlConfiguration.loadConfiguration(moderscfg1);

                            //ArrayList<String> get = new ArrayList<>(moderscfg.getConfigurationSection("Moders").getKeys(false));
                            ArrayList<String> list = new ArrayList<>();
                            for (int integer = 0; integer < moderscfg.getConfigurationSection("Moders").getKeys(false).size();) {
                                String nick = moderscfg.getConfigurationSection("Moders").getKeys(false).stream().toList().get(integer);
                                int punishments = moderscfg.getInt("Moders." + nick + ".Bans") + moderscfg.getInt("Moders." + nick + ".Mutes");
                                list.add("%number%. " + nick + " - " + punishments + " наказаний.");
                                integer ++;
                            }
                            list.sort(new Comparator<String>() {
                                @Override
                                public int compare(String s1s, String s2s) {
                                    String[] part = s1s.split(" - ");
                                    String s1 = part[1];
                                    String[] parts = s2s.split(" - ");
                                    String s2 = parts[1];
                                    String[] arr1 = s1.split("\\D"); // разбиваем строку на массив из чисел и не чисел
                                    String[] arr2 = s2.split("\\D");
                                    int i = 0;
                                    while (i < arr1.length && i < arr2.length) {
                                        if (arr1[i].isEmpty()) { // если текущий элемент массива пустой, значит это не число
                                            i++;
                                            continue;
                                        }
                                        if (arr2[i].isEmpty()) {
                                            i++;
                                            continue;
                                        }
                                        Integer num1 = Integer.parseInt(arr1[i]); // преобразуем текущий элемент массива в число
                                        Integer num2 = Integer.parseInt(arr2[i]);
                                        int cmp = num1.compareTo(num2); // сравниваем числа
                                        if (cmp != 0) {
                                            return cmp; // если числа не равны, возвращаем результат сравнения
                                        }
                                        i++;
                                    }
                                    return arr1.length - arr2.length; // если все числа равны, возвращаем разницу в длине массивов
                                }
                            }.reversed());
                            ArrayList<String> listed = new ArrayList<>();
                            for (int i = 0; i < list.size();) {
                                String replaced = list.get(i).replace("%number%", String.valueOf(i + 1));
                                listed.add(replaced);
                                i++;
                            }
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Блокировки за всё время:\n\n" + listed.stream().collect(Collectors.joining("\n"))).execute();
                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                        }
                    } else {
                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/moder list all)\n" +
                                "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                    }
                }
                if (e.getMessage().getText().startsWith(command + "list year")) {
                    if (auth.contains(id)) {
                        if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {

                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                        }
                    } else {
                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/moder list year)\n" +
                                "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                    }
                }
                if (e.getMessage().getText().startsWith(command + "list month")) {
                    if (auth.contains(id)) {
                        if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {

                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                        }
                    } else {
                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/moder list month)\n" +
                                "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                    }
                }
                if (e.getMessage().getText().startsWith(command + "list week")) {
                    if (auth.contains(id)) {
                        if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {

                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                        }
                    } else {
                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/moder list week)\n" +
                                "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                    }
                }
                if (e.getMessage().getText().startsWith(command + "list day")) {
                    if (auth.contains(id)) {
                        if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {

                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                        }
                    } else {
                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/moder list day)\n" +
                                "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                    }
                }
            }
        }
    }
    private static int getNumber(String s) {
        s.lastIndexOf(" - ");
        String numberString = s.replaceAll("[^0-9]", "");
        return Integer.parseInt(numberString);
    }
}