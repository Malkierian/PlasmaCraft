package malkierian.plasmacraft.items;

import java.util.Random;

import malkierian.plasmacraft.PlasmaCraft;
import malkierian.plasmacraft.entity.EntityAcid;
import malkierian.plasmacraft.init.PCItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class ItemEnergyWeapon extends ItemPlasma
{
	Random random;
	
	public ItemEnergyWeapon()
	{
		super("none");
	}

	public ItemEnergyWeapon(String name, int i)
	{
		super(name);
		random = new Random();
		setMaxDamage(i);
		maxStackSize = 1;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer, EnumHand hand)
	{
		EntityThrowable spawnEntity = null;
		ItemStack costItem = null;
		Item returnItem = null;
		String sound = "plasmacraft:weapon.lasergun";
		boolean isCreative = entityplayer.capabilities.isCreativeMode;
		
//		if(this == PlasmaCraft.items.lasergun)
//		{
//			spawnEntity = new EntityLaser(world, entityplayer, 9);
//			costItem = new ItemStack(PlasmaCraft.items.energyCell);
//		}
//		if(this == PlasmaCraft.items.lasergunsplit)
//		{
//			spawnEntity = new EntityLaser(world, entityplayer, 11);
//			costItem = new ItemStack(PlasmaCraft.items.energyCell);
//		}
//		if(this == PlasmaCraft.items.plasmagun)
//		{
//			spawnEntity = new EntityPlasma(world, entityplayer, 12);
//			costItem = new ItemStack(PlasmaCraft.items.battery, 1, ItemBattery.PLASMA_DAMAGE);
//			returnItem = PlasmaCraft.items.battery;
//		}
//		if(this == PlasmaCraft.items.plasmagunsplit)
//		{
//			spawnEntity = new EntityPlasma(world, entityplayer, 14);
//			costItem = new ItemStack(PlasmaCraft.items.battery, 1, ItemBattery.PLASMA_DAMAGE);
//			returnItem = PlasmaCraft.items.battery;
//		}
		if(this == PCItems.acidgun)
		{
			spawnEntity = new EntityAcid(world, entityplayer);
			costItem = new ItemStack(PCItems.vial, 1, ItemVial.ACID_DAMAGE); // TODO Acid charge/round for launcher
			returnItem = PCItems.vial;
			//TODO Mortar sound for plasma launcher
		}
//		if(this == PlasmaCraft.items.railgun)
//		{
//			spawnEntity = new EntityRailGun(world, entityplayer);
//			costItem = new ItemStack(PlasmaCraft.items.battery, 1, ItemBattery.OVERCHARGED_DAMAGE);
//			returnItem = PlasmaCraft.items.battery;
//			sound = "plasmacraft:weapon.railgun";
//		}
//		if(this == PlasmaCraft.items.lasershotgun)
//		{
//			spawnEntity = new EntityLaserShotgun(world, entityplayer);
//			costItem = new ItemStack(PlasmaCraft.items.energyCell);
//		}
//		if(this == PlasmaCraft.items.cryoblaster)
//		{
//			spawnEntity = new EntityCryoBlast(world, entityplayer);
//			costItem = new ItemStack(PlasmaCraft.items.battery, 1, ItemBattery.CRYO_DAMAGE);
//			returnItem = PlasmaCraft.items.battery;
//			sound = "plasmacraft:weapon.railgun";
//		}
//
		if(spawnEntity != null)
		{
			if(!world.isRemote)
			{
				if(!isCreative)
					if(PlasmaCraft.consumeInventoryItem(entityplayer.inventory, costItem))
					{
						itemstack.damageItem(1, entityplayer);
						if(returnItem != null)
						{
							int j = random.nextInt(3);

							if(j == 1)
								entityplayer.inventory.addItemStackToInventory(new ItemStack(returnItem, 1));
						}
					}
					else
						return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);

				spawnEntity.setHeadingFromThrower(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0, 1.75f, 0.1f);
				world.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));;
				world.spawnEntityInWorld(spawnEntity);
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
	}
}
