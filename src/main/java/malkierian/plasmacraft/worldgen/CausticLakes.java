package malkierian.plasmacraft.worldgen;

import java.util.Random;

import malkierian.plasmacraft.PlasmaCraft;
import malkierian.plasmacraft.init.PCBlocks;
import malkierian.plasmacraft.init.PCFluids;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockStone;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.TempCategory;

public class CausticLakes
{
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
			Biome biomegenbase = world.getBiome(new BlockPos(x, 0, z));
			if(biomegenbase == Biomes.BIRCH_FOREST
					|| biomegenbase == Biomes.BIRCH_FOREST_HILLS
					|| biomegenbase == Biomes.FOREST
					|| biomegenbase == Biomes.FOREST_HILLS
					|| biomegenbase == Biomes.JUNGLE
					|| biomegenbase == Biomes.JUNGLE_EDGE
					|| biomegenbase == Biomes.JUNGLE_HILLS
					|| biomegenbase == Biomes.MUSHROOM_ISLAND
					|| biomegenbase == Biomes.PLAINS
					|| biomegenbase == Biomes.SWAMPLAND)
			for(int i = 96; i > 50; i--)
			{
				Block id = world.getBlockState(new BlockPos(startX, i, startZ)).getBlock();
				if(id != Blocks.AIR)
				{
					if(id == Blocks.GRASS)
					{
						createSurfacePool(world, startX, i, startZ, rad, PCFluids.acidBlock);
						createSurfacePool(world, startX, i - 1, startZ, rad / 2, PCFluids.acidBlock);
						break;
					}
					else if(id instanceof BlockDirt || id instanceof BlockStone || id == Blocks.COBBLESTONE || id == Blocks.WATER)
					{
						break;
					}
				}
			}
		}	   
		for(int k1 = 0; k1 < PlasmaCraft.config.acid_spout.getSpoutCount(); k1++)
		{
			int i3 = x + rand.nextInt(16) + 8;
			int k4 = rand.nextInt(rand.nextInt(PlasmaCraft.config.acid_spout.getSpoutYSize()) + PlasmaCraft.config.acid_spout.getSpoutYStart());
			int l5 = z + rand.nextInt(16) + 8;
			(new WorldGenCaustics(PCFluids.acidBlock)).generate(world, rand, i3, k4, l5);
		}
		if(rand.nextFloat() >= 0.95f)
		{
			int startX = x + rand.nextInt(8);
			int startZ = z + rand.nextInt(8);
			int rad = 3 + rand.nextInt(3);
			Biome biomegenbase = world.getBiome(new BlockPos(x, 0, z));

			if(biomegenbase.getTempCategory() == TempCategory.COLD)
			{
				for(int i = 96; i > 50; i--)
				{
					Block id = world.getBlockState(new BlockPos(startX, i, startZ)).getBlock();
					if(id != Blocks.AIR)
					{
						if(id == Blocks.GRASS)
						{
							createSurfacePool(world, startX, i, startZ, rad, PCBlocks.frozenCryonite);
							createSurfacePool(world, startX, i - 1, startZ, rad / 2, PCBlocks.frozenCryonite);
							break;
						}
						else if(id instanceof BlockDirt || id == Blocks.WATER ||
								id instanceof BlockStone || id == Blocks.COBBLESTONE)
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

					if (world.getBlockState(new BlockPos(dx, y - 1, dz)).getBlock() != block) {
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
		return (x1 && x2) || (z1 && z2) || (x1 && z1) || (x1 && z2) || (x2 && z1) || (x2 && z2);
	}

	public void populateNetherLiquids(World world, Random random, int x,	int z)
	{
		rand = random;
	}
	
	public boolean isCaustic(World world, int x, int y, int z, Block block)
	{
		return (world.getBlockState(new BlockPos(x, y, z)).getBlock() == block);
	}
	
	public void setWithChance(World world, int x, int y, int z, Block stillId, float chance, boolean force)
	{
		if((rand.nextFloat() <= chance && world.getBlockState(new BlockPos(x, y - 2, z)).getBlock() != Blocks.AIR) || force)
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
				if(world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.WATER || 
						isCaustic(world, x, y, z, stillId))
				{
					world.setBlockState(new BlockPos(x, y, z), stillId.getDefaultState());
				}
				else
				{
					world.setBlockToAir(new BlockPos(x, y, z));
				}
				
				world.setBlockState(new BlockPos(x, y - 1, z), stillId.getDefaultState());
			}
		}
	}

	private boolean bordersAirOrLiquid(World world, int x, int y, int z)
	{
		Block left = world.getBlockState(new BlockPos(x + 1, y, z)).getBlock();
		Block right = world.getBlockState(new BlockPos(x - 1, y, z)).getBlock();
		Block back = world.getBlockState(new BlockPos(x, y, z + 1)).getBlock();
		Block front = world.getBlockState(new BlockPos(x, y, z - 1)).getBlock();
		return left == Blocks.AIR || left == Blocks.WATER || left == Blocks.LAVA ||
				right == Blocks.AIR || right == Blocks.WATER || right == Blocks.LAVA ||
				back == Blocks.AIR || back == Blocks.WATER || back == Blocks.LAVA ||
				front == Blocks.AIR || front == Blocks.WATER || front == Blocks.LAVA;
	}
}