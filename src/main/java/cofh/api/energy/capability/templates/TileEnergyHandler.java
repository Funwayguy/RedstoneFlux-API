package cofh.api.energy.capability.templates;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import cofh.api.energy.capability.CapabilityEnergyHandler;
import cofh.api.energy.capability.EnergyStorage;

public class TileEnergyHandler extends TileEntity
{
	EnergyStorage storage = new EnergyStorage(32000);
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		storage.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		storage.readFromNBT(tag);
	}
	
	@Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
		if(capability == CapabilityEnergyHandler.ENERGY_HANDLER_CAPABILITY)
		{
			return storage != null;
		}
		
        return super.hasCapability(capability, facing);
    }
    
	@Override
	@SuppressWarnings("unchecked")
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
		if(capability == CapabilityEnergyHandler.ENERGY_HANDLER_CAPABILITY)
		{
			return (T)storage;
		}
		
        return super.getCapability(capability, facing);
    }
}
