package me.skymc.taboolib.bungeesuite;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.skymc.taboolib.bungeesuite.bukkit.TBukkitChannel;
import me.skymc.taboolib.bungeesuite.bukkit.TBukkitChannelExecutor;
import me.skymc.taboolib.bungeesuite.bukkit.command.BukkitCommand;
import me.skymc.taboolib.bungeesuite.enums.ServerType;
import me.skymc.taboolib.bungeesuite.listener.ListenerBukkitMessage;
import me.skymc.taboolib.bungeesuite.listener.ListenerPlayer;
import me.skymc.taboolib.bungeesuite.logger.TLogger;
import me.skymc.taboolib.bungeesuite.permission.PermissionBukkitHandler;
import me.skymc.taboolib.bungeesuite.playerdata.PlayerDataBukkitHandler;
import me.skymc.taboolib.bungeesuite.plugindata.PluginDataBukkitHandler;

/**
 * @author Bkm016
 * @since 2018-04-16
 */
public class TabooLibBukkit extends JavaPlugin {
	
	@Getter
	private static TabooLibBukkit inst;
	@Getter
	private TBukkitChannel bukkitChannel;
	@Getter
	private TBukkitChannelExecutor bukkitChannelExecutor;
	@Getter
	private PlayerDataBukkitHandler playerDataHandler;
	@Getter
	private PermissionBukkitHandler permissionHandler;
	@Getter
	private PluginDataBukkitHandler pluginDataHandler;
	
	@Override
	public void onLoad() {
		ServerType.setServerType(ServerType.BUKKIT);
	}
	
	@Override
	public void onEnable() {
		inst = this;
		bukkitChannel = new TBukkitChannel(this);
		bukkitChannelExecutor = new TBukkitChannelExecutor(bukkitChannel);
		playerDataHandler = new PlayerDataBukkitHandler(bukkitChannel);
		permissionHandler = new PermissionBukkitHandler(this);
		pluginDataHandler = new PluginDataBukkitHandler(bukkitChannel);
		
		Bukkit.getPluginCommand("taboolibbungeesuite").setExecutor(new BukkitCommand());
		Bukkit.getPluginCommand("taboolibbungeesuite").setTabCompleter(new BukkitCommand());
		
		Bukkit.getMessenger().registerIncomingPluginChannel(this, "taboolib|out", new ListenerBukkitMessage());
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "taboolib|in");
		
		Bukkit.getPluginManager().registerEvents(new ListenerPlayer(), this);
		
		TLogger.info("插件已载入");
		TLogger.info("作者: &8" + getDescription().getAuthors());
		TLogger.info("版本: &8" + getDescription().getVersion());
		TLogger.info("框架: &8" + ServerType.getServerType());
	}
	
	@Override
	public void onDisable() {
		Bukkit.getMessenger().unregisterIncomingPluginChannel(this);
		Bukkit.getMessenger().unregisterOutgoingPluginChannel(this);
	}
}
