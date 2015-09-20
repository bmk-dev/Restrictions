package me.stray.restrictions;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Stray on 8/21/2015.
 */
public class BlockBreakListener implements Listener {

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
        if(!event.isCancelled()) {
            if(event.getBlock().getType() == Material.SNOW_BLOCK) {
                if(event.getPlayer().getGameMode() != GameMode.CREATIVE) {
                    event.setCancelled(true);
                    event.getBlock().setType(Material.AIR);
                    event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.SNOW_BLOCK, 1));
                }
            }
        }
    }

}
