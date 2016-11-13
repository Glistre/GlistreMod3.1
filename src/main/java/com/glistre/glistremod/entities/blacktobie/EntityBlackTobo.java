package com.glistre.glistremod.entities.blacktobie;

import java.util.Calendar;

import com.glistre.glistremod.GlistreMod;
import com.glistre.glistremod.entities.guardian.EntityTobieSkel;
import com.glistre.glistremod.entities.wolf.EntityGlistreWolf;
import com.glistre.glistremod.init.GlistreEntityRegistry;
import com.glistre.glistremod.init.ItemRegistry;
import com.glistre.glistremod.projectiles.blaster.EntityBlasterBolt;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
//import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityIronGolem;
//import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;

public class EntityBlackTobo extends EntityMob implements IRangedAttackMob
{
    private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
    private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityZombie.class, 1.2D, false);
//    private static final String __OBFID = "CL_00001697";
    private int attackTimer;
    
    public EntityBlackTobo(World world)
    {
        super(world);
        this.setSkeletonType(0);
        //add attack AI for TobieQueen to player next line
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, IMob.mobSelector));
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1,  new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
//        this.tasks.addTask(1,  new EntityAIAttackOnCollide(this, EntityTobieSkel.class, 1.0D, true));      
        this.tasks.addTask(3, new EntityAIRestrictSun(this));
        this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
        this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(0, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(0, new EntityAIWatchClosest(this, EntityTobieSkel.class, 8.0F));
        
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityTobieSkel.class, 2, true, false, IMob.mobSelector));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityGlistreWolf.class, 3, true, false, IMob.mobSelector));
      

        if (world != null && !world.isRemote)
        {
            this.setCombatTask();
        }
    }
    

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
 //       this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(150.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.34000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(12.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(65.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(10.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
        //changed 13 to 26 can go up to 31 better to use packets
        this.dataWatcher.addObject(13, new Byte((byte)0));
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "mob.skeleton.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.skeleton.hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.skeleton.death";
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.playSound("mob.skeleton.step", 0.15F, 1.0F);
    }

    @SideOnly(Side.CLIENT)
    public int getAttackTimer()
    {
        return this.attackTimer;
    }
    
     public boolean attackEntityAsMob(Entity targetEntity)
     {
       if (super.attackEntityAsMob(targetEntity))
       {
                  /*  if (this.getSkeletonType() == 1 && targetEntity instanceof EntityLivingBase)
                    {
                        ((EntityLivingBase)targetEntity).addPotionEffect(new PotionEffect(Potion.wither.id, 200));
                    }*/
        this.attackTimer = 10;
        this.worldObj.setEntityState(this, (byte)4);
        boolean flag = targetEntity.attackEntityFrom(DamageSource.causeMobDamage(this), (float)(7 + this.rand.nextInt(15)));

        if (flag)
        {
           targetEntity.motionY += 0.4000000059604645D;
        }

                    this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
                    return flag;
                }
                else
                {
                    return false;
                }
       }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
 /*   public void onLivingUpdate()
    {
        if (this.worldObj.isDaytime() && !this.worldObj.isRemote)
        {
            float f = this.getBrightness(1.0F);

            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)))
            {
                boolean flag = true;
                ItemStack itemstack = this.getEquipmentInSlot(4);

                if (itemstack != null)
                {
                    if (itemstack.isItemStackDamageable())
                    {
                        itemstack.setItemDamage(itemstack.getItemDamageForDisplay() + this.rand.nextInt(2));

                        if (itemstack.getItemDamageForDisplay() >= itemstack.getMaxDamage())
                        {
                            this.renderBrokenItemStack(itemstack);
                            this.setCurrentItemOrArmor(4, (ItemStack)null);
                        }
                    }

                    flag = false;
                }

                if (flag)
                {
                    this.setFire(8);
                }
            }
        }

        if (this.worldObj.isRemote && this.getSkeletonType() == 1)
        {
            this.setSize(0.72F, 2.34F);
        }

        super.onLivingUpdate();
    }*/

    /**
     * Handles updating while being ridden by an entity
     */
    public void updateRidden()
    {
        super.updateRidden();

        if (this.ridingEntity instanceof EntityCreature)
        {
            EntityCreature entitycreature = (EntityCreature)this.ridingEntity;
            this.renderYawOffset = entitycreature.renderYawOffset;
        }
    }

    /**
     * Called when the mob's health reaches 0.
     */
/*    public void onDeath(DamageSource source)
    {
        super.onDeath(source);
//source.getSourceOfDamage() instanceof EntityBlasterBolt &&
        if ( source.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)source.getEntity();
            double d0 = entityplayer.posX - this.posX;
            double d1 = entityplayer.posZ - this.posZ;

            if (d0 * d0 + d1 * d1 >= 2500.0D)
            {
                entityplayer.triggerAchievement(AchievementList.snipeSkeleton);
            }
        }
    }*/

    protected Item getDropItem()
    {
        return ItemRegistry.ender_gun;
    }
    

    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    @Override
    protected void dropFewItems(boolean hasPlayerHit, int lootingLev)
    {
    	World world = worldObj;
        int j;
        int k;
        
        if (this.getSkeletonType() == 0)
        {    
        	final String KEY = "activated";
            ItemStack toDrop = new ItemStack(ItemRegistry.mighty_sword, 1);
            if(toDrop.getTagCompound() == null)
            {
                toDrop.setTagCompound(new NBTTagCompound());
                toDrop.getTagCompound().setBoolean(KEY, false);

            }
     //       j = this.rand.nextInt(3 + lootingLev) - 1;
    //    	for (k = 0; k < j; ++k)
               
     //         	{
            if (!(worldObj.isRemote)){
            EntityItem dropItem = new EntityItem(worldObj, this.posX, this.posY, this.posZ, toDrop); 

            worldObj.spawnEntityInWorld(dropItem);
            }
 
        	j = this.rand.nextInt(3 + lootingLev) - 1;
              	
            for (k = 0; k < j; ++k)
            {
           	this.entityDropItem(new ItemStack(ItemRegistry.glistre_sword, 1, 1), 1.0F);
            }
        
        	j = this.rand.nextInt(3 + lootingLev) - 1;

        	for (k = 0; k < j; ++k)
        	{
            this.dropItem(ItemRegistry.nausea_protection, 1);
        	}
        	
        	j = this.rand.nextInt(3 + lootingLev);

        	for (k = 0; k < j; ++k)
        	{
        	ItemRegistry instance = new ItemRegistry();
        	ItemStack ancientBook = instance.getAncientBook();
        	ancientBook.getItem();
        	this.entityDropItem(ancientBook, 1.0F); 
        	}
        
        	j = this.rand.nextInt(3 + lootingLev) - 1;

        	for (k = 0; k < j; ++k)
        	{	
        	this.dropItem(GlistreEntityRegistry.splash_poison_protection, 1);
        	}
  
        	//1.8 way to make 40% chance of drop
        	if(rand.nextInt(100)<40){
        		int p;
                int q;
                
            	p = this.rand.nextInt(3 + lootingLev) + 1;

        	for (q = 0; q < p; ++q)
            // what does float do here?  likelihood of drop?     	       	
               {
            	
            	this.entityDropItem(new ItemStack(GlistreEntityRegistry.tobie_worst_projectile_1, 1, 1), 1.0F);
               }
        	for (q = 0; q < p; ++q)
                // what does float do here?  likelihood of drop?     	       	
                   {
                	
                	this.entityDropItem(new ItemStack(ItemRegistry.chain_helmet_1, 1, 1), 1.0F);
                   }
        	p = this.rand.nextInt(3 + lootingLev);

        	for (q = 0; q < p; ++q)
            	{	
            	this.dropItem(ItemRegistry.item_spawn_egg_2, 1);
            	}
        	}
        }
        
    }

 /*   @Override
    protected void dropRareDrop(int num)
    { 
        
        if (this.getSkeletonType() == 0){
        	int j;
            int k;
            
        	j = this.rand.nextInt(3 + num) + 1;

    	for (k = 0; k < j; ++k)
        // what does float do here?  likelihood of drop?     	       	
           {
        	
        	this.entityDropItem(new ItemStack(GlistreEntityRegistry.tobie_worst_projectile_1, 1, 1), 1.0F);
           }
    	for (k = 0; k < j; ++k)
            // what does float do here?  likelihood of drop?     	       	
               {
            	
            	this.entityDropItem(new ItemStack(ItemRegistry.chain_helmet_1, 1, 1), 1.0F);
               }
         
    	j = this.rand.nextInt(3 + num);

    	for (k = 0; k < j; ++k)
        	{	
        	this.dropItem(ItemRegistry.item_spawn_egg_3, 1);
        	} 
        }  
    }*/

    
    /**
     * Makes entity wear random armor based on difficulty
     */
   protected void addRandomArmor()
    {
 //       super.addRandomArmor();
       this.setCurrentItemOrArmor(0, new ItemStack(ItemRegistry.tobie_gun_1));
        // sets the ability for the RayGun to drop which is different than enderGun don't want RayGun for player
        this.equipmentDropChances[0] = 0.0F;
    }
   
   public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData entitylivingdata)
    {
	   entitylivingdata = super.func_180482_a(difficulty, entitylivingdata);

        if (this.worldObj.provider instanceof WorldProviderHell && this.getRNG().nextInt(5) > 0)
        {
            this.tasks.addTask(2, this.aiAttackOnCollide);
            this.setSkeletonType(1);
            this.setCurrentItemOrArmor(0, new ItemStack(ItemRegistry.sceptre_1));
            this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
 // sets the ability for the RayGun to drop which is different than blaster dont want RayGun for player
            this.equipmentDropChances[0] = 0.0F;
        }
        else
        {
            this.tasks.addTask(0, this.aiArrowAttack);
            this.addRandomArmor();
            this.setCurrentItemOrArmor(0, new ItemStack(ItemRegistry.tobie_gun_1));
            this.enchantEquipment();
        }

        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * difficulty.getClampedAdditionalDifficulty());
 //       this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * this.worldObj.(this.posX, this.posY, this.posZ));

        if (this.getEquipmentInSlot(4) == null)
        {
            Calendar calendar = this.worldObj.getCurrentDate();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F)
            {
                this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
                this.equipmentDropChances[4] = 0.0F;
            }
        }

        return entitylivingdata;
    }
   /**
    * Enchants the entity's armor and held item based on difficulty
    */
   public void enchantEquipment()
   {
       float f = 1.0F;

       if (this.getHeldItem() != null && this.rand.nextFloat() < 0.25F * f)
       {
           EnchantmentHelper.addRandomEnchantment(this.rand, this.getHeldItem(), (int)(5.0F + f * (float)this.rand.nextInt(18)));
       }

  //     for (int i = 0; i < 4; ++i)
  //     {
    	   
 //1.8 update not doubt this probably will not work :  change func_130225_q(i) to this getHeldItem   and dleteled for loop        for (int i = 0; i < 4; ++i)
    //       {	   
           ItemStack itemstack = this.getHeldItem();

           if (itemstack != null && this.rand.nextFloat() < 0.5F * f)
           {
               EnchantmentHelper.addRandomEnchantment(this.rand, itemstack, (int)(5.0F + f * (float)this.rand.nextInt(18)));
           }
 //      }
   }


    /**
     * sets this entity's combat AI.
     */
    public void setCombatTask()
    {
        this.tasks.removeTask(this.aiAttackOnCollide);
//        this.tasks.removeTask(this.aiArrowAttack);
        ItemStack itemstack = this.getHeldItem();

        if (itemstack != null && itemstack.getItem() == ItemRegistry.tobie_gun_1)
        {
            this.tasks.addTask(0, this.aiArrowAttack);
        }
        else
        {
            this.tasks.addTask(1, this.aiAttackOnCollide);
        }
    }

    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distance)
    {
 /*       EntityBlasterBolt entityblasterbolt = new EntityBlasterBolt(this.worldObj, this, target, 1.6F, (float)(14 - this.worldObj.getDifficulty().getDifficultyId() * 4));
        int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
        int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());
        entityblasterbolt.setDamage((double)(distance * 2.0F) + this.rand.nextGaussian() * 0.25D + (double)((float)this.worldObj.getDifficulty().getDifficultyId() * 0.11F));

        if (i > 0)
        {
            entityblasterbolt.setDamage(entityblasterbolt.getDamage() + (double)i * 0.5D + 0.5D);
        }

        if (j > 0)
        {
            entityblasterbolt.setKnockbackStrength(j);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItem()) > 0 || this.getSkeletonType() == 1)
        {
            entityblasterbolt.setFire(100);
        }*/
    	EntityBlasterBolt entityblasterbolt = new EntityBlasterBolt(this.worldObj, this, target, 500F, (float)(100));
        double d0 = target.posX - this.posX;
        double d1 = target.posY + (double)target.getEyeHeight() - 1.100000023841858D - entityblasterbolt.posY;
        double d2 = target.posZ - this.posZ;
        float f1 = MathHelper.sqrt_double(d0 * d0 + d2 * d2) * 0.2F;
        entityblasterbolt.setThrowableHeading(d0, d1 + (double)f1, d2, 0.6F, 12.0F);

        this.playSound("glistremod:laserblaster", 1.2F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityblasterbolt);
        

        }
            
            
    

    /**
     * Return this skeleton's type.
     */
    public int getSkeletonType()
    {
        return this.dataWatcher.getWatchableObjectByte(13);
    }

    /**
     * Set this skeleton's type.
     */
    public void setSkeletonType(int type)
    {
        this.dataWatcher.updateObject(13, Byte.valueOf((byte)type));
        this.isImmuneToFire = type == 1;

        if (type == 1)
        {
            this.setSize(0.72F, 2.34F);
        }
        else
        {
            this.setSize(0.6F, 1.8F);
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        if (compound.hasKey("SkeletonType", 99))
        {
            byte b0 = compound.getByte("SkeletonType");
            this.setSkeletonType(b0);
        }

        this.setCombatTask();

    }
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setByte("SkeletonType", (byte)this.getSkeletonType());
    }

    /**
     * Sets the held item, or an armor slot. Slot 0 is held item. Slot 1-4 is armor. Params: Item, slot
     */
    public void setCurrentItemOrArmor(int slot, ItemStack itemstack)
    {
        super.setCurrentItemOrArmor(slot, itemstack);

        if (!this.worldObj.isRemote && slot == 0)
        {
            this.setCombatTask();
        }
    }

    /**
     * Returns the Y Offset of this entity.
     */
    public double getYOffset()
    {
        return super.getYOffset() - 0.5D;
    }
}