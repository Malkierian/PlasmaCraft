package untouchedwagons.minecraft.plasmacraft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.World;

import untouchedwagons.minecraft.plasmacraft.PlasmaCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockReinforcedGlass extends BlockBreakable
{
	public BlockReinforcedGlass(String filename, Material material, boolean flag, float resistance)
	{
		super(filename, material, flag);
		setHardness(1.0F);
		setResistance(resistance);
		setStepSound(Block.soundTypeGlass);
		setTickRandomly(true);
		setCreativeTab(PlasmaCraft.plasmaTab);
	}

	public int quantityDropped(Random random)
	{
		if(this == PlasmaCraft.blocks.frozenCryonite)
			return 0;
		else
			return 1;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6)
	{
		if(this == PlasmaCraft.blocks.frozenCryonite)
			world.setBlock(x, y, z, PlasmaCraft.blocks.cryoniteBlock);
		else
			super.breakBlock(world, x, y, z, block, par6);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon_registrar)
	{
		if (this == PlasmaCraft.blocks.frozenCryonite)
			blockIcon = icon_registrar.registerIcon(PlasmaCraft.MOD_ID + ":frozenCryonite");
		else
			blockIcon = icon_registrar.registerIcon(PlasmaCraft.MOD_ID + ":reinforcedGlass");
	}
}
