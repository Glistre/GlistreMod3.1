package com.glistre.glistremod.worldgen;

import java.util.Random;

import com.glistre.glistremod.init.BiomeRegistry;
import com.glistre.glistremod.init.BlockRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenIceSpike;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenFreonSpikes extends WorldGenerator {

    public boolean generate(World worldIn, Random rand, BlockPos blockPos)
    {
        while (worldIn.isAirBlock(blockPos) && blockPos.getY() > 2)
        {
            blockPos = blockPos.down();
        }

        if (worldIn.getBlockState(blockPos).getBlock() != Blocks.snow)
        {
            return false;
        }
        else
        {
            blockPos = blockPos.up(rand.nextInt(4));
            int i = rand.nextInt(4) + 7;
            int i2 = rand.nextInt(1) + 1;
            int j2 = rand.nextInt(1) + 1;
            int k2 = rand.nextInt(1) +1;
            int i3 = i2/ 4 + rand.nextInt(2);
            int j3 = i2/ 4 + rand.nextInt(2);
            int k3 = i2/ 4 + rand.nextInt(2);
                        
            int j = i / 4 + rand.nextInt(2);

            if (j > 1 && rand.nextInt(60) == 0)
            {
                blockPos = blockPos.up(10 + rand.nextInt(30));
            }

            int k;
            int l;

            for (k = 0; k < i; ++k)
            {
                float f = (1.0F - (float)k / (float)i) * (float)j;
                l = MathHelper.ceiling_float_int(f);

                for (int i1 = -l; i1 <= l; ++i1)
                {
                    float f1 = (float)MathHelper.abs_int(i1) - 0.25F;

                    for (int j1 = -l; j1 <= l; ++j1)
                    {
                        float f2 = (float)MathHelper.abs_int(j1) - 0.25F;

                        if ((i1 == 0 && j1 == 0 || f1 * f1 + f2 * f2 <= f * f) && (i1 != -l && i1 != l && j1 != -l && j1 != l || rand.nextFloat() <= 0.75F))
                        {
                            Block block = worldIn.getBlockState(blockPos.add(i1, k, j1)).getBlock();

                            if (block.getMaterial() == Material.air || block == Blocks.dirt || block == Blocks.snow || block == Blocks.ice)
                            {
                                this.func_175906_a(worldIn, blockPos.add(i1, k, j1), Blocks.packed_ice);
                            }

                            if (k != 0 && l > 1)
                            {
                                block = worldIn.getBlockState(blockPos.add(i1, -k, j1)).getBlock();

                                if (block.getMaterial() == Material.air || block == Blocks.dirt || block == Blocks.snow || block == Blocks.ice)
                                {
                                    this.func_175906_a(worldIn, blockPos.add(i1, -k, j1), Blocks.packed_ice);
                                    this.func_175906_a(worldIn, blockPos.add(i3, -k3, j3), Blocks.diamond_ore);//adds one diamond ore in ice spikes
                              
                                }
                            }
                        }
                    }
                }
            }

            k = j - 1;

            if (k < 0)
            {
                k = 0;
            }
            else if (k > 1)
            {
                k = 1;
            }

            for (int k1 = -k; k1 <= k; ++k1)
            {
                l = -k;

                while (l <= k)
                {
                    BlockPos blockpos1 = blockPos.add(k1, -1, l);
                    int l1 = 50;

                    if (Math.abs(k1) == 1 && Math.abs(l) == 1)
                    {
                        l1 = rand.nextInt(5);
                    }

                    while (true)
                    {
                        if (blockpos1.getY() > 50)
                        {
                            Block block1 = worldIn.getBlockState(blockpos1).getBlock();

                            if (block1.getMaterial() == Material.air || block1 == Blocks.dirt || block1 == Blocks.snow || block1 == Blocks.ice || block1 == Blocks.packed_ice)
                            {
                                this.func_175906_a(worldIn, blockpos1, Blocks.packed_ice);
                                blockpos1 = blockpos1.down();
                                --l1;

                                if (l1 <= 0)
                                {
                                    blockpos1 = blockpos1.down(rand.nextInt(5) + 1);
                                    l1 = rand.nextInt(5);
                                }

                                continue;
                            }
                        }

                        ++l;
                        break;
                    }
                }
            }

            return true;
        }
    }
}