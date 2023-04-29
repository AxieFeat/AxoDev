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

import java.util.Arrays;
import java.util.Random;

import static org.bukkit.Bukkit.*;

public class RconMute implements Listener {

    Main plugin = Main.plugin();
    private final static VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getVkApiClient();
    private final static GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getActor();

    private final static Random RANDOM = new Random();
    @EventHandler
    public void onMessage(VKMessageEvent e) throws ApiException, ClientException {
        String command = "/s mute ";
        String commandfull = "/service mute ";
        String vk = String.valueOf(e.getMessage().getFromId());
        String admins = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Admins").toArray());
        String moders = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Moders").toArray());
        String auth = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Chats").toArray());
        String id = String.valueOf(e.getMessage().getPeerId() - 2000000000);

        if (e.getMessage().getText().equals("/s mute") && moders.contains(vk) | admins.contains(vk) && !(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Использование: /s mute <ник> [время] <причина>").execute();
        }
        if (e.getMessage().getText().equals("/service mute") && moders.contains(vk) | admins.contains(vk) && !(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Использование: /service mute <ник> [время] <причина>").execute();
        }

            if (moders.contains(vk) | admins.contains(vk)) {
                if (e.getMessage().getText().startsWith(command)) {
                    if (auth.contains(id)) {
                        if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                            try {
//                            if (!(e.getMessage().getText().startsWith(command)))
//                                return;
//                            if (e.getMessage().getText().length() < command.length())
//                                return;
                                String Nickname = e.getMessage().getText().toString();
                                String[] parts = Nickname.split(" ");
                                String nick = parts[2];
                                String time = parts[3];
                                String num = time.replaceAll("[^0-9]", "");
                                String totime = time.replaceAll("[^a-z]", "");
                                String check = time.replaceAll("[^а-яА-Я]", "");

                                String TimeError = ">> Доступные значения времени:\n"
                                        + "\n" + "• Секунды - s\n"
                                        + "• Минуты - m\n"
                                        + "• Часы - h\n"
                                        + "• Дни - d\n"
                                        + "• Месяцы - mo\n";


                                if (time.contains(num) & totime.equals("d") | totime.equals("h") | (totime.equals("m") | (totime.equals("s") | (totime.equals("mo")))) && parts[4] != null) {
                                    if (totime.equals("d")) {
                                        String reason = e.getMessage().getText().substring(command.length() + nick.length() + time.length() + 2);
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Игрок '" + nick + "' был замучен на " + num + " дней по причине: '" + reason + "'!").execute();
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Муты\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") замутил игрока " + nick + " на " + num + " дней по причине: '" + reason + "'!").execute();
                                        getServer().getScheduler().callSyncMethod(plugin, () -> {
                                            dispatchCommand(getConsoleSender(), "tempmute " + nick + " " + time + " " + reason);
                                            return null;
                                        });
                                    }
                                    if (totime.equals("h")) {
                                        String reason = e.getMessage().getText().substring(command.length() + nick.length() + time.length() + 2);
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Игрок '" + nick + "' был замучен на " + num + " часов по причине: '" + reason + "'!").execute();
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Муты\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") замутил игрока " + nick + " на " + num + " часов по причине: '" + reason + "'!").execute();
                                        getServer().getScheduler().callSyncMethod(plugin, () -> {
                                            dispatchCommand(getConsoleSender(), "tempmute " + nick + " " + time + " " + reason);
                                            return null;
                                        });
                                    }
                                    if (totime.equals("m")) {
                                        String reason = e.getMessage().getText().substring(command.length() + nick.length() + time.length() + 2);
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Игрок '" + nick + "' был замучен на " + num + " минут по причине: '" + reason + "'!").execute();
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Муты\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") замутил игрока " + nick + " на " + num + " минут по причине: '" + reason + "'!").execute();
                                        getServer().getScheduler().callSyncMethod(plugin, () -> {
                                            dispatchCommand(getConsoleSender(), "tempmute " + nick + " " + time + " " + reason);
                                            return null;
                                        });
                                    }
                                    if (totime.equals("s")) {
                                        String reason = e.getMessage().getText().substring(command.length() + nick.length() + time.length() + 2);
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Игрок '" + nick + "' был замучен на " + num + " секунд по причине: '" + reason + "'!").execute();
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Муты\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") замутил игрока " + nick + " на " + num + " секунд по причине: '" + reason + "'!").execute();
                                        getServer().getScheduler().callSyncMethod(plugin, () -> {
                                            dispatchCommand(getConsoleSender(), "tempmute " + nick + " " + time + " " + reason);
                                            return null;
                                        });
                                    }
                                    if (totime.equals("mo")) {
                                        String reason = e.getMessage().getText().substring(command.length() + nick.length() + time.length() + 2);
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Игрок '" + nick + "' был замучен на " + num + " месяцев по причине: '" + reason + "'!").execute();
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Муты\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") замутил игрока " + nick + " на " + num + " месяцев по причине: '" + reason + "'!").execute();
                                        getServer().getScheduler().callSyncMethod(plugin, () -> {
                                            dispatchCommand(getConsoleSender(), "tempmute " + nick + " " + time + " " + reason);
                                            return null;
                                        });
                                    }
                                } else {
                                    if (!(time.contains("s")) | !(time.contains("m")) | !(time.contains("h")) | !(time.contains("d")) | !(time.contains("mo"))) {
                                        if (time.length() == (num.length() + 2) | time.length() == (num.length() + 1) && (!time.contains(check))) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).
                                                    peerId(e.getPeer()).message(TimeError).execute();
                                        } else {
                                            String Nickname2 = e.getMessage().getText().toString();
                                            String[] parts2 = Nickname2.split(" ");
                                            String Nickname1 = parts2[2];
                                            String reason = e.getMessage().getText().substring(command.length() + Nickname1.length() + 1);
                                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Игрок '" + Nickname1 + "' был замучен навсегда по причине: '" + reason + "'!").execute();
                                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Муты\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") замутил игрока " + Nickname1 + " навсегда по причине: '" + reason + "'!").execute();
                                                getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                    dispatchCommand(getConsoleSender(), "mute " + Nickname1 + " " + reason);
                                                    return null;
                                                });
                                        }
                                    }
                                }

                            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException ignored) {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Использование: /s mute <ник> [время] <причина>").execute();
                            }
                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                        }
                    } else {
                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/s mute)\n" +
                                "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                    }
                }
                if (e.getMessage().getText().startsWith(commandfull)) {
                    if (auth.contains(id)) {
                        if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                            try {
//                            if (!(e.getMessage().getText().startsWith(command)))
//                                return;
//                            if (e.getMessage().getText().length() < command.length())
//                                return;
                                String Nickname = e.getMessage().getText().toString();
                                String[] parts = Nickname.split(" ");
                                String nick = parts[2];
                                String time = parts[3];
                                String num = time.replaceAll("[^0-9]", "");
                                String totime = time.replaceAll("[^a-z]", "");
                                String check = time.replaceAll("[^а-яА-Я]", "");

                                String TimeError = ">> Доступные значения времени:\n"
                                        + "\n" + "• Секунды - s\n"
                                        + "• Минуты - m\n"
                                        + "• Часы - h\n"
                                        + "• Дни - d\n"
                                        + "• Месяцы - mo\n";


                                if (time.contains(num) & totime.equals("d") | totime.equals("h") | (totime.equals("m") | (totime.equals("s") | (totime.equals("mo")))) && parts[4] != null) {
                                    if (totime.equals("d")) {
                                        String reason = e.getMessage().getText().substring(commandfull.length() + nick.length() + time.length() + 2);
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Игрок '" + nick + "' был замучен на " + num + " дней по причине: '" + reason + "'!").execute();
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Муты\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") замутил игрока " + nick + " на " + num + " дней по причине: '" + reason + "'!").execute();
                                        getServer().getScheduler().callSyncMethod(plugin, () -> {
                                            dispatchCommand(getConsoleSender(), "tempmute " + nick + " " + time + " " + reason);
                                            return null;
                                        });
                                    }
                                    if (totime.equals("h")) {
                                        String reason = e.getMessage().getText().substring(commandfull.length() + nick.length() + time.length() + 2);
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Игрок '" + nick + "' был замучен на " + num + " часов по причине: '" + reason + "'!").execute();
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Муты\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") замутил игрока " + nick + " на " + num + " часов по причине: '" + reason + "'!").execute();
                                        getServer().getScheduler().callSyncMethod(plugin, () -> {
                                            dispatchCommand(getConsoleSender(), "tempmute " + nick + " " + time + " " + reason);
                                            return null;
                                        });
                                    }
                                    if (totime.equals("m")) {
                                        String reason = e.getMessage().getText().substring(commandfull.length() + nick.length() + time.length() + 2);
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Игрок '" + nick + "' был замучен на " + num + " минут по причине: '" + reason + "'!").execute();
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Муты\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") замутил игрока " + nick + " на " + num + " минут по причине: '" + reason + "'!").execute();
                                        getServer().getScheduler().callSyncMethod(plugin, () -> {
                                            dispatchCommand(getConsoleSender(), "tempmute " + nick + " " + time + " " + reason);
                                            return null;
                                        });
                                    }
                                    if (totime.equals("s")) {
                                        String reason = e.getMessage().getText().substring(commandfull.length() + nick.length() + time.length() + 2);
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Игрок '" + nick + "' был замучен на " + num + " секунд по причине: '" + reason + "'!").execute();
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Муты\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") замутил игрока " + nick + " на " + num + " секунд по причине: '" + reason + "'!").execute();
                                        getServer().getScheduler().callSyncMethod(plugin, () -> {
                                            dispatchCommand(getConsoleSender(), "tempmute " + nick + " " + time + " " + reason);
                                            return null;
                                        });
                                    }
                                    if (totime.equals("mo")) {
                                        String reason = e.getMessage().getText().substring(commandfull.length() + nick.length() + time.length() + 2);
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Игрок '" + nick + "' был замучен на " + num + " месяцев по причине: '" + reason + "'!").execute();
                                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Муты\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") замутил игрока " + nick + " на " + num + " месяцев по причине: '" + reason + "'!").execute();
                                        getServer().getScheduler().callSyncMethod(plugin, () -> {
                                            dispatchCommand(getConsoleSender(), "tempmute " + nick + " " + time + " " + reason);
                                            return null;
                                        });
                                    }
                                } else {
                                    if (!(time.contains("s")) | !(time.contains("m")) | !(time.contains("h")) | !(time.contains("d")) | !(time.contains("mo"))) {
                                        if (time.length() == (num.length() + 2) | time.length() == (num.length() + 1) && (!time.contains(check))) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).
                                                    peerId(e.getPeer()).message(TimeError).execute();
                                        } else {
                                            String Nickname2 = e.getMessage().getText().toString();
                                            String[] parts2 = Nickname2.split(" ");
                                            String Nickname1 = parts2[2];
                                            String reason = e.getMessage().getText().substring(commandfull.length() + Nickname1.length() + 1);
                                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Игрок '" + Nickname1 + "' был замучен навсегда по причине: '" + reason + "'!").execute();
                                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Муты\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") замутил игрока " + Nickname1 + " навсегда по причине: '" + reason + "'!").execute();
                                                getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                    dispatchCommand(getConsoleSender(), "mute " + Nickname1 + " " + reason);
                                                    return null;
                                                });
                                        }
                                    }
                                }

                            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException ignored) {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Использование: /s mute <ник> [время] <причина>").execute();
                            }
                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                        }
                    } else {
                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/s mute)\n" +
                                "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                    }
                }
            }
    }
}

