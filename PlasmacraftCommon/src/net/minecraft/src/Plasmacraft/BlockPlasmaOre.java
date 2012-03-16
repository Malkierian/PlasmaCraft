package net.minecraft.src.Plasmacraft;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.BlockOre;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.forge.ISpecialResistance;
import net.minecraft.src.forge.ITextureProvider;

public class BlockPlasmaOre extends BlockOre implements ITextureProvider, ISpecialResistance
{
    public BlockPlasmaOre(int i, int j)
    {
        super(i, j);
        setTickRandomly(true);
        setStepSound(Block.soundStoneFootstep);
        setLightValue(0.54F);
    }
    
    public void addCreativeItems(ArrayList itemList)
    {
    	for(int i = 0; i < 5; i++)
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
    	int blockID = world.getBlockId(i, j, k);
        if(blockID == this.blockID && meta == PlasmaCraftCore.uraniumMeta)
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

	@Override
	public float getSpecialExplosionResistance(World world, int i, int j,
			int k, double src_x, double src_y, double src_z, Entity exploder) {
		switch(world.getBlockMetadata(i, j, k))
		{
		case PlasmaCraftCore.obsidiumMeta:
			return 1200F;
		case PlasmaCraftCore.uraniumMeta:
			return 6F;
		case PlasmaCraftCore.plutoniumMeta:
			return 8F;
		default:
			return 3F;
		}
	}
	
	@Override
	public float getHardness(int md)
	{
		switch(md)
		{
		case PlasmaCraftCore.obsidiumMeta:
			return 15F;
		case PlasmaCraftCore.uraniumMeta:
		case PlasmaCraftCore.plutoniumMeta:
			return 5F;
		default:
			return 3F;
		}
	}
}
