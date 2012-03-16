package net.minecraft.src.Plasmacraft;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.Vec3D;
import net.minecraft.src.World;
import net.minecraft.src.forge.ITextureProvider;

public abstract class BlockCausticFluids extends Block implements ITextureProvider
{
    public int renderID;
    public int stillTextureID;
    public int flowingTextureID;
    public int stillBlockID;
    public int flowingBlockID;
    public int armorTick;

    protected BlockCausticFluids(int blockid, int textureindex, int flowingTexture, int stillTexture, int stillBlockId, int flowingBlockId)
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
        setTickRandomly(true);
        armorTick = 0;
    }
    
    public void addCreativeItems(ArrayList itemList)
    {    	
    	itemList.add(new ItemStack(this, 1));
    }

    public boolean isBlockReplaceable(World world, int i, int j, int k)
    {
	    return world.getBlockId(i, j, k) == blockID;
    }
    
    public int getBlockColor()
    {
        return 0xffffff;
    }

    public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k)
    {
        return 0xffffff;
    }

    public void onBlockDestroyedByExplosion(World world, int i, int j, int k)
    {
        if((blockID == PlasmaCraftCore.acidMoving.blockID) | (blockID == PlasmaCraftCore.acidStill.blockID))
        {
            return;
        }
        if(PlasmaCraftCore.LiquidSourceExplodesAfterCausticExplosion)
        {
            world.setBlockWithNotify(i, j, k, 0);
            world.createExplosion(null, i, j, k, 4F);
        }
        else
        {
            return;
        }
    }

    public static float setFluidHeight(int i)
    {
        if(i >= 8)
        {
            i = 0;
        }
        float f = (float)(i + 1) / 9F;
        return f;
    }

    protected int getFlowDecay(World world, int i, int j, int k)
    {
        if(world.getBlockMaterial(i, j, k) != blockMaterial)
        {
            return -1;
        } else
        {
            return world.getBlockMetadata(i, j, k);
        }
    }

    protected int getEffectiveFlowDecay(IBlockAccess iblockaccess, int i, int j, int k)
    {
        if(iblockaccess.getBlockMaterial(i, j, k) != blockMaterial)
        {
            return -1;
        }
        int l = iblockaccess.getBlockMetadata(i, j, k);
        if(l >= 8)
        {
            l = 0;
        }
        return l;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean canCollideCheck(int i, boolean flag)
    {
        return flag && i == 0;
    }

    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        Material material = iblockaccess.getBlockMaterial(i, j, k);
        if(material == blockMaterial)
        {
            return false;
        }
        if(material == Material.ice)
        {
            return false;
        }
        if(l == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
    }

    public int getRenderType()
    {
        return renderID;
    }

    public int idDropped(int i, Random random)
    {
        return 0;
    }

    public int quantityDropped(Random random)
    {
        return 0;
    }

    private Vec3D getFlowVector(IBlockAccess iblockaccess, int i, int j, int k)
    {
        Vec3D vec3d = Vec3D.createVector(0.0D, 0.0D, 0.0D);
        int l = getEffectiveFlowDecay(iblockaccess, i, j, k);
        for(int i1 = 0; i1 < 4; i1++)
        {
            int j1 = i;
            int k1 = j;
            int l1 = k;
            if(i1 == 0)
            {
                j1--;
            }
            if(i1 == 1)
            {
                l1--;
            }
            if(i1 == 2)
            {
                j1++;
            }
            if(i1 == 3)
            {
                l1++;
            }
            int i2 = getEffectiveFlowDecay(iblockaccess, j1, k1, l1);
            if(i2 < 0)
            {
                if(iblockaccess.getBlockMaterial(j1, k1, l1).isSolid())
                {
                    continue;
                }
                i2 = getEffectiveFlowDecay(iblockaccess, j1, k1 - 1, l1);
                if(i2 >= 0)
                {
                    int j2 = i2 - (l - 8);
                    vec3d = vec3d.addVector((j1 - i) * j2, (k1 - j) * j2, (l1 - k) * j2);
                }
                continue;
            }
            if(i2 >= 0)
            {
                int k2 = i2 - l;
                vec3d = vec3d.addVector((j1 - i) * k2, (k1 - j) * k2, (l1 - k) * k2);
            }
        }

        if(iblockaccess.getBlockMetadata(i, j, k) >= 8)
        {
            boolean flag = false;
            if(flag || shouldSideBeRendered(iblockaccess, i, j, k - 1, 2))
            {
                flag = true;
            }
            if(flag || shouldSideBeRendered(iblockaccess, i, j, k + 1, 3))
            {
                flag = true;
            }
            if(flag || shouldSideBeRendered(iblockaccess, i - 1, j, k, 4))
            {
                flag = true;
            }
            if(flag || shouldSideBeRendered(iblockaccess, i + 1, j, k, 5))
            {
                flag = true;
            }
            if(flag || shouldSideBeRendered(iblockaccess, i, j + 1, k - 1, 2))
            {
                flag = true;
            }
            if(flag || shouldSideBeRendered(iblockaccess, i, j + 1, k + 1, 3))
            {
                flag = true;
            }
            if(flag || shouldSideBeRendered(iblockaccess, i - 1, j + 1, k, 4))
            {
                flag = true;
            }
            if(flag || shouldSideBeRendered(iblockaccess, i + 1, j + 1, k, 5))
            {
                flag = true;
            }
            if(flag)
            {
                vec3d = vec3d.normalize().addVector(0.0D, -6D, 0.0D);
            }
        }
        vec3d = vec3d.normalize();
        return vec3d;
    }

    public void velocityToAddToEntity(World world, int i, int j, int k, Entity entity, Vec3D vec3d)
    {
        Vec3D vec3d1 = getFlowVector(world, i, j, k);
        vec3d.xCoord += vec3d1.xCoord;
        vec3d.yCoord += vec3d1.yCoord;
        vec3d.zCoord += vec3d1.zCoord;
    }

    public int tickRate()
    {
        if(blockIndexInTexture == PlasmaCraftCore.acidStillIndex || blockIndexInTexture == PlasmaCraftCore.acidMovingIndex)
        {
            return 3;
        }
        if(blockIndexInTexture == PlasmaCraftCore.radioniteStillIndex || blockIndexInTexture == PlasmaCraftCore.radioniteMovingIndex)
        {
            return 20;
        }
        if(blockIndexInTexture == PlasmaCraftCore.plutoniumStillIndex || blockIndexInTexture == PlasmaCraftCore.plutoniumMovingIndex)
        {
            return 8;
        }
        if(blockIndexInTexture == PlasmaCraftCore.netherflowStillIndex || blockIndexInTexture == PlasmaCraftCore.netherflowMovingIndex)
        {
            return 5;
        }
        if(blockIndexInTexture == PlasmaCraftCore.cryoniteStillIndex || blockIndexInTexture == PlasmaCraftCore.cryoniteMovingIndex)
        {
            return 6;
        }
        if(blockIndexInTexture == PlasmaCraftCore.neptuniumStillIndex || blockIndexInTexture == PlasmaCraftCore.neptuniumMovingIndex)
        {
            return 10;
        }
        if(blockIndexInTexture == PlasmaCraftCore.uraniumStillIndex || blockIndexInTexture == PlasmaCraftCore.uraniumMovingIndex)
        {
            return 15;
        }
        return blockIndexInTexture != PlasmaCraftCore.obsidiumStillIndex && blockIndexInTexture != PlasmaCraftCore.obsidiumMovingIndex ? 5 : 25;
    }

    public float getBlockBrightness(IBlockAccess iblockaccess, int i, int j, int k)
    {
        float f = PlasmaCraftCore.proxy.getLightBrightess(iblockaccess, i, j, k);
        float f1 = PlasmaCraftCore.proxy.getLightBrightess(iblockaccess, i, j + 1, k);
        return f <= f1 ? f1 : f;
    }

    public int getMixedBrightnessForBlock(IBlockAccess iblockaccess, int i, int j, int k)
    {
        int l = PlasmaCraftCore.proxy.getLightBrightnessForSkyBlocks(iblockaccess, i, j, k, 0);
        int i1 = PlasmaCraftCore.proxy.getLightBrightnessForSkyBlocks(iblockaccess, i, j + 1, k, 0);
        int j1 = l & 0xff;
        int k1 = i1 & 0xff;
        int l1 = l >> 16 & 0xff;
        int i2 = i1 >> 16 & 0xff;
        return (j1 > k1 ? j1 : k1) | (l1 > i2 ? l1 : i2) << 16;
    }

    public void updateTick(World world, int i, int j, int k, Random random)
    {
        if(armorTick > 0)
        {
            armorTick--;
        }
        super.updateTick(world, i, j, k, random);
    }

    public int getRenderBlockPass()
    {
        return blockMaterial == Material.water ? 1 : 0;
    }

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
        Vec3D vec3d = null;
        vec3d = getFlowVector(iblockaccess, i, j, k);
        if(vec3d == null || vec3d.xCoord == 0.0D && vec3d.zCoord == 0.0D)
        {
            return -1000D;
        }
        else
        {
            return Math.atan2(vec3d.zCoord, vec3d.xCoord) - 1.5707963267948966D;
        }
    }

    public void onBlockAdded(World world, int i, int j, int k)
    {
        checkForHarden(world, i, j, k);
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
        checkForHarden(world, i, j, k);
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
        if((entity instanceof EntityPlayer) || PlasmaCraftCore.proxy.getEntityInstanceOf(entity))
        {
            EntityPlayer entityplayer = (EntityPlayer)entity;
            if(entityplayer.hurtTime > 0)
            {
                return;
            }
            ItemStack itemstack = PlasmaCraftCore.proxy.armorItemInSlot(entityplayer, 3);
            ItemStack itemstack1 = PlasmaCraftCore.proxy.armorItemInSlot(entityplayer, 2);
            ItemStack itemstack2 = PlasmaCraftCore.proxy.armorItemInSlot(entityplayer, 1);
            ItemStack itemstack3 = PlasmaCraftCore.proxy.armorItemInSlot(entityplayer, 0);
            if(itemstack == null || itemstack1 == null || itemstack2 == null || itemstack3 == null)
            {
                if(entityplayer.ridingEntity != null && (entityplayer.ridingEntity instanceof EntityCausticBoat))
                {
                    return;
                } else
                {
                    entityplayer.attackEntityFrom(DamageSource.lava, 3);
                    return;
                }
            }
            boolean flag = itemstack.itemID == PlasmaCraftCore.helmetHazmat.shiftedIndex;
            boolean flag1 = itemstack1.itemID == PlasmaCraftCore.plateHazmat.shiftedIndex;
            boolean flag2 = itemstack2.itemID == PlasmaCraftCore.legsHazmat.shiftedIndex;
            boolean flag3 = itemstack3.itemID == PlasmaCraftCore.bootsHazmat.shiftedIndex;
            if(flag && flag1 && flag2 && flag3)
            {
                if(armorTick == 0)
                {
                    entityplayer.inventory.damageArmor(1);
                    armorTick = 10;
                }
            }
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
        else if(!(entity instanceof EntityCausticBoat))
        {
        	entity.setFire(20);
            entity.attackEntityFrom(DamageSource.lava, 10);
        }
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
        if(blockIndexInTexture == PlasmaCraftCore.acidStillIndex || blockIndexInTexture == PlasmaCraftCore.acidMovingIndex)
        {
            if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
            {
                setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
            {
                setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.radioniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.radioniteFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.plutoniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.plutoniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.neptuniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.neptuniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.netherflowStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.netherflowFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.uraniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.uraniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.obsidiumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.obsidiumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.cryoniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.cryoniteFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.cobblestone.blockID);
            }
        }
        else if(blockIndexInTexture == PlasmaCraftCore.radioniteStillIndex || blockIndexInTexture == PlasmaCraftCore.radioniteMovingIndex)
        {
            if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
            {
                setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
            {
                setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.acidStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.acidFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.plutoniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.plutoniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.neptuniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.neptuniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.netherflowStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.netherflowFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.glowStone.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.uraniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.uraniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.obsidiumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.obsidiumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
        }
        else if(blockIndexInTexture == PlasmaCraftCore.plutoniumStillIndex || blockIndexInTexture == PlasmaCraftCore.plutoniumMovingIndex)
        {
            if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
            {
                setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
            {
                setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.acidStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.acidFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.radioniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.radioniteFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.neptuniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.neptuniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.netherflowStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.netherflowFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.netherrack.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.uraniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.uraniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.obsidiumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.obsidiumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.cryoniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.cryoniteFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
        }
        else if(blockIndexInTexture == PlasmaCraftCore.neptuniumStillIndex || blockIndexInTexture == PlasmaCraftCore.neptuniumMovingIndex)
        {
            if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
            {
                setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
            {
                setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.acidStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.acidFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.radioniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.radioniteFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.plutoniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.plutoniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.netherflowStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.netherflowFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.netherrack.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.uraniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.uraniumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.sand.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.obsidiumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.obsidiumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.cryoniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.cryoniteFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
        }
        else if(blockIndexInTexture == PlasmaCraftCore.netherflowStillIndex || blockIndexInTexture == PlasmaCraftCore.netherflowMovingIndex)
        {
            if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
            {
                setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
            {
                setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.acidStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.acidFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.radioniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.radioniteFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.glowStone.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.plutoniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.plutoniumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.netherrack.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.neptuniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.neptuniumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.netherrack.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.uraniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.uraniumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.slowSand.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.obsidiumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.obsidiumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.cryoniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.cryoniteFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.glowStone.blockID);
            }
        }
        else if(blockIndexInTexture == PlasmaCraftCore.uraniumStillIndex || blockIndexInTexture == PlasmaCraftCore.uraniumMovingIndex)
        {
            if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
            {
                setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
            {
                setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.acidStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.acidFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.radioniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.radioniteFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.plutoniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.plutoniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.neptuniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.neptuniumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.sand.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.netherflowStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.netherflowFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.slowSand.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.obsidiumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.obsidiumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.cryoniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.cryoniteFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 8F);
            }
        }
        else if(blockIndexInTexture == PlasmaCraftCore.obsidiumStillIndex || blockIndexInTexture == PlasmaCraftCore.obsidiumMovingIndex)
        {
            if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
            {
                setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
            {
                setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.acidStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.acidFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.radioniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.radioniteFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.plutoniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.plutoniumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.neptuniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.neptuniumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.netherflowStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.netherflowFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.uraniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.uraniumFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.cryoniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.cryoniteFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
            }
        }
        else if(blockIndexInTexture == PlasmaCraftCore.cryoniteStillIndex || blockIndexInTexture == PlasmaCraftCore.cryoniteMovingIndex)
        {
            if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
            {
                setAdjoiningLavaIDs(world, i, j, k, Block.cobblestone.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
            {
                setAdjoiningWaterIDs(world, i, j, k, Block.ice.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.acidStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.acidFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.cobblestone.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.plutoniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.plutoniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.neptuniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.neptuniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 4F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.netherflowStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.netherflowFlowingBlockID))
            {
                world.setBlockWithNotify(i, j, k, Block.glowStone.blockID);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.uraniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.uraniumFlowingBlockID))
            {
                world.createExplosion(null, i, j, k, 8F);
            }
            else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.obsidiumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraftCore.obsidiumFlowingBlockID))
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
		return PlasmaCraftCore.liquidTexture;
	}
}
