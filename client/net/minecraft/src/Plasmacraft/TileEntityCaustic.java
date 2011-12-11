package net.minecraft.src.Plasmacraft;

import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;

public class TileEntityCaustic extends TileEntity
{
    public byte causticID;

    public TileEntityCaustic()
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

    public void setCausticID(int i)
    {
        causticID = (byte)i;
        if(causticID < 0)
        {
            causticID = 0;
        }
        if(causticID > 7)
        {
            causticID = 7;
        }
    }
}
