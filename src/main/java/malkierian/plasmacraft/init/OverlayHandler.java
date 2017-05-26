package malkierian.plasmacraft.init;

import malkierian.plasmacraft.blocks.BlockCausticFluid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OverlayHandler
{
	public OverlayHandler(){}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onRenderBlockOverlay(RenderBlockOverlayEvent event)
	{
		if(event.getOverlayType() == RenderBlockOverlayEvent.OverlayType.WATER)
		{
			BlockPos pos = event.getBlockPos();
			World world = event.getPlayer().worldObj;
			IBlockState actualBlock = world.getBlockState(pos);
			if(actualBlock.getBlock() != Blocks.WATER)
			{
				event.setCanceled(true);
				renderCausticOverlay(((BlockCausticFluid)actualBlock.getBlock()).undertex, event.getRenderPartialTicks());
			}
		}
	}
	
	public void renderCausticOverlay(ResourceLocation underTex, float partialTicks)
	{
		Minecraft mc = Minecraft.getMinecraft();
		mc.getTextureManager().bindTexture(underTex);
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        float f = mc.thePlayer.getBrightness(partialTicks);
        GlStateManager.color(f, f, f, 0.5F);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.pushMatrix();
        float f1 = 4.0F;
        float f2 = -1.0F;
        float f3 = 1.0F;
        float f4 = -1.0F;
        float f5 = 1.0F;
        float f6 = -0.5F;
        float f7 = -mc.thePlayer.rotationYaw / 64.0F;
        float f8 = mc.thePlayer.rotationPitch / 64.0F;
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos(-1.0D, -1.0D, -0.5D).tex((double)(4.0F + f7), (double)(4.0F + f8)).endVertex();
        vertexbuffer.pos(1.0D, -1.0D, -0.5D).tex((double)(0.0F + f7), (double)(4.0F + f8)).endVertex();
        vertexbuffer.pos(1.0D, 1.0D, -0.5D).tex((double)(0.0F + f7), (double)(0.0F + f8)).endVertex();
        vertexbuffer.pos(-1.0D, 1.0D, -0.5D).tex((double)(4.0F + f7), (double)(0.0F + f8)).endVertex();
        tessellator.draw();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
	}
}
