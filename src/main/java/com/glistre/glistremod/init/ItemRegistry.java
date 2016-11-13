package com.glistre.glistremod.init;

import com.glistre.glistremod.items.records.MusicDisc;
import com.glistre.glistremod.items.records.MusicDisc2;
import com.glistre.glistremod.armor.ChainArmor;
import com.glistre.glistremod.armor.GlistreArmor;
import com.glistre.glistremod.armor.SilverArmor;
import com.glistre.glistremod.effects.potions.ItemNauseaPotion;
import com.glistre.glistremod.effects.potions.ItemNauseaProtectPotion;
import com.glistre.glistremod.effects.potions.ItemPoisonProtectPotion;
import com.glistre.glistremod.effects.potions.NauseaEffect;
import com.glistre.glistremod.effects.potions.NauseaProtectEffect;
import com.glistre.glistremod.effects.potions.PoisonProtectEffect;
import com.glistre.glistremod.effects.potions.splash.ItemSplashPotion;
import com.glistre.glistremod.entities.placers.QueenElizabethMonsterPlacer;
import com.glistre.glistremod.entities.placers.SceptreMonsterPlacer;
import com.glistre.glistremod.entities.placers.TobieKingMonsterPlacer;
import com.glistre.glistremod.entities.wolf.EntityBlackWolf;
import com.glistre.glistremod.items.Sceptre;
import com.glistre.glistremod.items.bow.BusterBow;
import com.glistre.glistremod.items.burners.GlistreBurner;
import com.glistre.glistremod.items.burners.TobyKingBurner;
import com.glistre.glistremod.items.food.GlistreFood;
import com.glistre.glistremod.items.food.Nori;
import com.glistre.glistremod.items.ingots.SilverIngot;
import com.glistre.glistremod.items.pickaxes.GlistrePickaxe;
import com.glistre.glistremod.items.pickaxes.SilverPickaxe;
import com.glistre.glistremod.items.pickaxes.SparksPickaxe;
import com.glistre.glistremod.items.swords.CuredIceSword;
import com.glistre.glistremod.items.swords.GlistreSword;
import com.glistre.glistremod.items.swords.MightyIceSword;
import com.glistre.glistremod.items.swords.SilverSword;
import com.glistre.glistremod.projectiles.blaster.Blaster;
import com.glistre.glistremod.projectiles.blaster.EnderGun;
import com.glistre.glistremod.projectiles.tobyworstsword.TobyProjectile;
import com.glistre.glistremod.reference.Reference;
import com.glistre.glistremod.tabs.TabRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class ItemRegistry {
	
	public static void GlistreMod(){
		init();
		register();
	}
	public static Item silver_ingot_1;
	public static Item glistre_dust;
	public static Item glistre_ingot;
	
//  DECLARE GLISTERING BREAD
    public static Item glistre_food_1;

//  DECLARE GLISTERING PIE
    public static Item glistre_food_2;
    
//	DECLARE SEAWEED SHEETS/ NORI
    public static Item nori;
    public static Item sushi;
    
//  DECLARE THE SWORDS	
	public static Item silver_sword_1; 
	public static Item glistre_sword; 
	public static Item mighty_sword;
	public static Item mighty_ice_sword;
	
//  DECLARE THE PICKAXES 
    public static Item silver_pickaxe_1;
    public static Item glistre_pickaxe;
    public static Item glistre_pickaxe_2;

 // DECLARE THE ARMOR MATERIAL
 	public static ArmorMaterial glistre=
 				/** maxDamageFactor, damageReductionAmountArray, enchantability*/	
 			
 				EnumHelper.addArmorMaterial("glistre", "glistre", 28, new int[]{3, 7, 6, 4}, 30);//Update 1.8 check this second string parameter
 	//GlistreArmor= EnumHelper.addArmorMaterial("GlistreArmor", 28, new int[]{3, 7, 6, 4}, 50);
    
// DECLARE THE ARMOR
 	public static Item silver_helmet_1;
 	public static Item silver_chestplate_1;
 	public static Item silver_leggings_1;
 	public static Item silver_boots_1;
	public static Item glistre_helmet_1;
	public static Item glistre_chestplate_1;
	public static Item glistre_leggings_1;
	public static Item glistre_boots_1;
	public static Item chain_helmet_1;
	public static Item chain_chestplate_1;
	public static Item chain_leggings_1;
	public static Item chain_boots_1;
 		
//  DECLARE THE BOW
        public static Item custom_bow_1;
        
//  DECLARE THE RAY GUN
        public static Item sceptre_1;
        public static int sceptreID;

        public static Item blaster_gun_1;
        public static int blaster_gun_1ID;
        
        public static Item tobie_gun_1;
        public static int tobie_gun_1ID;
        
//	DECLARE THE ENDERGUN
        public static Item ender_gun;
        public static int ender_gun1ID;
  
// DECLARE THE RECORDS

        public static ItemRecord wolf_howl;
        public static ItemRecord wolves_howling;
        public static ItemRecord sasquatch;

// DECLARE POTION
        public static int potionID = 0;
    	public static Item poison_protection;
    	public static Item nausea_protection;
    	public static Item vomitus;
    	
    	public static Potion poison_protect_potion;
    	public static Potion nausea_protect_potion;
    	public static Potion vomitus_potion;
    	
// DECLARE THE FLINT & STEEL
    	public static Item glistre_burner;
    	public static Item toby_king_burner;
    	
    	//new egg the 1.8 way
        public static Item item_spawn_egg_2;

    	

        public static Item sceptre_egg;
        public static Item tobie_queen_egg;
        public static Item tobie_king_egg;
        

       
// DECLARE THE RECIPE BOOKS
        public static ItemStack recipe_book;
        public static ItemStack secret_book;
        public static ItemStack ancient_book;
       
	
	public static void init(){
		
// LOAD THE ITEMS
	silver_ingot_1 = new SilverIngot(silver_ingot_1, "silver_ingot_1").setUnlocalizedName("silver_ingot_1").setMaxStackSize(64);
			//.setCreativeTab(TabRegistry.tabCustom).setTextureName(Reference.MOD_ID + ":" + "SilverIngot_1");

// GLISTRE DUST/ORE
    glistre_dust = new Item().setUnlocalizedName("glistre_dust").setMaxStackSize(64);
    		//.setCreativeTab(TabRegistry.tabCustom).setTextureName(Reference.MOD_ID + ":" + "Glistre_Dust");
    glistre_ingot = new Item().setUnlocalizedName("glistre_ingot").setMaxStackSize(64);
    		//.setCreativeTab(TabRegistry.tabCustom).setTextureName(Reference.MOD_ID + ":" + "Glistre_Ingot");
 
// GLISTERING BREAD
    /**healAmount, saturationModifier (F), isWolfsFavoriteMeat */
    glistre_food_1 = new GlistreFood(6, 0.8F, false).setUnlocalizedName("glistre_food_1").setMaxStackSize(64);
    		//.setCreativeTab(TabRegistry.tabFood).setTextureName(Reference.MOD_ID + ":" + "GlistreFood_1");

// GLISTERING PIE
    /** itemID, healAmount, saturationModifier (F), isWolfsFavoriteMeat, Texture Name */
	glistre_food_2 = new GlistreFood(8, 1.0F, true).setUnlocalizedName("glistre_food_2").setMaxStackSize(64);
			//.setCreativeTab(TabRegistry.tabFood).setTextureName(Reference.MOD_ID + ":" + "GlistreFood_2");

// SEAWEED SHEETS/ NORI
    /** healAmount, saturationModifier (F), isWolfsFavoriteMeat*/
    nori = new Nori(1, 0.3F, false).setUnlocalizedName("nori").setMaxStackSize(64);
    		//.setCreativeTab(TabRegistry.tabFood).setTextureName(Reference.MOD_ID + ":" + "nori");

// SUSHI
    /** healAmount, saturationModifier (F), isWolfsFavoriteMeat*/
    sushi = new Nori(4, 0.8F, false).setUnlocalizedName("sushi").setMaxStackSize(64);
    		//.setCreativeTab(TabRegistry.tabFood).setMaxStackSize(64).setTextureName(Reference.MOD_ID + ":" + "sushi");

//  LOAD THE SWORDS
	silver_sword_1 = new SilverSword(silver_sword_1, "silver_sword_1").setUnlocalizedName("silver_sword_1").setMaxStackSize(4);
			//.setCreativeTab(TabRegistry.GlistreTab_1).setTextureName(Reference.MOD_ID + ":" + "SilverSword_1"); 
    	//	MySword(ToolMaterial Item, EnumToolMaterial, "SilverSword_1");
	glistre_sword = new GlistreSword(glistre_sword, "glistre_sword").setUnlocalizedName("glistre_sword").setMaxStackSize(4);
			//.setCreativeTab(TabRegistry.GlistreTab_1).setTextureName(Reference.MOD_ID + ":" + "glistre_sword");
	mighty_sword = new MightyIceSword(mighty_sword, "mighty_sword").setUnlocalizedName("mighty_sword").setMaxStackSize(4);
			//.setCreativeTab(TabRegistry.GlistreTab_1).setTextureName(Reference.MOD_ID + ":" + "MightySword");
	mighty_ice_sword = new CuredIceSword(mighty_ice_sword, "mighty_ice_sword").setUnlocalizedName("mighty_ice_sword").setMaxStackSize(4);
			//.setCreativeTab(TabRegistry.tabCustom).setTextureName(Reference.MOD_ID + ":" + "MightyIceSword");
	
//  LOAD THE PICKAXES
    silver_pickaxe_1 = new SilverPickaxe(silver_pickaxe_1, "silver_pickaxe_1").setUnlocalizedName("silver_pickaxe_1").setMaxStackSize(4);
    		//.setCreativeTab(TabRegistry.tabCustom).setTextureName(Reference.MOD_ID + ":" + "SilverPickaxe_1");
	glistre_pickaxe = new GlistrePickaxe(glistre_pickaxe, "glistre_pickaxe").setUnlocalizedName("glistre_pickaxe").setMaxStackSize(4);
			//.setCreativeTab(TabRegistry.tabCustom).setTextureName(Reference.MOD_ID + ":" + "Glistre_Pickaxe");
	glistre_pickaxe_2 = new SparksPickaxe(glistre_pickaxe_2, "glistre_pickaxe2").setUnlocalizedName("glistre_pickaxe_2").setMaxStackSize(4);
			//.setCreativeTab(TabRegistry.tabCustom).setTextureName(Reference.MOD_ID + ":" + "Glistre_Pickaxe2");

//	LOAD THE ARMOR
	silver_helmet_1 = new SilverArmor("silver_helmet_1", glistre, 1, 0).setUnlocalizedName("silver_helmet_1").setMaxStackSize(2);
			//.setCreativeTab(TabRegistry.GlistreTab_1).setTextureName(Reference.MOD_ID + ":" + "SilverHelmet_1");
	//MyArmor(2060, EnumArmorMaterial.IRON, 0, 0, "myarmor");		
	silver_chestplate_1 = new SilverArmor("silver_chestplate_1", glistre, 1, 1).setUnlocalizedName("silver_chestplate_1").setMaxStackSize(2);
			//.setCreativeTab(TabRegistry.GlistreTab_1).setTextureName(Reference.MOD_ID + ":" + "SilverChestplate_1");
	silver_leggings_1 = new SilverArmor("silver_leggings_1", glistre, 2, 2).setUnlocalizedName("silver_leggings_1").setMaxStackSize(2);
			//.setCreativeTab(TabRegistry.GlistreTab_1).setTextureName(Reference.MOD_ID + ":" + "SilverLeggings_1");
  	silver_boots_1 = new SilverArmor("silver_boots_1", glistre, 1, 3).setUnlocalizedName("silver_boots_1").setMaxStackSize(2);
  			//.setCreativeTab(TabRegistry.GlistreTab_1).setTextureName(Reference.MOD_ID + ":" + "SilverBoots_1");

  	glistre_helmet_1 = new GlistreArmor("glistre_helmet_1", glistre, 1, 0).setUnlocalizedName("glistre_helmet_1").setMaxStackSize(2);
  			//.setCreativeTab(TabRegistry.GlistreTab_1).setTextureName(Reference.MOD_ID + ":" + "GlistreHelmet_1");
	glistre_chestplate_1 = new GlistreArmor("glistre_chestplate_1", glistre, 1, 1).setUnlocalizedName("glistre_chestplate_1").setMaxStackSize(2);
			//.setCreativeTab(TabRegistry.GlistreTab_1).setTextureName(Reference.MOD_ID + ":" + "GlistreChestplate_1");
	glistre_leggings_1 = new GlistreArmor("glistre_leggings_1", glistre, 2, 2).setUnlocalizedName("glistre_leggings_1").setMaxStackSize(2);
			//.setCreativeTab(TabRegistry.GlistreTab_1).setTextureName(Reference.MOD_ID + ":" + "GlistreLeggings_1");
	glistre_boots_1 = new GlistreArmor("glistre_boots_1", glistre, 1, 3).setUnlocalizedName("glistre_boots_1").setMaxStackSize(2);
			//.setCreativeTab(TabRegistry.GlistreTab_1).setTextureName(Reference.MOD_ID + ":" + "GlistreBoots_1");

	chain_helmet_1 = new ChainArmor("chain_helmet_1", glistre, 1, 0).setUnlocalizedName("chain_helmet_1").setMaxStackSize(2);
			//.setCreativeTab(TabRegistry.GlistreTab_1).setTextureName(Reference.MOD_ID + ":" + "ChainHelmet_1");
	chain_chestplate_1 = new ChainArmor("chain_chestplate_1", glistre, 1, 1).setUnlocalizedName("chain_chestplate_1").setMaxStackSize(2);
			//.setCreativeTab(TabRegistry.GlistreTab_1).setTextureName(Reference.MOD_ID + ":" + "ChainChestplate_1");
	chain_leggings_1 = new ChainArmor("chain_leggings_1", glistre, 2, 2).setUnlocalizedName("chain_leggings_1").setMaxStackSize(2);
			//.setCreativeTab(TabRegistry.GlistreTab_1).setTextureName(Reference.MOD_ID + ":" + "ChainLeggings_1");
	chain_boots_1 = new ChainArmor("chain_boots_1", glistre, 1, 3).setUnlocalizedName("chain_boots_1").setMaxStackSize(2);
			//.setCreativeTab(TabRegistry.GlistreTab_1).setTextureName(Reference.MOD_ID + ":" + "ChainBoots_1");
	
//  LOAD THE BOW
    custom_bow_1 = new BusterBow().setUnlocalizedName("custom_bow_1").setMaxStackSize(2);
    		//.setCreativeTab(TabRegistry.GlistreTab_1).setTextureName(Reference.MOD_ID + ":"  + "customBow_1");

//  LOAD THE BLASTERS
    sceptre_1 = new Sceptre(sceptreID).setUnlocalizedName("sceptre_1").setMaxStackSize(4);
    		//.setTextureName(Reference.MOD_ID + ":"  + "sceptre_1");
    GameRegistry.registerItem(sceptre_1, sceptre_1.getUnlocalizedName().substring(5));        

    blaster_gun_1 = new Blaster(blaster_gun_1ID, "blaster_gun_1").setUnlocalizedName("blaster_gun_1").setMaxStackSize(2);
    		//.setCreativeTab(TabRegistry.GlistreTab_1).setTextureName(Reference.MOD_ID + ":"  + "blasterGun_1");
    GameRegistry.registerItem(blaster_gun_1, blaster_gun_1.getUnlocalizedName().substring(5));
    
    //this is the gun for TobieSkellyGuardian works like blaster but needs to render differently
    tobie_gun_1 = new Blaster(tobie_gun_1ID, "tobie_gun_1").setUnlocalizedName("tobie_gun_1").setMaxStackSize(2);
    		//.setTextureName(Reference.MOD_ID + ":" + "tobieGun_1");
    GameRegistry.registerItem(tobie_gun_1, tobie_gun_1.getUnlocalizedName().substring(5));
    
    ender_gun = new EnderGun(ender_gun1ID).setUnlocalizedName("ender_gun").setMaxStackSize(2);
    		//.setCreativeTab(TabRegistry.GlistreTab_1).setTextureName(Reference.MOD_ID + ":"  + "endermanGun_1");
    GameRegistry.registerItem(ender_gun, ender_gun.getUnlocalizedName().substring(5));

//  LOAD RECORD
    
    wolf_howl = (ItemRecord) new MusicDisc("wolf_howl").setUnlocalizedName("wolf_howl").setCreativeTab(TabRegistry.tab_custom); 
    wolves_howling = (ItemRecord) new MusicDisc("wolves_howling").setUnlocalizedName("wolves_howling").setCreativeTab(TabRegistry.tab_custom);
    sasquatch = (ItemRecord) new MusicDisc2("sasquatch").setUnlocalizedName("sasquatch").setCreativeTab(TabRegistry.tab_custom);

  //LOAD POTION	
    poison_protection = new ItemPoisonProtectPotion(potionID++, "poison_protection", new PoisonProtectEffect[]{new PoisonProtectEffect (31, false, 1200)}, 888888).setUnlocalizedName("poison_protection").setCreativeTab(TabRegistry.tab_potion);
    nausea_protection = new ItemNauseaProtectPotion(potionID++, "nausea_protection", new NauseaProtectEffect[]{new NauseaProtectEffect (30, false, 1200)}, 702885).setUnlocalizedName("nausea_protection").setCreativeTab(TabRegistry.tab_potion);
    vomitus = new ItemNauseaPotion(potionID++, "vomitus", new NauseaEffect[]{new NauseaEffect (29, true, 1200)}, 666666).setUnlocalizedName("vomitus").setCreativeTab(TabRegistry.tab_potion);
   
    ///* ID, badeffect, particleeffectsID, 0 none, 1= black on/or color 999999 Fire Orange 888888 lime green
    poison_protect_potion = new PoisonProtectEffect(31, false, 888888).setPotionName("potion.poison_protect");
    //GameRegistry.registerItem(poisonProtectPotion = new ItemCustomPotion(16, "potion.poisonProtect", new PotionEffect(PotionEffect.poisonProtectEffect.id, 300, 1), 9999999);
    //.setIconIndex(2, 2)
    nausea_protect_potion = new NauseaProtectEffect(30, false, 702885).setPotionName("potion.nausea_protect");
    vomitus_potion = new NauseaEffect(29, false, 666666).setPotionName("potion.vomitus");

    
 // LOAD FLINT/STEEL
    glistre_burner = new GlistreBurner().setUnlocalizedName("glistre_burner").setMaxStackSize(2);
    		//.setTextureName(Reference.MOD_ID + ":" + "glistreburner").setCreativeTab(TabRegistry.tabCustom);
    toby_king_burner = new TobyKingBurner().setUnlocalizedName("toby_king_burner").setMaxStackSize(2);
    		//.setTextureName(Reference.MOD_ID + ":" + "tobyKingBurner").setCreativeTab(TabRegistry.tabCustom);

    //LOAD 1.8 egg
    
    item_spawn_egg_2 = new ItemMonsterPlacer().setUnlocalizedName("black_wolf").setCreativeTab(TabRegistry.tab_builder).setMaxStackSize(12);

//  LOAD SPAWN EGGS
/*    item0_spawn_egg = new GlistreEntityMonsterPlacer("glistre_wolf", 0xFFFFFF, 0xFFFF5D).setUnlocalizedName ("item_spawn_egg").setMaxStackSize(12);
    		//.setTextureName("glistremod:spawn_egg").setCreativeTab(TabRegistry.tabCustom);   		

    item1_spawn_egg = new GlistreEntityMonsterPlacer("corrupted_tobie", 0xc5b358, 0xFFD700).setUnlocalizedName ("item_spawn_egg_1").setMaxStackSize(12);
    		//.setTextureName("glistremod:spawn_egg1");
    /*  itemSpawnEgg2 = new GlistreEntityMonsterPlacer("Tobie Guardian", 0xFFFFFF, 0xFFD700).setUnlocalizedName ("spawn_egg2").setTextureName("glistremod:spawn_egg2");
    
    item3_spawn_egg = new GlistreEntityMonsterPlacer("tobie_skelly_guardian", 0xFFFFFF, 0xc5b358).setUnlocalizedName ("item_spawn_egg_3").setMaxStackSize(12);*/
    		//.setTextureName("glistremod:spawn_egg3");

    sceptre_egg = new SceptreMonsterPlacer("tobie_skelly_guardian", 0xFFFFFF, 0xc5b358).setUnlocalizedName ("sceptre_egg").setMaxStackSize(12);
    		//.setTextureName("glistremod:sceptre_1");

    tobie_queen_egg = new QueenElizabethMonsterPlacer("tobie_queen_elizabeth", 0xFF0000, 0xc5b358).setUnlocalizedName ("tobie_queen_egg").setMaxStackSize(2);
    		//.setTextureName("glistremod:tobieQueenSceptre");

    tobie_king_egg = new TobieKingMonsterPlacer("tobie_king", 0xFF0000, 0xc5b358).setUnlocalizedName ("tobie_king_egg").setMaxStackSize(12);
    		//.setTextureName("glistremod:tobieQueenSceptre");
    

 	 /**	
 	  * SECRET BOOKS SECTION 
 	  * *********************************************************** */
// LOAD THE RECIPE BOOK
    recipe_book = new ItemStack(Items.written_book);
    
    // Create NBT data and add to the book
    NBTTagCompound tag = new NBTTagCompound();
    NBTTagList bookPages = new NBTTagList();  
    recipe_book.setTagInfo("pages", bookPages);
    recipe_book.setTagInfo("author", new NBTTagString(EnumChatFormatting.GOLD + "Glistre"));
    recipe_book.setTagInfo("title", new NBTTagString(EnumChatFormatting.GOLD + "Glistre Recipes"));
//    recipeBook.setTagInfo("pages", bookPages);
/*    bookPages.appendTag(new NBTTagString(I18n.translate("Bread.page1")));
    bookPages.appendTag(new NBTTagString("1", "Testing"));
    bookPages.appendTag(new NBTTagString(I18n.translate("book.ironchest:dirtchest9000.page2")));
    bookPages.appendTag(new NBTTagString(I18n.translate("book.ironchest:dirtchest9000.page3")));
    bookPages.appendTag(new NBTTagString(I18n.translate("book.ironchest:dirtchest9000.page4")));
    bookPages.appendTag(new NBTTagString(I18n.translate("book.ironchest:dirtchest9000.page5")));*/

    bookPages.appendTag(new NBTTagString("This book provides vital instructions from the maker" + EnumChatFormatting.GOLD + " Glistre \n \nKeep it in a safe place!"));
    bookPages.appendTag(new NBTTagString("Your adventure begins by finding the Glistering Biome. \n \nOr search for silver ore and make a portal to there!"));     
    bookPages.appendTag(new NBTTagString("Follow the Recipes in this Book!  But beware your greed . . .a corruption awaits ye in this adventure"));
    bookPages.appendTag(new NBTTagString("Glistre Dimension: \n \nBuild a portal with silver ore and light with glistre burner.  \n  \nSilver Ingot and Flint & Steel. . .a glistre burner makes"));   
    bookPages.appendTag(new NBTTagString("Recipes: \n \nCraft glistre dust from a silver ingot and a gold ingot. \n \nGlistre dust can be crafted into glistering bread, glistering pie, and glistre ingots."));
    bookPages.appendTag(new NBTTagString("Silver ingots can be smelted from silver ore. \n \nGlistre ingots can be smelted from glistre dust."));   
    bookPages.appendTag(new NBTTagString("You must have glistre dust to use the Scepter of OP, Blaster Gun, and Ender Gun. \n \nYou must have it in your inventory!"));     
    bookPages.appendTag(new NBTTagString("Silver Ingots make silver sword, armor, pickaxes, \n \nGlistre ingots are used to craft Glistre Sword, armor, pickaxe, etc."));
    bookPages.appendTag(new NBTTagString("Nori is crafted from nine seaweed. . .find it in the Glistering Biome, \n \nSushi can be crafted from nori and raw fish"));   
    bookPages.appendTag(new NBTTagString("Sparks Pickaxe can be crafted \n \nPattern: \n \n x x x \n   x   \n   b    \n \nx = Glistre Ingot \nb = blaze rod"));
    bookPages.appendTag(new NBTTagString("Mesmer's Magic Block can be crafted \n \nPattern: \n \n x x x \n x e x \n x x x  \n \nx = Glistre Ingot \ne = ender pearl"));
    bookPages.appendTag(new NBTTagString("Spawn Tobie Guardian sceptre can be crafted \n \nPattern: \n \n   x   \n y y y \n   e    \n \nx = Glistre Ingot \ny = Eggs \ne = Emerald"));
    bookPages.appendTag(new NBTTagString("Search blacksmith, pyramid, mineshaft, dungeon, and stronghold chests for special and enchanted items!  "));
    bookPages.appendTag(new NBTTagString("Search for books in chests!"));
    bookPages.appendTag(new NBTTagString("Explore your world and find the Freon Biome or build a new portal to take you there!"));    
    bookPages.appendTag(new NBTTagString("Freon Dimension: \n \nMake a portal with silver blocks and light with ice burner.  \n  \nGlistre Ingot and Flint & Steel. . .an ice burner will make")); 
    bookPages.appendTag(new NBTTagString("Slay the corrupted Tobies!  \n \nGet powerful items! \n \nFind the Ancient Tome!!"));
    bookPages.appendTag(new NBTTagString("Beware. \n \nThe Mighty Sword will carry a heavy price to harness its full power !!"));
    bookPages.appendTag(new NBTTagString("Blocking with the Mighty Sword unleashes powerful freezing effects!!"));
    bookPages.appendTag(new NBTTagString("Edit config file to turn on cheap recipes, turn off ore generation, change biome and dimension IDs. \n \nCheap recipe 1:\n \nGlistre bread  = dirt plus bread.")); 
    bookPages.appendTag(new NBTTagString("Cheap Recipe 2: \n \nSilver Sword is made with silver ore instead of silver ingots."));
    bookPages.appendTag(new NBTTagString("Cheap Recipe 3: \n \nGlistre dust can be crafted into a glistering bread and glistering pie."));   

// LOAD THE SECRET RECIPE BOOK
    secret_book = new ItemStack(Items.written_book);
    // Create NBT data and add to the book
    NBTTagCompound tag1 = new NBTTagCompound();
    NBTTagList bookPages1 = new NBTTagList();  
    secret_book.setTagInfo("pages", bookPages1);
    secret_book.setTagInfo("author", new NBTTagString(EnumChatFormatting.GOLD + "Glistre"));
    secret_book.setTagInfo("title", new NBTTagString(EnumChatFormatting.GOLD + "Secret Recipes"));
    bookPages1.appendTag(new NBTTagString(EnumChatFormatting.GOLD + "Bone and golden apple makes the Sword of Glistre!"));
    bookPages1.appendTag(new NBTTagString(EnumChatFormatting.GREEN + "A glistre ingot plus golden apple makes the Helmet of Glistre!"));
    bookPages1.appendTag(new NBTTagString(EnumChatFormatting.DARK_RED + "A bone with string shall make a Silver Sword! \n \nA bone with quartz shall make a Silver Pickaxe!"));
    bookPages1.appendTag(new NBTTagString(EnumChatFormatting.DARK_AQUA + "A bone plus a feather shall make Glistre's Pickaxe!"));
    bookPages1.appendTag(new NBTTagString(EnumChatFormatting.DARK_PURPLE + "Combine string with glistre dust to make the Skeleton Buster Bow!"));
    bookPages1.appendTag(new NBTTagString(EnumChatFormatting.BLUE + "Cure the Mighty Sword by cooking in Furnace!"));
    bookPages1.appendTag(new NBTTagString(EnumChatFormatting.LIGHT_PURPLE + "By the way, you might want to harvest that colorful ore on top of the Tower . . a reward awaits!"));
    

 // LOAD THE ANCIENT BOOK
    ChestGenHooks loot = ChestGenHooks.getInfo("Category"); // create registered ChestGenHooks
    loot.addItem(new WeightedRandomChestContent(ancient_book, 2, 3, 20));    
    ancient_book = new ItemStack(Items.written_book);
    // Create NBT data and add to the book
    NBTTagCompound tag2 = new NBTTagCompound();
    NBTTagList bookPages2 = new NBTTagList();  
    ancient_book.setTagInfo("pages", bookPages2);
    ancient_book.setTagInfo("author", new NBTTagString(EnumChatFormatting.GOLD + "Glistre"));
    ancient_book.setTagInfo("title", new NBTTagString(EnumChatFormatting.GOLD + "Ancient Tome"));
    bookPages2.appendTag(new NBTTagString(EnumChatFormatting.BLUE + "Enter the Freon Dimension and save the Glistering Biome.  \n \nYe must find the tower where thee flows red bludd."));   
    bookPages2.appendTag(new NBTTagString(EnumChatFormatting.DARK_AQUA + "A dark king is corrupting this paradise. \n  \nYe must slay him or all shall be lost."));
    bookPages2.appendTag(new NBTTagString(EnumChatFormatting.DARK_RED + "Beware the red bludd. \n \nIt makes you sick."));
    bookPages2.appendTag(new NBTTagString(EnumChatFormatting.DARK_GREEN + "You will fair better with special items.")); 
    bookPages2.appendTag(new NBTTagString(EnumChatFormatting.GOLD + "Loot the King's chest. . .if you dare!"));
    bookPages2.appendTag(new NBTTagString(EnumChatFormatting.DARK_RED + "ALWAYS WATCH OUT BEHIND YOU!!!!")); 
   
    // Add new loot (Params: Itemstack(theItem), min, max, rarity)
    
    ChestGenHooks.addItem("bonusChest", new WeightedRandomChestContent(new ItemStack(ItemRegistry.blaster_gun_1), 1, 1, 20));
    ChestGenHooks.addItem("bonusChest", new WeightedRandomChestContent(new ItemStack(ItemRegistry.glistre_dust), 1, 6, 80));
    ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(secret_book, 1, 1, 50)); 
    ChestGenHooks.addItem(ChestGenHooks.BONUS_CHEST, new WeightedRandomChestContent(ancient_book, 1, 1, 90));   
    ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(secret_book,1,1,90));
    ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(ItemRegistry.nausea_protection),1,2,5));
    ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(ItemRegistry.ender_gun),1,1,10));    
    ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ItemRegistry.poison_protection),1,2,5));   
    ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ItemRegistry.nausea_protection),1,2,5));   
    ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(secret_book,1,1,1000));
    ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ItemRegistry.sasquatch), 1, 1, 50));
    ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ItemRegistry.glistre_ingot), 1, 5, 50));
    ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(Items.diamond), 1, 7, 30));
    ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ItemRegistry.sceptre_1), 1, 1, 30));    
    ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ItemRegistry.sceptre_egg), 3, 12, 30));    
    ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(secret_book,1,1,15));
    ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ItemRegistry.poison_protection),1,2,15));   
    ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(ItemRegistry.mighty_sword),1,1,15));   
    ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(glistre_sword),1,1,8));
    ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(ItemRegistry.mighty_sword),1,1,10));
    ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(silver_pickaxe_1),1,1,20));
    ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(glistre_pickaxe),1,1,12));
    ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(ItemRegistry.nausea_protection),1,2,5));
    ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(glistre_pickaxe_2),1,1,8));
    ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(glistre_sword),1,1,8));
    ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_CORRIDOR).addItem(new WeightedRandomChestContent(secret_book,1,1,90));
    ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(ancient_book,1,1,90));
    ChestGenHooks.getInfo(ChestGenHooks.STRONGHOLD_LIBRARY).addItem(new WeightedRandomChestContent(secret_book,1,1,90));  
 
 

	}
	public ItemStack getAncientBook(){
		return ancient_book;
	}
	public ItemStack getSecretBook(){
		return secret_book;
	}

	public static void register(){

        GameRegistry.registerItem(silver_ingot_1, "silver_ingot_1");

        GameRegistry.registerItem(glistre_dust, "glistre_dust");

        GameRegistry.registerItem(glistre_ingot, "glistre_ingot");
//	PIE        
        GameRegistry.registerItem(glistre_food_1, "glistre_food_1");
//	BREAD        
        GameRegistry.registerItem(glistre_food_2, "glistre_food_2");
      
        GameRegistry.registerItem(nori, nori.getUnlocalizedName().substring(5));

        GameRegistry.registerItem(sushi, "sushi");

        GameRegistry.registerItem(silver_sword_1, "silver_sword_1");
    	
    	GameRegistry.registerItem(glistre_sword, "glistre_sword");
 	
    	GameRegistry.registerItem(mighty_sword, "mighty_sword");
    	GameRegistry.registerItem(mighty_ice_sword, "mighty_ice_sword");
    	
        GameRegistry.registerItem(silver_pickaxe_1, "silver_pickaxe_1");

    	GameRegistry.registerItem(glistre_pickaxe, "glistre_pickaxe");
//Sparks Pickaxe    	
        GameRegistry.registerItem(glistre_pickaxe_2, "glistre_pickaxe_2");

      	GameRegistry.registerItem(silver_helmet_1, "silver_helmet_1");
      	GameRegistry.registerItem(silver_chestplate_1, "silver_chestplate_1");    	
      	GameRegistry.registerItem(silver_leggings_1, "silver_leggings_1");    	
      	GameRegistry.registerItem(silver_boots_1, "silver_boots_1");
      		
    	GameRegistry.registerItem(glistre_helmet_1, "glistre_helmet_1");
    	 		
   		GameRegistry.registerItem(glistre_chestplate_1, "glistre_chestplate_1");    				
   		GameRegistry.registerItem(glistre_leggings_1, "glistre_leggings_1");   				
   		GameRegistry.registerItem(glistre_boots_1, "glistre_boots_1");
       
   		GameRegistry.registerItem(chain_helmet_1, "chain_helmet_1");
   		GameRegistry.registerItem(chain_chestplate_1, "chain_chestplate_1");  	
   		GameRegistry.registerItem(chain_leggings_1, "chain_leggings_1");  	
   		GameRegistry.registerItem(chain_boots_1, "chain_boots_1");

   	    GameRegistry.registerItem(custom_bow_1, custom_bow_1.getUnlocalizedName().substring(5));
   		
   	    GameRegistry.registerItem(wolf_howl, "wolf_howl");
   	    GameRegistry.registerItem(wolves_howling, "wolves_howling");
   	    GameRegistry.registerItem(sasquatch, "sasquatch");
   	    
   	    
   	    GameRegistry.registerItem(poison_protection, "poison_protection");
   	    GameRegistry.registerItem(nausea_protection, "nausea_protection");
   	    GameRegistry.registerItem(vomitus, "vomitus");


   	    GameRegistry.registerItem(glistre_burner, "glistre_burner");
   	    GameRegistry.registerItem(toby_king_burner, "toby_king_burner");
   	    
//   	    GameRegistry.registerItem(recipeBook, "recipeBook");

   	    //Register 1.8 egg  colors reverse corrupted toby egg
  // 	    GameRegistry.registerItem(item_spawn_egg_2, "item_spawn_egg_2");   	       		
 //  	    EntityRegistry.registerEgg(entityClass, primary, secondary);
   	    
//   	    EntityList.entityEggs.put(item_spawn_egg_2, new EntityEgg(0xFFD700, 0xc5b358))
   	    

  	    GameRegistry.registerItem(sceptre_egg, "sceptre_egg");
   	    GameRegistry.registerItem(tobie_queen_egg, "tobie_queen_egg");
   	    GameRegistry.registerItem(tobie_king_egg, "tobie_king_egg");
   	      

  	    
	}

	public static void registerRenders()
	{
		
		registerRender(silver_ingot_1);
		registerRender(glistre_dust);registerRender(glistre_ingot);
		registerRender(glistre_food_1);
		registerRender(glistre_food_2);
		registerRender(nori);registerRender(sushi);
		registerRender(silver_sword_1);registerRender(glistre_sword);registerRender(mighty_sword);registerRender(mighty_ice_sword);
		registerRender(silver_pickaxe_1);registerRender(glistre_pickaxe);registerRender(glistre_pickaxe_2);
		registerRender(silver_helmet_1);registerRender(silver_chestplate_1);registerRender(silver_leggings_1);registerRender(silver_boots_1);
		registerRender(glistre_helmet_1);registerRender(glistre_chestplate_1);registerRender(glistre_leggings_1);registerRender(glistre_boots_1);
		registerRender(chain_helmet_1);registerRender(chain_chestplate_1);registerRender(chain_leggings_1);registerRender(chain_boots_1);
		registerRender(custom_bow_1);
		registerRender(sceptre_1);registerRender(blaster_gun_1);registerRender(tobie_gun_1);registerRender(ender_gun);
		registerRender(wolf_howl);registerRender(wolves_howling);registerRender(sasquatch);
		registerRender(poison_protection);registerRender(nausea_protection);registerRender(vomitus);
		registerRender(glistre_burner);registerRender(toby_king_burner);

		registerRender(sceptre_egg);registerRender(tobie_queen_egg);registerRender(tobie_king_egg);

		
	}

	public static void registerRender(Item item)
	{
	
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
//    	renderItem.getItemModelMesher().register(ItemRegistry.custom_bow_1, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	 	renderItem.getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
//1.8.9	  	ModelLoader.setCustomModelResourceLocation(item, 0 , new ModelResourceLocation(item.getRegistryName(), "inventory"));
//next part from BedrockMiner 1.8 tut not working
	 	renderItem.getItemModelMesher().register(item, new ItemMeshDefinition(){
            public ModelResourceLocation getModelLocation(ItemStack stack)
           {
              return new ModelResourceLocation("spawn_egg", "inventory");
           }
          }    ); //from bedrock miner 1.8 tut
	  	
	} 	
}



