package com.malkierian.Plasmacraft.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.malkierian.Plasmacraft.common.ContainerPlasmaBench;
import com.malkierian.Plasmacraft.common.GuiIds;
import com.malkierian.Plasmacraft.common.TilePlasmaBench;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		
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
		TileEntity tile = world.getTileEntity(x, y, z);
		
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