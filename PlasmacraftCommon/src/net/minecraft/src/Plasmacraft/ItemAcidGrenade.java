package net.minecraft.src.Plasmacraft;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemAcidGrenade extends ItemPlasma
{
    public ItemAcidGrenade(int i)
    {
        super(i);
        maxStackSize = 16;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        itemstack.stackSize--;
        world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        if(!PlasmaCraftCore.proxy.isMultiplayerWorld(world))
        {
            world.spawnEntityInWorld(new EntityAcidGrenade(world, entityplayer));
        }
        return itemstack;
    }
}
