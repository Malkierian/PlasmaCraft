package com.elvenwater.malkierian.Plasmacraft.common;

import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ICrafting;
import net.minecraft.src.IInventory;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

public class ContainerPlasmaBench extends Container
{
	
    private TilePlasmaBench bench;
    private int cookTime;
    private int burnTime;
    private int currBurnTime;

    public ContainerPlasmaBench(InventoryPlayer inventory, TilePlasmaBench tile)
    {
        cookTime = 0;
        burnTime = 0;
        currBurnTime = 0;
        bench = tile;
        addSlotToContainer(new Slot(tile, 0, 55, 24));
        addSlotToContainer(new Slot(tile, 1, 81, 57));
        addSlotToContainer(new Slot(tile, 2, 114, 24));
        for(int i = 0; i < 9; i++)
        {
            addSlotToContainer(new Slot(tile, 3 + i, 9 + i * 18, 84));
        }

        for(int j = 0; j < 3; j++)
        {
            for(int l = 0; l < 9; l++)
            {
                addSlotToContainer(new Slot(inventory, l + j * 9 + 9, 9 + l * 18, 117 + j * 18));
            }

        }

        for(int k = 0; k < 9; k++)
        {
            addSlotToContainer(new Slot(inventory, k, 9 + k * 18, 173));
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

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return bench.isUseableByPlayer(entityplayer);
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
}
