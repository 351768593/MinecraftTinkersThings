package firok.tiths.traits;

import firok.tiths.TinkersThings;
import firok.tiths.common.Configs;
import firok.tiths.util.InnerActions;
import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tools.ProjectileLauncherNBT;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.Tags;
import slimeknights.tconstruct.library.utils.TinkerUtil;

import static firok.tiths.common.Keys.colorTraitClustering;
import static firok.tiths.common.Keys.nameTraitClustering;

// 群簇
public class TraitClustering extends AbstractTrait
{
	public static final String NBTKey=TinkersThings.MOD_ID+'_'+nameTraitClustering;

	public TraitClustering()
	{
		super(nameTraitClustering, colorTraitClustering);
	}

	@Override
	public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
		if(InnerActions.addTrait(this,rootCompound,modifierTag)) return;

		NBTTagCompound tag = TagUtil.getToolTag(rootCompound);
		int durability=tag.getInteger(Tags.DURABILITY);
		float attack=tag.getFloat(Tags.ATTACK);
		float speed=tag.getFloat(Tags.ATTACKSPEEDMULTIPLIER);
		float mineSpeed=tag.getFloat(Tags.MININGSPEED);

		durability=Math.max(1,(int)(durability*0.95f));

		final int fac_durability = 550;
		final double fac_atk = 2;
		final double fac_spd = 1;
		final double fac_mspd = 1;
		final double fac_dspd = 0.08;

		// 计算因数 大于指定耐久度的百分比
		float factor ; // =Math.abs(1f * durability / Configs.Traits.factor_clustering_durability - 1);

//		factor=Math.max(factor,0.45f); // 因数最大为 0.45

		if(durability > fac_durability) // 大于指定耐久度
		{
			factor = (durability - fac_durability)/(float)fac_durability;
			if(factor>0.45f) factor=0.45f;

			attack += factor * fac_atk;
			tag.setFloat(Tags.ATTACK,attack);
		}
		else // 小于指定耐久度
		{
			factor = (fac_durability - durability)/(float)fac_durability;
			if(factor>0.45f) factor=0.45f;
			speed += factor * fac_mspd;
			mineSpeed += factor * fac_spd;

			if(TinkerUtil.hasCategory(rootCompound, Category.LAUNCHER)) {
				ProjectileLauncherNBT launcherData = new ProjectileLauncherNBT(TagUtil.getToolTag(rootCompound));
				launcherData.drawSpeed += launcherData.drawSpeed * fac_dspd;
				TagUtil.setToolTag(rootCompound, launcherData.get());
			}
			tag.setFloat(Tags.MININGSPEED,mineSpeed);
			tag.setFloat(Tags.ATTACKSPEEDMULTIPLIER,speed);
		}
	}

//	@Override
//	public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event) {
//		// 10% bonus speed
//		event.setNewSpeed(event.getNewSpeed() * (1 + bonus));
//	}

//	@Override
//	public List<String> getExtraInfo(ItemStack tool, NBTTagCompound modifierTag) {
//		String loc = String.format(LOC_Extra, getModifierIdentifier());
//
//		return ImmutableList.of(Util.translateFormatted(loc, Util.dfPercent.format(bonus)));
//	}
}