package me.stray.restrictions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Dropper;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.material.Dispenser;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

/**
 * Created by Stray on 4/9/2015.
 */
public class WorldListener implements Listener {

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        if(!event.getPlayer().getWorld().getName().toLowerCase().contains("free") && !event.getPlayer().getWorld().getName().toLowerCase().contains("wild")) {
            if(!event.getPlayer().isOp()) {
                if(event.getPlayer().hasPotionEffect(PotionEffectType.JUMP)) {
                    event.getPlayer().removePotionEffect(PotionEffectType.JUMP);
                    event.getPlayer().sendMessage(ChatColor.GRAY + "Your jump boost effect was removed to prevent glitching.");
                }
            }
        }
    }

    @EventHandler
    public void onPotionDrink(PlayerItemConsumeEvent event) {
        if(event.getItem().getType() == Material.POTION) {
            if(event.getItem().getDurability() == 8267 || event.getItem().getDurability() == 8235) {
                if (!event.getPlayer().getWorld().getName().toLowerCase().contains("free") && !event.getPlayer().getWorld().getName().toLowerCase().contains("wild")) {
                    if (!event.getPlayer().isOp()) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage(ChatColor.GRAY + "Potions of Leaping are disabled in this world.");
                    } 
                }
            }
        }
    }

    @EventHandler
    public void onPotionSplash(PotionSplashEvent event) {
        if(event.getPotion().getItem().getType() == Material.POTION) {
            if(event.getPotion().getItem().getDurability() == 16427 || event.getPotion().getItem().getDurability() == 16459) {
                if (!event.getPotion().getWorld().getName().toLowerCase().contains("free") && !event.getPotion().getWorld().getName().toLowerCase().contains("wild")) {
                    ProjectileSource shooter = event.getPotion().getShooter();
                    if (shooter instanceof Player) {
                        if (!((Player) event.getPotion().getShooter()).getPlayer().isOp()) {
                            event.setCancelled(true);
                            ((Player) event.getPotion().getShooter()).getPlayer().sendMessage(ChatColor.GRAY + "Potions of Leaping are disabled in this world.");
                        }
                    } else {
                        for(LivingEntity affected : event.getAffectedEntities()) {
                            if(affected instanceof Player) {
                                Player p = ((Player) affected).getPlayer();
                                if(!p.isOp()) {
                                    p.sendMessage(ChatColor.GRAY + "Potions of Leaping are disabled in this world");
                                    event.setCancelled(true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }




}
