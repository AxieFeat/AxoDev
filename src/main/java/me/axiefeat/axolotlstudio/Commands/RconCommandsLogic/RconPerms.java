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

public class RconPerms implements Listener {

    Main plugin = Main.plugin();
    private final static VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getVkApiClient();
    private final static GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider()
            .getActor();

    private final static Random RANDOM = new Random();

    @EventHandler
    public void onMessage(VKMessageEvent e) throws ApiException, ClientException {
        String command = "/perm user ";
        String vk = String.valueOf(e.getMessage().getFromId());
        String admins = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Admins").toArray());
        String moders = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Moders").toArray());
        String auth = Arrays.toString(Main.getInstance().getConfig().getIntegerList("Chats").toArray());
        String id = String.valueOf(e.getMessage().getPeerId() - 2000000000);
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
                                String type = parts[3];
                                String group = parts[4];
                                String GroupError = ">> Доступные группы:\n"
                                        + "\n" + "• Hr - HR-менеджер (16)\n"
                                        + "• Curator - Куратор (15)\n"
                                        + "• Helper - Хелпер (14)\n"
                                        + "• Moderator - Модератор (13)\n"
                                        + "• Builder - Билдер (12)\n"
                                        + "• Media - Медиа (11)\n"
                                        + "• Sponsor - Спонсор (10)\n"
                                        + "• Nyw - Нув (9)\n"
                                        + "• Boss - Босс (8)\n"
                                        + "• Developer - Девелопер (7)\n"
                                        + "• Bog - Бог (6)\n"
                                        + "• Elite - Элита (5)\n"
                                        + "• Lord - Лорд (4)\n"
                                        + "• Premium - Премимум (3)\n"
                                        + "• Vip - Вип (2)\n"
                                        + "• Default - Игрок (1)\n"
                                        + "\n"
                                        + "*Для выдачи группы можно использовать как полное\n"
                                        + "название (Vip), так и короткое обозначение (2)";
                                if (group.equals("Default") | group.equals("Vip") | group.equals("Premium") | group.equals("Lord") | group.equals("Elite") | group.equals("Bog") | group.equals("Developer") | group.equals("Boss") | group.equals("Nyw") | group.equals("Sponsor") | group.equals("Media") | group.equals("Builder") | group.equals("Moderator") | group.equals("Helper") | group.equals("Curator") | group.equals("Hr") |
                                        group.equals("1") | group.equals("2") | group.equals("3") | group.equals("4") | group.equals("5") | group.equals("6") | group.equals("7") | group.equals("8") | group.equals("9") | group.equals("10") | group.equals("11") | group.equals("12") | group.equals("13") | group.equals("14") | group.equals("15") | group.equals("16")) {
                                    if (type.equals("add")) {
                                        if (group.equals("Default") | group.equals("1")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Default" + " выдана игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") выдал игроку " + nick + " группу Default").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " add " + "Default");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Vip") | group.equals("2")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Vip" + " выдана игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") выдал игроку " + nick + " группу Vip").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " add " + "Vip");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Premium") | group.equals("3")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Premium" + " выдана игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") выдал игроку " + nick + " группу Premium").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " add " + "Premium");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Lord") | group.equals("4")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Lord" + " выдана игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") выдал игроку " + nick + " группу Lord").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " add " + "Lord");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Elite") | group.equals("5")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Elite" + " выдана игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") выдал игроку " + nick + " группу Elite").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " add " + "Elite");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Bog") | group.equals("6")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Bog" + " выдана игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") выдал игроку " + nick + " группу Bog").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " add " + "Bog");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Developer") | group.equals("7")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Developer" + " выдана игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") выдал игроку " + nick + " группу Developer").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " add " + "Developer");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Boss") | group.equals("8")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Boss" + " выдана игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") выдал игроку " + nick + " группу Boss").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " add " + "Boss");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Nyw") | group.equals("9")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Nyw" + " выдана игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") выдал игроку " + nick + " группу Nyw").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " add " + "Nyw");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Sponsor") | group.equals("10")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Sponsor" + " выдана игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") выдал игроку " + nick + " группу Sponsor").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " add " + "Sponsor");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Media") | group.equals("11")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Media" + " выдана игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") выдал игроку " + nick + " группу Media").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " add " + "Media");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Builder") | group.equals("12")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Builder" + " выдана игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") выдал игроку " + nick + " группу Builder").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " add " + "Builder");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Moderator") | group.equals("13")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Moderator" + " выдана игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") выдал игроку " + nick + " группу Moderator").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " add " + "Moderator");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Helper") | group.equals("14")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Helper" + " выдана игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") выдал игроку " + nick + " группу Helper").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " add " + "Helper");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Curator") | group.equals("15")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Curator" + " выдана игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") выдал игроку " + nick + " группу Curator").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " add " + "Curator");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Hr") | group.equals("16")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Hr" + " выдана игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") выдал игроку " + nick + " группу Hr").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " add " + "Hr");
                                                return null;
                                            });
                                        }
                                    }
                                    if (type.equals("set")) {
                                        if (group.equals("Default") | group.equals("1")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Default" + " установлена игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") установил игроку " + nick + " группу Default").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " set " + "Default");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Vip") | group.equals("2")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Vip" + " установлена игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") установил игроку " + nick + " группу Vip").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " set " + "Vip");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Premium") | group.equals("3")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Premium" + " установлена игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") установил игроку " + nick + " группу Premium").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " set " + "Premium");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Lord") | group.equals("4")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Lord" + " установлена игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") установил игроку " + nick + " группу Lord").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " set " + "Lord");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Elite") | group.equals("5")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Elite" + " установлена игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") установил игроку " + nick + " группу Elite").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " set " + "Elite");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Bog") | group.equals("6")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Bog" + " установлена игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") установил игроку " + nick + " группу Bog").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " set " + "Bog");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Developer") | group.equals("7")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Developer" + " установлена игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") установил игроку " + nick + " группу Developer").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " set " + "Developer");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Boss") | group.equals("8")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Boss" + " установлена игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") установил игроку " + nick + " группу Boss").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " set " + "Boss");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Nyw") | group.equals("9")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Nyw" + " установлена игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") установил игроку " + nick + " группу Nyw").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " set " + "Nyw");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Sponsor") | group.equals("10")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Sponsor" + " установлена игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") установил игроку " + nick + " группу Sponsor").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " set " + "Sponsor");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Media") | group.equals("11")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Media" + " установлена игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") установил игроку " + nick + " группу Media").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " set " + "Media");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Builder") | group.equals("12")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Builder" + " установлена игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") установил игроку " + nick + " группу Builder").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " set " + "Builder");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Moderator") | group.equals("13")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Moderator" + " установлена игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") установил игроку " + nick + " группу Moderator").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " set " + "Moderator");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Helper") | group.equals("14")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Helper" + " установлена игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") установил игроку " + nick + " группу Helper").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " set " + "Helper");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Curator") | group.equals("15")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Curator" + " установлена игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") установил игроку " + nick + " группу Curator").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " set " + "Curator");
                                                return null;
                                            });
                                        }
                                        if (group.equals("Hr") | group.equals("16")) {
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Группа "
                                                    + "Hr" + " установлена игроку " + nick).execute();
                                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(2000000000 + Main.getInstance().getConfig().getInt("LogChat")).message("#Перм\n\n" + ">> Игрок Rcon(id" + e.getMessage().getFromId() + ") установил игроку " + nick + " группу Hr").execute();
                                            getServer().getScheduler().callSyncMethod(plugin, () -> {
                                                dispatchCommand(getConsoleSender(), "lp user " + nick + " parent" + " set " + "Hr");
                                                return null;
                                            });
                                        }
                                    }
                                } else {
                                    CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(GroupError).execute();
                                }
                            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException ignored) {
                                CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message("» Использование: /perm user <ник> <add/set> <группа>").execute();
                            }
                        } else {
                            CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> В данном чате запрещены команды!").execute();
                        }
                    } else {
                        CLIENT.messages().send(ACTOR).randomId(RANDOM.nextInt()).peerId(e.getPeer()).message(">> Беседа не авторизована! (/perm)\n" +
                                "CHAT ID: " + (e.getPeer() - 2000000000)).execute();
                    }
                }
            }
    }
}


