package malkierian.plasmacraft.blocks;

import malkierian.plasmacraft.PlasmaCraft;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAcidBarrier extends Block
{
	public BlockAcidBarrier()
	{
		super(Material.GLASS);
		setHardness(3F);
		setResistance(5F);
		setLightOpacity(2);
		setLightLevel(0.94F);
		setSoundType(SoundType.GLASS);
		setCreativeTab(PlasmaCraft.plasmaTab);
		setUnlocalizedName("acidBarrier");
	}

	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity)
	{
		entity.attackEntityFrom(DamageSource.magic, 2);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		entity.attackEntityFrom(DamageSource.magic, 2);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World world, BlockPos pos)
	{
		float shrinkAmount = 0.125F;
		return new AxisAlignedBB(pos).contract(0.125F);
	}
}
