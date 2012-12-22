package com.elvenwater.malkierian.Plasmacraft.common;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel)
	{
        if(fuel.itemID == PlasmaCraft.netherflowVial.shiftedIndex)
        {
            return 0x7a120;
        }
        return fuel.itemID != PlasmaCraft.thermoPellet.shiftedIndex ? 0 : 0xf4240;
	}

}
