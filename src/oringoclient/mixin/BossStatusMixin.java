package oringo.mixin;

import map.Class362;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({BossStatus.class})
public class BossStatusMixin {
   @Inject(
      method = {"setBossStatus"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void method0(IBossDisplayData var0, boolean var1, CallbackInfo var2) {
      if (var0.getDisplayName().getFormattedText().equals("Wither§r") || var0.getDisplayName().getFormattedText().equals("Ender Dragon§r") && Class362.field46.method44()) {
         var2.cancel();
      }

   }
}
