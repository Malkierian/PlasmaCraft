package malkierian.plasmacraft.init;

import malkierian.plasmacraft.PlasmaCraft;
import malkierian.plasmacraft.items.ItemAcidGrenade;
import malkierian.plasmacraft.items.ItemBattery;
import malkierian.plasmacraft.items.ItemCausticBoat;
import malkierian.plasmacraft.items.ItemEnergyWeapon;
import malkierian.plasmacraft.items.ItemGoop;
import malkierian.plasmacraft.items.ItemIngot;
import malkierian.plasmacraft.items.ItemPlasma;
import malkierian.plasmacraft.items.ItemPlasmaArmor;
import malkierian.plasmacraft.items.ItemVial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PCItems
{
    public static ItemGoop GOOP;
    public static ItemVial VIALS;
    public static ItemIngot INGOTS;
    public static ItemPlasma THERMOPELLET;
    public static Item ACID_GUN;
    public static ItemBattery BATTERY;
    public static Item ACID_GRENADE;
    public static Item CAUSTIC_BOAT;
    public static Item HAZMAT_HOOD;
    public static Item HAZMAT_JACKET;
    public static Item HAZMAT_PANTS;
    public static Item HAZMAT_BOOTS;
    public static Item PLASMA_LEATHER;
    public static Item BEAM_SPLITTER;
    public static Item ENERGY_CELL;
    public static Item CRYO_BLASTER;
    public static Item LASER_GUN;
    public static Item LASER_GUN_SPLIT;
    public static Item LASER_SHOTGUN;
    public static Item PLASMA_GUN;
    public static Item PLASMA_GUN_SPLIT;
    public static Item RAIL_GUN;
    
	public static void init()
	{
		VIALS = register(new ItemVial());
		GOOP = register(new ItemGoop());
		INGOTS = register(new ItemIngot());
		THERMOPELLET = register(new ItemPlasma("thermopellet"));
	    ACID_GUN = register(new ItemEnergyWeapon("acidGun", 200));
	    BATTERY = new ItemBattery();
	    ACID_GRENADE = new ItemAcidGrenade("acidGrenade");
	    CAUSTIC_BOAT = (new ItemCausticBoat()).setUnlocalizedName("causticBoat");
//	    HAZMAT_HOOD = (new ItemPlasmaArmor(ItemArmor.ArmorMaterial.GOLD, PlasmaCraft.proxy.addArmor("hazmat"), 0)).setUnlocalizedName("hazmatHood");
//	    HAZMAT_JACKET = (new ItemPlasmaArmor(ItemArmor.ArmorMaterial.GOLD, PlasmaCraft.proxy.addArmor("hazmat"), 1)).setUnlocalizedName("hazmatJacket");
//	    HAZMAT_PANTS = (new ItemPlasmaArmor(ItemArmor.ArmorMaterial.GOLD, PlasmaCraft.proxy.addArmor("hazmat"), 2)).setUnlocalizedName("hazmatPants");
//	    HAZMAT_BOOTS = (new ItemPlasmaArmor(ItemArmor.ArmorMaterial.GOLD, PlasmaCraft.proxy.addArmor("hazmat"), 3)).setUnlocalizedName("hazmatBoots");
	    PLASMA_LEATHER = (new ItemPlasma()).setUnlocalizedName("plasmaLeather");
	    BEAM_SPLITTER = (new ItemPlasma()).setUnlocalizedName("beamSplitter");
	    ENERGY_CELL = (new ItemPlasma()).setUnlocalizedName("energyCell");
	    CRYO_BLASTER = (new ItemEnergyWeapon("cryoblaster", 100));
	    LASER_GUN = (new ItemEnergyWeapon("lasergun", 200));
	    LASER_GUN_SPLIT = (new ItemEnergyWeapon("lasergunsplit", 300));
	    LASER_SHOTGUN = (new ItemEnergyWeapon("lasershotgun", 200));
	    PLASMA_GUN = (new ItemEnergyWeapon("plasmagun", 200));
	    PLASMA_GUN_SPLIT = (new ItemEnergyWeapon("plasmagunsplit", 300));
	    RAIL_GUN = (new ItemEnergyWeapon("railgun", 200));
	}
	
	private static <T extends Item> T register(T item)
	{
		GameRegistry.register(item);
		if(item instanceof ItemPlasma)
			((ItemPlasma)item).registerItemModel(item);
		return item;
	}
}
