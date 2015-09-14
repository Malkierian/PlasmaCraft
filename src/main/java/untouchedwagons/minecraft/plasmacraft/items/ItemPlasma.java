package untouchedwagons.minecraft.plasmacraft.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import untouchedwagons.minecraft.plasmacraft.PlasmaCraft;

public class ItemPlasma extends Item
{
	public ItemPlasma()
	{
		super();
		setCreativeTab(PlasmaCraft.plasmaTab);
	}
	
	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon(PlasmaCraft.MOD_ID + ":" + getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1));
	}
}
