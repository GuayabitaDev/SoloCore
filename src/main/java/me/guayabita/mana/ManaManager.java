package me.guayabita.mana;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

@Getter
public class ManaManager {

	HashMap<UUID, Double> currentMana;

	public ManaManager() {
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
}