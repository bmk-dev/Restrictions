package me.stray.restrictions;

import com.sk89q.worldguard.internal.flywaydb.core.internal.metadatatable.MetaDataTable;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.metadata.LazyMetadataValue;
import org.bukkit.metadata.MetadataValue;

/**
 * Created by Stray on 8/15/2015.
 */
public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if(!event.getPlayer().hasPermission("restrictions.caps.exempt") && event.getMessage().length() > 4) {
            int caps = 0;
            for(int i = 0; i < event.getMessage().length(); i++) {
                if(Character.isUpperCase(event.getMessage().charAt(i))) {
                    caps++;
                }
            }

            double percentage = caps * 10.0 / event.getMessage().length();
            if(percentage > 5.0) {
                event.setMessage(event.getMessage().toLowerCase());
            }
        }
    }
}
