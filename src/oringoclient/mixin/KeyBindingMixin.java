package oringo.mixin;

import java.util.Iterator;
import map.Class362;
import map.Class408;
import net.minecraft.client.settings.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oringo.module.Module;

@Mixin({KeyBinding.class})
public class KeyBindingMixin {
   @Inject(
      method = {"unPressAllKeys"},
      at = {@At("HEAD")}
   )
   private static void method0(CallbackInfo var0) {
      Iterator var1 = Class362.method0().iterator();

      while(var1.hasNext()) {
         Module var2 = (Module)var1.next();
         if (var2 instanceof Class408) {
            ((Class408)var2).method6();
         }
      }

   }
}
