package cofh.api.energy.capability;

import net.minecraft.nbt.NBTTagCompound;
import cofh.api.energy.IEnergyStorage;
import cofh.api.energy.capability.wrappers.EnergyStoragePropertiesWrapper;

public class EnergyStorage implements IEnergyStorage, IEnergyHandler
{
	IEnergyStorageProperties[] storageProperties;
	private int capacity;
	private int energy;
	private int speed;
	private boolean canFill = true;
	private boolean canDrain = true;
	
	public EnergyStorage(int capacity)
	{
		this(capacity, 0, capacity);
	}
	
	public EnergyStorage(int capacity, int amount, int speed)
	{
		this(capacity, amount, speed, true, true);
	}
	
	public EnergyStorage(int capacity, int amount, int speed, boolean canFill, boolean canDrain)
	{
		this.capacity = capacity;
		this.energy = amount;
		this.speed = speed;
		this.canFill = canFill;
		this.canDrain = canDrain;
	}
	
	@Override
	public IEnergyStorageProperties[] getStorageProperties()
	{
		if(storageProperties == null)
		{
			storageProperties = new IEnergyStorageProperties[]{new EnergyStoragePropertiesWrapper(this)};
		}
		
		return storageProperties;
	}
	
	@Override
	public int receiveEnergy(int maxReceive, boolean simulate)
	{
		int value = Math.min(maxReceive, capacity - energy);
		value = Math.min(value, speed);
		
		if(!simulate)
		{
			energy += value;
		}
		
		return value;
	}
	
	@Override
	public int extractEnergy(int maxExtract, boolean simulate)
	{
		int value = Math.min(maxExtract, energy);
		value = Math.min(value, speed);
		
		if(!simulate)
		{
			this.energy -= value;
		}
		
		return value;
	}
	
	@Override
	public int getEnergyStored()
	{
		return this.energy;
	}

	@Override
	public int getMaxEnergyStored()
	{
		return this.capacity;
	}
	
	public boolean canReceive()
	{
		return this.canFill;
	}
	
	public boolean canExtract()
	{
		return this.canDrain;
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound tag)
	{
		tag.setInteger("Capacity", capacity);
		tag.setInteger("Charge", energy);
		tag.setInteger("Speed", speed);
		return tag;
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		this.capacity = !tag.hasKey("Capacity")? 32000 : tag.getInteger("Capacity");
		this.energy = tag.getInteger("Charge");
		this.speed = tag.getInteger("Speed");
	}
}
