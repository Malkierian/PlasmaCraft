package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

import java.util.List;

public class SMContainerPlasmaBench extends Container
{

    public SMContainerPlasmaBench(IInventory iinventory, SMTileEntityPlasmaBench tileentityplasma)
    {
        cookTime = 0;
        burnTime = 0;
        currBurnTime = 0;
        bench = tileentityplasma;
        addSlot(new Slot(tileentityplasma, 0, 55, 24));
        addSlot(new Slot(tileentityplasma, 1, 81, 57));
        addSlot(new Slot(tileentityplasma, 2, 114, 24));
		for(int i = 0; i < 9; i++)
		{
			addSlot(new Slot(tileentityplasma, 3 + i, 9 + i * 18, 84));
		}

        for(int i = 0; i < 3; i++)
        {
            for(int k = 0; k < 9; k++)
            {
            	addSlot(new Slot(iinventory, k + i * 9 + 9, 9 + k * 18, 117 + i * 18));
            }

        }

        for(int j = 0; j < 9; j++)
        {
        	addSlot(new Slot(iinventory, j, 9 + j * 18, 173));
        }

    }

    public void updateCraftingResults()
    {
        super.updateCraftingResults();
        for(int i = 0; i < crafters.size(); i++)
        {
            ICrafting icrafting = (ICrafting)crafters.get(i);
            if(cookTime != bench.furnaceCookTime)
            {
                icrafting.updateCraftingInventoryInfo(this, 0, bench.furnaceCookTime);
            }
            if(burnTime != bench.furnaceBurnTime)
            {
                icrafting.updateCraftingInventoryInfo(this, 1, bench.furnaceBurnTime);
            }
            if(currBurnTime != bench.currentItemBurnTime)
            {
                icrafting.updateCraftingInventoryInfo(this, 2, bench.currentItemBurnTime);
            }
        }

        cookTime = bench.furnaceCookTime;
        burnTime = bench.furnaceBurnTime;
        currBurnTime = bench.currentItemBurnTime;
    }

    public void updateProgressBar(int i, int j)
    {
        if(i == 0)
        {
            bench.furnaceCookTime = j;
        }
        if(i == 1)
        {
            bench.furnaceBurnTime = j;
        }
        if(i == 2)
        {
            bench.currentItemBurnTime = j;
        }
    }
    
    public ItemStack transferStackInSlot(int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(i);
        if(slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if(i == 2)
            {
                if(!mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }
            } else
            if(i >= 3 && i < 30)
            {
                if(!mergeItemStack(itemstack1, 30, 39, false))
                {
                    return null;
                }
            } else
            if(i >= 30 && i < 39)
            {
                if(!mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            } else
            if(!mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }
            if(itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            } else
            {
                slot.onSlotChanged();
            }
            if(itemstack1.stackSize != itemstack.stackSize)
            {
                slot.onPickupFromSlot(itemstack1);
            } else
            {
                return null;
            }
        }
        return itemstack;
    }


    public boolean isUsableByPlayer(EntityPlayer entityplayer)
    {
        return bench.canInteractWith(entityplayer);
    }

    private SMTileEntityPlasmaBench bench;
    private int cookTime;
    private int burnTime;
    private int currBurnTime;
    
	public boolean canInteractWith(EntityPlayer entityplayer) 
	{
		return true;
	}

}
