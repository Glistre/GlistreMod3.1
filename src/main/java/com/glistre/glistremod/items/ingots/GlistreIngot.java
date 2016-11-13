package com.glistre.glistremod.items.ingots;

import com.glistre.glistremod.GlistreMod;
import com.glistre.glistremod.reference.Reference;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
//import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class GlistreIngot extends Item {
    
    private String texturePath = (Reference.MOD_ID + ":" + "Glistre_Ingot");
    
    public GlistreIngot (Item ToolMaterial, String textureName)
    {
        super();
        this.setUnlocalizedName(textureName);
//        this.setTextureName("Glistre_Ingot");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

/*@Override   
@SideOnly(Side.CLIENT)

    public void registerIcons(IIconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon(texturePath);
    } */  


}