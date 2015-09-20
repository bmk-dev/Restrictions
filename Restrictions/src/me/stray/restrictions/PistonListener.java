package me.stray.restrictions;

import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Stray on 4/10/2015.
 */
public class PistonListener implements Listener {

    //Anti-Farm
    @EventHandler
    public void onPistonMoveBlock(BlockPistonExtendEvent event) {
        for(Block block : event.getBlocks()) {
            if(block.getType() == Material.SUGAR_CANE_BLOCK) {
                event.setCancelled(true);
            }
            else if(block.getType() == Material.PUMPKIN) {
                event.setCancelled(true);
            }
            else {

            }
        }
    }

    //Anti flying machine
    @EventHandler
    public void onPistonMoveBlock1(BlockPistonExtendEvent event) {
        for(Block block : event.getBlocks()) {
            if(block.getType().equals(Material.SLIME_BLOCK)) {
                ApplicableRegionSet ar = Restrictions.getWG().getRegionManager(event.getBlock().getWorld()).getApplicableRegions(event.getBlock().getLocation());
                Iterator<ProtectedRegion> prs = ar.iterator();
                while (prs.hasNext()) {
                    ProtectedRegion pr = prs.next();
                    //Requested by SDA so he can use slime blocks in events.
                    if (!pr.getId().toLowerCase().contains("event")) {
                        event.setCancelled(true);
                    }
                    else {
                        event.setCancelled(false);
                        break;
                    }
                }
            }
        }
    }


}
