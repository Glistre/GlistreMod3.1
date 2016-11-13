package com.glistre.glistremod.biome;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CLAY;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS;

import java.util.Random;

import com.glistre.glistremod.init.BlockRegistry;
//import com.glistre.glistremod.blocks.MyBlockGen;
import com.glistre.glistremod.worldgen.BlockGenGlistre;
import com.glistre.glistremod.worldgen.WorldGenSeaweed;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenClay;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BiomeDecoratorGlistre extends BiomeDecorator
{
	 /** The world the BiomeDecorator is currently decorating */
    public World currentWorld;
    /** The Biome Decorator's random number generator. */
    public Random randomGenerator;
    public BlockPos field_180294_c;
    /** The X-coordinate of the chunk currently being decorated */
//    public int chunk_X;
    /** The Z-coordinate of the chunk currently being decorated */
//    public int chunk_Z;
    public int seaweedPerChunk;
    /** The clay/seaweed generator. */
    public int block3PerChunk;
//    public WorldGenerator clayGen = new WorldGenClay(4);
   //added next two lines no idea if will work 
    public WorldGenClay seaweedGen = new WorldGenSeaweed(3);
//    public WorldGenMinable myblock1Gen = new WorldGenMinable(null, chunk_X, null);
    public BlockGenGlistre blockGenGlistre = new BlockGenGlistre();
 
	
	public BiomeDecoratorGlistre()
	{
		super();
//		seaweedPerChunk = new WorldGenSeaweed(16);
		this.seaweedGen = new WorldGenSeaweed(16);
		this.seaweedPerChunk = 200;
		this.blockGenGlistre = new BlockGenGlistre();
		this.block3PerChunk = 200;


	}

	@Override
	public void decorate(World world, Random random, BiomeGenBase biomegenbase, BlockPos blockPos) {
		super.decorate(world, random, biomegenbase, blockPos);
		 if (this.currentWorld != null)
	        {
	            throw new RuntimeException("Already decorating!!");
	        }
	        else
	        {
	            this.currentWorld = world;
	            this.randomGenerator = random;
	            this.field_180294_c = blockPos;
//	            this.chunk_X = blockPos.getX();
//	            this.chunk_Z = blockPos.getZ();
	            this.genDecorations(biomegenbase);
	            this.currentWorld = null;
	            this.randomGenerator = null;
	        }
	}

	@Override
	protected void genDecorations(BiomeGenBase p_150513_1_) {
		super.genDecorations(p_150513_1_);
		MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(currentWorld, randomGenerator, field_180294_c));
        this.generateOres();
        int i;
        int j;
        int k;
        int i1;
        int l;


       boolean doGen = TerrainGen.decorate(currentWorld, randomGenerator, field_180294_c, CLAY);
        for (j = 0; doGen && j < this.seaweedPerChunk; ++j)
//       
        	{
        	k = this.randomGenerator.nextInt(16) + 8;
        	l = this.randomGenerator.nextInt(16) + 8;
//        	i1 = this.nextInt(this.currentWorld.getHeight(k, l) * 2);
            i1 = nextInt(this.currentWorld.getHorizon(this.field_180294_c.add(k, 0, l)).getY() * 2);

 //       	(new WorldGenSeaweed(3)).generate(this.currentWorld, this.randomGenerator, k, i1, l);
            (new WorldGenSeaweed(3)).generate(this.currentWorld, this.randomGenerator, this.field_180294_c.add(k, i1, l));
     	
            }
        	
        	MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(currentWorld, randomGenerator, field_180294_c));

        	
        	}
       

        private int nextInt(int i) {
        	if (i <= 1)
        	return 0;
        	return this.randomGenerator.nextInt(i);

	}
	
}
