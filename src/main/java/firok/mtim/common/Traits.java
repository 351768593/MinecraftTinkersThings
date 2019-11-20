package firok.mtim.common;

import firok.mtim.MoreTinkersMaterials;
import firok.mtim.traits.TraitIcy;
import firok.mtim.traits.TraitLuxurious;
import firok.mtim.traits.TraitRadiant;
import firok.mtim.traits.TraitSwitching;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import java.lang.reflect.Field;

public class Traits
{
	public static final AbstractTrait luxurious=new TraitLuxurious(); // 奢华
	public static final AbstractTrait radiant=new TraitRadiant(); // 辉耀
	public static final AbstractTrait switching=new TraitSwitching(); // 换位
	public static final AbstractTrait icy=new TraitIcy(); // 冰冷


	public static void register()
	{
		Field[] fields=Traits.class.getDeclaredFields();
		MoreTinkersMaterials.log("register traits...");
		for(Field field:fields)
		{
			try
			{
				Object obj=field.get(null);

				if(obj instanceof AbstractTrait)
				{
					AbstractTrait trait=(AbstractTrait)obj;
//					String name=field.getName();

					TinkerRegistry.addTrait(trait);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
