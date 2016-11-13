package com.glistre.glistremod.projectiles.tobyworstsword;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import com.glistre.glistremod.GlistreMod;
import com.glistre.glistremod.effects.potions.splash.EntitySplashProjectile;
import com.glistre.glistremod.entities.blacktobie.EntityBlackTobo;
import com.glistre.glistremod.entities.king.EntityTobieKing;
import com.glistre.glistremod.entities.queen.EntityTobieQueen;
import com.glistre.glistremod.tabs.TabRegistry;
import com.google.common.collect.Multimap;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TobyEntitySword extends ItemSword
{
    private float field_150934_a;
    private final Item.ToolMaterial field_150933_b;
    private static final String __OBFID = "CL_00000072";

    public TobyEntitySword(Item.ToolMaterial material)
    {
    	super(GlistreMod.Silvers);
        this.field_150933_b = material;
        this.maxStackSize = 1;

        this.setMaxDamage(material.getMaxUses());
   //     this.setCreativeTab(TabRegistry.tab_custom);
        this.field_150934_a = 4.0F + material.getDamageVsEntity();
    }

    public float func_150931_i()
    {
        return this.field_150933_b.getDamageVsEntity();
    }


    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    @Override
    public boolean hitEntity(ItemStack itemstack, EntityLivingBase target, EntityLivingBase attacker){

        if (target instanceof EntityTobieQueen)
        {
        	target.setFire(13);//burn time in seconds
        	itemstack.damageItem(1, attacker);
        	target.attackEntityFrom(DamageSource.causeMobDamage(target), (20));//takes about 20 hits
        	return true;  	
        }
        else if(target instanceof EntityTobieKing)
        {
        	target.setFire(8);
        	itemstack.damageItem(1, attacker);
        	target.attackEntityFrom(DamageSource.causeMobDamage(target), (8));//tskes about 20 hits
        	return true;
    	}
        else if (target instanceof EntityBlackTobo)
        {
    	target.setFire(7);
    	itemstack.damageItem(1, attacker);
//    	target.attackEntityFrom(DamageSource.causeMobDamage(targetentity), (100));
//    	target.attackEntityFrom(DamageSource.fall, 100);
    	return true;
        }
       return true;
}

    public boolean onBlockDestroyed(ItemStack itemStack, World worldIn, Block block, BlockPos pos, EntityLivingBase entityliving)
    {
    	
        if ((double)block.getBlockHardness(worldIn, pos) != 0.0D)
        {
            itemStack.damageItem(2, entityliving);
        }

        return true;
    }
    @Override
    // Doing this override means that there is no localization for language
    // unless you specifically check for localization here and convert
    public String getItemStackDisplayName(ItemStack par1ItemStack)
    {
        return (EnumChatFormatting.DARK_RED + "Tobie's Worst Enemy...try Right-Clicking!");
    }

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }


    
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
@Override
   public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
   {

           if (!player.capabilities.isCreativeMode)
           {
               --itemStack.stackSize;
           }

           world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

           if (!world.isRemote)
           {
               world.spawnEntityInWorld(new TobyEntityProjectile(world, player));
           }

	   return itemStack;       
   }	
   

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
@Override
    public int getItemEnchantability()
    {
        return this.field_150933_b.getEnchantability();
    }

    /**
     * Return the name for this tool's material.
     */
/*@Override
    public String getToolMaterialName()
    {
        return this.field_150933_b.toString();
    }*/


    /**
     * Gets a map of item attribute modifiers, used by ItemSword to increase hit damage.
     */
@Override
    public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(itemModifierUUID, "Weapon modifier", (double)this.field_150934_a, 0));
        return multimap;
    }
}