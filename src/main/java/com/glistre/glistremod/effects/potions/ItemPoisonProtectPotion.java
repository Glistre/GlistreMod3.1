package com.glistre.glistremod.effects.potions;

import com.glistre.glistremod.GlistreMod;
import com.glistre.glistremod.effects.potions.PoisonProtectEffect;
import com.glistre.glistremod.init.ItemRegistry;
import com.glistre.glistremod.tabs.TabRegistry;

import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
//import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPoisonProtectPotion extends Item {


//	private IIcon iconOverlay;
	@SideOnly(Side.CLIENT)
//	private IIcon iconBottle;
	private int color;
	private PoisonProtectEffect[] effects;

	
	public ItemPoisonProtectPotion(int maxStackSize, String name, PoisonProtectEffect[] poisonProtectEffects, int color) {
		this.setMaxStackSize(maxStackSize);
		this.setCreativeTab(TabRegistry.tab_potion);
		this.setUnlocalizedName(name);
		this.effects = poisonProtectEffects;
		this.color=color;
	}


	@Override
	public EnumAction getItemUseAction(ItemStack p_77661_1_) {
		return EnumAction.DRINK;
	}

	@Override
	//1.7.10 #onEaten
	public ItemStack onItemUseFinish(ItemStack itemStack, World world, EntityPlayer player) {
		if (!player.capabilities.isCreativeMode) {
			--itemStack.stackSize;
			
		}


		if (!world.isRemote) {
			for (int i = 0; i < effects.length; i++) {
				if (effects[i] != null && effects[i].getPotionID() > 0 && !world.isRemote) {
//					player.addPotionEffect(new PotionEffect(potion[i]));
					player.removePotionEffect(Potion.poison.id);
					//item, duration, amplifier, good ? visible effects
					player.addPotionEffect(new PotionEffect(ItemRegistry.poison_protect_potion.id, 3000, 0, true, true));
//					player.addPotionEffect(new PotionEffect(this.effects[i].getPotionID(), this.effects[i].getDuration(), this.effects[i].getAmplifier(), this.effects[i].getIsAmbient()));
					
				}
			}
		}
		
/*		public void onEntityWalking(World world, int x, int y, int z, Entity entity){
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(mod_TutBase.Radiation.id, 300, 1, false));

			super.onEntityWalking(world, x, y, z, entity);
			}*/

		if (!player.capabilities.isCreativeMode) {
			if (itemStack.stackSize <= 0) {
				return new ItemStack(Items.glass_bottle);
			}

			player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
		}

		return itemStack;
	}

	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack p_77626_1_) {
		return 32;
	}
	
/*	@Override
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int par4, int par5, int par6, int par7, float par8, float par9, float par10){
        return false;
    }*/
	
	@Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {

            player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
//added to cure like milk 
 //           player.curePotionEffects(new ItemStack (Items.milk_bucket));
            
 //added this to add Poison Protection II... duration, level, bad or not //1.8 added last boolean for particles
			player.removePotionEffect(Potion.poison.id);
    		player.addPotionEffect(new PotionEffect(ItemRegistry.poison_protect_potion.id, 3000, 0, true, true));
 //trying to add particles effect 
 /*   		for(int countparticles = 0; countparticles <= 10; ++countparticles)
    		{
    			world.spawnParticle("mobSpell", player.posX + 1, player.posY, player.posZ, 1.0D, 1.0D, 1.0D);
    		}

    		player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));*/
    		
            return itemStack;

    }

/*	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}*/

/*	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack p_77636_1_, int pass) {
		if (pass == 0) {
			return true;
		} else {
			return false;
		}
	}*/

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int pass) {
		if (pass == 0) {
			return this.color;
		} else {
			return 99999999;
		}
	}

/*	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int damage, int pass) {

		if (pass == 0) {
			return this.iconOverlay;

		} else {
			return this.iconBottle;
		}

	}


@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconregister) {

//		this.itemIcon = Items.apple.getIconFromDamage(0);
		if (ItemPotion.func_94589_d("bottle_drinkable") != null && ItemPotion.func_94589_d("overlay") != null) {
			this.iconBottle = ItemPotion.func_94589_d("bottle_drinkable");
			this.iconOverlay = ItemPotion.func_94589_d("overlay");
		}

	}*/

}