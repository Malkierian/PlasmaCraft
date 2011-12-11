package net.minecraft.src.Plasmacraft;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
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

    public int idDropped(int i, Random random)
    {
        return PlasmaCraftCore.acidHot.blockID;
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
	public String getTextureFile()
	{
		return PlasmaCraftCore.terrainTexture;
	}
}
