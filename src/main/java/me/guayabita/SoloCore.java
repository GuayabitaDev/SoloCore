package me.guayabita;

import lombok.Getter;
import me.guayabita.listener.PlayerListener;
import me.guayabita.user.UserManager;
import me.guayabita.utils.FileConfig;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class SoloCore extends JavaPlugin {

	private FileConfig configFile, messagesFile, userDataFile;
	private UserManager userManager;


	@Override
	public void onEnable() {
		this.configFile = new FileConfig(this, "config.yml");
		this.messagesFile = new FileConfig(this, "messages.yml");
		this.userDataFile = new FileConfig(this, "data/user-data.yml");
		this.userManager = new UserManager(this);

		new PlayerListener(this);
	}

	@Override
	public void onDisable() {
		userManager.onDisable();
	}

	public void onReload() {
		this.configFile.reload();
		this.messagesFile.reload();
		this.userDataFile.reload();
	}

	public static SoloCore get() {
		return getPlugin(SoloCore.class);
	}
}
