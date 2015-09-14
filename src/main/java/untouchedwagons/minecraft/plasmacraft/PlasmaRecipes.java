package untouchedwagons.minecraft.plasmacraft;

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

		addPlasmaRecipe(PlasmaCraft.blocks.frozenCryonite, new ItemStack(PlasmaCraft.items.goopCryonite));
		addPlasmaRecipe(PlasmaCraft.items.goopNetherflow, new ItemStack(PlasmaCraft.items.ingotNetherflow));
		addPlasmaRecipe(PlasmaCraft.items.goopPlutonium, new ItemStack(PlasmaCraft.items.ingotPlutonium));
		addPlasmaRecipe(PlasmaCraft.items.goopRadionite, new ItemStack(PlasmaCraft.items.ingotRadionite));
		addPlasmaRecipe(PlasmaCraft.items.goopNeptunium, new ItemStack(PlasmaCraft.items.ingotNeptunium));
		addPlasmaRecipe(PlasmaCraft.items.goopObsidium, new ItemStack(PlasmaCraft.items.ingotObsidium));
		addPlasmaRecipe(PlasmaCraft.items.goopUranium, new ItemStack(PlasmaCraft.items.ingotUranium));
		addPlasmaRecipe(PlasmaCraft.items.goopCryonite, new ItemStack(PlasmaCraft.items.ingotCryonite));
		addPlasmaRecipe(PlasmaCraft.items.acidVial, new ItemStack(PlasmaCraft.items.goopAcid));
		addPlasmaRecipe(PlasmaCraft.items.netherflowVial, new ItemStack(PlasmaCraft.items.goopNetherflow));
		addPlasmaRecipe(PlasmaCraft.items.plutoniumVial, new ItemStack(PlasmaCraft.items.goopPlutonium));
		addPlasmaRecipe(PlasmaCraft.items.radioniteVial, new ItemStack(PlasmaCraft.items.goopRadionite));
		addPlasmaRecipe(PlasmaCraft.items.neptuniumVial, new ItemStack(PlasmaCraft.items.goopNeptunium));
		addPlasmaRecipe(PlasmaCraft.items.obsidiumVial, new ItemStack(PlasmaCraft.items.goopObsidium));
		addPlasmaRecipe(PlasmaCraft.items.uraniumVial, new ItemStack(PlasmaCraft.items.goopUranium));
		addPlasmaRecipe(PlasmaCraft.items.cryoniteVial, new ItemStack(PlasmaCraft.items.goopCryonite));
		addPlasmaRecipe(PlasmaCraft.items.goopAcid, new ItemStack(PlasmaCraft.items.plasma));
		addPlasmaRecipe(Items.slime_ball, new ItemStack(PlasmaCraft.items.goopAcid));
	}

	public void addPlasmaRecipe(Object object, ItemStack itemstack)
	{
		recipeDictionary.put(object, itemstack);
	}

	public ItemStack getPlasmaRecipe(Object i, int meta)
	{
		return recipeDictionary.get(i);
	}

}
