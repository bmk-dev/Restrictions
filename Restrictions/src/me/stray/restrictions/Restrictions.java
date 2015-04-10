package me.stray.restrictions;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Stray on 4/9/2015.
 */
public class Restrictions extends JavaPlugin implements Listener {

    File locationFile;
    FileConfiguration locations;

    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        registerEvents(this, new WorldListener());
        registerEvents(this, new PistonListener());
        registerEvents(this, this);

        locationFile = new File(getDataFolder(), "locations.yml");
        try {
            firstRun();
        } catch (Exception e) {
            e.printStackTrace();
        }
        locations = new YamlConfiguration();
        loadYamls();
    }


    @Override
    public void onDisable() {
        plugin = null;
        saveYamls();
    }

    public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    //Following 2 prevent afking. 2 Separate methods because I wanted to.
    //Uses player pitch/yaw to check whether or not the player is sitting in an afk machine. The chance of a non-afk player being kicked by this is nearly 0, as they would be moving
    //around, and pitch/yaw are both calculated down to the millionth.
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        if(!event.getPlayer().isOp()) {
            long check = System.currentTimeMillis() + (600 * 1000); // (seconds * 1000), 600 seconds is 10 minutes.
            if(locations.get("locations." + event.getPlayer().getUniqueId().toString()) != null) {
                if(locations.getDouble("locations." + p.getUniqueId().toString() + ".pitch") != p.getLocation().getPitch()) {
                    if(locations.getDouble("locations." + p.getUniqueId().toString() + ".yaw") != p.getLocation().getYaw()) {
                        locations.set("locations." + p.getUniqueId().toString(), null);
                        locations.set("locations." + p.getUniqueId().toString() + ".afk", check);
                        locations.set("locations." + p.getUniqueId().toString() + ".pitch", p.getLocation().getPitch());
                        locations.set("locations." + p.getUniqueId().toString() + ".yaw", p.getLocation().getYaw());
                        saveYamls();
                    }
                }
            }
            else {
                locations.set("locations." + p.getUniqueId().toString() + ".afk", check);
                locations.set("locations." + p.getUniqueId().toString() + ".yaw", p.getLocation().getYaw());
                locations.set("locations." + p.getUniqueId().toString() + ".pitch", p.getLocation().getPitch());
                saveYamls();
            }
        }
    }

    @EventHandler
    public void onPlayerMove1(PlayerMoveEvent event) {
        if(!event.getPlayer().isOp()) {
            Player p = event.getPlayer();
            if(locations.get("locations." + p.getUniqueId().toString()) != null) {
                if(locations.getLong("locations." + p.getUniqueId().toString() + ".afk") <= System.currentTimeMillis()) {
                    if(locations.getDouble("locations." + p.getUniqueId().toString() + ".pitch") == p.getLocation().getPitch()) {
                        if(locations.getDouble("locations." + p.getUniqueId().toString() + ".yaw") == p.getLocation().getYaw()) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kick " + p.getName() + " You have been kicked for idling too long.");
                            locations.set("locations." + p.getUniqueId().toString(), null);
                            saveYamls();
                        }
                    }
                }
            }
        }
    }

    private void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadYamls() {
        try {
            locations.load(locationFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveYamls() {
        try {

            locations.save(locationFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void firstRun() throws Exception {
        if(!locationFile.exists()){
            locationFile.getParentFile().mkdirs();
            copy(getResource("locations.yml"), locationFile);
        }

    }



}
