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
	
	public static Block acidMoving;
	public static Block acidStill;
	public static Block acidTnt;
	public static Block cryoniteMoving;
	public static Block cryoniteStill;
	public static Block frozenCryonite;
	public static Block neptuniumMoving;
	public static Block neptuniumStill;
	public static Block netherflowMoving;
	public static Block netherflowStill;
	public static Block obsidiumMoving;
	public static Block obsidiumStill;
	public static Block plutoniumMoving;
	public static Block plutoniumStill;
	public static Block radioniteMoving;
	public static Block radioniteStill;
	public static Block reinforcedGlass;
	public static Block uraniumMoving;
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
	
	public static int oreNeptuniumIndex = 8;
	public static int oreObsidiumIndex = 9;
	public static int orePlutoniumIndex = 13;
	public static int oreRadioniteIndex = 14;
	public static int oreUraniumIndex = 4;
	public static int oreLeadIndex = 23;

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

	public static int glowClothAcidIndex = 6;
	public static int glowClothCryoniteIndex = 21;
	public static int glowClothNeptuniumIndex = 18;
	public static int glowClothNetherflowIndex = 17;
	public static int glowClothObsidiumIndex = 22;
	public static int glowClothPlutoniumIndex = 20;
	public static int glowClothRadioniteIndex = 7;
	public static int glowClothUraniumIndex = 19;
	
	public static int plasmaIndex = 33;
	public static int acidBarrierIndex = 16;
	public static int acidTntBottomIndex = 2;
	public static int acidTntSideIndex = 1;
	public static int acidTntTopIndex = 3;

	public static int hazmatBootsIndex = 15;
	public static int hazmatHoodIndex = 16;
	public static int hazmatJacketIndex = 17;
	public static int hazmatPantsIndex = 18;
	public static int plasmaLeatherIndex = 35;

	public static int goopAcidIndex = 34;
	public static int goopCryoniteIndex = 9;
	public static int goopNeptuniumIndex = 23;
	public static int goopNetherflowIndex = 26;
	public static int goopObsidiumIndex = 29;
	public static int goopPlutoniumIndex = 39;
	public static int goopRadioniteIndex = 42;
	public static int goopUraniumIndex = 47;

	public static int frozenCryoniteIndex = 5;
	public static int plasmaBenchFrontActiveIndex = 10;
	public static int plasmaBenchFrontIdleIndex = 11;
	public static int plasmaBenchSidesIndex = 12;
	public static int reinforcedGlassIndex = 15;

	public static int acidStillIndex = 199;
	public static int acidMovingIndex = acidStillIndex + 1;
	public static int cryoniteStillIndex = 202;
	public static int cryoniteMovingIndex = cryoniteStillIndex + 1;
	public static int neptuniumStillIndex = 205;
	public static int neptuniumMovingIndex = neptuniumStillIndex + 1;
	public static int netherflowStillIndex = 225;
	public static int netherflowMovingIndex = netherflowStillIndex + 1;
	public static int obsidiumStillIndex = 228;
	public static int obsidiumMovingIndex = obsidiumStillIndex + 1;
	public static int plutoniumStillIndex = 231;
	public static int plutoniumMovingIndex = plutoniumStillIndex + 1;
	public static int radioniteStillIndex = 234;
	public static int radioniteMovingIndex = radioniteStillIndex + 1;
	public static int uraniumStillIndex = 237;
	public static int uraniumMovingIndex = uraniumStillIndex + 1;

	public static int ingotCryoniteIndex = 10;
	public static int ingotLeadIndex = 50;
	public static int ingotNeptuniumIndex = 24;
	public static int ingotNetherflowIndex = 27;
	public static int ingotObsidiumIndex = 30;
	public static int ingotPlutoniumIndex = 40;
	public static int ingotRadioniteIndex = 43;
	public static int ingotUraniumIndex = 48;

	public static int causticVialIndex = 12;
	public static int causticBoatIndex = 5;
	public static int cryoniteVialIndex = 11;
	public static int acidVialIndex = 0;
	public static int neptuniumVialIndex = 25;
	public static int netherflowVialIndex = 28;
	public static int obsidiumVialIndex = 31;
	public static int plutoniumVialIndex = 41;
	public static int radioniteVialIndex = 44;
	public static int uraniumVialIndex = 49;
	public static int acidGrenadeIndex = 1;
	public static int thermoPelletIndex = 46;

	public static int acidgunIndex = 2;
	public static int batteryChargedIndex = 6;
	public static int batteryCryoIndex = 7;
	public static int batteryEmptyIndex = 13;
	public static int batteryOverChargedIndex = 32;
	public static int batteryPlasmaIndex = 36;
	public static int beamSplitterIndex = 4;
	public static int cryoblasterIndex = 8;
	public static int energyCellIndex = 14;
	public static int lasergunIndex = 19;
	public static int lasergunsplitIndex = 20;
	public static int lasershotgunIndex = 21;
	public static int plasmagunIndex = 37;
	public static int plasmagunsplitIndex = 38;
	public static int railgunIndex = 45;
	
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
		orePlasma = new BlockPlasmaOre(oreBlockID, orePlutoniumIndex).setLightValue(0.5334f);
		GameRegistry.registerBlock(orePlasma, com.elvenwater.malkierian.Plasmacraft.common.items.ItemPlasmaOre.class);
		LanguageRegistry.addName(new ItemStack(orePlasma, 1, plutoniumMeta), 	"Plutonium Ore");
		LanguageRegistry.addName(new ItemStack(orePlasma, 1, radioniteMeta), 	"Radionite Ore");
		LanguageRegistry.addName(new ItemStack(orePlasma, 1, uraniumMeta), 		"Uranium Ore");
		LanguageRegistry.addName(new ItemStack(orePlasma, 1, neptuniumMeta), 	"Neptunium Ore");
		LanguageRegistry.addName(new ItemStack(orePlasma, 1, obsidiumMeta), 	"Obsidium Ore");
		
		oreLeadBlock = new BlockOre(oreLeadBlockID, oreLeadIndex).setLightValue(0.0f).setBlockName("Lead Ore");
		oreLeadBlock.setTextureFile(CommonProxy.BLOCK_PNG);
		GameRegistry.registerBlock(oreLeadBlock);
		LanguageRegistry.addName(oreLeadBlock, "Lead Ore");
		
		MinecraftForge.setBlockHarvestLevel(orePlasma, obsidiumMeta, 	"pickaxe", 3);
		MinecraftForge.setBlockHarvestLevel(orePlasma, uraniumMeta, 	"pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(orePlasma, radioniteMeta, 	"pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(orePlasma, plutoniumMeta, 	"pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(orePlasma, neptuniumMeta, 	"pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(oreLeadBlock, "pickaxe", 1);
		//MinecraftForge.setBlockHarvestLevel(frozenCryonite, "pickaxe", 1);
		
		acidMoving =		(new BlockCausticFlowing	(acidFlowingBlockID, 	   acidStillIndex, 		 acidMovingIndex, 		causticID, acidStillBlockID, 	   acidFlowingBlockID			 )).setBlockName("acidMoving");
		acidStill = 		(new BlockCausticStationary	(acidStillBlockID, 		   acidStillIndex, 		 acidMovingIndex, 		causticID, acidStillBlockID, 	   acidFlowingBlockID, 		 1.0F)).setBlockName("acidStill");
		cryoniteMoving = 	(new BlockCausticFlowing	(cryoniteFlowingBlockID,   cryoniteStillIndex, 	 cryoniteMovingIndex, 	causticID, cryoniteStillBlockID,   cryoniteFlowingBlockID		 )).setBlockName("cryoniteMoving");
		cryoniteStill = 	(new BlockCausticStationary	(cryoniteStillBlockID, 	   cryoniteStillIndex, 	 cryoniteMovingIndex, 	causticID, cryoniteStillBlockID,   cryoniteFlowingBlockID,   0.0F)).setBlockName("cryoniteStill");
		neptuniumMoving = 	(new BlockCausticFlowing	(neptuniumFlowingBlockID,  neptuniumStillIndex,  neptuniumMovingIndex, 	causticID, neptuniumStillBlockID,  neptuniumFlowingBlockID	 	 )).setBlockName("neptuniumMoving");
		neptuniumStill = 	(new BlockCausticStationary	(neptuniumStillBlockID,	neptuniumStillIndex,  neptuniumMovingIndex, 	causticID, neptuniumStillBlockID,  neptuniumFlowingBlockID,  1.0F)).setBlockName("neptuniumStill");
		netherflowMoving = 	(new BlockCausticFlowing	(netherflowFlowingBlockID, netherflowStillIndex, netherflowMovingIndex, causticID, netherflowStillBlockID, netherflowFlowingBlockID		 )).setBlockName("netherflowMoving");
		netherflowStill = 	(new BlockCausticStationary	(netherflowStillBlockID,   netherflowStillIndex, netherflowMovingIndex, causticID, netherflowStillBlockID, netherflowFlowingBlockID, 1.0F)).setBlockName("netherflowStill");
		obsidiumMoving = 	(new BlockCausticFlowing	(obsidiumFlowingBlockID,   obsidiumStillIndex, 	 obsidiumMovingIndex, 	causticID, obsidiumStillBlockID,   obsidiumFlowingBlockID		 )).setBlockName("obsidiumMoving");
		obsidiumStill = 	(new BlockCausticStationary	(obsidiumStillBlockID, 	   obsidiumStillIndex, 	 obsidiumMovingIndex, 	causticID, obsidiumStillBlockID,   obsidiumFlowingBlockID,   1.0F)).setBlockName("obsidiumStill");
		plutoniumMoving = 	(new BlockCausticFlowing	(plutoniumFlowingBlockID,  plutoniumStillIndex,  plutoniumMovingIndex, 	causticID, plutoniumStillBlockID,  plutoniumFlowingBlockID		 )).setBlockName("plutoniumMoving");
		plutoniumStill = 	(new BlockCausticStationary	(plutoniumStillBlockID,	plutoniumStillIndex,  plutoniumMovingIndex, 	causticID, plutoniumStillBlockID,  plutoniumFlowingBlockID,  1.0F)).setBlockName("plutoniumStill");
		radioniteMoving = 	(new BlockCausticFlowing	(radioniteFlowingBlockID,  radioniteStillIndex,  radioniteMovingIndex, 	causticID, radioniteStillBlockID,  radioniteFlowingBlockID		 )).setBlockName("radioniteMoving");
		radioniteStill = 	(new BlockCausticStationary	(radioniteStillBlockID,	radioniteStillIndex,  radioniteMovingIndex, 	causticID, radioniteStillBlockID,  radioniteFlowingBlockID,  1.0F)).setBlockName("radioniteStill");
		uraniumMoving = 	(new BlockCausticFlowing	(uraniumFlowingBlockID,	uraniumStillIndex, 	 uraniumMovingIndex, 	causticID, uraniumStillBlockID,	uraniumFlowingBlockID		 )).setBlockName("uraniumMoving");
		uraniumStill = 		(new BlockCausticStationary	(uraniumStillBlockID, 	   uraniumStillIndex, 	 uraniumMovingIndex, 	causticID, uraniumStillBlockID,	uraniumFlowingBlockID,	 1.0F)).setBlockName("uraniumStill");
		GameRegistry.registerBlock(acidMoving, "Acid");
//		GameRegistry.registerBlock(acidStill);
		GameRegistry.registerBlock(cryoniteMoving, "Cryonite");
//		GameRegistry.registerBlock(cryoniteStill);
		GameRegistry.registerBlock(neptuniumMoving, "Neptunium");
//		GameRegistry.registerBlock(neptuniumStill);
		GameRegistry.registerBlock(netherflowMoving, "Netherflow");
//		GameRegistry.registerBlock(netherflowStill);
		GameRegistry.registerBlock(obsidiumMoving, "Obsidium");
//		GameRegistry.registerBlock(obsidiumStill);
		GameRegistry.registerBlock(plutoniumMoving, "Plutonium");
//		GameRegistry.registerBlock(plutoniumStill);
		GameRegistry.registerBlock(radioniteMoving, "Radionite");
//		GameRegistry.registerBlock(radioniteStill);
		GameRegistry.registerBlock(uraniumMoving, "Uranium");
//		GameRegistry.registerBlock(uraniumStill);
		LanguageRegistry.addName(acidMoving, "Acid");
		//LanguageRegistry.addName(acidStill, "Acid Still");
		LanguageRegistry.addName(cryoniteMoving, "Cryonite");
		//LanguageRegistry.addName(cryoniteStill, "Cryonite Still");
		LanguageRegistry.addName(neptuniumMoving, "Neptunium");
		//LanguageRegistry.addName(neptuniumStill, "Neptunium Still");
		LanguageRegistry.addName(netherflowMoving, "Netherflow");
		//LanguageRegistry.addName(netherflowStill, "Netherflow Still");
		LanguageRegistry.addName(obsidiumMoving, "Obsidium");
		//LanguageRegistry.addName(obsidiumStill, "Obsidium Still");
		LanguageRegistry.addName(plutoniumMoving, "Plutonium");
		//LanguageRegistry.addName(plutoniumStill, "Plutonium Still");
		LanguageRegistry.addName(radioniteMoving, "Radionite");
		//LanguageRegistry.addName(radioniteStill, "Radionite Still");
		LanguageRegistry.addName(uraniumMoving, "Uranium");
		//LanguageRegistry.addName(uraniumStill, "Uranium Still");
		
		glowCloth = new BlockGlowCloth(glowClothBlockID, glowClothAcidIndex);
		GameRegistry.registerBlock(glowCloth, com.elvenwater.malkierian.Plasmacraft.common.items.ItemGlowCloth.class);
		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothAcidMeta),		"Acid Glowcloth");
		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothRadioniteMeta),	"Radionite Glowcloth");
		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothNetherflowMeta),	"Netherflow Glowcloth");
		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothNeptuniumMeta),	"Neptunium Glowcloth");
		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothUraniumMeta),		"Uranium Glowcloth");
		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothPlutoniumMeta),	"Plutonium Glowcloth");
		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothCryoniteMeta),	"Cryonite Glowcloth");
		LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothObsidiumMeta),	"Obsidium Glowcloth");
		
		frozenCryonite = (new BlockReinforcedGlass(cryoniteFrozenBlockID, frozenCryoniteIndex, Material.glass, false, 1.0F)).setBlockName("frozenCryonite");
		reinforcedGlass = (new BlockReinforcedGlass(reinforcedGlassBlockID, reinforcedGlassIndex, Material.glass, false, 500.0F)).setBlockName("reinforcedGlass");
		GameRegistry.registerBlock(frozenCryonite, "Frozen Cryonite");
		GameRegistry.registerBlock(reinforcedGlass, "Reinforced Glass");
		LanguageRegistry.addName(frozenCryonite, "Frozen Cryonite");
		LanguageRegistry.addName(reinforcedGlass, "Reinforced Glass");
		
		plasmaBench = (new BlockPlasmaBench(plasmaBenchBlockID)).setBlockName("plasmaBench");
		GameRegistry.registerBlock(plasmaBench);
		LanguageRegistry.addName(plasmaBench, "Plasmificator");
		
		acidBarrier = (new BlockAcidBarrier(acidBarrierBlockID, acidBarrierIndex)).setBlockName("acidBarrier");
		GameRegistry.registerBlock(acidBarrier);
		LanguageRegistry.addName(acidBarrier, "Acid Barrier");
		
		acidTnt = (new BlockAcidTNT(acidTNTBlockID, acidTntSideIndex, acidTntTopIndex, acidTntBottomIndex)).setBlockName("acidTnt");
		GameRegistry.registerBlock(acidTnt, "Acid TNT");
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
		goopAcid = (new ItemPlasma(goopAcidID)).setIconIndex(goopAcidIndex).setItemName("goopAcid");
		goopCryonite = (new ItemPlasma(goopCryoniteID)).setIconIndex(goopCryoniteIndex).setItemName("goopCryonite");
		goopNeptunium = (new ItemPlasma(goopNeptuniumID)).setIconIndex(goopNeptuniumIndex).setItemName("goopNeptunium");
		goopNetherflow = (new ItemPlasma(goopNetherflowID)).setIconIndex(goopNetherflowIndex).setItemName("goopNetherflow");
		goopObsidium = (new ItemPlasma(goopObsidiumID)).setIconIndex(goopObsidiumIndex).setItemName("goopObsidium");
		goopPlutonium = (new ItemPlasma(goopPlutoniumID)).setIconIndex(goopPlutoniumIndex).setItemName("goopPlutonium");
		goopRadionite = (new ItemPlasma(goopRadioniteID)).setIconIndex(goopRadioniteIndex).setItemName("goopRadionite");
		goopUranium = (new ItemPlasma(goopUraniumID)).setIconIndex(goopUraniumIndex).setItemName("goopUranium");
		plasma = (new ItemPlasma(plasmaID)).setIconIndex(plasmaIndex).setItemName("plasma");
		LanguageRegistry.addName(goopAcid, "Acid Goop");
		LanguageRegistry.addName(goopCryonite, "Cryonite Goop");
		LanguageRegistry.addName(goopNeptunium, "Neptunium Goop");
		LanguageRegistry.addName(goopNetherflow, "Netherflow Goop");
		LanguageRegistry.addName(goopObsidium, "Obsidium Goop");
		LanguageRegistry.addName(goopPlutonium, "Plutonium Goop");
		LanguageRegistry.addName(goopRadionite, "Radionite Goop");
		LanguageRegistry.addName(goopUranium, "Uranium Goop");
		
		ingotCryonite = (new ItemPlasma(ingotCryoniteID)).setIconIndex(ingotCryoniteIndex).setItemName("ingotCryonite");
		ingotLead = (new ItemPlasma(ingotLeadID)).setIconIndex(ingotLeadIndex).setItemName("ingotLead");
		ingotNeptunium = (new ItemPlasma(ingotNeptuniumID)).setIconIndex(ingotNeptuniumIndex).setItemName("ingotNeptunium");
		ingotNetherflow = (new ItemPlasma(ingotNetherflowID)).setIconIndex(ingotNetherflowIndex).setItemName("ingotNetherflow");
		ingotObsidium = (new ItemPlasma(ingotObsidiumID)).setIconIndex(ingotObsidiumIndex).setItemName("ingotObsidium");
		ingotPlutonium = (new ItemPlasma(ingotPlutoniumID)).setIconIndex(ingotPlutoniumIndex).setItemName("ingotPlutonium");
		ingotRadionite = (new ItemPlasma(ingotRadioniteID)).setIconIndex(ingotRadioniteIndex).setItemName("ingotRadionite");
		ingotUranium = (new ItemPlasma(ingotUraniumID)).setIconIndex(ingotUraniumIndex).setItemName("ingotUranium");
		LanguageRegistry.addName(ingotCryonite, "Cryonite Ingot");
		LanguageRegistry.addName(ingotLead, "Lead Ingot");
		LanguageRegistry.addName(ingotNeptunium, "Neptunium Ingot");
		LanguageRegistry.addName(ingotNetherflow, "Netherflow Ingot");
		LanguageRegistry.addName(ingotObsidium, "Obsidium Ingot");
		LanguageRegistry.addName(ingotPlutonium, "Plutonium Ingot");
		LanguageRegistry.addName(ingotRadionite, "Radionite Ingot");
		LanguageRegistry.addName(ingotUranium, "Uranium Ingot");
		LanguageRegistry.addName(plasma, "Plasma");

		acidVial = (new ItemVial(acidVialID, acidMoving.blockID, EnumPlasmaLiquid.ACID)).setIconIndex(acidVialIndex).setItemName("acidVial");
		causticVial = (new ItemVial(emptyVialID, 0, EnumPlasmaLiquid.EMPTY)).setIconIndex(causticVialIndex).setItemName("causticVial");
		cryoniteVial = (new ItemVial(cryoniteVialID, cryoniteMoving.blockID, EnumPlasmaLiquid.CRYONITE)).setIconIndex(cryoniteVialIndex).setItemName("cryoniteVial");
		neptuniumVial = (new ItemVial(neptuniumVialID, neptuniumMoving.blockID, EnumPlasmaLiquid.NEPTUNIUM)).setIconIndex(neptuniumVialIndex).setItemName("neptuniumVial");
		netherflowVial = (new ItemVial(netherflowVialID, netherflowMoving.blockID, EnumPlasmaLiquid.NETHERFLOW)).setIconIndex(netherflowVialIndex).setItemName("netherflowVial");
		obsidiumVial = (new ItemVial(obsidiumVialID, obsidiumMoving.blockID, EnumPlasmaLiquid.OBSIDIUM)).setIconIndex(obsidiumVialIndex).setItemName("obsidiumVial");
		plutoniumVial = (new ItemVial(plutoniumViaID, plutoniumMoving.blockID, EnumPlasmaLiquid.PLUTONIUM)).setIconIndex(plutoniumVialIndex).setItemName("plutoniumVial");
		radioniteVial = (new ItemVial(radioniteVialID, radioniteMoving.blockID, EnumPlasmaLiquid.RADIONITE)).setIconIndex(radioniteVialIndex).setItemName("radioniteVial");
		uraniumVial = (new ItemVial(uraniumViaID, uraniumMoving.blockID, EnumPlasmaLiquid.URANIUM)).setIconIndex(uraniumVialIndex).setItemName("uraniumVial");
		LanguageRegistry.addName(acidVial, "Acid Vial");
		LanguageRegistry.addName(causticVial, "Empty Vial");
		LanguageRegistry.addName(cryoniteVial, "Cryonite Vial");
		LanguageRegistry.addName(neptuniumVial, "Neptunium Vial");
		LanguageRegistry.addName(netherflowVial, "Netherflow Vial");
		LanguageRegistry.addName(obsidiumVial, "Obsidium Vial");
		LanguageRegistry.addName(plutoniumVial, "Plutonium Vial");
		LanguageRegistry.addName(radioniteVial, "Radionite Vial");
		LanguageRegistry.addName(uraniumVial, "Uranium Vial");
		
		causticBoat = (new ItemCausticBoat(causticBoatID)).setIconIndex(causticBoatIndex).setItemName("causticBoat");
		LanguageRegistry.addName(causticBoat, "Radionite Boat");

		batteryEmpty = (new ItemPlasma(batteryEmptyID)).setIconIndex(batteryEmptyIndex).setItemName("batteryEmpty");
		batteryCryo = (new ItemPlasma(batteryCryoID)).setIconIndex(batteryCryoIndex).setItemName("batteryCryo");
		batteryCharged = (new ItemPlasma(batteryChargedID)).setIconIndex(batteryChargedIndex).setItemName("batteryCharged");
		batteryOverCharged = (new ItemPlasma(batteryOverChargedID)).setIconIndex(batteryOverChargedIndex).setItemName("batteryOvercharged");
		batteryPlasma = (new ItemPlasma(batteryPlasmaID)).setIconIndex(batteryPlasmaIndex).setItemName("batteryPlasma");
		beamSplitter = (new ItemPlasma(beamSplitterID)).setIconIndex(beamSplitterIndex).setItemName("beamSplitter");
		energyCell = (new ItemPlasma(energyCellID)).setIconIndex(energyCellIndex).setItemName("energyCell");
		thermoPellet = (new ItemPlasma(thermoPelletID)).setIconIndex(thermoPelletIndex).setItemName("thermopellet");
		LanguageRegistry.addName(batteryCharged, "Caustic Battery: Charged");
		LanguageRegistry.addName(batteryCryo, "Cryo Battery");
		LanguageRegistry.addName(batteryEmpty, "Caustic Battery: Empty");
		LanguageRegistry.addName(batteryOverCharged, "Caustic Battery: Overcharged");
		LanguageRegistry.addName(batteryPlasma, "Caustic Battery: Plasma");
		LanguageRegistry.addName(beamSplitter, "Rifle Beam Splitter");
		LanguageRegistry.addName(energyCell, "Energy Cell");
		LanguageRegistry.addName(thermoPellet, "Thermopellet");
		
		acidgun = (new ItemEnergyWeapon(acidgunID, 200)).setIconIndex(acidgunIndex).setItemName("acidGun");
		cryoblaster = (new ItemEnergyWeapon(cryoBlasterID, 100)).setIconIndex(cryoblasterIndex).setItemName("cryoBlaster");
		lasershotgun = (new ItemEnergyWeapon(laserShotgunID, 200)).setIconIndex(lasershotgunIndex).setItemName("lasershotgunGun");
		lasergun = (new ItemEnergyWeapon(laserGunID, 200)).setIconIndex(lasergunIndex).setItemName("laserGun");
		lasergunsplit = (new ItemEnergyWeapon(laserGunsplitID, 300)).setIconIndex(lasergunsplitIndex).setItemName("laserGunSplit");
		plasmagun = (new ItemEnergyWeapon(plasmagunID, 200)).setIconIndex(plasmagunIndex).setItemName("plasmaGun");
		plasmagunsplit = (new ItemEnergyWeapon(plasmagunsplitID, 300)).setIconIndex(plasmagunsplitIndex).setItemName("plasmaGunSplit");
		railgun = (new ItemEnergyWeapon(railgunID, 200)).setIconIndex(railgunIndex).setItemName("railGun");
		LanguageRegistry.addName(acidgun, "Acid Launcher");
		LanguageRegistry.addName(cryoblaster, "Cryo Blaster");
		LanguageRegistry.addName(lasergun, "Laser Rifle");
		LanguageRegistry.addName(lasergunsplit, "Split Beam Laser Rifle");
		LanguageRegistry.addName(lasershotgun, "Laser Shotgun");
		LanguageRegistry.addName(plasmagun, "Plasma Rifle");
		LanguageRegistry.addName(plasmagunsplit, "Split Beam Plasma Rifle");
		LanguageRegistry.addName(railgun, "Rail Gun");
		
		acidGrenade = new ItemAcidGrenade(acidGrenadeID).setIconIndex(acidGrenadeIndex).setItemName("acidGrenade");
		LanguageRegistry.addName(acidGrenade, "Acid Grenade");

		hazmatBoots = (new ItemPlasmaArmor(hazmatBootsID, EnumArmorMaterial.GOLD, proxy.addArmor("hazmat"), 3)).setIconIndex(hazmatBootsIndex).setItemName("bootsHazmat");
		hazmatHood = (new ItemPlasmaArmor(hazmatHoodID, EnumArmorMaterial.GOLD, proxy.addArmor("hazmat"), 0)).setIconIndex(hazmatHoodIndex).setItemName("helmetHazmat");
		hazmatJacket = (new ItemPlasmaArmor(hazmatJacketID, EnumArmorMaterial.GOLD, proxy.addArmor("hazmat"), 1)).setIconIndex(hazmatJacketIndex).setItemName("plateHazmat");
		hazmatPants = (new ItemPlasmaArmor(hazmatPantsID, EnumArmorMaterial.GOLD, proxy.addArmor("hazmat"), 2)).setIconIndex(hazmatPantsIndex).setItemName("legsHazmat");
		LanguageRegistry.addName(hazmatBoots,	"Hazmat Boots");
		LanguageRegistry.addName(hazmatHood,	"Hazmat Hood");
		LanguageRegistry.addName(hazmatJacket,	"Hazmat Jacket");
		LanguageRegistry.addName(hazmatPants,	"Hazmat Pants");
		
		plasmaLeather = (new ItemPlasma(plasmaLeatherID)).setIconIndex(plasmaLeatherIndex).setItemName("plasmaLeather");
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
			"X#X", "# #", "X#X", Character.valueOf('#'), Item.ingotIron, Character.valueOf('X'), goopAcid
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
		Configuration c = new Configuration(new File(Minecraft.getMinecraftDir() + "/config/PlasmaCraft.cfg"));
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
