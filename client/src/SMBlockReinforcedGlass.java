


import java.util.Random;


public class SMBlockReinforcedGlass extends BlockBreakable
{

    public SMBlockReinforcedGlass(int i, int j, Material material, boolean flag)
    {
        super(i, j, material, flag);
        //if (blockID == mod_PlasmaCraft.frozenCryonite.blockID){
        //slipperiness = 3F;
        //}
        
    }
    
    public int idDropped(int i, Random random)
    {
        return blockID;
    }
    
    public int quantityDropped(Random random)
    {
        return 1;
    }
}
