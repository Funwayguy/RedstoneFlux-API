package cofh.api.energy.capability.wrappers;

import cofh.api.energy.capability.EnergyStorage;
import cofh.api.energy.capability.IEnergyStorageProperties;

public class EnergyStoragePropertiesWrapper implements IEnergyStorageProperties
{
	EnergyStorage storage;
	
	public EnergyStoragePropertiesWrapper(EnergyStorage storage)
	{
		this.storage = storage;
	}
	
	@Override
	public int getEnergyStored()
	{
		return storage.getEnergyStored();
	}

	@Override
	public int getEnergyCapacity()
	{
		return storage.getMaxEnergyStored();
	}

	@Override
	public boolean canReceive()
	{
		return storage.canReceive();
	}

	@Override
	public boolean canExtract()
	{
		return storage.canExtract();
	}
}
