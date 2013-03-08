package com.elvenwater.malkierian.Plasmacraft.common.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.elvenwater.malkierian.Plasmacraft.common.EnumPlasmaLiquid;
import com.elvenwater.malkierian.Plasmacraft.common.PlasmaCraft;
import com.elvenwater.malkierian.Plasmacraft.common.Entities.EntityMutantCow;

public class ItemVial extends ItemPlasma
{

    private int isFull;
    public EnumPlasmaLiquid contents;

    public ItemVial(int i, int j, EnumPlasmaLiquid liquid)
    {
        super(i);
        maxStackSize = 8;
        isFull = j;
        contents = liquid;
    }

    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        float f = 1.0F;
        float f1 = entityplayer.prevRotationPitch + (entityplayer.rotationPitch - entityplayer.prevRotationPitch) * f;
        float f2 = entityplayer.prevRotationYaw + (entityplayer.rotationYaw - entityplayer.prevRotationYaw) * f;
        double d = entityplayer.prevPosX + (entityplayer.posX - entityplayer.prevPosX) * (double)f;
        double d1 = (entityplayer.prevPosY + (entityplayer.posY - entityplayer.prevPosY) * (double)f + 1.6200000000000001D) - (double)entityplayer.yOffset;
        double d2 = entityplayer.prevPosZ + (entityplayer.posZ - entityplayer.prevPosZ) * (double)f;
        Vec3 vec3 = Vec3.createVectorHelper(d, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
        float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
        float f5 = -MathHelper.cos(-f1 * 0.01745329F);
        float f6 = MathHelper.sin(-f1 * 0.01745329F);
        float f7 = f4 * f5;
        float f8 = f6;
        float f9 = f3 * f5;
        double d3 = 5D;
        ItemStack returnStack = null;
        Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f8 * d3, (double)f9 * d3);
        MovingObjectPosition movingobjectposition = world.rayTraceBlocks_do(vec3, vec31, isFull == 0);
        if(movingobjectposition == null)
        {
            return itemstack;
        }
        if(movingobjectposition.typeOfHit == EnumMovingObjectType.TILE)
        {
            int i = movingobjectposition.blockX;
            int j = movingobjectposition.blockY;
            int k = movingobjectposition.blockZ;
            if(!world.canMineBlock(entityplayer, i, j, k))
            {
                return itemstack;
            }
            if(isFull == 0)
            {
                int l = world.getBlockId(i, j, k);
                if(l == PlasmaCraft.acidStill.blockID && world.getBlockMetadata(i, j, k) == 0)
                {
                    if(world.setBlockWithNotify(i, j, k, 0))
                    {
                    	returnStack = new ItemStack(PlasmaCraft.acidVial);
                    }
                }
                else
                {
                    if(l == PlasmaCraft.plutoniumStill.blockID && world.getBlockMetadata(i, j, k) == 0)
                    {
                        world.setBlockWithNotify(i, j, k, 0);
                        returnStack = new ItemStack(PlasmaCraft.plutoniumVial);
                    }
                    if(l == PlasmaCraft.radioniteStill.blockID && world.getBlockMetadata(i, j, k) == 0)
                    {
                        world.setBlockWithNotify(i, j, k, 0);
                        returnStack = new ItemStack(PlasmaCraft.radioniteVial);
                    }
                    if(l == PlasmaCraft.uraniumStill.blockID && world.getBlockMetadata(i, j, k) == 0)
                    {
                        world.setBlockWithNotify(i, j, k, 0);
                        returnStack = new ItemStack(PlasmaCraft.uraniumVial);
                    }
                    if(l == PlasmaCraft.neptuniumStill.blockID && world.getBlockMetadata(i, j, k) == 0)
                    {
                        world.setBlockWithNotify(i, j, k, 0);
                        returnStack = new ItemStack(PlasmaCraft.neptuniumVial);
                    }
                    if(l == PlasmaCraft.netherflowStill.blockID && world.getBlockMetadata(i, j, k) == 0)
                    {
                        world.setBlockWithNotify(i, j, k, 0);
                        returnStack = new ItemStack(PlasmaCraft.netherflowVial);
                    }
                    if(l == PlasmaCraft.obsidiumStill.blockID && world.getBlockMetadata(i, j, k) == 0)
                    {
                        world.setBlockWithNotify(i, j, k, 0);
                        returnStack = new ItemStack(PlasmaCraft.obsidiumVial);
                    }
                    if(l == PlasmaCraft.cryoniteStill.blockID && world.getBlockMetadata(i, j, k) == 0)
                    {
                        world.setBlockWithNotify(i, j, k, 0);
                        returnStack = new ItemStack(PlasmaCraft.cryoniteVial);
                    }
                }
                //if(itemstack.stackSize > 1)
                //{
                	if(returnStack != null)
                	{
	                	itemstack.stackSize--;
	                	entityplayer.inventory.addItemStackToInventory(returnStack);
	                	return itemstack;
                	}
                //}
                //else
                //{
                //	return returnStack;
                //}
            } else
            {
                if(movingobjectposition.sideHit == 0)
                {
                    j--;
                }
                if(movingobjectposition.sideHit == 1 && world.getBlockId(movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ) != Block.snow.blockID)
                {
                    j++;
                }
                if(movingobjectposition.sideHit == 2)
                {
                    k--;
                }
                if(movingobjectposition.sideHit == 3)
                {
                    k++;
                }
                if(movingobjectposition.sideHit == 4)
                {
                    i--;
                }
                if(movingobjectposition.sideHit == 5)
                {
                    i++;
                }
                if(world.isAirBlock(i, j, k) || !world.getBlockMaterial(i, j, k).isSolid())
                {
                    world.setBlockAndMetadataWithNotify(i, j, k, isFull, 0);
                    //if(itemstack.stackSize > 1)
                    //{
                    	itemstack.stackSize--;
                    	entityplayer.inventory.addItemStackToInventory(new ItemStack(PlasmaCraft.causticVial));
                    	return itemstack;
                    //}
                    //else
                    //{
                    //	return new ItemStack(PlasmaCraftCore.acidVial);
                    //}
                }
            }
        }
        if(isFull == 0 && (movingobjectposition.entityHit instanceof EntityMutantCow))
        {
        	//if(itemstack.stackSize > 1)
        	//{
        		itemstack.stackSize--;
        		entityplayer.inventory.addItemStackToInventory(new ItemStack(PlasmaCraft.acidVial));
        		return itemstack;
        	//}
        	//else
        	//{
        	//	return new ItemStack(PlasmaCraftCore.fullAcidVial);
        	//}
        } else
        {
            return itemstack;
        }
    }
}
