package malkierian.plasmacraft.entity;

import java.util.List;

import malkierian.plasmacraft.init.PCFluids;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityAcid extends EntityThrowable
{
	private int xTile;
	private int yTile;
	private int zTile;
	private Block inTile;
	private boolean inGround;
	public int arrowShake;
	public EntityLivingBase owner;
	private int ticksInAir;
    private int ticksInGround;
    public Entity ignoreEntity;
    private int ignoreTime;

	public EntityAcid(World world)
	{
		super(world);
	}

	public EntityAcid(World world, double d, double d1, double d2)
	{
		super(world, d, d1, d2);
	}

	public EntityAcid(World world, EntityLivingBase entityliving)
	{
		super(world, entityliving);
	}

//	public void setArrowHeading(double d, double d1, double d2, float f, 
//			float f1)
//	{
//		float f2 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
//		d /= f2;
//		d1 /= f2;
//		d2 /= f2;
//		d += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
//		d1 += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
//		d2 += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
//		d *= f;
//		d1 *= f;
//		d2 *= f;
//		motionX = d;
//		motionY = d1;
//		motionZ = d2;
//		float f3 = MathHelper.sqrt_double(d * d + d2 * d2);
//		prevRotationYaw = rotationYaw = (float)((Math.atan2(d, d2) * 180D) / 3.1415927410125732D);
//		prevRotationPitch = rotationPitch = (float)((Math.atan2(d1, f3) * 180D) / 3.1415927410125732D);
//	}

//	public void setVelocity(double d, double d1, double d2)
//	{
//		motionX = d;
//		motionY = d1;
//		motionZ = d2;
//		if(prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
//		{
//			float f = MathHelper.sqrt_double(d * d + d2 * d2);
//			prevRotationYaw = rotationYaw = (float)((Math.atan2(d, d2) * 180D) / 3.1415927410125732D);
//			prevRotationPitch = rotationPitch = (float)((Math.atan2(d1, f) * 180D) / 3.1415927410125732D);
//		}
//	}

	public int tickRate()
	{
		return 10;
	}

	public void onUpdate()
	{
		this.lastTickPosX = this.posX;
        this.lastTickPosY = this.posY;
        this.lastTickPosZ = this.posZ;
        super.onUpdate();

        if (this.throwableShake > 0)
        {
            --this.throwableShake;
        }

        if (this.inGround)
        {
            if (this.worldObj.getBlockState(new BlockPos(this.xTile, this.yTile, this.zTile)).getBlock() == this.inTile)
            {
                ++this.ticksInGround;

                if (this.ticksInGround == 1200)
                {
                    this.setDead();
                }

                return;
            }

            this.inGround = false;
            this.motionX *= (double)(this.rand.nextFloat() * 0.2F);
            this.motionY *= (double)(this.rand.nextFloat() * 0.2F);
            this.motionZ *= (double)(this.rand.nextFloat() * 0.2F);
            this.ticksInGround = 0;
            this.ticksInAir = 0;
        }
        else
        {
            ++this.ticksInAir;
        }

        Vec3d vec3d = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d vec3d1 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        RayTraceResult raytraceresult = this.worldObj.rayTraceBlocks(vec3d, vec3d1);
        vec3d = new Vec3d(this.posX, this.posY, this.posZ);
        vec3d1 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if (raytraceresult != null)
        {
            vec3d1 = new Vec3d(raytraceresult.hitVec.xCoord, raytraceresult.hitVec.yCoord, raytraceresult.hitVec.zCoord);
        }

        Entity entity = null;
        List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expandXyz(1.0D));
        double d0 = 0.0D;
        boolean flag = false;

        for (int i = 0; i < list.size(); ++i)
        {
            Entity entity1 = (Entity)list.get(i);

            if (entity1.canBeCollidedWith())
            {
                if (entity1 == this.ignoreEntity)
                {
                    flag = true;
                }
                else if (this.ticksExisted < 2 && this.ignoreEntity == null)
                {
                    this.ignoreEntity = entity1;
                    flag = true;
                }
                else
                {
                    flag = false;
                    AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expandXyz(0.30000001192092896D);
                    RayTraceResult raytraceresult1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);

                    if (raytraceresult1 != null)
                    {
                        double d1 = vec3d.squareDistanceTo(raytraceresult1.hitVec);

                        if (d1 < d0 || d0 == 0.0D)
                        {
                            entity = entity1;
                            d0 = d1;
                        }
                    }
                }
            }
        }

        if (this.ignoreEntity != null)
        {
            if (flag)
            {
                this.ignoreTime = 2;
            }
            else if (this.ignoreTime-- <= 0)
            {
                this.ignoreEntity = null;
            }
        }

        if (entity != null)
        {
            raytraceresult = new RayTraceResult(entity);
        }

        if (raytraceresult != null)
        {
            if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK && this.worldObj.getBlockState(raytraceresult.getBlockPos()).getBlock() == Blocks.PORTAL)
            {
                this.setPortal(raytraceresult.getBlockPos());
            }
            else
            {
                if(!net.minecraftforge.common.ForgeHooks.onThrowableImpact(this, raytraceresult))
                this.onImpact(raytraceresult);
            }
        }

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));

        for (this.rotationPitch = (float)(MathHelper.atan2(this.motionY, (double)f) * (180D / Math.PI)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
        {
            ;
        }

        while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
        {
            this.prevRotationPitch += 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw < -180.0F)
        {
            this.prevRotationYaw -= 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
        {
            this.prevRotationYaw += 360.0F;
        }

        this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
        this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
        float f1 = 0.99F;
        float f2 = this.getGravityVelocity();

        if (this.isInWater())
        {
            for (int j = 0; j < 4; ++j)
            {
                float f3 = 0.25F;
                this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * 0.25D, this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX, this.motionY, this.motionZ, new int[0]);
            }

            f1 = 0.8F;
        }

        this.motionX *= (double)f1;
        this.motionY *= (double)f1;
        this.motionZ *= (double)f1;

        if (!this.hasNoGravity())
        {
            this.motionY -= (double)f2;
        }

        this.setPosition(this.posX, this.posY, this.posZ);
	}

	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setShort("xTile", (short)xTile);
		nbttagcompound.setShort("yTile", (short)yTile);
		nbttagcompound.setShort("zTile", (short)zTile);
		nbttagcompound.setString("inTile", inTile.getRegistryName().getResourcePath());
		nbttagcompound.setByte("shake", (byte)arrowShake);
		nbttagcompound.setByte("inGround", (byte)(inGround ? 1 : 0));
	}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		xTile = nbttagcompound.getShort("xTile");
		yTile = nbttagcompound.getShort("yTile");
		zTile = nbttagcompound.getShort("zTile");
		inTile = Block.REGISTRY.getObject(new ResourceLocation(nbttagcompound.getString("inTile")));
		arrowShake = nbttagcompound.getByte("shake") & 0xff;
		inGround = nbttagcompound.getByte("inGround") == 1;
	}

	public void onCollideWithPlayer(EntityPlayer entityplayer)
	{
		if(worldObj.isRemote)
		{
			return;
		}
		if(inGround && owner == entityplayer && arrowShake <= 0 && entityplayer.inventory.addItemStackToInventory(new ItemStack(Items.ARROW, 1)))
		{
			setDead();
		}
	}

	public float getShadowSize()
	{
		return 0.0F;
	}

	@Override
	protected void onImpact(RayTraceResult rayTraceResult)
	{
		if(rayTraceResult.entityHit != null)
		{
			if(rayTraceResult.entityHit == owner)
			{
				return;
			}
			if(rayTraceResult.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, owner), 6))
			{
				worldObj.setBlockState(rayTraceResult.entityHit.getPosition(), PCFluids.acidBlock.getDefaultState());
				setDead();
			}
		}
		else if(rayTraceResult.getBlockPos() != null)
		{
			BlockPos pos = rayTraceResult.getBlockPos();
			boolean flag = true;
			if(worldObj.getBlockState(pos).getBlock() == Blocks.TALLGRASS)
			{
				worldObj.setBlockState(pos, Blocks.FIRE.getDefaultState());
				flag = false;
			}
			if(worldObj.getBlockState(pos).getBlock() == Blocks.SNOW)
			{
				worldObj.setBlockState(pos, Blocks.FIRE.getDefaultState());
				flag = false;
			}
			if(worldObj.getBlockState(pos).getBlock() == Blocks.RED_FLOWER)
			{
				worldObj.setBlockState(pos, Blocks.FIRE.getDefaultState());
				flag = false;
			}
			if(worldObj.getBlockState(pos).getBlock() == Blocks.YELLOW_FLOWER)
			{
				worldObj.setBlockState(pos, Blocks.FIRE.getDefaultState());
				flag = false;
			}
			if(worldObj.isAirBlock(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())) && flag)
			{
				worldObj.setBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), PCFluids.acidBlock.getDefaultState());
			}
			if(worldObj.isAirBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1)) && flag)
			{
				worldObj.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1), PCFluids.acidBlock.getDefaultState());
			}
			if(worldObj.isAirBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1)) && flag)
			{
				worldObj.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1), PCFluids.acidBlock.getDefaultState());
			}
			if(worldObj.isAirBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ())) && flag)
			{
				worldObj.setBlockState(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()), PCFluids.acidBlock.getDefaultState());
			}
			if(worldObj.isAirBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ())) && flag)
			{
				worldObj.setBlockState(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()), PCFluids.acidBlock.getDefaultState());
			}
			setDead();
		}
	}
}
