package com.malkierian.plasmacraft.common.Entities;

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
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.malkierian.plasmacraft.common.CryoBlast;

public class EntityCryoBlast extends Entity
{
	private int xTile;
	private int yTile;
	private int zTile;
	private Block inTile;
	private boolean inGround;
	public int arrowShake;
	public EntityLivingBase owner;
	private int ticksInAir;

	public EntityCryoBlast(World world)
	{
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		inTile = null;
		inGround = false;
		arrowShake = 0;
		ticksInAir = 0;
		setSize(0.5F, 0.5F);
	}

	public EntityCryoBlast(World world, double d, double d1, double d2)
	{
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		inTile = null;
		inGround = false;
		arrowShake = 0;
		ticksInAir = 0;
		setSize(0.5F, 0.5F);
		setPosition(d, d1, d2);
		yOffset = 0.0F;
	}

	public EntityCryoBlast(World world, EntityLivingBase entityliving)
	{
		super(world);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		inTile = null;
		inGround = false;
		arrowShake = 0;
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
		setArrowHeading(motionX, motionY, motionZ, 5F, 1.0F);
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
		if(arrowShake > 0)
		{
			arrowShake--;
		}
		if(inGround)
		{
			setDead();
		} else
		{
			ticksInAir++;
		}
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
				if(movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, owner), 1))
				{
					CryoBlast smcryoblast = new CryoBlast(worldObj, this, posX, posY, posZ, 2F);
					smcryoblast.isFlaming = false;
					smcryoblast.doExplosionA();
					smcryoblast.doExplosionB();
					setDead();
				}
			}
			else
			{
				CryoBlast smcryoblast1 = new CryoBlast(worldObj, this, posX, posY, posZ, 2F);
				smcryoblast1.isFlaming = false;
				smcryoblast1.doExplosionA();
				smcryoblast1.doExplosionB();
				setDead();
				xTile = movingobjectposition.blockX;
				yTile = movingobjectposition.blockY;
				zTile = movingobjectposition.blockZ;
				inTile = worldObj.getBlock(xTile, yTile, zTile);
				motionX = (float)(movingobjectposition.hitVec.xCoord - posX);
				motionY = (float)(movingobjectposition.hitVec.yCoord - posY);
				motionZ = (float)(movingobjectposition.hitVec.zCoord - posZ);
				float f5 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
				posX -= (motionX / (double)f5) * 0.05000000074505806D;
				posY -= (motionY / (double)f5) * 0.05000000074505806D;
				posZ -= (motionZ / (double)f5) * 0.05000000074505806D;
				inGround = true;
				arrowShake = 7;
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
		//float f4 = 0.03F;
		if(isInWater())
		{
			for(int i1 = 0; i1 < 4; i1++)
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
		nbttagcompound.setByte("shake", (byte)arrowShake);
		nbttagcompound.setByte("inGround", (byte)(inGround ? 1 : 0));
	}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		xTile = nbttagcompound.getShort("xTile");
		yTile = nbttagcompound.getShort("yTile");
		zTile = nbttagcompound.getShort("zTile");
		inTile = (Block)Item.itemRegistry.getObject(nbttagcompound.getString("inTile"));
		arrowShake = nbttagcompound.getByte("shake") & 0xff;
		inGround = nbttagcompound.getByte("inGround") == 1;
	}

	public void onCollideWithPlayer(EntityPlayer entityplayer)
	{
		if(worldObj.isRemote)
		{
			return;
		}
		if(inGround && owner == entityplayer && arrowShake <= 0 && entityplayer.inventory.addItemStackToInventory(new ItemStack(Items.arrow, 1)))
		{
			setDead();
		}
	}

	public float getShadowSize()
	{
		return 0.0F;
	}
}
