package me.stray.restrictions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

/**
 * Created by Stray on 9/20/2015.
 */
public class TabCompleteListener implements Listener {

    @EventHandler
    public void onPlayerTab(PlayerChatTabCompleteEvent event) {
        if(!event.getPlayer().isOp()) {
            event.getTabCompletions().clear();
        }
    }

}
