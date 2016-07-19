package cofh.api.energy.capability.wrappers;

import net.minecraft.item.ItemStack;
import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.capability.EnergyStorageProperties;
import cofh.api.energy.capability.IEnergyHandler;
import cofh.api.energy.capability.IEnergyStorageProperties;

@SuppressWarnings("deprecation")
public class ItemEnergyHandlerWrapper implements IEnergyHandler
{
	IEnergyContainerItem container;
	ItemStack stack;
	
	public ItemEnergyHandlerWrapper(IEnergyContainerItem container, ItemStack stack)
	{
		this.container = container;
	}
	
	@Override
	public IEnergyStorageProperties[] getStorageProperties()
	{
		return new IEnergyStorageProperties[]{new EnergyStorageProperties(container.getEnergyStored(stack), container.getMaxEnergyStored(stack))};
	}
	
	@Override
	public int receiveEnergy(int maxReceive, boolean simulate)
	{
		return container.receiveEnergy(stack, maxReceive, simulate);
	}
	
	@Override
	public int extractEnergy(int maxExtract, boolean simulate)
	{
		return container.extractEnergy(stack, maxExtract, simulate);
	}
}
