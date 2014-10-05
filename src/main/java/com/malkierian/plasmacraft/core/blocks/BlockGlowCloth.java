package com.malkierian.plasmacraft.core.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.malkierian.plasmacraft.core.PlasmaCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGlowCloth extends Block
{
	private IIcon[] icons;
	
	public BlockGlowCloth()
	{
		super(Material.cloth);
		setLightLevel(1F);
		setStepSound(Block.soundTypeCloth);
		setCreativeTab(PlasmaCraft.plasmaTab);
		setHardness(0.8F);
	}
	
//	public void addCreativeItems(ArrayList itemList)
//	{
//		for(int i = 0; i < 8; i++)
//		{
//			itemList.add(new ItemStack(this, 1, i));
//		}
//	}
	
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
		for (int ix = 0; ix < 8; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
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
