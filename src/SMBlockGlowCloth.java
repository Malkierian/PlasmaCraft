package net.minecraft.src;

import java.util.Random;

public class SMBlockGlowCloth extends Block
{
	
    protected SMBlockGlowCloth(int i, int j)
    {
        super(i, j, Material.cloth);
    }
    
    public int idDropped(int i, Random random)
    {
        return blockID;
    }
}