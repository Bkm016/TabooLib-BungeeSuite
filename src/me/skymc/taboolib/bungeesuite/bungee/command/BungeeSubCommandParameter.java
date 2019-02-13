package me.skymc.taboolib.bungeesuite.bungee.command;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public class BungeeSubCommandParameter {
	
	private String name;
	private boolean required;
	
	public BungeeSubCommandParameter(String name, boolean required) {
		this.name = name;
		this.required = required;
	}
	
	public String toString() {
		return required ? "��7[��8" + name + "��7]" : "��7<��8" + name + "��7>";
	}

	public String getName() {
		return this.name;
	}

	public boolean isRequired() {
		return this.required;
	}
}
