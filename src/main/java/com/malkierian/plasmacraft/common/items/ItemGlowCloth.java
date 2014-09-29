package com.malkierian.plasmacraft.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.malkierian.plasmacraft.common.PlasmaCraft;

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

	public ItemGlowCloth(Block block)
	{
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public IIcon getIconFromDamage(int i)
	{
		return PlasmaCraft.glowCloth.getIcon(0, i);
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