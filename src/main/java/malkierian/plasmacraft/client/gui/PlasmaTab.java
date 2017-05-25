package malkierian.plasmacraft.client.gui;

import java.util.Collections;
import java.util.List;

import malkierian.plasmacraft.PlasmaCraft;
import malkierian.plasmacraft.init.PCItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PlasmaTab extends CreativeTabs
{
	public PlasmaTab(String j)
	{
		super(j);
	}

	@Override
	public Item getTabIconItem()
	{
		return PCItems.vial;
	}

	@Override
	public void displayAllRelevantItems(List<ItemStack> list)
	{
		super.displayAllRelevantItems(list);
		Collections.sort(list, PlasmaCraft.tabSorter);
	}
}
