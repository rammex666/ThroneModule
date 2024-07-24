package org.rammex.thronemodule.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.rammex.thronemodule.ThroneModule;

public class ThroneCommand implements CommandExecutor {

    ThroneModule plugin;
    public ThroneCommand(ThroneModule plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;

        if(player.hasPermission("throne.set")){
            if(strings.length == 2){
                if(strings[0].equalsIgnoreCase("set")){
                    if(strings[1].equalsIgnoreCase("1")){
                        plugin.getConfig().set("point1", player.getLocation());
                        player.sendMessage("Point 1 set!");
                        plugin.saveConfig();
                        return true;
                    } else if(strings[1].equalsIgnoreCase("2")){
                        plugin.getConfig().set("point2", player.getLocation());
                        player.sendMessage("Point 2 set!");
                        plugin.saveConfig();
                        return true;
                    }
                }
            }
        } else{
            player.sendMessage("§b[ThroneModule] 1.0 by .rammex");
        }

        return false;
    }
}
