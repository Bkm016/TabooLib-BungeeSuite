package me.skymc.taboolib.bungeesuite.permission;

import me.skymc.taboolib.bungeesuite.TabooLibBukkit;
import me.skymc.taboolib.bungeesuite.events.BukkitCommandEvent;
import me.skymc.taboolib.bungeesuite.logger.TLogger;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.Arrays;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public class PermissionBukkitHandler implements Listener {
	
	private Plugin plugin;
	private Permission permission;
	private boolean vaultEnable;
	
	public PermissionBukkitHandler(Plugin plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
		permissionHook();
	}
	
	@EventHandler
	public void onCommand(BukkitCommandEvent e) {
		if (e.isCancelled() || !e.getArgs()[0].equals("Permission") || !vaultEnable) {
			return;
		}
		try {
			if (e.getArgs()[1].equalsIgnoreCase("Has")) {
				Player player = Bukkit.getPlayerExact(e.getArgs()[2]);
				if (player != null) {
					e.response(String.valueOf(permission.playerHas(player, e.getArgs()[3])));
				}
			}
			else if (e.getArgs()[1].equalsIgnoreCase("Group")) {
				Player player = Bukkit.getPlayerExact(e.getArgs()[2]);
				if (player != null) {
					e.response(permission.getPrimaryGroup((player)));
				}
			}
		} catch (Exception err) {
			TLogger.error("Invalid BukkitCommand: &c" + Arrays.asList(e.getArgs()));
			TLogger.error("Exception: &c" + err.getMessage());
		}
	}
	
	private void permissionHook() {
		if (vaultEnable = vaultTest()) {
			RegisteredServiceProvider<Permission> rsp = TabooLibBukkit.getInst().getServer().getServicesManager().getRegistration(Permission.class);
			permission = rsp.getProvider();
			TLogger.info("Support Vault");
		} else {
			TLogger.error("Notfound Vault");
		}
	}
	
	private boolean vaultTest() {
		try {
			Class.forName("net.milkbowl.vault.permission.Permission");
			return true;
		} catch (Exception ignored) {
			return false;
		}
	}

	public Plugin getPlugin() {
		return this.plugin;
	}

	public Permission getPermission() {
		return this.permission;
	}

	public boolean isVaultEnable() {
		return this.vaultEnable;
	}
}
