package net.minecraft.src.Plasmacraft;

import java.util.Random;

import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WorldGenFrozenCryonite extends WorldGenerator
{
    private int causticID;

    public WorldGenFrozenCryonite(int i)
    {
        causticID = i;
    }

    public boolean generate(World world, Random random, int i, int j, int k)
    {
        i -= 8;
        for(k -= 8; j > 0 && world.isAirBlock(i, j, k); j--) { }
        j -= 4;
        boolean aflag[] = new boolean[2048];
        int l = random.nextInt(4) + 4;
        for(int i1 = 0; i1 < l; i1++)
        {
            double d = random.nextDouble() * 6D + 3D;
            double d1 = random.nextDouble() * 4D + 2D;
            double d2 = random.nextDouble() * 6D + 3D;
            double d3 = random.nextDouble() * (16D - d - 2D) + 1.0D + d / 2D;
            double d4 = random.nextDouble() * (8D - d1 - 4D) + 2D + d1 / 2D;
            double d5 = random.nextDouble() * (16D - d2 - 2D) + 1.0D + d2 / 2D;
            for(int l2 = 1; l2 < 15; l2++)
            {
                for(int i3 = 1; i3 < 15; i3++)
                {
                    for(int j3 = 1; j3 < 7; j3++)
                    {
                        double d6 = ((double)l2 - d3) / (d / 2D);
                        double d7 = ((double)j3 - d4) / (d1 / 2D);
                        double d8 = ((double)i3 - d5) / (d2 / 2D);
                        double d9 = d6 * d6 + d7 * d7 + d8 * d8;
                        if(d9 < 1.0D)
                        {
                            aflag[(l2 * 16 + i3) * 8 + j3] = true;
                        }
                    }

                }

            }

        }

        for(int j1 = 0; j1 < 16; j1++)
        {
            for(int l1 = 0; l1 < 16; l1++)
            {
                for(int j2 = 0; j2 < 8; j2++)
                {
                    boolean flag = !aflag[(j1 * 16 + l1) * 8 + j2] && (j1 < 15 && aflag[((j1 + 1) * 16 + l1) * 8 + j2] || j1 > 0 && aflag[((j1 - 1) * 16 + l1) * 8 + j2] || l1 < 15 && aflag[(j1 * 16 + (l1 + 1)) * 8 + j2] || l1 > 0 && aflag[(j1 * 16 + (l1 - 1)) * 8 + j2] || j2 < 7 && aflag[(j1 * 16 + l1) * 8 + (j2 + 1)] || j2 > 0 && aflag[(j1 * 16 + l1) * 8 + (j2 - 1)]);
                    if(!flag)
                    {
                        continue;
                    }
                    Material material = world.getBlockMaterial(i + j1, j + j2, k + l1);
                    if(j2 >= 4 && material.isLiquid())
                    {
                        return false;
                    }
                    if(j2 < 4 && !material.isSolid() && world.getBlockId(i + j1, j + j2, k + l1) != causticID)
                    {
                        return false;
                    }
                }

            }

        }

        for(int k1 = 0; k1 < 16; k1++)
        {
            for(int i2 = 0; i2 < 16; i2++)
            {
                for(int k2 = 0; k2 < 8; k2++)
                {
                    if(aflag[(k1 * 16 + i2) * 8 + k2])
                    {
                        world.setBlock(i + k1, j + k2, k + i2, k2 >= 4 ? 0 : causticID);
                    }
                }

            }

        }

        return true;
    }
}
