package org.rammex.thronemodule;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.rammex.thronemodule.commands.ThroneCommand;
import org.rammex.thronemodule.events.PlayerMove;

public final class ThroneModule extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getLogger().info("ThroneModule enabled by .rammex");
        this.getCommand("throne").setExecutor(new ThroneCommand(this));
        Bukkit.getPluginManager().registerEvents(new PlayerMove(this), this);
        saveDefaultConfig();


    }

    @Override
    public void onDisable() {




    }
}
