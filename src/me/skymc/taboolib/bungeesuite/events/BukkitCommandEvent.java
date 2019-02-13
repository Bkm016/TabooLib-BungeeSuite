package me.skymc.taboolib.bungeesuite.events;

import me.skymc.taboolib.bungeesuite.TabooLibBukkit;
import me.skymc.taboolib.bungeesuite.util.ArrayUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

/**
 * @author Bkm016
 * @since 2018-04-16
 */
public class BukkitCommandEvent extends Event implements Cancellable {
	
	private static final HandlerList handlers = new HandlerList();
	
	private Player player;
	private String[] args;
	private UUID uid;
	private boolean cancel;
	
	public BukkitCommandEvent(Player player, UUID uid, String[] args) {
		this.player = player;
		this.args = args;
		this.uid = uid;;
	}
	
	@Override
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}
	
	@Override
	public boolean isCancelled() {
		return this.cancel;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
	public void response(String... args) {
		TabooLibBukkit.getInst().getBukkitChannel().sendBukkitMessage(player, ArrayUtils.addFirst(args, uid.toString()));
	}
	
	public String getString(int index) {
		return getString(index, "");
	}
	
	public int getInteger(int index) {
		return getInteger(index, 0);
	}
	
	public double getDouble(int index) {
		return getDouble(index, 0);
	}
	
	public long getLong(int index) {
		return getLong(index, 0);
	}
	
	public boolean getBoolean(int index) {
		return getBoolean(index, false);
	}
	
	public String getString(int index, String defaultVault) {
		return index < args.length ? args[index] : defaultVault;
	}
	
	public int getInteger(int index, int defaultVault) {
		try {
			return index < args.length ? Integer.valueOf(args[index]) : defaultVault;
		} catch (Exception e) {
			return defaultVault;
		}
	}
	
	public double getDouble(int index, double defaultVault) {
		try {
			return index < args.length ? Double.valueOf(args[index]) : defaultVault;
		} catch (Exception e) {
			return defaultVault;
		}
	}
	
	public long getLong(int index, long defaultVault) {
		try {
			return index < args.length ? Long.valueOf(args[index]) : defaultVault;
		} catch (Exception e) {
			return defaultVault;
		}
	}
	
	public boolean getBoolean(int index, boolean defaultVault) {
		try {
			return index < args.length ? Boolean.valueOf(args[index]) : defaultVault;
		} catch (Exception e) {
			return defaultVault;
		}
	}

	public Player getPlayer() {
		return this.player;
	}

	public String[] getArgs() {
		return this.args;
	}

	public UUID getUid() {
		return this.uid;
	}
}
