package com.malkierian.plasmacraft.client.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.malkierian.plasmacraft.core.PlasmaCraft;
import com.malkierian.plasmacraft.core.entities.EntityCausticBoat;

public class RenderCausticBoat extends Render
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(PlasmaCraft.MOD_ID, "textures/misc/causticBoat.png");
	protected ModelBase modelBoat;

	public RenderCausticBoat()
	{
		shadowSize = 0.5F;
		modelBoat = new ModelBoat();
	}
	
	public void renderBoat(EntityCausticBoat par1EntityBoat, double par2, double par4, double par6, float par8, float par9)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float)par2, (float)par4, (float)par6);
		GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);
		float var10 = (float)par1EntityBoat.getTimeSinceHit() - par9;
		float var11 = (float)par1EntityBoat.getDamageTaken() - par9;

		if (var11 < 0.0F)
		{
			var11 = 0.0F;
		}

		if (var10 > 0.0F)
		{
			GL11.glRotatef(MathHelper.sin(var10) * var10 * var11 / 10.0F * (float)par1EntityBoat.getForwardDirection(), 1.0F, 0.0F, 0.0F);
		}

		float var12 = 0.75F;
		GL11.glScalef(var12, var12, var12);
		GL11.glScalef(1.0F / var12, 1.0F / var12, 1.0F / var12);
		this.bindTexture(TEXTURE);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		this.modelBoat.render(par1EntityBoat, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}
	
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
	{
		this.renderBoat((EntityCausticBoat)par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return null;
	}
}
