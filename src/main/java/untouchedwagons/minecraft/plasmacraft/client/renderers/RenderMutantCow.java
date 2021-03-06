package untouchedwagons.minecraft.plasmacraft.client.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import untouchedwagons.minecraft.plasmacraft.entity.EntityMutantCow;

public class RenderMutantCow extends RenderLiving
{
	public RenderMutantCow(ModelBase modelbase, float f)
	{
		super(modelbase, f);
	}

	public void renderCow(EntityMutantCow smentitymutantcow, double d, double d1, double d2, 
			float f, float f1)
	{
//		super.doRenderLiving(smentitymutantcow, d, d1, d2, f, f1);
	}

	public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2, 
			float f, float f1)
	{
		renderCow((EntityMutantCow)entityliving, d, d1, d2, f, f1);
	}

	public void doRender(Entity entity, double d, double d1, double d2, 
			float f, float f1)
	{
		renderCow((EntityMutantCow)entity, d, d1, d2, f, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_)
	{
		return null;
	}
}
