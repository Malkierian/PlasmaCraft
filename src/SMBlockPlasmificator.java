package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

import java.util.Random;

public class SMBlockPlasmificator extends BlockContainer
{

    protected SMBlockPlasmificator(int i, int j, int k, int l, boolean flag)
    {
        super(i, Material.rock);
        isActive = flag;
        blockIndexInTexture = j;
        frontIdleTexture = k;
        frontActiveTexture = l;
    }

    public int idDropped(int i, Random random)
    {
        return mod_PlasmaCraft.plasmificatorIdle.blockID;
    }

    public void onBlockAdded(World world, int i, int j, int k)
    {
        super.onBlockAdded(world, i, j, k);
        setDefaultDirection(world, i, j, k);
    }

    private void setDefaultDirection(World world, int i, int j, int k)
    {
        int l = world.getBlockId(i, j, k - 1);
        int i1 = world.getBlockId(i, j, k + 1);
        int j1 = world.getBlockId(i - 1, j, k);
        int k1 = world.getBlockId(i + 1, j, k);
        byte byte0 = 3;
        if(Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
        {
            byte0 = 3;
        }
        if(Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
        {
            byte0 = 2;
        }
        if(Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
        {
            byte0 = 5;
        }
        if(Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
        {
            byte0 = 4;
        }
        world.setBlockMetadataWithNotify(i, j, k, byte0);
    }

    public int getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        int i1 = iblockaccess.getBlockMetadata(i, j, k);
        if(l != i1)
        {
            return blockIndexInTexture;
        }
        if(isActive)
        {
            return frontActiveTexture;
        } else
        {
            return frontIdleTexture;
        }
    }

    public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
        if(!isActive)
        {
            return;
        }
        // TODO
        /*
        int l = world.getBlockMetadata(i, j, k);
        float f = (float)i + 0.5F;
        float f1 = (float)j + 0.0F + (random.nextFloat() * 6F) / 16F;
        float f2 = (float)k + 0.5F;
        float f3 = 0.52F;
        float f4 = random.nextFloat() * 0.6F - 0.3F;
        if(l == 4)
        {
            world.spawnParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
            world.spawnParticle("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
        } else
        if(l == 5)
        {
            world.spawnParticle("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
            world.spawnParticle("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
        } else
        if(l == 2)
        {
            world.spawnParticle("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
            world.spawnParticle("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
        } else
        if(l == 3)
        {
            world.spawnParticle("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
            world.spawnParticle("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
        }
        */
    }

    public int getBlockTextureFromSide(int i)
    {
        if(i == 3)
        {
            return frontIdleTexture;
        }
        else
        {
            return blockIndexInTexture;
        }
    }

    /*public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        if(!world.multiplayerWorld)
        {
        	SMTileEntityPlasmaBench tile = (SMTileEntityPlasmaBench) world.getBlockTileEntity(i, j, k);
            ModLoader.OpenGUI(entityplayer, new SMGuiPlasmaBench(entityplayer.inventory, tile));
            return true;
        }
    	//SMTileEntityPlasmaBench tile = new SMTileEntityPlasmaBench();
        //ModLoader.OpenGUI(entityplayer, new SMGuiPlasmaBench(entityplayer.inventory, tile));
        return true;
    }*/
    
    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        if(world.multiplayerWorld)
        {
            SMTileEntityPlasmaBench bench = (SMTileEntityPlasmaBench)world.getBlockTileEntity(i, j, k);

            ModLoader.OpenGUI(entityplayer, new SMGuiPlasmaBench(entityplayer.inventory, bench));
            return true;
        }
        else
        {
        	SMTileEntityPlasmaBench bench = (SMTileEntityPlasmaBench)world.getBlockTileEntity(i, j, k);

            ModLoader.OpenGUI(entityplayer, new SMGuiPlasmaBench(entityplayer.inventory, bench));
            return true;
        }
    }

    public static void updatePlasmificatorBlockState(boolean flag, World world, int i, int j, int k)
    {
        int l = world.getBlockMetadata(i, j, k);
        TileEntity tileentity = world.getBlockTileEntity(i, j, k);
        if(flag)
        {
            world.setBlockWithNotify(i, j, k, mod_PlasmaCraft.plasmificatorActive.blockID);
        } else
        {
            world.setBlockWithNotify(i, j, k, mod_PlasmaCraft.plasmificatorIdle.blockID);
        }
        world.setBlockMetadataWithNotify(i, j, k, l);
        world.setBlockTileEntity(i, j, k, tileentity);
    }

    public TileEntity getBlockEntity()
    {
        return new SMTileEntityPlasmaBench();
    }

    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
    {
        int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        if(l == 0)
        {
            world.setBlockMetadataWithNotify(i, j, k, 2);
        }
        if(l == 1)
        {
            world.setBlockMetadataWithNotify(i, j, k, 5);
        }
        if(l == 2)
        {
            world.setBlockMetadataWithNotify(i, j, k, 3);
        }
        if(l == 3)
        {
            world.setBlockMetadataWithNotify(i, j, k, 4);
        }
    }

    private final boolean isActive;
    private final int frontIdleTexture;
    private final int frontActiveTexture;
}
