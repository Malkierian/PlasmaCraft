package net.minecraft.src;

import net.minecraft.client.Minecraft;
import java.io.File;
import java.util.*;

public class mod_PlasmaCraft extends BaseModMp
{
    public String getVersion()
    {
		return "1.8.1/0.2.3";
	}
    
    public void ModsLoaded()
    {
        ModLoaderMp.Init();
    }

    public void HandlePacket(Packet230ModLoader packet230modloader)
    {
    	
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

	public static void prepareProps()
	{
		props.getInt("LiquidSourceExplodesAfterCausticExplosion", 0);
		
		props.getInt("texturePackTileWidth", 16);
		props.getInt("texturePackTileHeight", 16);

		props.getInt("frozenCryoniteID", 131);
		props.getInt("cryoniteStillID", 130);
		props.getInt("cryoniteFlowingID", 129);
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
		props.getInt("cryoniteVialID", 2093);
		props.getInt("goopCryoniteID", 2094);
		props.getInt("ingotCryoniteID", 2095);
		props.getInt("cryoblasterID", 2096);
		props.getInt("batterycryoID", 2099);
		props.getInt("ThermoPelletID", 2098);

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

    public int AddFuel(int i)
    {
        if(i == mod_PlasmaCraft.netherflowVial.shiftedIndex)
        {
        	return 500000;
        }
        if(i == mod_PlasmaCraft.ThermoPellet.shiftedIndex)
        {
            return 1000000;
        }
        return 0;
    }

	public void RegisterAnimation(Minecraft minecraft)
	{	
		ModLoader.addAnimation(new SMTextureFrameAnimFX(plasmificatorFront, "/plasmacraft/animatedplasmificator.png", texTileWidth, texTileHeight));
		//ModLoader.addAnimation(new SMTextureFrameAnimFX(shockBlockIndex, "/plasmacraft/animatedshock.png", texTileWidth, texTileHeight));
		
		ModLoader.addAnimation(new SMTextureTintedStillFX(cryoniteStillIndex,      0.5f,  0.8f,  1.0f,  1.0f, 128f, 230f, 255f, 146f, 32f, 50f, 36f, 55f, texTileWidth, texTileHeight));
        ModLoader.addAnimation(new SMTextureTintedFlowFX(cryoniteMovingIndex, 	   0.5f,  0.8f,  1.0f,  1.0f, 128f, 230f, 255f, 146f, 32f, 50f, 36f, 55f, texTileWidth, texTileHeight));
		
        ModLoader.addAnimation(new SMTextureTintedStillFX(acidStillIndex,          0.5f,  1.0f,  0.5f,  1.0f, 32f,  255f, 50f,  146f, 32f, 64f, 64f, 50f, texTileWidth, texTileHeight));
        ModLoader.addAnimation(new SMTextureTintedFlowFX(acidMovingIndex, 		   0.5f,  1.0f,  0.5f,  1.0f, 32f,  255f, 50f,  146f, 32f, 64f, 64f, 50f, texTileWidth, texTileHeight));

        ModLoader.addAnimation(new SMTextureTintedStillFX(plutoniumStillIndex, 	   0.5f,  0.9f,  1.0f,  1.0f, 32f,  64f,  64f,  255f, 32f, 64f, 64f, 75f, texTileWidth, texTileHeight));
        ModLoader.addAnimation(new SMTextureTintedFlowFX(plutoniumMovingIndex,     0.5f,  0.9f,  1.0f,  1.0f, 32f,  64f,  64f,  255f, 32f, 64f, 64f, 75f, texTileWidth, texTileHeight));

        ModLoader.addAnimation(new SMTextureTintedStillFX(radioniteStillIndex,     0.9f,  0.4f,  1.0f,  1.0f, 64f,  64f,  64f,  255f, 64f, 32f, 64f, 75f, texTileWidth, texTileHeight));
        ModLoader.addAnimation(new SMTextureTintedFlowFX(radioniteMovingIndex,     0.9f,  0.4f,  1.0f,  1.0f, 64f,  32f,  64f,  255f, 64f, 32f, 64f, 75f, texTileWidth, texTileHeight));

        ModLoader.addAnimation(new SMTextureTintedStillFX(uraniumStillIndex,       0.9f,  0.9f,  0.3f,  1.0f, 255f, 255f, 64f,  160f, 64f, 64f, 32f, 50f, texTileWidth, texTileHeight));
        ModLoader.addAnimation(new SMTextureTintedFlowFX(uraniumMovingIndex,       0.9f,  0.9f,  0.3f,  1.0f, 255f, 255f, 64f,  160f, 64f, 64f, 32f, 50f, texTileWidth, texTileHeight));

        ModLoader.addAnimation(new SMTextureTintedStillFX(neptuniumStillIndex,     1.0f,  0.75f, 0.5f,  1.0f, 255f, 128f, 64f,  180f, 64f, 50f, 36f, 55f, texTileWidth, texTileHeight));
        ModLoader.addAnimation(new SMTextureTintedFlowFX(neptuniumMovingIndex,     1.0f,  0.75f, 0.5f,  1.0f, 255f, 128f, 64f,  180f, 64f, 50f, 36f, 55f, texTileWidth, texTileHeight));

        ModLoader.addAnimation(new SMTextureTintedStillFX(netherflowStillIndex,    1.0f,  0.4f,  0.4f,  1.0f, 255f, 32f,  32f,  200f, 64f, 32f, 32f, 60f, texTileWidth, texTileHeight));
        ModLoader.addAnimation(new SMTextureTintedFlowFX(netherflowMovingIndex,    1.0f,  0.4f,  0.4f,  1.0f, 255f, 32f,  32f,  200f, 64f, 32f, 32f, 60f, texTileWidth, texTileHeight));

        ModLoader.addAnimation(new SMTextureTintedStillFX(obsidiumStillIndex,      0.15f, 0.1f,  0.15f, 1.0f, 72f,  64f,  72f,  220f, 40f, 32f, 40f, 70f, texTileWidth, texTileHeight));
        ModLoader.addAnimation(new SMTextureTintedFlowFX(obsidiumMovingIndex,      0.15f, 0.1f,  0.15f, 1.0f, 72f,  64f,  72f,  220f, 40f, 32f, 40f, 70f, texTileWidth, texTileHeight));

        //ModLoader.addAnimation(new TextureTintedStillFX(milkStillIndex, 0.95f, 0.95f, 0.95f, 1.0f, 224f,  224f, 224f, 255f, 64f, 64f, 64f, 75f, texTileWidth, texTileHeight));
        //ModLoader.addAnimation(new TextureTintedFlowFX(milkMovingIndex, 0.95f, 0.95f, 0.95f, 1.0f, 224f,  224f, 224f, 255f, 64f, 64f, 64f, 75f, texTileWidth, texTileHeight));
	}
	
	public mod_PlasmaCraft()
	{
		AddTextures();
		plasmificatorFront = ModLoader.getUniqueSpriteIndex("/terrain.png");
		//shockBlockIndex = ModLoader.getUniqueSpriteIndex("/terrain.png");
		
		ModLoaderMp.RegisterGUI(this, 159);
		ModLoaderMp.RegisterNetClientHandlerEntity(SMEntityLaser.class, 160);
		ModLoaderMp.RegisterNetClientHandlerEntity(SMEntityLaserShotgun.class, 161);
		ModLoaderMp.RegisterNetClientHandlerEntity(SMEntityPlasma.class, 162);
		ModLoaderMp.RegisterNetClientHandlerEntity(SMEntityRailGun.class, 163);
		ModLoaderMp.RegisterNetClientHandlerEntity(SMEntityAcid.class, 164);
		ModLoaderMp.RegisterNetClientHandlerEntity(SMEntityAcidTNTPrimed.class, 165);
		ModLoaderMp.RegisterNetClientHandlerEntity(SMEntityAcidGrenade.class, 166);
		ModLoaderMp.RegisterNetClientHandlerEntity(SMEntityCryoBlast.class, 167);

		causticID = ModLoader.getUniqueBlockModelID(this, true);
        
		inst1 = this;
    	//caustic = new MaterialLiquid(MapColor.waterColor);

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
		cryoniteStill = (new SMBlockCausticStationary(cryoniteStillBlockID, cryoniteStillIndex, cryoniteMovingIndex, causticID, cryoniteStillBlockID, cryoniteFlowingBlockID)).setHardness(5F).setLightOpacity(3).setBlockName("cryoniteStill");
		cryoniteMoving = (new SMBlockCausticFlowing(cryoniteFlowingBlockID, cryoniteStillIndex, cryoniteMovingIndex, causticID, cryoniteStillBlockID, cryoniteFlowingBlockID)).setHardness(5F).setLightOpacity(3).setBlockName("cryoniteMoving");
		acidStill = (new SMBlockCausticStationary(acidStillBlockID, acidStillIndex, acidMovingIndex, causticID, acidStillBlockID, acidFlowingBlockID)).setHardness(5F).setLightValue(1.0F).setBlockName("acidStill");
        acidMoving = (new SMBlockCausticFlowing(acidFlowingBlockID, acidStillIndex, acidMovingIndex, causticID, acidStillBlockID, acidFlowingBlockID)).setHardness(5F).setLightValue(1.0F).setBlockName("acidMoving");
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

        //shockBlock = (new SMBlockShock(props.getInt("shockBlockID"), shockBlockIndex)
        //.setLightValue(5F)
       // .setResistance(100F)
      //  .setBlockName("shockblock")); // TODO
        
        reinforcedGlass = (new SMBlockReinforcedGlass(props.getInt("reinforcedGlassID"), reinforcedGlassIndex, Material.glass, false))
        .setHardness(1.0F)
        .setResistance(500F)
        .setStepSound(Block.soundGlassFootstep)
        .setBlockName("reinforcedGlass")
        .setTickOnLoad(true);
        
        frozenCryonite = (new SMBlockReinforcedGlass(props.getInt("frozenCryoniteID"), frozenCryoniteIndex, Material.glass, false))
		 .setHardness(1.0F)
		 .setResistance(1F)
		 .setStepSound(Block.soundGlassFootstep)
		 .setBlockName("frozenCryonite")
		 .setTickOnLoad(true);

        plasmificatorIdle = (new SMBlockPlasmificator(props.getInt("plasmificatorIdleID"), plasmificatorSidesIndex,
        																				 plasmificatorFrontIdleIndex,
        																				 plasmificatorFront, false))
        																				 .setHardness(3.0F)
        																				 .setStepSound(Block.soundStoneFootstep)
        																				 .setBlockName("plasmificatorIdle");


        plasmificatorActive = (new SMBlockPlasmificator(props.getInt("plasmificatorActiveID"), plasmificatorSidesIndex,
        																				   	 plasmificatorFrontIdleIndex,
        																				   	 plasmificatorFront, true))
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
    	
    	ThermoPellet = (new Item(props.getInt("ThermoPelletID")))
    	.setIconIndex(ThermoPelletIndex)
    	.setItemName("thermpellet");
    	
    	BatteryCryo = (new Item(props.getInt("BatteryCryoID")))
    	.setIconIndex(BatteryCryoIndex)
    	.setItemName("batteryCryo");
    	
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
    	
    	ingotCryonite = (new Item(props.getInt("ingotCryoniteID")))
    	.setIconIndex(ingotCryoniteIndex)
    	.setItemName("ingotCryonite");

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
    	
    	goopCryonite = (new Item(props.getInt("goopCryoniteID")))
    	.setIconIndex(goopCryoniteIndex)
    	.setItemName("goopCryonite");

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
        
        cryoniteVial = (new SMItemAcidVial(props.getInt("cryoniteVialID"), mod_PlasmaCraft.cryoniteMoving.blockID))
        .setIconIndex(cryoniteVialIndex)
        .setItemName("cryoniteVial")
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

        //int renderIndex = ModLoader.AddArmor("hazmat");
    	helmetHazmat = (new ItemArmor(props.getInt("hazmatHoodID"), 2, ModLoader.AddArmor("hazmat"), 0))
    	.setIconIndex(helmetIndex)
    	.setItemName("helmetHazmat");

    	plateHazmat = (new ItemArmor(props.getInt("hazmatJacketID"), 2, ModLoader.AddArmor("hazmat"), 1))
    	.setIconIndex(plateIndex)
    	.setItemName("plateHazmat");

    	legsHazmat = (new ItemArmor(props.getInt("hazmatPantsID"), 2, ModLoader.AddArmor("hazmat"), 2))
    	.setIconIndex(legsIndex)
    	.setItemName("legsHazmat");

    	bootsHazmat = (new ItemArmor(props.getInt("hazmatBootsID"), 2, ModLoader.AddArmor("hazmat"), 3))
    	.setIconIndex(bootsIndex)
    	.setItemName("bootsHazmat");

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
		//ModLoader.RegisterBlock(shockBlock);

		ModLoader.RegisterEntityID(SMEntityCausticBoat.class, "RadioniteBoat", ModLoader.getUniqueEntityId());
		ModLoader.RegisterEntityID(SMEntityAcidTNTPrimed.class, "AcidTNTPrimed", ModLoader.getUniqueEntityId());
		ModLoader.RegisterEntityID(SMEntityMutantCow.class, "MutantCow", ModLoader.getUniqueEntityId());

		ModLoader.AddName(cryoniteStill, "Cryonite (Still)");
		ModLoader.AddName(cryoniteMoving, "Cryonite (Moving)");
		ModLoader.AddName(acidMoving, "Acid (Moving)");
		ModLoader.AddName(acidStill, "Acid (Still)");
		ModLoader.AddName(plutoniumMoving, "Plutonium (Moving)");
		ModLoader.AddName(plutoniumStill, "Plutonium (Still)");
		ModLoader.AddName(radioniteMoving, "Radionite (Moving)");
		ModLoader.AddName(radioniteStill, "Radionite (Still)");
		ModLoader.AddName(netherflowMoving, "Netherflow (Moving)");
		ModLoader.AddName(netherflowStill, "Netherflow (Still)");
		ModLoader.AddName(neptuniumMoving, "Neptunium (Moving)");
		ModLoader.AddName(neptuniumStill, "Neptunium (Still)");
		ModLoader.AddName(obsidiumMoving, "Obsidium (Moving)");
		ModLoader.AddName(obsidiumStill, "Obsidium (Still)");

		//ModLoader.AddName(shockBlock, "Shock Block");
		ModLoader.AddName(oreUranium, "Uranium Ore");
		ModLoader.AddName(frozenCryonite, "Frozen Cryonite");
		ModLoader.AddName(glowCloth1, "Green Glowcloth");
		ModLoader.AddName(glowCloth2, "Purple Glowcloth");
		ModLoader.AddName(acidGrenade, "Acid Grenade");
		ModLoader.AddName(orePlutonium, "Plutonium Ore");
		ModLoader.AddName(oreRadionite, "Radionite Ore");
		ModLoader.AddName(oreNeptunium, "Neptunium Ore");
		ModLoader.AddName(oreObsidium, "Obsidium Ore");
		ModLoader.AddName(reinforcedGlass, "Reinforced Glass");
		ModLoader.AddName(plasmificatorIdle, "Plasmificator");
		ModLoader.AddName(plasmificatorActive, "Plasmificator");
		ModLoader.AddName(acidHot, "Acidic Barrier");
		ModLoader.AddName(acidTnt, "Acidic TNT");
		ModLoader.AddName(ingotPlutonium, "Plutonium Ingot");
		ModLoader.AddName(ingotRadionite, "Radionite Ingot");
		ModLoader.AddName(energyCell, "Energy Cell");
		ModLoader.AddName(beamSplitter, "Rifle Beam Splitter");
		ModLoader.AddName(BatteryEmpty, "Caustic Battery: Empty");
		ModLoader.AddName(ThermoPellet, "Thermonuclear Pellet");
		ModLoader.AddName(BatteryCryo, "Cryo Battery");
		ModLoader.AddName(BatteryCharged, "Caustic Battery: Charged");
		ModLoader.AddName(BatteryOvercharged, "Caustic Battery: Overcharged");
		ModLoader.AddName(BatteryPlasma, "Caustic Battery: Plasma");
		ModLoader.AddName(ingotNeptunium, "Neptunium Ingot");
		ModLoader.AddName(ingotObsidium, "Obsidium Ingot");
		ModLoader.AddName(ingotCryonite, "Cryonite Ingot");
		ModLoader.AddName(ingotUranium, "Uranium Ingot");
		ModLoader.AddName(ingotNetherflow, "Netherflow Ingot");
		ModLoader.AddName(goopPlutonium, "Plutonium Goop");
		ModLoader.AddName(goopRadionite, "Radionite Goop");
		ModLoader.AddName(goopNeptunium, "Neptunium Goop");
		ModLoader.AddName(goopNetherflow, "Netherflow Goop");
		ModLoader.AddName(goopObsidium, "Obsidium Goop");
		ModLoader.AddName(goopCryonite, "Cryonite Goop");
		ModLoader.AddName(goopUranium, "Uranium Goop");
		ModLoader.AddName(acidVial, "Empty Vial");
		ModLoader.AddName(fullAcidVial, "Acid Vial");
		ModLoader.AddName(plutoniumVial, "Plutonium Vial");
		ModLoader.AddName(radioniteVial, "Radionite Vial");
		ModLoader.AddName(uraniumVial, "Uranium Vial");
		ModLoader.AddName(neptuniumVial, "Neptunium Vial");
		ModLoader.AddName(netherflowVial, "Netherflow Vial");
		ModLoader.AddName(obsidiumVial, "Obsidium Vial");
		ModLoader.AddName(cryoniteVial, "Cryonite Vial");
		//ModLoader.AddName(milkVial, "Milk Vial");
		ModLoader.AddName(plasmaGel, "Plasma Goop");
		ModLoader.AddName(plasmaLeather, "Plasma-Coated Leather");
		ModLoader.AddName(plasma, "Plasma");
		ModLoader.AddName(lasergun, "Laser Rifle");
		ModLoader.AddName(plasmagun, "Plasma Rifle");
		ModLoader.AddName(plasmagunsplit, "Plasma Rifle + Beam Splitter");
		ModLoader.AddName(lasergunsplit, "Laser Rifle + Beam Splitter");
		ModLoader.AddName(acidgun, "Acid Launcher");
		ModLoader.AddName(railgun, "Rail Gun");
		ModLoader.AddName(cryoblaster, "Cryo Blaster");
		ModLoader.AddName(lasershotgun, "Laser Shotgun");
		ModLoader.AddName(causticBoat, "Radionite Boat");
		ModLoader.AddName(helmetHazmat, "Hazmat Hood");
		ModLoader.AddName(plateHazmat, "Hazmat Jacket");
		ModLoader.AddName(legsHazmat, "Hazmat Pants");
		ModLoader.AddName(bootsHazmat, "Hazmat Boots");

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
        //for(int i = 0; i < 256; i++)
        //{
        //    if(Block.blocksList[i] != null && Item.itemsList[i] != null && Item.itemsList[i] instanceof ItemBlock &&
        //    	!Item.itemsList[i].getHasSubtypes())
        //    {
        //        Item.itemsList[i] = new SMItemCausticSupportBlock(i - 256);
        //    }
        //}
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
		
        ModLoader.AddRecipe(new ItemStack(acidGrenade, 4), new Object[] {
            "X", "Y", "Z",
            Character.valueOf('X'), Item.ingotIron,
            Character.valueOf('Y'), fullAcidVial,
            Character.valueOf('Z'), plasma
        });

        ModLoader.AddRecipe(new ItemStack(acidGrenade, 4), new Object[] {
            "D", "C", "D",
            Character.valueOf('D'), Block.planks,
            Character.valueOf('C'), Block.dirt,
        });

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
        
        ModLoader.AddRecipe(new ItemStack(ThermoPellet, 1), new Object[] {
            "III", 
            "IXI",
            "III",
            Character.valueOf('X'), ingotPlutonium,
            Character.valueOf('I'), goopUranium
        });
        
        ModLoader.AddRecipe(new ItemStack(BatteryCryo, 1), new Object[] {
            "R", 
            "X",
            Character.valueOf('R'), goopCryonite,
            Character.valueOf('X'), BatteryEmpty
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
        
        ModLoader.AddRecipe(new ItemStack(cryoniteVial, 1), new Object[] {
            "Q", "J",
            Character.valueOf('Q'), goopCryonite,
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

		if(props.getInt("acidTntID") != 0)
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
	
	public static void AddTextures()
	{
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

		frozenCryoniteIndex = ModLoader.addOverride("/terrain.png", "/plasmacraft/terrain/frozenCryonite.png");
		
		cryoniteStillIndex = ModLoader.getUniqueSpriteIndex("/terrain.png");
		cryoniteMovingIndex = ModLoader.getUniqueSpriteIndex("/terrain.png");
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
		ingotCryoniteIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/cryonite_ingot.png");
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
		cryoniteVialIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/cryonite_vial.png");
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
		goopCryoniteIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/cryonite_goop.png");
		goopUraniumIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/uranium_goop.png");
		lasergunIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/lasergun.png");
		plasmagunIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/plasmagun.png");
		plasmagunsplitIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/plasmagunsplit.png");
		lasergunsplitIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/lasergunsplit.png");
		acidgunIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/acidgun.png");
		railgunIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/railgun.png");
		cryoblasterIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/cryoblaster.png");
		lasershotgunIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/lasershotgun.png");
		energyCellIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/energycell.png");
		BatteryEmptyIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/emptybat.png");
		ThermoPelletIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/thermopellet.png");
		BatteryCryoIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/cryobat.png");
		BatteryChargedIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/chargedbat.png");
		BatteryOverchargedIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/overchargedbat.png");
		BatteryPlasmaIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/plasmabat.png");
		beamSplitterIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/items/beamsplit.png");
		helmetIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/hazmat/hazmat_helmet.png");
		plateIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/hazmat/hazmat_jacket.png");
		legsIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/hazmat/hazmat_pants.png");
		bootsIndex = ModLoader.addOverride("/gui/items.png", "/plasmacraft/hazmat/hazmat_boots.png");
	}
	
    private float func_1224_a(IBlockAccess blockAccess, int i, int j, int k)
    {
        int l = 0;
        float f = 0.0F;
        for(int i1 = 0; i1 < 4; i1++)
        {
            int j1 = i - (i1 & 1);
            int k1 = j;
            int l1 = k - (i1 >> 1 & 1);
            if(blockAccess.getBlockMaterial(j1, k1 + 1, l1) == Material.water)
            {
                return 1.0F;
            }
            Material material1 = blockAccess.getBlockMaterial(j1, k1, l1);
            if(material1 == Material.water)
            {
                int i2 = blockAccess.getBlockMetadata(j1, k1, l1);
                if(i2 >= 8 || i2 == 0)
                {
                    f += BlockFluid.getFluidHeightPercent(i2) * 10F;
                    l += 10;
                }
                f += BlockFluid.getFluidHeightPercent(i2);
                l++;
            } else
            if(!material1.isSolid())
            {
                f++;
                l++;
            }
        }

        return 1.0F - f / (float)l;
    }

    public void renderBottomFace(Block block, double d, double d1, double d2, int i)
    {
        Tessellator tessellator = Tessellator.instance;
        int j = (i & 0xf) << 4;
        int k = i & 0xf0;
        double d3 = ((double)j + block.minX * 16D) / 256D;
        double d4 = (((double)j + block.maxX * 16D) - 0.01D) / 256D;
        double d5 = ((double)k + block.minZ * 16D) / 256D;
        double d6 = (((double)k + block.maxZ * 16D) - 0.01D) / 256D;
        if(block.minX < 0.0D || block.maxX > 1.0D)
        {
            d3 = ((float)j + 0.0F) / 256F;
            d4 = ((float)j + 15.99F) / 256F;
        }
        if(block.minZ < 0.0D || block.maxZ > 1.0D)
        {
            d5 = ((float)k + 0.0F) / 256F;
            d6 = ((float)k + 15.99F) / 256F;
        }
        double d7 = d + block.minX;
        double d8 = d + block.maxX;
        double d9 = d1 + block.minY;
        double d10 = d2 + block.minZ;
        double d11 = d2 + block.maxZ;
		tessellator.addVertexWithUV(d7, d9, d11, d3, d6);
		tessellator.addVertexWithUV(d7, d9, d10, d3, d5);
		tessellator.addVertexWithUV(d8, d9, d10, d4, d5);
		tessellator.addVertexWithUV(d8, d9, d11, d4, d6);
    }

    public boolean renderBlockCaustics(IBlockAccess blockAccess, Block block, int i, int j, int k)
    {
        Tessellator tessellator = Tessellator.instance;
        boolean flag = block.shouldSideBeRendered(blockAccess, i, j + 1, k, 1);
        boolean flag1 = block.shouldSideBeRendered(blockAccess, i, j - 1, k, 0);
        boolean aflag[] = new boolean[4];
        aflag[0] = block.shouldSideBeRendered(blockAccess, i, j, k - 1, 2);
        aflag[1] = block.shouldSideBeRendered(blockAccess, i, j, k + 1, 3);
        aflag[2] = block.shouldSideBeRendered(blockAccess, i - 1, j, k, 4);
        aflag[3] = block.shouldSideBeRendered(blockAccess, i + 1, j, k, 5);
        if(!flag && !flag1 && !aflag[0] && !aflag[1] && !aflag[2] && !aflag[3])
        {
            return false;
        }
        boolean flag2 = false;
        float f = 0.5F;
        float f1 = 1.0F;
        float f2 = 0.8F;
        float f3 = 0.6F;
        double d = 0.0D;
        double d1 = 1.0D;
        Material material = block.blockMaterial;
        int l = blockAccess.getBlockMetadata(i, j, k);
        float f4 = func_1224_a(blockAccess, i, j, k);
        float f5 = func_1224_a(blockAccess, i, j, k + 1);
        float f6 = func_1224_a(blockAccess, i + 1, j, k + 1);
        float f7 = func_1224_a(blockAccess, i + 1, j, k);
        if(flag)
        {
            flag2 = true;
            int i1 = block.getBlockTextureFromSideAndMetadata(1, l);
            float f9 = (float)((SMBlockCausticFluids)block).func_293_a(blockAccess, i, j, k, material);
            if(f9 > -999F)
            {
                i1 = block.getBlockTextureFromSideAndMetadata(2, l);
            }
            int l1 = (i1 & 0xf) << 4;
            int j2 = i1 & 0xf0;
            double d2 = (double)(l1 + 8) / 256D;
            double d3 = (double)(j2 + 8) / 256D;
            if(f9 < -999F)
            {
                f9 = 0.0F;
            } else
            {
                d2 = ((float)l1 + 16F) / 256F;
                d3 = ((float)j2 + 16F) / 256F;
                d2 -= 8F / 256F;
                d3 -= 8F / 256F;
            }
            float f11 = (MathHelper.sin(f9) * 4F) / 256F;
            float f13 = (MathHelper.cos(f9) * 4F) / 256F;
            float f15 = block.getBlockBrightness(blockAccess, i, j, k);
            tessellator.setColorOpaque_F(f1 * f15, f1 * f15, f1 * f15);
            tessellator.addVertexWithUV(i + 0, (float)j + f4, k + 0, d2 - (double)f13 - (double)f11, (d3 - (double)f13) + (double)f11);
            tessellator.addVertexWithUV(i + 0, (float)j + f5, k + 1, (d2 - (double)f13) + (double)f11, d3 + (double)f13 + (double)f11);
            tessellator.addVertexWithUV(i + 1, (float)j + f6, k + 1, d2 + (double)f13 + (double)f11, (d3 + (double)f13) - (double)f11);
            tessellator.addVertexWithUV(i + 1, (float)j + f7, k + 0, (d2 + (double)f13) - (double)f11, d3 - (double)f13 - (double)f11);
        }
        if(flag1)
        {
            float f8 = block.getBlockBrightness(blockAccess, i, j - 1, k);
            tessellator.setColorOpaque_F(f * f8, f * f8, f * f8);
            renderBottomFace(block, i, j, k, block.getBlockTextureFromSide(0));
            flag2 = true;
        }
        for(int j1 = 0; j1 < 4; j1++)
        {
            int k1 = i;
            int i2 = j;
            int k2 = k;
            if(j1 == 0)
            {
                k2--;
            }
            if(j1 == 1)
            {
                k2++;
            }
            if(j1 == 2)
            {
                k1--;
            }
            if(j1 == 3)
            {
                k1++;
            }
            int l2 = block.getBlockTextureFromSideAndMetadata(j1 + 2, l);
            int i3 = (l2 & 0xf) << 4;
            int j3 = l2 & 0xf0;
            if(aflag[j1])
            {
				float f10;
				float f12;
				float f14;
				float f16;
				float f17;
				float f18;
				if(j1 == 0)
				{
					f10 = f4;
					f12 = f7;
					f14 = i;
					f17 = i + 1;
					f16 = k;
					f18 = k;
				} else
				if(j1 == 1)
				{
					f10 = f6;
					f12 = f5;
					f14 = i + 1;
					f17 = i;
					f16 = k + 1;
					f18 = k + 1;
				} else
				if(j1 == 2)
				{
					f10 = f5;
					f12 = f4;
					f14 = i;
					f17 = i;
					f16 = k + 1;
					f18 = k;
				} else
				{
					f10 = f7;
					f12 = f6;
					f14 = i + 1;
					f17 = i + 1;
					f16 = k;
					f18 = k + 1;
				}
				flag2 = true;
        		double widthSlop = 16D / (double)texTileWidth;
        		double heightSlop = 16D / (double)texTileHeight;
        		widthSlop *= 0.01D;
        		heightSlop *= 0.01D;
				double d4 = (double)i3 / 256D;
				double d5 = (((double)i3 + 16D) - widthSlop) / 256D;
				double d6 = ((float)j3 + (1.0F - f10) * 16F) / 256F;
				double d7 = ((float)j3 + (1.0F - f12) * 16F) / 256F;
				double d8 = (((double)j3 + 16D) - heightSlop) / 256F;
				float f19 = block.getBlockBrightness(blockAccess, k1, i2, k2);
				if(j1 < 2)
				{
					f19 *= f2;
				} else
				{
					f19 *= f3;
				}
				tessellator.setColorOpaque_F(f1 * f19, f1 * f19, f1 * f19);
				tessellator.addVertexWithUV(f14, (float)j + f10, f16, d4, d6);
				tessellator.addVertexWithUV(f17, (float)j + f12, f18, d5, d7);
				tessellator.addVertexWithUV(f17, j + 0, f18, d5, d8);
				tessellator.addVertexWithUV(f14, j + 0, f16, d4, d8);
			}
        }

        block.minY = d;
        block.maxY = d1;
        return flag2;
    }

    public boolean RenderWorldBlock(RenderBlocks renderblocks, IBlockAccess iblockaccess, int i, int j, int k, Block block, int l)
    {
        if(l == acidStill.getRenderType())
        {
            return renderBlockCaustics(iblockaccess, block, i, j, k);
        }
        return false;
    }
    	
	public static void pressKey(int key)
	{
	    ModLoaderMp.SendKey(inst1, key);
	}
	
	
	
    /*public GuiScreen HandleGUI(int inventoryType) 
    {
            if(inventoryType == 159)
                    return new SMGuiPlasmaBench(ModLoader.getMinecraftInstance().thePlayer.inventory, 
                    		(SMTileEntityPlasmaBench)ModLoader.getMinecraftInstance().theWorld.getBlockTileEntity(
                    		(int)ModLoader.getMinecraftInstance().thePlayer.posX, 
                    		(int)ModLoader.getMinecraftInstance().thePlayer.posY, 
                    		(int)ModLoader.getMinecraftInstance().thePlayer.posZ)) ;
            else return null;
    }*/
	
	public GuiScreen HandleGUI(int inventoryType) 
    {
		EntityPlayer entityplayer = ModLoader.getMinecraftInstance().thePlayer;
        if(inventoryType == 159)
            return new SMGuiPlasmaBench(ModLoader.getMinecraftInstance().thePlayer.inventory, (new SMTileEntityPlasmaBench())) ;
    else return null;
    }
    
    /*public GuiScreen OpenModGUI(EntityPlayer player, Object tileEntity)
    {
		if(tileEntity instanceof SMTileEntityPlasmaBench)
		{
			return new SMGuiPlasmaBench(player.inventory, (SMTileEntityPlasmaBench)tileEntity);
		} else {
			return null;
		}
	}*/
	
	/*public GuiScreen HandleGUI(int inventoryType, Object tileEntity) 
	{
		if(inventoryType == 35)
		{
			return new SMGuiPlasmaBench(ModLoader.getMinecraftInstance().thePlayer.inventory, (SMTileEntityPlasmaBench)tileEntity);
		}
		else return null;
	}*/

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
            int y = random.nextInt(random.nextInt(10) + 60);
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
        
        if(random.nextInt(acidLakeChance) == 0)
        {
            int x = chunkX + random.nextInt(16) + 8;
            int y = random.nextInt(random.nextInt(10) + 60);
            int z = chunkZ + random.nextInt(16) + 8;
            BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(x, z);
            if (biome == BiomeGenBase.taiga | biome == BiomeGenBase.taiga | biome == BiomeGenBase.hills)
            {
            	(new SMWorldGenFrozenCryonite(frozenCryonite.blockID)).generate(world, random, x, y, z);
            }
        }
        

	}

    public void AddRenderer(Map map)
    {
        map.put(SMEntityAcidTNTPrimed.class, new SMRenderAcidTNTPrimed());
        map.put(SMEntityCausticBoat.class, new SMRenderCausticBoat());
        map.put(SMEntityAcidGrenade.class, new SMRenderAcidGrenade(acidGrenade.iconIndex));
        map.put(SMEntityPlasma.class, new SMRenderPlasma());
        map.put(SMEntityLaser.class, new SMRenderLaser());
        map.put(SMEntityRailGun.class, new SMRenderRailGun());
        map.put(SMEntityLaserShotgun.class, new SMRenderLaserShotgun());
        map.put(SMEntityAcid.class, new SMRenderAcid());
        //map.put(SMEntityMutantCow.class,new SMRenderMutantCow(new SMModelMutantCow(), 5));
        map.put(SMEntityCryoBlast.class, new SMRenderCryoBlast());
    }
	
    public void OSDHook(Minecraft minecraft, boolean flag)
    {
    }
     
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

    public static final Block orePlutonium;
    public static final Block oreRadionite;
    public static final Block oreNeptunium;
    public static final Block oreObsidium;
    public static final Block oreUranium;
    public static Block frozenCryonite;
    public static Block reinforcedGlass;
    public static Block plasmificatorIdle;
    public static Block plasmificatorActive;
    public static Block acidHot;
    public static Block acidTnt;

    //public static Block shockBlock;

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
    public static Item cryoblaster;
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
	public static int frozenCryoniteIndex;

	public static int gasIndex;
	public static int cryoniteStillIndex;
	public static int cryoniteMovingIndex;
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
	public static int ThermoPelletIndex;
	public static int BatteryCryoIndex;
	public static int BatteryChargedIndex;
	public static int BatteryOverchargedIndex;
	public static int BatteryPlasmaIndex;
	public static int ingotNeptuniumIndex;
	public static int ingotObsidiumIndex;
	public static int ingotCryoniteIndex;
	public static int ingotUraniumIndex;
	public static int ingotNetherflowIndex;
	public static int goopPlutoniumIndex;
	public static int goopRadioniteIndex;
	public static int goopNeptuniumIndex;
	public static int goopNetherflowIndex;
	public static int goopObsidiumIndex;
	public static int goopCryoniteIndex;
	public static int goopUraniumIndex;
    public static int acidGrenadeIndex;
    public static int lasergunIndex;
    public static int plasmagunIndex;
    public static int plasmagunsplitIndex;
    public static int lasergunsplitIndex;
    public static int acidgunIndex;
    public static int railgunIndex;
    public static int cryoblasterIndex;
    public static int lasershotgunIndex;
    
    //public static int shockBlockIndex;
    
	public static int acidVialIndex;
	public static int fullAcidVialIndex;
	public static int plutoniumVialIndex;
	public static int radioniteVialIndex;
	public static int uraniumVialIndex;
	public static int neptuniumVialIndex;
	public static int netherflowVialIndex;
	public static int obsidiumVialIndex;
	public static int cryoniteVialIndex;
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

	private static SMPlasmaProps props;

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

	private static int plutoniumOreYStart;
	private static int plutoniumOreYRange;
	private static int plutoniumOreVeinCount;
	private static int plutoniumOreVeinSize;
	
	private static int uraniumOreYStart;
	private static int uraniumOreYRange;
	private static int uraniumOreVeinCount;
	private static int uraniumOreVeinSize;
	
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
    public static int LiquidSourceExplodesAfterCausticExplosion;
    
    static mod_PlasmaCraft inst1;
    
	static
	{
		props = new SMPlasmaProps(new File(Minecraft.getMinecraftDir() + "/" + "mod_PlasmaCraft.props").getPath());
		prepareProps();
		
		texTileWidth = props.getInt("texturePackTileWidth");
		texTileHeight = props.getInt("texturePackTileHeight");

		if (props.getInt("LiquidSourceExplodesAfterCausticExplosion") == 1)
		{
			LiquidSourceExplodesAfterCausticExplosion = 1;
		}else {
			LiquidSourceExplodesAfterCausticExplosion = 0;
		}
		
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