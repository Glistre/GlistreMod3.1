package com.glistre.glistremod.effects.potions;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.glistre.glistremod.GlistreMod;
import com.glistre.glistremod.init.ItemRegistry;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
//import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class NauseaProtectEffect extends Potion {
//substring goes over to the right how ever many you specify, 
//and sets that as the new start. so 1 in this case... AKA if you don't put the slash at the beginning 
//and just make it modid + "textures/folder/icon.png" substring isn't required... or it may even require a colon to specify the domain so modid + ":" + "textures/folder/icon.png"﻿	

	public static final ResourceLocation icon = new ResourceLocation("glistremod:textures/gui/nausea_protect_potion_icon.png");

	
	/** ID value of the potion this effect matches. */
    private int potionID;
    /** The duration of the potion effect */
    private int duration;
    /** The amplifier of the potion effect */
    private int amplifier;

    
    private List<ItemStack> curativeItems;
    
    private int width;
    private int height;
    private int u;
    private int v;

    
    
	public NauseaProtectEffect(int potionID, boolean bad, int potionColor) {
		super(potionID, null, bad, potionColor);

	}

	
//this will put Minecraft Icons for potions  use renderInventoryEffect don't use this
/*	@Override
	public Potion setIconIndex(int x, int y) {
		super.setIconIndex(x, y);
		return this;
		}*/


	@SideOnly(Side.CLIENT)
	@Override
	public boolean hasStatusIcon() 
	{
//	    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("glistremod", "textures/gui/potionicon.png"));
	    return false;
	}
	
	@Override
	public void performEffect(EntityLivingBase entity, int strength) {

		super.performEffect(entity, strength);
		
		entity.removePotionEffect(Potion.confusion.id);
	}		

	@Override
	public boolean isReady(int ticksLeft, int amplifier){ 
		int k = (5 >> amplifier);
		return k < 1 || ticksLeft % k == 0;

	}


	@Override
	@SideOnly(Side.CLIENT)
	public void renderInventoryEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc){
		ITextureObject texture = Minecraft.getMinecraft().renderEngine.getTexture(icon);

		if (effect.getPotionID() == 30){
	
			GL11.glEnable(GL11.GL_BLEND);

			 mc.getTextureManager().bindTexture(icon);

			

		mc.currentScreen.drawTexturedModalRect(x + 5, y + 6, u + 9, v + 9, 18, 18);




		GL11.glDisable(GL11.GL_BLEND);

		}

}

	@Override
	@SideOnly(Side.CLIENT)
	public int getStatusIconIndex(){
		ITextureObject texture = Minecraft.getMinecraft().renderEngine.getTexture(icon);
		Minecraft.getMinecraft().renderEngine.bindTexture(icon);
		
		return super.getStatusIconIndex();
		

	}
	
	public boolean isBadEffect(){
		
		return false;
	}


	public int getPotionID() {
		return this.potionID;
	}
	

}
