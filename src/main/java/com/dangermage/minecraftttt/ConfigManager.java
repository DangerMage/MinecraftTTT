package com.dangermage.minecraftttt;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigManager {

    private FileConfiguration gunsConfig;
    private FileConfiguration mainConfig;


    public void createConfigs(MinecraftTTT plugin) {

        //----------------------------------------------------------------------
        File gunsConfigFile = new File(plugin.getDataFolder(), "guns.yml");
        if (!gunsConfigFile.exists()) {
            plugin.saveResource("guns.yml", false);
        }
        gunsConfig = YamlConfiguration.loadConfiguration(gunsConfigFile);
        //----------------------------------------------------------------------
        File mainConfigFile = new File(plugin.getDataFolder(), "config.yml");
        if (!mainConfigFile.exists()) {
            plugin.saveResource("config.yml", false);
        }
        mainConfig = YamlConfiguration.loadConfiguration(mainConfigFile);
        //----------------------------------------------------------------------

    }

    public FileConfiguration getGunsConfig() {
        return gunsConfig;
    }


    public FileConfiguration getMainConfig() {
        return mainConfig;
    }
}
