package me.guayabita.mana;

import me.guayabita.SoloCore;
import me.guayabita.user.User;
import me.guayabita.user.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ManaManager implements Runnable {

	private final SoloCore plugin;
	private final UserManager userManager;
	HashMap<UUID, Double> currentMana;

	public ManaManager(SoloCore plugin) {
		this.plugin = plugin;
		this.userManager = plugin.getUserManager();
		this.currentMana = new HashMap<>();
	}

	public double getCurrentMana(UUID uuid) {
		return currentMana.getOrDefault(uuid, 0.0);
	}

	public void increaseMana(Player player, double amount) {
		currentMana.put(player.getUniqueId(), getCurrentMana(player.getUniqueId()) + amount);
	}

	public void decreaseMana(Player player, double amount) {
		currentMana.put(player.getUniqueId(), getCurrentMana(player.getUniqueId()) - amount);
	}

	public void setMana(Player player, double amount) {
		currentMana.put(player.getUniqueId(), amount);
	}

	@Override
	public void run() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			User user = userManager.getUser(player.getName());

			if (getCurrentMana(player.getUniqueId()) < user.getMana()) {
				increaseMana(player, 0.5);
			}

		}
	}
}