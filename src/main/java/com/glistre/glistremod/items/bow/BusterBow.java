package com.glistre.glistremod.items.bow;

import java.util.List;

import com.glistre.glistremod.init.ItemRegistry;
import com.glistre.glistremod.reference.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
//import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
//import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BusterBow extends Item
{
    public static final String[] bowPullIconNameArray = new String[] {"pulling_0", "pulling_1", "pulling_2"};
  //SideOnly(Side.CLIENT)
    private final String name = "custom_bow_1";
//    private IIcon[] iconArray;
 //   private static final String __OBFID = "CL_00001777";

    public BusterBow()
    {
        this.maxStackSize = 1;
        this.setMaxDamage(384);
        //next line makes it approximately correct but not exactly centered in hand of player
        this.bFull3D = true;
        this.setCreativeTab(CreativeTabs.tabCombat);
    }
	public String getName()
	{
		return name;
	}

    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int itemInUseCount)
    {
        int j = this.getMaxItemUseDuration(itemStack) - itemInUseCount;

        ArrowLooseEvent event = new ArrowLooseEvent(player, itemStack, j);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return;
        }
        j = event.charge;

        boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, itemStack) > 0;

        if (flag || player.inventory.hasItem(Items.arrow))
        {
  //change float from 20.0F to 10.F to cut charge/shoot time in half
            float f = (float)j / 10.0F;
            f = (f * f + f * 2.0F) / 3.0F;

            if ((double)f < 0.1D)
            {
                return;
            }

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            EntityArrow entityarrow = new EntityArrow(world, player, f * 2.0F);

            if (f == 1.0F)
            {
                entityarrow.setIsCritical(true);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, itemStack);

            if (k > 0)
            {
                entityarrow.setDamage(entityarrow.getDamage() + (double)k * 0.5D + 0.5D);
            }

            int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, itemStack);

            if (l > 0)
            {
                entityarrow.setKnockbackStrength(l);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, itemStack) > 0)
            {
                entityarrow.setFire(100);
            }

            itemStack.damageItem(1, player);
            world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (flag)
            {
                entityarrow.canBePickedUp = 2;
            }
            else
            {
                player.inventory.consumeInventoryItem(Items.arrow);
            }

            if (!world.isRemote)
            {
                world.spawnEntityInWorld(entityarrow);
            }
        }
    }


  
    
    //next method does not seem to do anything since using displayAllRelevantItems
 /*   @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_, List list){

    		ItemStack enchant04 = new ItemStack(ItemRegistry.customBow_1);
    		enchant04.addEnchantment(Enchantment.infinity, 10);
    		enchant04.addEnchantment(Enchantment.power, 7);
    		list.add(enchant04);
    		}*/
    public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player)
    {	
    	return itemStack;
    }
    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack itemStack)
    {
        return 72000;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.BOW;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        ArrowNockEvent event = new ArrowNockEvent(player, itemStack);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return event.result;
        }

        if (player.capabilities.isCreativeMode || player.inventory.hasItem(Items.arrow))
        {
            player.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        }
        
        //adds sound effect on ArrowKnock
        
 //       world.playSoundAtEntity(player, "glistremod:epm_flash", 1.0F, 2.0F);

        return itemStack;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 1;
    }
/* following section not needed because its already in ItemRegistry 1.8   --removed BusterBowRenderer from 1.8 update
    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ItemRegistry.custom_bow_1, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
 {

        @Override
        public ModelResourceLocation getModelLocation(ItemStack stack) {
            // return appropriate model based on ItemStack
        }

    });*/

/*    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister p_94581_1_)
    {
        this.itemIcon = p_94581_1_.registerIcon("glistremod:bow_standby");
        this.iconArray = new IIcon[bowPullIconNameArray.length];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = p_94581_1_.registerIcon("glistremod:bow_" + bowPullIconNameArray[i]);
        }
    }
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining){
    	if (usingItem == null) {return itemIcon; }
    	int ticksInUse = stack.getMaxItemUseDuration() - useRemaining;
    	if (ticksInUse > 17){
    		return iconArray[2];
    	} else if (ticksInUse > 13){
    		return iconArray[1];
    	} else if (ticksInUse > 0){
    		return iconArray[0];
    	} else {
    		return itemIcon;
    	}
    }*/
    

    /**
     * used to cycle through icons based on their used duration, i.e. for the bow
     */
 /*   @SideOnly(Side.CLIENT)
    public IIcon getItemIconForUseDuration(int p_94599_1_)
    {
        return this.iconArray[p_94599_1_];
    }*/
}