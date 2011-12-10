




// Referenced classes of package net.minecraft.src:
//            EntityAnimal, Item, EntityPlayer, InventoryPlayer, 
//            ItemStack, World, NBTTagCompound

public class SMEntityMutantCow extends EntityAnimal
{

    public SMEntityMutantCow(World world)
    {
        super(world);
        texture = "/plasmacraft/mutantcow.png";
        setSize(0.9F, 1.3F);
    }

	public int getMaxHealth()
	{
		return 15;
	}

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
    }

    protected String getLivingSound()
    {
        return "mob.cow";
    }

    protected String getHurtSound()
    {
        return "mob.cowhurt";
    }

    protected String getDeathSound()
    {
        return "mob.cowhurt";
    }

    protected float getSoundVolume()
    {
        return 0.4F;
    }

    protected int getDropItemId()
    {
        return mod_PlasmaCraft.plasmaLeather.shiftedIndex;
    }

    public boolean interact(EntityPlayer entityplayer)
    {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        if(itemstack != null && itemstack.itemID == mod_PlasmaCraft.acidVial.shiftedIndex)
        {
            entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, new ItemStack(mod_PlasmaCraft.fullAcidVial));
            return true;
        } else
        {
            return super.interact(entityplayer);
        }
    }

	protected EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal)
	{
		return new SMEntityMutantCow(worldObj);
	}
}
