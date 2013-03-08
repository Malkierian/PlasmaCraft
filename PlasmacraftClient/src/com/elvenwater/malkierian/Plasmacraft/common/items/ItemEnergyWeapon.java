package com.elvenwater.malkierian.Plasmacraft.common.items;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.elvenwater.malkierian.Plasmacraft.common.PlasmaCraft;
import com.elvenwater.malkierian.Plasmacraft.common.Entities.EntityAcid;
import com.elvenwater.malkierian.Plasmacraft.common.Entities.EntityCryoBlast;
import com.elvenwater.malkierian.Plasmacraft.common.Entities.EntityLaser;
import com.elvenwater.malkierian.Plasmacraft.common.Entities.EntityLaserShotgun;
import com.elvenwater.malkierian.Plasmacraft.common.Entities.EntityPlasma;
import com.elvenwater.malkierian.Plasmacraft.common.Entities.EntityRailGun;

public class ItemEnergyWeapon extends ItemPlasma
{
	Random random;

	public ItemEnergyWeapon(int i, int j)
	{
		super(i);
		random = new Random();
		setMaxDamage(j);
		maxStackSize = 1;
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		if(itemID == PlasmaCraft.lasergun.itemID && entityplayer.inventory.consumeInventoryItem(PlasmaCraft.energyCell.itemID))
		{
			world.playSoundAtEntity(entityplayer, "plasmacraft.lasergun", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if(!world.isRemote)
			{
				itemstack.damageItem(1, entityplayer);
				world.spawnEntityInWorld(new EntityLaser(world, entityplayer, 9));
			}
		}
		if(itemID == PlasmaCraft.lasergunsplit.itemID && entityplayer.inventory.consumeInventoryItem(PlasmaCraft.energyCell.itemID))
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
		if(itemID == PlasmaCraft.plasmagun.itemID && entityplayer.inventory.consumeInventoryItem(PlasmaCraft.batteryPlasma.itemID))
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
		if(itemID == PlasmaCraft.plasmagunsplit.itemID && entityplayer.inventory.consumeInventoryItem(PlasmaCraft.batteryPlasma.itemID))
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
		if(itemID == PlasmaCraft.acidgun.itemID && entityplayer.inventory.consumeInventoryItem(PlasmaCraft.acidVial.itemID))
		{
			entityplayer.inventory.addItemStackToInventory(new ItemStack(PlasmaCraft.causticVial, 1));
			world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if(!world.isRemote)
			{
				itemstack.damageItem(1, entityplayer);
				world.spawnEntityInWorld(new EntityAcid(world, entityplayer));
			}
		}
		if(itemID == PlasmaCraft.railgun.itemID && entityplayer.inventory.consumeInventoryItem(PlasmaCraft.batteryOverCharged.itemID))
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
		if(itemID == PlasmaCraft.lasershotgun.itemID && entityplayer.inventory.consumeInventoryItem(PlasmaCraft.energyCell.itemID))
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
		if(itemID == PlasmaCraft.cryoblaster.itemID && entityplayer.inventory.consumeInventoryItem(PlasmaCraft.batteryCryo.itemID))
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
