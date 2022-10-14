package com.dangermage.minecraftttt.EventListeners;

import com.dangermage.minecraftttt.Guns.GunFunction;
import com.dangermage.minecraftttt.MinecraftTTT;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    private final GunFunction gunFunction;

    public PlayerInteractListener(MinecraftTTT plugin) {
        this.gunFunction = plugin.getGunFunction();
    }

    @EventHandler
    public void interactListener(PlayerInteractEvent event) {
        gunFunction.fireGun(event);
    }
}
