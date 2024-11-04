package oringo.mixin;

import map.Class168;
import net.minecraft.client.renderer.entity.RenderPainting;
import net.minecraft.entity.item.EntityPainting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oringo.module.OptimizationsModule;

@Mixin({RenderPainting.class})
public class RenderPaintingMixin {
   @Inject(
      method = {"renderPainting"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method0(EntityPainting var1, int var2, int var3, int var4, int var5, CallbackInfo var6) {
      if (OptimizationsModule.field0.method44()) {
         Class168.method0(var1, var2, var3, var4, var5);
         var6.cancel();
      }
   }
}
