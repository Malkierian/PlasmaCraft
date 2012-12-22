package com.elvenwater.malkierian.Plasmacraft.common;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

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