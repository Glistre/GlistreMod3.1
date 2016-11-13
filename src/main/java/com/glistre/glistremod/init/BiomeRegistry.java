package com.glistre.glistremod.init;

import com.glistre.glistremod.biome.FreonBiome;
import com.glistre.glistremod.biome.GlistreBiome;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.BiomeDictionary.Type;



public class BiomeRegistry {

	public static void GlistreMod(){
		initializeBiome();
		registerBiome();
	}
  
	public static BiomeGenBase biomeGlistre;
	public static BiomeGenBase biomeFreon;
	public static int biomeGlistreID = 155;
	public static int biomeFreonID = 154;
	
	public static void initializeBiome(){
	
// 1.7.10 valid biome IDs use 0-39, 129-167, neg one unallocated		
      biomeGlistre = new GlistreBiome(biomeGlistreID).setBiomeName("Glistering Biome");
      biomeFreon = new FreonBiome(biomeFreonID).setBiomeName("Freon Biome");
   	 

    }	

	public static void registerBiome(){
		BiomeDictionary.registerBiomeType(biomeGlistre, Type.FOREST);
		BiomeDictionary.registerBiomeType(biomeFreon, Type.COLD);
//change the next line to make biome more frequent, 50 is 50% probability of spawning, change to 10 later
		//		BiomeManager.addSpawnBiome(biomeGlistre);
		BiomeEntry glistreBiomeEntry = new BiomeEntry(biomeGlistre, 30);
		BiomeEntry freonBiomeEntry = new BiomeEntry(biomeFreon, 30);
//need to try with .COOL it did not seem to work
		BiomeManager.addBiome(BiomeType.WARM, glistreBiomeEntry);
		BiomeManager.addBiome(BiomeType.WARM, freonBiomeEntry);
	}

}