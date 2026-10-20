package com.enderdevelopment.plugin.lifesteal;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class LifeSteal {
    private static final int MAX_HEALTH_LIMIT = 40;

    public static void activate(Player killer, Player victim) {
        double currentMaxHealth = killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        if (currentMaxHealth < MAX_HEALTH_LIMIT) {
            killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(currentMaxHealth + 2);
            killer.setHealth(killer.getHealth() + 2);
        }
    }
}