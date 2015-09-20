package me.stray.restrictions;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Random;

/**
 * Created by Stray on 9/8/2015.
 */
public class EntitySpawnListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntitySpawn(EntitySpawnEvent event) {
        if(!event.isCancelled()) {
            if(event.getEntity().getWorld().getName().contains("world")) {
                if(event.getEntityType() == EntityType.GUARDIAN) {
                    Random rand = new Random();
                    int r = rand.nextInt(100);
                    if(r <= 85) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

}
