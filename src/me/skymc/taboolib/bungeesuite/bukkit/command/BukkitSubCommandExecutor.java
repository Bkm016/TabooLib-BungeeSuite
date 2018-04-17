package me.skymc.taboolib.bungeesuite.bukkit.command;

import org.bukkit.entity.Player;

import me.skymc.taboolib.bungeesuite.TabooLibBukkit;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public abstract class BukkitSubCommandExecutor implements BukkitSubCommand {
	
	public BukkitSubCommandType getType() {
		return BukkitSubCommandType.ALL;
	}
	
	public boolean requiredPlayer() {
		return false;
	}
	
	public Player getOnlinePlayer() {
		return TabooLibBukkit.getInst().getBukkitChannel().getOnlinePlayer();
	}
	
	public boolean isParameterConform(String[] args) {
		for (int i = 0 ; i < getArgs().length ; i++) {
			if (getArgs()[i].isRequired() && (args == null || args.length <= i)) {
				return false;
			}
		}
		return true;
	}
	
	public String getCommandString(String label) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("¡ìf/");
		stringBuilder.append(label);
		stringBuilder.append(" ");
		stringBuilder.append(getName());
		stringBuilder.append(" ");
		for (BukkitSubCommandParameter parameter : getArgs()) {
			stringBuilder.append(parameter.toString());
			stringBuilder.append(" ");
		}
		stringBuilder.append("¡ì6- ¡ìe");
		stringBuilder.append(getUsages());
		return stringBuilder.toString();
	}
}
