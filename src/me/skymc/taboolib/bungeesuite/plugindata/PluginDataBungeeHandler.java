package me.skymc.taboolib.bungeesuite.plugindata;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import lombok.Getter;
import me.skymc.taboolib.bungeesuite.configuration.ConfigurationLoader;
import me.skymc.taboolib.bungeesuite.events.BungeeCommandEvent;
import me.skymc.taboolib.bungeesuite.logger.TLogger;
import me.skymc.taboolib.bungeesuite.util.ArrayUtils;
import me.skymc.taboolib.bungeesuite.yaml.FileConfiguration;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

/**
 * @author Bkm016
 * @since 2018-04-19
 */
public class PluginDataBungeeHandler implements Listener {
	
	@Getter
	private Plugin plugin;
	@Getter
	private File dataFolder;
	@Getter
	private HashMap<String, FileConfiguration> plugindata = new HashMap<>();
	
	public PluginDataBungeeHandler(Plugin plugin) {
		this.plugin = plugin;
		this.dataFolder = new File(plugin.getDataFolder(), "plugindata");
		if (!dataFolder.exists()) {
			dataFolder.mkdirs();
		}
		BungeeCord.getInstance().getPluginManager().registerListener(plugin, this);
		BungeeCord.getInstance().getScheduler().schedule(plugin, new Runnable() {
			
			@Override
			public void run() {
				BungeeCord.getInstance().getScheduler().runAsync(plugin, () -> saveFile());
			}
		}, 60, 60, TimeUnit.SECONDS);
	}
	
	public void saveFile() {
		plugindata.entrySet().forEach(entry -> ConfigurationLoader.save(entry.getValue(), new File(dataFolder, formatName(entry.getKey()))));
	}
	
	@EventHandler
	public void onCommand(BungeeCommandEvent e) {
		if (e.isCancelled() || !e.getArgs()[0].equals("PluginData")) {
			return;
		}
		try {
			if (e.getString(1).equalsIgnoreCase("Reload")) {
				plugindata.put(e.getString(2), ConfigurationLoader.load(dataFolder, formatName(e.getString(2))));
				TLogger.info("Configuration " + e.getString(2) + " Reloaded!");
			}
			else if (e.getString(1).equalsIgnoreCase("Delete")) {
				if (plugindata.containsKey(e.getString(2))) {
					FileConfiguration configuration = plugindata.remove(e.getString(2));
					ConfigurationLoader.save(configuration, new File(dataFolder, formatName(e.getString(2))));
					TLogger.info("Configuration " + e.getString(2) + " Deleted!");
				}
			}
			else if (e.getString(1).equalsIgnoreCase("Set")) {
				FileConfiguration configuration = getPluginData(e.getString(2));
				for (int i = 3 ; i < e.getArgs().length ; i++) {
					String key = e.getString(i);
					String value = i++ < e.getArgs().length ? e.getString(i) : null;
					if (value != null) {
						configuration.set(key, value.equals("null") ? null : value);
					}
				}
			}
			else if (e.getString(1).equalsIgnoreCase("Get")) {
				List<String> vars = new ArrayList<>();
				FileConfiguration configuration = getPluginData(e.getString(2));
				for (int i = 3 ; i < e.getArgs().length ; i++) {
					String var = configuration.getString(e.getString(i));
					if (var == null || var.isEmpty()) {
						vars.add("null");
					} else {
						vars.add(var);
					}
				}
				e.response(vars.toArray(new String[0]));
			}
			else if (e.getString(1).equalsIgnoreCase("List")) {
				List<String> vars = new ArrayList<>();
				FileConfiguration configuration = getPluginData(e.getString(2));
				if (configuration.contains(e.getString(3))) {
					vars.addAll(configuration.getConfigurationSection(e.getString(3)).getKeys(false));
				}
				e.response(vars.toArray(new String[0]));
			}
		} catch (Exception err) {
			TLogger.error("Invalid BukkitCommand: &c" + ArrayUtils.asList(e.getArgs()));
			TLogger.error("Exception: &c" + err.getMessage());
		}
	}
	
	private String formatName(String var) {
		return var.contains(".yml") ? var : var + ".yml";
	}
	
	private FileConfiguration getPluginData(String name) {
		if (plugindata.containsKey(name)) {
			return plugindata.get(name);
		} else {
			FileConfiguration configuration = ConfigurationLoader.load(dataFolder, formatName(name));
			plugindata.put(name, ConfigurationLoader.load(dataFolder, formatName(name)));
			return configuration;
		}
	}
}
