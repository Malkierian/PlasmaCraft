package net.minecraft.src.Plasmacraft;

import java.util.Random;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

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
        if(shiftedIndex == PlasmaCraftCore.lasergun.shiftedIndex && entityplayer.inventory.consumeInventoryItem(PlasmaCraftCore.energyCell.shiftedIndex))
        {
            world.playSoundAtEntity(entityplayer, "plasmacraft.lasergun", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if(!world.multiplayerWorld)
            {
                itemstack.damageItem(1, entityplayer);
                world.entityJoinedWorld(new EntityLaser(world, entityplayer));
            }
        }
        if(shiftedIndex == PlasmaCraftCore.lasergunsplit.shiftedIndex && entityplayer.inventory.consumeInventoryItem(PlasmaCraftCore.energyCell.shiftedIndex))
        {
            itemstack.damageItem(1, entityplayer);
            world.playSoundAtEntity(entityplayer, "plasmacraft.lasergun", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
            for(int i = 0; i < 3; i++)
            {
                if(!world.multiplayerWorld)
                {
                    world.entityJoinedWorld(new EntityLaser(world, entityplayer));
                }
            }

        }
        if(shiftedIndex == PlasmaCraftCore.plasmagun.shiftedIndex && entityplayer.inventory.consumeInventoryItem(PlasmaCraftCore.BatteryPlasma.shiftedIndex))
        {
            int j = random.nextInt(3);
            if(j == 1)
            {
                entityplayer.inventory.addItemStackToInventory(new ItemStack(PlasmaCraftCore.BatteryEmpty, 1));
            }
            world.playSoundAtEntity(entityplayer, "plasmacraft.lasergun", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if(!world.multiplayerWorld)
            {
                itemstack.damageItem(1, entityplayer);
                world.entityJoinedWorld(new EntityPlasma(world, entityplayer));
            }
        }
        if(shiftedIndex == PlasmaCraftCore.plasmagunsplit.shiftedIndex && entityplayer.inventory.consumeInventoryItem(PlasmaCraftCore.BatteryPlasma.shiftedIndex))
        {
            itemstack.damageItem(1, entityplayer);
            world.playSoundAtEntity(entityplayer, "plasmacraft.lasergun", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
            int k = random.nextInt(3);
            if(k == 1)
            {
                entityplayer.inventory.addItemStackToInventory(new ItemStack(PlasmaCraftCore.BatteryEmpty, 1));
            }
            for(int j1 = 0; j1 < 3; j1++)
            {
                if(!world.multiplayerWorld)
                {
                    world.entityJoinedWorld(new EntityPlasma(world, entityplayer));
                }
            }

        }
        if(shiftedIndex == PlasmaCraftCore.acidgun.shiftedIndex && entityplayer.inventory.consumeInventoryItem(PlasmaCraftCore.fullAcidVial.shiftedIndex))
        {
            entityplayer.inventory.addItemStackToInventory(new ItemStack(PlasmaCraftCore.acidVial, 1));
            world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if(!world.multiplayerWorld)
            {
                itemstack.damageItem(1, entityplayer);
                world.entityJoinedWorld(new EntityAcid(world, entityplayer));
            }
        }
        if(shiftedIndex == PlasmaCraftCore.railgun.shiftedIndex && entityplayer.inventory.consumeInventoryItem(PlasmaCraftCore.BatteryOvercharged.shiftedIndex))
        {
            int l = random.nextInt(3);
            if(l == 1)
            {
                entityplayer.inventory.addItemStackToInventory(new ItemStack(PlasmaCraftCore.BatteryEmpty, 1));
            }
            world.playSoundAtEntity(entityplayer, "plasmacraft.railgun", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if(!world.multiplayerWorld)
            {
                itemstack.damageItem(1, entityplayer);
                world.entityJoinedWorld(new EntityRailGun(world, entityplayer));
            }
        }
        if(shiftedIndex == PlasmaCraftCore.lasershotgun.shiftedIndex && entityplayer.inventory.consumeInventoryItem(PlasmaCraftCore.energyCell.shiftedIndex))
        {
            world.playSoundAtEntity(entityplayer, "plasmacraft.lasergun", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
            for(int i1 = 0; i1 < 7; i1++)
            {
                if(!world.multiplayerWorld)
                {
                    itemstack.damageItem(1, entityplayer);
                    world.entityJoinedWorld(new EntityLaserShotgun(world, entityplayer));
                }
            }

        }
        if(shiftedIndex == PlasmaCraftCore.cryoblaster.shiftedIndex && entityplayer.inventory.consumeInventoryItem(PlasmaCraftCore.BatteryCryo.shiftedIndex))
        {
            world.playSoundAtEntity(entityplayer, "plasmacraft.railgun", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if(!world.multiplayerWorld)
            {
                itemstack.damageItem(1, entityplayer);
                world.entityJoinedWorld(new EntityCryoBlast(world, entityplayer));
            }
        }
        return itemstack;
    }
}
