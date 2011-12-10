package net.minecraft.src;


import java.util.Random;

public class SMWorldGenNetherCaustics extends WorldGenerator
{

    public SMWorldGenNetherCaustics(int i)
    {
		causticID = i;
    }

    public boolean generate(World world, Random random, int i, int j, int k)
    {
        // Basically, this function generates a caustic block if there is an empty block that is
        // surrounded by netherrack on all sides and has one block of air out of which it can flow.

		// Do not generate if there is a non-netherrack ceiling above this block or a non-stone floor beneath it
        if(world.getBlockId(i, j + 1, k) != Block.netherrack.blockID)
        {
            return false;
        }
        if(world.getBlockId(i, j - 1, k) != Block.netherrack.blockID)
        {
            return false;
        }

        // Do not generate if this is not air and also not netherrack
        if(world.getBlockId(i, j, k) != 0 && world.getBlockId(i, j, k) != Block.netherrack.blockID)
        {
            return false;
        }

		// Tally the number of surrounding netherrack blocks
        int l = 0;
        if(world.getBlockId(i - 1, j, k) == Block.netherrack.blockID)
        {
            l++;
        }
        if(world.getBlockId(i + 1, j, k) == Block.netherrack.blockID)
        {
            l++;
        }
        if(world.getBlockId(i, j, k - 1) == Block.netherrack.blockID)
        {
            l++;
        }
        if(world.getBlockId(i, j, k + 1) == Block.netherrack.blockID)
        {
            l++;
        }

        // Tally the number of surrounding air blocks
        int i1 = 0;
        if(world.isAirBlock(i - 1, j, k))
        {
            i1++;
        }
        if(world.isAirBlock(i + 1, j, k))
        {
            i1++;
        }
        if(world.isAirBlock(i, j, k - 1))
        {
            i1++;
        }
        if(world.isAirBlock(i, j, k + 1))
        {
            i1++;
        }

        // Only generate if we're surrounded by netherrack on 3 sides and have an opening on one side
        if(l == 3 && i1 == 1)
        {
            world.setBlockWithNotify(i, j, k, causticID);
            world.scheduledUpdatesAreImmediate = true;
            Block.blocksList[causticID].updateTick(world, i, j, k, random);
            world.scheduledUpdatesAreImmediate = false;
        }
        return true;
    }

    private int causticID;
}
