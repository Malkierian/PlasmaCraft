package malkierian.plasmacraft.common;

import malkierian.plasmacraft.PlasmaCraft;
import malkierian.plasmacraft.init.PCItems;
import malkierian.plasmacraft.items.ItemVial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler
{
    private ItemStack nether_flow_vial = new ItemStack(PCItems.VIALS, 1, ItemVial.NETHERFLOW_DAMAGE);

	@Override
	public int getBurnTime(ItemStack fuel)
	{
		if(fuel.isItemEqual(nether_flow_vial))
		{
			return 500000;
		}

		if (fuel.getItem() == PCItems.THERMOPELLET)
        {
            return 1000000;
        }

		return 0;
	}
}
