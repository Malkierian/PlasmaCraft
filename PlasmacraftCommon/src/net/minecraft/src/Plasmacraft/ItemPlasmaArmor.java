package net.minecraft.src.Plasmacraft;

import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.ItemArmor;
import net.minecraft.src.forge.ITextureProvider;

public class ItemPlasmaArmor extends ItemArmor implements ITextureProvider
{
	public ItemPlasmaArmor(int i, EnumArmorMaterial enumarmormaterial, int j, int k)
	{
		super(i, enumarmormaterial, j, k);
	}

	@Override
	public String getTextureFile()
	{
		return PlasmaCraftCore.itemTexture;
	}

}
