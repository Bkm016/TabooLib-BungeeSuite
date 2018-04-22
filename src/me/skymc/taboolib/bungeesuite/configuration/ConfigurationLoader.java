package me.skymc.taboolib.bungeesuite.configuration;

import java.io.File;

import me.skymc.taboolib.bungeesuite.logger.TLogger;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

/**
 * @author Bkm016
 * @since 2018-04-16
 */
public class ConfigurationLoader {
	
	public static Configuration load(File folder, String name) {
		ConfigurationProvider config = ConfigurationProvider.getProvider(YamlConfiguration.class);
		File file = new File(folder, name);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception ignored) {
			}
		}
		try {
			return config.load(file);
		} catch (Exception ignored) {
			TLogger.warn("Invalid File: &4" + name);
		}
		return new Configuration();
	}
	
	public static void save(Configuration configuration, File file) {
		ConfigurationProvider config = ConfigurationProvider.getProvider(YamlConfiguration.class);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (Exception ignored) {
			}
		}
		try {
			config.save(configuration, file);
		} catch (Exception ignored) {
			TLogger.warn("Invalid File: &4" + file.getName());
		}
	}
}
