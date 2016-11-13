package com.glistre.glistremod.projectiles.blaster;

import java.util.List;

import com.glistre.glistremod.GlistreMod;
import com.glistre.glistremod.init.ItemRegistry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

import com.glistre.glistremod.projectiles.blaster.*;
import com.glistre.glistremod.reference.Reference;

public class Blaster extends Item{

//	private String texturePath = "com.glistre.glistremod:";
	public final String textureName;
	
	   public Blaster(int par1, String textureName) {
	       super();
	       this.textureName = textureName;
	       this.setUnlocalizedName(textureName);
	       this.setFull3D();
	       
	       
	   }
	    /**
	     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	     */
	   public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int usesRemaining)
	    {
	        int j = this.getMaxItemUseDuration(itemStack) - usesRemaining;

	        ArrowLooseEvent event = new ArrowLooseEvent(player, itemStack, j);
	        MinecraftForge.EVENT_BUS.post(event);
	        if (event.isCanceled())
	        {
	            return;
	        }
	        j = event.charge;

	        boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, itemStack) > 0;

	        if (flag || player.inventory.hasItem(ItemRegistry.glistre_dust))
	        {
// change normal 20.0F to 1.0F to make instant charge 5.0F = 5 ticks or 1/4 second	        	
	            float f = (float)j / 1.0F;
	            f = (f * f + f * 2.0F) / 3.0F;

	            if ((double)f < 0.1D)
	            {
	                return;
	            }

	            if (f > 1.0F)
	            {
	                f = 1.0F;
	            }

	            EntityBlasterBolt par1EntityBlasterBolt = new EntityBlasterBolt(world, player, f * 2.0F);

	            if (f == 1.0F)
	            {
	                par1EntityBlasterBolt.setIsCritical(true);
	            }

	            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, itemStack);

	            if (k > 0)
	            {
	                par1EntityBlasterBolt.setDamage(par1EntityBlasterBolt.getDamage() + (double)k * 0.5D + 0.5D);
	            }

	            int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, itemStack);

	            if (l > 0)
	            {
	                par1EntityBlasterBolt.setKnockbackStrength(l);
	            }

	            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, itemStack) > 0)
	            {
	                par1EntityBlasterBolt.setFire(100);
	            }

	            itemStack.damageItem(1, player);
	            //this is the sound releasing from blaster but first line of same sound in EntityBlasterBolt will not sound without it, 
	            //the first float is volume 1.0F is usual depends on sound file
	            world.playSoundAtEntity(player, "glistremod:laser_blaster", 1.0F, 2.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

	            if (flag)
	            {
	                par1EntityBlasterBolt.canBePickedUp = 2;
	            }
	            else
	            {
	                player.inventory.consumeInventoryItem(ItemRegistry.glistre_dust);
	            }

/*	            if (!p_77615_2_.isRemote)
	            {
	                p_77615_2_.spawnEntityInWorld(par1EntityBlasterBolt);
	            }*/
//took out the if ...isRemote to get blasterBolt to render
	            //   if (!p_77615_2_.isRemote)
	          //  {
	            	world.spawnEntityInWorld(par1EntityBlasterBolt);
	         //   }
	        }
	    }

	    /**
	     * returns the action that specifies what animation to play when the items is being used
	     */
	    public EnumAction getItemUseAction(ItemStack p_77661_1_)
	    {
	        return EnumAction.BOW;
	    }
	   
	    /**
	     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer, if you're in Survivor mode you 
	     * have to have an arrow in inventory
	     */
	    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	    {
	    	ArrowNockEvent event = new ArrowNockEvent(player, itemStack);
	        MinecraftForge.EVENT_BUS.post(event);
	        if (event.isCanceled())
	        {
	            return event.result;
	        }

	        if (player.capabilities.isCreativeMode || player.inventory.hasItem(ItemRegistry.glistre_dust))
	        {
	            player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
	        }
	        
	        //adds sound effect on ArrowKnock
	        
	        world.playSoundAtEntity(player, "glistremod:epm_flash", 1.0F, 2.0F);

	        return itemStack;
	        
	    }
	
	        
	    public void onUsingItemTick(ItemStack stack, EntityPlayer player, int count)
	    {
	       this.setDamage(stack, 99999 - count);
	    }	       
	    /**
	     * How long it takes to use or consume an item
	     */
	    public int getMaxItemUseDuration(ItemStack par1ItemStack)
	    {
	        return 72000;
	    }

	    //adds appearance of enchantment
	    @SideOnly(Side.CLIENT)
	    public boolean hasEffect(ItemStack par1ItemStack)
	    {
		    return true;
	    } 


/*	   @Override   
	   @SideOnly(Side.CLIENT)

	       public void registerIcons(IIconRegister iconRegister)
	       {
		   this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + textureName);
//		  this.itemIcon = iconRegister.registerIcon(texturePath);
	       }	 */  

	} 


