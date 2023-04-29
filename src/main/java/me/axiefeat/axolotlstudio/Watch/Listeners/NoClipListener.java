package me.axiefeat.axolotlstudio.Watch.Listeners;


import me.axiefeat.axolotlstudio.Watch.Commands.WatchCmd;
import java.util.ArrayList;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class NoClipListener implements Listener {
    private static NoClipListener instance;
    public ArrayList<String> noclip = new ArrayList();
    private final BlockFace[] surrounding;

    public NoClipListener() {
        this.surrounding = new BlockFace[]{BlockFace.NORTH, BlockFace.NORTH_EAST, BlockFace.EAST, BlockFace.SOUTH_EAST, BlockFace.SOUTH, BlockFace.SOUTH_WEST, BlockFace.WEST, BlockFace.NORTH_WEST};
        instance = this;
    }

    public static NoClipListener getInstance() {
        return instance;
    }

    @EventHandler
    public void onNearBlock(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if ((player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) && this.noclip.contains(player.getName())) {
            if (this.nearBlock(player)) {
                player.setGameMode(GameMode.SPECTATOR);
            } else {
                player.setGameMode(GameMode.CREATIVE);
            }
        }

    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (this.noclip.contains(player.getName())) {
            this.noclip.remove(player.getName());
            player.setGameMode(GameMode.CREATIVE);
            WatchCmd.getInstance().teleportToSurface(player);
        }

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        if (this.noclip.contains(player.getName())) {
            this.noclip.remove(player.getName());
            player.setGameMode(GameMode.CREATIVE);
        }

    }

    public boolean nearBlock(Player player) {
        boolean nearBlock = false;
        Location[] locs = new Location[]{player.getLocation(), player.getLocation().add(0.0, 1.0, 0.0), player.getLocation().add(0.0, 2.0, 0.0)};
        Location[] var7 = locs;
        int var6 = locs.length;

        for(int var5 = 0; var5 < var6; ++var5) {
            Location loc = var7[var5];
            BlockFace[] var11;
            int var10 = (var11 = this.surrounding).length;

            for(int var9 = 0; var9 < var10; ++var9) {
                BlockFace bf = var11[var9];
                if (!loc.getBlock().getRelative(bf, 1).isEmpty()) {
                    nearBlock = true;
                }
            }
        }

        if (!player.getLocation().add(0.0, 2.0, 0.0).getBlock().isEmpty()) {
            nearBlock = true;
        }

        if (!player.getLocation().subtract(0.0, 1.0, 0.0).getBlock().isEmpty()) {
            nearBlock = true;
        }

        return nearBlock;
    }
}
