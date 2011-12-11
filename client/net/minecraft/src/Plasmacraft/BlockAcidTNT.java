package net.minecraft.src.Plasmacraft;

import net.minecraft.src.Block;
import net.minecraft.src.BlockTNT;
import net.minecraft.src.World;
import net.minecraft.src.forge.ITextureProvider;

public class BlockAcidTNT extends BlockTNT implements ITextureProvider
{

    private int topTextureIndex;
    private int bottomTextureIndex;

    public BlockAcidTNT(int i, int j, int k, int l)
    {
        super(i, j);
        topTextureIndex = k;
        bottomTextureIndex = l;
        setHardness(0.0F);
        setStepSound(Block.soundGrassFootstep);
    }

    public int getBlockTextureFromSide(int i)
    {
        if(i == 0)
        {
            return bottomTextureIndex;
        }
        if(i == 1)
        {
            return topTextureIndex;
        } else
        {
            return blockIndexInTexture;
        }
    }

    public void onBlockDestroyedByExplosion(World world, int i, int j, int k)
    {
        EntityAcidTNTPrimed smentityacidtntprimed = new EntityAcidTNTPrimed(world, (float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F);
        smentityacidtntprimed.fuse = world.rand.nextInt(smentityacidtntprimed.fuse / 4) + smentityacidtntprimed.fuse / 8;
        world.entityJoinedWorld(smentityacidtntprimed);
    }

    public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l)
    {
        if(world.multiplayerWorld)
        {
            return;
        } else
        {
            EntityAcidTNTPrimed smentityacidtntprimed = new EntityAcidTNTPrimed(world, (float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F);
            world.entityJoinedWorld(smentityacidtntprimed);
            world.playSoundAtEntity(smentityacidtntprimed, "random.fuse", 1.0F, 1.0F);
            return;
        }
    }

	@Override
	public String getTextureFile()
	{
		return PlasmaCraftCore.terrainTexture;
	}
}
