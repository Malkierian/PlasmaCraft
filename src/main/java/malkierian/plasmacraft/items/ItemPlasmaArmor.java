package malkierian.plasmacraft.items;

import malkierian.plasmacraft.PlasmaCraft;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPlasmaArmor extends ItemArmor
{
	public ItemPlasmaArmor(ArmorMaterial armormaterial, int j, EntityEquipmentSlot slot)
	{
		super(armormaterial, j, slot);
//		setCreativeTab(PlasmaCraft.plasmaTab);
	}
	
//	@Override
//	@SideOnly(Side.CLIENT)
//	public void registerIcons(IIconRegister iconRegister)
//	{
//		itemIcon = iconRegister.registerIcon(PlasmaCraft.MOD_ID + ":" + getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1));
//	}
}
