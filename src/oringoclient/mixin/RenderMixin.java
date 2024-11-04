package oringo.mixin;

import map.Class326;
import map.Class362;
import map.Class506;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oringo.module.OptimizationsModule;

@Mixin({Render.class})
public abstract class RenderMixin {
   @Inject(
      method = {"renderLivingLabel"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method0(Entity var1, String var2, double var3, double var5, double var7, int var9, CallbackInfo var10) {
      if (Class362.field22.method44() && var1 instanceof EntityOtherPlayerMP && Class506.method0(var1)) {
         Class362.field22.method0((EntityOtherPlayerMP)var1, var2, var3, var5, var7);
         var10.cancel();
      } else if (OptimizationsModule.field0.method44()) {
         Class326.method0(var1, var2, var3, var5, var7, var9);
         var10.cancel();
      }
   }

   @Shadow
   protected abstract boolean bindEntityTexture(Entity var1);

   @Shadow
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
   }
}
