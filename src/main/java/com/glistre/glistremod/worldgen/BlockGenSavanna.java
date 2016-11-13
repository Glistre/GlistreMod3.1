package com.glistre.glistremod.worldgen;

import java.util.Random;

import com.glistre.glistremod.init.BiomeRegistry;
import com.glistre.glistremod.init.BlockRegistry;
import com.glistre.glistremod.lib.ConfigurationGlistre;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenSavanna;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;



public class BlockGenSavanna implements net.minecraftforge.fml.common.IWorldGenerator {

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
    
    private void generateSurface(Random random, int chunkX, int chunkZ, World worldIn, IChunkProvider chunkGenerator, IChunkProvider chunkProvider ) {
    	if(!ConfigurationGlistre.enableWorldGeneration){
    	for(int i =0; i<20; i++){ 
    		BiomeGenBase b = worldIn.getBiomeGenForCoords(new BlockPos(chunkX, 0, chunkZ));   		
            int firstBlockXcoord = chunkX + random.nextInt(16);
            int firstBlockZcoord = chunkZ + random.nextInt(16);
            int OreY = random.nextInt(256); 
            int Ycoord = (worldIn.getHeight() - 40) + 40;   
            BlockPos YcoordPos = new BlockPos(firstBlockXcoord, Ycoord, firstBlockZcoord);
            BlockPos pos0 = new BlockPos(firstBlockXcoord, OreY, firstBlockZcoord);
        	if (b == BiomeGenBase.savanna || b == BiomeGenBase.savannaPlateau)
         		
                    // WorldGenMinable(IBlockState, int, Predicate)
                    (new WorldGenMinable(BlockRegistry.glistre_block_1.getDefaultState(), 22)).generate(worldIn, random, pos0);
    	}
    	

           //this below just tells me if its generating or not
    //         System.out.println("Generating Glistre Blocks in Savanna and Savanna Plateau");
         		
                   
    	}
    }
}


    

   
    

