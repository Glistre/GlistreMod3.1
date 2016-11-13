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

public class PoisonProtectEffect extends Potion {
//substring goes over to the right how ever many you specify, 
//and sets that as the new start. so 1 in this case... AKA if you don't put the slash at the beginning 
//and just make it modid + "textures/folder/icon.png" substring isn't required... or it may even require a colon to specify the domain so modid + ":" + "textures/folder/icon.png"﻿	

	public static final ResourceLocation icon = new ResourceLocation("glistremod:textures/gui/poison_protect_potion_icon.png");

	
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

    
    
	public PoisonProtectEffect(int potionID, boolean bad, int potionColor) {
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
		entity.removePotionEffect(Potion.poison.id);
//		entity.addPotionEffect(new PotionEffect(GlistreMod.poisonProtectPotion.id, 3000, 6));
		
//		entity.addPotionEffect(new PotionEffect(GlistreMod.poisonProtectPotion.id, entity.getActivePotionEffect(GlistreMod.poisonProtectPotion).getDuration(), 6));


	}		

	@Override
	public boolean isReady(int ticksLeft, int amplifier){ 
		int k = (5 >> amplifier);
		return k < 1 || ticksLeft % k == 0;

	}
	
/*	public static int getPotionColor(Collection collection)
	{
	    int waterColor = 0x385dc6;
	    if (collection == null || collection.isEmpty())
	    {
	        return waterColor;
	    }
	    float r = 0.0F;
	    float g = 0.0F;
	    float b = 0.0F;
	    float numColors = 0.0F;
	    for (Iterator iterator = collection.iterator(); iterator.hasNext();)
	    {
	        PotionEffect potioneffect = (PotionEffect)iterator.next();
	        int potionColor = Potion.potionTypes[potioneffect.getPotionID()].getLiquidColor();
	        int i = 0;
	        while (i <= potioneffect.getAmplifier())
	        {
	            r += (float)(potionColor >> 16 & 0xff) / 255F;
	            g += (float)(potionColor >> 8 & 0xff) / 255F;
	            b += (float)(potionColor >> 0 & 0xff) / 255F;
	            numColors++;
	            i++;
	        }
	    }

	    r = (hBit / numColors) * 255F;
	    g = (mBit / numColors) * 255F;
	    b = (lBit / numColors) * 255F;
	    return (int)r << 16 | (int)g << 8 | (int)b;
	}*/

	@Override
	@SideOnly(Side.CLIENT)
	public void renderInventoryEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc){
		ITextureObject texture = Minecraft.getMinecraft().renderEngine.getTexture(icon);

		if (effect.getPotionID() == 31){
	
			GL11.glEnable(GL11.GL_BLEND);

			 mc.getTextureManager().bindTexture(icon);

//		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("glistremod", "textures/gui/inventory.png"));
//		mc.renderEngine.bindTexture(icon);
			

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
