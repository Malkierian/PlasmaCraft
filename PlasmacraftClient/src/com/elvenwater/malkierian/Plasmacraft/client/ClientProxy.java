package com.elvenwater.malkierian.Plasmacraft.client;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayerSP;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ModLoader;
import net.minecraft.src.RenderEngine;
import net.minecraftforge.client.MinecraftForgeClient;

import com.elvenwater.malkierian.Plasmacraft.common.CommonProxy;
import com.elvenwater.malkierian.Plasmacraft.common.PlasmaCraft;
import com.elvenwater.malkierian.Plasmacraft.client.TextureFrameAnimFX;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		MinecraftForgeClient.preloadTexture(ITEMS_PNG);
		MinecraftForgeClient.preloadTexture(BLOCK_PNG);
		MinecraftForgeClient.preloadTexture(LIQUID_PNG);
		
		PlasmaCraft.causticID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new RenderCaustic());
	}
	
	@Override
	public boolean getEntityInstanceOf(Entity entity)
	{
		return entity instanceof EntityPlayerSP;
	}
	
	@Override
	public void registerTextureFX()
	{
		registerLiquidFX(PlasmaCraft.cryoniteStillIndex, 0.5F, 0.8F, 1.0F, 1.0F, 128F, 230F, 255F, 146F, 32F, 50F, 36F, 55F);
        registerLiquidFX(PlasmaCraft.acidStillIndex, 0.5F, 1.0F, 0.5F, 1.0F, 32F, 255F, 50F, 146F, 32F, 64F, 64F, 50F);
        registerLiquidFX(PlasmaCraft.plutoniumStillIndex, 0.5F, 0.9F, 1.0F, 1.0F, 32F, 64F, 64F, 255F, 32F, 64F, 64F, 75F);
        registerLiquidFX(PlasmaCraft.radioniteStillIndex, 0.7F, 0.75F, 1.0F, 1.0F, 64F, 64F, 64F, 255F, 64F, 48F, 64F, 75F);
        registerLiquidFX(PlasmaCraft.uraniumStillIndex, 0.9F, 0.9F, 0.3F, 1.0F, 255F, 255F, 64F, 160F, 64F, 64F, 32F, 50F);
        registerLiquidFX(PlasmaCraft.neptuniumStillIndex, 1.0F, 0.75F, 0.5F, 1.0F, 255F, 128F, 64F, 180F, 64F, 50F, 36F, 55F);
        registerLiquidFX(PlasmaCraft.netherflowStillIndex, 1.0F, 0.4F, 0.4F, 1.0F, 255F, 32F, 32F, 200F, 64F, 32F, 32F, 60F);
        registerLiquidFX(PlasmaCraft.obsidiumStillIndex, 0.35F, 0.1F, 0.35F, 1.0F, 72F, 64F, 72F, 250F, 40F, 32F, 40F, 10F);
        
        FMLClientHandler.instance().getClient().renderEngine.registerTextureFX(new TextureFrameAnimFX(PlasmaCraft.plasmaBenchFrontAnim, "/PlasmaCraftSprites/animations/animatedplasmificator.png"));
	}
	
	private void registerLiquidFX(int stillIndex, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12)
	{
		RenderEngine renderEngine = FMLClientHandler.instance().getClient().renderEngine;
    	renderEngine.registerTextureFX(new TextureTintedStillFX(stillIndex, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12));
    	renderEngine.registerTextureFX(new TextureTintedFlowFX(stillIndex + 1, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12));
    	renderEngine.registerTextureFX(new TextureTintedFlowFX(stillIndex + 2, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12));
    	renderEngine.registerTextureFX(new TextureTintedFlowFX(stillIndex + 17, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12));
    	renderEngine.registerTextureFX(new TextureTintedFlowFX(stillIndex + 18, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12));
	}

	public static boolean shouldSideBeRendered(IBlockAccess iblockaccess,
			int i, int j, int k, int l)
	{
		boolean flag = false;
        if(flag || CommonProxy.shouldSideBeRendered(iblockaccess, i, j, k - 1, 2))
        {
            flag = true;
        }
        if(flag || shouldSideBeRendered(iblockaccess, i, j, k + 1, 3))
        {
            flag = true;
        }
        if(flag || shouldSideBeRendered(iblockaccess, i - 1, j, k, 4))
        {
            flag = true;
        }
        if(flag || shouldSideBeRendered(iblockaccess, i + 1, j, k, 5))
        {
            flag = true;
        }
        if(flag || shouldSideBeRendered(iblockaccess, i, j + 1, k - 1, 2))
        {
            flag = true;
        }
        if(flag || shouldSideBeRendered(iblockaccess, i, j + 1, k + 1, 3))
        {
            flag = true;
        }
        if(flag || shouldSideBeRendered(iblockaccess, i - 1, j + 1, k, 4))
        {
            flag = true;
        }
        if(flag || shouldSideBeRendered(iblockaccess, i + 1, j + 1, k, 5))
        {
            flag = true;
        }
        return flag;
	}
}
