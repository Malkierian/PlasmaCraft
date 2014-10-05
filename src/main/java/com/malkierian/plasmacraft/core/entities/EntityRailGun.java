package com.malkierian.plasmacraft.core.entities;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityRailGun extends Entity
{
	private int xTile;
	private int yTile;
	private int zTile;
	private Block inTile;
	private boolean inGround;
	public EntityLivingBase owner;
	private int ticksInAir;

	public EntityRailGun(World world)
	{
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		inTile = null;
		inGround = false;
		ticksInAir = 0;
		setSize(0.5F, 0.5F);
	}

	public EntityRailGun(World world, double d, double d1, double d2)
	{
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		inTile = null;
		inGround = false;
		ticksInAir = 0;
		setSize(0.5F, 0.5F);
		setPosition(d, d1, d2);
		yOffset = 0.0F;
	}

	public EntityRailGun(World world, EntityLivingBase entityliving)
	{
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		inTile = null;
		inGround = false;
		ticksInAir = 0;
		owner = entityliving;
		setSize(0.5F, 0.5F);
		setLocationAndAngles(entityliving.posX, entityliving.posY + (double)entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
		posX -= MathHelper.cos((rotationYaw / 180F) * 3.141593F) * 0.16F;
		posY -= 0.10000000149011612D;
		posZ -= MathHelper.sin((rotationYaw / 180F) * 3.141593F) * 0.16F;
		setPosition(posX, posY, posZ);
		yOffset = 0.0F;
		motionX = -MathHelper.sin((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F);
		motionZ = MathHelper.cos((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F);
		motionY = -MathHelper.sin((rotationPitch / 180F) * 3.141593F);
		setArrowHeading(motionX, motionY, motionZ, 10F, 1.0F);
	}

	protected void entityInit()
	{
	}

	public void setArrowHeading(double d, double d1, double d2, float f, 
			float f1)
	{
		float f2 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
		d /= f2;
		d1 /= f2;
		d2 /= f2;
		d += rand.nextGaussian() * 9.9999832361936594E-005D * (double)f1;
		d1 += rand.nextGaussian() * 9.9999832361936594E-005D * (double)f1;
		d2 += rand.nextGaussian() * 9.9999832361936594E-005D * (double)f1;
		d *= f;
		d1 *= f;
		d2 *= f;
		motionX = d;
		motionY = d1;
		motionZ = d2;
		float f3 = MathHelper.sqrt_double(d * d + d2 * d2);
		prevRotationYaw = rotationYaw = (float)((Math.atan2(d, d2) * 180D) / 3.1415927410125732D);
		prevRotationPitch = rotationPitch = (float)((Math.atan2(d1, f3) * 180D) / 3.1415927410125732D);
	}

	public void setVelocity(double d, double d1, double d2)
	{
		motionX = d;
		motionY = d1;
		motionZ = d2;
		if(prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
		{
			float f = MathHelper.sqrt_double(d * d + d2 * d2);
			prevRotationYaw = rotationYaw = (float)((Math.atan2(d, d2) * 180D) / 3.1415927410125732D);
			prevRotationPitch = rotationPitch = (float)((Math.atan2(d1, f) * 180D) / 3.1415927410125732D);
		}
	}

	public int tickRate()
	{
		return 10;
	}

	public void onUpdate()
	{
		super.onUpdate();
		if(prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
		{
			float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
			prevRotationYaw = rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
			prevRotationPitch = rotationPitch = (float)((Math.atan2(motionY, f) * 180D) / 3.1415927410125732D);
		}
		ticksInAir++;
		Vec3 vec3d = Vec3.createVectorHelper(posX, posY, posZ);
		Vec3 vec3d1 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		MovingObjectPosition movingobjectposition = worldObj.rayTraceBlocks(vec3d, vec3d1);
		vec3d = Vec3.createVectorHelper(posX, posY, posZ);
		vec3d1 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		if(movingobjectposition != null)
		{
			vec3d1 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
		}
		Entity entity = null;
		List<?> list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
		double d = 0.0D;
		for(int i = 0; i < list.size(); i++)
		{
			Entity entity1 = (Entity)list.get(i);
			if(!entity1.canBeCollidedWith() || entity1 == owner && ticksInAir < 5)
			{
				continue;
			}
			float f3 = 0.3F;
			AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f3, f3, f3);
			MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);
			if(movingobjectposition1 == null)
			{
				continue;
			}
			double d1 = vec3d.distanceTo(movingobjectposition1.hitVec);
			if(d1 < d || d == 0.0D)
			{
				entity = entity1;
				d = d1;
			}
		}

		if(entity != null)
		{
			movingobjectposition = new MovingObjectPosition(entity);
		}
		if(movingobjectposition != null)
		{
			if(movingobjectposition.entityHit != null)
			{
				if(movingobjectposition.entityHit == owner)
				{
					return;
				}
				if(movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, owner), 12))
				{
					int j = MathHelper.floor_double(movingobjectposition.entityHit.boundingBox.minX);
					int l = MathHelper.floor_double(movingobjectposition.entityHit.boundingBox.minY);
					int j1 = MathHelper.floor_double(movingobjectposition.entityHit.boundingBox.minZ);
					worldObj.setBlock(j, l, j1, Blocks.fire);
				}
			}
			else
			{
				int k = movingobjectposition.blockX;
				int i1 = movingobjectposition.blockY;
				int k1 = movingobjectposition.blockZ;
				boolean flag = true;
				if(worldObj.getBlock(k, i1, k1) == Blocks.ice)
				{
					worldObj.setBlock(k, i1, k1, Blocks.water);
					flag = false;
				}
				if(worldObj.getBlock(k, i1, k1) == Blocks.tallgrass)
				{
					worldObj.setBlock(k, i1, k1, Blocks.fire);
					flag = false;
				}
				if(worldObj.getBlock(k, i1, k1) == Blocks.snow)
				{
					worldObj.setBlock(k, i1, k1, Blocks.fire);
					flag = false;
				}
				if(worldObj.getBlock(k, i1, k1) == Blocks.red_flower)
				{
					worldObj.setBlock(k, i1, k1, Blocks.fire);
					flag = false;
				}
				if(worldObj.getBlock(k, i1, k1) == Blocks.yellow_flower)
				{
					worldObj.setBlock(k, i1, k1, Blocks.fire);
					flag = false;
				}
				if(Blocks.fire.canBlockStay(worldObj, k, i1, k1) && worldObj.isAirBlock(k, i1, k1)&&flag)
				{
					worldObj.setBlock(k, i1, k1, Blocks.fire);
				}
				if(Blocks.fire.canBlockStay(worldObj, k, i1, k1 + 1) && worldObj.isAirBlock(k, i1, k1 + 1)&&flag)
				{
					worldObj.setBlock(k, i1, k1 + 1, Blocks.fire);
				}
				if(Blocks.fire.canBlockStay(worldObj, k, i1, k1 - 1) && worldObj.isAirBlock(k, i1, k1 - 1)&&flag)
				{
					worldObj.setBlock(k, i1, k1 - 1, Blocks.fire);
				}
				if(Blocks.fire.canBlockStay(worldObj, k, i1 + 1, k1) && worldObj.isAirBlock(k, i1 + 1, k1)&&flag)
				{
					worldObj.setBlock(k, i1 + 1, k1, Blocks.fire);
				}
				if(Blocks.fire.canBlockStay(worldObj, k, i1 - 1, k1) && worldObj.isAirBlock(k, i1 - 1, k1)&&flag)
				{
					worldObj.setBlock(k, i1 - 1, k1, Blocks.fire);
				}
				if(Blocks.fire.canBlockStay(worldObj, k + 1, i1, k1) && worldObj.isAirBlock(k + 1, i1, k1 )&&flag)
				{
					worldObj.setBlock(k + 1, i1, k1, Blocks.fire);
				}
				if(Blocks.fire.canBlockStay(worldObj, k - 1, i1, k1) && worldObj.isAirBlock(k - 1, i1, k1 )&&flag)
				{
					worldObj.setBlock(k - 1, i1, k1, Blocks.fire);
				}
				xTile = movingobjectposition.blockX;
				yTile = movingobjectposition.blockY;
				zTile = movingobjectposition.blockZ;
				inTile = worldObj.getBlock(xTile, yTile, zTile);
				if(inTile != null && inTile.getBlockHardness(worldObj, xTile, yTile, zTile) > 100)
				{
					motionX = (float)(movingobjectposition.hitVec.xCoord - posX);
					motionY = (float)(movingobjectposition.hitVec.yCoord - posY);
					motionZ = (float)(movingobjectposition.hitVec.zCoord - posZ);
					float f5 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
					posX -= (motionX / (double)f5) * 0.05D;
					posY -= (motionY / (double)f5) * 0.05D;
					posZ -= (motionZ / (double)f5) * 0.05D;
					inGround = true;
				}
			}
		}
		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		float f1 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
		rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
		for(rotationPitch = (float)((Math.atan2(motionY, f1) * 180D) / 3.1415927410125732D); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }
		for(; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F) { }
		for(; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F) { }
		for(; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F) { }
		rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
		rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
		float f2 = 0.99F;
		if(isInWater())
		{
			for(int l1 = 0; l1 < 4; l1++)
			{
				float f6 = 0.25F;
				worldObj.spawnParticle("bubble", posX - motionX * (double)f6, posY - motionY * (double)f6, posZ - motionZ * (double)f6, motionX, motionY, motionZ);
			}

			f2 = 0.8F;
		}
		motionX *= f2;
		motionY *= f2;
		motionZ *= f2;
		setPosition(posX, posY, posZ);
	}

	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setShort("xTile", (short)xTile);
		nbttagcompound.setShort("yTile", (short)yTile);
		nbttagcompound.setShort("zTile", (short)zTile);
		nbttagcompound.setString("inTile", inTile.getUnlocalizedName());
		nbttagcompound.setByte("inGround", (byte)(inGround ? 1 : 0));
	}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		xTile = nbttagcompound.getShort("xTile");
		yTile = nbttagcompound.getShort("yTile");
		zTile = nbttagcompound.getShort("zTile");
		inTile = (Block)Item.itemRegistry.getObject(nbttagcompound.getString("inTile"));
		inGround = nbttagcompound.getByte("inGround") == 1;
	}

	public void onCollideWithPlayer(EntityPlayer entityplayer)
	{
		return;
	}

	public float getShadowSize()
	{
		return 0.0F;
	}
}
