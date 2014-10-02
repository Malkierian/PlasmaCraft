package com.malkierian.plasmacraft.common;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel)
	{
		if(fuel.getItem() == PlasmaCraft.netherflowVial)
		{
			return 0x7a120;
		}
		return fuel.getItem() != PlasmaCraft.thermopellet ? 0 : 0xf4240;
	}

}
