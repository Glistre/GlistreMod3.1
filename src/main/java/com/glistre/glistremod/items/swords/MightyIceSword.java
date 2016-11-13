package com.glistre.glistremod.items.swords;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.glistre.glistremod.GlistreMod;
import com.glistre.glistremod.entities.EntityIcePotionBall;
import com.glistre.glistremod.entities.king.EntityTobieKing;
import com.glistre.glistremod.init.BlockRegistry;
import com.glistre.glistremod.init.ItemRegistry;
import com.glistre.glistremod.reference.Reference;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;


public class MightyIceSword extends ItemSword {
    
    private String texturePath = "com.glistre.glistremod:";
    
    public MightyIceSword(Item ToolMaterial, String textureName)
    {
        super(Item.ToolMaterial.IRON);
        this.setUnlocalizedName(textureName);
//        this.setTextureName("MightySword");
    }

/*@Override   
@SideOnly(Side.CLIENT)

    public void registerIcons(IIconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID +":" + "MightySword");
    }*/
@Override
//@SideOnly(Side.CLIENT)
public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List list){

		ItemStack enchant01 = new ItemStack(ItemRegistry.mighty_sword);
		enchant01.addEnchantment(Enchantment.smite, 2);
		list.add(enchant01);
	}
@Override
// Doing this override means that there is no localization for language
// unless you specifically check for localization here and convert
public String getItemStackDisplayName(ItemStack par1ItemStack)
{
    return (EnumChatFormatting.BLUE + "Mighty Ice Sword...ice and effects!");
}

@Override
//@SideOnly(Side.CLIENT)
/** Makes your Item Enchanted when it is crafted */
    public void onCreated(ItemStack item, World world, EntityPlayer player) 
    {
        item.addEnchantment(Enchantment.sharpness, 2);
        // Replace the "." after second "Enchantment" to see options
        // The number is the Enchantment Level
    }

@Override
//adds particle effects to Item
public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player)
{
	float var4 = 1.0F;
	int i = (int)(player.prevPosX + (player.posX - player.prevPosX) * (double)var4);
	int j = (int)(player.prevPosY + (player.posY - player.prevPosY) * (double)var4);
	int k = (int)(player.prevPosZ + (player.posZ - player.prevPosZ) * (double)var4);

	if(true){
	if(player instanceof EntityLivingBase) ((EntityLivingBase)player).addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 200, 3));
	if(player instanceof EntityLivingBase) ((EntityLivingBase)player).removePotionEffect(Potion.moveSlowdown.getId());

	}

	 for (int l = 0; l < 8; ++l)
     {
			par2World.spawnEntityInWorld(new EntityIcePotionBall(par2World, player, l));
			par2World.playSoundAtEntity(player, "glistremod:chill_hit", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.5F);
     
     }
	//added to cure like milk
//   player.curePotionEffects(new ItemStack (Items.milk_bucket)); 
	 
//	 target.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 800, 10));

	player.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
	return par1ItemStack;
	}


public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		 //x, y, and z coordinates. Mess around with them by adding or subtracting to move where the block places.
		 pos = new BlockPos(pos.getX(), pos.getY()+1, pos.getZ());//liquid ice
		 IBlockState state = BlockRegistry.liquid_ice.getDefaultState();
		 worldIn.setBlockState(pos, state);
		 worldIn.playSoundAtEntity(playerIn, "glistremod:fast_freezing_ice", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 1.2F) + 0.5F);
		 playerIn.removePotionEffect(Potion.moveSlowdown.getId());
		 return true;
	}

public boolean hitEntity(ItemStack item, EntityLivingBase target, EntityLivingBase player)
	{    
	    target.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200, 7));
	    return true;

	}
/*@Override
public void onUpdate(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected){
	if (entity instanceof EntityPlayer) {
		  EntityPlayer player = (EntityPlayer) entity;
		  if (player.inventory.hasItem(this)){
//		if(!itemStack.hasTagCompound())itemStack.setTagCompound(new NBTTagCompound());
		if( itemStack.stackTagCompound == null)
	        itemStack.setTagCompound(new NBTTagCompound());
		NBTTagCompound nbtTagCompound = itemStack.getTagCompound();		
//		NBTTagCompound NBT= itemStack.getTagCompound();
		nbtTagCompound.setBoolean("pickedUp", true);
		//NBTTagCompound NBT= itemStack.getTagCompound();
//		itemStack.setTagCompound(nbtTagCompound);
//		String s = ("Created by Player " + Minecraft.getMinecraft().thePlayer.getDisplayName());

		return;
	}
	}
}*/


/**
 * Called when a player drops the item into the world,
 * returning false from this will prevent the item from
 * being removed from the players inventory and spawning
 * in the world
 *
 * @param player The player that dropped the item
 * @param item The item stack, before the item is removed.
 */
@Override
public boolean onDroppedByPlayer(ItemStack itemStack, EntityPlayer player)
{
	super.onDroppedByPlayer(itemStack, player);
	NBTTagCompound nbtTagCompound = itemStack.getTagCompound();
//	if(!itemStack.hasTagCompound())itemStack.setTagCompound(new NBTTagCompound());
///////////might need to change getTagCompound 1.8 it was stackTagCompound
	if( itemStack.getTagCompound() == null)
        itemStack.setTagCompound(new NBTTagCompound());
	
//	NBTTagCompound NBT= itemStack.getTagCompound();
	nbtTagCompound.setBoolean("activated", true);
	//NBTTagCompound NBT= itemStack.getTagCompound();
//	itemStack.setTagCompound(nbtTagCompound);
//	String s = ("Created by Player " + Minecraft.getMinecraft().thePlayer.getDisplayName());

    return true;
		//super.onDroppedByPlayer(itemStack, player);
}
public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean b) {
	if(!itemStack.hasTagCompound())
		itemStack.setTagCompound(new NBTTagCompound());

    	 if (itemStack.hasTagCompound())
         {
             if (itemStack.getTagCompound().getBoolean("activated"))
             {
                 list.add(EnumChatFormatting.BLUE + "Activated");
             }
         }
         super.addInformation(itemStack, player, list, b);
}

/*public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5){
	super.onUpdate(stack, world, entity, par4, par5);
	{
		EntityTobieKing entityTk = new EntityTobieKing(world);
	    EntityPlayer  player = (EntityPlayer) entity;
	    ItemStack equipped = player.getCurrentEquippedItem();
	    if((!(world.isRemote)) && equipped == stack){
//	    	  player.getBrightness(3.0f);
//	    	  player.getBrightnessForRender(15728880);
 		List<EntityPlayer> playerList = world.getEntitiesWithinAABB(EntityPlayer.class, entityTk.getBoundingBox().expand(155.0D, 95.0D,155.0D));
   			Iterator<EntityPlayer> i1 = playerList.iterator();
    			EntityPlayer entityplayer;
                      while (i1.hasNext()){
                            entityplayer = (EntityPlayer)i1.next();  			
                            player.addChatComponentMessage(
                		new ChatComponentText(EnumChatFormatting.DARK_GREEN + "Toby King in Tower location" 
                + EnumChatFormatting.DARK_RED + " X: " + (int)Math.round(entity.posX) 
                + EnumChatFormatting.GOLD + " | Y: " + (int)Math.round(entity.posY) 
                + EnumChatFormatting.DARK_AQUA +" | Z: " + (int)Math.round(entity.posZ)));
	
	    }
	}
}
}*/
}
