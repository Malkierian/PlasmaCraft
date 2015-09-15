package untouchedwagons.minecraft.plasmacraft.extra;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class InventoryHelper {
    public static boolean consumeInventoryItem(InventoryPlayer inventoryPlayer, ItemStack stack)
    {
        for (int i = 0; i < inventoryPlayer.getSizeInventory(); i++)
        {
            ItemStack stack_in_slot = inventoryPlayer.getStackInSlot(i);
            if (!stack_in_slot.isItemEqual(stack))
                continue;

            if (--stack_in_slot.stackSize <= 0)
            {
                inventoryPlayer.setInventorySlotContents(i, null);
                return true;
            }
        }

        return false;
    }
}
