package me.skymc.taboolib.bungeesuite.plugindata;

import lombok.Getter;
import me.skymc.taboolib.bungeesuite.bukkit.TBukkitChannel;

/**
 * @author Bkm016
 * @since 2018-04-19
 */
public class PluginDataBukkitHandler {
	
	@Getter
	private TBukkitChannel channel;
	
	public PluginDataBukkitHandler(TBukkitChannel channel) {
		this.channel = channel;
		
	}
}
