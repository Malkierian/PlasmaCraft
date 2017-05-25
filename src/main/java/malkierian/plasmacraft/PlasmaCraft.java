package malkierian.plasmacraft;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import malkierian.plasmacraft.client.gui.PlasmaTab;
import malkierian.plasmacraft.config.PlasmaCraftConfig;
import malkierian.plasmacraft.init.PCBlocks;
import malkierian.plasmacraft.init.PCFluids;
import malkierian.plasmacraft.init.PCItems;
import malkierian.plasmacraft.items.ItemIngot;
import malkierian.plasmacraft.proxy.CommonProxy;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

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
//
//        PlasmaCraft.blocks = new PCBlocks(PlasmaCraft.fluids);
//        PlasmaCraft.items = new PCItems();
//
//        registerBlocks();
//        registerFuel();
        PCItems.init();
        PCBlocks.init();
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
			/*Item.getItemFromBlock(blocks.frozenCryonite), Item.getItemFromBlock(blocks.reinforcedGlass),
			Item.getItemFromBlock(PlasmaCraft.blocks.acidTnt), Item.getItemFromBlock(PlasmaCraft.blocks.acidBarrier),*/
			PCItems.goop, PCItems.ingots, PCItems.vial /*PlasmaCraft.items.causticBoat,
			PlasmaCraft.items.battery, PlasmaCraft.items.beamSplitter, PlasmaCraft.items.energyCell, PlasmaCraft.items.thermopellet,
			PlasmaCraft.items.acidgun, PlasmaCraft.items.cryoblaster, PlasmaCraft.items.lasershotgun, PlasmaCraft.items.lasergun, PlasmaCraft.items.lasergunsplit, PlasmaCraft.items.plasmagun, PlasmaCraft.items.plasmagunsplit, PlasmaCraft.items.railgun,
			PlasmaCraft.items.acidGrenade, PlasmaCraft.items.hazmatBoots, PlasmaCraft.items.hazmatHood, PlasmaCraft.items.hazmatJacket, PlasmaCraft.items.hazmatPants, PlasmaCraft.items.plasmaLeather*/);

		tabSorter = Ordering.explicit(order).onResultOf(new Function<ItemStack, Item>() {
			@Override
			public Item apply(ItemStack input)
			{
				return input.getItem();
			}
		});
//
//        registerRecipes();
//		registerOres();
//        registerEntities();
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
//		entityID = EntityRegistry.findGlobalUniqueEntityId();
//		EntityRegistry.registerGlobalEntityID(EntityAcid.class, "acid", entityID);
//		EntityRegistry.registerModEntity(EntityAcid.class, "acid", entityID, this, 32, 100, true);
//		
//		entityID = EntityRegistry.findGlobalUniqueEntityId();
//		EntityRegistry.registerGlobalEntityID(EntityCryoBlast.class, "cryoBlast", entityID);
//		EntityRegistry.registerModEntity(EntityCryoBlast.class, "cryoBlast", entityID, this, 32, 100, true);
	}
	
	private void registerFuel()
	{
//		GameRegistry.registerFuelHandler(new FuelHandler());
	}
	
	private void registerItems()
	{
//		GameRegistry.registerItem(PlasmaCraft.items.goop, "pc-goop");
//		GameRegistry.registerItem(PlasmaCraft.items.ingots, "pc-ingots");
//		PCItems.(PlasmaCraft.items.vial, "pc-vial");
//		GameRegistry.registerItem(PlasmaCraft.items.battery, "pc-battery");
//
//        GameRegistry.registerItem(PlasmaCraft.items.causticBoat, "Caustic Boat");
//
//		GameRegistry.registerItem(PlasmaCraft.items.beamSplitter, "Beam Splitter");
//		GameRegistry.registerItem(PlasmaCraft.items.energyCell, "Energy Cell");
//		GameRegistry.registerItem(PlasmaCraft.items.thermopellet, "Thermopellet");
//
//		GameRegistry.registerItem(PlasmaCraft.items.acidgun, "Acid Launcher");
//		GameRegistry.registerItem(PlasmaCraft.items.cryoblaster, "Cryo Blaster");
//		GameRegistry.registerItem(PlasmaCraft.items.lasershotgun, "Laser Shotgun");
//		GameRegistry.registerItem(PlasmaCraft.items.lasergun, "Laser Rifle");
//		GameRegistry.registerItem(PlasmaCraft.items.lasergunsplit, "Split Beam Laser Rifle");
//		GameRegistry.registerItem(PlasmaCraft.items.plasmagun, "Plasma Rifle");
//		GameRegistry.registerItem(PlasmaCraft.items.plasmagunsplit, "Split Beam Plasma Rifle");
//		GameRegistry.registerItem(PlasmaCraft.items.railgun, "Railgun");
//
//		GameRegistry.registerItem(PlasmaCraft.items.acidGrenade, "Acid Grenade");
//
//		GameRegistry.registerItem(PlasmaCraft.items.plasmaLeather, "Plasma Leather");
//
//		GameRegistry.registerItem(PlasmaCraft.items.hazmatBoots, "Hazmat Boots");
//		GameRegistry.registerItem(PlasmaCraft.items.hazmatHood, "Hazmat Hood");
//		GameRegistry.registerItem(PlasmaCraft.items.hazmatJacket, "Hazmat Jacket");
//		GameRegistry.registerItem(PlasmaCraft.items.hazmatPants, "Hazmat Pants");
	}
	
	private void registerOres()
	{
		OreDictionary.registerOre("orePlutonium", PCBlocks.oreItemPlutonium);
		OreDictionary.registerOre("oreUranium", PCBlocks.oreItemUranium);
		OreDictionary.registerOre("oreLead", PCBlocks.oreItemLead);
		OreDictionary.registerOre("oreObsidium", PCBlocks.oreItemObsidium);
		OreDictionary.registerOre("oreRadionite", PCBlocks.oreItemRadionite);
		OreDictionary.registerOre("oreNeptunium", PCBlocks.oreItemNeptunium);
		
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
//		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, BlockGlowCloth.glowClothAcidMeta), new ItemStack(PlasmaCraft.items.goop), new ItemStack(Blocks.wool, 1, 0));
//		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, BlockGlowCloth.glowClothPlutoniumMeta), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.PLUTONIUM_DAMAGE), new ItemStack(Blocks.wool, 1, 0));
//		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, BlockGlowCloth.glowClothRadioniteMeta), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.RADIONITE_DAMAGE), new ItemStack(Blocks.wool, 1, 0));
//		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, BlockGlowCloth.glowClothNeptuniumMeta), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.NEPTUNIUM_DAMAGE), new ItemStack(Blocks.wool, 1, 0));
//		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, BlockGlowCloth.glowClothNetherflowMeta), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.NETHERFLOW_DAMAGE), new ItemStack(Blocks.wool, 1, 0));
//		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, BlockGlowCloth.glowClothObsidiumMeta), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.OBSIDIUM_DAMAGE), new ItemStack(Blocks.wool, 1, 0));
//		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, BlockGlowCloth.glowClothCryoniteMeta), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.CRYONITE_DAMAGE), new ItemStack(Blocks.wool, 1, 0));
//		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, BlockGlowCloth.glowClothUraniumMeta), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.URANIUM_DAMAGE), new ItemStack(Blocks.wool, 1, 0));
//		
//		GameRegistry.addRecipe(new ItemStack(blocks.reinforcedGlass, 4), " X ", "X#X", " X ", '#', Blocks.glass, 'X', Items.iron_ingot);
//		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.vial, 1), "X#X", "Y Y", "X#X", '#', Items.iron_ingot, 'Y', blocks.reinforcedGlass, 'X', Blocks.glass);
//		GameRegistry.addRecipe(new ItemStack(blocks.acidBarrier, 1), " X ", "XZX", " X ", 'Z', blocks.reinforcedGlass, 'X', PlasmaCraft.items.goop);
//		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.causticBoat, 1), "R R", "RRR", 'R', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.RADIONITE_DAMAGE));
//		GameRegistry.addRecipe(new ItemStack(blocks.acidTnt, 4), "APA", "GAG", "APA", 'A', PlasmaCraft.items.vial, 'G', Items.gunpowder, 'P', PlasmaCraft.items.ingots);
//		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.acidGrenade, 4), "X", "Y", "Z", 'X', Items.iron_ingot, 'Y', PlasmaCraft.items.vial, 'Z', PlasmaCraft.items.ingots);
//		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.hazmatHood, 1), "LLL", "L L", 'L', PlasmaCraft.items.plasmaLeather);
//		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.hazmatJacket, 1), "L L", "LLL", "LLL", 'L', PlasmaCraft.items.plasmaLeather);
//		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.hazmatPants, 1), "LLL", "L L", "L L", 'L', PlasmaCraft.items.plasmaLeather);
//		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.hazmatBoots, 1), "L L", "L L", 'L', PlasmaCraft.items.plasmaLeather);
//		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.plasmaLeather, 1), "N", "J", 'N', PlasmaCraft.items.goop, 'J', Items.leather);
//		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.plasmagunsplit, 1), "YB", 'B', PlasmaCraft.items.plasmagun, 'Y', PlasmaCraft.items.beamSplitter);
//		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.lasergunsplit, 1), "YB", 'B', PlasmaCraft.items.lasergun, 'Y', PlasmaCraft.items.beamSplitter);
//
//        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PlasmaCraft.items.cryoblaster), "  A", "CBX", " DE", 'A', "ingotUranium", 'B', new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.CRYONITE_DAMAGE), 'C', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.CRYONITE_DAMAGE), 'D', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.OBSIDIUM_DAMAGE), 'X', new ItemStack(PlasmaCraft.items.battery, 1, ItemBattery.CRYO_DAMAGE), 'E', "ingotPlutonium"));
//        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PlasmaCraft.items.lasershotgun), "  A", "BCD", " EF", 'A', Items.repeater, 'B', PlasmaCraft.items.beamSplitter, 'C', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.NETHERFLOW_DAMAGE), 'D', new ItemStack(PlasmaCraft.items.battery, 1, ItemBattery.CHARGED_DAMAGE), 'E', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.RADIONITE_DAMAGE), 'F', "ingotPlutonium"));
//        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PlasmaCraft.items.lasergun), "ABC", " BD", 'A', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.NETHERFLOW_DAMAGE), 'B', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.OBSIDIUM_DAMAGE), 'C', new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.NETHERFLOW_DAMAGE), 'D', "ingotPlutonium"));
//        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PlasmaCraft.items.plasmagun), "ABC", " DC", 'A', Items.diamond, 'B', PlasmaCraft.items.ingots, 'C', "ingotPlutonium", 'D', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.OBSIDIUM_DAMAGE)));
//        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PlasmaCraft.items.railgun), "ABC", " DE", "AB ", 'A', "ingotLead", 'B', "ingotPlutonium", 'C', new ItemStack(PlasmaCraft.items.battery, 1, ItemBattery.PLASMA_DAMAGE), 'D', new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.PLUTONIUM_DAMAGE), 'E', "ingotUranium"));
//        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PlasmaCraft.items.acidgun), "  A", "BCD", " EF", 'A', new ItemStack(PlasmaCraft.items.vial), 'B', "ingotLead", 'C', "ingotUranium", 'D', new ItemStack(PlasmaCraft.blocks.reinforcedGlass), 'E', new ItemStack(PlasmaCraft.items.battery, 1, ItemBattery.PLASMA_DAMAGE), 'F', Items.iron_ingot));
//        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PlasmaCraft.items.beamSplitter), " A ", "BCD", " A ", 'A', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.NETHERFLOW_DAMAGE), 'B', Items.diamond, 'C', new ItemStack(PlasmaCraft.items.battery, 1, ItemBattery.PLASMA_DAMAGE), 'D', "ingotUranium"));
//        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(PlasmaCraft.items.thermopellet), "AAA", "ABA", "AAA", 'A', new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.URANIUM_DAMAGE), 'B', "ingotPlutonium"));
//        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(PlasmaCraft.items.battery, 1, ItemBattery.OVERCHARGED_DAMAGE), new ItemStack(PlasmaCraft.items.battery, 1, ItemBattery.CHARGED_DAMAGE), "ingotUranium"));
//
//		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.energyCell, 5), " R ", "RXR", " R ", 'R', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.NEPTUNIUM_DAMAGE), 'X', PlasmaCraft.items.goop);
//		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.battery, 8), "IRI", "I I", "IRI", 'R', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.RADIONITE_DAMAGE), 'I', Items.iron_ingot);
//		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.battery, 1, ItemBattery.CRYO_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.CRYONITE_DAMAGE), PlasmaCraft.items.battery);
//		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.battery, 1, ItemBattery.PLASMA_DAMAGE), PlasmaCraft.items.ingots, PlasmaCraft.items.battery);
//		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.battery, 1, ItemBattery.CHARGED_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.PLUTONIUM_DAMAGE), PlasmaCraft.items.battery);
//
//		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.CRYONITE_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.ACID_DAMAGE), new ItemStack(PlasmaCraft.items.ingots, 4, ItemIngot.CRYONITE_DAMAGE));
//		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.NEPTUNIUM_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.ACID_DAMAGE), new ItemStack(PlasmaCraft.items.ingots, 4, ItemGoop.NEPTUNIUM_DAMAGE));
//		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.NETHERFLOW_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.ACID_DAMAGE), new ItemStack(PlasmaCraft.items.ingots, 4, ItemGoop.NETHERFLOW_DAMAGE));
//		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.OBSIDIUM_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.ACID_DAMAGE), new ItemStack(PlasmaCraft.items.ingots, 4, ItemGoop.OBSIDIUM_DAMAGE));
//		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.PLUTONIUM_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.ACID_DAMAGE), new ItemStack(PlasmaCraft.items.ingots, 4, ItemGoop.PLUTONIUM_DAMAGE));
//		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.RADIONITE_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.ACID_DAMAGE), new ItemStack(PlasmaCraft.items.ingots, 4, ItemGoop.RADIONITE_DAMAGE));
//		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.URANIUM_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.ACID_DAMAGE), new ItemStack(PlasmaCraft.items.ingots, 4, ItemGoop.URANIUM_DAMAGE));
//		
//		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.ACID_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.ACID_DAMAGE), PlasmaCraft.items.vial);
//		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.CRYONITE_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.CRYONITE_DAMAGE), PlasmaCraft.items.vial);
//		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.NEPTUNIUM_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.NEPTUNIUM_DAMAGE), PlasmaCraft.items.vial);
//		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.NETHERFLOW_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.NETHERFLOW_DAMAGE), PlasmaCraft.items.vial);
//		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.OBSIDIUM_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.OBSIDIUM_DAMAGE), PlasmaCraft.items.vial);
//		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.PLUTONIUM_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.PLUTONIUM_DAMAGE), PlasmaCraft.items.vial);
//		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.RADIONITE_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.RADIONITE_DAMAGE), PlasmaCraft.items.vial);
//		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.URANIUM_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.URANIUM_DAMAGE), PlasmaCraft.items.vial);
//
//        GameRegistry.addSmelting(new ItemStack(PlasmaCraft.blocks.orePlasma, 1, BlockPlasmaOre.plutoniumMeta), new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.PLUTONIUM_DAMAGE), 0.1f);
//        GameRegistry.addSmelting(new ItemStack(PlasmaCraft.blocks.orePlasma, 1, BlockPlasmaOre.radioniteMeta), new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.RADIONITE_DAMAGE), 0.1f);
//        GameRegistry.addSmelting(new ItemStack(PlasmaCraft.blocks.orePlasma, 1, BlockPlasmaOre.neptuniumMeta), new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.NEPTUNIUM_DAMAGE), 0.1f );
//        GameRegistry.addSmelting(new ItemStack(PlasmaCraft.blocks.orePlasma, 1, BlockPlasmaOre.obsidiumMeta), new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.OBSIDIUM_DAMAGE), 0.1f );
//        GameRegistry.addSmelting(new ItemStack(PlasmaCraft.blocks.orePlasma, 1, BlockPlasmaOre.uraniumMeta), new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.URANIUM_DAMAGE), 0.1f );
//        GameRegistry.addSmelting(new ItemStack(PlasmaCraft.blocks.orePlasma, 1, BlockPlasmaOre.leadMeta), new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.LEAD_DAMAGE), 0.1f );
//        GameRegistry.addSmelting(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.CRYONITE_DAMAGE), new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.CRYONITE_DAMAGE), 0.0f);
//        GameRegistry.addSmelting(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.NETHERFLOW_DAMAGE), new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.NETHERFLOW_DAMAGE), 0.0f);
//		GameRegistry.addSmelting(Items.slime_ball, new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.ACID_DAMAGE), 0f);
	}
	
	public static void loadConfig(FMLPreInitializationEvent event)
	{
        PlasmaCraft.config = new PlasmaCraftConfig();
        PlasmaCraft.config.loadConfig(new Configuration(event.getSuggestedConfigurationFile()));
	}
}
