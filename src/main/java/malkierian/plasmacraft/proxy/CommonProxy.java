package malkierian.plasmacraft.proxy;

import malkierian.plasmacraft.init.PCFluids;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;

public class CommonProxy
{	
	public void registerRenderers()
	{
		
	}

	public boolean getEntityInstanceOf(Entity entity)
	{
		return false;
	}
	
	public void registerItemRenderer(Item item, int meta, String id)
	{
		
	}
	
	public void preInit()
	{
		
	}
	
	public void registerTextureFX()
	{
		
	}

	public static boolean shouldSideBeRendered(IBlockAccess iblockaccess,
			int i, int j, int k, int l)
	{
		return false;
	}

	public void registerEntities()
	{
		
	}

	public int addArmor(String name)
	{
		return 0;
	}
}
