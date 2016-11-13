package com.glistre.glistremod.items;

import com.glistre.glistremod.GlistreMod;
import com.glistre.glistremod.init.GlistreEntityRegistry;
import com.glistre.glistremod.init.ItemRegistry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
//import net.minecraft.client.renderer.texture.IIconRegister;
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

public class Sceptre extends Item{
	//private String texturePath = (Reference.MOD_ID + ":" + "sceptre_1");
	   public Sceptre(int par1) {
	       super();
	       this.setFull3D();
	       
	   }
	    /**
	     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	     */
	   public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_)
	    {
	        int j = this.getMaxItemUseDuration(p_77615_1_) - p_77615_4_;

	        ArrowLooseEvent event = new ArrowLooseEvent(p_77615_3_, p_77615_1_, j);
	        MinecraftForge.EVENT_BUS.post(event);
	        if (event.isCanceled())
	        {
	            return;
	        }
	        j = event.charge;

	        boolean flag = p_77615_3_.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, p_77615_1_) > 0;

	        if (flag || p_77615_3_.inventory.hasItem(ItemRegistry.glistre_dust))
	        {
// change normal j / 20.0F to j/ 1.0F to make instant charge 5.0F = 5 ticks or 1/4 second	        	

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

	            EntitySceptreBolt par1EntitySceptreBolt = new EntitySceptreBolt(p_77615_2_, p_77615_3_, f * 2.0F);

	            if (f == 1.0F)
	            {
	                par1EntitySceptreBolt.setIsCritical(true);
	            }

	            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, p_77615_1_);

	            if (k > 0)
	            {
	                par1EntitySceptreBolt.setDamage(par1EntitySceptreBolt.getDamage() + (double)k * 0.5D + 0.5D);
	            }

	            int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, p_77615_1_);

	            if (l > 0)
	            {
	                par1EntitySceptreBolt.setKnockbackStrength(l);
	            }

	            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, p_77615_1_) > 0)
	            {
	                par1EntitySceptreBolt.setFire(100);
	            }

	            p_77615_1_.damageItem(1, p_77615_3_);
	            //this is the sound releasing from blaster but first line of same sound in EntitySceptreBolt will not sound without it, 
	            //the first float is volume 1.0F is usual depends on sound file
	            p_77615_2_.playSoundAtEntity(p_77615_3_, "glistremod:enderblaster", 0.5F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

	            if (flag)
	            {
	                par1EntitySceptreBolt.canBePickedUp = 2;
	            }
	            else
	            {
	                p_77615_3_.inventory.consumeInventoryItem(GlistreEntityRegistry.blaster_bolt_1);
	            }
	          //took out the if ...isRemote to get blasterBolt to render in survivor
//	            if (!p_77615_2_.isRemote)
//	            {
	                p_77615_2_.spawnEntityInWorld(par1EntitySceptreBolt);
//	            }
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
	    public ItemStack onItemRightClick(ItemStack p_77659_1_, World world, EntityPlayer player)
	    {
	    	ArrowNockEvent event = new ArrowNockEvent(player, p_77659_1_);
	        MinecraftForge.EVENT_BUS.post(event);
	        if (event.isCanceled())
	        {
	            return event.result;
	        }

	        if (player.capabilities.isCreativeMode || player.inventory.hasItem(ItemRegistry.glistre_dust))
	        {
	            player.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
	        }
	        
	        //adds sound effect on ArrowKnock
	        
	        world.playSoundAtEntity(player, "glistremod:sceptre_1", 1.0F, 2.0F);

	        return p_77659_1_;
	        
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
	           this.itemIcon = iconRegister.registerIcon(texturePath);
	       }	*/   

	} 


