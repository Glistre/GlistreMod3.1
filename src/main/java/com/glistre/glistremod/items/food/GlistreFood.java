package com.glistre.glistremod.items.food;

import net.minecraftforge.fml.relauncher.SideOnly;

import com.glistre.glistremod.GlistreMod;
import com.glistre.glistremod.init.ItemRegistry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.client.renderer.texture.IIconRegister;


public class GlistreFood extends ItemFood {
	public String textureName;

	// private String texturePath = ("GlistreMod.MODID" + ":" + "textures/items"
	// + textureName);
	

	public GlistreFood(int healamount, Float saturationModifier, boolean isWolfsFavoriteMeat) {
		super(healamount, saturationModifier, isWolfsFavoriteMeat);
		this.setUnlocalizedName(textureName);
//		this.setTextureName("GlistreFood_1");

		// this.setPotionEffect(Potion.damageBoost.id, 30*20, 1, 1.0F);
	}
	// *20 makes the 30 be 30 seconds

	@Override
	public void onFoodEaten(ItemStack itemstack, World world, EntityPlayer player) {
		if (!player.capabilities.isCreativeMode) {
			--itemstack.stackSize;
		}
		if (!world.isRemote) {
			player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 30 * 20, 0));
			//potion 8 is Jump Boost
			player.addPotionEffect(new PotionEffect(8, 30 * 20, 0));
			player.addPotionEffect(new PotionEffect(ItemRegistry.poison_protect_potion.id, 30 * 20, 0));
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "You just got Boosts and Poison Protection!"));
		}
		
	
		
	}

	/*
	 * @Override
	 * 
	 * @SideOnly(Side.CLIENT) public void registerIcons(IIconRegister
	 * iconRegister) { this.itemIcon = iconRegister.registerIcon(texturePath); }
	 */

}