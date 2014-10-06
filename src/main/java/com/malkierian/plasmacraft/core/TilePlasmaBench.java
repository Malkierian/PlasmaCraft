package com.malkierian.plasmacraft.core;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import com.malkierian.plasmacraft.core.blocks.BlockPlasmaBench;
import com.malkierian.plasmacraft.core.items.ItemVial;

public class TilePlasmaBench extends TileEntity	implements IInventory
{

	private ItemStack furnaceItemStacks[];
	public int furnaceBurnTime;
	public int currentItemBurnTime;
	public int furnaceCookTime;

	public TilePlasmaBench()
	{
		furnaceItemStacks = new ItemStack[12];
		furnaceBurnTime = 0;
		currentItemBurnTime = 0;
		furnaceCookTime = 0;
	}

	@Override
	public int getSizeInventory()
	{
		return furnaceItemStacks.length;
	}

	public int getStartInventorySide(int side) {
		if(side==0) return 1;
		if(side==1) return 0;
		return 2;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		return furnaceItemStacks[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j)
	{
		if(furnaceItemStacks[i] != null)
		{
			if(furnaceItemStacks[i].stackSize <= j)
			{
				ItemStack itemstack = furnaceItemStacks[i];
				furnaceItemStacks[i] = null;
				return itemstack;
			}
			ItemStack itemstack1 = furnaceItemStacks[i].splitStack(j);
			if(furnaceItemStacks[i].stackSize == 0)
			{
				furnaceItemStacks[i] = null;
			}
			return itemstack1;
		} else
		{
			return null;
		}
	}

	public void setInventorySlotContents(int i, ItemStack itemstack)
	{
		furnaceItemStacks[i] = itemstack;
		if(itemstack != null && itemstack.stackSize > getInventoryStackLimit())
		{
			itemstack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readFromNBT(nbttagcompound);
		NBTTagList nbttaglist = nbttagcompound.getTagList("Items", 10);
		furnaceItemStacks = new ItemStack[getSizeInventory()];
		for(int i = 0; i < nbttaglist.tagCount(); i++)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
			byte byte0 = nbttagcompound1.getByte("Slot");
			if(byte0 >= 0 && byte0 < furnaceItemStacks.length)
			{
				furnaceItemStacks[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		furnaceBurnTime = nbttagcompound.getShort("BurnTime");
		furnaceCookTime = nbttagcompound.getShort("CookTime");
		currentItemBurnTime = getItemBurnTime(furnaceItemStacks[1]);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setShort("BurnTime", (short)furnaceBurnTime);
		nbttagcompound.setShort("CookTime", (short)furnaceCookTime);
		NBTTagList nbttaglist = new NBTTagList();
		for(int i = 0; i < furnaceItemStacks.length; i++)
		{
			if(furnaceItemStacks[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				furnaceItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbttagcompound.setTag("Items", nbttaglist);
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	public int getCookProgressScaled(int i)
	{
		return (furnaceCookTime * i) / 200;
	}

	public int getBurnTimeRemainingScaled(int i)
	{
		if(currentItemBurnTime == 0)
		{
			currentItemBurnTime = 200;
		}
		return (furnaceBurnTime * i) / currentItemBurnTime;
	}

	public boolean isBurning()
	{
		return furnaceBurnTime > 0;
	}

	@Override
	public void updateEntity()
	{
		boolean flag = furnaceBurnTime > 0;
		boolean flag1 = false;
		if(furnaceBurnTime > 0)
		{
			furnaceBurnTime--;
		}
		if(!worldObj.isRemote)
		{
			if(furnaceBurnTime == 0 && canSmelt())
			{
				currentItemBurnTime = furnaceBurnTime = getItemBurnTime(furnaceItemStacks[1]);
				if(furnaceBurnTime > 0)
				{
					flag1 = true;
					if(furnaceItemStacks[1] != null)
					{
						furnaceItemStacks[1].stackSize--;
						if(furnaceItemStacks[1].stackSize == 0)
						{
							furnaceItemStacks[1] = null;
						}
					}
				}
			}
			if(isBurning() && canSmelt())
			{
				furnaceCookTime++;
				if(furnaceCookTime == 200)
				{
					furnaceCookTime = 0;
					smeltItem();
					flag1 = true;
				}
			} else
			{
				furnaceCookTime = 0;
			}
			if(flag != (furnaceBurnTime > 0))
			{
				flag1 = true;
				BlockPlasmaBench.updatePlasmificatorBlockState(furnaceBurnTime > 0, worldObj, xCoord, yCoord, zCoord);
			}
		}
		if(flag1)
		{
			updateContainingBlockInfo();
		}
	}

	private int getAvailableSourceIndices(int ai[])
	{
		int ai1[] = {
			0, 3, 4, 5, 6, 7, 8, 9, 10, 11
		};
		int i = 0;
		for(int j = 0; j < 10; j++)
		{
			int k = ai1[j];
			if(furnaceItemStacks[k] == null)
			{
				continue;
			}
			ItemStack itemstack = PlasmaRecipes.getInstance().getPlasmaRecipe(furnaceItemStacks[k].getItem(), furnaceItemStacks[k].getItemDamage());
			if(itemstack != null)
			{
				ai[i] = k;
				i++;
			}
		}

		return i;
	}

	private int getAvailableDestIndex(ItemStack itemstack)
	{
		int ai[] = {
			2, 3, 4, 5, 6, 7, 8, 9, 10, 11
		};
		for(int i = 0; i < 10; i++)
		{
			int j = ai[i];
			if(furnaceItemStacks[j] == null)
			{
				return j;
			}
			if(!furnaceItemStacks[j].isItemEqual(itemstack))
			{
				continue;
			}
			if(furnaceItemStacks[j].stackSize < getInventoryStackLimit() && furnaceItemStacks[j].stackSize < furnaceItemStacks[j].getMaxStackSize())
			{
				return j;
			}
			if(furnaceItemStacks[j].stackSize < itemstack.getMaxStackSize())
			{
				return j;
			}
		}

		return -1;
	}

	private boolean canSmelt()
	{
		int ai[] = new int[10];
		int i = getAvailableSourceIndices(ai);
		if(i == 0)
		{
			return false;
		}
		for(int j = 0; j < i; j++)
		{
			int k = ai[j];
			ItemStack itemstack = PlasmaRecipes.getInstance().getPlasmaRecipe(furnaceItemStacks[k].getItem(), furnaceItemStacks[k].getItemDamage());
//			Item item = furnaceItemStacks[k].getItem();
			int l = getAvailableDestIndex(itemstack);
			if(l != -1)
			{
				return true;
			}
		}

		return false;
	}

	public void smeltItem()
	{
		if(!canSmelt())
		{
			return;
		}
		int ai[] = new int[10];
		int i = getAvailableSourceIndices(ai);
		if(i == 0)
		{
			return;
		}
		int j = 0;
		int k = 0;
		int l = 0;
		do
		{
			if(l >= i)
			{
				break;
			}
			int i1 = ai[l];
			ItemStack itemstack1 = PlasmaRecipes.getInstance().getPlasmaRecipe(furnaceItemStacks[i1].getItem(), furnaceItemStacks[i1].getItemDamage());
			j = getAvailableDestIndex(itemstack1);
			if(j != -1)
			{
				k = i1;
				break;
			}
			l++;
		} while(true);
		ItemStack itemstack = PlasmaRecipes.getInstance().getPlasmaRecipe(furnaceItemStacks[k].getItem(), furnaceItemStacks[k].getItemDamage());
		if(furnaceItemStacks[j] == null)
		{
			furnaceItemStacks[j] = itemstack.copy();
		} else
		if(furnaceItemStacks[j] == itemstack)
		{
			furnaceItemStacks[j].stackSize++;
		}
		boolean smeltVial = (furnaceItemStacks[k].getItem() instanceof ItemVial);
		furnaceItemStacks[k].stackSize--;
		if(furnaceItemStacks[k].stackSize <= 0)
		{
			furnaceItemStacks[k] = null;
		}
		if(smeltVial)
		{
			j = 0;
			k = 0;
			l = 0;
			do
			{
				if(l >= i)
				{
					break;
				}
				ItemStack itemstack1 = new ItemStack(PlasmaCraft.causticVial);
				j = getAvailableDestIndex(itemstack1);
				if(j != -1)
				{
					break;
				}
				l++;
			} while(true);
			ItemStack itemstack2 = new ItemStack(PlasmaCraft.causticVial);
			if(j == -1)
			{
				Random rand = new Random();
				float f = rand.nextFloat() * 0.8F + 0.1F;
				float f1 = rand.nextFloat() * 0.8F + 0.1F;
				float f2 = rand.nextFloat() * 0.8F + 0.1F;
				float f3 = 0.05F;
				EntityItem entityitem = new EntityItem(worldObj, (float)xCoord + f, (float)yCoord + f1, (float)zCoord + f2, new ItemStack(PlasmaCraft.causticVial));
				entityitem.motionX = (float)rand.nextGaussian() * f3;
				entityitem.motionY = (float)rand.nextGaussian() * f3 + 0.2F;
				entityitem.motionZ = (float)rand.nextGaussian() * f3;
				worldObj.spawnEntityInWorld(entityitem);
			} else
			if(furnaceItemStacks[j] == null)
			{
				furnaceItemStacks[j] = itemstack2.copy();
			} else
			if(furnaceItemStacks[j] == itemstack2)
			{
				furnaceItemStacks[j].stackSize++;
			}
		}
	}

	private int getItemBurnTime(ItemStack itemstack)
	{
		if(itemstack == null)
		{
			return 0;
		}
		Item i = itemstack.getItem();
		if(i == Items.redstone)
		{
			return 250;
		}
		if(i == PlasmaCraft.goopAcid)
		{
			return 1000;
		}
		if(i == PlasmaCraft.plasma)
		{
			return 4000;
		}
//		if(i == PlasmaCraft.batteryCharged.itemID)
//		{
//			return 4000;
//		}
		if(i == PlasmaCraft.radioniteVial)
		{
			return 8000;
		}
//		if(i == PlasmaCraft.batteryPlasma.itemID)
//		{
//			return 8000;
//		}
		return i != PlasmaCraft.plutoniumVial ? 0 : 20000;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		return null;
	}

	@Override
	public String getInventoryName()
	{
		return "Plasmificator";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return true;
	}

	@Override
	public void openInventory()
	{
	}

	@Override
	public void closeInventory()
	{
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
	{
		return false;
	}
}
