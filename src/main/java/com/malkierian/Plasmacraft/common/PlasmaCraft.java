package com.malkierian.Plasmacraft.common;

import java.io.File;

import javax.swing.Icon;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;

import com.malkierian.Plasmacraft.common.Entities.EntityAcid;
import com.malkierian.Plasmacraft.common.Entities.EntityCryoBlast;
import com.malkierian.Plasmacraft.common.Entities.EntityLaser;
import com.malkierian.Plasmacraft.common.Entities.EntityLaserShotgun;
import com.malkierian.Plasmacraft.common.Entities.EntityPlasma;
import com.malkierian.Plasmacraft.common.Entities.EntityRailGun;
import com.malkierian.Plasmacraft.common.blocks.BlockAcidBarrier;
import com.malkierian.Plasmacraft.common.blocks.BlockAcidTNT;
import com.malkierian.Plasmacraft.common.blocks.BlockGlowCloth;
import com.malkierian.Plasmacraft.common.blocks.BlockPlasmaBench;
import com.malkierian.Plasmacraft.common.blocks.BlockPlasmaOre;
import com.malkierian.Plasmacraft.common.blocks.BlockReinforcedGlass;
import com.malkierian.Plasmacraft.common.items.ItemAcidGrenade;
import com.malkierian.Plasmacraft.common.items.ItemCausticBoat;
import com.malkierian.Plasmacraft.common.items.ItemEnergyWeapon;
import com.malkierian.Plasmacraft.common.items.ItemPlasma;
import com.malkierian.Plasmacraft.common.items.ItemPlasmaArmor;
import com.malkierian.Plasmacraft.common.items.ItemVial;

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
	public static Block acidBarrier;
	
	public static Block orePlasma;
	public static Block oreLeadBlock;
	public static Block glowCloth;
	
	public static Block plasmaBench;
	
	public static int causticID = 180;
	
//	public static BlockCausticFluids acidMoving;
	public static Block acidStill;
	public static Block acidTnt;
//	public static BlockCausticFluids cryoniteMoving;
	public static Block cryoniteStill;
	public static Block frozenCryonite;
//	public static BlockCausticFluids neptuniumMoving;
	public static Block neptuniumStill;
//	public static BlockCausticFluids netherflowMoving;
	public static Block netherflowStill;
//	public static BlockCausticFluids obsidiumMoving;
	public static Block obsidiumStill;
//	public static BlockCausticFluids plutoniumMoving;
	public static Block plutoniumStill;
//	public static BlockCausticFluids radioniteMoving;
	public static Block radioniteStill;
	public static Block reinforcedGlass;
//	public static BlockCausticFluids uraniumMoving;
	public static Block uraniumStill;

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
	public static Item thermoPellet;

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
	public static Item batteryOverCharged;
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
	
	// The instance of your mod that Forge uses.
	@Instance("PlasmaCraft")
	public static PlasmaCraft instance;
	
//	private GuiHandler guiHandler = new GuiHandler();
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="com.malkierian.Plasmacraft.client.ClientProxy", serverSide="com.malkierian.Plasmacraft.common.CommonProxy")
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
		
//		NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
		
		proxy.registerRenderers();
		
//		MinecraftForge.EVENT_BUS.register(new PCBucketFillEvent());
		
		registerBlocks();
		
		registerFuel();
		
		registerItems();
		
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
		GameRegistry.registerBlock(orePlasma, com.malkierian.Plasmacraft.common.items.ItemPlasmaOre.class, "orePlasma");
//		LanguageRegistry.addName(new ItemStack(orePlasma, 1, plutoniumMeta), 	"Plutonium Ore");
//		LanguageRegistry.addName(new ItemStack(orePlasma, 1, radioniteMeta), 	"Radionite Ore");
//		LanguageRegistry.addName(new ItemStack(orePlasma, 1, uraniumMeta), 		"Uranium Ore");
//		LanguageRegistry.addName(new ItemStack(orePlasma, 1, neptuniumMeta), 	"Neptunium Ore");
//		LanguageRegistry.addName(new ItemStack(orePlasma, 1, obsidiumMeta), 	"Obsidium Ore");
		
//		oreLeadBlock = new BlockPlasmaOre().setLightLevel(0.0f).setBlockName("oreLead");
//		GameRegistry.registerBlock(oreLeadBlock, "oreLead");
//		LanguageRegistry.addName(oreLeadBlock, "Lead Ore");
		
//		MinecraftForge.setBlockHarvestLevel(orePlasma, obsidiumMeta, 	"pickaxe", 3);
//		MinecraftForge.setBlockHarvestLevel(orePlasma, uraniumMeta, 	"pickaxe", 2);
//		MinecraftForge.setBlockHarvestLevel(orePlasma, radioniteMeta, 	"pickaxe", 2);
//		MinecraftForge.setBlockHarvestLevel(orePlasma, plutoniumMeta, 	"pickaxe", 2);
//		MinecraftForge.setBlockHarvestLevel(orePlasma, neptuniumMeta, 	"pickaxe", 1);
//		MinecraftForge.setBlockHarvestLevel(oreLeadBlock, "pickaxe", 1);
		
//		acidMoving =		(BlockCausticFluids)(new BlockCausticFlowing	(acidFlowingBlockID, 		acidStillBlockID, 	   	acidFlowingBlockID))				
//				.setPlasmaLiquid(PlasmaLiquid.ACID)
//				.setBlockName("acidMoving");
//		acidStill = 							(new BlockCausticStationary		(acidStillBlockID, 			acidStillBlockID, 	   	acidFlowingBlockID, 		1.0F))
//				.setPlasmaLiquid(PlasmaLiquid.ACID)
//				.setBlockName("acidStill");
//		cryoniteMoving = 	(BlockCausticFluids)(new BlockCausticFlowing	(cryoniteFlowingBlockID, 	cryoniteStillBlockID,   cryoniteFlowingBlockID))
//				.setPlasmaLiquid(PlasmaLiquid.CRYONITE)
//				.setBlockName("cryoniteMoving");
//		cryoniteStill = 						(new BlockCausticStationary		(cryoniteStillBlockID, 		cryoniteStillBlockID,   cryoniteFlowingBlockID,   	0.0F))
//				.setPlasmaLiquid(PlasmaLiquid.CRYONITE)
//				.setBlockName("cryoniteStill");
//		neptuniumMoving = 	(BlockCausticFluids)(new BlockCausticFlowing	(neptuniumFlowingBlockID, 	neptuniumStillBlockID,  neptuniumFlowingBlockID))
//				.setPlasmaLiquid(PlasmaLiquid.NEPTUNIUM)
//				.setBlockName("neptuniumMoving");
//		neptuniumStill = 						(new BlockCausticStationary		(neptuniumStillBlockID, 	neptuniumStillBlockID,  neptuniumFlowingBlockID,  	1.0F))
//				.setPlasmaLiquid(PlasmaLiquid.NEPTUNIUM)
//				.setBlockName("neptuniumStill");
//		netherflowMoving = 	(BlockCausticFluids)(new BlockCausticFlowing	(netherflowFlowingBlockID, 	netherflowStillBlockID, netherflowFlowingBlockID))
//				.setPlasmaLiquid(PlasmaLiquid.NETHERFLOW)
//				.setBlockName("netherflowMoving");
//		netherflowStill = 						(new BlockCausticStationary		(netherflowStillBlockID, 	netherflowStillBlockID, netherflowFlowingBlockID, 	1.0F))
//				.setPlasmaLiquid(PlasmaLiquid.NETHERFLOW)
//				.setBlockName("netherflowStill");
//		obsidiumMoving = 	(BlockCausticFluids)(new BlockCausticFlowing	(obsidiumFlowingBlockID, 	obsidiumStillBlockID,   obsidiumFlowingBlockID))
//				.setPlasmaLiquid(PlasmaLiquid.OBSIDIUM)
//				.setBlockName("obsidiumMoving");
//		obsidiumStill = 						(new BlockCausticStationary		(obsidiumStillBlockID, 		obsidiumStillBlockID,   obsidiumFlowingBlockID,   	1.0F))
//				.setPlasmaLiquid(PlasmaLiquid.OBSIDIUM)
//				.setBlockName("obsidiumStill");
//		plutoniumMoving = 	(BlockCausticFluids)(new BlockCausticFlowing	(plutoniumFlowingBlockID, 	plutoniumStillBlockID,  plutoniumFlowingBlockID))
//				.setPlasmaLiquid(PlasmaLiquid.PLUTONIUM)
//				.setBlockName("plutoniumMoving");
//		plutoniumStill = 						(new BlockCausticStationary		(plutoniumStillBlockID, 	plutoniumStillBlockID,  plutoniumFlowingBlockID,  	1.0F))
//				.setPlasmaLiquid(PlasmaLiquid.PLUTONIUM)
//				.setBlockName("plutoniumStill");
//		radioniteMoving = 	(BlockCausticFluids)(new BlockCausticFlowing	(radioniteFlowingBlockID, 	radioniteStillBlockID,  radioniteFlowingBlockID))
//				.setPlasmaLiquid(PlasmaLiquid.RADIONITE)
//				.setBlockName("radioniteMoving");
//		radioniteStill = 						(new BlockCausticStationary		(radioniteStillBlockID, 	radioniteStillBlockID,  radioniteFlowingBlockID,  	1.0F))
//				.setPlasmaLiquid(PlasmaLiquid.RADIONITE)
//				.setBlockName("radioniteStill");
//		uraniumMoving = 	(BlockCausticFluids)(new BlockCausticFlowing	(uraniumFlowingBlockID, 	uraniumStillBlockID,	uraniumFlowingBlockID))
//				.setPlasmaLiquid(PlasmaLiquid.URANIUM)
//				.setBlockName("uraniumMoving");
//		uraniumStill = 							(new BlockCausticStationary		(uraniumStillBlockID, 		uraniumStillBlockID,	uraniumFlowingBlockID,		1.0F))
//				.setPlasmaLiquid(PlasmaLiquid.URANIUM)
//				.setBlockName("uraniumStill");
//		GameRegistry.registerBlock(acidMoving, "Acid");
//		GameRegistry.registerBlock(cryoniteMoving, "Cryonite");
//		GameRegistry.registerBlock(neptuniumMoving, "Neptunium");
//		GameRegistry.registerBlock(netherflowMoving, "Netherflow");
//		GameRegistry.registerBlock(obsidiumMoving, "Obsidium");
//		GameRegistry.registerBlock(plutoniumMoving, "Plutonium");
//		GameRegistry.registerBlock(radioniteMoving, "Radionite");
//		GameRegistry.registerBlock(uraniumMoving, "Uranium");
//		LanguageRegistry.addName(acidMoving, "Acid");
//		LanguageRegistry.addName(cryoniteMoving, "Cryonite");
//		LanguageRegistry.addName(neptuniumMoving, "Neptunium");
//		LanguageRegistry.addName(netherflowMoving, "Netherflow");
//		LanguageRegistry.addName(obsidiumMoving, "Obsidium");
//		LanguageRegistry.addName(plutoniumMoving, "Plutonium");
//		LanguageRegistry.addName(radioniteMoving, "Radionite");
//		LanguageRegistry.addName(uraniumMoving, "Uranium");
		
		glowCloth = new BlockGlowCloth().setBlockName("glowCloth");
		GameRegistry.registerBlock(glowCloth, com.malkierian.Plasmacraft.common.items.ItemGlowCloth.class, "glowCloth");
//		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothAcidMeta),		"Acid Glowcloth");
//		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothRadioniteMeta),	"Radionite Glowcloth");
//		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothNetherflowMeta),	"Netherflow Glowcloth");
//		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothNeptuniumMeta),	"Neptunium Glowcloth");
//		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothUraniumMeta),		"Uranium Glowcloth");
//		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothPlutoniumMeta),	"Plutonium Glowcloth");
//		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothCryoniteMeta),	"Cryonite Glowcloth");
//		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothObsidiumMeta),	"Obsidium Glowcloth");
		
		frozenCryonite = (new BlockReinforcedGlass("frozenCryonite", Material.glass, false, 1.0F)).setBlockName("frozenCryonite");
		reinforcedGlass = (new BlockReinforcedGlass("reinforcedGlass", Material.glass, false, 500.0F)).setBlockName("reinforcedGlass");
		GameRegistry.registerBlock(frozenCryonite, "Frozen Cryonite");
		GameRegistry.registerBlock(reinforcedGlass, "Reinforced Glass");
//		LanguageRegistry.addName(frozenCryonite, "Frozen Cryonite");
//		LanguageRegistry.addName(reinforcedGlass, "Reinforced Glass");
		
		plasmaBench = (new BlockPlasmaBench()).setBlockName("plasmaBench");
		GameRegistry.registerBlock(plasmaBench, "plasmaBench");
//		LanguageRegistry.addName(plasmaBench, "Plasmificator");
		
		acidBarrier = (new BlockAcidBarrier()).setBlockName("acidBarrier");
		GameRegistry.registerBlock(acidBarrier, "acidBarrier");
//		LanguageRegistry.addName(acidBarrier, "Acid Barrier");
		
		acidTnt = (new BlockAcidTNT()).setBlockName("acidTnt");
		GameRegistry.registerBlock(acidTnt, "acidTnt");
//		LanguageRegistry.addName(acidTnt, "Acid TNT");
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
		GameRegistry.registerItem(goopAcid, "goopAcid");
		GameRegistry.registerItem(goopCryonite, "goopCryonite");
		GameRegistry.registerItem(goopNeptunium, "goopNeptunium");
		GameRegistry.registerItem(goopNetherflow, "goopNetherflow");
		GameRegistry.registerItem(goopObsidium, "goopObsidium");
		GameRegistry.registerItem(goopPlutonium, "goopPlutonium");
		GameRegistry.registerItem(goopRadionite, "goopRadionite");
		GameRegistry.registerItem(goopUranium, "goopUranium");
//		LanguageRegistry.addName(goopAcid, "Acid Goop");
//		LanguageRegistry.addName(goopCryonite, "Cryonite Goop");
//		LanguageRegistry.addName(goopNeptunium, "Neptunium Goop");
//		LanguageRegistry.addName(goopNetherflow, "Netherflow Goop");
//		LanguageRegistry.addName(goopObsidium, "Obsidium Goop");
//		LanguageRegistry.addName(goopPlutonium, "Plutonium Goop");
//		LanguageRegistry.addName(goopRadionite, "Radionite Goop");
//		LanguageRegistry.addName(goopUranium, "Uranium Goop");
		
		ingotCryonite = (new ItemPlasma()).setUnlocalizedName("ingotCryonite");
		ingotLead = (new ItemPlasma()).setUnlocalizedName("ingotLead");
		ingotNeptunium = (new ItemPlasma()).setUnlocalizedName("ingotNeptunium");
		ingotNetherflow = (new ItemPlasma()).setUnlocalizedName("ingotNetherflow");
		ingotObsidium = (new ItemPlasma()).setUnlocalizedName("ingotObsidium");
		ingotPlutonium = (new ItemPlasma()).setUnlocalizedName("ingotPlutonium");
		ingotRadionite = (new ItemPlasma()).setUnlocalizedName("ingotRadionite");
		ingotUranium = (new ItemPlasma()).setUnlocalizedName("ingotUranium");
		plasma = (new ItemPlasma()).setUnlocalizedName("plasma");
		GameRegistry.registerItem(ingotCryonite, "ingotCryonite");
		GameRegistry.registerItem(ingotLead, "ingotLead");
		GameRegistry.registerItem(ingotNeptunium, "ingotNeptunium");
		GameRegistry.registerItem(ingotNetherflow, "ingotNetherflow");
		GameRegistry.registerItem(ingotObsidium, "ingotObsidium");
		GameRegistry.registerItem(ingotPlutonium, "ingotPlutonium");
		GameRegistry.registerItem(ingotRadionite, "ingotRadionite");
		GameRegistry.registerItem(ingotUranium, "ingotUranium");
		GameRegistry.registerItem(plasma, "plasma");
//		LanguageRegistry.addName(ingotCryonite, "Cryonite Ingot");
//		LanguageRegistry.addName(ingotLead, "Lead Ingot");
//		LanguageRegistry.addName(ingotNeptunium, "Neptunium Ingot");
//		LanguageRegistry.addName(ingotNetherflow, "Netherflow Ingot");
//		LanguageRegistry.addName(ingotObsidium, "Obsidium Ingot");
//		LanguageRegistry.addName(ingotPlutonium, "Plutonium Ingot");
//		LanguageRegistry.addName(ingotRadionite, "Radionite Ingot");
//		LanguageRegistry.addName(ingotUranium, "Uranium Ingot");
//		LanguageRegistry.addName(plasma, "Plasma");

//		acidVial = (new ItemVial(acidMoving.blockID, PlasmaLiquid.ACID)).setUnlocalizedName("vial_acid");
		causticVial = (new ItemVial(0, PlasmaLiquid.EMPTY)).setUnlocalizedName("vial_empty");
		GameRegistry.registerItem(causticVial, "causticVial");
//		cryoniteVial = (new ItemVial(cryoniteMoving.blockID, PlasmaLiquid.CRYONITE)).setUnlocalizedName("vial_cryonite");
//		neptuniumVial = (new ItemVial(neptuniumMoving.blockID, PlasmaLiquid.NEPTUNIUM)).setUnlocalizedName("vial_neptunium");
//		netherflowVial = (new ItemVial(netherflowMoving.blockID, PlasmaLiquid.NETHERFLOW)).setUnlocalizedName("vial_netherflow");
//		obsidiumVial = (new ItemVial(obsidiumMoving.blockID, PlasmaLiquid.OBSIDIUM)).setUnlocalizedName("vial_obsidium");
//		plutoniumVial = (new ItemVial(plutoniumMoving.blockID, PlasmaLiquid.PLUTONIUM)).setUnlocalizedName("vial_plutonium");
//		radioniteVial = (new ItemVial(radioniteMoving.blockID, PlasmaLiquid.RADIONITE)).setUnlocalizedName("vial_radionite");
//		uraniumVial = (new ItemVial(uraniumMoving.blockID, PlasmaLiquid.URANIUM)).setUnlocalizedName("vial_uranium");
//		LanguageRegistry.addName(acidVial, "Acid Vial");
//		LanguageRegistry.addName(causticVial, "Empty Vial");
//		LanguageRegistry.addName(cryoniteVial, "Cryonite Vial");
//		LanguageRegistry.addName(neptuniumVial, "Neptunium Vial");
//		LanguageRegistry.addName(netherflowVial, "Netherflow Vial");
//		LanguageRegistry.addName(obsidiumVial, "Obsidium Vial");
//		LanguageRegistry.addName(plutoniumVial, "Plutonium Vial");
//		LanguageRegistry.addName(radioniteVial, "Radionite Vial");
//		LanguageRegistry.addName(uraniumVial, "Uranium Vial");
		
		causticBoat = (new ItemCausticBoat()).setUnlocalizedName("causticBoat");
		GameRegistry.registerItem(causticBoat, "causticBoat");
//		LanguageRegistry.addName(causticBoat, "Radionite Boat");

		batteryEmpty = (new ItemPlasma()).setUnlocalizedName("batteryEmpty");
		batteryCryo = (new ItemPlasma()).setUnlocalizedName("batteryCryonite");
		batteryCharged = (new ItemPlasma()).setUnlocalizedName("batteryCharged");
		batteryOverCharged = (new ItemPlasma()).setUnlocalizedName("batteryOvercharged");
		batteryPlasma = (new ItemPlasma()).setUnlocalizedName("batteryPlasma");
		beamSplitter = (new ItemPlasma()).setUnlocalizedName("beamSplitter");
		energyCell = (new ItemPlasma()).setUnlocalizedName("energyCell");
		thermoPellet = (new ItemPlasma()).setUnlocalizedName("thermopellet");
		GameRegistry.registerItem(batteryEmpty, "batteryEmpty");
		GameRegistry.registerItem(batteryCryo, "batteryCryo");
		GameRegistry.registerItem(batteryCharged, "batteryCharged");
		GameRegistry.registerItem(batteryOverCharged, "batteryOverCharged");
		GameRegistry.registerItem(batteryPlasma, "batteryPlasma");
		GameRegistry.registerItem(beamSplitter, "beamSplitter");
		GameRegistry.registerItem(energyCell, "energyCell");
		GameRegistry.registerItem(thermoPellet, "thermopellet");
//		LanguageRegistry.addName(batteryCharged, "Caustic Battery: Charged");
//		LanguageRegistry.addName(batteryCryo, "Cryo Battery");
//		LanguageRegistry.addName(batteryEmpty, "Caustic Battery: Empty");
//		LanguageRegistry.addName(batteryOverCharged, "Caustic Battery: Overcharged");
//		LanguageRegistry.addName(batteryPlasma, "Caustic Battery: Plasma");
//		LanguageRegistry.addName(beamSplitter, "Rifle Beam Splitter");
//		LanguageRegistry.addName(energyCell, "Energy Cell");
//		LanguageRegistry.addName(thermoPellet, "Thermopellet");
		
		acidgun = (new ItemEnergyWeapon(200)).setUnlocalizedName("acidGun");
		cryoblaster = (new ItemEnergyWeapon(100)).setUnlocalizedName("cryoBlaster");
		lasershotgun = (new ItemEnergyWeapon(200)).setUnlocalizedName("laserShotgun");
		lasergun = (new ItemEnergyWeapon(200)).setUnlocalizedName("laserGun");
		lasergunsplit = (new ItemEnergyWeapon(300)).setUnlocalizedName("laserGunSplit");
		plasmagun = (new ItemEnergyWeapon(200)).setUnlocalizedName("plasmaGun");
		plasmagunsplit = (new ItemEnergyWeapon(300)).setUnlocalizedName("plasmaGunSplit");
		railgun = (new ItemEnergyWeapon(200)).setUnlocalizedName("railGun");
		GameRegistry.registerItem(acidgun, "acidGun");
		GameRegistry.registerItem(cryoblaster, "cryoBlaster");
		GameRegistry.registerItem(lasershotgun, "laserShotgun");
		GameRegistry.registerItem(lasergun, "laserGun");
		GameRegistry.registerItem(lasergunsplit, "laserGunSplit");
		GameRegistry.registerItem(plasmagun, "plasmaGun");
		GameRegistry.registerItem(plasmagunsplit, "plasmaGunSplit");
		GameRegistry.registerItem(railgun, "railgun");
//		LanguageRegistry.addName(acidgun, "Acid Launcher");
//		LanguageRegistry.addName(cryoblaster, "Cryo Blaster");
//		LanguageRegistry.addName(lasergun, "Laser Rifle");
//		LanguageRegistry.addName(lasergunsplit, "Split Beam Laser Rifle");
//		LanguageRegistry.addName(lasershotgun, "Laser Shotgun");
//		LanguageRegistry.addName(plasmagun, "Plasma Rifle");
//		LanguageRegistry.addName(plasmagunsplit, "Split Beam Plasma Rifle");
//		LanguageRegistry.addName(railgun, "Rail Gun");
		
		acidGrenade = new ItemAcidGrenade().setUnlocalizedName("acidGrenade");
		GameRegistry.registerItem(acidGrenade, "acidGrenade");
//		LanguageRegistry.addName(acidGrenade, "Acid Grenade");

		hazmatBoots = (new ItemPlasmaArmor(ArmorMaterial.GOLD, proxy.addArmor("hazmat"), 3)).setUnlocalizedName("hazmatBoots");
		hazmatHood = (new ItemPlasmaArmor(ArmorMaterial.GOLD, proxy.addArmor("hazmat"), 0)).setUnlocalizedName("hazmatHelmet");
		hazmatJacket = (new ItemPlasmaArmor(ArmorMaterial.GOLD, proxy.addArmor("hazmat"), 1)).setUnlocalizedName("hazmatPlate");
		hazmatPants = (new ItemPlasmaArmor(ArmorMaterial.GOLD, proxy.addArmor("hazmat"), 2)).setUnlocalizedName("hazmatLegs");
		GameRegistry.registerItem(hazmatBoots, "hazmat");
		GameRegistry.registerItem(hazmatHood, "hazmatHood");
		GameRegistry.registerItem(hazmatJacket, "hazmatJacket");
		GameRegistry.registerItem(hazmatPants, "");
//		LanguageRegistry.addName(hazmatBoots,	"Hazmat Boots");
//		LanguageRegistry.addName(hazmatHood,	"Hazmat Hood");
//		LanguageRegistry.addName(hazmatJacket,	"Hazmat Jacket");
//		LanguageRegistry.addName(hazmatPants,	"Hazmat Pants");
		
		plasmaLeather = (new ItemPlasma()).setUnlocalizedName("plasmaLeather");
		GameRegistry.registerItem(plasmaLeather, "plasmaLeather");
//		LanguageRegistry.addName(plasmaLeather,	"Plasma Leather");
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
