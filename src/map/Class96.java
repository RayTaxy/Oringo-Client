package map;

import net.minecraft.client.Minecraft;

public class Class96 {
   public static final int[] field0 = new int[32];

   static {
      for(int var0 = 0; var0 < 32; ++var0) {
         int var1 = (var0 >> 3 & 1) * 85;
         int var2 = (var0 >> 2 & 1) * 170 + var1;
         int var3 = (var0 >> 1 & 1) * 170 + var1;
         int var4 = (var0 & 1) * 170 + var1;
         if (var0 == 6) {
            var2 += 85;
         }

         if (Minecraft.getMinecraft().gameSettings.anaglyph) {
            int var5 = (var2 * 30 + var3 * 59 + var4 * 11) / 100;
            int var6 = (var2 * 30 + var3 * 70) / 100;
            int var7 = (var2 * 30 + var4 * 70) / 100;
            var2 = var5;
            var3 = var6;
            var4 = var7;
         }

         if (var0 >= 16) {
            var2 /= 4;
            var3 /= 4;
            var4 /= 4;
         }

         field0[var0] = (var2 & 255) << 16 | (var3 & 255) << 8 | var4 & 255;
      }

   }
}
