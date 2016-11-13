package com.glistre.glistremod.tileentity;

import java.util.Map;

import com.glistre.glistremod.blocks.TileEntityGlistreChest;
import com.glistre.glistremod.init.BlockRegistry;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;


public class GlistreChestInventoryRenderer extends TileEntityItemStackRenderer {
	//the Tile entity you want to render
//private TileEntityGlistreChest te = new TileEntityGlistreChest();
private Map<Integer, TileEntityGlistreChest> itemRenders = Maps.newHashMap();
	
	public GlistreChestInventoryRenderer() {

		itemRenders.put(0, (TileEntityGlistreChest) new TileEntityGlistreChest());

	}

    @Override
    public void renderByItem(ItemStack itemStack) {
        Block block = Block.getBlockFromItem(itemStack.getItem());
//Your custom block
        if (block == BlockRegistry.glistre_chest) {
       //     TileEntityRendererDispatcher.instance.renderTileEntityAt(this.te, 0.0D, 0.0D, 0.0D, 0.0F);
			TileEntityRendererDispatcher.instance.renderTileEntityAt(itemRenders.get(0), 0.0D, 0.0D, 0.0D, 0.0F);

        }else {

//for minecraft to render its own tile entities such as the chest
            super.renderByItem(itemStack);
        }
    }
}

