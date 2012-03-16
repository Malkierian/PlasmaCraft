package net.minecraft.src.Plasmacraft;

import net.minecraft.src.EntityAnimal;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.World;

public class EntityMutantCow extends EntityAnimal
{

    public EntityMutantCow(World world)
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
        return PlasmaCraftCore.plasmaLeather.shiftedIndex;
    }

    public boolean interact(EntityPlayer entityplayer)
    {
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        if(itemstack != null && itemstack.itemID == PlasmaCraftCore.acidVial.shiftedIndex)
        {
            entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, new ItemStack(PlasmaCraftCore.fullAcidVial));
            return true;
        }
        else
        {
            return super.interact(entityplayer);
        }
    }

    protected EntityAnimal func_40145_a(EntityAnimal entityanimal)
    {
        return new EntityMutantCow(worldObj);
    }

	public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal) {
		// TODO Auto-generated method stub
		return null;
	}
}
