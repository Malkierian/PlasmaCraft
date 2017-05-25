package malkierian.plasmacraft.blocks;

import java.util.List;
import java.util.Random;

import malkierian.plasmacraft.PlasmaCraft;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGlowCloth extends Block
{
    public static final int glowClothAcidMeta = 0;
    public static final int glowClothRadioniteMeta = 1;
    public static final int glowClothNetherflowMeta = 2;
    public static final int glowClothNeptuniumMeta = 3;
    public static final int glowClothUraniumMeta = 4;
    public static final int glowClothPlutoniumMeta = 5;
    public static final int glowClothCryoniteMeta = 6;
    public static final int glowClothObsidiumMeta = 7;

	public BlockGlowCloth(String unlocalizedName, BlockCausticFluid fluidBlock)
	{
		super(Material.CLOTH, fluidBlock.getMapColor());

		setUnlocalizedName(unlocalizedName);
		lightValue = fluidBlock.getLightLevel();
		setSoundType(SoundType.CLOTH);
		setCreativeTab(PlasmaCraft.plasmaTab);
		setHardness(0.8F);
	}
}
