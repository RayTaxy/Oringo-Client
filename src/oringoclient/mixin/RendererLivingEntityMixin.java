package oringo.mixin;

import java.util.Iterator;
import java.util.List;
import map.Class196;
import map.Class362;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.MathHelper;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oringo.event.Class125;

@Mixin(
   value = {RendererLivingEntity.class},
   priority = 1
)
public abstract class RendererLivingEntityMixin extends RenderMixin {
   @Shadow
   protected ModelBase mainModel;
   @Shadow
   protected boolean renderOutlines;
   @Shadow
   @Final
   private static Logger logger;
   @Shadow
   protected List layerRenderers;

   @Shadow
   protected abstract float interpolateRotation(float var1, float var2, float var3);

   @Shadow
   protected abstract void unsetScoreTeamColor();

   @Inject(
      method = {"preRenderCallback"},
      at = {@At("HEAD")}
   )
   private void method0(EntityLivingBase var1, float var2, CallbackInfo var3) {
      if (Class362.field53.method44() && Class362.field53.cn_.method1() && (!(var1 instanceof EntityArmorStand) || Class362.field53.field2.method1())) {
         GlStateManager.scale(Class362.field53.field0.method0(), Class362.field53.field0.method0(), Class362.field53.field0.method0());
      }

   }

   @Inject(
      method = {"doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method0(EntityLivingBase var1, double var2, double var4, double var6, float var8, float var9, CallbackInfo var10) {
      if (Class196.field3) {
         this.doRenderGlow(var1, var2, var4, var6, var8, var9);
         var10.cancel();
      }

   }

   @Shadow
   protected abstract float getSwingProgress(EntityLivingBase var1, float var2);

   @Shadow
   protected abstract void preRenderCallback(EntityLivingBase var1, float var2);

   @Shadow
   protected abstract boolean setDoRenderBrightness(EntityLivingBase var1, float var2);

   @Shadow
   protected abstract void renderLivingAt(EntityLivingBase var1, double var2, double var4, double var6);

   @Shadow
   protected abstract void rotateCorpse(EntityLivingBase var1, float var2, float var3, float var4);

   @Shadow
   protected abstract void unsetBrightness();

   @Shadow
   public abstract void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9);

   @Shadow
   protected abstract void renderModel(EntityLivingBase var1, float var2, float var3, float var4, float var5, float var6, float var7);

   @Shadow
   protected abstract boolean setScoreTeamColor(EntityLivingBase var1);

   public void doRenderGlow(EntityLivingBase var1, double var2, double var4, double var6, float var8, float var9) {
      GlStateManager.pushMatrix();
      GlStateManager.disableCull();
      this.mainModel.swingProgress = this.getSwingProgress(var1, var9);
      boolean var10 = var1.isRiding() && var1.ridingEntity != null && var1.ridingEntity.shouldRiderSit();
      this.mainModel.isRiding = var10;
      this.mainModel.isChild = var1.isChild();

      try {
         float var11 = this.interpolateRotation(var1.prevRenderYawOffset, var1.renderYawOffset, var9);
         float var12 = this.interpolateRotation(var1.prevRotationYawHead, var1.rotationYawHead, var9);
         float var13 = var12 - var11;
         float var15;
         if (var10 && var1.ridingEntity instanceof EntityLivingBase) {
            EntityLivingBase var14 = (EntityLivingBase)var1.ridingEntity;
            var11 = this.interpolateRotation(var14.prevRenderYawOffset, var14.renderYawOffset, var9);
            var13 = var12 - var11;
            var15 = MathHelper.wrapAngleTo180_float(var13);
            if (var15 < -85.0F) {
               var15 = -85.0F;
            }

            if (var15 >= 85.0F) {
               var15 = 85.0F;
            }

            var11 = var12 - var15;
            if (var15 * var15 > 2500.0F) {
               var11 += var15 * 0.2F;
            }
         }

         float var22 = var1.prevRotationPitch + (var1.rotationPitch - var1.prevRotationPitch) * var9;
         this.renderLivingAt(var1, var2, var4, var6);
         var15 = this.handleRotationFloat(var1, var9);
         this.rotateCorpse(var1, var15, var11, var9);
         GlStateManager.enableRescaleNormal();
         GlStateManager.scale(-1.0F, -1.0F, 1.0F);
         this.preRenderCallback(var1, var9);
         float var16 = 0.0625F;
         GlStateManager.translate(0.0F, -1.5078125F, 0.0F);
         float var17 = var1.prevLimbSwingAmount + (var1.limbSwingAmount - var1.prevLimbSwingAmount) * var9;
         float var18 = var1.limbSwing - var1.limbSwingAmount * (1.0F - var9);
         if (var1.isChild()) {
            var18 *= 3.0F;
         }

         if (var17 > 1.0F) {
            var17 = 1.0F;
         }

         GlStateManager.enableAlpha();
         this.mainModel.setLivingAnimations(var1, var18, var17, var9);
         this.mainModel.setRotationAngles(var18, var17, var15, var13, var22, 0.0625F, var1);
         if (this.bindEntityTexture(var1)) {
            this.mainModel.render(var1, var18, var17, var15, var13, var22, 0.0625F);
         }

         Iterator var19 = this.layerRenderers.iterator();

         while(var19.hasNext()) {
            LayerRenderer var20 = (LayerRenderer)var19.next();
            var20.doRenderLayer(var1, var18, var17, var9, var15, var13, var22, 0.0625F);
         }

         GlStateManager.disableRescaleNormal();
      } catch (Exception var21) {
         logger.error("Couldn't render entity", var21);
      }

      GlStateManager.enableCull();
      GlStateManager.popMatrix();
   }

   @Shadow
   protected abstract float handleRotationFloat(EntityLivingBase var1, float var2);

   @Shadow
   protected abstract void renderLayers(EntityLivingBase var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8);

   @Inject(
      method = {"renderLayers"},
      at = {@At("RETURN")},
      cancellable = true
   )
   protected void method0(EntityLivingBase var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, CallbackInfo var9) {
      if ((new Class125(var1, var2, var3, var5, var6, var7, var8, this.mainModel)).method7()) {
         var9.cancel();
      }

   }
}
