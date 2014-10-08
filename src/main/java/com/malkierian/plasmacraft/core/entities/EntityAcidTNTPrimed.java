package com.malkierian.plasmacraft.core.entities;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.malkierian.plasmacraft.core.AcidExplosion;

public class EntityAcidTNTPrimed extends Entity
{
	public int fuse;

	public EntityAcidTNTPrimed(World world)
	{
		super(world);
		fuse = 0;
		preventEntitySpawning = true;
		setSize(0.98F, 0.98F);
		yOffset = height / 2.0F;
	}

	public EntityAcidTNTPrimed(World world, double d, double d1, double d2)
	{
		this(world);
		setPosition(d, d1, d2);
		float f = (float)(Math.random() * 3.1415927410125732D * 2D);
		motionX = -MathHelper.sin((f * 3.141593F) / 180F) * 0.02F;
		motionY = 0.20000000298023224D;
		motionZ = -MathHelper.cos((f * 3.141593F) / 180F) * 0.02F;
		fuse = 80;
		prevPosX = d;
		prevPosY = d1;
		prevPosZ = d2;
	}

	protected void entityInit()
	{
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return !isDead;
	}

	@Override
	public void onUpdate()
	{
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		motionY -= 0.039999999105930328D;
		moveEntity(motionX, motionY, motionZ);
		motionX *= 0.98000001907348633D;
		motionY *= 0.98000001907348633D;
		motionZ *= 0.98000001907348633D;
		if(onGround)
		{
			motionX *= 0.69999998807907104D;
			motionZ *= 0.69999998807907104D;
			motionY *= -0.5D;
		}

		if (!this.worldObj.isRemote)
		{
			if(fuse-- <= 0)
			{
				setDead();
				explode();
			}
		}
		else
		{
			worldObj.spawnParticle("smoke", posX, posY + 0.5D, posZ, 0.0D, 0.0D, 0.0D);
		}
	}

	private void explode()
	{
		float f = 10F;
		AcidExplosion smacidexplosion = new AcidExplosion(worldObj, null, posX, posY, posZ, f);
		smacidexplosion.isFlaming = false;
		smacidexplosion.doExplosionA();
		smacidexplosion.doExplosionB();
	}

	protected void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setInteger("Fuse", fuse);
	}

	protected void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		fuse = nbttagcompound.getInteger("Fuse");
	}

	public float getShadowSize()
	{
		return 0.0F;
	}
}
