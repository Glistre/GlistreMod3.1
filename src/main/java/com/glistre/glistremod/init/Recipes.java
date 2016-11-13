package com.glistre.glistremod.init;

import com.glistre.glistremod.GlistreMod;
import com.glistre.glistremod.lib.ConfigurationGlistre;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class Recipes {
	
	public static void initShapedRecipes(){
		if(!ConfigurationGlistre.secretRecipe){

	    //  SILVER SWORD RECIPE  
   	        GameRegistry.addRecipe(new ItemStack(ItemRegistry.silver_sword_1, 1), new Object[]{" X "," X "," S ", 'S', Items.stick,'X', ItemRegistry.silver_ingot_1,});

		}else{
			
	    //  SILVER SWORD cheap RECIPE  
   	        GameRegistry.addRecipe(new ItemStack(ItemRegistry.silver_sword_1, 1), new Object[]{" X "," X "," S ", 'S', Items.stick,'X', BlockRegistry.silver_ore_1,});
		}	
	   	 /**	
	   	  * RECIPES SECTION 
	   	  * *********************************************************** */
	   	    
	//ITEM RECIPES
	   	    
	   	//SECRET RECIPES		      
		//  Glistre Sword secret recipe
	    	ItemStack enchant = new ItemStack(ItemRegistry.glistre_sword);
	    	enchant.addEnchantment(Enchantment.fireAspect, 7);
	    	GameRegistry.addShapelessRecipe(enchant, Items.bone, Items.golden_apple);
	    //  Helmet of Glistre secret recipe
	    	ItemStack enchant0 = new ItemStack(ItemRegistry.glistre_helmet_1);
	    	enchant0.addEnchantment(Enchantment.blastProtection, 9);
	   	   	GameRegistry.addShapelessRecipe(enchant0, ItemRegistry.glistre_ingot, Items.golden_apple);
		//  Silver Sword secret recipe
	    	ItemStack enchant01 = new ItemStack(ItemRegistry.silver_sword_1);
	    	enchant01.addEnchantment(Enchantment.fireAspect, 7);
	    	GameRegistry.addShapelessRecipe(enchant01, Items.bone, Items.string);	   	   	
	    //  Silver Pickaxe secret recipe
	    	ItemStack enchant02 = new ItemStack(ItemRegistry.silver_pickaxe_1);
	    	enchant02.addEnchantment(Enchantment.fortune, 11);
	    	GameRegistry.addShapelessRecipe(enchant02, Items.bone, Items.quartz);
	    //	Glistre Pickaxe secret recipe
	    	ItemStack enchant03 = new ItemStack(ItemRegistry.glistre_pickaxe);
	    	enchant03.addEnchantment(Enchantment.silkTouch, 6);
	    	GameRegistry.addShapelessRecipe(enchant03, Items.bone, Items.feather);
	    //  Skeleton Buster bow secret recipe   	    
	   	    ItemStack enchant04 = new ItemStack(ItemRegistry.custom_bow_1);
	   		enchant04.addEnchantment(Enchantment.infinity, 10);
	   		GameRegistry.addShapelessRecipe(enchant04, Items.string, ItemRegistry.glistre_dust);
	   		enchant04.addEnchantment(Enchantment.power, 7);
	   		
	// STANDARD RECIPES

	/*//  SILVER SWORD RECIPE  
		    GameRegistry.addRecipe(new ItemStack(ItemRegistry.SilverSword_1, 1), new Object[]{" X "," X "," S ", 'S', Items.stick,'X', ItemRegistry.SilverIngot_1,});
	*/
	//  GLISTERING PIE RECIPE  
		         ItemStack glistrepie = new ItemStack(ItemRegistry.glistre_food_2);        
		         GameRegistry.addShapelessRecipe(glistrepie, ItemRegistry.glistre_food_1, Items.sugar);
	   	        
	//  SEAWEED SHEET/ NORI
	   	         GameRegistry.addRecipe(new ItemStack(ItemRegistry.nori, 3), new Object[]
	   	        		 {
	   	        			"XXX",
	   	        			"XXX",
	   	        			"XXX",
	   	        		'X', BlockRegistry.block_seaweed,
	   	        		 });       
	//   SUSHI
	   	         ItemStack sushi = new ItemStack(ItemRegistry.sushi);        
	   	         GameRegistry.addShapelessRecipe(sushi, ItemRegistry.nori, Items.fish);
	   	        		    	             

	//  SWORD RECIPE  
	   	        GameRegistry.addRecipe(new ItemStack(ItemRegistry.glistre_sword, 1), new Object[]
	   	     	        {
	   	     	                " X ",
	   	     	                " X ",
	   	     	                " S ",
	   	     	            'S', Items.stick,
	   	     	            'X', ItemRegistry.glistre_ingot,
	   	     	        });
	   	        
	//  PICKAXES RECIPE  
	   	        GameRegistry.addRecipe(new ItemStack(ItemRegistry.silver_pickaxe_1, 1), new Object[]
	   	     	        {
	   	     	                "XXX",
	   	     	                " X ",
	   	     	                " S ",
	   	     	            'S', Items.stick,
	   	     	            'X', ItemRegistry.silver_ingot_1,
	   	     	        });
	//Glistre's Pickaxe
	   	     	GameRegistry.addRecipe(new ItemStack(ItemRegistry.glistre_pickaxe, 1), new Object[]
	   	     	        {
	   	     	                "XXX",
	   	     	                " X ",
	   	     	                " S ",
	   	     	            'S', Items.stick,
	   	     	            'X', ItemRegistry.glistre_ingot,
	   	     	        });
	//Sparks Pickaxe    	     	        
	   	     	GameRegistry.addRecipe(new ItemStack(ItemRegistry.glistre_pickaxe_2, 1), new Object[]
	   	     	        {
	   	     	                "XXX",
	   	     	                " X ",
	   	     	                " S ",
	   	     	            'S', Items.blaze_rod,
	   	     	            'X', ItemRegistry.glistre_ingot,
	   	     	        });
	   	     	
	//  SCEPTRE TOBIE GUARDIAN SPAWNER
	   	     	
	   	   	    GameRegistry.addRecipe(new ItemStack(ItemRegistry.sceptre_egg, 6), new Object[] {
	   	   	    		" X ",
	   	   	    		"YYY",
	   	   	    		" Z ",
	   	   	    		'X', ItemRegistry.glistre_ingot,
	   	   	    		'Y', Items.egg,
	   	   	    		'Z', Items.emerald,   		
	   	   	    		});

	// SMELTING RECIPE
	   	     	GameRegistry.addSmelting(BlockRegistry.silver_ore_1, (new ItemStack(ItemRegistry.silver_ingot_1, 1)), 12);
	   	     	GameRegistry.addSmelting(ItemRegistry.mighty_sword, (new ItemStack(ItemRegistry.mighty_ice_sword, 1)), 1000);

	//  GLISTRE BURNER RECIPE
	   	     	ItemStack glistreburner = new ItemStack(ItemRegistry.glistre_burner);
	   	     	GameRegistry.addShapelessRecipe(glistreburner, ItemRegistry.silver_ingot_1, Items.flint_and_steel);

	   	     	ItemStack blueburner = new ItemStack(ItemRegistry.toby_king_burner);
	   	     	GameRegistry.addShapelessRecipe(blueburner, ItemRegistry.glistre_ingot, Items.flint_and_steel);
	   	 //	BLOCK RECIPES
	   	     	GameRegistry.addRecipe(new ItemStack(BlockRegistry.silver_block_1, 1), new Object[]
	   	                {
	   	                        "XXX",
	   	                        "XXX",
	   	                        "XXX",
	   	                    'X', ItemRegistry.silver_ingot_1,
	   	                });
	   	                
	   	     	GameRegistry.addRecipe(new ItemStack(BlockRegistry.glistre_block_1, 1), new Object[]
	   	                {
	   	                        "XXX",
	   	                        "XXX",
	   	                        "XXX",
	   	                    'X', ItemRegistry.glistre_ingot,
	   	                });
	   	                
	   	     	GameRegistry.addRecipe(new ItemStack(BlockRegistry.enchanted_block_1, 1), new Object[]
	   	                {
	   	                        "XXX",
	   	                        "XSX",
	   	                        "XXX",
	   	                    'X', ItemRegistry.glistre_ingot,
	   	                    'S', Items.ender_pearl,
	   	                });
	   	        
//	   		ENCHANTED SILVER ARMOR RECIPES  
	   	     	GameRegistry.addRecipe(new ItemStack(ItemRegistry.silver_helmet_1, 1), new Object[]
	   	     			{
	   	     					"XXX",
	   	     					"X X",
	   	     				'X', ItemRegistry.silver_ingot_1,
	   	     			});         
	   	     
	   	     	GameRegistry.addRecipe(new ItemStack(ItemRegistry.silver_chestplate_1, 1), new Object[]
	   	     			{
	   	     					"X X",
	   	     					"XXX",
	   	     					"XXX",
	   	     				'X', ItemRegistry.silver_ingot_1,
	   	     			});        
	   	    
	   	       GameRegistry.addRecipe(new ItemStack(ItemRegistry.silver_leggings_1, 1), new Object[]
	   	    		   {
	   	    				   "XXX",
	   	    				   "X X",
	   	    				   "X X",
	   	    				'X', ItemRegistry.silver_ingot_1,
	   	    		   });         
	   	       
	   	       GameRegistry.addRecipe(new ItemStack(ItemRegistry.silver_boots_1, 1), new Object[]
	   	    		   {
	   	    				   "X X",
	   	    				   "X X",
	   	    				'X', ItemRegistry.silver_ingot_1,
	   	    		   });
	  	    
//			GameRegistry.addRecipe(new ItemStack(SilverSword_1, 1), new Object[]{ " X "," X "," S ", 'S', Items.blaze_rod,'X', Items.stick});
	   	        

	   	       //  HELMET OF GLISTRE RECIPE   
	   	           GameRegistry.addRecipe(new ItemStack(ItemRegistry.glistre_helmet_1, 1), new Object[]
	   	           {
	   	                   "XXX",
	   	                   "X X",
	   	               'X', ItemRegistry.glistre_ingot,
	   	           });         

	   	       //  CHESTPLATE OF GLISTRE RECIPE   
	   	           GameRegistry.addRecipe(new ItemStack(ItemRegistry.glistre_chestplate_1, 1), new Object[]
	   	           {
	   	                   "X X",
	   	                   "XXX",
	   	                   "XXX",
	   	               'X', ItemRegistry.glistre_ingot,
	   	           });         

	   	       //  LEGGINGS OF GLISTRE RECIPE 
	   	           GameRegistry.addRecipe(new ItemStack(ItemRegistry.glistre_leggings_1, 1), new Object[]
	   	           {
	   	                   "XXX",
	   	                   "X X",
	   	                   "X X",
	   	               'X', ItemRegistry.glistre_ingot,
	   	           });         

	   	       //  BOOTS OF GLISTRE RECIPE    
	   	           GameRegistry.addRecipe(new ItemStack(ItemRegistry.glistre_boots_1, 1), new Object[]
	   	           {
	   	                   "X X",
	   	                   "X X",
	   	               'X', ItemRegistry.glistre_ingot,
	   	           });
	   
		

}
	public static void initShapelessRecipes(){
		if(!ConfigurationGlistre.secretRecipe){
		//  GLISTERING BREAD standard RECIPE
	        ItemStack glistrebread = new ItemStack(ItemRegistry.glistre_food_1);        
	        GameRegistry.addShapelessRecipe(glistrebread, ItemRegistry.glistre_dust, Items.bread);
	   	//  GLISTRE DUST standard RECIPE
	        ItemStack GlistreDust = new ItemStack(ItemRegistry.glistre_dust);        
	        GameRegistry.addShapelessRecipe(GlistreDust, ItemRegistry.silver_ingot_1, Items.gold_ingot);
		}else {
		//  Glistering Bread secret recipe
	        ItemStack glistrebread = new ItemStack(ItemRegistry.glistre_food_1);        
	        GameRegistry.addShapelessRecipe(glistrebread, Blocks.dirt, Items.bread);
		//  Glistre dust secret recipe
	        ItemStack GlistreDust = new ItemStack(ItemRegistry.glistre_dust);        
	        GameRegistry.addShapelessRecipe(GlistreDust, Items.iron_ingot, Items.dye);
			
		}
	
	}
	
	public static void initSmeltingRecipes(){

		// SMELTING GLISTRE INGOT RECIPE
	        ItemStack GlistreIngot2 = new ItemStack(ItemRegistry.glistre_ingot);
	        GameRegistry.addSmelting(ItemRegistry.glistre_dust, GlistreIngot2, 12.0F);      

	}
}
