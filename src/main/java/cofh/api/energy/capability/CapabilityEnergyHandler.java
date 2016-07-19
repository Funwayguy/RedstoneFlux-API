package cofh.api.energy.capability;

import java.util.concurrent.Callable;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityEnergyHandler
{
	@CapabilityInject(IEnergyHandler.class)
	public static Capability<IEnergyHandler> ENERGY_HANDLER_CAPABILITY = null;
	
	static
	{
		CapabilityManager.INSTANCE.register(IEnergyHandler.class, new Capability.IStorage<IEnergyHandler>()
		{
			@Override
			public NBTBase writeNBT(Capability<IEnergyHandler> capability, IEnergyHandler instance, EnumFacing side)
			{
				if(!(instance instanceof EnergyStorage))
				{
					throw new RuntimeException("IEnergyHandler instance does not extend EnergyStorage");
				}
				
				NBTTagCompound nbt = new NBTTagCompound();
				EnergyStorage storage = (EnergyStorage)instance;
				storage.writeToNBT(nbt);
				return nbt;
			}
			
			@Override
			public void readNBT(Capability<IEnergyHandler> capability, IEnergyHandler instance, EnumFacing side, NBTBase nbt)
			{
				if(!(instance instanceof EnergyStorage))
				{
					throw new RuntimeException("IEnergyHandler instance does not extend EnergyStorage");
				}
				
				EnergyStorage storage = (EnergyStorage)instance;
				storage.readFromNBT((NBTTagCompound)nbt);
			}
		}, new Callable<IEnergyHandler>()
		{
			@Override
			public IEnergyHandler call() throws Exception
			{
				return new EnergyStorage(32000);
			}
		});
	}
}
