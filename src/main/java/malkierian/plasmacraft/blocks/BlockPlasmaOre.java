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

	
	public BlockPlasmaOre()
	{
		super();
		setTickRandomly(true);
		setSoundType(SoundType.STONE);
		setCreativeTab(PlasmaCraft.plasmaTab);
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 1;
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

//	@Override
//	public float getExplosionResistance(Entity exploder, World world, int x, int y, int z, double src_x, double src_y, double src_z)
//	{
//		switch(world.getBlockMetadata(x, y, z))
//		{
//            case obsidiumMeta:
//                return 1200F;
//            case uraniumMeta:
//                return 6F;
//            case plutoniumMeta:
//                return 8F;
//            default:
//                return 3F;
//		}
//	}
	
//	@Override
//	public float getBlockHardness(World world, int x, int y, int z)
//	{
//		int md = world.getBlockMetadata(x, y, z);
//		switch(md)
//		{
//            case obsidiumMeta:
//                return 15F;
//            case uraniumMeta:
//            case plutoniumMeta:
//                return 5F;
//            default:
//                return 3F;
//		}
//	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs tab, List subItems)
	{
		for (int ix = 0; ix < filenames.length; ix++)
		{
			subItems.add(new ItemStack(this, 1, ix));
		}
	}
}
