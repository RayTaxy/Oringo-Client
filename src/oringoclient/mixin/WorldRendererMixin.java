package oringo.mixin;

import java.nio.ByteOrder;
import java.nio.IntBuffer;
import map.Class362;
import net.minecraft.client.renderer.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import oringo.module.XRayModule;

@Mixin({WorldRenderer.class})
public abstract class WorldRendererMixin {
   @Shadow
   private IntBuffer rawIntBuffer;
   @Shadow
   private boolean noColor;

   @Overwrite
   public void putColorMultiplier(float var1, float var2, float var3, int var4) {
      int var5 = this.getColorIndex(var4);
      int var6 = -1;
      if (!this.noColor) {
         var6 = this.rawIntBuffer.get(var5);
         int var7;
         int var8;
         int var9;
         if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
            var7 = (int)((float)(var6 & 255) * var1);
            var8 = (int)((float)(var6 >> 8 & 255) * var2);
            var9 = (int)((float)(var6 >> 16 & 255) * var3);
            var6 = Class362.field21.method44() ? ((int)XRayModule.field4.method0() & 255) << 24 : var6 & -16777216;
            var6 = var6 | var9 << 16 | var8 << 8 | var7;
         } else {
            var7 = (int)((float)(var6 >> 24 & 255) * var1);
            var8 = (int)((float)(var6 >> 16 & 255) * var2);
            var9 = (int)((float)(var6 >> 8 & 255) * var3);
            var6 = (Class362.field21.method44() ? (int)XRayModule.field4.method0() : var6) & 255;
            var6 = var6 | var7 << 24 | var8 << 16 | var9 << 8;
         }
      }

      this.rawIntBuffer.put(var5, var6);
   }

   @Shadow
   public abstract int getColorIndex(int var1);
}
