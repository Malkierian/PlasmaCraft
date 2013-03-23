package com.elvenwater.malkierian.Plasmacraft.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFluid;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.elvenwater.malkierian.Plasmacraft.common.EntityCausticBoat;
import com.elvenwater.malkierian.Plasmacraft.common.PlasmaCraft;
import com.elvenwater.malkierian.Plasmacraft.common.PlasmaLiquid;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCausticFluids extends BlockFluid
{
	public int stillBlockID;
	public int flowingBlockID;
	public int armorTick;
	private PlasmaLiquid liquid;

	protected Icon[] icons;
	private static String[] names = {""};

	public BlockCausticFluids(int blockid, int stillBlockId, int flowingBlockId)
	{
		super(blockid, Material.water);
		float f = 0.0F;
		float f1 = 0.0F;
		stillBlockID = stillBlockId;
		flowingBlockID = flowingBlockId;
		armorTick = 0;
	}
	
	public BlockCausticFluids setPlasmaLiquid(PlasmaLiquid liquid)
	{
		this.liquid = liquid;
		return this;
	}
	
	@SideOnly(value = Side.CLIENT)
	@Override
	public int getBlockColor()
	{
		return 0xffffff;
	}

	@SideOnly(value = Side.CLIENT)
	@Override
	public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k)
	{
		return 0xffffff;
	}
	
	@Override
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2)
	{
		return par1 != 0 && par1 != 1 ? icons[1] : icons[0];
	}

	@Override
	public void onBlockDestroyedByExplosion(World world, int i, int j, int k, Explosion explosion)
	{
		if((blockID == PlasmaCraft.acidMoving.blockID) | (blockID == PlasmaCraft.acidStill.blockID))
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

	public int tickRate(World par1World)
	{
		if(this.liquid == PlasmaLiquid.ACID)
		{
			return 3;
		}
		if(this.liquid == PlasmaLiquid.RADIONITE)
		{
			return 20;
		}
		if(this.liquid == PlasmaLiquid.PLUTONIUM)
		{
			return 8;
		}
		if(this.liquid == PlasmaLiquid.NETHERFLOW)
		{
			return 5;
		}
		if(this.liquid == PlasmaLiquid.CRYONITE)
		{
			return 6;
		}
		if(this.liquid == PlasmaLiquid.NEPTUNIUM)
		{
			return 10;
		}
		if(this.liquid == PlasmaLiquid.URANIUM)
		{
			return 15;
		}
		return this.liquid != PlasmaLiquid.OBSIDIUM ? 5 : 25;
	}

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        if (this.blockID == PlasmaCraft.acidMoving.blockID || blockID == PlasmaCraft.acidStill.blockID)
        {
            this.icons = new Icon[] {par1IconRegister.registerIcon("acid"), par1IconRegister.registerIcon("acid_flow")};
        }
        else if (this.blockID == PlasmaCraft.cryoniteMoving.blockID || blockID == PlasmaCraft.cryoniteStill.blockID)
        {
            this.icons = new Icon[] {par1IconRegister.registerIcon("cryonite"), par1IconRegister.registerIcon("cryonite_flow")};
        }
        else if (this.blockID == PlasmaCraft.neptuniumMoving.blockID || blockID == PlasmaCraft.neptuniumStill.blockID)
        {
            this.icons = new Icon[] {par1IconRegister.registerIcon("neptunium"), par1IconRegister.registerIcon("neptunium_flow")};
        }
        else if (this.blockID == PlasmaCraft.netherflowMoving.blockID || blockID == PlasmaCraft.netherflowStill.blockID)
        {
            this.icons = new Icon[] {par1IconRegister.registerIcon("netherflow"), par1IconRegister.registerIcon("netherflow_flow")};
        }
        else if (this.blockID == PlasmaCraft.obsidiumMoving.blockID || blockID == PlasmaCraft.obsidiumStill.blockID)
        {
            this.icons = new Icon[] {par1IconRegister.registerIcon("obsidium"), par1IconRegister.registerIcon("obsidium_flow")};
        }
        else if (this.blockID == PlasmaCraft.plutoniumMoving.blockID || blockID == PlasmaCraft.plutoniumStill.blockID)
        {
            this.icons = new Icon[] {par1IconRegister.registerIcon("plutonium"), par1IconRegister.registerIcon("plutonium_flow")};
        }
        else if (this.blockID == PlasmaCraft.radioniteMoving.blockID || blockID == PlasmaCraft.radioniteStill.blockID)
        {
            this.icons = new Icon[] {par1IconRegister.registerIcon("radionite"), par1IconRegister.registerIcon("radionite_flow")};
        }
        else if (this.blockID == PlasmaCraft.uraniumMoving.blockID || blockID == PlasmaCraft.uraniumStill.blockID)
        {
            this.icons = new Icon[] {par1IconRegister.registerIcon("uranium"), par1IconRegister.registerIcon("uranium_flow")};
        }
    }

    @SideOnly(Side.CLIENT)
    public static Icon func_94424_b(String par0Str)
    {
        return par0Str == "acid" ? PlasmaCraft.acidMoving.icons[0] :
        	(par0Str == "acid_flow" ? PlasmaCraft.acidMoving.icons[1] :
        		(par0Str == "cryonite" ? PlasmaCraft.cryoniteMoving.icons[0] :
        			(par0Str == "cryonite_flow" ? PlasmaCraft.cryoniteMoving.icons[1] : 
        				(par0Str == "neptunium" ? PlasmaCraft.neptuniumMoving.icons[0] :
                			(par0Str == "neptunium_flow" ? PlasmaCraft.neptuniumMoving.icons[1] :
                				(par0Str == "netherflow" ? PlasmaCraft.netherflowMoving.icons[0] :
                        			(par0Str == "netherflow_flow" ? PlasmaCraft.netherflowMoving.icons[1] :
                        				(par0Str == "obsidium" ? PlasmaCraft.obsidiumMoving.icons[0] :
                                			(par0Str == "obsidium_flow" ? PlasmaCraft.obsidiumMoving.icons[1] :
                                				(par0Str == "plutonium" ? PlasmaCraft.plutoniumMoving.icons[0] :
                                        			(par0Str == "plutonium_flow" ? PlasmaCraft.cryoniteMoving.icons[1] :
                                        				(par0Str == "radionite" ? PlasmaCraft.radioniteMoving.icons[0] :
                                                			(par0Str == "radionite_flow" ? PlasmaCraft.radioniteMoving.icons[1] :
                                                				(par0Str == "uranium" ? PlasmaCraft.uraniumMoving.icons[0] :
                                                        			(par0Str == "uranium_flow" ? PlasmaCraft.uraniumMoving.icons[1] : null)))))))))))))));
    }
    
    private boolean blockAdjoinsBlockID(World world, int i, int j, int k, int l)
	{
		boolean flag = false;
		if(world.getBlockMaterial(i, j, k - 1) == Material.water && world.getBlockId(i, j, k - 1) == l)
		{
			flag = true;
		}
		if(world.getBlockMaterial(i, j, k + 1) == Material.water && world.getBlockId(i, j, k + 1) == l)
		{
			flag = true;
		}
		if(world.getBlockMaterial(i - 1, j, k) == Material.water && world.getBlockId(i - 1, j, k) == l)
		{
			flag = true;
		}
		if(world.getBlockMaterial(i + 1, j, k) == Material.water && world.getBlockId(i + 1, j, k) == l)
		{
			flag = true;
		}
		if(world.getBlockMaterial(i, j - 1, k) == Material.water && world.getBlockId(i, j - 1, k) == l)
		{
			flag = true;
		}
		return flag;
	}

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
			boolean flag = itemstack[3].itemID == PlasmaCraft.hazmatHood.itemID;
			boolean flag1 = itemstack[2].itemID == PlasmaCraft.hazmatJacket.itemID;
			boolean flag2 = itemstack[1].itemID == PlasmaCraft.hazmatPants.itemID;
			boolean flag3 = itemstack[0].itemID == PlasmaCraft.hazmatBoots.itemID;
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
				if(ent.getEntityItem().itemID == PlasmaCraft.ingotRadionite.itemID)
				{
					return;
				}
			}
			entity.setFire(3);
			entity.attackEntityFrom(DamageSource.inFire, 10);
		}
	}

	private void setAdjoiningLavaIDs(World world, int i, int j, int k, int l)
	{
		if(world.getBlockId(i - 1, j, k) == Block.lavaStill.blockID || world.getBlockId(i - 1, j, k) == Block.lavaMoving.blockID)
		{
			world.setBlock(i - 1, j, k, l);
		}
		if(world.getBlockId(i + 1, j, k) == Block.lavaStill.blockID || world.getBlockId(i + 1, j, k) == Block.lavaMoving.blockID)
		{
			world.setBlock(i + 1, j, k, l);
		}
		if(world.getBlockId(i, j - 1, k) == Block.lavaStill.blockID || world.getBlockId(i, j - 1, k) == Block.lavaMoving.blockID)
		{
			world.setBlock(i, j - 1, k, l);
		}
		if(world.getBlockId(i, j, k - 1) == Block.lavaStill.blockID || world.getBlockId(i, j, k - 1) == Block.lavaMoving.blockID)
		{
			world.setBlock(i, j, k - 1, l);
		}
		if(world.getBlockId(i, j, k + 1) == Block.lavaStill.blockID || world.getBlockId(i, j, k + 1) == Block.lavaMoving.blockID)
		{
			world.setBlock(i, j, k + 1, l);
		}
	}

	private void setAdjoiningWaterIDs(World world, int i, int j, int k, int l)
	{
		if(world.getBlockId(i - 1, j, k) == Block.waterStill.blockID || world.getBlockId(i - 1, j, k) == Block.waterMoving.blockID)
		{
			world.setBlock(i - 1, j, k, l);
		}
		if(world.getBlockId(i + 1, j, k) == Block.waterStill.blockID || world.getBlockId(i + 1, j, k) == Block.waterMoving.blockID)
		{
			world.setBlock(i + 1, j, k, l);
		}
		if(world.getBlockId(i, j - 1, k) == Block.waterStill.blockID || world.getBlockId(i, j - 1, k) == Block.waterMoving.blockID)
		{
			world.setBlock(i, j - 1, k, l);
		}
		if(world.getBlockId(i, j, k - 1) == Block.waterStill.blockID || world.getBlockId(i, j, k - 1) == Block.waterMoving.blockID)
		{
			world.setBlock(i, j, k - 1, l);
		}
		if(world.getBlockId(i, j, k + 1) == Block.waterStill.blockID || world.getBlockId(i, j, k + 1) == Block.waterMoving.blockID)
		{
			world.setBlock(i, j, k + 1, l);
		}
	}

	private void checkForHarden(World world, int i, int j, int k)
	{
		if(world.getBlockId(i, j, k) != blockID)
		{
			return;
		}
		//TODO fix harden
//		if(blockIndexInTexture == PlasmaCraft.acidStillIndex || blockIndexInTexture == PlasmaCraft.acidMovingIndex)
//		{
//			if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
//			{
//				setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
//			{
//				setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.obsidian.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.cobblestone.blockID);
//			}
//		}
//		else if(blockIndexInTexture == PlasmaCraft.radioniteStillIndex || blockIndexInTexture == PlasmaCraft.radioniteMovingIndex)
//		{
//			if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
//			{
//				setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
//			{
//				setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.glowStone.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.obsidian.blockID);
//			}
//		}
//		else if(blockIndexInTexture == PlasmaCraft.plutoniumStillIndex || blockIndexInTexture == PlasmaCraft.plutoniumMovingIndex)
//		{
//			if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
//			{
//				setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
//			{
//				setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.netherrack.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.obsidian.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//		}
//		else if(blockIndexInTexture == PlasmaCraft.neptuniumStillIndex || blockIndexInTexture == PlasmaCraft.neptuniumMovingIndex)
//		{
//			if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
//			{
//				setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
//			{
//				setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.netherrack.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.sand.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.obsidian.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//		}
//		else if(blockIndexInTexture == PlasmaCraft.netherflowStillIndex || blockIndexInTexture == PlasmaCraft.netherflowMovingIndex)
//		{
//			if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
//			{
//				setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
//			{
//				setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.glowStone.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.netherrack.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.netherrack.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.slowSand.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.obsidian.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.glowStone.blockID);
//			}
//		}
//		else if(blockIndexInTexture == PlasmaCraft.uraniumStillIndex || blockIndexInTexture == PlasmaCraft.uraniumMovingIndex)
//		{
//			if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
//			{
//				setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
//			{
//				setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.sand.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.slowSand.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.obsidian.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 8F, false);
//			}
//		}
//		else if(blockIndexInTexture == PlasmaCraft.obsidiumStillIndex || blockIndexInTexture == PlasmaCraft.obsidiumMovingIndex)
//		{
//			if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
//			{
//				setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
//			{
//				setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.obsidian.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.radioniteMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.obsidian.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.obsidian.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.obsidian.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.obsidian.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.obsidian.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.cryoniteMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.obsidian.blockID);
//			}
//		}
//		else if(blockIndexInTexture == PlasmaCraft.cryoniteStillIndex || blockIndexInTexture == PlasmaCraft.cryoniteMovingIndex)
//		{
//			if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
//			{
//				setAdjoiningLavaIDs(world, i, j, k, Block.cobblestone.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
//			{
//				setAdjoiningWaterIDs(world, i, j, k, Block.ice.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.acidMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.cobblestone.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.plutoniumMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.neptuniumMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 4F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.netherflowMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.glowStone.blockID);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.uraniumMoving.blockID))
//			{
//				world.createExplosion(null, i, j, k, 8F, false);
//			}
//			else if(blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumStill.blockID) || blockAdjoinsBlockID(world, i, j, k, PlasmaCraft.obsidiumMoving.blockID))
//			{
//				world.setBlock(i, j, k, Block.obsidian.blockID);
//			}
//		}
	}
}