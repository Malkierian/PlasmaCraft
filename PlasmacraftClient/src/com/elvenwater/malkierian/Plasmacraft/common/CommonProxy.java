package com.elvenwater.malkierian.Plasmacraft.common;

import net.minecraft.entity.Entity;
import net.minecraft.world.IBlockAccess;

public class CommonProxy
{
	public static String ITEMS_PNG = "/PlasmaCraftSprites/items0.png";
	public static String BLOCK_PNG = "/PlasmaCraftSprites/terrain0.png";
	public static String LIQUID_PNG = "/PlasmaCraftSprites/terrain1.png";
	
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
