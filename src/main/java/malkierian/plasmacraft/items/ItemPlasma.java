package malkierian.plasmacraft.items;

import malkierian.plasmacraft.PlasmaCraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemPlasma extends Item
{
	protected String name;
	
	public ItemPlasma()
	{
		setCreativeTab(PlasmaCraft.plasmaTab);
	}
	
	public ItemPlasma(String name)
	{
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(PlasmaCraft.plasmaTab);
	}
	
	public void registerItemModel(Item item)
	{
		PlasmaCraft.proxy.registerItemRenderer(this, 0, name);
	}
}
