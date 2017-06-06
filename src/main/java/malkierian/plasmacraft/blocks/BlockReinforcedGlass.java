package malkierian.plasmacraft.blocks;

import java.util.Random;

import malkierian.plasmacraft.PlasmaCraft;
import malkierian.plasmacraft.init.PCBlocks;
import malkierian.plasmacraft.init.PCFluids;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.TempCategory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockReinforcedGlass extends BlockBreakable
{
	public BlockReinforcedGlass(Material material, boolean flag, float resistance)
	{
		super(material, flag);
		setHardness(1.0F);
		setResistance(resistance);
		setSoundType(SoundType.GLASS);
		setTickRandomly(true);
		setCreativeTab(PlasmaCraft.plasmaTab);
	}

	public int quantityDropped(Random random)
	{
		if(this == PCBlocks.frozenCryonite)
			return 0;
		else
			return 1;
	}

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
    	if(this == PCBlocks.frozenCryonite)
    		return BlockRenderLayer.SOLID;
    	else
    		return BlockRenderLayer.CUTOUT;
    }
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		if(this == PCBlocks.frozenCryonite)
			world.setBlockState(pos, PCFluids.cryoniteBlock.getDefaultState());
		else
			super.breakBlock(world, pos, state);
	}
	
	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random)
	{
		updateTick(worldIn, pos, state, random);
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		super.updateTick(worldIn, pos, state, rand);
		if(this == PCBlocks.frozenCryonite)
		{
			if(worldIn.getBiome(pos).getTempCategory() != TempCategory.COLD)
			{
				int random = rand.nextInt(20);
				if(random > 18)
					breakBlock(worldIn, pos, state);
			}
		}
	}
}
