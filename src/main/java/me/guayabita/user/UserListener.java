package me.guayabita.user;

import me.guayabita.SoloCore;
import me.guayabita.utils.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class UserListener implements Listener {

	private final SoloCore plugin;
	private final UserManager userManager;

	public UserListener(SoloCore plugin) {
		this.plugin = plugin;
		this.userManager = plugin.getUserManager();
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	private void onAsyncUserPreLogin(AsyncPlayerPreLoginEvent event) {
		User user = userManager.createUser(event.getUniqueId(), event.getName());
		userManager.loadUser(user, false);
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	private void onUserLogin(PlayerLoginEvent event) {
		User user = userManager.getUser(event.getPlayer().getUniqueId());

		if (user == null) {
			event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
			event.setKickMessage(ChatUtil.translate(plugin.getMessagesFile().getString("kick-messages.failed-to-load-user")));
		}
	}

	@EventHandler
	private void onUserJoin(PlayerJoinEvent event) {

	}


	@EventHandler
	private void onUserQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		User user = userManager.getUser(player.getUniqueId());

		if (user != null) {
			userManager.saveUser(user, true, true);
			userManager.destroyUser(user);
		}
	}
}
