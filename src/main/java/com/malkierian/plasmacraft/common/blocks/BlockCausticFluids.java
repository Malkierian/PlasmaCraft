package com.malkierian.plasmacraft.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import com.malkierian.plasmacraft.common.EntityCausticBoat;
import com.malkierian.plasmacraft.common.PlasmaCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCausticFluids extends BlockFluidClassic
{
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons;
	private int armorTick;
	
	public BlockCausticFluids(Fluid fluid, Material material)
	{
		super(fluid, material);
		setCreativeTab(CreativeTabs.tabBlock);
		setLightLevel(0.8f);
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		return side!= 0 && side != 1 ? icons[1] : icons[0];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		icons = new IIcon[] {iconRegister.registerIcon(PlasmaCraft.MOD_ID + ":" + fluidName),
				iconRegister.registerIcon(PlasmaCraft.MOD_ID + ":" + fluidName + "_flow")};
	}

//	@Override
//	public int tickRate(World world)
//	{
//		if(this.liquid == PlasmaLiquid.ACID)
//		{
//			return 3;
//		}
//		if(this.liquid == PlasmaLiquid.RADIONITE)
//		{
//			return 20;
//		}
//		if(this.liquid == PlasmaLiquid.PLUTONIUM)
//		{
//			return 8;
//		}
//		if(this.liquid == PlasmaLiquid.NETHERFLOW)
//		{
//			return 5;
//		}
//		if(this.liquid == PlasmaLiquid.CRYONITE)
//		{
//			return 6;
//		}
//		if(this.liquid == PlasmaLiquid.NEPTUNIUM)
//		{
//			return 10;
//		}
//		if(this.liquid == PlasmaLiquid.URANIUM)
//		{
//			return 15;
//		}
//		return this.liquid != PlasmaLiquid.OBSIDIUM ? 5 : 25;
//	}
//
	@Override
	public void onBlockDestroyedByExplosion(World world, int i, int j, int k, Explosion explosion)
	{
		if(this.fluidName == "acid")
		{
			return;
		}
		if(PlasmaCraft.liquidSourceExplodesAfterCausticExplosion)
		{
			world.setBlockToAir(i, j, k);
			world.createExplosion(null, i, j, k, 4F, false);
		}
		else
		{
			return;
		}
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
	{
		onEntityWalking(world, i, j, k, entity);
	}

	public void onEntityWalking(World world, int i, int j, int k, Entity entity)
	{
		if((entity instanceof EntityPlayer) || PlasmaCraft.proxy.getEntityInstanceOf(entity))
		{
			EntityPlayer entityplayer = (EntityPlayer)entity;
			if(entityplayer.capabilities.isCreativeMode)
			{
				return;
			}
			if(entityplayer.hurtTime > 0)
			{
				return;
			}
			ItemStack itemstack[] = entityplayer.inventory.armorInventory;
			if(itemstack[0] == null || itemstack[1] == null || itemstack[2] == null || itemstack[3] == null)
			{
				if(entityplayer.ridingEntity != null && (entityplayer.ridingEntity instanceof EntityCausticBoat))
				{
					return;
				}
				else
				{
					entity.setFire(3);
					entityplayer.attackEntityFrom(DamageSource.inFire, 3);
					return;
				}
			}
			boolean flag = itemstack[3].getItem() == PlasmaCraft.hazmatHood;
			boolean flag1 = itemstack[2].getItem() == PlasmaCraft.hazmatJacket;
			boolean flag2 = itemstack[1].getItem() == PlasmaCraft.hazmatPants;
			boolean flag3 = itemstack[0].getItem() == PlasmaCraft.hazmatBoots;
			if(flag && flag1 && flag2 && flag3)
			{
				if(armorTick > 0)
				{
					armorTick--;
				}
				if(armorTick == 0)
				{
					entityplayer.inventory.damageArmor(1);
					armorTick = 100;
				}
			}
			else
			{
				entity.setFire(3);
				entityplayer.attackEntityFrom(DamageSource.inFire, 3);
			}
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
		else if(!(entity instanceof EntityCausticBoat))
		{
			if(entity instanceof EntityItem)
			{
				EntityItem ent = (EntityItem) entity;
				if(ent.getEntityItem().getItem() == PlasmaCraft.ingotRadionite)
				{
					return;
				}
			}
			entity.setFire(3);
			entity.attackEntityFrom(DamageSource.inFire, 10);
		}
	}
//    
//    @Override
//    public void onBlockAdded(World par1World, int par2, int par3, int par4)
//    {
//        this.checkForHarden(par1World, par2, par3, par4);
//    }
//
//    @Override
//    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
//    {
//        this.checkForHarden(par1World, par2, par3, par4);
//    }
//    
//    private int blockAdjoinsMaterial(Object[] blocks, Material material)
//	{
//    	int flag = 0;
//    	int stillID = material == Material.water ? Block.waterStill.blockID : Block.lavaStill.blockID;
//    	int movingID = material == Material.water ? Blocks.water : Block.lavaMoving.blockID;
//    	int back = -1, front = -1, down = -1, left = -1, right = -1;
//    	if(blocks[0] != null && !(blocks[0] instanceof BlockCausticFluids))
//    	{
//    		back = (Integer)blocks[0];
//    	}
//    	if(blocks[1] != null && !(blocks[1] instanceof BlockCausticFluids))
//    	{
//    		front = (Integer)blocks[1];
//    	}
//    	if(blocks[2] != null && !(blocks[2] instanceof BlockCausticFluids))
//    	{
//    		down = (Integer)blocks[2];
//    	}
//    	if(blocks[3] != null && !(blocks[3] instanceof BlockCausticFluids))
//    	{
//    		left = (Integer)blocks[3];
//    	}
//    	if(blocks[4] != null && !(blocks[4] instanceof BlockCausticFluids))
//    	{
//    		right = (Integer)blocks[4];
//    	}
//    	
//		if(back != -1 && (back == stillID || back == movingID))
//		{
//			flag |= 1;
//		}
//		if(front != -1 && (front == stillID || front == movingID))
//		{
//			flag |= 2;
//		}
//		if(down != -1 && (down == stillID || down == movingID))
//		{
//			flag |= 4;
//		}
//		if(left != -1 && (left == stillID || left == movingID))
//		{
//			flag |= 8;
//		}
//		if(right != -1 && (right == stillID || right == movingID))
//		{
//			flag |= 16;
//		}
//		return flag;
//	}
//    
//    private int blockAdjoinsBlock(Object[] blocks, BlockCausticFluids block)
//	{
//    	int flag = 0;
//    	int stillID = block.stillBlockID;
//    	int flowingID = block.flowingBlockID;
//    	BlockCausticFluids back = null, front = null, down = null, left = null, right = null;
//    	if(blocks[0] instanceof BlockCausticFluids)
//    	{
//    		back = (BlockCausticFluids)blocks[0];
//    	}
//    	if(blocks[1] instanceof BlockCausticFluids)
//    	{
//    		front = (BlockCausticFluids)blocks[1];
//    	}
//    	if(blocks[2] instanceof BlockCausticFluids)
//    	{
//    		down = (BlockCausticFluids)blocks[2];
//    	}
//    	if(blocks[3] instanceof BlockCausticFluids)
//    	{
//    		left = (BlockCausticFluids)blocks[3];
//    	}
//    	if(blocks[4] instanceof BlockCausticFluids)
//    	{
//    		right = (BlockCausticFluids)blocks[4];
//    	}
//    	
//		if(back != null && (back.blockID == stillID || back.blockID == flowingID))
//		{
//			flag |= 1;
//		}
//		if(front != null && (front.blockID == stillID || front.blockID == flowingID))
//		{
//			flag |= 2;
//		}
//		if(down != null && (down.blockID == stillID || down.blockID == flowingID))
//		{
//			flag |= 4;
//		}
//		if(left != null && (left.blockID == stillID || left.blockID == flowingID))
//		{
//			flag |= 8;
//		}
//		if(right != null && (right.blockID == stillID || right.blockID == flowingID))
//		{
//			flag |= 16;
//		}
//		return flag;
//	}
//
//	private void setAdjoiningIDs(World world, int i, int j, int k, int l, int flags)
//	{
//		if((flags & 1) == 1)
//		{
//			world.setBlock(i - 1, j, k, l);
//		}
//		if((flags & 2) == 2)
//		{
//			world.setBlock(i + 1, j, k, l);
//		}
//		if((flags & 4) == 4)
//		{
//			world.setBlock(i, j - 1, k, l);
//		}
//		if((flags & 8) == 8)
//		{
//			world.setBlock(i, j, k - 1, l);
//		}
//		if((flags & 16) == 16)
//		{
//			world.setBlock(i, j, k + 1, l);
//		}
//	}
//	
//	private Object[] getAdjoiningBlocks(World world, int i, int j, int k)
//	{
//		Object[] object = new Object[5];
//		int back = world.getBlock(i - 1, j, k);
//    	int front = world.getBlock(i + 1, j, k);
//    	int down = world.getBlock(i, j - 1, k);
//    	int left = world.getBlock(i, j, k - 1);
//    	int right = world.getBlock(i - 1, j, k + 1);
//    	if(Block.blocksList[back] instanceof BlockCausticFluids)
//    	{
//    		object[0] = Block.blocksList[back];
//    	}
//    	else
//    	{
//    		object[0] = back;
//    	}
//    	if(Block.blocksList[front] instanceof BlockCausticFluids)
//    	{
//    		object[1] = Block.blocksList[front];
//    	}
//    	else
//    	{
//    		object[1] = front;
//    	}
//    	if(Block.blocksList[down] instanceof BlockCausticFluids)
//    	{
//    		object[2] = Block.blocksList[down];
//    	}
//    	else
//    	{
//    		object[2] = down;
//    	}
//    	if(Block.blocksList[left] instanceof BlockCausticFluids)
//    	{
//    		object[3] = Block.blocksList[left];
//    	}
//    	else
//    	{
//    		object[3] = left;
//    	}
//    	if(Block.blocksList[right] instanceof BlockCausticFluids)
//    	{
//    		object[4] = Block.blocksList[right];
//    	}
//    	else
//    	{
//    		object[4] = right;
//    	}
//    	return object;
//	}
//
//	private void checkForHarden(World world, int i, int j, int k)
//	{
//		if(world.getBlock(i, j, k) != blockID)
//		{
//			return;
//		}
//		Object[] blocks = getAdjoiningBlocks(world, i, j, k);
//		if(liquid == PlasmaLiquid.ACID)
//		{
//			int flags = blockAdjoinsMaterial(blocks, Material.lava);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.sand.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsMaterial(blocks, Material.water);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.blockClay.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.radioniteMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.plutoniumMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.neptuniumMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.netherflowMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.uraniumMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.obsidiumMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.obsidian.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.cryoniteMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.cobblestone.blockID, flags);
//				return;
//			}
//		}
//		else if(liquid == PlasmaLiquid.RADIONITE)
//		{
//			int flags = blockAdjoinsMaterial(blocks, Material.lava);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.sand.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsMaterial(blocks, Material.water);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.blockClay.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.acidMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.plutoniumMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.neptuniumMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.netherflowMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.glowStone.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.uraniumMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.obsidiumMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.obsidian.blockID, flags);
//				return;
//			}
//		}
//		else if(liquid == PlasmaLiquid.PLUTONIUM)
//		{
//			int flags = blockAdjoinsMaterial(blocks, Material.lava);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.sand.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsMaterial(blocks, Material.water);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.blockClay.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.acidMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.radioniteMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.neptuniumMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.netherflowMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Blocks.netherrack, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.uraniumMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.obsidiumMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.obsidian.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.cryoniteMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//		}
//		else if(liquid == PlasmaLiquid.NEPTUNIUM)
//		{
//			int flags = blockAdjoinsMaterial(blocks, Material.lava);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.sand.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsMaterial(blocks, Material.water);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.blockClay.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.acidMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.radioniteMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.plutoniumMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.netherflowMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Blocks.netherrack, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.uraniumMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.sand.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.obsidiumMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.obsidian.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.cryoniteMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//		}
//		else if(liquid == PlasmaLiquid.NETHERFLOW)
//		{
//			int flags = blockAdjoinsMaterial(blocks, Material.lava);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.sand.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsMaterial(blocks, Material.water);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.blockClay.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.acidMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.radioniteMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.glowStone.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.plutoniumMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Blocks.netherrack, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.neptuniumMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Blocks.netherrack, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.uraniumMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.slowSand.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.obsidiumMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.obsidian.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.cryoniteMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.glowStone.blockID, flags);
//				return;
//			}
//		}
//		else if(liquid == PlasmaLiquid.URANIUM)
//		{
//			int flags = blockAdjoinsMaterial(blocks, Material.lava);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.sand.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsMaterial(blocks, Material.water);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.blockClay.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.acidMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.radioniteMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.plutoniumMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.neptuniumMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.sand.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.netherflowMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.slowSand.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.obsidiumMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.obsidian.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.cryoniteMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 8F, false);
//				return;
//			}
//		}
//		else if(liquid == PlasmaLiquid.OBSIDIUM)
//		{
//			int flags = blockAdjoinsMaterial(blocks, Material.lava);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.sand.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsMaterial(blocks, Material.water);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.blockClay.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.acidMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.obsidian.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.radioniteMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.obsidian.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.plutoniumMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.obsidian.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.neptuniumMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.obsidian.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.netherflowMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.obsidian.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.uraniumMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.obsidian.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.cryoniteMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.obsidian.blockID, flags);
//				return;
//			}
//		}
//		else if(liquid == PlasmaLiquid.CRYONITE)
//		{
//			int flags = blockAdjoinsMaterial(blocks, Material.lava);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.cobblestone.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsMaterial(blocks, Material.water);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Blocks.ice, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.acidMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.cobblestone.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.plutoniumMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.neptuniumMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.netherflowMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.glowStone.blockID, flags);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.uraniumMoving);
//			if(flags != 0)
//			{
//				world.createExplosion(null, i, j, k, 8F, false);
//				return;
//			}
//			flags = blockAdjoinsBlock(blocks, PlasmaCraft.obsidiumMoving);
//			if(flags != 0)
//			{
//				setAdjoiningIDs(world, i, j, k, Block.obsidian.blockID, flags);
//				return;
//			}
//		}
//	}
}