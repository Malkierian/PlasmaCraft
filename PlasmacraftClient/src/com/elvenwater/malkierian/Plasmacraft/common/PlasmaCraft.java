package com.elvenwater.malkierian.Plasmacraft.common;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.util.Icon;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

import com.elvenwater.malkierian.Plasmacraft.client.GuiHandler;
import com.elvenwater.malkierian.Plasmacraft.common.Entities.EntityAcid;
import com.elvenwater.malkierian.Plasmacraft.common.Entities.EntityCryoBlast;
import com.elvenwater.malkierian.Plasmacraft.common.Entities.EntityLaser;
import com.elvenwater.malkierian.Plasmacraft.common.Entities.EntityLaserShotgun;
import com.elvenwater.malkierian.Plasmacraft.common.Entities.EntityPlasma;
import com.elvenwater.malkierian.Plasmacraft.common.Entities.EntityRailGun;
import com.elvenwater.malkierian.Plasmacraft.common.blocks.BlockAcidBarrier;
import com.elvenwater.malkierian.Plasmacraft.common.blocks.BlockAcidTNT;
import com.elvenwater.malkierian.Plasmacraft.common.blocks.BlockCausticFlowing;
import com.elvenwater.malkierian.Plasmacraft.common.blocks.BlockCausticFluids;
import com.elvenwater.malkierian.Plasmacraft.common.blocks.BlockCausticStationary;
import com.elvenwater.malkierian.Plasmacraft.common.blocks.BlockGlowCloth;
import com.elvenwater.malkierian.Plasmacraft.common.blocks.BlockPlasmaBench;
import com.elvenwater.malkierian.Plasmacraft.common.blocks.BlockPlasmaOre;
import com.elvenwater.malkierian.Plasmacraft.common.blocks.BlockReinforcedGlass;
import com.elvenwater.malkierian.Plasmacraft.common.items.ItemAcidGrenade;
import com.elvenwater.malkierian.Plasmacraft.common.items.ItemCausticBoat;
import com.elvenwater.malkierian.Plasmacraft.common.items.ItemEnergyWeapon;
import com.elvenwater.malkierian.Plasmacraft.common.items.ItemPlasma;
import com.elvenwater.malkierian.Plasmacraft.common.items.ItemPlasmaArmor;
import com.elvenwater.malkierian.Plasmacraft.common.items.ItemVial;
import com.elvenwater.malkierian.Plasmacraft.common.listeners.PCBucketFillEvent;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "PlasmaCraft", name = "PlasmaCraft", version = "0.3.2")
@NetworkMod(channels = "PlasmaCraft", clientSideRequired = true, serverSideRequired = false,
packetHandler = PacketHandler.class)
public class PlasmaCraft
{
	public static Block acidBarrier;
	
	public static Block orePlasma;
	public static Block oreLeadBlock;
	public static Block glowCloth;
	
	public static Block plasmaBench;
	
	public static int causticID = 180;
	
	public static BlockCausticFluids acidMoving;
	public static Block acidStill;
	public static Block acidTnt;
	public static BlockCausticFluids cryoniteMoving;
	public static Block cryoniteStill;
	public static Block frozenCryonite;
	public static BlockCausticFluids neptuniumMoving;
	public static Block neptuniumStill;
	public static BlockCausticFluids netherflowMoving;
	public static Block netherflowStill;
	public static BlockCausticFluids obsidiumMoving;
	public static Block obsidiumStill;
	public static BlockCausticFluids plutoniumMoving;
	public static Block plutoniumStill;
	public static BlockCausticFluids radioniteMoving;
	public static Block radioniteStill;
	public static Block reinforcedGlass;
	public static BlockCausticFluids uraniumMoving;
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
	
	public static Icon oreLeadIndex;

	public static final int plutoniumMeta = 0;
	public static final int radioniteMeta = 1;
	public static final int neptuniumMeta = 2;
	public static final int obsidiumMeta = 3; 
	public static final int uraniumMeta = 4;
	
	public static int oreBlockID;
	public static int oreLeadBlockID;
	public static int glowClothBlockID;
	
	public static int acidFlowingBlockID;
	public static int acidStillBlockID;
	public static int cryoniteFlowingBlockID;
	public static int cryoniteStillBlockID;
	public static int neptuniumFlowingBlockID;
	public static int neptuniumStillBlockID;
	public static int netherflowFlowingBlockID;
	public static int netherflowStillBlockID;
	public static int obsidiumFlowingBlockID;
	public static int obsidiumStillBlockID;
	public static int plutoniumFlowingBlockID;
	public static int plutoniumStillBlockID;
	public static int radioniteFlowingBlockID;
	public static int radioniteStillBlockID;
	public static int uraniumFlowingBlockID;
	public static int uraniumStillBlockID;
	
	public static int cryoniteFrozenBlockID;
	public static int reinforcedGlassBlockID;
	
	public static int plasmaBenchBlockID;
	public static int acidBarrierBlockID;
	public static int acidTNTBlockID;

	public static int goopAcidID;
	public static int goopCryoniteID;
	public static int goopNeptuniumID;
	public static int goopNetherflowID;
	public static int goopObsidiumID;
	public static int goopPlutoniumID;
	public static int goopRadioniteID;
	public static int goopUraniumID;
	public static int plasmaID;

	public static int ingotCryoniteID;
	public static int ingotLeadID;
	public static int ingotNeptuniumID;
	public static int ingotNetherflowID;
	public static int ingotObsidiumID;
	public static int ingotPlutoniumID;
	public static int ingotRadioniteID;
	public static int ingotUraniumID;
	
	public static int acidVialID;
	public static int cryoniteVialID;
	public static int emptyVialID;
	public static int neptuniumVialID;
	public static int netherflowVialID;
	public static int obsidiumVialID;
	public static int plutoniumViaID;
	public static int radioniteVialID;
	public static int uraniumViaID;
	
	public static int causticBoatID;
	public static int thermoPelletID;
	public static int acidGrenadeID;

	public static int hazmatBootsID;
	public static int hazmatHoodID;
	public static int hazmatJacketID;
	public static int hazmatPantsID;
	public static int plasmaLeatherID;

	public static boolean liquidSourceExplodesAfterCausticExplosion;
	public static int acidgunID;
	public static int batteryChargedID;
	public static int batteryCryoID;
	public static int batteryEmptyID;
	public static int batteryOverChargedID;
	public static int batteryPlasmaID;
	public static int beamSplitterID;
	public static int cryoBlasterID;
	public static int energyCellID;
	public static int laserGunID;
	public static int laserGunsplitID;
	public static int laserShotgunID;
	public static int plasmagunID;
	public static int plasmagunsplitID;
	public static int railgunID;

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
	
	private GuiHandler guiHandler = new GuiHandler();
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="com.elvenwater.malkierian.Plasmacraft.client.ClientProxy", serverSide="com.elvenwater.malkierian.Plasmacraft.common.CommonProxy")
	public static CommonProxy proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		instance = this;
	}
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		loadConfig();
		
		NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
		
		proxy.registerRenderers();
		
		MinecraftForge.EVENT_BUS.register(new PCBucketFillEvent());
		
		registerBlocks();
		
		registerFuel();
		
		registerItems();
		
		registerRecipes();
		
		registerOres();
		
		registerTileEntities();
		
		proxy.registerTextureFX();
		
		registerEntities();
		
		GameRegistry.registerWorldGenerator(new WorldGenerator());
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event)
	{
		// Stub Method
	}
	
	private void registerBlocks()
	{
		orePlasma = new BlockPlasmaOre(oreBlockID).setLightValue(0.5334f).setUnlocalizedName("orePlasma");
		GameRegistry.registerBlock(orePlasma, com.elvenwater.malkierian.Plasmacraft.common.items.ItemPlasmaOre.class, "orePlasma");
		LanguageRegistry.addName(new ItemStack(orePlasma, 1, plutoniumMeta), 	"Plutonium Ore");
		LanguageRegistry.addName(new ItemStack(orePlasma, 1, radioniteMeta), 	"Radionite Ore");
		LanguageRegistry.addName(new ItemStack(orePlasma, 1, uraniumMeta), 		"Uranium Ore");
		LanguageRegistry.addName(new ItemStack(orePlasma, 1, neptuniumMeta), 	"Neptunium Ore");
		LanguageRegistry.addName(new ItemStack(orePlasma, 1, obsidiumMeta), 	"Obsidium Ore");
		
		oreLeadBlock = new BlockOre(oreLeadBlockID).setLightValue(0.0f).setUnlocalizedName("oreLead");
		GameRegistry.registerBlock(oreLeadBlock, "oreLead");
		LanguageRegistry.addName(oreLeadBlock, "Lead Ore");
		
		MinecraftForge.setBlockHarvestLevel(orePlasma, obsidiumMeta, 	"pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(orePlasma, uraniumMeta, 	"pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(orePlasma, radioniteMeta, 	"pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(orePlasma, plutoniumMeta, 	"pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(orePlasma, neptuniumMeta, 	"pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(oreLeadBlock, "pickaxe", 1);
		
		acidMoving =		(BlockCausticFluids)(new BlockCausticFlowing	(acidFlowingBlockID, 		acidStillBlockID, 	   	acidFlowingBlockID))				
				.setPlasmaLiquid(PlasmaLiquid.ACID)
				.setUnlocalizedName("acidMoving");
		acidStill = 							(new BlockCausticStationary		(acidStillBlockID, 			acidStillBlockID, 	   	acidFlowingBlockID, 		1.0F))
				.setPlasmaLiquid(PlasmaLiquid.ACID)
				.setUnlocalizedName("acidStill");
		cryoniteMoving = 	(BlockCausticFluids)(new BlockCausticFlowing	(cryoniteFlowingBlockID, 	cryoniteStillBlockID,   cryoniteFlowingBlockID))
				.setPlasmaLiquid(PlasmaLiquid.CRYONITE)
				.setUnlocalizedName("cryoniteMoving");
		cryoniteStill = 						(new BlockCausticStationary		(cryoniteStillBlockID, 		cryoniteStillBlockID,   cryoniteFlowingBlockID,   	0.0F))
				.setPlasmaLiquid(PlasmaLiquid.CRYONITE)
				.setUnlocalizedName("cryoniteStill");
		neptuniumMoving = 	(BlockCausticFluids)(new BlockCausticFlowing	(neptuniumFlowingBlockID, 	neptuniumStillBlockID,  neptuniumFlowingBlockID))
				.setPlasmaLiquid(PlasmaLiquid.NEPTUNIUM)
				.setUnlocalizedName("neptuniumMoving");
		neptuniumStill = 						(new BlockCausticStationary		(neptuniumStillBlockID, 	neptuniumStillBlockID,  neptuniumFlowingBlockID,  	1.0F))
				.setPlasmaLiquid(PlasmaLiquid.NEPTUNIUM)
				.setUnlocalizedName("neptuniumStill");
		netherflowMoving = 	(BlockCausticFluids)(new BlockCausticFlowing	(netherflowFlowingBlockID, 	netherflowStillBlockID, netherflowFlowingBlockID))
				.setPlasmaLiquid(PlasmaLiquid.NETHERFLOW)
				.setUnlocalizedName("netherflowMoving");
		netherflowStill = 						(new BlockCausticStationary		(netherflowStillBlockID, 	netherflowStillBlockID, netherflowFlowingBlockID, 	1.0F))
				.setPlasmaLiquid(PlasmaLiquid.NETHERFLOW)
				.setUnlocalizedName("netherflowStill");
		obsidiumMoving = 	(BlockCausticFluids)(new BlockCausticFlowing	(obsidiumFlowingBlockID, 	obsidiumStillBlockID,   obsidiumFlowingBlockID))
				.setPlasmaLiquid(PlasmaLiquid.OBSIDIUM)
				.setUnlocalizedName("obsidiumMoving");
		obsidiumStill = 						(new BlockCausticStationary		(obsidiumStillBlockID, 		obsidiumStillBlockID,   obsidiumFlowingBlockID,   	1.0F))
				.setPlasmaLiquid(PlasmaLiquid.OBSIDIUM)
				.setUnlocalizedName("obsidiumStill");
		plutoniumMoving = 	(BlockCausticFluids)(new BlockCausticFlowing	(plutoniumFlowingBlockID, 	plutoniumStillBlockID,  plutoniumFlowingBlockID))
				.setPlasmaLiquid(PlasmaLiquid.PLUTONIUM)
				.setUnlocalizedName("plutoniumMoving");
		plutoniumStill = 						(new BlockCausticStationary		(plutoniumStillBlockID, 	plutoniumStillBlockID,  plutoniumFlowingBlockID,  	1.0F))
				.setPlasmaLiquid(PlasmaLiquid.PLUTONIUM)
				.setUnlocalizedName("plutoniumStill");
		radioniteMoving = 	(BlockCausticFluids)(new BlockCausticFlowing	(radioniteFlowingBlockID, 	radioniteStillBlockID,  radioniteFlowingBlockID))
				.setPlasmaLiquid(PlasmaLiquid.RADIONITE)
				.setUnlocalizedName("radioniteMoving");
		radioniteStill = 						(new BlockCausticStationary		(radioniteStillBlockID, 	radioniteStillBlockID,  radioniteFlowingBlockID,  	1.0F))
				.setPlasmaLiquid(PlasmaLiquid.RADIONITE)
				.setUnlocalizedName("radioniteStill");
		uraniumMoving = 	(BlockCausticFluids)(new BlockCausticFlowing	(uraniumFlowingBlockID, 	uraniumStillBlockID,	uraniumFlowingBlockID))
				.setPlasmaLiquid(PlasmaLiquid.URANIUM)
				.setUnlocalizedName("uraniumMoving");
		uraniumStill = 							(new BlockCausticStationary		(uraniumStillBlockID, 		uraniumStillBlockID,	uraniumFlowingBlockID,		1.0F))
				.setPlasmaLiquid(PlasmaLiquid.URANIUM)
				.setUnlocalizedName("uraniumStill");
		GameRegistry.registerBlock(acidMoving, "Acid");
		GameRegistry.registerBlock(cryoniteMoving, "Cryonite");
		GameRegistry.registerBlock(neptuniumMoving, "Neptunium");
		GameRegistry.registerBlock(netherflowMoving, "Netherflow");
		GameRegistry.registerBlock(obsidiumMoving, "Obsidium");
		GameRegistry.registerBlock(plutoniumMoving, "Plutonium");
		GameRegistry.registerBlock(radioniteMoving, "Radionite");
		GameRegistry.registerBlock(uraniumMoving, "Uranium");
		LanguageRegistry.addName(acidMoving, "Acid");
		LanguageRegistry.addName(cryoniteMoving, "Cryonite");
		LanguageRegistry.addName(neptuniumMoving, "Neptunium");
		LanguageRegistry.addName(netherflowMoving, "Netherflow");
		LanguageRegistry.addName(obsidiumMoving, "Obsidium");
		LanguageRegistry.addName(plutoniumMoving, "Plutonium");
		LanguageRegistry.addName(radioniteMoving, "Radionite");
		LanguageRegistry.addName(uraniumMoving, "Uranium");
		
		glowCloth = new BlockGlowCloth(glowClothBlockID).setUnlocalizedName("glowCloth");
		GameRegistry.registerBlock(glowCloth, com.elvenwater.malkierian.Plasmacraft.common.items.ItemGlowCloth.class, "glowCloth");
		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothAcidMeta),		"Acid Glowcloth");
		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothRadioniteMeta),	"Radionite Glowcloth");
		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothNetherflowMeta),	"Netherflow Glowcloth");
		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothNeptuniumMeta),	"Neptunium Glowcloth");
		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothUraniumMeta),		"Uranium Glowcloth");
		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothPlutoniumMeta),	"Plutonium Glowcloth");
		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothCryoniteMeta),	"Cryonite Glowcloth");
		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothObsidiumMeta),	"Obsidium Glowcloth");
		
		frozenCryonite = (new BlockReinforcedGlass(cryoniteFrozenBlockID, "frozenCryonite", Material.glass, false, 1.0F)).setUnlocalizedName("frozenCryonite");
		reinforcedGlass = (new BlockReinforcedGlass(reinforcedGlassBlockID, "reinforcedGlass", Material.glass, false, 500.0F)).setUnlocalizedName("reinforcedGlass");
		GameRegistry.registerBlock(frozenCryonite, "Frozen Cryonite");
		GameRegistry.registerBlock(reinforcedGlass, "Reinforced Glass");
		LanguageRegistry.addName(frozenCryonite, "Frozen Cryonite");
		LanguageRegistry.addName(reinforcedGlass, "Reinforced Glass");
		
		plasmaBench = (new BlockPlasmaBench(plasmaBenchBlockID)).setUnlocalizedName("plasmaBench");
		GameRegistry.registerBlock(plasmaBench, "plasmaBench");
		LanguageRegistry.addName(plasmaBench, "Plasmificator");
		
		acidBarrier = (new BlockAcidBarrier(acidBarrierBlockID)).setUnlocalizedName("acidBarrier");
		GameRegistry.registerBlock(acidBarrier, "acidBarrier");
		LanguageRegistry.addName(acidBarrier, "Acid Barrier");
		
		acidTnt = (new BlockAcidTNT(acidTNTBlockID)).setUnlocalizedName("acidTnt");
		GameRegistry.registerBlock(acidTnt, "acidTnt");
		LanguageRegistry.addName(acidTnt, "Acid TNT");
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
		goopAcid = (new ItemPlasma(goopAcidID)).setUnlocalizedName("goopAcid");
		goopCryonite = (new ItemPlasma(goopCryoniteID)).setUnlocalizedName("goopCryonite");
		goopNeptunium = (new ItemPlasma(goopNeptuniumID)).setUnlocalizedName("goopNeptunium");
		goopNetherflow = (new ItemPlasma(goopNetherflowID)).setUnlocalizedName("goopNetherflow");
		goopObsidium = (new ItemPlasma(goopObsidiumID)).setUnlocalizedName("goopObsidium");
		goopPlutonium = (new ItemPlasma(goopPlutoniumID)).setUnlocalizedName("goopPlutonium");
		goopRadionite = (new ItemPlasma(goopRadioniteID)).setUnlocalizedName("goopRadionite");
		goopUranium = (new ItemPlasma(goopUraniumID)).setUnlocalizedName("goopUranium");
		LanguageRegistry.addName(goopAcid, "Acid Goop");
		LanguageRegistry.addName(goopCryonite, "Cryonite Goop");
		LanguageRegistry.addName(goopNeptunium, "Neptunium Goop");
		LanguageRegistry.addName(goopNetherflow, "Netherflow Goop");
		LanguageRegistry.addName(goopObsidium, "Obsidium Goop");
		LanguageRegistry.addName(goopPlutonium, "Plutonium Goop");
		LanguageRegistry.addName(goopRadionite, "Radionite Goop");
		LanguageRegistry.addName(goopUranium, "Uranium Goop");
		
		ingotCryonite = (new ItemPlasma(ingotCryoniteID)).setUnlocalizedName("ingotCryonite");
		ingotLead = (new ItemPlasma(ingotLeadID)).setUnlocalizedName("ingotLead");
		ingotNeptunium = (new ItemPlasma(ingotNeptuniumID)).setUnlocalizedName("ingotNeptunium");
		ingotNetherflow = (new ItemPlasma(ingotNetherflowID)).setUnlocalizedName("ingotNetherflow");
		ingotObsidium = (new ItemPlasma(ingotObsidiumID)).setUnlocalizedName("ingotObsidium");
		ingotPlutonium = (new ItemPlasma(ingotPlutoniumID)).setUnlocalizedName("ingotPlutonium");
		ingotRadionite = (new ItemPlasma(ingotRadioniteID)).setUnlocalizedName("ingotRadionite");
		ingotUranium = (new ItemPlasma(ingotUraniumID)).setUnlocalizedName("ingotUranium");
		plasma = (new ItemPlasma(plasmaID)).setUnlocalizedName("plasma");
		LanguageRegistry.addName(ingotCryonite, "Cryonite Ingot");
		LanguageRegistry.addName(ingotLead, "Lead Ingot");
		LanguageRegistry.addName(ingotNeptunium, "Neptunium Ingot");
		LanguageRegistry.addName(ingotNetherflow, "Netherflow Ingot");
		LanguageRegistry.addName(ingotObsidium, "Obsidium Ingot");
		LanguageRegistry.addName(ingotPlutonium, "Plutonium Ingot");
		LanguageRegistry.addName(ingotRadionite, "Radionite Ingot");
		LanguageRegistry.addName(ingotUranium, "Uranium Ingot");
		LanguageRegistry.addName(plasma, "Plasma");

		acidVial = (new ItemVial(acidVialID, acidMoving.blockID, PlasmaLiquid.ACID)).setUnlocalizedName("vial_acid");
		causticVial = (new ItemVial(emptyVialID, 0, PlasmaLiquid.EMPTY)).setUnlocalizedName("vial_empty");
		cryoniteVial = (new ItemVial(cryoniteVialID, cryoniteMoving.blockID, PlasmaLiquid.CRYONITE)).setUnlocalizedName("vial_cryonite");
		neptuniumVial = (new ItemVial(neptuniumVialID, neptuniumMoving.blockID, PlasmaLiquid.NEPTUNIUM)).setUnlocalizedName("vial_neptunium");
		netherflowVial = (new ItemVial(netherflowVialID, netherflowMoving.blockID, PlasmaLiquid.NETHERFLOW)).setUnlocalizedName("vial_netherflow");
		obsidiumVial = (new ItemVial(obsidiumVialID, obsidiumMoving.blockID, PlasmaLiquid.OBSIDIUM)).setUnlocalizedName("vial_obsidium");
		plutoniumVial = (new ItemVial(plutoniumViaID, plutoniumMoving.blockID, PlasmaLiquid.PLUTONIUM)).setUnlocalizedName("vial_plutonium");
		radioniteVial = (new ItemVial(radioniteVialID, radioniteMoving.blockID, PlasmaLiquid.RADIONITE)).setUnlocalizedName("vial_radionite");
		uraniumVial = (new ItemVial(uraniumViaID, uraniumMoving.blockID, PlasmaLiquid.URANIUM)).setUnlocalizedName("vial_uranium");
		LanguageRegistry.addName(acidVial, "Acid Vial");
		LanguageRegistry.addName(causticVial, "Empty Vial");
		LanguageRegistry.addName(cryoniteVial, "Cryonite Vial");
		LanguageRegistry.addName(neptuniumVial, "Neptunium Vial");
		LanguageRegistry.addName(netherflowVial, "Netherflow Vial");
		LanguageRegistry.addName(obsidiumVial, "Obsidium Vial");
		LanguageRegistry.addName(plutoniumVial, "Plutonium Vial");
		LanguageRegistry.addName(radioniteVial, "Radionite Vial");
		LanguageRegistry.addName(uraniumVial, "Uranium Vial");
		
		causticBoat = (new ItemCausticBoat(causticBoatID)).setUnlocalizedName("causticBoat");
		LanguageRegistry.addName(causticBoat, "Radionite Boat");

		batteryEmpty = (new ItemPlasma(batteryEmptyID)).setUnlocalizedName("batteryEmpty");
		batteryCryo = (new ItemPlasma(batteryCryoID)).setUnlocalizedName("batteryCryonite");
		batteryCharged = (new ItemPlasma(batteryChargedID)).setUnlocalizedName("batteryCharged");
		batteryOverCharged = (new ItemPlasma(batteryOverChargedID)).setUnlocalizedName("batteryOvercharged");
		batteryPlasma = (new ItemPlasma(batteryPlasmaID)).setUnlocalizedName("batteryPlasma");
		beamSplitter = (new ItemPlasma(beamSplitterID)).setUnlocalizedName("beamSplitter");
		energyCell = (new ItemPlasma(energyCellID)).setUnlocalizedName("energyCell");
		thermoPellet = (new ItemPlasma(thermoPelletID)).setUnlocalizedName("thermopellet");
		LanguageRegistry.addName(batteryCharged, "Caustic Battery: Charged");
		LanguageRegistry.addName(batteryCryo, "Cryo Battery");
		LanguageRegistry.addName(batteryEmpty, "Caustic Battery: Empty");
		LanguageRegistry.addName(batteryOverCharged, "Caustic Battery: Overcharged");
		LanguageRegistry.addName(batteryPlasma, "Caustic Battery: Plasma");
		LanguageRegistry.addName(beamSplitter, "Rifle Beam Splitter");
		LanguageRegistry.addName(energyCell, "Energy Cell");
		LanguageRegistry.addName(thermoPellet, "Thermopellet");
		
		acidgun = (new ItemEnergyWeapon(acidgunID, 200)).setUnlocalizedName("acidGun");
		cryoblaster = (new ItemEnergyWeapon(cryoBlasterID, 100)).setUnlocalizedName("cryoBlaster");
		lasershotgun = (new ItemEnergyWeapon(laserShotgunID, 200)).setUnlocalizedName("laserShotgun");
		lasergun = (new ItemEnergyWeapon(laserGunID, 200)).setUnlocalizedName("laserGun");
		lasergunsplit = (new ItemEnergyWeapon(laserGunsplitID, 300)).setUnlocalizedName("laserGunSplit");
		plasmagun = (new ItemEnergyWeapon(plasmagunID, 200)).setUnlocalizedName("plasmaGun");
		plasmagunsplit = (new ItemEnergyWeapon(plasmagunsplitID, 300)).setUnlocalizedName("plasmaGunSplit");
		railgun = (new ItemEnergyWeapon(railgunID, 200)).setUnlocalizedName("railGun");
		LanguageRegistry.addName(acidgun, "Acid Launcher");
		LanguageRegistry.addName(cryoblaster, "Cryo Blaster");
		LanguageRegistry.addName(lasergun, "Laser Rifle");
		LanguageRegistry.addName(lasergunsplit, "Split Beam Laser Rifle");
		LanguageRegistry.addName(lasershotgun, "Laser Shotgun");
		LanguageRegistry.addName(plasmagun, "Plasma Rifle");
		LanguageRegistry.addName(plasmagunsplit, "Split Beam Plasma Rifle");
		LanguageRegistry.addName(railgun, "Rail Gun");
		
		acidGrenade = new ItemAcidGrenade(acidGrenadeID).setUnlocalizedName("acidGrenade");
		LanguageRegistry.addName(acidGrenade, "Acid Grenade");

		hazmatBoots = (new ItemPlasmaArmor(hazmatBootsID, EnumArmorMaterial.GOLD, proxy.addArmor("hazmat"), 3)).setUnlocalizedName("hazmatBoots");
		hazmatHood = (new ItemPlasmaArmor(hazmatHoodID, EnumArmorMaterial.GOLD, proxy.addArmor("hazmat"), 0)).setUnlocalizedName("hazmatHelmet");
		hazmatJacket = (new ItemPlasmaArmor(hazmatJacketID, EnumArmorMaterial.GOLD, proxy.addArmor("hazmat"), 1)).setUnlocalizedName("hazmatPlate");
		hazmatPants = (new ItemPlasmaArmor(hazmatPantsID, EnumArmorMaterial.GOLD, proxy.addArmor("hazmat"), 2)).setUnlocalizedName("hazmatLegs");
		LanguageRegistry.addName(hazmatBoots,	"Hazmat Boots");
		LanguageRegistry.addName(hazmatHood,	"Hazmat Hood");
		LanguageRegistry.addName(hazmatJacket,	"Hazmat Jacket");
		LanguageRegistry.addName(hazmatPants,	"Hazmat Pants");
		
		plasmaLeather = (new ItemPlasma(plasmaLeatherID)).setUnlocalizedName("plasmaLeather");
		LanguageRegistry.addName(plasmaLeather,	"Plasma Leather");
	}
	
	private void registerOres()
	{
		OreDictionary.registerOre("orePlutonium", new ItemStack(orePlasma, 1, plutoniumMeta));
		OreDictionary.registerOre("oreUranium", new ItemStack(orePlasma, 1, uraniumMeta));
		OreDictionary.registerOre("oreLead", oreLeadBlock);
		
		OreDictionary.registerOre("ingotPlutonium", new ItemStack(ingotPlutonium, 1));
		OreDictionary.registerOre("ingotUranium", new ItemStack(ingotUranium, 1));
		OreDictionary.registerOre("ingotLead", ingotLead);
	}
	
	private void registerRecipes()
	{
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothAcidMeta), goopAcid, new ItemStack(Block.cloth, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothPlutoniumMeta), goopPlutonium, new ItemStack(Block.cloth, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothRadioniteMeta), goopRadionite, new ItemStack(Block.cloth, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothNeptuniumMeta), goopNeptunium, new ItemStack(Block.cloth, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothNetherflowMeta), goopNetherflow, new ItemStack(Block.cloth, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothObsidiumMeta), goopObsidium, new ItemStack(Block.cloth, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothCryoniteMeta), goopCryonite, new ItemStack(Block.cloth, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(glowCloth, 1, glowClothUraniumMeta), goopUranium, new ItemStack(Block.cloth, 1, 0));
		
		GameRegistry.addRecipe(new ItemStack(reinforcedGlass, 4), new Object[] {
			" X ", "X#X", " X ", Character.valueOf('#'), Block.glass, Character.valueOf('X'), Item.ingotIron
		});
		GameRegistry.addRecipe(new ItemStack(causticVial, 1), new Object[] {
			"X#X", "Y Y", "X#X", Character.valueOf('#'), Item.ingotIron, Character.valueOf('Y'), reinforcedGlass, Character.valueOf('X'), Block.glass
		});
		GameRegistry.addRecipe(new ItemStack(plasmaBench, 1), new Object[] {
			"X#X", "# #", "X#X", Character.valueOf('#'), Item.ingotIron, Character.valueOf('X'), acidVial
		});
		GameRegistry.addRecipe(new ItemStack(acidBarrier, 1), new Object[] {
			" X ", "XZX", " X ", Character.valueOf('Z'), reinforcedGlass, Character.valueOf('X'), goopAcid
		});
		GameRegistry.addRecipe(new ItemStack(causticBoat, 1), new Object[] {
			"R R", "RRR", Character.valueOf('R'), ingotRadionite
		});
		GameRegistry.addRecipe(new ItemStack(acidTnt, 4), new Object[] {
			"APA", "GAG", "APA", Character.valueOf('A'), acidVial, Character.valueOf('G'), Item.gunpowder, Character.valueOf('P'), plasma
		});
		GameRegistry.addRecipe(new ItemStack(acidGrenade, 4), new Object[] {
			"X", "Y", "Z", Character.valueOf('X'), Item.ingotIron, Character.valueOf('Y'), acidVial, Character.valueOf('Z'), plasma
		});
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
			"N", "J", Character.valueOf('N'), goopAcid, Character.valueOf('J'), Item.leather
		});
		ModLoader.addRecipe(new ItemStack(plasmagunsplit, 1), new Object[] {
			"YB", Character.valueOf('B'), plasmagun, Character.valueOf('Y'), beamSplitter
		});
		ModLoader.addRecipe(new ItemStack(lasergunsplit, 1), new Object[] {
			"YB", Character.valueOf('B'), lasergun, Character.valueOf('Y'), beamSplitter
		});
		ModLoader.addRecipe(new ItemStack(cryoblaster, 1), new Object[] {
			"  A", "CBX", " DE", Character.valueOf('A'), ingotUranium, Character.valueOf('B'), goopCryonite, Character.valueOf('C'), ingotCryonite, Character.valueOf('D'), 
			ingotObsidium, Character.valueOf('X'), batteryCryo, Character.valueOf('E'), ingotPlutonium
		});
		ModLoader.addRecipe(new ItemStack(energyCell, 5), new Object[] {
			" R ", "RXR", " R ", Character.valueOf('R'), ingotNeptunium, Character.valueOf('X'), goopAcid
		});
		ModLoader.addRecipe(new ItemStack(batteryEmpty, 8), new Object[] {
			"IRI", "I I", "IRI", Character.valueOf('R'), ingotRadionite, Character.valueOf('I'), Item.ingotIron
		});
		ModLoader.addRecipe(new ItemStack(batteryCryo, 1), new Object[] {
			"R", "X", Character.valueOf('R'), goopCryonite, Character.valueOf('X'), batteryEmpty
		});
		ModLoader.addRecipe(new ItemStack(batteryPlasma, 1), new Object[] {
			"R", "X", Character.valueOf('R'), plasma, Character.valueOf('X'), batteryEmpty
		});
		ModLoader.addRecipe(new ItemStack(batteryCharged, 1), new Object[] {
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
		
		GameRegistry.addSmelting(oreLeadBlock.blockID, new ItemStack(ingotLead, 1), 0.1f);
	}
	
	public static void loadConfig()
	{
		Configuration c;
		if(FMLCommonHandler.instance().getSide() == Side.CLIENT)
		{
			c = new Configuration(new File(Minecraft.getMinecraftDir() + "/config/PlasmaCraft.cfg"));
		}
		else
		{
			c = new Configuration(new File("./config/PlasmaCraft.cfg"));
		}
		c.load();
		
		liquidSourceExplodesAfterCausticExplosion = c.get(Configuration.CATEGORY_GENERAL, "LiquidSourceExplodesAfterCausticExplosion", true).getBoolean(true);

		radioniteStillBlockID = c.getBlock("ID.RadioniteStill", 2519).getInt();
		radioniteFlowingBlockID = c.getBlock("ID.RadioniteFlowing", 2518).getInt();
		plutoniumStillBlockID = c.getBlock("ID.PlutoniumStill", 2517).getInt();
		plutoniumFlowingBlockID = c.getBlock("ID.PlutoniumFlowing", 2516).getInt();
		neptuniumStillBlockID = c.getBlock("ID.NeptuniumStill", 2515).getInt();
		neptuniumFlowingBlockID = c.getBlock("ID.NeptuniumFlowing", 2514).getInt();
		uraniumStillBlockID = c.getBlock("ID.UraniumStill", 2513).getInt();
		uraniumFlowingBlockID = c.getBlock("ID.UraniumFlowing", 2512).getInt();
		obsidiumStillBlockID = c.getBlock("ID.ObsidiumStill", 2511).getInt();
		obsidiumFlowingBlockID = c.getBlock("ID.ObsidiumFlowing", 2510).getInt();
		netherflowStillBlockID = c.getBlock("ID.NetherflowStill", 2509).getInt();
		netherflowFlowingBlockID = c.getBlock("ID.NetherflowFlowing", 2508).getInt();
		cryoniteFrozenBlockID = c.getBlock("ID.CryoniteFrozen", 2507).getInt();
		cryoniteStillBlockID = c.getBlock("ID.CryoniteStill", 2506).getInt();
		cryoniteFlowingBlockID = c.getBlock("ID.CryoniteFlowing", 2505).getInt();
		acidStillBlockID = c.getBlock("ID.AcidStill", 2504).getInt();
		acidFlowingBlockID = c.getBlock("ID.AcidFlowing", 2503).getInt();
		reinforcedGlassBlockID = c.getBlock("ID.ReinforcedGlass", 2502).getInt();
		plasmaBenchBlockID = c.getBlock("ID.Plasmificator", 2522).getInt();
		acidBarrierBlockID = c.getBlock("ID.AcidBarrier", 2521).getInt();
		acidTNTBlockID = c.getBlock("ID.AcidTNT", 2520).getInt();
		
		glowClothBlockID = c.getBlock("ID.GlowCloth", 2501).getInt();

		oreBlockID = c.getBlock("ID.Ore", 2500).getInt();
		oreLeadBlockID = c.getBlock("ID.LeadOre", 2523).getInt();
		
		ingotLeadID = c.getItem("ingotLeadID", 27047).getInt();
		ingotPlutoniumID = c.getItem("ingotPlutoniumID", 27048).getInt();
		ingotRadioniteID = c.getItem("ingotRadioniteID", 27049).getInt();
		emptyVialID = c.getItem("emptyVialID", 27050).getInt();
		acidVialID = c.getItem("acidVialID", 27051).getInt();
		plutoniumViaID = c.getItem("plutoniumViaID", 27052).getInt();
		radioniteVialID = c.getItem("radioniteVialID", 27053).getInt();
		plasmaID = c.getItem("plasmaID", 27055).getInt();
		causticBoatID = c.getItem("causticBoatID", 27056).getInt();
		hazmatHoodID = c.getItem("hazmatHoodID", 27057).getInt();
		hazmatJacketID = c.getItem("hazmatJacketID", 27058).getInt();
		hazmatPantsID = c.getItem("hazmatPantsID", 27059).getInt();
		hazmatBootsID = c.getItem("hazmatBootsID", 27060).getInt();
		uraniumViaID = c.getItem("uraniumViaID", 27061).getInt();
		neptuniumVialID = c.getItem("neptuniumVialID", 27062).getInt();
		netherflowVialID = c.getItem("netherflowVialID", 27063).getInt();
		obsidiumVialID = c.getItem("obsidiumVialID", 27064).getInt();
		acidGrenadeID = c.getItem("acidNadeID", 27068).getInt();
		ingotNeptuniumID = c.getItem("ingotNeptuniumID", 27069).getInt();
		ingotObsidiumID = c.getItem("ingotObsidiumID", 27070).getInt();
		goopAcidID = c.getItem("goopAcidID", 27071).getInt();
		goopPlutoniumID = c.getItem("goopPlutoniumID", 27072).getInt();
		goopRadioniteID = c.getItem("goopRadioniteID", 27073).getInt();
		goopNeptuniumID = c.getItem("goopNeptuniumID", 27074).getInt();
		goopObsidiumID = c.getItem("goopObsidiumID", 27075).getInt();
		goopNetherflowID = c.getItem("goopNetherflowID", 27076).getInt();
		goopUraniumID = c.getItem("goopUraniumID", 27077).getInt();
		ingotUraniumID = c.getItem("ingotUraniumID", 27078).getInt();
		ingotNetherflowID = c.getItem("ingotNetherflowID", 27079).getInt();
		laserGunID = c.getItem("lasergunID", 27080).getInt();
		energyCellID = c.getItem("energyCellID", 27081).getInt();
		plasmagunID = c.getItem("plasmagunID", 27082).getInt();
		batteryEmptyID = c.getItem("BatteryEmptyID", 27083).getInt();
		batteryPlasmaID = c.getItem("BatteryPlasmaID", 27084).getInt();
		acidgunID = c.getItem("acidgunID", 27085).getInt();
		beamSplitterID = c.getItem("beamSplitterID", 27086).getInt();
		plasmagunsplitID = c.getItem("plasmagunsplitID", 27087).getInt();
		laserGunsplitID = c.getItem("lasergunsplitID", 27088).getInt();
		railgunID = c.getItem("railgunID", 27089).getInt();
		batteryChargedID = c.getItem("batteryChargedID", 27090).getInt();
		batteryOverChargedID = c.getItem("batteryOverchargedID", 27091).getInt();
		laserShotgunID = c.getItem("lasershotgunID", 27092).getInt();
		cryoniteVialID = c.getItem("cryoniteVialID", 27093).getInt();
		goopCryoniteID = c.getItem("goopCryoniteID", 27094).getInt();
		ingotCryoniteID = c.getItem("ingotCryoniteID", 27095).getInt();
		cryoBlasterID = c.getItem("cryoblasterID", 20796).getInt();
		batteryCryoID = c.getItem("batterycryoID", 27097).getInt();
		thermoPelletID = c.getItem("thermoPelletID", 27098).getInt();
		plasmaLeatherID = c.getItem("plasmaLeatherID", 27099).getInt();

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
