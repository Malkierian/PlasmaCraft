package malkierian.plasmacraft.items;

import java.util.List;

import malkierian.plasmacraft.PlasmaCraft;
import malkierian.plasmacraft.blocks.BlockCausticFluid;
import malkierian.plasmacraft.init.PCFluids;
import malkierian.plasmacraft.init.PCItems;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemVial extends ItemPlasma
{
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
		super("pc-vial");

		this.setHasSubtypes(true);
		this.maxStackSize = 8;

        this.blocks = new Block[] {
                Blocks.AIR,
                PCFluids.acidBlock,
                PCFluids.cryoniteBlock,
                PCFluids.neptuniumBlock,
                PCFluids.netherflowBlock,
                PCFluids.obsidiumBlock,
                PCFluids.plutoniumBlock,
                PCFluids.radioniteBlock,
                PCFluids.uraniumBlock
        };
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer playerIn, EnumHand hand)
	{
		float f = 1.0F;
		float f1 = playerIn.prevRotationPitch + (playerIn.rotationPitch - playerIn.prevRotationPitch) * f;
		float f2 = playerIn.prevRotationYaw + (playerIn.rotationYaw - playerIn.prevRotationYaw) * f;
		double d = playerIn.prevPosX + (playerIn.posX - playerIn.prevPosX) * (double)f;
		double d1 = (playerIn.prevPosY + (playerIn.posY - playerIn.prevPosY) * (double)f + 1.6200000000000001D) - (double)playerIn.renderOffsetY;
		double d2 = playerIn.prevPosZ + (playerIn.posZ - playerIn.prevPosZ) * (double)f;
		Vec3d vec3 = new Vec3d(d, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
		float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
		float f5 = -MathHelper.cos(-f1 * 0.01745329F);
		float f6 = MathHelper.sin(-f1 * 0.01745329F);
		float f7 = f4 * f5;
		float f8 = f6;
		float f9 = f3 * f5;
		double d3 = 5D;
		ItemStack returnStack = null;
		Vec3d vec31 = vec3.addVector((double)f7 * d3, (double)f8 * d3, (double)f9 * d3);
		RayTraceResult rayTraceResult = world.rayTraceBlocks(vec3, vec31, itemstack.getItemDamage() == EMPTY_DAMAGE);
		if(rayTraceResult == null)
		{
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
		}
		if(rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK)
		{
			BlockPos pos = rayTraceResult.getBlockPos();

			if(!world.canMineBlockBody(playerIn, pos))
			{
				return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
			}

			if(itemstack.getItemDamage() == EMPTY_DAMAGE)
			{
				IBlockState block = world.getBlockState(pos);

                if (!(block.getBlock() instanceof BlockCausticFluid))
                    return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);

                BlockCausticFluid bcf = (BlockCausticFluid) block.getBlock();

				world.setBlockToAir(pos);
                returnStack = new ItemStack(PCItems.vial, 1, bcf.getFluidID() + 1);

                if(!playerIn.capabilities.isCreativeMode)
                    itemstack.stackSize--;

                playerIn.inventory.addItemStackToInventory(returnStack);
				return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
			}
            else
			{
            	float x = pos.getX(), y = pos.getY(), z = pos.getZ();
				if(rayTraceResult.sideHit == EnumFacing.DOWN)
				{
					y--;
				}
				if(rayTraceResult.sideHit == EnumFacing.UP && world.getBlockState(pos).getBlock() != Blocks.SNOW)
				{
					y++;
				}
				if(rayTraceResult.sideHit == EnumFacing.NORTH)
				{
					z--;
				}
				if(rayTraceResult.sideHit == EnumFacing.SOUTH)
				{
					z++;
				}
				if(rayTraceResult.sideHit == EnumFacing.WEST)
				{
					x--;
				}
				if(rayTraceResult.sideHit == EnumFacing.EAST)
				{
					x++;
				}
				BlockPos newPos = new BlockPos(x, y, z);
				if(world.isAirBlock(newPos) || !world.getBlockState(newPos).getMaterial().isSolid() || 
						world.getBlockState(newPos).getBlock() instanceof BlockCausticFluid)
				{
					world.setBlockState(newPos, this.blocks[itemstack.getItemDamage()].getDefaultState());
					if(!playerIn.capabilities.isCreativeMode)
					{
						itemstack.stackSize--;
						playerIn.inventory.addItemStackToInventory(new ItemStack(PCItems.vial, 1, 0));
					}
					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
				}
			}
		}

		return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
	}

    @Override
    public String getUnlocalizedName(ItemStack itemStackIn) {
        return String.format("%s.%d", super.getUnlocalizedName(itemStackIn), itemStackIn.getItemDamage());
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
    public void registerItemModel(Item item)
    {
    	PlasmaCraft.proxy.registerItemRenderer(this, EMPTY_DAMAGE, "vial/pc-vial_" + EMPTY_DAMAGE);
    	PlasmaCraft.proxy.registerItemRenderer(this, ACID_DAMAGE, "vial/pc-vial_" + ACID_DAMAGE);
    	PlasmaCraft.proxy.registerItemRenderer(this, CRYONITE_DAMAGE, "vial/pc-vial_" + CRYONITE_DAMAGE);
    	PlasmaCraft.proxy.registerItemRenderer(this, NEPTUNIUM_DAMAGE, "vial/pc-vial_" + NEPTUNIUM_DAMAGE);
    	PlasmaCraft.proxy.registerItemRenderer(this, NETHERFLOW_DAMAGE, "vial/pc-vial_" + NETHERFLOW_DAMAGE);
    	PlasmaCraft.proxy.registerItemRenderer(this, OBSIDIUM_DAMAGE, "vial/pc-vial_" + OBSIDIUM_DAMAGE);
    	PlasmaCraft.proxy.registerItemRenderer(this, PLUTONIUM_DAMAGE, "vial/pc-vial_" + PLUTONIUM_DAMAGE);
    	PlasmaCraft.proxy.registerItemRenderer(this, RADIONITE_DAMAGE, "vial/pc-vial_" + RADIONITE_DAMAGE);
    	PlasmaCraft.proxy.registerItemRenderer(this, URANIUM_DAMAGE, "vial/pc-vial_" + URANIUM_DAMAGE);
    }
}
