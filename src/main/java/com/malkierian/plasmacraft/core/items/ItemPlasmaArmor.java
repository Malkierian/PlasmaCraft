package com.malkierian.plasmacraft.core.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemArmor;

import com.malkierian.plasmacraft.core.PlasmaCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPlasmaArmor extends ItemArmor
{
	public ItemPlasmaArmor(ArmorMaterial armormaterial, int j, int k)
	{
		super(armormaterial, j, k);
		setCreativeTab(PlasmaCraft.plasmaTab);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon(PlasmaCraft.MOD_ID + ":" + getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1));
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
