package com.glistre.glistremod.util;

import com.glistre.glistremod.blocks.fluids.ModFluids;
import com.glistre.glistremod.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.IFluidBlock;

public class GlistreModelManager   {  
	
	public static void GlistreMod(){
		registerAllModels();
	}

public static final GlistreModelManager INSTANCE = new GlistreModelManager();

private static final String FLUID_MODEL_PATH = (Reference.MOD_ID + ":" +  "rud");

public static void registerAllModels() {
        registerFluidModels();
}

private static void registerFluidModels() {
        for (IFluidBlock fluidBlock :ModFluids.fluidBlocks) {
                registerFluidModel(fluidBlock);
        }
}

private static void registerFluidModel(IFluidBlock fluidBlock) {
        Item item = Item.getItemFromBlock((Block) fluidBlock);

        ModelBakery.addVariantName(item);

        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(FLUID_MODEL_PATH, fluidBlock.getFluid().getName());

        ModelLoader.setCustomMeshDefinition(item, stack -> modelResourceLocation);

        ModelLoader.setCustomStateMapper((Block) fluidBlock, new StateMapperBase() {
                @Override
                protected ModelResourceLocation getModelResourceLocation(IBlockState p_178132_1_) {
                        return modelResourceLocation;
                }
        });
} 

}
