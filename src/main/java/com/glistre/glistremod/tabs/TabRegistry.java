package com.glistre.glistremod.tabs;

import java.util.List;

import com.glistre.glistremod.GlistreMod;
import com.glistre.glistremod.init.BlockRegistry;
import com.glistre.glistremod.init.GlistreEntityRegistry;
import com.glistre.glistremod.init.ItemRegistry;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabRegistry {
	
	public static void GlistreMod(){
		initializeTab();
		registerTab();
	}
	


	
	//DECLARE CREATIVE TABS
	public static GlistreTabs glistre_tab_1;		
	public static GlistreTabs tab_custom;
	public static GlistreTabs tab_builder;
   	public static GlistreTabs tab_food;    	
	public static GlistreTabs tab_potion;
	public static void initializeTab(){
		
		//LOAD THE CREATIVE TABS
		glistre_tab_1 = new GlistreTabs("glistre_tab_1"){

			public Item getTabIconItem(){
				return (ItemRegistry.glistre_sword);
			}
			
	/*		public String getTranslatedTabLabel() {
	            return "glistre_tab_1"; //name of the tab you want to display when hovering
	        }*/
	
			
			//puts in tab and displays in the order that you'd like
			public void displayAllReleventItems(List list) {
				
				list.add(new ItemStack(ItemRegistry.silver_sword_1));
				ItemStack enchant01 = new ItemStack(ItemRegistry.silver_sword_1);
				enchant01.addEnchantment(Enchantment.baneOfArthropods, 8);
				list.add(enchant01);
				
				list.add(new ItemStack(ItemRegistry.glistre_sword));
				ItemStack enchant = new ItemStack(ItemRegistry.glistre_sword);
				enchant.addEnchantment(Enchantment.sharpness, 5);
				list.add(enchant);
				
				list.add(new ItemStack(ItemRegistry.mighty_sword));
				list.add(new ItemStack(ItemRegistry.mighty_ice_sword));
				list.add(new ItemStack(GlistreEntityRegistry.tobie_worst_projectile_1));

				list.add(new ItemStack(ItemRegistry.custom_bow_1));
				ItemStack enchant04 = new ItemStack(ItemRegistry.custom_bow_1);
	    		enchant04.addEnchantment(Enchantment.infinity, 10);
	    		enchant04.addEnchantment(Enchantment.power, 7);
	    		list.add(enchant04);

				list.add(new ItemStack(ItemRegistry.blaster_gun_1));
				list.add(new ItemStack(ItemRegistry.ender_gun));

				list.add(new ItemStack(ItemRegistry.silver_helmet_1));
				list.add(new ItemStack(ItemRegistry.silver_chestplate_1));
				list.add(new ItemStack(ItemRegistry.silver_leggings_1));
				list.add(new ItemStack(ItemRegistry.silver_boots_1));
				ItemStack enchant0 = new ItemStack(ItemRegistry.silver_helmet_1);
				ItemStack enchant1 = new ItemStack(ItemRegistry.silver_chestplate_1);
				ItemStack enchant2 = new ItemStack(ItemRegistry.silver_leggings_1);
				ItemStack enchant3 = new ItemStack(ItemRegistry.silver_boots_1);
				enchant0.addEnchantment(Enchantment.protection, 4);
				enchant1.addEnchantment(Enchantment.blastProtection, 4);
				enchant2.addEnchantment(Enchantment.protection, 4);							
				enchant3.addEnchantment(Enchantment.protection, 4);
				enchant3.addEnchantment(Enchantment.featherFalling, 4);
	    		list.add(enchant0);
	    		list.add(enchant1);
	    		list.add(enchant2);
	    		list.add(enchant3);				
								
				list.add(new ItemStack(ItemRegistry.glistre_helmet_1));
				list.add(new ItemStack(ItemRegistry.glistre_chestplate_1));
				list.add(new ItemStack(ItemRegistry.glistre_leggings_1));
				list.add(new ItemStack(ItemRegistry.glistre_boots_1));
				ItemStack enchant4 = new ItemStack(ItemRegistry.glistre_helmet_1);
				ItemStack enchant5 = new ItemStack(ItemRegistry.glistre_chestplate_1);				
				ItemStack enchant6 = new ItemStack(ItemRegistry.glistre_leggings_1);				
				ItemStack enchant7 = new ItemStack(ItemRegistry.glistre_boots_1);				
				enchant4.addEnchantment(Enchantment.fireProtection, 4);
				enchant5.addEnchantment(Enchantment.fireProtection, 4);
				enchant6.addEnchantment(Enchantment.fireProtection, 4);
				enchant7.addEnchantment(Enchantment.fireProtection, 4);
	    		list.add(enchant4);
	    		list.add(enchant5);
	    		list.add(enchant6);
	    		list.add(enchant7);				

				list.add(new ItemStack(ItemRegistry.chain_helmet_1));
				list.add(new ItemStack(ItemRegistry.chain_chestplate_1));				
				list.add(new ItemStack(ItemRegistry.chain_leggings_1));			
				list.add(new ItemStack(ItemRegistry.chain_boots_1));	
				ItemStack enchant8 = new ItemStack(ItemRegistry.chain_helmet_1);
				ItemStack enchant9 = new ItemStack(ItemRegistry.chain_chestplate_1);
				ItemStack enchant10 = new ItemStack(ItemRegistry.chain_leggings_1);
				ItemStack enchant11 = new ItemStack(ItemRegistry.chain_boots_1);
				enchant8 = new ItemStack(ItemRegistry.chain_helmet_1);
				enchant9 = new ItemStack(ItemRegistry.chain_chestplate_1);
				enchant10 = new ItemStack(ItemRegistry.chain_leggings_1);
				enchant11 = new ItemStack(ItemRegistry.chain_boots_1);
	    		enchant8.addEnchantment(Enchantment.projectileProtection, 5);
	    		enchant9.addEnchantment(Enchantment.projectileProtection, 5);
	    		enchant10.addEnchantment(Enchantment.projectileProtection, 5);
	    		enchant11.addEnchantment(Enchantment.projectileProtection, 5);
	    		list.add(enchant8);
	    		list.add(enchant9);
	    		list.add(enchant10);
	    		list.add(enchant11);
				
			}

	};

	tab_custom = new GlistreTabs("tab_custom") {

			public Item getTabIconItem() {

				return (ItemRegistry.silver_pickaxe_1);
			}
			
			public void displayAllReleventItems(List list) {
				list.add(new ItemStack(ItemRegistry.silver_pickaxe_1));
				ItemStack enchant02 = new ItemStack(ItemRegistry.silver_pickaxe_1);
	    		enchant02.addEnchantment(Enchantment.fortune, 4);
	    		list.add(enchant02);
	    		
				list.add(new ItemStack(ItemRegistry.glistre_pickaxe));
		    	ItemStack enchant03 = new ItemStack(ItemRegistry.glistre_pickaxe);
		    	enchant03.addEnchantment(Enchantment.efficiency, 3);
		    	enchant03.addEnchantment(Enchantment.unbreaking, 3);
		    	list.add(enchant03);
		    	
				list.add(new ItemStack(ItemRegistry.glistre_pickaxe_2));
				ItemStack enchant04 = new ItemStack(ItemRegistry.glistre_pickaxe_2);
		    	enchant04.addEnchantment(Enchantment.efficiency, 4);
		    	list.add(enchant04);
		    		
				list.add(new ItemStack(ItemRegistry.glistre_burner));
				list.add(new ItemStack(ItemRegistry.toby_king_burner));

				list.add(new ItemStack(ItemRegistry.wolf_howl));
				list.add(new ItemStack(ItemRegistry.wolves_howling));
				list.add(new ItemStack(ItemRegistry.sasquatch));
				list.add(new ItemStack(ItemRegistry.silver_ingot_1));
				list.add(new ItemStack(ItemRegistry.glistre_ingot));
				list.add(new ItemStack(ItemRegistry.glistre_dust));
				
				
			}
			
	};

	tab_food = new GlistreTabs("tab_food") {

		public Item getTabIconItem() {

			return (ItemRegistry.glistre_food_1);
			
		}
		public void displayAllReleventItems(List list) {
		list.add(new ItemStack(ItemRegistry.glistre_food_1));
		list.add(new ItemStack(ItemRegistry.glistre_food_2));	
		list.add(new ItemStack(ItemRegistry.nori));	
		list.add(new ItemStack(ItemRegistry.sushi));	
		}	
	};

	tab_builder = new GlistreTabs("tab_builder") {

		public Item getTabIconItem() {

			return new ItemStack(BlockRegistry.silver_ore_1).getItem();
		}
		
		public void displayAllReleventItems(List list) {
		
		list.add(new ItemStack(BlockRegistry.silver_ore_1));
		list.add(new ItemStack(BlockRegistry.silver_ore_2));
		list.add(new ItemStack(BlockRegistry.silver_ore_3));
		list.add(new ItemStack(BlockRegistry.silver_ore_4));
		list.add(new ItemStack(BlockRegistry.silver_block_1));
		list.add(new ItemStack(BlockRegistry.silver_block_2));
		list.add(new ItemStack(BlockRegistry.silver_block_3));
		list.add(new ItemStack(BlockRegistry.silver_block_4));
		list.add(new ItemStack(BlockRegistry.glistre_block_1));	
		list.add(new ItemStack(BlockRegistry.enchanted_block_1));
		list.add(new ItemStack(BlockRegistry.void_block_1));		
		list.add(new ItemStack(BlockRegistry.light_fire));	
		list.add(new ItemStack(BlockRegistry.light_portal));
		list.add(new ItemStack(BlockRegistry.light_toby_king_fire));	
		list.add(new ItemStack(BlockRegistry.light_toby_king_portal));				
		list.add(new ItemStack(BlockRegistry.liquid_ice));
		list.add(new ItemStack(BlockRegistry.block_seaweed));
		
		}

	};

	tab_potion = new GlistreTabs("tab_potion"){
		
			public Item getTabIconItem() {
				return (ItemRegistry.poison_protection);
			}
			@Override
			//puts in tab and displays in the order that you'd like
			public void displayAllReleventItems(List list) {
				
				list.add(new ItemStack(ItemRegistry.poison_protection));				
				list.add(new ItemStack(ItemRegistry.nausea_protection));
				list.add(new ItemStack(ItemRegistry.vomitus));


		//		list.add(new ItemStack(ItemRegistry.sceptre_1));
		//		list.add(new ItemStack(ItemRegistry.sceptre_egg));
		//		list.add(new ItemStack(ItemRegistry.tobie_queen_egg));
		//		list.add(new ItemStack(ItemRegistry.tobie_king_egg));
				
				
				list.add(new ItemStack(GlistreEntityRegistry.splash_poison_protection));
				
				list.add(new ItemStack(GlistreEntityRegistry.blaster_bolt_1));
				list.add(new ItemStack(GlistreEntityRegistry.ender_bolt_1));
				list.add(new ItemStack(GlistreEntityRegistry.sceptre_bolt_1));

		}
	};
	
	}

	public static void registerTab(){
		
	}
	
	public static void registerRenders(){
		
	}
}
