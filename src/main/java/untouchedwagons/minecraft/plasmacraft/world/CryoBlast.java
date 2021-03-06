package untouchedwagons.minecraft.plasmacraft.world;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import untouchedwagons.minecraft.plasmacraft.PlasmaCraft;

public class CryoBlast extends Explosion
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

	public CryoBlast(World world, Entity entity, double d, double d1, double d2, float f)
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
			for(int k = 0; k < i; k++)
			{
label0:
				for(int l = 0; l < i; l++)
				{
					if(j != 0 && j != i - 1 && k != 0 && k != i - 1 && l != 0 && l != i - 1)
					{
						continue;
					}
					double d = ((float)j / ((float)i - 1.0F)) * 2.0F - 1.0F;
					double d1 = ((float)k / ((float)i - 1.0F)) * 2.0F - 1.0F;
					double d2 = ((float)l / ((float)i - 1.0F)) * 2.0F - 1.0F;
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
						int l2 = MathHelper.floor_double(d5);
						int i3 = MathHelper.floor_double(d7);
						int j3 = MathHelper.floor_double(d9);
						Block k3 = worldObj.getBlock(l2, i3, j3);
						if(k3 != Blocks.air)
						{
							f1 -= (k3.getExplosionResistance(exploder) + 0.3F) * f2;
						}
						if(f1 > 0.0F)
						{
							destroyedBlockPositions.add(new ChunkPosition(l2, i3, j3));
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
				entity.attackEntityFrom(DamageSource.setExplosionSource(this), (int)(((d13 * d13 + d13) / 2D) * 8D * (double)radius + 1.0D));
				if(entity instanceof EntityLiving)
				{
					EntityLiving el = (EntityLiving)entity;
					el.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 10 * 20, 4));
				}
				double d14 = d13;
				entity.motionX += d6 * d14;
				entity.motionY += d8 * d14;
				entity.motionZ += d10 * d14;
			}
		}

		radius = f;
		ArrayList<ChunkPosition> arraylist = new ArrayList<ChunkPosition>();
		arraylist.addAll(destroyedBlockPositions);
	}

	private boolean isFlowable(Block i)
	{
		return i == Blocks.air || i == Blocks.wooden_door || i == Blocks.iron_door || i == Blocks.standing_sign || i == Blocks.wall_sign || i == Blocks.ladder || i == Blocks.reeds || i == Blocks.redstone_wire;
	}

	public void doExplosionB()
	{
		worldObj.playSoundEffect(posX, posY, posZ, "random.explode", 4F, (1.0F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
		ArrayList<ChunkPosition> arraylist = new ArrayList<ChunkPosition>();
		arraylist.addAll(destroyedBlockPositions);
		for(int i = arraylist.size() - 1; i >= 0; i--)
		{
			ChunkPosition chunkposition = arraylist.get(i);
			int i1 = chunkposition.chunkPosX;
			int l1 = chunkposition.chunkPosY;
			int k2 = chunkposition.chunkPosZ;
			Block i3 = worldObj.getBlock(i1, l1, k2);
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

			if(i3 != Blocks.air)
			{
				continue;
			}
			i3.dropBlockAsItemWithChance(worldObj, i1, l1, k2, worldObj.getBlockMetadata(i1, l1, k2), 0.3F, 0);
			worldObj.setBlockToAir(i1, l1, k2);
			i3.onBlockDestroyedByExplosion(worldObj, i1, l1, k2, this);
			
			int r = ExplosionRNG.nextInt(10);
			if(r < 7 && worldObj.isAirBlock(i1, l1, k2) && Blocks.snow.canBlockStay(worldObj, i1, l1, k2))
			{
				worldObj.setBlock(i1, l1, k2, Blocks.snow);
			}
			else if(r < 9 && worldObj.isAirBlock(i1, l1, k2) && Blocks.ice.canBlockStay(worldObj, i1, l1, k2))
			{
				worldObj.setBlock(i1, l1, k2, Blocks.ice);
			}
			else if(worldObj.isAirBlock(i1, l1, k2) && PlasmaCraft.blocks.frozenCryonite.canBlockStay(worldObj, i1, l1, k2))
			{
				worldObj.setBlock(i1, l1, k2, PlasmaCraft.blocks.frozenCryonite);
			}
			
			if(worldObj.getBlock(i1, l1, k2) == Blocks.water || worldObj.getBlock(i1, l1 + 1, k2) == Blocks.water)
			{
				worldObj.setBlock(i1, l1, k2, Blocks.ice);
				worldObj.setBlock(i1, l1 + 1, k2, Blocks.snow);
            }
		}
	}
}
