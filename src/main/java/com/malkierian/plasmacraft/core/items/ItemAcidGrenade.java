package com.malkierian.plasmacraft.core.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.malkierian.plasmacraft.core.entities.EntityAcidGrenade;

public class ItemAcidGrenade extends ItemPlasma
{
	public ItemAcidGrenade()
	{
		super();
		maxStackSize = 16;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if(!world.isRemote)
		{
			world.spawnEntityInWorld(new EntityAcidGrenade(world, entityplayer));
		}
		itemstack.stackSize--;
		return itemstack;
	}
}
