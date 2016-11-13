package com.glistre.glistremod.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.DimensionManager;

import java.util.Arrays;

import com.glistre.glistremod.init.BiomeRegistry;
import com.glistre.glistremod.lib.Defaults;

//import com.glistre.glistremod.world.GlistreWorldChunkManager;

public class WorldProviderGlistre extends WorldProvider{

//	public World worldObj;
	private float rainfall;
	
@Override
	public void registerWorldChunkManager(){
//		this.worldChunkMgr = new GlistreWorldChunkManager(this.worldObj);
		this.worldChunkMgr = new WorldChunkManagerHell(BiomeRegistry.biomeGlistre, 1.0F);
		
//		this.isHellWorld = false;
//		this.worldChunkMgr.getRainfall(1.0F,  x, z, width, length);
//		this.worldChunkMgr = new WorldChunkManagerHell(BiomeRegistry.biomeFreon, .25F);
//		worldObj.provider.dimensionId = Defaults.DIM_ID.GLISTRE;
//		this.hasNoSky = false;
//		this.dimensionId = DimensionRegistry.dimensionId;
		this.dimensionId = Defaults.DIM_ID.GLISTRE;
		this.rainfall = 1.0F;
		this.hasNoSky = false;
		

	}
@Override
public void resetRainAndThunder(){
	worldObj.getWorldInfo().setRainTime(0);
	worldObj.getWorldInfo().setRaining(true);
	worldObj.getWorldInfo().setThunderTime(0);
	worldObj.getWorldInfo().setThundering(false);
}
/*
@Override
	public boolean getHasNoSky {
	return false;
}*/
public static WorldProvider getProviderForDimension(int id)
{
	return DimensionManager.createProviderFor(Defaults.DIM_ID.GLISTRE);
}

@Override
	public boolean isSurfaceWorld() {
		return true;
}

@Override	
	public boolean canRespawnHere() { 
		return true; 
	}
/*
@Override	
	public boolean canDoRainSnowIce(Chunk chunk) {
		return true;
	}
	
	public IChunkProvider createChunkGeneration(){
		return null;
	}
@Override    
	public boolean canSnowAt(int x, int y, int z, boolean checkLight)
    {
        return worldObj.canSnowAtBody(x, y, z, false);
    }
@Override
    public boolean canDoLightning(Chunk chunk)
    {
        return true;
    }
@Override 
public void resetRainAndThunder()
{
    worldObj.getWorldInfo().setRainTime(0);
    worldObj.getWorldInfo().setRaining(true);
    worldObj.getWorldInfo().setThunderTime(0);
    worldObj.getWorldInfo().setThundering(true);
}*/
@Override
    public String getWelcomeMessage() {
    
	if (this instanceof WorldProviderGlistre){
		return "Entering the Glistering Biome!";
	}
	else {
		return null;
		
    }
	}
	@Override
	public String getDimensionName() {
		
		return "Glistre";
	}
	@Override
	public String getInternalNameSuffix() {
		
		return "Glistre";
	}

}
