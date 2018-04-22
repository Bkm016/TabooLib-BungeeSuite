package me.skymc.taboolib.bungeesuite.packet;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Bkm016
 * @since 2018-04-19
 */
public class TChannelPacketPluginData implements TChannelPacket {
	
	private List<String> keys = new ArrayList<>();
	private List<Object> values = new ArrayList<>();

	@Override
	public String serialize() {
		return null;
	}
}
