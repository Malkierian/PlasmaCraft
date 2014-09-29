package com.malkierian.plasmacraft.common;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class PlasmaRecipes
{
	private static final PlasmaRecipes instance = new PlasmaRecipes();
	private Map<Object, ItemStack> recipeDictionary;

	public static final PlasmaRecipes getInstance()
	{
		return instance;
	}

	private PlasmaRecipes()
	{
		recipeDictionary = new HashMap<Object, ItemStack>();
//		addPlasmaRecipe(PlasmaCraft.plutonium, new ItemStack(PlasmaCraft.goopPlutonium));
//		addPlasmaRecipe(PlasmaCraft.radionite, new ItemStack(PlasmaCraft.goopRadionite));
//		addPlasmaRecipe(PlasmaCraft.neptunium, new ItemStack(PlasmaCraft.goopNeptunium));
//		addPlasmaRecipe(PlasmaCraft.obsidium, new ItemStack(PlasmaCraft.goopObsidium));
//		addPlasmaRecipe(PlasmaCraft.uranium, new ItemStack(PlasmaCraft.goopUranium));
		addPlasmaRecipe(PlasmaCraft.frozenCryonite, new ItemStack(PlasmaCraft.goopCryonite));
		addPlasmaRecipe(PlasmaCraft.goopNetherflow, new ItemStack(PlasmaCraft.ingotNetherflow));
		addPlasmaRecipe(PlasmaCraft.goopPlutonium, new ItemStack(PlasmaCraft.ingotPlutonium));
		addPlasmaRecipe(PlasmaCraft.goopRadionite, new ItemStack(PlasmaCraft.ingotRadionite));
		addPlasmaRecipe(PlasmaCraft.goopNeptunium, new ItemStack(PlasmaCraft.ingotNeptunium));
		addPlasmaRecipe(PlasmaCraft.goopObsidium, new ItemStack(PlasmaCraft.ingotObsidium));
		addPlasmaRecipe(PlasmaCraft.goopUranium, new ItemStack(PlasmaCraft.ingotUranium));
		addPlasmaRecipe(PlasmaCraft.goopCryonite, new ItemStack(PlasmaCraft.ingotCryonite));
		addPlasmaRecipe(PlasmaCraft.acidVial, new ItemStack(PlasmaCraft.goopAcid));
		addPlasmaRecipe(PlasmaCraft.netherflowVial, new ItemStack(PlasmaCraft.goopNetherflow));
		addPlasmaRecipe(PlasmaCraft.plutoniumVial, new ItemStack(PlasmaCraft.goopPlutonium));
		addPlasmaRecipe(PlasmaCraft.radioniteVial, new ItemStack(PlasmaCraft.goopRadionite));
		addPlasmaRecipe(PlasmaCraft.neptuniumVial, new ItemStack(PlasmaCraft.goopNeptunium));
		addPlasmaRecipe(PlasmaCraft.obsidiumVial, new ItemStack(PlasmaCraft.goopObsidium));
		addPlasmaRecipe(PlasmaCraft.uraniumVial, new ItemStack(PlasmaCraft.goopUranium));
		addPlasmaRecipe(PlasmaCraft.cryoniteVial, new ItemStack(PlasmaCraft.goopCryonite));
		addPlasmaRecipe(PlasmaCraft.goopAcid, new ItemStack(PlasmaCraft.plasma));
		//addPlasmaRecipe(PlasmaCraft.batteryCharged.itemID, new ItemStack(PlasmaCraft.batteryOverCharged));
		addPlasmaRecipe(Items.slime_ball, new ItemStack(PlasmaCraft.goopAcid));
	}

	public void addPlasmaRecipe(Object object, ItemStack itemstack)
	{
		recipeDictionary.put(object, itemstack);
	}

	public ItemStack getPlasmaRecipe(Object i, int meta)
	{
		return (ItemStack)recipeDictionary.get(i);
	}

}
