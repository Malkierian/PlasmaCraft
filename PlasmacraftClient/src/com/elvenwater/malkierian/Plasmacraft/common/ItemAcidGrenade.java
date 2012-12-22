package com.elvenwater.malkierian.Plasmacraft.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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
        if(!world.isRemote)
        {
            world.spawnEntityInWorld(new EntityAcidGrenade(world, entityplayer));
        }
        return itemstack;
    }
}
