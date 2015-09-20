package me.stray.restrictions;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Stray on 8/21/2015.
 */
public class VersionCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String s, String[] strings) {
        if(cmd.getName().equalsIgnoreCase("restrictions")) {
            commandSender.sendMessage(ChatColor.GRAY + "You're running Restrictions v" + Restrictions.version);
        }
        return true;
    }
}
