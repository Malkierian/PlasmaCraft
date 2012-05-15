package net.minecraft.src.Plasmacraft;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.Potion;
import net.minecraft.src.PotionEffect;
import net.minecraft.src.Vec3D;
import net.minecraft.src.World;

public interface IPCProxy
{
	public String getMinecraftDirectory();

	public boolean isClient (World world);
	
	public boolean getEntityInstanceOf(Entity entity);

	public int getLightBrightnessForSkyBlocks(IBlockAccess iblockaccess, int i,
			int j, int k, int l);

	public float getLightBrightess(IBlockAccess iblockaccess, int i, int j,
			int k);

	public ItemStack armorItemInSlot(EntityPlayer entityplayer, int i);

	public void playSoundEffect(World world, int i, int j, int k, Block block);

	public double getAverageEdgeLength(AxisAlignedBB boundingBox);
	
}