package untouchedwagons.minecraft.plasmacraft.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import untouchedwagons.minecraft.plasmacraft.PlasmaCraft;

import java.util.List;

public class ItemIngot extends Item {
    private IIcon[] icons;

    public static final int PLASMA_DAMAGE = 0;
    public static final int CRYONITE_DAMAGE = 1;
    public static final int LEAD_DAMAGE = 2;
    public static final int NEPTUNIUM_DAMAGE = 3;
    public static final int NETHERFLOW_DAMAGE = 4;
    public static final int OBSIDIUM_DAMAGE = 5;
    public static final int PLUTONIUM_DAMAGE = 6;
    public static final int RADIONITE_DAMAGE = 7;
    public static final int URANIUM_DAMAGE = 8;

    public ItemIngot() {
        this.setCreativeTab(PlasmaCraft.plasmaTab);
        this.setUnlocalizedName("pc-ingot");
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
        item_list.add(new ItemStack(item, 1, PLASMA_DAMAGE));
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
    public void registerIcons(IIconRegister icon_registrar)
    {
        this.icons = new IIcon[9];
        this.icons[PLASMA_DAMAGE] = icon_registrar.registerIcon("plasmacraft:ingots");
        this.icons[CRYONITE_DAMAGE] = icon_registrar.registerIcon("plasmacraft:ingotCryonite");
        this.icons[LEAD_DAMAGE] = icon_registrar.registerIcon("plasmacraft:ingotLead");
        this.icons[NEPTUNIUM_DAMAGE] = icon_registrar.registerIcon("plasmacraft:ingotNeptunium");
        this.icons[NETHERFLOW_DAMAGE] = icon_registrar.registerIcon("plasmacraft:ingotNetherflow");
        this.icons[OBSIDIUM_DAMAGE] = icon_registrar.registerIcon("plasmacraft:ingotObsidium");
        this.icons[PLUTONIUM_DAMAGE] = icon_registrar.registerIcon("plasmacraft:ingotPlutonium");
        this.icons[RADIONITE_DAMAGE] = icon_registrar.registerIcon("plasmacraft:ingotRadionite");
        this.icons[URANIUM_DAMAGE] = icon_registrar.registerIcon("plasmacraft:ingotUranium");
    }
}
