package com.malkierian.plasmacraft.core.items;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.malkierian.plasmacraft.core.PlasmaCraft;
import com.malkierian.plasmacraft.core.entities.EntityAcid;
import com.malkierian.plasmacraft.core.entities.EntityCryoBlast;
import com.malkierian.plasmacraft.core.entities.EntityLaser;
import com.malkierian.plasmacraft.core.entities.EntityLaserShotgun;
import com.malkierian.plasmacraft.core.entities.EntityPlasma;
import com.malkierian.plasmacraft.core.entities.EntityRailGun;

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
		Entity spawnEntity = null;
		Item costItem = null;
		Item returnItem = null;
		String sound = "plasmacraft:weapon.lasergun";
		boolean isCreative = entityplayer.capabilities.isCreativeMode;
		boolean fire = false;
		
		if(this == PlasmaCraft.lasergun)
		{
			spawnEntity = new EntityLaser(world, entityplayer, 9);
			costItem = PlasmaCraft.energyCell;
		}
		if(this == PlasmaCraft.lasergunsplit)
		{
			spawnEntity = new EntityLaser(world, entityplayer, 11);
			costItem = PlasmaCraft.energyCell;
		}
		if(this == PlasmaCraft.plasmagun)
		{
			spawnEntity = new EntityPlasma(world, entityplayer, 12);
			costItem = PlasmaCraft.batteryPlasma;
			returnItem = PlasmaCraft.batteryEmpty;
		}
		if(this == PlasmaCraft.plasmagunsplit)
		{
			spawnEntity = new EntityPlasma(world, entityplayer, 14);
			costItem = PlasmaCraft.batteryPlasma;
			returnItem = PlasmaCraft.batteryEmpty;
		}
		if(this == PlasmaCraft.acidgun)
		{
			spawnEntity = new EntityAcid(world, entityplayer);
			costItem = PlasmaCraft.acidVial;
			returnItem = PlasmaCraft.causticVial;
			sound = "random.bow";
		}
		if(this == PlasmaCraft.railgun)
		{
			spawnEntity = new EntityRailGun(world, entityplayer);
			costItem = PlasmaCraft.batteryOvercharged;
			returnItem = PlasmaCraft.batteryEmpty;
			sound = "plasmacraft:weapon.railgun";
		}
		if(this == PlasmaCraft.lasershotgun)
		{
			spawnEntity = new EntityLaserShotgun(world, entityplayer);
			costItem = PlasmaCraft.energyCell;
		}
		if(this == PlasmaCraft.cryoblaster)
		{
			spawnEntity = new EntityCryoBlast(world, entityplayer);
			costItem = PlasmaCraft.batteryCryo;
			returnItem = PlasmaCraft.batteryEmpty;
			sound = "plasmacraft:weapon.railgun";
		}
		if(spawnEntity != null)
		{
			if(!world.isRemote)
			{
				if(!isCreative)
					if(entityplayer.inventory.consumeInventoryItem(costItem))
					{
						itemstack.damageItem(1, entityplayer);
						if(returnItem != null)
						{
							int j = random.nextInt(3);
							if(j == 1)
							{
								entityplayer.inventory.addItemStackToInventory(new ItemStack(returnItem, 1));
							}
						}
						fire = true;
					}
					else
						return itemstack;
				else
					fire = true;
				if(fire)
				{
					world.playSoundAtEntity(entityplayer, sound, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
					world.spawnEntityInWorld(spawnEntity);
				}
			}
		}
		return itemstack;
	}
}
