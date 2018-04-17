package me.skymc.taboolib.bungeesuite.timeable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public abstract class Timeable {
	
	@Getter
	@Setter
	long startDate;
	
	@Getter
	@Setter
	long effectiveDate;
	
	public Timeable(long effective) {
		startDate = System.currentTimeMillis();
		effectiveDate = System.currentTimeMillis() + effective;
	}
	
	public boolean isTimeLess() {
		return System.currentTimeMillis() > effectiveDate;
	}
}
