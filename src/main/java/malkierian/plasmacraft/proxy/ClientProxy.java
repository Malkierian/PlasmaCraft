package malkierian.plasmacraft.proxy;

import malkierian.plasmacraft.PlasmaCraft;
import malkierian.plasmacraft.client.renderers.RenderAcid;
import malkierian.plasmacraft.client.renderers.RenderAcidTNTPrimed;
import malkierian.plasmacraft.entity.EntityAcid;
import malkierian.plasmacraft.entity.EntityAcidTNTPrimed;
import malkierian.plasmacraft.init.PCFluids;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
//		RenderingRegistry.registerEntityRenderingHandler(EntityCausticBoat.class, new RenderCausticBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityAcidTNTPrimed.class, new RenderAcidTNTPrimed(Minecraft.getMinecraft().getRenderManager()));
//		
		RenderingRegistry.registerEntityRenderingHandler(EntityAcid.class, new RenderAcid(Minecraft.getMinecraft().getRenderManager()));
//		RenderingRegistry.registerEntityRenderingHandler(EntityCryoBlast.class, new RenderCryoBlast());
//		RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new RenderLaser());
//		RenderingRegistry.registerEntityRenderingHandler(EntityLaserShotgun.class, new RenderLaserShotgun());
//		RenderingRegistry.registerEntityRenderingHandler(EntityMutantCow.class, new RenderMutantCow(new ModelMutantCow(), 1.0f));
//		RenderingRegistry.registerEntityRenderingHandler(EntityPlasma.class, new RenderPlasma());
//		RenderingRegistry.registerEntityRenderingHandler(EntityRailGun.class, new RenderRailGun());
	}
	
	@Override
	public boolean getEntityInstanceOf(Entity entity)
	{
		return entity instanceof EntityPlayerSP;
	}
	
	@Override
	public void preInit()
	{
		PCFluids.registerRenders();
	}
	
	@Override
	public void registerItemRenderer(Item item, int meta, String id)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(PlasmaCraft.modId + ":" + id, "inventory"));
	}
	
//	@Override
//	public int addArmor(String name)
//	{
//		return RenderingRegistry.addNewArmourRendererPrefix(name);
//	}
}
