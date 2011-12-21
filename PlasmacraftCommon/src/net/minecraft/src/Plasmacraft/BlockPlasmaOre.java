package net.minecraft.src.Plasmacraft;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.BlockOre;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.forge.ITextureProvider;

public class BlockPlasmaOre extends BlockOre implements ITextureProvider
{
    public BlockPlasmaOre(int i, int j)
    {
        super(i, j);
        setTickOnLoad(true);
        setHardness(3F);
        setResistance(5F);
        setStepSound(Block.soundStoneFootstep);
    }
    
    public void addCreativeItems(ArrayList itemList)
    {    	
    	itemList.add(new ItemStack(this, 1));
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
    		return PlasmaCraftCore.orePlutoniumIndex;
    	case 1:
    		return PlasmaCraftCore.oreRadioniteIndex;
    	case 2:
    		return PlasmaCraftCore.oreNeptuniumIndex;
    	case 3:
    		return PlasmaCraftCore.oreObsidiumIndex;
    	case 4:
    		return PlasmaCraftCore.blockUraniumIndex;
    	default:
    		return PlasmaCraftCore.orePlutoniumIndex;
    	}
    	
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }

    public void onEntityWalking(World world, int i, int j, int k, Entity entity)
    {
        onEntityCollidedWithBlock(world, i, j, k, entity);
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
    	int meta = world.getBlockMetadata(i, j, k);
        if(meta == PlasmaCraftCore.uraniumMeta)
        {
            entity.attackEntityFrom(DamageSource.cactus, 5);
            return;
        }
        else
        {
            return;
        }
    }

	@Override
	public String getTextureFile()
	{
		return PlasmaCraftCore.terrainTexture;
	}
}
