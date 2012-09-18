package com.elvenwater.malkierian.Plasmacraft.common;

import net.minecraft.src.Item;

public class ItemPlasma extends Item
{
    public ItemPlasma(int i)
    {
        super(i);
    }

	@Override
	public String getTextureFile()
	{
		return CommonProxy.ITEMS_PNG;
	}
}
