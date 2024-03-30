package me.guayabita.mana;

import lombok.Setter;
import me.guayabita.SoloCore;
import me.guayabita.user.User;
import me.guayabita.user.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Setter
public class ManaRunnable implements Runnable {

	private final SoloCore plugin;
	private final UserManager userManager;
	private final ManaManager manaManager;

	public ManaRunnable(SoloCore plugin) {
		this.plugin = plugin;
		this.manaManager = plugin.getManaManager();
		this.userManager = plugin.getUserManager();
	}

	@Override
	public void run() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			User user = userManager.getUser(player.getName());
			if (manaManager.getCurrentMana(player.getUniqueId()) < user.getMana()) {
				manaManager.increaseMana(player, plugin.getConfigFile().getDouble("MANA.mana-regen"));
			}
		}
	}
}
