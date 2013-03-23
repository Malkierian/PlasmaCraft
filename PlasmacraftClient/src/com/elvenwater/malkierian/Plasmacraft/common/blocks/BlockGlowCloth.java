package com.elvenwater.malkierian.Plasmacraft.common.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import com.elvenwater.malkierian.Plasmacraft.common.CommonProxy;
import com.elvenwater.malkierian.Plasmacraft.common.PlasmaCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGlowCloth extends Block
{
	private Icon[] icons;
	
	public BlockGlowCloth(int i)
	{
		super(i, Material.cloth);
		setLightValue(1F);
		setStepSound(Block.soundClothFootstep);
		setCreativeTab(CreativeTabs.tabBlock);
		setUnlocalizedName("glowCloth");
		setHardness(0.8F);
	}
	
	public void addCreativeItems(ArrayList itemList)
	{
		for(int i = 0; i < 8; i++)
		{
			itemList.add(new ItemStack(this, 1, i));
		}
	}

	public int idDropped(int i, Random random)
	{
		return blockID;
	}
	
	public int damageDropped(int i)
	{
		return i;
	}
	
	public Icon getBlockTextureFromSideAndMetadata(int i, int j)
	{
		return icons[j];
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		for (int ix = 0; ix < 8; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}

	public int quantityDropped(Random random)
	{
		return 1;
	}

    @SideOnly(Side.CLIENT)
    public void func_94332_a(IconRegister par1IconRegister)
    {
        this.icons = new Icon[] {
        		par1IconRegister.func_94245_a("glowCloth_acid"),
        		par1IconRegister.func_94245_a("glowCloth_radionite"),
        		par1IconRegister.func_94245_a("glowCloth_netherflow"),
        		par1IconRegister.func_94245_a("glowCloth_neptunium"),
        		par1IconRegister.func_94245_a("glowCloth_uranium"),
        		par1IconRegister.func_94245_a("glowCloth_plutonium"),
        		par1IconRegister.func_94245_a("glowCloth_cryonite"),
        		par1IconRegister.func_94245_a("glowCloth_obsidium")
        };
    }
}
