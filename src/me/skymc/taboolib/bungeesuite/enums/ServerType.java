package me.skymc.taboolib.bungeesuite.enums;

/**
 * @author Bkm016
 * @since 2018-04-16
 */
public enum ServerType {
	
	BUNGEECORD, BUKKIT;
	
	private static ServerType serverType;

	public static ServerType getServerType() {
		return ServerType.serverType;
	}

	public static void setServerType(ServerType serverType) {
		ServerType.serverType = serverType;
	}
}
