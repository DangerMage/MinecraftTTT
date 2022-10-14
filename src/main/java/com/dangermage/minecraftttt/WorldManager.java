package com.dangermage.minecraftttt;

import com.grinderwolf.swm.api.SlimePlugin;
import com.grinderwolf.swm.api.exceptions.CorruptedWorldException;
import com.grinderwolf.swm.api.exceptions.NewerFormatException;
import com.grinderwolf.swm.api.exceptions.UnknownWorldException;
import com.grinderwolf.swm.api.exceptions.WorldInUseException;
import com.grinderwolf.swm.api.loaders.SlimeLoader;
import com.grinderwolf.swm.api.world.SlimeWorld;
import com.grinderwolf.swm.api.world.properties.SlimeProperties;
import com.grinderwolf.swm.api.world.properties.SlimePropertyMap;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.IOException;

public class WorldManager {

    private final SlimePlugin slimePlugin;
    private final Plugin plugin;
    private final SlimeLoader slimeLoader;
    private final SlimePropertyMap worldProperties;

    public WorldManager(MinecraftTTT plugin) {
        this.plugin = plugin;
        this.slimePlugin = (SlimePlugin) Bukkit.getPluginManager().getPlugin("SlimeWorldManager");
        assert slimePlugin != null;
        this.slimeLoader = slimePlugin.getLoader("file");

        this.worldProperties = new SlimePropertyMap();

        this.worldProperties.setValue(SlimeProperties.DIFFICULTY, "easy");
        this.worldProperties.setValue(SlimeProperties.ALLOW_ANIMALS, false);
        this.worldProperties.setValue(SlimeProperties.ALLOW_MONSTERS, false);
        this.worldProperties.setValue(SlimeProperties.PVP, true);

    }

    public void loadWorld(String worldName) {
        try {
            SlimeWorld world = slimePlugin.loadWorld(slimeLoader, worldName, true, worldProperties);

            slimePlugin.generateWorld(world);
        } catch (UnknownWorldException | IOException | CorruptedWorldException | NewerFormatException |
                 WorldInUseException exception) {
            plugin.getLogger().warning("Caught Exception: " + exception + "\nSomething went wrong when attempting to call loadWorld");

        }
    }

}
