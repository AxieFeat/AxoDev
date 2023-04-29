package me.axiefeat.axolotlstudio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;
import org.bukkit.entity.Player;

public class MySQL {
    public MySQL() {
    }

    public static Connection openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException var2) {
            System.err.println(var2);
            var2.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + Main.getInstance().getConfig().getString("database.host") + ":" + Main.getInstance().getConfig().getInt("database.port") + "/" + Main.getInstance().getConfig().getString("database.database-name") + "?autoReconnect=true", Main.getInstance().getConfig().getString("database.username"), Main.getInstance().getConfig().getString("database.password"));
            System.out.println("Успешное подключение к базе данных!");
            return conn;
        } catch (SQLException var1) {
            System.out.println("Не удалось подключится к базе данных!");
            System.err.println(var1);
            var1.printStackTrace();
            return null;
        }
    }

    public static void createTable() {
        Statement state;
        if (!Main.getInstance().getConfig().getBoolean("database.setup")) {
            Main.getInstance().getConfig().set("database.setup", true);
            Main.getInstance().saveConfig();

            try {
                state = Main.conn.createStatement();
                state.executeUpdate("CREATE TABLE IF NOT EXISTS `reports` (`id` int(11) NOT NULL,`reason` text NOT NULL,`from` text NOT NULL,`who` text NOT NULL,`done` int(11) NOT NULL DEFAULT '0',`time` text NOT NULL)");
            } catch (SQLException var3) {
                System.out.println("Не удалось создать основную базу данных!");
                System.err.println(var3);
                var3.printStackTrace();
            }

            try {
                state = Main.conn.createStatement();
                state.executeUpdate("CREATE TABLE IF NOT EXISTS `counter` (`counter` int(11) NOT NULL DEFAULT '0')");
                state.executeUpdate("ALTER TABLE `counter` ADD UNIQUE(`counter`)");
                state.executeUpdate("INSERT INTO `counter`(`counter`) VALUES (0)");
            } catch (SQLException var2) {
                System.out.println("Не удалось создать побочную базу данных!");
                System.err.println(var2);
                var2.printStackTrace();
            }
        }

        try {
            state = Main.conn.createStatement();
            state.executeUpdate("CREATE TABLE IF NOT EXISTS `uuids` (`name` text NOT NULL, `UUID` text NOT NULL)");
        } catch (SQLException var1) {
            System.out.println("Report this error: Table_create_2");
            System.err.println(var1);
            var1.printStackTrace();
        }

    }

    public static void Update(String qry) {
        try {
            Statement stmt = Main.conn.createStatement();
            stmt.executeUpdate(qry);
            stmt.close();
        } catch (Exception var2) {
            System.err.println(var2);
        }

    }

    public static Connection getConnection() {
        return Main.conn;
    }

    public static ResultSet Query(String qry) {
        ResultSet rs = null;

        try {
            Statement stmt = Main.conn.createStatement();
            rs = stmt.executeQuery(qry);
        } catch (Exception var3) {
            System.err.println(var3);
        }

        return rs;
    }

    public static void create(String grund, String wer, String von, String time) {
        Update("INSERT INTO `reports`(`id`, `reason`, `from`, `who`, `time`) VALUES (" + getReportCount() + ",'" + grund + "','" + von + "','" + wer + "','" + time + "')");
        int i = getReportCount() + 1;
        Update("DELETE FROM `counter`");
        Update("INSERT INTO `counter`(`counter`) VALUES (" + i + ")");
    }

    public static void remove(int id) {
        Update("UPDATE `reports` SET `done`=1 WHERE `id` = " + id);
    }

    public static String getInfo(int id) {
        String c = null;

        String reason;
        String who;
        String from;
        String time;
        int done;
        try {
            for(ResultSet rs = Query("SELECT * FROM `reports` WHERE `id` = " + id); rs.next(); c = reason + ", " + who + ", " + from + ", " + done + ", " + time) {
                reason = rs.getString("reason");
                who = rs.getString("who");
                from = rs.getString("from");
                time = rs.getString("time");
                done = rs.getInt("done");
            }
        } catch (Exception var8) {
            System.err.println(var8);
            var8.printStackTrace();
        }

        return c;
    }

    public static int getReportCount() {
        int c = 0;

        int id;
        try {
            for(ResultSet rs = Query("SELECT `counter` FROM `counter`"); rs.next(); c = id) {
                id = rs.getInt("counter");
            }
        } catch (Exception var3) {
            System.err.println(var3);
            var3.printStackTrace();
        }

        return c;
    }

    public static ArrayList<UUID> getUUIDs() {
        ArrayList<UUID> uuids = new ArrayList();
        ResultSet rs = Query("SELECT `UUID` FROM `uuids`");

        try {
            while(rs.next()) {
                String temp = rs.getString("UUID");
                uuids.add(UUID.fromString(temp));
            }
        } catch (SQLException var3) {
        }

        return uuids;
    }

    public static boolean addUUID(Player p) {
        try {
            Update("INSERT INTO `uuids`(`name`, `UUID`) VALUES ('" + p.getName() + "','" + p.getUniqueId() + "')");
            return true;
        } catch (Exception var2) {
            System.out.println("Игрок должен быть в сети!");
            return false;
        }
    }

    public static void delUUID(Player p) {
        Update("DELETE FROM `uuids` WHERE `name` = '" + p.getName() + "'");
    }
}

