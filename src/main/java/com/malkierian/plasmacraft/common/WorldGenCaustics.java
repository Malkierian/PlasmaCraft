package com.malkierian.plasmacraft.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenCaustics extends WorldGenerator
{
	private Block caustic;

	public WorldGenCaustics(Block block)
	{
		caustic = block;
	}

	public boolean generate(World world, Random random, int i, int j, int k)
	{
		if(world.getBlock(i, j + 1, k) != Blocks.stone)
		{
			return false;
		}
		if(world.getBlock(i, j - 1, k) != Blocks.stone)
		{
			return false;
		}
		if(world.getBlock(i, j, k) != Blocks.air && world.getBlock(i, j, k) != Blocks.stone)
		{
			return false;
		}
		int l = 0;
		if(world.getBlock(i - 1, j, k) == Blocks.stone)
		{
			l++;
		}
		if(world.getBlock(i + 1, j, k) == Blocks.stone)
		{
			l++;
		}
		if(world.getBlock(i, j, k - 1) == Blocks.stone)
		{
			l++;
		}
		if(world.getBlock(i, j, k + 1) == Blocks.stone)
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
//			Block.blocksList[caustic].updateTick(world, i, j, k, random);
			world.scheduledUpdatesAreImmediate = false;
		}
		return true;
	}
}
