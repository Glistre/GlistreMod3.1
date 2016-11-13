package com.glistre.glistremod.entities.wolf;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;


public class BlackRenderWolf extends RenderLiving
{

    public BlackRenderWolf(RenderManager renderManager, ModelBase modelBase, float floatShadowSize)
    {
        super(renderManager, modelBase, floatShadowSize);

    }


	/**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return new ResourceLocation("glistremod:textures/entities/black_wolf.png");
    }
}
