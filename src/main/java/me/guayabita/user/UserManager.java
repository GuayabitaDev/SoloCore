package me.guayabita.user;

import lombok.Getter;
import me.guayabita.SoloCore;
import me.guayabita.user.storage.FlatFile;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class UserManager {

	private final SoloCore plugin;

	private final IUser user;
	private final Map<UUID, User> users;

	public UserManager(SoloCore plugin) {
		this.plugin = plugin;
		this.users = new HashMap<>();
		this.user = new FlatFile(plugin, this);
	}

	public User getUser(UUID uuid) {
		return users.getOrDefault(uuid, null);
	}

	public User getUser(String name) {
		return user.getUser(name);
	}

	public User createUser(UUID uuid, String name) {
		User user = new User(plugin, uuid, name);
		users.put(uuid, user);
		return user;
	}

	public void saveUser(User user, boolean async, boolean forceSave) {
		if (async) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> this.user.saveUser(user, forceSave));
		} else {
			this.user.saveUser(user, forceSave);
		}
	}

	public void loadUser(User user, boolean byName) {
		this.user.loadUser(user, byName);
	}

	public void destroyUser(User user) {
		users.remove(user.getUuid());
	}

	public void onDisable() {
		for (User user : users.values()) {
			if (user != null) saveUser(user, false, false);
		}
		plugin.getUserDataFile().save();
	}
}
