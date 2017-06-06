package malkierian.plasmacraft.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
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
		BlockPos pos = new BlockPos(i, j, k);
		if(world.getBlockState(pos.add(0, 1, 0)).getBlock() != Blocks.STONE)
		{
			return false;
		}
		if(world.getBlockState(pos.add(0, -1, 0)).getBlock() != Blocks.STONE)
		{
			return false;
		}
		if(world.getBlockState(pos).getBlock() != Blocks.AIR && world.getBlockState(pos).getBlock() != Blocks.STONE)
		{
			return false;
		}
		int l = 0;
		if(world.getBlockState(pos.add(-1, 0, 0)).getBlock() == Blocks.STONE)
		{
			l++;
		}
		if(world.getBlockState(pos.add(1, 0, 0)).getBlock() == Blocks.STONE)
		{
			l++;
		}
		if(world.getBlockState(pos.add(0, 0, -1)).getBlock() == Blocks.STONE)
		{
			l++;
		}
		if(world.getBlockState(pos.add(0, 0, 1)).getBlock() == Blocks.STONE)
		{
			l++;
		}
		int i1 = 0;
		if(world.isAirBlock(pos.add(-1, 0, 0)))
		{
			i1++;
		}
		if(world.isAirBlock(pos.add(1, 0, 0)))
		{
			i1++;
		}
		if(world.isAirBlock(pos.add(0, 0, -1)))
		{
			i1++;
		}
		if(world.isAirBlock(pos.add(0, 0, 1)))
		{
			i1++;
		}
		if(l == 3 && i1 == 1)
		{
			world.setBlockState(pos, caustic.getDefaultState());
			world.scheduleUpdate(pos, caustic, 0);
		}
		return true;
	}
}
