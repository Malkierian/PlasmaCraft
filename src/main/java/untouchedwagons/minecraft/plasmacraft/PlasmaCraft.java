package untouchedwagons.minecraft.plasmacraft;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import untouchedwagons.minecraft.plasmacraft.blocks.*;
import untouchedwagons.minecraft.plasmacraft.client.gui.GuiHandler;
import untouchedwagons.minecraft.plasmacraft.client.gui.PlasmaTab;
import untouchedwagons.minecraft.plasmacraft.common.FuelHandler;
import untouchedwagons.minecraft.plasmacraft.config.PlasmaCraftConfig;
import untouchedwagons.minecraft.plasmacraft.entity.EntityAcid;
import untouchedwagons.minecraft.plasmacraft.entity.EntityAcidGrenade;
import untouchedwagons.minecraft.plasmacraft.entity.EntityAcidTNTPrimed;
import untouchedwagons.minecraft.plasmacraft.entity.EntityCausticBoat;
import untouchedwagons.minecraft.plasmacraft.entity.EntityCryoBlast;
import untouchedwagons.minecraft.plasmacraft.entity.EntityLaser;
import untouchedwagons.minecraft.plasmacraft.entity.EntityLaserShotgun;
import untouchedwagons.minecraft.plasmacraft.entity.EntityPlasma;
import untouchedwagons.minecraft.plasmacraft.entity.EntityRailGun;
import untouchedwagons.minecraft.plasmacraft.fluids.PCFluids;
import untouchedwagons.minecraft.plasmacraft.proxy.CommonProxy;
import untouchedwagons.minecraft.plasmacraft.tileentity.TilePlasmaBench;
import untouchedwagons.minecraft.plasmacraft.worldgen.WorldGenerator;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import untouchedwagons.minecraft.plasmacraft.items.*;

@Mod(modid = "plasmacraft", name = "PlasmaCraft", version = "0.4.0-beta1")
public class PlasmaCraft
{
	public static String MOD_ID = "plasmacraft";

    public static PCFluids fluids;
    public static PCBlocks blocks;
    public static PCItems items;

	public static PlasmaTab plasmaTab = new PlasmaTab("PlasmaCraft");
    public static PlasmaCraftConfig config;

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

    public static Comparator<ItemStack> tabSorter;
	
	// The instance of your mod that Forge uses.
	@Instance("plasmacraft")
	public static PlasmaCraft instance;
	
	private GuiHandler guiHandler = new GuiHandler();
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="untouchedwagons.minecraft.plasmacraft.proxy.ClientProxy", serverSide="untouchedwagons.minecraft.plasmacraft.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
        loadConfig(event);

        PlasmaCraft.fluids = new PCFluids();
        registerFluids();

        PlasmaCraft.blocks = new PCBlocks(PlasmaCraft.fluids);
        PlasmaCraft.items = new PCItems();

        registerBlocks();
        registerFuel();
        registerItems();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);
		
		proxy.registerRenderers();
		
		List<Item> order = Arrays.asList(Item.getItemFromBlock(blocks.orePlasma), Item.getItemFromBlock(blocks.glowCloth), Item.getItemFromBlock(blocks.frozenCryonite), Item.getItemFromBlock(blocks.reinforcedGlass),
				Item.getItemFromBlock(PlasmaCraft.blocks.acidTnt), Item.getItemFromBlock(PlasmaCraft.blocks.acidBarrier), Item.getItemFromBlock(PlasmaCraft.blocks.plasmaBench),
				PlasmaCraft.items.goop, PlasmaCraft.items.ingots, PlasmaCraft.items.vial, PlasmaCraft.items.causticBoat,
				PlasmaCraft.items.battery, PlasmaCraft.items.beamSplitter, PlasmaCraft.items.energyCell, PlasmaCraft.items.thermopellet,
				PlasmaCraft.items.acidgun, PlasmaCraft.items.cryoblaster, PlasmaCraft.items.lasershotgun, PlasmaCraft.items.lasergun, PlasmaCraft.items.lasergunsplit, PlasmaCraft.items.plasmagun, PlasmaCraft.items.plasmagunsplit, PlasmaCraft.items.railgun,
				PlasmaCraft.items.acidGrenade, PlasmaCraft.items.hazmatBoots, PlasmaCraft.items.hazmatHood, PlasmaCraft.items.hazmatJacket, PlasmaCraft.items.hazmatPants, PlasmaCraft.items.plasmaLeather);

		tabSorter = Ordering.explicit(order).onResultOf(new Function<ItemStack, Item>(){
			@Override
			public Item apply(ItemStack input)
			{
				return input.getItem();
			}
		});

        registerRecipes();
		registerOres();
		registerTileEntities();
        registerEntities();

        proxy.registerTextureFX();
		
		GameRegistry.registerWorldGenerator(new WorldGenerator(), 20);
	}

    private void registerFluids()
    {
        FluidRegistry.registerFluid(PlasmaCraft.fluids.acidFluid);
        FluidRegistry.registerFluid(PlasmaCraft.fluids.cryoniteFluid);
        FluidRegistry.registerFluid(PlasmaCraft.fluids.neptuniumFluid);
        FluidRegistry.registerFluid(PlasmaCraft.fluids.netherflowFluid);
        FluidRegistry.registerFluid(PlasmaCraft.fluids.obsidiumFluid);
        FluidRegistry.registerFluid(PlasmaCraft.fluids.plutoniumFluid);
        FluidRegistry.registerFluid(PlasmaCraft.fluids.radioniteFluid);
        FluidRegistry.registerFluid(PlasmaCraft.fluids.uraniumFluid);
    }
	
	private void registerBlocks()
	{
        GameRegistry.registerBlock(blocks.orePlasma, ItemPlasmaOre.class, "orePlasma");
		GameRegistry.registerBlock(blocks.acidBlock, "Acid");
		GameRegistry.registerBlock(blocks.cryoniteBlock, "Cryonite");
		GameRegistry.registerBlock(blocks.neptuniumBlock, "Neptunium");
		GameRegistry.registerBlock(blocks.netherflowBlock, "Netherflow");
		GameRegistry.registerBlock(blocks.obsidiumBlock, "Obsidium");
		GameRegistry.registerBlock(blocks.plutoniumBlock, "Plutonium");
		GameRegistry.registerBlock(blocks.radioniteBlock, "Radionite");
		GameRegistry.registerBlock(blocks.uraniumBlock, "Uranium");
		GameRegistry.registerBlock(blocks.glowCloth, ItemGlowCloth.class, "glowCloth");
		GameRegistry.registerBlock(blocks.frozenCryonite, "Frozen Cryonite");
		GameRegistry.registerBlock(blocks.reinforcedGlass, "Reinforced Glass");
		GameRegistry.registerBlock(blocks.plasmaBench, "Plasmificator");
		GameRegistry.registerBlock(blocks.acidBarrier, "Acid Barrier");
		GameRegistry.registerBlock(blocks.acidTnt, "Acid TNT");
	}
	
	private void registerEntities()
	{
		int entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityCausticBoat.class, "causticBoat", entityID);
		EntityRegistry.registerModEntity(EntityCausticBoat.class, "causticBoat", entityID, this, 32, 100, true);
		
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityAcidTNTPrimed.class, "acidTntPrimed", entityID);
		EntityRegistry.registerModEntity(EntityAcidTNTPrimed.class, "acidTntPrimed", entityID, this, 32, 100, false);
		
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
		EntityRegistry.registerGlobalEntityID(EntityPlasma.class, "ingots", entityID);
		EntityRegistry.registerModEntity(EntityPlasma.class, "ingots", entityID, this, 32, 100, true);
		
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityRailGun.class, "railGun", entityID);
		EntityRegistry.registerModEntity(EntityRailGun.class, "railGun", entityID, this, 32, 100, true);
		
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityAcid.class, "acid", entityID);
		EntityRegistry.registerModEntity(EntityAcid.class, "acid", entityID, this, 32, 100, true);
		
		entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityCryoBlast.class, "cryoBlast", entityID);
		EntityRegistry.registerModEntity(EntityCryoBlast.class, "cryoBlast", entityID, this, 32, 100, true);
	}
	
	private void registerFuel()
	{
		GameRegistry.registerFuelHandler(new FuelHandler());
	}
	
	private void registerItems()
	{
		GameRegistry.registerItem(PlasmaCraft.items.goop, "pc-goop");
		GameRegistry.registerItem(PlasmaCraft.items.ingots, "pc-ingots");
		GameRegistry.registerItem(PlasmaCraft.items.vial, "pc-vial");
		GameRegistry.registerItem(PlasmaCraft.items.battery, "pc-battery");

        GameRegistry.registerItem(PlasmaCraft.items.causticBoat, "Caustic Boat");

		GameRegistry.registerItem(PlasmaCraft.items.beamSplitter, "Beam Splitter");
		GameRegistry.registerItem(PlasmaCraft.items.energyCell, "Energy Cell");
		GameRegistry.registerItem(PlasmaCraft.items.thermopellet, "Thermopellet");

		GameRegistry.registerItem(PlasmaCraft.items.acidgun, "Acid Launcher");
		GameRegistry.registerItem(PlasmaCraft.items.cryoblaster, "Cryo Blaster");
		GameRegistry.registerItem(PlasmaCraft.items.lasershotgun, "Laser Shotgun");
		GameRegistry.registerItem(PlasmaCraft.items.lasergun, "Laser Rifle");
		GameRegistry.registerItem(PlasmaCraft.items.lasergunsplit, "Split Beam Laser Rifle");
		GameRegistry.registerItem(PlasmaCraft.items.plasmagun, "Plasma Rifle");
		GameRegistry.registerItem(PlasmaCraft.items.plasmagunsplit, "Split Beam Plasma Rifle");
		GameRegistry.registerItem(PlasmaCraft.items.railgun, "Railgun");

		GameRegistry.registerItem(PlasmaCraft.items.acidGrenade, "Acid Grenade");

		GameRegistry.registerItem(PlasmaCraft.items.plasmaLeather, "Plasma Leather");

		GameRegistry.registerItem(PlasmaCraft.items.hazmatBoots, "Hazmat Boots");
		GameRegistry.registerItem(PlasmaCraft.items.hazmatHood, "Hazmat Hood");
		GameRegistry.registerItem(PlasmaCraft.items.hazmatJacket, "Hazmat Jacket");
		GameRegistry.registerItem(PlasmaCraft.items.hazmatPants, "Hazmat Pants");
	}
	
	private void registerOres()
	{
		OreDictionary.registerOre("orePlutonium", new ItemStack(blocks.orePlasma, 1, BlockPlasmaOre.plutoniumMeta));
		OreDictionary.registerOre("oreUranium", new ItemStack(blocks.orePlasma, 1, BlockPlasmaOre.uraniumMeta));
		OreDictionary.registerOre("oreLead", new ItemStack(blocks.orePlasma, 1, BlockPlasmaOre.leadMeta));
		
		OreDictionary.registerOre("ingotPlutonium", new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.PLUTONIUM_DAMAGE));
		OreDictionary.registerOre("ingotUranium", new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.URANIUM_DAMAGE));
		OreDictionary.registerOre("ingotLead", new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.LEAD_DAMAGE));
	}
	
	private void registerRecipes()
	{
		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, BlockGlowCloth.glowClothAcidMeta), new ItemStack(PlasmaCraft.items.goop), new ItemStack(Blocks.wool, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, BlockGlowCloth.glowClothPlutoniumMeta), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.PLUTONIUM_DAMAGE), new ItemStack(Blocks.wool, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, BlockGlowCloth.glowClothRadioniteMeta), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.RADIONITE_DAMAGE), new ItemStack(Blocks.wool, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, BlockGlowCloth.glowClothNeptuniumMeta), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.NEPTUNIUM_DAMAGE), new ItemStack(Blocks.wool, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, BlockGlowCloth.glowClothNetherflowMeta), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.NETHERFLOW_DAMAGE), new ItemStack(Blocks.wool, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, BlockGlowCloth.glowClothObsidiumMeta), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.OBSIDIUM_DAMAGE), new ItemStack(Blocks.wool, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, BlockGlowCloth.glowClothCryoniteMeta), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.CRYONITE_DAMAGE), new ItemStack(Blocks.wool, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(blocks.glowCloth, 1, BlockGlowCloth.glowClothUraniumMeta), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.URANIUM_DAMAGE), new ItemStack(Blocks.wool, 1, 0));
		
		GameRegistry.addRecipe(new ItemStack(blocks.reinforcedGlass, 4), " X ", "X#X", " X ", '#', Blocks.glass, 'X', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.vial, 1), "X#X", "Y Y", "X#X", '#', Items.iron_ingot, 'Y', blocks.reinforcedGlass, 'X', Blocks.glass);
		GameRegistry.addRecipe(new ItemStack(blocks.plasmaBench, 1), "X#X", "# #", "X#X", '#', Items.iron_ingot, 'X', new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.EMPTY_DAMAGE));
		GameRegistry.addRecipe(new ItemStack(blocks.acidBarrier, 1), " X ", "XZX", " X ", 'Z', blocks.reinforcedGlass, 'X', PlasmaCraft.items.goop);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.causticBoat, 1), "R R", "RRR", 'R', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.RADIONITE_DAMAGE));
		GameRegistry.addRecipe(new ItemStack(blocks.acidTnt, 4), "APA", "GAG", "APA", 'A', PlasmaCraft.items.vial, 'G', Items.gunpowder, 'P', PlasmaCraft.items.ingots);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.acidGrenade, 4), "X", "Y", "Z", 'X', Items.iron_ingot, 'Y', PlasmaCraft.items.vial, 'Z', PlasmaCraft.items.ingots);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.hazmatHood, 1), "LLL", "L L", 'L', PlasmaCraft.items.plasmaLeather);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.hazmatJacket, 1), "L L", "LLL", "LLL", 'L', PlasmaCraft.items.plasmaLeather);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.hazmatPants, 1), "LLL", "L L", "L L", 'L', PlasmaCraft.items.plasmaLeather);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.hazmatBoots, 1), "L L", "L L", 'L', PlasmaCraft.items.plasmaLeather);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.plasmaLeather, 1), "N", "J", 'N', PlasmaCraft.items.goop, 'J', Items.leather);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.plasmagunsplit, 1), "YB", 'B', PlasmaCraft.items.plasmagun, 'Y', PlasmaCraft.items.beamSplitter);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.lasergunsplit, 1), "YB", 'B', PlasmaCraft.items.lasergun, 'Y', PlasmaCraft.items.beamSplitter);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.cryoblaster, 1), "  A", "CBX", " DE", 'A', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.URANIUM_DAMAGE), 'B', new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.CRYONITE_DAMAGE), 'C', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.CRYONITE_DAMAGE), 'D', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.OBSIDIUM_DAMAGE), 'X', new ItemStack(PlasmaCraft.items.battery, 1, ItemBattery.CRYO_DAMAGE), 'E', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.PLUTONIUM_DAMAGE));
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.lasershotgun, 1), "  A", "BCD", " EF", 'A', Items.repeater, 'B', PlasmaCraft.items.beamSplitter, 'C', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.NETHERFLOW_DAMAGE), 'D', new ItemStack(PlasmaCraft.items.battery, 1, ItemBattery.CHARGED_DAMAGE), 'E', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.RADIONITE_DAMAGE), 'F', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.PLUTONIUM_DAMAGE));
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.lasergun, 1), "ABC", " BD", 'A', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.NETHERFLOW_DAMAGE), 'B', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.OBSIDIUM_DAMAGE), 'C', new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.NETHERFLOW_DAMAGE), 'D', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.PLUTONIUM_DAMAGE));
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.plasmagun, 1), "ABC", " DC", 'A', Items.diamond, 'B', PlasmaCraft.items.ingots, 'C', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.PLUTONIUM_DAMAGE), 'D', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.OBSIDIUM_DAMAGE));
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.energyCell, 5), " R ", "RXR", " R ", 'R', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.NEPTUNIUM_DAMAGE), 'X', PlasmaCraft.items.goop);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.battery, 8), "IRI", "I I", "IRI", 'R', new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.RADIONITE_DAMAGE), 'I', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.battery, 1, ItemBattery.CRYO_DAMAGE), "R", "X", 'R', new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.CRYONITE_DAMAGE), 'X', PlasmaCraft.items.battery);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.battery, 1, ItemBattery.PLASMA_DAMAGE), "R", "X", 'R', PlasmaCraft.items.ingots, 'X', PlasmaCraft.items.battery);
		GameRegistry.addRecipe(new ItemStack(PlasmaCraft.items.battery, 1, ItemBattery.CHARGED_DAMAGE), "R", "X", 'R', new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.PLUTONIUM_DAMAGE), 'X', PlasmaCraft.items.battery);

		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.goop, 4, ItemGoop.CRYONITE_DAMAGE), PlasmaCraft.items.ingots, new ItemStack(PlasmaCraft.items.goop, 4, ItemGoop.CRYONITE_DAMAGE));
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.goop, 4, ItemGoop.NEPTUNIUM_DAMAGE), PlasmaCraft.items.ingots, new ItemStack(PlasmaCraft.items.goop, 4, ItemGoop.NEPTUNIUM_DAMAGE));
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.goop, 4, ItemGoop.NETHERFLOW_DAMAGE), PlasmaCraft.items.ingots, new ItemStack(PlasmaCraft.items.goop, 4, ItemGoop.NETHERFLOW_DAMAGE));
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.goop, 4, ItemGoop.OBSIDIUM_DAMAGE), PlasmaCraft.items.ingots, new ItemStack(PlasmaCraft.items.goop, 4, ItemGoop.OBSIDIUM_DAMAGE));
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.goop, 4, ItemGoop.PLUTONIUM_DAMAGE), PlasmaCraft.items.ingots, new ItemStack(PlasmaCraft.items.goop, 4, ItemGoop.PLUTONIUM_DAMAGE));
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.goop, 4, ItemGoop.RADIONITE_DAMAGE), PlasmaCraft.items.ingots, new ItemStack(PlasmaCraft.items.goop, 4, ItemGoop.RADIONITE_DAMAGE));
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.goop, 4, ItemGoop.URANIUM_DAMAGE), PlasmaCraft.items.ingots, new ItemStack(PlasmaCraft.items.goop, 4, ItemGoop.URANIUM_DAMAGE));
		
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.ACID_DAMAGE), PlasmaCraft.items.goop, PlasmaCraft.items.vial);
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.CRYONITE_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.CRYONITE_DAMAGE), PlasmaCraft.items.vial);
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.NEPTUNIUM_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.NEPTUNIUM_DAMAGE), PlasmaCraft.items.vial);
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.NETHERFLOW_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.NETHERFLOW_DAMAGE), PlasmaCraft.items.vial);
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.OBSIDIUM_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.OBSIDIUM_DAMAGE), PlasmaCraft.items.vial);
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.PLUTONIUM_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.PLUTONIUM_DAMAGE), PlasmaCraft.items.vial);
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.RADIONITE_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.RADIONITE_DAMAGE), PlasmaCraft.items.vial);
		GameRegistry.addShapelessRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.URANIUM_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.URANIUM_DAMAGE), PlasmaCraft.items.vial);

        GameRegistry.addSmelting(new ItemStack(PlasmaCraft.blocks.orePlasma, 1, BlockPlasmaOre.leadMeta), new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.LEAD_DAMAGE), 0.1f );
	}
	
	public static void loadConfig(FMLPreInitializationEvent event)
	{
        PlasmaCraft.config = new PlasmaCraftConfig();
        PlasmaCraft.config.loadConfig(new Configuration(event.getSuggestedConfigurationFile()));
	}

	private void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TilePlasmaBench.class,   "tilePlasmaBench");
	}
}
