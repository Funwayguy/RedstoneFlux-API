package cofh.api.energy.capability.templates;

import cofh.api.energy.capability.CapabilityEnergyHandler;
import cofh.api.energy.capability.EnergyStorage;
import cofh.api.energy.capability.IEnergyHandler;
import cofh.api.energy.capability.IEnergyStorageProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class EnergyHandlerItemStack implements ICapabilityProvider, IEnergyHandler
{
	EnergyStorage storage;
	ItemStack stack;
	
	public EnergyHandlerItemStack(ItemStack stack, EnergyStorage storage)
	{
		this.storage = storage;
		this.stack = stack;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if(capability == CapabilityEnergyHandler.ENERGY_HANDLER_CAPABILITY)
		{
			return storage != null;
		}
		
		return false;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if(capability == CapabilityEnergyHandler.ENERGY_HANDLER_CAPABILITY)
		{
			return (T)storage;
		}
		
		return null;
	}
	
	@Override
	public IEnergyStorageProperties[] getStorageProperties()
	{
		return storage.getStorageProperties();
	}
	
	@Override
	public int receiveEnergy(int maxReceive, boolean simulate)
	{
		readNBT();
		
		int value = storage.receiveEnergy(maxReceive, simulate);
		
		if(!simulate)
		{
			writeNBT();
		}
		
		return value;
	}
	
	@Override
	public int extractEnergy(int maxExtract, boolean simulate)
	{
		readNBT();
		
		int value = storage.extractEnergy(maxExtract, simulate);
		
		if(!simulate)
		{
			writeNBT();
		}
		
		return value;
	}
	
	public void readNBT()
	{
		NBTTagCompound tag = stack.getTagCompound();
		tag = tag != null? tag : new NBTTagCompound();
		storage.readFromNBT(tag.getCompoundTag("Energy"));
	}
	
	public void writeNBT()
	{
		NBTTagCompound tag = stack.getTagCompound();
		tag = tag != null? tag : new NBTTagCompound();
		tag.setTag("Energy", storage.writeToNBT(new NBTTagCompound()));
		stack.setTagCompound(tag);
	}
}
