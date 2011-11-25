package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode


public class SMTileEntityCaustic extends TileEntity
{

    public SMTileEntityCaustic()
    {
		causticID = -1;
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setByte("causticID", causticID);
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        causticID = nbttagcompound.getByte("causticID");
        if(causticID < 0)
        {
            causticID = 0;
        }
        if(causticID > 7)
        {
            causticID = 7;
        }
    }

	public void setCausticID(int id)
	{
		causticID = (byte)id;
        if(causticID < 0)
        {
            causticID = 0;
        }
        if(causticID > 7)
        {
            causticID = 7;
        }
	}

    public byte causticID;
}
