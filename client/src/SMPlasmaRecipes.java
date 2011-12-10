package net.minecraft.src;


import java.util.HashMap;
import java.util.Map;

public class SMPlasmaRecipes
{

    public static final SMPlasmaRecipes getInstance()
    {
        return instance;
    }

    private SMPlasmaRecipes()
    {
        recipeDictionary = new HashMap();
        addPlasmaRecipe(mod_PlasmaCraft.orePlutonium.blockID, new ItemStack(mod_PlasmaCraft.goopPlutonium));
        addPlasmaRecipe(mod_PlasmaCraft.oreRadionite.blockID, new ItemStack(mod_PlasmaCraft.goopRadionite));
        addPlasmaRecipe(mod_PlasmaCraft.oreNeptunium.blockID, new ItemStack(mod_PlasmaCraft.goopNeptunium));
        addPlasmaRecipe(mod_PlasmaCraft.oreObsidium.blockID, new ItemStack(mod_PlasmaCraft.goopObsidium));
        addPlasmaRecipe(mod_PlasmaCraft.oreUranium.blockID, new ItemStack(mod_PlasmaCraft.goopUranium));
        addPlasmaRecipe(mod_PlasmaCraft.frozenCryonite.blockID, new ItemStack(mod_PlasmaCraft.goopCryonite));

        addPlasmaRecipe(mod_PlasmaCraft.goopNetherflow.shiftedIndex, new ItemStack(mod_PlasmaCraft.ingotNetherflow));
        addPlasmaRecipe(mod_PlasmaCraft.goopPlutonium.shiftedIndex, new ItemStack(mod_PlasmaCraft.ingotPlutonium));
        addPlasmaRecipe(mod_PlasmaCraft.goopRadionite.shiftedIndex, new ItemStack(mod_PlasmaCraft.ingotRadionite));
        addPlasmaRecipe(mod_PlasmaCraft.goopNeptunium.shiftedIndex, new ItemStack(mod_PlasmaCraft.ingotNeptunium));
        addPlasmaRecipe(mod_PlasmaCraft.goopObsidium.shiftedIndex, new ItemStack(mod_PlasmaCraft.ingotObsidium));
        addPlasmaRecipe(mod_PlasmaCraft.goopUranium.shiftedIndex, new ItemStack(mod_PlasmaCraft.ingotUranium));
        addPlasmaRecipe(mod_PlasmaCraft.goopCryonite.shiftedIndex, new ItemStack(mod_PlasmaCraft.ingotCryonite));

        addPlasmaRecipe(mod_PlasmaCraft.netherflowVial.shiftedIndex, new ItemStack(mod_PlasmaCraft.goopNetherflow));
        addPlasmaRecipe(mod_PlasmaCraft.plutoniumVial.shiftedIndex, new ItemStack(mod_PlasmaCraft.goopPlutonium));
        addPlasmaRecipe(mod_PlasmaCraft.radioniteVial.shiftedIndex, new ItemStack(mod_PlasmaCraft.goopRadionite));
        addPlasmaRecipe(mod_PlasmaCraft.neptuniumVial.shiftedIndex, new ItemStack(mod_PlasmaCraft.goopNeptunium));
        addPlasmaRecipe(mod_PlasmaCraft.obsidiumVial.shiftedIndex, new ItemStack(mod_PlasmaCraft.goopObsidium));
        addPlasmaRecipe(mod_PlasmaCraft.uraniumVial.shiftedIndex, new ItemStack(mod_PlasmaCraft.goopUranium));
        addPlasmaRecipe(mod_PlasmaCraft.cryoniteVial.shiftedIndex, new ItemStack(mod_PlasmaCraft.goopCryonite));

        addPlasmaRecipe(mod_PlasmaCraft.plasmaGel.shiftedIndex, new ItemStack(mod_PlasmaCraft.plasma));
        addPlasmaRecipe(mod_PlasmaCraft.BatteryCharged.shiftedIndex, new ItemStack(mod_PlasmaCraft.BatteryOvercharged));
        addPlasmaRecipe(Item.slimeBall.shiftedIndex, new ItemStack(mod_PlasmaCraft.plasmaGel));
        addPlasmaRecipe(Block.obsidian.blockID, new ItemStack(mod_PlasmaCraft.goopObsidium));
    }

    public void addPlasmaRecipe(int id, ItemStack itemstack)
    {
        recipeDictionary.put(Integer.valueOf(id), itemstack);
    }

    public ItemStack getPlasmaRecipe(int id)
    {
        return (ItemStack)recipeDictionary.get(Integer.valueOf(id));
    }

    private static final SMPlasmaRecipes instance = new SMPlasmaRecipes();
    private Map recipeDictionary;

}
