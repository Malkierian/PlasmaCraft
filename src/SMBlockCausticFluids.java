


import java.util.Random;

import net.minecraft.client.Minecraft;

public abstract class SMBlockCausticFluids extends Block
{

    protected SMBlockCausticFluids(int i, int j, int k, int l, int m, int n)
    {
        super(i, j, Material.water);
        float f = 0.0F;
        float f1 = 0.0F;
        stillTextureID = j;
        flowingTextureID = k;
        stillBlockID = m;
        flowingBlockID = n;
        renderID = l;
        setBlockBounds(0.0F + f1, 0.0F + f, 0.0F + f1, 1.0F + f1, 1.0F + f, 1.0F + f1);
        setTickOnLoad(true);
        armorTick = 0;
    }
    
    public int getBlockColor()
    {
        return 0xffffff;
    }

    public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k)
    {
        return 0xffffff;
    }

    public void onBlockDestroyedByExplosion(World world, int i, int j, int k)
    {
    	if (blockID == mod_PlasmaCraft.acidMoving.blockID | blockID == mod_PlasmaCraft.acidStill.blockID)
    	{
    		return;
    	} else 
    	{
    		if (mod_PlasmaCraft.LiquidSourceExplodesAfterCausticExplosion == 1) {
    			world.setBlockWithNotify(i, j, k, 0);
    			world.createExplosion(null, i, j, k, 4);
    		} else {
    			return;
    		}
    	}
    }
    
    public static float setFluidHeight(int i)
    {
        if(i >= 8)
        {
            i = 0;
        }
        float f = (float)(i + 1) / 9F;
        return f;
    }

    protected int getFlowDecay(World world, int i, int j, int k)
    {
        if(world.getBlockMaterial(i, j, k) != blockMaterial)
        {
            return -1;
        }
        else
        {
            return world.getBlockMetadata(i, j, k);
        }
    }

    protected int getEffectiveFlowDecay(IBlockAccess iblockaccess, int i, int j, int k)
    {
        if(iblockaccess.getBlockMaterial(i, j, k) != blockMaterial)
        {
            return -1;
        }

        int l = iblockaccess.getBlockMetadata(i, j, k);
        if(l >= 8)
        {
            l = 0;
        }
        return l;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean canCollideCheck(int i, boolean flag)
    {
        return flag && i == 0;
    }

    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        Material material = iblockaccess.getBlockMaterial(i, j, k);
        if(material == blockMaterial)
        {
            return false;
        }
        if(material == Material.ice)
        {
            return false;
        }
        if(l == 1)
        {
            return true;
        }
        else
        {
            return super.shouldSideBeRendered(iblockaccess, i, j, k, l);
        }
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
    }

    public int getRenderType()
    {
        return renderID;
    }

    public int idDropped(int i, Random random)
    {
        return 0;
    }

    public int quantityDropped(Random random)
    {
        return 0;
    }

    private Vec3D getFlowVector(IBlockAccess iblockaccess, int i, int j, int k)
    {
        Vec3D vec3d = Vec3D.createVector(0.0D, 0.0D, 0.0D);
        int l = getEffectiveFlowDecay(iblockaccess, i, j, k);
        for(int i1 = 0; i1 < 4; i1++)
        {
            int j1 = i;
            int k1 = j;
            int l1 = k;
            if(i1 == 0)
            {
                j1--;
            }
            if(i1 == 1)
            {
                l1--;
            }
            if(i1 == 2)
            {
                j1++;
            }
            if(i1 == 3)
            {
                l1++;
            }
            int i2 = getEffectiveFlowDecay(iblockaccess, j1, k1, l1);
            if(i2 < 0)
            {
                if(iblockaccess.getBlockMaterial(j1, k1, l1).getIsSolid())
                {
                    continue;
                }
                i2 = getEffectiveFlowDecay(iblockaccess, j1, k1 - 1, l1);
                if(i2 >= 0)
                {
                    int j2 = i2 - (l - 8);
                    vec3d = vec3d.addVector((j1 - i) * j2, (k1 - j) * j2, (l1 - k) * j2);
                }
                continue;
            }
            if(i2 >= 0)
            {
                int k2 = i2 - l;
                vec3d = vec3d.addVector((j1 - i) * k2, (k1 - j) * k2, (l1 - k) * k2);
            }
        }

        if(iblockaccess.getBlockMetadata(i, j, k) >= 8)
        {
            boolean flag = false;
            if(flag || shouldSideBeRendered(iblockaccess, i, j, k - 1, 2))
            {
                flag = true;
            }
            if(flag || shouldSideBeRendered(iblockaccess, i, j, k + 1, 3))
            {
                flag = true;
            }
            if(flag || shouldSideBeRendered(iblockaccess, i - 1, j, k, 4))
            {
                flag = true;
            }
            if(flag || shouldSideBeRendered(iblockaccess, i + 1, j, k, 5))
            {
                flag = true;
            }
            if(flag || shouldSideBeRendered(iblockaccess, i, j + 1, k - 1, 2))
            {
                flag = true;
            }
            if(flag || shouldSideBeRendered(iblockaccess, i, j + 1, k + 1, 3))
            {
                flag = true;
            }
            if(flag || shouldSideBeRendered(iblockaccess, i - 1, j + 1, k, 4))
            {
                flag = true;
            }
            if(flag || shouldSideBeRendered(iblockaccess, i + 1, j + 1, k, 5))
            {
                flag = true;
            }
            if(flag)
            {
                vec3d = vec3d.normalize().addVector(0.0D, -6D, 0.0D);
            }
        }
        vec3d = vec3d.normalize();
        return vec3d;
    }

    public void velocityToAddToEntity(World world, int i, int j, int k, Entity entity, Vec3D vec3d)
    {
        Vec3D vec3d1 = getFlowVector(world, i, j, k);
        vec3d.xCoord += vec3d1.xCoord;
        vec3d.yCoord += vec3d1.yCoord;
        vec3d.zCoord += vec3d1.zCoord;
    }

    public int tickRate()
    {
		if(blockIndexInTexture == mod_PlasmaCraft.acidStillIndex || blockIndexInTexture == mod_PlasmaCraft.acidMovingIndex)
		{
			return 3;
		}

		if(blockIndexInTexture == mod_PlasmaCraft.radioniteStillIndex || blockIndexInTexture == mod_PlasmaCraft.radioniteMovingIndex)
		{
			return 20;
		}

		if(blockIndexInTexture == mod_PlasmaCraft.plutoniumStillIndex || blockIndexInTexture == mod_PlasmaCraft.plutoniumMovingIndex)
		{
			return 8;
		}

		if(blockIndexInTexture == mod_PlasmaCraft.netherflowStillIndex || blockIndexInTexture == mod_PlasmaCraft.netherflowMovingIndex)
		{
			return 5;
		}
		
		if(blockIndexInTexture == mod_PlasmaCraft.cryoniteStillIndex || blockIndexInTexture == mod_PlasmaCraft.cryoniteMovingIndex)
		{
			return 6;
		}

		if(blockIndexInTexture == mod_PlasmaCraft.neptuniumStillIndex || blockIndexInTexture == mod_PlasmaCraft.neptuniumMovingIndex)
		{
			return 10;
		}

		if(blockIndexInTexture == mod_PlasmaCraft.uraniumStillIndex || blockIndexInTexture == mod_PlasmaCraft.uraniumMovingIndex)
		{
			return 15;
		}

		if(blockIndexInTexture == mod_PlasmaCraft.obsidiumStillIndex || blockIndexInTexture == mod_PlasmaCraft.obsidiumMovingIndex)
		{
			return 25;
		}

		return 5;
    }

    public float getBlockBrightness(IBlockAccess iblockaccess, int i, int j, int k)
    {
        float f = iblockaccess.getLightBrightness(i, j, k);
        float f1 = iblockaccess.getLightBrightness(i, j + 1, k);
        return f <= f1 ? f1 : f;
    }
    
    //TODO
    public int getMixedBrightnessForBlock(IBlockAccess iblockaccess, int i, int j, int k)
    {
        int l = iblockaccess.getLightBrightnessForSkyBlocks(i, j, k, 0);
        int i1 = iblockaccess.getLightBrightnessForSkyBlocks(i, j + 1, k, 0);
        int j1 = l & 0xff;
        int k1 = i1 & 0xff;
        int l1 = l >> 16 & 0xff;
        int i2 = i1 >> 16 & 0xff;
        return (j1 <= k1 ? k1 : j1) | (l1 <= i2 ? i2 : l1) << 16;
    }

    public void updateTick(World world, int i, int j, int k, Random random)
    {
		if(armorTick > 0)
		{
			armorTick--;
		}
        super.updateTick(world, i, j, k, random);
    }

    public int getRenderBlockPass()
    {
    	return blockMaterial != Material.water ? 0 : 1;
    }

    public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
        if(random.nextInt(64) == 0)
        {
            int l = world.getBlockMetadata(i, j, k);
            if(l > 0 && l < 8)
            {
                world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, "liquid.water", random.nextFloat() * 0.25F + 0.75F, random.nextFloat() * 1.0F + 0.5F);
            }
        }
    }

    public double func_293_a(IBlockAccess iblockaccess, int i, int j, int k, Material material)
    {
        Vec3D vec3d = null;
		vec3d = getFlowVector(iblockaccess, i, j, k);
        if(vec3d == null || (vec3d.xCoord == 0.0D && vec3d.zCoord == 0.0D))
        {
            return -1000D;
        }
        else
        {
            return Math.atan2(vec3d.zCoord, vec3d.xCoord) - 1.5707963267948966D;
        }
    }

    public void onBlockAdded(World world, int i, int j, int k)
    {
        checkForHarden(world, i, j, k);
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
        checkForHarden(world, i, j, k);
    }

	private boolean blockAdjoinsBlockID(World world, int i, int j, int k, int id)
	{
		boolean flag = false;
		if(world.getBlockMaterial(i, j, k - 1) == Material.water)
		{
			if(world.getBlockId(i, j, k - 1) == id) flag = true;
		}

		if(world.getBlockMaterial(i, j, k + 1) == Material.water)
		{
			if(world.getBlockId(i, j, k + 1) == id) flag = true;
		}

		if(world.getBlockMaterial(i - 1, j, k) == Material.water)
		{
			if(world.getBlockId(i - 1, j, k) == id) flag = true;
		}

		if(world.getBlockMaterial(i + 1, j, k) == Material.water)
		{
			if(world.getBlockId(i + 1, j, k) == id) flag = true;
		}

		if(world.getBlockMaterial(i, j - 1, k) == Material.water)
		{
			if(world.getBlockId(i, j - 1, k) == id) flag = true;
		}
		return flag;
	}

	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
		onEntityWalking(world, i, j, k, entity);
    }

    public void onEntityWalking(World world, int i,int j, int k, Entity entity)
    {
		/*if(entity instanceof EntityCow)
		{
            SMEntityMutantCow mcow = new SMEntityMutantCow(world);
            mcow.setLocationAndAngles(i, j, k, entity.rotationPitch, entity.rotationYaw);
            world.entityJoinedWorld(mcow);
			entity.setEntityDead();
			return;
		}*/
		
		if(entity instanceof EntityPlayer || entity instanceof EntityPlayerSP)
		{
			EntityPlayer player = (EntityPlayer)entity;
			if(player.hurtTime > 0)
			{
				return;
			}

			ItemStack helmetStack = player.inventory.armorItemInSlot(3);
			ItemStack plateStack = player.inventory.armorItemInSlot(2);
			ItemStack legsStack = player.inventory.armorItemInSlot(1);
			ItemStack bootsStack = player.inventory.armorItemInSlot(0);
			if(helmetStack == null || plateStack == null || legsStack == null || bootsStack == null)
			{
				if(player.ridingEntity != null)
				{
					if(player.ridingEntity instanceof SMEntityCausticBoat)
					{
						return;
					}
				}
				player.attackEntityFrom(DamageSource.lava, 3);
				return;
			}
			else
			{
				boolean helmetValid = (helmetStack.itemID == mod_PlasmaCraft.helmetHazmat.shiftedIndex);
				boolean plateValid = (plateStack.itemID == mod_PlasmaCraft.plateHazmat.shiftedIndex);
				boolean legsValid = (legsStack.itemID == mod_PlasmaCraft.legsHazmat.shiftedIndex);
				boolean bootsValid = (bootsStack.itemID == mod_PlasmaCraft.bootsHazmat.shiftedIndex);
				if(helmetValid && plateValid && legsValid && bootsValid)
				{
					if(armorTick == 0)
					{
						player.inventory.damageArmor(1);
						armorTick = 10;
					}
				}
				else
				{
					player.func_40046_d(20);
					player.attackEntityFrom(DamageSource.lava, 3);
				}
			}
		}
		else if(entity instanceof EntityLiving)
		{
			EntityLiving living = (EntityLiving)entity;
			if(living.hurtTime > 0)
			{
				return;
			}
			living.func_40046_d(20);
			living.attackEntityFrom(DamageSource.lava, 3);
		}
		else if(!(entity instanceof SMEntityCausticBoat))
		{
			entity.func_40046_d(20);
			entity.attackEntityFrom(DamageSource.lava, 10);
		}
    }

	private void setAdjoiningLavaIDs(World world, int i, int j, int k, int id)
	{
		if(world.getBlockId(i - 1, j, k) == Block.lavaStill.blockID || world.getBlockId(i - 1, j, k) == Block.lavaMoving.blockID)
		{
			world.setBlockWithNotify(i - 1, j, k, id);
		}
		if(world.getBlockId(i + 1, j, k) == Block.lavaStill.blockID || world.getBlockId(i + 1, j, k) == Block.lavaMoving.blockID)
		{
			world.setBlockWithNotify(i + 1, j, k, id);
		}
		if(world.getBlockId(i, j - 1, k) == Block.lavaStill.blockID || world.getBlockId(i, j - 1, k) == Block.lavaMoving.blockID)
		{
			world.setBlockWithNotify(i, j - 1, k, id);
		}
		if(world.getBlockId(i, j, k - 1) == Block.lavaStill.blockID || world.getBlockId(i, j, k - 1) == Block.lavaMoving.blockID)
		{
			world.setBlockWithNotify(i, j, k - 1, id);
		}
		if(world.getBlockId(i, j, k + 1) == Block.lavaStill.blockID || world.getBlockId(i, j, k + 1) == Block.lavaMoving.blockID)
		{
			world.setBlockWithNotify(i, j, k + 1, id);
		}
	}

	private void setAdjoiningWaterIDs(World world, int i, int j, int k, int id)
	{
		if(world.getBlockId(i - 1, j, k) == Block.waterStill.blockID || world.getBlockId(i - 1, j, k) == Block.waterMoving.blockID)
		{
			world.setBlockWithNotify(i - 1, j, k, id);
		}
		if(world.getBlockId(i + 1, j, k) == Block.waterStill.blockID || world.getBlockId(i + 1, j, k) == Block.waterMoving.blockID)
		{
			world.setBlockWithNotify(i + 1, j, k, id);
		}
		if(world.getBlockId(i, j - 1, k) == Block.waterStill.blockID || world.getBlockId(i, j - 1, k) == Block.waterMoving.blockID)
		{
			world.setBlockWithNotify(i, j - 1, k, id);
		}
		if(world.getBlockId(i, j, k - 1) == Block.waterStill.blockID || world.getBlockId(i, j, k - 1) == Block.waterMoving.blockID)
		{
			world.setBlockWithNotify(i, j, k - 1, id);
		}
		if(world.getBlockId(i, j, k + 1) == Block.waterStill.blockID || world.getBlockId(i, j, k + 1) == Block.waterMoving.blockID)
		{
			world.setBlockWithNotify(i, j, k + 1, id);
		}
	}

    private void checkForHarden(World world, int i, int j, int k)
    {
        if(world.getBlockId(i, j, k) != blockID)
        {
            return;
        }

		if(blockIndexInTexture == mod_PlasmaCraft.acidStillIndex || blockIndexInTexture == mod_PlasmaCraft.acidMovingIndex)
		{

		    if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
			{
				setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
			{
				setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.radioniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.radioniteFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.plutoniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.plutoniumFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.neptuniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.neptuniumFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.netherflowStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.netherflowFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.uraniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.uraniumFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.obsidiumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.obsidiumFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.cryoniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.cryoniteFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.cobblestone.blockID);
			}
		 
		}
		else if(blockIndexInTexture == mod_PlasmaCraft.radioniteStillIndex || blockIndexInTexture == mod_PlasmaCraft.radioniteMovingIndex)
		{
			if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
			{
				setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
			{
				setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.acidStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.acidFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.plutoniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.plutoniumFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.neptuniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.neptuniumFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.netherflowStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.netherflowFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.glowStone.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.uraniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.uraniumFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.obsidiumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.obsidiumFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
			}
		}
		else if(blockIndexInTexture == mod_PlasmaCraft.plutoniumStillIndex || blockIndexInTexture == mod_PlasmaCraft.plutoniumMovingIndex)
		{
			if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
			{
				setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
			{
				setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.acidStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.acidFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.radioniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.radioniteFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
				
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.neptuniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.neptuniumFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.netherflowStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.netherflowFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.netherrack.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.uraniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.uraniumFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.obsidiumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.obsidiumFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.cryoniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.cryoniteFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
		}
		else if(blockIndexInTexture == mod_PlasmaCraft.neptuniumStillIndex || blockIndexInTexture == mod_PlasmaCraft.neptuniumMovingIndex)
		{
			if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
			{
				setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
			{
				setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.acidStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.acidFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.radioniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.radioniteFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.plutoniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.plutoniumFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.netherflowStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.netherflowFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.netherrack.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.uraniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.uraniumFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.sand.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.obsidiumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.obsidiumFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.cryoniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.cryoniteFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
		}
		else if(blockIndexInTexture == mod_PlasmaCraft.netherflowStillIndex || blockIndexInTexture == mod_PlasmaCraft.netherflowMovingIndex)
		{
			if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
			{
				setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
			{
				setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.acidStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.acidFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.radioniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.radioniteFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.glowStone.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.plutoniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.plutoniumFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.netherrack.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.neptuniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.neptuniumFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.netherrack.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.uraniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.uraniumFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.slowSand.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.obsidiumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.obsidiumFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.cryoniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.cryoniteFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.glowStone.blockID);
			}
		}
		else if(blockIndexInTexture == mod_PlasmaCraft.uraniumStillIndex || blockIndexInTexture == mod_PlasmaCraft.uraniumMovingIndex)
		{
			if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
			{
				setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
			{
				setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.acidStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.acidFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.radioniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.radioniteFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.plutoniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.plutoniumFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.neptuniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.neptuniumFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.sand.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.netherflowStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.netherflowFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.slowSand.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.obsidiumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.obsidiumFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.cryoniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.cryoniteFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 8); // TBD
			}
		}
		else if(blockIndexInTexture == mod_PlasmaCraft.obsidiumStillIndex || blockIndexInTexture == mod_PlasmaCraft.obsidiumMovingIndex)
		{
			if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
			{
				setAdjoiningLavaIDs(world, i, j, k, Block.sand.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
			{
				setAdjoiningWaterIDs(world, i, j, k, Block.blockClay.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.acidStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.acidFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.radioniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.radioniteFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.plutoniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.plutoniumFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.neptuniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.neptuniumFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.netherflowStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.netherflowFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.uraniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.uraniumFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.cryoniteStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.cryoniteFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
			} 
		}
		else if(blockIndexInTexture == mod_PlasmaCraft.cryoniteStillIndex || blockIndexInTexture == mod_PlasmaCraft.cryoniteMovingIndex)
		{
			if(blockAdjoinsBlockID(world, i, j, k, Block.lavaStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.lavaMoving.blockID))
			{
				setAdjoiningLavaIDs(world, i, j, k, Block.cobblestone.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, Block.waterStill.blockID) || blockAdjoinsBlockID(world, i, j, k, Block.waterMoving.blockID))
			{
				setAdjoiningWaterIDs(world, i, j, k, Block.ice.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.acidStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.acidFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.cobblestone.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.plutoniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.plutoniumFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.neptuniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.neptuniumFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 4); // TBD
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.netherflowStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.netherflowFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.glowStone.blockID);
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.uraniumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.uraniumFlowingBlockID))
			{
				world.createExplosion(null, i, j, k, 8); // TBD
			}
			else if(blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.obsidiumStillBlockID) || blockAdjoinsBlockID(world, i, j, k, mod_PlasmaCraft.obsidiumFlowingBlockID))
			{
				world.setBlockWithNotify(i, j, k, Block.obsidian.blockID);
			} 
		}
    }

    protected void triggerLavaMixEffects(World world, int i, int j, int k)
    {
        world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);
        for(int l = 0; l < 8; l++)
        {
            world.spawnParticle("largesmoke", (double)i + Math.random(), (double)j + 1.2D, (double)k + Math.random(), 0.0D, 0.0D, 0.0D);
        }
    }
    
    public int renderID;
    public int stillTextureID;
    public int flowingTextureID;
    public int stillBlockID;
    public int flowingBlockID;
    public int armorTick;
}
