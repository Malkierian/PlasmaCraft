package com.elvenwater.malkierian.Plasmacraft.common;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if(world.provider.isHellWorld)
			generateNether(world, random, chunkX*16, chunkZ*16);
		else
		   generateSurface(world, random, chunkX*16, chunkZ*16);
	}
	
	private void generateNether(World world, Random random, int i, int j)
	{
		for(int k = 0; k < PlasmaCraft.neptuniumOreVeinCount; k++)
        {
            int l1 = i + random.nextInt(16);
            int i3 = random.nextInt(PlasmaCraft.neptuniumOreYRange) + PlasmaCraft.neptuniumOreYStart;
            int j4 = j + random.nextInt(16);
            (new WorldGenNetherMinable(PlasmaCraft.orePlasma.blockID, PlasmaCraft.neptuniumOreVeinSize, PlasmaCraft.neptuniumMeta)).generate(world, random, l1, i3, j4);
        }

        for(int l = 0; l < PlasmaCraft.obsidiumOreVeinCount; l++)
        {
            int i2 = i + random.nextInt(16);
            int j3 = random.nextInt(PlasmaCraft.obsidiumOreYRange) + PlasmaCraft.obsidiumOreYStart;
            int k4 = j + random.nextInt(16);
            (new WorldGenNetherMinable(PlasmaCraft.orePlasma.blockID, PlasmaCraft.obsidiumOreVeinSize, PlasmaCraft.obsidiumMeta)).generate(world, random, i2, j3, k4);
        }

        if(random.nextInt(PlasmaCraft.netherflowLakeChance) == 0)
        {
            int i1 = random.nextInt(16) + 8;
            int j2 = random.nextInt(random.nextInt(PlasmaCraft.netherflowLakeYRange) + PlasmaCraft.netherflowLakeYStart);
            int k3 = random.nextInt(16) + 8;
            if(j2 < PlasmaCraft.netherflowLakeYCutoff)
            {
                (new WorldGenCaustics(PlasmaCraft.netherflowMoving.blockID)).generate(world, random, i1, j2, k3);
            }
        }
        for(int j1 = 0; j1 < PlasmaCraft.netherflowSpoutCount; j1++)
        {
            int k2 = i + random.nextInt(16) + 8;
			int l3 = random.nextInt(random.nextInt(PlasmaCraft.netherflowSpoutYRange) + PlasmaCraft.netherflowSpoutYStart);
            int l4 = j + random.nextInt(16) + 8;
            (new WorldGenNetherCaustics(PlasmaCraft.netherflowMoving.blockID)).generate(world, random, k2, l3, l4);
        }

        for(int k1 = 0; k1 < PlasmaCraft.neptuniumSpoutCount; k1++)
        {
            int l2 = i + random.nextInt(16) + 8;
            int i4 = random.nextInt(random.nextInt(PlasmaCraft.neptuniumSpoutYRange) + PlasmaCraft.neptuniumSpoutYStart);
            int i5 = j + random.nextInt(16) + 8;
            (new WorldGenNetherCaustics(PlasmaCraft.neptuniumMoving.blockID)).generate(world, random, l2, i4, i5);
        }
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
	            (new WorldGenMulti(PlasmaCraft.orePlasma.blockID, PlasmaCraft.plutoniumOreVeinSize, PlasmaCraft.plutoniumMeta)).generate(world, random, i2, k3, i5);
	        }
    	}

    	if(PlasmaCraft.generateUranium)
    	{
	        for(int l = 0; l < PlasmaCraft.uraniumOreVeinCount; l++)
	        {
	            int j2 = i + random.nextInt(16);
	            int l3 = random.nextInt(PlasmaCraft.uraniumOreYRange) + PlasmaCraft.uraniumOreYStart;
	            int j5 = j + random.nextInt(16);
	            (new WorldGenMulti(PlasmaCraft.orePlasma.blockID, PlasmaCraft.uraniumOreVeinSize, PlasmaCraft.uraniumMeta)).generate(world, random, j2, l3, j5);
	        }
    	}
    	
		for(int i1 = 0; i1 < PlasmaCraft.radioniteOreVeinCount; i1++)
		{
			int k2 = i + random.nextInt(16);
			int i4 = random.nextInt(PlasmaCraft.radioniteOreYRange) + PlasmaCraft.radioniteOreYStart;
			int k5 = j + random.nextInt(16);
			(new WorldGenMulti(PlasmaCraft.orePlasma.blockID, PlasmaCraft.radioniteOreVeinSize, PlasmaCraft.radioniteMeta)).generate(world, random, k2, i4, k5);
		}
    	
		for(int i1 = 0; i1 < PlasmaCraft.leadOreVeinCount; i1++)
		{
			int k2 = i + random.nextInt(16);
			int i4 = random.nextInt(PlasmaCraft.leadOreYRange) + PlasmaCraft.leadOreYStart;
			int k5 = j + random.nextInt(16);
			(new WorldGenMulti(PlasmaCraft.oreLeadBlock.blockID, PlasmaCraft.leadOreVeinSize, 0)).generate(world, random, k2, i4, k5);
		}
        
        (new CausticLakes()).populateSurfaceLiquids(world, random, i, j);
	}
}
