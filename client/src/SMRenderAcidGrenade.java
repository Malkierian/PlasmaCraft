


import org.lwjgl.opengl.GL11;

public class SMRenderAcidGrenade extends Render
{

    public SMRenderAcidGrenade(int i)
    {
        itemIconIndex = i;
    }

    public void doRender(Entity entity, double d, double d1, double d2,
            float f, float f1)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d, (float)d1, (float)d2);
        GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        loadTexture("/plasmacraft/items/acidnade.png");
        Tessellator tessellator = Tessellator.instance;
        float f6 = 1.0F;
        float f7 = 0.5F;
        float f8 = 0.25F;
        GL11.glRotatef(180F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(0.0F - f7, 0.0F - f8, 0.0D, 0.0f, 1.0f);
        tessellator.addVertexWithUV(f6 - f7, 0.0F - f8, 0.0D, 1.0f, 1.0f);
        tessellator.addVertexWithUV(f6 - f7, 1.0F - f8, 0.0D, 1.0f, 0.0f);
        tessellator.addVertexWithUV(0.0F - f7, 1.0F - f8, 0.0D, 0.0f, 0.0f);
        tessellator.draw();
        GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        GL11.glPopMatrix();
    }

    private int itemIconIndex;
}
