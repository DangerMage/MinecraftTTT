package com.dangermage.minecraftttt.Guns;

import com.dangermage.minecraftttt.ConfigManager;
import com.dangermage.minecraftttt.HelperMethods.ItemBuilder;
import com.dangermage.minecraftttt.MinecraftTTT;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;


public class GunCreator {

    private final MinecraftTTT plugin;
    private final ConfigManager configManager;
    private final HashMap<String, GunObject> gunData;
    private final HashMap<String, ItemStack> gunItems;
    private final NamespacedKey gunKey;

    public GunCreator(MinecraftTTT plugin) {
        this.plugin = plugin;
        this.configManager = plugin.getConfigManager();
        this.gunData = new HashMap<>();
        this.gunItems = new HashMap<>();
        this.gunKey = new NamespacedKey(plugin, "TTTGun");

    }

    public void addGuns() {
        gunData.clear();
        gunItems.clear();
        FileConfiguration gunConfig = configManager.getGunsConfig();
        ConfigurationSection gunConfigSection = gunConfig.getConfigurationSection("guns");

        if (gunConfigSection == null) {
            gunConfig.set("guns.debug.fireRate", 1);
            gunConfig.set("guns.debug.damage", 1);
            gunConfig.set("guns.debug.ammoCapacity", 1);
            gunConfig.set("guns.debug.texture", 1);
            gunConfig.set("guns.debug.reloadTime", 1);
            gunConfig.set("guns.debug.fireType", "straight");
            gunConfig.set("guns.debug.bulletsPerShot", 1);
            gunConfig.set("guns.debug.range", 1);

        }

        if (gunConfigSection != null) {
            for (String entry : gunConfigSection.getKeys(false)) {
                double fireRate = gunConfig.getDouble("guns." + entry + ".fireRate", .1);
                int damage = gunConfig.getInt("guns." + entry + ".damage", 1);
                int ammoCapacity = gunConfig.getInt("guns." + entry + ".ammoCapacity", 1);
                int texture = gunConfig.getInt("guns." + entry + ".texture", 0);
                double reloadTime = gunConfig.getDouble("guns." + entry + ".reloadTime", 1);
                String fireType = gunConfig.getString("guns." + entry + ".fireType", "straight");
                int bsp = gunConfig.getInt("guns." + entry + ".bulletsPerShot", 1);
                int range = gunConfig.getInt("guns." + entry + ".range", 1);
                String name = gunConfig.getString("guns." + entry + ".name", "default");

                if (!fireType.equalsIgnoreCase("straight") && !fireType.equalsIgnoreCase("spread")) {
                    fireType = "straight";
                }

                GunObject gunObject = new GunObject(fireRate, damage, ammoCapacity, reloadTime, fireType, bsp, range);
                ItemStack gunItem = new ItemBuilder(Material.SHEARS).setAmount(1).setUnbreakable().setTexture(texture).setName(name).setStringPDC(gunKey, entry).buildItem();

                gunData.put(entry, gunObject);
                gunItems.put(entry, gunItem);

            }
        }

        plugin.getLogger().info("gunData contains: " + gunData);
        plugin.getLogger().info("gunItems contains: " + gunItems);


    }

    public HashMap<String, GunObject> getGunData() {
        return gunData;
    }

    public HashMap<String, ItemStack> getGunItems() {
        return gunItems;
    }

    public NamespacedKey getGunKey() {
        return gunKey;
    }
}
