package com.elvenwater.malkierian.Plasmacraft.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class AcidExplosion extends Explosion
{

	public boolean isFlaming;
	protected Random ExplosionRNG;
	protected World worldObj;
	public double posX;
	public double posY;
	public double posZ;
	public Entity exploder;
	public float radius;
	public Set<ChunkPosition> destroyedBlockPositions;

	public AcidExplosion(World world, Entity entity, double d, double d1, double d2, float f)
	{
		super(world, entity, d, d1, d2, f);
		isFlaming = false;
		destroyedBlockPositions = new HashSet<ChunkPosition>();
		ExplosionRNG = new Random();
		worldObj = world;
		exploder = entity;
		radius = f;
		posX = d;
		posY = d1;
		posZ = d2;
	}

	public void doExplosionA()
	{
		float f = radius;
		int i = 16;
		for(int j = 0; j < i; j++)
		{
			for(int l = 0; l < i; l++)
			{
label0:
				for(int j1 = 0; j1 < i; j1++)
				{
					if(j != 0 && j != i - 1 && l != 0 && l != i - 1 && j1 != 0 && j1 != i - 1)
					{
						continue;
					}
					double d = ((float)j / ((float)i - 1.0F)) * 2.0F - 1.0F;
					double d1 = ((float)l / ((float)i - 1.0F)) * 2.0F - 1.0F;
					double d2 = ((float)j1 / ((float)i - 1.0F)) * 2.0F - 1.0F;
					double d3 = Math.sqrt(d * d + d1 * d1 + d2 * d2);
					d /= d3;
					d1 /= d3;
					d2 /= d3;
					float f1 = radius * 0.8F;
					double d5 = posX;
					double d7 = posY;
					double d9 = posZ;
					float f2 = 0.3F;
					do
					{
						if(f1 <= 0.0F)
						{
							continue label0;
						}
						int j4 = MathHelper.floor_double(d5);
						int k4 = MathHelper.floor_double(d7);
						int l4 = MathHelper.floor_double(d9);
						int i5 = worldObj.getBlockId(j4, k4, l4);
						if(i5 > 0)
						{
//							if (Block.blocksList[i5] instanceof ISpecialResistance)
//							{
//								ISpecialResistance isr = (ISpecialResistance)Block.blocksList[i5];
//								f1 -= (isr.getSpecialExplosionResistance(worldObj, j4, k4, l4, posX, posY, posZ, exploder) + 0.3F) * f2;
//							} 
//							else 
//							{
								f1 -= (Block.blocksList[i5].getExplosionResistance(exploder) + 0.3F) * f2;
//							}
						}
						if(f1 > 0.0F)
						{
							destroyedBlockPositions.add(new ChunkPosition(j4, k4, l4));
						}
						d5 += d * (double)f2;
						d7 += d1 * (double)f2;
						d9 += d2 * (double)f2;
						f1 -= f2 * 0.75F;
					} while(true);
				}

			}

		}

		radius *= 2.0F;
		int k = MathHelper.floor_double(posX - (double)radius - 1.0D);
		int i1 = MathHelper.floor_double(posX + (double)radius + 1.0D);
		int k1 = MathHelper.floor_double(posY - (double)radius - 1.0D);
		int l1 = MathHelper.floor_double(posY + (double)radius + 1.0D);
		int i2 = MathHelper.floor_double(posZ - (double)radius - 1.0D);
		int j2 = MathHelper.floor_double(posZ + (double)radius + 1.0D);
		List<?> list = worldObj.getEntitiesWithinAABBExcludingEntity(exploder, AxisAlignedBB.getBoundingBox(k, k1, i2, i1, l1, j2));
		Vec3 vec3d = Vec3.createVectorHelper(posX, posY, posZ);
		for(int k2 = 0; k2 < list.size(); k2++)
		{
			Entity entity = (Entity)list.get(k2);
			double d4 = entity.getDistance(posX, posY, posZ) / (double)radius;
			if(d4 <= 1.0D)
			{
				double d6 = entity.posX - posX;
				double d8 = entity.posY - posY;
				double d10 = entity.posZ - posZ;
				double d11 = MathHelper.sqrt_double(d6 * d6 + d8 * d8 + d10 * d10);
				d6 /= d11;
				d8 /= d11;
				d10 /= d11;
				double d12 = worldObj.getBlockDensity(vec3d, entity.boundingBox);
				double d13 = (1.0D - d4) * d12;
				// TODO entity damage
//				entity.attackEntityFrom(DamageSource.explosion, (int)(((d13 * d13 + d13) / 2D) * 8D * (double)radius + 1.0D));
				double d14 = d13;
				entity.motionX += d6 * d14;
				entity.motionY += d8 * d14;
				entity.motionZ += d10 * d14;
			}
		}

		radius = f;
		ArrayList<ChunkPosition> arraylist = new ArrayList<ChunkPosition>();
		arraylist.addAll(destroyedBlockPositions);
		if(isFlaming)
		{
			for(int l2 = arraylist.size() - 1; l2 >= 0; l2--)
			{
				ChunkPosition chunkposition = (ChunkPosition)arraylist.get(l2);
				int i3 = chunkposition.x;
				int j3 = chunkposition.y;
				int k3 = chunkposition.z;
				int l3 = worldObj.getBlockId(i3, j3, k3);
				int i4 = worldObj.getBlockId(i3, j3 - 1, k3);
				if(l3 == 0 && Block.opaqueCubeLookup[i4] && ExplosionRNG.nextInt(3) == 0)
				{
					worldObj.setBlock(i3, j3, k3, Block.fire.blockID, 0, 0);
				}
			}

		}
	}

	private boolean isFlowable(int i)
	{
		return i == 0 || i == Block.doorWood.blockID || i == Block.doorSteel.blockID || i == Block.signPost.blockID || i == Block.ladder.blockID || i == Block.reed.blockID || i == Block.redstoneWire.blockID;
	}

	public void doExplosionB()
	{
		worldObj.playSoundEffect(posX, posY, posZ, "random.explode", 4F, (1.0F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
		ArrayList<ChunkPosition> arraylist = new ArrayList<ChunkPosition>();
		arraylist.addAll(destroyedBlockPositions);
		for(int i = arraylist.size() - 1; i >= 0; i--)
		{
			ChunkPosition chunkposition = (ChunkPosition)arraylist.get(i);
			int i1 = chunkposition.x;
			int l1 = chunkposition.y;
			int k2 = chunkposition.z;
			int i3 = worldObj.getBlockId(i1, l1, k2);
			for(int k3 = 0; k3 < 1; k3++)
			{
				double d1 = (float)i1 + worldObj.rand.nextFloat();
				double d2 = (float)l1 + worldObj.rand.nextFloat();
				double d3 = (float)k2 + worldObj.rand.nextFloat();
				double d4 = d1 - posX;
				double d5 = d2 - posY;
				double d6 = d3 - posZ;
				double d7 = MathHelper.sqrt_double(d4 * d4 + d5 * d5 + d6 * d6);
				d4 /= d7;
				d5 /= d7;
				d6 /= d7;
				double d8 = 0.5D / (d7 / (double)radius + 0.10000000000000001D);
				d8 *= worldObj.rand.nextFloat() * worldObj.rand.nextFloat() + 0.3F;
				d4 *= d8;
				d5 *= d8;
				d6 *= d8;
				worldObj.spawnParticle("explode", (d1 + posX * 1.0D) / 2D, (d2 + posY * 1.0D) / 2D, (d3 + posZ * 1.0D) / 2D, d4, d5, d6);
				worldObj.spawnParticle("smoke", d1, d2, d3, d4, d5, d6);
			}

			if(i3 > 0)
			{
				Block.blocksList[i3].dropBlockAsItemWithChance(worldObj, i1, l1, k2, worldObj.getBlockMetadata(i1, l1, k2), 0.3F, i3);
				worldObj.setBlock(i1, l1, k2, 0, 0, 0);
				Block.blocksList[i3].onBlockDestroyedByExplosion(worldObj, i1, l1, k2, this);
			}
		}

		for(int j = arraylist.size() - 1; j >= 0; j--)
		{
			ChunkPosition chunkposition1 = (ChunkPosition)arraylist.get(j);
			int j1 = chunkposition1.x;
			int i2 = chunkposition1.y;
			int l2 = chunkposition1.z;
			int j3 = worldObj.getBlockId(j1, i2, l2);
			double d = (double)i2 - posY;
			if(d < -2D && j3 == 0)
			{
				worldObj.setBlock(j1, i2, l2, PlasmaCraft.acidMoving.blockID, 0, 0);
			}
		}

		for(int k = (int)(posX - (double)(radius + 1.0F)); k <= (int)(posX + (double)(radius + 1.0F)); k++)
		{
			for(int l = (int)(posY - (double)(radius + 1.0F)); l <= (int)(posY + (double)(radius + 1.0F)); l++)
			{
				for(int k1 = (int)(posZ - (double)(radius + 1.0F)); k1 <= (int)(posZ + (double)(radius + 1.0F)); k1++)
				{
					int j2 = worldObj.getBlockId(k, l, k1);
					if(j2 != PlasmaCraft.acidMoving.blockID && j2 != PlasmaCraft.acidStill.blockID)
					{
						continue;
					}
					boolean flag = isFlowable(worldObj.getBlockId(k - 1, l, k1));
					boolean flag1 = isFlowable(worldObj.getBlockId(k + 1, l, k1));
					boolean flag2 = isFlowable(worldObj.getBlockId(k, l, k1 + 1));
					boolean flag3 = isFlowable(worldObj.getBlockId(k, l, k1 - 1));
					boolean flag4 = isFlowable(worldObj.getBlockId(k, l - 1, k1));
					if(flag || flag1 || flag2 || flag3 || flag4)
					{
						worldObj.setBlock(k, l, k1, Block.cobblestone.blockID, 0, 0);
					}
				}

			}

		}

	}
}
