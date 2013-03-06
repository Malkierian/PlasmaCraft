package com.elvenwater.malkierian.Plasmacraft.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

import com.elvenwater.malkierian.Plasmacraft.common.Entities.EntityMutantCow;

public class RenderMutantCow extends RenderLiving
{

    public RenderMutantCow(ModelBase modelbase, float f)
    {
        super(modelbase, f);
    }

    public void renderCow(EntityMutantCow smentitymutantcow, double d, double d1, double d2, 
            float f, float f1)
    {
        super.doRenderLiving(smentitymutantcow, d, d1, d2, f, f1);
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
}
