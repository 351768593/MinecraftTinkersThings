package firok.tiths.client.render.entity;

import firok.tiths.entity.projectile.ProjectileJavelin;
import net.minecraft.client.renderer.entity.RenderManager;
import slimeknights.tconstruct.library.client.renderer.RenderProjectileBase;

public class RendererProjectileJavelin extends RenderProjectileBase<ProjectileJavelin>
{

	public RendererProjectileJavelin(RenderManager renderManager) {
		super(renderManager);
	}

//	@Override
//	protected void customCustomRendering(EntityArrow entity, double x, double y, double z, float entityYaw, float partialTicks) {
//		if(!entity.i) {
//			entity.roll += entity.rollSpeed * partialTicks;
//		}
//		float r = entity.roll;
//
//		GL11.glRotatef(r, 0f, 0f, 1f);
//	}
}