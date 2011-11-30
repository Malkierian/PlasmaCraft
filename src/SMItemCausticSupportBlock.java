



public class SMItemCausticSupportBlock extends ItemBlock
{
    public SMItemCausticSupportBlock(int i)
    {
        super(i);
        blockID = i + 256;
    }

    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l)
    {
        if(world.getBlockId(i, j, k) == Block.snow.blockID)
        {
            l = 0;
        } else
        {
            if(l == 0)
            {
                j--;
            }
            if(l == 1)
            {
                j++;
            }
            if(l == 2)
            {
                k--;
            }
            if(l == 3)
            {
                k++;
            }
            if(l == 4)
            {
                i--;
            }
            if(l == 5)
            {
                i++;
            }
        }
        if(itemstack.stackSize == 0)
        {
            return false;
        }
		int blockBlockId = world.getBlockId(i, j, k);
		boolean isCaustic = false;
		if(Block.blocksList[blockBlockId] != null)
		{
			isCaustic = Block.blocksList[blockBlockId].getRenderType() == mod_PlasmaCraft.causticID;
		}
        if(world.canBlockBePlacedAt(blockID, i, j, k, false, 1) || isCaustic)
        {
            Block block = Block.blocksList[blockID];
            if(world.setBlockAndMetadataWithNotify(i, j, k, blockID, getPlacedBlockMetadata(itemstack.getItemDamage())))
            {
                Block.blocksList[blockID].onBlockPlaced(world, i, j, k, l);
                Block.blocksList[blockID].onBlockPlacedBy(world, i, j, k, entityplayer);
                world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, block.stepSound.stepSoundDir2(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                itemstack.stackSize--;
            }
        }
        return true;
    }

    public String getItemNameIS(ItemStack itemstack)
    {
        return Block.blocksList[blockID].getBlockName();
    }

    public String getItemName()
    {
        return Block.blocksList[blockID].getBlockName();
    }

    private int blockID;
}
