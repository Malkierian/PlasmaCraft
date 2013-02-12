package com.elvenwater.malkierian.Plasmacraft.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockCausticFluids extends Block
{
    public int renderID;
    public int stillTextureID;
    public int flowingTextureID;
    public int stillBlockID;
    public int flowingBlockID;
    public int armorTick;

    public BlockCausticFluids(int blockid, int textureindex, int flowingTexture, int stillTexture, int stillBlockId, int flowingBlockId)
    {
        super(blockid, textureindex, Material.water);
        float f = 0.0F;
        float f1 = 0.0F;
        stillTextureID = textureindex;
        flowingTextureID = flowingTexture;
        stillBlockID = stillBlockId;
        flowingBlockID = flowingBlockId;
        renderID = stillTexture;
        setBlockBounds(0.0F + f1, 0.0F + f, 0.0F + f1, 1.0F + f1, 1.0F + f, 1.0F + f1);
        armorTick = 0;
        this.setTickRandomly(true);
    }
    
    @SideOnly(value = Side.CLIENT)
    @Override
    public int getBlockColor()
    {
        return 0xffffff;
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k)
    {
        return 0xffffff;
    }
    
    @Override
    public int getBlockTextureFromSide(int par1)
    {
        return par1 != 0 && par1 != 1 ? flowingTextureID : stillTextureID;
    }

    @Override
    public boolean isBlockReplaceable(World world, int i, int j, int k)
    {
	    return world.getBlockId(i, j, k) == blockID;
    }

    @Override
    public void onBlockDestroyedByExplosion(World world, int i, int j, int k)
    {
        if((blockID == PlasmaCraft.acidMoving.blockID) | (blockID == PlasmaCraft.acidStill.blockID))
        {
            return;
        }
//        if(PlasmaCraft.liquidSourceExplodesAfterCausticExplosion)
//        {
//            world.setBlockWithNotify(i, j, k, 0);
//            world.createExplosion(null, i, j, k, 4F, false);
//        }
        else
        {
            return;
        }
    }
    
    public static float getFluidHeightPercent(int par0)
    {
        if (par0 >= 8)
        {
            par0 = 0;
        }

        return (float)(par0 + 1) / 9.0F;
    }

    protected int getFlowDecay(World par1World, int par2, int par3, int par4)
    {
        return par1World.getBlockMaterial(par2, par3, par4) == this.blockMaterial ? par1World.getBlockMetadata(par2, par3, par4) : -1;
    }
    
    protected int getEffectiveFlowDecay(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        if (par1IBlockAccess.getBlockMaterial(par2, par3, par4) != this.blockMaterial)
        {
            return -1;
        }
        else
        {
            int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);

            if (var5 >= 8)
            {
                var5 = 0;
            }

            return var5;
        }
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean canCollideCheck(int i, boolean flag)
    {
        return flag && i == 0;
    }

    @SideOnly(value = Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        Material var6 = par1IBlockAccess.getBlockMaterial(par2, par3, par4);
        return var6 == this.blockMaterial || par5 == 0 ? false : (par5 == 1 ? true : (var6 == Material.ice ? false : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5)));
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
    }

    @Override
    public int getRenderType()
    {
        return renderID;
    }

    @Override
    public int idDropped(int i, Random random, int j)
    {
        return 0;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }

    private Vec3 getFlowVector(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        Vec3 var5 = par1IBlockAccess.getWorldVec3Pool().getVecFromPool(0.0D, 0.0D, 0.0D);
        int var6 = this.getEffectiveFlowDecay(par1IBlockAccess, par2, par3, par4);

        for (int var7 = 0; var7 < 4; ++var7)
        {
            int var8 = par2;
            int var10 = par4;

            if (var7 == 0)
            {
                var8 = par2 - 1;
            }

            if (var7 == 1)
            {
                var10 = par4 - 1;
            }

            if (var7 == 2)
            {
                ++var8;
            }

            if (var7 == 3)
            {
                ++var10;
            }

            int var11 = this.getEffectiveFlowDecay(par1IBlockAccess, var8, par3, var10);
            int var12;

            if (var11 < 0)
            {
                if (!par1IBlockAccess.getBlockMaterial(var8, par3, var10).blocksMovement())
                {
                    var11 = this.getEffectiveFlowDecay(par1IBlockAccess, var8, par3 - 1, var10);

                    if (var11 >= 0)
                    {
                        var12 = var11 - (var6 - 8);
                        var5 = var5.addVector((double)((var8 - par2) * var12), (double)((par3 - par3) * var12), (double)((var10 - par4) * var12));
                    }
                }
            }
            else if (var11 >= 0)
            {
                var12 = var11 - var6;
                var5 = var5.addVector((double)((var8 - par2) * var12), (double)((par3 - par3) * var12), (double)((var10 - par4) * var12));
            }
        }

        if (par1IBlockAccess.getBlockMetadata(par2, par3, par4) >= 8)
        {
            boolean var13 = false;

            if (var13 || this.isBlockSolid(par1IBlockAccess, par2, par3, par4 - 1, 2))
            {
                var13 = true;
            }

            if (var13 || this.isBlockSolid(par1IBlockAccess, par2, par3, par4 + 1, 3))
            {
                var13 = true;
            }

            if (var13 || this.isBlockSolid(par1IBlockAccess, par2 - 1, par3, par4, 4))
            {
                var13 = true;
            }

            if (var13 || this.isBlockSolid(par1IBlockAccess, par2 + 1, par3, par4, 5))
            {
                var13 = true;
            }

            if (var13 || this.isBlockSolid(par1IBlockAccess, par2, par3 + 1, par4 - 1, 2))
            {
                var13 = true;
            }

            if (var13 || this.isBlockSolid(par1IBlockAccess, par2, par3 + 1, par4 + 1, 3))
            {
                var13 = true;
            }

            if (var13 || this.isBlockSolid(par1IBlockAccess, par2 - 1, par3 + 1, par4, 4))
            {
                var13 = true;
            }

            if (var13 || this.isBlockSolid(par1IBlockAccess, par2 + 1, par3 + 1, par4, 5))
            {
                var13 = true;
            }

            if (var13)
            {
                var5 = var5.normalize().addVector(0.0D, -6.0D, 0.0D);
            }
        }

        var5 = var5.normalize();
        return var5;
    }

    @Override
    public void velocityToAddToEntity(World world, int i, int j, int k, Entity entity, Vec3 vec3)
    {
        Vec3 vec31 = getFlowVector(world, i, j, k);
        vec3.xCoord += vec31.xCoord;
        vec3.yCoord += vec31.yCoord;
        vec3.zCoord += vec31.zCoord;
    }

    @Override
    public int tickRate()
    {
        if(blockIndexInTexture == PlasmaCraft.acidStillIndex || blockIndexInTexture == PlasmaCraft.acidMovingIndex)
        {
            return 3;
        }
        if(blockIndexInTexture == PlasmaCraft.radioniteStillIndex || blockIndexInTexture == PlasmaCraft.radioniteMovingIndex)
        {
            return 20;
        }
        if(blockIndexInTexture == PlasmaCraft.plutoniumStillIndex || blockIndexInTexture == PlasmaCraft.plutoniumMovingIndex)
        {
            return 8;
        }
        if(blockIndexInTexture == PlasmaCraft.netherflowStillIndex || blockIndexInTexture == PlasmaCraft.netherflowMovingIndex)
        {
            return 5;
        }
        if(blockIndexInTexture == PlasmaCraft.cryoniteStillIndex || blockIndexInTexture == PlasmaCraft.cryoniteMovingIndex)
        {
            return 6;
        }
        if(blockIndexInTexture == PlasmaCraft.neptuniumStillIndex || blockIndexInTexture == PlasmaCraft.neptuniumMovingIndex)
        {
            return 10;
        }
        if(blockIndexInTexture == PlasmaCraft.uraniumStillIndex || blockIndexInTexture == PlasmaCraft.uraniumMovingIndex)
        {
            return 15;
        }
        return blockIndexInTexture != PlasmaCraft.obsidiumStillIndex && blockIndexInTexture != PlasmaCraft.obsidiumMovingIndex ? 5 : 25;
    }

    public int getMixedBrightnessForBlock(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int var5 = par1IBlockAccess.getLightBrightnessForSkyBlocks(par2, par3, par4, 0);
        int var6 = par1IBlockAccess.getLightBrightnessForSkyBlocks(par2, par3 + 1, par4, 0);
        int var7 = var5 & 255;
        int var8 = var6 & 255;
        int var9 = var5 >> 16 & 255;
        int var10 = var6 >> 16 & 255;
        return (var7 > var8 ? var7 : var8) | (var9 > var10 ? var9 : var10) << 16;
//        return 15728880;
    }

    @SideOnly(Side.CLIENT)

    /**
     * How bright to render this block based on the light its receiving. Args: iBlockAccess, x, y, z
     */
    public float getBlockBrightness(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        float var5 = par1IBlockAccess.getLightBrightness(par2, par3, par4);
        float var6 = par1IBlockAccess.getLightBrightness(par2, par3 + 1, par4);
        return var5 > var6 ? var5 : var6;
    }

    @Override
    public void updateTick(World world, int i, int j, int k, Random random)
    {
        if(armorTick > 0)
        {
            armorTick--;
        }
        super.updateTick(world, i, j, k, random);
    }

    @Override
    public int getRenderBlockPass()
    {
        return blockMaterial == Material.water ? 1 : 0;
    }

    @Override
    public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
        if(random.nextInt(64) == 0)
        {
            int l = world.getBlockMetadata(i, j, k);
            if(l > 0 && l < 8)
            {
                world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, "liquid.water", random.nextFloat() * 0.25F + 0.75F, random.nextFloat() * 1.0F + 0.5F);
            }
        }
    }

    public double func_293_a(IBlockAccess iblockaccess, int i, int j, int k, Material material)
    {
        Vec3 vec3 = null;
        vec3 = getFlowVector(iblockaccess, i, j, k);
        if(vec3 == null || vec3.xCoord == 0.0D && vec3.zCoord == 0.0D)
        {
            return -1000D;
        }
        else
        {
            return Math.atan2(vec3.zCoord, vec3.xCoord) - 1.5707963267948966D;
        }
    }

    public void onBlockAdded(World world, int i, int j, int k)
    {
        //checkForHarden(world, i, j, k);
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
        //checkForHarden(world, i, j, k);
    }

    private boolean blockAdjoinsBlockID(World world, int i, int j, int k, int l)
    {
        boolean flag = false;
        if(world.getBlockMaterial(i, j, k - 1) == Material.water && world.getBlockId(i, j, k - 1) == l)
        {
            flag = true;
        }
        if(world.getBlockMaterial(i, j, k + 1) == Material.water && world.getBlockId(i, j, k + 1) == l)
        {
            flag = true;
        }
        if(world.getBlockMaterial(i - 1, j, k) == Material.water && world.getBlockId(i - 1, j, k) == l)
        {
            flag = true;
        }
        if(world.getBlockMaterial(i + 1, j, k) == Material.water && world.getBlockId(i + 1, j, k) == l)
        {
            flag = true;
        }
        if(world.getBlockMaterial(i, j - 1, k) == Material.water && world.getBlockId(i, j - 1, k) == l)
        {
            flag = true;
        }
        return flag;
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
        onEntityWalking(world, i, j, k, entity);
    }

    public void onEntityWalking(World world, int i, int j, int k, Entity entity)
    {
        if((entity instanceof EntityPlayer) || PlasmaCraft.proxy.getEntityInstanceOf(entity))
        {
            EntityPlayer entityplayer = (EntityPlayer)entity;
            if(entityplayer.hurtTime > 0)
            {
                return;
            }
            ItemStack itemstack[] = entityplayer.inventory.armorInventory;
            if(itemstack[0] == null || itemstack[1] == null || itemstack[2] == null || itemstack[3] == null)
            {
//                if(entityplayer.ridingEntity != null && (entityplayer.ridingEntity instanceof EntityCausticBoat))
//                {
//                    return;
//                } else
                {
                    entityplayer.attackEntityFrom(DamageSource.lava, 3);
                    return;
                }
            }
//            boolean flag = itemstack[0].itemID == PlasmaCraft.hazmatHood.itemID;
//            boolean flag1 = itemstack[1].itemID == PlasmaCraft.hazmatJacket.itemID;
//            boolean flag2 = itemstack[2].itemID == PlasmaCraft.hazmatPants.itemID;
//            boolean flag3 = itemstack[3].itemID == PlasmaCraft.hazmatBoots.itemID;
//            if(flag && flag1 && flag2 && flag3)
//            {
//                if(armorTick == 0)
//                {
//                    entityplayer.inventory.damageArmor(1);
//                    armorTick = 10;
//                }
//            }
            else
            {
            	entity.setFire(20);
                entityplayer.attackEntityFrom(DamageSource.lava, 3);
            }
        }
        else if(entity instanceof EntityLiving)
        {
            EntityLiving entityliving = (EntityLiving)entity;
            if(entityliving.hurtTime > 0)
            {
                return;
            }
            entity.setFire(20);
            entityliving.attackEntityFrom(DamageSource.lava, 3);
        }
//        else if(!(entity instanceof EntityCausticBoat))
//        {
//        	if(entity instanceof EntityItem)
//        	{
//            	EntityItem ent = (EntityItem) entity;
//            	if(ent.item.itemID == PlasmaCraft.ingotRadioniteID + 256)
//            	{
//            		return;
//            	}
//        	}
//        	entity.setFire(20);
//            entity.attackEntityFrom(DamageSource.lava, 10);
//        }
    }

    private void setAdjoiningLavaIDs(World world, int i, int j, int k, int l)
    {
        if(world.getBlockId(i - 1, j, k) == Block.lavaStill.blockID || world.getBlockId(i - 1, j, k) == Block.lavaMoving.blockID)
        {
            world.setBlockWithNotify(i - 1, j, k, l);
        }
        if(world.getBlockId(i + 1, j, k) == Block.lavaStill.blockID || world.getBlockId(i + 1, j, k) == Block.lavaMoving.blockID)
        {
            world.setBlockWithNotify(i + 1, j, k, l);
        }
        if(world.getBlockId(i, j - 1, k) == Block.lavaStill.blockID || world.getBlockId(i, j - 1, k) == Block.lavaMoving.blockID)
        {
            world.setBlockWithNotify(i, j - 1, k, l);
        }
        if(world.getBlockId(i, j, k - 1) == Block.lavaStill.blockID || world.getBlockId(i, j, k - 1) == Block.lavaMoving.blockID)
        {
            world.setBlockWithNotify(i, j, k - 1, l);
        }
        if(world.getBlockId(i, j, k + 1) == Block.lavaStill.blockID || world.getBlockId(i, j, k + 1) == Block.lavaMoving.blockID)
        {
            world.setBlockWithNotify(i, j, k + 1, l);
        }
    }

    private void setAdjoiningWaterIDs(World world, int i, int j, int k, int l)
    {
        if(world.getBlockId(i - 1, j, k) == Block.waterStill.blockID || world.getBlockId(i - 1, j, k) == Block.waterMoving.blockID)
        {
            world.setBlockWithNotify(i - 1, j, k, l);
        }
        if(world.getBlockId(i + 1, j, k) == Block.waterStill.blockID || world.getBlockId(i + 1, j, k) == Block.waterMoving.blockID)
        {
            world.setBlockWithNotify(i + 1, j, k, l);
        }
        if(world.getBlockId(i, j - 1, k) == Block.waterStill.blockID || world.getBlockId(i, j - 1, k) == Block.waterMoving.blockID)
        {
            world.setBlockWithNotify(i, j - 1, k, l);
        }
        if(world.getBlockId(i, j, k - 1) == Block.waterStill.blockID || world.getBlockId(i, j, k - 1) == Block.waterMoving.blockID)
        {
            world.setBlockWithNotify(i, j, k - 1, l);
        }
        if(world.getBlockId(i, j, k + 1) == Block.waterStill.blockID || world.getBlockId(i, j, k + 1) == Block.waterMoving.blockID)
        {
            world.setBlockWithNotify(i, j, k + 1, l);
        }
    }

    private void checkForHarden(World world, int i, int j, int k)
    {
        if(world.getBlockId(i, j, k) != blockID)
        {
            return;
        }
        if(blockIndexInTexture == PlasmaCraft.acidStillIndex || blockIndexInTexture == PlasmaCraft.acidMovingIndex)
        {
            if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
            {
                setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
            {
                setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.cobblestone.blockID);
            }
        }
        else if(blockIndexInTexture == PlasmaCraft.radioniteStillIndex || blockIndexInTexture == PlasmaCraft.radioniteMovingIndex)
        {
            if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
            {
                setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
            {
                setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.glowStone.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
        }
        else if(blockIndexInTexture == PlasmaCraft.plutoniumStillIndex || blockIndexInTexture == PlasmaCraft.plutoniumMovingIndex)
        {
            if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
            {
                setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
            {
                setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.netherrack.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
        }
        else if(blockIndexInTexture == PlasmaCraft.neptuniumStillIndex || blockIndexInTexture == PlasmaCraft.neptuniumMovingIndex)
        {
            if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
            {
                setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
            {
                setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.netherrack.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.sand.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
        }
        else if(blockIndexInTexture == PlasmaCraft.netherflowStillIndex || blockIndexInTexture == PlasmaCraft.netherflowMovingIndex)
        {
            if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
            {
                setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
            {
                setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.glowStone.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.netherrack.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.netherrack.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.slowSand.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.glowStone.blockID);
            }
        }
        else if(blockIndexInTexture == PlasmaCraft.uraniumStillIndex || blockIndexInTexture == PlasmaCraft.uraniumMovingIndex)
        {
            if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
            {
                setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
            {
                setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.sand.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.slowSand.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 8F, false);
            }
        }
        else if(blockIndexInTexture == PlasmaCraft.obsidiumStillIndex || blockIndexInTexture == PlasmaCraft.obsidiumMovingIndex)
        {
            if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
            {
                setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
            {
                setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
        }
        else if(blockIndexInTexture == PlasmaCraft.cryoniteStillIndex || blockIndexInTexture == PlasmaCraft.cryoniteMovingIndex)
        {
            if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
            {
                setAdjoiningLavaIDs(world, i, j, k, Block.cobblestone.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
            {
                setAdjoiningWaterIDs(world, i, j, k, Block.ice.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.cobblestone.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.glowStone.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 8F, false);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
        }
    }

    protected void triggerLavaMixEffects(World world, int i, int j, int k)
    {
        world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
        for(int l = 0; l < 8; l++)
        {
            world.spawnParticle("largesmoke", (double)i + Math.random(), (double)j + 1.2D, (double)k + Math.random(), 0.0D, 0.0D, 0.0D);
        }
    }

	@Override
	public String getTextureFile()
	{
		return CommonProxy.BLOCK_PNG;
	}
}