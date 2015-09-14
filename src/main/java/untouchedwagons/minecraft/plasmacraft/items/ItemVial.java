package untouchedwagons.minecraft.plasmacraft.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import untouchedwagons.minecraft.plasmacraft.PlasmaCraft;
import untouchedwagons.minecraft.plasmacraft.blocks.PCBlocks;
import untouchedwagons.minecraft.plasmacraft.entities.EntityMutantCow;

public class ItemVial extends ItemPlasma
{
	private Block isFull;

	public ItemVial(Block block)
	{
		super();
		maxStackSize = 8;
		isFull = block;
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
		MovingObjectPosition movingobjectposition = world.rayTraceBlocks(vec3, vec31, isFull == Blocks.air);
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
			if(isFull == Blocks.air)
			{
				Block l = world.getBlock(x, y, z);
				if(l == PlasmaCraft.blocks.acidBlock && world.getBlockMetadata(x, y, z) == 0)
				{
					if(world.setBlockToAir(x, y, z))
					{
						returnStack = new ItemStack(PlasmaCraft.items.acidVial);
					}
				}
				else
				{
					if(l == PlasmaCraft.blocks.plutoniumBlock && world.getBlockMetadata(x, y, z) == 0)
					{
						world.setBlockToAir(x, y, z);
						returnStack = new ItemStack(PlasmaCraft.items.plutoniumVial);
					}
					if(l == PlasmaCraft.blocks.radioniteBlock && world.getBlockMetadata(x, y, z) == 0)
					{
						world.setBlockToAir(x, y, z);
						returnStack = new ItemStack(PlasmaCraft.items.radioniteVial);
					}
					if(l == PlasmaCraft.blocks.uraniumBlock && world.getBlockMetadata(x, y, z) == 0)
					{
						world.setBlockToAir(x, y, z);
						returnStack = new ItemStack(PlasmaCraft.items.uraniumVial);
					}
					if(l == PlasmaCraft.blocks.neptuniumBlock && world.getBlockMetadata(x, y, z) == 0)
					{
						world.setBlockToAir(x, y, z);
						returnStack = new ItemStack(PlasmaCraft.items.neptuniumVial);
					}
					if(l == PlasmaCraft.blocks.netherflowBlock && world.getBlockMetadata(x, y, z) == 0)
					{
						world.setBlockToAir(x, y, z);
						returnStack = new ItemStack(PlasmaCraft.items.netherflowVial);
					}
					if(l == PlasmaCraft.blocks.obsidiumBlock && world.getBlockMetadata(x, y, z) == 0)
					{
						world.setBlockToAir(x, y, z);
						returnStack = new ItemStack(PlasmaCraft.items.obsidiumVial);
					}
					if(l == PlasmaCraft.blocks.cryoniteBlock && world.getBlockMetadata(x, y, z) == 0)
					{
						world.setBlockToAir(x, y, z);
						returnStack = new ItemStack(PlasmaCraft.items.cryoniteVial);
					}
				}
				if(returnStack != null)
				{
					if(!entityplayer.capabilities.isCreativeMode)
						itemstack.stackSize--;
					entityplayer.inventory.addItemStackToInventory(returnStack);
					return itemstack;
				}
			} else
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
					world.setBlock(x, y, z, isFull);
					if(!entityplayer.capabilities.isCreativeMode)
					{
						itemstack.stackSize--;
						entityplayer.inventory.addItemStackToInventory(new ItemStack(PlasmaCraft.items.causticVial));
					}
					return itemstack;
				}
			}
		}
		if(isFull == Blocks.air && (movingobjectposition.entityHit instanceof EntityMutantCow)) {
            if (!entityplayer.capabilities.isCreativeMode)
                itemstack.stackSize--;

            entityplayer.inventory.addItemStackToInventory(new ItemStack(PlasmaCraft.items.acidVial));
        }

        return itemstack;
	}
}
