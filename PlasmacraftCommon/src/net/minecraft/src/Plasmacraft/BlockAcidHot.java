package net.minecraft.src.Plasmacraft;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraft.src.forge.ITextureProvider;

public class BlockAcidHot extends Block implements ITextureProvider
{
    protected BlockAcidHot(int i, int j)
    {
        super(i, j, Material.glass);
        setHardness(3F);
        setResistance(5F);
        setLightValue(0.2F);
        setLightOpacity(2);
        setLightValue(1.0F);
        setStepSound(Block.soundGlassFootstep);
    }
    
    public void addCreativeItems(ArrayList itemList)
    {    	
    	itemList.add(new ItemStack(this, 1));
    }

    public int idDropped(int i, Random random)
    {
        return PlasmaCraftCore.acidBarrier.blockID;
    }

    public int getRenderBlockPass()
    {
        return 1;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public void onEntityWalking(World world, int i, int j, int k, Entity entity)
    {
    	entity.setFire(20);
        entity.attackEntityFrom(DamageSource.cactus, 50);
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
        entity.attackEntityFrom(DamageSource.cactus, 50);
    }
    
    @Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
    	float shrinkAmount = 0.125F;
    	return AxisAlignedBB.getBoundingBoxFromPool(x + shrinkAmount, y + shrinkAmount, z + shrinkAmount,
    			x + 1 - shrinkAmount, y + 1 - shrinkAmount, z + 1 - shrinkAmount);
	}

	@Override
	public String getTextureFile()
	{
		return PlasmaCraftCore.terrainTexture;
	}
}
