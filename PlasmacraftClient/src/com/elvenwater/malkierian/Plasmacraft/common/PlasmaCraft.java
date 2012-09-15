package com.elvenwater.malkierian.Plasmacraft.common;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.MinecraftForge;
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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "Malkierian_PlasmaCraft", name = "PlasmaCraft", version = "0.3.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class PlasmaCraft
{
	public static Block orePlasma;
	public static Block glowCloth;
	
	public static int oreNeptuniumIndex = 8;
	public static int oreObsidiumIndex = 9;
	public static int orePlutoniumIndex = 13;
	public static int oreRadioniteIndex = 14;
	public static int oreUraniumIndex = 4;

	public static final int plutoniumMeta = 0;
	public static final int radioniteMeta = 1;
	public static final int neptuniumMeta = 2;
    public static final int obsidiumMeta = 3; 
    public static final int uraniumMeta = 4;
    
	public static int oreBlockID = 2500;
	public static int glowClothBlockID = 2501;

	public static int acidLakeYCutoff = 48;
	public static int acidSpoutCount = 20;
	public static int acidSpoutYRange = 30;
	public static int acidSpoutYStart = 8;
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
    
    public static final int glowClothAcidMeta = 0;
    public static final int glowClothRadioniteMeta = 1;
    public static final int glowClothNetherflowMeta = 2;
    public static final int glowClothNeptuniumMeta = 3;
    public static final int glowClothUraniumMeta = 4;
    public static final int glowClothPlutoniumMeta = 5;
    public static final int glowClothCryoniteMeta = 6;
    public static final int glowClothObsidiumMeta = 7;
	
	public static boolean generateUranium = true;
	public static boolean generatePlutonium = true;
	
	// The instance of your mod that Forge uses.
	@Instance("PlasmaCraft")
	public static PlasmaCraft instance;
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="com.elvenwater.malkierian.Plasmacraft.client.ClientProxy", serverSide="com.elvenwater.malkierian.Plasmacraft.common.CommonProxy")
	public static CommonProxy proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		// Stub Method
	}
	
	@Init
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		
		registerBlocks();
		
		GameRegistry.registerWorldGenerator(new WorldGenerator());
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
	
	private void registerBlocks()
	{
		orePlasma = new BlockPlasmaOre(oreBlockID, orePlutoniumIndex);
		GameRegistry.registerBlock(orePlasma, com.elvenwater.malkierian.Plasmacraft.common.ItemPlasmaOre.class);
		LanguageRegistry.addName(new ItemStack(orePlasma, 1, plutoniumMeta), 	"Plutonium Ore");
		LanguageRegistry.addName(new ItemStack(orePlasma, 1, radioniteMeta), 	"Radionite Ore");
		LanguageRegistry.addName(new ItemStack(orePlasma, 1, uraniumMeta), 		"Uranium Ore");
		LanguageRegistry.addName(new ItemStack(orePlasma, 1, neptuniumMeta), 	"Neptunium Ore");
		LanguageRegistry.addName(new ItemStack(orePlasma, 1, obsidiumMeta), 	"Obsidium Ore");
		
		MinecraftForge.setBlockHarvestLevel(orePlasma, obsidiumMeta, "pickaxe", 3);
        MinecraftForge.setBlockHarvestLevel(orePlasma, uraniumMeta, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(orePlasma, radioniteMeta, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(orePlasma, plutoniumMeta, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(orePlasma, neptuniumMeta, "pickaxe", 1);
        //MinecraftForge.setBlockHarvestLevel(frozenCryonite, "pickaxe", 1);
        
        glowCloth = new BlockGlowCloth(glowClothBlockID, glowClothAcidIndex);
        GameRegistry.registerBlock(glowCloth, com.elvenwater.malkierian.Plasmacraft.common.ItemGlowCloth.class);
        LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothAcidMeta),	"Acid Glowcloth");
        LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothRadioniteMeta),	"Radionite Glowcloth");
        LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothNetherflowMeta),	"Netherflow Glowcloth");
        LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothNeptuniumMeta),	"Neptunium Glowcloth");
        LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothUraniumMeta),	"Uranium Glowcloth");
        LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothPlutoniumMeta),	"Plutonium Glowcloth");
        LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothCryoniteMeta),	"Cryonite Glowcloth");
        LanguageRegistry.addName(new ItemStack(glowCloth, 1, glowClothObsidiumMeta),	"Obsidium Glowcloth");
	}
}
