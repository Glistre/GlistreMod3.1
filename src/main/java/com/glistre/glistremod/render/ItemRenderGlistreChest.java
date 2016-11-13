package com.glistre.glistremod.render;

import com.glistre.glistremod.blocks.TileEntityGlistreChest;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemRenderGlistreChest implements IItemRenderer{
	
	private ModelChest chestModel;
	
	public ItemRenderGlistreChest(){
		chestModel = new ModelChest();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityGlistreChest(), 0.0D, 0.0D, 0.0D, 0.0F);
		
	}

}
