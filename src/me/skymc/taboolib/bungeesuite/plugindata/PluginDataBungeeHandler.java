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
import me.skymc.taboolib.bungeesuite.util.ByteUtils;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
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
	private HashMap<String, Configuration> plugindata = new HashMap<>();
	
	public PluginDataBungeeHandler(Plugin plugin) {
		this.plugin = plugin;
		this.dataFolder = new File(plugin.getDataFolder(), "plugindata");
		if (!dataFolder.exists()) {
			plugin.getDataFolder().mkdirs();
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
			if (e.getString(1).equalsIgnoreCase("Create")) {
				if (!plugindata.containsKey(e.getString(2))) {
					plugindata.put(e.getString(2), ConfigurationLoader.load(dataFolder, formatName(e.getString(2))));
					TLogger.info("Configuration " + e.getString(2) + " Loaded!");
				}
			}
			else if (e.getString(1).equalsIgnoreCase("Reload")) {
				plugindata.put(e.getString(2), ConfigurationLoader.load(dataFolder, formatName(e.getString(2))));
				TLogger.info("Configuration " + e.getString(2) + " Reloaded!");
			}
			else if (e.getString(1).equalsIgnoreCase("Delete")) {
				if (plugindata.containsKey(e.getString(2))) {
					Configuration configuration = plugindata.remove(e.getString(2));
					ConfigurationLoader.save(configuration, new File(dataFolder, formatName(e.getString(2))));
					TLogger.info("Configuration " + e.getString(2) + " Deleted!");
				}
			}
			else if (e.getString(1).equalsIgnoreCase("Set")) {
				if (plugindata.containsKey(e.getString(2))) {
					Configuration configuration = plugindata.get(e.getString(2));
					for (int i = 3 ; i < e.getArgs().length ; i++) {
						String key = e.getString(i);
						String value = i++ < e.getArgs().length ? e.getString(i) : null;
						if (value != null) {
							configuration.set(ByteUtils.decode(key), ByteUtils.decode(value));
						}
					}
				} else {
					TLogger.warn("Configuration " + e.getString(2) + " Not found!");
				}
			}
			else if (e.getString(1).equalsIgnoreCase("Get")) {
				if (plugindata.containsKey(e.getString(2))) {
					List<String> vars = new ArrayList<>();
					Configuration configuration = plugindata.get(e.getString(2));
					for (int i = 3 ; i < e.getArgs().length ; i++) {
						String var = configuration.getString(ByteUtils.decode(e.getString(i)));
						if (var == null || var.isEmpty()) {
							vars.add("null");
						} else {
							vars.add("var");
						}
					}
					e.response(ByteUtils.encode(vars.toArray(new String[0])));
				} else {
					e.response("null");
				}
			}
			else if (e.getString(1).equalsIgnoreCase("List")) {
				if (plugindata.containsKey(e.getString(2))) {
					List<String> vars = new ArrayList<>();
					Configuration configuration = plugindata.get(e.getString(2));
					if (configuration.contains(e.getString(3))) {
						vars.addAll(configuration.getSection(e.getString(3)).getKeys());
					}
					e.response(ByteUtils.encode(vars.toArray(new String[0])));
				} else {
					e.response("null");
				}
			}
		} catch (Exception err) {
			TLogger.error("Invalid BukkitCommand: &c" + ArrayUtils.asList(e.getArgs()));
			TLogger.error("Exception: &c" + err.getMessage());
		}
	}

	private String formatName(String var) {
		return var.contains(".yml") ? var : var + ".yml";
	}
}
