package malkierian.plasmacraft.worldgen;

import java.util.Random;

import malkierian.plasmacraft.PlasmaCraft;
import malkierian.plasmacraft.blocks.BlockPlasmaOre;
import malkierian.plasmacraft.init.PCBlocks;
import malkierian.plasmacraft.init.PCFluids;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenerator implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		switch(world.provider.getDimension())
		{
			case -1:
				generateNether(world, random, chunkX*16, chunkZ*16);
			case 0:
				generateSurface(world, random, chunkX*16, chunkZ*16);
		}
	}
	
	private void generateNether(World world, Random random, int i, int j)
	{
		for(int k = 0; k < PlasmaCraft.config.neptunium.getVeinCount(); k++)
		{
			int l1 = i + random.nextInt(16);
			int i3 = random.nextInt(PlasmaCraft.config.neptunium.getVeinYRange()) + PlasmaCraft.config.neptunium.getVeinYStart();
			int j4 = j + random.nextInt(16);
			(new WorldGenNetherMinable(PCBlocks.oreNeptunium, PlasmaCraft.config.neptunium.getVeinSize(), BlockPlasmaOre.neptuniumMeta)).generate(world, random, l1, i3, j4);
		}

		for(int l = 0; l < PlasmaCraft.config.obsidium.getVeinCount(); l++)
		{
			int i2 = i + random.nextInt(16);
			int j3 = random.nextInt(PlasmaCraft.config.obsidium.getVeinYRange()) + PlasmaCraft.config.obsidium.getVeinYStart();
			int k4 = j + random.nextInt(16);
			(new WorldGenNetherMinable(PCBlocks.oreObsidium, PlasmaCraft.config.obsidium.getVeinSize(), BlockPlasmaOre.obsidiumMeta)).generate(world, random, i2, j3, k4);
		}

		if(random.nextInt(PlasmaCraft.config.netherflow_lake.getLakeChance()) == 0)
		{
			int i1 = random.nextInt(16) + 8;
			int j2 = random.nextInt(random.nextInt(PlasmaCraft.config.netherflow_lake.getLakeYSize()) + PlasmaCraft.config.netherflow_lake.getLakeYStart());
			int k3 = random.nextInt(16) + 8;
			if(j2 < PlasmaCraft.config.netherflow_lake.getLakeCutoff())
			{
				(new WorldGenCaustics(PCFluids.netherflowBlock)).generate(world, random, i1, j2, k3);
			}
		}
		for(int j1 = 0; j1 < PlasmaCraft.config.netherflow_spout.getSpoutCount(); j1++)
		{
			int k2 = i + random.nextInt(16) + 8;
			int l3 = random.nextInt(random.nextInt(PlasmaCraft.config.netherflow_spout.getSpoutYSize()) + PlasmaCraft.config.netherflow_spout.getSpoutYStart());
			int l4 = j + random.nextInt(16) + 8;
			(new WorldGenNetherCaustics(PCFluids.netherflowBlock)).generate(world, random, k2, l3, l4);
		}

		for(int k1 = 0; k1 < PlasmaCraft.config.neptunium_spout.getSpoutCount(); k1++)
		{
			int l2 = i + random.nextInt(16) + 8;
			int i4 = random.nextInt(random.nextInt(PlasmaCraft.config.neptunium_spout.getSpoutYSize()) + PlasmaCraft.config.neptunium_spout.getSpoutYStart());
			int i5 = j + random.nextInt(16) + 8;
			(new WorldGenNetherCaustics(PCFluids.neptuniumBlock)).generate(world, random, l2, i4, i5);
		}
	}
	
	private void generateSurface(World world, Random random, int i, int j)
	{
		if(PlasmaCraft.config.plutonium.doVeinGeneration())
		{
			for(int k = 0; k < PlasmaCraft.config.plutonium.getVeinCount(); k++)
			{
				int i2 = i + random.nextInt(16);
				int k3 = random.nextInt(PlasmaCraft.config.plutonium.getVeinYRange()) + PlasmaCraft.config.plutonium.getVeinYStart();
				int i5 = j + random.nextInt(16);
				(new WorldGenMulti(PCBlocks.orePlutonium, PlasmaCraft.config.plutonium.getVeinSize(), BlockPlasmaOre.plutoniumMeta)).generate(world, random, i2, k3, i5);
			}
		}

		if(PlasmaCraft.config.uranium.doVeinGeneration())
		{
			for(int l = 0; l < PlasmaCraft.config.uranium.getVeinCount(); l++)
			{
				int j2 = i + random.nextInt(16);
				int l3 = random.nextInt(PlasmaCraft.config.uranium.getVeinYRange()) + PlasmaCraft.config.uranium.getVeinYStart();
				int j5 = j + random.nextInt(16);
				(new WorldGenMulti(PCBlocks.oreUranium, PlasmaCraft.config.uranium.getVeinSize(), BlockPlasmaOre.uraniumMeta)).generate(world, random, j2, l3, j5);
			}
		}
		
		for(int i1 = 0; i1 < PlasmaCraft.config.radionite.getVeinCount(); i1++)
		{
			int k2 = i + random.nextInt(16);
			int i4 = random.nextInt(PlasmaCraft.config.radionite.getVeinYRange()) + PlasmaCraft.config.radionite.getVeinYStart();
			int k5 = j + random.nextInt(16);
			(new WorldGenMulti(PCBlocks.oreRadionite, PlasmaCraft.config.radionite.getVeinSize(), BlockPlasmaOre.radioniteMeta)).generate(world, random, k2, i4, k5);
		}
		
		if(PlasmaCraft.config.lead.doVeinGeneration())
		{
			for(int i1 = 0; i1 < PlasmaCraft.config.lead.getVeinCount(); i1++)
			{
				int k2 = i + random.nextInt(16);
				int i4 = random.nextInt(PlasmaCraft.config.lead.getVeinYRange()) + PlasmaCraft.config.lead.getVeinYStart();
				int k5 = j + random.nextInt(16);
				(new WorldGenMulti(PCBlocks.oreLead, PlasmaCraft.config.lead.getVeinSize(), BlockPlasmaOre.leadMeta)).generate(world, random, k2, i4, k5);
			}
		}

		(new CausticLakes()).populateSurfaceLiquids(world, random, i, j);
	}
}
