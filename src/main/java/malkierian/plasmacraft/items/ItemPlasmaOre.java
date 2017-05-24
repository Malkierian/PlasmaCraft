package malkierian.plasmacraft.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemPlasmaOre extends ItemBlock
{
	public static String[] blockNames = {
		"plutonium",
		"radionite",
		"neptunium",
		"obsidium",
		"uranium",
		"lead"
		};

	public ItemPlasmaOre(Block block)
	{
		super(block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

//	@Override
//	public IIcon getIconFromDamage(int i)
//	{
//		return PlasmaCraft.blocks.orePlasma.getIcon(0, i);
//	}
	
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