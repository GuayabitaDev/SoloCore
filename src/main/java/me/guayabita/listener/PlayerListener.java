package me.guayabita.listener;

import me.guayabita.SoloCore;
import me.guayabita.user.User;
import me.guayabita.user.UserManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {
	private final UserManager userManager;

	public PlayerListener(SoloCore plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.userManager = plugin.getUserManager();
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		User user = userManager.getUser(player.getName());

		player.sendMessage("Welcome to the server, " + player.getName() + "!");
		player.sendMessage("Your strength is " + user.getStrength());
		player.sendMessage("Your defense is " + user.getDefense());
		player.sendMessage("Your agility is " + user.getAgility());
		player.sendMessage("Your intelligence is " + user.getIntelligence());
		player.sendMessage("Your perception is " + user.getPerception());
	}
}