package oringo.mixin;

import net.minecraft.world.storage.MapData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oringo.module.BossBarModule;
import oringo.module.CameraModule;

@Mixin(
   targets = {"net.minecraft.client.gui.MapItemRenderer.Instance"}
)
public class GuiMixin {
   @Shadow
   @Final
   private MapData mapData;

   @Inject(
      method = {"render"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void method0(boolean var1, CallbackInfo var2) {
      if (BossBarModule.method0(this.mapData, var1)) {
         CameraModule.method0(this.mapData);
         var2.cancel();
      }

   }
}
