package net.minecraft.src.Plasmacraft;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModLoader;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntityFurnace;
import net.minecraft.src.World;
import net.minecraft.src.forge.ITextureProvider;

public class BlockPlasmificator extends BlockContainer implements ITextureProvider
{
	private Random plasmificatorRand;
    private final boolean isActive;
    private final int frontIdleTexture;
    private final int frontActiveTexture;
    private static boolean keepPlasmificatorInventory = false;

    protected BlockPlasmificator(int i, int j, int k, int l, boolean flag, float lightValue)
    {
        super(i, Material.rock);
        isActive = flag;
        plasmificatorRand = new Random();
        blockIndexInTexture = j;
        frontIdleTexture = k;
        frontActiveTexture = l;
        setHardness(3F);
        setStepSound(Block.soundStoneFootstep);
        setLightValue(lightValue);
    }
    
    public void addCreativeItems(ArrayList itemList)
    {    	
    	itemList.add(new ItemStack(this, 1));
    }

    public int idDropped(int i, Random random)
    {
        return PlasmaCraftCore.plasmificatorIdle.blockID;
    }

    public void onBlockAdded(World world, int i, int j, int k)
    {
        super.onBlockAdded(world, i, j, k);
        setDefaultDirection(world, i, j, k);
    }

    private void setDefaultDirection(World world, int i, int j, int k)
    {
        if(world.isRemote)
        {
            return;
        }
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
        } else
        {
            return;
        }
    }

    public int getBlockTextureFromSide(int i)
    {
        if(i == 3)
        {
            return frontIdleTexture;
        } else
        {
            return blockIndexInTexture;
        }
    }

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
    	if(world.isRemote)
    	{
    		return true;
    	}
        TileEntityPlasmaBench tileentityplasmabench = (TileEntityPlasmaBench)world.getBlockTileEntity(i, j, k);
        if(tileentityplasmabench != null)
        {
        	PlasmaCraftCore.proxy.OpenGUI(entityplayer,  tileentityplasmabench);
        }
        return true;
    }

    public static void updatePlasmificatorBlockState(boolean flag, World world, int i, int j, int k)
    {
        int l = world.getBlockMetadata(i, j, k);
        TileEntity tileentity = world.getBlockTileEntity(i, j, k);
        keepPlasmificatorInventory = true;
        if(flag)
        {
            world.setBlockWithNotify(i, j, k, PlasmaCraftCore.plasmificatorActive.blockID);
        } else
        {
            world.setBlockWithNotify(i, j, k, PlasmaCraftCore.plasmificatorIdle.blockID);
        }
        keepPlasmificatorInventory = false;
        world.setBlockMetadataWithNotify(i, j, k, l);
        if(tileentity != null)
        {
            tileentity.validate();
            world.setBlockTileEntity(i, j, k, tileentity);
        }
    }

    public TileEntity getBlockEntity()
    {
        return new TileEntityPlasmaBench();
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

    public void onBlockRemoval(World world, int i, int j, int k)
    {
        if(!keepPlasmificatorInventory)
        {
            TileEntityPlasmaBench tileentityfurnace = (TileEntityPlasmaBench)world.getBlockTileEntity(i, j, k);
            if(tileentityfurnace != null)
            {
label0:
                for(int l = 0; l < tileentityfurnace.getSizeInventory(); l++)
                {
                    ItemStack itemstack = tileentityfurnace.getStackInSlot(l);
                    if(itemstack == null)
                    {
                        continue;
                    }
                    float f = plasmificatorRand.nextFloat() * 0.8F + 0.1F;
                    float f1 = plasmificatorRand.nextFloat() * 0.8F + 0.1F;
                    float f2 = plasmificatorRand.nextFloat() * 0.8F + 0.1F;
                    do
                    {
                        if(itemstack.stackSize <= 0)
                        {
                            continue label0;
                        }
                        int i1 = plasmificatorRand.nextInt(21) + 10;
                        if(i1 > itemstack.stackSize)
                        {
                            i1 = itemstack.stackSize;
                        }
                        itemstack.stackSize -= i1;
                        EntityItem entityitem = new EntityItem(world, (float)i + f, (float)j + f1, (float)k + f2, new ItemStack(itemstack.itemID, i1, itemstack.getItemDamage()));
                        float f3 = 0.05F;
                        entityitem.motionX = (float)plasmificatorRand.nextGaussian() * f3;
                        entityitem.motionY = (float)plasmificatorRand.nextGaussian() * f3 + 0.2F;
                        entityitem.motionZ = (float)plasmificatorRand.nextGaussian() * f3;
                        world.spawnEntityInWorld(entityitem);
                    } while(true);
                }

            }
        }
        super.onBlockRemoval(world, i, j, k);
    }

	@Override
	public String getTextureFile()
	{
		return PlasmaCraftCore.terrainTexture;
	}
}
