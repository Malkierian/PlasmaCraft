package malkierian.plasmacraft.blocks;

import java.util.HashMap;
import java.util.Map;

import malkierian.plasmacraft.PlasmaCraft;
import malkierian.plasmacraft.init.PCFluids;
import malkierian.plasmacraft.init.PCItems;
import malkierian.plasmacraft.items.ItemIngot;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockCausticFluid extends BlockFluidClassic
{
	private int fluidId;
	private float explosionStrength;
	public ResourceLocation undertex;
	
	public BlockCausticFluid(Fluid fluid, Material material, int damage, float luminosity, float explosiveness)
	{
		super(fluid, material);
		fluidId = damage;
		setLightLevel(luminosity);
		explosionStrength = explosiveness;
		setUnlocalizedName(fluid.getUnlocalizedName());
		undertex = new ResourceLocation(PlasmaCraft.modId + ":textures/misc/under" + fluid.getName() + ".png");
	}
	
	public float getExplosionStrength()
	{
		return explosionStrength;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public Boolean isEntityInsideMaterial(IBlockAccess world, BlockPos blockpos, IBlockState iblockstate, Entity entity, double yToTest, Material materialIn, boolean testingHead)
	{
		if(materialIn == Material.WATER)
		{
			if(testingHead)
			{
				if(iblockstate.getBlock() instanceof BlockCausticFluid)
				{
					BlockCausticFluid fluid = (BlockCausticFluid)iblockstate.getBlock();
					if((yToTest % 1) <= fluid.getFluidHeightForRender(world, blockpos))
						return true;
				}
				return false;
			}
			else
				return true;
		}
		return false;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, 
			ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if(!(heldItem.getItem() == PCItems.VIALS))
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
	
	public MapColor getMapColor()
	{
		return blockMapColor;
	}
	
	public int getLightLevel()
	{
		return lightValue;
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
			
			ItemStack itemstack[] = entityPlayer.inventory.armorInventory;

			if(itemstack[0] == null || itemstack[1] == null || itemstack[2] == null || itemstack[3] == null)
			{
//				TODO Redo caustic boat interaction
//				if(entityPlayer.ridingEntity != null && (entityPlayer.ridingEntity instanceof EntityCausticBoat))
//				{
//					return;
//				}
//				else
//				{
					entity.setFire(3);
					entityPlayer.attackEntityFrom(DamageSource.inFire, 3);
					return;
//				}
			}
//TODO Set up hazmat interactions
//			boolean flag = itemstack[3].getItem() == PCItems.hazmatHood;
//			boolean flag1 = itemstack[2].getItem() == PCItems.hazmatJacket;
//			boolean flag2 = itemstack[1].getItem() == PCItems.hazmatPants;
//			boolean flag3 = itemstack[0].getItem() == PCItems.hazmatBoots;
//
//			if(flag && flag1 && flag2 && flag3)
//			{
//				if(armorTick > 0)
//				{
//					armorTick--;
//				}
//				if(armorTick == 0)
//				{
//					entityPlayer.inventory.damageArmor(1);
//					armorTick = 100;
//				}
//			}
//			else
//			{
//				entity.setFire(3);
//				entityPlayer.attackEntityFrom(DamageSource.inFire, 3);
//			}
		}
		else if(entity instanceof EntityLiving)
		{
			EntityLiving entityliving = (EntityLiving)entity;
			if(entityliving.hurtTime > 0)
			{
				return;
			}
			entity.setFire(3);
			entityliving.attackEntityFrom(DamageSource.inFire, 3);
		}
		// TODO readd caustic boat
		else if(!(entity instanceof EntityBoat))
		{
			if(entity instanceof EntityItem)
			{
				EntityItem ent = (EntityItem) entity;
                ItemStack itemStack = ent.getEntityItem();

				if (itemStack.getItem() == PCItems.INGOTS && itemStack.getItemDamage() == ItemIngot.RADIONITE_DAMAGE)
				{
					return;
				}
			}
			entity.setFire(3);
			entity.attackEntityFrom(DamageSource.inFire, 10);
		}
	}
    
    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        this.checkForHarden(world, pos);
    	super.onBlockAdded(world, pos, state);
    }

    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor)
    {
        this.checkForHarden((World)world, pos);
    	super.onNeighborChange(world, pos, neighbor);
    }
	
	private Map<Block, BlockPos> getAdjoiningBlocks(World world, BlockPos pos)
	{
		Map<Block, BlockPos> list = new HashMap<Block, BlockPos>();
		BlockPos pos2 = pos.add(-1, 0, 0);
		list.put(world.getBlockState(pos2).getBlock(), pos2);
		pos2 = pos.add(1, 0, 0);
    	list.put(world.getBlockState(pos2).getBlock(), pos2);
		pos2 = pos.add(0, -1, 0);
    	list.put(world.getBlockState(pos2).getBlock(), pos2);
		pos2 = pos.add(0, 0, -1);
    	list.put(world.getBlockState(pos2).getBlock(), pos2);
		pos2 = pos.add(0, 0, 1);
    	list.put(world.getBlockState(pos2).getBlock(), pos2);
    	return list;
	}

	private void checkForHarden(World world, BlockPos pos)
	{
		IBlockState blockState = world.getBlockState(pos);
		Block block = blockState.getBlock();
		if(block != this)
		{
			return;
		}

		Map<Block, BlockPos> blockList = getAdjoiningBlocks(world, pos);

		for(Block neighbor : blockList.keySet())
		{
			IBlockState neighborState = world.getBlockState(blockList.get(neighbor));
			int blockMeta = blockState.getBlock().getMetaFromState(blockState);
			int neighborMeta = neighborState.getBlock().getMetaFromState(neighborState);
			if(neighborMeta > 0 && blockMeta > 0)
				return;
			if(neighbor != block)
			{
				BlockPos pos2 = blockList.get(neighbor);
				if(blockMeta == 0)
					pos2 = pos;
				processBlockChange(world, neighbor, block, pos2);
			}
		}
	}
	
	private void processBlockChange(World world, Block neighbor, Block block, BlockPos pos)
	{
		if(neighbor == Blocks.LAVA)
		{
			if(this == PCFluids.cryoniteBlock)
				world.setBlockState(pos, Blocks.COBBLESTONE.getDefaultState());
			else
				world.setBlockState(pos, Blocks.SAND.getDefaultState());
		}
		else if(neighbor == Blocks.WATER)
		{
			if(this == PCFluids.cryoniteBlock)
				world.setBlockState(pos, Blocks.ICE.getDefaultState());
			else
				world.setBlockState(pos, Blocks.CLAY.getDefaultState());
		}
		else if(neighbor == PCFluids.obsidiumBlock)
		{
			if(this instanceof BlockCausticFluid)
				world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
		}
		else if(block == PCFluids.obsidiumBlock)
		{
			if(this instanceof BlockCausticFluid)
				world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
		}
		else if(neighbor == PCFluids.acidBlock)
		{
			if(this == PCFluids.cryoniteBlock)
				world.setBlockState(pos, Blocks.COBBLESTONE.getDefaultState());
			else
				explodeBlock(world, pos, neighbor, block);
		}
		else if(neighbor == PCFluids.radioniteBlock)
		{
			if(this == PCFluids.netherflowBlock)
				world.setBlockState(pos, Blocks.GLOWSTONE.getDefaultState());
			else
				explodeBlock(world, pos, neighbor, block);
		}
		else if(neighbor == PCFluids.plutoniumBlock)
		{
			if(this == PCFluids.netherflowBlock)
				world.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
			else
				explodeBlock(world, pos, neighbor, block);
		}
		else if(neighbor == PCFluids.neptuniumBlock)
		{
			if(this == PCFluids.netherflowBlock)
				world.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
			else if(this == PCFluids.uraniumBlock)
				world.setBlockState(pos, Blocks.SAND.getDefaultState());
			else
				explodeBlock(world, pos, neighbor, block);
		}
		else if(neighbor == PCFluids.netherflowBlock)
		{
			if(this == PCFluids.radioniteBlock)
				world.setBlockState(pos, Blocks.GLOWSTONE.getDefaultState());
			else if(this == PCFluids.plutoniumBlock)
				world.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
			else if(this == PCFluids.neptuniumBlock)
				world.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
			else if(this == PCFluids.uraniumBlock)
				world.setBlockState(pos, Blocks.SOUL_SAND.getDefaultState());
			else if(this == PCFluids.cryoniteBlock)
				world.setBlockState(pos, Blocks.GLOWSTONE.getDefaultState());
			else
				explodeBlock(world, pos, neighbor, block);
		}
		else if(neighbor == PCFluids.uraniumBlock)
		{
			if(this == PCFluids.neptuniumBlock)
				world.setBlockState(pos, Blocks.SAND.getDefaultState());
			else if(this == PCFluids.netherflowBlock)
				world.setBlockState(pos, Blocks.SOUL_SAND.getDefaultState());
			else
				explodeBlock(world, pos, neighbor, block);
		}
		else if(neighbor == PCFluids.cryoniteBlock)
		{
			if(this == PCFluids.acidBlock)
				world.setBlockState(pos, Blocks.COBBLESTONE.getDefaultState());
			else if(this == PCFluids.netherflowBlock)
				world.setBlockState(pos, Blocks.GLOWSTONE.getDefaultState());
			else
				explodeBlock(world, pos, neighbor, block);
		}
	}

	private void explodeBlock(World world, BlockPos pos, Block neighbor, Block block)
	{
		if(neighbor instanceof BlockCausticFluid)
		{
			float explosionStrength = ((BlockCausticFluid)neighbor).getExplosionStrength();
			if(((BlockCausticFluid)block).getExplosionStrength() > explosionStrength)
				explosionStrength = ((BlockCausticFluid)block).getExplosionStrength();
			world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), explosionStrength, true);
		}
	}
	
	public int getFluidID()
	{
		return fluidId;
	}
}
