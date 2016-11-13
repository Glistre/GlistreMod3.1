package com.glistre.glistremod.items.swords;

import java.util.List;
import java.util.Random;

import com.glistre.glistremod.GlistreMod;
import com.glistre.glistremod.entities.EntityIcePotionBall;
import com.glistre.glistremod.init.BlockRegistry;
import com.glistre.glistremod.init.ItemRegistry;
import com.glistre.glistremod.reference.Reference;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;


public class CuredIceSword extends ItemSword {
    
    private String texturePath = "com.glistre.glistremod:";
    
    public CuredIceSword(Item ToolMaterial, String textureName)
    {
        super(Item.ToolMaterial.EMERALD);
        this.setUnlocalizedName(textureName);
//        this.setTextureName("MightyIceSword");
    }

/*@Override   
@SideOnly(Side.CLIENT)

    public void registerIcons(IIconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon(Reference.MOD_ID +":" + "MightyIceSword");
    }*/
@Override
//@SideOnly(Side.CLIENT)
public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List list){

		ItemStack enchant01 = new ItemStack(ItemRegistry.mighty_ice_sword);
		enchant01.addEnchantment(Enchantment.smite, 2);
		list.add(enchant01);
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

@Override
public boolean onItemUse(ItemStack itemStack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		 //x, y, and z coordinates. Mess around with them by adding or subtracting to move where the block places.
	
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
}
