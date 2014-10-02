package com.malkierian.plasmacraft.common;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

public class PlasmaTab extends CreativeTabs
{
	public PlasmaTab(String j)
	{
		super(j);
	}

	@Override
	public Item getTabIconItem()
	{
		return PlasmaCraft.plasma;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void displayAllReleventItems(List list)
	{
		Comparator<ItemStack> c = Ordering.explicit(list).onResultOf(new Function<ItemStack, Item>(){
			@Override
			public Item apply(ItemStack input)
			{
				return input.getItem();
			}
		});
		Collections.sort(list, c);
		super.displayAllReleventItems(list);
	}
}
