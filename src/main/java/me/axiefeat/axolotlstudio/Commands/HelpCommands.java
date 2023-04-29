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

public class HelpCommands implements Listener {
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
        String managers = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Managers").toArray());
        String auth = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Chats").toArray());
        String id = String.valueOf(e.getMessage().getPeerId() - 2000000000);
            if (moders.contains(vk) | admins.contains(vk)) {
                    if (e.getMessage().getText().equalsIgnoreCase("/shop")) {
                        if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                            if (auth.contains(id)) {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Использование: /shop <rep/money/snow> <ник> <кол-во>") // https://vk.com/dev/messages.send
                                        .execute();
                                return;
                            } else {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/shop)" +
                                        "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                            }
                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                        }
                    }
                    if (e.getMessage().getText().equalsIgnoreCase("/service")) {
                        if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                            if (auth.contains(id)) {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message("/service - команда для работы с сервисами сервера (/s)\n" +
                                                "Аргументы команды:\n" +
                                                "　• usrinfo <ник> - Информация об игроке;\n" +
                                                "　• isblock <ник> - Проверить наличие блокировок игрока;\n" +
                                                "　• reset <reg|stats|regions|homes|all> <ник> - Снести определенную информацию об аккаунте;\n" +
                                                "　• kick <ник> - Кикнуть игрока с сервера;\n" +
                                                "　• notify <ник>  <сообщение> - Отправить уведомление пользователю;\n" +
                                                "　• ban <ник> [время] <причина> - Забанить игрока на сервере;\n" +
                                                "　• unban <ник> - Разбанить игрока на сервере;\n" +
                                                "　• mute <ник> [время] <причина> - Замутить игрока на сервере;\n" +
                                                "　• unmute <ник> - Размутить игрока на сервере." +
                                                "\n" +
                                                "* Обязательные аргументы: <>\n" +
                                                "* Не обязательные аргументы: []")
                                        .execute();
                                return;
                            } else {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/service)\n" +
                                        "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                            }
                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                        }
                    }
                    if (e.getMessage().getText().equalsIgnoreCase("/kick")) {
                        if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                            if (auth.contains(id)) {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Использование: /kick <обращение> [причина]") // https://vk.com/dev/messages.send
                                        .execute();
                                return;
                            } else {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/kick)\n" +
                                        "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                            }
                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                        }
                    }
                    if (e.getMessage().getText().equalsIgnoreCase("/moder")) {
                        if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                            if (auth.contains(id)) {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Использование: /moder <add/del> <ник>\n" + ">> Использование: /moder list <day/week/month/year/all>\n" + ">> Использование: /moder list <add|del> <ник>\n" + ">> Использование: /moder score <add/remove> <ник> <кол-во>") // https://vk.com/dev/messages.send
                                        .execute();
                                return;
                            } else {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/moder)\n" +
                                        "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                            }
                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                        }
                    }
                    if (e.getMessage().getText().equalsIgnoreCase("/perm")) {
                        if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                            if (auth.contains(id)) {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Использование: /perm user <ник> <add/set> <группа>") // https://vk.com/dev/messages.send
                                        .execute();
                                return;
                            } else {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/perm)\n" +
                                        "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                            }
                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                        }
                    }
                    if (e.getMessage().getText().equalsIgnoreCase("/perm user")) {
                        if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                            if (auth.contains(id)) {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Использование: /perm user <ник> <add/set> <группа>") // https://vk.com/dev/messages.send
                                        .execute();
                                return;
                            } else {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/perm)\n" +
                                        "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                            }
                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                        }
                    }
                    if (e.getMessage().getText().equalsIgnoreCase("/perm")) {
                        if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                            if (auth.contains(id)) {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Использование: /perm user <ник> <add/set> <группа>") // https://vk.com/dev/messages.send
                                        .execute();
                                return;
                            } else {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/perm)\n" +
                                        "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                            }
                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                        }
                    }
            }

            if (managers.contains(vk) | admins.contains(vk)) {
                    if (e.getMessage().getText().equalsIgnoreCase("/warn")) {
                        if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                            if (auth.contains(id)) {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Использование: /warn <обращение> <причина>") // https://vk.com/dev/messages.send
                                        .execute();
                                return;
                            } else {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/warn)\n" +
                                        "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                            }
                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                        }
                    }
                    if (e.getMessage().getText().equalsIgnoreCase("/unwarn")) {
                        if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                            if (auth.contains(id)) {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Использование: /unwarn <обращение> [all]") // https://vk.com/dev/messages.send
                                        .execute();
                                return;
                            } else {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/unwarn)\n" +
                                        "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                            }
                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                        }
                    }
                    if (e.getMessage().getText().equalsIgnoreCase("/warns")) {
                        if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                            if (auth.contains(id)) {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Использование: /warns <обращение>") // https://vk.com/dev/messages.send
                                        .execute();
                                return;
                            } else {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/warns)\n" +
                                        "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                            }
                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                        }
                    }
            }
            if (e.getMessage().getText().equalsIgnoreCase("/help")) {
                if (!(e.getPeer() == 2000000000 + Main.getInstance().getConfig().getInt("LogChat"))) {
                    if (auth.contains(id)) {
                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message("» Команды для администраторов чата:\n" +
                                        "• /shop <rep/money/snow> <ник> <кол-во> - вызвать команду по выдаче валют;\n" +
                                        "• /service - команда для работы с сервисами сервера;\n" +
                                        "• /warn <обращение> <причина> - выдать предупреждение;\n" +
                                        "• /unwarn <обращение> [all] - вычитание предупреждений пользователя;\n" +
                                        "• /warns <обращение> - посмотреть варны пользователя;\n" +
                                        "• /kick <обращение> [причина] - кикнуть юзера из чата;\n" +
                                        "• /moder - Команда для управления модерацией;\n" +
                                        "• /perm user <ник> <add/set> <группа> - выдать привилегию;\n" +
                                        "• /filter <add/del> <слово> - система управления цензурой слов в группе вк. (SOON...)\n" +
                                        "\n" +
                                        "» Общедоступные команды:\n" +
                                        "• /ping - проверить работоспособность бота;\n" +
                                        "• /info - вывести информацию(полезные ссылки);\n" +
                                        "• /online - вывести онлайн.\n" +
                                        "\n" +
                                        "* Обязательные аргументы: <>\n" +
                                        "* Не обязательные аргументы: []") // https://vk.com/dev/messages.send
                                .execute();
                    } else {
                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/help)\n" +
                                "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                    }
                } else {
                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                }
            }
    }
}