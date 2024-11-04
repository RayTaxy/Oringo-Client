package oringo.mixin;

import map.Class362;
import net.minecraft.client.renderer.chunk.SetVisibility;
import net.minecraft.client.renderer.chunk.VisGraph;
import net.minecraft.util.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({VisGraph.class})
public class VisGraphMixin {
   @Inject(
      method = {"computeVisibility"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method0(CallbackInfoReturnable var1) {
      if (Class362.field21.method44() || Class362.field43.method44()) {
         SetVisibility var2 = new SetVisibility();
         var2.setAllVisible(true);
         var1.setReturnValue(var2);
      }

   }

   @Inject(
      method = {"func_178606_a"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method1(BlockPos var1, CallbackInfo var2) {
      if (Class362.field21.method44() || Class362.field43.method44()) {
         var2.cancel();
      }

   }
}
