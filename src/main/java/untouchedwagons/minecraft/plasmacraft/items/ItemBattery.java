package untouchedwagons.minecraft.plasmacraft.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import untouchedwagons.minecraft.plasmacraft.PlasmaCraft;

import java.util.List;

public class ItemBattery extends Item {
    private IIcon[] icons;

    public static final int EMPTY_DAMAGE = 0;
    public static final int CHARGED_DAMAGE = 1;
    public static final int CRYO_DAMAGE = 2;
    public static final int PLASMA_DAMAGE = 3;
    public static final int OVERCHARGED_DAMAGE = 4;

    public ItemBattery() {
        this.setCreativeTab(PlasmaCraft.plasmaTab);
        this.setUnlocalizedName("pc-battery");
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
        item_list.add(new ItemStack(item, 1, EMPTY_DAMAGE));
        item_list.add(new ItemStack(item, 1, CHARGED_DAMAGE));
        item_list.add(new ItemStack(item, 1, CRYO_DAMAGE));
        item_list.add(new ItemStack(item, 1, PLASMA_DAMAGE));
        item_list.add(new ItemStack(item, 1, OVERCHARGED_DAMAGE));
    }

    @Override
    public void registerIcons(IIconRegister icon_registrar)
    {
        this.icons = new IIcon[5];
        this.icons[EMPTY_DAMAGE] = icon_registrar.registerIcon("plasmacraft:batteryEmpty");
        this.icons[CHARGED_DAMAGE] = icon_registrar.registerIcon("plasmacraft:batteryCharged");
        this.icons[CRYO_DAMAGE] = icon_registrar.registerIcon("plasmacraft:batteryCryonite");
        this.icons[PLASMA_DAMAGE] = icon_registrar.registerIcon("plasmacraft:batteryPlasma");
        this.icons[OVERCHARGED_DAMAGE] = icon_registrar.registerIcon("plasmacraft:batteryOvercharged");
    }
}
