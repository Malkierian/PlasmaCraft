package com.elvenwater.malkierian.Plasmacraft.common.items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import com.elvenwater.malkierian.Plasmacraft.common.PlasmaCraft;

public class ItemGlowCloth extends ItemBlock
{
	public static String[] blockNames = {
		"acid",
		"radionite",
		"netherflow",
		"neptunium",
		"uranium",
		"plutonium",
		"cryonite",
		"obsidium"
		};

	public ItemGlowCloth(int id)
	{
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public Icon getIconFromDamage(int i)
	{
		return PlasmaCraft.glowCloth.getBlockTextureFromSideAndMetadata(0, i);
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