


import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class SMGuiPlasmaBench extends GuiContainer
{

    public SMGuiPlasmaBench(InventoryPlayer inventoryplayer, SMTileEntityPlasmaBench tileentitybench)
    {
        super(new SMContainerPlasmaBench(inventoryplayer, tileentitybench));
        bench = tileentitybench;
        xSize = 176;
        ySize = 200;
    }

    protected void drawGuiContainerForegroundLayer()
    {
        fontRenderer.drawString("Plasmificator", 60, 10, 0x008000);
        fontRenderer.drawString("Queue", 10, 75, 0x008000);
        fontRenderer.drawString("Inventory", 10, 106, 0x008000);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int m, int n)
    {
        int i = mc.renderEngine.getTexture("/gui/plasmabench.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(i);
        int j = (width - xSize) / 2;
        int k = (height - ySize) / 2;
        drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
        if(bench.isBurning())
        {
            int l = bench.getBurnTimeRemainingScaled(80);
            if(l >= 60)
            {
            	drawGradientRect(j + 78, (k + 56) + (19 - (l - 60)), j + 78 + 2, (k + 56) + (19 - (l - 60)) + (l - 60) + 1, 0xffffff00, 0xffffff00);
            	drawGradientRect(j + 80, k + 74, j + 80 + 20, k + 74 + 2, 0xffffff00, 0xffffff00);
            	drawGradientRect(j + 98, k + 54, j + 98 + 2, k + 54 + 20, 0xffffff00, 0xffffff00);
            	drawGradientRect(j + 78, k + 54, j + 78 + 20, k + 54 + 2, 0xffffff00, 0xffffff00);
			}
			else if(l >= 40)
			{
            	drawGradientRect((j + 80) + (19 - (l - 40)), k + 74, (j + 80) + (19 - (l - 40)) + (l - 40) + 1, k + 74 + 2, 0xffffff00, 0xffffff00);
            	drawGradientRect(j + 98, k + 54, j + 98 + 2, k + 54 + 20, 0xffffff00, 0xffffff00);
            	drawGradientRect(j + 78, k + 54, j + 78 + 20, k + 54 + 2, 0xffffff00, 0xffffff00);
			}
			else if(l >= 20)
			{
            	drawGradientRect(j + 98, k + 54, j + 98 + 2, k + 54 + (l - 20) + 1, 0xffffff00, 0xffffff00);
            	drawGradientRect(j + 78, k + 54, j + 78 + 20, k + 54 + 2, 0xffffff00, 0xffffff00);
			}
			else
			{
            	drawGradientRect(j + 78, k + 54, j + 78 + l + 1, k + 54 + 2, 0xffffff00, 0xffffff00);
			}
        }
        int i1 = bench.getCookProgressScaled(24);
        drawTexturedModalRect(j + 76, k + 24, 176, 14, i1 + 1, 16);
    }

    private SMTileEntityPlasmaBench bench;
}
