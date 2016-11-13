package com.glistre.glistremod.armor;

import java.util.List;

import com.glistre.glistremod.GlistreMod;
import com.glistre.glistremod.init.ItemRegistry;
import com.glistre.glistremod.reference.Reference;

//import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ChainArmor extends ItemArmor {
   
	public ChainArmor (String unlocalizedName, ArmorMaterial material, int renderIndex, int armorType) {
		super(material, renderIndex, armorType);
		this.setCreativeTab(CreativeTabs.tabCombat); 


	}

		    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
	    	
			if (stack.getItem() == ItemRegistry.chain_helmet_1 || stack.getItem() == ItemRegistry.chain_chestplate_1 || stack.getItem() == ItemRegistry.chain_boots_1){
	  
	    		return Reference.MOD_ID + ":" + "textures/armor/chainarmor_layer_1.png";
	  
	    	}
	    	if (stack.getItem() == ItemRegistry.chain_leggings_1) {
	 
	    		return Reference.MOD_ID + ":" + "textures/armor/chainarmor_layer_2.png";

	    	}

	    	else return null;
	    }
		    
		    @Override
		    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {

		        if (itemStack.getItem() == ItemRegistry.chain_chestplate_1) {
		            effectPlayer(player, Potion.digSpeed, 0);
		        }
		        if (itemStack.getItem() == ItemRegistry.chain_leggings_1) {
		            effectPlayer(player, Potion.moveSpeed, 0);
		        }
		        if (itemStack.getItem() == ItemRegistry.chain_boots_1) {
		            effectPlayer(player, Potion.jump, 1);
		        }
		    } 

		    private void effectPlayer(EntityPlayer player, Potion potion, int amplifier) {
		        //Always effect for 8 seconds, then refresh//set amplifier to -1 gets rid of particle swirls
		        if (player.getActivePotionEffect(potion) == null || player.getActivePotionEffect(potion).getDuration() <= 1)
		            player.addPotionEffect(new PotionEffect(potion.id, 500, -1, true, true));	    
		    
		    }

		    //the getSubItems does not seem to work had to add to diplayAllRelevantItems in TabRegistry
@SideOnly(Side.CLIENT)
@Override
    public void getSubItems(Item item, CreativeTabs tab, List list){

    		ItemStack enchant4 = new ItemStack(this);
    		enchant4.addEnchantment(Enchantment.projectileProtection, 5);
    		list.add(enchant4);

    		    		
	}
	    		
}