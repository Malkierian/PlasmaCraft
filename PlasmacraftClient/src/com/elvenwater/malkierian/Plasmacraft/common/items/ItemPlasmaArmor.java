package com.elvenwater.malkierian.Plasmacraft.common.items;

import com.elvenwater.malkierian.Plasmacraft.common.CommonProxy;
import com.elvenwater.malkierian.Plasmacraft.common.PlasmaCraft;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class ItemPlasmaArmor extends ItemArmor implements IArmorTextureProvider
{
	public ItemPlasmaArmor(int i, EnumArmorMaterial enumarmormaterial, int j, int k)
	{
		super(i, enumarmormaterial, j, k);
	}

	@Override
	public String getTextureFile()
	{
		return CommonProxy.ITEMS_PNG;
	}

	@Override
	public String getArmorTextureFile(ItemStack itemstack)
	{
		if(itemstack.itemID == PlasmaCraft.hazmatHood.itemID
				|| itemstack.itemID == PlasmaCraft.hazmatJacket.itemID
				|| itemstack.itemID == PlasmaCraft.hazmatBoots.itemID)
		{
			return "/PlasmaCraftSprites/armor/hazmat_1.png";
		}
		else if(itemstack.itemID == PlasmaCraft.hazmatPants.itemID)
		{
			return "/PlasmaCraftSprites/armor/hazmat_2.png";
		}
		return null;
	}
}
