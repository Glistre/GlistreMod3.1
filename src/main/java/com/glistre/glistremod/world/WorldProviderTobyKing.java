package com.glistre.glistremod.world;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.DimensionManager;

import com.glistre.glistremod.init.BiomeRegistry;
import com.glistre.glistremod.init.DimensionRegistry;
import com.glistre.glistremod.lib.Defaults;
//import com.glistre.glistremod.world.GlistreWorldChunkManager;

public class WorldProviderTobyKing extends WorldProvider{



	public void registerWorldChunkManager(){
//		this.worldChunkMgr = new WorldChunkManagerHell(BiomeRegistry.biomeGlistre, .25F);
		this.worldChunkMgr = new WorldChunkManagerHell(BiomeRegistry.biomeFreon, 1.0F);
//		worldObj.provider. = Defaults.DIM_ID.FREON;
//		this.dimensionId = DimensionRegistry.dimensionId;
//		this.dimensionId = 8;
		dimensionId = Defaults.DIM_ID.FREON;
	}
	
	public static WorldProvider getProviderForDimension(int id)
	{
		return DimensionManager.createProviderFor(Defaults.DIM_ID.FREON);
	}
	
	public boolean canRespawnHere() { 
		return true; 
	}
	
	public IChunkProvider createChunkGeneration(){
		return null;
	}
	
	@Override
	public String getDimensionName() {
		
		return "Freon";
	}

	@Override
	public String getInternalNameSuffix() {
		
		return "Freon";
	}

}
