package untouchedwagons.minecraft.plasmacraft.common;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;
import untouchedwagons.minecraft.plasmacraft.PlasmaCraft;
import untouchedwagons.minecraft.plasmacraft.items.ItemVial;
import untouchedwagons.minecraft.plasmacraft.items.PCItems;

public class FuelHandler implements IFuelHandler
{
    private ItemStack nether_flow_vial = new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.NETHERFLOW_DAMAGE);

	@Override
	public int getBurnTime(ItemStack fuel)
	{
		if(fuel.isItemEqual(nether_flow_vial))
		{
			return 500000;
		}
		return fuel.getItem() != PlasmaCraft.items.thermopellet ? 0 : 1000000;
	}
}
