package untouchedwagons.minecraft.plasmacraft.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import untouchedwagons.minecraft.plasmacraft.PlasmaCraft;
import untouchedwagons.minecraft.plasmacraft.blocks.BlockCausticFluid;

import java.util.List;

public class ItemVial extends Item
{
    private IIcon[] icons;

    public static final int EMPTY_DAMAGE = 0;
    public static final int ACID_DAMAGE = 1;
    public static final int CRYONITE_DAMAGE = 2;
    public static final int NEPTUNIUM_DAMAGE = 3;
    public static final int NETHERFLOW_DAMAGE = 4;
    public static final int OBSIDIUM_DAMAGE = 5;
    public static final int PLUTONIUM_DAMAGE = 6;
    public static final int RADIONITE_DAMAGE = 7;
    public static final int URANIUM_DAMAGE = 8;

    public final Block[] blocks;

    public ItemVial()
	{
		super();

        this.setUnlocalizedName("pc-vial");
		this.setHasSubtypes(true);
		this.maxStackSize = 8;

        this.blocks = new Block[]
                {
                        Blocks.air,
                        PlasmaCraft.blocks.acidBlock,
                        PlasmaCraft.blocks.cryoniteBlock,
                        PlasmaCraft.blocks.neptuniumBlock,
                        PlasmaCraft.blocks.netherflowBlock,
                        PlasmaCraft.blocks.obsidiumBlock,
                        PlasmaCraft.blocks.plutoniumBlock,
                        PlasmaCraft.blocks.radioniteBlock,
                        PlasmaCraft.blocks.uraniumBlock
                };
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		float f = 1.0F;
		float f1 = entityplayer.prevRotationPitch + (entityplayer.rotationPitch - entityplayer.prevRotationPitch) * f;
		float f2 = entityplayer.prevRotationYaw + (entityplayer.rotationYaw - entityplayer.prevRotationYaw) * f;
		double d = entityplayer.prevPosX + (entityplayer.posX - entityplayer.prevPosX) * (double)f;
		double d1 = (entityplayer.prevPosY + (entityplayer.posY - entityplayer.prevPosY) * (double)f + 1.6200000000000001D) - (double)entityplayer.yOffset;
		double d2 = entityplayer.prevPosZ + (entityplayer.posZ - entityplayer.prevPosZ) * (double)f;
		Vec3 vec3 = Vec3.createVectorHelper(d, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
		float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
		float f5 = -MathHelper.cos(-f1 * 0.01745329F);
		float f6 = MathHelper.sin(-f1 * 0.01745329F);
		float f7 = f4 * f5;
		float f8 = f6;
		float f9 = f3 * f5;
		double d3 = 5D;
		ItemStack returnStack = null;
		Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f8 * d3, (double)f9 * d3);
		MovingObjectPosition movingobjectposition = world.rayTraceBlocks(vec3, vec31, itemstack.getItemDamage() == EMPTY_DAMAGE);
		if(movingobjectposition == null)
		{
			return itemstack;
		}
		if(movingobjectposition.typeOfHit == MovingObjectType.BLOCK)
		{
			int x = movingobjectposition.blockX;
			int y = movingobjectposition.blockY;
			int z = movingobjectposition.blockZ;

			if(!world.canMineBlock(entityplayer, x, y, z))
			{
				return itemstack;
			}

			if(itemstack.getItemDamage() == EMPTY_DAMAGE)
			{
				Block block = world.getBlock(x, y, z);

                if (!(block instanceof BlockCausticFluid))
                    return itemstack;

                BlockCausticFluid bcf = (BlockCausticFluid) block;

				world.setBlockToAir(x, y, z);
                returnStack = new ItemStack(PlasmaCraft.items.vial, 1, bcf.getFluidId());

                if(!entityplayer.capabilities.isCreativeMode)
                    itemstack.stackSize--;

                entityplayer.inventory.addItemStackToInventory(returnStack);
                return itemstack;
			}
            else
			{
				if(movingobjectposition.sideHit == 0)
				{
					y--;
				}
				if(movingobjectposition.sideHit == 1 && world.getBlock(movingobjectposition.blockX, movingobjectposition.blockY, movingobjectposition.blockZ) != Blocks.snow)
				{
					y++;
				}
				if(movingobjectposition.sideHit == 2)
				{
					z--;
				}
				if(movingobjectposition.sideHit == 3)
				{
					z++;
				}
				if(movingobjectposition.sideHit == 4)
				{
					x--;
				}
				if(movingobjectposition.sideHit == 5)
				{
					x++;
				}
				if(world.isAirBlock(x, y, z) || !world.getBlock(x, y, z).getMaterial().isSolid())
				{
					world.setBlock(x, y, z, this.blocks[itemstack.getItemDamage()]);
					if(!entityplayer.capabilities.isCreativeMode)
					{
						itemstack.stackSize--;
						entityplayer.inventory.addItemStackToInventory(new ItemStack(PlasmaCraft.items.vial));
					}
					return itemstack;
				}
			}
		}

        return itemstack;
	}

    @Override
    public String getUnlocalizedName(ItemStack p_77667_1_) {
        return String.format("%s.%d", super.getUnlocalizedName(p_77667_1_), p_77667_1_.getItemDamage());
    }

    @Override
    public IIcon getIconFromDamage(int meta)
    {
        return this.icons[meta];
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List item_list)
    {
        item_list.add(new ItemStack(item, 1, EMPTY_DAMAGE));
        item_list.add(new ItemStack(item, 1, ACID_DAMAGE));
        item_list.add(new ItemStack(item, 1, CRYONITE_DAMAGE));
        item_list.add(new ItemStack(item, 1, NEPTUNIUM_DAMAGE));
        item_list.add(new ItemStack(item, 1, NETHERFLOW_DAMAGE));
        item_list.add(new ItemStack(item, 1, OBSIDIUM_DAMAGE));
        item_list.add(new ItemStack(item, 1, PLUTONIUM_DAMAGE));
        item_list.add(new ItemStack(item, 1, RADIONITE_DAMAGE));
        item_list.add(new ItemStack(item, 1, URANIUM_DAMAGE));
    }

    @Override
    public void registerIcons(IIconRegister icon_registrar)
    {
        this.icons = new IIcon[9];
        this.icons[EMPTY_DAMAGE] = icon_registrar.registerIcon("plasmacraft:vial_empty");
        this.icons[ACID_DAMAGE] = icon_registrar.registerIcon("plasmacraft:vial_acid");
        this.icons[CRYONITE_DAMAGE] = icon_registrar.registerIcon("plasmacraft:vial_cryonite");
        this.icons[NEPTUNIUM_DAMAGE] = icon_registrar.registerIcon("plasmacraft:vial_neptunium");
        this.icons[NETHERFLOW_DAMAGE] = icon_registrar.registerIcon("plasmacraft:vial_netherflow");
        this.icons[OBSIDIUM_DAMAGE] = icon_registrar.registerIcon("plasmacraft:vial_obsidium");
        this.icons[PLUTONIUM_DAMAGE] = icon_registrar.registerIcon("plasmacraft:vial_plutonium");
        this.icons[RADIONITE_DAMAGE] = icon_registrar.registerIcon("plasmacraft:vial_radionite");
        this.icons[URANIUM_DAMAGE] = icon_registrar.registerIcon("plasmacraft:vial_uranium");
    }
}
