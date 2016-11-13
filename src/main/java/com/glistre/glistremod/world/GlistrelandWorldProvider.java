/*import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.DimensionManager;

public class WorldProvider extends WorldProvider
{

	@Override
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManager(worldObj.getSeed(), terrainType);
		this.hasNoSky = true;
	}

	@Override
	public String getDimensionName()
	{
		return "Glistreland";
	}

	public static WorldProvider getProviderForDimension(int id)
	{
		return DimensionManager.createProviderFor(Glistreland.DIMENSIONID);
	}

	@Override
	public String getWelcomeMessage()
	{
		return "Welcome to the Glistreland";
	}

	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new RasterlandChunkProvider(worldObj, worldObj.getSeed(), true);
	}

	@Override
	public boolean canRespawnHere()
	{
		return true;
	}

	
	@SideOnly(Side.CLIENT)
	public boolean isSkyColored()
	{
		return false;
	}
	
	public float getCloudHeight()
	{
		return 0.0F;
	}
	
	public boolean isSurfaceWorld()
	{
		return false;
	}

	public float[] calcSunriseSunsetColors(float par1, float par2)
	{
		return null;
	}

	public Vec3 getFogColor(float par1, float par2)
	{
		return this.worldObj.getWorldVec3Pool().getVecFromPool(0.0, 0.0, 0.0);
	}

}*/