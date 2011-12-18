package net.minecraft.src.Plasmacraft;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.forge.IBucketHandler;

public class PCBucketHandler implements IBucketHandler {

	@Override
	public ItemStack fillCustomBucket(World world, int i, int j, int k) {
		int blockid = world.getBlockId(i, j, k);
		if(blockid == PlasmaCraftCore.acidStillBlockID || blockid == PlasmaCraftCore.acidFlowingBlockID
				|| blockid == PlasmaCraftCore.cryoniteStillBlockID || blockid == PlasmaCraftCore.cryoniteFlowingBlockID
				|| blockid == PlasmaCraftCore.neptuniumStillBlockID || blockid == PlasmaCraftCore.neptuniumFlowingBlockID
				|| blockid == PlasmaCraftCore.netherflowStillBlockID || blockid == PlasmaCraftCore.netherflowFlowingBlockID
				|| blockid == PlasmaCraftCore.obsidiumStillBlockID || blockid == PlasmaCraftCore.obsidiumFlowingBlockID
				|| blockid == PlasmaCraftCore.plutoniumStillBlockID || blockid == PlasmaCraftCore.plutoniumFlowingBlockID
				|| blockid == PlasmaCraftCore.radioniteStillBlockID || blockid == PlasmaCraftCore.radioniteFlowingBlockID
				|| blockid == PlasmaCraftCore.uraniumStillBlockID || blockid == PlasmaCraftCore.uraniumFlowingBlockID)
			return new ItemStack(Item.bucketEmpty);
		return null;
	}
	
	public PCBucketHandler()
	{
		
	}
	
}