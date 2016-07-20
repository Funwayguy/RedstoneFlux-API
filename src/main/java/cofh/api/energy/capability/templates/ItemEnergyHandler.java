package cofh.api.energy.capability.templates;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import cofh.api.energy.capability.EnergyStorage;

public class ItemEnergyHandler extends Item
{
	private int initCharge;
	private int capacity;
	private int speed;
	private boolean canFill = true;
	private boolean canDrain = true;
	
	public ItemEnergyHandler(int capacity)
	{
		this.capacity = capacity;
	}
	
	public ItemEnergyHandler setInitCharge(int charge)
	{
		this.initCharge = charge;
		return this;
	}
	
	public ItemEnergyHandler setTransferRate(int speed)
	{
		this.speed = speed;
		return this;
	}
	
	public ItemEnergyHandler setCanRecieve(boolean canFill)
	{
		this.canFill = canFill;
		return this;
	}
	
	public ItemEnergyHandler setCanExtract(boolean canDrain)
	{
		this.canDrain = canDrain;
		return this;
	}
	
	@Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt)
    {
    	return new EnergyHandlerItemStack(stack, new EnergyStorage(capacity, initCharge, speed, canFill, canDrain));
    }
}
