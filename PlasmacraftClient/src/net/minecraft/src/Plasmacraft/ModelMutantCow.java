package net.minecraft.src.Plasmacraft;

import net.minecraft.src.MathHelper;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelMutantCow extends ModelBase
{
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer horn2;
	ModelRenderer udders;
	ModelRenderer New_Shape1;

    public ModelMutantCow()
    {
        float f = 0.0F;
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -4F, -6F, 8, 8, 6, f);
        head.setRotationPoint(0.0F, 4F, -8F);
        body = new ModelRenderer(this, 18, 4);
        body.addBox(-6F, -10F, -7F, 12, 18, 10, f);
        body.setRotationPoint(0.0F, 5F, 2.0F);
        body.rotationPointX = 1.5708F;
        leg1 = new ModelRenderer(this, 0, 16);
        leg1.addBox(-3F, 0.0F, -2F, 4, 12, 4, f);
        leg1.setRotationPoint(-3F, 12F, 7F);
        leg2 = new ModelRenderer(this, 0, 16);
        leg2.addBox(-1F, 0.0F, -2F, 4, 12, 4, f);
        leg2.setRotationPoint(3F, 12F, 7F);
        leg3 = new ModelRenderer(this, 0, 16);
        leg3.addBox(-3F, 0.0F, -3F, 3, 12, 3, f);
        leg3.setRotationPoint(-3F, 11F, -5F);
        leg4 = new ModelRenderer(this, 0, 16);
        leg4.addBox(-1F, 0.0F, -3F, 4, 12, 4, f);
        leg4.setRotationPoint(3F, 12F, -5F);
        horn2 = new ModelRenderer(this, 22, 0);
        horn2.addBox(3F, -5F, -4F, 1, 3, 1, f);
        horn2.setRotationPoint(0.0F, 3F, -7F);
        udders = new ModelRenderer(this, 52, 0);
        udders.addBox(-2F, -3F, 0.0F, 4, 6, 2, f);
        udders.setRotationPoint(0.0F, 9F, 11F);
        udders.rotationPointX = 3.14159F;
        New_Shape1 = new ModelRenderer(this, 0, 0);
        New_Shape1.addBox(0.0F, 0.0F, 0.0F, 8, 6, 8, f);
        New_Shape1.setRotationPoint(-11F, 7F, -3F);
        New_Shape1.rotationPointZ = 1.5708F;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5);
        horn2.rotateAngleY = head.rotateAngleY;
        horn2.rotateAngleX = head.rotateAngleX;
        New_Shape1.rotateAngleX = body.rotateAngleX;
        New_Shape1.rotateAngleY = body.rotateAngleY;
        head.rotateAngleX = f4 / 57.29578F;
        head.rotateAngleY = f3 / 57.29578F;
        body.rotateAngleX = 1.570796F;
        leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;
        leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }

    public void render(float f, float f1, float f2, float f3, float f4, float f5)
    {
        head.render(f5);
        body.render(f5);
        leg1.render(f5);
        leg2.render(f5);
        leg3.render(f5);
        leg4.render(f5);
        horn2.render(f5);
        udders.render(f5);
        New_Shape1.render(f5);
    }
}
