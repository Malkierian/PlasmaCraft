package malkierian.plasmacraft;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import malkierian.plasmacraft.client.gui.PlasmaTab;
import malkierian.plasmacraft.common.FuelHandler;
import malkierian.plasmacraft.config.PlasmaCraftConfig;
import malkierian.plasmacraft.entity.EntityAcid;
import malkierian.plasmacraft.init.OverlayHandler;
import malkierian.plasmacraft.init.PCBlocks;
import malkierian.plasmacraft.init.PCFluids;
import malkierian.plasmacraft.init.PCItems;
import malkierian.plasmacraft.items.ItemGoop;
import malkierian.plasmacraft.items.ItemIngot;
import malkierian.plasmacraft.items.ItemVial;
import malkierian.plasmacraft.proxy.CommonProxy;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

@Mod(modid = PlasmaCraft.modId, name = PlasmaCraft.name, version = PlasmaCraft.version, acceptedMinecraftVersions = "[1.10.2]")
public class PlasmaCraft
{
	public static PlasmaTab plasmaTab = new PlasmaTab("PlasmaCraft");
    public static PlasmaCraftConfig config;
    public static final String modId = "plasmacraft";
    public static final String name = "PlasmaCraft";
    public static final String version = "0.4.0";

    public static Comparator<ItemStack> tabSorter;
	
	// The instance of your mod that Forge uses.
	@Instance(PlasmaCraft.modId)
	public static PlasmaCraft instance;
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="malkierian.plasmacraft.proxy.ClientProxy", serverSide="malkierian.plasmacraft.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
        loadConfig(event);

        PCFluids.initFluids();
        proxy.preInit();
        registerFuel();
        PCItems.init();
        PCBlocks.init();
		MinecraftForge.EVENT_BUS.register(new OverlayHandler());
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
//		proxy.registerRenderers();
//		
		List<Item> order = Arrays.asList(Item.getItemFromBlock(PCBlocks.glowClothAcid), Item.getItemFromBlock(PCBlocks.glowClothCryonite),
			Item.getItemFromBlock(PCBlocks.glowClothNeptunium), Item.getItemFromBlock(PCBlocks.glowClothNetherflow),
			Item.getItemFromBlock(PCBlocks.glowClothObsidium), Item.getItemFromBlock(PCBlocks.glowClothPlutonium),
			Item.getItemFromBlock(PCBlocks.glowClothRadionite), Item.getItemFromBlock(PCBlocks.glowClothUranium),
			Item.getItemFromBlock(PCBlocks.oreLead), Item.getItemFromBlock(PCBlocks.oreNeptunium), Item.getItemFromBlock(PCBlocks.oreObsidium),
			Item.getItemFromBlock(PCBlocks.orePlutonium), Item.getItemFromBlock(PCBlocks.oreRadionite), Item.getItemFromBlock(PCBlocks.oreUranium),
			Item.getItemFromBlock(PCBlocks.frozenCryonite), Item.getItemFromBlock(PCBlocks.reinforcedGlass),
			/*Item.getItemFromBlock(PCBlocks.acidTnt),*/ Item.getItemFromBlock(PCBlocks.acidBarrier),
			PCItems.goop, PCItems.ingots, PCItems.vial /*PCItems.causticBoat,
			PCItems.battery, PCItems.beamSplitter, PCItems.energyCell*/, PCItems.thermopellet,
			PCItems.acidgun/*, PCItems.cryoblaster, PCItems.lasershotgun, PCItems.lasergun, PCItems.lasergunsplit, PCItems.plasmagun, PCItems.plasmagunsplit, PCItems.railgun,
			PCItems.acidGrenade, PCItems.hazmatBoots, PCItems.hazmatHood, PCItems.hazmatJacket, PCItems.hazmatPants, PCItems.plasmaLeather*/);

		tabSorter = Ordering.explicit(order).onResultOf(new Function<ItemStack, Item>() {
			@Override
			public Item apply(ItemStack input)
			{
				return input.getItem();
			}
		});
//
        registerRecipes();
		registerOres();
        registerEntities();
//
//        proxy.registerTextureFX();
//		
//		GameRegistry.registerWorldGenerator(new WorldGenerator(), 20);
	}

    private void registerFluids()
    {
//        FluidRegistry.registerFluid(PlasmaCraft.fluids.acidFluid);
//        FluidRegistry.registerFluid(PlasmaCraft.fluids.cryoniteFluid);
//        FluidRegistry.registerFluid(PlasmaCraft.fluids.neptuniumFluid);
//        FluidRegistry.registerFluid(PlasmaCraft.fluids.netherflowFluid);
//        FluidRegistry.registerFluid(PlasmaCraft.fluids.obsidiumFluid);
//        FluidRegistry.registerFluid(PlasmaCraft.fluids.plutoniumFluid);
//        FluidRegistry.registerFluid(PlasmaCraft.fluids.radioniteFluid);
//        FluidRegistry.registerFluid(PlasmaCraft.fluids.uraniumFluid);
    }
	
	private void registerBlocks()
	{
//        GameRegistry.registerBlock(blocks.orePlasma, ItemPlasmaOre.class, "orePlasma");
//		GameRegistry.registerBlock(blocks.acidBlock, "Acid");
//		GameRegistry.registerBlock(blocks.cryoniteBlock, "Cryonite");
//		GameRegistry.registerBlock(blocks.neptuniumBlock, "Neptunium");
//		GameRegistry.registerBlock(blocks.netherflowBlock, "Netherflow");
//		GameRegistry.registerBlock(blocks.obsidiumBlock, "Obsidium");
//		GameRegistry.registerBlock(blocks.plutoniumBlock, "Plutonium");
//		GameRegistry.registerBlock(blocks.radioniteBlock, "Radionite");
//		GameRegistry.registerBlock(blocks.uraniumBlock, "Uranium");
//		GameRegistry.registerBlock(blocks.glowCloth, ItemGlowCloth.class, "glowCloth");
//		GameRegistry.registerBlock(blocks.frozenCryonite, "Frozen Cryonite");
//		GameRegistry.registerBlock(blocks.reinforcedGlass, "Reinforced Glass");
//		GameRegistry.registerBlock(blocks.acidBarrier, "Acid Barrier");
//		GameRegistry.registerBlock(blocks.acidTnt, "Acid TNT");
	}
	
	private void registerEntities()
	{
		EntityRegistry.registerModEntity(EntityAcid.class, "acid", 0, this, 32, 100, true);
//		int entityID = EntityRegistry.findGlobalUniqueEntityId();
//		EntityRegistry.registerGlobalEntityID(EntityCausticBoat.class, "causticBoat", entityID);
//		EntityRegistry.registerModEntity(EntityCausticBoat.class, "causticBoat", entityID, this, 32, 100, true);
//		
//		entityID = EntityRegistry.findGlobalUniqueEntityId();
//		EntityRegistry.registerGlobalEntityID(EntityAcidTNTPrimed.class, "acidTntPrimed", entityID);
//		EntityRegistry.registerModEntity(EntityAcidTNTPrimed.class, "acidTntPrimed", entityID, this, 32, 100, false);
//		
//		entityID = EntityRegistry.findGlobalUniqueEntityId();
//		EntityRegistry.registerGlobalEntityID(EntityAcidGrenade.class, "acidGrenade", entityID);
//		EntityRegistry.registerModEntity(EntityAcidGrenade.class, "acidGrenade", entityID, this, 32, 100, true);
//		
//		entityID = EntityRegistry.findGlobalUniqueEntityId();
//		EntityRegistry.registerGlobalEntityID(EntityLaser.class, "laser", entityID);
//		EntityRegistry.registerModEntity(EntityLaser.class, "laser", entityID, this, 32, 100, true);
//		
//		entityID = EntityRegistry.findGlobalUniqueEntityId();
//		EntityRegistry.registerGlobalEntityID(EntityLaserShotgun.class, "laserShotgun", entityID);
//		EntityRegistry.registerModEntity(EntityLaserShotgun.class, "laserShotgun", entityID, this, 32, 100, true);
//		
//		entityID = EntityRegistry.findGlobalUniqueEntityId();
//		EntityRegistry.registerGlobalEntityID(EntityPlasma.class, "ingots", entityID);
//		EntityRegistry.registerModEntity(EntityPlasma.class, "ingots", entityID, this, 32, 100, true);
//		
//		entityID = EntityRegistry.findGlobalUniqueEntityId();
//		EntityRegistry.registerGlobalEntityID(EntityRailGun.class, "railGun", entityID);
//		EntityRegistry.registerModEntity(EntityRailGun.class, "railGun", entityID, this, 32, 100, true);
//		
//		
//		entityID = EntityRegistry.findGlobalUniqueEntityId();
//		EntityRegistry.registerGlobalEntityID(EntityCryoBlast.class, "cryoBlast", entityID);
//		EntityRegistry.registerModEntity(EntityCryoBlast.class, "cryoBlast", entityID, this, 32, 100, true);
	}
	
	private void registerFuel()
	{
		GameRegistry.registerFuelHandler(new FuelHandler());
	}
	
	private void registerItems()
	{
//		PCItems.(PCItems.vial, "pc-vial");
//		GameRegistry.registerItem(PCItems.battery, "pc-battery");
//
//        GameRegistry.registerItem(PCItems.causticBoat, "Caustic Boat");
//
//		GameRegistry.registerItem(PCItems.beamSplitter, "Beam Splitter");
//		GameRegistry.registerItem(PCItems.energyCell, "Energy Cell");
//
//		GameRegistry.registerItem(PCItems.cryoblaster, "Cryo Blaster");
//		GameRegistry.registerItem(PCItems.lasershotgun, "Laser Shotgun");
//		GameRegistry.registerItem(PCItems.lasergun, "Laser Rifle");
//		GameRegistry.registerItem(PCItems.lasergunsplit, "Split Beam Laser Rifle");
//		GameRegistry.registerItem(PCItems.plasmagun, "Plasma Rifle");
//		GameRegistry.registerItem(PCItems.plasmagunsplit, "Split Beam Plasma Rifle");
//		GameRegistry.registerItem(PCItems.railgun, "Railgun");
//
//		GameRegistry.registerItem(PCItems.acidGrenade, "Acid Grenade");
//
//		GameRegistry.registerItem(PCItems.plasmaLeather, "Plasma Leather");
//
//		GameRegistry.registerItem(PCItems.hazmatBoots, "Hazmat Boots");
//		GameRegistry.registerItem(PCItems.hazmatHood, "Hazmat Hood");
//		GameRegistry.registerItem(PCItems.hazmatJacket, "Hazmat Jacket");
//		GameRegistry.registerItem(PCItems.hazmatPants, "Hazmat Pants");
	}
	
	private void registerOres()
	{
		OreDictionary.registerOre("orePlutonium", new ItemStack(PCBlocks.orePlutonium));
		OreDictionary.registerOre("oreUranium", new ItemStack(PCBlocks.oreUranium));
		OreDictionary.registerOre("oreLead", new ItemStack(PCBlocks.oreLead));
		OreDictionary.registerOre("oreObsidium", new ItemStack(PCBlocks.oreObsidium));
		OreDictionary.registerOre("oreRadionite", new ItemStack(PCBlocks.oreRadionite));
		OreDictionary.registerOre("oreNeptunium", new ItemStack(PCBlocks.oreNeptunium));
		
		OreDictionary.registerOre("ingotPlutonium", new ItemStack(PCItems.ingots, 1, ItemIngot.PLUTONIUM_DAMAGE));
		OreDictionary.registerOre("ingotUranium", new ItemStack(PCItems.ingots, 1, ItemIngot.URANIUM_DAMAGE));
		OreDictionary.registerOre("ingotLead", new ItemStack(PCItems.ingots, 1, ItemIngot.LEAD_DAMAGE));
		OreDictionary.registerOre("ingotCryonite", new ItemStack(PCItems.ingots, 1, ItemIngot.CRYONITE_DAMAGE));
		OreDictionary.registerOre("ingotNeptunium", new ItemStack(PCItems.ingots, 1, ItemIngot.NEPTUNIUM_DAMAGE));
		OreDictionary.registerOre("ingotNetherflow", new ItemStack(PCItems.ingots, 1, ItemIngot.NETHERFLOW_DAMAGE));
		OreDictionary.registerOre("ingotObsidium", new ItemStack(PCItems.ingots, 1, ItemIngot.OBSIDIUM_DAMAGE));
		OreDictionary.registerOre("ingotRadionite", new ItemStack(PCItems.ingots, 1, ItemIngot.RADIONITE_DAMAGE));
	}
	
	private void registerRecipes()
	{
		GameRegistry.addShapelessRecipe(new ItemStack(PCBlocks.glowClothAcid, 1), new ItemStack(PCItems.goop, 1, ItemGoop.ACID_DAMAGE), new ItemStack(Blocks.WOOL, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(PCBlocks.glowClothCryonite, 1), new ItemStack(PCItems.goop, 1, ItemGoop.CRYONITE_DAMAGE), new ItemStack(Blocks.WOOL, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(PCBlocks.glowClothNeptunium, 1), new ItemStack(PCItems.goop, 1, ItemGoop.NEPTUNIUM_DAMAGE), new ItemStack(Blocks.WOOL, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(PCBlocks.glowClothNetherflow, 1), new ItemStack(PCItems.goop, 1, ItemGoop.NETHERFLOW_DAMAGE), new ItemStack(Blocks.WOOL, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(PCBlocks.glowClothObsidium, 1), new ItemStack(PCItems.goop, 1, ItemGoop.OBSIDIUM_DAMAGE), new ItemStack(Blocks.WOOL, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(PCBlocks.glowClothPlutonium, 1), new ItemStack(PCItems.goop, 1, ItemGoop.PLUTONIUM_DAMAGE), new ItemStack(Blocks.WOOL, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(PCBlocks.glowClothRadionite, 1), new ItemStack(PCItems.goop, 1, ItemGoop.RADIONITE_DAMAGE), new ItemStack(Blocks.WOOL, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(PCBlocks.glowClothUranium, 1), new ItemStack(PCItems.goop, 1, ItemGoop.URANIUM_DAMAGE), new ItemStack(Blocks.WOOL, 1, 0));
//		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PCBlocks.reinforcedGlass, 4), " X ", "X#X", " X ", '#', Blocks.GLASS, 'X', "ingotLead"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PCItems.vial, 4), "X#X", "Y Y", "X#X", '#', "ingotLead", 'Y', PCBlocks.reinforcedGlass, 'X', Blocks.GLASS));
		GameRegistry.addRecipe(new ItemStack(PCBlocks.acidBarrier, 1), " X ", "XZX", " X ", 'Z', PCBlocks.reinforcedGlass, 'X', PCItems.goop);
//		GameRegistry.addRecipe(new ItemStack(PCItems.causticBoat, 1), "R R", "RRR", 'R', new ItemStack(PCItems.ingots, 1, ItemIngot.RADIONITE_DAMAGE));
//		GameRegistry.addRecipe(new ItemStack(blocks.acidTnt, 4), "APA", "GAG", "APA", 'A', PCItems.vial, 'G', Items.gunpowder, 'P', PCItems.ingots);
//		GameRegistry.addRecipe(new ItemStack(PCItems.acidGrenade, 4), "X", "Y", "Z", 'X', Items.IRON_INGOT, 'Y', PCItems.vial, 'Z', PCItems.ingots);
//		GameRegistry.addRecipe(new ItemStack(PCItems.hazmatHood, 1), "LLL", "L L", 'L', PCItems.plasmaLeather);
//		GameRegistry.addRecipe(new ItemStack(PCItems.hazmatJacket, 1), "L L", "LLL", "LLL", 'L', PCItems.plasmaLeather);
//		GameRegistry.addRecipe(new ItemStack(PCItems.hazmatPants, 1), "LLL", "L L", "L L", 'L', PCItems.plasmaLeather);
//		GameRegistry.addRecipe(new ItemStack(PCItems.hazmatBoots, 1), "L L", "L L", 'L', PCItems.plasmaLeather);
//		GameRegistry.addRecipe(new ItemStack(PCItems.plasmaLeather, 1), "N", "J", 'N', PCItems.goop, 'J', Items.leather);
//		GameRegistry.addRecipe(new ItemStack(PCItems.plasmagunsplit, 1), "YB", 'B', PCItems.plasmagun, 'Y', PCItems.beamSplitter);
//		GameRegistry.addRecipe(new ItemStack(PCItems.lasergunsplit, 1), "YB", 'B', PCItems.lasergun, 'Y', PCItems.beamSplitter);
//
//        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PCItems.cryoblaster), "  A", "CBX", " DE", 'A', "ingotUranium", 'B', new ItemStack(PCItems.goop, 1, ItemGoop.CRYONITE_DAMAGE), 'C', new ItemStack(PCItems.ingots, 1, ItemIngot.CRYONITE_DAMAGE), 'D', new ItemStack(PCItems.ingots, 1, ItemIngot.OBSIDIUM_DAMAGE), 'X', new ItemStack(PCItems.battery, 1, ItemBattery.CRYO_DAMAGE), 'E', "ingotPlutonium"));
//        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PCItems.lasershotgun), "  A", "BCD", " EF", 'A', Items.repeater, 'B', PCItems.beamSplitter, 'C', new ItemStack(PCItems.ingots, 1, ItemIngot.NETHERFLOW_DAMAGE), 'D', new ItemStack(PCItems.battery, 1, ItemBattery.CHARGED_DAMAGE), 'E', new ItemStack(PCItems.ingots, 1, ItemIngot.RADIONITE_DAMAGE), 'F', "ingotPlutonium"));
//        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PCItems.lasergun), "ABC", " BD", 'A', new ItemStack(PCItems.ingots, 1, ItemIngot.NETHERFLOW_DAMAGE), 'B', new ItemStack(PCItems.ingots, 1, ItemIngot.OBSIDIUM_DAMAGE), 'C', new ItemStack(PCItems.goop, 1, ItemGoop.NETHERFLOW_DAMAGE), 'D', "ingotPlutonium"));
//        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PCItems.plasmagun), "ABC", " DC", 'A', Items.DIAMOND, 'B', PCItems.ingots, 'C', "ingotPlutonium", 'D', new ItemStack(PCItems.ingots, 1, ItemIngot.OBSIDIUM_DAMAGE)));
//        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PCItems.railgun), "ABC", " DE", "AB ", 'A', "ingotLead", 'B', "ingotPlutonium", 'C', new ItemStack(PCItems.battery, 1, ItemBattery.PLASMA_DAMAGE), 'D', new ItemStack(PCItems.goop, 1, ItemGoop.PLUTONIUM_DAMAGE), 'E', "ingotUranium"));
//        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PCItems.acidgun), "  A", "BCD", " EF", 'A', new ItemStack(PCItems.vial), 'B', "ingotLead", 'C', "ingotUranium", 'D', new ItemStack(PlasmaCraft.blocks.reinforcedGlass), 'E', new ItemStack(PCItems.battery, 1, ItemBattery.PLASMA_DAMAGE), 'F', Items.IRON_INGOT));
//        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PCItems.beamSplitter), " A ", "BCD", " A ", 'A', new ItemStack(PCItems.ingots, 1, ItemIngot.NETHERFLOW_DAMAGE), 'B', Items.DIAMOND, 'C', new ItemStack(PCItems.battery, 1, ItemBattery.PLASMA_DAMAGE), 'D', "ingotUranium"));
//        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PCItems.thermopellet), "AAA", "ABA", "AAA", 'A', new ItemStack(PCItems.goop, 1, ItemGoop.URANIUM_DAMAGE), 'B', "ingotPlutonium"));
//        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(PCItems.battery, 1, ItemBattery.OVERCHARGED_DAMAGE), new ItemStack(PCItems.battery, 1, ItemBattery.CHARGED_DAMAGE), "ingotUranium"));
//
//		GameRegistry.addRecipe(new ItemStack(PCItems.energyCell, 5), " R ", "RXR", " R ", 'R', new ItemStack(PCItems.ingots, 1, ItemIngot.NEPTUNIUM_DAMAGE), 'X', PCItems.goop);
//		GameRegistry.addRecipe(new ItemStack(PCItems.battery, 8), "IRI", "I I", "IRI", 'R', new ItemStack(PCItems.ingots, 1, ItemIngot.RADIONITE_DAMAGE), 'I', Items.IRON_INGOT);
//		GameRegistry.addShapelessRecipe(new ItemStack(PCItems.battery, 1, ItemBattery.CRYO_DAMAGE), new ItemStack(PCItems.goop, 1, ItemGoop.CRYONITE_DAMAGE), PCItems.battery);
//		GameRegistry.addShapelessRecipe(new ItemStack(PCItems.battery, 1, ItemBattery.PLASMA_DAMAGE), PCItems.ingots, PCItems.battery);
//		GameRegistry.addShapelessRecipe(new ItemStack(PCItems.battery, 1, ItemBattery.CHARGED_DAMAGE), new ItemStack(PCItems.goop, 1, ItemGoop.PLUTONIUM_DAMAGE), PCItems.battery);
//
//		GameRegistry.addShapelessRecipe(new ItemStack(PCItems.goop, 1, ItemGoop.CRYONITE_DAMAGE), new ItemStack(PCItems.goop, 1, ItemGoop.ACID_DAMAGE), new ItemStack(PCItems.ingots, 4, ItemIngot.CRYONITE_DAMAGE));
//		GameRegistry.addShapelessRecipe(new ItemStack(PCItems.goop, 1, ItemGoop.NEPTUNIUM_DAMAGE), new ItemStack(PCItems.goop, 1, ItemGoop.ACID_DAMAGE), new ItemStack(PCItems.ingots, 4, ItemGoop.NEPTUNIUM_DAMAGE));
//		GameRegistry.addShapelessRecipe(new ItemStack(PCItems.goop, 1, ItemGoop.NETHERFLOW_DAMAGE), new ItemStack(PCItems.goop, 1, ItemGoop.ACID_DAMAGE), new ItemStack(PCItems.ingots, 4, ItemGoop.NETHERFLOW_DAMAGE));
//		GameRegistry.addShapelessRecipe(new ItemStack(PCItems.goop, 1, ItemGoop.OBSIDIUM_DAMAGE), new ItemStack(PCItems.goop, 1, ItemGoop.ACID_DAMAGE), new ItemStack(PCItems.ingots, 4, ItemGoop.OBSIDIUM_DAMAGE));
//		GameRegistry.addShapelessRecipe(new ItemStack(PCItems.goop, 1, ItemGoop.PLUTONIUM_DAMAGE), new ItemStack(PCItems.goop, 1, ItemGoop.ACID_DAMAGE), new ItemStack(PCItems.ingots, 4, ItemGoop.PLUTONIUM_DAMAGE));
//		GameRegistry.addShapelessRecipe(new ItemStack(PCItems.goop, 1, ItemGoop.RADIONITE_DAMAGE), new ItemStack(PCItems.goop, 1, ItemGoop.ACID_DAMAGE), new ItemStack(PCItems.ingots, 4, ItemGoop.RADIONITE_DAMAGE));
//		GameRegistry.addShapelessRecipe(new ItemStack(PCItems.goop, 1, ItemGoop.URANIUM_DAMAGE), new ItemStack(PCItems.goop, 1, ItemGoop.ACID_DAMAGE), new ItemStack(PCItems.ingots, 4, ItemGoop.URANIUM_DAMAGE));
//		
		GameRegistry.addShapelessRecipe(new ItemStack(PCItems.vial, 1, ItemVial.ACID_DAMAGE), new ItemStack(PCItems.goop, 1, ItemGoop.ACID_DAMAGE), PCItems.vial);
		GameRegistry.addShapelessRecipe(new ItemStack(PCItems.vial, 1, ItemVial.CRYONITE_DAMAGE), new ItemStack(PCItems.goop, 1, ItemGoop.CRYONITE_DAMAGE), PCItems.vial);
		GameRegistry.addShapelessRecipe(new ItemStack(PCItems.vial, 1, ItemVial.NEPTUNIUM_DAMAGE), new ItemStack(PCItems.goop, 1, ItemGoop.NEPTUNIUM_DAMAGE), PCItems.vial);
		GameRegistry.addShapelessRecipe(new ItemStack(PCItems.vial, 1, ItemVial.NETHERFLOW_DAMAGE), new ItemStack(PCItems.goop, 1, ItemGoop.NETHERFLOW_DAMAGE), PCItems.vial);
		GameRegistry.addShapelessRecipe(new ItemStack(PCItems.vial, 1, ItemVial.OBSIDIUM_DAMAGE), new ItemStack(PCItems.goop, 1, ItemGoop.OBSIDIUM_DAMAGE), PCItems.vial);
		GameRegistry.addShapelessRecipe(new ItemStack(PCItems.vial, 1, ItemVial.PLUTONIUM_DAMAGE), new ItemStack(PCItems.goop, 1, ItemGoop.PLUTONIUM_DAMAGE), PCItems.vial);
		GameRegistry.addShapelessRecipe(new ItemStack(PCItems.vial, 1, ItemVial.RADIONITE_DAMAGE), new ItemStack(PCItems.goop, 1, ItemGoop.RADIONITE_DAMAGE), PCItems.vial);
		GameRegistry.addShapelessRecipe(new ItemStack(PCItems.vial, 1, ItemVial.URANIUM_DAMAGE), new ItemStack(PCItems.goop, 1, ItemGoop.URANIUM_DAMAGE), PCItems.vial);
//
//        GameRegistry.addSmelting(new ItemStack(PCBlocks.orePlutonium, 1), new ItemStack(PCItems.ingots, 1, ItemIngot.PLUTONIUM_DAMAGE), 0.1f);
//        GameRegistry.addSmelting(new ItemStack(PCBlocks.oreRadionite, 1), new ItemStack(PCItems.ingots, 1, ItemIngot.RADIONITE_DAMAGE), 0.1f);
//        GameRegistry.addSmelting(new ItemStack(PCBlocks.oreNeptunium, 1), new ItemStack(PCItems.ingots, 1, ItemIngot.NEPTUNIUM_DAMAGE), 0.1f );
//        GameRegistry.addSmelting(new ItemStack(PCBlocks.oreObsidium, 1), new ItemStack(PCItems.ingots, 1, ItemIngot.OBSIDIUM_DAMAGE), 0.1f );
//        GameRegistry.addSmelting(new ItemStack(PCBlocks.oreUranium, 1), new ItemStack(PCItems.ingots, 1, ItemIngot.URANIUM_DAMAGE), 0.1f );
        GameRegistry.addSmelting(new ItemStack(PCBlocks.oreLead, 1), new ItemStack(PCItems.ingots, 1, ItemIngot.LEAD_DAMAGE), 0.1f );
//        GameRegistry.addSmelting(new ItemStack(PCItems.vial, 1, ItemVial.CRYONITE_DAMAGE), new ItemStack(PCItems.ingots, 1, ItemIngot.CRYONITE_DAMAGE), 0.0f);
//        GameRegistry.addSmelting(new ItemStack(PCItems.vial, 1, ItemVial.NETHERFLOW_DAMAGE), new ItemStack(PCItems.ingots, 1, ItemIngot.NETHERFLOW_DAMAGE), 0.0f);
//		GameRegistry.addSmelting(Items.SLIME_BALL, new ItemStack(PCItems.goop, 1, ItemGoop.ACID_DAMAGE), 0f);
	}
	
	public static void loadConfig(FMLPreInitializationEvent event)
	{
        PlasmaCraft.config = new PlasmaCraftConfig();
        PlasmaCraft.config.loadConfig(new Configuration(event.getSuggestedConfigurationFile()));
	}

	public static boolean consumeInventoryItem(InventoryPlayer inventory, ItemStack costItem)
	{
		if(inventory.hasItemStack(costItem))
		{
			int slot = inventory.getSlotFor(costItem);
			inventory.getStackInSlot(slot).stackSize--;
			return true;
		}
		return false;
	}
}
