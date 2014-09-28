package com.malkierian.Plasmacraft.common.blocks;

import net.minecraftforge.fluids.BlockFluidBase;


public class BlockCausticFluids //extends BlockFluidBase
{
//	public int stillBlockID;
//	public int flowingBlockID;
//	public int armorTick;
//	private PlasmaLiquid liquid;
//
//	protected Icon[] icons;
//	private static String[] names = {""};
//
//	public BlockCausticFluids(int blockid, int stillBlockId, int flowingBlockId)
//	{
//		super(blockid, Material.water);
//		float f = 0.0F;
//		float f1 = 0.0F;
//		stillBlockID = stillBlockId;
//		flowingBlockID = flowingBlockId;
//		armorTick = 0;
//	}
//	
//	public BlockCausticFluids setPlasmaLiquid(PlasmaLiquid liquid)
//	{
//		this.liquid = liquid;
//		return this;
//	}
//	
//	@SideOnly(value = Side.CLIENT)
//	@Override
//	public int getBlockColor()
//	{
//		return 0xffffff;
//	}
//
//	@SideOnly(value = Side.CLIENT)
//	@Override
//	public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k)
//	{
//		return 0xffffff;
//	}
//	
//	@Override
//	public Icon getIcon(int par1, int par2)
//	{
//		return par1 != 0 && par1 != 1 ? icons[1] : icons[0];
//	}
//
//	@Override
//	public void onBlockDestroyedByExplosion(World world, int i, int j, int k, Explosion explosion)
//	{
//		if((blockID == PlasmaCraft.acidMoving.blockID) | (blockID == PlasmaCraft.acidStill.blockID))
//		{
//			return;
//		}
//		if(PlasmaCraft.liquidSourceExplodesAfterCausticExplosion)
//		{
//			world.setBlockToAir(i, j, k);
//			world.createExplosion(null, i, j, k, 4F, false);
//		}
//		else
//		{
//			return;
//		}
//	}
//
//	public int tickRate(World par1World)
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
//    @SideOnly(Side.CLIENT)
//    public void registerIcons(IconRegister par1IconRegister)
//    {
//        if (this.blockID == PlasmaCraft.acidMoving.blockID || blockID == PlasmaCraft.acidStill.blockID)
//        {
//            this.icons = new Icon[] {par1IconRegister.registerIcon("acid"), par1IconRegister.registerIcon("acid_flow")};
//        }
//        else if (this.blockID == PlasmaCraft.cryoniteMoving.blockID || blockID == PlasmaCraft.cryoniteStill.blockID)
//        {
//            this.icons = new Icon[] {par1IconRegister.registerIcon("cryonite"), par1IconRegister.registerIcon("cryonite_flow")};
//        }
//        else if (this.blockID == PlasmaCraft.neptuniumMoving.blockID || blockID == PlasmaCraft.neptuniumStill.blockID)
//        {
//            this.icons = new Icon[] {par1IconRegister.registerIcon("neptunium"), par1IconRegister.registerIcon("neptunium_flow")};
//        }
//        else if (this.blockID == PlasmaCraft.netherflowMoving.blockID || blockID == PlasmaCraft.netherflowStill.blockID)
//        {
//            this.icons = new Icon[] {par1IconRegister.registerIcon("netherflow"), par1IconRegister.registerIcon("netherflow_flow")};
//        }
//        else if (this.blockID == PlasmaCraft.obsidiumMoving.blockID || blockID == PlasmaCraft.obsidiumStill.blockID)
//        {
//            this.icons = new Icon[] {par1IconRegister.registerIcon("obsidium"), par1IconRegister.registerIcon("obsidium_flow")};
//        }
//        else if (this.blockID == PlasmaCraft.plutoniumMoving.blockID || blockID == PlasmaCraft.plutoniumStill.blockID)
//        {
//            this.icons = new Icon[] {par1IconRegister.registerIcon("plutonium"), par1IconRegister.registerIcon("plutonium_flow")};
//        }
//        else if (this.blockID == PlasmaCraft.radioniteMoving.blockID || blockID == PlasmaCraft.radioniteStill.blockID)
//        {
//            this.icons = new Icon[] {par1IconRegister.registerIcon("radionite"), par1IconRegister.registerIcon("radionite_flow")};
//        }
//        else if (this.blockID == PlasmaCraft.uraniumMoving.blockID || blockID == PlasmaCraft.uraniumStill.blockID)
//        {
//            this.icons = new Icon[] {par1IconRegister.registerIcon("uranium"), par1IconRegister.registerIcon("uranium_flow")};
//        }
//    }
//
//    @SideOnly(Side.CLIENT)
//    public static Icon func_94424_b(String par0Str)
//    {
//        return par0Str == "acid" ? PlasmaCraft.acidMoving.icons[0] :
//        	(par0Str == "acid_flow" ? PlasmaCraft.acidMoving.icons[1] :
//        		(par0Str == "cryonite" ? PlasmaCraft.cryoniteMoving.icons[0] :
//        			(par0Str == "cryonite_flow" ? PlasmaCraft.cryoniteMoving.icons[1] : 
//        				(par0Str == "neptunium" ? PlasmaCraft.neptuniumMoving.icons[0] :
//                			(par0Str == "neptunium_flow" ? PlasmaCraft.neptuniumMoving.icons[1] :
//                				(par0Str == "netherflow" ? PlasmaCraft.netherflowMoving.icons[0] :
//                        			(par0Str == "netherflow_flow" ? PlasmaCraft.netherflowMoving.icons[1] :
//                        				(par0Str == "obsidium" ? PlasmaCraft.obsidiumMoving.icons[0] :
//                                			(par0Str == "obsidium_flow" ? PlasmaCraft.obsidiumMoving.icons[1] :
//                                				(par0Str == "plutonium" ? PlasmaCraft.plutoniumMoving.icons[0] :
//                                        			(par0Str == "plutonium_flow" ? PlasmaCraft.cryoniteMoving.icons[1] :
//                                        				(par0Str == "radionite" ? PlasmaCraft.radioniteMoving.icons[0] :
//                                                			(par0Str == "radionite_flow" ? PlasmaCraft.radioniteMoving.icons[1] :
//                                                				(par0Str == "uranium" ? PlasmaCraft.uraniumMoving.icons[0] :
//                                                        			(par0Str == "uranium_flow" ? PlasmaCraft.uraniumMoving.icons[1] : null)))))))))))))));
//    }
//
//	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
//	{
//		onEntityWalking(world, i, j, k, entity);
//	}
//
//	public void onEntityWalking(World world, int i, int j, int k, Entity entity)
//	{
//		if((entity instanceof EntityPlayer) || PlasmaCraft.proxy.getEntityInstanceOf(entity))
//		{
//			EntityPlayer entityplayer = (EntityPlayer)entity;
//			if(entityplayer.capabilities.isCreativeMode)
//			{
//				return;
//			}
//			if(entityplayer.hurtTime > 0)
//			{
//				return;
//			}
//			ItemStack itemstack[] = entityplayer.inventory.armorInventory;
//			if(itemstack[0] == null || itemstack[1] == null || itemstack[2] == null || itemstack[3] == null)
//			{
//				if(entityplayer.ridingEntity != null && (entityplayer.ridingEntity instanceof EntityCausticBoat))
//				{
//					return;
//				}
//				else
//				{
//					entity.setFire(3);
//					entityplayer.attackEntityFrom(DamageSource.inFire, 3);
//					return;
//				}
//			}
//			boolean flag = itemstack[3].itemID == PlasmaCraft.hazmatHood.itemID;
//			boolean flag1 = itemstack[2].itemID == PlasmaCraft.hazmatJacket.itemID;
//			boolean flag2 = itemstack[1].itemID == PlasmaCraft.hazmatPants.itemID;
//			boolean flag3 = itemstack[0].itemID == PlasmaCraft.hazmatBoots.itemID;
//			if(flag && flag1 && flag2 && flag3)
//			{
//				if(armorTick > 0)
//				{
//					armorTick--;
//				}
//				if(armorTick == 0)
//				{
//					entityplayer.inventory.damageArmor(1);
//					armorTick = 100;
//				}
//			}
//			else
//			{
//				entity.setFire(3);
//				entityplayer.attackEntityFrom(DamageSource.inFire, 3);
//			}
//		}
//		else if(entity instanceof EntityLiving)
//		{
//			EntityLiving entityliving = (EntityLiving)entity;
//			if(entityliving.hurtTime > 0)
//			{
//				return;
//			}
//			entity.setFire(3);
//			entityliving.attackEntityFrom(DamageSource.inFire, 3);
//		}
//		else if(!(entity instanceof EntityCausticBoat))
//		{
//			if(entity instanceof EntityItem)
//			{
//				EntityItem ent = (EntityItem) entity;
//				if(ent.getEntityItem().itemID == PlasmaCraft.ingotRadionite.itemID)
//				{
//					return;
//				}
//			}
//			entity.setFire(3);
//			entity.attackEntityFrom(DamageSource.inFire, 10);
//		}
//	}
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
//
//	@Override
//	public int stillLiquidId()
//	{
//		return stillBlockID;
//	}
//
//	@Override
//	public boolean isMetaSensitive()
//	{
//		return false;
//	}
//
//	@Override
//	public int stillLiquidMeta()
//	{
//		return 0;
//	}
}