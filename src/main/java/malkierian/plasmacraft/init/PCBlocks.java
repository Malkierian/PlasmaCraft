package malkierian.plasmacraft.init;

import malkierian.plasmacraft.PlasmaCraft;
import malkierian.plasmacraft.blocks.BlockGlowCloth;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PCBlocks
{
//    public Block acidBarrier = new BlockAcidBarrier().setBlockName("acidBarrier");
//    public static Block orePlasma = new BlockPlasmaOre().setLightLevel(0.5334f).setBlockName("orePlasma");
    public static Block glowClothAcid;
    public static Block glowClothCryonite;
    public static Block glowClothNeptunium;
    public static Block glowClothNetherflow;
    public static Block glowClothObsidium;
    public static Block glowClothPlutonium;
    public static Block glowClothRadionite;
    public static Block glowClothUranium;
//    public Block acidTnt = new BlockAcidTNT().setBlockName("acidTnt");
//    public Block frozenCryonite = new BlockReinforcedGlass("frozenCryonite", Material.glass, false, 1.0F).setBlockName("frozenCryonite");
//    public Block reinforcedGlass = new BlockReinforcedGlass("reinforcedGlass", Material.glass, false, 500.0F).setBlockName("reinforcedGlass");

    public PCBlocks()
    {
    	
    }
    
    public static void init()
    {
    	glowClothAcid = registerBlock("glowClothAcid", new BlockGlowCloth("glowClothAcid", PCFluids.acidBlock));
        glowClothCryonite = registerBlock("glowClothCryonite", new BlockGlowCloth("glowClothCryonite", PCFluids.cryoniteBlock));
        glowClothNeptunium = registerBlock("glowClothNeptunium", new BlockGlowCloth("glowClothNeptunium", PCFluids.neptuniumBlock));
        glowClothNetherflow = registerBlock("glowClothNetherflow", new BlockGlowCloth("glowClothNetherflow", PCFluids.netherflowBlock));
        glowClothObsidium = registerBlock("glowClothObsidium", new BlockGlowCloth("glowClothObsidium", PCFluids.obsidiumBlock));
        glowClothPlutonium = registerBlock("glowClothPlutonium", new BlockGlowCloth("glowClothPlutonium", PCFluids.plutoniumBlock));
        glowClothRadionite = registerBlock("glowClothRadionite", new BlockGlowCloth("glowClothRadionite", PCFluids.radioniteBlock));
        glowClothUranium = registerBlock("glowClothUranium", new BlockGlowCloth("glowClothUranium", PCFluids.uraniumBlock));
    }

	private static <T extends Block> T registerBlock(String name, T block)
	{
		ResourceLocation loc = new ResourceLocation(PlasmaCraft.modId + ":blocks/" + name);
		block.setRegistryName(loc);
		ItemBlock item = new ItemBlock(block);
		item.setRegistryName(loc);
		item.setCreativeTab(PlasmaCraft.plasmaTab);
		GameRegistry.register(block);
		GameRegistry.register(item);
		PlasmaCraft.proxy.registerItemRenderer(item, 0, name);

		return block;
	}
}
