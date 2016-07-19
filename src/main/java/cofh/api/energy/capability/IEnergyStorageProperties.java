package cofh.api.energy.capability;

public interface IEnergyStorageProperties
{
	/**
	 * Returns the amount of energy currently stored.
	 */
	int getEnergyStored();

	/**
	 * Returns the maximum amount of energy that can be stored.
	 */
	int getEnergyCapacity();
	
	/**
	 * Returns true if the object can receive energy
	 */
	boolean canReceive();
	
	/**
	 * Returns true if energy can be extracted
	 */
	boolean canExtract();
}
