package com.malkierian.plasmacraft.common.listeners;

import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;

import com.malkierian.plasmacraft.common.PlasmaCraft;

import cpw.mods.fml.common.eventhandler.Event.Result;

public class PCBucketFillEvent
{
//	@ForgeSubscribe
//	public void onPlayerUseBucket(FillBucketEvent event)
//	{
//		World world = event.world;
//		int blockId = world.getBlock(event.target.blockX, event.target.blockY, event.target.blockZ);
//		if(blockId == PlasmaCraft.acidMoving.blockID || blockId == PlasmaCraft.acidStill.blockID
//				|| blockId == PlasmaCraft.cryoniteMoving.blockID || blockId == PlasmaCraft.cryoniteStill.blockID
//				|| blockId == PlasmaCraft.neptuniumMoving.blockID || blockId == PlasmaCraft.neptuniumStill.blockID
//				|| blockId == PlasmaCraft.netherflowMoving.blockID || blockId == PlasmaCraft.netherflowStill.blockID
//				|| blockId == PlasmaCraft.obsidiumMoving.blockID || blockId == PlasmaCraft.obsidiumStill.blockID
//				|| blockId == PlasmaCraft.plutoniumMoving.blockID || blockId == PlasmaCraft.plutoniumStill.blockID
//				|| blockId == PlasmaCraft.radioniteMoving.blockID || blockId == PlasmaCraft.radioniteStill.blockID
//				|| blockId == PlasmaCraft.uraniumMoving.blockID || blockId == PlasmaCraft.uraniumStill.blockID)
//		{
//			event.setResult(Result.DENY);
//			event.setCanceled(true);
//		}
//	}
}
