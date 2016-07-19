package cofh.api.energy.capability;


public interface IEnergyHandler
{
    /**
     * Returns an array of objects which represent the internal energy storage.
     * These objects cannot be used to manipulate the internal energy.
     *
     * @return Properties for the relevant internal energy storage.
     */
	IEnergyStorageProperties[] getStorageProperties();
	
	/**
	 * Add energy to an IEnergyReceiver, internal distribution is left entirely to the IEnergyReceiver.
	 *
	 * @param maxReceive
	 *            Maximum amount of energy to receive.
	 * @param simulate
	 *            If TRUE, the charge will only be simulated.
	 * @return Amount of energy that was (or would have been, if simulated) received.
	 */
	int receiveEnergy(int maxReceive, boolean simulate);
	
	/**
	 * Remove energy from an IEnergyProvider, internal distribution is left entirely to the IEnergyProvider.
	 *
	 * @param maxExtract
	 *            Maximum amount of energy to extract.
	 * @param simulate
	 *            If TRUE, the extraction will only be simulated.
	 * @return Amount of energy that was (or would have been, if simulated) extracted.
	 */
	int extractEnergy(int maxExtract, boolean simulate);
}
