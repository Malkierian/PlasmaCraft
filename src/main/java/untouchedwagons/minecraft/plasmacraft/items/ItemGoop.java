package untouchedwagons.minecraft.plasmacraft.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import untouchedwagons.minecraft.plasmacraft.PlasmaCraft;

import java.util.List;

public class ItemGoop extends Item {
    private IIcon[] icons;

    public static final int ACID_DAMAGE = 0;
    public static final int CRYONITE_DAMAGE = 1;
    public static final int NEPTUNIUM_DAMAGE = 2;
    public static final int NETHERFLOW_DAMAGE = 3;
    public static final int OBSIDIUM_DAMAGE = 4;
    public static final int PLUTONIUM_DAMAGE = 5;
    public static final int RADIONITE_DAMAGE = 6;
    public static final int URANIUM_DAMAGE = 7;

    public ItemGoop() {
        this.setCreativeTab(PlasmaCraft.plasmaTab);
        this.setUnlocalizedName("pc-goop");
        this.setHasSubtypes(true);
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
    public void registerIcons(IIconRegister icon_registrar)
    {
        this.icons = new IIcon[8];
        this.icons[ACID_DAMAGE] = icon_registrar.registerIcon("plasmacraft:goopAcid");
        this.icons[CRYONITE_DAMAGE] = icon_registrar.registerIcon("plasmacraft:goopCryonite");
        this.icons[NEPTUNIUM_DAMAGE] = icon_registrar.registerIcon("plasmacraft:goopNeptunium");
        this.icons[NETHERFLOW_DAMAGE] = icon_registrar.registerIcon("plasmacraft:goopNetherflow");
        this.icons[OBSIDIUM_DAMAGE] = icon_registrar.registerIcon("plasmacraft:goopObsidium");
        this.icons[PLUTONIUM_DAMAGE] = icon_registrar.registerIcon("plasmacraft:goopPlutonium");
        this.icons[RADIONITE_DAMAGE] = icon_registrar.registerIcon("plasmacraft:goopRadionite");
        this.icons[URANIUM_DAMAGE] = icon_registrar.registerIcon("plasmacraft:goopUranium");
    }
}
