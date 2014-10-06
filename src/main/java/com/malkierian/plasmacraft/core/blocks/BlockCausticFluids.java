package com.malkierian.plasmacraft.core.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import com.malkierian.plasmacraft.core.PlasmaCraft;
import com.malkierian.plasmacraft.core.entities.EntityCausticBoat;

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
    
    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
    	super.onBlockAdded(world, x, y, z);
        this.checkForHarden(world, x, y, z);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
    	super.onNeighborBlockChange(world, x, y, z, block);
        this.checkForHarden(world, x, y, z);
    }
	
	
	private void setAdjoiningIDs(World world, int i, int j, int k, Block setTo, Block replace)
	{
		if(world.getBlock(i - 1, j, k) == replace)
		{
			world.setBlock(i - 1, j, k, setTo);
		}
		if(world.getBlock(i + 1, j, k) == replace)
		{
			world.setBlock(i + 1, j, k, setTo);
		}
		if(world.getBlock(i, j - 1, k) == replace)
		{
			world.setBlock(i, j - 1, k, setTo);
		}
		if(world.getBlock(i, j, k - 1) == replace)
		{
			world.setBlock(i, j, k - 1, setTo);
		}
		if(world.getBlock(i, j, k + 1) == replace)
		{
			world.setBlock(i, j, k + 1, setTo);
		}
	}
	
	private List<Block> getAdjoiningBlocks(World world, int i, int j, int k)
	{
		List<Block> list = new ArrayList<Block>();
		list.add(world.getBlock(i - 1, j, k));
    	list.add(world.getBlock(i + 1, j, k));
    	list.add(world.getBlock(i, j - 1, k));
    	list.add(world.getBlock(i, j, k - 1));
    	list.add(world.getBlock(i - 1, j, k + 1));
    	return list;
	}

	private void checkForHarden(World world, int i, int j, int k)
	{
		if(world.getBlock(i, j, k) != this)
		{
			return;
		}
		List<Block> blockList = getAdjoiningBlocks(world, i, j, k);
		if(this == PlasmaCraft.acidBlock)
		{
			if(blockList.contains(PlasmaCraft.radioniteBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.plutoniumBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.neptuniumBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.netherflowBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.uraniumBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.obsidiumBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.obsidian, PlasmaCraft.obsidiumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.cryoniteBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.cobblestone, PlasmaCraft.cryoniteBlock);
				return;
			}
			if(blockList.contains(Blocks.lava))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.sand, Blocks.lava);
				return;
			}
			if(blockList.contains(Blocks.water))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.clay, Blocks.water);
				return;
			}
		}
		else if(this == PlasmaCraft.radioniteBlock)
		{
			if(blockList.contains(Blocks.lava))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.sand, Blocks.lava);
				return;
			}
			if(blockList.contains(Blocks.water))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.clay, Blocks.water);
				return;
			}
			if(blockList.contains(PlasmaCraft.acidBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.plutoniumBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.neptuniumBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.netherflowBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.glowstone, PlasmaCraft.netherflowBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.uraniumBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.obsidiumBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.obsidian, PlasmaCraft.obsidiumBlock);
				return;
			}
		}
		else if(this == PlasmaCraft.plutoniumBlock)
		{
			if(blockList.contains(Blocks.lava))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.sand, Blocks.lava);
				return;
			}
			if(blockList.contains(Blocks.water))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.clay, Blocks.water);
				return;
			}
			if(blockList.contains(PlasmaCraft.acidBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.radioniteBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.neptuniumBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.netherflowBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.netherrack, PlasmaCraft.netherflowBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.uraniumBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.obsidiumBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.obsidian, PlasmaCraft.obsidiumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.cryoniteBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
		}
		else if(this == PlasmaCraft.neptuniumBlock)
		{
			if(blockList.contains(Blocks.lava))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.sand, Blocks.lava);
				return;
			}
			if(blockList.contains(Blocks.water))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.clay, Blocks.water);
				return;
			}
			if(blockList.contains(PlasmaCraft.acidBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.radioniteBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.plutoniumBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.netherflowBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.netherrack, PlasmaCraft.netherflowBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.uraniumBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.sand, PlasmaCraft.uraniumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.obsidiumBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.obsidian, PlasmaCraft.obsidiumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.cryoniteBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
		}
		else if(this == PlasmaCraft.netherflowBlock)
		{
			if(blockList.contains(Blocks.lava))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.sand, Blocks.lava);
				return;
			}
			if(blockList.contains(Blocks.water))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.clay, Blocks.water);
				return;
			}
			if(blockList.contains(PlasmaCraft.acidBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.radioniteBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.glowstone, PlasmaCraft.radioniteBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.plutoniumBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.netherrack, PlasmaCraft.plutoniumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.neptuniumBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.netherrack, PlasmaCraft.neptuniumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.uraniumBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.soul_sand, PlasmaCraft.uraniumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.obsidiumBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.obsidian, PlasmaCraft.obsidiumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.cryoniteBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.glowstone, PlasmaCraft.cryoniteBlock);
				return;
			}
		}
		else if(this == PlasmaCraft.uraniumBlock)
		{
			if(blockList.contains(Blocks.lava))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.sand, Blocks.lava);
				return;
			}
			if(blockList.contains(Blocks.water))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.clay, Blocks.water);
				return;
			}
			if(blockList.contains(PlasmaCraft.acidBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.radioniteBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.plutoniumBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.neptuniumBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.sand, PlasmaCraft.neptuniumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.netherflowBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.soul_sand, PlasmaCraft.netherflowBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.obsidiumBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.obsidian, PlasmaCraft.obsidiumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.cryoniteBlock))
			{
				world.createExplosion(null, i, j, k, 8F, false);
				return;
			}
		}
		else if(this == PlasmaCraft.obsidiumBlock)
		{
			if(blockList.contains(Blocks.lava))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.sand, Blocks.lava);
				return;
			}
			if(blockList.contains(Blocks.water))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.clay, Blocks.water);
				return;
			}
			if(blockList.contains(PlasmaCraft.acidBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.obsidian, PlasmaCraft.acidBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.radioniteBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.obsidian, PlasmaCraft.radioniteBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.plutoniumBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.obsidian, PlasmaCraft.plutoniumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.neptuniumBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.obsidian, PlasmaCraft.neptuniumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.netherflowBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.obsidian, PlasmaCraft.netherflowBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.uraniumBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.obsidian, PlasmaCraft.uraniumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.cryoniteBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.obsidian, PlasmaCraft.cryoniteBlock);
				return;
			}
		}
		else if(this == PlasmaCraft.cryoniteBlock)
		{
			if(blockList.contains(Blocks.lava))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.cobblestone, Blocks.lava);
				return;
			}
			if(blockList.contains(Blocks.water))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.ice, Blocks.water);
				return;
			}
			if(blockList.contains(PlasmaCraft.acidBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.cobblestone, PlasmaCraft.acidBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.plutoniumBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.neptuniumBlock))
			{
				world.createExplosion(null, i, j, k, 4F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.netherflowBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.glowstone, PlasmaCraft.netherflowBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.uraniumBlock))
			{
				world.createExplosion(null, i, j, k, 8F, false);
				return;
			}
			if(blockList.contains(PlasmaCraft.obsidiumBlock))
			{
				setAdjoiningIDs(world, i, j, k, Blocks.obsidian, PlasmaCraft.obsidiumBlock);
				return;
			}
		}
	}
}