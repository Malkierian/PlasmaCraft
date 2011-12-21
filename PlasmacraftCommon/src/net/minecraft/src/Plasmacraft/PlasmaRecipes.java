package net.minecraft.src.Plasmacraft;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

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
        addPlasmaRecipe(PlasmaCraftCore.plutoniumMeta, new ItemStack(PlasmaCraftCore.goopPlutonium));
        addPlasmaRecipe(PlasmaCraftCore.radioniteMeta, new ItemStack(PlasmaCraftCore.goopRadionite));
        addPlasmaRecipe(PlasmaCraftCore.neptuniumMeta, new ItemStack(PlasmaCraftCore.goopNeptunium));
        addPlasmaRecipe(PlasmaCraftCore.obsidiumMeta, new ItemStack(PlasmaCraftCore.goopObsidium));
        addPlasmaRecipe(PlasmaCraftCore.uraniumMeta, new ItemStack(PlasmaCraftCore.goopUranium));
        addPlasmaRecipe(PlasmaCraftCore.frozenCryonite.blockID, new ItemStack(PlasmaCraftCore.goopCryonite));
        addPlasmaRecipe(PlasmaCraftCore.goopNetherflow.shiftedIndex, new ItemStack(PlasmaCraftCore.ingotNetherflow));
        addPlasmaRecipe(PlasmaCraftCore.goopPlutonium.shiftedIndex, new ItemStack(PlasmaCraftCore.ingotPlutonium));
        addPlasmaRecipe(PlasmaCraftCore.goopRadionite.shiftedIndex, new ItemStack(PlasmaCraftCore.ingotRadionite));
        addPlasmaRecipe(PlasmaCraftCore.goopNeptunium.shiftedIndex, new ItemStack(PlasmaCraftCore.ingotNeptunium));
        addPlasmaRecipe(PlasmaCraftCore.goopObsidium.shiftedIndex, new ItemStack(PlasmaCraftCore.ingotObsidium));
        addPlasmaRecipe(PlasmaCraftCore.goopUranium.shiftedIndex, new ItemStack(PlasmaCraftCore.ingotUranium));
        addPlasmaRecipe(PlasmaCraftCore.goopCryonite.shiftedIndex, new ItemStack(PlasmaCraftCore.ingotCryonite));
        addPlasmaRecipe(PlasmaCraftCore.fullAcidVial.shiftedIndex, new ItemStack(PlasmaCraftCore.plasmaGel));
        addPlasmaRecipe(PlasmaCraftCore.netherflowVial.shiftedIndex, new ItemStack(PlasmaCraftCore.goopNetherflow));
        addPlasmaRecipe(PlasmaCraftCore.plutoniumVial.shiftedIndex, new ItemStack(PlasmaCraftCore.goopPlutonium));
        addPlasmaRecipe(PlasmaCraftCore.radioniteVial.shiftedIndex, new ItemStack(PlasmaCraftCore.goopRadionite));
        addPlasmaRecipe(PlasmaCraftCore.neptuniumVial.shiftedIndex, new ItemStack(PlasmaCraftCore.goopNeptunium));
        addPlasmaRecipe(PlasmaCraftCore.obsidiumVial.shiftedIndex, new ItemStack(PlasmaCraftCore.goopObsidium));
        addPlasmaRecipe(PlasmaCraftCore.uraniumVial.shiftedIndex, new ItemStack(PlasmaCraftCore.goopUranium));
        addPlasmaRecipe(PlasmaCraftCore.cryoniteVial.shiftedIndex, new ItemStack(PlasmaCraftCore.goopCryonite));
        addPlasmaRecipe(PlasmaCraftCore.plasmaGel.shiftedIndex, new ItemStack(PlasmaCraftCore.plasma));
        addPlasmaRecipe(PlasmaCraftCore.BatteryCharged.shiftedIndex, new ItemStack(PlasmaCraftCore.BatteryOvercharged));
        addPlasmaRecipe(Item.slimeBall.shiftedIndex, new ItemStack(PlasmaCraftCore.plasmaGel));
        addPlasmaRecipe(Block.obsidian.blockID, new ItemStack(PlasmaCraftCore.goopObsidium));
    }

    public void addPlasmaRecipe(int i, ItemStack itemstack)
    {
        recipeDictionary.put(Integer.valueOf(i), itemstack);
    }

    public ItemStack getPlasmaRecipe(int i, int meta)
    {
    	if(i == PlasmaCraftCore.orePlasma.blockID)
    	{
    		return (ItemStack)recipeDictionary.get(Integer.valueOf(meta));
    	}
    	else
    		return (ItemStack)recipeDictionary.get(Integer.valueOf(i));
    }

}
