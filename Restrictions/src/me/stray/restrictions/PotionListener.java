package me.stray.restrictions;

import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import org.bukkit.*;
import org.bukkit.block.Dropper;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.*;
import org.bukkit.material.Dispenser;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Stray on 4/9/2015.
 */
public class PotionListener implements Listener {

    @EventHandler
    public void onPotionDrink(PlayerItemConsumeEvent event) {
        if(event.getItem().getType() == Material.POTION) {
            short s = event.getItem().getDurability();
            if(s == 8233 || s == 8267 || s == 8235 || s == 8203 || s == 8238 || s == 8270) {
                event.setCancelled(true);
            }
        }
        if(event.getItem().getType() == Material.GOLDEN_APPLE && event.getItem().getDurability() == 1) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPotionSplash(PotionSplashEvent event) {
        if(event.getPotion().getItem().getType() == Material.POTION) {
            if(event.getPotion().getItem().getDurability() == 16427 || event.getPotion().getItem().getDurability() == 16459
                    || event.getPotion().getItem().getDurability() == 16425 || event.getPotion().getItem().getDurability() == 16395
                    || event.getPotion().getItem().getDurability() == 16462 || event.getPotion().getItem().getDurability() == 16430) {
                event.setCancelled(true);
            }
        }
    }
}
