package com.glistre.glistremod.effects;

import java.util.Random;

import com.glistre.glistremod.blocks.RudBlock;
import com.glistre.glistremod.blocks.fluids.ModFluids;
import com.glistre.glistremod.init.ItemRegistry;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fluids.BlockFluidBase;


public class GlistreModEventHooks {

	/*	static boolean hasAlreadyOpened = true;
	//GuiModInfo extends GuiScreen
	@SubscribeEvent
	public void onGuiOpened(GuiOpenEvent event){
		if(event.gui instanceof GuiContainer){
			if (!hasAlreadyOpened){
				event.gui = new GuiScreen();
				hasAlreadyOpened = false;
			}
		}
	}*/
/*	@SubscribeEvent
	public void eventHandler(RenderGameOverlayEvent event){
		if (event.isCancelable()|| event.type != ElementType.ALL)
		{
		return;
	}
	}*/


	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event) {
	if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer thePlayer = (EntityPlayer)event.entityLiving;{

	if (event.entityLiving.isPotionActive(ItemRegistry.poison_protect_potion)) {
	if (event.entityLiving.getActivePotionEffect(ItemRegistry.poison_protect_potion).getDuration()==0) 
	{
		event.entityLiving.removePotionEffect(ItemRegistry.poison_protect_potion.id);
	}
/*	if (event.entityLiving.isPotionActive(ItemRegistry.nauseaProtectPotion)) {
		if (event.entityLiving.getActivePotionEffect(ItemRegistry.nauseaProtectPotion).getDuration()==0) 
		{
			event.entityLiving.removePotionEffect(ItemRegistry.nauseaProtectPotion.id);
		}*/
/*	if (event.entityLiving.isPotionActive(ItemRegistry.vomitusPotion)) {
		if (event.entityLiving.getActivePotionEffect(ItemRegistry.vomitusPotion).getDuration()==0) 
		{
			event.entityLiving.removePotionEffect(ItemRegistry.vomitusPotion.id);
		}
	}*/
	else if (event.entityLiving.worldObj.rand.nextInt(20) == 0)
	
//	{	event.entityLiving.attackEntityFrom(DamageSource.generic, -4);
	{	event.entityLiving.heal(4.0F);


	 return;
	}
	}
	}
	}
	}

	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void playerSwim(EntityViewRenderEvent.FogColors event)
	{
		EntityPlayer player = (EntityPlayer)event.entity;
		//Entity entity = event.entity;
		World world = player.getEntityWorld();
		
		BlockPos pos = player.getPosition();
		IBlockState blockstate = world.getBlockState(pos);
		Block block = blockstate.getBlock();
//		System.out.println("Colored");


		if (block.equals(ModFluids.rud.getBlock()))//changes color of fog
		{		
		//	int red = 80, green = 13, blue = 147;

			int color = ((RudBlock) block).getFluid().getColor();
			event.red = (color >> 56 & 255) / 255.0F;//not sure how the heck this adjusts color exactly
			event.green = (color >> 8 & 255) / 255.0F;
	        event.blue = (color & 255) / 255.0F;
/*			event.red = (255F); 
			event.green = (0F); 
			event.blue = (0F);*/
//			System.out.println("Reddish Colored");
		}
		
/*		else{
			
			event.red = (0F); 
			event.green = (0F); 
			event.blue = (0F);
			System.out.println("Black");
		}*/
	
	}	
	
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void playerSwim(FogDensity e)
	{
		EntityPlayer player = (EntityPlayer)e.entity;
		World w = player.getEntityWorld();
		BlockPos pos = player.getPosition();
		IBlockState bs = w.getBlockState(pos);
		Block b = bs.getBlock();
		if(b.equals(ModFluids.rud.getBlock()))
		{
			float alpha = 34.0F; 
			e.density= (float) (alpha/100.0F);
//			System.out.println("Fogged");
		
		}else 
	
		 e.density = (float) 0.0F;  //turns off fog when not in Rudd

    e.setCanceled(true); // must be canceled to affect the fog density 
	}


}
//	@SubscribeEvent
//	MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(currentWorld, randomGenerator, chunk_X, chunk_Z));

	

