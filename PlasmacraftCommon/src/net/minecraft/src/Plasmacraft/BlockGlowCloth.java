package net.minecraft.src.Plasmacraft;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraft.src.forge.ITextureProvider;

public class BlockGlowCloth extends Block implements ITextureProvider
{
    protected BlockGlowCloth(int i, int j)
    {
        super(i, j, Material.cloth);
        setLightValue(1F);
        setStepSound(Block.soundClothFootstep);
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
    
    protected int damageDropped(int i)
    {
    	return i;
    }
    
    public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
    	switch (j)
    	{
    	case 0:
    		return PlasmaCraftCore.glowClothAcidIndex;
    	case 1:
    		return PlasmaCraftCore.glowClothRadioniteIndex;
    	case 2:
    		return PlasmaCraftCore.glowClothNetherflowIndex;
    	case 3:
    		return PlasmaCraftCore.glowClothNeptuniumIndex;
    	case 4:
    		return PlasmaCraftCore.glowClothUraniumIndex;
    	case 5:
    		return PlasmaCraftCore.glowClothPlutoniumIndex;
    	case 6:
    		return PlasmaCraftCore.glowClothCryoniteIndex;
    	default:
    		return PlasmaCraftCore.glowClothObsidiumIndex;
    	}
    	
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
