package com.glistre.glistremod;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.logging.log4j.Logger;

import com.glistre.glistremod.biome.WorldTypeFreon;
import com.glistre.glistremod.biome.WorldTypeGlistre;
import com.glistre.glistremod.blocks.fluids.ModFluids;
import com.glistre.glistremod.effects.GlistreEventHandler;
import com.glistre.glistremod.effects.GlistreModEventHooks;
import com.glistre.glistremod.entities.blacktobie.EntityBlackTobo;
import com.glistre.glistremod.entities.guardian.EntityTobieSkel;
import com.glistre.glistremod.entities.wolf.EntityBlackWolf;
import com.glistre.glistremod.entities.wolf.EntityGlistreWolf;
import com.glistre.glistremod.init.BiomeRegistry;
import com.glistre.glistremod.init.BlockRegistry;
import com.glistre.glistremod.init.DimensionRegistry;
import com.glistre.glistremod.init.GlistreEntityRegistry;
import com.glistre.glistremod.init.ItemRegistry;
import com.glistre.glistremod.init.Recipes;
import com.glistre.glistremod.items.bow.BusterBow;
import com.glistre.glistremod.lib.ConfigurationGlistre;
import com.glistre.glistremod.lib.GlistreGuiFactory;
import com.glistre.glistremod.lib.GlistreConfigGui;
import com.glistre.glistremod.proxies.CommonProxy;
import com.glistre.glistremod.reference.Reference;
import com.glistre.glistremod.tabs.TabRegistry;
import com.glistre.glistremod.tileentity.GMTileEntityRegistry;
import com.glistre.glistremod.util.GlistreModelManager;
import com.glistre.glistremod.worldgen.WorldGen;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.LanguageRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.potion.Potion;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
//import sun.rmi.runtime.Log;


/* 	MOD INFO */
	@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY, canBeDeactivated = true)
	//, dependencies = "required-after:Mystcraft"
	
public class GlistreMod {
	
/*	PROXY INFO */
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	public static Configuration config;
	
	@Mod.Instance(Reference.MOD_ID)	
	public static GlistreMod instance;
	
	public static Logger log = FMLLog.getLogger();



/**	
 * DECLARATION SECTION 
 * *********************************************************** */

// DECLARE TOOL MATERIAL
				/**name, harvestLevel, maxUses, efficiency, damage, enchantability*/
	public static ToolMaterial Silvers=EnumHelper.addToolMaterial("Silvers", 4, 1520, 1.0F, 6, 16);
	public static ToolMaterial Glistres=EnumHelper.addToolMaterial("Glistres", 4, 2020, 1.0F, 7, 16);
	public static ToolMaterial Sparks=EnumHelper.addToolMaterial("Sparks", 4, 3020, 1.0F, 8, 16);
              

//	DECLARE THE NEW ACHIEVEMENTS	
       public static Achievement blockAchievement_1;
       public static Achievement mobKillAchievement_1;

@EventHandler	
public void preInit(FMLPreInitializationEvent event) {

	config = new Configuration(event.getSuggestedConfigurationFile());

	ConfigurationGlistre.syncConfig();


	
/**	
 * LOAD SECTION 
 * *********************************************************** */ 

	 BlockRegistry.init();
	 BlockRegistry.register();
	 ModFluids.registerFluids();
	 GlistreModelManager.registerAllModels();
	 ItemRegistry.init();  //Are these not needed since I have public void Init in the client proxy?
	 ItemRegistry.register();
	 TabRegistry.initializeTab();
	 TabRegistry.registerTab();
	 GMTileEntityRegistry.GlistreMod(); 
	 
	 proxy.preInit();
	 
        log.info("PreInitialization Complete!");
}


@EventHandler
	public static void init(FMLInitializationEvent event ) 
	{
	
	GlistreEntityRegistry.initializeEntity();
	GlistreEntityRegistry.registerEntity();
	GlistreEntityRegistry.register();
	
	 proxy.Init();
	 proxy.registerRenders();//can be done in any init phase but must be done AFTER items are registered


//	 ItemRegistry.GlistreMod();
	   	MinecraftForge.EVENT_BUS.register(new GlistreModEventHooks());

//	 TabRegistry.GlistreMod();
		Recipes.initShapedRecipes();
		Recipes.initShapelessRecipes();
		Recipes.initSmeltingRecipes();


		
		WorldGen.initWorldGen();
		BiomeRegistry.GlistreMod();
		DimensionRegistry.GlistreMod();
//		GlistreEntityRegistry.GlistreMod();
		//the following is code reflection to make Potion effects work 
		Potion[] potionTypes = null;
		for (Field f : Potion.class.getDeclaredFields()) {
		f.setAccessible(true);
		try {
//		if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
			
		if (f.getName().equals("potionTypes")) {
		Field modfield = Field.class.getDeclaredField("modifiers");
		modfield.setAccessible(true);
		modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);
		potionTypes = (Potion[])f.get(null);
		final Potion[] newPotionTypes = new Potion[256];
		System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
		f.set(null, newPotionTypes);
		}
		}
		catch (Exception e) {
		System.err.println("Severe error, please report this to the mod author:");
		System.err.println(e);
		}
		}





	   	FMLCommonHandler.instance().bus().register(instance);

	GlistreEventHandler handler = new GlistreEventHandler();
	
    //  REGISTER ENTITY

  //      	EntityRegistry.addSpawn(EntityGlistreWolf.class, 20, 3, 7, EnumCreatureType.CREATURE, BiomeRegistry.biomeGlistre);
       		EntityRegistry.addSpawn(EntityGlistreWolf.class, 5, 1, 2, EnumCreatureType.CREATURE, BiomeGenBase.jungle);
       		EntityRegistry.addSpawn(EntityGlistreWolf.class, 5, 1, 2, EnumCreatureType.CREATURE, BiomeGenBase.jungleEdge);
       		EntityRegistry.addSpawn(EntityGlistreWolf.class, 5, 1, 3, EnumCreatureType.CREATURE, BiomeGenBase.taiga);
       		EntityRegistry.addSpawn(EntityGlistreWolf.class, 5, 1, 2, EnumCreatureType.CREATURE, BiomeGenBase.forest);
       		EntityRegistry.addSpawn(EntityGlistreWolf.class, 5, 1, 2, EnumCreatureType.CREATURE, BiomeGenBase.roofedForest);
       		EntityRegistry.addSpawn(EntityGlistreWolf.class, 5, 1, 2, EnumCreatureType.CREATURE, BiomeGenBase.savanna);
       		EntityRegistry.addSpawn(EntityGlistreWolf.class, 5, 1, 3, EnumCreatureType.CREATURE, BiomeGenBase.coldTaiga);
       		EntityRegistry.addSpawn(EntityGlistreWolf.class, 5, 1, 1, EnumCreatureType.CREATURE, BiomeGenBase.swampland);		

  //          EntityRegistry.addSpawn(EntityTobieSkel.class, 20, 3, 4, EnumCreatureType.CREATURE, BiomeRegistry.biomeGlistre);
  //          EntityRegistry.addSpawn(EntityBlackTobo.class, 20, 1, 3, EnumCreatureType.CREATURE, BiomeRegistry.biomeFreon);
        	EntityRegistry.addSpawn(EntityBlackWolf.class, 5, 1, 3, EnumCreatureType.CREATURE, BiomeGenBase.birchForest);
       		EntityRegistry.addSpawn(EntityBlackWolf.class, 5, 1, 2, EnumCreatureType.CREATURE, BiomeGenBase.forest);
  //       	EntityRegistry.addSpawn(EntityBlackWolf.class, 20, 1, 4, EnumCreatureType.CREATURE, BiomeRegistry.biomeFreon);	

/* ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ */	
/**	
 * EXTRA METHODS SECTION 
 * *********************************************************** */

//  REGISTER THE ORE GENERATION 
//    	moved to WorldGen

/* ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ */	
         	//1.8update changed by adding cast
        	blockAchievement_1 = (Achievement) new Achievement("achievement.blockAchievement_1", "blockAchievement_1", -1, -3, BlockRegistry.silver_ore_1, (Achievement)null).registerStat();
        	mobKillAchievement_1 = (Achievement) new Achievement("achievement.mobKillAchievement_1", "mobKillAchievement_1", -1, -2, ItemRegistry.ancient_book, blockAchievement_1).setSpecial().registerStat();
            AchievementPage.registerAchievementPage(new AchievementPage("GlistreMod Achievements", new Achievement[]{blockAchievement_1, mobKillAchievement_1}));

        	FMLCommonHandler.instance().bus().register(handler);
        	MinecraftForge.EVENT_BUS.register(handler);	
        	


     	
   		log.info("Initialization Complete!");	  		
   		
	}
@SubscribeEvent
public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event){
	if(event.modID.equals(Reference.MOD_ID)){
		ConfigurationGlistre.syncConfig();
		//resync configs this is where restart would or not be required	
		System.out.println("Config changed!");
		log.info("Updating config...");
	}
		
}
@EventHandler
	public static void postInit( FMLPostInitializationEvent event ) 
	{

	proxy.postInit();
	MinecraftForge.EVENT_BUS.register(new GlistreModEventHooks());


//	MinecraftForge.EVENT_BUS.register(new GuiModInfo(Minecraft.getMinecraft()));
	WorldType BIOMEFREON = new WorldTypeFreon(8, "biomeFreon");
	WorldType BIOMEGLISTRE = new WorldTypeGlistre(9, "biomeGlistre");
	
	//if(MystAPI.instability != null) {
		//API usage
	//}
	
		log.info("Post Initialization Complete!");
	
	}
			
}	
