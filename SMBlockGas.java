package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

import java.util.Random;

public class SMBlockGas extends Block
{

    protected SMBlockGas(int i, int j, int render, boolean supply, Material material)
    {
        super(i, j, material);
        renderID = render;
        supplyBlock = supply;
        if(supply)
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
        }
        else
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

    public void onEntityWalking(World world, int i,int j, int k, Entity entity)
    {
		if(!(entity instanceof EntityPlayer || entity instanceof EntityPlayerSP))
		{
			//entity.fire = 400;
			//entity.attackEntityFrom(null, 3);
		}
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
		if(!(entity instanceof EntityPlayer || entity instanceof EntityPlayerSP))
		{
			//entity.fire = 400;
			//entity.attackEntityFrom(null, 3);
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

		int myDensity = getAirDensity(world, i, j, k);
		int densityTotal = 0;
		int densityCount = 0;
		if(supplyBlock)
		{
			if(world.getBlockMaterial(i - 1, j, k) == mod_PlasmaCraft.gas) world.scheduleBlockUpdate(i - 1, j, k, blockID, tickRate());
			if(world.getBlockMaterial(i + 1, j, k) == mod_PlasmaCraft.gas) world.scheduleBlockUpdate(i + 1, j, k, blockID, tickRate());
			if(world.getBlockMaterial(i, j - 1, k) == mod_PlasmaCraft.gas) world.scheduleBlockUpdate(i, j - 1, k, blockID, tickRate());
			if(world.getBlockMaterial(i, j + 1, k) == mod_PlasmaCraft.gas) world.scheduleBlockUpdate(i, j + 1, k, blockID, tickRate());
			if(world.getBlockMaterial(i, j, k - 1) == mod_PlasmaCraft.gas) world.scheduleBlockUpdate(i, j, k - 1, blockID, tickRate());
			if(world.getBlockMaterial(i, j, k + 1) == mod_PlasmaCraft.gas) world.scheduleBlockUpdate(i, j, k + 1, blockID, tickRate());
			return;
		}
		for(int x = -1; x <= 1; x++)
		{
			for(int y = -1; y <= 1; y++)
			{
				for(int z = -1; z <= 1; z++)
				{
					int multiplier = 1;
					if(x == 0 || y == 0 || z == 0)
					{
						multiplier = 2;
					}
					int density = getAirDensity(world, i + x, j + y, z + k);
					if(density > 0)
					{
						densityCount++;
						densityTotal += density * multiplier;
					}
				}
			}
		}

		if(densityCount == 0)
		{
			world.setBlockWithNotify(i, j, k, 0);
			return;
		}

		densityTotal /= densityCount;

		if(densityTotal == 0)
		{
			world.setBlockWithNotify(i, j, k, 0);
			return;
		}

		if(densityTotal > 15) densityTotal = 15;
		world.setBlockMetadataWithNotify(i, j, k, densityTotal);

		for(int x = -1; x <= 1; x++)
		{
			for(int y = -1; y <= 1; y++)
			{
				for(int z = -1; z <= 1; z++)
				{
					world.scheduleBlockUpdate(i + x, j + y, k + z, blockID, tickRate());
				}
			}
		}
    }

    private int renderID;
    private boolean supplyBlock;
}
