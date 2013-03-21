package me.chaseoes.freezeapi;

import java.util.HashSet;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class FreezeAPI extends JavaPlugin implements Listener {
    
    private HashSet<String> frozen = new HashSet<String>();
    
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }
    
    public void freeze(Player player) {
        frozen.add(player.getName());
    }
    
    public void unFreeze(Player player) {
        frozen.remove(player.getName());
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getTo().getBlockX() != event.getFrom().getBlockX() && event.getTo().getBlockY() != event.getFrom().getBlockY() && event.getTo().getBlockZ() != event.getFrom().getBlockZ()) {
            if (frozen.contains(event.getPlayer().getName())) {
                event.setTo(event.getFrom());
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if (frozen.contains(event.getPlayer().getName())) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (frozen.contains(event.getPlayer().getName())) {
            event.setCancelled(true);
        }
    }

}
