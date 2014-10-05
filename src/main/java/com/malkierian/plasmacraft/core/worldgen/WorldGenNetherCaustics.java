package com.malkierian.plasmacraft.core.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenNetherCaustics extends WorldGenerator
{
	private Block caustic;

	public WorldGenNetherCaustics(Block block)
	{
		caustic = block;
	}

	public boolean generate(World world, Random random, int i, int j, int k)
	{
		if(world.getBlock(i, j + 1, k) != Blocks.netherrack)
		{
			return false;
		}
		if(world.getBlock(i, j - 1, k) != Blocks.netherrack)
		{
			return false;
		}
		if(world.getBlock(i, j, k) != Blocks.air && world.getBlock(i, j, k) != Blocks.netherrack)
		{
			return false;
		}
		int l = 0;
		if(world.getBlock(i - 1, j, k) == Blocks.netherrack)
		{
			l++;
		}
		if(world.getBlock(i + 1, j, k) == Blocks.netherrack)
		{
			l++;
		}
		if(world.getBlock(i, j, k - 1) == Blocks.netherrack)
		{
			l++;
		}
		if(world.getBlock(i, j, k + 1) == Blocks.netherrack)
		{
			l++;
		}
		int i1 = 0;
		if(world.isAirBlock(i - 1, j, k))
		{
			i1++;
		}
		if(world.isAirBlock(i + 1, j, k))
		{
			i1++;
		}
		if(world.isAirBlock(i, j, k - 1))
		{
			i1++;
		}
		if(world.isAirBlock(i, j, k + 1))
		{
			i1++;
		}
		if(l == 3 && i1 == 1)
		{
			world.setBlock(i, j, k, caustic);
			world.scheduledUpdatesAreImmediate = true;
//			Block.blocksList[causticID].updateTick(world, i, j, k, random);
			world.scheduledUpdatesAreImmediate = false;
		}
		return true;
	}
}
