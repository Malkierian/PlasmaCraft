package com.malkierian.plasmacraft.common.items;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.malkierian.plasmacraft.common.PlasmaCraft;
import com.malkierian.plasmacraft.common.Entities.EntityAcid;
import com.malkierian.plasmacraft.common.Entities.EntityCryoBlast;
import com.malkierian.plasmacraft.common.Entities.EntityLaser;
import com.malkierian.plasmacraft.common.Entities.EntityLaserShotgun;
import com.malkierian.plasmacraft.common.Entities.EntityPlasma;
import com.malkierian.plasmacraft.common.Entities.EntityRailGun;

public class ItemEnergyWeapon extends ItemPlasma
{
	Random random;

	public ItemEnergyWeapon(int i)
	{
		super();
		random = new Random();
		setMaxDamage(i);
		maxStackSize = 1;
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		if(this == PlasmaCraft.lasergun && entityplayer.inventory.consumeInventoryItem(PlasmaCraft.energyCell))
		{
			world.playSoundAtEntity(entityplayer, "plasmacraft.lasergun", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if(!world.isRemote)
			{
				itemstack.damageItem(1, entityplayer);
				world.spawnEntityInWorld(new EntityLaser(world, entityplayer, 9));
			}
		}
		if(this == PlasmaCraft.lasergunsplit && entityplayer.inventory.consumeInventoryItem(PlasmaCraft.energyCell))
		{
			itemstack.damageItem(1, entityplayer);
			world.playSoundAtEntity(entityplayer, "plasmacraft.lasergun", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
			for(int i = 0; i < 3; i++)
			{
				if(!world.isRemote)
				{
					world.spawnEntityInWorld(new EntityLaser(world, entityplayer, 11));
				}
			}

		}
		if(this == PlasmaCraft.plasmagun && entityplayer.inventory.consumeInventoryItem(PlasmaCraft.batteryPlasma))
		{
			int j = random.nextInt(3);
			if(j == 1)
			{
				entityplayer.inventory.addItemStackToInventory(new ItemStack(PlasmaCraft.batteryEmpty, 1));
			}
			world.playSoundAtEntity(entityplayer, "plasmacraft.lasergun", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if(!world.isRemote)
			{
				itemstack.damageItem(1, entityplayer);
				world.spawnEntityInWorld(new EntityPlasma(world, entityplayer, 12));
			}
		}
		if(this == PlasmaCraft.plasmagunsplit && entityplayer.inventory.consumeInventoryItem(PlasmaCraft.batteryPlasma))
		{
			itemstack.damageItem(1, entityplayer);
			world.playSoundAtEntity(entityplayer, "plasmacraft.lasergun", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
			int k = random.nextInt(3);
			if(k == 1)
			{
				entityplayer.inventory.addItemStackToInventory(new ItemStack(PlasmaCraft.batteryEmpty, 1));
			}
			for(int j1 = 0; j1 < 3; j1++)
			{
				if(!world.isRemote)
				{
					world.spawnEntityInWorld(new EntityPlasma(world, entityplayer, 14));
				}
			}

		}
		if(this == PlasmaCraft.acidgun && entityplayer.inventory.consumeInventoryItem(PlasmaCraft.acidVial))
		{
			entityplayer.inventory.addItemStackToInventory(new ItemStack(PlasmaCraft.causticVial, 1));
			world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if(!world.isRemote)
			{
				itemstack.damageItem(1, entityplayer);
				world.spawnEntityInWorld(new EntityAcid(world, entityplayer));
			}
		}
		if(this == PlasmaCraft.railgun && entityplayer.inventory.consumeInventoryItem(PlasmaCraft.batteryOvercharged))
		{
			int l = random.nextInt(3);
			if(l == 1)
			{
				entityplayer.inventory.addItemStackToInventory(new ItemStack(PlasmaCraft.batteryEmpty, 1));
			}
			world.playSoundAtEntity(entityplayer, "plasmacraft.railgun", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if(!world.isRemote)
			{
				itemstack.damageItem(1, entityplayer);
				world.spawnEntityInWorld(new EntityRailGun(world, entityplayer));
			}
		}
		if(this == PlasmaCraft.lasershotgun && entityplayer.inventory.consumeInventoryItem(PlasmaCraft.energyCell))
		{
			world.playSoundAtEntity(entityplayer, "plasmacraft.lasergun", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
			for(int i1 = 0; i1 < 7; i1++)
			{
				if(!world.isRemote)
				{
					itemstack.damageItem(1, entityplayer);
					world.spawnEntityInWorld(new EntityLaserShotgun(world, entityplayer));
				}
			}

		}
		if(this == PlasmaCraft.cryoblaster && entityplayer.inventory.consumeInventoryItem(PlasmaCraft.batteryCryo))
		{
			world.playSoundAtEntity(entityplayer, "plasmacraft.railgun", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if(!world.isRemote)
			{
				itemstack.damageItem(1, entityplayer);
				world.spawnEntityInWorld(new EntityCryoBlast(world, entityplayer));
			}
		}
		return itemstack;
	}
}
