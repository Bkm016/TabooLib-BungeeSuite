package me.skymc.taboolib.bungeesuite.timeable;

/**
 * @author Bkm016
 * @since 2018-04-17
 */
public abstract class Timeable {
	
	long startDate;
	
	long effectiveDate;
	
	public Timeable(long effective) {
		startDate = System.currentTimeMillis();
		effectiveDate = System.currentTimeMillis() + effective;
	}
	
	public boolean isTimeLess() {
		return System.currentTimeMillis() > effectiveDate;
	}

	public long getStartDate() {
		return this.startDate;
	}

	public long getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public void setEffectiveDate(long effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
}
