package com.glistre.glistremod.worldgen;

import java.util.Random;

import com.glistre.glistremod.biome.GlistreBiome;
import com.glistre.glistremod.init.BiomeRegistry;
import com.glistre.glistremod.init.BlockRegistry;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class FirstBlockGen implements net.minecraftforge.fml.common.IWorldGenerator {

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

    	for(int i =0; i<20; i++){ 
    		BiomeGenBase b = worldIn.getBiomeGenForCoords(new BlockPos(chunkX, 0, chunkZ));   		
            int firstBlockXcoord = chunkX + random.nextInt(16);
            int firstBlockZcoord = chunkZ + random.nextInt(16);
            int OreY = random.nextInt(256); 
            int Ycoord = (worldIn.getHeight() - 40) + 40;   

            
            BlockPos YcoordPos = new BlockPos(firstBlockXcoord, Ycoord, firstBlockZcoord);
            BlockPos pos0 = new BlockPos(firstBlockXcoord, OreY, firstBlockZcoord);
            if (b == BiomeRegistry.biomeFreon || b == BiomeRegistry.biomeGlistre || b == BiomeGenBase.taiga)
         		
                    // WorldGenMinable(IBlockState, int, Predicate)
                    (new WorldGenMinable(BlockRegistry.silver_ore_1.getDefaultState(), 32)).generate(worldIn, random, pos0);
    	}      
   
//    	if (b.biomeName.equals("Glistering Biome") ||  b.biomeName.equals("Taiga"))
    	for(int i =0; i<10; i++){ 
    		BiomeGenBase b = worldIn.getBiomeGenForCoords(new BlockPos(chunkX, 0, chunkZ));   		
            int firstBlockXcoord = chunkX + random.nextInt(16);
            int firstBlockZcoord = chunkZ + random.nextInt(16);
            int OreY = random.nextInt(256); 
            int Ycoord = (worldIn.getHeight() - 40) + 40;   
  
            BlockPos YcoordPos = new BlockPos(firstBlockXcoord, Ycoord, firstBlockZcoord);
            BlockPos pos0 = new BlockPos(firstBlockXcoord, OreY, firstBlockZcoord);
            if (b == BiomeRegistry.biomeFreon || b == BiomeRegistry.biomeGlistre)         		
                    // WorldGenMinable(IBlockState, int, Predicate)
                    (new WorldGenMinable(BlockRegistry.enchanted_block_1.getDefaultState(), 6)).generate(worldIn, random, pos0);
    	} 
    	
//   creates massive spikes everywhere
   	for(int i =0; i<2; i++){ 
    		BiomeGenBase b = worldIn.getBiomeGenForCoords(new BlockPos(chunkX, 0, chunkZ));   		
            int firstBlockXcoord = chunkX + random.nextInt(16);
            int firstBlockZcoord = chunkZ + random.nextInt(16);
            int OreY = random.nextInt(256); 
            
            int Ycoord = (worldIn.getHeight() - 40) + 40;   
            BlockPos YcoordPos = new BlockPos(firstBlockXcoord, Ycoord, firstBlockZcoord);
            BlockPos pos0 = new BlockPos(firstBlockXcoord, OreY, firstBlockZcoord);
            IBlockState state0 = BlockRegistry.liquid_ice.getDefaultState();
            if (b == BiomeRegistry.biomeFreon)  {       		
                    // WorldGenMinable(IBlockState, int, Predicate)
                if(worldIn.getBlockState(pos0).getBlock() == Blocks.snow  || worldIn.getBlockState(pos0).getBlock() == Blocks.grass || worldIn.getBlockState(pos0).getBlock() == Blocks.sand)
              	worldIn.setBlockState(pos0, state0);
                new WorldGenFreonSpikes().generate(worldIn, random, pos0);  	
//is generating ?
    //            System.out.println("Generating Packed Ice in Freon Biome");		
            }
    	}

        
    }

}   
    

