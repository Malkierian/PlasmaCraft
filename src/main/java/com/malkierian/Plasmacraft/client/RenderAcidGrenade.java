package com.malkierian.Plasmacraft.client;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderAcidGrenade extends Render
{
	public RenderAcidGrenade(int i)
	{
	}

	public void doRender(Entity entity, double d, double d1, double d2, 
			float f, float f1)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)d, (float)d1, (float)d2);
		GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
//		loadTexture("/PlasmaCraftSprites/misc/acidnade.png");
		Tessellator tessellator = Tessellator.instance;
		float f2 = 1.0F;
		float f3 = 0.5F;
		float f4 = 0.25F;
		GL11.glRotatef(180F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		tessellator.addVertexWithUV(0.0F - f3, 0.0F - f4, 0.0D, 0.0D, 1.0D);
		tessellator.addVertexWithUV(f2 - f3, 0.0F - f4, 0.0D, 1.0D, 1.0D);
		tessellator.addVertexWithUV(f2 - f3, 1.0F - f4, 0.0D, 1.0D, 0.0D);
		tessellator.addVertexWithUV(0.0F - f3, 1.0F - f4, 0.0D, 0.0D, 0.0D);
		tessellator.draw();
		GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return null;
	}
}
