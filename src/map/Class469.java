package map;

import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class Class469 {
   public static final Minecraft field0 = Minecraft.getMinecraft();
   public static final int field1 = -318865360;

   public static boolean method0() {
      return Class190.field0;
   }

   public static void method0(float var0, float var1, float var2, float var3, int var4) {
      float var5;
      if (var0 < var2) {
         var5 = var0;
         var0 = var2;
         var2 = var5;
      }

      if (var1 < var3) {
         var5 = var1;
         var1 = var3;
         var3 = var5;
      }

      var5 = (float)(var4 >> 24 & 255) / 255.0F;
      float var6 = (float)(var4 >> 16 & 255) / 255.0F;
      float var7 = (float)(var4 >> 8 & 255) / 255.0F;
      float var8 = (float)(var4 & 255) / 255.0F;
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color(var6, var7, var8, var5);
      Class479.field3.begin(7, DefaultVertexFormats.POSITION);
      Class479.field3.pos((double)var0, (double)var3, 0.0D).endVertex();
      Class479.field3.pos((double)var2, (double)var3, 0.0D).endVertex();
      Class479.field3.pos((double)var2, (double)var1, 0.0D).endVertex();
      Class479.field3.pos((double)var0, (double)var1, 0.0D).endVertex();
      Class479.field1.draw();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   public static ArrayList method1() {
      ArrayList var0 = new ArrayList(100);
      Class208[][] var1 = Class198.field1;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         Class208[] var4 = var1[var3];
         var0.addAll(Arrays.asList(var4));
      }

      return var0;
   }
}
