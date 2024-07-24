package org.rammex.thronemodule.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.rammex.thronemodule.ThroneModule;

import java.util.Arrays;

public class PlayerMove implements Listener {
    ThroneModule plugin;
    BukkitRunnable task;
    boolean isTaskRunning = false;

    public PlayerMove(ThroneModule plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location point1 = (Location) plugin.getConfig().get("point1");
        Location point2 = (Location) plugin.getConfig().get("point2");
        Integer money = plugin.getConfig().getInt("moneypersecondes");

        if(isWithin(player.getLocation(), point1, point2)) {
            if(!isTaskRunning) {
                task = new BukkitRunnable() {
                    @Override
                    public void run() {
                        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "eco give %player% %money%".replace("%money%", money.toString()).replace("%player%", event.getPlayer().getName()));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("message")));
                    }
                };

                task.runTaskTimer(plugin, 0L, 60L); // Exécute la tâche toutes les 3 secondes (60 ticks)
                isTaskRunning = true;
            }
        } else {
            if(task != null) {
                task.cancel();
                task = null;
                isTaskRunning = false;
            }
        }
    }

    private boolean isWithin(Location location, Location point1, Location point2) {
        double[] dim = new double[2];

        dim[0] = point1.getX();
        dim[1] = point2.getX();
        Arrays.sort(dim);
        if (location.getX() < dim[0] || location.getX() > dim[1]) return false;

        dim[0] = point1.getY();
        dim[1] = point2.getY();
        Arrays.sort(dim);
        if (location.getY() < dim[0] || location.getY() > dim[1]) return false;

        dim[0] = point1.getZ();
        dim[1] = point2.getZ();
        Arrays.sort(dim);
        if (location.getZ() < dim[0] || location.getZ() > dim[1]) return false;

        return true;
    }
}