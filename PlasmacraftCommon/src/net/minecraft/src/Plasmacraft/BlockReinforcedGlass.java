package net.minecraft.src.Plasmacraft;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.BlockBreakable;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.forge.ITextureProvider;

public class BlockReinforcedGlass extends BlockBreakable implements ITextureProvider
{
    public BlockReinforcedGlass(int i, int j, Material material, boolean flag, float resistance)
    {
        super(i, j, material, flag);
        setHardness(1.0F);
        setResistance(resistance);
        setStepSound(Block.soundGlassFootstep);
        setTickOnLoad(true);
    }
    
    public void addCreativeItems(ArrayList itemList)
    {    	
    	itemList.add(new ItemStack(this, 1));
    }

    public int idDropped(int i, Random random)
    {
        return blockID;
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }

	@Override
	public String getTextureFile()
	{
		return PlasmaCraftCore.terrainTexture;
	}
}
