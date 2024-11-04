package oringo.mixin;

import map.Class362;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import net.minecraft.util.EnumFacing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import oringo.module.TimeChangerModule;

@Mixin({RenderGlobal.class})
public class RenderGlobalMixin {
   @Redirect(
      method = {"renderSky(FI)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/multiplayer/WorldClient;getCelestialAngle(F)F"
)
   )
   public float getCelestialAngle(WorldClient var1, float var2) {
      TimeChangerModule var3 = TimeChangerModule.cO_;
      return var3.method44() && var3.field4.method1() ? var3.field2.method1() / 100.0F : var1.getCelestialAngle(var2);
   }

   @Redirect(
      method = {"setupTerrain"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/renderer/chunk/CompiledChunk;isVisible(Lnet/minecraft/util/EnumFacing;Lnet/minecraft/util/EnumFacing;)Z"
)
   )
   public boolean isChunkInFrustum(CompiledChunk var1, EnumFacing var2, EnumFacing var3) {
      return var1.isVisible(var2, var3) || Class362.field43.method44();
   }

   @Redirect(
      method = {"renderSky(FI)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/multiplayer/WorldClient;getCelestialAngleRadians(F)F"
)
   )
   public float getCelestialAngleRadians(WorldClient var1, float var2) {
      TimeChangerModule var3 = TimeChangerModule.cO_;
      if (var3.method44() && var3.field4.method1()) {
         float var4 = var3.field2.method1() / 100.0F;
         return (float)((double)var4 * 3.141592653589793D * 2.0D);
      } else {
         return var1.getCelestialAngle(var2);
      }
   }
}
