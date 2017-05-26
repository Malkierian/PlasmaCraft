package malkierian.plasmacraft.blocks;

import java.util.HashMap;
import java.util.Map;

import malkierian.plasmacraft.PlasmaCraft;
import malkierian.plasmacraft.init.PCFluids;
import malkierian.plasmacraft.init.PCItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
//		else if(!(entity instanceof EntityCausticBoat))
//		{
//			if(entity instanceof EntityItem)
//			{
//				EntityItem ent = (EntityItem) entity;
//                ItemStack itemStack = ent.getEntityItem();
//
//				if (itemStack.getItem() == PlasmaCraft.items.ingots && itemStack.getItemDamage() == ItemIngot.RADIONITE_DAMAGE)
//				{
//					return;
//				}
//			}
//			entity.setFire(3);
//			entity.attackEntityFrom(DamageSource.inFire, 10);
//		}
	}
    
    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
    	super.onBlockAdded(world, pos, state);
        this.checkForHarden(world, pos);
    }

    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor)
    {
    	super.onNeighborChange(world, pos, neighbor);
        this.checkForHarden((World)world, pos);
    }
	
	
//	private void setAdjoiningIDs(World world, BlockPos pos, Block setTo, Block replace)
//	{
//		if(world.getBlockState(x - 1, y, z) == replace)
//		{
//			world.setBlock(x - 1, y, z, setTo);
//		}
//		if(world.getBlock(x + 1, y, z) == replace)
//		{
//			world.setBlock(x + 1, y, z, setTo);
//		}
//		if(world.getBlock(x, y - 1, z) == replace)
//		{
//			world.setBlock(x, y - 1, z, setTo);
//		}
//		if(world.getBlock(x, y, z - 1) == replace)
//		{
//			world.setBlock(x, y, z - 1, setTo);
//		}
//		if(world.getBlock(x, y, z + 1) == replace)
//		{
//			world.setBlock(x, y, z + 1, setTo);
//		}
//	}
	
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
		if(world.getBlockState(pos).getBlock() != this)
		{
			return;
		}

		Map<Block, BlockPos> blockList = getAdjoiningBlocks(world, pos);

		for(Block block : blockList.keySet())
		{
			if(block == Blocks.LAVA)
			{
				if(this == PCFluids.cryoniteBlock)
					world.setBlockState(blockList.get(block), Blocks.COBBLESTONE.getDefaultState());
				else
					world.setBlockState(blockList.get(block), Blocks.SAND.getDefaultState());
			}
			else if(block == Blocks.WATER)
			{
				if(this == PCFluids.cryoniteBlock)
					world.setBlockState(blockList.get(block), Blocks.ICE.getDefaultState());
				else
					world.setBlockState(blockList.get(block), Blocks.CLAY.getDefaultState());
			}
			else if(this == PCFluids.acidBlock)
			{
				if(block == PCFluids.radioniteBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
					return;
				}
				if(block == PCFluids.plutoniumBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
					return;
				}
				if(block == PCFluids.neptuniumBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
					return;
				}
				if(block == PCFluids.netherflowBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
					return;
				}
				if(block == PCFluids.uraniumBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
					return;
				}
				if(block == PCFluids.obsidiumBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.OBSIDIAN.getDefaultState());
					return;
				}
				if(block == PCFluids.cryoniteBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.COBBLESTONE.getDefaultState());
					return;
				}
			}
			else if(this == PCFluids.radioniteBlock)
			{
				if(block == PCFluids.acidBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
					return;
				}
				if(block == PCFluids.plutoniumBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
					return;
				}
				if(block == PCFluids.neptuniumBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
					return;
				}
				if(block == PCFluids.netherflowBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.GLOWSTONE.getDefaultState());
					return;
				}
				if(block == PCFluids.uraniumBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
					return;
				}
				if(block == PCFluids.obsidiumBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.OBSIDIAN.getDefaultState());
				}
			}
			else if(this == PCFluids.plutoniumBlock)
			{
				if(block == PCFluids.acidBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
					return;
				}
				if(block == PCFluids.radioniteBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
					return;
				}
				if(block == PCFluids.neptuniumBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
					return;
				}
				if(block == PCFluids.netherflowBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.NETHERRACK.getDefaultState());
					return;
				}
				if(block == PCFluids.uraniumBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
					return;
				}
				if(block == PCFluids.obsidiumBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.OBSIDIAN.getDefaultState());
					return;
				}
				if(block == PCFluids.cryoniteBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
				}
			}
			else if(this == PCFluids.neptuniumBlock)
			{
				if(block == PCFluids.acidBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
					return;
				}
				if(block == PCFluids.radioniteBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
					return;
				}
				if(block == PCFluids.plutoniumBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
					return;
				}
				if(block == PCFluids.netherflowBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.NETHERRACK.getDefaultState());
					return;
				}
				if(block == PCFluids.uraniumBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.SAND.getDefaultState());
					return;
				}
				if(block == PCFluids.obsidiumBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.OBSIDIAN.getDefaultState());
					return;
				}
				if(block == PCFluids.cryoniteBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
				}
			}
			else if(this == PCFluids.netherflowBlock)
			{
				if(block == PCFluids.acidBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
					return;
				}
				if(block == PCFluids.radioniteBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.GLOWSTONE.getDefaultState());
					return;
				}
				if(block == PCFluids.plutoniumBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.NETHERRACK.getDefaultState());
					return;
				}
				if(block == PCFluids.neptuniumBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.NETHERRACK.getDefaultState());
					return;
				}
				if(block == PCFluids.uraniumBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.SOUL_SAND.getDefaultState());
					return;
				}
				if(block == PCFluids.obsidiumBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.OBSIDIAN.getDefaultState());
					return;
				}
				if(block == PCFluids.cryoniteBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.GLOWSTONE.getDefaultState());
				}
			}
			else if(this == PCFluids.uraniumBlock)
			{
				if(block == PCFluids.acidBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
					return;
				}
				if(block == PCFluids.radioniteBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
					return;
				}
				if(block == PCFluids.plutoniumBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
					return;
				}
				if(block == PCFluids.neptuniumBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.SAND.getDefaultState());
					return;
				}
				if(block == PCFluids.netherflowBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.SOUL_SAND.getDefaultState());
					return;
				}
				if(block == PCFluids.obsidiumBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.OBSIDIAN.getDefaultState());
					return;
				}
				if(block == PCFluids.cryoniteBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 6F, true);
				}
			}
			else if(this == PCFluids.obsidiumBlock)
			{
				if(block == PCFluids.acidBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.OBSIDIAN.getDefaultState());
					return;
				}
				if(block == PCFluids.radioniteBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.OBSIDIAN.getDefaultState());
					return;
				}
				if(block == PCFluids.plutoniumBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.OBSIDIAN.getDefaultState());
					return;
				}
				if(block == PCFluids.neptuniumBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.OBSIDIAN.getDefaultState());
					return;
				}
				if(block == PCFluids.netherflowBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.OBSIDIAN.getDefaultState());
					return;
				}
				if(block == PCFluids.uraniumBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.OBSIDIAN.getDefaultState());
					return;
				}
				if(block == PCFluids.cryoniteBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.OBSIDIAN.getDefaultState());
				}
			}
			else if(this == PCFluids.cryoniteBlock)
			{
				if(block == PCFluids.acidBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.COBBLESTONE.getDefaultState());
					return;
				}
				if(block == PCFluids.plutoniumBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
					return;
				}
				if(block == PCFluids.neptuniumBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 3F, true);
					return;
				}
				if(block == PCFluids.netherflowBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.GLOWSTONE.getDefaultState());
					return;
				}
				if(block == PCFluids.uraniumBlock)
				{
					world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 6F, true);
					return;
				}
				if(block == PCFluids.obsidiumBlock)
				{
					world.setBlockState(blockList.get(block), Blocks.OBSIDIAN.getDefaultState());
				}
			}
		}
	}
	
	public int getFluidID()
	{
		return fluidId;
	}
}
