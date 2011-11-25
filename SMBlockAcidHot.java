package net.minecraft.src;

import java.util.Random;

public class SMBlockAcidHot extends Block
{

    protected SMBlockAcidHot(int i, int j)
    {
        super(i, j, Material.glass);
    }

    public int idDropped(int i, Random random)
    {
		return mod_PlasmaCraft.acidHot.blockID;
    }

    public int getRenderBlockPass()
    {
        return 1;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public void onEntityWalking(World world, int i,int j, int k, Entity entity)
    {
		entity.fire = 400;
		entity.attackEntityFrom(DamageSource.cactus, 50);
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
		entity.attackEntityFrom(DamageSource.cactus, 50);
    }
}
