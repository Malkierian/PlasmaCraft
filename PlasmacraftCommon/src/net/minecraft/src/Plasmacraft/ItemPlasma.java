package net.minecraft.src.Plasmacraft;

import net.minecraft.src.Item;
import net.minecraft.src.forge.ITextureProvider;

public class ItemPlasma extends Item implements ITextureProvider
{
    public ItemPlasma(int i)
    {
        super(i);
    }

	@Override
	public String getTextureFile()
	{
		return PlasmaCraftCore.itemTexture;
	}
}
