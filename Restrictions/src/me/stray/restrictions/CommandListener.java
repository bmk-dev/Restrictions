package me.stray.restrictions;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Created by Stray on 9/10/2015.
 */
public class CommandListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        if(!event.isCancelled()) {
            String s = event.getMessage().toLowerCase();
            if(s.contains("ehome") || s.contains("essentials:") || s.contains("bukkit:") || s.contains("minecraft:")) {
                if(!event.getPlayer().isOp()) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(ChatColor.RED + "I'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
                }
            }
        }
    }

}
