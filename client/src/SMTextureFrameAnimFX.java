package net.minecraft.src;


import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SMTextureFrameAnimFX extends TextureFX
{
    public SMTextureFrameAnimFX(int baseIndex, String fileName, int w, int h)
    {
        super(baseIndex);
        width = w;
        height = h;
        tick = 0;
        tileSize = 1;
        numFrames = 0;
        srcSize = 0;
        try
        {
            BufferedImage bufferedimage = ImageIO.read((net.minecraft.client.Minecraft.class).getResource(fileName));
            srcSize = bufferedimage.getHeight();
        	fileBuf = new int[bufferedimage.getWidth() * bufferedimage.getHeight()];
            numFrames = bufferedimage.getWidth() / bufferedimage.getHeight();
            bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), fileBuf, 0, bufferedimage.getWidth());
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    public void onTick()
    {
		if(srcSize == 0)
		{
			return;
		}
        tick++;
		tick %= numFrames;
		for(int v = 0; v < srcSize; v++) // edited, Height
		{
			int srcBase = v * srcSize * numFrames;
			for(int u = 0; u < srcSize; u++) // edited, Width
			{
				int srcU = srcSize * tick + u;
				int color = fileBuf[srcBase + srcU];
				int blue  = (color >>  0) & 0x000000ff;
				int green = (color >>  8) & 0x000000ff;
				int red   = (color >> 16) & 0x000000ff;
				int alpha = (color >> 24) & 0x000000ff;
				if(anaglyphEnabled)
				{
					int new_red = (red * 30 + green * 59 + blue * 11) / 100;
					int new_green = (red * 30 + green * 70) / 100;
					int new_blue = (red * 30 + blue * 70) / 100;
					red = new_red;
					green = new_green;
					blue = new_blue;
				}
				if (srcSize != width) // new code
				{
					int scale = width / srcSize;
								
					for (int x = 0; x < scale; x++)
					{
						for (int z = 0; z < scale; z++)
						{
						int offset = (v * width * scale) + (u * scale) + (x*width) + z;
						offset = offset * 4; // byte offset					
						imageData[offset + 0] = (byte)red;
						imageData[offset + 1] = (byte)green;
						imageData[offset + 2] = (byte)blue;
						imageData[offset + 3] = (byte)alpha;
						}
					}
				}
				else // old code! cause its faster...  
				{
					int offset = (v * width + u) * 4;
					imageData[offset + 0] = (byte)red;
					imageData[offset + 1] = (byte)green;
					imageData[offset + 2] = (byte)blue;
					imageData[offset + 3] = (byte)alpha;
				}

			}
		}
    }

    protected int fileBuf[];
    private int tick;
    private int srcSize;
    private int numFrames;
    private int width;
    private int height;
}