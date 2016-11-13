package com.glistre.glistremod.tileentity;

import com.glistre.glistremod.blocks.TileEntityGlistreChest;
import com.glistre.glistremod.reference.Reference;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class GMTileEntityRegistry {

	public static void GlistreMod(){
//		initializeTileEntity();
		registerTileEntity();
	}	

//	public static void initializeTileEntity(){
		
//	}
	
	public static void registerTileEntity(){
		GameRegistry.registerTileEntity(TileEntityGlistreChest.class, Reference.MOD_ID);
	}
	
}