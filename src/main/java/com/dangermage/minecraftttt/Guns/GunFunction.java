package com.dangermage.minecraftttt.Guns;

import com.dangermage.minecraftttt.MinecraftTTT;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.function.Predicate;

public class GunFunction {

    private final GunCreator gunCreator;

    public GunFunction(MinecraftTTT plugin) {
        this.gunCreator = plugin.getGunCreator();
    }

    public void makeBeam(Player player) {
        World world = player.getWorld();
        Location beamStart = player.getLocation();
        Vector beamDirection = beamStart.getDirection();

        for (int i = 0; i <= 100; i++) {
            beamStart.add(beamDirection);
            beamStart.add(0,1.5,0);
            world.spawnParticle(Particle.CRIT, beamStart, 1, 0,0,0,0.1);
            beamStart.subtract(0,1.5,0);
        }
    }

    public void fireGun(PlayerInteractEvent event) {
        if (event.getAction().isRightClick()) {
            Player player = event.getPlayer();
            ItemStack usedItem = event.getItem();

            if (usedItem == null) {
                return;
            }

            if (usedItem.hasItemMeta()) {
                ItemMeta itemMeta = usedItem.getItemMeta();
                if (itemMeta.getPersistentDataContainer().has(gunCreator.getGunKey())) {
                    String gun = itemMeta.getPersistentDataContainer().get(gunCreator.getGunKey(), PersistentDataType.STRING);
                    player.sendMessage("Gun used: " + gun);

                    HashMap<String, GunObject> gunData = gunCreator.getGunData();

                    GunObject gunObject = gunData.get(gun);




                    World world = player.getWorld();

                    makeBeam(player);

                    RayTraceResult blockResult = world.rayTraceBlocks(player.getEyeLocation(), player.getEyeLocation().getDirection(), 100);

                    RayTraceResult result;

                    if (blockResult == null) {
                        player.sendMessage("Block trace didn't hit anything");
                        result = world.rayTraceEntities(player.getEyeLocation(), player.getEyeLocation().getDirection(), 100, 0, Predicate.not(Predicate.isEqual(player)));
                    } else {
                        Block block = blockResult.getHitBlock();
                        result = world.rayTraceEntities(player.getEyeLocation(), player.getEyeLocation().getDirection(), player.getLocation().distance(block.getLocation()), 0, Predicate.not(Predicate.isEqual(player)));
                        player.sendMessage("Block hit with ray trace: " + block);
                    }


                    if (result == null) {
                        return;
                    }


                    Entity entity = result.getHitEntity();


                    player.sendMessage("Entity hit with ray trace: " + entity);

                    if (entity instanceof LivingEntity livingEntity) {

                        livingEntity.damage(gunObject.damage());
                        livingEntity.setNoDamageTicks(0);
                    }


                }
            }

        }
    }
}
