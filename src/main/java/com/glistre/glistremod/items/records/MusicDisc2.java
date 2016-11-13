package com.glistre.glistremod.items.records;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.glistre.glistremod.reference.Reference;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.state.IBlockState;
//import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class MusicDisc2 extends ItemRecord
{
 private static final Map records = new HashMap();


 public final String recordName;


 public MusicDisc2(String recordName)
 {
 super(recordName);

 this.recordName = recordName;
 this.maxStackSize = 1;
 records.put(recordName, this);
 }


 /*@Override
 public void registerIcons(IIconRegister iconRegister)
 {
 itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + recordName);
 }*/


 @Override
 public boolean onItemUse(ItemStack itemStack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ){
 IBlockState state = worldIn.getBlockState(pos);

 if (state.getBlock() == Blocks.jukebox && !((Boolean)state.getValue(BlockJukebox.HAS_RECORD)).booleanValue())
 
 //if (world.getBlock(x, y, z) == Blocks.jukebox && world.getBlockMetadata(x, y, z) == 0)
 {
 if (worldIn.isRemote)
 return true;
 else
 {

	 ((BlockJukebox)Blocks.jukebox).insertRecord(worldIn, pos, state, itemStack);
 worldIn.playAuxSFXAtEntity((EntityPlayer)null, 1005, pos, Item.getIdFromItem(this));
 --itemStack.stackSize;
 return true;
 }
 } 
 else
 return false;
 }


 /*@Override
 public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
 {
 par3List.add(this.getRecordNameLocal());
 }*/


/* @Override

 public String getRecordNameLocal()
 {
 return StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc");
 }*/


 /*@Override
 public EnumRarity getRarity(ItemStack itemStack)
 {
 return EnumRarity.rare;
 }*/


 public static MusicDisc2 getRecord(String par0Str)
 {
 return (MusicDisc2)records.get(par0Str);
 }


 @Override
 public ResourceLocation getRecordResource(String name)
 {
 return new ResourceLocation(Reference.MOD_ID +":"+ name);
 }
}