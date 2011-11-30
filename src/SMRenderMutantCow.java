




// Referenced classes of package net.minecraft.src:
//            RenderLiving, EntityCow, ModelBase, EntityLiving, 
//            Entity

public class SMRenderMutantCow extends RenderLiving
{

    public SMRenderMutantCow(ModelBase modelbase, float f)
    {
        super(modelbase, f);
    }

    public void renderCow(SMEntityMutantCow entitycow, double d, double d1, double d2, 
            float f, float f1)
    {
        super.doRenderLiving(entitycow, d, d1, d2, f, f1);
    }

    public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2, 
            float f, float f1)
    {
        renderCow((SMEntityMutantCow)entityliving, d, d1, d2, f, f1);
    }

    public void doRender(Entity entity, double d, double d1, double d2, 
            float f, float f1)
    {
        renderCow((SMEntityMutantCow)entity, d, d1, d2, f, f1);
    }
}
