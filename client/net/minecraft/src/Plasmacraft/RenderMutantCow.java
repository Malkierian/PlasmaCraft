package net.minecraft.src.Plasmacraft;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModelBase;
import net.minecraft.src.RenderLiving;

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
