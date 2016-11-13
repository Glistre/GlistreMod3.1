package com.glistre.glistremod.effects;

import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.world.World;

public class EntityPortalFreonFX extends EntityPortalFX{

	public EntityPortalFreonFX(World world, double posX, double posY, double posZ,
            double parMotionX, double parMotionY, double parMotionZ) 
    {
        super(world, posX, posY, posZ, parMotionX, parMotionY, parMotionZ);
        setParticleTextureIndex(82); // see Jabelar list happy villager four-pointed stars 82, portalparticle shapes 0-7, 7 rainbow
        particleScale = 2.0F;
        setRBGColorF(1.000f, 0.843f, 0.000f); //red, green, blue? this is mostly green
    }
	

}
