package oringo.mixin;

import map.Class362;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({RenderPlayer.class})
public abstract class RenderPlayerMixin extends RendererLivingEntityMixin {
   @Shadow
   protected abstract void setModelVisibilities(AbstractClientPlayer var1);

   public void doRenderGlow(EntityLivingBase var1, double var2, double var4, double var6, float var8, float var9) {
      this.setModelVisibilities((AbstractClientPlayer)var1);
      super.doRenderGlow(var1, var2, var4, var6, var8, var9);
   }

   @Inject(
      method = {"preRenderCallback(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V"},
      at = {@At("HEAD")}
   )
   public void method0(AbstractClientPlayer var1, float var2, CallbackInfo var3) {
      if (Class362.field53.method44() && Class362.field53.field3.method1()) {
         GlStateManager.scale(Class362.field53.field0.method0(), Class362.field53.field0.method0(), Class362.field53.field0.method0());
      }

   }
}
