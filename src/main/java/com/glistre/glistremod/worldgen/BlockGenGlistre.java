package com.glistre.glistremod.worldgen;

import java.util.Random;

import com.glistre.glistremod.init.BiomeRegistry;
import com.glistre.glistremod.init.BlockRegistry;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenHills;
import net.minecraft.world.biome.BiomeGenStoneBeach;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;


public class BlockGenGlistre implements net.minecraftforge.fml.common.IWorldGenerator {

	@Override
    public void generate(Random random, int chunkX, int chunkZ, World worldIn, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch(worldIn.provider.getDimensionId())
        {
        //case -1 = Nether
        
            case -1: 
                generateSurface(random, chunkX*16,chunkZ*16, worldIn, chunkGenerator, chunkProvider);
                break;
        //case 0 = Overworld
            case 0:
            	generateSurface(random, chunkX*16,chunkZ*16, worldIn, chunkGenerator, chunkProvider);
            	break;
        //case 1 = End
            case 1:
            	generateSurface(random, chunkX*16,chunkZ*16, worldIn, chunkGenerator, chunkProvider);
            	break;         	
        //default case e.g. Mystcraft ..others
            default:
            	generateSurface(random, chunkX*16,chunkZ*16, worldIn, chunkGenerator, chunkProvider);
            	break;
        }              
    }
    private void generateSurface(Random random, int chunkX, int chunkZ, World worldIn, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    
    	
    	for(int i =0; i<100; i++){ 
    		BiomeGenBase b = worldIn.getBiomeGenForCoords(new BlockPos(chunkX, 0, chunkZ));   		
            int firstBlockXcoord = chunkX + random.nextInt(16);
            int firstBlockZcoord = chunkZ + random.nextInt(16);
            int OreY = random.nextInt(256); 
            int Ycoord = (worldIn.getHeight() - 40) + 40;   
            BlockPos YcoordPos = new BlockPos(firstBlockXcoord, Ycoord, firstBlockZcoord);
            BlockPos pos0 = new BlockPos(firstBlockXcoord, OreY, firstBlockZcoord);
         	if (b == BiomeRegistry.biomeFreon || b == BiomeGenBase.birchForest || b == BiomeGenBase.birchForestHills)
         		
                    // WorldGenMinable(IBlockState, int, Predicate)
                    (new WorldGenMinable(BlockRegistry.silver_block_1.getDefaultState(), 12)).generate(worldIn, random, pos0);
                  //this below just tells me if its generating or not
               //     System.out.println("Generating Silver Blocks in Freon & Birch Forest Biomes");
         		}
 		for(int i =0; i<10; i++){ 
            BiomeGenBase b = worldIn.getBiomeGenForCoords(new BlockPos(chunkX, 0, chunkZ)); 	
            int firstBlockXcoord = chunkX + random.nextInt(16);
            int firstBlockZcoord = chunkZ + random.nextInt(16);
            int OreY = random.nextInt(256);
            int Ycoord = (worldIn.getHeight() - 40) + 40;   
            BlockPos YcoordPos = new BlockPos(firstBlockXcoord, Ycoord, firstBlockZcoord);         
            BlockPos pos0 = new BlockPos(firstBlockXcoord, OreY, firstBlockZcoord);
          
         	 if (b == BiomeGenBase.extremeHills || b == BiomeGenBase.extremeHillsEdge || b == BiomeGenBase.extremeHillsPlus || b == BiomeGenBase.stoneBeach)
 
                	
//                    int Ycoord = world.getHeightValue(Xcoord, Zcoord);
                    (new WorldGenMinable(BlockRegistry.silver_ore_1.getDefaultState(), 20)).generate(worldIn, random, pos0);
                  //this below just tells me if its generating or not
                //    System.out.println("Generating Silver Ore in ExtremeHills Biome");
                   
    	}
         
    }
    
}




    

   
    

