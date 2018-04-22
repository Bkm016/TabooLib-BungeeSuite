package me.skymc.taboolib.bungeesuite.serialize;

import java.util.List;

import lombok.Getter;

/**
 * @author Bkm016
 * @since 2018-04-19
 */
public class TChannelStringList extends TChannelSerializable {
	
	@Getter
	private List<String> args;
	
	public TChannelStringList(List<String> args) {
		this.args = args;
	}

	@Override
	public String serialize() {
		StringBuilder stringBuilder = new StringBuilder();
		
		return null;
	}

}
