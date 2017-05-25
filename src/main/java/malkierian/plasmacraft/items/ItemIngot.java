package malkierian.plasmacraft.items;

import java.util.List;

import malkierian.plasmacraft.PlasmaCraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemIngot extends ItemPlasma
{
    public static final int CRYONITE_DAMAGE = 0;
    public static final int LEAD_DAMAGE = 1;
    public static final int NEPTUNIUM_DAMAGE = 2;
    public static final int NETHERFLOW_DAMAGE = 3;
    public static final int OBSIDIUM_DAMAGE = 4;
    public static final int PLUTONIUM_DAMAGE = 5;
    public static final int RADIONITE_DAMAGE = 6;
    public static final int URANIUM_DAMAGE = 7;

    public ItemIngot()
    {
    	super("pc-ingot");
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return String.format("%s.%d", super.getUnlocalizedName(stack), stack.getItemDamage());
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List item_list)
    {
        item_list.add(new ItemStack(item, 1, CRYONITE_DAMAGE));
        item_list.add(new ItemStack(item, 1, LEAD_DAMAGE));
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
    	PlasmaCraft.proxy.registerItemRenderer(this, CRYONITE_DAMAGE, "ingot/ingot_" + CRYONITE_DAMAGE);
    	PlasmaCraft.proxy.registerItemRenderer(this, LEAD_DAMAGE, "ingot/ingot_" + LEAD_DAMAGE);
    	PlasmaCraft.proxy.registerItemRenderer(this, NEPTUNIUM_DAMAGE, "ingot/ingot_" + NEPTUNIUM_DAMAGE);
    	PlasmaCraft.proxy.registerItemRenderer(this, NETHERFLOW_DAMAGE, "ingot/ingot_" + NETHERFLOW_DAMAGE);
    	PlasmaCraft.proxy.registerItemRenderer(this, OBSIDIUM_DAMAGE, "ingot/ingot_" + OBSIDIUM_DAMAGE);
    	PlasmaCraft.proxy.registerItemRenderer(this, PLUTONIUM_DAMAGE, "ingot/ingot_" + PLUTONIUM_DAMAGE);
    	PlasmaCraft.proxy.registerItemRenderer(this, RADIONITE_DAMAGE, "ingot/ingot_" + RADIONITE_DAMAGE);
    	PlasmaCraft.proxy.registerItemRenderer(this, URANIUM_DAMAGE, "ingot/ingot_" + URANIUM_DAMAGE);
    }
}
