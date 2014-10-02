package com.malkierian.plasmacraft.common;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import com.malkierian.plasmacraft.common.Entities.EntityAcid;
import com.malkierian.plasmacraft.common.Entities.EntityCryoBlast;
import com.malkierian.plasmacraft.common.Entities.EntityLaser;
import com.malkierian.plasmacraft.common.Entities.EntityLaserShotgun;
import com.malkierian.plasmacraft.common.Entities.EntityPlasma;
import com.malkierian.plasmacraft.common.Entities.EntityRailGun;
import com.malkierian.plasmacraft.common.blocks.BlockAcidBarrier;
import com.malkierian.plasmacraft.common.blocks.BlockAcidTNT;
import com.malkierian.plasmacraft.common.blocks.BlockCausticFluids;
import com.malkierian.plasmacraft.common.blocks.BlockGlowCloth;
import com.malkierian.plasmacraft.common.blocks.BlockPlasmaBench;
import com.malkierian.plasmacraft.common.blocks.BlockPlasmaOre;
import com.malkierian.plasmacraft.common.blocks.BlockReinforcedGlass;
import com.malkierian.plasmacraft.common.items.ItemAcidGrenade;
import com.malkierian.plasmacraft.common.items.ItemCausticBoat;
import com.malkierian.plasmacraft.common.items.ItemEnergyWeapon;
import com.malkierian.plasmacraft.common.items.ItemPlasma;
import com.malkierian.plasmacraft.common.items.ItemPlasmaArmor;
import com.malkierian.plasmacraft.common.items.ItemVial;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "plasmacraft", name = "PlasmaCraft", version = "0.3.4")
public class PlasmaCraft
{
	public static String MOD_ID = "plasmacraft";
	
	public static PlasmaTab plasmaTab;
	
	public static Block acidBarrier;
	
	public static Block orePlasma;
	public static Block oreLeadBlock;
	public static Block glowCloth;
	
	public static Block plasmaBench;
	
	public static int causticID = 180;

	public static BlockCausticFluids acidBlock;
	public static BlockCausticFluids cryoniteBlock;
	public static BlockCausticFluids neptuniumBlock;
	public static BlockCausticFluids netherflowBlock;
	public static BlockCausticFluids obsidiumBlock;
	public static BlockCausticFluids plutoniumBlock;
	public static BlockCausticFluids radioniteBlock;
	public static BlockCausticFluids uraniumBlock;
	public static Fluid acidFluid;
	public static Fluid cryoniteFluid;
	public static Fluid neptuniumFluid;
	public static Fluid netherflowFluid;
	public static Fluid obsidiumFluid;
	public static Fluid plutoniumFluid;
	public static Fluid radioniteFluid;
	public static Fluid uraniumFluid;
	
	public static Block acidTnt;
	public static Block frozenCryonite;
	public static Block reinforcedGlass;

	public static Item goopAcid;
	public static Item goopCryonite;
	public static Item goopNeptunium;
	public static Item goopNetherflow;
	public static Item goopObsidium;
	public static Item goopPlutonium;
	public static Item goopRadionite;
	public static Item goopUranium;
	public static Item plasma;
	public static Item acidGrenade;
	public static Item causticBoat;
	public static Item thermopellet;

	public static Item ingotCryonite;
	public static Item ingotLead;
	public static Item ingotNeptunium;
	public static Item ingotNetherflow;
	public static Item ingotObsidium;
	public static Item ingotPlutonium;
	public static Item ingotRadionite;
	public static Item ingotUranium;
	
	public static Item hazmatBoots;
	public static Item hazmatHood;
	public static Item hazmatJacket;
	public static Item hazmatPants;
	public static Item plasmaLeather;
	
	public static Item acidVial;
	public static Item causticVial;
	public static Item cryoniteVial;
	public static Item neptuniumVial;
	public static Item netherflowVial;
	public static Item obsidiumVial;
	public static Item plutoniumVial;
	public static Item radioniteVial;
	public static Item uraniumVial;
	
	public static Item acidgun;
	public static Item batteryCharged;
	public static Item batteryCryo;
	public static Item batteryEmpty;
	public static Item batteryOvercharged;
	public static Item batteryPlasma;
	public static Item beamSplitter;
	public static Item cryoblaster;
	public static Item energyCell;
	public static Item lasergun;
	public static Item lasergunsplit;
	public static Item lasershotgun;
	public static Item plasmagun;
	public static Item plasmagunsplit;
	public static Item railgun;

	public static final int plutoniumMeta = 0;
	public static final int radioniteMeta = 1;
	public static final int neptuniumMeta = 2;
	public static final int obsidiumMeta = 3; 
	public static final int uraniumMeta = 4;
	public static final int leadMeta = 5;
	
	public static boolean liquidSourceExplodesAfterCausticExplosion;
	
	public static int acidLakeYCutoff = 48;
	public static int acidSpoutCount = 20;
	public static int acidSpoutYRange = 30;
	public static int acidSpoutYStart = 8;
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
	
//	private GuiHandler guiHandler = new GuiHandler();
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="com.malkierian.plasmacraft.client.ClientProxy", serverSide="com.malkierian.plasmacraft.common.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		instance = this;
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		loadConfig();
		
//		NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);
		
		proxy.registerRenderers();
		
//		MinecraftForge.EVENT_BUS.register(new PCBucketFillEvent());
		
		plasmaTab = new PlasmaTab("PlasmaCraft");
		
		registerBlocks();
		
		registerFuel();
		
		registerItems();
		
		List<Item> order = Arrays.asList(Item.getItemFromBlock(orePlasma), Item.getItemFromBlock(glowCloth), Item.getItemFromBlock(frozenCryonite), Item.getItemFromBlock(reinforcedGlass),
				Item.getItemFromBlock(acidTnt), Item.getItemFromBlock(acidBarrier), Item.getItemFromBlock(plasmaBench),
				goopAcid, goopCryonite, goopNeptunium, goopNetherflow, goopObsidium, goopPlutonium, goopRadionite, goopUranium,
				ingotCryonite, ingotLead, ingotNeptunium, ingotNetherflow, ingotObsidium, ingotPlutonium, ingotRadionite, ingotUranium, plasma,
				acidVial, causticVial, cryoniteVial, neptuniumVial, netherflowVial, obsidiumVial, plutoniumVial, radioniteVial, uraniumVial,
				causticBoat,
				batteryEmpty, batteryCryo, batteryCharged, batteryOvercharged, batteryPlasma, beamSplitter, energyCell, thermopellet,
				acidgun, cryoblaster, lasershotgun, lasergun, lasergunsplit, plasmagun, plasmagunsplit, railgun,
				acidGrenade,
				hazmatBoots, hazmatHood, hazmatJacket, hazmatPants,
				plasmaLeather);

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
		
//		GameRegistry.registerWorldGenerator(new WorldGenerator());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		// Stub Method
	}
	
	private void registerBlocks()
	{
		orePlasma = new BlockPlasmaOre().setLightLevel(0.5334f).setBlockName("orePlasma");
		GameRegistry.registerBlock(orePlasma, com.malkierian.plasmacraft.common.items.ItemPlasmaOre.class, "orePlasma");
		
		acidFluid = new Fluid("acid").setDensity(80).setViscosity(400);
		cryoniteFluid = new Fluid("cryonite").setDensity(80).setViscosity(600);
		neptuniumFluid = new Fluid("neptunium").setDensity(80).setViscosity(300);
		netherflowFluid = new Fluid("netherflow").setDensity(80).setViscosity(450);
		obsidiumFluid = new Fluid("obsidium").setDensity(80).setViscosity(1200);
		plutoniumFluid = new Fluid("plutonium").setDensity(80).setViscosity(800);
		radioniteFluid = new Fluid("radionite").setDensity(80).setViscosity(1000);
		uraniumFluid = new Fluid("uranium").setDensity(150).setViscosity(800);
		FluidRegistry.registerFluid(acidFluid);
		FluidRegistry.registerFluid(cryoniteFluid);
		FluidRegistry.registerFluid(neptuniumFluid);
		FluidRegistry.registerFluid(netherflowFluid);
		FluidRegistry.registerFluid(obsidiumFluid);
		FluidRegistry.registerFluid(plutoniumFluid);
		FluidRegistry.registerFluid(radioniteFluid);
		FluidRegistry.registerFluid(uraniumFluid);

		acidBlock = (BlockCausticFluids) new BlockCausticFluids(acidFluid, Material.water).setBlockName("acid");
		cryoniteBlock = (BlockCausticFluids) new BlockCausticFluids(cryoniteFluid, Material.water).setBlockName("cryonite");
		neptuniumBlock = (BlockCausticFluids) new BlockCausticFluids(neptuniumFluid, Material.water).setBlockName("neptunium");
		netherflowBlock = (BlockCausticFluids) new BlockCausticFluids(netherflowFluid, Material.water).setBlockName("netherflow");
		obsidiumBlock = (BlockCausticFluids) new BlockCausticFluids(obsidiumFluid, Material.water).setBlockName("obsidium");
		plutoniumBlock = (BlockCausticFluids) new BlockCausticFluids(plutoniumFluid, Material.water).setBlockName("plutonium");
		radioniteBlock = (BlockCausticFluids) new BlockCausticFluids(radioniteFluid, Material.water).setBlockName("radionite");
		uraniumBlock = (BlockCausticFluids) new BlockCausticFluids(uraniumFluid, Material.water).setBlockName("uranium");
		GameRegistry.registerBlock(acidBlock, "Acid");
		GameRegistry.registerBlock(cryoniteBlock, "Cryonite");
		GameRegistry.registerBlock(neptuniumBlock, "Neptunium");
		GameRegistry.registerBlock(netherflowBlock, "Netherflow");
		GameRegistry.registerBlock(obsidiumBlock, "Obsidium");
		GameRegistry.registerBlock(plutoniumBlock, "Plutonium");
		GameRegistry.registerBlock(radioniteBlock, "Radionite");
		GameRegistry.registerBlock(uraniumBlock, "Uranium");
		
		glowCloth = new BlockGlowCloth().setBlockName("glowCloth");
		GameRegistry.registerBlock(glowCloth, com.malkierian.plasmacraft.common.items.ItemGlowCloth.class, "glowCloth");
		
		frozenCryonite = (new BlockReinforcedGlass("frozenCryonite", Material.glass, false, 1.0F)).setBlockName("frozenCryonite");
		reinforcedGlass = (new BlockReinforcedGlass("reinforcedGlass", Material.glass, false, 500.0F)).setBlockName("reinforcedGlass");
		GameRegistry.registerBlock(frozenCryonite, "Frozen Cryonite");
		GameRegistry.registerBlock(reinforcedGlass, "Reinforced Glass");
		
		plasmaBench = (new BlockPlasmaBench()).setBlockName("plasmaBench");
		GameRegistry.registerBlock(plasmaBench, "Plasmificator");
		
		acidBarrier = (new BlockAcidBarrier()).setBlockName("acidBarrier");
		GameRegistry.registerBlock(acidBarrier, "Acid Barrier");
		
		acidTnt = (new BlockAcidTNT()).setBlockName("acidTnt");
		GameRegistry.registerBlock(acidTnt, "Acid TNT");
	}
	
	private void registerEntities()
	{
		int entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityCausticBoat.class, "causticBoat", entityID);
		EntityRegistry.registerModEntity(EntityCausticBoat.class, "causticBoat", entityID, this, 64, 5, true);
		
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityAcidTNTPrimed.class, "acidTntPrimed", entityID);
		EntityRegistry.registerModEntity(EntityAcidTNTPrimed.class, "acidTntPrimed", entityID, this, 64, 100, false);
		
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
		goopAcid = (new ItemPlasma()).setUnlocalizedName("goopAcid");
		goopCryonite = (new ItemPlasma()).setUnlocalizedName("goopCryonite");
		goopNeptunium = (new ItemPlasma()).setUnlocalizedName("goopNeptunium");
		goopNetherflow = (new ItemPlasma()).setUnlocalizedName("goopNetherflow");
		goopObsidium = (new ItemPlasma()).setUnlocalizedName("goopObsidium");
		goopPlutonium = (new ItemPlasma()).setUnlocalizedName("goopPlutonium");
		goopRadionite = (new ItemPlasma()).setUnlocalizedName("goopRadionite");
		goopUranium = (new ItemPlasma()).setUnlocalizedName("goopUranium");
		
		ingotCryonite = (new ItemPlasma()).setUnlocalizedName("ingotCryonite");
		ingotLead = (new ItemPlasma()).setUnlocalizedName("ingotLead");
		ingotNeptunium = (new ItemPlasma()).setUnlocalizedName("ingotNeptunium");
		ingotNetherflow = (new ItemPlasma()).setUnlocalizedName("ingotNetherflow");
		ingotObsidium = (new ItemPlasma()).setUnlocalizedName("ingotObsidium");
		ingotPlutonium = (new ItemPlasma()).setUnlocalizedName("ingotPlutonium");
		ingotRadionite = (new ItemPlasma()).setUnlocalizedName("ingotRadionite");
		ingotUranium = (new ItemPlasma()).setUnlocalizedName("ingotUranium");
		plasma = (new ItemPlasma()).setUnlocalizedName("plasma");

		acidVial = (new ItemVial(acidBlock)).setUnlocalizedName("vial_acid");
		causticVial = (new ItemVial(Blocks.air)).setUnlocalizedName("vial_empty");
		cryoniteVial = (new ItemVial(cryoniteBlock)).setUnlocalizedName("vial_cryonite");
		neptuniumVial = (new ItemVial(neptuniumBlock)).setUnlocalizedName("vial_neptunium");
		netherflowVial = (new ItemVial(netherflowBlock)).setUnlocalizedName("vial_netherflow");
		obsidiumVial = (new ItemVial(obsidiumBlock)).setUnlocalizedName("vial_obsidium");
		plutoniumVial = (new ItemVial(plutoniumBlock)).setUnlocalizedName("vial_plutonium");
		radioniteVial = (new ItemVial(radioniteBlock)).setUnlocalizedName("vial_radionite");
		uraniumVial = (new ItemVial(uraniumBlock)).setUnlocalizedName("vial_uranium");
		
		causticBoat = (new ItemCausticBoat()).setUnlocalizedName("causticBoat");

		batteryEmpty = (new ItemPlasma()).setUnlocalizedName("batteryEmpty");
		batteryCryo = (new ItemPlasma()).setUnlocalizedName("batteryCryonite");
		batteryCharged = (new ItemPlasma()).setUnlocalizedName("batteryCharged");
		batteryOvercharged = (new ItemPlasma()).setUnlocalizedName("batteryOvercharged");
		batteryPlasma = (new ItemPlasma()).setUnlocalizedName("batteryPlasma");
		beamSplitter = (new ItemPlasma()).setUnlocalizedName("beamSplitter");
		energyCell = (new ItemPlasma()).setUnlocalizedName("energyCell");
		thermopellet = (new ItemPlasma()).setUnlocalizedName("thermopellet");
		
		acidgun = (new ItemEnergyWeapon(200)).setUnlocalizedName("acidGun");
		cryoblaster = (new ItemEnergyWeapon(100)).setUnlocalizedName("cryoBlaster");
		lasershotgun = (new ItemEnergyWeapon(200)).setUnlocalizedName("laserShotgun");
		lasergun = (new ItemEnergyWeapon(200)).setUnlocalizedName("laserGun");
		lasergunsplit = (new ItemEnergyWeapon(300)).setUnlocalizedName("laserGunSplit");
		plasmagun = (new ItemEnergyWeapon(200)).setUnlocalizedName("plasmaGun");
		plasmagunsplit = (new ItemEnergyWeapon(300)).setUnlocalizedName("plasmaGunSplit");
		railgun = (new ItemEnergyWeapon(200)).setUnlocalizedName("railGun");
		
		acidGrenade = new ItemAcidGrenade().setUnlocalizedName("acidGrenade");

		hazmatBoots = (new ItemPlasmaArmor(ArmorMaterial.GOLD, proxy.addArmor("hazmat"), 3)).setUnlocalizedName("hazmatBoots");
		hazmatHood = (new ItemPlasmaArmor(ArmorMaterial.GOLD, proxy.addArmor("hazmat"), 0)).setUnlocalizedName("hazmatHood");
		hazmatJacket = (new ItemPlasmaArmor(ArmorMaterial.GOLD, proxy.addArmor("hazmat"), 1)).setUnlocalizedName("hazmatJacket");
		hazmatPants = (new ItemPlasmaArmor(ArmorMaterial.GOLD, proxy.addArmor("hazmat"), 2)).setUnlocalizedName("hazmatPants");
		
		plasmaLeather = (new ItemPlasma()).setUnlocalizedName("plasmaLeather");

		GameRegistry.registerItem(goopAcid, "Acid Goop");
		GameRegistry.registerItem(goopCryonite, "Cryonite Goop");
		GameRegistry.registerItem(goopNeptunium, "Neptunium Goop");
		GameRegistry.registerItem(goopNetherflow, "Netherflow Goop");
		GameRegistry.registerItem(goopObsidium, "Obsidium Goop");
		GameRegistry.registerItem(goopPlutonium, "Plutonium Goop");
		GameRegistry.registerItem(goopRadionite, "Radionite Goop");
		GameRegistry.registerItem(goopUranium, "Uranium Goop");

		GameRegistry.registerItem(ingotCryonite, "Cryonite Ingot");
		GameRegistry.registerItem(ingotLead, "Lead Ingot");
		GameRegistry.registerItem(ingotNeptunium, "Neptunium Ingot");
		GameRegistry.registerItem(ingotNetherflow, "Netherflow Ingot");
		GameRegistry.registerItem(ingotObsidium, "Obsidium Ingot");
		GameRegistry.registerItem(ingotPlutonium, "Plutonium Ingot");
		GameRegistry.registerItem(ingotRadionite, "Radionite Ingot");
		GameRegistry.registerItem(ingotUranium, "Uranium Ingot");
		GameRegistry.registerItem(plasma, "Plasma");

		GameRegistry.registerItem(acidVial, "Acid Vial");
		GameRegistry.registerItem(causticVial, "Caustic Vial");
		GameRegistry.registerItem(cryoniteVial, "Cryonite Vial");
		GameRegistry.registerItem(neptuniumVial, "Neptunium Vial");
		GameRegistry.registerItem(netherflowVial, "Netherflow Vial");
		GameRegistry.registerItem(obsidiumVial, "Obsidium Vial");
		GameRegistry.registerItem(plutoniumVial, "Plutonium Vial");
		GameRegistry.registerItem(radioniteVial, "Radionite Vial");
		GameRegistry.registerItem(uraniumVial, "Uranium Vial");

		GameRegistry.registerItem(causticBoat, "Caustic Boat");

		GameRegistry.registerItem(batteryEmpty, "Empty Battery");
		GameRegistry.registerItem(batteryCryo, "Cryo Battery");
		GameRegistry.registerItem(batteryCharged, "Charged Caustic Battery");
		GameRegistry.registerItem(batteryOvercharged, "Overcharged Caustic Battery");
		GameRegistry.registerItem(batteryPlasma, "Plasma Battery");
		GameRegistry.registerItem(beamSplitter, "Beam Splitter");
		GameRegistry.registerItem(energyCell, "Energy Cell");
		GameRegistry.registerItem(thermopellet, "Thermopellet");

		GameRegistry.registerItem(acidgun, "Acid Launcher");
		GameRegistry.registerItem(cryoblaster, "Cryo Blaster");
		GameRegistry.registerItem(lasershotgun, "Laser Shotgun");
		GameRegistry.registerItem(lasergun, "Laser Rifle");
		GameRegistry.registerItem(lasergunsplit, "Split Beam Laser Rifle");
		GameRegistry.registerItem(plasmagun, "Plasma Rifle");
		GameRegistry.registerItem(plasmagunsplit, "Split Beam Plasma Rifle");
		GameRegistry.registerItem(railgun, "Railgun");

		GameRegistry.registerItem(acidGrenade, "Acid Grenade");

		GameRegistry.registerItem(plasmaLeather, "Plasma Leather");

		GameRegistry.registerItem(hazmatBoots, "Hazmat Boots");
		GameRegistry.registerItem(hazmatHood, "Hazmat Hood");
		GameRegistry.registerItem(hazmatJacket, "Hazmat Jacket");
		GameRegistry.registerItem(hazmatPants, "Hazmat Pants");
	}
	
	private void registerOres()
	{
		OreDictionary.registerOre("orePlutonium", new ItemStack(orePlasma, 1, plutoniumMeta));
		OreDictionary.registerOre("oreUranium", new ItemStack(orePlasma, 1, uraniumMeta));
		OreDictionary.registerOre("oreLead", new ItemStack(orePlasma, 1, leadMeta));
		
		OreDictionary.registerOre("ingotPlutonium", new ItemStack(ingotPlutonium, 1));
		OreDictionary.registerOre("ingotUranium", new ItemStack(ingotUranium, 1));
		OreDictionary.registerOre("ingotLead", ingotLead);
	}
	
	private void registerRecipes()
	{
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothAcidMeta), goopAcid, new ItemStack(Blocks.wool, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothPlutoniumMeta), goopPlutonium, new ItemStack(Blocks.wool, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothRadioniteMeta), goopRadionite, new ItemStack(Blocks.wool, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothNeptuniumMeta), goopNeptunium, new ItemStack(Blocks.wool, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothNetherflowMeta), goopNetherflow, new ItemStack(Blocks.wool, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothObsidiumMeta), goopObsidium, new ItemStack(Blocks.wool, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothCryoniteMeta), goopCryonite, new ItemStack(Blocks.wool, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothUraniumMeta), goopUranium, new ItemStack(Blocks.wool, 1, 0));
		
		GameRegistry.addRecipe(new ItemStack(reinforcedGlass, 4), new Object[] {
			" X ", "X#X", " X ", Character.valueOf('#'), Blocks.glass, Character.valueOf('X'), Items.iron_ingot
		});
		GameRegistry.addRecipe(new ItemStack(causticVial, 1), new Object[] {
			"X#X", "Y Y", "X#X", Character.valueOf('#'), Items.iron_ingot, Character.valueOf('Y'), reinforcedGlass, Character.valueOf('X'), Blocks.glass
		});
//		GameRegistry.addRecipe(new ItemStack(plasmaBench, 1), new Object[] {
//			"X#X", "# #", "X#X", Character.valueOf('#'), Items.iron_ingot, Character.valueOf('X'), acidVial
//		});
		GameRegistry.addRecipe(new ItemStack(acidBarrier, 1), new Object[] {
			" X ", "XZX", " X ", Character.valueOf('Z'), reinforcedGlass, Character.valueOf('X'), goopAcid
		});
		GameRegistry.addRecipe(new ItemStack(causticBoat, 1), new Object[] {
			"R R", "RRR", Character.valueOf('R'), ingotRadionite
		});
//		GameRegistry.addRecipe(new ItemStack(acidTnt, 4), new Object[] {
//			"APA", "GAG", "APA", Character.valueOf('A'), acidVial, Character.valueOf('G'), Items.gunpowder, Character.valueOf('P'), plasma
//		});
//		GameRegistry.addRecipe(new ItemStack(acidGrenade, 4), new Object[] {
//			"X", "Y", "Z", Character.valueOf('X'), Items.iron_ingot, Character.valueOf('Y'), acidVial, Character.valueOf('Z'), plasma
//		});
		GameRegistry.addRecipe(new ItemStack(hazmatHood, 1), new Object[] {
			"LLL", "L L", Character.valueOf('L'), plasmaLeather
		});
		GameRegistry.addRecipe(new ItemStack(hazmatJacket, 1), new Object[] {
			"L L", "LLL", "LLL", Character.valueOf('L'), plasmaLeather
		});
		GameRegistry.addRecipe(new ItemStack(hazmatPants, 1), new Object[] {
			"LLL", "L L", "L L", Character.valueOf('L'), plasmaLeather
		});
		GameRegistry.addRecipe(new ItemStack(hazmatBoots, 1), new Object[] {
			"L L", "L L", Character.valueOf('L'), plasmaLeather
		});
		GameRegistry.addRecipe(new ItemStack(plasmaLeather, 1), new Object[] {
			"N", "J", Character.valueOf('N'), goopAcid, Character.valueOf('J'), Items.leather
		});
		GameRegistry.addRecipe(new ItemStack(plasmagunsplit, 1), new Object[] {
			"YB", Character.valueOf('B'), plasmagun, Character.valueOf('Y'), beamSplitter
		});
		GameRegistry.addRecipe(new ItemStack(lasergunsplit, 1), new Object[] {
			"YB", Character.valueOf('B'), lasergun, Character.valueOf('Y'), beamSplitter
		});
		GameRegistry.addRecipe(new ItemStack(cryoblaster, 1), new Object[] {
			"  A", "CBX", " DE", Character.valueOf('A'), ingotUranium, Character.valueOf('B'), goopCryonite, Character.valueOf('C'), ingotCryonite, Character.valueOf('D'), 
			ingotObsidium, Character.valueOf('X'), batteryCryo, Character.valueOf('E'), ingotPlutonium
		});
		GameRegistry.addRecipe(new ItemStack(lasershotgun, 1), new Object[] {
			"  A", "BCD", " EF", Character.valueOf('A'), Items.repeater, Character.valueOf('B'), beamSplitter, Character.valueOf('C'), ingotNetherflow, Character.valueOf('D'), 
			batteryCharged, Character.valueOf('E'), ingotRadionite, Character.valueOf('F'), ingotPlutonium
		});
		GameRegistry.addRecipe(new ItemStack(lasergun, 1), new Object[] {
			"ABC", " BD", Character.valueOf('A'), ingotNetherflow, Character.valueOf('B'), ingotObsidium, Character.valueOf('C'), goopNetherflow, Character.valueOf('D'), ingotPlutonium,
		});
		GameRegistry.addRecipe(new ItemStack(plasmagun, 1), new Object[] {
			"ABC", " DC", Character.valueOf('A'), Items.diamond, Character.valueOf('B'), plasma, Character.valueOf('C'), ingotPlutonium, Character.valueOf('D'), ingotObsidium,
		});
		GameRegistry.addRecipe(new ItemStack(energyCell, 5), new Object[] {
			" R ", "RXR", " R ", Character.valueOf('R'), ingotNeptunium, Character.valueOf('X'), goopAcid
		});
		GameRegistry.addRecipe(new ItemStack(batteryEmpty, 8), new Object[] {
			"IRI", "I I", "IRI", Character.valueOf('R'), ingotRadionite, Character.valueOf('I'), Items.iron_ingot
		});
		GameRegistry.addRecipe(new ItemStack(batteryCryo, 1), new Object[] {
			"R", "X", Character.valueOf('R'), goopCryonite, Character.valueOf('X'), batteryEmpty
		});
		GameRegistry.addRecipe(new ItemStack(batteryPlasma, 1), new Object[] {
			"R", "X", Character.valueOf('R'), plasma, Character.valueOf('X'), batteryEmpty
		});
		GameRegistry.addRecipe(new ItemStack(batteryCharged, 1), new Object[] {
			"R", "X", Character.valueOf('R'), goopPlutonium, Character.valueOf('X'), batteryEmpty
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(goopCryonite, 4), plasma, goopCryonite);
		GameRegistry.addShapelessRecipe(new ItemStack(goopNeptunium, 4), plasma, goopNeptunium);
		GameRegistry.addShapelessRecipe(new ItemStack(goopNetherflow, 4), plasma, goopNetherflow);
		GameRegistry.addShapelessRecipe(new ItemStack(goopObsidium, 4), plasma, goopObsidium);
		GameRegistry.addShapelessRecipe(new ItemStack(goopPlutonium, 4), plasma, goopPlutonium);
		GameRegistry.addShapelessRecipe(new ItemStack(goopRadionite, 4), plasma, goopRadionite);
		GameRegistry.addShapelessRecipe(new ItemStack(goopUranium, 4), plasma, goopUranium);
		
		GameRegistry.addShapelessRecipe(new ItemStack(acidVial, 1), goopAcid, causticVial);
		GameRegistry.addShapelessRecipe(new ItemStack(cryoniteVial, 1), goopCryonite, causticVial);
		GameRegistry.addShapelessRecipe(new ItemStack(neptuniumVial, 1), goopNeptunium, causticVial);
		GameRegistry.addShapelessRecipe(new ItemStack(netherflowVial, 1), goopNetherflow, causticVial);
		GameRegistry.addShapelessRecipe(new ItemStack(obsidiumVial, 1), goopObsidium, causticVial);
		GameRegistry.addShapelessRecipe(new ItemStack(plutoniumVial, 1), goopPlutonium, causticVial);
		GameRegistry.addShapelessRecipe(new ItemStack(radioniteVial, 1), goopRadionite, causticVial);
		GameRegistry.addShapelessRecipe(new ItemStack(uraniumVial, 1), goopUranium, causticVial);
		
		GameRegistry.addSmelting(oreLeadBlock, new ItemStack(ingotLead, 1), 0.1f);
	}
	
	public static void loadConfig()
	{
		Configuration c;
		if(FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			c = new Configuration(new File(Minecraft.getMinecraft().mcDataDir, "/config/PlasmaCraft.cfg"));
		}
		else
		{
			c = new Configuration(new File("./config/PlasmaCraft.cfg"));
		}
		c.load();
		
		liquidSourceExplodesAfterCausticExplosion = c.get(Configuration.CATEGORY_GENERAL, "LiquidSourceExplodesAfterCausticExplosion", true).getBoolean(true);

		generateLead = c.get(Configuration.CATEGORY_GENERAL, "General.GenerateLead", true).getBoolean(true);
		generateUranium = c.get(Configuration.CATEGORY_GENERAL, "General.GenerateUranium", true).getBoolean(true);
		generatePlutonium = c.get(Configuration.CATEGORY_GENERAL, "General.GeneratePlutonium", true).getBoolean(true);
		
		c.save();
	}

	private void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TilePlasmaBench.class,   "tilePlasmaBench");		
	}
}
