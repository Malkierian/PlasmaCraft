package com.malkierian.Plasmacraft.common.items;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import com.malkierian.Plasmacraft.common.PlasmaCraft;

public class ItemPlasmaArmor extends ItemArmor
{
	public ItemPlasmaArmor(int i, ArmorMaterial armormaterial, int j, int k)
	{
		super(armormaterial, j, k);
	}

//	@Override
//	public String getArmorTextureFile(ItemStack itemstack)
//	{
//		if(itemstack.itemID == PlasmaCraft.hazmatHood.itemID
//				|| itemstack.itemID == PlasmaCraft.hazmatJacket.itemID
//				|| itemstack.itemID == PlasmaCraft.hazmatBoots.itemID)
//		{
//			return "/PlasmaCraftSprites/armor/hazmat_1.png";
//		}
//		else if(itemstack.itemID == PlasmaCraft.hazmatPants.itemID)
//		{
//			return "/PlasmaCraftSprites/armor/hazmat_2.png";
//		}
//		return null;
//	}
}
