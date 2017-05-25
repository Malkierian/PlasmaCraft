package malkierian.plasmacraft.blocks;

import java.util.List;
import java.util.Random;

import malkierian.plasmacraft.PlasmaCraft;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.SoundType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPlasmaOre extends BlockOre
{
	public static final String[] filenames = new String[] {"ore_plutonium", "ore_radionite", "ore_neptunium", "ore_obsidium", "ore_uranium", "ore_lead"};

    public static final int plutoniumMeta = 0;
    public static final int radioniteMeta = 1;
    public static final int neptuniumMeta = 2;
    public static final int obsidiumMeta = 3;
    public static final int uraniumMeta = 4;
    public static final int leadMeta = 5;

	
	public BlockPlasmaOre(String name, float lightLevel, float hardness)
	{
		super();
		setUnlocalizedName(name);
		setTickRandomly(true);
		setLightLevel(lightLevel);
		setSoundType(SoundType.STONE);
		setCreativeTab(PlasmaCraft.plasmaTab);
		setHardness(hardness);
	}
	
	public BlockPlasmaOre(String name, float lightLevel)
	{
		this(name, lightLevel, 3F);
	}

//	@Override
//	public void onEntityWalking(World world, int x, int y, int z, Entity entity)
//	{
//		onEntityCollidedWithBlock(world, x, y, z, entity);
//	}
//
//	@Override
//	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
//	{
//		int meta = world.getBlockMetadata(x, y, z);
//		Block block = world.getBlock(x, y, z);
//
//		if(block == this && meta == uraniumMeta) {
//			entity.attackEntityFrom(DamageSource.cactus, 5);
//		}
//	}
}
