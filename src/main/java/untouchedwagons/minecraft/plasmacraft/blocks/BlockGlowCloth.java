package untouchedwagons.minecraft.plasmacraft.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import untouchedwagons.minecraft.plasmacraft.PlasmaCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGlowCloth extends Block
{
    private IIcon[] icons;

    public static final int glowClothAcidMeta = 0;
    public static final int glowClothRadioniteMeta = 1;
    public static final int glowClothNetherflowMeta = 2;
    public static final int glowClothNeptuniumMeta = 3;
    public static final int glowClothUraniumMeta = 4;
    public static final int glowClothPlutoniumMeta = 5;
    public static final int glowClothCryoniteMeta = 6;
    public static final int glowClothObsidiumMeta = 7;

	public BlockGlowCloth()
	{
		super(Material.cloth);

		setLightLevel(1F);
		setStepSound(Block.soundTypeCloth);
		setCreativeTab(PlasmaCraft.plasmaTab);
		setHardness(0.8F);
	}
	
	@Override
	public int damageDropped(int i)
	{
		return i;
	}

	@Override
	public IIcon getIcon(int i, int j)
	{
		return icons[j];
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs tab, List subItems) {
		for (int i = 0; i < 8; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.icons = new IIcon[] {
        		par1IconRegister.registerIcon("plasmacraft:glowCloth_acid"),
        		par1IconRegister.registerIcon("plasmacraft:glowCloth_radionite"),
        		par1IconRegister.registerIcon("plasmacraft:glowCloth_netherflow"),
        		par1IconRegister.registerIcon("plasmacraft:glowCloth_neptunium"),
        		par1IconRegister.registerIcon("plasmacraft:glowCloth_uranium"),
        		par1IconRegister.registerIcon("plasmacraft:glowCloth_plutonium"),
        		par1IconRegister.registerIcon("plasmacraft:glowCloth_cryonite"),
        		par1IconRegister.registerIcon("plasmacraft:glowCloth_obsidium")
        };
    }
}
