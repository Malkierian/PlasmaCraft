package net.minecraft.src.Plasmacraft;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.forge.IOreHandler;

public class PCOreHandler implements IOreHandler
{
	public PCOreHandler()
	{
		
	}
	@Override
	public void registerOre(String oreClass, ItemStack ore) {
		if(oreClass == "oreUranium")
		{
			PlasmaRecipes.getInstance().addPlasmaRecipe((ore.itemID == PlasmaCraftCore.oreBlockID ? PlasmaCraftCore.uraniumMeta : ore.itemID), new ItemStack(PlasmaCraftCore.goopUranium));
		}
		else if(oreClass == "orePlutonium")
		{
			PlasmaRecipes.getInstance().addPlasmaRecipe((ore.itemID == PlasmaCraftCore.oreBlockID ? PlasmaCraftCore.plutoniumMeta : ore.itemID), new ItemStack(PlasmaCraftCore.goopPlutonium));
		}
		else if(oreClass == "ingotUranium")
		{
	        ModLoader.AddRecipe(new ItemStack(PlasmaCraftCore.beamSplitter, 1), new Object[] {
	            " N ", "BXQ", " N ", Character.valueOf('N'), PlasmaCraftCore.ingotNetherflow, Character.valueOf('X'), PlasmaCraftCore.BatteryPlasma, Character.valueOf('Q'), ore.getItem(), Character.valueOf('B'), 
	            Item.diamond
	        });
	        ModLoader.AddRecipe(new ItemStack(PlasmaCraftCore.acidgun, 1), new Object[] {
	            "  Z", "ABC", " MN", Character.valueOf('Z'), PlasmaCraftCore.acidVial, Character.valueOf('A'), PlasmaCraftCore.ingotObsidium, Character.valueOf('B'), ore.getItem(), Character.valueOf('C'), 
	            PlasmaCraftCore.reinforcedGlass, Character.valueOf('M'), PlasmaCraftCore.BatteryPlasma, Character.valueOf('N'), Item.ingotIron
	        });
		}
		else if(oreClass == "ingotPlutonium")
		{
	        ModLoader.AddRecipe(new ItemStack(PlasmaCraftCore.lasergun, 1), new Object[] {
	            "XYZ", " YQ", Character.valueOf('X'), PlasmaCraftCore.ingotNetherflow, Character.valueOf('Y'), PlasmaCraftCore.ingotObsidium, Character.valueOf('Z'), PlasmaCraftCore.goopNetherflow, Character.valueOf('Q'), ore.getItem()
	        });
	        ModLoader.AddRecipe(new ItemStack(PlasmaCraftCore.plasmagun, 1), new Object[] {
	            "XBZ", " YZ", Character.valueOf('X'), Item.diamond, Character.valueOf('B'), PlasmaCraftCore.plasma, Character.valueOf('Z'), ore.getItem(), Character.valueOf('Y'), PlasmaCraftCore.ingotObsidium
	        });
	        ModLoader.AddRecipe(new ItemStack(PlasmaCraftCore.ThermoPellet, 1), new Object[] {
	            "III", "IXI", "III", Character.valueOf('X'), ore.getItem(), Character.valueOf('I'), PlasmaCraftCore.goopUranium
	        });
	        ModLoader.AddRecipe(new ItemStack(PlasmaCraftCore.lasershotgun, 1), new Object[] {
	            "  Z", "XBQ", " UP", Character.valueOf('Z'), Item.redstoneRepeater, Character.valueOf('X'), PlasmaCraftCore.beamSplitter, Character.valueOf('B'), PlasmaCraftCore.ingotNetherflow, Character.valueOf('Q'), 
	            PlasmaCraftCore.BatteryCharged, Character.valueOf('P'), PlasmaCraftCore.ingotPlutonium, Character.valueOf('U'), PlasmaCraftCore.ingotRadionite
	        });
	        ModLoader.AddRecipe(new ItemStack(PlasmaCraftCore.railgun, 1), new Object[] {
	            "XYZ", " BC", "XY ", Character.valueOf('Z'), PlasmaCraftCore.BatteryPlasma, Character.valueOf('X'), PlasmaCraftCore.ingotObsidium, Character.valueOf('B'), PlasmaCraftCore.goopPlutonium, Character.valueOf('C'), 
	            Item.ingotGold, Character.valueOf('Y'), PlasmaCraftCore.ingotPlutonium
	        });
		}
	}
	
}