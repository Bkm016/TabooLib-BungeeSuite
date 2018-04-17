package me.skymc.taboolib.bungeesuite.bukkit.command;

import lombok.Getter;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public class BukkitSubCommandParameter {
	
	@Getter
	private String name;
	@Getter
	private boolean required;
	
	public BukkitSubCommandParameter(String name, boolean required) {
		this.name = name;
		this.required = required;
	}
	
	public String toString() {
		return required ? "��7[��8" + name + "��7]" : "��7<��8" + name + "��7>";
	}
}
