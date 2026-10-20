package com.enderdevelopment.plugin.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ReaperScythe {
    private static final NamespacedKey KILL_COUNT_KEY = new NamespacedKey("com.enderdevelopment.plugin", "kill_count");

    public static ItemStack createScythe() {
        ItemStack scythe = new ItemStack(Material.NETHERITE_HOE);
        ItemMeta meta = scythe.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§6Faux du Reaper");
            List<String> lore = new ArrayList<>();
            lore.add("§7Une faux évolutive avec enchantements");
            meta.setLore(lore);
            meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
            meta.getPersistentDataContainer().set(KILL_COUNT_KEY, PersistentDataType.INTEGER, 0);
            scythe.setItemMeta(meta);
        }

        return scythe;
    }

    public static void increaseKillCount(ItemStack scythe) {
        ItemMeta meta = scythe.getItemMeta();
        if (meta != null) {
            int killCount = meta.getPersistentDataContainer().getOrDefault(KILL_COUNT_KEY, PersistentDataType.INTEGER, 0);
            killCount++;
            meta.getPersistentDataContainer().set(KILL_COUNT_KEY, PersistentDataType.INTEGER, killCount);
            meta.removeEnchant(Enchantment.DAMAGE_ALL);
            meta.addEnchant(Enchantment.DAMAGE_ALL, killCount + 1, true);
            scythe.setItemMeta(meta);
        }
    }

    public static int getKillCount(ItemStack scythe) {
        ItemMeta meta = scythe.getItemMeta();
        if (meta != null) {
            return meta.getPersistentDataContainer().getOrDefault(KILL_COUNT_KEY, PersistentDataType.INTEGER, 0);
        }
        return 0;
    }
}