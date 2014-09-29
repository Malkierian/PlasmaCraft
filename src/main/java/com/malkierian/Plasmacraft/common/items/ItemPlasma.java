package com.malkierian.Plasmacraft.common.items;

import com.malkierian.Plasmacraft.common.PlasmaCraft;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemPlasma extends Item
{
	public ItemPlasma()
	{
		super();
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon(PlasmaCraft.MOD_ID + ":" + getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1));
	}
}
