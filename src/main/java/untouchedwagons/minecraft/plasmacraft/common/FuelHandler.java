package untouchedwagons.minecraft.plasmacraft.common;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;
import untouchedwagons.minecraft.plasmacraft.PlasmaCraft;
import untouchedwagons.minecraft.plasmacraft.items.PCItems;

public class FuelHandler implements IFuelHandler
{
	@Override
	public int getBurnTime(ItemStack fuel)
	{
		if(fuel.getItem() == PlasmaCraft.items.netherflowVial)
		{
			return 500000;
		}
		return fuel.getItem() != PlasmaCraft.items.thermopellet ? 0 : 1000000;
	}
}
