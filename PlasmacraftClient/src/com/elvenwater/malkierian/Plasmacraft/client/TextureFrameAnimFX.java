package com.elvenwater.malkierian.Plasmacraft.client;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.client.texturepacks.TexturePackList;
import net.minecraft.src.ModLoader;
import net.minecraftforge.client.ForgeHooksClient;

import com.elvenwater.malkierian.Plasmacraft.common.CommonProxy;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.FMLTextureFX;

public class TextureFrameAnimFX extends FMLTextureFX
{
	protected int fileBuffer[];
	private int tick;
	private int numFrames;
	private int tileResolution;
	private String filePath;
	
	public TextureFrameAnimFX(int indexToReplace, String filePath)
	{
		super(indexToReplace);
		this.filePath = filePath;
		setup();
	}
	
	@Override
	public void setup()
	{
		super.setup();
		tileResolution = 16;
		tick = 0;
		try
		{
			TexturePackList tpl = (TexturePackList)ModLoader.getPrivateValue(RenderEngine.class, FMLClientHandler.instance().getClient().renderEngine, 10);
			InputStream s;
			
			s = tpl.getSelectedTexturePack().getResourceAsStream(filePath);
			if(s == null)
			{
				s = (net.minecraft.client.Minecraft.class).getResourceAsStream(filePath);
			}
			
			BufferedImage bufferedimage = ImageIO.read(s);
			
			fileBuffer = new int[bufferedimage.getWidth() * bufferedimage.getHeight()];
			numFrames = bufferedimage.getWidth() / bufferedimage.getHeight();
			tileResolution = bufferedimage.getHeight();
			bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), fileBuffer, 0, bufferedimage.getWidth());
			imageData = new byte[tileResolution * tileResolution * 4];
		}
		catch(IOException ioexception)
		{
			ioexception.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void onTick()
	{
		++tick;

		for (int var2 = 0; var2 < tileSizeSquare; ++var2)
		{
			int var3 = imageData[var2 * 4 + 0] & 255;
			int var4 = imageData[var2 * 4 + 1] & 255;
			int var5 = imageData[var2 * 4 + 2] & 255;
			int var6 = imageData[var2 * 4 + 3] & 255;

			if (this.anaglyphEnabled)
			{
				int var7 = (var3 * 30 + var4 * 59 + var5 * 11) / 100;
				int var8 = (var3 * 30 + var4 * 70) / 100;
				int var9 = (var3 * 30 + var5 * 70) / 100;
				var3 = var7;
				var4 = var8;
				var5 = var9;
			}

			this.imageData[var2 * 4 + 0] = (byte)var3;
			this.imageData[var2 * 4 + 1] = (byte)var4;
			this.imageData[var2 * 4 + 2] = (byte)var5;
			this.imageData[var2 * 4 + 3] = (byte)var6;
		}
	}

	@Override
	public void bindImage(RenderEngine renderengine)
	{
		ForgeHooksClient.bindTexture(CommonProxy.BLOCK_PNG, 0);
	}
}
