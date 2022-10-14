package com.dangermage.minecraftttt;

import com.dangermage.minecraftttt.Commands.GiveGunCommand;
import com.dangermage.minecraftttt.EventListeners.PlayerInteractListener;
import com.dangermage.minecraftttt.Guns.GunCreator;
import com.dangermage.minecraftttt.Guns.GunFunction;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftTTT extends JavaPlugin {

    private final ConfigManager configManager = new ConfigManager();
    private final GunCreator gunCreator = new GunCreator(this);
    private final GunFunction gunFunction = new GunFunction(this);

    @Override
    public void onEnable() {
        // Plugin startup logic
        configManager.createConfigs(this);
        gunCreator.addGuns();

        // Register Events
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);

        // Register Commands
        getCommand("GiveGun").setExecutor(new GiveGunCommand(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public GunCreator getGunCreator() {
        return gunCreator;
    }

    public GunFunction getGunFunction() {
        return gunFunction;
    }

}
