package me.skymc.taboolib.bungeesuite.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.skymc.taboolib.bungeesuite.TabooLibBukkit;

/**
 * @author Bkm016
 * @since 2018-04-20
 */
public class ListenerPlayer implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Bukkit.getScheduler().runTaskLaterAsynchronously(TabooLibBukkit.getInst(), () -> TabooLibBukkit.getInst().getPlayerDataHandler().loadPlayerDataLocal(e.getPlayer()), 5);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		TabooLibBukkit.getInst().getPlayerDataHandler().unloadPlayerDataLocal(e.getPlayer());
	}

}
