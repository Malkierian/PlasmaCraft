package com.elvenwater.malkierian.Plasmacraft.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class CausticLakes
{
	private static int acidLakeYCutoff = 48;
	private static int acidSpoutCount = 20;
	private static int acidSpoutYRange = 30;
	private static int acidSpoutYStart = 8;
	private static Random rand = null;
	public CausticLakes()
	{
		
	}
	public void populateSurfaceLiquids(World world, Random random, int x, int z)
	{
		rand = random;
		if(rand.nextFloat() >= 0.97f)
		{
			int startX = x + rand.nextInt(8);
			int startZ = z + rand.nextInt(8);
			int rad = 4 + rand.nextInt(4);
			BiomeGenBase biomegenbase = world.getWorldChunkManager().getBiomeGenAt(x, z);
			if(biomegenbase == BiomeGenBase.forest
					|| biomegenbase == BiomeGenBase.forestHills
					|| biomegenbase == BiomeGenBase.jungle
					|| biomegenbase == BiomeGenBase.jungleHills
					|| biomegenbase == BiomeGenBase.mushroomIsland
					|| biomegenbase == BiomeGenBase.plains
					|| biomegenbase == BiomeGenBase.swampland)
			for(int i = 96; i > 50; i--)
			{
				int id = world.getBlockId(startX, i, startZ);
				if(id != 0)
				{
					if(id == Block.grass.blockID)
					{
						createSurfacePool(world, startX, i, startZ, rad, PlasmaCraft.acidStill.blockID, PlasmaCraft.acidMoving.blockID);
						createSurfacePool(world, startX, i - 1, startZ, rad / 2, PlasmaCraft.acidStill.blockID, PlasmaCraft.acidMoving.blockID);
						break;
					}
					else if(id == Block.dirt.blockID || id == Block.waterStill.blockID ||
							id == Block.stone.blockID || id == Block.cobblestone.blockID ||
							id == Block.waterMoving.blockID)
					{
						break;
					}
				}
			}
		}	   
		for(int k1 = 0; k1 < acidSpoutCount; k1++)
		{
			int i3 = x + rand.nextInt(16) + 8;
			int k4 = rand.nextInt(rand.nextInt(acidSpoutYRange) + acidSpoutYStart);
			int l5 = z + rand.nextInt(16) + 8;
			(new WorldGenCaustics(PlasmaCraft.acidMoving.blockID)).generate(world, rand, i3, k4, l5);
		}
		if(rand.nextFloat() >= 0.95f)
		{
			int startX = x + rand.nextInt(8);
			int startZ = z + rand.nextInt(8);
			int rad = 3 + rand.nextInt(3);
			BiomeGenBase biomegenbase = world.getWorldChunkManager().getBiomeGenAt(x, z);
			if(biomegenbase == BiomeGenBase.taiga 
					|| biomegenbase == BiomeGenBase.frozenOcean
					|| biomegenbase == BiomeGenBase.iceMountains 
					|| biomegenbase == BiomeGenBase.frozenRiver
					|| biomegenbase == BiomeGenBase.icePlains
					|| biomegenbase == BiomeGenBase.taigaHills)
			{
				for(int i = 96; i > 50; i--)
				{
					int id = world.getBlockId(startX, i, startZ);
					if(id != 0)
					{
						if(id == Block.grass.blockID)
						{
							createSurfacePool(world, startX, i, startZ, rad, PlasmaCraft.frozenCryonite.blockID, PlasmaCraft.frozenCryonite.blockID);
							createSurfacePool(world, startX, i - 1, startZ, rad / 2, PlasmaCraft.frozenCryonite.blockID, PlasmaCraft.frozenCryonite.blockID);
							break;
						}
						else if(id == Block.dirt.blockID || id == Block.waterStill.blockID ||
								id == Block.stone.blockID || id == Block.cobblestone.blockID ||
								id == Block.waterMoving.blockID)
						{
							break;
						}
					}
				}
			}
		}
	}
	
	public void createSurfacePool(World world, int x, int y, int z, int rad, int stillId, int movingId)
	{
			setWithChance(world, x, y, z, stillId, movingId, 1F, true);
			
			for (int w = 1; w <= rad; ++w) {
				float chance = (float) (rad - w + 6)
						/ (float) (rad + 6);
				
				for (int d = -w; d <= w; ++d) {			
					setWithChance(world, x + d, y, z + w, stillId, movingId, chance, false);
					setWithChance(world, x + d, y, z - w, stillId, movingId, chance, false);
					setWithChance(world, x + w, y, z + d, stillId, movingId, chance, false);
					setWithChance(world, x - w, y, z + d, stillId, movingId, chance, false);							
				}
			}
			
			for (int dx = x - rad; dx <= x + rad; ++dx) {
				for (int dz = z - rad; dz <= z + rad; ++dz) {

					if (world.getBlockId(dx, y - 1, dz) != stillId) {
						if (bordersCaustic(world, dx, y - 1, dz, stillId, movingId))
						{
							setWithChance(world, dx, y, dz, stillId, movingId, 1.0F, true);
						}
					}
				}
			}
	}

	private boolean bordersCaustic(World world, int x, int y, int z,
			int stillId, int movingId)
	{
		boolean x1 = isCaustic(world, x - 1, y, z, stillId, movingId);
		boolean x2 = isCaustic(world, x + 1, y, z, stillId, movingId);
		boolean z1 = isCaustic(world, x, y, z - 1, stillId, movingId);
		boolean z2 = isCaustic(world, x, y, z + 1, stillId, movingId);
		if((x1 && x2) || (z1 && z2) || (x1 && z1) || (x1 && z2) || (x2 && z1) || (x2 && z2))
			return true;
		else
			return false;
	}

	public void populateNetherLiquids(World world, Random random, int x,	int z)
	{
		rand = random;
	}
	
	public boolean isCaustic(World world, int x, int y, int z, int stillId, int movingId)
	{
		return (world.getBlockId(x, y, z) == stillId || world.getBlockId(x, y, z) == movingId);
	}
	
	public void setWithChance(World world, int x, int y, int z, int stillId, int movingId, float chance, boolean force)
	{
		if((rand.nextFloat() <= chance && world.getBlockId(x, y - 2, z) != 0) || force)
		{
			boolean adjacentCaustic = false;
			if(bordersAirOrLiquid(world, x, y - 1, z))
				return;
			
			if(isCaustic(world, x + 1, y - 1, z, stillId, movingId) ||
					isCaustic(world, x - 1, y - 1, z, stillId, movingId) ||
					isCaustic(world, x, y - 1, z + 1, stillId, movingId) ||
					isCaustic(world, x, y - 1, z - 1, stillId, movingId))
			{
				adjacentCaustic = true;
			}
			
			if(adjacentCaustic || force && !bordersAirOrLiquid(world, x, y - 1, z))
			{
				if(world.getBlockId(x, y, z) == Block.waterStill.blockID || 
						world.getBlockId(x, y, z) == Block.waterMoving.blockID ||
						isCaustic(world, x, y, z, stillId, movingId))
				{
					world.setBlockAndMetadataWithNotify(x, y, z, stillId, 0, 0);
				}
				else
				{
					world.setBlockAndMetadataWithNotify(x, y, z, 0, 0, 0);
				}
				
				world.setBlockAndMetadataWithNotify(x, y - 1, z, stillId, 0, 0);
			}
		}
	}

	private boolean bordersAirOrLiquid(World world, int x, int y, int z)
	{
		int left = world.getBlockId(x + 1, y, z);
		int right = world.getBlockId(x - 1, y, z);
		int back = world.getBlockId(x, y, z + 1);
		int front = world.getBlockId(x, y, z - 1);
		if(left == 0 || left == Block.waterStill.blockID || left == Block.waterMoving.blockID ||
				left == Block.lavaMoving.blockID || left == Block.lavaStill.blockID ||
				right == 0 || right == Block.waterStill.blockID || right == Block.waterMoving.blockID ||
				right == Block.lavaMoving.blockID || right == Block.lavaStill.blockID ||
				back == 0 || back == Block.waterStill.blockID || back == Block.waterMoving.blockID ||
				back == Block.lavaMoving.blockID || back == Block.lavaStill.blockID ||
				front == 0 || front == Block.waterStill.blockID || front == Block.waterMoving.blockID ||
				front == Block.lavaMoving.blockID || front == Block.lavaStill.blockID)
		{
			return true;
		}
		return false;
	}
}