package com.elvenwater.malkierian.Plasmacraft.client;

import com.elvenwater.malkierian.Plasmacraft.common.ContainerPlasmaBench;
import com.elvenwater.malkierian.Plasmacraft.common.GuiIds;
import com.elvenwater.malkierian.Plasmacraft.common.TilePlasmaBench;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z)
	{
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		
		if(tile == null)
			return null;
		
		switch(ID)
		{
		case GuiIds.PLASMIFICATOR:
			return new ContainerPlasmaBench(player.inventory, (TilePlasmaBench)tile);
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z)
	{
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		
		if(tile == null)
			return null;
		
		switch(ID)
		{
		case GuiIds.PLASMIFICATOR:
			return new GuiPlasmaBench(player.inventory, (TilePlasmaBench)tile);
		default:
			return null;
		}
	}
}