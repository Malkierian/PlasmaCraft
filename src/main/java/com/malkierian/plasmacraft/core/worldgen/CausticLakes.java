package com.malkierian.plasmacraft.core.worldgen;

import java.util.Random;

import com.malkierian.plasmacraft.core.PlasmaCraft;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class CausticLakes
{
//	private static int acidLakeYCutoff = 48;
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
			if(biomegenbase == BiomeGenBase.birchForest
					|| biomegenbase == BiomeGenBase.birchForestHills
					|| biomegenbase == BiomeGenBase.forest
					|| biomegenbase == BiomeGenBase.forestHills
					|| biomegenbase == BiomeGenBase.jungle
					|| biomegenbase == BiomeGenBase.jungleHills
					|| biomegenbase == BiomeGenBase.mushroomIsland
					|| biomegenbase == BiomeGenBase.plains
					|| biomegenbase == BiomeGenBase.swampland)
			for(int i = 96; i > 50; i--)
			{
				Block id = world.getBlock(startX, i, startZ);
				if(id != Blocks.air)
				{
					if(id == Blocks.grass)
					{
						createSurfacePool(world, startX, i, startZ, rad, PlasmaCraft.acidBlock);
						createSurfacePool(world, startX, i - 1, startZ, rad / 2, PlasmaCraft.acidBlock);
						break;
					}
					else if(id == Blocks.dirt || id == Blocks.stone || id == Blocks.cobblestone || id == Blocks.water)
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
			(new WorldGenCaustics(PlasmaCraft.acidBlock)).generate(world, rand, i3, k4, l5);
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
					Block id = world.getBlock(startX, i, startZ);
					if(id != Blocks.air)
					{
						if(id == Blocks.grass)
						{
							createSurfacePool(world, startX, i, startZ, rad, PlasmaCraft.frozenCryonite);
							createSurfacePool(world, startX, i - 1, startZ, rad / 2, PlasmaCraft.frozenCryonite);
							break;
						}
						else if(id == Blocks.dirt || id == Blocks.water ||
								id == Blocks.stone || id == Blocks.cobblestone ||
								id == Blocks.water)
						{
							break;
						}
					}
				}
			}
		}
	}
	
	public void createSurfacePool(World world, int x, int y, int z, int rad, Block block)
	{
			setWithChance(world, x, y, z, block, 1F, true);
			
			for (int w = 1; w <= rad; ++w) {
				float chance = (float) (rad - w + 6)
						/ (float) (rad + 6);
				
				for (int d = -w; d <= w; ++d) {			
					setWithChance(world, x + d, y, z + w, block, chance, false);
					setWithChance(world, x + d, y, z - w, block, chance, false);
					setWithChance(world, x + w, y, z + d, block, chance, false);
					setWithChance(world, x - w, y, z + d, block, chance, false);							
				}
			}
			
			for (int dx = x - rad; dx <= x + rad; ++dx) {
				for (int dz = z - rad; dz <= z + rad; ++dz) {

					if (world.getBlock(dx, y - 1, dz) != block) {
						if (bordersCaustic(world, dx, y - 1, dz, block))
						{
							setWithChance(world, dx, y, dz, block, 1.0F, true);
						}
					}
				}
			}
	}

	private boolean bordersCaustic(World world, int x, int y, int z, Block block)
	{
		boolean x1 = isCaustic(world, x - 1, y, z, block);
		boolean x2 = isCaustic(world, x + 1, y, z, block);
		boolean z1 = isCaustic(world, x, y, z - 1, block);
		boolean z2 = isCaustic(world, x, y, z + 1, block);
		if((x1 && x2) || (z1 && z2) || (x1 && z1) || (x1 && z2) || (x2 && z1) || (x2 && z2))
			return true;
		else
			return false;
	}

	public void populateNetherLiquids(World world, Random random, int x,	int z)
	{
		rand = random;
	}
	
	public boolean isCaustic(World world, int x, int y, int z, Block block)
	{
		return (world.getBlock(x, y, z) == block);
	}
	
	public void setWithChance(World world, int x, int y, int z, Block stillId, float chance, boolean force)
	{
		if((rand.nextFloat() <= chance && world.getBlock(x, y - 2, z) != Blocks.air) || force)
		{
			boolean adjacentCaustic = false;
			if(bordersAirOrLiquid(world, x, y - 1, z))
				return;
			
			if(isCaustic(world, x + 1, y - 1, z, stillId) ||
					isCaustic(world, x - 1, y - 1, z, stillId) ||
					isCaustic(world, x, y - 1, z + 1, stillId) ||
					isCaustic(world, x, y - 1, z - 1, stillId))
			{
				adjacentCaustic = true;
			}
			
			if(adjacentCaustic || force && !bordersAirOrLiquid(world, x, y - 1, z))
			{
				if(world.getBlock(x, y, z) == Blocks.water || 
						isCaustic(world, x, y, z, stillId))
				{
					world.setBlock(x, y, z, stillId);
				}
				else
				{
					world.setBlockToAir(x, y, z);
				}
				
				world.setBlock(x, y - 1, z, stillId);
			}
		}
	}

	private boolean bordersAirOrLiquid(World world, int x, int y, int z)
	{
		Block left = world.getBlock(x + 1, y, z);
		Block right = world.getBlock(x - 1, y, z);
		Block back = world.getBlock(x, y, z + 1);
		Block front = world.getBlock(x, y, z - 1);
		if(left == Blocks.air || left == Blocks.water || left == Blocks.lava ||
				right == Blocks.air|| right == Blocks.water || right == Blocks.lava ||
				back == Blocks.air|| back == Blocks.water || back == Blocks.lava ||
				front == Blocks.air|| front == Blocks.water || front == Blocks.lava)
		{
			return true;
		}
		return false;
	}
}