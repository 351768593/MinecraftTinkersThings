package firok.tiths.traits;

import com.google.common.collect.ImmutableList;
import firok.tiths.TinkersThings;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.world.BlockEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import java.util.Iterator;
import java.util.List;

import static firok.tiths.common.Keys.colorTraitStonePhasing;
import static firok.tiths.common.Keys.nameTraitStonePhasing;
import static firok.tiths.util.Predicates.isStone;

// 石之相变
public class TraitStonePhasing extends AbstractTrait
{
	public static final String NBTKey= TinkersThings.MOD_ID+'_'+nameTraitStonePhasing;
	public TraitStonePhasing()
	{
		super(nameTraitStonePhasing, colorTraitStonePhasing);
	}

	@Override
	public void blockHarvestDrops(ItemStack tool, BlockEvent.HarvestDropsEvent event)
	{
		Iterator<ItemStack> iter=event.getDrops().iterator();

		while(iter.hasNext())
		{
			ItemStack stack=iter.next();
			if(isStone(stack))
			{
				iter.remove();
				NBTTagCompound nbt=tool.hasTagCompound()?tool.getTagCompound():new NBTTagCompound();
				int origin=nbt.hasKey(NBTKey)?nbt.getInteger(NBTKey):0;
				nbt.setInteger(NBTKey,origin+1);
				tool.setTagCompound(nbt);
			}
		}
	}

	@Override
	public List<String> getExtraInfo(ItemStack tool, NBTTagCompound modifierTag)
	{
		// I18n.translateToLocal(this.getUnlocalizedName()); // fixme
		NBTTagCompound nbt;
		int amount=tool.hasTagCompound()?((nbt=tool.getTagCompound()).hasKey(NBTKey)?nbt.getInteger(NBTKey):0):0;
		return ImmutableList.of("STONE_STORED"+amount);
	}


	public static boolean costStone(ItemStack stack)
	{
		NBTTagCompound nbt=stack.hasTagCompound()?stack.getTagCompound():new NBTTagCompound();
		int amount=nbt.hasKey(NBTKey)?nbt.getInteger(NBTKey):0;
		amount--;
		boolean success=amount>=0;
		if(success)
		{
			nbt.setInteger(NBTKey,success?amount:0);
			stack.setTagCompound(nbt);
		}
		return amount>=0;
	}
}
