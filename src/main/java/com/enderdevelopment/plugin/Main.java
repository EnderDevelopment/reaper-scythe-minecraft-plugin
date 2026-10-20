package com.enderdevelopment.plugin;

import com.enderdevelopment.plugin.abilities.ReaperTraction;
import com.enderdevelopment.plugin.abilities.ShadowBurst;
import com.enderdevelopment.plugin.items.ReaperScythe;
import com.enderdevelopment.plugin.lifesteal.LifeSteal;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.isOp()) {
            player.getInventory().addItem(ReaperScythe.createScythe());
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            Player killer = event.getEntity().getKiller();
            ItemStack itemInHand = killer.getInventory().getItemInMainHand();

            if (itemInHand.getItemMeta() != null && itemInHand.getItemMeta().getDisplayName().equals("§6Faux du Reaper")) {
                ReaperScythe.increaseKillCount(itemInHand);
                int killCount = ReaperScythe.getKillCount(itemInHand);
                if (killCount >= 10 && event.getEntity() instanceof Player) {
                    LifeSteal.activate(killer, (Player) event.getEntity());
                } else if (killCount >= 2 && event.getAction().toString().contains("RIGHT")) {
                    ShadowBurst.activate(player);
                } else if (killCount >= 4 && event.getAction().toString().contains("RIGHT")) {
                    ReaperTraction.activate(player);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        if (itemInHand.getItemMeta() != null && itemInHand.getItemMeta().getDisplayName().equals("§6Faux du Reaper")) {
            int killCount = ReaperScythe.getKillCount(itemInHand);

            if (killCount >= 2 && event.getAction().toString().contains("RIGHT")) {
                ShadowBurst.activate(player);
            } else if (killCount >= 4 && event.getAction().toString().contains("RIGHT")) {
                ReaperTraction.activate(player);
            }
        }
    }
}