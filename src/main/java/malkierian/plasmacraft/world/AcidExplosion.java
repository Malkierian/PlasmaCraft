package malkierian.plasmacraft.world;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import malkierian.plasmacraft.init.PCFluids;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockSign;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class AcidExplosion extends CausticExplosion
{
	public AcidExplosion(World worldIn, Entity entityIn, double x, double y, double z, float size, boolean flaming, boolean smoking)
	{
		super(worldIn, entityIn, x, y, z, size, flaming, smoking);
	}

    /**
     * Does the second part of the explosion (sound, particles, drop spawn)
     */
	@Override
    public void doExplosionB(boolean spawnParticles)
    {
		super.doExplosionB(spawnParticles);
        for(BlockPos pos : this.affectedBlockPositions)
		{
			Block j3 = worldObj.getBlockState(pos).getBlock();
			double d = (double)pos.getY() - explosionY;
			if(d < -2D && j3 == Blocks.AIR)
			{
				worldObj.setBlockState(pos, PCFluids.acidBlock.getDefaultState());
			}
		}

		for(int k = (int)(explosionX - (double)(explosionSize + 1.0F)); k <= (int)(explosionX + (double)(explosionSize + 1.0F)); k++)
		{
			for(int l = (int)(explosionY - (double)(explosionSize + 1.0F)); l <= (int)(explosionY + (double)(explosionSize + 1.0F)); l++)
			{
				for(int k1 = (int)(explosionZ - (double)(explosionSize + 1.0F)); k1 <= (int)(explosionZ + (double)(explosionSize + 1.0F)); k1++)
				{
					BlockPos pos = new BlockPos(k, l, k1);
					Block j2 = worldObj.getBlockState(pos).getBlock();
					if(j2 != PCFluids.acidBlock)
					{
						continue;
					}
					boolean flag = isFlowable(worldObj.getBlockState(pos.add(-1, 0, 0)), worldObj, pos);
					boolean flag1 = isFlowable(worldObj.getBlockState(pos.add(1, 0, 0)), worldObj, pos);
					boolean flag2 = isFlowable(worldObj.getBlockState(pos.add(0, 0, 1)), worldObj, pos);
					boolean flag3 = isFlowable(worldObj.getBlockState(pos.add(0, 0, -1)), worldObj, pos);
					boolean flag4 = isFlowable(worldObj.getBlockState(pos.add(0, -1, 0)), worldObj, pos);
					if(flag || flag1 || flag2 || flag3 || flag4)
					{
						worldObj.setBlockState(pos, Blocks.COBBLESTONE.getDefaultState());
					}
				}
			}
		}
    }

	private boolean isFlowable(IBlockState state, World world, BlockPos pos)
	{
		Block i = state.getBlock();
		return i.isReplaceable(world, pos) || i instanceof BlockDoor || i instanceof BlockSign || i instanceof BlockLadder || i == Blocks.REEDS || i == Blocks.REDSTONE_WIRE;
	}

    public Map<EntityPlayer, Vec3d> getPlayerKnockbackMap()
    {
        return this.playerKnockbackMap;
    }

    /**
     * Returns either the entity that placed the explosive block, the entity that caused the explosion or null.
     */
    public EntityLivingBase getExplosivePlacedBy()
    {
        return this.exploder == null ? null : (this.exploder instanceof EntityTNTPrimed ? ((EntityTNTPrimed)this.exploder).getTntPlacedBy() : (this.exploder instanceof EntityLivingBase ? (EntityLivingBase)this.exploder : null));
    }

    public void clearAffectedBlockPositions()
    {
        this.affectedBlockPositions.clear();
    }

    public List<BlockPos> getAffectedBlockPositions()
    {
        return this.affectedBlockPositions;
    }

    public Vec3d getPosition(){ return this.position; }
}
