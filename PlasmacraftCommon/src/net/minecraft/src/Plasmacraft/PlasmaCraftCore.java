package net.minecraft.src.Plasmacraft;

import java.io.File;
import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenMinable;
import net.minecraft.src.forge.Configuration;
import net.minecraft.src.forge.Property;

public class PlasmaCraftCore 
{
	public static String itemTexture = "/PlasmaCraftSprites/items0.png";
	public static String terrainTexture = "/PlasmaCraftSprites/terrain0.png";
	public static String liquidTexture = "/PlasmaCraftSprites/terrain1.png";
	
	public static String Version()
	{
		return "1.0.0/0.2.4";
	}
	
	public static IPCProxy proxy;
	
	public static Block cryoniteStill;
	public static Block cryoniteMoving;
	public static Block acidStill;
	public static Block acidMoving;
	public static Block plutoniumStill;
	public static Block plutoniumMoving;
	public static Block radioniteStill;
	public static Block radioniteMoving;
	public static Block neptuniumStill;
	public static Block neptuniumMoving;
	public static Block netherflowStill;
	public static Block netherflowMoving;
	public static Block obsidiumStill;
	public static Block obsidiumMoving;
	public static Block uraniumStill;
	public static Block uraniumMoving;
	public static Block glowCloth1;
	public static Block glowCloth2;
	public static Block orePlutonium;
	public static Block oreRadionite;
	public static Block oreNeptunium;
	public static Block oreObsidium;
	public static Block oreUranium;
	public static Block frozenCryonite;
	public static Block reinforcedGlass;
	public static Block plasmificatorIdle;
	public static Block plasmificatorActive;
	public static Block acidHot;
	public static Block acidTnt;
	public static Item causticBoat;
	public static Item ingotPlutonium;
	public static Item ingotRadionite;
	public static Item energyCell;
	public static Item beamSplitter;
	public static Item BatteryEmpty;
	public static Item ThermoPellet;
	public static Item BatteryCryo;
	public static Item BatteryCharged;
	public static Item BatteryOvercharged;
	public static Item BatteryPlasma;
	public static Item ingotNeptunium;
	public static Item ingotObsidium;
	public static Item ingotCryonite;
	public static Item ingotUranium;
	public static Item ingotNetherflow;
	public static Item goopPlutonium;
	public static Item goopRadionite;
	public static Item goopNeptunium;
	public static Item goopNetherflow;
	public static Item goopObsidium;
	public static Item goopCryonite;
	public static Item goopUranium;
	public static Item acidVial;
	public static Item fullAcidVial;
	public static Item plutoniumVial;
	public static Item radioniteVial;
	public static Item neptuniumVial;
	public static Item netherflowVial;
	public static Item obsidiumVial;
	public static Item cryoniteVial;
	public static Item uraniumVial;
	public static Item plasma;
	public static Item plasmaGel;
	public static Item plasmaLeather;
	public static Item acidGrenade;
	public static Item lasergun;
	public static Item plasmagun;
	public static Item plasmagunsplit;
	public static Item lasergunsplit;
	public static Item acidgun;
	public static Item railgun;
	public static Item cryoblaster;
	public static Item lasershotgun;
	public static Item helmetHazmat;
	public static Item plateHazmat;
	public static Item legsHazmat;
	public static Item bootsHazmat;
	public static Material caustic;
	public static Material gas;
	
	public static int orePlutoniumIndex = 13;
	public static int oreRadioniteIndex = 14;
	public static int oreNeptuniumIndex = 8;
	public static int oreObsidiumIndex = 9;
	public static int reinforcedGlassIndex = 15;
	public static int plasmificatorSidesIndex = 12;
	public static int plasmificatorFrontIdleIndex = 11;
	public static int plasmificatorFrontActiveIndex = 10;
	public static int acidHotIndex = 16;
	public static int acidTntSideIndex = 1;
	public static int acidTntTopIndex = 3;
	public static int acidTntBottomIndex = 2;
	public static int glowCloth1Index = 6;
	public static int glowCloth2Index = 7;
	public static int blockUraniumIndex = 4;
	public static int frozenCryoniteIndex = 5;
	
	public static int cryoniteStillIndex = 0;
	public static int cryoniteMovingIndex = cryoniteStillIndex + 1;
	public static int acidStillIndex = 32;
	public static int acidMovingIndex = acidStillIndex + 1;
	public static int plutoniumStillIndex = 64;
	public static int plutoniumMovingIndex = plutoniumStillIndex + 1;
	public static int radioniteStillIndex = 96;
	public static int radioniteMovingIndex = radioniteStillIndex + 1;
	public static int uraniumStillIndex = 128;
	public static int uraniumMovingIndex = uraniumStillIndex + 1;
	public static int neptuniumStillIndex = 160;
	public static int neptuniumMovingIndex = neptuniumStillIndex + 1;
	public static int netherflowStillIndex = 192;
	public static int netherflowMovingIndex = netherflowStillIndex + 1;
	public static int obsidiumStillIndex = 224;
	public static int obsidiumMovingIndex = obsidiumStillIndex + 1;
	
	public static int ingotPlutoniumIndex = 40;
	public static int ingotRadioniteIndex = 43;
	public static int energyCellIndex = 14;
	public static int beamSplitterIndex = 4;
	public static int BatteryEmptyIndex = 13;
	public static int ThermoPelletIndex = 46;
	public static int BatteryCryoIndex = 7;
	public static int BatteryChargedIndex = 6;
	public static int BatteryOverchargedIndex = 32;
	public static int BatteryPlasmaIndex = 36;
	public static int ingotNeptuniumIndex = 24;
	public static int ingotObsidiumIndex = 30;
	public static int ingotCryoniteIndex = 10;
	public static int ingotUraniumIndex = 48;
	public static int ingotNetherflowIndex = 27;
	public static int goopPlutoniumIndex = 39;
	public static int goopRadioniteIndex = 42;
	public static int goopNeptuniumIndex = 23;
	public static int goopNetherflowIndex = 26;
	public static int goopObsidiumIndex = 29;
	public static int goopCryoniteIndex = 9;
	public static int goopUraniumIndex = 47;
	public static int acidGrenadeIndex = 1;
	public static int lasergunIndex = 19;
	public static int plasmagunIndex = 37;
	public static int plasmagunsplitIndex = 38;
	public static int lasergunsplitIndex = 20;
	public static int acidgunIndex = 2;
	public static int railgunIndex = 45;
	public static int cryoblasterIndex = 8;
	public static int lasershotgunIndex = 21;
	public static int acidVialIndex = 12;
	public static int fullAcidVialIndex = 0;
	public static int plutoniumVialIndex = 41;
	public static int radioniteVialIndex = 44;
	public static int uraniumVialIndex = 49;
	public static int neptuniumVialIndex = 25;
	public static int netherflowVialIndex = 28;
	public static int obsidiumVialIndex = 31;
	public static int cryoniteVialIndex = 11;
	public static int plasmaGelIndex = 34;
	public static int plasmaLeatherIndex = 35;
	public static int plasmaIndex = 33;
	public static int causticBoatIndex = 5;
	public static int helmetIndex = 16;
	public static int plateIndex = 17;
	public static int legsIndex = 18;
	public static int bootsIndex = 15;
	
	public static int cryoniteStillBlockID;
	public static int cryoniteFlowingBlockID;
	public static int acidStillBlockID;
	public static int acidFlowingBlockID;
	public static int plutoniumStillBlockID;
	public static int plutoniumFlowingBlockID;
	public static int radioniteStillBlockID;
	public static int radioniteFlowingBlockID;
	public static int netherflowStillBlockID;
	public static int netherflowFlowingBlockID;
	public static int neptuniumStillBlockID;
	public static int neptuniumFlowingBlockID;
	public static int uraniumStillBlockID;
	public static int uraniumFlowingBlockID;
	public static int obsidiumStillBlockID;
	public static int obsidiumFlowingBlockID;
	
	public static int hazmatHoodID;
	public static int hazmatJacketID;
	public static int hazmatPantsID;
	public static int hazmatBootsID;
	public static int plasmificatorFront;
	
	public static boolean LiquidSourceExplodesAfterCausticExplosion;
	public static int cryoniteFrozenBlockID;
	public static int oreUraniumBlockID;
	public static int oreObsidiumBlockID;
	public static int oreNeptuniumBlockID;
	public static int oreRadioniteBlockID;
	public static int orePlutoniumBlockID;
	public static int pinkGlowClothBlockID;
	public static int greenGlowClothBlockID;
	public static int acidTNTBlockID;
	public static int acidBarrierBlockID;
	public static int plasmificatorActiveBlockID;
	public static int plasmificatorIdleBlockID;
	public static int reinforcedGlassBlockID;
	public static int ingotPlutoniumID;
	public static int ingotRadioniteID;
	public static int emptyVialID;
	public static int acidVialID;
	public static int plutoniumViaID;
	public static int radioniteVialID;
	public static int plasmaGelID;
	public static int plasmaID;
	public static int causticBoatID;
	public static int uraniumViaID;
	public static int neptuniumVialID;
	public static int netherflowVialID;
	public static int obsidiumVialID;
	public static int acidNadeID;
	public static int ingotNeptuniumID;
	public static int ingotObsidiumID;
	public static int goopPlutoniumID;
	public static int goopRadioniteID;
	public static int goopNeptuniumID;
	public static int goopObsidiumID;
	public static int goopNetherflowID;
	public static int plasmaLeatherID;
	public static int goopUraniumID;
	public static int ingotUraniumID;
	public static int ingotNetherflowID;
	public static int lasergunID;
	public static int energyCellID;
	public static int plasmagunID;
	public static int BatteryEmptyID;
	public static int BatteryPlasmaID;
	public static int acidgunID;
	public static int beamSplitterID;
	public static int plasmagunsplitID;
	public static int lasergunsplitID;
	public static int railgunID;
	public static int BatteryChargedID;
	public static int BatteryOverchargedID;
	public static int lasershotgunID;
	public static int cryoniteVialID;
	public static int goopCryoniteID;
	public static int ingotCryoniteID;
	public static int cryoblasterID;
	public static int batterycryoID;
	public static int ThermoPelletID;

	private static int plutoniumOreVeinCount = 2;
	private static int plutoniumOreYRange = 16;
	private static int plutoniumOreYStart = 4;
	private static int plutoniumOreVeinSize = 6;
	private static int uraniumOreVeinCount = 2;
	private static int uraniumOreYRange = 16;
	private static int uraniumOreYStart = 4;
	private static int uraniumOreVeinSize = 6;
	private static int radioniteOreVeinCount = 2;
	private static int radioniteOreYRange = 24;
	private static int radioniteOreYStart = 4;
	private static int radioniteOreVeinSize = 2;
	private static int acidLakeChance = 32;
	private static int acidLakeYCutoff = 96;
	private static int acidSpoutCount = 20;
	private static int acidSpoutYRange = 30;
	private static int acidSpoutYStart = 8;
	private static int neptuniumOreVeinCount = 4;
	private static int neptuniumOreYRange = 64;
	private static int neptuniumOreYStart = 32;
	private static int neptuniumOreVeinSize = 10;
	private static int obsidiumOreVeinCount = 4;
	private static int obsidiumOreYRange = 64;
	private static int obsidiumOreYStart = 32;
	private static int obsidiumOreVeinSize = 10;
	private static int netherflowLakeChance = 32;
	private static int netherflowLakeYRange = 16;
	private static int netherflowLakeYStart = 56;
	private static int netherflowLakeYCutoff = 96;
	private static int netherflowSpoutCount = 20;
	private static int netherflowSpoutYRange = 96;
	private static int netherflowSpoutYStart = 16;
	private static int neptuniumSpoutCount = 20;
	private static int neptuniumSpoutYRange = 64;
	private static int neptuniumSpoutYStart = 8;
	
	public static void init(int causticID)
	{
		loadConfig();
        
        ModLoader.RegisterTileEntity(TileEntityPlasmaBench.class, "plasmaBench");
        ModLoader.RegisterTileEntity(TileEntityCaustic.class, "causticTile");
		
        orePlutonium = (new BlockPlasmaOre(orePlutoniumBlockID, orePlutoniumIndex)).setBlockName("orePlutonium");
        oreRadionite = (new BlockPlasmaOre(oreRadioniteBlockID, oreRadioniteIndex)).setBlockName("oreRadionite");
        oreNeptunium = (new BlockPlasmaOre(oreNeptuniumBlockID, oreNeptuniumIndex)).setBlockName("oreNeptunium");
        oreObsidium = (new BlockPlasmaOre(oreObsidiumBlockID, oreObsidiumIndex)).setBlockName("oreObsidium");
        oreUranium = (new BlockPlasmaOre(oreUraniumBlockID, blockUraniumIndex)).setBlockName("oreUranium");
        
        glowCloth1 = (new BlockGlowCloth(greenGlowClothBlockID, glowCloth1Index)).setBlockName("glowcloth1");
        glowCloth2 = (new BlockGlowCloth(pinkGlowClothBlockID, glowCloth2Index)).setBlockName("glowcloth2");
        cryoniteStill = (new BlockCausticStationary(cryoniteStillBlockID, cryoniteStillIndex, cryoniteMovingIndex, causticID, cryoniteStillBlockID, cryoniteFlowingBlockID)).setBlockName("cryoniteStill");
        cryoniteMoving = (new BlockCausticFlowing(cryoniteFlowingBlockID, cryoniteStillIndex, cryoniteMovingIndex, causticID, cryoniteStillBlockID, cryoniteFlowingBlockID)).setBlockName("cryoniteMoving");
        acidStill = (new BlockCausticStationary(acidStillBlockID, acidStillIndex, acidMovingIndex, causticID, acidStillBlockID, acidFlowingBlockID)).setBlockName("acidStill");
        acidMoving = (new BlockCausticFlowing(acidFlowingBlockID, acidStillIndex, acidMovingIndex, causticID, acidStillBlockID, acidFlowingBlockID)).setBlockName("acidMoving");
        radioniteStill = (new BlockCausticStationary(radioniteStillBlockID, radioniteStillIndex, radioniteMovingIndex, causticID, radioniteStillBlockID, radioniteFlowingBlockID)).setBlockName("radioniteStill");
        radioniteMoving = (new BlockCausticFlowing(radioniteFlowingBlockID, radioniteStillIndex, radioniteMovingIndex, causticID, radioniteStillBlockID, radioniteFlowingBlockID)).setBlockName("radioniteMoving");
        plutoniumStill = (new BlockCausticStationary(plutoniumStillBlockID, plutoniumStillIndex, plutoniumMovingIndex, causticID, plutoniumStillBlockID, plutoniumFlowingBlockID)).setBlockName("plutoniumStill");
        plutoniumMoving = (new BlockCausticFlowing(plutoniumFlowingBlockID, plutoniumStillIndex, plutoniumMovingIndex, causticID, plutoniumStillBlockID, plutoniumFlowingBlockID)).setBlockName("plutoniumMoving");
        netherflowStill = (new BlockCausticStationary(netherflowStillBlockID, netherflowStillIndex, netherflowMovingIndex, causticID, netherflowStillBlockID, netherflowFlowingBlockID)).setBlockName("netherflowStill");
        netherflowMoving = (new BlockCausticFlowing(netherflowFlowingBlockID, netherflowStillIndex, netherflowMovingIndex, causticID, netherflowStillBlockID, netherflowFlowingBlockID)).setBlockName("netherflowMoving");
        neptuniumStill = (new BlockCausticStationary(neptuniumStillBlockID, neptuniumStillIndex, neptuniumMovingIndex, causticID, neptuniumStillBlockID, neptuniumFlowingBlockID)).setBlockName("neptuniumStill");
        neptuniumMoving = (new BlockCausticFlowing(neptuniumFlowingBlockID, neptuniumStillIndex, neptuniumMovingIndex, causticID, neptuniumStillBlockID, neptuniumFlowingBlockID)).setBlockName("neptuniumMoving");
        uraniumStill = (new BlockCausticStationary(uraniumStillBlockID, uraniumStillIndex, uraniumMovingIndex, causticID, uraniumStillBlockID, uraniumFlowingBlockID)).setBlockName("uraniumStill");
        uraniumMoving = (new BlockCausticFlowing(uraniumFlowingBlockID, uraniumStillIndex, uraniumMovingIndex, causticID, uraniumStillBlockID, uraniumFlowingBlockID)).setBlockName("uraniumMoving");
        obsidiumStill = (new BlockCausticStationary(obsidiumStillBlockID, obsidiumStillIndex, obsidiumMovingIndex, causticID, obsidiumStillBlockID, obsidiumFlowingBlockID)).setBlockName("obsidiumStill");
        obsidiumMoving = (new BlockCausticFlowing(obsidiumFlowingBlockID, obsidiumStillIndex, obsidiumMovingIndex, causticID, obsidiumStillBlockID, obsidiumFlowingBlockID)).setBlockName("obsidiumMoving");
        reinforcedGlass = (new BlockReinforcedGlass(reinforcedGlassBlockID, reinforcedGlassIndex, Material.glass, false, 500.0F)).setBlockName("reinforcedGlass");
        frozenCryonite = (new BlockReinforcedGlass(cryoniteFrozenBlockID, frozenCryoniteIndex, Material.glass, false, 1.0F)).setBlockName("frozenCryonite");
        plasmificatorIdle = (new BlockPlasmificator(plasmificatorIdleBlockID, plasmificatorSidesIndex, plasmificatorFrontIdleIndex, plasmificatorFront, false, 0.0F)).setBlockName("plasmificatorIdle");
        plasmificatorActive = (new BlockPlasmificator(plasmificatorActiveBlockID, plasmificatorSidesIndex, plasmificatorFrontIdleIndex, plasmificatorFront, true, 1.0F)).setBlockName("plasmificatorActive");
        acidHot = (new BlockAcidHot(acidBarrierBlockID, acidHotIndex)).setBlockName("acidHot");
        acidTnt = (new BlockAcidTNT(acidTNTBlockID, acidTntSideIndex, acidTntTopIndex, acidTntBottomIndex)).setBlockName("acidTnt");
        
        ingotPlutonium = (new ItemPlasma(ingotPlutoniumID)).setIconIndex(ingotPlutoniumIndex).setItemName("ingotPlutonium");
        ingotRadionite = (new ItemPlasma(ingotRadioniteID)).setIconIndex(ingotRadioniteIndex).setItemName("ingotRadionite");
        energyCell = (new ItemPlasma(energyCellID)).setIconIndex(energyCellIndex).setItemName("energyCell");
        beamSplitter = (new ItemPlasma(beamSplitterID)).setIconIndex(beamSplitterIndex).setItemName("beamSplitter");
        BatteryEmpty = (new ItemPlasma(BatteryEmptyID)).setIconIndex(BatteryEmptyIndex).setItemName("batteryEmpy");
        ThermoPellet = (new ItemPlasma(ThermoPelletID)).setIconIndex(ThermoPelletIndex).setItemName("thermpellet");
        BatteryCryo = (new ItemPlasma(batterycryoID)).setIconIndex(BatteryCryoIndex).setItemName("batteryCryo");
        BatteryCharged = (new ItemPlasma(BatteryChargedID)).setIconIndex(BatteryChargedIndex).setItemName("batteryCharged");
        BatteryOvercharged = (new ItemPlasma(BatteryOverchargedID)).setIconIndex(BatteryOverchargedIndex).setItemName("batteryOvercharged");
        BatteryPlasma = (new ItemPlasma(BatteryPlasmaID)).setIconIndex(BatteryPlasmaIndex).setItemName("batteryPlasma");
        ingotNeptunium = (new ItemPlasma(ingotNeptuniumID)).setIconIndex(ingotNeptuniumIndex).setItemName("ingotNeptunium");
        ingotObsidium = (new ItemPlasma(ingotObsidiumID)).setIconIndex(ingotObsidiumIndex).setItemName("ingotObsidium");
        ingotCryonite = (new ItemPlasma(ingotCryoniteID)).setIconIndex(ingotCryoniteIndex).setItemName("ingotCryonite");
        ingotUranium = (new ItemPlasma(ingotUraniumID)).setIconIndex(ingotUraniumIndex).setItemName("ingotUranium");
        ingotNetherflow = (new ItemPlasma(ingotNetherflowID)).setIconIndex(ingotNetherflowIndex).setItemName("ingotNetherflow");
        goopPlutonium = (new ItemPlasma(goopPlutoniumID)).setIconIndex(goopPlutoniumIndex).setItemName("goopPlutonium");
        goopRadionite = (new ItemPlasma(goopRadioniteID)).setIconIndex(goopRadioniteIndex).setItemName("goopRadionite");
        goopNeptunium = (new ItemPlasma(goopNeptuniumID)).setIconIndex(goopNeptuniumIndex).setItemName("goopNeptunium");
        goopNetherflow = (new ItemPlasma(goopNetherflowID)).setIconIndex(goopNetherflowIndex).setItemName("goopNetherflow");
        goopObsidium = (new ItemPlasma(goopObsidiumID)).setIconIndex(goopObsidiumIndex).setItemName("goopObsidium");
        goopCryonite = (new ItemPlasma(goopCryoniteID)).setIconIndex(goopCryoniteIndex).setItemName("goopCryonite");
        goopUranium = (new ItemPlasma(goopUraniumID)).setIconIndex(goopUraniumIndex).setItemName("goopUranium");
        acidVial = (new ItemVial(emptyVialID, 0, EnumVialLiquid.EMPTY)).setIconIndex(acidVialIndex).setItemName("acidVial");
        fullAcidVial = (new ItemVial(acidVialID, acidMoving.blockID, EnumVialLiquid.ACID)).setIconIndex(fullAcidVialIndex).setItemName("fullAcidVial");
        plutoniumVial = (new ItemVial(plutoniumViaID, plutoniumMoving.blockID, EnumVialLiquid.PLUTONIUM)).setIconIndex(plutoniumVialIndex).setItemName("plutoniumVial");
        radioniteVial = (new ItemVial(radioniteVialID, radioniteMoving.blockID, EnumVialLiquid.RADIONITE)).setIconIndex(radioniteVialIndex).setItemName("radioniteVial");
        uraniumVial = (new ItemVial(uraniumViaID, uraniumMoving.blockID, EnumVialLiquid.URANIUM)).setIconIndex(uraniumVialIndex).setItemName("uraniumVial");
        neptuniumVial = (new ItemVial(neptuniumVialID, neptuniumMoving.blockID, EnumVialLiquid.NEPTUNIUM)).setIconIndex(neptuniumVialIndex).setItemName("neptuniumVial");
        netherflowVial = (new ItemVial(netherflowVialID, netherflowMoving.blockID, EnumVialLiquid.NETHERFLOW)).setIconIndex(netherflowVialIndex).setItemName("netherflowVial");
        obsidiumVial = (new ItemVial(obsidiumVialID, obsidiumMoving.blockID, EnumVialLiquid.OBSIDIUM)).setIconIndex(obsidiumVialIndex).setItemName("obsidiumVial");
        cryoniteVial = (new ItemVial(cryoniteVialID, cryoniteMoving.blockID, EnumVialLiquid.CRYONITE)).setIconIndex(cryoniteVialIndex).setItemName("cryoniteVial");
        plasmaGel = (new ItemPlasma(plasmaGelID)).setIconIndex(plasmaGelIndex).setItemName("plasmaGel");
        plasmaLeather = (new ItemPlasma(plasmaLeatherID)).setIconIndex(plasmaLeatherIndex).setItemName("plasmaLeather");
        plasma = (new ItemPlasma(plasmaID)).setIconIndex(plasmaIndex).setItemName("plasma");
        causticBoat = (new ItemCausticBoat(causticBoatID)).setIconIndex(causticBoatIndex).setItemName("causticBoat");
        acidGrenade = (new ItemAcidGrenade(acidNadeID)).setIconIndex(acidGrenadeIndex).setItemName("acidGrenade");
        lasergun = (new ItemEnergyWeapon(lasergunID, 100)).setIconIndex(lasergunIndex).setItemName("laserGun");
        plasmagun = (new ItemEnergyWeapon(plasmagunID, 100)).setIconIndex(plasmagunIndex).setItemName("plasmaGun");
        plasmagunsplit = (new ItemEnergyWeapon(plasmagunsplitID, 75)).setIconIndex(plasmagunsplitIndex).setItemName("plasmaGunSplit");
        lasergunsplit = (new ItemEnergyWeapon(lasergunsplitID, 75)).setIconIndex(lasergunsplitIndex).setItemName("laserGunSplit");
        acidgun = (new ItemEnergyWeapon(acidgunID, 100)).setIconIndex(acidgunIndex).setItemName("acidGun");
        railgun = (new ItemEnergyWeapon(railgunID, 100)).setIconIndex(railgunIndex).setItemName("railGun");
        //cryoblaster = (new ItemEnergyWeapon(cryoblasterID, 100)).setIconIndex(cryoblasterIndex).setItemName("cryoBlaster");
        lasershotgun = (new ItemEnergyWeapon(lasershotgunID, 100)).setIconIndex(lasershotgunIndex).setItemName("lasershotgunGun");

        helmetHazmat = (new ItemPlasmaArmor(hazmatHoodID, EnumArmorMaterial.values()[2], ModLoader.AddArmor("hazmat"), 0)).setIconIndex(helmetIndex).setItemName("helmetHazmat");
        plateHazmat = (new ItemPlasmaArmor(hazmatJacketID, EnumArmorMaterial.values()[2], ModLoader.AddArmor("hazmat"), 1)).setIconIndex(plateIndex).setItemName("plateHazmat");
        legsHazmat = (new ItemPlasmaArmor(hazmatPantsID, EnumArmorMaterial.values()[2], ModLoader.AddArmor("hazmat"), 2)).setIconIndex(legsIndex).setItemName("legsHazmat");
        bootsHazmat = (new ItemPlasmaArmor(hazmatBootsID, EnumArmorMaterial.values()[2], ModLoader.AddArmor("hazmat"), 3)).setIconIndex(bootsIndex).setItemName("bootsHazmat");
        
        ModLoader.RegisterBlock(glowCloth1);
        ModLoader.RegisterBlock(glowCloth2);
        ModLoader.RegisterBlock(cryoniteStill);
        ModLoader.RegisterBlock(cryoniteMoving);
        ModLoader.RegisterBlock(acidStill);
        ModLoader.RegisterBlock(acidMoving);
        ModLoader.RegisterBlock(radioniteStill);
        ModLoader.RegisterBlock(radioniteMoving);
        ModLoader.RegisterBlock(plutoniumStill);
        ModLoader.RegisterBlock(plutoniumMoving);
        ModLoader.RegisterBlock(netherflowStill);
        ModLoader.RegisterBlock(netherflowMoving);
        ModLoader.RegisterBlock(neptuniumStill);
        ModLoader.RegisterBlock(neptuniumMoving);
        ModLoader.RegisterBlock(uraniumStill);
        ModLoader.RegisterBlock(uraniumMoving);
        ModLoader.RegisterBlock(obsidiumStill);
        ModLoader.RegisterBlock(obsidiumMoving);
        ModLoader.RegisterBlock(orePlutonium);
        ModLoader.RegisterBlock(oreRadionite);
        ModLoader.RegisterBlock(oreNeptunium);
        ModLoader.RegisterBlock(oreObsidium);
        ModLoader.RegisterBlock(oreUranium);
        ModLoader.RegisterBlock(frozenCryonite);
        ModLoader.RegisterBlock(reinforcedGlass);
        ModLoader.RegisterBlock(plasmificatorIdle);
        ModLoader.RegisterBlock(plasmificatorActive);
        ModLoader.RegisterBlock(acidHot);
        ModLoader.RegisterBlock(acidTnt);
        
        AddRecipes();
	}
	
	public static int getInt(Property property)
	{
		return Integer.parseInt(property.value);
	}
	
	public static boolean getBool(Property property)
	{
		return Boolean.parseBoolean(property.value);
	}
	
    public static void loadConfig()
    {
    	Configuration c = new Configuration(new File(proxy.getMinecraftDirectory() + "config/PlasmaCraft.cfg"));
    	c.load();
    	
        LiquidSourceExplodesAfterCausticExplosion = getBool(c.getOrCreateBooleanProperty("LiquidSourceExplodesAfterCausticExplosion", Configuration.GENERAL_PROPERTY, false));

        radioniteStillBlockID = getInt(c.getOrCreateBlockIdProperty("ID.RadioniteStill", 143));
        radioniteFlowingBlockID = getInt(c.getOrCreateBlockIdProperty("ID.RadioniteFlowing", 142));
        plutoniumStillBlockID = getInt(c.getOrCreateBlockIdProperty("ID.PlutoniumStill", 141));
        plutoniumFlowingBlockID = getInt(c.getOrCreateBlockIdProperty("ID.PlutoniumFlowing", 140));
        neptuniumStillBlockID = getInt(c.getOrCreateBlockIdProperty("ID.NeptuniumStill", 139));
        neptuniumFlowingBlockID = getInt(c.getOrCreateBlockIdProperty("ID.NeptuniumFlowing", 138));
        uraniumStillBlockID = getInt(c.getOrCreateBlockIdProperty("ID.UraniumStill", 137));
        uraniumFlowingBlockID = getInt(c.getOrCreateBlockIdProperty("ID.UraniumFlowing", 136));
        obsidiumStillBlockID = getInt(c.getOrCreateBlockIdProperty("ID.ObsidiumStill", 135));
        obsidiumFlowingBlockID = getInt(c.getOrCreateBlockIdProperty("ID.ObsidiumFlowing", 134));
        netherflowStillBlockID = getInt(c.getOrCreateBlockIdProperty("ID.NetherflowStill", 133));
        netherflowFlowingBlockID = getInt(c.getOrCreateBlockIdProperty("ID.NetherflowFlowing", 132));
        cryoniteFrozenBlockID = getInt(c.getOrCreateBlockIdProperty("ID.CryoniteFrozen", 131));
        cryoniteStillBlockID = getInt(c.getOrCreateBlockIdProperty("ID.CryoniteStill", 130));
        cryoniteFlowingBlockID = getInt(c.getOrCreateBlockIdProperty("ID.CryoniteFlowing", 129));
        oreUraniumBlockID = getInt(c.getOrCreateBlockIdProperty("ID.OreUranium", 128));
        acidStillBlockID = getInt(c.getOrCreateBlockIdProperty("ID.AcidStill", 127));
        acidFlowingBlockID = getInt(c.getOrCreateBlockIdProperty("ID.AcidFlowing", 126));
        reinforcedGlassBlockID = getInt(c.getOrCreateBlockIdProperty("ID.ReinforcedGlass", 125));
        plasmificatorIdleBlockID = getInt(c.getOrCreateBlockIdProperty("ID.PlasmificatorIdle", 153));
        plasmificatorActiveBlockID = getInt(c.getOrCreateBlockIdProperty("ID.PlasmificatorActive", 152));
        acidBarrierBlockID = getInt(c.getOrCreateBlockIdProperty("ID.AcidBarrier", 151));
        acidTNTBlockID = getInt(c.getOrCreateBlockIdProperty("ID.AcidTNT", 150));
        oreNeptuniumBlockID = getInt(c.getOrCreateBlockIdProperty("ID.OreNeptunium", 149));
        oreObsidiumBlockID = getInt(c.getOrCreateBlockIdProperty("ID.OreObsidium", 148));
        greenGlowClothBlockID = getInt(c.getOrCreateBlockIdProperty("ID.GreenGlowCloth", 147));
        pinkGlowClothBlockID = getInt(c.getOrCreateBlockIdProperty("ID.PinkGlowCloth", 146));
        orePlutoniumBlockID = getInt(c.getOrCreateBlockIdProperty("ID.OrePlutonium", 145));
        oreRadioniteBlockID = getInt(c.getOrCreateBlockIdProperty("ID.OreRadionite", 144));
        
        ingotPlutoniumID = getInt(c.getOrCreateIntProperty("ingotPlutoniumID", Configuration.ITEM_PROPERTY, 2048));
        ingotRadioniteID = getInt(c.getOrCreateIntProperty("ingotRadioniteID", Configuration.ITEM_PROPERTY, 2049));
        emptyVialID = getInt(c.getOrCreateIntProperty("emptyVialID", Configuration.ITEM_PROPERTY, 2050));
        acidVialID = getInt(c.getOrCreateIntProperty("acidVialID", Configuration.ITEM_PROPERTY, 2051));
        plutoniumViaID = getInt(c.getOrCreateIntProperty("plutoniumViaID", Configuration.ITEM_PROPERTY, 2052));
        radioniteVialID = getInt(c.getOrCreateIntProperty("radioniteVialID", Configuration.ITEM_PROPERTY, 2053));
        plasmaGelID = getInt(c.getOrCreateIntProperty("plasmaGelID", Configuration.ITEM_PROPERTY, 2054));
        plasmaID = getInt(c.getOrCreateIntProperty("plasmaID", Configuration.ITEM_PROPERTY, 2055));
        causticBoatID = getInt(c.getOrCreateIntProperty("causticBoatID", Configuration.ITEM_PROPERTY, 2056));
        hazmatHoodID = getInt(c.getOrCreateIntProperty("hazmatHoodID", Configuration.ITEM_PROPERTY, 2057));
        hazmatJacketID = getInt(c.getOrCreateIntProperty("hazmatJacketID", Configuration.ITEM_PROPERTY, 2058));
        hazmatPantsID = getInt(c.getOrCreateIntProperty("hazmatPantsID", Configuration.ITEM_PROPERTY, 2059));
        hazmatBootsID = getInt(c.getOrCreateIntProperty("hazmatBootsID", Configuration.ITEM_PROPERTY, 2060));
        uraniumViaID = getInt(c.getOrCreateIntProperty("uraniumViaID", Configuration.ITEM_PROPERTY, 2061));
        neptuniumVialID = getInt(c.getOrCreateIntProperty("neptuniumVialID", Configuration.ITEM_PROPERTY, 2062));
        netherflowVialID = getInt(c.getOrCreateIntProperty("netherflowVialID", Configuration.ITEM_PROPERTY, 2063));
        obsidiumVialID = getInt(c.getOrCreateIntProperty("obsidiumVialID", Configuration.ITEM_PROPERTY, 2064));
        acidNadeID = getInt(c.getOrCreateIntProperty("acidNadeID", Configuration.ITEM_PROPERTY, 2068));
        ingotNeptuniumID = getInt(c.getOrCreateIntProperty("ingotNeptuniumID", Configuration.ITEM_PROPERTY, 2069));
        ingotObsidiumID = getInt(c.getOrCreateIntProperty("ingotObsidiumID", Configuration.ITEM_PROPERTY, 2070));
        goopPlutoniumID = getInt(c.getOrCreateIntProperty("goopPlutoniumID", Configuration.ITEM_PROPERTY, 2071));
        goopRadioniteID = getInt(c.getOrCreateIntProperty("goopRadioniteID", Configuration.ITEM_PROPERTY, 2072));
        goopNeptuniumID = getInt(c.getOrCreateIntProperty("goopNeptuniumID", Configuration.ITEM_PROPERTY, 2073));
        goopObsidiumID = getInt(c.getOrCreateIntProperty("goopObsidiumID", Configuration.ITEM_PROPERTY, 2074));
        goopNetherflowID = getInt(c.getOrCreateIntProperty("goopNetherflowID", Configuration.ITEM_PROPERTY, 2075));
        plasmaLeatherID = getInt(c.getOrCreateIntProperty("plasmaLeatherID", Configuration.ITEM_PROPERTY, 2076));
        goopUraniumID = getInt(c.getOrCreateIntProperty("goopUraniumID", Configuration.ITEM_PROPERTY, 2077));
        ingotUraniumID = getInt(c.getOrCreateIntProperty("ingotUraniumID", Configuration.ITEM_PROPERTY, 2078));
        ingotNetherflowID = getInt(c.getOrCreateIntProperty("ingotNetherflowID", Configuration.ITEM_PROPERTY, 2079));
        lasergunID = getInt(c.getOrCreateIntProperty("lasergunID", Configuration.ITEM_PROPERTY, 2080));
        energyCellID = getInt(c.getOrCreateIntProperty("energyCellID", Configuration.ITEM_PROPERTY, 2081));
        plasmagunID = getInt(c.getOrCreateIntProperty("plasmagunID", Configuration.ITEM_PROPERTY, 2082));
        BatteryEmptyID = getInt(c.getOrCreateIntProperty("BatteryEmptyID", Configuration.ITEM_PROPERTY, 2083));
        BatteryPlasmaID = getInt(c.getOrCreateIntProperty("BatteryPlasmaID", Configuration.ITEM_PROPERTY, 2084));
        acidgunID = getInt(c.getOrCreateIntProperty("acidgunID", Configuration.ITEM_PROPERTY, 2085));
        beamSplitterID = getInt(c.getOrCreateIntProperty("beamSplitterID", Configuration.ITEM_PROPERTY, 2086));
        plasmagunsplitID = getInt(c.getOrCreateIntProperty("plasmagunsplitID", Configuration.ITEM_PROPERTY, 2087));
        lasergunsplitID = getInt(c.getOrCreateIntProperty("lasergunsplitID", Configuration.ITEM_PROPERTY, 2088));
        railgunID = getInt(c.getOrCreateIntProperty("railgunID", Configuration.ITEM_PROPERTY, 2089));
        BatteryChargedID = getInt(c.getOrCreateIntProperty("BatteryChargedID", Configuration.ITEM_PROPERTY, 2090));
        BatteryOverchargedID = getInt(c.getOrCreateIntProperty("BatteryOverchargedID", Configuration.ITEM_PROPERTY, 2091));
        lasershotgunID = getInt(c.getOrCreateIntProperty("lasershotgunID", Configuration.ITEM_PROPERTY, 2092));
        cryoniteVialID = getInt(c.getOrCreateIntProperty("cryoniteVialID", Configuration.ITEM_PROPERTY, 2093));
        goopCryoniteID = getInt(c.getOrCreateIntProperty("goopCryoniteID", Configuration.ITEM_PROPERTY, 2094));
        ingotCryoniteID = getInt(c.getOrCreateIntProperty("ingotCryoniteID", Configuration.ITEM_PROPERTY, 2095));
        cryoblasterID = getInt(c.getOrCreateIntProperty("cryoblasterID", Configuration.ITEM_PROPERTY, 2096));
        batterycryoID = getInt(c.getOrCreateIntProperty("batterycryoID", Configuration.ITEM_PROPERTY, 2099));
        ThermoPelletID = getInt(c.getOrCreateIntProperty("ThermoPelletID", Configuration.ITEM_PROPERTY, 2098));
        
        c.save();
    }
    
    public static void AddRecipes()
    {
        ModLoader.AddRecipe(new ItemStack(lasergun, 1), new Object[] {
            "XYZ", " YQ", Character.valueOf('X'), ingotNetherflow, Character.valueOf('Y'), ingotObsidium, Character.valueOf('Z'), goopNetherflow, Character.valueOf('Q'), ingotPlutonium
        });
        ModLoader.AddRecipe(new ItemStack(plasmagun, 1), new Object[] {
            "XBZ", " YZ", Character.valueOf('X'), Item.diamond, Character.valueOf('B'), plasma, Character.valueOf('Z'), ingotPlutonium, Character.valueOf('Y'), ingotObsidium
        });
        ModLoader.AddRecipe(new ItemStack(plasmagunsplit, 1), new Object[] {
            "YB", Character.valueOf('B'), plasmagun, Character.valueOf('Y'), beamSplitter
        });
        ModLoader.AddRecipe(new ItemStack(lasergunsplit, 1), new Object[] {
            "YB", Character.valueOf('B'), lasergun, Character.valueOf('Y'), beamSplitter
        });
        ModLoader.AddRecipe(new ItemStack(acidgun, 1), new Object[] {
            "  Z", "ABC", " MN", Character.valueOf('Z'), acidVial, Character.valueOf('A'), ingotObsidium, Character.valueOf('B'), ingotUranium, Character.valueOf('C'), 
            reinforcedGlass, Character.valueOf('M'), BatteryPlasma, Character.valueOf('N'), Item.ingotIron
        });
        ModLoader.AddRecipe(new ItemStack(railgun, 1), new Object[] {
            "XYZ", " BC", "XY ", Character.valueOf('Z'), BatteryPlasma, Character.valueOf('X'), ingotObsidium, Character.valueOf('B'), goopPlutonium, Character.valueOf('C'), 
            Item.ingotGold, Character.valueOf('Y'), ingotPlutonium
        });
        //ModLoader.AddRecipe(new ItemStack(cryoblaster, 1), new Object[] {
        //    "  A", "CBX", " DE", Character.valueOf('A'), ingotUranium, Character.valueOf('B'), goopCryonite, Character.valueOf('C'), ingotCryonite, Character.valueOf('D'), 
        //    ingotObsidium, Character.valueOf('X'), BatteryCryo, Character.valueOf('E'), ingotPlutonium
        //});
        ModLoader.AddRecipe(new ItemStack(lasershotgun, 1), new Object[] {
            "  Z", "XBQ", " UP", Character.valueOf('Z'), Item.redstoneRepeater, Character.valueOf('X'), beamSplitter, Character.valueOf('B'), ingotNetherflow, Character.valueOf('Q'), 
            BatteryCharged, Character.valueOf('P'), ingotPlutonium, Character.valueOf('U'), ingotRadionite
        });
        ModLoader.AddRecipe(new ItemStack(acidGrenade, 4), new Object[] {
            "X", "Y", "Z", Character.valueOf('X'), Item.ingotIron, Character.valueOf('Y'), fullAcidVial, Character.valueOf('Z'), plasma
        });
        ModLoader.AddRecipe(new ItemStack(acidGrenade, 4), new Object[] {
            "D", "C", "D", Character.valueOf('D'), Block.planks, Character.valueOf('C'), Block.dirt
        });
        ModLoader.AddRecipe(new ItemStack(reinforcedGlass, 1), new Object[] {
            "X", "#", Character.valueOf('#'), Block.glass, Character.valueOf('X'), Item.ingotIron
        });
        ModLoader.AddRecipe(new ItemStack(acidVial, 1), new Object[] {
            "X#X", "Y Y", "X#X", Character.valueOf('#'), Item.ingotIron, Character.valueOf('Y'), reinforcedGlass, Character.valueOf('X'), Block.glass
        });
        ModLoader.AddRecipe(new ItemStack(plasmificatorIdle, 1), new Object[] {
            "X#X", "# #", "X#X", Character.valueOf('#'), Item.ingotIron, Character.valueOf('X'), fullAcidVial
        });
        ModLoader.AddRecipe(new ItemStack(causticBoat, 1), new Object[] {
            "R R", "RRR", Character.valueOf('R'), ingotRadionite
        });
        ModLoader.AddRecipe(new ItemStack(energyCell, 5), new Object[] {
            " R ", "RXR", " R ", Character.valueOf('R'), ingotNeptunium, Character.valueOf('X'), plasmaGel
        });
        ModLoader.AddRecipe(new ItemStack(beamSplitter, 1), new Object[] {
            " N ", "BXQ", " N ", Character.valueOf('N'), ingotNetherflow, Character.valueOf('X'), BatteryPlasma, Character.valueOf('Q'), ingotUranium, Character.valueOf('B'), 
            Item.diamond
        });
        ModLoader.AddRecipe(new ItemStack(BatteryEmpty, 8), new Object[] {
            "IRI", "I I", "IRI", Character.valueOf('R'), ingotRadionite, Character.valueOf('I'), Item.ingotIron
        });
        ModLoader.AddRecipe(new ItemStack(ThermoPellet, 1), new Object[] {
            "III", "IXI", "III", Character.valueOf('X'), ingotPlutonium, Character.valueOf('I'), goopUranium
        });
        ModLoader.AddRecipe(new ItemStack(BatteryCryo, 1), new Object[] {
            "R", "X", Character.valueOf('R'), goopCryonite, Character.valueOf('X'), BatteryEmpty
        });
        ModLoader.AddRecipe(new ItemStack(BatteryPlasma, 1), new Object[] {
            "R", "X", Character.valueOf('R'), plasma, Character.valueOf('X'), BatteryEmpty
        });
        ModLoader.AddRecipe(new ItemStack(BatteryCharged, 1), new Object[] {
            "R", "X", Character.valueOf('R'), goopPlutonium, Character.valueOf('X'), BatteryEmpty
        });
        ModLoader.AddRecipe(new ItemStack(fullAcidVial, 1), new Object[] {
            "JKL", Character.valueOf('J'), Block.dirt, Character.valueOf('K'), Block.sand, Character.valueOf('L'), Block.planks
        });
        ModLoader.AddRecipe(new ItemStack(plasmaLeather, 1), new Object[] {
            "N", "J", Character.valueOf('N'), plasmaGel, Character.valueOf('J'), Item.leather
        });
        ModLoader.AddRecipe(new ItemStack(acidHot, 1), new Object[] {
            "Z", "X", Character.valueOf('Z'), reinforcedGlass, Character.valueOf('X'), fullAcidVial
        });
        ModLoader.AddRecipe(new ItemStack(plutoniumVial, 1), new Object[] {
            "Q", "E", Character.valueOf('Q'), goopPlutonium, Character.valueOf('E'), acidVial
        });
        ModLoader.AddRecipe(new ItemStack(radioniteVial, 1), new Object[] {
            "Q", "F", Character.valueOf('Q'), goopRadionite, Character.valueOf('F'), acidVial
        });
        ModLoader.AddRecipe(new ItemStack(uraniumVial, 1), new Object[] {
            "Q", "G", Character.valueOf('Q'), goopUranium, Character.valueOf('G'), acidVial
        });
        ModLoader.AddRecipe(new ItemStack(neptuniumVial, 1), new Object[] {
            "Q", "H", Character.valueOf('Q'), goopNeptunium, Character.valueOf('H'), acidVial
        });
        ModLoader.AddRecipe(new ItemStack(netherflowVial, 1), new Object[] {
            "Q", "I", Character.valueOf('Q'), goopNetherflow, Character.valueOf('I'), acidVial
        });
        ModLoader.AddRecipe(new ItemStack(obsidiumVial, 1), new Object[] {
            "Q", "J", Character.valueOf('Q'), goopObsidium, Character.valueOf('J'), acidVial
        });
        ModLoader.AddRecipe(new ItemStack(cryoniteVial, 1), new Object[] {
            "Q", "J", Character.valueOf('Q'), goopCryonite, Character.valueOf('J'), acidVial
        });
        ModLoader.AddRecipe(new ItemStack(helmetHazmat, 1), new Object[] {
            "LLL", "L L", Character.valueOf('L'), plasmaLeather
        });
        ModLoader.AddRecipe(new ItemStack(plateHazmat, 1), new Object[] {
            "L L", "LLL", "LLL", Character.valueOf('L'), plasmaLeather
        });
        ModLoader.AddRecipe(new ItemStack(legsHazmat, 1), new Object[] {
            "LLL", "L L", "L L", Character.valueOf('L'), plasmaLeather
        });
        ModLoader.AddRecipe(new ItemStack(bootsHazmat, 1), new Object[] {
            "L L", "L L", Character.valueOf('L'), plasmaLeather
        });
        ModLoader.AddRecipe(new ItemStack(acidTnt, 4), new Object[] {
            "APA", "GAG", "APA", Character.valueOf('A'), fullAcidVial, Character.valueOf('G'), Item.gunpowder, Character.valueOf('P'), plasma
        });
        ModLoader.AddRecipe(new ItemStack(Item.gunpowder, 4), new Object[] {
            "AVG", Character.valueOf('A'), fullAcidVial, Character.valueOf('V'), acidVial, Character.valueOf('G'), plasmaGel
        });
        ModLoader.AddRecipe(new ItemStack(glowCloth1, 1), new Object[] {
            "C", "D", Character.valueOf('C'), Block.cloth, Character.valueOf('D'), fullAcidVial
        });
        ModLoader.AddRecipe(new ItemStack(glowCloth2, 1), new Object[] {
            "C", "D", Character.valueOf('C'), Block.cloth, Character.valueOf('D'), goopRadionite
        });
    }

    public static void GenerateNether(World world, Random random, int i, int j)
    {
        for(int k = 0; k < neptuniumOreVeinCount; k++)
        {
            int l1 = i + random.nextInt(16);
            int i3 = random.nextInt(neptuniumOreYRange) + neptuniumOreYStart;
            int j4 = j + random.nextInt(16);
            (new WorldGenNetherMinable(oreNeptunium.blockID, neptuniumOreVeinSize)).generate(world, random, l1, i3, j4);
        }

        for(int l = 0; l < obsidiumOreVeinCount; l++)
        {
            int i2 = i + random.nextInt(16);
            int j3 = random.nextInt(obsidiumOreYRange) + obsidiumOreYStart;
            int k4 = j + random.nextInt(16);
            (new WorldGenNetherMinable(oreObsidium.blockID, obsidiumOreVeinSize)).generate(world, random, i2, j3, k4);
        }

        if(random.nextInt(netherflowLakeChance) == 0)
        {
            int i1 = random.nextInt(16) + 8;
            int j2 = random.nextInt(random.nextInt(netherflowLakeYRange) + netherflowLakeYStart);
            int k3 = random.nextInt(16) + 8;
            if(j2 < netherflowLakeYCutoff)
            {
                (new WorldGenCausticLakes(netherflowMoving.blockID)).generate(world, random, i1, j2, k3);
            }
        }
        for(int j1 = 0; j1 < netherflowSpoutCount; j1++)
        {
            int k2 = i + random.nextInt(16) + 8;
			int l3 = random.nextInt(random.nextInt(netherflowSpoutYRange) + netherflowSpoutYStart);
            int l4 = j + random.nextInt(16) + 8;
            (new WorldGenNetherCaustics(netherflowMoving.blockID)).generate(world, random, k2, l3, l4);
        }

        for(int k1 = 0; k1 < neptuniumSpoutCount; k1++)
        {
            int l2 = i + random.nextInt(16) + 8;
            int i4 = random.nextInt(random.nextInt(neptuniumSpoutYRange) + neptuniumSpoutYStart);
            int i5 = j + random.nextInt(16) + 8;
            (new WorldGenNetherCaustics(neptuniumMoving.blockID)).generate(world, random, l2, i4, i5);
        }

    }

    public static void GenerateSurface(World world, Random random, int i, int j)
    {
        for(int k = 0; k < plutoniumOreVeinCount; k++)
        {
            int i2 = i + random.nextInt(16);
            int k3 = random.nextInt(plutoniumOreYRange) + plutoniumOreYStart;
            int i5 = j + random.nextInt(16);
            (new WorldGenMinable(orePlutoniumBlockID, plutoniumOreVeinSize)).generate(world, random, i2, k3, i5);
        }

        for(int l = 0; l < uraniumOreVeinCount; l++)
        {
            int j2 = i + random.nextInt(16);
            int l3 = random.nextInt(uraniumOreYRange) + uraniumOreYStart;
            int j5 = j + random.nextInt(16);
            (new WorldGenMinable(oreUraniumBlockID, uraniumOreVeinSize)).generate(world, random, j2, l3, j5);
        }

        for(int i1 = 0; i1 < radioniteOreVeinCount; i1++)
        {
            int k2 = i + random.nextInt(16);
            int i4 = random.nextInt(radioniteOreYRange) + radioniteOreYStart;
            int k5 = j + random.nextInt(16);
            (new WorldGenMinable(oreRadioniteBlockID, radioniteOreVeinSize)).generate(world, random, k2, i4, k5);
        }

        if(random.nextInt(acidLakeChance) == 0)
        {
            int j1 = random.nextInt(16) + 8;
            int l2 = random.nextInt(random.nextInt(10) + 60);
            int j4 = random.nextInt(16) + 8;
            if(l2 < acidLakeYCutoff)
            {
                (new WorldGenCausticLakes(acidMoving.blockID)).generate(world, random, j1, l2, j4);
            }
        }
        for(int k1 = 0; k1 < acidSpoutCount; k1++)
        {
            int i3 = i + random.nextInt(16) + 8;
            int k4 = random.nextInt(random.nextInt(acidSpoutYRange) + acidSpoutYStart);
            int l5 = j + random.nextInt(16) + 8;
            (new WorldGenCaustics(acidMoving.blockID)).generate(world, random, i3, k4, l5);
        }

        if(random.nextInt(acidLakeChance) == 0)
        {
            int l1 = i + random.nextInt(16) + 8;
            int j3 = random.nextInt(random.nextInt(10) + 60);
            int l4 = j + random.nextInt(16) + 8;
            BiomeGenBase biomegenbase = world.getWorldChunkManager().getBiomeGenAt(l1, l4);
            if((biomegenbase == BiomeGenBase.taiga) | (biomegenbase == BiomeGenBase.taiga) | (biomegenbase == PlasmaCraftCore.proxy.getHillBiome()))
            {
                (new WorldGenFrozenCryonite(frozenCryonite.blockID)).generate(world, random, l1, j3, l4);
            }
        }
    }
}
