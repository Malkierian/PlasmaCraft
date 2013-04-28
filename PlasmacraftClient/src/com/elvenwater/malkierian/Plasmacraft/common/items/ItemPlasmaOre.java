package com.elvenwater.malkierian.Plasmacraft.common.items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import com.elvenwater.malkierian.Plasmacraft.common.CommonProxy;
import com.elvenwater.malkierian.Plasmacraft.common.PlasmaCraft;

public class ItemPlasmaOre extends ItemBlock
{
	public static String[] blockNames = {
		"plutonium",
		"radionite",
		"neptunium",
		"obsidium",
		"uranium"
		};

	public ItemPlasmaOre(int id)
	{
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public Icon getIconFromDamage(int i)
	{
		return PlasmaCraft.orePlasma.getIcon(0, i);
	}
	
	@Override
	public int getMetadata(int damage)
	{
		return damage;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		return super.getUnlocalizedName() + "." + blockNames[itemstack.getItemDamage()];
	}	
}