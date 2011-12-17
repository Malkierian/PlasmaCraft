package net.minecraft.src.Plasmacraft;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.forge.ITextureProvider;

public class BlockGlowCloth extends Block implements ITextureProvider
{
    protected BlockGlowCloth(int i, int j)
    {
        super(i, j, Material.cloth);
        setLightValue(1.0F);
        setStepSound(Block.soundClothFootstep);
    }
    
    public void addCreativeItems(ArrayList itemList)
    {    	
    	itemList.add(new ItemStack(this, 1));
    }

    public int idDropped(int i, Random random)
    {
        return blockID;
    }

	@Override
	public String getTextureFile()
	{
		return PlasmaCraftCore.terrainTexture;
	}
}
