package untouchedwagons.minecraft.plasmacraft.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import untouchedwagons.minecraft.plasmacraft.PlasmaCraft;

public class PCItems {
    public ItemGoop goop = new ItemGoop();
    public ItemIngot ingots = new ItemIngot();
    public ItemBattery battery = new ItemBattery();
    public ItemVial vial = new ItemVial();
    
    public Item acidGrenade = new ItemAcidGrenade().setUnlocalizedName("acidGrenade");
    public Item causticBoat = (new ItemCausticBoat()).setUnlocalizedName("causticBoat");
    public Item thermopellet = (new ItemPlasma()).setUnlocalizedName("thermopellet");

    public Item hazmatHood = (new ItemPlasmaArmor(ItemArmor.ArmorMaterial.GOLD, PlasmaCraft.proxy.addArmor("hazmat"), 0)).setUnlocalizedName("hazmatHood");
    public Item hazmatJacket = (new ItemPlasmaArmor(ItemArmor.ArmorMaterial.GOLD, PlasmaCraft.proxy.addArmor("hazmat"), 1)).setUnlocalizedName("hazmatJacket");
    public Item hazmatPants = (new ItemPlasmaArmor(ItemArmor.ArmorMaterial.GOLD, PlasmaCraft.proxy.addArmor("hazmat"), 2)).setUnlocalizedName("hazmatPants");
    public Item hazmatBoots = (new ItemPlasmaArmor(ItemArmor.ArmorMaterial.GOLD, PlasmaCraft.proxy.addArmor("hazmat"), 3)).setUnlocalizedName("hazmatBoots");
    
    public Item plasmaLeather = (new ItemPlasma()).setUnlocalizedName("plasmaLeather");

    public Item beamSplitter = (new ItemPlasma()).setUnlocalizedName("beamSplitter");
    public Item energyCell = (new ItemPlasma()).setUnlocalizedName("energyCell");
    
    public Item acidgun = (new ItemEnergyWeapon(200)).setUnlocalizedName("acidGun");
    public Item cryoblaster = (new ItemEnergyWeapon(100)).setUnlocalizedName("cryoBlaster");
    public Item lasergun = (new ItemEnergyWeapon(200)).setUnlocalizedName("laserGun");
    public Item lasergunsplit = (new ItemEnergyWeapon(300)).setUnlocalizedName("laserGunSplit");
    public Item lasershotgun = (new ItemEnergyWeapon(200)).setUnlocalizedName("laserShotgun");
    public Item plasmagun = (new ItemEnergyWeapon(200)).setUnlocalizedName("plasmaGun");
    public Item plasmagunsplit = (new ItemEnergyWeapon(300)).setUnlocalizedName("plasmaGunSplit");
    public Item railgun = (new ItemEnergyWeapon(200)).setUnlocalizedName("railGun");
}
