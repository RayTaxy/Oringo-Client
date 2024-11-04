package oringo.mixin;

import net.minecraft.world.storage.WorldInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import oringo.module.TimeChangerModule;

@Mixin({WorldInfo.class})
public class WorldInfoMixin {
   @Inject(
      method = {"getWorldTime"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method0(CallbackInfoReturnable var1) {
      TimeChangerModule var2 = TimeChangerModule.cO_;
      if (var2.method44() && !var2.field4.method1()) {
         var1.setReturnValue((long)(var2.field2.method0() * 240.0D));
         var1.cancel();
      }

   }
}
