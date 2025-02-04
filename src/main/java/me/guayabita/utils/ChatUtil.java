package me.guayabita.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@UtilityClass
public class ChatUtil {

	public String NORMAL_LINE = "&7&m-----------------------------";

	public String translate(String text) {
		Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
		Matcher matcher = pattern.matcher(text);

		while (matcher.find()) {
			String hexCode = text.substring(matcher.start(), matcher.end());
			String replaceSharp = hexCode.replace('#', 'x');

			char[] ch = replaceSharp.toCharArray();
			StringBuilder builder = new StringBuilder();

			for (char c : ch) {
				builder.append("&").append(c);
			}

			text = text.replace(hexCode, builder.toString());
			matcher = pattern.matcher(text);
		}
		return ChatColor.translateAlternateColorCodes('&', text);
	}

	public String[] translate(String[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = translate(array[i]);
		}
		return array;
	}

	public List<String> translate(List<String> list) {
		return list.stream().map(ChatUtil::translate).collect(Collectors.toList());
	}

	public void sendMessage(CommandSender sender, String text) {
		if (text.isEmpty()) return;
		sender.sendMessage(translate(text));
	}

	public void sendMessage(CommandSender sender, String[] array) {
		if (array.length == 0) return;
		sender.sendMessage(translate(array));
	}

	public String strip(String text) {
		return ChatColor.stripColor(text);
	}

	public void broadcast(String text) {
		Bukkit.broadcastMessage(translate(text));

	}

	public void logger(String text) {
		Bukkit.getConsoleSender().sendMessage(translate(text));
	}

	public void logger(String[] text) {
		Bukkit.getConsoleSender().sendMessage(translate(text));
	}

	public String capitalize(String text) {
		return text.toLowerCase().replace("_", " ");
	}
}