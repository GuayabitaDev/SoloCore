package me.guayabita.user.storage;

import me.guayabita.SoloCore;
import me.guayabita.user.IUser;
import me.guayabita.user.User;
import me.guayabita.user.UserManager;
import me.guayabita.utils.FileConfig;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class FlatFile implements IUser {

	private final SoloCore plugin;
	private final FileConfig userDataFile;
	private final UserManager userManager;

	public FlatFile(SoloCore plugin, UserManager userManager) {
		this.plugin = plugin;
		this.userDataFile = plugin.getUserDataFile();
		this.userManager = userManager;
	}

	@Override
	public User getUser(String name) {
		Player player = Bukkit.getPlayer(name);

		if (player != null) {
			return userManager.getUser(player.getUniqueId());
		}

		ConfigurationSection section = userDataFile.getConfiguration().getConfigurationSection("users." + name);

		if (section != null) {
			User user = new User(name);
			this.loadUser(user, section);
			return user;
		}
		return null;
	}

	@Override
	public void saveUser(User user, boolean forceSave) {
		toSavable(user, forceSave);
	}

	@Override
	public void loadUser(User user, boolean byName) {
		ConfigurationSection section = userDataFile.getConfiguration().getConfigurationSection("users." + user.getUuid());

		if (section == null) {
			this.saveUser(user, true);
			return;
		}

		this.loadUser(user, section);
	}

	public void loadUser(User user, ConfigurationSection section) {
		user.setStrength(section.getInt(".strength"));
		user.setDefense(section.getInt(".defense"));
		user.setAgility(section.getInt(".agility"));
		user.setMana(section.getDouble(".mana"));
	}

	@Override
	public void reset() {
		FileConfiguration config = userDataFile.getConfiguration();
		config.set("users", new HashMap<>());
		userDataFile.save();
		userDataFile.reload();
	}

	public void toSavable(User user, boolean forceSave) {
		ConfigurationSection section = userDataFile.getConfiguration().getConfigurationSection("users");
		if (section == null) return;
		String UUID = user.getUuid().toString();
		section.set(UUID + ".strength", user.getStrength());
		section.set(UUID + ".defense", user.getDefense());
		section.set(UUID + ".agility", user.getAgility());
		section.set(UUID + ".mana", user.getMana());
		if (forceSave) userDataFile.save();
	}
}