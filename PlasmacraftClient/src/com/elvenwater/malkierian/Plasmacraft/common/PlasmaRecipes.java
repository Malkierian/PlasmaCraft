package com.elvenwater.malkierian.Plasmacraft.common;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PlasmaRecipes
{
    private static final PlasmaRecipes instance = new PlasmaRecipes();
    private Map<Integer, ItemStack> recipeDictionary;

    public static final PlasmaRecipes getInstance()
    {
        return instance;
    }

    private PlasmaRecipes()
    {
        recipeDictionary = new HashMap<Integer, ItemStack>();
        addPlasmaRecipe(PlasmaCraft.plutoniumMeta, new ItemStack(PlasmaCraft.goopPlutonium));
        addPlasmaRecipe(PlasmaCraft.radioniteMeta, new ItemStack(PlasmaCraft.goopRadionite));
        addPlasmaRecipe(PlasmaCraft.neptuniumMeta, new ItemStack(PlasmaCraft.goopNeptunium));
        addPlasmaRecipe(PlasmaCraft.obsidiumMeta, new ItemStack(PlasmaCraft.goopObsidium));
        addPlasmaRecipe(PlasmaCraft.uraniumMeta, new ItemStack(PlasmaCraft.goopUranium));
        addPlasmaRecipe(PlasmaCraft.frozenCryonite.blockID, new ItemStack(PlasmaCraft.goopCryonite));
        addPlasmaRecipe(PlasmaCraft.goopNetherflow.itemID, new ItemStack(PlasmaCraft.ingotNetherflow));
        addPlasmaRecipe(PlasmaCraft.goopPlutonium.itemID, new ItemStack(PlasmaCraft.ingotPlutonium));
        addPlasmaRecipe(PlasmaCraft.goopRadionite.itemID, new ItemStack(PlasmaCraft.ingotRadionite));
        addPlasmaRecipe(PlasmaCraft.goopNeptunium.itemID, new ItemStack(PlasmaCraft.ingotNeptunium));
        addPlasmaRecipe(PlasmaCraft.goopObsidium.itemID, new ItemStack(PlasmaCraft.ingotObsidium));
        addPlasmaRecipe(PlasmaCraft.goopUranium.itemID, new ItemStack(PlasmaCraft.ingotUranium));
        addPlasmaRecipe(PlasmaCraft.goopCryonite.itemID, new ItemStack(PlasmaCraft.ingotCryonite));
        addPlasmaRecipe(PlasmaCraft.acidVial.itemID, new ItemStack(PlasmaCraft.goopAcid));
        addPlasmaRecipe(PlasmaCraft.netherflowVial.itemID, new ItemStack(PlasmaCraft.goopNetherflow));
        addPlasmaRecipe(PlasmaCraft.plutoniumVial.itemID, new ItemStack(PlasmaCraft.goopPlutonium));
        addPlasmaRecipe(PlasmaCraft.radioniteVial.itemID, new ItemStack(PlasmaCraft.goopRadionite));
        addPlasmaRecipe(PlasmaCraft.neptuniumVial.itemID, new ItemStack(PlasmaCraft.goopNeptunium));
        addPlasmaRecipe(PlasmaCraft.obsidiumVial.itemID, new ItemStack(PlasmaCraft.goopObsidium));
        addPlasmaRecipe(PlasmaCraft.uraniumVial.itemID, new ItemStack(PlasmaCraft.goopUranium));
        addPlasmaRecipe(PlasmaCraft.cryoniteVial.itemID, new ItemStack(PlasmaCraft.goopCryonite));
        addPlasmaRecipe(PlasmaCraft.goopAcid.itemID, new ItemStack(PlasmaCraft.plasma));
        //addPlasmaRecipe(PlasmaCraft.batteryCharged.itemID, new ItemStack(PlasmaCraft.batteryOverCharged));
        addPlasmaRecipe(Item.slimeBall.itemID, new ItemStack(PlasmaCraft.goopAcid));
    }

    public void addPlasmaRecipe(int i, ItemStack itemstack)
    {
        recipeDictionary.put(Integer.valueOf(i), itemstack);
    }

    public ItemStack getPlasmaRecipe(int i, int meta)
    {
    	if(i == PlasmaCraft.orePlasma.blockID)
    	{
    		return (ItemStack)recipeDictionary.get(Integer.valueOf(meta));
    	}
    	else if(i < 5)
    		return null;
    	else
    		return (ItemStack)recipeDictionary.get(Integer.valueOf(i));
    }

}
