package untouchedwagons.minecraft.plasmacraft.client.gui;

import java.util.Collections;
import java.util.List;

import untouchedwagons.minecraft.plasmacraft.PlasmaCraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import untouchedwagons.minecraft.plasmacraft.items.PCItems;

public class PlasmaTab extends CreativeTabs
{
	public PlasmaTab(String j)
	{
		super(j);
	}

	@Override
	public Item getTabIconItem()
	{
		return PlasmaCraft.items.plasma;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void displayAllReleventItems(List list)
	{
		super.displayAllReleventItems(list);
		Collections.sort(list, PlasmaCraft.tabSorter);
	}
}
