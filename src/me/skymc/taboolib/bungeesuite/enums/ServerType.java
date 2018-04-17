package me.skymc.taboolib.bungeesuite.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bkm016
 * @since 2018-04-16
 */
public enum ServerType {
	
	BUNGEECORD, BUKKIT;
	
	@Setter
	@Getter
	private static ServerType serverType;

}
