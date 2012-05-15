package net.minecraft.src;

import java.io.File;
import java.net.URISyntaxException;
import java.util.*;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.Plasmacraft.BlockAcidHot;
import net.minecraft.src.Plasmacraft.BlockAcidTNT;
import net.minecraft.src.Plasmacraft.BlockCausticFlowing;
import net.minecraft.src.Plasmacraft.BlockCausticStationary;
import net.minecraft.src.Plasmacraft.BlockGlowCloth;
import net.minecraft.src.Plasmacraft.BlockPlasmaOre;
import net.minecraft.src.Plasmacraft.BlockPlasmificator;
import net.minecraft.src.Plasmacraft.BlockReinforcedGlass;
import net.minecraft.src.Plasmacraft.ContainerPlasmaBench;
import net.minecraft.src.Plasmacraft.EntityAcid;
import net.minecraft.src.Plasmacraft.EntityAcidGrenade;
import net.minecraft.src.Plasmacraft.EntityAcidTNTPrimed;
import net.minecraft.src.Plasmacraft.EntityCausticBoat;
import net.minecraft.src.Plasmacraft.EntityCryoBlast;
import net.minecraft.src.Plasmacraft.EntityLaser;
import net.minecraft.src.Plasmacraft.EntityLaserShotgun;
import net.minecraft.src.Plasmacraft.EntityMutantCow;
import net.minecraft.src.Plasmacraft.EntityPlasma;
import net.minecraft.src.Plasmacraft.EntityRailGun;
import net.minecraft.src.Plasmacraft.GuiIds;
import net.minecraft.src.Plasmacraft.ItemAcidGrenade;
import net.minecraft.src.Plasmacraft.ItemVial;
import net.minecraft.src.Plasmacraft.ItemCausticBoat;
import net.minecraft.src.Plasmacraft.ItemCausticSupportBlock;
import net.minecraft.src.Plasmacraft.ItemEnergyWeapon;
import net.minecraft.src.Plasmacraft.ItemPlasma;
import net.minecraft.src.Plasmacraft.PCServerProxy;
import net.minecraft.src.Plasmacraft.PlasmaCraftCore;
import net.minecraft.src.Plasmacraft.TilePlasmaBench;
import net.minecraft.src.Plasmacraft.WorldGenCausticLakes;
import net.minecraft.src.Plasmacraft.WorldGenCaustics;
import net.minecraft.src.Plasmacraft.WorldGenNetherCaustics;
import net.minecraft.src.Plasmacraft.WorldGenNetherMinable;
import net.minecraft.src.forge.IGuiHandler;
import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.NetworkMod;

public class mod_PlasmaCraft extends NetworkMod
{
    public static mod_PlasmaCraft instance;
	public int causticRenderID;
	
    public String getVersion()
    {
    	return PlasmaCraftCore.Version();
	}
	
    public static void sendFlak(double x, double y, double z)
	  {
	    float[] dataFloat = new float[3];
	    dataFloat[0] = (float)x;
	    dataFloat[1] = (float)y;
	    dataFloat[2] = (float)z;

//	    Packet230ModLoader packet = new Packet230ModLoader();
//	    packet.packetType = 1;
//	    packet.dataFloat = dataFloat;
//	    ModLoaderMp.sendPacketToAll(inst1, packet);
	  }
	 
	public static int floatColorsToDamage(float r, float g, float b)
	{
		int r32 = (int)(r * 255.0f);
		int g32 = (int)(g * 255.0f);
		int b32 = (int)(b * 255.0f);
		if(r32 < 0) r32 = 0;
		if(g32 < 0) g32 = 0;
		if(b32 < 0) b32 = 0;
		if(r32 > 255) r32 = 255;
		if(g32 > 255) g32 = 255;
		if(b32 > 255) b32 = 255;
		return (r32 << 16) | (g32 << 8) | b32;
	}

	
	public mod_PlasmaCraft()
	{
		instance = this;
	}

    @Override
    public void generateNether(World world, Random random, int i, int j)
    {
    	PlasmaCraftCore.generateNether(world, random, i, j);
    }

    @Override
    public void generateSurface(World world, Random random, int i, int j)
    {
    	PlasmaCraftCore.generateSurface(world, random, i, j);
    }
	
	@Override
	public void load()
	{
		PlasmaCraftCore.proxy = new PCServerProxy();
        causticRenderID = 206;
        PlasmaCraftCore.init(causticRenderID, this);

		// HACK around the fact that we can't edit the Item class directly
        for(int i = 0; i < 256; i++)
        {
            if(Block.blocksList[i] != null && Item.itemsList[i] != null && Item.itemsList[i] instanceof ItemBlock &&
            	!Item.itemsList[i].getHasSubtypes())
            {
                Item.itemsList[i] = new ItemCausticSupportBlock(i - 256);
            }
        }
	}

    @Override
    public int addFuel(int i, int j)
    {
        if(i == PlasmaCraftCore.netherflowVial.shiftedIndex)
        {
            return 0x7a120;
        }
        return i != PlasmaCraftCore.ThermoPellet.shiftedIndex ? 0 : 0xf4240;
    }

	@Override
	public boolean clientSideRequired()
	{
		return true;
	}

	@Override
	public boolean serverSideRequired()
	{
		return false;
	}

    
}