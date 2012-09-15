package com.elvenwater.malkierian.Plasmacraft.client;

import net.minecraftforge.client.MinecraftForgeClient;

import com.elvenwater.malkierian.Plasmacraft.common.CommonProxy;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers() {
		MinecraftForgeClient.preloadTexture(ITEMS_PNG);
		MinecraftForgeClient.preloadTexture(BLOCK_PNG);
	}
}
