package cofh.api.energy.capability;

import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.capability.wrappers.ItemEnergyHandlerWrapper;
import cofh.api.energy.capability.wrappers.TileEnergyHandlerWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class EnergyUtil
{
	public static IEnergyHandler getEnergyHandler(World world, BlockPos pos, EnumFacing side)
	{
		TileEntity tile = world.getTileEntity(pos);
		
		if(tile.hasCapability(CapabilityEnergyHandler.ENERGY_HANDLER_CAPABILITY, side))
		{
			return tile.getCapability(CapabilityEnergyHandler.ENERGY_HANDLER_CAPABILITY, side);
		} else if(tile instanceof cofh.api.energy.IEnergyHandler)
		{
			cofh.api.energy.IEnergyHandler handler = (cofh.api.energy.IEnergyHandler)tile;
			
			if(handler.canConnectEnergy(side))
			{
				return new TileEnergyHandlerWrapper(handler, side);
			}
		}
		
		return null;
	}
	
	public static IEnergyHandler getEnergyHandler(ItemStack stack)
	{
		if(stack == null)
		{
			return null;
		} else if(stack.hasCapability(CapabilityEnergyHandler.ENERGY_HANDLER_CAPABILITY, null))
		{
			return stack.getCapability(CapabilityEnergyHandler.ENERGY_HANDLER_CAPABILITY, null);
		} else if(stack != null && stack.getItem() instanceof IEnergyContainerItem)
		{
			return new ItemEnergyHandlerWrapper((IEnergyContainerItem)stack.getItem(), stack);
		}
		
		return null;
	}
	
	public static int transferEnergy(IEnergyHandler source, IEnergyHandler destination, int maxAmount, boolean simulate)
	{
		int out = source.extractEnergy(maxAmount, true);
		int in = destination.receiveEnergy(maxAmount, true);
		int value = Math.min(out, in);
		
		if(!simulate && out > 0)
		{
			source.extractEnergy(value, false);
			destination.receiveEnergy(value, false);
		}
		
		return value;
	}
}
