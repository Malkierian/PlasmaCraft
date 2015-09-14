package untouchedwagons.minecraft.plasmacraft.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemArmor;

import untouchedwagons.minecraft.plasmacraft.PlasmaCraft;

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
}
