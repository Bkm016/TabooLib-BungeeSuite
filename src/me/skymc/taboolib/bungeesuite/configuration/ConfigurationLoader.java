package me.skymc.taboolib.bungeesuite.configuration;

import java.io.File;

import me.skymc.taboolib.bungeesuite.logger.TLogger;
import me.skymc.taboolib.bungeesuite.yaml.FileConfiguration;
import me.skymc.taboolib.bungeesuite.yaml.YamlConfiguration;

/**
 * @author Bkm016
 * @since 2018-04-16
 */
public class ConfigurationLoader {
	
	public static FileConfiguration load(File folder, String name) {
		File file = new File(folder, name);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception ignored) {
			}
		}
		return YamlConfiguration.loadConfiguration(file);
	}
	
	public static void save(FileConfiguration configuration, File file) {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception ignored) {
			}
		}
		try {
			configuration.save(file);
		} catch (Exception ignored) {
			TLogger.warn("Invalid File: &4" + file.getName());
		}
	}
}
