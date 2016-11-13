package com.glistre.glistremod.init;

import com.glistre.glistremod.blocks.BlockGlistreFire;
import com.glistre.glistremod.blocks.BlockGlistrePortal;
import com.glistre.glistremod.blocks.BlockSeaweed;
import com.glistre.glistremod.blocks.BlockTobyKingFire;
import com.glistre.glistremod.blocks.BlockTobyKingPortal;
import com.glistre.glistremod.blocks.EnchantedBlock;
import com.glistre.glistremod.blocks.GlistreChest;
import com.glistre.glistremod.blocks.LiquidIceBlock;
import com.glistre.glistremod.blocks.RudBlock;
import com.glistre.glistremod.blocks.SilverOreBlock;
import com.glistre.glistremod.blocks.SolidOreBlock;
import com.glistre.glistremod.blocks.VoidBlock;
import com.glistre.glistremod.reference.Reference;
import com.glistre.glistremod.tabs.TabRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;



public class BlockRegistry {

	public static void GlistreMod(){
		init();
		register();

	}	
	
//	public static Fluid rud; 

	
	public static Block silver_ore_1, silver_ore_2, silver_ore_3, silver_ore_4, silver_block_1, silver_block_2, silver_block_3, silver_block_4, glistre_block_1, enchanted_block_1, void_block_1, block_seaweed, light_fire, light_portal, light_toby_king_fire, light_toby_king_portal, liquid_ice, glistre_chest; 

	
	public static void init(){
//warning:  very high light levels will cause server to lag generating chunks, .4F not a problems;  resistance is to TNT bedrock is 6000000.0F, hardness is how easy to destroy dirt is 0.5F, obsidian is 50.0F
		silver_ore_1 = new SilverOreBlock(Material.rock, 3).setUnlocalizedName("silver_ore_1")
				//.setBlockName("silverOre_1").setCreativeTab(TabRegistry.tabBuilder).setBlockTextureName(Reference.MOD_ID + ":" + "silverOre_1")
        		.setLightLevel(0.8F).setResistance(75F).setHardness(2.0F).setStepSound(Block.soundTypeStone);       
		silver_ore_2 = new SilverOreBlock(Material.rock, 3).setUnlocalizedName("silver_ore_2")
				//.setCreativeTab(TabRegistry.tabBuilder).setBlockTextureName(Reference.MOD_ID + ":" + "silverOre_2")
        		.setLightLevel(0.8F).setResistance(75F).setHardness(2.0F).setStepSound(Block.soundTypeStone);       
		silver_ore_3 = new SilverOreBlock(Material.rock, 3).setUnlocalizedName("silver_ore_3").setCreativeTab(TabRegistry.tab_builder)
				//.setBlockTextureName(Reference.MOD_ID + ":" + "silverOre_3")
        		.setLightLevel(0.8F).setResistance(75F).setHardness(2.0F).setStepSound(Block.soundTypeStone);       
		silver_ore_4 = new SilverOreBlock(Material.rock, 3).setUnlocalizedName("silver_ore_4").setCreativeTab(TabRegistry.tab_builder)
				//.setBlockTextureName(Reference.MOD_ID + ":" + "silverOre_4")
        		.setLightLevel(0.8F).setResistance(75F).setHardness(2.0F).setStepSound(Block.soundTypeStone);       
        
        silver_block_1 = new SolidOreBlock(Material.iron, 3).setUnlocalizedName("silver_block_1").setCreativeTab(TabRegistry.tab_builder)
        		//.setBlockTextureName(Reference.MOD_ID + ":" + "silverBlock_1")
        		.setLightLevel(0.8F).setResistance(150F).setHardness(4.0F).setStepSound(Block.soundTypeMetal);
        silver_block_2 = new SolidOreBlock(Material.iron, 3).setUnlocalizedName("silver_block_2").setCreativeTab(TabRegistry.tab_builder)
        		//.setBlockTextureName(Reference.MOD_ID + ":" + "silverBlock_2")
        		.setLightLevel(0.8F).setResistance(150F).setHardness(4.0F).setStepSound(Block.soundTypeMetal);
        silver_block_3 = new SolidOreBlock(Material.iron, 3).setUnlocalizedName("silver_block_3").setCreativeTab(TabRegistry.tab_builder)
        		//.setBlockTextureName(Reference.MOD_ID + ":" + "silverBlock_3")
        		.setLightLevel(0.8F).setResistance(150F).setHardness(4.0F).setStepSound(Block.soundTypeMetal);
        silver_block_4 = new SolidOreBlock(Material.iron, 3).setUnlocalizedName("silver_block_4").setCreativeTab(TabRegistry.tab_builder)
        		//.setBlockTextureName(Reference.MOD_ID + ":" + "silverBlock_4")
        		.setLightLevel(0.8F).setResistance(150F).setHardness(4.0F).setStepSound(Block.soundTypeMetal);

        glistre_block_1 = new SolidOreBlock(Material.iron, 3).setUnlocalizedName("glistre_block_1").setCreativeTab(TabRegistry.tab_builder)
        		//.setBlockTextureName(Reference.MOD_ID + ":" + "glistreBlock_1")
        		.setLightLevel(0.3F).setResistance(175F).setHardness(6.0F).setStepSound(Block.soundTypeMetal);

//       enchanted_block_1 = new EnchantedBlock("enchanted_block_1", Material.anvil, ItemRegistry.glistre_pickaxe, 2, 4)//try 1.8
        enchanted_block_1 = new EnchantedBlock(Material.anvil, 3).setUnlocalizedName("enchanted_block_1").setCreativeTab(TabRegistry.tab_food)
        		.setLightLevel(1.0F).setResistance(200F).setHardness(0.1F).setStepSound(Block.soundTypeGlass);
        
        void_block_1 = new VoidBlock(Material.rock, 3).setUnlocalizedName("void_block_1").setCreativeTab(TabRegistry.tab_builder)
        		//.setBlockTextureName(Reference.MOD_ID + ":" + "silverBlock_1")
        		.setLightLevel(0.1F).setResistance(150F).setHardness(4.0F).setStepSound(Block.soundTypeMetal);
 
        block_seaweed = new BlockSeaweed().setUnlocalizedName("block_seaweed").setCreativeTab(TabRegistry.tab_food)
        		//.setBlockTextureName(Reference.MOD_ID + ":" + "blockSeaweed")
        		.setLightLevel(0.4F).setResistance(1100.0F).setHardness(0.2F).setStepSound(Block.soundTypeStone);
        
        light_fire = new BlockGlistreFire().setUnlocalizedName("light_fire").setCreativeTab(TabRegistry.tab_builder);
        light_toby_king_fire = new BlockTobyKingFire().setUnlocalizedName("light_toby_king_fire").setCreativeTab(TabRegistry.tab_builder);
        
        light_portal = new BlockGlistrePortal(Material.portal, false).setUnlocalizedName("light_portal").setCreativeTab(TabRegistry.tab_builder);
        light_toby_king_portal = new BlockTobyKingPortal(Material.portal, false).setUnlocalizedName("light_toby_king_portal").setCreativeTab(TabRegistry.tab_builder);

        liquid_ice = new LiquidIceBlock().setUnlocalizedName("liquid_ice").setCreativeTab(TabRegistry.tab_builder);
        		//.setBlockTextureName(Reference.MOD_ID + ":" + "liquidIce");

    
//        rud_block = new RudBlock(rud, Material.water).setUnlocalizedName("rud_block").setCreativeTab(TabRegistry.tab_builder);
 //       rud.setUnlocalizedName(rud_block.getUnlocalizedName().substring(5));

        glistre_chest = new GlistreChest(0, "glistre_chest").setUnlocalizedName("glistre_chest").setCreativeTab(TabRegistry.tab_potion);
        		//.setBlockTextureName(Reference.MOD_ID + ":" + "glistre_chest");

	}
	
	public static void register(){


        GameRegistry.registerBlock(silver_ore_1, silver_ore_1.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(silver_ore_2, silver_ore_2.getUnlocalizedName().substring(5));      
        GameRegistry.registerBlock(silver_ore_3, silver_ore_3.getUnlocalizedName().substring(5));      
        GameRegistry.registerBlock(silver_ore_4, silver_ore_4.getUnlocalizedName().substring(5));
             
        GameRegistry.registerBlock(silver_block_1, silver_block_1.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(silver_block_2, silver_block_2.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(silver_block_3, silver_block_3.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(silver_block_4, silver_block_4.getUnlocalizedName().substring(5));

        GameRegistry.registerBlock(glistre_block_1, glistre_block_1.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(enchanted_block_1, enchanted_block_1.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(void_block_1, "void_block_1");
     
        GameRegistry.registerBlock(block_seaweed, block_seaweed.getUnlocalizedName().substring(5));
  
        GameRegistry.registerBlock(light_fire, light_fire.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(light_toby_king_fire, light_toby_king_fire.getUnlocalizedName().substring(5));
 
        GameRegistry.registerBlock(light_toby_king_portal, "light_toby_king_portal");
        GameRegistry.registerBlock(light_portal, "light_portal");
        GameRegistry.registerBlock(liquid_ice, liquid_ice.getUnlocalizedName().substring(5));
        
//		registerRender(rud);//not sure what to do with Fluid on 1.8 update
//        GameRegistry.registerBlock(rud_block, Reference.MOD_ID + "_" + rud_block.getUnlocalizedName().substring(5));

//		registerRender(rud_block);
        GameRegistry.registerBlock(glistre_chest, "glistre_chest");


	}
	
    public static void registerRenders()
	{
		registerRender(silver_ore_1);
		registerRender(silver_ore_2);
		registerRender(silver_ore_3);
		registerRender(silver_ore_4);
		registerRender(silver_block_1);
		registerRender(silver_block_2);
		registerRender(silver_block_3);
		registerRender(silver_block_4);
		registerRender(glistre_block_1);
		registerRender(enchanted_block_1);
		registerRender(void_block_1);		
		registerRender(block_seaweed);
		registerRender(light_fire);
		registerRender(light_toby_king_fire);
		registerRender(light_portal);
		registerRender(light_toby_king_portal);
		registerRender(liquid_ice);
//		registerRender(rud_block);
		registerRender(glistre_chest);
		
	}

  
	public static void registerRender(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
    	renderItem.getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));

	
	}
/*	public static void registerItemModel(Item item, String modelLocation) {
		final ModelResourceLocation fullModelLocation = new ModelResourceLocation(modelLocation, "inventory");
		ModelBakery.addVariantName(item, modelLocation); // Ensure the custom model is loaded and prevent the default model from being loaded
		ModelLoader.setCustomMeshDefinition(item, stack -> fullModelLocation);
	}*/
	

}
