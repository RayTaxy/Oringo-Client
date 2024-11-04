package oringo.mixin;

import map.Class488;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ReportedException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({RenderManager.class})
public abstract class RenderManagerMixin implements Class488 {
   @Shadow
   private double renderPosY;
   @Shadow
   private double renderPosX;
   @Shadow
   public TextureManager renderEngine;
   @Shadow
   private boolean renderOutlines;
   @Shadow
   private double renderPosZ;

   public void method0(EntityLivingBase var1, float var2) {
      Render var3 = this.getEntityRenderObject(var1);
      if (var3 instanceof RendererLivingEntity && this.renderEngine != null) {
         try {
            if (var1.ticksExisted == 0) {
               var1.lastTickPosX = var1.posX;
               var1.lastTickPosY = var1.posY;
               var1.lastTickPosZ = var1.posZ;
            }

            double var4 = var1.lastTickPosX + (var1.posX - var1.lastTickPosX) * (double)var2;
            double var6 = var1.lastTickPosY + (var1.posY - var1.lastTickPosY) * (double)var2;
            double var8 = var1.lastTickPosZ + (var1.posZ - var1.lastTickPosZ) * (double)var2;
            float var10 = var1.prevRotationYaw + (var1.rotationYaw - var1.prevRotationYaw) * var2;
            var3.doRender(var1, var4 - this.renderPosX, var6 - this.renderPosY, var8 - this.renderPosZ, var10, var2);
         } catch (Throwable var11) {
            throw new ReportedException(CrashReport.makeCrashReport(var11, "Rendering entity glow"));
         }
      }

   }

   @Shadow
   public abstract Render getEntityRenderObject(Entity var1);

   @Shadow
   public abstract Render getEntityClassRenderObject(Class var1);
}
