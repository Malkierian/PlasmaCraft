package net.minecraft.src.Plasmacraft;

import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockGas extends Block
{

    private int renderID;
    private boolean supplyBlock;

    protected BlockGas(int i, int j, int k, boolean flag, Material material)
    {
        super(i, j, material);
        renderID = k;
        supplyBlock = flag;
        if(flag)
        {
            setTickOnLoad(true);
        }
    }

    public int getBlockTextureFromSide(int i)
    {
        return blockIndexInTexture;
    }

    protected int getAirDensity(World world, int i, int j, int k)
    {
        if(world.getBlockMaterial(i, j, k) != blockMaterial)
        {
            return 0;
        } else
        {
            return world.getBlockMetadata(i, j, k);
        }
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

    public void onEntityWalking(World world, int i, int j, int k, Entity entity)
    {
        if(!(entity instanceof EntityPlayer))
        {
            if(PlasmaCraftCore.proxy.getEntityInstanceOf(entity));
        }
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
        if(!(entity instanceof EntityPlayer))
        {
            if(PlasmaCraftCore.proxy.getEntityInstanceOf(entity));
        }
    }

    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        return true;
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

    public int tickRate()
    {
        return 5;
    }

    public void updateTick(World world, int i, int j, int k, Random random)
    {
        checkFlowDirections(world, i, j, k);
        super.updateTick(world, i, j, k, random);
    }

    public int getRenderBlockPass()
    {
        return 1;
    }

    public void onBlockAdded(World world, int i, int j, int k)
    {
        checkFlowDirections(world, i, j, k);
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
        checkFlowDirections(world, i, j, k);
    }

    protected void checkFlowDirections(World world, int i, int j, int k)
    {
        if(world.getBlockId(i, j, k) != blockID)
        {
            return;
        }
        int i1 = 0;
        int j1 = 0;
        if(supplyBlock)
        {
            if(world.getBlockMaterial(i - 1, j, k) == PlasmaCraftCore.gas)
            {
                world.scheduleBlockUpdate(i - 1, j, k, blockID, tickRate());
            }
            if(world.getBlockMaterial(i + 1, j, k) == PlasmaCraftCore.gas)
            {
                world.scheduleBlockUpdate(i + 1, j, k, blockID, tickRate());
            }
            if(world.getBlockMaterial(i, j - 1, k) == PlasmaCraftCore.gas)
            {
                world.scheduleBlockUpdate(i, j - 1, k, blockID, tickRate());
            }
            if(world.getBlockMaterial(i, j + 1, k) == PlasmaCraftCore.gas)
            {
                world.scheduleBlockUpdate(i, j + 1, k, blockID, tickRate());
            }
            if(world.getBlockMaterial(i, j, k - 1) == PlasmaCraftCore.gas)
            {
                world.scheduleBlockUpdate(i, j, k - 1, blockID, tickRate());
            }
            if(world.getBlockMaterial(i, j, k + 1) == PlasmaCraftCore.gas)
            {
                world.scheduleBlockUpdate(i, j, k + 1, blockID, tickRate());
            }
            return;
        }
        for(int k1 = -1; k1 <= 1; k1++)
        {
            for(int i2 = -1; i2 <= 1; i2++)
            {
                for(int k2 = -1; k2 <= 1; k2++)
                {
                    byte byte0 = 1;
                    if(k1 == 0 || i2 == 0 || k2 == 0)
                    {
                        byte0 = 2;
                    }
                    int i3 = getAirDensity(world, i + k1, j + i2, k2 + k);
                    if(i3 > 0)
                    {
                        j1++;
                        i1 += i3 * byte0;
                    }
                }

            }

        }

        if(j1 == 0)
        {
            world.setBlockWithNotify(i, j, k, 0);
            return;
        }
        i1 /= j1;
        if(i1 == 0)
        {
            world.setBlockWithNotify(i, j, k, 0);
            return;
        }
        if(i1 > 15)
        {
            i1 = 15;
        }
        world.setBlockMetadataWithNotify(i, j, k, i1);
        for(int l1 = -1; l1 <= 1; l1++)
        {
            for(int j2 = -1; j2 <= 1; j2++)
            {
                for(int l2 = -1; l2 <= 1; l2++)
                {
                    world.scheduleBlockUpdate(i + l1, j + j2, k + l2, blockID, tickRate());
                }

            }

        }

    }
}
