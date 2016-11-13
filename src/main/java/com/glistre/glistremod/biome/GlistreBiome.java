package com.glistre.glistremod.biome;

import java.util.Random;

import com.glistre.glistremod.entities.blacktobie.EntityBlackTobo;
import com.glistre.glistremod.entities.guardian.EntityTobieSkel;
import com.glistre.glistremod.entities.wolf.EntityGlistreWolf;
import com.glistre.glistremod.init.BlockRegistry;
import com.glistre.glistremod.worldgen.WorldGenSeaweed;
import com.google.common.base.Predicate;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
//import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenClay;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.world.BlockEvent;

public class GlistreBiome extends BiomeGenBase
{
	BiomeDecoratorGlistre customBiomeDecorator;
    private WorldGenerator theWorldGenerator;
    public GlistreBiome(int id)
    {
        super(id);
        this.theWorldGenerator = new WorldGenMinable((IBlockState) BlockRegistry.silver_ore_1.getDefaultState(), 20);
        this.theWorldGenerator = new WorldGenMinable((IBlockState) BlockRegistry.silver_block_1.getDefaultState(), 8);
        this.setBiomeName("Glistering Biome");
      //this is the top layer, so the first thing you see will be this block
        //next are the 3-5 layers bellow your top layer

        this.topBlock = (IBlockState) Blocks.grass.getDefaultState();
		this.fillerBlock = (IBlockState) Blocks.dirt.getDefaultState();
		
		this.theBiomeDecorator = new BiomeDecoratorGlistre();
		customBiomeDecorator = new BiomeDecoratorGlistre();
		customBiomeDecorator =(BiomeDecoratorGlistre)theBiomeDecorator;
		this.theBiomeDecorator = this.createBiomeDecorator();
		this.customBiomeDecorator.seaweedPerChunk = 200;
		this.customBiomeDecorator.seaweedGen = new WorldGenSeaweed(3);
		this.theBiomeDecorator.clayPerChunk = 2;      
        this.theBiomeDecorator.treesPerChunk = 2;
        this.theBiomeDecorator.waterlilyPerChunk = 150; // what's wrong now? 
        this.theBiomeDecorator.bigMushroomsPerChunk = 1;
        this.theBiomeDecorator.flowersPerChunk = 100;
        this.theBiomeDecorator.reedsPerChunk = 10;
        this.theBiomeDecorator.grassPerChunk = 10;

//        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableMonsterList.clear(); 
        this.spawnableCreatureList.add(new SpawnListEntry(EntityOcelot.class, 10, 1, 1));      
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 20, 5, 7));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 1, 2, 7));     
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 1, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class, 1, 1, 1));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityGlistreWolf.class, 20, 3, 5));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityTobieSkel.class, 30, 3, 7));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityBlackTobo.class, 10, 1, 2));
        
        this.addFlower(Blocks.yellow_flower.getDefaultState().withProperty(Blocks.yellow_flower.getTypeProperty(), BlockFlower.EnumFlowerType.DANDELION), 30);
 //       this.addFlower(Blocks.yellow_flower, 10, 30);  from 1.7.10
        this.addFlower(Blocks.red_flower.getDefaultState().withProperty(Blocks.red_flower.getTypeProperty(), BlockFlower.EnumFlowerType.POPPY), 30);

//       this.addFlower(Blocks.red_flower, 20, 30);  from 1.7.10
//        this.addFlower(Blocks.pumpkin, 7, 20);       
 //  1.8 pumpkins??     this.addFlower(Blocks.pumpkin.getDefaultState().withProperty(((BlockFlower) Blocks.pumpkin).getTypeProperty(), BlockFlower.EnumFlowerType.DANDELION), 30);

        
 //       this.setColor(0x7F007F);
//        this.setColor(0x443333);
        this.canSpawnLightningBolt();
        this.setColor(0x003000);
        this.setEnableSnow();
     
        this.enableSnow = true;
        this.enableRain = true;
        this.isHighHumidity();
        this.setMinMaxHeight(0.75F, 1.1F);
        //first parameter is temp .2F and rain looks like snow, second parameter is rainfall 0F none .5F is normal
        this.setTemperatureRainfall(0.75F, 1.0F);
        
       
    }
@Override
    public void addFlower(IBlockState state, int weight)
{
 this.flowers.add(new FlowerEntry(state, weight));
}

    public boolean darkenSkyDuringRain() 
    {        
    	return true;     
    }
    
    public boolean canSpawnLightningBolt() 
    { 
        return this.enableSnow ? true : this.enableRain; 
    } 
   
/*	@Override
	public BiomeGenBase.TempCategory getTempCategory()
	{
		return TempCategory.COLD;
	}*/

	private void setMinMaxHeight(float f, float g) 
	{
	
	}
//    @SideOnly(Side.CLIENT)
 /*   public int getBiomeGrassColor()
    {
    	return 0x443333;
    }
 //   @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor()
    {
    	return 0x443333;
    }*/
 //   @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(float par1)
    {
//    	return 0x443333; //grey sky
    	//gold/grey sky
    	return 0xA0A016;
    }
	
    /**
     * Gets a WorldGen appropriate for this biome.
    */ 
    
    public WorldGenerator getRandomWorldGenForGrass(Random random){
       return random.nextInt(2) == 0 ?  new WorldGenSeaweed(3) : new WorldGenSeaweed(10);
        
   }

    public WorldGenerator getRandomWorldGenForTrees(Random par1Random){
    	return(WorldGenerator)(par1Random.nextInt(1) == 0 ? this.worldGeneratorBigTree : (par1Random.nextInt(6) == 0 ? this.worldGeneratorSwamp : par1Random.nextInt(20) == 0 ? this.worldGeneratorTrees : this.worldGeneratorTrees));
    }
   
    //replaces GlistreBlock with SilverBlock copied from BlockEmeraldClass
    public void decorate(World worldIn, Random random, BlockPos blockPos)
    {
        super.decorate(worldIn, random, blockPos);
        
        //next two bizarre lines of code is update to 1.8
        for (int chunkX = 0; chunkX < 4; ++chunkX) {
			for (int chunkZ = 0; chunkZ < 4; ++chunkZ) {
        int k = 3 + random.nextInt(6);
        int l;
        
        int i1;
        int j1;
        for (l = 0; l < k; ++l)
        {
            i1 = chunkX + random.nextInt(16);
            j1 = random.nextInt(28) + 4;
            int k1 = chunkZ + random.nextInt(16);

 //           if (worldIn.getBlockState(blockPos).isReplaceableOreGen(worldIn, i1, j1, k1, BlockRegistry.glistre_block_1))
 
            if (worldIn.getBlockState(blockPos).getBlock().isReplaceableOreGen(worldIn, blockPos, (BlockHelper.forBlock(BlockRegistry.silver_ore_1))))
{
  //              worldIn.setBlockState(blockPos, BlockRegistry.silver_block_1, 2);
                worldIn.setBlockState(blockPos, (IBlockState) BlockRegistry.silver_block_1.getDefaultState(), 2);

}
        }

        for (k = 0; k < 7; ++k)
        {
            l = chunkX + random.nextInt(16);
            i1 = random.nextInt(64);
            j1 = chunkZ + random.nextInt(16);
            this.theWorldGenerator.generate(worldIn, random, blockPos);
        }
    }

        }  
    }
}
       
    
