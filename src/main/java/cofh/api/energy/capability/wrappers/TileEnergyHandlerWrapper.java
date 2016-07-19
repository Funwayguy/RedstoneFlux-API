package cofh.api.energy.capability.wrappers;

import net.minecraft.util.EnumFacing;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.capability.EnergyStorageProperties;
import cofh.api.energy.capability.IEnergyHandler;
import cofh.api.energy.capability.IEnergyStorageProperties;

@SuppressWarnings("deprecation")
public class TileEnergyHandlerWrapper implements IEnergyHandler
{
	EnumFacing side;
	cofh.api.energy.IEnergyHandler handler;
	IEnergyProvider provider;
	IEnergyReceiver receiver;
	
	public TileEnergyHandlerWrapper(cofh.api.energy.IEnergyHandler handler, EnumFacing side)
	{
		this.handler = handler;
		
		if(handler instanceof IEnergyProvider)
		{
			provider = (IEnergyProvider)handler;
		}
		
		if(handler instanceof IEnergyReceiver)
		{
			receiver = (IEnergyReceiver)handler;
		}
	}
	
	@Override
	public IEnergyStorageProperties[] getStorageProperties()
	{
		return new IEnergyStorageProperties[]{new EnergyStorageProperties(handler.getEnergyStored(side), handler.getMaxEnergyStored(side))};
	}
	
	@Override
	public int receiveEnergy(int maxReceive, boolean simulate)
	{
		if(receiver == null)
		{
			return 0;
		}
		
		return receiver.receiveEnergy(side, maxReceive, simulate);
	}
	
	@Override
	public int extractEnergy(int maxExtract, boolean simulate)
	{
		if(provider == null)
		{
			return 0;
		}
		
		return provider.extractEnergy(side, maxExtract, simulate);
	}
}
