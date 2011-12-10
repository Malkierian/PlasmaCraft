package net.minecraft.src;
import java.util.Random;

public class SMItemEnergyWeapon extends Item
{
	Random random = new Random();
    public SMItemEnergyWeapon(int i, int maxdmg)
    {
        super(i);
        setMaxDamage(maxdmg);
        maxStackSize = 1;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {	
    	//Laserrifle
    	if (shiftedIndex == mod_PlasmaCraft.lasergun.shiftedIndex) 
    	{
    		if(entityplayer.inventory.consumeInventoryItem(mod_PlasmaCraft.energyCell.shiftedIndex))
    		{
            world.playSoundAtEntity(entityplayer, "plasmacraft.lasergun", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
            	if(!world.singleplayerWorld)
            	{
            		itemstack.damageItem(1, entityplayer);
            		world.entityJoinedWorld(new SMEntityLaser(world, entityplayer));
            	}
    		}
    	}
    	
    	//Laserrifle + beamsplitter
    	if (shiftedIndex == mod_PlasmaCraft.lasergunsplit.shiftedIndex)
    	{
    		if(entityplayer.inventory.consumeInventoryItem(mod_PlasmaCraft.energyCell.shiftedIndex))
    		{       
    			itemstack.damageItem(1, entityplayer);
        	world.playSoundAtEntity(entityplayer, "plasmacraft.lasergun", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
        		for (int burst = 0; burst < 3; burst++) 
        		{
        			if(!world.singleplayerWorld)
        			{
        				
        				world.entityJoinedWorld(new SMEntityLaser(world, entityplayer));
        			}
        		}
    		}
    	}
    	
    	//Plasmarifle
    	if (shiftedIndex == mod_PlasmaCraft.plasmagun.shiftedIndex)
    	{
            if(entityplayer.inventory.consumeInventoryItem(mod_PlasmaCraft.BatteryPlasma.shiftedIndex))
            {                	

            	int randomInt = random.nextInt(3);
            	if (randomInt == 1) 
            	{
            		entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_PlasmaCraft.BatteryEmpty, 1));
            	}
            	//for (int burst = 0; burst < 3; burst++) {
            		world.playSoundAtEntity(entityplayer, "plasmacraft.lasergun", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
                	if(!world.singleplayerWorld)
                	{
                	itemstack.damageItem(1, entityplayer);
                    world.entityJoinedWorld(new SMEntityPlasma(world, entityplayer));
                	}
            	//}
            }
    	}
    	
    	//Plasmarifle + beamsplitter
    	if (shiftedIndex == mod_PlasmaCraft.plasmagunsplit.shiftedIndex)
    	{
            if(entityplayer.inventory.consumeInventoryItem(mod_PlasmaCraft.BatteryPlasma.shiftedIndex))
            {                	
            	itemstack.damageItem(1, entityplayer);
            	world.playSoundAtEntity(entityplayer, "plasmacraft.lasergun", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
            	int randomInt = random.nextInt(3);
            	if (randomInt == 1) 
            	{
            		entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_PlasmaCraft.BatteryEmpty, 1));
            	}
            	for (int burst = 0; burst < 3; burst++) {
                	if(!world.singleplayerWorld)
                	{
                    world.entityJoinedWorld(new SMEntityPlasma(world, entityplayer));
                	}
            	}
            }
    	}
    	
    	//AcidLauncher
    	if (shiftedIndex == mod_PlasmaCraft.acidgun.shiftedIndex)
    	{
            if(entityplayer.inventory.consumeInventoryItem(mod_PlasmaCraft.fullAcidVial.shiftedIndex))
            {                	
            	entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_PlasmaCraft.acidVial, 1));
            		world.playSoundAtEntity(entityplayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
                	if(!world.singleplayerWorld)
                	{
                		itemstack.damageItem(1, entityplayer);
                    world.entityJoinedWorld(new SMEntityAcid(world, entityplayer));
                	}
            }
    	}
    	
    	//Railgun
    	if (shiftedIndex == mod_PlasmaCraft.railgun.shiftedIndex)
    	{
    			if(entityplayer.inventory.consumeInventoryItem(mod_PlasmaCraft.BatteryOvercharged.shiftedIndex))
    			{                	
    				int randomInt = random.nextInt(3);
    				if (randomInt == 1) 
    				{
    					entityplayer.inventory.addItemStackToInventory(new ItemStack(mod_PlasmaCraft.BatteryEmpty, 1));
    				}
    				//for (int burst = 0; burst < 3; burst++) {
    					world.playSoundAtEntity(entityplayer, "plasmacraft.railgun", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
    					if(!world.singleplayerWorld)
    					{
    						itemstack.damageItem(1, entityplayer);
    						world.entityJoinedWorld(new SMEntityRailGun(world, entityplayer));
    					}
    					//}
    			}
    		
    	}
    	
    	//Laser Shotgun
    	if (shiftedIndex == mod_PlasmaCraft.lasershotgun.shiftedIndex)
    	{
            if(entityplayer.inventory.consumeInventoryItem(mod_PlasmaCraft.energyCell.shiftedIndex))
            {                	
            	world.playSoundAtEntity(entityplayer, "plasmacraft.lasergun", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
            	for (int burst = 0; burst < 7; burst++) {
                	if(!world.singleplayerWorld)
                	{
                		itemstack.damageItem(1, entityplayer);
                		world.entityJoinedWorld(new SMEntityLaserShotgun(world, entityplayer));
                	}
            	}
            }
    	}
    	
    	if (shiftedIndex == mod_PlasmaCraft.cryoblaster.shiftedIndex)
    	{
            if(entityplayer.inventory.consumeInventoryItem(mod_PlasmaCraft.energyCell.shiftedIndex))
            {                	
            	world.playSoundAtEntity(entityplayer, "plasmacraft.railgun", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F)); 
                	if(!world.singleplayerWorld)
                	{
                		itemstack.damageItem(1, entityplayer);
                		world.entityJoinedWorld(new SMEntityCryoBlast(world, entityplayer));
                	}
            	
            }
    	}
        return itemstack;
    }
}


