package com.elvenwater.malkierian.Plasmacraft.common.listeners;

import com.elvenwater.malkierian.Plasmacraft.common.PlasmaCraft;

import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class PCBucketFillEvent
{
	@ForgeSubscribe
	public void onPlayerUseBucket(FillBucketEvent event)
	{
		World world = event.world;
		int blockId = world.getBlockId(event.target.blockX, event.target.blockY, event.target.blockZ);
		if(blockId == PlasmaCraft.acidMoving.blockID || blockId == PlasmaCraft.acidStill.blockID
				|| blockId == PlasmaCraft.cryoniteMoving.blockID || blockId == PlasmaCraft.cryoniteStill.blockID
				|| blockId == PlasmaCraft.neptuniumMoving.blockID || blockId == PlasmaCraft.neptuniumStill.blockID
				|| blockId == PlasmaCraft.netherflowMoving.blockID || blockId == PlasmaCraft.netherflowStill.blockID
				|| blockId == PlasmaCraft.obsidiumMoving.blockID || blockId == PlasmaCraft.obsidiumStill.blockID
				|| blockId == PlasmaCraft.plutoniumMoving.blockID || blockId == PlasmaCraft.plutoniumStill.blockID
				|| blockId == PlasmaCraft.radioniteMoving.blockID || blockId == PlasmaCraft.radioniteStill.blockID
				|| blockId == PlasmaCraft.uraniumMoving.blockID || blockId == PlasmaCraft.uraniumStill.blockID)
		{
			event.setResult(Result.DENY);
			event.setCanceled(true);
		}
	}
}
