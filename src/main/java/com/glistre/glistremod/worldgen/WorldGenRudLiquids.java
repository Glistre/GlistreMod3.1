package com.glistre.glistremod.worldgen;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenLiquids;

public class WorldGenRudLiquids extends WorldGenLiquids
{
    private Block something;
    private static final String __OBFID = "CL_00000434";

    public WorldGenRudLiquids(Block block)
    {
    	super(block);
        this.something = block;
        
    }

    public boolean generate(World worldIn, Random rand, BlockPos blockpos)
    {
        if (worldIn.getBlockState(blockpos.up()).getBlock() != Blocks.stone)
        {
            return false;
        }
        else if (worldIn.getBlockState(blockpos.down()).getBlock() != Blocks.stone)
        {
            return false;
        }
        else if (worldIn.getBlockState(blockpos).getBlock().getMaterial() != Material.air && worldIn.getBlockState(blockpos).getBlock() != Blocks.stone)
        {
            return false;
        }
        else
        {
            int i = 0;

            if (worldIn.getBlockState(blockpos.west()).getBlock() == Blocks.stone)
            {
                ++i;
            }

            if (worldIn.getBlockState(blockpos.east()).getBlock() == Blocks.stone)
            {
                ++i;
            }

            if (worldIn.getBlockState(blockpos.north()).getBlock() == Blocks.stone)
            {
                ++i;
            }

            if (worldIn.getBlockState(blockpos.south()).getBlock() == Blocks.stone)
            {
                ++i;
            }

            int j = 0;

            if (worldIn.isAirBlock(blockpos.west()))
            {
                ++j;
            }

            if (worldIn.isAirBlock(blockpos.east()))
            {
                ++j;
            }

            if (worldIn.isAirBlock(blockpos.north()))
            {
                ++j;
            }

            if (worldIn.isAirBlock(blockpos.south()))
            {
                ++j;
            }

            if (i == 3 && j == 1)
            {
                worldIn.setBlockState(blockpos, this.something.getDefaultState(), 2);
                worldIn.forceBlockUpdateTick(this.something, blockpos, rand);
            }

            return true;
        }
    }
}