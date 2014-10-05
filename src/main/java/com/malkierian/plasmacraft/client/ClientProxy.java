package com.malkierian.plasmacraft.client;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;

import com.malkierian.plasmacraft.core.CommonProxy;
import com.malkierian.plasmacraft.core.PlasmaCraft;
import com.malkierian.plasmacraft.core.entities.EntityAcid;
import com.malkierian.plasmacraft.core.entities.EntityAcidTNTPrimed;
import com.malkierian.plasmacraft.core.entities.EntityCausticBoat;
import com.malkierian.plasmacraft.core.entities.EntityCryoBlast;
import com.malkierian.plasmacraft.core.entities.EntityLaser;
import com.malkierian.plasmacraft.core.entities.EntityLaserShotgun;
import com.malkierian.plasmacraft.core.entities.EntityMutantCow;
import com.malkierian.plasmacraft.core.entities.EntityPlasma;
import com.malkierian.plasmacraft.core.entities.EntityRailGun;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
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
	public int addArmor(String name)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(name);
	}
}
