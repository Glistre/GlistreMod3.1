package com.glistre.glistremod.lib;

import com.glistre.glistremod.GlistreMod;
import com.glistre.glistremod.reference.Reference;

import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class GlistreConfigGui extends GuiConfig{
	
	public GlistreConfigGui(GuiScreen screen){
		super(screen, new ConfigElement(GlistreMod.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), 
				Reference.MOD_ID, 
				false, 
				false, 
//				GuiConfig.getAbridgedConfigPath(GlistreMod.config.toString()));
				(EnumChatFormatting.GOLD + "Play Glistre Mod Any Way You Want or Fix ID Conflicts"));
        titleLine2 = GuiConfig.getAbridgedConfigPath(GlistreMod.config.toString());
	}

}
