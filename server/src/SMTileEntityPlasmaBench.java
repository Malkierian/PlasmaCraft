package net.minecraft.src;

import java.net.URISyntaxException;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode


public class SMTileEntityPlasmaBench extends TileEntity
    implements IInventory
{
    public SMTileEntityPlasmaBench()
    {
        furnaceItemStacks = new ItemStack[12];
        furnaceBurnTime = 0;
        currentItemBurnTime = 0;
        furnaceCookTime = 0;
    }
    
    public int getSizeInventory()
    {
    	return furnaceItemStacks.length;
    }

    public ItemStack getStackInSlot(int i)
    {
        return furnaceItemStacks[i];
    }

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

    public String getInvName()
    {
        return "Plasmificator";
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
        furnaceItemStacks = new ItemStack[getSizeInventory()];
        for(int i = 0; i < nbttaglist.tagCount(); i++)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
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
                nbttaglist.setTag(nbttagcompound1);
            }
        }

        nbttagcompound.setTag("Items", nbttaglist);
    }

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

    public void updateEntity()
    {
        boolean flag = furnaceBurnTime > 0;
        boolean flag1 = false;
        if(furnaceBurnTime > 0)
        {
            furnaceBurnTime--;
        }
        if(!worldObj.singleplayerWorld)
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
                SMBlockPlasmificator.updatePlasmificatorBlockState(furnaceBurnTime > 0, worldObj, xCoord, yCoord, zCoord);
            }
        }
        if(flag1)
        {
            onInventoryChanged();
        }
    }

	private int getAvailableSourceIndices(int[] indices)
	{
		final int maxCount = 10; // 1 regular slot + 9 queue slots
		final int sourceIndices[] = { 0, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
		int count = 0;
		for(int sourceIndex = 0; sourceIndex < maxCount; sourceIndex++)
		{
			int index = sourceIndices[sourceIndex];
			if(furnaceItemStacks[index] == null)
			{
				continue;
			}
			ItemStack recipeResult = SMPlasmaRecipes.getInstance().getPlasmaRecipe(furnaceItemStacks[index].getItem().shiftedIndex);
			if(recipeResult == null)
			{
				continue;
			}
			indices[count] = index;
			count++;
		}
		return count;
	}

	private int getAvailableDestIndex(ItemStack recipeResult)
	{
		final int maxCount = 10; // 1 regular slot + 9 queue slots
		final int destIndices[] = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

		for(int destIndex = 0; destIndex < maxCount; destIndex++)
		{
			int index = destIndices[destIndex];
			if(furnaceItemStacks[index] == null)
			{
				return index;
			}
			if(!furnaceItemStacks[index].isItemEqual(recipeResult))
			{
				continue;
			}
			if(furnaceItemStacks[index].stackSize < getInventoryStackLimit() && furnaceItemStacks[index].stackSize < furnaceItemStacks[index].getMaxStackSize())
			{
				return index;
			}
        	if(furnaceItemStacks[index].stackSize < recipeResult.getMaxStackSize())
        	{
				return index;
			}
		}

		return -1;
	}

	private boolean canSmelt()
	{
		final int maxCount = 10; // 1 regular slot + 9 queue slots
		int[] sourceIndices = new int[maxCount];
		int sourceCount = getAvailableSourceIndices(sourceIndices);
		if(sourceCount == 0)
		{
			return false;
		}

		for(int testIndex = 0; testIndex < sourceCount; testIndex++)
		{
			// At this point we know that the item is smeltable, but is there a destination?
			int index = sourceIndices[testIndex];
			ItemStack recipeResult = SMPlasmaRecipes.getInstance().getPlasmaRecipe(furnaceItemStacks[index].getItem().shiftedIndex);
			int destIndex = getAvailableDestIndex(recipeResult);
			if(destIndex != -1)
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

		final int maxCount = 10; // 1 regular slot + 9 queue slots
		int[] sourceIndices = new int[maxCount];
		int sourceCount = getAvailableSourceIndices(sourceIndices);
		if(sourceCount == 0)
		{
			return;
		}

		int destIndex = 0;
		int sourceIndex = 0;
		for(int testIndex = 0; testIndex < sourceCount; testIndex++)
		{
			int index = sourceIndices[testIndex];
			ItemStack recipeResult = SMPlasmaRecipes.getInstance().getPlasmaRecipe(furnaceItemStacks[index].getItem().shiftedIndex);
			destIndex = getAvailableDestIndex(recipeResult);
			if(destIndex != -1)
			{
				sourceIndex = index;
				break;
			}
		}

        ItemStack itemstack = SMPlasmaRecipes.getInstance().getPlasmaRecipe(furnaceItemStacks[sourceIndex].getItem().shiftedIndex);
        if(furnaceItemStacks[destIndex] == null)
        {
            furnaceItemStacks[destIndex] = itemstack.copy();
        } else
        if(furnaceItemStacks[destIndex].itemID == itemstack.itemID)
        {
            furnaceItemStacks[destIndex].stackSize++;
        }
        furnaceItemStacks[sourceIndex].stackSize--;
        if(furnaceItemStacks[sourceIndex].stackSize <= 0)
        {
            furnaceItemStacks[sourceIndex] = null;
        }
    }

    private int getItemBurnTime(ItemStack itemstack)
    {
        if(itemstack == null)
        {
            return 0;
        }
        int i = itemstack.getItem().shiftedIndex;
        if(i == mod_PlasmaCraft.plasmaGel.shiftedIndex)
        {
            return 1000;
        }
        if(i == mod_PlasmaCraft.plasma.shiftedIndex)
        {
            return 4000;
        }
        if(i == mod_PlasmaCraft.BatteryCharged.shiftedIndex)
        {
            return 4000;
        }
        if(i == mod_PlasmaCraft.radioniteVial.shiftedIndex)
        {
			return 8000;
		}
        if(i == mod_PlasmaCraft.BatteryPlasma.shiftedIndex)
        {
			return 8000;
		}
        if(i == mod_PlasmaCraft.plutoniumVial.shiftedIndex)
        {
			return 20000;
		}
        return 0;
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        if(worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this)
        {
            return false;
        }
        return entityplayer.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
    }
    private ItemStack furnaceItemStacks[];
    public int furnaceBurnTime;
    public int currentItemBurnTime;
    public int furnaceCookTime;

	public void openChest() 
	{
		return;
	}

	public void closeChest() 
	{
		return;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) 
	{
		return true;
	}

}
