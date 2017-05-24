package malkierian.plasmacraft.fluids;

import malkierian.plasmacraft.PlasmaCraft;
import malkierian.plasmacraft.blocks.BlockCausticFluid;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PCFluids
{
    public static Fluid acidFluid;
    public static Fluid cryoniteFluid;
    public static Fluid neptuniumFluid;
    public static Fluid netherflowFluid;
    public static Fluid obsidiumFluid;
    public static Fluid plutoniumFluid;
    public static Fluid radioniteFluid;
    public static Fluid uraniumFluid;

    public static BlockCausticFluid acidBlock;
    public static BlockCausticFluid cryoniteBlock;
    public static BlockCausticFluid neptuniumBlock;
    public static BlockCausticFluid netherflowBlock;
    public static BlockCausticFluid obsidiumBlock;
    public static BlockCausticFluid plutoniumBlock;
    public static BlockCausticFluid radioniteBlock;
    public static BlockCausticFluid uraniumBlock;
    
//    public static MaterialCaustic
    
    public static void initFluids()
    {
    	acidFluid = createFluid("acid", PlasmaCraft.modId + ":blocks/fluid/acid", true).setDensity(900).setViscosity(400);
	    cryoniteFluid = createFluid("cryonite", PlasmaCraft.modId + ":blocks/fluid/cryonite", true).setDensity(1400).setViscosity(600);
	    neptuniumFluid = createFluid("neptunium", PlasmaCraft.modId + ":blocks/fluid/neptunium", true).setDensity(800).setViscosity(300);
	    netherflowFluid = createFluid("netherflow", PlasmaCraft.modId + ":blocks/fluid/netherflow", true).setDensity(1100).setViscosity(450);
	    obsidiumFluid = createFluid("obsidium", PlasmaCraft.modId + ":blocks/fluid/obsidium", true).setDensity(2500).setViscosity(1200);
	    plutoniumFluid = createFluid("plutonium", PlasmaCraft.modId + ":blocks/fluid/plutonium", true).setDensity(1500).setViscosity(800);
	    radioniteFluid = createFluid("radionite", PlasmaCraft.modId + ":blocks/fluid/radionite", true).setDensity(1800).setViscosity(1000);
	    uraniumFluid = createFluid("uranium", PlasmaCraft.modId + ":blocks/fluid/uranium", true).setDensity(1500).setViscosity(800);
	    
	    FluidRegistry.registerFluid(acidFluid);
	    FluidRegistry.registerFluid(cryoniteFluid);
	    FluidRegistry.registerFluid(neptuniumFluid);
	    FluidRegistry.registerFluid(netherflowFluid);
	    FluidRegistry.registerFluid(obsidiumFluid);
	    FluidRegistry.registerFluid(plutoniumFluid);
	    FluidRegistry.registerFluid(radioniteFluid);
	    FluidRegistry.registerFluid(uraniumFluid);

	    acidBlock = registerFluidBlock(new BlockCausticFluid(acidFluid, new MaterialLiquid(MapColor.LIME), 0, 0.6F, 0), acidFluid.getStill());
	    cryoniteBlock = registerFluidBlock(new BlockCausticFluid(cryoniteFluid, new MaterialLiquid(MapColor.CYAN), 1, 0.5F, 0.8F), cryoniteFluid.getStill());
	    neptuniumBlock = registerFluidBlock(new BlockCausticFluid(neptuniumFluid, new MaterialLiquid(MapColor.GOLD), 2, 0.5F, 1.5F), neptuniumFluid.getStill());
	    netherflowBlock = registerFluidBlock(new BlockCausticFluid(netherflowFluid, new MaterialLiquid(MapColor.RED), 3, 0.6F, 1.0F), netherflowFluid.getStill());
	    obsidiumBlock = registerFluidBlock(new BlockCausticFluid(obsidiumFluid, new MaterialLiquid(MapColor.OBSIDIAN), 4, 0.4F, 1.0F), obsidiumFluid.getStill());
	    plutoniumBlock = registerFluidBlock(new BlockCausticFluid(plutoniumFluid, new MaterialLiquid(MapColor.GREEN), 5, 0.6F, 3F), plutoniumFluid.getStill());
	    radioniteBlock = registerFluidBlock(new BlockCausticFluid(radioniteFluid, new MaterialLiquid(MapColor.MAGENTA), 6, 0.6F, 2F), radioniteFluid.getStill());
	    uraniumBlock = registerFluidBlock(new BlockCausticFluid(uraniumFluid, new MaterialLiquid(MapColor.YELLOW), 7, 0.6F, 3F), uraniumFluid.getStill());
    }
    
    public static void registerRenders()
    {
    	registerRender(acidBlock);
    	registerRender(cryoniteBlock);
    	registerRender(neptuniumBlock);
    	registerRender(netherflowBlock);
    	registerRender(obsidiumBlock);
    	registerRender(plutoniumBlock);
    	registerRender(radioniteBlock);
    	registerRender(uraniumBlock);
    }
    
    public static final class IMDef implements ItemMeshDefinition
    {
    	private ModelResourceLocation loc;
    	
    	public IMDef(ModelResourceLocation loc)
    	{
    		this.loc = loc;
    	}

		@Override
		public ModelResourceLocation getModelLocation(ItemStack stack)
		{
			return loc;
		}
    	
    }
    
    public static void registerRender(IFluidBlock item)
    {
    	Item i = Item.getItemFromBlock((Block) item);
        String loc = PlasmaCraft.modId + ":" + item.getFluid().getName();
        
        ModelResourceLocation modelLoc = new ModelResourceLocation(loc, "fluid");

        ModelLoader.setCustomMeshDefinition(i, stack -> modelLoc );
        ModelLoader.setCustomStateMapper((Block) item, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return modelLoc;
            }
        });
    }

	private static Fluid createFluid(String name, String textureName, boolean hasFlowIcon) {
		ResourceLocation still = new ResourceLocation(textureName);
		ResourceLocation flowing = hasFlowIcon ? new ResourceLocation(textureName + "_flow") : still;

		Fluid fluid = new Fluid(name, still, flowing);
		if (!FluidRegistry.registerFluid(fluid)) {
			throw new IllegalStateException(String.format("Unable to register fluid %s", fluid.getUnlocalizedName()));
		}

		return fluid;
	}

	private static <T extends Block & IFluidBlock> T registerFluidBlock(T block, ResourceLocation still) {
		String fluidName = block.getFluid().getUnlocalizedName();
		block.setUnlocalizedName(fluidName);
		GameRegistry.register(block, still);
		GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));

		return block;
	}
}
