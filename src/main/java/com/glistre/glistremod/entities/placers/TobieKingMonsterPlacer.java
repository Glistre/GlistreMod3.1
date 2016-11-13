package com.glistre.glistremod.entities.placers;
import java.util.List;

import com.glistre.glistremod.GlistreMod;
import com.glistre.glistremod.reference.Reference;
import com.glistre.glistremod.tabs.TabRegistry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
//import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
//import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeCache.Block;



public class TobieKingMonsterPlacer extends ItemMonsterPlacer
{
    @SideOnly(Side.CLIENT)
//    private IIcon theIcon;
    protected int colorBase = 0xFFFFFF;
    protected int colorSpots = 0xFFFF5D;

    protected String entityToSpawnName = "Tobie King";
    protected String entityToSpawnNameFull = "Tobie King";
    protected EntityLiving entityToSpawn = null;

    public TobieKingMonsterPlacer()
    {
        super();
        
    }
    
    public TobieKingMonsterPlacer(String entityName, int primarycolor, 
          int secondarycolor)
    {
        setHasSubtypes(false);
        maxStackSize = 64;
        setCreativeTab(TabRegistry.tab_custom);
        setEntityToSpawnName(entityName);
        colorBase = primarycolor;
        colorSpots = secondarycolor;
        // DEBUG
 //       System.out.println("Spawn egg constructor for "+entityToSpawnName);
    }

    /**
     * Called when a Block is right-clicked with this Item
     *  
     * @param pos The block being right-clicked
     * @param side The side being right-clicked
     */
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else if (!playerIn.canPlayerEdit(pos.offset(side), side, stack))
        {
            return false;
        }
        else
        {
            IBlockState iblockstate = worldIn.getBlockState(pos);

            if (iblockstate.getBlock() == Blocks.mob_spawner)
            {
                TileEntity tileentity = worldIn.getTileEntity(pos);

                if (tileentity instanceof TileEntityMobSpawner)
                {
                    MobSpawnerBaseLogic mobspawnerbaselogic = ((TileEntityMobSpawner)tileentity).getSpawnerBaseLogic();
                    mobspawnerbaselogic.setEntityName(ItemMonsterPlacer.getEntityName(stack));
                    tileentity.markDirty();
                    worldIn.markBlockForUpdate(pos);

                    if (!playerIn.capabilities.isCreativeMode)
                    {
                        --stack.stackSize;
                    }

                    return true;
                }
            }

            pos = pos.offset(side);
            double d0 = 0.0D;

            if (side == EnumFacing.UP && iblockstate instanceof BlockFence)
            {
                d0 = 0.5D;
            }

            Entity entity = spawnCreature(worldIn, ItemMonsterPlacer.getEntityName(stack), (double)pos.getX() + 0.5D, (double)pos.getY() + d0, (double)pos.getZ() + 0.5D);

            if (entity != null)
            {
                if (entity instanceof EntityLivingBase && stack.hasDisplayName())
                {
                    entity.setCustomNameTag(stack.getDisplayName());
                }

                if (!playerIn.capabilities.isCreativeMode)
                {
                    --stack.stackSize;
                }
            }

            return true;
        }
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. 
     *Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, 
          EntityPlayer playerIn)
    {
        if (worldIn.isRemote)
        {
            return itemStackIn;
        }
        else
        {
            MovingObjectPosition movingobjectposition = 
                  getMovingObjectPositionFromPlayer(worldIn, playerIn, true);

            if (movingobjectposition == null)
            {
                return itemStackIn;
            }
            else
            {
                if (movingobjectposition.typeOfHit == MovingObjectPosition
                      .MovingObjectType.BLOCK)
                {
                	BlockPos blockpos = movingobjectposition.getBlockPos();

                    if (!worldIn.isBlockModifiable(playerIn, blockpos))
                    {
                        return itemStackIn;
                    }

                    if (!playerIn.canPlayerEdit(blockpos, movingobjectposition
                          .sideHit, itemStackIn))
                    {
                        return itemStackIn;
                    }

                    if (worldIn.getBlockState(blockpos).getBlock() instanceof BlockLiquid)
                    {
                        Entity entity = spawnCreature(worldIn, ItemMonsterPlacer.getEntityName(itemStackIn), (double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.5D, (double)blockpos.getZ() + 0.5D);

                        if (entity != null)
                        {
                            if (entity instanceof EntityLivingBase && itemStackIn
                                  .hasDisplayName())
                            {
                                ((EntityLiving)entity).setCustomNameTag(itemStackIn
                                      .getDisplayName());
                            }

                            if (!playerIn.capabilities.isCreativeMode)
                            {
                                --itemStackIn.stackSize;
                            }
 
                            playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
                                             
                        }
                    }
                }

                return itemStackIn;
            }
        }
    }

    /**
     * Spawns the creature specified by the egg's type in the location specified by 
     * the last three parameters.
     * Parameters: world, entityID, x, y, z.
     */
    public Entity spawnEntity(World worldIn, int entityID, double x, double y, double z)
    {
     
       if (!worldIn.isRemote) // never spawn entity on client side
       {
            entityToSpawnNameFull = Reference.MOD_ID+"."+entityToSpawnName;
            if (EntityList.stringToClassMapping.containsKey(entityToSpawnNameFull))
            {
                entityToSpawn = (EntityLiving) EntityList
                      .createEntityByName(entityToSpawnNameFull, worldIn);
                entityToSpawn.setLocationAndAngles(x, y, z, 
                      MathHelper.wrapAngleTo180_float(worldIn.rand.nextFloat()
                      * 360.0F), 0.0F);
                entityToSpawn.rotationYawHead = entityToSpawn.rotationYaw;
                entityToSpawn.renderYawOffset = entityToSpawn.rotationYaw;
                worldIn.spawnEntityInWorld(entityToSpawn);
//1.8 not sure what this did changed entityliving below to entityToSpawn      entityToSpawn.onSpawnWithEgg((IEntityLivingData)null);
                entityToSpawn.func_180482_a(worldIn.getDifficultyForLocation(new BlockPos(entityToSpawn)), (IEntityLivingData)null);

                entityToSpawn.playLivingSound();
            }
            else
            {
                //DEBUG
                System.out.println("Entity not found "+entityToSpawnName);
            }
        }
      
        return entityToSpawn;
    }


    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
    {
        subItems.add(new ItemStack(itemIn, 1, 0));     
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int parColorType)
    {
        return (parColorType == 0) ? colorBase : colorSpots;
    }

/*    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }*/
    
    @Override
    // Doing this override means that there is no localization for language
    // unless you specifically check for localization here and convert
    public String getItemStackDisplayName(ItemStack itemStack)
    {
        return (EnumChatFormatting.GOLD + "Spawn "+entityToSpawnName);
    }  


/*    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        theIcon = par1IconRegister.registerIcon(getIconString() + "_overlay");
    }
    
    /**
     * Gets an icon index based on an item's damage value and the given render pass
     */
 /*   @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(int parDamageVal, int parRenderPass)
    {
        return parRenderPass > 0 ? theIcon : super.getIconFromDamageForRenderPass(parDamageVal, 
              parRenderPass);
    }*/
    
    public void setColors(int parColorBase, int parColorSpots)
    {
     colorBase = parColorBase;
     colorSpots = parColorSpots;
    }
    
    public int getColorBase()
    {
     return colorBase;
    }
    
    public int getColorSpots()
    {
     return colorSpots;
    }
    
    public void setEntityToSpawnName(String entityName)
    {
        entityToSpawnName = entityName;
        entityToSpawnNameFull = Reference.MOD_ID+"."+entityToSpawnName; 
    }

}