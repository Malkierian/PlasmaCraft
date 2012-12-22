package com.elvenwater.malkierian.Plasmacraft.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemPlasma extends Item
{
    public ItemPlasma(int i)
    {
        super(i);
        setCreativeTab(CreativeTabs.tabMisc);
    }

	@Override
	public String getTextureFile()
	{
		return CommonProxy.ITEMS_PNG;
	}
}
