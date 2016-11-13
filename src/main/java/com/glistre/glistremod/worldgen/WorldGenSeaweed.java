package com.glistre.glistremod.worldgen;

import java.util.Random;

import com.glistre.glistremod.blocks.BlockSeaweed;
import com.glistre.glistremod.blocks.SilverOreBlock;
import com.glistre.glistremod.init.BlockRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenClay;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSeaweed extends WorldGenClay
{
    public BlockSeaweed fieldseaweed;
    private int metadata;
    /** The number of blocks to generate. */
    private int numberOfBlocks;
 //   private static final String __OBFID = "CL_00000405";

    public WorldGenSeaweed(int num)
    {
    	super(num);
        this.fieldseaweed = (BlockSeaweed) BlockRegistry.block_seaweed;
        this.numberOfBlocks = num;
//        this.numberOfBlocks = 16;
    }

    public boolean generate(World worldIn, Random random, BlockPos blockPos)
    {
        if (worldIn.getBlockState(blockPos).getBlock().getMaterial() != Material.water)
        {
            return false;
        }
        else
        {
            int l = random.nextInt(this.numberOfBlocks - 2) + 2;
            byte b0 = 1;

            for (int i1 = blockPos.getX() - l; i1 <= blockPos.getX() + l; ++i1)
            {
                for (int j1 = blockPos.getZ() - l; j1 <= blockPos.getZ() + l; ++j1)
                {
                    int k1 = i1 - blockPos.getX();
                    int l1 = j1 - blockPos.getZ();

                    if (k1 * k1 + l1 * l1 <= l * l)
                    {
                        for (int i2 = blockPos.getY() - b0; i2 <= blockPos.getY() + b0; ++i2)
                        {
                        	BlockPos blockPos1 = new BlockPos(i1, i2, j1);
                            Block block = worldIn.getBlockState(blockPos1).getBlock();

                            if (block == Blocks.dirt || block == Blocks.sand || block == Blocks.gravel || block == Blocks.clay || block == BlockRegistry.silver_ore_1)
                            { 
                            	//not sure why the state adds 2 here for 1.8
                                worldIn.setBlockState(blockPos1, this.fieldseaweed.getDefaultState(), 2);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}