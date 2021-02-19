package firok.tiths.intergration.orespawn;

import com.mcmoddev.orespawn.api.os3.OS3API;
import com.mcmoddev.orespawn.api.plugin.IOreSpawnPlugin;
import com.mcmoddev.orespawn.api.plugin.OreSpawnPlugin;
import firok.tiths.TinkersThings;

@OreSpawnPlugin(modid = TinkersThings.MOD_ID, resourcePath = "/worldgens.json")
public class TithsOreSpawnPlugin implements IOreSpawnPlugin {

	@Override
	public void register(final OS3API apiInterface) {
		// nothing for us to do - all of our ores are in the
		// jar and the code handles that
//		apiInterface.addFeature("tree-root",null);

	}
}
