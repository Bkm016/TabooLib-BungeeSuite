package me.skymc.taboolib.bungeesuite.bukkit.command;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public class BukkitSubCommandParameter {
	
	private String name;
	private boolean required;
	
	public BukkitSubCommandParameter(String name, boolean required) {
		this.name = name;
		this.required = required;
	}
	
	public String toString() {
		return required ? "¡ì7[¡ì8" + name + "¡ì7]" : "¡ì7<¡ì8" + name + "¡ì7>";
	}

	public String getName() {
		return this.name;
	}

	public boolean isRequired() {
		return this.required;
	}
}
