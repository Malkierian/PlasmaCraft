package untouchedwagons.minecraft.plasmacraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;

public class PCBlocks {
    public final Fluid acidFluid = new Fluid("acid").setDensity(80).setViscosity(400);
    public final Fluid cryoniteFluid = new Fluid("cryonite").setDensity(80).setViscosity(600);
    public final Fluid neptuniumFluid = new Fluid("neptunium").setDensity(80).setViscosity(300);
    public final Fluid netherflowFluid = new Fluid("netherflow").setDensity(80).setViscosity(450);
    public final Fluid obsidiumFluid = new Fluid("obsidium").setDensity(80).setViscosity(1200);
    public final Fluid plutoniumFluid = new Fluid("plutonium").setDensity(80).setViscosity(800);
    public final Fluid radioniteFluid = new Fluid("radionite").setDensity(80).setViscosity(1000);
    public final Fluid uraniumFluid = new Fluid("uranium").setDensity(150).setViscosity(800);

    public Block acidBarrier = new BlockAcidBarrier().setBlockName("acidBarrier");
    public Block orePlasma = new BlockPlasmaOre().setLightLevel(0.5334f).setBlockName("orePlasma");
    public Block glowCloth = new BlockGlowCloth().setBlockName("glowCloth");
    public Block plasmaBench = (new BlockPlasmaBench()).setBlockName("plasmaBench");
    public Block acidTnt = new BlockAcidTNT().setBlockName("acidTnt");
    public Block frozenCryonite = new BlockReinforcedGlass("frozenCryonite", Material.glass, false, 1.0F).setBlockName("frozenCryonite");
    public Block reinforcedGlass = new BlockReinforcedGlass("reinforcedGlass", Material.glass, false, 500.0F).setBlockName("reinforcedGlass");

    public final BlockCausticFluids acidBlock = (BlockCausticFluids) new BlockCausticFluids(acidFluid, Material.water).setBlockName("acid");
    public final BlockCausticFluids cryoniteBlock = (BlockCausticFluids) new BlockCausticFluids(cryoniteFluid, Material.water).setBlockName("cryonite");
    public final BlockCausticFluids neptuniumBlock = (BlockCausticFluids) new BlockCausticFluids(neptuniumFluid, Material.water).setBlockName("neptunium");
    public final BlockCausticFluids netherflowBlock = (BlockCausticFluids) new BlockCausticFluids(netherflowFluid, Material.water).setBlockName("netherflow");
    public final BlockCausticFluids obsidiumBlock = (BlockCausticFluids) new BlockCausticFluids(obsidiumFluid, Material.water).setBlockName("obsidium");
    public final BlockCausticFluids plutoniumBlock = (BlockCausticFluids) new BlockCausticFluids(plutoniumFluid, Material.water).setBlockName("plutonium");
    public final BlockCausticFluids radioniteBlock = (BlockCausticFluids) new BlockCausticFluids(radioniteFluid, Material.water).setBlockName("radionite");
    public final BlockCausticFluids uraniumBlock = (BlockCausticFluids) new BlockCausticFluids(uraniumFluid, Material.water).setBlockName("uranium");
}
