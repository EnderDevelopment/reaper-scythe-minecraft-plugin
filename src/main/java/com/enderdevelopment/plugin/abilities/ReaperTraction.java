package com.enderdevelopment.plugin.abilities;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;

public class ReaperTraction {
    public static void activate(Player player) {
        Location location = player.getLocation();
        List<Entity> nearbyEntities = player.getNearbyEntities(5, 5, 5);

        for (Entity entity : nearbyEntities) {
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                Vector direction = location.toVector().subtract(livingEntity.getLocation().toVector()).normalize().multiply(1.5);
                livingEntity.setVelocity(direction);
            }
        }

        location.getWorld().spawnParticle(Particle.SMOKE_NORMAL, location, 100, 1, 1, 1, 0.1);
    }
}