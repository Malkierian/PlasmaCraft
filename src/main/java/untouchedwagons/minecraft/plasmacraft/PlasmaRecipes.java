package untouchedwagons.minecraft.plasmacraft;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import untouchedwagons.minecraft.plasmacraft.items.ItemGoop;
import untouchedwagons.minecraft.plasmacraft.items.ItemIngot;
import untouchedwagons.minecraft.plasmacraft.items.ItemVial;

public class PlasmaRecipes
{
	private static final PlasmaRecipes instance = new PlasmaRecipes();
	private Map<ItemStack, ItemStack> recipeDictionary;

	public static final PlasmaRecipes getInstance()
	{
		return instance;
	}

	private PlasmaRecipes()
	{
		recipeDictionary = new HashMap<ItemStack, ItemStack>();

		addPlasmaRecipe(new ItemStack(PlasmaCraft.blocks.frozenCryonite), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.CRYONITE_DAMAGE));
		addPlasmaRecipe(new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.NETHERFLOW_DAMAGE), new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.NETHERFLOW_DAMAGE));
		addPlasmaRecipe(new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.PLUTONIUM_DAMAGE), new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.PLUTONIUM_DAMAGE));
		addPlasmaRecipe(new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.RADIONITE_DAMAGE), new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.RADIONITE_DAMAGE));
		addPlasmaRecipe(new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.NEPTUNIUM_DAMAGE), new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.NEPTUNIUM_DAMAGE));
		addPlasmaRecipe(new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.OBSIDIUM_DAMAGE), new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.OBSIDIUM_DAMAGE));
		addPlasmaRecipe(new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.URANIUM_DAMAGE), new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.URANIUM_DAMAGE));
		addPlasmaRecipe(new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop.CRYONITE_DAMAGE), new ItemStack(PlasmaCraft.items.ingots, 1, ItemIngot.CRYONITE_DAMAGE));
		addPlasmaRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.ACID_DAMAGE), new ItemStack(PlasmaCraft.items.goop));
		addPlasmaRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.NETHERFLOW_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop. NETHERFLOW_DAMAGE));
		addPlasmaRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.PLUTONIUM_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop. PLUTONIUM_DAMAGE));
		addPlasmaRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.RADIONITE_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop. RADIONITE_DAMAGE));
		addPlasmaRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.NEPTUNIUM_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop. NEPTUNIUM_DAMAGE));
		addPlasmaRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.OBSIDIUM_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop. OBSIDIUM_DAMAGE));
		addPlasmaRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.URANIUM_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop. URANIUM_DAMAGE));
		addPlasmaRecipe(new ItemStack(PlasmaCraft.items.vial, 1, ItemVial.CRYONITE_DAMAGE), new ItemStack(PlasmaCraft.items.goop, 1, ItemGoop. CRYONITE_DAMAGE));
		addPlasmaRecipe(new ItemStack(PlasmaCraft.items.goop), new ItemStack(PlasmaCraft.items.ingots));
		addPlasmaRecipe(new ItemStack(Items.slime_ball), new ItemStack(PlasmaCraft.items.goop));
	}

	public void addPlasmaRecipe(ItemStack input, ItemStack output)
	{
		recipeDictionary.put(input, output);
	}

    public ItemStack getPlasmaRecipe(ItemStack furnaceItemStack) {
        return recipeDictionary.get(furnaceItemStack);
    }
}
