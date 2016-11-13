package com.glistre.glistremod.effects.potions.splash;

import javax.swing.Icon;

import com.glistre.glistremod.GlistreMod;
import com.glistre.glistremod.effects.potions.PoisonProtectEffect;
import com.glistre.glistremod.init.ItemRegistry;
import com.glistre.glistremod.projectiles.tobyworstsword.TobyEntityProjectile;
import com.glistre.glistremod.tabs.TabRegistry;

import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
//import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSplashPotion extends Item {

	private int color;
	private PotionEffect[] effects;

	
	public ItemSplashPotion(int maxStackSize, String name, PotionEffect[] effects, int color) {
		this.setMaxStackSize(maxStackSize);
//		this.setUnlocalizedName(name);
		this.effects = effects;
		this.color=color;
	}


	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}
    /**
     * returns whether or not a potion is a throwable splash potion based on damage value
     */
    public static boolean isSplash(int value)
    {
        return (value & 16384) != 0;
    }
	
	@Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ){
        return false;
    }
	
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */

/*   public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
   {
	   if (isSplash(itemStack.getMetadata()))
       {
           if (!player.capabilities.isCreativeMode)
           {
               --itemStack.stackSize;
           }

           world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

           if (!world.isRemote)
           {
           for (int i = 0; i < effects.length; i++) {
				if (effects[i] != null && effects[i].getPotionID() > 0 && !world.isRemote) {
               world.spawnEntityInWorld(new EntitySplashProjectile(world, player, itemStack));
 //      		   player.addPotionEffect(new PotionEffect(GlistreMod.poisonProtectPotion.id, 3000, 0, false));
               player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
           }
           }
           }     
           
       }
	   return itemStack;       
   }	*/
   @Override
   public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
   {

           if (!player.capabilities.isCreativeMode)
           {
               --itemStack.stackSize;
           }

           world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

           if (!world.isRemote)
           {
               world.spawnEntityInWorld(new EntitySplashProjectile(world, player, color));
           }

	   return itemStack;       
   }


    /**
     * Returns true if the potion has an instant effect instead of a continuous one (eg Harming)
     */
    public boolean isInstant()
    {
        return true;
    }



	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemStack) {
		
		return true;
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int pass) {
		if (pass == 0) {
			return this.color;
		} else {
			return 99999999;
		}
	}
}



