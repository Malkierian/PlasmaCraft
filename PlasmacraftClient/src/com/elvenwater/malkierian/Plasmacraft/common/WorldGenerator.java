package com.elvenwater.malkierian.Plasmacraft.common;

import java.util.Random;

import net.minecraft.src.IChunkProvider;
import net.minecraft.src.World;
//import net.minecraft.src.Plasmacraft.CausticLakes;
import com.elvenwater.malkierian.Plasmacraft.common.WorldGenMulti;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		switch (world.provider.worldType)
		{
		   case -1: generateNether(world, random, chunkX*16, chunkZ*16);
		   case 0: generateSurface(world, random, chunkX*16, chunkZ*16);
		}
	}
	
	private void generateNether(World world, Random random, int i, int j)
	{
		
	}
	
	private void generateSurface(World world, Random random, int i, int j)
	{
		if(PlasmaCraft.generatePlutonium)
    	{
	        for(int k = 0; k < PlasmaCraft.plutoniumOreVeinCount; k++)
	        {
	            int i2 = i + random.nextInt(16);
	            int k3 = random.nextInt(PlasmaCraft.plutoniumOreYRange) + PlasmaCraft.plutoniumOreYStart;
	            int i5 = j + random.nextInt(16);
	            (new WorldGenMulti(PlasmaCraft.oreBlockID, PlasmaCraft.plutoniumOreVeinSize, PlasmaCraft.plutoniumMeta)).generate(world, random, i2, k3, i5);
	        }
    	}

    	if(PlasmaCraft.generateUranium)
    	{
	        for(int l = 0; l < PlasmaCraft.uraniumOreVeinCount; l++)
	        {
	            int j2 = i + random.nextInt(16);
	            int l3 = random.nextInt(PlasmaCraft.uraniumOreYRange) + PlasmaCraft.uraniumOreYStart;
	            int j5 = j + random.nextInt(16);
	            (new WorldGenMulti(PlasmaCraft.oreBlockID, PlasmaCraft.uraniumOreVeinSize, PlasmaCraft.uraniumMeta)).generate(world, random, j2, l3, j5);
	        }
    	}
    	
        for(int i1 = 0; i1 < PlasmaCraft.radioniteOreVeinCount; i1++)
        {
            int k2 = i + random.nextInt(16);
            int i4 = random.nextInt(PlasmaCraft.radioniteOreYRange) + PlasmaCraft.radioniteOreYStart;
            int k5 = j + random.nextInt(16);
            (new WorldGenMulti(PlasmaCraft.oreBlockID, PlasmaCraft.radioniteOreVeinSize, PlasmaCraft.radioniteMeta)).generate(world, random, k2, i4, k5);
        }
        
        //CausticLakes.populateSurfaceLiquids(world, random, i, j);
	}
}
