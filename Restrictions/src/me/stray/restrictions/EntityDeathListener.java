package me.stray.restrictions;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by Stray on 9/11/2015.
 */
public class EntityDeathListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDeath(EntityDeathEvent event) {
        if(event.getEntity().getType() == EntityType.GUARDIAN) {
            if(event.getDroppedExp() > 0) {
                event.setDroppedExp(event.getDroppedExp() / 2);
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        final Player p = event.getEntity();
        p.getWorld().playSound(p.getLocation(), Sound.IRONGOLEM_HIT, 1.0F, 1F);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Restrictions.plugin, new Runnable() {
            @Override
            public void run() {
                p.spigot().respawn();
            }
        }, 1L);
    }

}
