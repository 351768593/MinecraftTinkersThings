package firok.tiths.common;

import firok.tiths.TinkersThings;
import firok.tiths.modding.ModdingInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 魔改工具注册表
 */
public class ModdingInfos
{
	public static List<ModdingInfo> moddings=new ArrayList<>();

	public static void registerModding(ModdingInfo info)
	{
		boolean pass=true;
		String error=null;
		FOR_FIND_EQUALS_INFO:for(ModdingInfo modding:moddings)
		{
			if(modding==info)
			{
				error=String.format("trying to register a modding info [%s] that has already been registered.",modding.type());
				pass=false;
				break FOR_FIND_EQUALS_INFO;
			}
			else if(modding.type().equals(info.type()))
			{
				error=String.format("trying to register a modding info with duplicated type name [%s].",info.type());
				pass=false;
				break FOR_FIND_EQUALS_INFO;
			}
			else if(modding.equalsToolInfo(info))
			{
				error=String.format("trying to register a modding info [%s] that has the same tool info with [%s].",info.type(),modding.type());
				pass=false;
				break FOR_FIND_EQUALS_INFO;
			}
		}
		if(!Configs.General.enable_strict_mode) // 禁用严格模式
		{
			if(error!=null)
			{
				TinkersThings.log(error);
			}
		}
		else if(error!=null) // 启用严格模式
		{
			throw new RuntimeException(error);
		}
		moddings.add(info);
	}
	;
}
