package oringo.mixin;

import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.tileentity.TileEntityChest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import oringo.event.Class464;

@Mixin({TileEntityChestRenderer.class})
public class TileEntityChestRendererMixin {
   @Inject(
      method = {"renderTileEntityAt(Lnet/minecraft/tileentity/TileEntityChest;DDDFI)V"},
      at = {@At("RETURN")}
   )
   public void method0(TileEntityChest var1, double var2, double var4, double var6, float var8, int var9, CallbackInfo var10) {
      if (var1 != null) {
         (new Class464.Class0(var1, var2, var4, var6, var8, var9)).method7();
      }

   }

   @Inject(
      method = {"renderTileEntityAt(Lnet/minecraft/tileentity/TileEntityChest;DDDFI)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void method1(TileEntityChest var1, double var2, double var4, double var6, float var8, int var9, CallbackInfo var10) {
      if (var1 != null && (new Class464.Class1(var1, var2, var4, var6, var8, var9)).method7()) {
         var10.cancel();
      }

   }
}
