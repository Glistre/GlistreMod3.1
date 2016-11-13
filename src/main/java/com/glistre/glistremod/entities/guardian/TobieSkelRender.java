package com.glistre.glistremod.entities.guardian;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.glistre.glistremod.entities.blacktobie.BlackModelTobo;

@SideOnly(Side.CLIENT)
public class TobieSkelRender extends RenderBiped
{
    private static final ResourceLocation skeletonTextures = new ResourceLocation("glistremod:textures/entities/tobie_guardian.png");
//    private static final ResourceLocation witherSkeletonTextures = new ResourceLocation("glistremod:textures/entities/TobieQueenElizabeth.png");
//    private static final String __OBFID = "CL_00001023";

    public TobieSkelRender(RenderManager renderManager, BlackModelTobo myModelTobo, float f)
    {
        super(renderManager, new BlackModelTobo(), 0.5F);
    }

	/**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
   protected void preRenderCallback(EntityTobieSkel tobieskel, float p_77041_2_)
    {
	   
	   //skeleton type == 1 wither; 1.2F, 1.2F, 1.2F == normaal size , increase size without remodeling in Techne to 8.0F, 8.0F, 8.0F
        if (tobieskel.getSkeletonType() == 0)
        {
            GL11.glScalef(1.2F, 1.2F, 1.2F);
        }
    }

    public void func_82422_c()
    {
        GL11.glTranslatef(0.09375F, 0.1875F, 0.0F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityTobieSkel tobieskel)
    {
        return skeletonTextures;
        		//tobieskel.getSkeletonType() == 1 ? witherSkeletonTextures : skeletonTextures;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
/*    protected ResourceLocation getEntityTexture(EntityLiving tobieskel)
    {
        return this.getEntityTexture((EntityTobieSkel)tobieskel);
    }*/

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
   protected void preRenderCallback(EntityLivingBase tobieskel, float p_77041_2_)
    {
        this.preRenderCallback((EntityTobieSkel)tobieskel, p_77041_2_);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity tobieskel)
    {
        return this.getEntityTexture((EntityTobieSkel)tobieskel);
    }
}