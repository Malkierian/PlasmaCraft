package untouchedwagons.minecraft.plasmacraft.proxy;

import net.minecraft.entity.Entity;
import net.minecraft.world.IBlockAccess;

public class CommonProxy
{
	// Client stuff
	public void registerRenderers()
	{
		// Nothing here as this is the server side proxy
	}

	public boolean getEntityInstanceOf(Entity entity)
	{
		// TODO Auto-generated method stub
		return false;
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
