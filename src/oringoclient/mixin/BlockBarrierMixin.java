package oringo.mixin;

import net.minecraft.block.BlockBarrier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import oringo.module.RenderBarriersModule;

@Mixin({BlockBarrier.class})
public class BlockBarrierMixin {
   @Inject(
      method = {"getRenderType"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void getRenderType$oringo(CallbackInfoReturnable var1) {
      if (RenderBarriersModule.field0.method44()) {
         var1.setReturnValue(3);
      }

   }
}
