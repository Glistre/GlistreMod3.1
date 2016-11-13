package com.glistre.glistremod.items.burners;

import com.glistre.glistremod.GlistreMod;
import com.glistre.glistremod.blocks.BlockGlistrePortal;
import com.glistre.glistremod.init.BlockRegistry;
import com.glistre.glistremod.tabs.TabRegistry;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
//import net.minecraft.util.WeightedRandom.Item;
//import net.minecraft.item.Item;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class TobyKingBurner extends ItemFlintAndSteel
{
    private static final String __OBFID = "CL_00000035";

    public TobyKingBurner()
    {
    	
        this.maxStackSize = 1;
        this.setMaxDamage(64);
  //      this.setCreativeTab(TabRegistry.glistre_tab_1);
       }

       /**
        * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
        * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
        */
       public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
       {
        /*   if (side == 0)
           {
               --y;
           }

           if (side == 1)
           {
               ++y;
           }

           if (side == 2)
           {
               --z;
           }

           if (side == 3)
           {
               ++z;
           }

           if (side == 4)
           {
               --x;
           }

           if (side == 5)
           {
               ++x;
           }*/

           pos = pos.offset(side);

           if (!playerIn.canPlayerEdit(pos, side, stack))
           {
               return false;
           }
           else
           {
               if (worldIn.isAirBlock(pos))
//               	if (world.getBlock(x, y - 1, z) != BlockRegistry.MyBlock_1 || !((BlockGlistrePortal) BlockRegistry.lightPortal).getPortalSize(world, x, y, z)){

               {
                   worldIn.playSoundEffect((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                   worldIn.setBlockState(pos, BlockRegistry.light_toby_king_fire.getDefaultState());
               }
//               	}
               stack.damageItem(1, playerIn);
               return true;
           }
       }
   }