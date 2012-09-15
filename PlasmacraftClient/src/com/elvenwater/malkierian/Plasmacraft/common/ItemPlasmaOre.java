package com.elvenwater.malkierian.Plasmacraft.common;

import net.minecraft.src.Block;
import net.minecraft.src.BlockCloth;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;

public class ItemPlasmaOre extends ItemBlock
{
	public static String[] blockNames = {
		"Plutonium Ore",
        "Radionite Ore",
        "Neptunium Ore",
        "Obsidium Ore",
        "Uranium Ore"
        };

	public ItemPlasmaOre(int id)
	{
		super(id);
        setMaxDamage(0);
        setHasSubtypes(true);
	}

    public int getIconFromDamage(int i)
    {
        return PlasmaCraft.orePlasma.getBlockTextureFromSideAndMetadata(0, i);
    }
    
	@Override
    public int getMetadata(int damage)
    {
    	return damage;
    }
    
	@Override
    public String getItemNameIS(ItemStack itemstack)
	{
        return getItemName() + "." + blockNames[itemstack.getItemDamage()];
    }

    public String getTextureFile() 
    {
		return CommonProxy.BLOCK_PNG;
    }
	
}