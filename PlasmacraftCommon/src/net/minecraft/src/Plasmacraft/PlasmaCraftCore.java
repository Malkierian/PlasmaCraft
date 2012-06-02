package net.minecraft.src.Plasmacraft;

import java.io.File;
import java.util.Random;

import net.minecraft.src.BaseMod;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import net.minecraft.src.mod_PlasmaCraft;
import net.minecraft.src.Plasmacraft.WorldGenCaustics;
import net.minecraft.src.WorldGenLakes;
import net.minecraft.src.WorldGenMinable;
import net.minecraft.src.forge.Configuration;
import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.Property;

public class PlasmaCraftCore 
{
	public static String itemTexture = "/PlasmaCraftSprites/items0.png";
	public static String terrainTexture = "/PlasmaCraftSprites/terrain0.png";
	public static String liquidTexture = "/PlasmaCraftSprites/terrain1.png";
	
	private static mod_PlasmaCraft mod;
	
	public static String Version()
	{
		return "1.2.5/0.2.9";
	}
	
	public static IPCProxy proxy;
	
	public static Block acidBarrier;
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
	public static Block plasmaBench;
	public static Block plutoniumMoving;
	public static Block plutoniumStill;
	public static Block radioniteMoving;
	public static Block radioniteStill;
	public static Block reinforcedGlass;
	public static Block uraniumMoving;
	public static Block uraniumStill;

	public static Block orePlasma;
	public static Block glowCloth;
	
	public static Item acidGrenade;
	public static Item acidVial;
	public static Item acidgun;
	public static Item batteryCharged;
	public static Item batteryCryo;
	public static Item batteryEmpty;
	public static Item batteryOverCharged;
	public static Item batteryPlasma;
	public static Item beamSplitter;
	public static Item causticBoat;
	public static Item causticVial;
	public static Item cryoblaster;
	public static Item cryoniteVial;
	public static Item energyCell;
	public static Item goopCryonite;
	public static Item goopNeptunium;
	public static Item goopNetherflow;
	public static Item goopObsidium;
	public static Item goopPlutonium;
	public static Item goopRadionite;
	public static Item goopUranium;
	public static Item hazmatBoots;
	public static Item hazmatHood;
	public static Item hazmatJacket;
	public static Item hazmatPants;
	public static Item ingotCryonite;
	public static Item ingotNeptunium;
	public static Item ingotNetherflow;
	public static Item ingotObsidium;
	public static Item ingotPlutonium;
	public static Item ingotRadionite;
	public static Item ingotUranium;
	public static Item lasergun;
	public static Item lasergunsplit;
	public static Item lasershotgun;
	public static Item neptuniumVial;
	public static Item netherflowVial;
	public static Item obsidiumVial;
	public static Item plasma;
	public static Item plasmaGel;
	public static Item plasmaLeather;
	public static Item plasmagun;
	public static Item plasmagunsplit;
	public static Item plutoniumVial;
	public static Item radioniteVial;
	public static Item railgun;
	public static Item thermoPellet;
	public static Item uraniumVial;
	public static Material caustic;
	public static Material gas;
	
	public static int acidBarrierIndex = 16;
	public static int acidTntBottomIndex = 2;
	public static int acidTntSideIndex = 1;
	public static int acidTntTopIndex = 3;
	public static int blockUraniumIndex = 4;
	public static int frozenCryoniteIndex = 5;
	public static int oreNeptuniumIndex = 8;
	public static int oreObsidiumIndex = 9;
	public static int orePlutoniumIndex = 13;
	public static int oreRadioniteIndex = 14;
	public static int plasmaBenchFrontActiveIndex = 10;
	public static int plasmaBenchFrontIdleIndex = 11;
	public static int plasmaBenchSidesIndex = 12;
	public static int reinforcedGlassIndex = 15;

	public static int glowClothAcidIndex = 6;
	public static int glowClothCryoniteIndex = 21;
	public static int glowClothNeptuniumIndex = 18;
	public static int glowClothNetherflowIndex = 17;
	public static int glowClothObsidiumIndex = 22;
	public static int glowClothPlutoniumIndex = 20;
	public static int glowClothRadioniteIndex = 7;
	public static int glowClothUraniumIndex = 19;

	public static int acidStillIndex = 1;
	public static int acidMovingIndex = acidStillIndex + 1;
	public static int cryoniteStillIndex = 32;
	public static int cryoniteMovingIndex = cryoniteStillIndex + 1;
	public static int neptuniumStillIndex = 64;
	public static int neptuniumMovingIndex = neptuniumStillIndex + 1;
	public static int netherflowStillIndex = 96;
	public static int netherflowMovingIndex = netherflowStillIndex + 1;
	public static int obsidiumStillIndex = 128;
	public static int obsidiumMovingIndex = obsidiumStillIndex + 1;
	public static int plutoniumStillIndex = 160;
	public static int plutoniumMovingIndex = plutoniumStillIndex + 1;
	public static int radioniteStillIndex = 192;
	public static int radioniteMovingIndex = radioniteStillIndex + 1;
	public static int uraniumStillIndex = 228;
	public static int uraniumMovingIndex = uraniumStillIndex + 1;
	
	public static int acidGrenadeIndex = 1;
	public static int acidVialIndex = 12;
	public static int acidgunIndex = 2;
	public static int batteryChargedIndex = 6;
	public static int batteryCryoIndex = 7;
	public static int batteryEmptyIndex = 13;
	public static int batteryOverChargedIndex = 32;
	public static int batteryPlasmaIndex = 36;
	public static int beamSplitterIndex = 4;
	public static int causticBoatEntityID;
	public static int causticBoatIndex = 5;
	public static int causticBoatNetEntityID;
	public static int cryoblasterIndex = 8;
	public static int cryoniteVialIndex = 11;
	public static int energyCellIndex = 14;
	public static int fullAcidVialIndex = 0;
	public static int goopCryoniteIndex = 9;
	public static int goopNeptuniumIndex = 23;
	public static int goopNetherflowIndex = 26;
	public static int goopObsidiumIndex = 29;
	public static int goopPlutoniumIndex = 39;
	public static int goopRadioniteIndex = 42;
	public static int goopUraniumIndex = 47;
	public static int hazmatBootsIndex = 15;
	public static int hazmatHoodIndex = 16;
	public static int hazmatJacketIndex = 17;
	public static int hazmatPantsIndex = 18;
	public static int ingotCryoniteIndex = 10;
	public static int ingotNeptuniumIndex = 24;
	public static int ingotNetherflowIndex = 27;
	public static int ingotObsidiumIndex = 30;
	public static int ingotPlutoniumIndex = 40;
	public static int ingotRadioniteIndex = 43;
	public static int ingotUraniumIndex = 48;
	public static int lasergunIndex = 19;
	public static int lasergunsplitIndex = 20;
	public static int lasershotgunIndex = 21;
	public static int neptuniumVialIndex = 25;
	public static int netherflowVialIndex = 28;
	public static int obsidiumVialIndex = 31;
	public static int plasmaGelIndex = 34;
	public static int plasmaIndex = 33;
	public static int plasmaLeatherIndex = 35;
	public static int plasmagunIndex = 37;
	public static int plasmagunsplitIndex = 38;
	public static int plutoniumVialIndex = 41;
	public static int radioniteVialIndex = 44;
	public static int railgunIndex = 45;
	public static int thermoPelletIndex = 46;
	public static int uraniumVialIndex = 49;
	
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
	
	public static final int plutoniumMeta = 0;
	public static final int radioniteMeta = 1;
	public static final int neptuniumMeta = 2;
    public static final int obsidiumMeta = 3; 
    public static final int uraniumMeta = 4;
    
    public static final int glowClothAcidMeta = 0;
    public static final int glowClothRadioniteMeta = 1;
    public static final int glowClothNetherflowMeta = 2;
    public static final int glowClothNeptuniumMeta = 3;
    public static final int glowClothUraniumMeta = 4;
    public static final int glowClothPlutoniumMeta = 5;
    public static final int glowClothCryoniteMeta = 6;
    public static final int glowClothObsidiumMeta = 7;

	public static int hazmatBootsID;
	public static int hazmatHoodID;
	public static int hazmatJacketID;
	public static int hazmatPantsID;
	public static int plasmaBenchFrontAnim;
	
	public static boolean liquidSourceExplodesAfterCausticExplosion;
	public static int acidBarrierBlockID;
	public static int acidGrenadeID;
	public static int acidTNTBlockID;
	public static int acidVialID;
	public static int acidgunID;
	public static int batteryChargedID;
	public static int batteryCryoID;
	public static int batteryEmptyID;
	public static int batteryOverChargedID;
	public static int batteryPlasmaID;
	public static int beamSplitterID;
	public static int causticBoatID;
	public static int cryoBlasterID;
	public static int cryoniteFrozenBlockID;
	public static int cryoniteVialID;
	public static int emptyVialID;
	public static int energyCellID;
	public static int goopCryoniteID;
	public static int goopNeptuniumID;
	public static int goopNetherflowID;
	public static int goopObsidiumID;
	public static int goopPlutoniumID;
	public static int goopRadioniteID;
	public static int goopUraniumID;
	public static int ingotCryoniteID;
	public static int ingotNeptuniumID;
	public static int ingotNetherflowID;
	public static int ingotObsidiumID;
	public static int ingotPlutoniumID;
	public static int ingotRadioniteID;
	public static int ingotUraniumID;
	public static int laserGunID;
	public static int laserGunsplitID;
	public static int laserShotgunID;
	public static int neptuniumVialID;
	public static int netherflowVialID;
	public static int obsidiumVialID;
	public static int plasmaBenchActiveBlockID;
	public static int plasmaBenchBlockID;
	public static int plasmaGelID;
	public static int plasmaID;
	public static int plasmaLeatherID;
	public static int plasmagunID;
	public static int plasmagunsplitID;
	public static int plutoniumViaID;
	public static int radioniteVialID;
	public static int railgunID;
	public static int reinforcedGlassBlockID;
	public static int thermoPelletID;
	public static int uraniumViaID;

	public static int oreBlockID;
	public static int glowClothBlockID;

	private static int acidLakeYCutoff = 48;
	private static int acidSpoutCount = 20;
	private static int acidSpoutYRange = 30;
	private static int acidSpoutYStart = 8;
	private static int neptuniumOreVeinCount = 6;
	private static int neptuniumOreVeinSize = 10;
	private static int neptuniumOreYRange = 64;
	private static int neptuniumOreYStart = 32;
	private static int neptuniumSpoutCount = 20;
	private static int neptuniumSpoutYRange = 64;
	private static int neptuniumSpoutYStart = 8;
	private static int netherflowLakeChance = 32;
	private static int netherflowLakeYCutoff = 96;
	private static int netherflowLakeYRange = 16;
	private static int netherflowLakeYStart = 56;
	private static int netherflowSpoutCount = 20;
	private static int netherflowSpoutYRange = 96;
	private static int netherflowSpoutYStart = 16;
	private static int obsidiumOreVeinCount = 4;
	private static int obsidiumOreVeinSize = 10;
	private static int obsidiumOreYRange = 64;
	private static int obsidiumOreYStart = 32;
	private static int plutoniumOreVeinCount = 4;
	private static int plutoniumOreVeinSize = 6;
	private static int plutoniumOreYRange = 16;
	private static int plutoniumOreYStart = 4;
	private static int radioniteOreVeinCount = 4;
	private static int radioniteOreVeinSize = 6;
	private static int radioniteOreYRange = 24;
	private static int radioniteOreYStart = 4;
	private static int uraniumOreVeinCount = 4;
	private static int uraniumOreVeinSize = 6;
	private static int uraniumOreYRange = 16;
	private static int uraniumOreYStart = 4;
	
	public static int liquidRes;	
	private static boolean generateUranium;
	private static boolean generatePlutonium;
	
	public static PCBucketHandler handler;
	public static PCOreHandler oHandler;
	
	public static void init(int causticID, mod_PlasmaCraft mod2)
	{
		mod = mod2;
		loadConfig();
		
		handler = new PCBucketHandler();
		MinecraftForge.registerCustomBucketHandler(handler);

        MinecraftForge.setGuiHandler(mod, new GuiHandler());
        
        ModLoader.registerTileEntity(TilePlasmaBench.class,   "plasmaBench");
		ModLoader.registerEntityID(EntityAcid.class, 		  "acid", 			ModLoader.getUniqueEntityId());
		ModLoader.registerEntityID(EntityAcidGrenade.class,   "acidGrenade", 	ModLoader.getUniqueEntityId());
		ModLoader.registerEntityID(EntityAcidTNTPrimed.class, "acidTNTPrimed", 	ModLoader.getUniqueEntityId());
        ModLoader.registerEntityID(EntityCausticBoat.class,	  "radioniteBoat", 	ModLoader.getUniqueEntityId());
        ModLoader.registerEntityID(EntityCryoBlast.class, 	  "cryoBlast", 		ModLoader.getUniqueEntityId());
        ModLoader.registerEntityID(EntityLaser.class, 		  "laser", 			ModLoader.getUniqueEntityId());
        ModLoader.registerEntityID(EntityLaserShotgun.class,  "laserShotgun", 	ModLoader.getUniqueEntityId());
        ModLoader.registerEntityID(EntityMutantCow.class, 	  "mutantCow", 		ModLoader.getUniqueEntityId());
        ModLoader.registerEntityID(EntityPlasma.class, 		  "plasma", 		ModLoader.getUniqueEntityId());
        ModLoader.registerEntityID(EntityRailGun.class, 	  "railGun", 		ModLoader.getUniqueEntityId());
        
        MinecraftForge.registerEntity(EntityLaser.class, 		 mod, 160, 32, 100, true);
        MinecraftForge.registerEntity(EntityLaserShotgun.class,  mod, 161, 32, 100, true);
        MinecraftForge.registerEntity(EntityPlasma.class, 		 mod, 162, 32, 100, true);
        MinecraftForge.registerEntity(EntityRailGun.class, 		 mod, 163, 32, 100, true);
        MinecraftForge.registerEntity(EntityAcid.class, 		 mod, 164, 32, 100, true);
        MinecraftForge.registerEntity(EntityAcidTNTPrimed.class, mod, 165, 32, 100, true);
        MinecraftForge.registerEntity(EntityAcidGrenade.class, 	 mod, 166, 32, 100, true);
        MinecraftForge.registerEntity(EntityCryoBlast.class, 	 mod, 167, 32, 100, true);
        MinecraftForge.registerEntity(EntityCausticBoat.class, 	 mod, 168, 32, 100, true);
        MinecraftForge.registerEntity(EntityAcidTNTPrimed.class, mod, 169, 32, 100, true);
        MinecraftForge.registerEntity(EntityMutantCow.class, 	 mod, 170, 32, 100, true);
		
        orePlasma = (new BlockPlasmaOre(oreBlockID, orePlutoniumIndex)).setBlockName("orePlasma");
		
        glowCloth = (new BlockGlowCloth(glowClothBlockID, glowClothAcidIndex)).setBlockName("glowCloth");
        
        acidMoving =		(new BlockCausticFlowing	(acidFlowingBlockID, 	   acidStillIndex, 		 acidMovingIndex, 		causticID, acidStillBlockID, 	   acidFlowingBlockID			 )).setBlockName("acidMoving");
        acidStill = 		(new BlockCausticStationary	(acidStillBlockID, 		   acidStillIndex, 		 acidMovingIndex, 		causticID, acidStillBlockID, 	   acidFlowingBlockID, 		 1.0F)).setBlockName("acidStill");
        cryoniteMoving = 	(new BlockCausticFlowing	(cryoniteFlowingBlockID,   cryoniteStillIndex, 	 cryoniteMovingIndex, 	causticID, cryoniteStillBlockID,   cryoniteFlowingBlockID		 )).setBlockName("cryoniteMoving");
        cryoniteStill = 	(new BlockCausticStationary	(cryoniteStillBlockID, 	   cryoniteStillIndex, 	 cryoniteMovingIndex, 	causticID, cryoniteStillBlockID,   cryoniteFlowingBlockID,   0.0F)).setBlockName("cryoniteStill");
        neptuniumMoving = 	(new BlockCausticFlowing	(neptuniumFlowingBlockID,  neptuniumStillIndex,  neptuniumMovingIndex, 	causticID, neptuniumStillBlockID,  neptuniumFlowingBlockID	 	 )).setBlockName("neptuniumMoving");
        neptuniumStill = 	(new BlockCausticStationary	(neptuniumStillBlockID,    neptuniumStillIndex,  neptuniumMovingIndex, 	causticID, neptuniumStillBlockID,  neptuniumFlowingBlockID,  1.0F)).setBlockName("neptuniumStill");
        netherflowMoving = 	(new BlockCausticFlowing	(netherflowFlowingBlockID, netherflowStillIndex, netherflowMovingIndex, causticID, netherflowStillBlockID, netherflowFlowingBlockID		 )).setBlockName("netherflowMoving");
        netherflowStill = 	(new BlockCausticStationary	(netherflowStillBlockID,   netherflowStillIndex, netherflowMovingIndex, causticID, netherflowStillBlockID, netherflowFlowingBlockID, 1.0F)).setBlockName("netherflowStill");
        obsidiumMoving = 	(new BlockCausticFlowing	(obsidiumFlowingBlockID,   obsidiumStillIndex, 	 obsidiumMovingIndex, 	causticID, obsidiumStillBlockID,   obsidiumFlowingBlockID		 )).setBlockName("obsidiumMoving");
        obsidiumStill = 	(new BlockCausticStationary	(obsidiumStillBlockID, 	   obsidiumStillIndex, 	 obsidiumMovingIndex, 	causticID, obsidiumStillBlockID,   obsidiumFlowingBlockID,   1.0F)).setBlockName("obsidiumStill");
        plutoniumMoving = 	(new BlockCausticFlowing	(plutoniumFlowingBlockID,  plutoniumStillIndex,  plutoniumMovingIndex, 	causticID, plutoniumStillBlockID,  plutoniumFlowingBlockID		 )).setBlockName("plutoniumMoving");
        plutoniumStill = 	(new BlockCausticStationary	(plutoniumStillBlockID,    plutoniumStillIndex,  plutoniumMovingIndex, 	causticID, plutoniumStillBlockID,  plutoniumFlowingBlockID,  1.0F)).setBlockName("plutoniumStill");
        radioniteMoving = 	(new BlockCausticFlowing	(radioniteFlowingBlockID,  radioniteStillIndex,  radioniteMovingIndex, 	causticID, radioniteStillBlockID,  radioniteFlowingBlockID		 )).setBlockName("radioniteMoving");
        radioniteStill = 	(new BlockCausticStationary	(radioniteStillBlockID,    radioniteStillIndex,  radioniteMovingIndex, 	causticID, radioniteStillBlockID,  radioniteFlowingBlockID,  1.0F)).setBlockName("radioniteStill");
        uraniumMoving = 	(new BlockCausticFlowing	(uraniumFlowingBlockID,    uraniumStillIndex, 	 uraniumMovingIndex, 	causticID, uraniumStillBlockID,    uraniumFlowingBlockID		 )).setBlockName("uraniumMoving");
        uraniumStill = 		(new BlockCausticStationary	(uraniumStillBlockID, 	   uraniumStillIndex, 	 uraniumMovingIndex, 	causticID, uraniumStillBlockID,    uraniumFlowingBlockID,	 1.0F)).setBlockName("uraniumStill");
        
        reinforcedGlass = (new BlockReinforcedGlass(reinforcedGlassBlockID, reinforcedGlassIndex, Material.glass, false, 500.0F)).setBlockName("reinforcedGlass");
        frozenCryonite = (new BlockReinforcedGlass(cryoniteFrozenBlockID, frozenCryoniteIndex, Material.glass, false, 1.0F)).setBlockName("frozenCryonite");
        
        plasmaBench = (new BlockPlasmaBench(plasmaBenchBlockID)).setBlockName("plasmaBench");
        
        acidBarrier = (new BlockAcidHot(acidBarrierBlockID, acidBarrierIndex)).setBlockName("acidBarrier");
        
        acidTnt = (new BlockAcidTNT(acidTNTBlockID, acidTntSideIndex, acidTntTopIndex, acidTntBottomIndex)).setBlockName("acidTnt");
        
        ingotPlutonium = (new ItemPlasma(ingotPlutoniumID)).setIconIndex(ingotPlutoniumIndex).setItemName("ingotPlutonium");
        ingotRadionite = (new ItemPlasma(ingotRadioniteID)).setIconIndex(ingotRadioniteIndex).setItemName("ingotRadionite");
        energyCell = (new ItemPlasma(energyCellID)).setIconIndex(energyCellIndex).setItemName("energyCell");
        beamSplitter = (new ItemPlasma(beamSplitterID)).setIconIndex(beamSplitterIndex).setItemName("beamSplitter");
        batteryEmpty = (new ItemPlasma(batteryEmptyID)).setIconIndex(batteryEmptyIndex).setItemName("batteryEmpy");
        thermoPellet = (new ItemPlasma(thermoPelletID)).setIconIndex(thermoPelletIndex).setItemName("thermpellet");
        batteryCryo = (new ItemPlasma(batteryCryoID)).setIconIndex(batteryCryoIndex).setItemName("batteryCryo");
        batteryCharged = (new ItemPlasma(batteryChargedID)).setIconIndex(batteryChargedIndex).setItemName("batteryCharged");
        batteryOverCharged = (new ItemPlasma(batteryOverChargedID)).setIconIndex(batteryOverChargedIndex).setItemName("batteryOvercharged");
        batteryPlasma = (new ItemPlasma(batteryPlasmaID)).setIconIndex(batteryPlasmaIndex).setItemName("batteryPlasma");
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
        causticVial = (new ItemVial(emptyVialID, 0, EnumPlasmaLiquid.EMPTY)).setIconIndex(acidVialIndex).setItemName("acidVial");
        acidVial = (new ItemVial(acidVialID, acidMoving.blockID, EnumPlasmaLiquid.ACID)).setIconIndex(fullAcidVialIndex).setItemName("fullAcidVial");
        plutoniumVial = (new ItemVial(plutoniumViaID, plutoniumMoving.blockID, EnumPlasmaLiquid.PLUTONIUM)).setIconIndex(plutoniumVialIndex).setItemName("plutoniumVial");
        radioniteVial = (new ItemVial(radioniteVialID, radioniteMoving.blockID, EnumPlasmaLiquid.RADIONITE)).setIconIndex(radioniteVialIndex).setItemName("radioniteVial");
        uraniumVial = (new ItemVial(uraniumViaID, uraniumMoving.blockID, EnumPlasmaLiquid.URANIUM)).setIconIndex(uraniumVialIndex).setItemName("uraniumVial");
        neptuniumVial = (new ItemVial(neptuniumVialID, neptuniumMoving.blockID, EnumPlasmaLiquid.NEPTUNIUM)).setIconIndex(neptuniumVialIndex).setItemName("neptuniumVial");
        netherflowVial = (new ItemVial(netherflowVialID, netherflowMoving.blockID, EnumPlasmaLiquid.NETHERFLOW)).setIconIndex(netherflowVialIndex).setItemName("netherflowVial");
        obsidiumVial = (new ItemVial(obsidiumVialID, obsidiumMoving.blockID, EnumPlasmaLiquid.OBSIDIUM)).setIconIndex(obsidiumVialIndex).setItemName("obsidiumVial");
        cryoniteVial = (new ItemVial(cryoniteVialID, cryoniteMoving.blockID, EnumPlasmaLiquid.CRYONITE)).setIconIndex(cryoniteVialIndex).setItemName("cryoniteVial");
        plasmaGel = (new ItemPlasma(plasmaGelID)).setIconIndex(plasmaGelIndex).setItemName("plasmaGel");
        plasmaLeather = (new ItemPlasma(plasmaLeatherID)).setIconIndex(plasmaLeatherIndex).setItemName("plasmaLeather");
        plasma = (new ItemPlasma(plasmaID)).setIconIndex(plasmaIndex).setItemName("plasma");
        causticBoat = (new ItemCausticBoat(causticBoatID)).setIconIndex(causticBoatIndex).setItemName("causticBoat");
        acidGrenade = (new ItemAcidGrenade(acidGrenadeID)).setIconIndex(acidGrenadeIndex).setItemName("acidGrenade");
        lasergun = (new ItemEnergyWeapon(laserGunID, 200)).setIconIndex(lasergunIndex).setItemName("laserGun");
        plasmagun = (new ItemEnergyWeapon(plasmagunID, 200)).setIconIndex(plasmagunIndex).setItemName("plasmaGun");
        plasmagunsplit = (new ItemEnergyWeapon(plasmagunsplitID, 300)).setIconIndex(plasmagunsplitIndex).setItemName("plasmaGunSplit");
        lasergunsplit = (new ItemEnergyWeapon(laserGunsplitID, 300)).setIconIndex(lasergunsplitIndex).setItemName("laserGunSplit");
        acidgun = (new ItemEnergyWeapon(acidgunID, 200)).setIconIndex(acidgunIndex).setItemName("acidGun");
        railgun = (new ItemEnergyWeapon(railgunID, 200)).setIconIndex(railgunIndex).setItemName("railGun");
        cryoblaster = (new ItemEnergyWeapon(cryoBlasterID, 100)).setIconIndex(cryoblasterIndex).setItemName("cryoBlaster");
        lasershotgun = (new ItemEnergyWeapon(laserShotgunID, 200)).setIconIndex(lasershotgunIndex).setItemName("lasershotgunGun");

        hazmatHood = (new ItemPlasmaArmor(hazmatHoodID, EnumArmorMaterial.values()[2], ModLoader.addArmor("hazmat"), 0)).setIconIndex(hazmatHoodIndex).setItemName("helmetHazmat");
        hazmatJacket = (new ItemPlasmaArmor(hazmatJacketID, EnumArmorMaterial.values()[2], ModLoader.addArmor("hazmat"), 1)).setIconIndex(hazmatJacketIndex).setItemName("plateHazmat");
        hazmatPants = (new ItemPlasmaArmor(hazmatPantsID, EnumArmorMaterial.values()[2], ModLoader.addArmor("hazmat"), 2)).setIconIndex(hazmatPantsIndex).setItemName("legsHazmat");
        hazmatBoots = (new ItemPlasmaArmor(hazmatBootsID, EnumArmorMaterial.values()[2], ModLoader.addArmor("hazmat"), 3)).setIconIndex(hazmatBootsIndex).setItemName("bootsHazmat");
        
        ModLoader.registerBlock(cryoniteStill);
        ModLoader.registerBlock(cryoniteMoving);
        ModLoader.registerBlock(acidStill);
        ModLoader.registerBlock(acidMoving);
        ModLoader.registerBlock(radioniteStill);
        ModLoader.registerBlock(radioniteMoving);
        ModLoader.registerBlock(plutoniumStill);
        ModLoader.registerBlock(plutoniumMoving);
        ModLoader.registerBlock(netherflowStill);
        ModLoader.registerBlock(netherflowMoving);
        ModLoader.registerBlock(neptuniumStill);
        ModLoader.registerBlock(neptuniumMoving);
        ModLoader.registerBlock(uraniumStill);
        ModLoader.registerBlock(uraniumMoving);
        ModLoader.registerBlock(obsidiumStill);
        ModLoader.registerBlock(obsidiumMoving);
        ModLoader.registerBlock(frozenCryonite);
        ModLoader.registerBlock(reinforcedGlass);
        ModLoader.registerBlock(plasmaBench);
        ModLoader.registerBlock(acidBarrier);
        ModLoader.registerBlock(acidTnt);
        
        ModLoader.registerBlock(glowCloth, net.minecraft.src.Plasmacraft.ItemGlowCloth.class);

        ModLoader.registerBlock(orePlasma, net.minecraft.src.Plasmacraft.ItemPlasmaOre.class);
        
        MinecraftForge.setBlockHarvestLevel(orePlasma, obsidiumMeta, "pickaxe", 3);
        MinecraftForge.setBlockHarvestLevel(orePlasma, uraniumMeta, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(orePlasma, radioniteMeta, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(orePlasma, plutoniumMeta, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(orePlasma, neptuniumMeta, "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(frozenCryonite, "pickaxe", 1);
		
		oHandler = new PCOreHandler();
		MinecraftForge.registerOreHandler(oHandler);
        
        MinecraftForge.registerOre("orePlutonium", new ItemStack(orePlasma, 1, plutoniumMeta));
        MinecraftForge.registerOre("oreUranium", new ItemStack(orePlasma, 1, uraniumMeta));
        
        MinecraftForge.registerOre("ingotPlutonium", new ItemStack(ingotPlutonium, 1));
        MinecraftForge.registerOre("ingotUranium", new ItemStack(ingotUranium, 1));
        
        Item.itemsList[oreBlockID] = new ItemPlasmaOre(oreBlockID - 256).setItemName("OreBlock");
        
        Item.itemsList[glowClothBlockID] = new ItemGlowCloth(glowClothBlockID - 256).setItemName("GlowCloth");
        
        addRecipes();
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
    	
        liquidSourceExplodesAfterCausticExplosion = getBool(c.getOrCreateBooleanProperty("LiquidSourceExplodesAfterCausticExplosion", Configuration.CATEGORY_GENERAL, true));

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
        acidStillBlockID = getInt(c.getOrCreateBlockIdProperty("ID.AcidStill", 127));
        acidFlowingBlockID = getInt(c.getOrCreateBlockIdProperty("ID.AcidFlowing", 126));
        reinforcedGlassBlockID = getInt(c.getOrCreateBlockIdProperty("ID.ReinforcedGlass", 125));
        plasmaBenchBlockID = getInt(c.getOrCreateBlockIdProperty("ID.Plasmificator", 152));
        acidBarrierBlockID = getInt(c.getOrCreateBlockIdProperty("ID.AcidBarrier", 151));
        acidTNTBlockID = getInt(c.getOrCreateBlockIdProperty("ID.AcidTNT", 150));
        
        glowClothBlockID = getInt(c.getOrCreateBlockIdProperty("ID.GlowCloth", 146));

        oreBlockID = getInt(c.getOrCreateBlockIdProperty("ID.Ore", 128));
        
        ingotPlutoniumID = getInt(c.getOrCreateIntProperty("ingotPlutoniumID", Configuration.CATEGORY_ITEM, 2048));
        ingotRadioniteID = getInt(c.getOrCreateIntProperty("ingotRadioniteID", Configuration.CATEGORY_ITEM, 2049));
        emptyVialID = getInt(c.getOrCreateIntProperty("emptyVialID", Configuration.CATEGORY_ITEM, 2050));
        acidVialID = getInt(c.getOrCreateIntProperty("acidVialID", Configuration.CATEGORY_ITEM, 2051));
        plutoniumViaID = getInt(c.getOrCreateIntProperty("plutoniumViaID", Configuration.CATEGORY_ITEM, 2052));
        radioniteVialID = getInt(c.getOrCreateIntProperty("radioniteVialID", Configuration.CATEGORY_ITEM, 2053));
        plasmaGelID = getInt(c.getOrCreateIntProperty("plasmaGelID", Configuration.CATEGORY_ITEM, 2054));
        plasmaID = getInt(c.getOrCreateIntProperty("plasmaID", Configuration.CATEGORY_ITEM, 2055));
        causticBoatID = getInt(c.getOrCreateIntProperty("causticBoatID", Configuration.CATEGORY_ITEM, 2056));
        causticBoatEntityID = getInt(c.getOrCreateIntProperty("causticBoatID", Configuration.CATEGORY_ITEM, 2056));
        causticBoatNetEntityID = getInt(c.getOrCreateIntProperty("causticBoatID", Configuration.CATEGORY_ITEM, 2056));
        hazmatHoodID = getInt(c.getOrCreateIntProperty("hazmatHoodID", Configuration.CATEGORY_ITEM, 2057));
        hazmatJacketID = getInt(c.getOrCreateIntProperty("hazmatJacketID", Configuration.CATEGORY_ITEM, 2058));
        hazmatPantsID = getInt(c.getOrCreateIntProperty("hazmatPantsID", Configuration.CATEGORY_ITEM, 2059));
        hazmatBootsID = getInt(c.getOrCreateIntProperty("hazmatBootsID", Configuration.CATEGORY_ITEM, 2060));
        uraniumViaID = getInt(c.getOrCreateIntProperty("uraniumViaID", Configuration.CATEGORY_ITEM, 2061));
        neptuniumVialID = getInt(c.getOrCreateIntProperty("neptuniumVialID", Configuration.CATEGORY_ITEM, 2062));
        netherflowVialID = getInt(c.getOrCreateIntProperty("netherflowVialID", Configuration.CATEGORY_ITEM, 2063));
        obsidiumVialID = getInt(c.getOrCreateIntProperty("obsidiumVialID", Configuration.CATEGORY_ITEM, 2064));
        acidGrenadeID = getInt(c.getOrCreateIntProperty("acidNadeID", Configuration.CATEGORY_ITEM, 2068));
        ingotNeptuniumID = getInt(c.getOrCreateIntProperty("ingotNeptuniumID", Configuration.CATEGORY_ITEM, 2069));
        ingotObsidiumID = getInt(c.getOrCreateIntProperty("ingotObsidiumID", Configuration.CATEGORY_ITEM, 2070));
        goopPlutoniumID = getInt(c.getOrCreateIntProperty("goopPlutoniumID", Configuration.CATEGORY_ITEM, 2071));
        goopRadioniteID = getInt(c.getOrCreateIntProperty("goopRadioniteID", Configuration.CATEGORY_ITEM, 2072));
        goopNeptuniumID = getInt(c.getOrCreateIntProperty("goopNeptuniumID", Configuration.CATEGORY_ITEM, 2073));
        goopObsidiumID = getInt(c.getOrCreateIntProperty("goopObsidiumID", Configuration.CATEGORY_ITEM, 2074));
        goopNetherflowID = getInt(c.getOrCreateIntProperty("goopNetherflowID", Configuration.CATEGORY_ITEM, 2075));
        plasmaLeatherID = getInt(c.getOrCreateIntProperty("plasmaLeatherID", Configuration.CATEGORY_ITEM, 2076));
        goopUraniumID = getInt(c.getOrCreateIntProperty("goopUraniumID", Configuration.CATEGORY_ITEM, 2077));
        ingotUraniumID = getInt(c.getOrCreateIntProperty("ingotUraniumID", Configuration.CATEGORY_ITEM, 2078));
        ingotNetherflowID = getInt(c.getOrCreateIntProperty("ingotNetherflowID", Configuration.CATEGORY_ITEM, 2079));
        laserGunID = getInt(c.getOrCreateIntProperty("lasergunID", Configuration.CATEGORY_ITEM, 2080));
        energyCellID = getInt(c.getOrCreateIntProperty("energyCellID", Configuration.CATEGORY_ITEM, 2081));
        plasmagunID = getInt(c.getOrCreateIntProperty("plasmagunID", Configuration.CATEGORY_ITEM, 2082));
        batteryEmptyID = getInt(c.getOrCreateIntProperty("BatteryEmptyID", Configuration.CATEGORY_ITEM, 2083));
        batteryPlasmaID = getInt(c.getOrCreateIntProperty("BatteryPlasmaID", Configuration.CATEGORY_ITEM, 2084));
        acidgunID = getInt(c.getOrCreateIntProperty("acidgunID", Configuration.CATEGORY_ITEM, 2085));
        beamSplitterID = getInt(c.getOrCreateIntProperty("beamSplitterID", Configuration.CATEGORY_ITEM, 2086));
        plasmagunsplitID = getInt(c.getOrCreateIntProperty("plasmagunsplitID", Configuration.CATEGORY_ITEM, 2087));
        laserGunsplitID = getInt(c.getOrCreateIntProperty("lasergunsplitID", Configuration.CATEGORY_ITEM, 2088));
        railgunID = getInt(c.getOrCreateIntProperty("railgunID", Configuration.CATEGORY_ITEM, 2089));
        batteryChargedID = getInt(c.getOrCreateIntProperty("BatteryChargedID", Configuration.CATEGORY_ITEM, 2090));
        batteryOverChargedID = getInt(c.getOrCreateIntProperty("BatteryOverchargedID", Configuration.CATEGORY_ITEM, 2091));
        laserShotgunID = getInt(c.getOrCreateIntProperty("lasershotgunID", Configuration.CATEGORY_ITEM, 2092));
        cryoniteVialID = getInt(c.getOrCreateIntProperty("cryoniteVialID", Configuration.CATEGORY_ITEM, 2093));
        goopCryoniteID = getInt(c.getOrCreateIntProperty("goopCryoniteID", Configuration.CATEGORY_ITEM, 2094));
        ingotCryoniteID = getInt(c.getOrCreateIntProperty("ingotCryoniteID", Configuration.CATEGORY_ITEM, 2095));
        cryoBlasterID = getInt(c.getOrCreateIntProperty("cryoblasterID", Configuration.CATEGORY_ITEM, 2096));
        batteryCryoID = getInt(c.getOrCreateIntProperty("batterycryoID", Configuration.CATEGORY_ITEM, 2099));
        thermoPelletID = getInt(c.getOrCreateIntProperty("ThermoPelletID", Configuration.CATEGORY_ITEM, 2098));
        
        liquidRes = getInt(c.getOrCreateIntProperty("liquidResolution", Configuration.CATEGORY_GENERAL, 16));
        generateUranium = getBool(c.getOrCreateBooleanProperty("GenerateUranium", Configuration.CATEGORY_GENERAL, true));
        generatePlutonium = getBool(c.getOrCreateBooleanProperty("GeneratePlutonium", Configuration.CATEGORY_GENERAL, true));
        
        c.save();
    }
    
    public static void addRecipes()
    {
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
        ModLoader.addRecipe(new ItemStack(acidGrenade, 4), new Object[] {
            "X", "Y", "Z", Character.valueOf('X'), Item.ingotIron, Character.valueOf('Y'), acidVial, Character.valueOf('Z'), plasma
        });
        ModLoader.addRecipe(new ItemStack(reinforcedGlass, 1), new Object[] {
            "X", "#", Character.valueOf('#'), Block.glass, Character.valueOf('X'), Item.ingotIron
        });
        ModLoader.addRecipe(new ItemStack(causticVial, 1), new Object[] {
            "X#X", "Y Y", "X#X", Character.valueOf('#'), Item.ingotIron, Character.valueOf('Y'), reinforcedGlass, Character.valueOf('X'), Block.glass
        });
        ModLoader.addRecipe(new ItemStack(plasmaBench, 1), new Object[] {
            "X#X", "# #", "X#X", Character.valueOf('#'), Item.ingotIron, Character.valueOf('X'), acidVial
        });
        ModLoader.addRecipe(new ItemStack(causticBoat, 1), new Object[] {
            "R R", "RRR", Character.valueOf('R'), ingotRadionite
        });
        ModLoader.addRecipe(new ItemStack(energyCell, 5), new Object[] {
            " R ", "RXR", " R ", Character.valueOf('R'), ingotNeptunium, Character.valueOf('X'), plasmaGel
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
        ModLoader.addRecipe(new ItemStack(plasmaLeather, 1), new Object[] {
            "N", "J", Character.valueOf('N'), plasmaGel, Character.valueOf('J'), Item.leather
        });
        ModLoader.addRecipe(new ItemStack(acidBarrier, 1), new Object[] {
            "Z", "X", Character.valueOf('Z'), reinforcedGlass, Character.valueOf('X'), acidVial
        });
        ModLoader.addRecipe(new ItemStack(plutoniumVial, 1), new Object[] {
            "Q", "E", Character.valueOf('Q'), goopPlutonium, Character.valueOf('E'), causticVial
        });
        ModLoader.addRecipe(new ItemStack(radioniteVial, 1), new Object[] {
            "Q", "F", Character.valueOf('Q'), goopRadionite, Character.valueOf('F'), causticVial
        });
        ModLoader.addRecipe(new ItemStack(uraniumVial, 1), new Object[] {
            "Q", "G", Character.valueOf('Q'), goopUranium, Character.valueOf('G'), causticVial
        });
        ModLoader.addRecipe(new ItemStack(neptuniumVial, 1), new Object[] {
            "Q", "H", Character.valueOf('Q'), goopNeptunium, Character.valueOf('H'), causticVial
        });
        ModLoader.addRecipe(new ItemStack(netherflowVial, 1), new Object[] {
            "Q", "I", Character.valueOf('Q'), goopNetherflow, Character.valueOf('I'), causticVial
        });
        ModLoader.addRecipe(new ItemStack(obsidiumVial, 1), new Object[] {
            "Q", "J", Character.valueOf('Q'), goopObsidium, Character.valueOf('J'), causticVial
        });
        ModLoader.addRecipe(new ItemStack(cryoniteVial, 1), new Object[] {
            "Q", "J", Character.valueOf('Q'), goopCryonite, Character.valueOf('J'), causticVial
        });
        ModLoader.addRecipe(new ItemStack(hazmatHood, 1), new Object[] {
            "LLL", "L L", Character.valueOf('L'), plasmaLeather
        });
        ModLoader.addRecipe(new ItemStack(hazmatJacket, 1), new Object[] {
            "L L", "LLL", "LLL", Character.valueOf('L'), plasmaLeather
        });
        ModLoader.addRecipe(new ItemStack(hazmatPants, 1), new Object[] {
            "LLL", "L L", "L L", Character.valueOf('L'), plasmaLeather
        });
        ModLoader.addRecipe(new ItemStack(hazmatBoots, 1), new Object[] {
            "L L", "L L", Character.valueOf('L'), plasmaLeather
        });
        ModLoader.addRecipe(new ItemStack(acidTnt, 4), new Object[] {
            "APA", "GAG", "APA", Character.valueOf('A'), acidVial, Character.valueOf('G'), Item.gunpowder, Character.valueOf('P'), plasma
        });
        ModLoader.addRecipe(new ItemStack(glowCloth, 1, glowClothAcidMeta), new Object[] {
            "C", "D", Character.valueOf('C'), Block.cloth, Character.valueOf('D'), acidVial
        });
        ModLoader.addRecipe(new ItemStack(glowCloth, 1, glowClothRadioniteMeta), new Object[] {
            "C", "D", Character.valueOf('C'), Block.cloth, Character.valueOf('D'), goopRadionite
        });
        ModLoader.addRecipe(new ItemStack(glowCloth, 1, glowClothNetherflowMeta), new Object[] {
            "C", "D", Character.valueOf('C'), Block.cloth, Character.valueOf('D'), goopNetherflow
        });
        ModLoader.addRecipe(new ItemStack(glowCloth, 1, glowClothNeptuniumMeta), new Object[] {
            "C", "D", Character.valueOf('C'), Block.cloth, Character.valueOf('D'), goopNeptunium
        });
        ModLoader.addRecipe(new ItemStack(glowCloth, 1, glowClothUraniumMeta), new Object[] {
            "C", "D", Character.valueOf('C'), Block.cloth, Character.valueOf('D'), goopUranium
        });
        ModLoader.addRecipe(new ItemStack(glowCloth, 1, glowClothPlutoniumMeta), new Object[] {
            "C", "D", Character.valueOf('C'), Block.cloth, Character.valueOf('D'), goopPlutonium
        });
        ModLoader.addRecipe(new ItemStack(glowCloth, 1, glowClothCryoniteMeta), new Object[] {
            "C", "D", Character.valueOf('C'), Block.cloth, Character.valueOf('D'), goopCryonite
        });
        ModLoader.addRecipe(new ItemStack(glowCloth, 1, glowClothObsidiumMeta), new Object[] {
            "C", "D", Character.valueOf('C'), Block.cloth, Character.valueOf('D'), goopObsidium
        });
    }

    public static void generateNether(World world, Random random, int i, int j)
    {
        for(int k = 0; k < neptuniumOreVeinCount; k++)
        {
            int l1 = i + random.nextInt(16);
            int i3 = random.nextInt(neptuniumOreYRange) + neptuniumOreYStart;
            int j4 = j + random.nextInt(16);
            (new WorldGenNetherMinable(oreBlockID, neptuniumOreVeinSize, neptuniumMeta)).generate(world, random, l1, i3, j4);
        }

        for(int l = 0; l < obsidiumOreVeinCount; l++)
        {
            int i2 = i + random.nextInt(16);
            int j3 = random.nextInt(obsidiumOreYRange) + obsidiumOreYStart;
            int k4 = j + random.nextInt(16);
            (new WorldGenNetherMinable(oreBlockID, obsidiumOreVeinSize, obsidiumMeta)).generate(world, random, i2, j3, k4);
        }

        if(random.nextInt(netherflowLakeChance) == 0)
        {
            int i1 = random.nextInt(16) + 8;
            int j2 = random.nextInt(random.nextInt(netherflowLakeYRange) + netherflowLakeYStart);
            int k3 = random.nextInt(16) + 8;
            if(j2 < netherflowLakeYCutoff)
            {
                //(new WorldGenCausticLakes(netherflowMoving.blockID)).generate(world, random, i1, j2, k3);
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

    public static void generateSurface(World world, Random random, int i, int j)
    {
    	if(generatePlutonium)
    	{
	        for(int k = 0; k < plutoniumOreVeinCount; k++)
	        {
	            int i2 = i + random.nextInt(16);
	            int k3 = random.nextInt(plutoniumOreYRange) + plutoniumOreYStart;
	            int i5 = j + random.nextInt(16);
	            (new WorldGenMulti(oreBlockID, plutoniumOreVeinSize, plutoniumMeta)).generate(world, random, i2, k3, i5);
	        }
    	}

    	if(generateUranium)
    	{
	        for(int l = 0; l < uraniumOreVeinCount; l++)
	        {
	            int j2 = i + random.nextInt(16);
	            int l3 = random.nextInt(uraniumOreYRange) + uraniumOreYStart;
	            int j5 = j + random.nextInt(16);
	            (new WorldGenMulti(oreBlockID, uraniumOreVeinSize, uraniumMeta)).generate(world, random, j2, l3, j5);
	        }
    	}
    	
        for(int i1 = 0; i1 < radioniteOreVeinCount; i1++)
        {
            int k2 = i + random.nextInt(16);
            int i4 = random.nextInt(radioniteOreYRange) + radioniteOreYStart;
            int k5 = j + random.nextInt(16);
            (new WorldGenMulti(oreBlockID, radioniteOreVeinSize, radioniteMeta)).generate(world, random, k2, i4, k5);
        }
        
        CausticLakes.populateSurfaceLiquids(world, random, i, j);
    }
}
