package com.glistre.glistremod.projectiles.tobyworstsword;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemPotion;
import net.minecraft.potion.PotionHelper;
//import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class TobyRenderProjectile extends Render
{
//    private Item field_94151_a;
	protected final Item something;
//    private int field_94150_f;
    private final RenderItem somethingElse;
    private static final ResourceLocation tobyWorstTextures = new ResourceLocation("glistremod:textures/tobie_worst_projectile_1.png");


    public TobyRenderProjectile(RenderManager renderManager, Item item, RenderItem renderItem) {
    	super (renderManager);
//    	 this.field_94151_a = item;
    	this.something = item;
//         this.field_94150_f = par2;
    	 this.somethingElse = renderItem;
	}
    
    public ItemStack func_177082_d(Entity entity)
	{
		return new ItemStack(this.something, 1, 0);
	}

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity entity, double x, double y, double z, float par8, float partialTicks)
    {

            GL11.glPushMatrix();
            GL11.glTranslatef((float)x, (float)y, (float)z);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            this.bindEntityTexture(entity);
            
            GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            this.bindTexture(TextureMap.locationBlocksTexture);
            this.somethingElse.renderItemModel(this.func_177082_d(entity));
            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            super.doRender(entity, x, y, z, par8, partialTicks);

    }

    protected boolean bindEntityTexture(Entity entity)
    {
        ResourceLocation resourcelocation = this.getEntityTexture(entity);

        if (resourcelocation == null)
        {
            return false;
        }
        else
        {
            this.bindTexture(resourcelocation);
            return true;
        }
    }

    public void bindTexture(ResourceLocation location)
    {
        this.renderManager.renderEngine.bindTexture(location);
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {

		return this.tobyWorstTextures;

	}
}
 
