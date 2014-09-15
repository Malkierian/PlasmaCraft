package com.elvenwater.malkierian.Plasmacraft.client;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.MinecraftForgeClient;

import com.elvenwater.malkierian.Plasmacraft.common.CommonProxy;
import com.elvenwater.malkierian.Plasmacraft.common.EntityAcidGrenade;
import com.elvenwater.malkierian.Plasmacraft.common.EntityAcidTNTPrimed;
import com.elvenwater.malkierian.Plasmacraft.common.EntityCausticBoat;
import com.elvenwater.malkierian.Plasmacraft.common.PlasmaCraft;
import com.elvenwater.malkierian.Plasmacraft.common.Entities.EntityAcid;
import com.elvenwater.malkierian.Plasmacraft.common.Entities.EntityCryoBlast;
import com.elvenwater.malkierian.Plasmacraft.common.Entities.EntityLaser;
import com.elvenwater.malkierian.Plasmacraft.common.Entities.EntityLaserShotgun;
import com.elvenwater.malkierian.Plasmacraft.common.Entities.EntityMutantCow;
import com.elvenwater.malkierian.Plasmacraft.common.Entities.EntityPlasma;
import com.elvenwater.malkierian.Plasmacraft.common.Entities.EntityRailGun;

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
		RenderingRegistry.registerEntityRenderingHandler(EntityCausticBoat.class, new RenderCausticBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityAcidTNTPrimed.class, new RenderAcidTNTPrimed());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityAcid.class, new RenderAcid());
		RenderingRegistry.registerEntityRenderingHandler(EntityCryoBlast.class, new RenderCryoBlast());
		RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new RenderLaser());
		RenderingRegistry.registerEntityRenderingHandler(EntityLaserShotgun.class, new RenderLaserShotgun());
		RenderingRegistry.registerEntityRenderingHandler(EntityMutantCow.class, new RenderMutantCow(new ModelMutantCow(), 1.0f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPlasma.class, new RenderPlasma());
		RenderingRegistry.registerEntityRenderingHandler(EntityRailGun.class, new RenderRailGun());
	}
	
	@Override
	public boolean getEntityInstanceOf(Entity entity)
	{
		return entity instanceof EntityPlayerSP;
	}
	
	@Override
	public void registerTextureFX()
	{

	}
	
	private void registerLiquidFX(int stillIndex, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12)
	{

	}
	
	@Override
	public int addArmor(String name)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(name);
	}
}
