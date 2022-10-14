package com.dangermage.minecraftttt.HelperMethods;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class ItemBuilder {

    public ItemStack item;

    public ItemBuilder(Material material) {
        item = new ItemStack(material);
    }

    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder setName(String name) {
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(name));
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setTexture(int texture) {
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(texture);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setUnbreakable() {
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(List<Component> lore) {
        ItemMeta meta = item.getItemMeta();
        meta.lore(lore);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setStringPDC(NamespacedKey key, String value) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, value);
        item.setItemMeta(meta);
        return this;
    }

    public ItemStack buildItem() {
        return item;
    }
}
