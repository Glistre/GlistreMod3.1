package com.glistre.glistremod.entities.king;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.glistre.glistremod.GlistreMod;
import com.glistre.glistremod.entities.blacktobie.EntityBlackTobo;
import com.glistre.glistremod.entities.guardian.EntityTobieSkel;
import com.glistre.glistremod.entities.wolf.EntityGlistreWolf;
import com.glistre.glistremod.init.GlistreEntityRegistry;
import com.glistre.glistremod.init.ItemRegistry;
import com.glistre.glistremod.items.swords.MightyIceSword;
import com.glistre.glistremod.projectiles.blaster.EntityBlasterBolt;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
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
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
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
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;

public class EntityTobieKing extends EntityMob implements IBossDisplayData, IRangedAttackMob

{
    private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
    private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.2D, false);
//    private static final String __OBFID = "CL_00001697";
    private static final ItemStack defaultHeldItem = new ItemStack(ItemRegistry.sceptre_1, 1);
    private int attackTimer;
//	private int tickCounter = 0;
//	private boolean counterEnabled = true;
    

    
    public EntityTobieKing(World world)
    {
        super(world);
        //add attack AI for TobieQueen to player next line
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, IMob.mobSelector));
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1,  new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
        this.tasks.addTask(1,  new EntityAIAttackOnCollide(this, EntityTobieSkel.class, 1.0D, true));      
        this.tasks.addTask(3, new EntityAIRestrictSun(this));
        this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
        this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(0, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(0, new EntityAIWatchClosest(this, EntityTobieSkel.class, 8.0F));
        
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(0, new EntityAINearestAttackableTarget(this, EntityTobieSkel.class, 2, true, false, IMob.mobSelector));
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
 //     raise health from 15.0D to 215.0D
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(215.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(10.0D);
    }

    protected void entityInit()
    { 
    	//changed 13 to 25 can go up to 31 better to use packets
        super.entityInit();
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
/*  public void onLivingUpdate()
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
 /*   public void updateRidden()
    {
        super.updateRidden();

        if (this.ridingEntity instanceof EntityCreature)
        {
            EntityCreature entitycreature = (EntityCreature)this.ridingEntity;
            this.renderYawOffset = entitycreature.renderYawOffset;
        }
    }*/

    /**
     * Called when the mob's health reaches 0.
     */
 /*   public void onDeath(DamageSource damageSource)
    {
        super.onDeath(damageSource);

        if (damageSource.getSourceOfDamage() instanceof EntityBlasterBolt && damageSource.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)damageSource.getEntity();
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
    protected void dropFewItems(boolean hasPlayerHit, int lootingLev)
    {
        int j;
        int k;

        if (this.getSkeletonType() == 0)
      
        {
        j = this.rand.nextInt(3 + lootingLev);

        for (k = 0; k < j; ++k)
        	{
            this.dropItem(GlistreEntityRegistry.ender_bolt_1, 1);
        	}
        }

        j = this.rand.nextInt(3 + lootingLev);

        for (k = 0; k < j; ++k)
        	{
            this.dropItem(ItemRegistry.glistre_dust, 1);
        	}
        
        j = this.rand.nextInt(3 + lootingLev);

        for (k = 0; k < j; ++k)
        	{
            this.dropItem(ItemRegistry.glistre_ingot, 1);
        	} 
    	//1.8 way to make 20% chance of drop
    	if(rand.nextInt(100)<20){
    		int p;
            int q;
            
        	p = this.rand.nextInt(3 + lootingLev) + 1;

    	for (q = 0; q < p; ++q)
        // what does float do here?  likelihood of drop?     	       	
           {
        	
        	this.entityDropItem(new ItemStack(GlistreEntityRegistry.tobie_worst_projectile_1, 1, 1), 1.0F);
           }
    	}
    }

/*    @Override
    protected void dropRareDrop(int num)
    { 
        if (this.getSkeletonType() == 0)
        	
        // what does float do here?       	       	
       
        {
            this.entityDropItem(new ItemStack(GlistreEntityRegistry.tobie_worst_projectile_1, 1, 1), 0.0F);
        } 
    }*/

    
    /**
     * Makes entity wear random armor based on difficulty
     */
   protected void addRandomArmor()
    {
 //       super.addRandomArmor();
       this.setCurrentItemOrArmor(0, new ItemStack(ItemRegistry.sceptre_1));
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
           this.setCurrentItemOrArmor(0, new ItemStack(ItemRegistry.sceptre_1));
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
        this.tasks.removeTask(this.aiArrowAttack);
        ItemStack itemstack = this.getHeldItem();

        if (itemstack != null && itemstack.getItem() == ItemRegistry.sceptre_1)
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
    public void attackEntityWithRangedAttack(EntityLivingBase entity, float distance)
    {
        EntityBlasterBolt entityblasterbolt = new EntityBlasterBolt(this.worldObj, this, entity, 1.6F, (float)(14 - this.worldObj.getDifficulty().getDifficultyId() * 4));
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
        }

        this.playSound("glistremod:laserblaster", 1.2F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        
        this.worldObj.spawnEntityInWorld(entityblasterbolt);
        
        //next part shoots fireballs from Tobie Queen
        //calculates the x,y,z DISTANCE between the projectile and the target. getLook() only gives the DIRECTION.

            double d0 = entity.posX - this.posX;
            double d1 = entity.getBoundingBox().minY + (double)(entity.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
            double d2 = entity.posZ - this.posZ;
            //calculating the accuracy (randomness) of the projectile. (The square root of the total distance divided by half. So, larger distances are more accurately fired.)
 
            float f1 = MathHelper.sqrt_float(distance) * 0.5F;
            BlockPos pos = new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ);
            this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1009, pos, 0);

            for (int k = 0; k < 1; ++k)
            {
         //This sets the x,y,z vector of the projectile, it uses the distance numbers calculated above, times a random number. (Because the Blaze fires randomly), 
         //you can take out "this.rand.nextGaussian() * (double)f1" and the projectile will always hit you dead on.    

                EntityLargeFireball entitylargefireball = new EntityLargeFireball(this.worldObj, this, d0 + this.rand.nextGaussian() * (double)f1, d1, d2 + this.rand.nextGaussian() * (double)f1);
        //This is where the projectile starts, the 0.5F is added to half the height to place it near normal size mobs mouth area , but for Queen should be 8.0 chest area, 8.9 mouth but
                // lower if preRenderOffset is higher value 4.0 puts at chest.            
   //mob.ghast.fireball
                entitylargefireball.posY = this.posY + (double)(this.height / 2.0F) + 4.0D;
                this.playSound("mob.blaze.hit", 1.2F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));

                this.worldObj.spawnEntityInWorld(entitylargefireball);
            }
        }
    @Override
    public void onLivingUpdate()
    {
      if (this.getHealth() < this.getMaxHealth() && this.ticksExisted % 400 == 0)
      {
       // this.heal(1.0F);
          this.playSound("mob.blaze.hit", 1.2F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
       
  	     worldObj.spawnParticle(EnumParticleTypes.PORTAL, posX + (rand.nextDouble() - 0.5D) * (double)width, posY + (rand.nextDouble() * (double)height), posZ + (rand.nextDouble() - 0.5D) * (double)width, 1.0D, 0.0D, 0.0D);

      }
      else if (this.getHealth() == 0 ){
          this.playSound("glistremod:sceptre_1", 1.2F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
          Minecraft.getMinecraft().thePlayer.addChatMessage(
					new ChatComponentText(EnumChatFormatting.DARK_RED + "You have AWAKENED TOBIE QUEEN ELIZABETH!"));
          worldObj.spawnParticle(EnumParticleTypes.PORTAL, posX + (rand.nextDouble() - 0.5D) * (double)width, posY + (rand.nextDouble() * (double)height), posZ + (rand.nextDouble() - 0.5D) * (double)width, 1.0D, 0.0D, 0.0D);
      }
      
      super.onLivingUpdate();
    }
   
   @Override
    public void onUpdate(){
    	super.onUpdate();
    	if (!(worldObj.isRemote && this.ticksExisted < 400)){
   /*     	NBTTagCompound nbt = new NBTTagCompound();
        	nbt.setBoolean("spawned", true);
        	if (nbt.getBoolean("spawned") == true && nbt.hasKey("spawned") ){*/
    	//gets list of players within 55 blocks - -very large radius
//		int r = 35; //radius   
		List<EntityPlayer> playerList = worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expand(155.0D, 75.0D, 155.0D));
		Iterator<EntityPlayer> i1 = playerList.iterator();
		EntityPlayer entityplayer;

              while (i1.hasNext())
              {
            	     entityplayer = (EntityPlayer)i1.next();		
			
	
			double inRange = this.getDistanceSqToEntity(entityplayer);
			
				//	 && !nbt.getTagCompound().getBoolean(KEY))
			if ( inRange < 1600.0D & inRange > 1580.0D)
				
/*				if (worldObj.spawnEntityInWorld(this) == true){
	        	NBTTagCompound nbt = this.getEntityData();
	        //	NBTTagCompound nbt = new NBTTagCompound();
	        	nbt.setBoolean("spawned", true);
				if(nbt.hasKey("spawned")){
			//	+ nbt.getBoolean("spawned")
				*/
				if(entityplayer.getHeldItem() !=null &&  entityplayer.getHeldItem().getItem() == ItemRegistry.mighty_sword){

		        			entityplayer.addChatComponentMessage(
                    		new ChatComponentText(EnumChatFormatting.DARK_GREEN + "Toby King in Range, Tower location" 
                    + EnumChatFormatting.DARK_RED + " X: " + (int)Math.round(this.posX) 
                    + EnumChatFormatting.GOLD + " | Y: " + (int)Math.round(this.posY) 
                    + EnumChatFormatting.DARK_AQUA +" | Z: " + (int)Math.round(this.posZ)));
			}
			
              }
    	}
		//DEBUG this below just tells me if its generating or not 
              //get and print location of Toby king when it spawns in world
   //     System.out.println("Toby King spawned in Tower in Freon Biome location X: " + (int)Math.round(this.posX) + " | Y: " + (int)Math.round(this.posY) + " | Z: " + (int)Math.round(this.posZ));
    			
    }

    
    @Override
    public boolean canDespawn(){
		return false;
    	
    }
    
 /*   @Override
    public boolean attackEntityFrom(DamageSource source, float amt) {
    	if (source.getDamageType().equals("player")) {
    		Entity ent = source.getEntity();

    		if (ent instanceof EntityPlayer && (this.worldObj.isRemote) ) {
    			EntityPlayer player = (EntityPlayer)ent;

    				player.attackEntityFrom(DamageSource.causeMobDamage(this), 2);
    				
    					if (this.getHealth() < this.getMaxHealth() && this.ticksExisted % 20 == 0)
    				      {
    						
    	                this.playSound("glistremod:sceptre_1", 1.2F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));

    		    	    worldObj.spawnParticle("reddust", posX + (rand.nextDouble() - 0.5D) * (double)width, posY + (rand.nextDouble() * (double)height), posZ + (rand.nextDouble() - 0.5D) * (double)width, 1.0D, 0.0D, 0.0D);
    				    
    				      }
    			}
    		}
    	

    	return super.attackEntityFrom(source, amt);
    }*/
               
    

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
    public void setSkeletonType(int bones)
    {
        this.dataWatcher.updateObject(13, Byte.valueOf((byte)bones));
        this.isImmuneToFire = bones == 1;

        if (bones == 1)
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

    public void readEntityFromNBT(NBTTagCompound tag)//this loads the mob to disc
    {
        super.readEntityFromNBT(tag);

        if (tag.hasKey("SkeletonType", 99))
        {
            byte b0 = tag.getByte("SkeletonType");
            this.setSkeletonType(b0);
//            tickCounter = tag.getInteger("tickCounter");
//    		counterEnabled = tag.getBoolean("counterEnabled");
        }

        this.setCombatTask();
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */

    public void writeEntityToNBT(NBTTagCompound tag)//this saves the mob to disc
    {
        super.writeEntityToNBT(tag);
        tag.setByte("SkeletonType", (byte)this.getSkeletonType());
//		tag.setInteger("tickCounter", tickCounter);
//		tag.setBoolean("counterEnabled", counterEnabled);
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
    
    @Override
    public ItemStack getHeldItem()
    {
        return defaultHeldItem;
    }


/*	@Override
	public void tick() {
		
		System.out.println("Tick");
		if(counterEnabled)
		{
			tickCounter++;
			
			if(tickCounter > 20)
			{
				tickCounter = 0;
				
 
			}
    
		}}*/
}