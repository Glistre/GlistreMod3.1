package com.glistre.glistremod.biome;

import java.util.Random;

import com.glistre.glistremod.blocks.fluids.ModFluids;
import com.glistre.glistremod.init.BlockRegistry;
import com.glistre.glistremod.worldgen.TobyKingTowerGen;
import com.glistre.glistremod.worldgen.WorldGenFreonSpikes;
import com.google.common.base.Predicate;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenIceSpike;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
//import net.minecraft.world.gen.feature.WorldGenHugeTrees;
//import net.minecraft.world.gen.feature.WorldGenTaiga1;
//import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class FreonBiome extends BiomeGenBase{

    private WorldGenerator theWorldGenerator;
    private WorldGenIceSpike worldGenIceSpike;

 //   private WorldGenIceSpike field_150616_aD = new WorldGenIceSpike(); 
	
	public FreonBiome(int id){
		super(id);
        this.setBiomeName("Freon Biome");
        this.topBlock = (IBlockState) Blocks.snow.getDefaultState();
		this.fillerBlock = (IBlockState) Blocks.packed_ice.getDefaultState();
		this.theBiomeDecorator.treesPerChunk = 16;
        this.theWorldGenerator = new WorldGenLiquids(ModFluids.rud_block);
        this.theWorldGenerator = new WorldGenLakes(ModFluids.rud_block);

        this.theWorldGenerator = new WorldGenMinable((IBlockState) BlockRegistry.silver_ore_1.getDefaultState(), 20);
        this.theWorldGenerator = new WorldGenMinable((IBlockState) BlockRegistry.silver_block_1.getDefaultState(), 8);
        this.theWorldGenerator = new WorldGenMinable((IBlockState) BlockRegistry.liquid_ice.getDefaultState(), 8);
        this.theWorldGenerator = new WorldGenFreonSpikes();
//        this.worldGenIceSpike = new WorldGenIceSpike();
       
 //       this.theWorldGenerator = new WorldGenTaiga1();
 //       this.theWorldGenerator = new WorldGenBigTree(enableSnow);
 //       this.theWorldGenerator = new WorldGenBigTree(enableSnow);
		
        this.setColor(0x443333);
        this.setEnableSnow();
        this.setMinMaxHeight(1.7F, 4.1F);
        this.setTemperatureRainfall(1.75F, 1.25F);
//change water color to blood
//        this.waterColorMultiplier = 0xFF0000;
        this.canSpawnLightningBolt();
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 1, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class, 30, 1, 2));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityWitch.class, 20, 1, 2));
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 20, 1, 1));
        this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 10, 1, 2));
        this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityCaveSpider.class, 10, 1, 2));
        
      
	}
	
	private void setMinMaxHeight(float f, float g) 
	{
	
	}
	
    public int getSkyColorByTemp(float par1)
    {
// grey sky
    	return 0x443333;  
    }
    
    @Override
	public BiomeGenBase.TempCategory getTempCategory()
	{
		return TempCategory.COLD;
	}
   /* @Override
    public WorldGenAbstractTree func_150567_a(Random par1Random)
    {
        return (WorldGenAbstractTree)(par1Random.nextInt(6) == 0 ? new WorldGenYourTree(true, true) : new WorldGenYourOtherTree(true, true));
    } */

    public WorldGenerator getRandomWorldGenForTrees(Random random){
    	return(WorldGenerator)(random.nextInt(1) == 0 ? new WorldGenFreonSpikes() : (random.nextInt(6) == 0 
    			? this.worldGeneratorSwamp : random.nextInt(50) == 0  
   				? this.theWorldGenerator : this.theWorldGenerator));
    }
    @SubscribeEvent
	public void DecorateBiomeEvent(net.minecraftforge.event.terraingen.DecorateBiomeEvent event)
	{
		BlockPos pos = event.pos;
		World world = event.world;
		Random random = event.rand;
		
		int xCoord = pos.getX();
		int zCoord = pos.getZ();

		
		
		BlockPos pos1 = new BlockPos(xCoord+2, 0, zCoord+2);
		
		if (random.nextInt(500)==0) (new TobyKingTowerGen()).generate(world, random, pos1);
	}

  /*  public WorldGenerator getRandomWorldGenForTrees(Random random){
    	  if (random.nextInt(3) == 0) {
    	    return new WorldGenTaiga1();
    	  }
    	 else {
    	    return new WorldGenTaiga2(false);
    	  }
    	}*/
    
    //1.7.10 replaces GlistreBlock with SilverBlock copied from BlockEmeraldClass
    //1.8 replaces SilverOre with SilverBlock
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
//1.8 changed target from glistre_block_1 to silver_ore_1 //Predicate is google.common.base.Predicate
            //1.8.9 should be BlockMatcher
            
            if (worldIn.getBlockState(blockPos).getBlock().isReplaceableOreGen(worldIn, blockPos, BlockHelper.forBlock(BlockRegistry.silver_ore_1)))
            		
            		
            {
            	 //not sure if 1.8 needs the .getDefaultState() or not
            	//last argument to setBlockState is a bit flag that tells Minecraft whether or not to update the client side / notify neighboring blocks / other things, and you almost always want it set to either 2 (notify client) or 3 (notify client AND notify neighbors, IIRC).
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
        
 /*       for (int j = 0; j < 10; ++j)//50 is how often lakes spawn in vanilla
        {
        int m = chunkX +random.nextInt(16) + 8;
        int n = random.nextInt(random.nextInt(92) + 8);//12 is also the current elevation that lakes spawn in vanilla
        int o = chunkZ + random.nextInt(16) + 8;
        (new WorldGenLiquids(ModFluids.rud_block)).generate(worldIn, random, blockPos);
        }
       
        for (int p = 0; p < 20; ++p)//50 is how often lakes spawn in vanilla
        {
        int m = chunkX +random.nextInt(16) + 8;
        int n = random.nextInt(random.nextInt(60) + 8);// seems to impact frequency of spawn ; 12 is the current elevation that lakes spawn in vanilla
        int o = chunkZ + random.nextInt(16) + 8;
        (new WorldGenLakes(ModFluids.rud_block)).generate(worldIn, random, blockPos);
        }*/
    
      }    
  
     }
    }
}

