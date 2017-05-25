package malkierian.plasmacraft.init;

import malkierian.plasmacraft.PlasmaCraft;
import malkierian.plasmacraft.blocks.BlockAcidBarrier;
import malkierian.plasmacraft.blocks.BlockGlowCloth;
import malkierian.plasmacraft.blocks.BlockPlasmaOre;
import malkierian.plasmacraft.blocks.BlockReinforcedGlass;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PCBlocks
{
    public static Block glowClothAcid;
    public static Block glowClothCryonite;
    public static Block glowClothNeptunium;
    public static Block glowClothNetherflow;
    public static Block glowClothObsidium;
    public static Block glowClothPlutonium;
    public static Block glowClothRadionite;
    public static Block glowClothUranium;
    public static ItemBlock glowClothItemAcid;
    public static ItemBlock glowClothItemCryonite;
    public static ItemBlock glowClothItemNeptunium;
    public static ItemBlock glowClothItemNetherflow;
    public static ItemBlock glowClothItemObsidium;
    public static ItemBlock glowClothItemPlutonium;
    public static ItemBlock glowClothItemRadionite;
    public static ItemBlock glowClothItemUranium;
    
    public static Block oreLead;
    public static Block oreNeptunium;
    public static Block oreObsidium;
    public static Block orePlutonium;
    public static Block oreRadionite;
    public static Block oreUranium;
    public static ItemBlock oreItemLead;
    public static ItemBlock oreItemNeptunium;
    public static ItemBlock oreItemObsidium;
    public static ItemBlock oreItemPlutonium;
    public static ItemBlock oreItemRadionite;
    public static ItemBlock oreItemUranium;
    
//    public Block acidTnt = new BlockAcidTNT().setBlockName("acidTnt");
    public static Block acidBarrier;
    public static ItemBlock acidBarrierItem;
    
    public static Block frozenCryonite;
    public static ItemBlock frozenCryoniteItem;
    
    public static Block reinforcedGlass;
    public static ItemBlock reinforcedGlassItem;

    public PCBlocks()
    {
    	
    }
    
    public static void init()
    {
    	glowClothAcid = registerBlock("glowClothAcid", new BlockGlowCloth("glowClothAcid", PCFluids.acidBlock), glowClothItemAcid);
        glowClothCryonite = registerBlock("glowClothCryonite", new BlockGlowCloth("glowClothCryonite", PCFluids.cryoniteBlock), glowClothItemCryonite);
        glowClothNeptunium = registerBlock("glowClothNeptunium", new BlockGlowCloth("glowClothNeptunium", PCFluids.neptuniumBlock), glowClothItemNeptunium);
        glowClothNetherflow = registerBlock("glowClothNetherflow", new BlockGlowCloth("glowClothNetherflow", PCFluids.netherflowBlock), glowClothItemNetherflow);
        glowClothObsidium = registerBlock("glowClothObsidium", new BlockGlowCloth("glowClothObsidium", PCFluids.obsidiumBlock), glowClothItemObsidium);
        glowClothPlutonium = registerBlock("glowClothPlutonium", new BlockGlowCloth("glowClothPlutonium", PCFluids.plutoniumBlock), glowClothItemPlutonium);
        glowClothRadionite = registerBlock("glowClothRadionite", new BlockGlowCloth("glowClothRadionite", PCFluids.radioniteBlock), glowClothItemRadionite);
        glowClothUranium = registerBlock("glowClothUranium", new BlockGlowCloth("glowClothUranium", PCFluids.uraniumBlock), glowClothItemUranium);
        
        oreLead = registerBlock("oreLead", new BlockPlasmaOre("oreLead", 0), oreItemLead).setResistance(3);
        oreNeptunium = registerBlock("oreNeptunium", new BlockPlasmaOre("oreNeptunium", 0.5f), oreItemNeptunium).setResistance(3);
        oreObsidium = registerBlock("oreObsidium", new BlockPlasmaOre("oreObsidium", 0.34f, 15).setResistance(1200), oreItemObsidium);
        orePlutonium = registerBlock("orePlutonium", new BlockPlasmaOre("orePlutonium", 0.67f, 5).setResistance(8), oreItemPlutonium);
        oreRadionite = registerBlock("oreRadionite", new BlockPlasmaOre("oreRadionite", 0.5f).setResistance(3), oreItemRadionite);
        oreUranium = registerBlock("oreUranium", new BlockPlasmaOre("oreUranium", 0.6f, 5).setResistance(6), oreItemUranium);
        
        acidBarrier = registerBlock("acidBarrier", new BlockAcidBarrier(), acidBarrierItem);
        frozenCryonite = registerBlock("frozenCryonite", new BlockReinforcedGlass(Material.GLASS, false, 1.0F).setUnlocalizedName("frozenCryonite"), frozenCryoniteItem);
        reinforcedGlass = registerBlock("reinforcedGlass", new BlockReinforcedGlass(Material.GLASS, false, 500.0F).setUnlocalizedName("reinforcedGlass"), reinforcedGlassItem);
    }

	private static <T extends Block> T registerBlock(String name, T block, ItemBlock item)
	{
		ResourceLocation loc = new ResourceLocation(PlasmaCraft.modId + ":blocks/" + name);
		block.setRegistryName(loc);
		item = new ItemBlock(block);
		item.setRegistryName(loc);
		item.setCreativeTab(PlasmaCraft.plasmaTab);
		GameRegistry.register(block);
		GameRegistry.register(item);
		PlasmaCraft.proxy.registerItemRenderer(item, 0, name);

		return block;
	}
}
