package net.minecraft.src;
import java.io.File;
import java.net.URISyntaxException;
import java.util.*;

import net.minecraft.server.MinecraftServer;

public class mod_PlasmaCraft extends BaseModMp
{
    public String Version()
    {
    	return "1.8.1/0.2.3";
	}
    
    // PC == ChunkProviderGenerate
    // JX == ChunkProviderHell
	
    public static void sendFlak(double x, double y, double z)
	  {
	    float[] dataFloat = new float[3];
	    dataFloat[0] = (float)x;
	    dataFloat[1] = (float)y;
	    dataFloat[2] = (float)z;

	    Packet230ModLoader packet = new Packet230ModLoader();
	    packet.packetType = 1;
	    packet.dataFloat = dataFloat;
	    ModLoaderMp.SendPacketToAll(inst1, packet);
	  }
	 
	public static int floatColorsToDamage(float r, float g, float b)
	{
		int r32 = (int)(r * 255.0f);
		int g32 = (int)(g * 255.0f);
		int b32 = (int)(b * 255.0f);
		if(r32 < 0) r32 = 0;
		if(g32 < 0) g32 = 0;
		if(b32 < 0) b32 = 0;
		if(r32 > 255) r32 = 255;
		if(g32 > 255) g32 = 255;
		if(b32 > 255) b32 = 255;
		return (r32 << 16) | (g32 << 8) | b32;
	}

	
	public mod_PlasmaCraft()
	{
		ModLoaderMp.RegisterEntityTrackerEntry(SMEntityLaser.class, 160);
		ModLoaderMp.RegisterEntityTrackerEntry(SMEntityLaserShotgun.class, 161);
		ModLoaderMp.RegisterEntityTrackerEntry(SMEntityPlasma.class, 162);
		ModLoaderMp.RegisterEntityTrackerEntry(SMEntityRailGun.class, 163);
		ModLoaderMp.RegisterEntityTrackerEntry(SMEntityAcid.class, 164);
		ModLoaderMp.RegisterEntityTrackerEntry(SMEntityAcidTNTPrimed.class, 165);
		ModLoaderMp.RegisterEntityTrackerEntry(SMEntityAcidGrenade.class, 166);
		ModLoaderMp.RegisterEntityTrackerEntry(SMEntityCryoBlast.class, 167);

		
		ModLoaderMp.RegisterEntityTracker(SMEntityLaser.class, 160, 3);
		ModLoaderMp.RegisterEntityTracker(SMEntityLaserShotgun.class, 160 ,3);
		ModLoaderMp.RegisterEntityTracker(SMEntityPlasma.class, 160 , 3);
		ModLoaderMp.RegisterEntityTracker(SMEntityRailGun.class, 160, 3);
		ModLoaderMp.RegisterEntityTracker(SMEntityAcid.class, 160 , 3);
		ModLoaderMp.RegisterEntityTracker(SMEntityAcidTNTPrimed.class, 160 , 3);
		ModLoaderMp.RegisterEntityTracker(SMEntityAcidGrenade.class, 160, 3);
		ModLoaderMp.RegisterEntityTracker(SMEntityCryoBlast.class, 160, 3);

		
		causticID = ModLoader.getUniqueBlockModelID(this, true);
        glowCloth1Index = ModLoader.addOverride("/terrain.png", "/plasmacraft/terrain/glowclothgreen.png");
        glowCloth2Index = ModLoader.addOverride("/terrain.png", "/plasmacraft/terrain/glowclothpink.png");
		reinforcedGlassIndex = ModLoader.addOverride("/terrain.png", "/plasmacraft/terrain/reinforced_glass.png");
		plasmificatorSidesIndex = ModLoader.addOverride("/terrain.png", "/plasmacraft/terrain/plasmificator_sides.png");
		plasmificatorFrontIdleIndex = ModLoader.addOverride("/terrain.png", "/plasmacraft/terrain/plasmificator_front_idle.png");
		plasmificatorFrontActiveIndex = ModLoader.addOverride("/terrain.png", "/plasmacraft/terrain/plasmificator_front_active.png");
		acidHotIndex = ModLoader.addOverride("/terrain.png", "/plasmacraft/terrain/acid_barrier.png");
		acidTntSideIndex = ModLoader.addOverride("/terrain.png", "/plasmacraft/terrain/acid_tnt.png");
		acidTntTopIndex = ModLoader.addOverride("/terrain.png", "/plasmacraft/terrain/acid_tnt_top.png");
		acidTntBottomIndex = ModLoader.addOverride("/terrain.png", "/plasmacraft/terrain/acid_tnt_bottom.png");

		acidStillIndex = ModLoader.getUniqueSpriteIndex("/terrain.png");
		acidMovingIndex = ModLoader.getUniqueSpriteIndex("/terrain.png");
		plutoniumStillIndex = ModLoader.getUniqueSpriteIndex("/terrain.png");
		plutoniumMovingIndex = ModLoader.getUniqueSpriteIndex("/terrain.png");
		radioniteStillIndex = ModLoader.getUniqueSpriteIndex("/terrain.png");
		radioniteMovingIndex = ModLoader.getUniqueSpriteIndex("/terrain.png");
		uraniumStillIndex = ModLoader.getUniqueSpriteIndex("/terrain.png");
		uraniumMovingIndex = ModLoader.getUniqueSpriteIndex("/terrain.png");
		neptuniumStillIndex = ModLoader.getUniqueSpriteIndex("/terrain.png");
		neptuniumMovingIndex = ModLoader.getUniqueSpriteIndex("/terrain.png");
		netherflowStillIndex = ModLoader.getUniqueSpriteIndex("/terrain.png");
		netherflowMovingIndex = ModLoader.getUniqueSpriteIndex("/terrain.png");
		obsidiumStillIndex = ModLoader.getUniqueSpriteIndex("/terrain.png");
		obsidiumMovingIndex = ModLoader.getUniqueSpriteIndex("/terrain.png");
		//milkStillIndex = ModLoader.getUniqueSpriteIndex("/terrain.png");
		//milkMovingIndex = ModLoader.getUniqueSpriteIndex("/terrain.png");

		ingotPlutoniumIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/plutonium_ingot.png");
		ingotRadioniteIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/radionite_ingot.png");
		ingotNeptuniumIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/neptunium_ingot.png");
		ingotObsidiumIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/obsidium_ingot.png");
		ingotUraniumIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/uranium_ingot.png");
		ingotNetherflowIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/netherflow_ingot.png");
		acidVialIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/empty_vial.png");
		fullAcidVialIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/acid_vial.png");
		plutoniumVialIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/plutonium_vial.png");
		radioniteVialIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/radionite_vial.png");
		uraniumVialIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/uranium_vial.png");
		neptuniumVialIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/neptunium_vial.png");
		netherflowVialIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/netherflow_vial.png");
		obsidiumVialIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/obsidium_vial.png");
		//milkVialIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/milk_vial.png");
		plasmaGelIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/plasma_gel.png");
		plasmaLeatherIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/plasma_leather.png");
		plasmaIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/plasma.png");
		causticBoatIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/caustic_boat.png");
		acidGrenadeIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/acidgrenade.png");
		goopPlutoniumIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/plutonium_goop.png");
		goopRadioniteIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/radionite_goop.png");
		goopNeptuniumIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/neptunium_goop.png");
		goopNetherflowIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/netherflow_goop.png");
		goopObsidiumIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/obsidium_goop.png");
		goopUraniumIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/uranium_goop.png");
		lasergunIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/lasergun.png");
		plasmagunIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/plasmagun.png");
		plasmagunsplitIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/plasmagunsplit.png");
		lasergunsplitIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/lasergunsplit.png");
		acidgunIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/acidgun.png");
		railgunIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/railgun.png");
		lasershotgunIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/lasershotgun.png");
		energyCellIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/energycell.png");
		BatteryEmptyIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/emptybat.png");
		BatteryChargedIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/chargedbat.png");
		BatteryOverchargedIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/overchargedbat.png");
		BatteryPlasmaIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/plasmabat.png");
		beamSplitterIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/beamsplit.png");
		helmetIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/hazmat/hazmat_helmet.png");
		plateIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/hazmat/hazmat_jacket.png");
		legsIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/hazmat/hazmat_pants.png");
		bootsIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/hazmat/hazmat_boots.png");

		frozenCryoniteIndex = ModLoader.addOverride("/terrain.png", "/plasmacraft/terrain/frozenCryonite.png");
		cryoniteStillIndex = ModLoader.getUniqueSpriteIndex("/terrain.png");
		cryoniteMovingIndex = ModLoader.getUniqueSpriteIndex("/terrain.png");
		ingotCryoniteIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/cryonite_ingot.png");
		cryoniteVialIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/cryonite_vial.png");
		goopCryoniteIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/cryonite_goop.png");
		cryoblasterIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/cryoblaster.png");
		BatteryCryoIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/cryobat.png");
    	caustic = new MaterialLiquid(MapColor.waterColor);


 		ModLoader.RegisterTileEntity(SMTileEntityPlasmaBench.class, "plasmaBench");
 		ModLoader.RegisterTileEntity(SMTileEntityCaustic.class, "causticTile");

		if(props.getInt("greenGlowClothID") != 0)
		{
    		glowCloth1 = (new SMBlockGlowCloth(props.getInt("greenGlowClothID"), glowCloth1Index)
    		.setBlockName("glowcloth1")
    		.setLightValue(1.0F)
    		.setStepSound(Block.soundClothFootstep));
		}

		if(props.getInt("pinkGlowClothID") != 0)
		{
    		glowCloth2 = (new SMBlockGlowCloth(props.getInt("pinkGlowClothID"), glowCloth2Index)
    		.setBlockName("glowcloth2")
    		.setLightValue(1.0F)
    		.setStepSound(Block.soundClothFootstep));
		}

		cryoniteStillBlockID = props.getInt("cryoniteStillID");
		cryoniteFlowingBlockID = props.getInt("cryoniteFlowingID");
		cryoniteStill = (new SMBlockCausticStationary(cryoniteStillBlockID, cryoniteStillIndex, cryoniteMovingIndex, causticID, cryoniteStillBlockID, cryoniteFlowingBlockID)).setHardness(5F).setLightOpacity(3).setBlockName("cryoniteStill");
		cryoniteMoving = (new SMBlockCausticFlowing(cryoniteFlowingBlockID, cryoniteStillIndex, cryoniteMovingIndex, causticID, cryoniteStillBlockID, cryoniteFlowingBlockID)).setHardness(5F).setLightOpacity(3).setBlockName("cryoniteMoving");
		
		acidStillBlockID = props.getInt("acidStillID");
		acidFlowingBlockID = props.getInt("acidFlowingID");
		radioniteStillBlockID = props.getInt("radioniteStillID");
		radioniteFlowingBlockID = props.getInt("radioniteFlowingID");
		plutoniumStillBlockID = props.getInt("plutoniumStillID");
		plutoniumFlowingBlockID = props.getInt("plutoniumFlowingID");
		netherflowStillBlockID = props.getInt("netherflowStillID");
		netherflowFlowingBlockID = props.getInt("netherflowFlowingID");
		neptuniumStillBlockID = props.getInt("neptuniumStillID");
		neptuniumFlowingBlockID = props.getInt("neptuniumFlowingID");
		uraniumStillBlockID = props.getInt("uraniumStillID");
		uraniumFlowingBlockID = props.getInt("uraniumFlowingID");
		obsidiumStillBlockID = props.getInt("obsidiumStillID");
		obsidiumFlowingBlockID = props.getInt("obsidiumFlowingID");
		System.out.println("acidStillBlockID " + acidStillBlockID);
		System.out.println("acidFlowingBlockID " + acidFlowingBlockID);
        acidStill = (new SMBlockCausticStationary(acidStillBlockID, acidStillIndex, acidMovingIndex, causticID, acidStillBlockID, acidFlowingBlockID)).setHardness(5F).setLightValue(1.0F).setLightOpacity(3).setBlockName("acidStill");
        acidMoving = (new SMBlockCausticFlowing(acidFlowingBlockID, acidStillIndex, acidMovingIndex, causticID, acidStillBlockID, acidFlowingBlockID)).setHardness(5F).setLightValue(1.0F).setLightOpacity(3).setBlockName("acidMoving");
        radioniteStill = (new SMBlockCausticStationary(radioniteStillBlockID, radioniteStillIndex, radioniteMovingIndex, causticID, radioniteStillBlockID, radioniteFlowingBlockID)).setHardness(5F).setLightValue(1.0F).setLightOpacity(3).setBlockName("radioniteStill");
        radioniteMoving = (new SMBlockCausticFlowing(radioniteFlowingBlockID, radioniteStillIndex, radioniteMovingIndex, causticID, radioniteStillBlockID, radioniteFlowingBlockID)).setHardness(5F).setLightValue(1.0F).setLightOpacity(3).setBlockName("radioniteMoving");
        plutoniumStill = (new SMBlockCausticStationary(plutoniumStillBlockID, plutoniumStillIndex, plutoniumMovingIndex, causticID, plutoniumStillBlockID, plutoniumFlowingBlockID)).setHardness(5F).setLightValue(1.0F).setLightOpacity(3).setBlockName("plutoniumStill");
        plutoniumMoving = (new SMBlockCausticFlowing(plutoniumFlowingBlockID, plutoniumStillIndex, plutoniumMovingIndex, causticID, plutoniumStillBlockID, plutoniumFlowingBlockID)).setHardness(5F).setLightValue(1.0F).setLightOpacity(3).setBlockName("plutoniumMoving");
        netherflowStill = (new SMBlockCausticStationary(netherflowStillBlockID, netherflowStillIndex, netherflowMovingIndex, causticID, netherflowStillBlockID, netherflowFlowingBlockID)).setHardness(5F).setLightValue(1.0F).setLightOpacity(3).setBlockName("netherflowStill");
        netherflowMoving = (new SMBlockCausticFlowing(netherflowFlowingBlockID, netherflowStillIndex, netherflowMovingIndex, causticID, netherflowStillBlockID, netherflowFlowingBlockID)).setHardness(5F).setLightValue(1.0F).setLightOpacity(3).setBlockName("netherflowMoving");
        neptuniumStill = (new SMBlockCausticStationary(neptuniumStillBlockID, neptuniumStillIndex, neptuniumMovingIndex, causticID, neptuniumStillBlockID, neptuniumFlowingBlockID)).setHardness(5F).setLightValue(1.0F).setLightOpacity(3).setBlockName("neptuniumStill");
        neptuniumMoving = (new SMBlockCausticFlowing(neptuniumFlowingBlockID, neptuniumStillIndex, neptuniumMovingIndex, causticID, neptuniumStillBlockID, neptuniumFlowingBlockID)).setHardness(5F).setLightValue(1.0F).setLightOpacity(3).setBlockName("neptuniumMoving");
        uraniumStill = (new SMBlockCausticStationary(uraniumStillBlockID, uraniumStillIndex, uraniumMovingIndex, causticID, uraniumStillBlockID, uraniumFlowingBlockID)).setHardness(5F).setLightValue(1.0F).setLightOpacity(3).setBlockName("uraniumStill");
        uraniumMoving = (new SMBlockCausticFlowing(uraniumFlowingBlockID, uraniumStillIndex, uraniumMovingIndex, causticID, uraniumStillBlockID, uraniumFlowingBlockID)).setHardness(5F).setLightValue(1.0F).setLightOpacity(3).setBlockName("uraniumMoving");
        obsidiumStill = (new SMBlockCausticStationary(obsidiumStillBlockID, obsidiumStillIndex, obsidiumMovingIndex, causticID, obsidiumStillBlockID, obsidiumFlowingBlockID)).setHardness(5F).setLightValue(0.6F).setLightOpacity(3).setBlockName("obsidiumStill");
        obsidiumMoving = (new SMBlockCausticFlowing(obsidiumFlowingBlockID, obsidiumStillIndex, obsidiumMovingIndex, causticID, obsidiumStillBlockID, obsidiumFlowingBlockID)).setHardness(5F).setLightValue(0.6F).setLightOpacity(3).setBlockName("obsidiumMoving");

        reinforcedGlass = (new SMBlockReinforcedGlass(props.getInt("reinforcedGlassID"), reinforcedGlassIndex, Material.glass, false))
        .setHardness(1.0F)
        .setResistance(500F)
        .setStepSound(Block.soundGlassFootstep)
        .setBlockName("reinforcedGlass");
        
        frozenCryonite = (new SMBlockReinforcedGlass(props.getInt("frozenCryoniteID"), frozenCryoniteIndex, Material.glass, false))
		 .setHardness(1.0F)
		 .setResistance(1F)
		 .setStepSound(Block.soundGlassFootstep)
		 .setBlockName("frozenCryonite")
		 .setTickOnLoad(true);

        plasmificatorIdle = (new SMBlockPlasmificator(props.getInt("plasmificatorIdleID"), plasmificatorSidesIndex,
        																				 plasmificatorFrontIdleIndex,
        																				 plasmificatorFrontActiveIndex, false))
        																				 .setHardness(3.0F)
        																				 .setStepSound(Block.soundStoneFootstep)
        																				 .setBlockName("plasmificatorIdle");


        plasmificatorActive = (new SMBlockPlasmificator(props.getInt("plasmificatorActiveID"), plasmificatorSidesIndex,
        																				   	 plasmificatorFrontIdleIndex,
        																				   	 plasmificatorFrontActiveIndex, true))
        																				   	 .setHardness(3.0F)
        																				   	 .setLightValue(1.0f)
        																				   	 .setStepSound(Block.soundStoneFootstep)
        																				   	 .setBlockName("plasmificatorActive");

        if(props.getInt("acidBarrierID") != 0)
		{
        	acidHot = (new SMBlockAcidHot(props.getInt("acidBarrierID"), acidHotIndex))
        	.setHardness(3F)
        	.setResistance(5F)
        	.setLightValue(0.2F)
        	.setLightOpacity(2)
        	.setLightValue(1.0f)
        	.setStepSound(Block.soundGlassFootstep)
        	.setBlockName("acidHot");
		}

		if(props.getInt("acidTntID") != 0)
		{
        	acidTnt = (new SMBlockAcidTNT(props.getInt("acidTntID"), acidTntSideIndex, acidTntTopIndex, acidTntBottomIndex))
        	.setHardness(0.0F)
        	.setStepSound(Block.soundGrassFootstep)
        	.setBlockName("acidTnt");
		}
		
    	BatteryCryo = (new Item(props.getInt("BatteryCryoID")))
    	.setIconIndex(BatteryCryoIndex)
    	.setItemName("batteryCryo");
    	
    	ingotCryonite = (new Item(props.getInt("ingotCryoniteID")))
    	.setIconIndex(ingotCryoniteIndex)
    	.setItemName("ingotCryonite");
    	
    	goopCryonite = (new Item(props.getInt("goopCryoniteID")))
    	.setIconIndex(goopCryoniteIndex)
    	.setItemName("goopCryonite");
        
        cryoniteVial = (new SMItemAcidVial(props.getInt("cryoniteVialID"), mod_PlasmaCraft.cryoniteMoving.blockID))
        .setIconIndex(cryoniteVialIndex)
        .setItemName("cryoniteVial")
        .setContainerItem(acidVial);
        
    	cryoblaster = (new SMItemEnergyWeapon(props.getInt("cryoblasterID"), 100))
    	.setIconIndex(cryoblasterIndex)
    	.setItemName("cryoBlaster");

    	ingotPlutonium = (new Item(props.getInt("ingotPlutoniumID")))
    	.setIconIndex(ingotPlutoniumIndex)
    	.setItemName("ingotPlutonium");

    	ingotRadionite = (new Item(props.getInt("ingotRadioniteID")))
    	.setIconIndex(ingotRadioniteIndex)
    	.setItemName("ingotRadionite");
    	
    	energyCell = (new Item(props.getInt("energyCellID")))
    	.setIconIndex(energyCellIndex)
    	.setItemName("energyCell");
    	
    	beamSplitter = (new Item(props.getInt("beamSplitterID")))
    	.setIconIndex(beamSplitterIndex)
    	.setItemName("beamSplitter");
    	
    	BatteryEmpty = (new Item(props.getInt("BatteryEmptyID")))
    	.setIconIndex(BatteryEmptyIndex)
    	.setItemName("batteryEmpy");
    	
    	BatteryCharged = (new Item(props.getInt("BatteryChargedID")))
    	.setIconIndex(BatteryChargedIndex)
    	.setItemName("batteryCharged");

    	BatteryOvercharged = (new Item(props.getInt("BatteryOverchargedID")))
    	.setIconIndex(BatteryOverchargedIndex)
    	.setItemName("batteryOvercharged");
    	
    	BatteryPlasma = (new Item(props.getInt("BatteryPlasmaID")))
    	.setIconIndex(BatteryPlasmaIndex)
    	.setItemName("batteryPlasma");

    	ingotNeptunium = (new Item(props.getInt("ingotNeptuniumID")))
    	.setIconIndex(ingotNeptuniumIndex)
    	.setItemName("ingotNeptunium");

    	ingotObsidium = (new Item(props.getInt("ingotObsidiumID")))
    	.setIconIndex(ingotObsidiumIndex)
    	.setItemName("ingotObsidium");

    	ingotUranium = (new Item(props.getInt("ingotUraniumID")))
    	.setIconIndex(ingotUraniumIndex)
    	.setItemName("ingotUranium");

    	ingotNetherflow = (new Item(props.getInt("ingotNetherflowID")))
    	.setIconIndex(ingotNetherflowIndex)
    	.setItemName("ingotNetherflow");

    	goopPlutonium = (new Item(props.getInt("goopPlutoniumID")))
    	.setIconIndex(goopPlutoniumIndex)
    	.setItemName("goopPlutonium");

    	goopRadionite = (new Item(props.getInt("goopRadioniteID")))
    	.setIconIndex(goopRadioniteIndex)
    	.setItemName("goopRadionite");

    	goopNeptunium = (new Item(props.getInt("goopNeptuniumID")))
    	.setIconIndex(goopNeptuniumIndex)
    	.setItemName("goopNeptunium");

    	goopNetherflow = (new Item(props.getInt("goopNetherflowID")))
    	.setIconIndex(goopNetherflowIndex)
    	.setItemName("goopNetherflow");

    	goopObsidium = (new Item(props.getInt("goopObsidiumID")))
    	.setIconIndex(goopObsidiumIndex)
    	.setItemName("goopObsidium");

    	goopUranium = (new Item(props.getInt("goopUraniumID")))
    	.setIconIndex(goopUraniumIndex)
    	.setItemName("goopUranium");

        acidVial = (new SMItemAcidVial(props.getInt("emptyVialID"), 0))
        .setIconIndex(acidVialIndex)
        .setItemName("acidVial");

        fullAcidVial = (new SMItemAcidVial(props.getInt("acidVialID"), mod_PlasmaCraft.acidMoving.blockID))
        .setIconIndex(fullAcidVialIndex)
        .setItemName("fullAcidVial")
        .setContainerItem(acidVial);

        plutoniumVial = (new SMItemAcidVial(props.getInt("plutoniumViaID"), mod_PlasmaCraft.plutoniumMoving.blockID))
        .setIconIndex(plutoniumVialIndex)
        .setItemName("plutoniumVial")
        .setContainerItem(acidVial);

        radioniteVial = (new SMItemAcidVial(props.getInt("radioniteVialID"), mod_PlasmaCraft.radioniteMoving.blockID))
        .setIconIndex(radioniteVialIndex)
        .setItemName("radioniteVial")
        .setContainerItem(acidVial);

        uraniumVial = (new SMItemAcidVial(props.getInt("uraniumViaID"), mod_PlasmaCraft.uraniumMoving.blockID))
        .setIconIndex(uraniumVialIndex)
        .setItemName("uraniumVial")
        .setContainerItem(acidVial);

        neptuniumVial = (new SMItemAcidVial(props.getInt("neptuniumVialID"), mod_PlasmaCraft.neptuniumMoving.blockID))
        .setIconIndex(neptuniumVialIndex)
        .setItemName("neptuniumVial")
        .setContainerItem(acidVial);

        netherflowVial = (new SMItemAcidVial(props.getInt("netherflowVialID"), mod_PlasmaCraft.netherflowMoving.blockID))
        .setIconIndex(netherflowVialIndex)
        .setItemName("netherflowVial")
        .setContainerItem(acidVial);

        obsidiumVial = (new SMItemAcidVial(props.getInt("obsidiumVialID"), mod_PlasmaCraft.obsidiumMoving.blockID))
        .setIconIndex(obsidiumVialIndex)
        .setItemName("obsidiumVial")
        .setContainerItem(acidVial);

        //milkVial = (new ItemAcidVial(props.getInt("milkVialID"), 7))
        //.setIconIndex(milkVialIndex)
        //.setItemName("milkVial")
        //.setContainerItem(acidVial);

        plasmaGel = (new SMItemPlasmaGel(props.getInt("plasmaGelID")))
        .setIconIndex(plasmaGelIndex)
        .setItemName("plasmaGel");

        plasmaLeather = (new Item(props.getInt("plasmaLeatherID")))
        .setIconIndex(plasmaLeatherIndex)
        .setItemName("plasmaLeather");

        plasma = (new SMItemPlasma(props.getInt("plasmaID")))
        .setIconIndex(plasmaIndex)
        .setItemName("plasma");

    	causticBoat = (new SMItemCausticBoat(props.getInt("causticBoatID")))
    	.setIconIndex(causticBoatIndex)
    	.setItemName("causticBoat");

    	acidGrenade = (new SMItemAcidGrenade(props.getInt("acidNadeID")))
    	.setIconIndex(acidGrenadeIndex)
    	.setItemName("acidGrenade");
    	
    	lasergun = (new SMItemEnergyWeapon(props.getInt("lasergunID"), 100))
    	.setIconIndex(lasergunIndex)
    	.setItemName("laserGun");
    	
    	plasmagun = (new SMItemEnergyWeapon(props.getInt("plasmagunID"), 100))
    	.setIconIndex(plasmagunIndex)
    	.setItemName("plasmaGun");
    	
    	plasmagunsplit = (new SMItemEnergyWeapon(props.getInt("plasmagunsplitID"), 75))
    	.setIconIndex(plasmagunsplitIndex)
    	.setItemName("plasmaGunSplit");
    	
    	lasergunsplit = (new SMItemEnergyWeapon(props.getInt("lasergunsplitID"), 75))
    	.setIconIndex(lasergunsplitIndex)
    	.setItemName("laserGunSplit");
    	
    	acidgun = (new SMItemEnergyWeapon(props.getInt("acidgunID"), 100))
    	.setIconIndex(acidgunIndex)
    	.setItemName("acidGun");
    	
    	railgun = (new SMItemEnergyWeapon(props.getInt("railgunID"), 100))
    	.setIconIndex(railgunIndex)
    	.setItemName("railGun");
    	
    	cryoblaster = (new SMItemEnergyWeapon(props.getInt("cryoblasterID"), 100))
    	.setIconIndex(cryoblasterIndex)
    	.setItemName("cryoBlaster");
    	
    	lasershotgun = (new SMItemEnergyWeapon(props.getInt("lasershotgunID"), 100))
    	.setIconIndex(lasershotgunIndex)
    	.setItemName("lasershotgunGun");


        hazmatHoodID = props.getInt("hazmatHoodID");
        hazmatJacketID = props.getInt("hazmatJacketID");
        hazmatPantsID = props.getInt("hazmatPantsID");
        hazmatBootsID = props.getInt("hazmatBootsID");

        int renderIndex = ModLoader.AddArmor("hazmat");
    	helmetHazmat = (new ItemArmor(props.getInt("hazmatHoodID"), 2, renderIndex, 0))
    	.setIconIndex(helmetIndex)
    	.setItemName("helmetHazmat");

    	plateHazmat = (new ItemArmor(props.getInt("hazmatJacketID"), 2, renderIndex, 1))
    	.setIconIndex(plateIndex)
    	.setItemName("plateHazmat");

    	legsHazmat = (new ItemArmor(props.getInt("hazmatPantsID"), 2, renderIndex, 2))
    	.setIconIndex(legsIndex)
    	.setItemName("legsHazmat");

    	bootsHazmat = (new ItemArmor(props.getInt("hazmatBootsID"), 2, renderIndex, 3))
    	.setIconIndex(bootsIndex)
    	.setItemName("bootsHazmat");

        ModLoader.RegisterBlock(cryoniteStill);
        ModLoader.RegisterBlock(cryoniteMoving);
        ModLoader.RegisterBlock(frozenCryonite);
        ModLoader.RegisterBlock(glowCloth1);
        ModLoader.RegisterBlock(glowCloth2);
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
		ModLoader.RegisterBlock(reinforcedGlass);
		ModLoader.RegisterBlock(plasmificatorIdle);
		ModLoader.RegisterBlock(plasmificatorActive);
		ModLoader.RegisterBlock(acidHot);
		ModLoader.RegisterBlock(acidTnt);

		ModLoader.RegisterEntityID(SMEntityCausticBoat.class, "RadioniteBoat", ModLoader.getUniqueEntityId());
		ModLoader.RegisterEntityID(SMEntityAcidTNTPrimed.class, "AcidTNTPrimed", ModLoader.getUniqueEntityId());

		neptuniumOreYStart = props.getInt("neptuniumOreYStart");
		neptuniumOreYRange = props.getInt("neptuniumOreYRange");
		neptuniumOreVeinCount = props.getInt("neptuniumOreVeinCount");
		neptuniumOreVeinSize = props.getInt("neptuniumOreVeinSize");
		obsidiumOreYStart = props.getInt("obsidiumOreYStart");
		obsidiumOreYRange = props.getInt("obsidiumOreYRange");
		obsidiumOreVeinCount = props.getInt("obsidiumOreVeinCount");
		obsidiumOreVeinSize = props.getInt("obsidiumOreVeinSize");
		netherflowLakeChance = props.getInt("netherflowLakeChance");
		netherflowLakeYStart = props.getInt("netherflowLakeYStart");
		netherflowLakeYRange = props.getInt("netherflowLakeYRange");
		netherflowLakeYCutoff = props.getInt("netherflowLakeYCutoff");
		netherflowSpoutYStart = props.getInt("netherflowSpoutYStart");
		netherflowSpoutYRange = props.getInt("netherflowSpoutYRange");
		netherflowSpoutCount = props.getInt("netherflowSpoutCount");
		neptuniumSpoutYStart = props.getInt("neptuniumSpoutYStart");
		neptuniumSpoutYRange = props.getInt("neptuniumSpoutYRange");
		neptuniumSpoutCount = props.getInt("neptuniumSpoutCount");

		plutoniumOreYStart = props.getInt("plutoniumOreYStart");
		plutoniumOreYRange = props.getInt("plutoniumOreYRange");
		plutoniumOreVeinCount = props.getInt("plutoniumOreVeinCount");
		plutoniumOreVeinSize = props.getInt("plutoniumOreVeinSize");
		
		uraniumOreYStart = props.getInt("uraniumOreYStart");
		uraniumOreYRange = props.getInt("uraniumOreYRange");
		uraniumOreVeinCount = props.getInt("uraniumOreVeinCount");
		uraniumOreVeinSize = props.getInt("uraniumOreVeinSize");
		
		radioniteOreYStart = props.getInt("radioniteOreYStart");
		radioniteOreYRange = props.getInt("radioniteOreYRange");
		radioniteOreVeinCount = props.getInt("radioniteOreVeinCount");
		radioniteOreVeinSize = props.getInt("radioniteOreVeinSize");
		acidLakeChance = props.getInt("acidLakeChance");
		acidLakeYStart = props.getInt("acidLakeYStart");
		acidLakeYRange = props.getInt("acidLakeYRange");
		acidLakeYCutoff = props.getInt("acidLakeYCutoff");
		acidSpoutYStart = props.getInt("acidSpoutYStart");
		acidSpoutYRange = props.getInt("acidSpoutYRange");
		acidSpoutCount = props.getInt("acidSpoutCount");
		
		
		
		AddRecipes();

		// HACK around the fact that we can't edit the Item class directly
        for(int i = 0; i < 256; i++)
        {
            if(Block.blocksList[i] != null && Item.itemsList[i] != null && Item.itemsList[i] instanceof ItemBlock &&
            	!Item.itemsList[i].getHasSubtypes())
            {
                Item.itemsList[i] = new SMItemCausticSupportBlock(i - 256);
            }
        }
	}

	public static void AddRecipes()
	{
		
        // Begin PlasmaCraft recipes
		ModLoader.AddRecipe(new ItemStack(lasergun, 1), new Object[]{
			"XYZ", 
			" YQ",
			Character.valueOf('X'), ingotNetherflow,
			Character.valueOf('Y'), ingotObsidium,
			Character.valueOf('Z'), goopNetherflow,
			Character.valueOf('Q'), ingotPlutonium
		});
		
		ModLoader.AddRecipe(new ItemStack(plasmagun, 1), new Object[]{
			"XBZ", 
			" YZ",
			Character.valueOf('X'), Item.diamond,
			Character.valueOf('B'), plasma,
			Character.valueOf('Z'), ingotPlutonium,
			Character.valueOf('Y'), ingotObsidium
		});
		
		ModLoader.AddRecipe(new ItemStack(plasmagunsplit, 1), new Object[]{
			"YB", 
			Character.valueOf('B'), plasmagun,
			Character.valueOf('Y'), beamSplitter
		});
		
		ModLoader.AddRecipe(new ItemStack(lasergunsplit, 1), new Object[]{
			"YB", 
			Character.valueOf('B'), lasergun,
			Character.valueOf('Y'), beamSplitter
		});
		
		ModLoader.AddRecipe(new ItemStack(acidgun, 1), new Object[]{
			"  Z", 
			"ABC",
			" MN",
			Character.valueOf('Z'), acidVial,
			Character.valueOf('A'), ingotObsidium,
			Character.valueOf('B'), ingotUranium,
			Character.valueOf('C'), reinforcedGlass,
			Character.valueOf('M'), BatteryPlasma,
			Character.valueOf('N'), Item.ingotIron
		});
		
		ModLoader.AddRecipe(new ItemStack(railgun, 1), new Object[]{
			"XYZ", 
			" BC",
			"XY ",
			Character.valueOf('Z'), BatteryPlasma,
			Character.valueOf('X'), ingotObsidium,
			Character.valueOf('B'), goopPlutonium,
			Character.valueOf('C'), Item.ingotGold,
			Character.valueOf('Y'), ingotPlutonium
		});
		
		ModLoader.AddRecipe(new ItemStack(lasershotgun, 1), new Object[]{
			"  Z", 
			"XBQ",
			" UP",
			Character.valueOf('Z'), Item.redstoneRepeater,
			Character.valueOf('X'), beamSplitter,
			Character.valueOf('B'), ingotNetherflow,
			Character.valueOf('Q'), BatteryCharged,
			Character.valueOf('P'), ingotPlutonium,
			Character.valueOf('U'), ingotRadionite
		});
		
		if (serverprops.getBoolean("AllowAcidGrenades")) 
		{
        ModLoader.AddRecipe(new ItemStack(acidGrenade, 4), new Object[] {
            "X", "Y", "Z",
            Character.valueOf('X'), Item.ingotIron,
            Character.valueOf('Y'), fullAcidVial,
            Character.valueOf('Z'), plasma
        });
		}

        //ModLoader.AddRecipe(new ItemStack(acidGrenade, 4), new Object[] {
        //    "D", "C", "D",
        //    Character.valueOf('D'), Block.planks,
        //    Character.valueOf('C'), Block.dirt,
        //});

        ModLoader.AddRecipe(new ItemStack(reinforcedGlass, 1), new Object[] {
            "X", "#",
            Character.valueOf('#'), Block.glass,
            Character.valueOf('X'), Item.ingotIron,
        });

        ModLoader.AddRecipe(new ItemStack(acidVial, 1), new Object[] {
            "X#X", "Y Y", "X#X",
            Character.valueOf('#'), Item.ingotIron,
            Character.valueOf('Y'), reinforcedGlass,
            Character.valueOf('X'), Block.glass
        });

        ModLoader.AddRecipe(new ItemStack(plasmificatorIdle, 1), new Object[] {
            "X#X", "# #", "X#X",
            Character.valueOf('#'), Item.ingotIron,
            Character.valueOf('X'), fullAcidVial
        });

        ModLoader.AddRecipe(new ItemStack(causticBoat, 1), new Object[] {
            "R R", "RRR",
            Character.valueOf('R'), ingotRadionite
        });
        
        ModLoader.AddRecipe(new ItemStack(energyCell, 5), new Object[] {
            " R ", 
            "RXR",
            " R ",
            Character.valueOf('R'), ingotNeptunium,
            Character.valueOf('X'), plasmaGel
        });
        
        ModLoader.AddRecipe(new ItemStack(beamSplitter, 1), new Object[] {
            " N ", 
            "BXQ",
            " N ",
            Character.valueOf('N'), ingotNetherflow,
            Character.valueOf('X'), BatteryPlasma,
            Character.valueOf('Q'), ingotUranium,
            Character.valueOf('B'), Item.diamond
        });
        
        ModLoader.AddRecipe(new ItemStack(BatteryEmpty, 8), new Object[] {
            "IRI", 
            "I I",
            "IRI",
            Character.valueOf('R'), ingotRadionite,
            Character.valueOf('I'), Item.ingotIron
        });
        
        ModLoader.AddRecipe(new ItemStack(BatteryPlasma, 1), new Object[] {
            "R", 
            "X",
            Character.valueOf('R'), plasma,
            Character.valueOf('X'), BatteryEmpty
        });
        
        ModLoader.AddRecipe(new ItemStack(BatteryCharged, 1), new Object[] {
            "R", 
            "X",
            Character.valueOf('R'), goopPlutonium,
            Character.valueOf('X'), BatteryEmpty
        });
        
		ModLoader.AddRecipe(new ItemStack(cryoblaster, 1), new Object[]{
			"  A", 
			"CBX",
			" DE",
			Character.valueOf('A'), ingotUranium,
			Character.valueOf('B'), goopCryonite,
			Character.valueOf('C'), ingotCryonite,
			Character.valueOf('D'), ingotObsidium,
			Character.valueOf('X'), BatteryCryo,
			Character.valueOf('E'), ingotPlutonium
		});
		
        ModLoader.AddRecipe(new ItemStack(BatteryCryo, 1), new Object[] {
            "R", 
            "X",
            Character.valueOf('R'), goopCryonite,
            Character.valueOf('X'), BatteryEmpty
        });
        
        ModLoader.AddRecipe(new ItemStack(cryoniteVial, 1), new Object[] {
            "Q", "J",
            Character.valueOf('Q'), goopCryonite,
            Character.valueOf('J'), acidVial
        });

        ModLoader.AddRecipe(new ItemStack(plasmaGel, 2), new Object[] {
            "JJJ",
            Character.valueOf('J'), fullAcidVial
        });
        
        ModLoader.AddRecipe(new ItemStack(plasmaGel, 6), new Object[] {
            "JJJ",
        	"JJJ",
        	"JJJ",
            Character.valueOf('J'), fullAcidVial
        });

        ModLoader.AddRecipe(new ItemStack(fullAcidVial, 1), new Object[] {
            "JKL",
            Character.valueOf('J'), Block.dirt, Character.valueOf('K'), Block.sand, Character.valueOf('L'), Block.planks
        });

        ModLoader.AddRecipe(new ItemStack(plasmaLeather, 1), new Object[] {
            "N", "J",
            Character.valueOf('N'), plasmaGel,
            Character.valueOf('J'), Item.leather
        });

		if(props.getInt("acidBarrierID") != 0)
		{
			ModLoader.AddRecipe(new ItemStack(acidHot, 1), new Object[] {
				"Z", "X",
				Character.valueOf('Z'), reinforcedGlass,
				Character.valueOf('X'), fullAcidVial
			});
		}

        ModLoader.AddRecipe(new ItemStack(plutoniumVial, 1), new Object[] {
            "Q", "E",
            Character.valueOf('Q'), goopPlutonium,
            Character.valueOf('E'), acidVial
        });

        ModLoader.AddRecipe(new ItemStack(radioniteVial, 1), new Object[] {
            "Q", "F",
            Character.valueOf('Q'), goopRadionite,
            Character.valueOf('F'), acidVial
        });

        ModLoader.AddRecipe(new ItemStack(uraniumVial, 1), new Object[] {
            "Q", "G",
            Character.valueOf('Q'), goopUranium,
            Character.valueOf('G'), acidVial
        });

        ModLoader.AddRecipe(new ItemStack(neptuniumVial, 1), new Object[] {
            "Q", "H",
            Character.valueOf('Q'), goopNeptunium,
            Character.valueOf('H'), acidVial
        });

        ModLoader.AddRecipe(new ItemStack(netherflowVial, 1), new Object[] {
            "Q", "I",
            Character.valueOf('Q'), goopNetherflow,
            Character.valueOf('I'), acidVial
        });

        ModLoader.AddRecipe(new ItemStack(obsidiumVial, 1), new Object[] {
            "Q", "J",
            Character.valueOf('Q'), goopObsidium,
            Character.valueOf('J'), acidVial
        });

        //ModLoader.AddRecipe(new ItemStack(milkVial, 1), new Object[] {
        //    "Q", "K",
        //    Character.valueOf('Q'), Item.bucketMilk,
        //    Character.valueOf('J'), acidVial
        //});

        ModLoader.AddRecipe(new ItemStack(helmetHazmat, 1), new Object[] {
            "LLL", "L L",
            Character.valueOf('L'), plasmaLeather
        });

        ModLoader.AddRecipe(new ItemStack(plateHazmat, 1), new Object[] {
            "L L", "LLL", "LLL",
            Character.valueOf('L'), plasmaLeather
        });

        ModLoader.AddRecipe(new ItemStack(legsHazmat, 1), new Object[] {
            "LLL", "L L", "L L",
            Character.valueOf('L'), plasmaLeather
        });

        ModLoader.AddRecipe(new ItemStack(bootsHazmat, 1), new Object[] {
            "L L", "L L",
            Character.valueOf('L'), plasmaLeather
        });

        
		if(serverprops.getBoolean("AllowAcidTNT"))
		{
			ModLoader.AddRecipe(new ItemStack(acidTnt, 4), new Object[] {
				"APA", "GAG", "APA",
				Character.valueOf('A'), fullAcidVial,
				Character.valueOf('G'), Item.gunpowder,
				Character.valueOf('P'), plasma
			});
		}

        ModLoader.AddRecipe(new ItemStack(Item.gunpowder, 4), new Object[] {
            "AVG",
            Character.valueOf('A'), fullAcidVial,
            Character.valueOf('V'), acidVial,
            Character.valueOf('G'), plasmaGel
        });

		if(props.getInt("greenGlowClothID") != 0)
		{
        	ModLoader.AddRecipe(new ItemStack(glowCloth1, 1), new Object[] {
        	    "C", "D",
        	    Character.valueOf('C'), Block.cloth,
        	    Character.valueOf('D'), fullAcidVial
        	});
		}

		if(props.getInt("pinkGlowClothID") != 0)
		{
			ModLoader.AddRecipe(new ItemStack(glowCloth2, 1), new Object[] {
				"C", "D",
				Character.valueOf('C'), Block.cloth,
				Character.valueOf('D'), goopRadionite
			});
		}

        //ModLoader.AddRecipe(new ItemStack(Item.leather, 64), new Object[] {
        //    "Q Q", " Q ", "Q Q",
        //    Character.valueOf('Q'), Block.planks
        //});

        //ModLoader.AddRecipe(new ItemStack(mod_PlasmaCraft.gasSource, 64), new Object[] {
        //    "G G", " G ", "G G",
        //    Character.valueOf('G'), Block.glass
        //});

		/*
        ModLoader.AddRecipe(new ItemStack(helmetHazmat, 1), new Object[] {
            "QQQ", "Q Q",
            Character.valueOf('Q'), Item.stick
        });

        ModLoader.AddRecipe(new ItemStack(bootsHazmat, 1), new Object[] {
            "Q Q", "Q Q",
            Character.valueOf('Q'), Item.stick
        });

        ModLoader.AddRecipe(new ItemStack(plateHazmat, 1), new Object[] {
            "Q Q", "QQQ", "QQQ",
            Character.valueOf('Q'), Item.stick
        });

        ModLoader.AddRecipe(new ItemStack(legsHazmat, 1), new Object[] {
            "QQQ", "Q Q", "Q Q",
            Character.valueOf('Q'), Item.stick
        });

        ModLoader.AddRecipe(new ItemStack(legsHazmat, 1), new Object[] {
            "QQQ", "Q Q", "Q Q",
            Character.valueOf('Q'), Item.stick
        });

        ModLoader.AddRecipe(new ItemStack(fullAcidVial, 1), new Object[] {
            "Q Q", "Q Q", "Q Q",
            Character.valueOf('Q'), Item.stick
        });

        ModLoader.AddRecipe(new ItemStack(radioniteVial, 1), new Object[] {
            "QQ ", "QQ ", "Q Q",
            Character.valueOf('Q'), Item.stick
        });

        ModLoader.AddRecipe(new ItemStack(plutoniumVial, 1), new Object[] {
            "QQ ", "QQ ", "Q  ",
            Character.valueOf('Q'), Item.stick
        });

        ModLoader.AddRecipe(new ItemStack(uraniumVial, 1), new Object[] {
            "Q Q", "Q Q", "QQQ",
            Character.valueOf('Q'), Item.stick
        });

        ModLoader.AddRecipe(new ItemStack(obsidiumVial, 1), new Object[] {
            "QQQ", "Q Q", "QQQ",
            Character.valueOf('Q'), Item.stick
        });

        ModLoader.AddRecipe(new ItemStack(netherflowVial, 1), new Object[] {
            "QQQ", "QQQ", "Q Q",
            Character.valueOf('Q'), Item.stick
        });

        ModLoader.AddRecipe(new ItemStack(neptuniumVial, 1), new Object[] {
            "QQQ", "Q  ", "QQQ",
            Character.valueOf('Q'), Item.stick
        });
        */
	}

	public static void prepareProps()
	{
		//server properties
		serverprops.getBoolean("AllowAcidTNT", true);
		serverprops.getBoolean("AllowAcidGrenades", true);
		serverprops.getBoolean("CollidingLiquidsExplode", true);
		serverprops.getBoolean("PlasmaRifleCreatesFire", true);
		serverprops.getBoolean("PlasmaRifleCreatesExplosions", true);
		serverprops.getBoolean("LaserRifleCreatesFire", true);
		serverprops.getBoolean("RailGunCreatesFire", true);
		serverprops.getBoolean("LiquidSourceExplodesAfterCausticExplosion", false);
		//
	    	
		props.getInt("frozenCryoniteID", 131);
		props.getInt("cryoniteStillID", 130);
		props.getInt("cryoniteFlowingID", 129);
		props.getInt("cryoniteVialID", 2093);
		props.getInt("goopCryoniteID", 2094);
		props.getInt("ingotCryoniteID", 2095);
		props.getInt("cryoblasterID", 2096);
		props.getInt("batterycryoID", 2099);
		
		props.getInt("texturePackTileWidth", 16);
		props.getInt("texturePackTileHeight", 16);

		props.getInt("oreUraniumID", 128);
		props.getInt("acidStillID", 127);
		props.getInt("acidFlowingID", 126);
		props.getInt("reinforcedGlassID", 125);
		props.getInt("plasmificatorIdleID", 124);
		props.getInt("plasmificatorActiveID", 123);
		props.getInt("acidBarrierID", 122);
		props.getInt("acidTntID", 121);
		props.getInt("oreNeptuniumID", 120);
		props.getInt("oreObsidiumID", 119);
		props.getInt("greenGlowClothID", 118);
		props.getInt("pinkGlowClothID", 117);
		props.getInt("orePlutoniumID", 116);
		props.getInt("oreRadioniteID", 115);
		props.getInt("oreNeptuniumID", 114);
		props.getInt("oreObsidiumID", 113);
		props.getInt("radioniteStillID", 143);
		props.getInt("radioniteFlowingID", 142);
		props.getInt("plutoniumStillID", 141);
		props.getInt("plutoniumFlowingID", 140);
		props.getInt("neptuniumStillID", 139);
		props.getInt("neptuniumFlowingID", 138);
		props.getInt("uraniumStillID", 137);
		props.getInt("uraniumFlowingID", 136);
		props.getInt("obsidiumStillID", 135);
		props.getInt("obsidiumFlowingID", 134);
		props.getInt("netherflowStillID", 133);
		props.getInt("netherflowFlowingID", 132);

		props.getInt("ingotPlutoniumID", 2048);
		props.getInt("ingotRadioniteID", 2049);
		props.getInt("emptyVialID", 2050);
		props.getInt("acidVialID", 2051);
		props.getInt("plutoniumViaID", 2052);
		props.getInt("radioniteVialID", 2053);
		props.getInt("plasmaGelID", 2054);
		props.getInt("plasmaID", 2055);
		props.getInt("causticBoatID", 2056);
		props.getInt("hazmatHoodID", 2057);
		props.getInt("hazmatJacketID", 2058);
		props.getInt("hazmatPantsID", 2059);
		props.getInt("hazmatBootsID", 2060);
		props.getInt("uraniumViaID", 2061);
		props.getInt("neptuniumVialID", 2062);
		props.getInt("netherflowVialID", 2063);
		props.getInt("obsidiumVialID", 2064);
		//props.getInt("milkVialID", 2065);
		props.getInt("acidNadeID", 2068);
		props.getInt("ingotNeptuniumID", 2069);
		props.getInt("ingotObsidiumID", 2070);
		props.getInt("goopPlutoniumID", 2071);
		props.getInt("goopRadioniteID", 2072);
		props.getInt("goopNeptuniumID", 2073);
		props.getInt("goopObsidiumID", 2074);
		props.getInt("goopNetherflowID", 2075);
		props.getInt("plasmaLeatherID", 2076);
		props.getInt("goopUraniumID", 2077);
		props.getInt("ingotUraniumID", 2078);
		props.getInt("ingotNetherflowID", 2079);
		props.getInt("lasergunID", 2080);
		props.getInt("energyCellID", 2081);
		props.getInt("plasmagunID", 2082);
		props.getInt("BatteryEmptyID", 2083);
		props.getInt("BatteryPlasmaID", 2084);
		props.getInt("acidgunID", 2085);
		props.getInt("beamSplitterID", 2086);
		props.getInt("plasmagunsplitID", 2087);
		props.getInt("lasergunsplitID", 2088);
		props.getInt("railgunID", 2089);
		props.getInt("BatteryChargedID", 2090);
		props.getInt("BatteryOverchargedID", 2091);
		props.getInt("lasershotgunID", 2092);

		props.getInt("neptuniumOreYStart", 32);
		props.getInt("neptuniumOreYRange", 64);
		props.getInt("neptuniumOreVeinCount", 4);
		props.getInt("neptuniumOreVeinSize", 10);
		props.getInt("obsidiumOreYStart", 16);
		props.getInt("obsidiumOreYRange", 32);
		props.getInt("obsidiumOreVeinCount", 4);
		props.getInt("obsidiumOreVeinSize", 10);
		props.getInt("netherflowLakeChance", 32);
		props.getInt("netherflowLakeYStart", 56);
		props.getInt("netherflowLakeYRange", 16);
		props.getInt("netherflowLakeYCutoff", 96);
		props.getInt("netherflowSpoutYStart", 8);
		props.getInt("netherflowSpoutYRange", 96);
		props.getInt("netherflowSpoutCount", 20);
		props.getInt("neptuniumSpoutYStart", 8);
		props.getInt("neptuniumSpoutYRange", 64);
		props.getInt("neptuniumSpoutCount", 20);

		props.getInt("plutoniumOreYStart", 4);
		props.getInt("plutoniumOreYRange", 16);
		props.getInt("plutoniumOreVeinCount", 2);
		props.getInt("plutoniumOreVeinSize", 6);
		
		props.getInt("uraniumOreYStart", 4);
		props.getInt("uraniumOreYRange", 16);
		props.getInt("uraniumOreVeinCount", 2);
		props.getInt("uraniumOreVeinSize", 6);
		
		props.getInt("radioniteOreYStart", 4);
		props.getInt("radioniteOreYRange", 24);
		props.getInt("radioniteOreVeinCount", 2);
		props.getInt("radioniteOreVeinSize", 6);
		props.getInt("acidLakeChance", 32);
		props.getInt("acidLakeYStart", 64);
		props.getInt("acidLakeYRange", 30);
		props.getInt("acidLakeYCutoff", 96);
		props.getInt("acidSpoutYStart", 8);
		props.getInt("acidSpoutYRange", 30);
		props.getInt("acidSpoutCount", 20);
	}
	
	public void GenerateNether(World world, Random random, int chunkX, int chunkZ)
	{
		for(int i = 0; i < neptuniumOreVeinCount; i++)
		{
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(neptuniumOreYRange) + neptuniumOreYStart;
			int z = chunkZ + random.nextInt(16);
			(new SMWorldGenNetherMinable(oreNeptunium.blockID, neptuniumOreVeinSize)).generate(world, random, x, y, z);
		}

		for(int i = 0; i < obsidiumOreVeinCount; i++)
		{
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(obsidiumOreYRange) + obsidiumOreYStart;
			int z = chunkZ + random.nextInt(16);
			(new SMWorldGenNetherMinable(oreObsidium.blockID, obsidiumOreVeinSize)).generate(world, random, x, y, z);
		}

        if(random.nextInt(netherflowLakeChance) == 0)
        {
            int x = random.nextInt(16) + 8;
            int y = random.nextInt(random.nextInt(netherflowLakeYRange) + netherflowLakeYStart);
            int z = random.nextInt(16) + 8;
            if(y < netherflowLakeYCutoff)
            {
                (new SMWorldGenCausticLakes(netherflowMoving.blockID)).generate(world, random, x, y, z);
            }
        }

        for(int index = 0; index < netherflowSpoutCount; index++)
        {
            int x = chunkX + random.nextInt(16) + 8;
            int y = random.nextInt(random.nextInt(netherflowSpoutYRange) + netherflowSpoutYStart);
            int z = chunkZ + random.nextInt(16) + 8;
            (new SMWorldGenNetherCaustics(netherflowMoving.blockID)).generate(world, random, x, y, z);
        }

        for(int index = 0; index < neptuniumSpoutCount; index++)
        {
            int x = chunkX + random.nextInt(16) + 8;
            int y = random.nextInt(random.nextInt(neptuniumSpoutYRange) + neptuniumSpoutYStart);
            int z = chunkZ + random.nextInt(16) + 8;
            (new SMWorldGenNetherCaustics(neptuniumMoving.blockID)).generate(world, random, x, y, z);
        }
	}

	public void GenerateSurface(World world, Random random, int chunkX, int chunkZ)
	{
		for(int i = 0; i < plutoniumOreVeinCount; i++)
		{
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(plutoniumOreYRange) + plutoniumOreYStart;
			int z = chunkZ + random.nextInt(16);
			(new WorldGenMinable(props.getInt("orePlutoniumID"), plutoniumOreVeinSize)).generate(world, random, x, y, z);
		}
		
		for(int i = 0; i < uraniumOreVeinCount; i++)
		{
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(uraniumOreYRange) + uraniumOreYStart;
			int z = chunkZ + random.nextInt(16);
			(new WorldGenMinable(props.getInt("oreUraniumID"), uraniumOreVeinSize)).generate(world, random, x, y, z);
		}

		for(int i = 0; i < radioniteOreVeinCount; i++)
		{
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(radioniteOreYRange) + radioniteOreYStart;
			int z = chunkZ + random.nextInt(16);
			(new WorldGenMinable(props.getInt("oreRadioniteID"), radioniteOreVeinSize)).generate(world, random, x, y, z);
		}

        if(random.nextInt(acidLakeChance) == 0)
        {
            int x = random.nextInt(16) + 8;
            int y = random.nextInt(random.nextInt(acidLakeYRange) + acidLakeYStart);
            int z = random.nextInt(16) + 8;
            if(y < acidLakeYCutoff)
            {
                (new SMWorldGenCausticLakes(acidMoving.blockID)).generate(world, random, x, y, z);
            }
        }

        for(int index = 0; index < acidSpoutCount; index++)
        {
            int x = chunkX + random.nextInt(16) + 8;
            int y = random.nextInt(random.nextInt(acidSpoutYRange) + acidSpoutYStart);
            int z = chunkZ + random.nextInt(16) + 8;
            (new SMWorldGenCaustics(acidMoving.blockID)).generate(world, random, x, y, z);
        }

	}
	
    public static Block cryoniteStill;
    public static Block cryoniteMoving;
    public static Block frozenCryonite;
    public static Item BatteryCryo;
    public static Item ingotCryonite;
    public static Item goopCryonite;
    public static Item cryoniteVial;
    public static Item cryoblaster;
    public static int frozenCryoniteIndex;
    public static int cryoniteStillIndex;
    public static int cryoniteMovingIndex;
    public static int BatteryCryoIndex;
    public static int ingotCryoniteIndex;
    public static int goopCryoniteIndex;
    public static int cryoblasterIndex;
    public static int cryoniteVialIndex;
    public static int cryoniteStillBlockID;
	public static int cryoniteFlowingBlockID;
   
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

    public static final Block orePlutonium;
    public static final Block oreRadionite;
    public static final Block oreNeptunium;
    public static final Block oreObsidium;
    public static final Block oreUranium;
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
	public static Item BatteryCharged;
	public static Item BatteryOvercharged;
	public static Item BatteryPlasma;
	public static Item ingotNeptunium;
	public static Item ingotObsidium;
	public static Item ingotUranium;
	public static Item ingotNetherflow;
	public static Item goopPlutonium;
	public static Item goopRadionite;
	public static Item goopNeptunium;
	public static Item goopNetherflow;
	public static Item goopObsidium;
	public static Item goopUranium;
    public static Item acidVial;
    public static Item fullAcidVial;
    public static Item plutoniumVial;
    public static Item radioniteVial;
    public static Item neptuniumVial;
    public static Item netherflowVial;
    public static Item obsidiumVial;
    public static Item uraniumVial;
    public static Item milkVial;
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
    public static Item lasershotgun;

    public static Item helmetHazmat;
    public static Item plateHazmat;
    public static Item legsHazmat;
    public static Item bootsHazmat;

    public static Material caustic;
    public static Material gas;

	public static int orePlutoniumIndex;
	public static int oreRadioniteIndex;
	public static int oreNeptuniumIndex;
	public static int oreObsidiumIndex;
	public static int reinforcedGlassIndex;
	public static int plasmificatorSidesIndex;
	public static int plasmificatorFrontIdleIndex;
	public static int plasmificatorFrontActiveIndex;
	public static int acidHotIndex;
	public static int acidTntSideIndex;
	public static int acidTntTopIndex;
	public static int acidTntBottomIndex;
	public static int glowCloth1Index;
	public static int glowCloth2Index;
	public static int blockUraniumIndex;

	public static int gasIndex;
	public static int acidStillIndex;
	public static int acidMovingIndex;
	public static int plutoniumStillIndex;
	public static int plutoniumMovingIndex;
	public static int radioniteStillIndex;
	public static int radioniteMovingIndex;
	public static int uraniumStillIndex;
	public static int uraniumMovingIndex;
	public static int neptuniumStillIndex;
	public static int neptuniumMovingIndex;
	public static int netherflowStillIndex;
	public static int netherflowMovingIndex;
	public static int obsidiumStillIndex;
	public static int obsidiumMovingIndex;
	public static int milkStillIndex;
	public static int milkMovingIndex;

	public static int ingotPlutoniumIndex;
	public static int ingotRadioniteIndex;
	public static int energyCellIndex;
	public static int beamSplitterIndex;
	public static int BatteryEmptyIndex;
	public static int BatteryChargedIndex;
	public static int BatteryOverchargedIndex;
	public static int BatteryPlasmaIndex;
	public static int ingotNeptuniumIndex;
	public static int ingotObsidiumIndex;
	public static int ingotUraniumIndex;
	public static int ingotNetherflowIndex;
	public static int goopPlutoniumIndex;
	public static int goopRadioniteIndex;
	public static int goopNeptuniumIndex;
	public static int goopNetherflowIndex;
	public static int goopObsidiumIndex;
	public static int goopUraniumIndex;
    public static int acidGrenadeIndex;
    public static int lasergunIndex;
    public static int plasmagunIndex;
    public static int plasmagunsplitIndex;
    public static int lasergunsplitIndex;
    public static int acidgunIndex;
    public static int railgunIndex;
    public static int lasershotgunIndex;
    
	public static int acidVialIndex;
	public static int fullAcidVialIndex;
	public static int plutoniumVialIndex;
	public static int radioniteVialIndex;
	public static int uraniumVialIndex;
	public static int neptuniumVialIndex;
	public static int netherflowVialIndex;
	public static int obsidiumVialIndex;
	public static int milkVialIndex;

	public static int plasmaGelIndex;
	public static int plasmaLeatherIndex;
	public static int plasmaIndex;

	public static int causticBoatIndex;

	public static int helmetIndex;
	public static int plateIndex;
	public static int legsIndex;
	public static int bootsIndex;

	public static int causticID;
	public static int gasID;

	static SMPlasmaProps props;
	private static SMPlasmaProps serverprops;

	private static int texTileWidth;
	private static int texTileHeight;

	private static int neptuniumOreYStart;
	private static int neptuniumOreYRange;
	private static int neptuniumOreVeinCount;
	private static int neptuniumOreVeinSize;
	private static int obsidiumOreYStart;
	private static int obsidiumOreYRange;
	private static int obsidiumOreVeinCount;
	private static int obsidiumOreVeinSize;
	private static int netherflowLakeChance;
	private static int netherflowLakeYStart;
	private static int netherflowLakeYRange;
	private static int netherflowLakeYCutoff;
	private static int netherflowSpoutYStart;
	private static int netherflowSpoutYRange;
	private static int netherflowSpoutCount;
	private static int neptuniumSpoutYStart;
	private static int neptuniumSpoutYRange;
	private static int neptuniumSpoutCount;

	static mod_PlasmaCraft inst1;
	
	static int plutoniumOreYStart;
	static int plutoniumOreYRange;
	static int plutoniumOreVeinCount;
	static int plutoniumOreVeinSize;
	
	static int uraniumOreYStart;
	static int uraniumOreYRange;
	static int uraniumOreVeinCount;
	static int uraniumOreVeinSize;
	
	private static int radioniteOreYStart;
	private static int radioniteOreYRange;
	private static int radioniteOreVeinCount;
	private static int radioniteOreVeinSize;
	private static int acidLakeChance;
	private static int acidLakeYStart;
	private static int acidLakeYRange;
	private static int acidLakeYCutoff;
	private static int acidSpoutYStart;
	private static int acidSpoutYRange;
	private static int acidSpoutCount;

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
    private static String path;
    
    public static int allowCollisionExplosions;
    public static int PlasmaRifleCreatesFire;
    public static int PlasmaRifleCreatesExplosions;
    public static int LaserRifleCreatesFire;
    public static int LiquidSourceExplodesAfterCausticExplosion;
    public static int RailGunCreatesFire;
    
	static
	{
		try {
			path = MinecraftServer.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		path = (path.substring(0, path.lastIndexOf(47))).substring(0, path.lastIndexOf(47));
		props = new SMPlasmaProps(new File(path + "/" + "mod_PlasmaCraft.props").getPath());
		serverprops = new SMPlasmaProps(new File(path + "/" + "PlasmaCraftServer.props").getPath());
		prepareProps();
		
		if (serverprops.getBoolean("CollidingLiquidsExplode")){
			allowCollisionExplosions = 1;
		}else {
			allowCollisionExplosions = 0;
		}
		
		if (serverprops.getBoolean("PlasmaRifleCreatesFire")){
			PlasmaRifleCreatesFire = 1;
		}else {
			PlasmaRifleCreatesFire = 0;
		}
		
		if (serverprops.getBoolean("PlasmaRifleCreatesExplosions"))
		{
			PlasmaRifleCreatesExplosions = 1;
		}else {
			PlasmaRifleCreatesExplosions = 0;
		}
		
		if (serverprops.getBoolean("LaserRifleCreatesFire"))
		{
			LaserRifleCreatesFire = 1;
		}else {
			LaserRifleCreatesFire = 0;
		}
		
		if (serverprops.getBoolean("LiquidSourceExplodesAfterCausticExplosion"))
		{
			LiquidSourceExplodesAfterCausticExplosion = 1;
		}else {
			LiquidSourceExplodesAfterCausticExplosion = 0;
		}
		
		if (serverprops.getBoolean("RailGunCreatesFire")){
			RailGunCreatesFire = 1;
		} else {
			RailGunCreatesFire = 0;
		}
		
		texTileWidth = props.getInt("texturePackTileWidth");
		texTileHeight = props.getInt("texturePackTileHeight");

		orePlutoniumIndex = ModLoader.addOverride("/terrain.png", "/plasmacraft/terrain/plutonium.png");
		oreRadioniteIndex = ModLoader.addOverride("/terrain.png", "/plasmacraft/terrain/radionite.png");
		oreNeptuniumIndex = ModLoader.addOverride("/terrain.png", "/plasmacraft/terrain/neptunium.png");
		oreObsidiumIndex = ModLoader.addOverride("/terrain.png", "/plasmacraft/terrain/obsidium.png");
		blockUraniumIndex = ModLoader.addOverride("/terrain.png", "/plasmacraft/terrain/blockuranium.png");

        orePlutonium = (new SMBlockPlasmaOre(props.getInt("orePlutoniumID"), orePlutoniumIndex))
        .setHardness(3F)
        .setResistance(5F)
        .setStepSound(Block.soundStoneFootstep)
        .setLightValue(1.0f)
        .setBlockName("orePlutonium");
        
        oreRadionite = (new SMBlockPlasmaOre(props.getInt("oreRadioniteID"), oreRadioniteIndex))
        .setHardness(3F)
        .setResistance(5F)
        .setStepSound(Block.soundStoneFootstep)
        .setLightValue(0.9f)
        .setBlockName("oreRadionite");
        
        oreNeptunium = (new SMBlockPlasmaOre(props.getInt("oreNeptuniumID"), oreNeptuniumIndex))
        .setHardness(3F)
        .setResistance(5F)
        .setStepSound(Block.soundStoneFootstep)
        .setLightValue(0.8f)
        .setBlockName("oreNeptunium");
        
        oreObsidium = (new SMBlockPlasmaOre(props.getInt("oreObsidiumID"), oreObsidiumIndex))
        .setHardness(10F)
        .setResistance(2000F)
        .setStepSound(Block.soundStoneFootstep)
        .setLightValue(0.7f)
        .setBlockName("oreObsidium");
        
        oreUranium = (new SMBlockPlasmaOre(props.getInt("oreUraniumID"), blockUraniumIndex))
		 .setHardness(3.0F)
		 .setLightValue(1.0F)
		 .setLightOpacity(2)
		 .setResistance(5F)
		 .setStepSound(Block.soundStoneFootstep)
		 .setBlockName("oreUranium");
	}
}