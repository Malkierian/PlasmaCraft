package untouchedwagons.minecraft.plasmacraft.items;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import untouchedwagons.minecraft.plasmacraft.PlasmaCraft;
import untouchedwagons.minecraft.plasmacraft.entities.EntityAcid;
import untouchedwagons.minecraft.plasmacraft.entities.EntityCryoBlast;
import untouchedwagons.minecraft.plasmacraft.entities.EntityLaser;
import untouchedwagons.minecraft.plasmacraft.entities.EntityLaserShotgun;
import untouchedwagons.minecraft.plasmacraft.entities.EntityPlasma;
import untouchedwagons.minecraft.plasmacraft.entities.EntityRailGun;

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
		
		if(this == PlasmaCraft.items.lasergun)
		{
			spawnEntity = new EntityLaser(world, entityplayer, 9);
			costItem = PlasmaCraft.items.energyCell;
		}
		if(this == PlasmaCraft.items.lasergunsplit)
		{
			spawnEntity = new EntityLaser(world, entityplayer, 11);
			costItem = PlasmaCraft.items.energyCell;
		}
		if(this == PlasmaCraft.items.plasmagun)
		{
			spawnEntity = new EntityPlasma(world, entityplayer, 12);
			costItem = PlasmaCraft.items.batteryPlasma;
			returnItem = PlasmaCraft.items.batteryEmpty;
		}
		if(this == PlasmaCraft.items.plasmagunsplit)
		{
			spawnEntity = new EntityPlasma(world, entityplayer, 14);
			costItem = PlasmaCraft.items.batteryPlasma;
			returnItem = PlasmaCraft.items.batteryEmpty;
		}
		if(this == PlasmaCraft.items.acidgun)
		{
			spawnEntity = new EntityAcid(world, entityplayer);
			costItem = PlasmaCraft.items.acidVial;
			returnItem = PlasmaCraft.items.causticVial;
			sound = "random.bow";
		}
		if(this == PlasmaCraft.items.railgun)
		{
			spawnEntity = new EntityRailGun(world, entityplayer);
			costItem = PlasmaCraft.items.batteryOvercharged;
			returnItem = PlasmaCraft.items.batteryEmpty;
			sound = "plasmacraft:weapon.railgun";
		}
		if(this == PlasmaCraft.items.lasershotgun)
		{
			spawnEntity = new EntityLaserShotgun(world, entityplayer);
			costItem = PlasmaCraft.items.energyCell;
		}
		if(this == PlasmaCraft.items.cryoblaster)
		{
			spawnEntity = new EntityCryoBlast(world, entityplayer);
			costItem = PlasmaCraft.items.batteryCryo;
			returnItem = PlasmaCraft.items.batteryEmpty;
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

				world.playSoundAtEntity(entityplayer, sound, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
				world.spawnEntityInWorld(spawnEntity);
			}
		}
		return itemstack;
	}
}
