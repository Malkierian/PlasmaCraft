package com.elvenwater.malkierian.Plasmacraft.client;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFluid;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

import com.elvenwater.malkierian.Plasmacraft.common.PlasmaCraft;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderCaustic implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer)
	{
		
		if(block.getRenderType() != PlasmaCraft.causticID)
			return true;
		
		return renderBlockCaustics(renderer, world, block, x, y, z);
	}

	@Override public boolean shouldRender3DInInventory()
	{
		return false;
	}
	@Override public int getRenderId()
	{
		return PlasmaCraft.causticID;
	}

	private float getFluidHeight(IBlockAccess iblockaccess, int i, int j, int k, Material mat)
	{
		int l = 0;
		float f = 0.0F;
		for(int i1 = 0; i1 < 4; i1++)
		{
			int j1 = i - (i1 & 1);
			int k1 = j;
			int l1 = k - (i1 >> 1 & 1);
			if(iblockaccess.getBlockMaterial(j1, k1 + 1, l1) == Material.water)
			{
				return 1.0F;
			}
			Material material = iblockaccess.getBlockMaterial(j1, k1, l1);
			if(material == Material.water)
			{
				int i2 = iblockaccess.getBlockMetadata(j1, k1, l1);
				if(i2 >= 8 || i2 == 0)
				{
					f += BlockFluid.getFluidHeightPercent(i2) * 10F;
					l += 10;
				}
				f += BlockFluid.getFluidHeightPercent(i2);
				l++;
				continue;
			}
			if(!material.isSolid())
			{
				f++;
				l++;
			}
		}

		return 1.0F - f / (float)l;
	}

	public void renderBottomFace(Block block, double d, double d1, double d2, 
			int i)
	{
		Tessellator tessellator = Tessellator.instance;
		int j = (i & 0xf) << 4;
		int k = i & 0xf0;
		double d3 = ((double)j + block.getBlockBoundsMinX() * 16D) / 256D;
		double d4 = (((double)j + block.getBlockBoundsMaxX() * 16D) - 0.01D) / 256D;
		double d5 = ((double)k + block.getBlockBoundsMinZ() * 16D) / 256D;
		double d6 = (((double)k + block.getBlockBoundsMaxZ() * 16D) - 0.01D) / 256D;
		if(block.getBlockBoundsMinX() < 0.0D || block.getBlockBoundsMaxX() > 1.0D)
		{
			d3 = ((float)j + 0.0F) / 256F;
			d4 = ((float)j + 15.99F) / 256F;
		}
		if(block.getBlockBoundsMinZ() < 0.0D || block.getBlockBoundsMaxZ() > 1.0D)
		{
			d5 = ((float)k + 0.0F) / 256F;
			d6 = ((float)k + 15.99F) / 256F;
		}
		double d7 = d + block.getBlockBoundsMinX();
		double d8 = d + block.getBlockBoundsMaxX();
		double d9 = d1 + block.getBlockBoundsMinY();
		double d10 = d2 + block.getBlockBoundsMinZ();
		double d11 = d2 + block.getBlockBoundsMaxZ();
		tessellator.addVertexWithUV(d7, d9, d11, d3, d6);
		tessellator.addVertexWithUV(d7, d9, d10, d3, d5);
		tessellator.addVertexWithUV(d8, d9, d10, d4, d5);
		tessellator.addVertexWithUV(d8, d9, d11, d4, d6);
	}

	public boolean renderBlockCaustics(RenderBlocks renderblocks, IBlockAccess iblockaccess, Block block, int i, int j, int k)
	{
		Tessellator tessellator = Tessellator.instance;
		int l = block.colorMultiplier(iblockaccess, i, j, k);
		float f = (float)(l >> 16 & 0xff) / 255F;
		float f1 = (float)(l >> 8 & 0xff) / 255F;
		float f2 = (float)(l & 0xff) / 255F;
		boolean flag = block.shouldSideBeRendered(iblockaccess, i, j + 1, k, 1);
		boolean flag1 = block.shouldSideBeRendered(iblockaccess, i, j - 1, k, 0);
		boolean aflag[] = {
			block.shouldSideBeRendered(iblockaccess, i, j, k - 1, 2), block.shouldSideBeRendered(iblockaccess, i, j, k + 1, 3), block.shouldSideBeRendered(iblockaccess, i - 1, j, k, 4), block.shouldSideBeRendered(iblockaccess, i + 1, j, k, 5)
		};
		if(!flag && !flag1 && !aflag[0] && !aflag[1] && !aflag[2] && !aflag[3])
		{
			return false;
		}
		boolean flag2 = false;
		float f3 = 0.5F;
		float f4 = 1.0F;
		float f5 = 0.8F;
		float f6 = 0.6F;
		double d = 0.0D;
		double d1 = 1.0D;
		Material material = block.blockMaterial;
		int i1 = iblockaccess.getBlockMetadata(i, j, k);
		double d2 = getFluidHeight(iblockaccess, i, j, k, material);
		double d3 = getFluidHeight(iblockaccess, i, j, k + 1, material);
		double d4 = getFluidHeight(iblockaccess, i + 1, j, k + 1, material);
		double d5 = getFluidHeight(iblockaccess, i + 1, j, k, material);
		double d6 = 0.0010000000474974513D;
		if(renderblocks.renderAllFaces || flag)
		{
			flag2 = true;
			int j1 = block.getBlockTextureFromSideAndMetadata(1, i1);
			float f7 = (float)BlockFluid.getFlowDirection(iblockaccess, i, j, k, material);
			if(f7 > -999F)
			{
				j1 = block.getBlockTextureFromSideAndMetadata(2, i1);
			}
			d2 -= d6;
			d3 -= d6;
			d4 -= d6;
			d5 -= d6;
			int k2 = (j1 & 0xf) << 4;
			int l1 = j1 & 0xf0;
			double d7 = ((double)k2 + 8D) / 256D;
			double d8 = ((double)l1 + 8D) / 256D;
			if(f7 < -999F)
			{
				f7 = 0.0F;
			} else
			{
				d7 = (float)(k2 + 16) / 256F;
				d8 = (float)(l1 + 16) / 256F;
			}
			double d10 = (double)(MathHelper.sin(f7) * 8F) / 256D;
			double d12 = (double)(MathHelper.cos(f7) * 8F) / 256D;
			tessellator.setBrightness(block.getMixedBrightnessForBlock(iblockaccess, i, j, k));
			float f9 = 1.0F;
			tessellator.setColorOpaque_F(f4 * f9 * f, f4 * f9 * f1, f4 * f9 * f2);
			tessellator.addVertexWithUV(i + 0, (double)j + d2, k + 0, d7 - d12 - d10, (d8 - d12) + d10);
			tessellator.addVertexWithUV(i + 0, (double)j + d3, k + 1, (d7 - d12) + d10, d8 + d12 + d10);
			tessellator.addVertexWithUV(i + 1, (double)j + d4, k + 1, d7 + d12 + d10, (d8 + d12) - d10);
			tessellator.addVertexWithUV(i + 1, (double)j + d5, k + 0, (d7 + d12) - d10, d8 - d12 - d10);
		}
		if(renderblocks.renderAllFaces || flag1)
		{
			tessellator.setBrightness(block.getMixedBrightnessForBlock(iblockaccess, i, j - 1, k));
			float f8 = 1.0F;
			tessellator.setColorOpaque_F(f3 * f8, f3 * f8, f3 * f8);
			renderBottomFace(block, i, (double)j + d6, k, block.getBlockTextureFromSide(0));
			flag2 = true;
		}
		for(int k1 = 0; k1 < 4; k1++)
		{
			int j2 = i;
			int i2 = k;
			if(k1 == 0)
			{
				i2 = k - 1;
			}
			if(k1 == 1)
			{
				i2++;
			}
			if(k1 == 2)
			{
				j2 = i - 1;
			}
			if(k1 == 3)
			{
				j2++;
			}
			int l2 = block.getBlockTextureFromSideAndMetadata(k1 + 2, i1);
			int i3 = (l2 & 0xf) << 4;
			int j3 = l2 & 0xf0;
			if(renderblocks.renderAllFaces || aflag[k1])
			{
				double d9;
				double d11;
				double d13;
				double d14;
				double d15;
				double d16;
				if(k1 == 0)
				{
					d11 = d2;
					d9 = d5;
					d14 = i;
					d16 = i + 1;
					d13 = (double)k + d6;
					d15 = (double)k + d6;
				} else
				if(k1 == 1)
				{
					d11 = d4;
					d9 = d3;
					d14 = i + 1;
					d16 = i;
					d13 = (double)(k + 1) - d6;
					d15 = (double)(k + 1) - d6;
				} else
				if(k1 == 2)
				{
					d11 = d3;
					d9 = d2;
					d14 = (double)i + d6;
					d16 = (double)i + d6;
					d13 = k + 1;
					d15 = k;
				} else
				{
					d11 = d5;
					d9 = d4;
					d14 = (double)(i + 1) - d6;
					d16 = (double)(i + 1) - d6;
					d13 = k;
					d15 = k + 1;
				}
				flag2 = true;
				double d17 = (float)(i3 + 0) / 256F;
				double d18 = ((double)(i3 + 16) - 0.01D) / 256D;
				double d19 = ((double)j3 + (1.0D - d11) * 16D) / 256D;
				double d20 = ((double)j3 + (1.0D - d9) * 16D) / 256D;
				double d21 = ((double)(j3 + 16) - 0.01D) / 256D;
				tessellator.setBrightness(block.getMixedBrightnessForBlock(iblockaccess, j2, j, i2));
				float f10 = 1.0F;
				if(k1 < 2)
				{
					f10 *= f5;
				} else
				{
					f10 *= f6;
				}
				tessellator.setColorOpaque_F(f4 * f10 * f, f4 * f10 * f1, f4 * f10 * f2);
				tessellator.addVertexWithUV(d14, (double)j + d11, d13, d17, d19);
				tessellator.addVertexWithUV(d16, (double)j + d9, d15, d18, d20);
				tessellator.addVertexWithUV(d16, j + 0, d15, d18, d21);
				tessellator.addVertexWithUV(d14, j + 0, d13, d17, d21);
			}
		}

		block.setBlockBounds((float)block.getBlockBoundsMinX(),
				(float)block.getBlockBoundsMaxX(), (float)d, (float)d1,
				(float)block.getBlockBoundsMinZ(), (float)block.getBlockBoundsMaxZ());
		return flag2;
	}

}