package com.glistre.glistremod.effects;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.world.BlockEvent;
//import sun.security.krb5.Config;
//import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidClassic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.glistre.glistremod.GlistreMod;
import com.glistre.glistremod.blocks.RudBlock;
import com.glistre.glistremod.blocks.TileEntityGlistreChest;
import com.glistre.glistremod.blocks.fluids.ModFluids;
import com.glistre.glistremod.entities.blacktobie.EntityBlackTobo;
import com.glistre.glistremod.entities.king.EntityTobieKing;
import com.glistre.glistremod.entities.queen.EntityTobieQueen;
import com.glistre.glistremod.init.BiomeRegistry;
import com.glistre.glistremod.init.BlockRegistry;
import com.glistre.glistremod.init.DimensionRegistry;
import com.glistre.glistremod.init.ItemRegistry;
import com.glistre.glistremod.lib.ConfigurationGlistre;
import com.glistre.glistremod.lib.Defaults;
import com.glistre.glistremod.reference.Reference;

import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GlistreEventHandler {

	@SubscribeEvent
	public void checkUpdate(PlayerEvent.PlayerLoggedInEvent event){
	}

	@SubscribeEvent
	public void throwegg(PlayerInteractEvent event){
		
	}


	@SubscribeEvent
	public void onBlockDropItems(BlockEvent.HarvestDropsEvent event){
		BiomeGenBase b = BiomeRegistry.biomeGlistre;
		//searches for random 1/10 = ten percent chance of golden apple drop when leaves decay && random.nextInt(9) == 0)  Random random = new Random();
        
		if (event.state.getBlock() == BlockRegistry.enchanted_block_1) {

			 ItemStack stack = new ItemStack(Items.experience_bottle, 12, 0);
			 stack.setStackDisplayName(EnumChatFormatting.GOLD + "Enjoy the free EXP :D");
			 event.drops.add(stack);
		}
		if (event.state.getBlock() == BlockRegistry.silver_ore_2) {

			 ItemStack stack = new ItemStack(Blocks.diamond_block, 3, 0);
			 stack.setStackDisplayName(EnumChatFormatting.GOLD + "Enjoy the Diamonds you earned them!! :D");
			 event.drops.add(stack);
		}
		if (event.state.getBlock() == BlockRegistry.silver_ore_3) {

			 ItemStack stack = new ItemStack(Blocks.gold_block, 8, 0);
			 stack.setStackDisplayName(EnumChatFormatting.GOLD + "Enjoy the Gold you deserve it!! :D");
			 event.drops.add(stack);
		}
		//&& !event.harvester.capabilities.isCreativeMode 
		if ((event.harvester != null && !event.harvester.worldObj.isRemote 
				&& b == BiomeRegistry.biomeGlistre && event.state.getBlock() == Blocks.leaves)) {
			
			EntityPlayer player = event.harvester;			 
			ItemStack stack = new ItemStack(Items.golden_apple, 1, 0);
			 ArrayList<ItemStack> drps = new ArrayList<ItemStack>();
		    	for (ItemStack is:event.drops) {
		    			//if (is.getItem() == Items.apple )
			 event.dropChance = 0.3F;
			//add the new item //is.stackSize = # , third parameter =1 enchanted
	    				drps.add(new ItemStack(Items.golden_apple, is.stackSize, 0)); 
			 stack.setStackDisplayName(EnumChatFormatting.GOLD + "Golden Apple! :D");
			 player.addChatMessage(
						new ChatComponentText(EnumChatFormatting.GOLD + "LOOK FOR A SURPRISE IN THE GLISTERING BIOME:D")); 
		}
    	event.drops.clear(); //remove the old
		event.drops.addAll(drps);
		}
		
		if ((event.harvester != null && !event.harvester.worldObj.isRemote && b == BiomeRegistry.biomeGlistre && event.state.getBlock() == Blocks.leaves)) {
			 EntityPlayer player = event.harvester;
			 ItemStack stack1 = new ItemStack(Items.golden_apple, 1, 1);
			 ArrayList<ItemStack> drps1 = new ArrayList<ItemStack>();
		    	for (ItemStack is1:event.drops) {

			 event.dropChance = 0.2F;
	    				drps1.add(new ItemStack(Items.golden_apple, is1.stackSize, 1)); //add the new item //is.stackSize = # , third parameter =1 enchanted
			 stack1.setStackDisplayName(EnumChatFormatting.GOLD + "Enchanted Golden Apple! :D");
			 player.addChatMessage(
						new ChatComponentText(EnumChatFormatting.GOLD + "LOOK FOR A SURPRISE IN THE GLISTERING BIOME :D" ));	 
		    	}
		    	event.drops.clear(); //remove the old
				event.drops.addAll(drps1);
		}
		

	    if ((b == BiomeRegistry.biomeGlistre && (event.state.getBlock() == Blocks.leaves ) )) {	
	    	ArrayList<ItemStack> drps2 = new ArrayList<ItemStack>();
	    	for (ItemStack is2:event.drops) {
	    			if (is2.getItem() == Items.apple )	{
	    				event.dropChance = (float)1.0;// Change to e.g. 1.0f, if you manipulate the list and want to guarantee it always drops
	    				drps2.add(new ItemStack(Items.golden_apple, is2.stackSize, 0)); //add the new item //is.stackSize = # , third parameter =1 enchanted
	    				is2.setStackDisplayName(EnumChatFormatting.GOLD + "Golden Apple! :D");
	    			}
	    			else {
	    				drps2.add(is2);//add any other items that dropped, but which we're not replacing
	    			}
	    		}
	    	event.drops.clear(); //remove the old
			event.drops.addAll(drps2);
	    	}
		}

//	worldObj.getChunkFromBlockCoords(blockpos).getBiome(blockpos, worldObj.getWorldChunkManager()).biomeName	
	// (event.player.worldObj.provider.dimensionId == 0)
	@SubscribeEvent
	public void thunderStorm (PlayerTickEvent event){
	BiomeGenBase b = BiomeRegistry.biomeFreon;
	Random rand = new Random(); 
	int varX=rand.nextInt(25); 
	int varZ=rand.nextInt(25);
	int spawnchance=rand.nextInt(150);

	if (spawnchance == 0){	
	
		if (!event.player.worldObj.isRemote )
			// DEBUG
		//	 System.out.println("Player Tick for: " + event.side );

		{ 
				// DEBUG
		//		System.out.println("Getting biome coords" + event.player.worldObj.getBiomeGenForCoords(event.player.getPlayerCoordinates().posX, event.player.getPlayerCoordinates().posZ));
	//		worldObj.getChunkFromBlockCoords(blockpos).getBiome(blockpos, worldObj.getWorldChunkManager()).biomeName

	//added 1.8 update next line				
			BlockPos pos0 = event.player.getPosition();
	//1.7.10 version---	if (event.player.worldObj.getBiomeGenForCoords(event.player.getPlayerCoordinates().posX, event.player.getPlayerCoordinates().posZ) == BiomeRegistry.biomeFreon){
if (event.player.worldObj.getBiomeGenForCoords(pos0) == BiomeRegistry.biomeFreon){

					
							       //1.8 code			if(player.worldObj.getBiomeGenForCoords(new BlockPos(player.posX, player.posY, player.posZ)) == BiomeRegistry.biomeFreon)						
				event.player.worldObj.playSound(event.player.posX, event.player.posY, event.player.posZ, "ambient.weather.thunder", 100.0F, 1.0F, false);
					
				event.player.worldObj.addWeatherEffect(new EntityLightningBolt(event.player.worldObj, event.player.posX + varX, 
						
						event.player.posY, event.player.posZ + varZ));
			}	if (event.player.isPotionActive(Potion.confusion.id) &&
					//1.8 changed next two lines
					event.player.worldObj.getBiomeGenForCoords(pos0) == BiomeRegistry.biomeGlistre 
		//1.7.10 version			event.player.worldObj.getBiomeGenForCoords(event.player.getPlayerCoordinates().posX, event.player.getPlayerCoordinates().posZ) == BiomeRegistry.biomeGlistre 
//1.7.10 version					|| event.player.worldObj.getBiomeGenForCoords(event.player.getPlayerCoordinates().posX, event.player.getPlayerCoordinates().posZ) == BiomeRegistry.biomeFreon){  
				|| event.player.worldObj.getBiomeGenForCoords(pos0) == BiomeRegistry.biomeFreon){  

					event.player.worldObj.playSound(event.player.posX, event.player.posY, event.player.posZ, "ambient.weather.thunder", 100.0F, 1.0F, false);
					
					event.player.worldObj.addWeatherEffect(new EntityLightningBolt(event.player.worldObj, event.player.posX + varX, 
							event.player.posY, event.player.posZ + varZ));	
			}
		  }
	   }
	}
	//BiomeGenBase biome = player.worldObj.getBiomeGenForCoords(player.getPlayerCoordinates().posX, player.getPlayerCoordinates().posZ); 
//	if (event.player.worldObj.getBiomeGenForCoords(event.player.getPlayerCoordinates().posX, event.player.getPlayerCoordinates().posZ) == BiomeRegistry.biomeFreon){
		//	try y coord==	event.player.worldObj.getPrecipitationHeight(var1, var2);	
//		System.out.println("Getting biome coords" + event.player.worldObj.getBiomeGenForCoords(event.player.getPlayerCoordinates().posX, event.player.getPlayerCoordinates().posZ));

		//	       System.out.println("Getting biome coords" + event.player.worldObj.getBiomeGenForCoords(event.player.chunkCoordX, event.player.chunkCoordZ).biomeName.equals("Freon Biome"));

//		if(event.player.worldObj.getBiomeGenForCoords(event.player.chunkCoordX, event.player.chunkCoordZ).biomeName.equals("Freon Biome")){	

	
//par2World.playSoundAtEntity(player, "ambient.weather.thunder", 0.3F, 0.1F);	
/*	@SubscribeEvent
	public void onPlayer(PlayerTickEvent event) {
//||  event.player.worldObj.provider.dimensionId == 0 
//		&& event.player.worldObj.getBiomeGenForCoords(event.player.chunkCoordX, event.player.chunkCoordZ).biomeName.equals("Freon Biome"))
		    if ( event.player.worldObj.provider.dimensionId == 8 ) {
			Random rand = new Random();
			int addX = rand.nextInt(25);
			int addZ = rand.nextInt(25);
			if (rand.nextInt(2) == 1)
				addX = -addX;
			if (rand.nextInt(2) == 1)
				addZ = -addZ;
			int lightingSpawnChance = rand.nextInt(50);
			
			if (lightingSpawnChance == 10)
				event.player.worldObj.addWeatherEffect(new EntityLightningBolt(
						event.player.worldObj, event.player.posX + addX,
						event.player.posY, event.player.posZ + addZ));		
		}	
	}*/
	
//		event.player.worldObj.addWeatherEffect(new EntityLightningBolt(event.player.worldObj, event.player.posX + item1, event.player.posY, event.player.posZ + item2));
//		event.player.worldObj.getPrecipitationHeight(item1, item2);

/*	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			System.out.println("Config changed!");
		    if(event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
		       GlistreMod.config.hasChanged();{
//		    	   GlistreMod.config.load();
		    	   ConfigurationGlistre.loadConfig();
					GlistreMod.config.save();
				}
		    }
	}*/

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event){
		if(event.modID.equals(Reference.MOD_ID)){
			
		ConfigurationGlistre.syncConfig();
			//resync configs this is where restart would or not be required	
			System.out.println("Config changed!");
		}
		//resync configs this is where restart would or not be required	
			
	}
/*}	
		if (b == BiomeRegistry.biomeGlistre 
				&& (event.pickedUp.getEntityItem().equals(new ItemStack(ItemRegistry.MightySword).hasTagCompound() &&
						event.pickedUp.getEntityItem().equals(new ItemStack(ItemRegistry.MightySword).getTagCompound().getBoolean("dropped") == true*/

	@SubscribeEvent
	public void onPickup(PlayerEvent.ItemPickupEvent event){
		BiomeGenBase b = BiomeRegistry.biomeGlistre;
		BiomeGenBase b1 = BiomeGenBase.desert;
		BiomeGenBase b2 = BiomeGenBase.savanna;
		BiomeGenBase b3 = BiomeGenBase.savannaPlateau;
		BiomeGenBase b4 = BiomeGenBase.mesa;
		World world = event.player.worldObj;

		if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(BlockRegistry.silver_ore_1))){

			event.player.addStat(GlistreMod.blockAchievement_1, 1);	

			EntityPlayer player = (EntityPlayer) event.player;
			player.addExperience(3);
		
			event.player.addChatMessage(new ChatComponentText("You just harvested Silver Ore!"));
		}
		if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(Items.golden_apple, 1, 1))){
		EntityPlayer player = (EntityPlayer)event.player;
		 player.addChatMessage(
					new ChatComponentText(EnumChatFormatting.GREEN + "YOU FOUND IT >>>> A GOD APPLE!!! :D"));
		}
		
		final String KEY = "activated"; // make sure not to repeat literals, store it in a variable like this
		ItemStack playerPickedUp = event.pickedUp.getEntityItem();
		if(playerPickedUp.getItem() == ItemRegistry.mighty_sword && playerPickedUp.getTagCompound() != null 
				&& playerPickedUp.getTagCompound().hasKey(KEY) && !playerPickedUp.getTagCompound().getBoolean(KEY)){
			
		EntityPlayer player = (EntityPlayer) event.player;
		
		if  (player != null && player.worldObj.provider.getDimensionId() == 0 || player.worldObj.provider.getDimensionId() == Defaults.DIM_ID.GLISTRE || player.dimension != -1 || player.dimension != 1 && !event.player.worldObj.isRemote 
				&& event.pickedUp.getEntityItem().isItemEqual(new ItemStack(ItemRegistry.mighty_sword))){
		
			if (!(event.player.worldObj.isRemote) && !(event.player.worldObj.getWorldInfo().isRaining() 
				&& event.pickedUp.getEntityItem().equals(new ItemStack(ItemRegistry.mighty_sword)))){
    

					
				WorldServer worldserver = MinecraftServer.getServer().worldServers[0];
				//World world = Minecraft.getMinecraft().theWorld;  //this is client side world
				WorldInfo worldinfo = worldserver.getWorldInfo();
				//first parameter is temp .2F and rain looks like snow, second parameter is rainfall 0F none .5F is normal)		
					b.setTemperatureRainfall(0.0F, 1.0F);
					b1.setTemperatureRainfall(0.0F, 1.0F);
					b2.setTemperatureRainfall(0.0F, 1.0F);
					b3.setTemperatureRainfall(0.0F, 1.0F);
					b4.setTemperatureRainfall(0.0F,  1.0F);
					event.player.worldObj.getRainStrength(500.0F);
					event.player.worldObj.setRainStrength(500.0F);
					event.player.worldObj.getWorldInfo().setRainTime(0);
					event.player.worldObj.getWorldInfo().setThunderTime(0);
					b.setEnableSnow();	
					b1.setEnableSnow();
					b2.setEnableSnow();
					b3.setEnableSnow();
					b4.setEnableSnow();
					
					//debug
					System.out.println("rain=" + player.worldObj.getWorldInfo().isRaining());
					
					worldinfo.setRaining(true);
					worldinfo.setThundering(true);
					event.player.worldObj.getWorldInfo().setRaining(true);
					//debug
					System.out.println("rain=" + player.worldObj.getWorldInfo().isRaining());
					
					
					event.player.worldObj.playSound(event.player.posX, event.player.posY, event.player.posZ, "ambient.weather.thunder", 100.0F, 1.0F, false);
					event.player.worldObj.addWeatherEffect(new EntityLightningBolt(event.player.worldObj, event.player.posX , 		
							event.player.posY, event.player.posZ ));

					player.addPotionEffect(new PotionEffect(Potion.blindness.id, 2500, 0));					
					PotionEffect vomitus = (new PotionEffect(Potion.confusion.id, 40000, 4));
					vomitus.getCurativeItems().clear();				
					player.addPotionEffect(vomitus); 
					//player.addPotionEffect(new PotionEffect(ItemRegistry.vomitusPotion.id, 100, 1000, true));
	
						}
						
						event.player.addChatMessage(
								new ChatComponentText(EnumChatFormatting.DARK_RED + "DESTRUCTION OF THE BIOMES HAVE BEGUN!!"));	
						event.player.addChatMessage(
								new ChatComponentText(EnumChatFormatting.DARK_AQUA+ "YOU MUST KILL THE TOBIE KING TO SAVE THIS WORLD!!"));	
						event.player.addChatMessage(
								new ChatComponentText(EnumChatFormatting.DARK_AQUA +  "BEGIN FREEZING WORLD!!"));
//						ICommandManager server = MinecraftServer.getServer().getCommandManager();
//						server.executeCommand(player, "/op" + player);
						
//						server.executeCommand(player, "/weather rain 40000");
//						server.executeCommand(player, "/deop" + player);
						event.player.worldObj.setWorldTime(12000);
						
						playerPickedUp.getTagCompound().removeTag(KEY);
							
						}
		}
	}	

	@SubscribeEvent
	public void onCrafting(PlayerEvent.ItemSmeltedEvent event){
		 if(event.smelting.getItem() == ItemRegistry.mighty_ice_sword){
			 
			 EntityPlayer player = (EntityPlayer)event.player;
			
			 if((!event.player.worldObj.isRemote) && (event.player.worldObj.getWorldInfo().isRaining())){
					 event.player.worldObj.getWorldInfo().setRaining(false);
					 event.player.worldObj.getWorldInfo().setThundering(false);
					 event.player.worldObj.setWorldTime(2000);
					 ICommandManager server = MinecraftServer.getServer().getCommandManager();
					 	server.executeCommand(player, "/op" + player);
						server.executeCommand(player, "/weather clear");
						server.executeCommand(player, "/deop" + player);
						
			 }
		 }
		//	 event.player.triggerAchievement(achievementCuredIceSword);)
			 
	}
		 
	@SubscribeEvent
	public void onPlayerJoin(PlayerLoggedInEvent event)
	{
		if (!event.player.worldObj.isRemote)
		{
			event.player.inventory.addItemStackToInventory(ItemRegistry.recipe_book);
			event.player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Have fun with GlistreMod!"));
			event.player.addChatMessage(
					new ChatComponentText(EnumChatFormatting.GOLD + "You have a Recipe Book!"));
		}
	}
	
	@SubscribeEvent
	public void playerInteractEvent(PlayerInteractEvent event) {
		if(event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
			//Player right click on a block
			if(!event.entityPlayer.isSneaking()) {
				//Player is not sneaking
				
				
				TileEntity tileentity = event.world.getTileEntity(event.pos);
				
				if (!event.world.isRemote && tileentity instanceof TileEntityGlistreChest) {
					World world = event.entity.worldObj;
			        	//spawn corrupted Tobies 
			        	EntityBlackTobo entity = new EntityBlackTobo(world);
			        	EntityBlackTobo entity1 = new EntityBlackTobo(world);
			        	EntityBlackTobo entity2 = new EntityBlackTobo(world);

			        	EntityPlayer player = event.entityPlayer;
			        	entity.copyLocationAndAnglesFrom(player);
			    		entity.setPosition(event.entityPlayer.posX - 3, event.entityPlayer.posY + 3, event.entityPlayer.posZ - 3);    		
			        	entity1.copyLocationAndAnglesFrom(player);
			    		entity1.setPosition(event.entityPlayer.posX - 5, event.entityPlayer.posY + 3, event.entityPlayer.posZ - 5);    		
			        	entity2.copyLocationAndAnglesFrom(player);
			    		entity2.setPosition(event.entityPlayer.posX - 7, event.entityPlayer.posY + 3, event.entityPlayer.posZ - 7);    		

			    		
			    		
			    		
			    		//entity.setLocationAndAngles(i, j, k, yaw, pitch)
			    		//entity.setLocationAndAngles(event.x + 2, event.y + 1, event.z + 11, 0.0F , 0.0F);		    		
			    		
			    			 world.spawnEntityInWorld(entity);
			    			 world.spawnEntityInWorld(entity1);
			    			 world.spawnEntityInWorld(entity2);

			    		
//			    		Minecraft.getMinecraft().thePlayer.addChatMessage(
//			    	    		new ChatComponentText(EnumChatFormatting.DARK_GREEN + "Chest spawned Toby King at location X: " + entity.posX + "|Y: " + entity.posY + EnumChatFormatting.DARK_GREEN + "|Z: " + entity.posZ));
			    		//DEBUG this below just tells me if its generating or not 
			         //   System.out.println("Generating Toby King in Freon Biome");
		//			FMLClientHandler.instance().displayGuiScreen(event.entityPlayer, new GuiChest(event.entityPlayer.inventory, (IInventory) tileentity));
			
			            //makes chest not open when right clicked
			            //	event.setCanceled(true);
					
				}
			}
		}
	}
/*	@SubscribeEvent
	public void onEntitySpawnsTobieKing(LivingSpawnEvent event) {
		if (event.entity instanceof EntityTobieKing)  {
        	NBTTagCompound nbt = event.entity.getEntityData();
        //	NBTTagCompound nbt = new NBTTagCompound();
        	nbt.setBoolean("spawned", true);
			if(nbt.hasKey("spawned")){
				
		List<EntityPlayer> playerList = event.entity.worldObj.getEntitiesWithinAABB(EntityPlayer.class, event.entity.getEntityBoundingBox().expand(35.0D, 15.0D, 15.0D));
		Iterator<EntityPlayer> i1 = playerList.iterator();
		EntityPlayer player;

              while (i1.hasNext())
              {
            	     player = (EntityPlayer)i1.next();	

		        			player.addChatComponentMessage(
                    		new ChatComponentText(EnumChatFormatting.DARK_GREEN + "Toby King in Range, location" 
                    + EnumChatFormatting.DARK_RED + " X: " + (int)Math.round(event.entity.posX) 
                    + EnumChatFormatting.GOLD + " | Y: " + (int)Math.round(event.entity.posY) 
                    + EnumChatFormatting.DARK_AQUA +" | Z: " + (int)Math.round(event.entity.posZ)));
				}
              }
              }
		}*/
	
	@SubscribeEvent
	public void onEntityTobieQueenDeath(LivingDeathEvent event){
		if (!event.entityLiving.worldObj.isRemote ){
		if(!(event.entityLiving instanceof EntityTobieQueen)){
			return;
		}
		//TobieQueenElizabeth
		EntityTobieQueen queen = (EntityTobieQueen) event.entityLiving;
		if (event.entityLiving instanceof EntityTobieQueen){
		//	event.entityLiving.worldObj.setWorldTime(2000); 
			World world = event.entityLiving.worldObj;
		
			DamageSource source = event.source;
//			EntityTobieQueen queen = (EntityTobieQueen) event.entityLiving;		
			if (source.getEntity() instanceof EntityPlayer) {
				EntityPlayer sourcePlayer = (EntityPlayer) source.getEntity();
				sourcePlayer.addStat(GlistreMod.mobKillAchievement_1, 1);	
				//System.out.println("Tobie Queen Elizabeth "+entityLiving.getName()+" died. Entity on "+(event.entity.worldObj.isRemote ? "client" : "server") + " world.");

				sourcePlayer.addChatMessage(
						new ChatComponentText(EnumChatFormatting.GOLD + "YOU KILLED THE EVIL TOBIE QUEEN ELIZABETH!!!!"));
				sourcePlayer.addPotionEffect(new PotionEffect(Potion.heal.id, 2500, 0));
				sourcePlayer.addPotionEffect(new PotionEffect(ItemRegistry.poison_protect_potion.id, 3000, 0, false, false));			
				sourcePlayer.addPotionEffect(new PotionEffect(ItemRegistry.nausea_protect_potion.id, 3000, 0, false, false));			
				sourcePlayer.getActivePotionEffects();
				sourcePlayer.removePotionEffect(Potion.confusion.id);			
				sourcePlayer.addExperience(80000);
				
				sourcePlayer.addChatMessage(
						new ChatComponentText(EnumChatFormatting.DARK_GREEN + "CURE THE MIGHTY ICE SWORD!!"));
				sourcePlayer.addChatMessage(
						new ChatComponentText(EnumChatFormatting.DARK_GREEN + "HINT: USE THE SECRET RECIPE!"));
				}
			    
			}
		}
			
	}	
/*	@SubscribeEvent
	public void onEntityBlackToboDeath(LivingDeathEvent event){
		if (!(event.entityLiving.worldObj.isRemote) &&
		!(event.entityLiving instanceof EntityBlackTobo)){
			return;
		}
		World world = event.entityLiving.worldObj;
		DamageSource source = event.source;
		
		if (!(world.isRemote) && (event.entityLiving instanceof EntityBlackTobo && !(world.getWorldInfo().isRaining()))){
			EntityPlayer sourcePlayer = (EntityPlayer) source.getEntity();
		//Corrupted Tobie
		EntityBlackTobo blackTobo = (EntityBlackTobo)event.entityLiving;
		//WorldInfo worldinfo = worldserver.getWorldInfo();
		
		//b.setTemperatureRainfall(0.0F, 1.0F);
		world.setRainStrength(500.0F);
		world.getWorldInfo().setRainTime(0);
		world.getWorldInfo().setRaining(true);
		}
		}*/
		
		
		@SubscribeEvent
		public void onEntityTobieKingDeath(LivingDeathEvent event){
			if (!event.entityLiving.worldObj.isRemote ){
			if(!(event.entityLiving instanceof EntityTobieKing)){
				return;
			}
	
			//Tobie King
			EntityTobieKing king = (EntityTobieKing)event.entityLiving;
			
					World world = event.entity.worldObj;
					//spawn Tobie Queen Elizabeth on place of Tobie King
					EntityTobieQueen queen = new EntityTobieQueen(world); 
					queen.copyLocationAndAnglesFrom(king);
					world.spawnEntityInWorld(queen);		
			//		System.out.println("Tobie King "+entityLiving.getName()+" died. Entity on "+(event.entity.worldObj.isRemote ? "client" : "server") + " world.");
		    		Minecraft.getMinecraft().thePlayer.addChatMessage(
    	    		new ChatComponentText(EnumChatFormatting.DARK_RED + "YOU HAVE AWAKENED TOBY QUEEN ELIZABETH!!!!!"));

					Random rand = new Random();
					for(int countparticles = 0; countparticles <= 80; ++countparticles)
					{
						//not sure about 1.8 update substituted BlockPos
						
						world.spawnParticle(EnumParticleTypes.REDSTONE, king.posX + (rand.nextDouble() - 0.5D) * (double)king.width, king.posY + rand.nextDouble() * (double)king.height - (double)king.getYOffset(), king.posZ + (rand.nextDouble() - 0.5D) * (double)king.width, 255, 215, 0);
					}		
			}
		}
		

/*		@SubscribeEvent
		@SideOnly(Side.CLIENT)
		public void onFogColors(FogColors event) {
			RudBlock block = RudBlock.atEyeLevel(event.entity);
			if (block != null) {
				int color = block.getFluid().getColor();
				event.red = (color >> 16 & 255) / 255.0F;
				event.green = (color >> 8 & 255) / 255.0F;
		        event.blue = (color & 255) / 255.0F;
			}
		}*/
	}


	
