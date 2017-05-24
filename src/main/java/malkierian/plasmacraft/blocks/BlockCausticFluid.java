package malkierian.plasmacraft.blocks;

import malkierian.plasmacraft.PlasmaCraft;
import malkierian.plasmacraft.items.PCItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockCausticFluid extends BlockFluidClassic
{
	private int fluidId;
	private float explosionStrength;
	
	public BlockCausticFluid(Fluid fluid, Material material, int damage, float luminosity, float explosiveness)
	{
		super(fluid, material);
		fluidId = damage;
		setLightLevel(luminosity);
		explosionStrength = explosiveness;
		setUnlocalizedName(fluid.getUnlocalizedName());
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, 
			ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if(!(heldItem.getItem() == PCItems.vial))
			return false;
		else
			return true;
	}
	
	@Override
	public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn)
	{
		if(this.getFluid().getUnlocalizedName().equals("acid"))
			return;
		
		if(PlasmaCraft.config.destroy_source_blocks)
		{
			worldIn.setBlockToAir(pos);
			worldIn.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), explosionStrength, true);
		}
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		onEntityWalk(world, pos, state, entity);
	}
	
	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity)
	{
		onEntityWalk(world, pos, world.getBlockState(pos), entity);
	}
	
	public void onEntityWalk(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if(entity instanceof EntityPlayer || PlasmaCraft.proxy.getEntityInstanceOf(entity))
		{
			EntityPlayer entityPlayer = (EntityPlayer) entity;
			
			if(entityPlayer.capabilities.isCreativeMode)
				return;
			
			if(entityPlayer.hurtTime > 0)
				return;
			
			
		}
	}
	
	public int getFluidID()
	{
		return fluidId;
	}
}
