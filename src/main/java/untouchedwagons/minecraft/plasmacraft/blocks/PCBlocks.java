package untouchedwagons.minecraft.plasmacraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import untouchedwagons.minecraft.plasmacraft.fluids.PCFluids;
import untouchedwagons.minecraft.plasmacraft.items.ItemVial;

public class PCBlocks {

    public Block acidBarrier = new BlockAcidBarrier().setBlockName("acidBarrier");
    public Block orePlasma = new BlockPlasmaOre().setLightLevel(0.5334f).setBlockName("orePlasma");
    public Block glowCloth = new BlockGlowCloth().setBlockName("glowCloth");
    public Block plasmaBench = (new BlockPlasmaBench()).setBlockName("plasmaBench");
    public Block acidTnt = new BlockAcidTNT().setBlockName("acidTnt");
    public Block frozenCryonite = new BlockReinforcedGlass("frozenCryonite", Material.glass, false, 1.0F).setBlockName("frozenCryonite");
    public Block reinforcedGlass = new BlockReinforcedGlass("reinforcedGlass", Material.glass, false, 500.0F).setBlockName("reinforcedGlass");

    public final BlockCausticFluid acidBlock;
    public final BlockCausticFluid cryoniteBlock;
    public final BlockCausticFluid neptuniumBlock;
    public final BlockCausticFluid netherflowBlock ;
    public final BlockCausticFluid obsidiumBlock;
    public final BlockCausticFluid plutoniumBlock;
    public final BlockCausticFluid radioniteBlock;
    public final BlockCausticFluid uraniumBlock;

    public PCBlocks(PCFluids fluids) {
        acidBlock = (BlockCausticFluid) new BlockCausticFluid(fluids.acidFluid, Material.water).setFluidId(ItemVial.ACID_DAMAGE).setBlockName("acid");
        cryoniteBlock = (BlockCausticFluid) new BlockCausticFluid(fluids.cryoniteFluid, Material.water).setFluidId(ItemVial.CRYONITE_DAMAGE).setBlockName("cryonite");
        neptuniumBlock = (BlockCausticFluid) new BlockCausticFluid(fluids.neptuniumFluid, Material.water).setFluidId(ItemVial.NEPTUNIUM_DAMAGE).setBlockName("neptunium");
        netherflowBlock = (BlockCausticFluid) new BlockCausticFluid(fluids.netherflowFluid, Material.water).setFluidId(ItemVial.NETHERFLOW_DAMAGE).setBlockName("netherflow");
        obsidiumBlock = (BlockCausticFluid) new BlockCausticFluid(fluids.obsidiumFluid, Material.water).setFluidId(ItemVial.OBSIDIUM_DAMAGE).setBlockName("obsidium");
        plutoniumBlock = (BlockCausticFluid) new BlockCausticFluid(fluids.plutoniumFluid, Material.water).setFluidId(ItemVial.PLUTONIUM_DAMAGE).setBlockName("plutonium");
        radioniteBlock = (BlockCausticFluid) new BlockCausticFluid(fluids.radioniteFluid, Material.water).setFluidId(ItemVial.RADIONITE_DAMAGE).setBlockName("radionite");
        uraniumBlock = (BlockCausticFluid) new BlockCausticFluid(fluids.uraniumFluid, Material.water).setFluidId(ItemVial.URANIUM_DAMAGE).setBlockName("uranium");
    }
}
