package untouchedwagons.minecraft.plasmacraft.blocks;

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

import untouchedwagons.minecraft.plasmacraft.PlasmaCraft;
import untouchedwagons.minecraft.plasmacraft.entity.EntityCausticBoat;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import untouchedwagons.minecraft.plasmacraft.items.ItemIngot;

public class BlockCausticFluid extends BlockFluidClassic
{
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons;
	private int armorTick;
	private int fluidId;

	public BlockCausticFluid(Fluid fluid, Material material)
	{
		super(fluid, material);
		setLightLevel(0.6F);
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		return side!= 0 && side != 1 ? icons[1] : icons[0];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon_registrar)
	{
		icons = new IIcon[] {icon_registrar.registerIcon(PlasmaCraft.MOD_ID + ":" + fluidName),
				icon_registrar.registerIcon(PlasmaCraft.MOD_ID + ":" + fluidName + "_flow")};
	}
	
	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion)
	{
		if(this.fluidName.equals("acid"))
		{
			return;
		}

		if(PlasmaCraft.config.destroy_source_blocks)
		{
			world.setBlockToAir(x, y, z);
			world.createExplosion(null, x, y, z, 3F, true);
		}
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		onEntityWalking(world, x, y, z, entity);
	}

	public void onEntityWalking(World world, int x, int y, int z, Entity entity)
	{
		if((entity instanceof EntityPlayer) || PlasmaCraft.proxy.getEntityInstanceOf(entity))
		{
			@SuppressWarnings("ConstantConditions")
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

			boolean flag = itemstack[3].getItem() == PlasmaCraft.items.hazmatHood;
			boolean flag1 = itemstack[2].getItem() == PlasmaCraft.items.hazmatJacket;
			boolean flag2 = itemstack[1].getItem() == PlasmaCraft.items.hazmatPants;
			boolean flag3 = itemstack[0].getItem() == PlasmaCraft.items.hazmatBoots;

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
                ItemStack itemStack = ent.getEntityItem();

				if (itemStack.getItem() == PlasmaCraft.items.ingots && itemStack.getItemDamage() == ItemIngot.RADIONITE_DAMAGE)
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
	
	
	private void setAdjoiningIDs(World world, int x, int y, int z, Block setTo, Block replace)
	{
		if(world.getBlock(x - 1, y, z) == replace)
		{
			world.setBlock(x - 1, y, z, setTo);
		}
		if(world.getBlock(x + 1, y, z) == replace)
		{
			world.setBlock(x + 1, y, z, setTo);
		}
		if(world.getBlock(x, y - 1, z) == replace)
		{
			world.setBlock(x, y - 1, z, setTo);
		}
		if(world.getBlock(x, y, z - 1) == replace)
		{
			world.setBlock(x, y, z - 1, setTo);
		}
		if(world.getBlock(x, y, z + 1) == replace)
		{
			world.setBlock(x, y, z + 1, setTo);
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

	private void checkForHarden(World world, int x, int y, int z)
	{
		if(world.getBlock(x, y, z) != this)
		{
			return;
		}

		List<Block> blockList = getAdjoiningBlocks(world, x, y, z);

        // TODO fix this mess
		if(this == PlasmaCraft.blocks.acidBlock)
		{
			if(blockList.contains(PlasmaCraft.blocks.radioniteBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.plutoniumBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.neptuniumBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.netherflowBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.uraniumBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.obsidiumBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.obsidian, PlasmaCraft.blocks.obsidiumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.cryoniteBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.cobblestone, PlasmaCraft.blocks.cryoniteBlock);
				return;
			}
			if(blockList.contains(Blocks.lava))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.sand, Blocks.lava);
				return;
			}
			if(blockList.contains(Blocks.water))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.clay, Blocks.water);
			}
		}
		else if(this == PlasmaCraft.blocks.radioniteBlock)
		{
			if(blockList.contains(Blocks.lava))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.sand, Blocks.lava);
				return;
			}
			if(blockList.contains(Blocks.water))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.clay, Blocks.water);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.acidBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.plutoniumBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.neptuniumBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.netherflowBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.glowstone, PlasmaCraft.blocks.netherflowBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.uraniumBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.obsidiumBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.obsidian, PlasmaCraft.blocks.obsidiumBlock);
			}
		}
		else if(this == PlasmaCraft.blocks.plutoniumBlock)
		{
			if(blockList.contains(Blocks.lava))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.sand, Blocks.lava);
				return;
			}
			if(blockList.contains(Blocks.water))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.clay, Blocks.water);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.acidBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.radioniteBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.neptuniumBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.netherflowBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.netherrack, PlasmaCraft.blocks.netherflowBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.uraniumBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.obsidiumBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.obsidian, PlasmaCraft.blocks.obsidiumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.cryoniteBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
			}
		}
		else if(this == PlasmaCraft.blocks.neptuniumBlock)
		{
			if(blockList.contains(Blocks.lava))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.sand, Blocks.lava);
				return;
			}
			if(blockList.contains(Blocks.water))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.clay, Blocks.water);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.acidBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.radioniteBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.plutoniumBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.netherflowBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.netherrack, PlasmaCraft.blocks.netherflowBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.uraniumBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.sand, PlasmaCraft.blocks.uraniumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.obsidiumBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.obsidian, PlasmaCraft.blocks.obsidiumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.cryoniteBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
			}
		}
		else if(this == PlasmaCraft.blocks.netherflowBlock)
		{
			if(blockList.contains(Blocks.lava))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.sand, Blocks.lava);
				return;
			}
			if(blockList.contains(Blocks.water))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.clay, Blocks.water);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.acidBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.radioniteBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.glowstone, PlasmaCraft.blocks.radioniteBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.plutoniumBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.netherrack, PlasmaCraft.blocks.plutoniumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.neptuniumBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.netherrack, PlasmaCraft.blocks.neptuniumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.uraniumBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.soul_sand, PlasmaCraft.blocks.uraniumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.obsidiumBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.obsidian, PlasmaCraft.blocks.obsidiumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.cryoniteBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.glowstone, PlasmaCraft.blocks.cryoniteBlock);
			}
		}
		else if(this == PlasmaCraft.blocks.uraniumBlock)
		{
			if(blockList.contains(Blocks.lava))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.sand, Blocks.lava);
				return;
			}
			if(blockList.contains(Blocks.water))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.clay, Blocks.water);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.acidBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.radioniteBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.plutoniumBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.neptuniumBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.sand, PlasmaCraft.blocks.neptuniumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.netherflowBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.soul_sand, PlasmaCraft.blocks.netherflowBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.obsidiumBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.obsidian, PlasmaCraft.blocks.obsidiumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.cryoniteBlock))
			{
				world.createExplosion(null, x, y, z, 6F, true);
			}
		}
		else if(this == PlasmaCraft.blocks.obsidiumBlock)
		{
			if(blockList.contains(Blocks.lava))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.sand, Blocks.lava);
				return;
			}
			if(blockList.contains(Blocks.water))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.clay, Blocks.water);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.acidBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.obsidian, PlasmaCraft.blocks.acidBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.radioniteBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.obsidian, PlasmaCraft.blocks.radioniteBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.plutoniumBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.obsidian, PlasmaCraft.blocks.plutoniumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.neptuniumBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.obsidian, PlasmaCraft.blocks.neptuniumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.netherflowBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.obsidian, PlasmaCraft.blocks.netherflowBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.uraniumBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.obsidian, PlasmaCraft.blocks.uraniumBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.cryoniteBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.obsidian, PlasmaCraft.blocks.cryoniteBlock);
			}
		}
		else if(this == PlasmaCraft.blocks.cryoniteBlock)
		{
			if(blockList.contains(Blocks.lava))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.cobblestone, Blocks.lava);
				return;
			}
			if(blockList.contains(Blocks.water))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.ice, Blocks.water);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.acidBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.cobblestone, PlasmaCraft.blocks.acidBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.plutoniumBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.neptuniumBlock))
			{
				world.createExplosion(null, x, y, z, 3F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.netherflowBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.glowstone, PlasmaCraft.blocks.netherflowBlock);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.uraniumBlock))
			{
				world.createExplosion(null, x, y, z, 6F, true);
				return;
			}
			if(blockList.contains(PlasmaCraft.blocks.obsidiumBlock))
			{
				setAdjoiningIDs(world, x, y, z, Blocks.obsidian, PlasmaCraft.blocks.obsidiumBlock);
			}
		}
	}

	public BlockCausticFluid setFluidId(int fluidId) {
		this.fluidId = fluidId;

		return this;
	}

    public int getFluidId() {
        return fluidId;
    }
}