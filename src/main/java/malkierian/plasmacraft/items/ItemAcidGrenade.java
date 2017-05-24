package malkierian.plasmacraft.items;

import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAcidGrenade extends ItemPlasma
{
	public ItemAcidGrenade(String name)
	{
		super(name);
		maxStackSize = 16;
	}

//	@Override
//	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
//	{
//		world.plays.playSoundAtEntity(entityplayer, SoundRegistry.class.get"random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
//		if(!world.isRemote)
//		{
//			world.spawnEntityInWorld(new EntityAcidGrenade(world, entityplayer));
//		}
//		itemstack.stackSize--;
//		return itemstack;
//	}
}
