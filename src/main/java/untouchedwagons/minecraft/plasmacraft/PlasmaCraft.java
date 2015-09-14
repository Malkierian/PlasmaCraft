package untouchedwagons.minecraft.plasmacraft;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import untouchedwagons.minecraft.plasmacraft.blocks.*;
import untouchedwagons.minecraft.plasmacraft.client.gui.GuiHandler;
import untouchedwagons.minecraft.plasmacraft.client.gui.PlasmaTab;
import untouchedwagons.minecraft.plasmacraft.common.FuelHandler;
import untouchedwagons.minecraft.plasmacraft.entities.EntityAcid;
import untouchedwagons.minecraft.plasmacraft.entities.EntityAcidGrenade;
import untouchedwagons.minecraft.plasmacraft.entities.EntityAcidTNTPrimed;
import untouchedwagons.minecraft.plasmacraft.entities.EntityCausticBoat;
import untouchedwagons.minecraft.plasmacraft.entities.EntityCryoBlast;
import untouchedwagons.minecraft.plasmacraft.entities.EntityLaser;
import untouchedwagons.minecraft.plasmacraft.entities.EntityLaserShotgun;
import untouchedwagons.minecraft.plasmacraft.entities.EntityPlasma;
import untouchedwagons.minecraft.plasmacraft.entities.EntityRailGun;
import untouchedwagons.minecraft.plasmacraft.items.ItemAcidGrenade;
import untouchedwagons.minecraft.plasmacraft.items.ItemCausticBoat;
import untouchedwagons.minecraft.plasmacraft.items.ItemEnergyWeapon;
import untouchedwagons.minecraft.plasmacraft.items.ItemPlasma;
import untouchedwagons.minecraft.plasmacraft.items.ItemPlasmaArmor;
import untouchedwagons.minecraft.plasmacraft.items.ItemVial;
import untouchedwagons.minecraft.plasmacraft.proxy.CommonProxy;
import untouchedwagons.minecraft.plasmacraft.tileentity.TilePlasmaBench;
import untouchedwagons.minecraft.plasmacraft.worldgen.WorldGenerator;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import untouchedwagons.minecraft.plasmacraft.items.*;

@Mod(modid = "plasmacraft", name = "PlasmaCraft", version = "0.4.0-beta1")
public class PlasmaCraft
{
	public static String MOD_ID = "plasmacraft";

    public static final PCBlocks blocks = new PCBlocks();
    public static final PCItems items = new PCItems();

	public static PlasmaTab plasmaTab;

    public static Configuration configuration;

    public static int causticID = 180;

    public static boolean liquidSourceExplodesAfterCausticExplosion;

	public static int leadOreVeinCount = 6;
	public static int leadOreVeinSize = 6;
	public static int leadOreYRange = 80;
	public static int leadOreYStart = 4;
	public static int neptuniumOreVeinCount = 6;
	public static int neptuniumOreVeinSize = 10;
	public static int neptuniumOreYRange = 64;
	public static int neptuniumOreYStart = 32;
	public static int neptuniumSpoutCount = 20;
	public static int neptuniumSpoutYRange = 64;
	public static int neptuniumSpoutYStart = 8;
	public static int netherflowLakeChance = 32;
	public static int netherflowLakeYCutoff = 96;
	public static int netherflowLakeYRange = 16;
	public static int netherflowLakeYStart = 56;
	public static int netherflowSpoutCount = 20;
	public static int netherflowSpoutYRange = 96;
	public static int netherflowSpoutYStart = 16;
	public static int obsidiumOreVeinCount = 4;
	public static int obsidiumOreVeinSize = 10;
	public static int obsidiumOreYRange = 64;
	public static int obsidiumOreYStart = 32;
	public static int plutoniumOreVeinCount = 4;
	public static int plutoniumOreVeinSize = 6;
	public static int plutoniumOreYRange = 16;
	public static int plutoniumOreYStart = 4;
	public static int radioniteOreVeinCount = 4;
	public static int radioniteOreVeinSize = 6;
	public static int radioniteOreYRange = 24;
	public static int radioniteOreYStart = 4;
	public static int uraniumOreVeinCount = 4;
	public static int uraniumOreVeinSize = 6;
	public static int uraniumOreYRange = 16;
	public static int uraniumOreYStart = 4;
	
	public static final int glowClothAcidMeta = 0;
	public static final int glowClothRadioniteMeta = 1;
	public static final int glowClothNetherflowMeta = 2;
	public static final int glowClothNeptuniumMeta = 3;
	public static final int glowClothUraniumMeta = 4;
	public static final int glowClothPlutoniumMeta = 5;
	public static final int glowClothCryoniteMeta = 6;
	public static final int glowClothObsidiumMeta = 7;

	public static boolean generateLead;
	public static boolean generateUranium;
	public static boolean generatePlutonium;

	public static int plasmaBenchFrontAnim;
	
	public static Comparator<ItemStack> tabSorter;
	
	// The instance of your mod that Forge uses.
	@Instance("PlasmaCraft")
	public static PlasmaCraft instance;
	
	private GuiHandler guiHandler = new GuiHandler();
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="com.malkierian.plasmacraft.client.ClientProxy", serverSide="com.malkierian.plasmacraft.common.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		instance = this;

        loadConfig(event);

        registerBlocks();
        registerFuel();
        registerItems();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);
		
		proxy.registerRenderers();
		
		plasmaTab = new PlasmaTab("PlasmaCraft");
		
		List<Item> order = Arrays.asList(Item.getItemFromBlock(blocks.orePlasma), Item.getItemFromBlock(blocks.glowCloth), Item.getItemFromBlock(blocks.frozenCryonite), Item.getItemFromBlock(blocks.reinforcedGlass),
				Item.getItemFromBlock(blocks.acidTnt), Item.getItemFromBlock(blocks.acidBarrier), Item.getItemFromBlock(blocks.plasmaBench),
				PlasmaCraft.items.goopAcid, PlasmaCraft.items.goopCryonite, PlasmaCraft.items.goopNeptunium, PlasmaCraft.items.goopNetherflow, PlasmaCraft.items.goopObsidium, PlasmaCraft.items.goopPlutonium, PlasmaCraft.items.goopRadionite, PlasmaCraft.items.goopUranium,
				PlasmaCraft.items.ingotCryonite, PlasmaCraft.items.ingotLead, PlasmaCraft.items.ingotNeptunium, PlasmaCraft.items.ingotNetherflow, PlasmaCraft.items.ingotObsidium, PlasmaCraft.items.ingotPlutonium, PlasmaCraft.items.ingotRadionite, PlasmaCraft.items.ingotUranium, PlasmaCraft.items.plasma,
				PlasmaCraft.items.acidVial, PlasmaCraft.items.causticVial, PlasmaCraft.items.cryoniteVial, PlasmaCraft.items.neptuniumVial, PlasmaCraft.items.netherflowVial, PlasmaCraft.items.obsidiumVial, PlasmaCraft.items.plutoniumVial, PlasmaCraft.items.radioniteVial, PlasmaCraft.items.uraniumVial,
				PlasmaCraft.items.causticBoat,
				PlasmaCraft.items.batteryEmpty, PlasmaCraft.items.batteryCryo, PlasmaCraft.items.batteryCharged, PlasmaCraft.items.batteryOvercharged, PlasmaCraft.items.batteryPlasma, PlasmaCraft.items.beamSplitter, PlasmaCraft.items.energyCell, PlasmaCraft.items.thermopellet,
				PlasmaCraft.items.acidgun, PlasmaCraft.items.cryoblaster, PlasmaCraft.items.lasershotgun, PlasmaCraft.items.lasergun, PlasmaCraft.items.lasergunsplit, PlasmaCraft.items.plasmagun, PlasmaCraft.items.plasmagunsplit, PlasmaCraft.items.railgun,
				PlasmaCraft.items.acidGrenade,
				PlasmaCraft.items.hazmatBoots, PlasmaCraft.items.hazmatHood, PlasmaCraft.items.hazmatJacket, PlasmaCraft.items.hazmatPants,
				PlasmaCraft.items.plasmaLeather);

		tabSorter = Ordering.explicit(order).onResultOf(new Function<ItemStack, Item>(){
			@Override
			public Item apply(ItemStack input)
			{
				return input.getItem();
			}
		});

        registerRecipes();
		
		registerOres();
		
		registerTileEntities();
		
		proxy.registerTextureFX();
		
		registerEntities();
		
		GameRegistry.registerWorldGenerator(new WorldGenerator(), 20);
	}
	
	private void registerBlocks()
	{
		FluidRegistry.registerFluid(PlasmaCraft.blocks.acidFluid);
		FluidRegistry.registerFluid(PlasmaCraft.blocks.cryoniteFluid);
		FluidRegistry.registerFluid(PlasmaCraft.blocks.neptuniumFluid);
		FluidRegistry.registerFluid(PlasmaCraft.blocks.netherflowFluid);
		FluidRegistry.registerFluid(PlasmaCraft.blocks.obsidiumFluid);
		FluidRegistry.registerFluid(PlasmaCraft.blocks.plutoniumFluid);
		FluidRegistry.registerFluid(PlasmaCraft.blocks.radioniteFluid);
		FluidRegistry.registerFluid(PlasmaCraft.blocks.uraniumFluid);

        GameRegistry.registerBlock(blocks.orePlasma, ItemPlasmaOre.class, "orePlasma");
		GameRegistry.registerBlock(blocks.acidBlock, "Acid");
		GameRegistry.registerBlock(blocks.cryoniteBlock, "Cryonite");
		GameRegistry.registerBlock(blocks.neptuniumBlock, "Neptunium");
		GameRegistry.registerBlock(blocks.netherflowBlock, "Netherflow");
		GameRegistry.registerBlock(blocks.obsidiumBlock, "Obsidium");
		GameRegistry.registerBlock(blocks.plutoniumBlock, "Plutonium");
		GameRegistry.registerBlock(blocks.radioniteBlock, "Radionite");
		GameRegistry.registerBlock(blocks.uraniumBlock, "Uranium");
		GameRegistry.registerBlock(blocks.glowCloth, ItemGlowCloth.class, "glowCloth");
		GameRegistry.registerBlock(blocks.frozenCryonite, "Frozen Cryonite");
		GameRegistry.registerBlock(blocks.reinforcedGlass, "Reinforced Glass");
		GameRegistry.registerBlock(blocks.plasmaBench, "Plasmificator");
		GameRegistry.registerBlock(blocks.acidBarrier, "Acid Barrier");
		GameRegistry.registerBlock(blocks.acidTnt, "Acid TNT");
	}
	
	private void registerEntities()
	{
		int entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityCausticBoat.class, "causticBoat", entityID);
		EntityRegistry.registerModEntity(EntityCausticBoat.class, "causticBoat", entityID, this, 32, 100, true);
		
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityAcidTNTPrimed.class, "acidTntPrimed", entityID);
		EntityRegistry.registerModEntity(EntityAcidTNTPrimed.class, "acidTntPrimed", entityID, this, 32, 100, false);
		
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityAcidGrenade.class, "acidGrenade", entityID);
		EntityRegistry.registerModEntity(EntityAcidGrenade.class, "acidGrenade", entityID, this, 32, 100, true);
		
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityLaser.class, "laser", entityID);
		EntityRegistry.registerModEntity(EntityLaser.class, "laser", entityID, this, 32, 100, true);
		
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityLaserShotgun.class, "laserShotgun", entityID);
		EntityRegistry.registerModEntity(EntityLaserShotgun.class, "laserShotgun", entityID, this, 32, 100, true);
		
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityPlasma.class, "plasma", entityID);
		EntityRegistry.registerModEntity(EntityPlasma.class, "plasma", entityID, this, 32, 100, true);
		
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityRailGun.class, "railGun", entityID);
		EntityRegistry.registerModEntity(EntityRailGun.class, "railGun", entityID, this, 32, 100, true);
		
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityAcid.class, "acid", entityID);
		EntityRegistry.registerModEntity(EntityAcid.class, "acid", entityID, this, 32, 100, true);
		
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityCryoBlast.class, "cryoBlast", entityID);
		EntityRegistry.registerModEntity(EntityCryoBlast.class, "cryoBlast", entityID, this, 32, 100, true);
		
//		entityID = EntityRegistry.findGlobalUniqueEntityId();
//		MinecraftForge.registerEntity(EntityMutantCow.class, 	 this, 170, 32, 100, true);
	}
	
	private void registerFuel()
	{
		GameRegistry.registerFuelHandler(new FuelHandler());
	}
	
	private void registerItems()
	{
		PlasmaCraft.items.goopAcid = (new ItemPlasma()).setUnlocalizedName("goopAcid");
		PlasmaCraft.items.goopCryonite = (new ItemPlasma()).setUnlocalizedName("goopCryonite");
		PlasmaCraft.items.goopNeptunium = (new ItemPlasma()).setUnlocalizedName("goopNeptunium");
		PlasmaCraft.items.goopNetherflow = (new ItemPlasma()).setUnlocalizedName("goopNetherflow");
		PlasmaCraft.items.goopObsidium = (new ItemPlasma()).setUnlocalizedName("goopObsidium");
		PlasmaCraft.items.goopPlutonium = (new ItemPlasma()).setUnlocalizedName("goopPlutonium");
		PlasmaCraft.items.goopRadionite = (new ItemPlasma()).setUnlocalizedName("goopRadionite");
		PlasmaCraft.items.goopUranium = (new ItemPlasma()).setUnlocalizedName("goopUranium");
		
		PlasmaCraft.items.ingotCryonite = (new ItemPlasma()).setUnlocalizedName("ingotCryonite");
		PlasmaCraft.items.ingotLead = (new ItemPlasma()).setUnlocalizedName("ingotLead");
		PlasmaCraft.items.ingotNeptunium = (new ItemPlasma()).setUnlocalizedName("ingotNeptunium");
		PlasmaCraft.items.ingotNetherflow = (new ItemPlasma()).setUnlocalizedName("ingotNetherflow");
		PlasmaCraft.items.ingotObsidium = (new ItemPlasma()).setUnlocalizedName("ingotObsidium");
		PlasmaCraft.items.ingotPlutonium = (new ItemPlasma()).setUnlocalizedName("ingotPlutonium");
		PlasmaCraft.items.ingotRadionite = (new ItemPlasma()).setUnlocalizedName("ingotRadionite");
		PlasmaCraft.items.ingotUranium = (new ItemPlasma()).setUnlocalizedName("ingotUranium");
		PlasmaCraft.items.plasma = (new ItemPlasma()).setUnlocalizedName("plasma");

		PlasmaCraft.items.acidVial = (new ItemVial(blocks.acidBlock)).setUnlocalizedName("vial_acid");
		PlasmaCraft.items.causticVial = (new ItemVial(Blocks.air)).setUnlocalizedName("vial_empty");
		PlasmaCraft.items.cryoniteVial = (new ItemVial(blocks.cryoniteBlock)).setUnlocalizedName("vial_cryonite");
		PlasmaCraft.items.neptuniumVial = (new ItemVial(blocks.neptuniumBlock)).setUnlocalizedName("vial_neptunium");
		PlasmaCraft.items.netherflowVial = (new ItemVial(blocks.netherflowBlock)).setUnlocalizedName("vial_netherflow");
		PlasmaCraft.items.obsidiumVial = (new ItemVial(blocks.obsidiumBlock)).setUnlocalizedName("vial_obsidium");
		PlasmaCraft.items.plutoniumVial = (new ItemVial(blocks.plutoniumBlock)).setUnlocalizedName("vial_plutonium");
		PlasmaCraft.items.radioniteVial = (new ItemVial(blocks.radioniteBlock)).setUnlocalizedName("vial_radionite");
		PlasmaCraft.items.uraniumVial = (new ItemVial(blocks.uraniumBlock)).setUnlocalizedName("vial_uranium");
		
		PlasmaCraft.items.causticBoat = (new ItemCausticBoat()).setUnlocalizedName("causticBoat");

		PlasmaCraft.items.batteryEmpty = (new ItemPlasma()).setUnlocalizedName("batteryEmpty");
		PlasmaCraft.items.batteryCryo = (new ItemPlasma()).setUnlocalizedName("batteryCryonite");
		PlasmaCraft.items.batteryCharged = (new ItemPlasma()).setUnlocalizedName("batteryCharged");
		PlasmaCraft.items.batteryOvercharged = (new ItemPlasma()).setUnlocalizedName("batteryOvercharged");
		PlasmaCraft.items.batteryPlasma = (new ItemPlasma()).setUnlocalizedName("batteryPlasma");
		PlasmaCraft.items.beamSplitter = (new ItemPlasma()).setUnlocalizedName("beamSplitter");
		PlasmaCraft.items.energyCell = (new ItemPlasma()).setUnlocalizedName("energyCell");
		PlasmaCraft.items.thermopellet = (new ItemPlasma()).setUnlocalizedName("thermopellet");
		
		PlasmaCraft.items.acidgun = (new ItemEnergyWeapon(200)).setUnlocalizedName("acidGun");
		PlasmaCraft.items.cryoblaster = (new ItemEnergyWeapon(100)).setUnlocalizedName("cryoBlaster");
		PlasmaCraft.items.lasershotgun = (new ItemEnergyWeapon(200)).setUnlocalizedName("laserShotgun");
		PlasmaCraft.items.lasergun = (new ItemEnergyWeapon(200)).setUnlocalizedName("laserGun");
		PlasmaCraft.items.lasergunsplit = (new ItemEnergyWeapon(300)).setUnlocalizedName("laserGunSplit");
		PlasmaCraft.items.plasmagun = (new ItemEnergyWeapon(200)).setUnlocalizedName("plasmaGun");
		PlasmaCraft.items.plasmagunsplit = (new ItemEnergyWeapon(300)).setUnlocalizedName("plasmaGunSplit");
		PlasmaCraft.items.railgun = (new ItemEnergyWeapon(200)).setUnlocalizedName("railGun");
		
		PlasmaCraft.items.acidGrenade = new ItemAcidGrenade().setUnlocalizedName("acidGrenade");

		PlasmaCraft.items.hazmatBoots = (new ItemPlasmaArmor(ArmorMaterial.GOLD, proxy.addArmor("hazmat"), 3)).setUnlocalizedName("hazmatBoots");
		PlasmaCraft.items.hazmatHood = (new ItemPlasmaArmor(ArmorMaterial.GOLD, proxy.addArmor("hazmat"), 0)).setUnlocalizedName("hazmatHood");
		PlasmaCraft.items.hazmatJacket = (new ItemPlasmaArmor(ArmorMaterial.GOLD, proxy.addArmor("hazmat"), 1)).setUnlocalizedName("hazmatJacket");
		PlasmaCraft.items.hazmatPants = (new ItemPlasmaArmor(ArmorMaterial.GOLD, proxy.addArmor("hazmat"), 2)).setUnlocalizedName("hazmatPants");
		
		PlasmaCraft.items.plasmaLeather = (new ItemPlasma()).setUnlocalizedName("plasmaLeather");

		GameRegistry.registerItem(PlasmaCraft.items.goopAcid, "Acid Goop");
		GameRegistry.registerItem(PlasmaCraft.items.goopCryonite, "Cryonite Goop");
		GameRegistry.registerItem(PlasmaCraft.items.goopNeptunium, "Neptunium Goop");
		GameRegistry.registerItem(PlasmaCraft.items.goopNetherflow, "Netherflow Goop");
		GameRegistry.registerItem(PlasmaCraft.items.goopObsidium, "Obsidium Goop");
		GameRegistry.registerItem(PlasmaCraft.items.goopPlutonium, "Plutonium Goop");
		GameRegistry.registerItem(PlasmaCraft.items.goopRadionite, "Radionite Goop");
		GameRegistry.registerItem(PlasmaCraft.items.goopUranium, "Uranium Goop");

		GameRegistry.registerItem(PlasmaCraft.items.ingotCryonite, "Cryonite Ingot");
		GameRegistry.registerItem(PlasmaCraft.items.ingotLead, "Lead Ingot");
		GameRegistry.registerItem(PlasmaCraft.items.ingotNeptunium, "Neptunium Ingot");
		GameRegistry.registerItem(PlasmaCraft.items.ingotNetherflow, "Netherflow Ingot");
		GameRegistry.registerItem(PlasmaCraft.items.ingotObsidium, "Obsidium Ingot");
		GameRegistry.registerItem(PlasmaCraft.items.ingotPlutonium, "Plutonium Ingot");
		GameRegistry.registerItem(PlasmaCraft.items.ingotRadionite, "Radionite Ingot");
		GameRegistry.registerItem(PlasmaCraft.items.ingotUranium, "Uranium Ingot");
		GameRegistry.registerItem(PlasmaCraft.items.plasma, "Plasma");

		
		GameRegistry.registerItem(PlasmaCraft.items.acidVial, "Acid Vial");
		GameRegistry.registerItem(PlasmaCraft.items.causticVial, "Caustic Vial");
		GameRegistry.registerItem(PlasmaCraft.items.cryoniteVial, "Cryonite Vial");
		GameRegistry.registerItem(PlasmaCraft.items.neptuniumVial, "Neptunium Vial");
		GameRegistry.registerItem(PlasmaCraft.items.netherflowVial, "Netherflow Vial");
		GameRegistry.registerItem(PlasmaCraft.items.obsidiumVial, "Obsidium Vial");
		GameRegistry.registerItem(PlasmaCraft.items.plutoniumVial, "Plutonium Vial");
		GameRegistry.registerItem(PlasmaCraft.items.radioniteVial, "Radionite Vial");
		GameRegistry.registerItem(PlasmaCraft.items.uraniumVial, "Uranium Vial");

		GameRegistry.registerItem(PlasmaCraft.items.causticBoat, "Caustic Boat");

		GameRegistry.registerItem(PlasmaCraft.items.batteryEmpty, "Empty Battery");
		GameRegistry.registerItem(PlasmaCraft.items.batteryCryo, "Cryo Battery");
		GameRegistry.registerItem(PlasmaCraft.items.batteryCharged, "Charged Caustic Battery");
		GameRegistry.registerItem(PlasmaCraft.items.batteryOvercharged, "Overcharged Caustic Battery");
		GameRegistry.registerItem(PlasmaCraft.items.batteryPlasma, "Plasma Battery");
		GameRegistry.registerItem(PlasmaCraft.items.beamSplitter, "Beam Splitter");
		GameRegistry.registerItem(PlasmaCraft.items.energyCell, "Energy Cell");
		GameRegistry.registerItem(PlasmaCraft.items.thermopellet, "Thermopellet");

		GameRegistry.registerItem(PlasmaCraft.items.acidgun, "Acid Launcher");
		GameRegistry.registerItem(PlasmaCraft.items.cryoblaster, "Cryo Blaster");
		GameRegistry.registerItem(PlasmaCraft.items.lasershotgun, "Laser Shotgun");
		GameRegistry.registerItem(PlasmaCraft.items.lasergun, "Laser Rifle");
		GameRegistry.registerItem(PlasmaCraft.items.lasergunsplit, "Split Beam Laser Rifle");
		GameRegistry.registerItem(PlasmaCraft.items.plasmagun, "Plasma Rifle");
		GameRegistry.registerItem(PlasmaCraft.items.plasmagunsplit, "Split Beam Plasma Rifle");
		GameRegistry.registerItem(PlasmaCraft.items.railgun, "Railgun");

		GameRegistry.registerItem(PlasmaCraft.items.acidGrenade, "Acid Grenade");

		GameRegistry.registerItem(PlasmaCraft.items.plasmaLeather, "Plasma Leather");

		GameRegistry.registerItem(PlasmaCraft.items.hazmatBoots, "Hazmat Boots");
		GameRegistry.registerItem(PlasmaCraft.items.hazmatHood, "Hazmat Hood");
		GameRegistry.registerItem(PlasmaCraft.items.hazmatJacket, "Hazmat Jacket");
		GameRegistry.registerItem(PlasmaCraft.items.hazmatPants, "Hazmat Pants");
	}
	
	private void registerOres()
	{
		OreDictionary.registerOre("orePlutonium", new ItemStack(blocks.orePlasma, 1, BlockPlasmaOre.plutoniumMeta));
		OreDictionary.registerOre("oreUranium", new ItemStack(blocks.orePlasma, 1, BlockPlasmaOre.uraniumMeta));
		OreDictionary.registerOre("oreLead", new ItemStack(blocks.orePlasma, 1, BlockPlasmaOre.leadMeta));
		
		OreDictionary.registerOre("ingotPlutonium", new ItemStack(PlasmaCraft.items.ingotPlutonium, 1));
		OreDictionary.registerOre("ingotUranium", new ItemStack(PlasmaCraft.items.ingotUranium, 1));
		OreDictionary.registerOre("ingotLead", PlasmaCraft.items.ingotLead);
	}
	
	private void registerRecipes()
	{
		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, glowClothAcidMeta), PlasmaCraft.items.goopAcid, new ItemStack(Blocks.wool, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, glowClothPlutoniumMeta), PlasmaCraft.items.goopPlutonium, new ItemStack(Blocks.wool, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, glowClothRadioniteMeta), PlasmaCraft.items.goopRadionite, new ItemStack(Blocks.wool, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, glowClothNeptuniumMeta), PlasmaCraft.items.goopNeptunium, new ItemStack(Blocks.wool, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, glowClothNetherflowMeta), PlasmaCraft.items.goopNetherflow, new ItemStack(Blocks.wool, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, glowClothObsidiumMeta), PlasmaCraft.items.goopObsidium, new ItemStack(Blocks.wool, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, glowClothCryoniteMeta), PlasmaCraft.items.goopCryonite, new ItemStack(Blocks.wool, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, glowClothUraniumMeta), PlasmaCraft.items.goopUranium, new ItemStack(Blocks.wool, 1, 0));
		
		GameRegistry.addRecipe(new ItemStack(blocks.reinforcedGlass, 4), " X ", "X#X", " X ", '#', Blocks.glass, 'X', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.causticVial, 1), "X#X", "Y Y", "X#X", '#', Items.iron_ingot, 'Y', blocks.reinforcedGlass, 'X', Blocks.glass);
		GameRegistry.addRecipe(new ItemStack(blocks.plasmaBench, 1), "X#X", "# #", "X#X", '#', Items.iron_ingot, 'X', PlasmaCraft.items.acidVial);
		GameRegistry.addRecipe(new ItemStack(blocks.acidBarrier, 1), " X ", "XZX", " X ", 'Z', blocks.reinforcedGlass, 'X', PlasmaCraft.items.goopAcid);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.causticBoat, 1), "R R", "RRR", 'R', PlasmaCraft.items.ingotRadionite);
		GameRegistry.addRecipe(new ItemStack(blocks.acidTnt, 4), "APA", "GAG", "APA", 'A', PlasmaCraft.items.acidVial, 'G', Items.gunpowder, 'P', PlasmaCraft.items.plasma);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.acidGrenade, 4), "X", "Y", "Z", 'X', Items.iron_ingot, 'Y', PlasmaCraft.items.acidVial, 'Z', PlasmaCraft.items.plasma);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.hazmatHood, 1), "LLL", "L L", 'L', PlasmaCraft.items.plasmaLeather);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.hazmatJacket, 1), "L L", "LLL", "LLL", 'L', PlasmaCraft.items.plasmaLeather);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.hazmatPants, 1), "LLL", "L L", "L L", 'L', PlasmaCraft.items.plasmaLeather);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.hazmatBoots, 1), "L L", "L L", 'L', PlasmaCraft.items.plasmaLeather);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.plasmaLeather, 1), "N", "J", 'N', PlasmaCraft.items.goopAcid, 'J', Items.leather);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.plasmagunsplit, 1), "YB", 'B', PlasmaCraft.items.plasmagun, 'Y', PlasmaCraft.items.beamSplitter);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.lasergunsplit, 1), "YB", 'B', PlasmaCraft.items.lasergun, 'Y', PlasmaCraft.items.beamSplitter);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.cryoblaster, 1), "  A", "CBX", " DE", 'A', PlasmaCraft.items.ingotUranium, 'B', PlasmaCraft.items.goopCryonite, 'C', PlasmaCraft.items.ingotCryonite, 'D',
                PlasmaCraft.items.ingotObsidium, 'X', PlasmaCraft.items.batteryCryo, 'E', PlasmaCraft.items.ingotPlutonium);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.lasershotgun, 1), "  A", "BCD", " EF", 'A', Items.repeater, 'B', PlasmaCraft.items.beamSplitter, 'C', PlasmaCraft.items.ingotNetherflow, 'D',
                PlasmaCraft.items.batteryCharged, 'E', PlasmaCraft.items.ingotRadionite, 'F', PlasmaCraft.items.ingotPlutonium);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.lasergun, 1), "ABC", " BD", 'A', PlasmaCraft.items.ingotNetherflow, 'B', PlasmaCraft.items.ingotObsidium, 'C', PlasmaCraft.items.goopNetherflow, 'D', PlasmaCraft.items.ingotPlutonium);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.plasmagun, 1), "ABC", " DC", 'A', Items.diamond, 'B', PlasmaCraft.items.plasma, 'C', PlasmaCraft.items.ingotPlutonium, 'D', PlasmaCraft.items.ingotObsidium);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.energyCell, 5), " R ", "RXR", " R ", 'R', PlasmaCraft.items.ingotNeptunium, 'X', PlasmaCraft.items.goopAcid);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.batteryEmpty, 8), "IRI", "I I", "IRI", 'R', PlasmaCraft.items.ingotRadionite, 'I', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.batteryCryo, 1), "R", "X", 'R', PlasmaCraft.items.goopCryonite, 'X', PlasmaCraft.items.batteryEmpty);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.batteryPlasma, 1), "R", "X", 'R', PlasmaCraft.items.plasma, 'X', PlasmaCraft.items.batteryEmpty);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.batteryCharged, 1), "R", "X", 'R', PlasmaCraft.items.goopPlutonium, 'X', PlasmaCraft.items.batteryEmpty);

		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.goopCryonite, 4), PlasmaCraft.items.plasma, PlasmaCraft.items.goopCryonite);
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.goopNeptunium, 4), PlasmaCraft.items.plasma, PlasmaCraft.items.goopNeptunium);
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.goopNetherflow, 4), PlasmaCraft.items.plasma, PlasmaCraft.items.goopNetherflow);
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.goopObsidium, 4), PlasmaCraft.items.plasma, PlasmaCraft.items.goopObsidium);
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.goopPlutonium, 4), PlasmaCraft.items.plasma, PlasmaCraft.items.goopPlutonium);
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.goopRadionite, 4), PlasmaCraft.items.plasma, PlasmaCraft.items.goopRadionite);
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.goopUranium, 4), PlasmaCraft.items.plasma, PlasmaCraft.items.goopUranium);
		
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.acidVial, 1), PlasmaCraft.items.goopAcid, PlasmaCraft.items.causticVial);
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.cryoniteVial, 1), PlasmaCraft.items.goopCryonite, PlasmaCraft.items.causticVial);
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.neptuniumVial, 1), PlasmaCraft.items.goopNeptunium, PlasmaCraft.items.causticVial);
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.netherflowVial, 1), PlasmaCraft.items.goopNetherflow, PlasmaCraft.items.causticVial);
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.obsidiumVial, 1), PlasmaCraft.items.goopObsidium, PlasmaCraft.items.causticVial);
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.plutoniumVial, 1), PlasmaCraft.items.goopPlutonium, PlasmaCraft.items.causticVial);
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.radioniteVial, 1), PlasmaCraft.items.goopRadionite, PlasmaCraft.items.causticVial);
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.uraniumVial, 1), PlasmaCraft.items.goopUranium, PlasmaCraft.items.causticVial);

        GameRegistry.addSmelting(new ItemStack(PlasmaCraft.blocks.orePlasma, 1, BlockPlasmaOre.leadMeta), new ItemStack(PlasmaCraft.items.ingotLead, 1), 0.1f );
	}
	
	public static void loadConfig(FMLPreInitializationEvent event)
	{
        PlasmaCraft.configuration = new Configuration(event.getSuggestedConfigurationFile());
		
		liquidSourceExplodesAfterCausticExplosion = configuration.get(Configuration.CATEGORY_GENERAL, "LiquidSourceExplodesAfterCausticExplosion", true).getBoolean(true);

		generateLead = configuration.get(Configuration.CATEGORY_GENERAL, "General.GenerateLead", true).getBoolean(true);
		generateUranium = configuration.get(Configuration.CATEGORY_GENERAL, "General.GenerateUranium", true).getBoolean(true);
		generatePlutonium = configuration.get(Configuration.CATEGORY_GENERAL, "General.GeneratePlutonium", true).getBoolean(true);
		
		configuration.save();
	}

	private void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TilePlasmaBench.class,   "tilePlasmaBench");
	}
}
