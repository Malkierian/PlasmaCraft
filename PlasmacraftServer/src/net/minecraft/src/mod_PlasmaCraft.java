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
import net.minecraft.src.Plasmacraft.ItemAcidGrenade;
import net.minecraft.src.Plasmacraft.ItemVial;
import net.minecraft.src.Plasmacraft.ItemCausticBoat;
import net.minecraft.src.Plasmacraft.ItemCausticSupportBlock;
import net.minecraft.src.Plasmacraft.ItemEnergyWeapon;
import net.minecraft.src.Plasmacraft.ItemPlasma;
import net.minecraft.src.Plasmacraft.PCServerProxy;
import net.minecraft.src.Plasmacraft.PlasmaCraftCore;
import net.minecraft.src.Plasmacraft.TileEntityCaustic;
import net.minecraft.src.Plasmacraft.TileEntityPlasmaBench;
import net.minecraft.src.Plasmacraft.WorldGenCausticLakes;
import net.minecraft.src.Plasmacraft.WorldGenCaustics;
import net.minecraft.src.Plasmacraft.WorldGenNetherCaustics;
import net.minecraft.src.Plasmacraft.WorldGenNetherMinable;

public class mod_PlasmaCraft extends BaseModMp
{
	public int causticRenderID;
    public String Version()
    {
    	return PlasmaCraftCore.Version();
	}
    
    // PC == ChunkProviderGenerate
    // JX == ChunkProviderHell
	
    public static void sendFlak(double x, double y, double z)
	  {
	    float[] dataFloat = new float[3];
	    dataFloat[0] = (float)x;
	    dataFloat[1] = (float)y;
	    dataFloat[2] = (float)z;

	    Packet230ModLoader packet = new Packet230ModLoader();
	    packet.packetType = 1;
	    packet.dataFloat = dataFloat;
	    ModLoaderMp.SendPacketToAll(inst1, packet);
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
		PlasmaCraftCore.proxy = new PCServerProxy();
        causticRenderID = ModLoader.getUniqueBlockModelID(this, false);
        PlasmaCraftCore.init(causticRenderID);

		//ModLoader.RegisterEntityID(EntityCausticBoat.class, "RadioniteBoat", ModLoader.getUniqueEntityId());
		//ModLoader.RegisterEntityID(EntityAcidTNTPrimed.class, "AcidTNTPrimed", ModLoader.getUniqueEntityId());
        ModLoaderMp.RegisterEntityTrackerEntry(EntityLaser.class, 160);
        ModLoaderMp.RegisterEntityTrackerEntry(EntityLaserShotgun.class, 161);
        ModLoaderMp.RegisterEntityTrackerEntry(EntityPlasma.class, 162);
        ModLoaderMp.RegisterEntityTrackerEntry(EntityRailGun.class, 163);
        ModLoaderMp.RegisterEntityTrackerEntry(EntityAcid.class, 164);
        ModLoaderMp.RegisterEntityTrackerEntry(EntityAcidTNTPrimed.class, 165);
        ModLoaderMp.RegisterEntityTrackerEntry(EntityAcidGrenade.class, 166);
        ModLoaderMp.RegisterEntityTrackerEntry(EntityCryoBlast.class, 167);
        ModLoaderMp.RegisterEntityTrackerEntry(EntityCausticBoat.class, 168);
        ModLoaderMp.RegisterEntityTrackerEntry(EntityAcidTNTPrimed.class, 169);
        ModLoaderMp.RegisterEntityTrackerEntry(EntityMutantCow.class, 170);

        ModLoaderMp.RegisterEntityTracker(EntityLaser.class, 160, 5);
        ModLoaderMp.RegisterEntityTracker(EntityLaserShotgun.class, 160, 5);
        ModLoaderMp.RegisterEntityTracker(EntityPlasma.class, 160, 5);
        ModLoaderMp.RegisterEntityTracker(EntityRailGun.class, 160, 5);
        ModLoaderMp.RegisterEntityTracker(EntityAcid.class, 160, 5);
        ModLoaderMp.RegisterEntityTracker(EntityAcidTNTPrimed.class, 160, 5);
        ModLoaderMp.RegisterEntityTracker(EntityAcidGrenade.class, 160, 5);
        ModLoaderMp.RegisterEntityTracker(EntityCryoBlast.class, 160, 5);
        ModLoaderMp.RegisterEntityTracker(EntityCausticBoat.class, 160, 5);
        ModLoaderMp.RegisterEntityTracker(EntityAcidTNTPrimed.class, 160, 5);
        ModLoaderMp.RegisterEntityTracker(EntityMutantCow.class, 160, 5);

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

	public void GenerateNether(World world, Random random, int chunkX, int chunkZ)
	{

		PlasmaCraftCore.GenerateNether(world, random, chunkX, chunkZ);
	}

	public void GenerateSurface(World world, Random random, int chunkX, int chunkZ)
	{
		PlasmaCraftCore.GenerateSurface(world, random, chunkX, chunkZ);
	}
    
	static mod_PlasmaCraft inst1;

    
}