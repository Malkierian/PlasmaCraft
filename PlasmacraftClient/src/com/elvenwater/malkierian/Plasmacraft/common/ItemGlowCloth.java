package com.elvenwater.malkierian.Plasmacraft.common;

import net.minecraft.src.Block;
import net.minecraft.src.BlockCloth;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;

public class ItemGlowCloth extends ItemBlock
{
	public static String[] blockNames = {
		"Acid Glowcloth",
	    "Radionite Glowcloth",
	    "Netherflow Glowcloth",
	    "Neptunium Glowcloth",
	    "Uranium Glowcloth",
	    "Plutonium Glowcloth",
	    "Cryonite Glowcloth",
	    "Obsidium Glowcloth"
	    };

	public ItemGlowCloth(int id)
	{
		super(id);
        setMaxDamage(0);
        setHasSubtypes(true);
	}

    public int getIconFromDamage(int i)
    {
        return PlasmaCraft.glowCloth.getBlockTextureFromSideAndMetadata(0, i);
    }
    
    public int getMetadata(int damage)
    {
            return damage;
    }
    
    public String getItemNameIS(ItemStack itemstack)
    {
        return getItemName() + "." + blockNames[itemstack.getItemDamage()];
    }
	
}