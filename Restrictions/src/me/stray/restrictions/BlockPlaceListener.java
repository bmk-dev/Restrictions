package me.stray.restrictions;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import java.util.Iterator;

/**
 * Created by Stray on 7/15/2015.
 */
public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(event.getBlock().getType() == Material.BEACON) {
            if(!event.getBlock().getWorld().getName().contains("freeworld")) {
                if(!event.getPlayer().isOp()) {
                    event.setCancelled(true);
                }
            }
        }
        ApplicableRegionSet ar = Restrictions.getWG().getRegionManager(event.getBlock().getWorld()).getApplicableRegions(event.getBlock().getLocation());
        Iterator<ProtectedRegion> prs = ar.iterator();
        while (prs.hasNext()) {
            ProtectedRegion pr = prs.next();
            if (pr.getId().toLowerCase().contains("quarry")) {
                if(event.getBlock().getTypeId() == 6) {
                    if(!event.getPlayer().isOp()) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage(ChatColor.GRAY + "No placing saplings in the mines!");
                        break;
                    }
                }
            }
        }
    }
}
