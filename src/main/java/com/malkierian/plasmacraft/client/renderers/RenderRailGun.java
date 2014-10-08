package com.malkierian.plasmacraft.client.renderers;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.malkierian.plasmacraft.core.PlasmaCraft;
import com.malkierian.plasmacraft.core.entities.EntityRailGun;

public class RenderRailGun extends Render
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(PlasmaCraft.MOD_ID, "textures/misc/railgunbolt.png");
	public RenderRailGun()
	{
	}

	public void renderArrow(EntityRailGun smentityrailgun, double d, double d1, double d2, 
			float f, float f1)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)d, (float)d1, (float)d2);
		GL11.glRotatef((smentityrailgun.prevRotationYaw + (smentityrailgun.rotationYaw - smentityrailgun.prevRotationYaw) * f1) - 90F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(smentityrailgun.prevRotationPitch + (smentityrailgun.rotationPitch - smentityrailgun.prevRotationPitch) * f1, 0.0F, 0.0F, 1.0F);
		Tessellator tessellator = Tessellator.instance;
		int i = 0;
		float f2 = 0.0F;
		float f3 = 0.5F;
		float f4 = (float)(0 + i * 10) / 32F;
		float f5 = (float)(5 + i * 10) / 32F;
		float f6 = 0.0F;
		float f7 = 0.15625F;
		float f8 = (float)(5 + i * 10) / 32F;
		float f9 = (float)(10 + i * 10) / 32F;
		float f10 = 0.05625F;
		GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
		GL11.glRotatef(45F, 1.0F, 0.0F, 0.0F);
		GL11.glScalef(f10, f10, f10);
		GL11.glTranslatef(-4F, 0.0F, 0.0F);
		GL11.glNormal3f(f10, 0.0F, 0.0F);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(-7D, -2D, -2D, f6, f8);
		tessellator.addVertexWithUV(-7D, -2D, 2D, f7, f8);
		tessellator.addVertexWithUV(-7D, 2D, 2D, f7, f9);
		tessellator.addVertexWithUV(-7D, 2D, -2D, f6, f9);
		tessellator.draw();
		GL11.glNormal3f(-f10, 0.0F, 0.0F);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(-7D, 2D, -2D, f6, f8);
		tessellator.addVertexWithUV(-7D, 2D, 2D, f7, f8);
		tessellator.addVertexWithUV(-7D, -2D, 2D, f7, f9);
		tessellator.addVertexWithUV(-7D, -2D, -2D, f6, f9);
		tessellator.draw();
		for(int j = 0; j < 4; j++)
		{
			GL11.glRotatef(90F, 1.0F, 0.0F, 0.0F);
			GL11.glNormal3f(0.0F, 0.0F, f10);
			tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(-8D, -2D, 0.0D, f2, f4);
			tessellator.addVertexWithUV(8D, -2D, 0.0D, f3, f4);
			tessellator.addVertexWithUV(8D, 2D, 0.0D, f3, f5);
			tessellator.addVertexWithUV(-8D, 2D, 0.0D, f2, f5);
			tessellator.draw();
		}

		GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
		GL11.glPopMatrix();
	}

	public void doRender(Entity entity, double d, double d1, double d2, 
			float f, float f1)
	{
		renderArrow((EntityRailGun)entity, d, d1, d2, f, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return TEXTURE;
	}
}
