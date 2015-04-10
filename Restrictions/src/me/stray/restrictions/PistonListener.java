package me.stray.restrictions;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;

import java.util.List;

/**
 * Created by Stray on 4/10/2015.
 */
public class PistonListener implements Listener {

    @EventHandler
    public void onPistonMoveBlock(BlockPistonExtendEvent event) {

        for(Block block : event.getBlocks()) {
            if(block.getType() == Material.SUGAR_CANE_BLOCK) {
                String string = event.getBlock().getWorld().getName() + ", " + event.getBlock().getX() + ", " + event.getBlock().getY() + ", " + event.getBlock().getZ();
                event.setCancelled(true);
                Restrictions.plugin.getServer().getLogger().info("Prevented piston from breaking SUGAR_CANES at " + string);
            }
            else if(block.getType() == Material.PUMPKIN) {
                String string = event.getBlock().getWorld().getName() + ", " + event.getBlock().getX() + ", " + event.getBlock().getY() + ", " + event.getBlock().getZ();
                event.setCancelled(true);
                Restrictions.plugin.getServer().getLogger().info("Prevented piston from breaking PUMPKIN at " + string);
            }
            else if(block.getType() == Material.MELON_BLOCK) {
                String string = event.getBlock().getWorld().getName() + ", " + event.getBlock().getX() + ", " + event.getBlock().getY() + ", " + event.getBlock().getZ();
                event.setCancelled(true);
                Restrictions.plugin.getServer().getLogger().info("Prevented piston from breaking MELON_BLOCK at " + string);
            }
            else {

            }
        }
    }


}
