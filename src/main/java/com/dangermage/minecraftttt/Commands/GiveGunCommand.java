package com.dangermage.minecraftttt.Commands;

import com.dangermage.minecraftttt.Guns.GunCreator;
import com.dangermage.minecraftttt.MinecraftTTT;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class GiveGunCommand implements CommandExecutor {

    private final GunCreator gunCreator;

    public GiveGunCommand(MinecraftTTT plugin) {
        this.gunCreator = plugin.getGunCreator();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("GiveGun")) {
                HashMap<String, ItemStack> gunItems = gunCreator.getGunItems();

                if (args.length > 0) {


                    if (gunItems.containsKey(args[0])) {
                        ((Player) sender).getInventory().addItem(gunItems.get(args[0]));
                        return true;
                    }
                    else {
                        sender.sendMessage("Gun doesn't exist");
                    }

                }

            }
        }

        return false;
    }
}
