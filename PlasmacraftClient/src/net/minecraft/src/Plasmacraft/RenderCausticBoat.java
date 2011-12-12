package net.minecraft.src.Plasmacraft;

import net.minecraft.src.Entity;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelBoat;
import net.minecraft.src.Render;

import org.lwjgl.opengl.GL11;

public class RenderCausticBoat extends Render
{
    protected ModelBase modelBoat;

    public RenderCausticBoat()
    {
        shadowSize = 0.5F;
        modelBoat = new ModelBoat();
    }

    public void renderBoat(EntityCausticBoat smentitycausticboat, double d, double d1, double d2, 
            float f, float f1)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d, (float)d1, (float)d2);
        GL11.glRotatef(180F - f, 0.0F, 1.0F, 0.0F);
        float f2 = (float)smentitycausticboat.getTimeSinceHit() - f1;
        float f3 = (float)smentitycausticboat.getDamageTaken() - f1;
        if(f3 < 0.0F)
        {
            f3 = 0.0F;
        }
        if(f2 > 0.0F)
        {
            GL11.glRotatef(((MathHelper.sin(f2) * f2 * f3) / 10F) * (float)smentitycausticboat.getForwardDirection(), 1.0F, 0.0F, 0.0F);
        }
        loadTexture("/PlasmaCraftSprites/misc/boat_caustic.png");
        GL11.glScalef(-1F, -1F, 1.0F);
        modelBoat.render(smentitycausticboat, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    public void doRender(Entity entity, double d, double d1, double d2, 
            float f, float f1)
    {
        renderBoat((EntityCausticBoat)entity, d, d1, d2, f, f1);
    }
}
