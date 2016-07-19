package cofh.api.energy.capability;

public class EnergyStorageProperties implements IEnergyStorageProperties
{
	private final int contents;
	private final int capacity;
	private final boolean canDrain;
	private final boolean canFill;
	
	public EnergyStorageProperties(int contents, int capacity)
	{
		this(contents, capacity, true, true);
	}
	
	public EnergyStorageProperties(int contents, int capacity, boolean canFill, boolean canDrain)
	{
		this.contents = contents;
		this.capacity = capacity;
		this.canFill = canFill;
		this.canDrain = canDrain;
	}
	
	@Override
	public int getEnergyStored()
	{
		return contents;
	}
	
	@Override
	public int getEnergyCapacity()
	{
		return capacity;
	}
	
	@Override
	public boolean canReceive()
	{
		return canFill;
	}
	
	@Override
	public boolean canExtract()
	{
		return canDrain;
	}
}
