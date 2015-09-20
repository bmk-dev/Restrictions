package me.stray.restrictions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import java.util.HashMap;

/**
 * Created by Stray on 4/9/2015.
 */
public class Restrictions extends JavaPlugin implements Listener {

    public static HashMap<Player, ScreenPosition> positions = new HashMap<Player, ScreenPosition>();
    public static String version = "1.6.3";
    public static Plugin plugin;
    private static WorldGuardPlugin wg;

    @Override
    public void onEnable() {
        plugin = this;
        registerEvents(this, new PotionListener());
        registerEvents(this, new PistonListener());
        registerEvents(this, new BlockPlaceListener());
        registerEvents(this, new ChatListener());
        registerEvents(this, new BlockBreakListener());
        registerEvents(this, new EntitySpawnListener());
        registerEvents(this, new CommandListener());
        registerEvents(this, this);
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("restrictions").setExecutor(new VersionCommand());
        wg = (WorldGuardPlugin) getServer().getPluginManager().getPlugin("WorldGuard");
        doTick();
    }

    private WorldGuardPlugin getWorldGuard() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            return null;
        }

        return (WorldGuardPlugin) plugin;
    }

    public static WorldGuardPlugin getWG() {
        return wg;
    }


    @Override
    public void onDisable() {
        plugin = null;
    }

    public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(positions.get(event.getPlayer()) != null) {
            positions.remove(event.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if(positions.get(event.getPlayer()) != null) {
            positions.remove(event.getPlayer());
        }
    }

    private void doTick() {
        getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()) {
                    if(!p.hasPermission("restrictions.kick.exempt") && !p.hasPermission("essentials.afk")) {
                        if (positions.get(p)!=null) {
                            if (!arePositionsEqual(getScreenPosition(p), positions.get(p))) {
                                positions.remove(p);
                                positions.put(p, getScreenPosition(p));
                            }
                            else {
                                p.kickPlayer("You have been kicked for idling too long.");
                                positions.remove(p);
                            }
                        }
                        else {
                            positions.put(p, getScreenPosition(p));
                        }
                    }
                }
                doTick();
            }
        }, 12000L);
    }

    public boolean arePositionsEqual(ScreenPosition first, ScreenPosition last) {
        if(first.Pitch == last.Pitch && first.Yaw == last.Yaw)
            return true;
        else
            return false;
    }

    public ScreenPosition getScreenPosition(Player p) {
        return new ScreenPosition(p.getLocation().getPitch(), p.getLocation().getYaw());
    }
}
