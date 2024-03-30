package me.guayabita.user;

import lombok.Getter;
import lombok.Setter;
import me.guayabita.SoloCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter
@Setter
public class User {

	private UUID uuid;
	private String name;
	private int strength;
	private int defense;
	private int agility;
	private double mana;

	public User(SoloCore plugin, UUID uuid, String name) {
		this.uuid = uuid;
		this.name = name;
		this.strength = plugin.getConfigFile().getInt("initial-strength");
		this.defense = plugin.getConfigFile().getInt("initial-defense");
		this.agility = plugin.getConfigFile().getInt("initial-agility");
		this.mana = plugin.getConfigFile().getDouble("initial-mana");
	}

	public User(String name) {
		this.name = name;
	}

	public void addStrength(int amount) {
		this.strength += amount;
	}

	public void removeStrength(int amount) {
		this.strength -= amount;
	}

	public void addDefense(int amount) {
		this.defense += amount;
	}

	public void removeDefense(int amount) {
		this.defense -= amount;
	}

	public void addAgility(int amount) {
		this.agility += amount;
	}

	public void removeAgility(int amount) {
		this.agility -= amount;
	}

	public void addMana(double amount) {
		this.mana += mana;
	}

	public void removeMana(double mana) {
		this.mana -= mana;
	}

	public Player getPlayer() {
		return Bukkit.getPlayer(name);
	}

	public boolean isOffline() {
		return getPlayer() == null;
	}
}
