package com.malkierian.Plasmacraft.common;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityAcidGrenade extends Entity
{

	private int xTileSnowball;
	private int yTileSnowball;
	private int zTileSnowball;
	private Block inTileSnowball;
	private boolean inGroundSnowball;
	public int shakeSnowball;
	private EntityLivingBase thrower;
	private int ticksInGroundSnowball;
	private int ticksInAirSnowball;

	public EntityAcidGrenade(World world)
	{
		super(world);
		xTileSnowball = -1;
		yTileSnowball = -1;
		zTileSnowball = -1;
		inTileSnowball = null;
		inGroundSnowball = false;
		shakeSnowball = 0;
		ticksInAirSnowball = 0;
		setSize(0.25F, 0.25F);
	}

	protected void entityInit()
	{
	}

	public boolean isInRangeToRenderDist(double d)
	{
		double d1 = boundingBox.getAverageEdgeLength() * 4D;
		d1 *= 64D;
		return d < d1 * d1;
	}

	public EntityAcidGrenade(World world, EntityLivingBase entityliving)
	{
		super(world);
		xTileSnowball = -1;
		yTileSnowball = -1;
		zTileSnowball = -1;
		inTileSnowball = null;
		inGroundSnowball = false;
		shakeSnowball = 0;
		ticksInAirSnowball = 0;
		thrower = entityliving;
		setSize(0.25F, 0.25F);
		setLocationAndAngles(entityliving.posX, entityliving.posY + (double)entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
		posX -= MathHelper.cos((rotationYaw / 180F) * 3.141593F) * 0.16F;
		posY -= 0.10000000149011612D;
		posZ -= MathHelper.sin((rotationYaw / 180F) * 3.141593F) * 0.16F;
		setPosition(posX, posY, posZ);
		yOffset = 0.0F;
		float f = 0.4F;
		motionX = -MathHelper.sin((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F) * f;
		motionZ = MathHelper.cos((rotationYaw / 180F) * 3.141593F) * MathHelper.cos((rotationPitch / 180F) * 3.141593F) * f;
		motionY = -MathHelper.sin((rotationPitch / 180F) * 3.141593F) * f;
		setSnowballHeading(motionX, motionY, motionZ, 2.0F, 1.0F);
	}

	public EntityAcidGrenade(World world, double d, double d1, double d2)
	{
		super(world);
		xTileSnowball = -1;
		yTileSnowball = -1;
		zTileSnowball = -1;
		inTileSnowball = null;
		inGroundSnowball = false;
		shakeSnowball = 0;
		ticksInAirSnowball = 0;
		ticksInGroundSnowball = 0;
		setSize(0.25F, 0.25F);
		setPosition(d, d1, d2);
		yOffset = 0.0F;
	}

	public void setSnowballHeading(double d, double d1, double d2, float f, 
			float f1)
	{
		float f2 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
		d /= f2;
		d1 /= f2;
		d2 /= f2;
		d += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
		d1 += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
		d2 += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
		d *= f;
		d1 *= f;
		d2 *= f;
		motionX = d;
		motionY = d1;
		motionZ = d2;
		float f3 = MathHelper.sqrt_double(d * d + d2 * d2);
		prevRotationYaw = rotationYaw = (float)((Math.atan2(d, d2) * 180D) / 3.1415927410125732D);
		prevRotationPitch = rotationPitch = (float)((Math.atan2(d1, f3) * 180D) / 3.1415927410125732D);
		ticksInGroundSnowball = 0;
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

	public void onUpdate()
	{
		lastTickPosX = posX;
		lastTickPosY = posY;
		lastTickPosZ = posZ;
		super.onUpdate();
		if(shakeSnowball > 0)
		{
			shakeSnowball--;
		}
		ticksInGroundSnowball++;
		if(ticksInGroundSnowball == 1200)
		{
			setDead();
		}
		Vec3 vec1 = Vec3.createVectorHelper(posX, posY, posZ);
		Vec3 vec2 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		MovingObjectPosition movingobjectposition = worldObj.rayTraceBlocks(vec1, vec2);
		vec1 = Vec3.createVectorHelper(posX, posY, posZ);
		vec2 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
		if(movingobjectposition != null)
		{
			vec2 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
		}
		if(!worldObj.isRemote)
		{
			Entity entity = null;
			List<?> list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
			double d = 0.0D;
			for(int j = 0; j < list.size(); j++)
			{
				Entity entity1 = (Entity)list.get(j);
				if(!entity1.canBeCollidedWith() || entity1 == thrower && ticksInAirSnowball < 5)
				{
					continue;
				}
				float f4 = 0.3F;
				AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f4, f4, f4);
				MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec1, vec2);
				if(movingobjectposition1 == null)
				{
					continue;
				}
				double d1 = vec1.distanceTo(movingobjectposition1.hitVec);
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
		}
		if(movingobjectposition != null)
		{
			if(movingobjectposition.entityHit == thrower)
			{
				return;
			} else
			{
				System.out.println("Acid explosion!");
				AcidExplosion smacidexplosion = new AcidExplosion(worldObj, this, posX, posY, posZ, 3F);
				smacidexplosion.isFlaming = false;
				smacidexplosion.doExplosionA();
				smacidexplosion.doExplosionB();
				setDead();
				return;
			}
		}
		posX += motionX;
		posY += motionY;
		posZ += motionZ;
		float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
		rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
		for(rotationPitch = (float)((Math.atan2(motionY, f) * 180D) / 3.1415927410125732D); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }
		for(; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F) { }
		for(; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F) { }
		for(; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F) { }
		rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
		rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
		float f1 = 0.99F;
		float f2 = 0.03F;
		if(handleWaterMovement())
		{
			for(int i = 0; i < 4; i++)
			{
				float f3 = 0.25F;
				worldObj.spawnParticle("bubble", posX - motionX * (double)f3, posY - motionY * (double)f3, posZ - motionZ * (double)f3, motionX, motionY, motionZ);
			}

			f1 = 0.8F;
		}
		motionX *= f1;
		motionY *= f1;
		motionZ *= f1;
		motionY -= f2;
		setPosition(posX, posY, posZ);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setShort("xTile", (short)xTileSnowball);
		nbttagcompound.setShort("yTile", (short)yTileSnowball);
		nbttagcompound.setShort("zTile", (short)zTileSnowball);
		nbttagcompound.setString("inTile", inTileSnowball.getUnlocalizedName());
		nbttagcompound.setByte("shake", (byte)shakeSnowball);
		nbttagcompound.setByte("inGround", (byte)(inGroundSnowball ? 1 : 0));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		xTileSnowball = nbttagcompound.getShort("xTile");
		yTileSnowball = nbttagcompound.getShort("yTile");
		zTileSnowball = nbttagcompound.getShort("zTile");
		inTileSnowball = (Block)Item.itemRegistry.getObject(nbttagcompound.getString("inTile"));
		shakeSnowball = nbttagcompound.getByte("shake") & 0xff;
		inGroundSnowball = nbttagcompound.getByte("inGround") == 1;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer entityplayer)
	{
		if(inGroundSnowball && (EntityPlayer)thrower == entityplayer && shakeSnowball <= 0 && entityplayer.inventory.addItemStackToInventory(new ItemStack(Items.arrow, 1)))
		{
			worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
			entityplayer.onItemPickup(this, 1);
			setDead();
		}
	}

	public float getShadowSize()
	{
		return 0.0F;
	}
}
