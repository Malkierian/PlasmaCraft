package com.elvenwater.malkierian.Plasmacraft.common.items;

import com.elvenwater.malkierian.Plasmacraft.common.CommonProxy;
import com.elvenwater.malkierian.Plasmacraft.common.PlasmaCraft;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemPlasmaOre extends ItemBlock
{
	public static String[] blockNames = {
		"Plutonium Ore",
		"Radionite Ore",
		"Neptunium Ore",
		"Obsidium Ore",
		"Uranium Ore"
		};

	public ItemPlasmaOre(int id)
	{
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	public int getIconFromDamage(int i)
	{
		return PlasmaCraft.orePlasma.getBlockTextureFromSideAndMetadata(0, i);
	}
	
	@Override
	public int getMetadata(int damage)
	{
		return damage;
	}
	
	@Override
	public String getItemNameIS(ItemStack itemstack)
	{
		return getItemName() + "." + blockNames[itemstack.getItemDamage()];
	}

	public String getTextureFile() 
	{
		return CommonProxy.BLOCK_PNG;
	}
	
}