package malkierian.plasmacraft.world;

import java.util.ArrayList;

import malkierian.plasmacraft.PlasmaCraft;
import malkierian.plasmacraft.init.PCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockSnow;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CryoBlast extends CausticExplosion
{

	public CryoBlast(World world, Entity entity, double x, double y, double z, float size, boolean flaming, boolean smoking)
	{
		super(world, entity, x, y, z, size, flaming, smoking);
	}

	@Override
	public void doExplosionB(boolean spawnParticles)
	{
		super.doExplosionB(spawnParticles);
		ArrayList<BlockPos> arraylist = new ArrayList<BlockPos>();
		arraylist.addAll(affectedBlockPositions);
		for(BlockPos pos : arraylist)
		{
			Block i3 = worldObj.getBlockState(pos).getBlock();

			int r = explosionRNG.nextInt(10);
			if(r < 7 && worldObj.isAirBlock(pos) && Blocks.SNOW.canPlaceBlockAt(worldObj, pos))
			{
				worldObj.setBlockState(pos, Blocks.SNOW.getDefaultState());
			}
			else if(r < 9 && worldObj.isAirBlock(pos) && Blocks.ICE.canPlaceBlockAt(worldObj, pos))
			{
				worldObj.setBlockState(pos, Blocks.ICE.getDefaultState());
			}
			else if(worldObj.isAirBlock(pos) && PCBlocks.frozenCryonite.canPlaceBlockAt(worldObj, pos))
			{
				worldObj.setBlockState(pos, PCBlocks.frozenCryonite.getDefaultState());
			}
			
			if(worldObj.getBlockState(pos).getBlock() == Blocks.WATER || worldObj.getBlockState(pos.add(0, 1, 0)).getBlock() == Blocks.WATER)
			{
				worldObj.setBlockState(pos, Blocks.ICE.getDefaultState());
				worldObj.setBlockState(pos.add(0, 1, 0), Blocks.SNOW.getDefaultState());
            }
		}
	}
}
