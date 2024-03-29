package me.guayabita.mana;


import me.guayabita.SoloCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class ManaManager {
    private final HashMap<UUID, Double> mana = new HashMap<>();
    private final SoloCore plugin;

    public ManaManager(SoloCore plugin) {
        this.plugin = plugin;
        Bukkit.getScheduler().runTaskTimer(plugin, 
    }


    public void addPlayer(Player player){
        mana.put(player.getUniqueId(), 100.0);
    }

    public void removePlayer(Player player){
        mana.remove(player.getUniqueId());
    }


}
