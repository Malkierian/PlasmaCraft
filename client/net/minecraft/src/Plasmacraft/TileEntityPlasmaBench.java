package net.minecraft.src.Plasmacraft;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.TileEntity;

public class TileEntityPlasmaBench extends TileEntity
    implements IInventory
{

    private ItemStack furnaceItemStacks[];
    public int furnaceBurnTime;
    public int currentItemBurnTime;
    public int furnaceCookTime;

    public TileEntityPlasmaBench()
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
        if(!worldObj.multiplayerWorld)
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
                BlockPlasmificator.updatePlasmificatorBlockState(furnaceBurnTime > 0, worldObj, xCoord, yCoord, zCoord);
            }
        }
        if(flag1)
        {
            onInventoryChanged();
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
            ItemStack itemstack = PlasmaRecipes.getInstance().getPlasmaRecipe(furnaceItemStacks[k].getItem().shiftedIndex);
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
            ItemStack itemstack = PlasmaRecipes.getInstance().getPlasmaRecipe(furnaceItemStacks[k].getItem().shiftedIndex);
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
            ItemStack itemstack1 = PlasmaRecipes.getInstance().getPlasmaRecipe(furnaceItemStacks[i1].getItem().shiftedIndex);
            j = getAvailableDestIndex(itemstack1);
            if(j != -1)
            {
                k = i1;
                break;
            }
            l++;
        } while(true);
        ItemStack itemstack = PlasmaRecipes.getInstance().getPlasmaRecipe(furnaceItemStacks[k].getItem().shiftedIndex);
        if(furnaceItemStacks[j] == null)
        {
            furnaceItemStacks[j] = itemstack.copy();
        } else
        if(furnaceItemStacks[j].itemID == itemstack.itemID)
        {
            furnaceItemStacks[j].stackSize++;
        }
        furnaceItemStacks[k].stackSize--;
        if(furnaceItemStacks[k].stackSize <= 0)
        {
            furnaceItemStacks[k] = null;
        }
    }

    private int getItemBurnTime(ItemStack itemstack)
    {
        if(itemstack == null)
        {
            return 0;
        }
        int i = itemstack.getItem().shiftedIndex;
        if(i == PlasmaCraftCore.plasmaGel.shiftedIndex)
        {
            return 1000;
        }
        if(i == PlasmaCraftCore.plasma.shiftedIndex)
        {
            return 4000;
        }
        if(i == PlasmaCraftCore.BatteryCharged.shiftedIndex)
        {
            return 4000;
        }
        if(i == PlasmaCraftCore.radioniteVial.shiftedIndex)
        {
            return 8000;
        }
        if(i == PlasmaCraftCore.BatteryPlasma.shiftedIndex)
        {
            return 8000;
        }
        return i != PlasmaCraftCore.plutoniumVial.shiftedIndex ? 0 : 20000;
    }

    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        if(worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this)
        {
            return false;
        } else
        {
            return entityplayer.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
        }
    }

    public void openChest()
    {
    }

    public void closeChest()
    {
    }
}
