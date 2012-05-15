package net.minecraft.src.Plasmacraft;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraft.src.forge.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if(!world.blockExists(x, y, z))
		        return null;
		
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		
		switch(ID)
		{
		case GuiIds.PLASMIFICATOR:
			if(!(tile instanceof TilePlasmaBench))
				return null;
			return new ContainerPlasmaBench(player.inventory, (TilePlasmaBench)tile);
		
		default:
			return null;
		}
	}
}